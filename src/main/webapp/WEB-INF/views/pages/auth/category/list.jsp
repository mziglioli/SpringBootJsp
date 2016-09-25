<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="cell auto-size padding20 bg-white" id="cell-content">
	<c:if test="${not empty success}">
		<div class="row no-margin padding5">
	        <div class="cell colspan6">
	    		<div class="input-state-info mif-checkmark" style="color: green;">
	   				<span style="padding-left: 10">
						<spring:message code="${success}" />
	   				</span>
	   			</div>
	   		</div>
	    </div>
	</c:if>
	<c:if test="${not empty errors}">
		<c:forEach var="e" items="${errors}" varStatus="counter">
			<div class="row no-margin padding5">
		        <div class="cell colspan6">
	   	    		<div class="input-state-error mif-notification" style="color: red;">
	       				<span style="padding-left: 10">
							<spring:message code="${e.defaultMessage}" />
	       				</span>
	       			</div>
	       		</div>
	       	</div>
		</c:forEach>
	</c:if>
	<h2><spring:message code="label.all.categories" /></h2> <a href="<c:url value='/${entityName}/new' />"><spring:message code="label.create.${entityName}" /></a>
	<c:set var="entityFields" value="${['id','name']}" scope="request" />
	<tiles:insertAttribute name="table" />
</div>
