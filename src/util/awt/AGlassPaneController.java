package util.awt;
import javax.swing.JSlider;

import util.annotations.PreferredWidgetClass;
import util.annotations.Visible;

public class AGlassPaneController implements GlassPaneController{
	GlassPaneController glassPane;
	public AGlassPaneController(GlassPaneController aGlassPane) {
		glassPane = aGlassPane;
	}
	@Override
	public int getPointerWidth() {
		// TODO Auto-generated method stub
		return glassPane.getPointerWidth();
	}

	@Override
	@PreferredWidgetClass(JSlider.class)
	public void setPointerWidth(int aWidth) {
		glassPane.setPointerWidth(aWidth);
	}

	@Override
	@PreferredWidgetClass(JSlider.class)
	public int getPointerHeight() {
		return glassPane.getPointerHeight();
	}

	@Override
	public void setPointerHeight(int aHeight) {
		glassPane.setPointerHeight(aHeight);
		
	}

	@Override
	public boolean isShowTelePointer() {
		return glassPane.isShowTelePointer();
	}

	@Override
	public void setShowTelePointer(boolean showTelePointer) {
		glassPane.setShowTelePointer(showTelePointer);		
	}
	@Override
	@Visible(false)
	public GlassPaneController getGlassPaneController() {
		return glassPane;
	}
	@Override
	@Visible(false)
	public void setGlassPaneController(GlassPaneController newVal) {
		glassPane = newVal;		
	}
	@Override
	public int getPointerSize() {
		return glassPane.getPointerSize();
	}
	@Override
	public void setPointerSize(int aSize) {
		glassPane.setPointerSize(aSize);
		
	}

}
