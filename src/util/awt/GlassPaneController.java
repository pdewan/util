package util.awt;

public interface GlassPaneController {
	int getPointerWidth();

	void setPointerWidth(int aWidth);

	int getPointerHeight();

	void setPointerHeight(int aHeight);
	boolean isShowTelePointer();
	void setShowTelePointer(boolean showTelePointer);

	GlassPaneController getGlassPaneController();

	void setGlassPaneController(GlassPaneController glassPaneController);
}
