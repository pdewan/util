package util.misc;

import java.awt.Color;

public interface ObjectColorManager {

	public abstract void clearPredefinedColors();

	public abstract void setPredefinedColors(Color[] newVal);

	public abstract void addPredefinedColor(Color newVal);

	public abstract void removePredefinedColor(Color newVal);

	public abstract Color getColor(Object anObject);

	public abstract Color getNextPredefinedColor();

	public abstract Color getNextRandomColor();

	public abstract boolean isUniqueColor(Color newColor);

	public abstract boolean isDiferentFromSurroundingColors(Color newColor);

	public abstract double getDifferenceThreshold();

	public abstract void setDifferenceThreshold(double differenceThreshold);

	public abstract boolean isCirculatePredefined();

	public abstract void setCirculatePredefined(boolean circulatePredefined);

	public abstract boolean isAutoLowerThreshold();

	public abstract void setAutoLowerThreshold(boolean autoLowerThreshold);

	public abstract double getSurroundingDifferenceThreshold();

	public abstract void setSurroundingDifferenceThreshold(double backgroundDifference);

//	public abstract Color getBackgroundColor();
//
//	public abstract void setBackgroundColor(Color backgroundColor);

	public abstract double getThresholdDivisor();

	public abstract void setThresholdDivisor(double thresholdDivisor);

	public abstract int getNumTriesBeforeLoweringThreshold();

	public abstract void setNumTriesBeforeLoweringThreshold(
			int numTriesBeforeLoweringThreshold);

	Color getNextAdaptiveRandomColor(Object anObject);

	void setSurroundingColors(Color[] newVal);

	void addSurroundingColor(Color newVal);

	void removeSurroundingColor(Color newVal);


}