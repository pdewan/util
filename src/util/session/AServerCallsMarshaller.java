package util.session;
/*
 * calls to server --> SentMessage
 */
public interface AServerCallsMarshaller {

	public abstract SentMessage asyncJoin();

	public abstract SentMessage leave();

	public abstract SentMessage toOthers(Object object);
	
	public abstract SentMessage toNonCallers(Object object, String aCaller);

	public abstract SentMessage toAll(Object object);

	public abstract SentMessage toUser(String userName, Object object);

	public abstract SentMessage toUsers(String[] userName, Object object);

}