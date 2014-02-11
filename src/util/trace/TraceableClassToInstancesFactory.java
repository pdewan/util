package util.trace;

public class TraceableClassToInstancesFactory {
	static TraceableClassToInstances traceableClassToCount;
	public static TraceableClassToInstances getOrCreateTraceableClassToInstances() {
		if (traceableClassToCount == null) {
			traceableClassToCount = new ATraceableClassToInstances();
			TraceableBus.addTraceableListener(traceableClassToCount);
		}
		return traceableClassToCount;
	}

}
