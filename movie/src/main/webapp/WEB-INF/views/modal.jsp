<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 보러 가자~</title>
<link rel="stylesheet" href="resource/css/bootstrap.min.css">
<script src="resource/js/jquery-3.6.0.min.js"></script>
<script src="resource/js/jquery.serializeObject.js"></script>
<script src="resource/js/bootstrap.bundle.min.js"></script>
<style type="text/css">
.myModal.modal-fullsize {
	width: 100%;
	height: 100%;
	margin: 0;
	padding: 0;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$('#myModal').on('shown.bs.modal', function () {
	})
});

/*모달*/
function fnModuleInfo(str){
   $('#rsrvModal .modal-content').load("rsrv");
   $('#rsrvModal').modal();
   
}

</script>
</head>
<body>
	<h1>모달테스트</h1>
	<!-- Moa Modal Button -->
	<a onclick="fnModuleInfo('aaaa')"> <i class="fa fa-arrow-circle-right fa-lg">모달</i></a>
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal2">
  Launch demo modal
</button>
	<!-- Moa Modal-->
	
	<div class="modal fade" id="rsrvModal" tabindex="-1" role="dialog"	aria-labelledby="label" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog modal-xl" role="document">
			<div class="modal-content"></div>
		</div>
	</div> 
	
</body>





</html>