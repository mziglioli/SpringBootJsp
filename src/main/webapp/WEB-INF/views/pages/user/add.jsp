<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">
<link	href="<c:url value="/static/css/bootstrap.min.css"/>" rel="stylesheet">
<link href="<c:url value="/static/css/sticky-footer-navbar.css"/>" rel="stylesheet">
<title>Edit User page</title>
</head>
<body>
	<c:import url="../fragments/header.jsp"></c:import>
	<spring:message code="label.create.user" />
	<c:import url="../fragments/footer.jsp"></c:import>
</body>
</html>