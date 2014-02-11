package util.session;

import java.io.Serializable;

public interface SessionManagerClient extends Communicator, MessageReceiver,
		DelayedMessageReceiver, Serializable/* , JoinSynchronizer */{

}
