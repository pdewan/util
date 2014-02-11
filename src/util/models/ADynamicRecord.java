package util.models;

import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

//import bus.uigen.DynamicRecord;
//import bus.uigen.ListenableTable;
//import bus.uigen.ListenableVector;
//import bus.uigen.uiBean;
//import bus.uigen.controller.VirtualMethodDescriptor;
//import bus.uigen.controller.uiMethodInvocationManager;
//import bus.uigen.introspect.MethodDescriptorProxy;
//import bus.uigen.introspect.PropertyDescriptorProxy;
//import bus.uigen.reflect.MethodProxy;

public class ADynamicRecord implements DynamicRecord {

	String virtualClass;
	Hashtable<String, Object> properties = new Hashtable();

	public ADynamicRecord() {
		// descriptorViewSupport = new DescriptorViewSupport();
		// TODO Auto-generated constructor stub
	}

	public String getVirtualClass() {
		return virtualClass;
	}

	public void setVirtualClass(String newVal) {
		virtualClass = newVal;
	}

	/*
	 * protected void set(Object theTargetObject,
	 * Vector<PropertyDescriptorProxy> theProperties,
	 * Vector<VirtualMethodDescriptor> theCommands) {
	 * descriptorViewSupport.set(theTargetObject, theProperties);
	 * //addProperties (theTargetObject, theProperties); }
	 * 
	 * protected void addProperty(Object theTargetObject,
	 * PropertyDescriptorProxy theProperty) {
	 * descriptorViewSupport.addProperty(theTargetObject, theProperty);
	 * 
	 * } protected void addPropertyGroup(String name, APropertyAndCommandFilter
	 * value) { descriptorViewSupport.addPropertyGroup(name, value);
	 * 
	 * 
	 * } /* protected APropertyAndCommandFilter getPropertyGroup(String name) {
	 * return descriptorViewSupport.getPropertyGroup(name); //return
	 * propertyValues.get(name); } protected void addMethod(Object
	 * theTargetObject, MethodDescriptorProxy theCommand) {
	 * descriptorViewSupport.addMethod(theTargetObject, theCommand);
	 * 
	 * } protected void addProperties (Object theTargetObject,
	 * PropertyDescriptorProxy[] theProperties) {
	 * descriptorViewSupport.addProperties(theTargetObject, theProperties); }
	 */
	/*
	 * protected void addProperties (Object theTargetObject,
	 * Vector<PropertyDescriptorProxy> theProperties) {
	 * 
	 * descriptorViewSupport.addProperties(theTargetObject, theProperties);
	 * 
	 * } protected void addMethods (Object theTargetObject,
	 * Vector<MethodDescriptorProxy> theMethods) {
	 * descriptorViewSupport.addMethods(theTargetObject, theMethods);
	 * 
	 * 
	 * 
	 * }
	 */
	// Object[] emptyParams = {};
	public String[] getDynamicProperties() {
		int numProperties = properties.size();
		String[] propertyArray = new String[numProperties];
		Enumeration<String> keys = properties.keys();
		int i = 0;
		while (keys.hasMoreElements())
			propertyArray[i++] = keys.nextElement();

		return propertyArray;
		// return propertyNames;
	}

	public Object getDynamicProperty(String name) {
		return properties.get(name);

	}

	public void setDynamicProperty(String name, Object newVal) {
		properties.put(name, newVal);
	}

	public boolean onlyDynamicCommands() {
		return false;
	}

	public boolean onlyDynamicProperties() {
		return true;
	}

	public String[] getDynamicCommands() {
		return null;
	}

	public void invokeDynamicCommand(String name) {

	}

	ListenableVector list;

	public void attachList(ListenableVector theList) {
		list = theList;
	}

	public ListenableVector attachedList() {
		return list;
	}

	ListenableTable table;

	public void attachTable(ListenableTable theTable) {
		table = theTable;
	}

	public ListenableTable attachedTable() {
		return table;
	}

}
