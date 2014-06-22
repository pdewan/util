package util.session;
/*
 * Too generic a name, and one implementation marshalls receivced message and another
 * queues it. Why not have this implemented also by flters?
 */
public interface MessageProcessor<MessageType> {
	public void processMessage(MessageType theMessage);

}
