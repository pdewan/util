package util.session;

import java.io.Serializable;
/*
 * This is the message sent on an RPC call. RMI calls from clients at the
 * local end are ummarshalled into this message so that a uniform set of filters
 * filters can be employed such as delays and jitter and causality
 * I guess causality and OT do not require it but certainly delay does
 * as well as time stamping   and any kind of embellishment
 * making a direct RMI call does nto give flexibility, better to make
 * a local call and then have a local unmarshall and then a remote call
 * so extensibility vs abstraction is an issue
 * still need RMI references so it is not as if RMI is no good
 * this is a multicast message, where the destination is not bound
 * It has to be multiplexed into different ReceiveMessages
 * At the server end, this is receivged even tho it is called a sent message, 
 * and it is converted into a received message later
 */ 
//public interface SentMessage extends Serializable, util.models.CorrectCloneable {
public interface SentMessage extends GroupMessage, util.models.CorrectCloneable {


	public abstract SentMessageType getSentMessageType();

	public abstract void setSentMessageType(SentMessageType senttMessageType);

	public abstract Object[] getArgs();

	public abstract void setArgs(Object[] args);

//	public abstract long getTimeStamp();
//
//	public abstract void setTimeStamp(long timeStamp);
//
//	public boolean isUserMessage();
//
//	public Object getUserMessage();
//
//	public void setUserMessage(Object theMessage);
//
	public String getSendingUser();

	public void setSendingUser(String theUser);

//	public String getApplicationName();
//
//	public void setApplicationName(String applicationName);
//
//	public String getSessionName();
//
//	public void setSessionName(String sessionName);

	public ObjectReceiver getMessageReceiver();

	public void setMessageReceiver(ObjectReceiver messageReceiver);

}