<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 영화등록</title>
	<script src="resource/js/jquery-3.6.0.min.js"></script>

</head>
<body>
		<input type="button" id="searchMovie">
		<input type="date" id="today">
	
</body>

	<script src="resource/js/jquery-3.6.0.min.js"></script>
	<script src="resource/js/jquery.serializeObject.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		
		$("#searchMovie").click(function(){
			var todate = $("#today").val();
			var params = {date : todate}
			var names = $.param(params)
			
			$.ajax({
				url: "insertApiMovie?"+names
				,type: "get"
				,success: function(result){
					
				}, // success end
				error: function(error) {
					console.log(error);
				} // error end
			}) // ajax end	
		});
		
	});

	</script>
</html>