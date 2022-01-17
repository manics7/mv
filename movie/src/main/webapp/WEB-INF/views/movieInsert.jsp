<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 영화등록</title>
<link rel="stylesheet" type="text/css" href="resource/css/movieInsert.css">
</head>
<body>	
	<nav style="padding-top:35px; padding-bottom : 35px; background: #1d1d1d;">
	<jsp:include page="business_header.jsp"></jsp:include>
	</nav>
	
	<div class="main_wrap">
		<div class="container_wrap">
			<div>
				<jsp:include page="business_sidebar.jsp"></jsp:include>
			</div>
			<div class="insertMovie">
				<div id="title_wrap">
					<h2>영화 등록</h2>
				</div>
				<hr>
				<div id="dateSelete_wrap">
					<input type="date" id="today">
					<input type="button" id="searchMovie" value="영화 조회">
					<input type="button" id="searchList" value="목록 출력">
				</div>

				<form action="./movieInsertProc" method="post" enctype="multipart/form-data">
					<input id="Cd" name="movie_cd" readonly="readonly" placeholder="영화코드">
					<input id="Nm" name="movie_nm" placeholder="영화명">
					<input type="hidden" id="Genre">
					<br>
					<input id="Dt" name="open_dt" placeholder="개봉일">
					<input id="GN" name="genre_nm" placeholder="장르">
					<input id="Tm" name="show_tm" placeholder="상영시간">
					<br>
					<input id="dr" name="directors" placeholder="감독">
					<input id="ac" name="actors" placeholder="배우">
					<br>
					<select name="show_types">
						<option value="">상영 종류</option>
						<option value="2D">2D</option>
						<option value="4DX">4DX</option>
					</select>
					<select name="watch_grade_nm">
						<option value="">관람등급</option>
						<option value="all">전체관람가</option>
						<option value="12">12세 이상</option>
						<option value="15">15세 이상</option>
						<option value="18">청소년 관람불가</option>
					</select>
					<input type="text" readonly="readonly" class="upload-name" value="포스터 선택">
					<br>
					<!-- 파일 처리 영역 -->
					<input type="file" name="poster" id="file">
					
					<input type="hidden" id="filecheck" value="0" name="fileCheck">
					<br>
					<input type="submit" value="영화등록">
				</form>
		
				<table>
					<tr>
						<th class="e">영화코드</th>
						<th class="nm">영화명</th>
						<th class="e">개봉일</th>
						<th class="e">장르</th>
					</tr>
				</table>
				<table id="apiList" >
			
				</table>
			</div>
		</div>
	</div>
	
	<footer>
	<jsp:include page="footer.jsp"></jsp:include>
	</footer>
		
		
</body>

	<script src="resource/js/jquery-3.6.0.min.js"></script>
	<script src="resource/js/jquery.serializeObject.js"></script>
	<script type="text/javascript">
		
	$(document).ready(function() {
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
								 	+ "<td class='e' movieCd="+result[i].movieCd.replaceAll(" ", "&nbsp;")+ " >" + result[i].movieCd + "</td>"
									+ "<td class='nm' movieNm=" + result[i].movieNm.replaceAll(" ", "&nbsp;")+ " >" + result[i].movieNm + "</td>"
// 									+ "<td repGenreNm="+result[i].repGenreNm.replaceAll(" ", "&nbsp;")+">" + result[i].repGenreNm + "</td>"
									+ "<td class='e' openDt="+result[i].openDt.replaceAll(" ", "&nbsp;")+">" + result[i].openDt + "</td>"
									+ "<td class='e' genreAlt="+result[i].genreAlt.replaceAll(" ", "&nbsp;")+">" + result[i].genreAlt + "</td>"
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
// 			var repGenreNm = $(td).eq(2).attr("repGenreNm");
			var openDt = $(td).eq(2).attr("openDt");
			var genreAlt = $(td).eq(3).attr("genreAlt");
			//폼의 input 에 저장
			$("#Cd").val(movieCd);
			$("#Nm").val(movieNm);
// 			$("#Genre").val(repGenreNm);
			$("#Dt").val(openDt);
			$("#GN").val(genreAlt);
			
		});		
		
		// 업로드할 파일을 선택하면 upload-name요소에 파일 이름 출력, file
		$("#file").on("change", function() {
			// 파일 목록 가져오기
			var files = $("#file")[0].files;
			console.log(files);
			
			var fileName = "";
			
			if(files.length == 1) {
				fileName = files[0].name;
			}
			
			$(".upload-name").val(fileName);
			
			// 파일 취소 시
			if(fileName == "") {
				$("#fileCheck").val(0);
				$(".upload-name").val("포스터 선택");
			}
			// 파일 선택 시
			else {
				$("#fileCheck").val(1);
			}
		});		
	});

	</script>
</html>