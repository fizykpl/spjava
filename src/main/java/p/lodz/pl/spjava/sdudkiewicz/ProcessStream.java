package p.lodz.pl.spjava.sdudkiewicz;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class ProcessStream {
	private boolean bInput = false;
	private boolean bError = false;
	private String input = "";
	private String error = "";

	public ProcessStream(Process process) {
		try {
			this.input = IOUtils.toString(process.getInputStream(), "UTF-8");
			bInput = !input.isEmpty();
			this.error = IOUtils.toString(process.getErrorStream(), "UTF-8");
			bError = !error.isEmpty();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ProcessStream(String error) {
		this.input = "";
		bInput = false;
		this.error = error;
		bError = true;
	}

	public boolean isbInput() {
		return bInput;
	}

	public void setbInput(boolean bInput) {
		this.bInput = bInput;
	}

	public boolean isbError() {
		return bError;
	}

	public void setbError(boolean bError) {
		this.bError = bError;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
