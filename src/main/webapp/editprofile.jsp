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
				<h1 class="auth_header">Edit Profile</h1>
				<form class="auth_form" id="editprofile_form" action="editprofile" method="post">
					<div class="input_container">
						<label class="input_label" for="editprofile_fullname">Fullname</label>
						<input class="input_box" type="text" id="editprofile_fullname" name="fullname"/>
					</div>
					<div class="input_container">
						<label class="input_label" for="editprofile_username">Username</label>
						<input class="input_box" type="text" id="editprofile_username" name="username"/>
					</div>
					<div class="input_container">
						<label class="input_label" for="editprofile_email">Email</label>
						<input class="input_box" type="text" id="editprofile_email" name="email"/>
					</div>
					<% 
					
						String error = (String)request.getAttribute("error");
						if(error != null){
							out.print("<p class='auth_error'>" + error + "</p>");
						}
					
					%>
					
					<button class="auth_btn" id="editprofile_submit" type="submit">Save</button>
					<a class="auth_btn" href="home.jsp">Back</a>
				</form>
			</div>
		</section>
	</body>
</html>