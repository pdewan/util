package util.tags;

public interface DistributedTags {
	String REGISTRY = "Registry";
	String SERVER = "Server";
	String CLIENT = "Client";
	String SESSION_MANAGER = "Session Manager";
	String CAUSAL = "CAUSAL";
	String OT = "Operation Transformer";
	String RMI = "RMI";
	String SOCKETS = "Sockets";
	String NIO = "NIO";
	String GIPC = "GIPC";
	String SERVER_REMOTE_INTERFACE = "ServerRemoteInterface";
	String CLIENT_REMOTE_INTERFACE = "ClientRemoteInterface";
	String SERVER_REMOTE_OBJECT = "ServerRemoteObject";
	String CLIENT_REMOTE_OBJECT = "ClientRemoteObject";	
//	String REMOTE_INTERFACE = "RemoteInterface";
//	String SERVER_REMOTE_OBJECT = "ServerRemoteObject";
//	String REMOTE_OBJECT = "RemoteObject";
	String CLIENT_OUT_COUPLER = "ClientOutCoupler";
	String SERVER_OUT_COUPLER = "ClientOutCoupler";

	String CLIENT_CONFIGURER = "ClientConfigurer";
	String SERVER_CONFIGURER = "ServerConfigurer";
	
	
	String ATOMIC_BROADCAST = "AtomicBroadcast";
	String TWO_PHASE_COMMIT = "TwoPhaseCommit";
	String PAXOS = "Paxos";
	

	String SERVER_READ_THREAD = "Server Read Thread";
	String CLIENT_READ_THREAD = "Client Read Thread";
	String READ_THREAD_INTERFACE = "Read Thread Interface";
	


//	String CONFIGURER = "Configurer";

	// some typical client names
	String CLIENT_1 = "Alice";
	String CLIENT_2 = "Bob";
	String CLIENT_3 = "Cathy";
	String CLIENT_4 = "David";

}
