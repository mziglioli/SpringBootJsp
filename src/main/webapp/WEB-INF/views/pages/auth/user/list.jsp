<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	<h2><spring:message code="label.list.user" /></h2> <a href="<c:url value='/${entityName}/new' />"><spring:message code="label.create.user" /></a>
	<c:set var="entityFields" value="${['id','name','username','status','authorities']}" scope="request" />
	<tiles:insertAttribute name="table" />
</div>