package util.models;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import util.annotations.PreferredWidgetClass;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.LIST_PATTERN)
@PreferredWidgetClass(JTextArea.class)
public class AConsoleModelWithListOutput {
	List<String> output = new ArrayList(50);
	public int size() {
		return output.size();
	}
	public String get(int index) {
		return output.get(index);
	}
	public void add(String element) {
		output.add(element);
	}
	
//	public static void main(String[] args) {
//		AConsoleModelWithListOutput consoleModel = new AConsoleModelWithListOutput();
//		ObjectEditor.setPreferredWidget(AConsoleModelWithListOutput.class, TextArea.class);
//		ObjectEditor.edit(consoleModel);
//	}
}
