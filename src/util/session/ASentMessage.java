package util.session;

import util.misc.Common;
import util.trace.Tracer;

public class ASentMessage implements SentMessage {

	SentMessageType sentMessageType;
	String sendingUser;
	Object args[];
	long timeStamp;
	boolean isUserMessage;
	String applicationName;
	String sessionName;

	public ASentMessage(String theSessionName, String theApplicationName,
			String theSendingUser, MessageReceiver theClient,
			SentMessageType theMessageType, Object[] theArgs) {
		applicationName = theApplicationName;
		sessionName = theSessionName;
		sendingUser = theSendingUser;
		sentMessageType = theMessageType;
		messageReceiver = theClient;
		args = theArgs;
		timeStamp = System.currentTimeMillis();
		isUserMessage = theMessageType != SentMessageType.Join
				&& theMessageType != SentMessageType.Leave;
	}


	public MessageReceiver getMessageReceiver() {
		return messageReceiver;
	}

	public void setMessageReceiver(MessageReceiver messageReceiver) {
		this.messageReceiver = messageReceiver;
	}

	MessageReceiver messageReceiver;

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.SentMessage#getSenttMessageType()
	 */
	public SentMessageType getSentMessageType() {
		return sentMessageType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * util.session.SentMessage#setSenttMessageType(util.session.SentMessageType
	 * )
	 */
	public void setSentMessageType(SentMessageType sentMessageType) {
		this.sentMessageType = sentMessageType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.SentMessage#getArgs()
	 */
	public Object[] getArgs() {
		return args;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.SentMessage#setArgs(java.lang.Object[])
	 */
	public void setArgs(Object[] args) {
		this.args = args;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.SentMessage#getTimeStamp()
	 */
	public long getTimeStamp() {
		return timeStamp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.SentMessage#setTimeStamp(long)
	 */
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public Object getUserMessage() {
		if (sentMessageType.equals(SentMessageType.Others)
				|| sentMessageType.equals(SentMessageType.All))
			return args[0];
		else if (sentMessageType.equals(SentMessageType.User)
				|| sentMessageType.equals(SentMessageType.Hosts))
			return args[1];
		else
			return null;
	}

	@Override
	public void setUserMessage(Object theMessage) {
		if (sentMessageType.equals(SentMessageType.Others)
				|| sentMessageType.equals(SentMessageType.All))
			args[0] = theMessage;
		else if (sentMessageType.equals(SentMessageType.User)
				|| sentMessageType.equals(SentMessageType.Hosts))
			args[1] = theMessage;

	}

	@Override
	public boolean isUserMessage() {
		return isUserMessage;
	}

	@Override
	public String getSendingUser() {
		return sendingUser;
	}

	@Override
	public void setSendingUser(String theUser) {
		sendingUser = theUser;
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	public static SentMessage toSpecificUser(SentMessage originalMessage,
			String toUser) {
		if (originalMessage.getSentMessageType() != SentMessageType.Others) {

			Tracer.error("toSpecificUser: Exceptng message targetted at others, returning it unmodified");
			return originalMessage;
		}
		SentMessage retVal = (SentMessage) Common.deepCopy(originalMessage);
		retVal.setSentMessageType(SentMessageType.User);
		Object[] args = originalMessage.getArgs();
		Object[] newArgs = { toUser, args[0], args[1], args[2] };
		retVal.setArgs(newArgs);
		return retVal;
	}
	public String toString() {
		return //sendingUser + "->" + 
				"[" +	
				sentMessageType + "," + 
				timeStamp + 
//				"[" + Common.toString(args) + "]";
				(getUserMessage() != null? "," + getUserMessage():"") +

//				getUserMessage() + 
				"]";
	
		
				
	}
}
