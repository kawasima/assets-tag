package net.unit8.taglib.assets;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

public class StylesheetTag extends AssetsBaseTag {

	private static final long serialVersionUID = 1L;

	private String media = null;

	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	@Override
	protected String getExtension() {
		return "css";
	}
	@Override
	protected void writeTag(String path) throws IOException {
		JspWriter writer = (bodyContent != null) ? bodyContent.getEnclosingWriter() : pageContext.getOut();
		writer.print("<link rel=\"stylesheet\" type=\"text/css\" href=\""
				+ getAssetsPrefix() + path + "\"");
		if (media != null)
			writer.print(" media=\"" + media + "\"");
		writer.print(" />");
	}

}
