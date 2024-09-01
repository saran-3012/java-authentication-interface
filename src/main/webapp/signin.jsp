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
				<h1 class="auth_header">Sign In</h1>
				<form class="auth_form" id="signin_form" action="signin" method="post">
					<div class="input_container">
						<label class="input_label" for="signin_email">Email</label>
						<input class="input_box" type="text" id="signin_email" name="email"/>
					</div>
					<div class="input_container">
						<label class="input_label" for="signin_password">Password</label>
						<input class="input_box" type="password" id="signin_password" name="pass"/>
					</div>
					<% 
						
						String error = (String)request.getAttribute("error");
						if(error != null){
							out.print("<p class='auth_error'>" + error + "</p>");
						}
					
					%>
					<a href="forgetpassword.jsp">Forget Password?</a>
					<button class="auth_btn" id="signin_submit" type="submit">Sign in</button>
				</form>
			</div>
		</section>
	</body>
</html>