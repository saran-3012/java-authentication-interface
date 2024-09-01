package com.saran.auth.editprofile;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.saran.dao.UserDao;
import com.saran.models.User;

public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fullname = (String)request.getParameter("fullname");
		String username = (String)request.getParameter("username");
		String email = (String)request.getParameter("email");
		
		User user = new User();
		
		
		if(fullname != null && fullname != "") {
			user.setFullname(fullname);
		}
		
		if(username != null && username != "") {
			user.setUsername(username);
		}
		
		if(email != null && email != "") {
			user.setEmail(email);
		}
		
		UserDao dao = new UserDao();
		dao.connect();
		
		boolean hasUserUpdated = dao.updateUser(user);
		
		RequestDispatcher rd;
		
		if(hasUserUpdated) {
			HttpSession session = request.getSession();
			User existingUser = (User) session.getAttribute("user"); 
			
			String newFullname = user.getFullname();
			String newUsername = user.getUsername();
			String newEmail = user.getEmail();
			
			if(newFullname != null) {
				existingUser.setFullname(newFullname);
			}
			if(newUsername != null) {
				existingUser.setUsername(newUsername);
			}
			if(newEmail != null) {
				existingUser.setEmail(newEmail);
			}
			
			session.setAttribute("user", existingUser);
			rd = request.getRequestDispatcher("home.jsp");
		}
		else {
			request.setAttribute("error", "Nothing to update!");
			rd = request.getRequestDispatcher("editprofile.jsp");
		}
		
		dao.disconnect();
		
		rd.forward(request, response);
		
	}

}
