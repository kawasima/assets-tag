package net.unit8.taglib.assets.minifier;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

import com.yahoo.platform.yui.compressor.CssCompressor;
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

public class YuiMinifier {
	private String minifyJsSingle(Reader in) throws IOException {
		JavaScriptCompressor compressor = new JavaScriptCompressor(in, new ErrorReporter() {
			public void warning(String message, String sourceName, int line, String lineSource, int lineOffset) {
                if (line < 0) {
                    System.err.println("\n[WARNING] " + message);
                } else {
                    System.err.println("\n[WARNING] " + line + ':' + lineOffset + ':' + message);
                }
			}

			public void error(String message, String sourceName, int line, String lineSource, int lineOffset) {
                if (line < 0) {
                    System.err.println("\n[ERROR] " + message);
                } else {
                    System.err.println("\n[ERROR] " + line + ':' + lineOffset + ':' + message);
                }
			}

			public EvaluatorException runtimeError(String message, String sourceName, int line, String lineSource, int lineOffset) {
                error(message, sourceName, line, lineSource, lineOffset);
                return new EvaluatorException(message);
			}
		});

        StringWriter writer = new StringWriter();
        compressor.compress(writer, -1, false, false, false, false);
        return writer.toString();
	}

	private String minifyCssSingle(Reader in) throws IOException {
        CssCompressor compressor = new CssCompressor(in);
        StringWriter writer = new StringWriter();
        compressor.compress(writer, -1);
        return writer.toString();
	}

	public void minifyJs(List<File> files) throws IOException {
		StringWriter out = new StringWriter();
		for (File file : files) {
			FileReader in = null;
			try {
				in = new FileReader(file);
				out.write(minifyJsSingle(in));
			} finally {
				IOUtils.closeQuietly(in);
			}
		}
		System.out.println(out.toString());
	}

	public void minifyCss(List<File> files) throws IOException {
		StringWriter out = new StringWriter();
		for (File file : files) {
			FileReader in = null;
			try {
				in = new FileReader(file);
				out.write(minifyCssSingle(in));
			} finally {
				IOUtils.closeQuietly(in);
			}
		}
		System.out.println(out.toString());

	}
}
