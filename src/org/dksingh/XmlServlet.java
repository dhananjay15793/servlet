package org.dksingh;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class XmlServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// System.out.println("xml servlet called ");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		/*
		 * out.println(userName); out.println(password);
		 */
		String action = request.getParameter("action");
		System.out.println(action);
		if ("SignUp".equals(action)) {

			// HttpSession session = request.getSession();

			response.sendRedirect("/simpleservletproject/signup.html");
		}

		/*
		 * try { LocalDB.insertQuery(userName, password); } catch (SQLException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * }
		 */

		else if ("Login".equals(action)) {
			System.out.println("you entered " + action);
			try {
				if (LocalDB.selectQuery(userName, LocalDB.encrypt(password))) {
					out.println("<font color='green'>Login Successfull</font>");
					List<UserLogin> list = TestJDBC.getvaluefromDB();
					out.println(
							"<table border='1'>  <tr> <th>ID</th>   <th>Firstname</th>    <th>Lastname</th>     <th>Email</th>      <th>UserName</th>  </tr>");

					for (int i = 0; i < list.size(); i++) {
						UserLogin userlogin = list.get(i);
						out.println(" <tr> <td>" + userlogin.getId() + "</td>   <td>" + userlogin.getFirstName()
								+ "</td>    <td>" + userlogin.getLastName() + "</td>     <td>" + userlogin.getEmail()
								+ "</td>      <td>" + userlogin.getUserName() + "</td>  </tr>");

					}
					out.print("</table>");

				} else
					out.println("<font color='red'>Login Failed</font>");
				System.out.println("login Process uncompleted");

				out.println("<br>");
				out.println("<a href='http://localhost:8080/simpleservletproject/'>Go To Main Page</a>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}

/*
 * if(userName!="" & userName!=null){ session.setAttribute("savedUserName",
 * userName); } out.println("<h3>UserName is from GET method  </h3>"+
 * (String)session.getAttribute("savedUserName")); }
 */

/*
 * protected void service(HttpServletRequest request, HttpServletResponse
 * response)throws ServletException, IOException{ System.out.println("hhhhhh");
 * doPost(request, response); }
 */

/*
 * protected void doPost(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException { //System.out.println(
 * "xml servlet called "); response.setContentType("text/html"); PrintWriter
 * out=response.getWriter(); String userName=request.getParameter("userName");
 * String fullName=request.getParameter("fullName");
 * 
 * HttpSession session=request.getSession();
 * 
 * if(userName!="" & userName!=null){ session.setAttribute("savedUserName",
 * userName); } out.println("UserName is from POST method "+userName);
 * out.println("fullName is from POST method "+fullName);
 * 
 * 
 * 
 * 
 * String prof=request.getParameter("prof"); out.println("you are a "+ prof);
 * 
 * String location=request.getParameter("location"); out.println("you are in  "+
 * location);
 * 
 * }
 */
