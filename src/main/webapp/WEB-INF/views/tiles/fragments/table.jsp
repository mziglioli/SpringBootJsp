<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<table id="table_${entityName}" class="display table striped hovered cell-hovered border bordered">
	<thead>
		<tr>
			<c:forEach var="f" items="${entityFields}">
				<th class="sortable-column"><spring:message code="label.${f}" /></th>
			</c:forEach>
			<th><spring:message code="label.edit" /></th>
			<th><spring:message code="label.delete" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="entity" items="${entityList}" varStatus="counter">
			<tr>
				<c:forEach var="f" items="${entityFields}">
					<td>${entity.getValueByPropertyName(f)}</td>
				</c:forEach>
				<td style="width: 80px !important"><a href="<c:url value="/${entityName}/${entity.id}"/>"><span class="mif-pencil prepend-icon"></span> </a></td>
				<td style="width: 80px !important"><a href="javascript:deleteDialog('${entity.id}')"><span class="mif-bin prepend-icon"></span> </a></td>
			</tr>
			<div data-role="dialog" id="deleteDialog_${entityName}_${entity.id}" class="padding20" data-close-button="true" data-windows-style="true">
	    		<div class="container">
		        	<h1><spring:message code="label.delete.msg" arguments="${entity.name}"/></h1>
		        	<form action="<c:url value='${deleteURL}${entity.id}'/>" method="post" class="form-horizontal" >
		        		<input hidden="true" value="${entity.id}" type="text" id="form-${entityName}-id" name="id"/>
						<button class="button primary" type="submit">
		          			<span class="mif-checkmark prepend-icon"></span>
		          			<span>
					        	<spring:message code="label.yes" />
					        </span>	
					    </button>
		         	</form>
		         	<a class="button danger" onclick="deleteDialog('${entity.id}')">
		         		<span class="mif-cancel prepend-icon"></span>
		         		<span><spring:message code="label.no" /></span>
		         	</a>
	    		</div>
			</div>
		</c:forEach>
	</tbody>
</table>
	
<script>
$(document).ready(function() {
	var tableAttr = {
			all:'<spring:message code="label.table.all" />',
			display:'<spring:message code="label.table.display" />',
			records:'<spring:message code="label.table.records" />',
			empty:'<spring:message code="label.table.empty" />',
			showing:'<spring:message code="label.table.showing" />',
			of:'<spring:message code="label.table.of" />',
			emptyRecords:'<spring:message code="label.table.empty.records" />',
	    	filters:'<spring:message code="label.table.filter" />',
	    	totalRecords:'<spring:message code="label.table.total.records" />'
	};
	buildDataTable('table_${entityName}', tableAttr);
});
function deleteDialog(id){
    var dialog = $("#deleteDialog_${entityName}_"+id).data('dialog');
    if (!dialog.element.data('opened')) {
        dialog.open();
    } else {
        dialog.close();
    }
}
</script>	