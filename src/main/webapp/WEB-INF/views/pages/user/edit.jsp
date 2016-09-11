<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="cell auto-size padding20 bg-white" id="cell-content">
	<h2><spring:message code="label.new.user" /></h2>
	<a style="color: red;" href="<c:url value='/user/' />"><spring:message code="label.cancel" /></a>
	<form action="<c:url value='/user' />" method="put" class="form-horizontal">
	    <div class="grid">
	        <div class="row cells12">
	        	<div class="input-control text flex-dir-row full-size">
					<span class="mif-user prepend-icon"></span> 
					<input type="text" id="name" class="form-control" name="name" placeholder="<spring:message code="label.name" />" required autofocus size="200" maxlength="200" value="${entity.nome}"/>
				</div>
			 </div>
	    </div>
     	<div class="grid">
	        <div class="row cells12">
				<div class="input-control text full-size">
					<span class="mif-user prepend-icon"></span> 
					<input type="email" id="username" class="form-control" name="username" placeholder="<spring:message code="label.username" />" required value="${entity.username}"/>
				</div>
		</div>
		</div>
		<div class="form-actions">
			<button class="button primary" type="submit"><spring:message code="label.update" /></button>
			<button class="button link fg-grayLight" type="reset"><spring:message code="label.cancel" /></button>
		</div>
	</form>
</div>