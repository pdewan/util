package util.remote;

public class SendReceiveSettingsFactory {

	static SendReceiveSettings settings;
	public static SendReceiveSettings getOrCreateSingleton() {
		if (settings == null)
			settings = new ASendReceiveSettings();
		return settings;
	}
}
