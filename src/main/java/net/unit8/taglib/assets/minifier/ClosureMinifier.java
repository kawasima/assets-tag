package net.unit8.taglib.assets.minifier;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.JSSourceFile;
import com.google.javascript.jscomp.Result;

public class ClosureMinifier {
	public void minify(List<File> files) {
		Compiler compiler = new Compiler();
		List<JSSourceFile> inputs = new ArrayList<JSSourceFile>(files.size());
		for (File file : files) {
			inputs.add(JSSourceFile.fromFile(file));
		}
		List<JSSourceFile> externs = new ArrayList<JSSourceFile>();

		CompilerOptions options = new CompilerOptions();
		Result result = compiler.compile(externs, inputs, options);
		PrintStream out = null;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		out = new PrintStream(baos);
		out.println(compiler.toSource());
		System.out.println(new String(baos.toByteArray()));
	}
}
