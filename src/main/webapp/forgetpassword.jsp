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
		<section class="auth_container">
			<div class="auth_wrapper">
				<h1 class="auth_header">Forget Password</h1>
				<form class="auth_form" id="forgetpassword_form" action="forgetpassword" method="post">
					<div class="input_container">
						<label class="input_label" for="forgetpassword_username">Username</label>
						<input class="input_box" type="text" id="forgetpassword_username" name="username"/>
					</div>
					<div class="input_container">
						<label class="input_label" for="forgetpassword_email">Email</label>
						<input class="input_box" type="text" id="forgetpassword_email" name="email"/>
					</div>
					<div class="input_container">
						<label class="input_label" for="forgetpassword_newpass">New Password</label>
						<input class="input_box" type="password" id="forgetpassword_newpass" name="newpass"/>
					</div>
					<% 
					
						String error = (String)request.getAttribute("error");
						if(error != null){
							out.print("<p class='auth_error'>" + error + "</p>");
						}
					
					%>
					
					<button class="auth_btn" id="forgetpassword_submit" type="submit">Change</button>
					<a class="auth_btn" href="home.jsp">Cancel</a> 
				</form>
			</div>
		</section>
	</body>
</html>