package util.trace.session;

import util.trace.Tracer;
import util.trace.session.MessageInSendingQueue;

import util.trace.session.SendDataRequest;

public class SessionTracerSetter {
	
	public static void traceSession() {
		Tracer.showInfo(true);
		setSessionPrintStatus();		
	}
	
	public static void setSessionPrintStatus() {
		Tracer.setKeywordPrintStatus(ClientJoined.class, true);
		Tracer.setKeywordPrintStatus(ClientLeft.class, true);
		Tracer.setKeywordPrintStatus(MessageInSendingQueue.class, true);
		Tracer.setKeywordPrintStatus(SentMessageDelayed.class, true);
		Tracer.setKeywordPrintStatus(MessageSentNew.class, true);
		Tracer.setKeywordPrintStatus(SendDataRequest.class, true);
		Tracer.setKeywordPrintStatus(MessageReceived.class, true);
		Tracer.setKeywordPrintStatus(MessageRetrievedFromReceivingQueueNew.class, true);
		Tracer.setKeywordPrintStatus(ReceivedMessageDelayed.class, true);
		Tracer.setKeywordPrintStatus(ReceivedMessageDistributedToListenersNew.class, true);


	}

}
