package com.saran.auth.signin;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.saran.dao.UserDao;
import com.saran.models.User;


public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = (String)request.getParameter("email");
		String pass = (String)request.getParameter("pass");
		
		UserDao dao = new UserDao();
		dao.connect();
		
		User user = dao.getUser(email, pass);
		
		if(user != null) {
			request.getSession().setAttribute("user", user);
			dao.disconnect();
			response.sendRedirect("home.jsp");
		}
		else {
			request.setAttribute("error", "Invalid login credentials!");
			dao.disconnect();
			request.getRequestDispatcher("signin.jsp").forward(request, response);
		}
	}

}
