package util.models;

import javax.swing.Icon;

import util.misc.Common;

public class ALabelModel implements LabelModel {
	String text;
	Icon icon;
	public ALabelModel(String aText) {
		text = aText;		
	}
	public ALabelModel(Icon anIcon) {
		icon = anIcon;		
	}
	public ALabelModel(String aText, Icon anIcon) {
		super();
		this.text = aText;
		this.icon = anIcon;
	}
	public String getText() {
		return text;
	}
	public void setText(String aText) {
		this.text = aText;
	}
	public Icon getIcon() {
		return icon;
	}
	public void setIcon(Icon anIcon) {
		this.icon = anIcon;
	}
	/*
	 * useful when checking if a component is equal to
	 * corresponding component of another object
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof LabelModel)) return false;
		LabelModel otherLabelModel = (LabelModel) other;
	
		return (this == other) || 
				(Common.equal(text, otherLabelModel.getText()) &&
				Common.equal(icon, otherLabelModel.getIcon()));
	}

}
