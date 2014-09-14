package util.session;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.misc.ThreadSupport;
import util.trace.Tracer;
import util.trace.session.MessagePutInQueue;
import util.trace.session.MessageRetrievedFromQueue;
import util.trace.session.MessageSent;
import util.trace.session.SentMessageDelayed;

@util.annotations.StructurePattern(util.annotations.StructurePatternNames.BEAN_PATTERN)
/*
 * should probably be created by factory
 * does not actually delay, ssimply keeps teh delays
 * aclso delays, had a threrad for peers but keeps delays to server
 * bad design!
 */
public class ADelayManager implements DelayManager {

	int delayToServer, delayVariation;
	Map<String, Integer> peerDelays = new HashMap();
	List<UserDelayRecord> sortedDelayRecords = new LinkedList(); // really need a queue
	Communicator communicator;
	Thread delayThread;
	public static final String DELAY_THREAD_NAME = "Message Sender";
	public static final String DELAY_QUEUE_NAME = "Delay Queue";
	String destination;

	public ADelayManager(Communicator theCommunicator, String aDestination) {
		communicator = theCommunicator;
		destination = aDestination;
	}
	
	@Override
	public void createThread() {
		if (delayThread != null) return;
		delayThread = new Thread(this);
		delayThread.setName(DELAY_THREAD_NAME + 
				"(" + communicator.getSessionName() + ", " + communicator.getApplicationName() + ", " +  destination + ")");
		delayThread.start();
		
		
	}
	
	@Override
	public void run() {
		while (true) {
			processNextMessage();
		}
		
	}
	synchronized void waitForNonEmptyQueue() {
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	synchronized void notifyNonEmptyQueue() {
		notify();
	}
	 void processNextMessage() {
		if (sortedDelayRecords.size() == 0)
			waitForNonEmptyQueue();
		// process one pending message at a time as additional messages may be added in between
		// the current messages. The thread is locked until the current message
		// is sent
		UserDelayRecord aUserDelayRecord = sortedDelayRecords.get(0);
		sortedDelayRecords.remove(0);
	
		long currentTime = System.currentTimeMillis();
		long delay = aUserDelayRecord.getDeliveryTime() - currentTime;
		ReceivedMessage aReceivedMessage = aUserDelayRecord.getReceivedMessage();
		if (aReceivedMessage == null) {
			System.out.println("Null received message!");
			return;
		}
			
		MessageReceiver aClient = aUserDelayRecord.getClient();
//		System.out.println("delivery time:" + aUserDelayRecord.getDeliveryTime() + " currentTime:" + currentTime +
//		" TS:" + aReceivedMessage.getTimeStamp());
		String aName = aUserDelayRecord.getName();
		MessageRetrievedFromQueue.newCase(
				CommunicatorSelector.getProcessName(), 
				aUserDelayRecord, 
				aReceivedMessage.getClientName(),
				DELAY_QUEUE_NAME,
				this);
		if (delay > 0) {
//			Tracer.info(this, "Client delaying sending message to absolute time: " + messageTime + " and delay:" +
//					  actualDelay + " and time stamp:" + aReceivedMessage.getTimeStamp());
			SentMessageDelayed.newCase(CommunicatorSelector.getProcessName(), 
					aReceivedMessage, aName, delay, this);

			ThreadSupport.sleep(delay);
		} 
//		else {
//			System.out.println("Message not delayed, delay:" + delay + "TS:" + aReceivedMessage.getTimeStamp());
//		}
	
		
		try {
			MessageSent.newCase(CommunicatorSelector.getProcessName(),
					aReceivedMessage , aName,  this);

			aClient.newMessage(aReceivedMessage);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		MessageSent.newCase(CommunicatorSelector.getProcessName(), receivedMessage , clients.get(client),  this);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.MessageDelayer#getMinimumDelayToServer()
	 */
	public int getMinimumDelayToServer() {
		return delayToServer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.MessageDelayer#setMinimumDelayToServer(int)
	 */
	public void setMinimumDelayToServer(int theDelay) {
		delayToServer = theDelay;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.MessageDelayer#getDelayVariation()
	 */
	public int getDelayVariation() {
		return delayVariation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.MessageDelayer#setDelayVariation(int)
	 */
	public void setDelayVariation(int theDelay) {
		delayVariation = theDelay;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.MessageDelayer#calculateDelay(long)
	 */
	public long calculateDelay(long messageTime) {
		return calculateDelay(messageTime, getMinimumDelayToServer(),
				getDelayVariation());

	}

	public static long calculateDelay(long messageTime, int minimumDelay,
			int delayVariation) {
		if (minimumDelay == 0) return 0; // as there is only one delay variation for all sites, we dont want sites with no dleay to have variation
//		System.out.println("delayvariation:" + delayVariation + " min delay:" + minimumDelay);
		double random = Math.random();
//		System.out.println("random:" + random);
		long randomDelay = Math.round(delayVariation * random);
//		System.out.println("random delay:" + randomDelay);
		long delayIncurred = (System.currentTimeMillis() - messageTime);
//		System.out.println("delay incurred:" + delayIncurred);

		long actualDelay = randomDelay + minimumDelay
				- delayIncurred;
//				- (System.currentTimeMillis() - messageTime);
//		System.out.println("actual delay:" + actualDelay);

		
		return actualDelay;
	}

	public void refreshClients() {
		if (communicator.getClients().size() == sortedDelayRecords.size())
			return;
		Set<ObjectReceiver> keys = communicator.getClients().keySet();
		sortedDelayRecords.clear();
		for (ObjectReceiver client : keys) {
			sortedDelayRecords
					.add(new AUserDelayRecord(client, communicator.getClients()
							.get(client), getMinimumDelayToPeer(communicator
							.getClients().get(client)), null));
		}
	}
	@Override
	public  void addMessage(ReceivedMessage aReceivedMessage, ObjectReceiver aClient, long aDelay) {
			MessagePutInQueue.newCase(
					CommunicatorSelector.getProcessName(), 
					aReceivedMessage, aReceivedMessage.getClientName(), DELAY_QUEUE_NAME, this);

//			sortedDelayRecords
//					.add(new AUserDelayRecord(aClient, 
//							communicator.getClients()
//							.get(aClient), getMinimumDelayToPeer(communicator
//							.getClients().get(aClient)), aReceivedMessage));
			sortedDelayRecords
			.add(new AUserDelayRecord(aClient, 
					communicator.getClients()
					.get(aClient), aDelay, aReceivedMessage));
			// messages have to go in FIFO order
			if (!((CommunicatorInternal) communicator).isOrderedDelivery())
			Collections.sort(sortedDelayRecords);
			if (sortedDelayRecords.size() == 1)
				notifyNonEmptyQueue();
			
		}
	
	
	
	
	
	
	
	

	public void refreshAndSortClients() {
		refreshClients();
		Collections.sort(sortedDelayRecords);
	}
	

//	public void refreshAndSortClients(Object aMessage, ObjectReceiver aClient) {
//		addMessage(aMessage, aClient);
//		Collections.sort(sortedClients);
//	}
	
	


	public void setMinimumDelayToPeer(String thePeer, int theDelay) {
		peerDelays.put(thePeer, theDelay);
		// not sure we want to refresh clients or sort past clients
//		refreshClients();
//		UserDelayRecord userDelayRecord = getUserDelayRecord(thePeer);
//		if (userDelayRecord == null)
//			return;
//		userDelayRecord.setDelay(theDelay);
//		Collections.sort(sortedDelayRecords);

	}

	public int getMinimumDelayToPeer(String thePeer) {
		Integer retVal = peerDelays.get(thePeer);
		if (retVal == null)
			return 0;
		else
			return retVal;
	}

	UserDelayRecord getUserDelayRecord(String client) {
		for (UserDelayRecord record : sortedDelayRecords) {
			if (record.getName().equals(client))
				return record;
		}
		return null;
	}
	/*
	 * clients are sorted for delta level processing
	 * @see util.session.DelayManager#getSortedClients()
	 */
	public List<UserDelayRecord> getSortedDelayRecords() {
		return sortedDelayRecords;
	}



	
}
