package util.session;

import java.io.Serializable;

public interface SessionManagerClient extends Communicator, ObjectReceiver,
		MessageDispatcher, Serializable/* , JoinSynchronizer */{

}
