package net.unit8.taglib.assets;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

public class JavascriptTag extends AssetsBaseTag {

	private static final long serialVersionUID = 1L;
	private Boolean defer = false;
	private Boolean async = false;
	private String charset;

	@Override
	protected void writeTag(String path) throws IOException {
		JspWriter writer = (bodyContent != null) ? bodyContent.getEnclosingWriter() : pageContext.getOut();
		writer.print("<script type=\"text/javascript\" src=\""
				+ getAssetsPrefix() + path + "\"");
		if (charset != null)
			writer.print(" charset=\"" + charset + "\"");
		if (defer)
			writer.print(" defer");
		if (async)
			writer.print(" async");
		writer.print("></script>");
	}
	@Override
	protected String getExtension() {
		return "js";
	}

	public Boolean getDefer() {
		return defer;
	}
	public void setDefer(Boolean defer) {
		this.defer = defer;
	}
	public Boolean getAsync() {
		return async;
	}
	public void setAsync(Boolean async) {
		this.async = async;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}

}
