package com.axway.academy.loren.linuxCLI;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * Prints system properties and reads manifest file
 *
 * @author LorenIvanov
 */
public class LinuxCLIForJavaDevelopmentJHomework {

	/**
	 * Calls two methods
	 */
	protected void execute() {
		printProperties();
		readManifestFile();
	}

	/**
	 * Read information from manifest file
	 */
	protected void readManifestFile() {
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
	protected void printProperties() {
		Properties props = System.getProperties();
		props.list(System.out);
	}

	/**
	 * Creates LinuxCLIForJavaDevelopmentJHomework object and call its execute
	 * method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LinuxCLIForJavaDevelopmentJHomework obj = new LinuxCLIForJavaDevelopmentJHomework();
		obj.execute();
	}

}
