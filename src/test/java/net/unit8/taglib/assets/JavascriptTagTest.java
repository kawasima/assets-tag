package net.unit8.taglib.assets;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;

import org.junit.Test;

public class JavascriptTagTest {

	@Test
	public void test() throws JspException {
		JavascriptTag tag = new JavascriptTag();
		JavascriptTag.assetsPath = "/assets";
		JspWriter jspWriter = new MockJspWriter(8192, false);
		BodyContent b = new MockBodyContent(jspWriter);
		tag.setSrc("/js/jquery");
		tag.setBodyContent(b);
		tag.doAfterBody();
		System.out.println(jspWriter.toString());
	}

}
