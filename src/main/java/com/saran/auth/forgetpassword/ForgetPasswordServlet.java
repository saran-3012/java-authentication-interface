package com.saran.auth.forgetpassword;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.saran.dao.UserDao;
import com.saran.models.User;


public class ForgetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			response.sendRedirect("index.html");
			return;
		}
		
		String username = (String) request.getParameter("username");
		String email = (String) request.getParameter("email");
		String newPass = (String) request.getParameter("newpass");
		
		Integer uid = user.getUid();
		
		UserDao dao = new UserDao();
		
		dao.connect();
		
		boolean hasPasswordChanged = dao.changePassword(uid, username, email, newPass);
		
		if(hasPasswordChanged) {
			dao.disconnect();
			response.sendRedirect("home.jsp");
		}
		else {
			request.setAttribute("error", "Something went wrong!");
			dao.disconnect();
			request.getRequestDispatcher("forgetpassword.jsp").forward(request, response);
		}
	}

}
