package util.trace.session;

import util.trace.Tracer;
import util.trace.session.MessageInSendingQueue;

import util.trace.session.SendMessageRequest;

public class SessionTracerSetter {
	
	public static void traceSession() {
		Tracer.showInfo(true);
		setSessionPrintStatus();		
	}
	
	public static void setSessionPrintStatus() {
		Tracer.setKeywordPrintStatus(ClientJoined.class, true);
		Tracer.setKeywordPrintStatus(ClientLeftNew.class, true);
		Tracer.setKeywordPrintStatus(MessageInSendingQueue.class, true);
		Tracer.setKeywordPrintStatus(SentMessageDelayedNew.class, true);
		Tracer.setKeywordPrintStatus(MessageSentNew.class, true);
		Tracer.setKeywordPrintStatus(SendMessageRequest.class, true);
		Tracer.setKeywordPrintStatus(MessageReceivedNew.class, true);
		Tracer.setKeywordPrintStatus(MessageRetrievedFromReceivingQueueNew.class, true);
		Tracer.setKeywordPrintStatus(ReceivedMessageDelayedNew.class, true);
		Tracer.setKeywordPrintStatus(ReceivedMessageDistributedToListenersNew.class, true);


	}

}
