package util.awt;
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
	public void setPointerWidth(int aWidth) {
		glassPane.setPointerWidth(aWidth);
	}

	@Override
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

}
