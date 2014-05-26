package util.trace.session;

import util.trace.Tracer;
import util.trace.session.MessageInSendingQueue;
import util.trace.session.MessageReceived;
import util.trace.session.MessageRetrievedFromReceivingQueue;
import util.trace.session.MessageSent;
import util.trace.session.ReceivedMessageDelayed;
import util.trace.session.ReceivedMessageDistributedToListeners;
import util.trace.session.SendMessageRequest;
import util.trace.session.SentMessageDelayed;

public class SessionTracerSetter {
	
	public static void traceSession() {
		Tracer.showInfo(true);
		setSessionPrintStatus();		
	}
	
	public static void setSessionPrintStatus() {		
		Tracer.setKeywordPrintStatus(MessageInSendingQueue.class, true);
		Tracer.setKeywordPrintStatus(SentMessageDelayed.class, true);
		Tracer.setKeywordPrintStatus(MessageSent.class, true);
		Tracer.setKeywordPrintStatus(SendMessageRequest.class, true);
		Tracer.setKeywordPrintStatus(MessageReceived.class, true);
		Tracer.setKeywordPrintStatus(MessageRetrievedFromReceivingQueue.class, true);
		Tracer.setKeywordPrintStatus(ReceivedMessageDelayed.class, true);
		Tracer.setKeywordPrintStatus(ReceivedMessageDistributedToListeners.class, true);





	}

}
