<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
 	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title><spring:message code="label.project.name" /></title>
	<link href="<c:url value='/static/metro/css/metro.css' />" rel="stylesheet">
	<link href="<c:url value='/static/metro/css/metro-schemes.css' />" rel="stylesheet">
	<link href="<c:url value='/static/metro/css/metro-icons.css' />" rel="stylesheet">
	<link href="<c:url value='/static/metro/css/metro-responsive.css' />" rel="stylesheet">
	<script src="<c:url value='/static/metro/js/jquery-2.1.3.min.js' />"></script>
	<script src="<c:url value='/static/metro/js/metro.js' />"></script>
</head>
	<body>
	<tiles:insertAttribute name="header" />
	<div class="page-content">
		<div class="grid responsive flex-grid">
		    <div class="row cells4">
	    	    <tiles:insertAttribute name="menu" />
	    	    <tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>	
	<tiles:insertAttribute name="footer" />
</body>
</html>