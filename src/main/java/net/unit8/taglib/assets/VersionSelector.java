package net.unit8.taglib.assets;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

public class VersionSelector {
	private String versionA;
	private String versionB;

	private static final Pattern p = Pattern.compile("-((?:[\\d\\.]+)(?:\\.min)?)$");

	/**
	 * Select version
	 *
	 * <code>
	 *   selector.prefer(A).to(B);
	 * </code>
	 *
	 * @param flie
	 * @return
	 */
	public VersionSelector prefer(File file) {
		if (file == null) {
			versionA = null;
		} else {
			String baseName = FilenameUtils.getBaseName(file.getName());
			versionA = getVersion(baseName);
		}
		return this;
	}
	public boolean to(File file) {
		if (file == null) {
			versionB = null;
		} else {
			String baseName = FilenameUtils.getBaseName(file.getName());
			versionB = getVersion(baseName);
		}
		if (versionA == null) return true;
		if (versionB == null) return false;

		String[] versionAs = versionA.split("\\.");
		String[] versionBs = versionB.split("\\.");
		boolean versionAmin = StringUtils.equals(versionAs[versionAs.length-1], "min");
		boolean versionBmin = StringUtils.equals(versionBs[versionBs.length-1], "min");

		try {
			for(int i=0; i<versionAs.length && i < versionBs.length; i++) {
				int delta = toInt(versionAs[i]) - toInt(versionBs[i]);
				if (Math.abs(delta) > 0){
					return delta < 0;
				}
			}
			return versionAs.length <= versionBs.length;
		} finally {
			versionA = null;
			versionB = null;
		}
	}

	private int toInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch(NumberFormatException e) {
			return 0;
		}
	}
	private String getVersion(String name) {
		Matcher m = p.matcher(name);
		return (m.find() && m.groupCount() > 0) ? m.group(1) : null;
	}
}
