package net.unit8.taglib.assets;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;

public class VersionSelector {
	private String versionA;
	private String versionB;

	private static final Pattern p = Pattern.compile("-([\\d\\.]+)$");

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
		String baseName = FilenameUtils.getBaseName(file.getName());
		versionA = getVersion(baseName);
		return this;
	}
	public boolean to(File file) {
		String baseName = FilenameUtils.getBaseName(file.getName());
		versionB = getVersion(baseName);
		if (versionA == null || versionB == null) {
			return true;
		}
		String[] versionAs = versionA.split("\\.");
		String[] versionBs = versionA.split("\\.");

		try {
			for(int i=0; i<versionAs.length && i < versionBs.length; i++) {
				if (Integer.parseInt(versionAs[i]) < Integer.parseInt(versionBs[i])) {
					return true;
				}
			}
			return versionAs.length <= versionBs.length;
		} finally {
			versionA = null;
			versionB = null;
		}
	}

	private String getVersion(String name) {
		Matcher m = p.matcher(name);
		return (m.find() && m.groupCount() > 0) ? m.group(1) : null;
	}
}
