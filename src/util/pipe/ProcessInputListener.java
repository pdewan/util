package util.pipe;

public interface ProcessInputListener {
	/*
	 * The input does not contain a new line character
	 */
	void newInputLine(String aProcessName, String anInput);
	void inputTerminated(String aProcessName);
	

}
