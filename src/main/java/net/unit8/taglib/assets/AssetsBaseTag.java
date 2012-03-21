package net.unit8.taglib.assets;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.lang.StringUtils;

public abstract class AssetsBaseTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private static final String SRC_TAG_START = "<src>";
	private static final String SRC_TAG_END = "</src>";
	protected static String assetsPath;
	protected static String assetsPrefix;

	private String src;
	private Boolean minify = false;
	private String aggregatedName;

	public String getAssetsPrefix() {
		if (assetsPrefix != null)
			return assetsPrefix;
		assetsPrefix = (String)pageContext.findAttribute("net.unit8.taglib.assets.AssetsPrefix");
		if (getAssetsPath().startsWith(pageContext.getServletContext().getRealPath("/"))) {
			assetsPrefix = pageContext.getServletContext().getContextPath();
		} else {
			assetsPrefix = "";
		}
		return assetsPrefix;
	}

	public String getAssetsPath() {
		if (assetsPath != null)
			return assetsPath;
		assetsPath = (String)pageContext.findAttribute("net.unit8.taglib.assets.AssetsPath");
		if (assetsPath == null) {
			assetsPath = pageContext.getServletContext().getRealPath("/");
		}
		return assetsPath;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public void setMinify(Boolean minify) {
		this.minify = minify;
	}
	public Boolean getMinify() {
		return minify;
	}

	public void setAggregatedName(String minifiedName) {
		this.aggregatedName = minifiedName;
	}
	public String getAggregatedName() {
		return aggregatedName;
	}

	protected abstract void writeTag(String spath) throws IOException;
	protected abstract String getExtension();


	protected File findResource(String path) {
		File directory = new File(getAssetsPath(), FilenameUtils.getPath(path));
		String basename = FilenameUtils.getBaseName(path);
		FileFilter filter = new RegexFileFilter("^" + basename + "\\-[\\d\\.]+(\\.min)?\\." + getExtension() + "$");
		File[] files = directory.listFiles(filter);
		if (files == null)
			return null;
		VersionSelector selector = new VersionSelector();
		File recommendResource = null;
		for (File file : files) {
			if (selector.prefer(recommendResource).to(file)) {
				recommendResource = file;
			}
		}
		return recommendResource;
	}

	protected void aggregate() {

	}

	private List<String> getResources(String body) throws JspException {
		List<String> resources;
		if (StringUtils.isEmpty(body)) {
			resources = new ArrayList<String>();
			if (StringUtils.isEmpty(src))
				 throw new JspException("No sources founded.");
			resources.add(src);
		} else {
			 resources = parseBody(body);
			 if (StringUtils.isEmpty(aggregatedName))
				 throw new JspException("When you use multiple sources, the aggregateName is needed.");

			 if (!StringUtils.isEmpty(src))
				 resources.add(src);
		}
		return resources;
	}

	private String buildPath(File file, String resource) {
		if (file == null) {
			return resource;
		} else {
			String path = new File(resource).getParent();
			return FilenameUtils.separatorsToUnix(new File(path, file.getName()).getPath());
		}
	}


	@Override
	public int doAfterBody() throws JspException {
		BodyContent content = getBodyContent();
		String body = content.getString();
		List<String> resources = getResources(body);
		try {
			for(String resource : resources) {
				File file = findResource(resource);
				// TODO  Not implemented yet.
				writeTag(buildPath(file, resource));
			}
		} catch (IOException ex) {
			throw new JspException(ex);
		}
		pageContext.setAttribute("net.unit8.taglib.assets.eval_body", true);

		return SKIP_BODY;
	}
	@Override
	public int doEndTag() throws JspException {
		Boolean evalBody = (Boolean)pageContext.getAttribute("net.unit8.taglib.assets.eval_body");
		if (evalBody == null || !evalBody) {
			try {
				File file = findResource(src);
				writeTag(buildPath(file, src));
			} catch (IOException ex) {
				throw new JspException(ex);
			}
		}

		return EVAL_PAGE;
	}

	protected List<String> parseBody(final String bodyString) {
		int bodyIndex = 0;
		List<String> resources = new ArrayList<String>();
		while (bodyIndex < (bodyString.length() - 1)) {
			int indexSrcStart = bodyString.indexOf(SRC_TAG_START, bodyIndex);
			if (indexSrcStart == -1) {
				return resources;
			}
			int indexSrcEnd = bodyString.indexOf(SRC_TAG_END, bodyIndex);
			if (indexSrcEnd == -1) {
				return resources;
			}
			String source = bodyString.substring(indexSrcStart + SRC_TAG_START.length(), indexSrcEnd).trim();
			resources.add(source);
			bodyIndex = indexSrcEnd + SRC_TAG_END.length();
		}
		return resources;
	}
}
