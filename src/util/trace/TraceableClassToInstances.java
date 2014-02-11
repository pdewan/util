package util.trace;

import java.util.Map;
import java.util.List;

public interface TraceableClassToInstances extends TraceableListener {
	public Map<Class<? extends Exception>, List<Exception>> getClassToInstances();
}
