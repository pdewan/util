package util.models;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public interface LabelBeanModel extends PropertyListenerRegisterer{
	public String getText() ;
//	public void setText(String text) ;
	public Icon getIcon() ;
//	public void setIcon(Icon icon) ;
	void set(String newText, Icon newIcon);
	void setText(String newValue);
	void setIcon(Icon newValue);

}
