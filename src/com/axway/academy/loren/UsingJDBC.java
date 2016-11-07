package com.axway.academy.loren;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Create console application which uses JDBC
 *
 * @author LorenIvanov
 */
public class UsingJDBC {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.7:3306/Loren_Axway?autoReconnect=true&useSSL=false";

	// Database credentials
	static final String USER = "Loren";
	static final String PASS = "123";

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private Scanner userInput = new Scanner(System.in);
	private static String SQL;

	/**
	 * Prints the oldest employee
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	protected void oldestEmployee() throws SQLException, Exception {
		System.out.println("Printing oldest employee:");
		SQL = "SELECT * FROM employees ORDER BY Birthdate ASC LIMIT 1;";
		rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			// Retrieve by column name
			int id = rs.getInt("id");
			String name = rs.getString("Name");
			String surname = rs.getString("Surname");
			String birthdate = rs.getString("Birthdate");

			// Display values
			System.out.print("ID: " + id);
			System.out.print(", Name: " + name);
			System.out.print(", Surname: " + surname);
			System.out.println(", Birthdate " + birthdate);
		}
	}

	/**
	 * Prints the youngest employee
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	protected void youngestEmployee() throws SQLException, Exception {
		System.out.println("Printing youngest employee:");
		SQL = "SELECT * FROM employees ORDER BY Birthdate DESC LIMIT 1;";
		rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			// Retrieve by column name
			int id = rs.getInt("id");
			String name = rs.getString("Name");
			String surname = rs.getString("Surname");
			String birthdate = rs.getString("Birthdate");

			// Display values
			System.out.print("ID: " + id);
			System.out.print(", Name: " + name);
			System.out.print(", Surname: " + surname);
			System.out.println(", Birthdate " + birthdate);
		}
	}

	/**
	 * 
	 * @throws SQLException
	 */
	protected void createStatements() throws SQLException, Exception {
		String name = null;
		String surname = null;
		String birthdate = null;
		// Execute a query
		System.out.println("Inserting records into the table...");
		stmt = conn.createStatement();
		System.out.println("How much people you will insert into the table?");
		int numberOfPeople = validateUserInput();
		numberOfPeople = Math.abs(numberOfPeople);
		for (int i = 0; i < numberOfPeople; i++) {
			SQL = "insert into employees values (";
			SQL = SQL.concat("null,\"");
			System.out.print("Enter name: ");
			name = userInput.nextLine();
			SQL = SQL.concat(name);
			SQL = SQL.concat("\",\"");
			System.out.print("Enter surname: ");
			surname = userInput.nextLine();
			SQL = SQL.concat(surname);
			SQL = SQL.concat("\",'");
			System.out
					.println("Enter date of birth as following : year, month, day:");
			birthdate = dateOfBirth();
			SQL = SQL.concat(birthdate);
			SQL = SQL.concat("')");
			System.out.println(SQL);
			stmt.executeUpdate(SQL);
		}
		if (numberOfPeople != 0) {
			System.out.println("Inserted records into the table...");
		}
	}

	/**
	 * Creates string (yyyy-mm-dd)
	 * 
	 * @return
	 */
	private String dateOfBirth() {
		String birthdate = "";
		int[] dateOfBirth = new int[3];
		for (int i = 0; i < dateOfBirth.length; i++) {
			dateOfBirth[i] = validateUserInput();
			birthdate = birthdate + dateOfBirth[i];
			birthdate = birthdate.concat("-");
		}
		if (dateOfBirth[1] < 1 || dateOfBirth[1] > 12 || dateOfBirth[2] < 1
				|| dateOfBirth[2] > 31) {
			System.out.println("Please enter real date of birth!");
			return dateOfBirth();
		}
		birthdate = birthdate.substring(0, birthdate.length() - 1);
		return birthdate;
	}

	/**
	 * Validates that user enters an integer
	 * 
	 * @return
	 */
	private int validateUserInput() {
		int userChoise = 0;
		while (!userInput.hasNextInt()) {
			System.out.println("Please enter valid number.");
			userInput.next();
		}
		userChoise = userInput.nextInt();
		userInput.nextLine();
		return userChoise;
	}

	/**
	 * Show table values
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	protected void showTableValues() throws SQLException, Exception {
		// Execute a query
		System.out.println("Creating statement...");
		stmt = conn.createStatement();

		SQL = "SELECT * FROM employees";
		rs = stmt.executeQuery(SQL);

		// Extract data from result set
		while (rs.next()) {
			// Retrieve by column name
			int id = rs.getInt("id");
			String name = rs.getString("Name");
			String surname = rs.getString("Surname");
			String birthdate = rs.getString("Birthdate");

			// Display values
			System.out.print("ID: " + id);
			System.out.print(", Name: " + name);
			System.out.print(", Surname: " + surname);
			System.out.println(", Birthdate " + birthdate);
		}
	}

	/**
	 * Connects to database
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */

	protected void databaseConnection() throws SQLException,
			ClassNotFoundException, Exception {

		// Register JDBC driver
		Class.forName(JDBC_DRIVER);

		// Open a connection
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);

	}

	/**
	 * Creates instance of the class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		UsingJDBC obj = new UsingJDBC();
		try {
			obj.databaseConnection();
			obj.showTableValues();
			obj.createStatements();
			obj.youngestEmployee();
			obj.oldestEmployee();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
