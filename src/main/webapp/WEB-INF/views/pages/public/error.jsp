<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Error page</title>
</head>
<body>
    <strong>${user}</strong>
    
    Url <strong>${url}</strong>
    <spring:message code="error.exception" /> <strong>${exception}</strong>
</body>
</html>