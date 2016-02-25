<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>JEEDemo Hello World (Model 2)</title>
</head>
<body>
	<h1>Willkommen in der Welt der JSPs mit Model2!</h1>
	<p>Lieber <strong><c:out value="${userName}" /></strong>,</p>
	<p>die Welt der JSPs mit Model2 heißt Sie herzlich willkommen!</p>
</body>
</html>