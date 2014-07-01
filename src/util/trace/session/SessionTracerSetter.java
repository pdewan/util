package util.trace.session;

import util.trace.ImplicitKeywordKind;
import util.trace.Tracer;

public class SessionTracerSetter {
	
	public static void traceSession() {
		Tracer.showInfo(true);
		setSessionPrintStatus();		
	}
	
	public static void setSessionPrintStatus() {
		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_PACKAGE_NAME);
		Tracer.setKeywordPrintStatus(ThreadCreated.class, true);
		Tracer.setKeywordPrintStatus(QueueCreated.class, true);

		Tracer.setKeywordPrintStatus(ServerClientJoined.class, true);
		Tracer.setKeywordPrintStatus(ServerClientLeft.class, true);
		Tracer.setKeywordPrintStatus(ClientJoinInitiated.class, true);
		Tracer.setKeywordPrintStatus(ClientJoinFinished.class, true);		
		Tracer.setKeywordPrintStatus(ClientJoinInformationUpdated.class, true);
		Tracer.setKeywordPrintStatus(ClientJoinNotificationDistributedToListeners.class, true);		
		Tracer.setKeywordPrintStatus(ClientJoinNotificationReceived.class, true);
		Tracer.setKeywordPrintStatus(ClientJoinNotificationUnmarshalled.class, true);
		Tracer.setKeywordPrintStatus(ClientLeaveInformationUpdated.class, true);

		Tracer.setKeywordPrintStatus(ClientLeaveNotificationDistributedToListeners.class, true);

		Tracer.setKeywordPrintStatus(ClientLeaveNotificationReceived.class, true);
		Tracer.setKeywordPrintStatus(ClientLeaveNotificationUnmarshalled.class, true);
		Tracer.setKeywordPrintStatus(ClientReceivedObjectUnmarshalled.class, true);

		Tracer.setKeywordPrintStatus(MessagePutInQueue.class, true);
		Tracer.setKeywordPrintStatus(MessageRetrievedFromQueue.class, true);
		Tracer.setKeywordPrintStatus(MessageSent.class, true);


		Tracer.setKeywordPrintStatus(SentMessageDelayed.class, true);
		Tracer.setKeywordPrintStatus(SendDataRequest.class, true);
		Tracer.setKeywordPrintStatus(MessageGivenToFilter.class, true);
		Tracer.setKeywordPrintStatus(MessageReceived.class, true);
		Tracer.setKeywordPrintStatus(MessageRetrievedFromQueue.class, true);
		Tracer.setKeywordPrintStatus(ReceivedMessageDelayed.class, true);
		Tracer.setKeywordPrintStatus(ReceivedMessageDistributedToListeners.class, true);
		Tracer.setKeywordPrintStatus(SendDataRequest.class, true);
		Tracer.setKeywordPrintStatus(SentMessageDelayed.class, true);
	}

}
