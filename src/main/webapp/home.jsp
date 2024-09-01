<%@page import="com.saran.models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Authentication</title>
		<link rel="stylesheet" href="./index.css">
	</head>
	<body>	
		<section class="home_container">
			<div class="home_wrapper">
				<%
					User user = (User) session.getAttribute("user");
					if(user == null){
						out.print("<h1>Something went Wrong</h1>");
						out.print("<a href='signin.jsp' class='anc_btn'><button class='home_btn'>Try again</button></a>");
					}
					else{
						out.print("<h1> Hello, " + user.getFullname() + "</h1>");
						out.print("<a href='editprofile.jsp' class='anc_btn'><button class='home_btn'>Edit Profile</button></a>");
						out.print("<a href='changepassword.jsp' class='anc_btn'><button class='home_btn'>Change Password</button></a>");
						out.print("<a href='signout' class='anc_btn'><button class='home_btn'>Sign Out</button></a>");
					}
				%>
			</div>
		</section>
	</body>
</html>