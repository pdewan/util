package util.awt;

public class ASerializableRequest implements SerializableRequest {
	int frameId;
	String name;
	Object[] args;

	public ASerializableRequest(int theFrameId, String theName, Object[] theArgs) {
		frameId = theFrameId;
		name = theName;
		args = theArgs;

	}

	public int getSource() {
		return frameId;
	}

	public Object[] getArgs() {
		return args;
	}

	@Override
	public String getName() {
		return name;
	}

}
