package util.trace.session;

import util.trace.Tracer;

public class SessionTracerSetter {
	
	public static void traceSession() {
		Tracer.showInfo(true);
		setSessionPrintStatus();		
	}
	
	public static void setSessionPrintStatus() {
		Tracer.setKeywordPrintStatus(ThreadCreated.class, true);
		Tracer.setKeywordPrintStatus(QueueCreated.class, true);

		Tracer.setKeywordPrintStatus(ServerClientJoined.class, true);
		Tracer.setKeywordPrintStatus(ServerClientLeft.class, true);
		Tracer.setKeywordPrintStatus(MessagePutInQueue.class, true);
		Tracer.setKeywordPrintStatus(MessageRetrievedFromQueue.class, true);

		Tracer.setKeywordPrintStatus(SentMessageDelayed.class, true);
		Tracer.setKeywordPrintStatus(MessageSent.class, true);
		Tracer.setKeywordPrintStatus(SendDataRequest.class, true);
		Tracer.setKeywordPrintStatus(MessageReceived.class, true);
		Tracer.setKeywordPrintStatus(MessageRetrievedFromQueue.class, true);
		Tracer.setKeywordPrintStatus(ReceivedMessageDelayed.class, true);
		Tracer.setKeywordPrintStatus(ReceivedMessageDistributedToListeners.class, true);
		Tracer.setKeywordPrintStatus(SendDataRequest.class, true);
		Tracer.setKeywordPrintStatus(SentMessageDelayed.class, true);
	}

}
