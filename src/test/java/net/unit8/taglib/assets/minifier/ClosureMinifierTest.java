package net.unit8.taglib.assets.minifier;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ClosureMinifierTest {

	@Test
	public void test() {
		ClosureMinifier minifier = new ClosureMinifier();
		List<File> inputs = new ArrayList<File>();
		inputs.add(new File("src/test/resources/a1.js"));
		inputs.add(new File("src/test/resources/a2.js"));
		minifier.minify(inputs);
	}

	@Test
	public void minifyCss() {
		ClosureMinifier minifier = new ClosureMinifier();
		List<File> inputs = new ArrayList<File>();
		inputs.add(new File("src/test/resources/a1.css"));
		inputs.add(new File("src/test/resources/a2.css"));
		minifier.minify(inputs);
	}


}
