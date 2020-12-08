package util.misc;

import javax.swing.JOptionPane;

public class RecentMessage {

	public static OutputLoggingLevel s_outputLoggingLevel = OutputLoggingLevel.ERROR;

	public static enum OutputLoggingLevel {
		NONE, ERROR, WARNING, INFO, DEBUG, USER_MESSAGE
	}

	/*
	 * Sasa: TODO: I would use the output logging level instead of the boolean
	 * flags to control output because - it is easier to escalate output logging
	 * - users can easily change it using a combo box if we chose to expose it
	 * in the UI - it is more consistent with other logging library approaches
	 */
	static boolean showWarnings = false;
	static boolean showInfo = false;

	public static void showWarnings(boolean newValue) {
		showWarnings = newValue;
	}

	public static void showInfo(boolean newValue) {
		showInfo = newValue;
	}

	public static void fatalError(String error) {
		String msg = "Fatal Error***" + error + ". Terminating program";
		System.out.println(msg);
		JOptionPane.showMessageDialog(null, msg);
		System.exit(1);
	}

	public static void error(String error) {
		if (s_outputLoggingLevel.ordinal() >= OutputLoggingLevel.ERROR
				.ordinal()) {
			System.err.println("E***" + error);
		}
	}

	public static void warning(String warning) {
		if (showWarnings) {
			System.err.println("W***" + warning);
		} else if (s_outputLoggingLevel.ordinal() >= OutputLoggingLevel.WARNING
				.ordinal()) {
			System.err.println("W***" + warning);
		}
	}

	public static void info(String info) {
		if (showInfo) {
			System.out.println("I***" + info);
		} else if (s_outputLoggingLevel.ordinal() >= OutputLoggingLevel.INFO
				.ordinal()) {
			System.err.println("I***" + info);
		}
	}

	public static void debug(String debugMessage) {
		if (s_outputLoggingLevel.ordinal() >= OutputLoggingLevel.DEBUG
				.ordinal()) {
			System.err.println("D***" + debugMessage);
		}
	}

	public static void userMessage(String userMessage) {
		if (s_outputLoggingLevel.ordinal() >= OutputLoggingLevel.USER_MESSAGE
				.ordinal()) {
			System.err.println("U***" + userMessage);
		}
	}
}
