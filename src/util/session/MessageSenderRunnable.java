package util.session;

public interface MessageSenderRunnable extends Runnable {
	void setProcessGroup(ProcessGroup theProcessGroup);

	void setLocalProcessGroup(ProcessGroupLocal localProcessGroup);

}
