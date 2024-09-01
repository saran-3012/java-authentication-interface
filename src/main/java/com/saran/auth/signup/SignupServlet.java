package com.saran.auth.signup;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.saran.dao.UserDao;
import com.saran.models.User;

public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fullname = (String)request.getParameter("fullname");
		String username = (String)request.getParameter("username");
		String email = (String)request.getParameter("email");
		String pass = (String)request.getParameter("pass");
		
		
		UserDao dao = new UserDao();
		dao.connect();
		
		if(fullname == null || fullname == "") {
			request.setAttribute("error", "Fullname is required");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
			return;
		}
		
		if(username == null || username == "") {
			request.setAttribute("error", "Username is required");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
			return;
		}
		
		if(email == null || email == "") {
			request.setAttribute("error", "Email is required");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
			return;
		}
		
		if(pass == null || pass == "") {
			request.setAttribute("error", "Password is required");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
			return;
		}
		
		
		boolean hasUserCreated = dao.addUser(fullname, username, email, pass);
		
		if(hasUserCreated) {
			User user = dao.getUser(email, pass);
			if(user != null) {
				request.getSession().setAttribute("user", user);
				dao.disconnect();
				response.sendRedirect("home.jsp");
			}
			else {
				request.setAttribute("error", "Something went wrong!");
				dao.disconnect();
				request.getRequestDispatcher("signup.jsp").forward(request, response);
			}
		}
		else {
			request.setAttribute("error", "User already exists");
			dao.disconnect();
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
		
	}

}
