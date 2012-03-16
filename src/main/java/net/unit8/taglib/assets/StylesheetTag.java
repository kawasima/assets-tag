package net.unit8.taglib.assets;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

public class StylesheetTag extends AssetsBaseTag {

	private static final long serialVersionUID = 1L;

	@Override
	protected void writeTag(String path) throws IOException {
		JspWriter writer = bodyContent.getEnclosingWriter();
		writer.print("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + path + "\" />");
	}

}
