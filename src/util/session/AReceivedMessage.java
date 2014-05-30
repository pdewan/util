package util.session;

import java.util.Map;

public class AReceivedMessage implements ReceivedMessage {
	ReceivedMessageType receivedMessageType;
	String clientName;
	String applicationName;
	Object newObject;
	long timeStamp;
	MessageReceiver client;
	boolean newSession;
	boolean newApplication;
	String sessionName;
	boolean isUserMessage;
	Map<MessageReceiver, String> clients;
	SerializedProcessGroups serializedProcessGroups;
	ProcessGroup processGroup;

	public AReceivedMessage(ReceivedMessageType theReceivedMessageType,
			String theClientName, MessageReceiver theClient,
			String theApplicationName, Object theNewObject,
			boolean theNewSession, boolean theNewApplication,
			Map<MessageReceiver, String> theClients,
			SerializedProcessGroups theSerializedProcessGroups,
			ProcessGroup theProcessGroup) {
		receivedMessageType = theReceivedMessageType;
		clientName = theClientName;
		newObject = theNewObject;
		timeStamp = System.currentTimeMillis();
		client = theClient;
		applicationName = theApplicationName;
		newSession = theNewSession;
		newApplication = theNewApplication;
		isUserMessage = receivedMessageType == ReceivedMessageType.NewObject;
		clients = theClients;
		serializedProcessGroups = theSerializedProcessGroups;
		processGroup = theProcessGroup;
	}

	public ProcessGroup getProcessGroup() {
		return processGroup;
	}

	public void setProcessGroup(ProcessGroup processGroup) {
		this.processGroup = processGroup;
	}

	public SerializedProcessGroups getSerializedProcessGroups() {
		return serializedProcessGroups;
	}

	public void setSerializedProcessGroups(
			SerializedProcessGroups serializedProcessGroups) {
		this.serializedProcessGroups = serializedProcessGroups;
	}

	public AReceivedMessage() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.ReceivedMessage#getReceivedMessageType()
	 */
	public ReceivedMessageType getReceivedMessageType() {
		return receivedMessageType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.ReceivedMessage#setReceivedMessageType(util.session.
	 * ReceivedMessageType)
	 */
	public void setReceivedMessageType(ReceivedMessageType receivedMessageType) {
		this.receivedMessageType = receivedMessageType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.ReceivedMessage#getClientName()
	 */
	public String getClientName() {
		return clientName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.ReceivedMessage#setClientName(java.lang.String)
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.ReceivedMessage#getApplicationName()
	 */
	public String getApplicationName() {
		return applicationName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.ReceivedMessage#setApplicationName(java.lang.String)
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.ReceivedMessage#getNewObject()
	 */
	public Object getUserMessage() {
		return newObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.ReceivedMessage#setNewObject(java.lang.Object)
	 */
	public void setUserMessage(Object newObject) {
		this.newObject = newObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.ReceivedMessage#getTimeStamp()
	 */
	public long getTimeStamp() {
		return timeStamp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.ReceivedMessage#setTimeStamp(long)
	 */
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.ReceivedMessage#getClient()
	 */
	public MessageReceiver getClient() {
		return client;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.ReceivedMessage#setClient(util.session.MessageReceiver)
	 */
	public void setClient(MessageReceiver client) {
		this.client = client;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.ReceivedMessage#isNewSession()
	 */
	public boolean isNewSession() {
		return newSession;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.ReceivedMessage#setNewSession(boolean)
	 */
	public void setNewSession(boolean newSession) {
		this.newSession = newSession;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.ReceivedMessage#isNewApplication()
	 */
	public boolean isNewApplication() {
		return newApplication;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.ReceivedMessage#setNewApplication(boolean)
	 */
	public void setNewApplication(boolean newApplication) {
		this.newApplication = newApplication;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.ReceivedMessage#getSessionName()
	 */
	public String getSessionName() {
		return sessionName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.ReceivedMessage#setSessionName(java.lang.String)
	 */
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	@Override
	public boolean isUserMessage() {
		return isUserMessage;
	}

	@Override
	public Map<MessageReceiver, String> getClients() {
		return clients;
	}

	@Override
	public void setClients(Map<MessageReceiver, String> clients) {
		this.clients = clients;

	}

//	public String toString() {
//		return "message object: " + getUserMessage();
//	}

	public static ReceivedMessage toReceivedMessage(SentMessage sentMessage) {
		ReceivedMessage receivedMessage = new AReceivedMessage();
		receivedMessage.setReceivedMessageType(ReceivedMessageType.NewObject);
		receivedMessage.setUserMessage(sentMessage.getUserMessage());
		return receivedMessage;
	}
	
	public String toString() {
		return "[" +	
				receivedMessageType + "," + 
				timeStamp + 
//				"[" + sessionName + ", " + applicationName + ", " + clientName + ", " + client;
				(getUserMessage() != null? "," + getUserMessage():"")
				+ "]" ;

				
	}

}
