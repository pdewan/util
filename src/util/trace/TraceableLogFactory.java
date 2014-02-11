package util.trace;

public class TraceableLogFactory {
	static TraceableLog traceableLog;
	public static TraceableLog getTraceableLog() {
		if (traceableLog == null) {
			traceableLog = new ATraceableLog();
			TraceableBus.addTraceableListener(traceableLog);
		}
		return traceableLog;
	}

}
