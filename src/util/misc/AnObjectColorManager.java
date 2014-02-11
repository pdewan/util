package util.misc;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import util.models.Hashcodetable;

public class AnObjectColorManager implements ObjectColorManager {
	public static int COLOR_RANGE_SIZE = 256;
	double surroundingDifferenceThreshold = 500;
//	Color backgroundColor = Color.LIGHT_GRAY;
	int numTriesBeforeLoweringThreshold = 5;
	double thresholdDivisor = 1.3;
	Color[] surroundingColorsArray = new Color[] {Color.BLACK};
	List<Color> surroundingColors = new ArrayList();
	
	double differenceThreshold = 125;
	boolean autoLowerThreshold = true;
	
	
	boolean circulatePredefined;
	int nextPredefinedColorIndex;	
	Color[] predefinedColorsArray =  new Color[] {Color.BLACK, 
		Color.BLUE, 
		Color.CYAN, 
		Color.DARK_GRAY, 
		Color.GRAY, 
		Color.GREEN, 
		Color.LIGHT_GRAY, 
		Color.MAGENTA, 
		Color.PINK,
		Color.RED,
		Color.WHITE,
		Color.YELLOW
};
	List<Color> predefinedColors = new ArrayList();
	
	public Hashcodetable<Object, Color> objectToColor = new Hashcodetable();
	
    public AnObjectColorManager() {
    	predefinedColors = Common.arrayToVector(predefinedColorsArray);
    	surroundingColors = Common.arrayToVector(surroundingColorsArray);
	}
    /* (non-Javadoc)
	 * @see util.misc.ObjectColorManager#clearPredefinedColors()
	 */
    @Override
	public void clearPredefinedColors() {
    	predefinedColors.clear();
    }
    /* (non-Javadoc)
	 * @see util.misc.ObjectColorManager#setPredefinedColors(java.awt.Color[])
	 */
    @Override
	public void setPredefinedColors(Color[] newVal) {
    	predefinedColors = Common.arrayToVector(predefinedColorsArray);
    }
    @Override
    public void setSurroundingColors(Color[] newVal) {
    	surroundingColors = Common.arrayToVector(surroundingColorsArray);
    }
    @Override
	public void addPredefinedColor(Color newVal) {
    	predefinedColors.add(newVal);
    }    
    
    @Override
	public void addSurroundingColor(Color newVal) {
    	surroundingColors.add(newVal);
    } 
    /* (non-Javadoc)
	 * @see util.misc.ObjectColorManager#removePredefinedColor(java.awt.Color)
	 */
    @Override
	public void removePredefinedColor(Color newVal) {
    	predefinedColors.remove(newVal);
    }
    @Override
	public void removeSurroundingColor(Color newVal) {
    	surroundingColors.remove(newVal);
    }
	/* (non-Javadoc)
	 * @see util.misc.ObjectColorManager#getColor(java.lang.Object)
	 */
//    public Color getNextPredefinedColor() {
//    	Color retVal = null;
//    	if (nextPredefinedColorIndex < predefinedColors.size()) {
//			retVal = predefinedColors.get(nextPredefinedColorIndex);
//			nextPredefinedColorIndex++;
//			if (nextPredefinedColorIndex == predefinedColors.size() && circulatePredefined) {
//				nextPredefinedColorIndex = 0;
//			}
//			
//		} 
//    	return retVal;
//    }
	@Override
	public Color getColor(Object anObject) {
		Color retVal = objectToColor.get(anObject);
		if (retVal != null) return retVal;
		retVal = getNextPredefinedColor();
		if (retVal == null) {
			retVal = getNextAdaptiveRandomColor(anObject);
//			for (int i = 0; i < numTriesBeforeLoweringThreshold && retVal != null; i++) {
//				retVal = generateRandomColor();
//			}
//
//			if (retVal == null && autoLowerThreshold) {
//				differenceThreshold /= thresholdDivisor;
//				return getColor(anObject);
//			}
			
		}
		if (!isDiferentFromSurroundingColors(retVal))
			return getColor(anObject);

		if (retVal != null && anObject != null )
			objectToColor.put(anObject, retVal);
		return retVal;
	}
	
	/* (non-Javadoc)
	 * @see util.misc.ObjectColorManager#getNextPredefinedColor()
	 */
	@Override
	public Color getNextPredefinedColor() {
		Color retVal = null;
		if (nextPredefinedColorIndex < predefinedColors.size()) {
			retVal = predefinedColors.get(nextPredefinedColorIndex);
			nextPredefinedColorIndex++;
			if (nextPredefinedColorIndex == predefinedColors.size() && circulatePredefined) {
				nextPredefinedColorIndex = 0;			}
			
		} 
		return retVal;
	}
	@Override
	public Color getNextAdaptiveRandomColor(Object anObject) {
		Color retVal = null;
		for (int i = 0; i < numTriesBeforeLoweringThreshold && retVal == null; i++) {
			retVal = getNextRandomColor();
		}

		if (retVal == null && autoLowerThreshold && differenceThreshold >= 1){
			differenceThreshold /= thresholdDivisor;
			return getNextAdaptiveRandomColor(anObject);
		}
		if (retVal == null)
			retVal = generateRandomColor();
		return retVal;
	}
	
	/* (non-Javadoc)
	 * @see util.misc.ObjectColorManager#getNextRandomColor()
	 */
	@Override
	public Color getNextRandomColor() {
		Color newColor = generateRandomColor();
		if (!isUniqueColor(newColor) || !isDiferentFromSurroundingColors(newColor))
			return null;
		return newColor;
	}
	
	/* (non-Javadoc)
	 * @see util.misc.ObjectColorManager#isUniqueColor(java.awt.Color)
	 */
	@Override
	public boolean isUniqueColor(Color newColor) {
		Collection<Color> existingColors = objectToColor.values();
		for (Color existingColor:existingColors) {
			if (!differentEnough(newColor, existingColor, differenceThreshold))
				return false;
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see util.misc.ObjectColorManager#isDiferentFromBackground(java.awt.Color)
	 */
	@Override
	public boolean isDiferentFromSurroundingColors(Color newColor) {
		for (Color surroundingColor:surroundingColors)
		 if (!differentEnough(newColor, surroundingColor, surroundingDifferenceThreshold)) {
			 return false;
		 }
		return true;
	}
	
	public static Color generateRandomColor() {
		int red = Common.random(0, COLOR_RANGE_SIZE);
		int green = Common.random(0, COLOR_RANGE_SIZE);
		int blue =  Common.random(0, COLOR_RANGE_SIZE);
		return new Color(red, green, blue);
	}
	
//	public  boolean differentEnough(Color color1, Color color2) {
//		int redDifference = Math.abs(color1.getRed() - color2.getRed());
//		int greenDifference = Math.abs(color1.getGreen() - color2.getGreen());
//		int blueDifference = Math.abs(color1.getBlue()) - color2.getBlue();
//		return (redDifference + greenDifference + blueDifference) < differenceThreshold;
//	}
	public static boolean differentEnough(Color color1, Color color2, double aDifferenceThreshold) {
		if (color1 == null || color2 == null) {
			return true;
		}
		int redDifference = Math.abs(color1.getRed() - color2.getRed());
		int greenDifference = Math.abs(color1.getGreen() - color2.getGreen());
		int blueDifference = Math.abs(color1.getBlue() - color2.getBlue());
		int totalDiference = redDifference + greenDifference + blueDifference;
		return totalDiference > aDifferenceThreshold;
	}
	/* (non-Javadoc)
	 * @see util.misc.ObjectColorManager#getDifferenceThreshold()
	 */
	@Override
	public double getDifferenceThreshold() {
		return differenceThreshold;
	}
	/* (non-Javadoc)
	 * @see util.misc.ObjectColorManager#setDifferenceThreshold(double)
	 */
	@Override
	public void setDifferenceThreshold(double differenceThreshold) {
		this.differenceThreshold = differenceThreshold;
	}
	/* (non-Javadoc)
	 * @see util.misc.ObjectColorManager#isCirculatePredefined()
	 */
	@Override
	public boolean isCirculatePredefined() {
		return circulatePredefined;
	}
	/* (non-Javadoc)
	 * @see util.misc.ObjectColorManager#setCirculatePredefined(boolean)
	 */
	@Override
	public void setCirculatePredefined(boolean circulatePredefined) {
		this.circulatePredefined = circulatePredefined;
	}
	/* (non-Javadoc)
	 * @see util.misc.ObjectColorManager#isAutoLowerThreshold()
	 */
	@Override
	public boolean isAutoLowerThreshold() {
		return autoLowerThreshold;
	}
	/* (non-Javadoc)
	 * @see util.misc.ObjectColorManager#setAutoLowerThreshold(boolean)
	 */
	@Override
	public void setAutoLowerThreshold(boolean autoLowerThreshold) {
		this.autoLowerThreshold = autoLowerThreshold;
	}
	/* (non-Javadoc)
	 * @see util.misc.ObjectColorManager#getBackgroundDifference()
	 */
	@Override
	public double getSurroundingDifferenceThreshold() {
		return surroundingDifferenceThreshold;
	}
	/* (non-Javadoc)
	 * @see util.misc.ObjectColorManager#setBackgroundDifference(double)
	 */
	@Override
	public void setSurroundingDifferenceThreshold(double backgroundDifference) {
		this.surroundingDifferenceThreshold = backgroundDifference;
	}
	/* (non-Javadoc)
	 * @see util.misc.ObjectColorManager#getBackgroundColor()
	 */
//	@Override
//	public Color getBackgroundColor() {
//		return backgroundColor;
//	}
	/* (non-Javadoc)
	 * @see util.misc.ObjectColorManager#setBackgroundColor(java.awt.Color)
	 */
//	@Override
//	public void setBackgroundColor(Color backgroundColor) {
//		this.backgroundColor = backgroundColor;
//	}
	/* (non-Javadoc)
	 * @see util.misc.ObjectColorManager#getThresholdDivisor()
	 */
	@Override
	public double getThresholdDivisor() {
		return thresholdDivisor;
	}
	/* (non-Javadoc)
	 * @see util.misc.ObjectColorManager#setThresholdDivisor(int)
	 */
	@Override
	public void setThresholdDivisor(double thresholdDivisor) {
		this.thresholdDivisor = thresholdDivisor;
	}
	/* (non-Javadoc)
	 * @see util.misc.ObjectColorManager#getNumTriesBeforeLoweringThreshold()
	 */
	@Override
	public int getNumTriesBeforeLoweringThreshold() {
		return numTriesBeforeLoweringThreshold;
	}
	/* (non-Javadoc)
	 * @see util.misc.ObjectColorManager#setNumTriesBeforeLoweringThreshold(int)
	 */
	@Override
	public void setNumTriesBeforeLoweringThreshold(
			int numTriesBeforeLoweringThreshold) {
		this.numTriesBeforeLoweringThreshold = numTriesBeforeLoweringThreshold;
	}
	

}
