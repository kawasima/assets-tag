package net.unit8.taglib.assets;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspWriter;

public class MockJspWriter extends JspWriter {
	private StringWriter sw;

	protected MockJspWriter(int bufferSize, boolean autoFlush) {
		super(bufferSize, autoFlush);
		sw = new StringWriter();
	}

	@Override
	public void clear() throws IOException {
		sw = new StringWriter();
	}

	@Override
	public void clearBuffer() throws IOException {
		clear();
	}

	@Override
	public void close() throws IOException {
		sw.close();
	}

	@Override
	public void flush() throws IOException {
		sw.flush();

	}

	@Override
	public int getRemaining() {
		return sw.getBuffer().capacity();
	}

	@Override
	public void newLine() throws IOException {
		sw.append("¥n");
	}

	@Override
	public void print(boolean b) throws IOException {
		sw.getBuffer().append(b);
	}

	@Override
	public void print(char c) throws IOException {
		sw.append(c);
	}

	@Override
	public void print(int i) throws IOException {
		sw.getBuffer().append(i);
	}

	@Override
	public void print(long l) throws IOException {
		sw.getBuffer().append(l);
	}

	@Override
	public void print(float f) throws IOException {
		sw.getBuffer().append(f);
	}

	@Override
	public void print(double d) throws IOException {
		sw.getBuffer().append(d);
	}

	@Override
	public void print(char[] chars) throws IOException {
		sw.getBuffer().append(chars);
	}

	@Override
	public void print(String s) throws IOException {
		sw.append(s);
	}

	@Override
	public void print(Object o) throws IOException {
		sw.getBuffer().append(o);
	}

	@Override
	public void println() throws IOException {
		sw.write("¥n");
	}

	@Override
	public void println(boolean b) throws IOException {
		print(b);
		println();
	}

	@Override
	public void println(char c) throws IOException {
		print(c);
		println();
	}

	@Override
	public void println(int i) throws IOException {
		print(i);
		println();
	}

	@Override
	public void println(long l) throws IOException {
		print(l);
		println();
	}

	@Override
	public void println(float f) throws IOException {
		print(f);
		println();
	}

	@Override
	public void println(double d) throws IOException {
		print(d);
		println();
	}

	@Override
	public void println(char[] chars) throws IOException {
		print(chars);
		println();
	}

	@Override
	public void println(String s) throws IOException {
		print(s);
		println();
	}

	@Override
	public void println(Object o) throws IOException {
		print(o);
		println();
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		sw.write(cbuf, off, len);
	}

	@Override
	public String toString() {
		return sw.toString();
	}

}
