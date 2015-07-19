package util.misc;

public class ClearanceManagerFactory {
	static BroadcastingClearanceManager broadcastingClearanceManager = new ABroadcastingClearanceManager();
	static ClearanceManager clearanceManager;

	public static BroadcastingClearanceManager getBroadcastingClearanceManager() {
		return broadcastingClearanceManager;
	}

	public static void setBroadcastingClearanceManager(
			BroadcastingClearanceManager broadcastingClearanceManager) {
		ClearanceManagerFactory.broadcastingClearanceManager = broadcastingClearanceManager;
	}
	
	public static ClearanceManager getOrCreateClearanceManager() {
		if (clearanceManager == null)
			clearanceManager = new AClearanceManager();
		return clearanceManager;
	}
	
	

}
