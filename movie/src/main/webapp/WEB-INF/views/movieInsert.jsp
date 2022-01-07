<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		
		<hr>
		
	<form action="">
		<input id="Cd">
		<input id="Nm">
		<input id="Genre">
		<input id="Dt">
		<input id="GN">
		<input id="poster">
	</form>
		
		<input type="button" id="searchList">
		<table id="apiList" >
			<tr>
				<th>영화코드</th>
				<th>영화명</th>
				<th>대표장르</th>
				<th>개봉일</th>
				<th>장르</th>
			</tr>
		</table>
	
</body>

	<script src="resource/js/jquery-3.6.0.min.js"></script>
	<script src="resource/js/jquery.serializeObject.js"></script>
	<script type="text/javascript">
	function view(){
		
		
		
	}
	
	$(document).ready(function(){
		
		
		$("#searchMovie").click(function(){
			var todate = $("#today").val();
			var params = {date : todate}
			var names = $.param(params)
				
			
			$.ajax({
				url: "insertApiMovie?"+names
				,type: "get"
				,success: function(result){
						view();					
				}, // success end
				error: function(error) {
					console.log(error);
				} // error end
			}) // ajax end	
		});
		
		$("#searchList").click(function() {
			var todate = $("#today").val();
			var params = {date : todate}
			var names = $.param(params)
			
			
			$.ajax({
				url: "insertApiMovie?"+names
				,type: "GET"
				,success: function(result) {
					
					var apiList = "";
					
					for(var i=0; i<result.length; i++){
						
						apiList += "<tr>"
									+ "<td movieCd="+result[i].movieCd.replaceAll(" ", "&nbsp;")+ " >" + result[i].movieCd + "</td>"
									+ "<td movieNm=" + result[i].movieNm.replaceAll(" ", "&nbsp;")+ " >" + result[i].movieNm + "</td>"
									+ "<td repGenreNm="+result[i].repGenreNm.replaceAll(" ", "&nbsp;")+">" + result[i].repGenreNm + "</td>"
									+ "<td openDt="+result[i].openDt.replaceAll(" ", "&nbsp;")+">" + result[i].openDt + "</td>"
									+ "<td genreAlt="+result[i].genreAlt.replaceAll(" ", "&nbsp;")+">" + result[i].genreAlt + "</td>"
									+ "</tr>"
					}
					$("#apiList").html(apiList);
				}
			,error: function(error) {
				
			}
			});
		})
		
		$(document).on('click',"#apiList tr",function() {
			//$("#apiList tr")
			var tr = $(this);
			var td = tr.children();
			
			var movieCd = $(td).eq(0).attr("movieCd");
			var movieNm = $(td).eq(1).attr("movieNm");
			var repGenreNm = $(td).eq(2).attr("repGenreNm");
			var openDt = $(td).eq(3).attr("openDt");
			var genreAlt = $(td).eq(4).attr("genreAlt");
			//폼의 input 에 저장
			$("#Cd").val(movieCd);
			$("#Nm").val(movieNm);
			$("#Genre").val(repGenreNm);
			$("#Dt").val(openDt);
			$("#GN").val(genreAlt);
			
		});		
		
	});

	</script>
</html>