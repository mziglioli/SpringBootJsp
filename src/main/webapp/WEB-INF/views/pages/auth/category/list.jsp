<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
function deleteDialog(id){
    var dialog = $("#deleteDialog_"+id).data('dialog');
    if (!dialog.element.data('opened')) {
        dialog.open();
    } else {
        dialog.close();
    }
}
</script>
<div class="cell auto-size padding20 bg-white" id="cell-content">
	<c:if test="${not empty success}">
		<div class="row no-margin padding5">
	        <div class="cell colspan6">
	    		<div class="input-state-info mif-checkmark" style="color: green;">
	   				<span style="padding-left: 10">
						<spring:message code="${success}" />
	   				</span>
	   			</div>
	   		</div>
	    </div>
	</c:if>
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
	<h2><spring:message code="label.all.categories" /></h2> <a href="<c:url value='/${entityName}/new' />"><spring:message code="label.create.category" /></a>
	<table class="table striped hovered cell-hovered border bordered">
		<tr>
			<th class="sortable-column"><spring:message code="label.id" /></th>
			<th class="sortable-column"><spring:message code="label.name" /></th>
			<th><spring:message code="label.edit" /></th>
			<th><spring:message code="label.delete" /></th>
		</tr>
		<c:forEach var="c" items="${entityList}" varStatus="counter">
			<tr>
				<td>${c.id}</td>
				<td>${c.name}</td>
				<td><a href="<c:url value="/${entityName}/${c.id}"/>"><span class="mif-pencil prepend-icon"></span> </a></td>
				<td><a href="javascript:deleteDialog('${c.id}')"><span class="mif-bin prepend-icon"></span> </a></td>
			</tr>
			<div data-role="dialog" id="deleteDialog_${c.id}" class="padding20" data-close-button="true" data-windows-style="true">
            	<div class="container">
                	<h1><spring:message code="label.delete.msg" arguments="${c.name}"/></h1>
                	<form action="<c:url value='${deleteURL}${c.id}'/>" method="post" class="form-horizontal" >
                		<input hidden="true" value="${c.id}" type="text" id="form-${entityName}-id" name="id">
	   					<button class="button primary" type="submit">
		                	<span class="mif-checkmark prepend-icon"></span>
		                	<span>
			                	<spring:message code="label.yes" />
			                </span>	
		                </button>
	                </form>
	                <a class="button danger" onclick="deleteDialog('${c.id}')">
	                	<span class="mif-cancel prepend-icon"></span>
	                	<span><spring:message code="label.no" /></span>
	                </a>
            	</div>
        	</div>
		</c:forEach>
	</table>
</div>