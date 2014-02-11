package util.models;

public interface DynamicRecord {
	public String getVirtualClass();

	public void setVirtualClass(String newVal);

	public String[] getDynamicProperties();

	public Object getDynamicProperty(String name);

	public void setDynamicProperty(String name, Object newVal);

	public boolean onlyDynamicCommands();

	public boolean onlyDynamicProperties();

	public String[] getDynamicCommands();

	public void invokeDynamicCommand(String name);
}