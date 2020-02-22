package util.trace.bean;

import util.trace.ImplicitKeywordKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;

public class BeanTraceUtility {
	public static void setTracing() {
		Tracer.showInfo(true);
		Tracer.setDisplayThreadName(true); 
		TraceableInfo.setPrintTraceable(true);
		TraceableInfo.setPrintSource(true);
		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);		
		Tracer.setKeywordPrintStatus(SetProperty.class, true);
		Tracer.setKeywordPrintStatus(NotifiedPropertyChangeEvent.class, true);
		Tracer.setKeywordPrintStatus(AddedPropertyChangeListener.class, true);
		Tracer.setKeywordPrintStatus(RemovedPropertyChangeListener.class, true);

	}
}
