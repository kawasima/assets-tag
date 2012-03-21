package net.unit8.taglib.assets;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

public class VersionSelectorTest extends VersionSelector {
	@Test
	public void test() {
		VersionSelector selector = new VersionSelector();
		Assert.assertTrue(selector.prefer(new File("jquery-1.6.4.js")).to(new File("jquery-1.7.js")));
		Assert.assertFalse("1.7 > 1.6.4", selector.prefer(new File("jquery-1.7.js")).to(new File("jquery-1.6.4.js")));
		Assert.assertTrue(selector.prefer(new File("jquery-1.7.js")).to(new File("jquery-1.7.1.js")));
	}

}
