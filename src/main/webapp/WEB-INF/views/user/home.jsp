<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">


<link
	href="<c:url value="/static/css/bootstrap.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/static/css/sticky-footer-navbar.css"/>"
	rel="stylesheet">

<title>Home page</title>
</head>
<body>

	Dear
	<strong>${user}</strong>, this is home page
	<a href="<c:url value="/logout" />">Logout</a>

	<div>
		<button type="button" class="btn btn-danger">Go to Users</button>
		<a href="<c:url value='/user/' />">go to foo</a>
	</div>
	<script src="<c:url value="/static/lib/jquery-1.11.1.min.js"/>"></script>
	<script
		src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/static/js/bootstrap-filestyle.min.js"/>"></script>
</body>
</html>