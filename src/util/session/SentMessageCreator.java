package util.session;

public interface SentMessageCreator {

	public abstract SentMessage asyncJoin();

	public abstract SentMessage leave();

	public abstract SentMessage toOthers(Object object);

	public abstract SentMessage toAll(Object object);

	public abstract SentMessage toUser(String userName, Object object);

	public abstract SentMessage toUsers(String[] userName, Object object);

}