<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<script src="/app.js"></script>
<style type="text/css">
h1 {
	text-align: center;
	font-family: sans-serif;
}
div{
	text-align: center;		
}

</style>

</head>
<body>
	<div>
		<h1>Create your account</h1>
		<br>
		<br>
		<form:form action="login" method="POST" modelAttribute="loginInfo">
		
			<p>First Name *</p>
			<div>
				<form:input path="firstName" size="50" />
			</div>
			<p>Last Name *</p>
			<div>
				<form:input path="lastName" size="50" />
			</div>
			<p>Email *</p>
			<div>
				<form:input path="email" size="50" />
			</div>
			<p>Contact Number *</p>
			<div>
				<form:input path="contactNumber" size="50" />
			</div>
			<p>Date of birth *</p>
			<div>
				<form:input path="dob" size="50" />
			</div>
			<p>Gender</p>
			<div>
				<form:radiobutton path="gender" value="M" label="Male" />
				<form:radiobutton path="gender" value="F" label="Femail" />
			</div>
			<p>Country</p>
			<div>
				<form:select path = "country" id="country">
                     <form:option value = "NONE" label = "Select-Country"/>
                     <form:options items = "${countryList}" />
                  </form:select>  
			</div>
			<p>State</p>
			<div>
				<form:select path="state">
					 <form:option value = "NONE" label = "Select-State"/>
                    <%--  <form:options items = "${countryList}" /> --%>
                </form:select>
			</div>
			<p>City</p>
			<div>
				<form:select path="city" />
			</div>
			<input type="submit" value="Submit" style="font-size: 20px;">
			<a href="login">Back to Sign in</a>
			</th>
			<br>
			<br>
		</form:form>
	</div>
</body>
</html>