package util.session;

public class SendReceiveSettingsFactory {

	static SendReceiveSettings settings;
	public static SendReceiveSettings getOrCreateSettings() {
		if (settings == null)
			settings = new ASendReceiveSettings();
		return settings;
	}
}
