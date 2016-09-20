<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="cell auto-size padding20 bg-white" id="cell-content">
	<div class="row">
		<div class="cell colspan5">
			<h2><spring:message code="label.update.user" /></h2>
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
		<input hidden="true" value="${form.id}" type="text" id="form-${entityName}-id" name="id">
	    <div class="flex-grid">
	        <div class="row no-margin">
		        <div class="cell colspan6">
		        	<div class="input-control text flex-dir-row full-size">
						<span class="mif-user prepend-icon"></span> 
						<input value="${form.name}" type="text" id="form-${entityName}-name" class="form-control" name="name" placeholder="<spring:message code="label.name" />" required autofocus maxlength="255">
					</div>
					<div class="input-control text full-size">
						<span class="mif-mail prepend-icon"></span> 
						<input value="${form.username}" id="form-${entityName}-username" type="email" class="form-control" name="username" placeholder="<spring:message code="label.username" />" required maxlength="255">
					</div>
					<div class="input-control full-size" data-role="select" data-multiple="true" data-placeholder="Select a state">
						<span style="font-weight: bold;"><spring:message code="label.authorities" />: </span>
                        <label class="switch-original"><spring:message code="label.user" />
                        	<input type="checkbox" name="user" id="form-${entityName}-auth-user" 
                        		<c:if test="${form.user eq true}">checked="checked"</c:if>>
	                        <span class="check"></span>
                        </label>
                        <label class="switch-original"><spring:message code="label.manager" />
                        	<input type="checkbox" name="manager" id="form-${entityName}-auth-manager" 
                        		<c:if test="${form.manager eq true}">checked="checked"</c:if>>
	                        <span class="check"></span>
                        </label>
                        <label class="switch-original"><spring:message code="label.admin" />
                        	<input type="checkbox" name="admin" id="form-${entityName}-auth-admin" 
                        		<c:if test="${form.admin eq true}">checked="checked"</c:if>>
	                        <span class="check"></span>
                        </label>
                    </div>
                    <div class="cell">
                        <span style="font-weight: bold;"><spring:message code="label.status" />: </span>
                        <label class="input-control radio small-check">
                            <input type="radio" name="status"  id="form-${entityName}-status-active"
	                            <c:if test="${form.status eq 'Ativo'}">checked</c:if>>
                            <span class="check"></span>
                            <span class="caption"><spring:message code="label.active" /></span>
                        </label>    
                        <label class="input-control radio small-check">
                            <input type="radio" name="status" id="form-${entityName}-status-inactive"
	                            <c:if test="${form.status eq 'Desativo'}">checked</c:if>>
                            <span class="check"></span>
                            <span class="caption"><spring:message code="label.inactive" /></span>
                        </label>
					</div>
					<script type="text/javascript">
						//TODO
					</script>
				</div>	
			 </div>
			<div class="form-actions">
				<button class="button primary" type="submit" id="form-${entityName}-submit"><spring:message code="label.update" /></button>
				<button class="button link fg-grayLight" type="reset" id="form-${entityName}-cancel"><spring:message code="label.cancel" /></button>
			</div>
		</div>	
	</form>
</div>