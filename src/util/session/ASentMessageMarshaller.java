package util.session;

public class ASentMessageMarshaller implements AServerCallsMarshaller {
	String clientName, sessionName, applicationName;
	ObjectReceiver exportedMessageReceiver;

	public ASentMessageMarshaller(String theClientName, String theSessionName,
			String theApplicationName,
			ObjectReceiver theExportedMessageReceiver) {
		clientName = theClientName;
		sessionName = theSessionName;
		applicationName = theApplicationName;
		exportedMessageReceiver = theExportedMessageReceiver;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.SentMessageCreator#asyncJoin()
	 */
	public SentMessage asyncJoin() {
		Object[] args = { sessionName, applicationName, clientName,
				exportedMessageReceiver };
		return new ASentMessage(sessionName, applicationName, clientName,
				exportedMessageReceiver, SentMessageType.Join, args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.SentMessageCreator#leave()
	 */
	public SentMessage leave() {
		Object[] args = {};
		return new ASentMessage(sessionName, applicationName, clientName,
				exportedMessageReceiver, SentMessageType.Leave, args);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.SentMessageCreator#toOthers(java.lang.Object)
	 */
	public SentMessage toOthers(Object object) {
		Object[] args = { object, clientName, exportedMessageReceiver };
		return new ASentMessage(sessionName, applicationName, clientName,
				exportedMessageReceiver, SentMessageType.Others, args);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.SentMessageCreator#toAll(java.lang.Object)
	 */
	public SentMessage toAll(Object object) {
		Object[] args = { object, clientName, exportedMessageReceiver };
		// SentMessage message = new ASentMessage(clientName,
		// SentMessageType.All, args) ;
		return new ASentMessage(sessionName, applicationName, clientName,
				exportedMessageReceiver, SentMessageType.All, args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.SentMessageCreator#toUser(java.lang.String,
	 * java.lang.Object)
	 */
	public SentMessage toUser(String userName, Object object) {
		Object[] args = { userName, object, clientName, exportedMessageReceiver };
		return new ASentMessage(sessionName, applicationName, clientName,
				exportedMessageReceiver, SentMessageType.User, args);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.session.SentMessageCreator#toUsers(java.lang.String[],
	 * java.lang.Object)
	 */
	public SentMessage toUsers(String[] userName, Object object) {
		return null;

	}

}
