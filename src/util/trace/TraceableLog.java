package util.trace;

import java.util.List;

public interface TraceableLog extends TraceableListener {
	List<Exception> getLog();

}
