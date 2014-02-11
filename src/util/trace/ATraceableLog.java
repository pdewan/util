package util.trace;

import java.util.ArrayList;
import java.util.List;

public class ATraceableLog implements TraceableLog{	
	
	List<Exception> log = new ArrayList(1000);
	public List<Exception> getLog() {
		return log;
	}
	@Override
	public void newEvent(Exception aTraceable) {
		log.add(aTraceable);		
	}
	

}
