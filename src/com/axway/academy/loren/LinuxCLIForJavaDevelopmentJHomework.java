package com.axway.academy.loren;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * 
 *
 * @author LorenIvanov
 */
public class LinuxCLIForJavaDevelopmentJHomework {

	public LinuxCLIForJavaDevelopmentJHomework() {
		printProperties();
		readManifestFile();
	}

	/**
	 * Read information from manifest file
	 */
	private void readManifestFile() {
		Manifest m;
		Scanner userInput = new Scanner(System.in);
		System.out.println("Please enter jar locaion.");
		String jarLocation = userInput.nextLine();
		try {
			m = new JarFile(jarLocation).getManifest();
			System.out.println(m);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Prints system properties
	 */
	private void printProperties() {
		Properties props = System.getProperties();
		props.list(System.out);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new LinuxCLIForJavaDevelopmentJHomework();
	}

}
