package net.unit8.taglib.assets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;

public class MockPageContext extends PageContext {
	private HashMap<String, Object> attributes;
	private JspWriter jspWriter;

	public MockPageContext() {
		attributes = new HashMap<String, Object>();
		jspWriter = new MockJspWriter(8192, false);
	}
	@Override
	public void initialize(Servlet servlet, ServletRequest request,
			ServletResponse response, String errorPageURL,
			boolean needsSession, int bufferSize, boolean autoFlush)
			throws IOException, IllegalStateException, IllegalArgumentException {
	}

	@Override
	public void release() {
	}

	@Override
	public HttpSession getSession() {
		return null;
	}

	@Override
	public Object getPage() {
		return null;
	}

	@Override
	public ServletRequest getRequest() {
		return null;
	}

	@Override
	public ServletResponse getResponse() {
		return null;
	}

	@Override
	public Exception getException() {
		return null;
	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public ServletContext getServletContext() {
		return null;
	}

	@Override
	public void forward(String relativeUrlPath) throws ServletException,
			IOException {
	}

	@Override
	public void include(String relativeUrlPath) throws ServletException,
			IOException {
	}

	@Override
	public void include(String relativeUrlPath, boolean flush)
			throws ServletException, IOException {
	}

	@Override
	public void handlePageException(Exception e) throws ServletException,
			IOException {
	}

	@Override
	public void handlePageException(Throwable t) throws ServletException,
			IOException {
	}

	@Override
	public void setAttribute(String name, Object value) {
		attributes.put(name, value);
	}

	@Override
	public void setAttribute(String name, Object value, int scope) {
		attributes.put(name, value);
	}

	@Override
	public Object getAttribute(String name) {
		return attributes.get(name);
	}

	@Override
	public Object getAttribute(String name, int scope) {
		return attributes.get(name);
	}

	@Override
	public Object findAttribute(String name) {
		return attributes.get(name);
	}

	@Override
	public void removeAttribute(String name) {
		attributes.remove(name);
	}

	@Override
	public void removeAttribute(String name, int scope) {
		attributes.remove(name);
	}

	@Override
	public int getAttributesScope(String name) {
		return PAGE_SCOPE;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration getAttributeNamesInScope(int scope) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public JspWriter getOut() {
		return jspWriter;
	}

	@Override
	public ExpressionEvaluator getExpressionEvaluator() {
		return null;
	}

	@Override
	public VariableResolver getVariableResolver() {
		return null;
	}

}
