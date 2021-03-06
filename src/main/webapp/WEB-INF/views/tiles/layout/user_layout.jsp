<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
	<title><spring:message code="label.project.name" /></title>
	<link href="<c:url value='/static/metro/css/metro.css' />" rel="stylesheet">
	<link href="<c:url value='/static/metro/css/metro-schemes.css' />" rel="stylesheet">
	<link href="<c:url value='/static/metro/css/metro-icons.css' />" rel="stylesheet">
	<link href="<c:url value='/static/metro/css/metro-responsive.css' />" rel="stylesheet">
	<link href="<c:url value='/static/metro/css/docs.css' />" rel="stylesheet">
	
	<script src="<c:url value='/static/metro/js/jquery-2.1.3.min.js' />"></script>
	<script src="<c:url value='/static/metro/js/select2.min.js' />"></script>
	<script src="<c:url value='/static/metro/js/metro.js' />"></script>
    <script src="<c:url value='/static/metro/js/docs.js' />"></script>
    <script src="<c:url value='/static/metro/js/ga.js' />"></script>
	<script src="<c:url value='/static/metro/js/jquery.dataTables.min.js' />"></script>
	<script src="<c:url value='/static/metro/js/page.js' />"></script>
</head>
	<body style="height: 100%; padding-bottom:120px;">
	<tiles:insertAttribute name="header" />
	<div class="page-content" style="height: 100%;">
		<div class="grid responsive flex-grid">
		    <div class="row">
	    	    <tiles:insertAttribute name="menu" />
	    	    <tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>	
	<tiles:insertAttribute name="footer" />
</body>
</html>