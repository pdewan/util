package util.misc;

public class ClearanceManagerFactory {
	static BroadcastingClearanceManager broadcastingClearanceManager = new ABroadcastingClearanceManager();

	public static BroadcastingClearanceManager getBroadcastingClearanceManager() {
		return broadcastingClearanceManager;
	}

	public static void setBroadcastingClearanceManager(
			BroadcastingClearanceManager broadcastingClearanceManager) {
		ClearanceManagerFactory.broadcastingClearanceManager = broadcastingClearanceManager;
	}
	

}
