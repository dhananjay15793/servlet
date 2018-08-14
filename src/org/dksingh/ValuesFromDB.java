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
 * Servlet implementation class ValuesFromDB
 */
@WebServlet("/ValuesFromDB")
public class ValuesFromDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		System.out.println(action);
		if ("DBvalue".equals(action)) {
			out.println("<br>");
			response.sendRedirect("http://localhost:8080/simpleservletproject");
			
			try {
				TestJDBC.getvaluefromDB();	
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		  }

	
		
	}

}
