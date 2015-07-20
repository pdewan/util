package util.remote;

public class DistributedProcessControllerFactory {
	static DistributedProcessController singleton;
	public static DistributedProcessController getSingleton() {
		return singleton;
	}
	public static void setSingleton(DistributedProcessController singleton) {
		DistributedProcessControllerFactory.singleton = singleton;
	}
	public static DistributedProcessController getOrCreateSingleton() {
		if (singleton == null) {
			singleton = new ADistributedProcessController();
		}
		return singleton;
			
	}

}
