<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="cell auto-size padding20 bg-white" id="cell-content">
	<h2><spring:message code="label.list.user" /></h2> <a href="<c:url value='/user/new' />"><spring:message code="label.create.user" /></a>
	<table class="table striped hovered cell-hovered border bordered">
		<tr>
			<th class="sortable-column">count</th>
			<th class="sortable-column">username</th>
			<th class="sortable-column">auth</th>
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
</div>