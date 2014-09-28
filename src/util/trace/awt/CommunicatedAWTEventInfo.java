package util.trace.awt;
import java.awt.AWTEvent;
import java.awt.Component;
import java.util.Arrays;
import java.util.List;

import util.trace.Traceable;
import util.trace.TraceableInfo;
import util.trace.session.AddressedMessageInfo;
import util.trace.session.CommunicatedInfo;
import util.trace.session.ProcessInfo;
public class CommunicatedAWTEventInfo extends CommunicatedInfo {
	protected AWTEvent awtEvent;
	String name;
	String globalId; // null or empty if local event because I am lazy
	String paramString;
	
	

	public String getName() {
		return name;
	}
	public String getParamString() {
		return paramString;
	}

	public static final String AWT_EVENT = "AWTEvent";
	public static String[] awtEventEqualPropertiesArray = {"name", "globalId", "paramString"};
//	public static List<String> listEditEqualPropertiesList = Arrays.asList(listEditEqualPropertiesArray);

	public CommunicatedAWTEventInfo(String aMessage,
			String aProcessName,
			AWTEvent anAWTEvent, 
			String aGlobalId,
			String aDestinationOrSource,
			Object aFinder) {
		super(aMessage, aProcessName, aDestinationOrSource, aFinder);
		awtEvent = anAWTEvent;
		if (anAWTEvent.getSource() instanceof Component) {
			name = ((Component ) anAWTEvent.getSource()).getName();
		}
		globalId = aGlobalId;
		
	}
	public String getGlobalId() {
		return globalId;
	}
	protected void setEqualPropertiesList() {
		super.setEqualPropertiesList();
//		String[] listEditEqualPropertiesArray = {"OperationName", "Index", "Element"};

//		consoleEqualPropertiesArray = new String[]{"Output"};
		equalPropertiesList.addAll(Arrays.asList(awtEventEqualPropertiesArray));
//		equalPropertiesList.addAll(listEditEqualPropertiesList);

		
//		equalPropertiesList = Arrays.asList(equalPropertiesArray);

	}
	
	
	public CommunicatedAWTEventInfo(String aName, String aParamString, String aGlobalId) {
		this("", aName, aParamString, aGlobalId,  null); 
		
	}
	public CommunicatedAWTEventInfo(String aMessage, 
			String aName, String aParamString, 
			String aGlobalId,
			CommunicatedInfo aTraceable
//			String aProcessName,			
//			Long aTimeStamp,
//			String aThreadName,
//			Object aFinder
			) {
		super(aMessage, aTraceable);
		name = aName;
		paramString = aParamString;
		globalId = aGlobalId;
		
	}
	public CommunicatedAWTEventInfo(String aMessage, 
			CommunicatedAWTEventInfo anInfo
//			String aProcessName,			
//			Long aTimeStamp,
//			String aThreadName,
//			Object aFinder
			) {
		this(aMessage, 
				anInfo.name,
				anInfo.paramString,
				anInfo.globalId,
				null


				 );
	}
	
	public static CommunicatedAWTEventInfo toTraceable (String aMessage) {
		CommunicatedInfo aTraceable = CommunicatedInfo.toTraceable(aMessage);
		List<String> anArgs = getArgs(aMessage, AWT_EVENT);
		String aName = getName(anArgs);
		String aParamString = getParamString(anArgs);
		String aGlobalId = getGlobalId(anArgs);
		
		return new CommunicatedAWTEventInfo(aMessage, aName, aParamString, aGlobalId,  aTraceable);
				
	}
	
//	public static String getListEdit(String aMessage) {
//		return getArgOfCompositeDescriptor(aMessage, LIST_EDIT);
//	}
//	
//	public static OperationName getOperationName(String aMessage) {
//		String aListEdit = getListEdit(aMessage);
//		aListEdit = aListEdit.trim();
//		int aNameEndIndex = aListEdit.indexOf("(");
//		String aName = aListEdit.substring(0, aNameEndIndex);
//		return OperationName.fromString(aName);
//	}
//	
	
	
		
	public AWTEvent getAWTEvent() {
		return awtEvent;
	}
	
	static String toParamString(AWTEvent anAWTEvent) {
		try {
		return anAWTEvent.paramString();
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}
	public 
//	    static String toString(AWTEvent anAWTEvent, String aGlobalId) {
		static String toString(String aProcessName, AWTEvent anAWTEvent, String aGlobalId,  String aDestinationOrSource) {
		if (anAWTEvent == null) {
			System.out.println("Null event");
			return "";
		}
		String aName = "";
		if (anAWTEvent.getSource() instanceof Component) {
			aName = ((Component) anAWTEvent.getSource()).getName();
		}
		try {
		return toString(System.currentTimeMillis()) +
				" " + AWT_EVENT +  ("(") + 
				aName
				+ "," +
				aGlobalId
				+ "," +
//				anAWTEvent.paramString() +
				toParamString(anAWTEvent) +
				CommunicatedInfo.toString(aProcessName, aDestinationOrSource)
				
								
			+ ")";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
						
	}
	public static String toLocalInfoToString(AWTEvent anAWTEvent, String aGlobalId) {
		if (anAWTEvent == null) {
			System.out.println("Null event");
			return "";
		}
		String aName = "";
		if (anAWTEvent.getSource() instanceof Component) {
			aName = ((Component) anAWTEvent.getSource()).getName();
		}
	return 
			AWT_EVENT +  ("(") + 
			aName
			
			+ "," +
				aGlobalId	
				+ "," +
						anAWTEvent.paramString()
		+ ")";
			
}
	public static String getName(List<String> anArgs) {
		return anArgs.get(0);		
	}
	public static String getGlobalId(List<String> anArgs) {
		return anArgs.get(1);
	}
	public static String getParamString(List<String> anArgs) {
		return anArgs.get(2);
	}
	
	

//	public String toLocalInfoToString() {
//		return toLocalInfoToString(operationName, index, element, list); 
//	}
	public String alternativeToString() {
//		return "ListEdit(" +  toString(operationName, index, element) + ")";
		return 
//				LIST_EDIT + "(" +
//		"ListEdit(" +  
		
		toString(getProcessName(), awtEvent, globalId, getDestinationOrSource()); 
//		")";
	}
	
	public boolean equalsEdit(CommunicatedAWTEventInfo other) {
		return awtEvent.paramString().equals(other.awtEvent.paramString());
	}
	
}
