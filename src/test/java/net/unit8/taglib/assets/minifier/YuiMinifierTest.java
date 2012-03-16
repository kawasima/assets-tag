package net.unit8.taglib.assets.minifier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class YuiMinifierTest {
	@Test
	public void minifyJs() throws IOException {
		YuiMinifier minifier = new YuiMinifier();
		List<File> inputs = new ArrayList<File>();
		inputs.add(new File("src/test/resources/a1.js"));
		inputs.add(new File("src/test/resources/a2.js"));
		minifier.minifyJs(inputs);
	}

	@Test
	public void minifyCss() throws IOException {
		YuiMinifier minifier = new YuiMinifier();
		List<File> inputs = new ArrayList<File>();
		inputs.add(new File("src/test/resources/a1.css"));
		inputs.add(new File("src/test/resources/a2.css"));
		minifier.minifyCss(inputs);
	}
}
