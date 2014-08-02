package util.trace.console;

import util.trace.ImplicitKeywordKind;
import util.trace.MessagePrefixKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;

public class ConsoleTraceSetter {
	public static void traceConsole() {
		Tracer.showInfo(true);
		TraceableInfo.setPrintSource(true);
		TraceableInfo.setPrintTime(false);
		Tracer.setMessagePrefixKind(MessagePrefixKind.FULL_CLASS_NAME);
		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);

		setConsolePrintStatus();		
	}
	
	public static void setConsolePrintStatus() {
//		SessionTracerSetter.setSessionPrintStatus();
//		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);
		
		Tracer.setKeywordPrintStatus(ConsoleOutput.class, true);
		Tracer.setKeywordPrintStatus(ConsoleError.class, true);
		Tracer.setKeywordPrintStatus(ConsoleInput.class, true);



//		Tracer.setKeywordPrintStatus(MessageInSendingQueue.class, true);
//		Tracer.setKeywordPrintStatus(SentMessageDelayed.class, true);
//		Tracer.setKeywordPrintStatus(MessageSent.class, true);
//		Tracer.setKeywordPrintStatus(SendMessageRequest.class, true);
//		Tracer.setKeywordPrintStatus(MessageReceived.class, true);
//		Tracer.setKeywordPrintStatus(MessageRetrievedFromReceivingQueue.class, true);
//		Tracer.setKeywordPrintStatus(ReceivedMessageDelayed.class, true);
//		Tracer.setKeywordPrintStatus(ReceivedMessageDistributedToListeners.class, true);





	}

}
