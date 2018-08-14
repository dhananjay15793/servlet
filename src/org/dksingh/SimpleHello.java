package org.dksingh;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SimpleHello
 */
@WebServlet("/SimpleHello")
public class SimpleHello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("hello from get method ");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String username=request.getParameter("username");
		HttpSession session=request.getSession();
		
		if(username!="" & username!=null){
			session.setAttribute("savedUserName", username);
		}
		//String userid=request.getParameter("userid");
		out.println("<h3>Hello in html</h3>"+ (String)session.getAttribute("savedUserName"));
		//out.println("userid ="+userid);
		
	}

}
