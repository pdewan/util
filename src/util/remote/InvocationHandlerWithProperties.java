package util.remote;

import java.lang.reflect.InvocationHandler;

public interface InvocationHandlerWithProperties extends InvocationHandler {
	Class getProxyTargetClass();
	String getProxyDestination();
	String getProxyName();	
	void setProxyDestination(String aDestination);
	void setProxyName(String aDestination);
	

	
	

}
