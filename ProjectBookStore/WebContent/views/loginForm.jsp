<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login form</title>
</head>

<h1>Login form</h1>

<body>
<form style="margin-left: 20px;margin-top: 31px;"  action="../LoginServlet" method="post">
<label>First name:</label><br/>
<input type="text"  name="fname" value="" /><br/>
<label>Last name:</label><br/>
<input type="text"  name="lname" value="" /><br/>
<label>Password:</label><br/>
<input type="password"  name="pass" value="" /><br/><br/>
<input type="submit" name="login_button" value="Login"/>
</form>

</body>
</html>