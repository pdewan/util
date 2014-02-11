package util.session;

import java.util.Map;
import java.util.HashMap;

public class ASerializedMulticastGroups extends
		HashMap<String, Map<MessageReceiver, String>> implements
		SerializedProcessGroups {

}
