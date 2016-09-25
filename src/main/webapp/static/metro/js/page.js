function buildDataTable(objName, tableAttr){
	 $('#'+objName).DataTable({
		 "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, tableAttr.all]],
	     "language": {
	    	 "lengthMenu": tableAttr.display + " _MENU_ " + tableAttr.records,
	    	 "zeroRecords": tableAttr.empty,
	    	 "info": tableAttr.showing + " _PAGE_ " + tableAttr.of + "_PAGES_",
	    	 "infoEmpty": tableAttr.emptyRecords,
	    	 "infoFiltered": tableAttr.filter + "_MAX_" + tableAttr.totalRecords
	     }
	 });
}