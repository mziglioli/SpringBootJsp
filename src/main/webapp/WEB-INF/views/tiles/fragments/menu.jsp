<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="container page-content">
	<div class="grid responsive">
	    <div class="row cells4">
    	    <div class="cell">
        	    <h5></h5>
	            <ul class="sidebar no-responsive-future" id="sidebar-2">
	            	<!-- FIXME -->
	            	<sec:authorize access="isAuthenticated()">
						<c:forEach var="menu" items="${menus}" varStatus="id">
  							 <li><a href="<c:url value='${menu.link}'/>">
			                    <span class="${menu.icon}"></span>
			                    <span class="title"><spring:message code="${menu.name}" /></span>
			                    <span class="counter">0</span>
			                </a></li>
  						</c:forEach>
					</sec:authorize>
				</ul>
	        </div>
		</div>
	</div>
</div>


