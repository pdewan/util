package util.session;

import java.util.HashMap;
import java.util.Map;

public class ASerializedMulticastGroups extends
		HashMap<String, Map<MessageReceiver, String>> implements
		SerializedProcessGroups {

}
