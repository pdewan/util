package util.trace;

import java.util.ArrayList;
import java.util.List;

public class ATraceableLog implements TraceableLog{	
	
	List<Exception> log;
	public ATraceableLog(int aSize) {
		log = new ArrayList(aSize);
	}
	public ATraceableLog() {
		log = new ArrayList();
	}
	public List<Exception> getLog() {
		return log;
	}
	@Override
	public void newEvent(Exception aTraceable) {
		log.add(aTraceable);		
	}
	public String toString() {
		return log.toString();
	}
	

}
