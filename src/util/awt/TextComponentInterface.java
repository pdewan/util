package util.awt;
public interface TextComponentInterface extends ComponentInterface, ActionListenable{
	String getText();
	void setText(String newVal);
}
