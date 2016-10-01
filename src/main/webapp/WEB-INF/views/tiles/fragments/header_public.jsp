<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="app-bar darcula" data-role="appbar">
	<ul class="app-bar-menu" style="padding-left: 20%">
		<li>
			<img src="<c:url value='/static/metro/images/wn8.png' />" style="height: 28px; display: inline-block; padding:0 10px 0 10px ">
			<spring:message code="label.project.name" />
		</li>
	</ul>
	<div class="app-bar-element place-right" style="padding-right: 10%">
		<a class="dropdown-toggle fg-white"><span class="mif-enter"></span>
			<spring:message code="label.enter" />
		</a>
		<div class="app-bar-drop-container place-right" data-role="dropdown"
			data-no-close="true">
			<div class="padding20">
				<form action="<c:url value='/public/login' />" method="post" class="form-horizontal">
					<h4><spring:message code="label.login.project"/></h4>
			    	<c:if test="${param.error != null}">
			        	<div class="alert alert-danger">
			            	<p><spring:message code="error.invalid.login" /></p>
			         	</div>
			     	</c:if>
					<div class="input-control text">
						<span class="mif-user prepend-icon"></span> 
						<input type="email" id="username" class="form-control" value="admin@admin.com"  name="username" placeholder="<spring:message code="label.username" />" required autofocus>
					</div>
					<div class="input-control text">
						<span class="mif-lock prepend-icon"></span> 
						<input type="password" id="inputPassword" class="form-control" name="password" value="admin" placeholder="<spring:message code="label.password" />" required>
    						<input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
					</div>
					<div class="form-actions">
						<button class="button primary" type="submit"><spring:message code="label.login" /></button>
						<button class="button link fg-grayLighter" type="reset"><spring:message code="label.cancel" /></button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>