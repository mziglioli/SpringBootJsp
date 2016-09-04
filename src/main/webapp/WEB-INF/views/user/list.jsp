<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">


<link href="<c:url value="/static/css/bootstrap.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/static/css/sticky-footer-navbar.css"/>"
	rel="stylesheet">

<title>Users page</title>
</head>
<body>

	This is home page
	<a href="<c:url value="/logout" />">Logout</a>
	</br>
	<a href="<c:url value="/user/new" />">New user</a>
	<table>
		<tr>
			<th>count</th>
			<th>username</th>
			<th>auth</th>
			<th>edit</th>
		</tr>
		<c:forEach var="u" items="${entityList}" varStatus="counter">
			<tr>
				<td>${counter.count}</td>
				<td>${u.username}</td>
				<td>${u.getAuth()}</td>
				<td><a href="<c:url value="/user/${u.id}"/>">Edit</a></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>