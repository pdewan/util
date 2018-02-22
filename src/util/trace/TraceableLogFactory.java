package util.trace;

public class TraceableLogFactory {
	static TraceableLog traceableLog;
	public static final int LARGE_SIZE = 1000;
	static boolean enableTraceableLog = false;
	
	public static boolean isEnableTraceableLog() {
		return enableTraceableLog;
	}

	public static void setEnableTraceableLog(boolean enableTraceableLog) {
		TraceableLogFactory.enableTraceableLog = enableTraceableLog;
	}

	public static TraceableLog getTraceableLog() {
		if (traceableLog == null && isEnableTraceableLog()) {
			traceableLog = new ATraceableLog(LARGE_SIZE);
			TraceableBus.addTraceableListener(traceableLog);
		}
		return traceableLog;
	}

}
