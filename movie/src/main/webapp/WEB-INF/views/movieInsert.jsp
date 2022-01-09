<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 영화등록</title>
</head>
<body>	

	<jsp:include page="business_header.jsp"></jsp:include>
	<jsp:include page="business_sidebar.jsp"></jsp:include>

	<div id="insert_wrap">
		<div id="title_wrap">
			영화 등록
		</div>
		<hr>
		<div id="dateSelete_wrap">
			<input type="date" id="today">
			<input type="button" id="searchMovie" value="영화 조회"><input type="button" id="searchList" value="목록 출력">
		</div>
	</div>

	<form action="./movieInsertProc" method="post" enctype="multipart/form-data">
<!-- 		등록한 영화가 어느 영화관에서 등록됐는지 알아야 함 movie테이블에 b_id? 아니면 business테이블에 th_code? -->
<%-- 		<input type="hidden" id="businessInfo" value="${businessInfo.b_id }"> --%>
		<input id="Cd" name="movie_cd" readonly="readonly">
		<input id="Nm" name="movie_nm">
		<input type="hidden" id="Genre">
		<input id="Dt" name="open_dt">
		<input id="GN" name="genre_nm">
		<input name="show_tm" placeholder="상영시간">
		<input name="directors" placeholder="감독">
		<input name="actors" placeholder="배우">
		<select name="show_types">
			<option disabled="disabled">상영 종류</option>
			<option value="2D">2D</option>
			<option value="4DX">4DX</option>
		</select>
		<select name="watch_grade_nm">
			<option disabled="disabled">관람등급</option>
			<option value="전체">전체관람가</option>
			<option value="12세 이상">12세 이상</option>
			<option value="15세 이상">15세 이상</option>
			<option value="청소년 관람불가">청소년 관람불가</option>
		</select>
		<!-- 파일 처리 영역 -->
		<input type="file" name="moviePoster" id="posterFile">
		<input type="text" readonly="readonly" id="upload-name" value="포스터 선택">
		<input type="hidden" id="fileCheck" value="0" name="fileUp">
		<input type="submit" value="영화등록">
	</form>
		
		
		<table>
			<tr>
				<th>영화코드</th>
				<th>영화명</th>
				<th>대표장르</th>
				<th>개봉일</th>
				<th>장르</th>
			</tr>
		</table>
		<table id="apiList" >
			
		</table>
	
</body>

	<script src="resource/js/jquery-3.6.0.min.js"></script>
	<script src="resource/js/jquery.serializeObject.js"></script>
	<script type="text/javascript">
	
	$(document).ready(function(){
		
		var msg = "${msg}";
		if(msg != "") {
			alert(msg);
		}
		
		$("#searchMovie").click(function(){
			var todate = $("#today").val();
			var params = {date : todate}
			var names = $.param(params)
				
			
			$.ajax({
				url: "insertApiMovie?"+names
				,type: "get"
				,success: function(result){
					alert
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
		
		// 업로드할 파일을 선택하면 upload-name요소에 파일 이름 출력, file
		$("#posterFile").on("change", function() {
			// 파일 목록 가져오기
			var fileUpload = $("#posterFile")
			console.log(fileUpload);
			
			var fileName = "";
			
			if(fileUpload.length == 1) {
				fileName = fileUpload.name
			}
			
			$("#upload-name").val(fileName);
			
			// 파일 취소 시
			if(fileName == "") {
				$("#fileCheck").val(0);
				$("#upload-name").val("포스터 선택")
			}
			// 파일 선택 시
			else {
				$("#fileCheck").val(1);
			}
		});
		
	});

	</script>
</html>