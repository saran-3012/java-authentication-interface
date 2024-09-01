<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Authentication</title>
		<link rel="stylesheet" href="./auth.css">
	</head>
	<body>
	
		<%
			if(session.getAttribute("user") != null){
				response.sendRedirect("home.jsp");
			}
		%>
		
		<section class="auth_container">
			<div class="auth_wrapper">
				<h1 class="auth_header">Sign Up</h1>
				<form class="auth_form" id="signin_form" action="signup" method="post">
					<div class="input_container">
						<label class="input_label" for="signup_fullname">Fullname</label>
						<input class="input_box" type="text" id="signup_fullname" name="fullname"/>
					</div>
					<div class="input_container">
						<label class="input_label" for="signup_username">Username</label>
						<input class="input_box" type="text" id="signup_username" name="username"/>
					</div>
					<div class="input_container">
						<label class="input_label" for="signup_email">Email</label>
						<input class="input_box" type="text" id="signup_email" name="email"/>
					</div>
					<div class="input_container">
						<label class="input_label" for="siginup_password">Password</label>
						<input class="input_box" type="password" id="signup_password" name="pass"/>
					</div>
					<% 

						String error = (String)request.getAttribute("error");
						if(error != null){
							out.print("<p class='auth_error'>" + error + "</p>");
						}
				
					%>
					<button class="auth_btn" id="signup_submit" type="submit">Sign up</button>
				</form>
			</div>
		</section>
	</body>
</html>