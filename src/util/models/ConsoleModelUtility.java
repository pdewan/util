package util.models;


import java.beans.PropertyChangeEvent;

import util.trace.Tracer;

public class ConsoleModelUtility {
	public static boolean isOutputLine(PropertyChangeEvent aConsoleModelEvent) {
		return aConsoleModelEvent.getPropertyName().equals(ConsoleModel.OUTPUT_LINE);
	}
	public static boolean isOutput(PropertyChangeEvent aConsoleModelEvent) {
		return aConsoleModelEvent.getPropertyName().equals("output");
	}
	
	public static String getText(PropertyChangeEvent aConsoleModelEvent) {
		return aConsoleModelEvent.getNewValue().toString();
	}
	public static boolean isInfo(PropertyChangeEvent aConsoleModelEvent) {
		return Tracer.isInfo(getText(aConsoleModelEvent));
	}
	public static boolean isInput(PropertyChangeEvent aConsoleModelEvent) {
		return aConsoleModelEvent.getPropertyName().equalsIgnoreCase("input");
	}
	public static boolean textEquals(PropertyChangeEvent aConsoleModelEvent, String aText) {
		return aConsoleModelEvent.getNewValue().equals(aText);
	}
	
	public static boolean textContains(PropertyChangeEvent aConsoleModelEvent, String aText) {
		return aConsoleModelEvent.getNewValue().toString().contains(aText);
	}
	public static boolean isConsole(PropertyChangeEvent aConsoleModelEvent, Class aClass ) {
		return ((ConsoleModel) (aConsoleModelEvent.getSource())).getTitle().contains(aClass.getSimpleName());		
	}
	public static boolean isConsole(PropertyChangeEvent aConsoleModelEvent, String aName ) {
		return ((ConsoleModel) (aConsoleModelEvent.getSource())).getTitle().contains(aName);		
	}
	public static boolean containsText(PropertyChangeEvent aConsoleModelEvent,  String aText) {
		return (aConsoleModelEvent.getNewValue().toString()).contains(aText);
	}
	public static void setConsoleInput (PropertyChangeEvent aConsoleModelEvent, String anInput) {
		((ConsoleModel) aConsoleModelEvent.getSource()).setInput(anInput);
	}
	
	

}
