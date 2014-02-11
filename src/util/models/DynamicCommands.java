package util.models;

import java.lang.annotation.Annotation;

public interface DynamicCommands {
	public String[] getDynamicCommands();

	public void invokeDynamicCommand(String theCommand);

	public Annotation getDynamicCommandAnnotation(String theCommand,
			Class annotationClass);

	public Object getDynamicPropertyType(String theProperty);
}
