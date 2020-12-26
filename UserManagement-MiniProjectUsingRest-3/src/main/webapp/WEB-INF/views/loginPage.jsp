<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	
</head>
<body>
	<form:form action="login" method="POST" modelAttribute="loginInfo">
		<div align="center">
			<table>
				<tr>
					<td><h2 align="center">Sign in to</h2></td>
				</tr>
				<tr>
					<th align="left">Email address *</th>
				</tr>
				<tr>
					<td><form:input path="email" size="50" /></td>
				</tr>
				<tr>
					<th align="left">Password *</th>
				</tr>
				<tr>
					<td><form:password path="password" size="50" /></td>
				</tr>
				<tr>
					<th><input type="submit" value="Sign in"
						style="font-size: 20px"></th>
					<br>
					<br>
				</tr>
				<tr>
					<th><a href="/registerPage">New User? Create an account</a></th>
					<br>
					<br>
				</tr>
			</table>
		</div>
	</form:form>




</body>
</html>