package util.session;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.misc.Common;
import util.models.BoundedBuffer;
import util.trace.Tracer;

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
