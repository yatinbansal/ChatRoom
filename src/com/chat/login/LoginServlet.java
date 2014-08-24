package com.chat.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chat.DAO.MsgDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "authentication", urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userid");
		String password = request.getParameter("passwd");
		try {
			System.out.println(MsgDAO.validateUser(userId, password));
			if(MsgDAO.validateUser(userId, password)) 
			{
			       HttpSession session = request.getSession(true);	    
			       session.setAttribute("currentSessionUser",userId); 
			       session.setMaxInactiveInterval(30*60);
		           Cookie userIdck = new Cookie("user", userId);
		           userIdck.setMaxAge(30*60);
		           response.addCookie(userIdck);
			       response.sendRedirect("mainpage.jsp"); //logged-in page   
			}
			
			else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
	            PrintWriter out= response.getWriter();
	            out.println("<font color=red>Either user name or password is wrong.</font>");
	            rd.include(request, response);
			}
				
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
