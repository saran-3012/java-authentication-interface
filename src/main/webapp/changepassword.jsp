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
				<h1 class="auth_header">Change Password</h1>
				<form class="auth_form" id="changepassword_form" action="changepassword" method="post">
					<div class="input_container">
						<label class="input_label" for="changepassword_oldpass">Old Password</label>
						<input class="input_box" type="password" id="changepassword_oldpass" name="oldpass"/>
					</div>
					<div class="input_container">
						<label class="input_label" for="changepassword_newpass">New Password</label>
						<input class="input_box" type="password" id="changepassword_newpass" name="newpass"/>
					</div>
					<% 
					
						String error = (String)request.getAttribute("error");
						if(error != null){
							out.print("<p class='auth_error'>" + error + "</p>");
						}
					
					%>
					
					<a href="forgetpassword.jsp">Forget Password?</a>
					
					<button class="auth_btn" id="changepassword_submit" type="submit">Change</button>
					<a class="auth_btn" href="home.jsp">Cancel</a>
				</form>
			</div>
		</section>
	</body>
</html>