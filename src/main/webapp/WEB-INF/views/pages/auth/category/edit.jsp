<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="cell auto-size padding20 bg-white" id="cell-content">
	<div class="row">
		<div class="cell colspan5">
			<h2><spring:message code="label.update.category" /></h2>
		</div>
		<div class="cell colspan6">
			<a style="color: red;" href="<c:url value='/${entityName}/' />"><spring:message code="label.cancel" /></a>
		</div>
	</div>	
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
	<form action="<c:url value='${updateURL}' />" method="post" class="form-horizontal" name="${entityName}" >
		<input hidden="true" value="${entity.id}" type="text" id="form-${entityName}-id" name="id">
	    <div class="flex-grid">
	        <div class="row no-margin">
		        <div class="cell colspan6">
		        	<div class="input-control text flex-dir-row full-size">
						<span class="mif-user prepend-icon"></span> 
						<input value="${entity.name}" type="text" id="form-${entityName}-name" class="form-control" name="name" placeholder="<spring:message code="label.name" />" required autofocus maxlength="255">
					</div>
				</div>	
			 </div>
			<div class="form-actions">
				<button class="button primary" type="submit" id="form-${entityName}-submit"><spring:message code="label.update" /></button>
				<button class="button link fg-grayLight" type="reset" id="form-${entityName}-cancel"><spring:message code="label.cancel" /></button>
			</div>
		</div>	
	</form>
</div>