<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 공지사항</title>
<link rel="stylesheet" type="text/css" href="resource/css/hf.css">
<script src="resource/js/jquery-3.6.0.min.js"></script>
<script src="resource/js/jquery.serializeObject.js"></script>
<script type="text/javascript">
$(document).ready(function() {

	
});

function getNotice() {
	var formData = new FormData();
	
	$.ajax({
		type : "GET",
		url : "/getNotice",
		success : function(res) {
			console.log(res);
		},
		err : function(err) {
			console.log("err:", err)
		}
	});
}
</script>
</head>
	<header>
	
	
	</header>

	<section>
		<button id="" value="출력" onclick="getNotice()">출력</button>
	</section>
	
	<footer>
		
	</footer>

</body>
</html>