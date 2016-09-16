<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="cell auto-size padding20 bg-white" id="cell-content">
	<h2><spring:message code="label.all.products" /></h2> <a href="<c:url value='/product/new' />"><spring:message code="label.create.product" /></a>
	<table class="table striped hovered cell-hovered border bordered">
		<tr>
			<th class="sortable-column">count</th>
			<th class="sortable-column">name</th>
			<th class="sortable-column">category</th>
			<th class="sortable-column">price</th>
			<th>edit</th>
		</tr>
		<c:forEach var="p" items="${entityList}" varStatus="counter">
			<tr>
				<td>${counter.count}</td>
				<td>${p.name}</td>
				<td>${p.category}</td>
				<td>${p.price}</td>
				<td><a href="<c:url value="/product/${p.id}"/>">Edit</a></td>
			</tr>
		</c:forEach>
	</table>
</div>