package ua.testov.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ThreadCopyFile implements Runnable  {
	private File in;
	private File out;

	public ThreadCopyFile() {
		super();
	}

	public ThreadCopyFile(File in, File out) {
		super();
		this.in = in;
		this.out = out;
	}

	public File getIn() {
		return in;
	}

	public File getOut() {
		return out;
	}
	public void fileCopy() throws IOException {

		byte[] buffer = new byte[1024 * 1024];
		int readByte = 0;

		try (FileInputStream fis = new FileInputStream(this.in); FileOutputStream fos = new FileOutputStream(this.out)) {
			for (; (readByte = fis.read(buffer)) > 0;) {
				fos.write(buffer, 0, readByte);
			}

		} catch (IOException e) {
			throw e;
		}
	}
	@Override
	public void run() {
		try {
			fileCopy();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
