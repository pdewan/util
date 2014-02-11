package util.trace;



public class ImageFileNameNull extends ObjectWarning {
	public ImageFileNameNull(Object aTarget, Object aFinder) {
		super("The ImageFileName property of Image shape " + aTarget + " is null", aTarget, aFinder);	
	}
	public static ImageFileNameNull newCase(Object aTarget, Object aFinder) {
//		String aMessage = "The ImageFileName property of Image shape " + aTarget + " is null" ;
		ImageFileNameNull retVal =  new ImageFileNameNull(aTarget, aFinder);
		retVal.announce();
		return retVal;
	}

}
