package util.models;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public interface DynamicBean {

	public String[] getDynamicProperties();

	public Object getDynamicProperty(String name);

	public void setDynamicProperty(String name, Object newVal);

	public String[] getDynamicCommands();

	public void invokeDynamicCommand(String name);

	public boolean preSetDynamicProperty(String name);

}
