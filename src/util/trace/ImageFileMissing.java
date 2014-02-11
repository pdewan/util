package util.trace;



public class ImageFileMissing extends TraceableError {
	String imageFileName;
	public ImageFileMissing( String anImageFileName,  Object aFinder) {
		super("Image file " + anImageFileName + " not found or corrupt.", aFinder);	
		imageFileName = anImageFileName;
	}
	public String getImageFileName() {
		return imageFileName;		
	}
	public static ImageFileMissing newCase(String anImageFileName, Object aFinder) {
//		String aMessage = "Image file " + anImageFileName + " not found.";
		ImageFileMissing retVal = new ImageFileMissing(anImageFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
