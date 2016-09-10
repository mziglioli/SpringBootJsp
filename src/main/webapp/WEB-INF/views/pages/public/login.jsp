<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<form action="<c:url value='/public/login' />" method="post" class="form-horizontal">
     <c:if test="${param.error != null}">
         <div class="alert alert-danger">
             <p><spring:message code="error.invalid.login" /></p>
         </div>
     </c:if>
     <c:if test="${param.logout != null}">
         <div class="alert alert-success">
             <p><spring:message code="msg.loggout.success" /></p>
         </div>
     </c:if>
     <h2 class="form-signin-heading"><spring:message code="label.sign.in" /></h2>
     <label for="inputEmail" class="sr-only"><spring:message code="label.username" /></label>
     <input type="email" id="username" class="form-control" name="username" placeholder="<spring:message code="label.username" />" required autofocus>
     <label for="inputPassword" class="sr-only"><spring:message code="label.password" /></label>
     <input type="password" id="inputPassword" class="form-control" name="password" placeholder="<spring:message code="label.password" />" required>
     <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
  <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
  </form>
</div>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>