package util.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.Icon;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.misc.Common;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class ALabelBeanModel implements LabelBeanModel {
	String text;
	Icon icon;
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	public ALabelBeanModel() {
		text = "";		
	}
	public ALabelBeanModel(String aText) {
		text = aText;		
	}
	public ALabelBeanModel(Icon anIcon) {
		icon = anIcon;		
	}
	public ALabelBeanModel(String aText, Icon anIcon) {
		super();
		this.text = aText;
		this.icon = anIcon;
	}
	public String getText() {
		return text;
	}
	@Override
	public void set (String newText, Icon newIcon) {
		if (Common.equal(newText, text) && Common.equal(newIcon, icon)) return;
		text = newText;
		icon = newIcon;
		propertyChangeSupport.firePropertyChange("this", null, this);

	}
//	public void setText(String newValue) {
//		String oldValue = text;
//		if (oldValue == newValue) return; // redudant but useful for debugging
//		this.text = newValue;
//		propertyChangeSupport.firePropertyChange("Text", oldValue, newValue);
//	}
	public Icon getIcon() {
		return icon;
	}
//	public void setIcon(Icon newValue) {
//		Icon oldValue = icon;
//		if (oldValue == newValue) return; // redudant but useful for debugging
//		this.icon = newValue;
//		propertyChangeSupport.firePropertyChange("Icon", oldValue, newValue);
//	}
	/*
	 * useful when checking if a component is equal to
	 * corresponding component of another object
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof LabelBeanModel)) return false;
		LabelBeanModel otherLabelModel = (LabelBeanModel) other;
	
		return (this == other) || 
				(Common.equal(text, otherLabelModel.getText()) &&
				Common.equal(icon, otherLabelModel.getIcon()));
	}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
	}

}
