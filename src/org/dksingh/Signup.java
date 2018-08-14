package org.dksingh;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Signup
 */
@WebServlet(description = "signup page servlet", urlPatterns = { "/Signup" })
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String userName=request.getParameter("userName");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("re-Password");
		String encodedPassword="";
		
		String action = request.getParameter("action");
		if("signUp".equals(action)){
			try {
				if(LocalDB.checkEmailNotRegistered(email)){
					out.println("<font color='red'>Email-id already registered </font>");
				}else{
					
					if(password.equals(rePassword)){
						System.out.println("password matched");
						 encodedPassword=LocalDB.encrypt(password);
					
					 LocalDB.insertQuery(firstName, lastName, email, userName,encodedPassword);
					 out.println("<font color='green'>signUp successfull </font>");
				   }else{
					   System.out.println("password matched");
					   out.println("<font color='red'>password mismatch </font>");
				   }
				}
			}
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
