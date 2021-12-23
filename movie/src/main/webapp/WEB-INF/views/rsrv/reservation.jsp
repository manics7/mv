<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head >
<meta charset="UTF-8">
<link rel="stylesheet" href="resource/css/bootstrap.css">
<script src="resource/js/jquery-3.6.0.min.js"></script>
<script src="resource/js/jquery.serializeObject.js"></script>
<script src="resource/js/bootstrap.bundle.js"></script>
<style type="text/css">



</style>
<script type="text/javascript">


$(document).ready(function() {
	
});


</script>
</head>
<!-- histoty Modal-->
<div class="modal-content">
	<div class="modal-header">
	    <h5 class="modal-title" id="historyModalLabel">빠른예매</h5>
	    <button type="button" class="btn btn-secondary my-2 my-sm-0">초기화</button>
	    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
	        <span aria-hidden="true">×</span>
	    </button>
	</div>
	<div class="modal-body">
	        <div class="container">
	        
	        <button type="button" class="btn btn-secondary my-2 my-sm-0" id="rsrvSeat" onclick="rsrvSeat()">좌석선택</button>
	        </div>
	</div>
	<div class="modal-footer">
	    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
	</div>
</div>
<div class="modal fade" id="MoaModal" tabindex="-1" role="dialog"	aria-labelledby="historyModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl" role="document">
			<div class="modal-content"></div>
		</div>
	</div>


</html>