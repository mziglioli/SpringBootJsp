<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:message code="label.create.user" />
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
