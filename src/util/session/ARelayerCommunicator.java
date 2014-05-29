package util.session;


@util.annotations.StructurePattern(util.annotations.StructurePatternNames.BEAN_PATTERN)
public class ARelayerCommunicator extends AnAbstractCommunicator {
	public ARelayerCommunicator(String theServerHost, String theSessionName,
			String theApplicationName, String theClientName) {
		super(theServerHost, theSessionName, theApplicationName, theClientName,
				true);

	}

	public ARelayerCommunicator(String[] args) {

		super(args[0], args[1], args[2], args[3], true);

	}

}
