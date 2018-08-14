package org.dksingh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestJDBC {
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/dklocal";
	static final String USER = "root";
	static final String PASS = "root";
	static Connection conn = null;
	static PreparedStatement stmt = null;

	public static List<UserLogin> getvaluefromDB() throws SQLException {
		ArrayList<UserLogin> list =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			System.out.println("Creating statement...");
			String query = "select * from userlogin";
			stmt = conn.prepareStatement(query);
			System.out.println("id\t firstname\t lastname\t email\t username\t");

			ResultSet rs = stmt.executeQuery();
		   list = new ArrayList<UserLogin>();
			while (rs.next()) {
				UserLogin userLogin = new UserLogin();
				userLogin.setId(rs.getInt("ID"));
				userLogin.setFirstName(rs.getString("FIRSTNAME"));
				userLogin.setLastName(rs.getString("LASTNAME"));
				userLogin.setEmail(rs.getString("EMAIL"));
				userLogin.setUserName(rs.getString("USERNAME"));
				list.add(userLogin);
			}
			
			System.out.println("List Size:::"+list.size());
			/*
			for (int i = 0; i < list.size(); i++) {
				UserLogin userLogin = list.get(i);
				if (userLogin.getFirstName().trim().isEmpty())
					continue;
				System.out.println(userLogin.getId() + "\t" + userLogin.getFirstName() + "\t" + userLogin.getLastName()
						+ "\t" + userLogin.getEmail() + "\t" + userLogin.getUserName());

			}
*/
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (conn != null)
				conn.close();
			if (stmt != null)
				stmt.close();
		}

		return list;

	}

}