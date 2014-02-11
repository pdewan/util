package util.trace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ATraceableClassToInstances implements TraceableClassToInstances {
	Map<Class<? extends Exception>, List<Exception>> classToCount = new HashMap();
	@Override
	public void newEvent(Exception aTraceable) {
		
		List<Exception> list = classToCount.get(aTraceable.getClass());
		if (list == null) {
			list = new ArrayList();
			classToCount.put(aTraceable.getClass(), list );
		}
		
		list.add(aTraceable);
		
				
	}	
	public Map<Class<? extends Exception>, List<Exception>> getClassToInstances() {
		return classToCount;		
	}
}
