package util.trace;

public class TraceableLogFactory {
	static TraceableLog traceableLog;
	public static final int LARGE_SIZE = 1000;
	public static TraceableLog getTraceableLog() {
		if (traceableLog == null) {
			traceableLog = new ATraceableLog(LARGE_SIZE);
			TraceableBus.addTraceableListener(traceableLog);
		}
		return traceableLog;
	}

}
