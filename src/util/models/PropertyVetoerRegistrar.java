package util.models;

import java.beans.VetoableChangeListener;

public interface PropertyVetoerRegistrar {
	public void addVetoableChangeListener( VetoableChangeListener listener );
    public void removeVetoableChangeListener( VetoableChangeListener listener );
}
