<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>JEEDemo Hello World (Model 2)</title>
</head>
<body>
	<h1>Die Welt der JSPs mit Model 2</h1>
	<form name="helloWorldForm" action="<c:out value='${formAction}' />" method="post">
		<p>Wen dürfen wir in der Welt der JSPs mit Model2 begrüßen?</p>
		<label name="userNameLabel">Name:&nbsp;</label> 
		<input name="userName" type="text" maxlength="32" /> 
		<input name="submit" type="submit" value="OK" />
	</form>
</body>
</html>