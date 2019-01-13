package ua.testov.test;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		File workDirectory = new File(".");
		File[] arrWorkDirectory = workDirectory.listFiles(new MyFileFilter());

		File newFolder = new File("New Folder");
		newFolder.mkdirs();
		for (int i = 0; i < arrWorkDirectory.length; i++) {
			File out = new File(newFolder, "(copy) " + arrWorkDirectory[i].getName());
			try {
				out.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		File[] arrNewFolder = newFolder.listFiles();

		Thread[] arrThr = new Thread[arrWorkDirectory.length];
		for (int i = 0; i < arrThr.length; i++) {
			arrThr[i] = new Thread(new ThreadCopyFile(arrWorkDirectory[i], arrNewFolder[i]));
		}

		for (int i = 0; i < arrThr.length; i++) {
			arrThr[i].start();
		}
		for (int i = 0; i < arrThr.length; i++) {
			try {
				arrThr[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("File copy finished");
	}
}
