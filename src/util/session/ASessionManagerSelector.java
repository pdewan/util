package util.session;

public class ASessionManagerSelector {
	
	static ASessionManager sessionManager;	
	public static ASessionManager getSessionManager() {
		if (sessionManager == null)
			sessionManager = new ASessionManager();		
		return sessionManager;
	}
}
