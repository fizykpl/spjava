package p.lodz.pl.spjava.sdudkiewicz;

import java.io.IOException;
import java.util.concurrent.Callable;

public class Processing implements Callable<ProcessStream> {

	String command;

	public Processing(String command) {
		super();
		this.command = command;
	}

	@Override
	public ProcessStream call() throws Exception {
		Runtime rt = Runtime.getRuntime();
		Process pr = null;
		try {
			pr = rt.exec(command);
			return new ProcessStream(pr);
		} catch (IOException e) {
			e.printStackTrace();
			return new ProcessStream(e.getMessage());
		}
	}
}
