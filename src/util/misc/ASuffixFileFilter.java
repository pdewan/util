package util.misc;

import java.io.File;

import javax.swing.filechooser.FileFilter;


public class ASuffixFileFilter extends FileFilter {
	String suffix;
	public ASuffixFileFilter(String aSuffix) {
		suffix = aSuffix;
	}
	@Override
	public boolean accept(File f) {
		return f.getName().endsWith(suffix);
	}
	@Override
	public String getDescription() {
		return suffix;
	}

	

}
