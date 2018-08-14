package org.dksingh;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.bind.DatatypeConverter;

public class LocalDB {
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/dklocal";
	static final String USER = "root";
	static final String PASS = "root";
	static Connection conn = null;
	static PreparedStatement stmt = null;

	public static String encrypt(String password) {

		byte[] helloBytes = password.getBytes(StandardCharsets.UTF_8);
		String newVal = DatatypeConverter.printBase64Binary(helloBytes);
		System.out.println(password + " encoded=> " + newVal);
		return newVal;

	}

	public static String decryptPassword(String password) {

		byte[] encodedPasswordBytes = DatatypeConverter.parseBase64Binary(password);
		String passwordAgain = new String(encodedPasswordBytes, StandardCharsets.UTF_8);
		System.out.println(password + " decoded=> " + passwordAgain);

		return passwordAgain;
	}

	public static void insertQuery(String firstName, String lastName, String email, String userName,
			String encodedPassword) throws SQLException {

		try {

			// Open a connection
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute a query
			System.out.println("Creating statement...");
			// String query="insert into userlogin values('"+username+"',
			// '"+password+"')";
			String query = "insert into userlogin(FIRSTNAME,LASTNAME,EMAIL,USERNAME,PASSWORD) values(?,?,?,?,?)";
			stmt = conn.prepareStatement(query);

			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, email);
			stmt.setString(4, userName);
			stmt.setString(5, encodedPassword);
			stmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (conn != null)
				conn.close();
			if (stmt != null)
				stmt.close();
		}
	}

	public static boolean selectQuery(String uname, String pass) throws SQLException {

		try {

			// Open a connection
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute a query
			System.out.println("Creating statement...");
			// String query="insert into userlogin values('"+username+"',
			// '"+password+"')";
			String query = "select * from userlogin where username = ? and password =?";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, uname);
			stmt.setString(2, pass);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println("Credential Matched");
				return true;
			} else {
				System.out.println("Credential Mismatched");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (conn != null)
				conn.close();
			if (stmt != null)
				stmt.close();
		}
		return false;
	}

	public static boolean checkEmailNotRegistered(String email) throws SQLException {

		try {
			// Open a connection
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute a query
			System.out.println("Creating statement...");
			// String query="insert into userlogin values('"+username+"',
			// '"+password+"')";
			String query = "select * from userlogin where email = ? ";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println("email already registered to another user :");
				return true;
			} else {
				// Login Failed Logic
				System.out.println("email not registered to another user :");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (conn != null)
				conn.close();
			if (stmt != null)
				stmt.close();
		}
		return false;
	}

}
