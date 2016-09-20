<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="cell auto-size padding20 bg-white" id="cell-content">
	<div class="row">
		<div class="cell colspan5">
			<h2><spring:message code="label.update.product" /></h2>
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
					<div class="input-control full-size" data-role="select" data-template-result="fmtState" data-placeholder="<spring:message code="label.select.category" />">
                        	<select class="full-size" name="category.id" id="form-${entityName}-category-select" >
                        	<option></option>
					    	<c:if test="${not empty categories}">
								<c:forEach var="c" items="${categories}" varStatus="counter">
									<c:if test="${entity.category.id eq c.id}">
										<option  value="${c.id}" selected="selected" >${c.name}</option>									
									</c:if>
									<c:if test="${entity.category.id ne c.id}">
										<option  value="${c.id}" >${c.name}</option>									
									</c:if>
						        </c:forEach>
							</c:if>
					    </select>
					</div>
				 	<div class="input-control text full-size">
						<span class="mif-user prepend-icon"></span> 
						<input value="${entity.price}" id="form-${entityName}-price" type="number" class="form-control" name="price" placeholder="<spring:message code="label.price" />" required maxlength="255">
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