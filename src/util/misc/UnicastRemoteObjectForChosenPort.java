/*
 * Created on Jun 14, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package util.misc;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author dewan
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class UnicastRemoteObjectForChosenPort extends UnicastRemoteObject {
	private int myPort;
	static int commonPort = 0;

	// static boolean anonymousMode = false;
	protected UnicastRemoteObjectForChosenPort() throws RemoteException {
		super(commonPort);

	}

	protected UnicastRemoteObjectForChosenPort(int thePort)
			throws RemoteException {
		super(thePort);
		myPort = thePort;
	}

	int getMyPort() {
		return myPort;
	}

	public static void setCommonPort(int thePort) {
		commonPort = thePort;
	}

	public static int getCommonPort() {
		return commonPort;
	}
	/*
	 * public static void setAnonymousMode (boolean newVal) { anonymousMode =
	 * newVal; } public static boolean getAnonymousMode () { return
	 * anonymousMode; }
	 */

}
