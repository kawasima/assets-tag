package net.unit8.taglib.assets;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;

import org.junit.Test;

public class JavascriptTagTest {

	@Test
	public void test() throws JspException {
		JavascriptTag tag = new JavascriptTag();
		JavascriptTag.assetsPath = "src/test/resources/webapp";
		JavascriptTag.assetsPrefix = "";
		PageContext context = new MockPageContext();
		tag.setPageContext(context);
		tag.setSrc("/js/jquery.js");
		tag.doEndTag();
		System.out.println(context.getOut().toString());
	}

	@Test
	public void selectMinifiedFile() throws JspException {
		JavascriptTag tag = new JavascriptTag();
		JavascriptTag.assetsPath = "src/test/resources/webapp";
		JavascriptTag.assetsPrefix = "";
		PageContext context = new MockPageContext();
		tag.setPageContext(context);
		tag.setSrc("/js/jquery-ui.js");
		tag.doEndTag();
		System.out.println(context.getOut().toString());
	}

	@Test
	public void multiSource() throws JspException, IOException {
		JavascriptTag tag = new JavascriptTag();
		JavascriptTag.assetsPath = "src/test/resources/webapp";
		JavascriptTag.assetsPrefix = "";
		PageContext context = new MockPageContext();
		BodyContent b = new MockBodyContent(context.getOut());
		b.write("<src>/js/jquery.js</src>");
		tag.setPageContext(context);
		tag.setAggregatedName("aggregate.js");
		tag.setBodyContent(b);
		tag.doAfterBody();
		System.out.println(context.getOut().toString());
	}

}
