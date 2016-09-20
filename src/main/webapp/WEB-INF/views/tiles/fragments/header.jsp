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
		<ul class="app-bar-menu">
           	<li>
               	<a href="" class="dropdown-toggle">
               		<sec:authorize access="isAuthenticated()">
						<sec:authentication property="principal.username" /> 
					</sec:authorize>
               	</a>
               	<ul class="d-menu" data-role="dropdown">
                   	<li><a href="<c:url value='/profile' />"><spring:message code="label.profile" /></a></li>
                   	<li class="divider"></li>
                   	<li><a href="<c:url value='/public/logout' />"><spring:message code="label.logout" /></a></li>
               	</ul>
           	</li>
       	</ul>
	</div>
</div>
