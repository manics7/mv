<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화관 등록</title>
<link rel="stylesheet" href="resource/css/theaterList.css">
<link rel="stylesheet" href="resource/css/theaterAdd.css">
<script src="resource/js/jquery-3.6.0.min.js"></script>
<script src="resource/js/jquery.serializeObject.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	//'로고' 파일을 선택할 경우
	$("#logoFile").on("change", function() {
		//로고 사진 파일 입력창에서 선택한 파일의 목록 가져오기
		var Lfiles = $("#logoFile")[0].files;
		console.log(Lfiles);
		
		var LfileName = "";
		
		//'파일선택' 부분에 파일 이름 표시
		if(Lfiles.length > 1) {//한 개 이상의 파일을 선택할 경우
			LfileName = Lfiles[0].name + " 외 " + (Lfiles.length - 1) + "개";
		} else if (Lfiles.length == 1) {
			LfileName = Lfiles[0].name;
		}
		
		$(".logo-name").val(LfileName);
		
		//파일이 선택 됐는지 안 됐는지 체크하는 부분
		if(LfileName == "") {//취소할 경우
			$("#logo-check").val(0);
			$(".logo-name").val("파일선택");
		} else {//파일을 선택할 경우
			$("#logo-check").val(1);
		}
		
	});
	
	//'영화관 사진' 파일을 선택할 경우
	$("#theaterFile").on("change", function() {
		//영화관 사진 파일 입력창에서 선택한 파일의 목록 가져오기
		var Tfiles = $("#theaterFile")[0].files;
		console.log(Tfiles);
		
		var TfileName = "";
		
		//'파일선택' 부분에 파일 이름 표시
		if(Tfiles.length > 1) {//하나 이상의 파일을 선택할 경우
			TfileName = Tfiles[0].name + " 외 " + (Tfiles.length - 1) + "개";
		} else if(Tfiles.length == 1) {
			TfileName = Tfiles[0].name;
		}
		
		$(".theater-name").val(TfileName);
		
		//파일이 선택 됐는지 안 됐는지 체크하는 부분
		if(TfileName == "") {//취소할 경우
			$("#theater-check").val(0);
			$(".theater-name").val("파일선택");
		} else {//파일을 선택할 경우
			$("#theater-check").val(1);
		}
		
	});
	
}); 
</script>
</head>
<body>
<section>
<div class="detail">
	<div class="inner">
		<div id="page_wrap">
			<div id="side">
				<h2><a href="#">Business Page</a></h2>
				<ul id="bupage_list">
					<li><a id="bupage_menu" href="#">영화관 관리</a></li>
					<li><a id="bupage_menu" href="#">영화 관리</a></li>
					<li><a id="bupage_menu" href="#">상영관 관리</a></li>
					<li><a id="bupage_menu" href="#">상영 일정 관리</a></li>
					<li><a id="bupage_menu" href="#">이벤트 관리</a></li>
				</ul>
			</div>
			<div id="content">
				<div id="thadd_title">
					<h2>영화관 등록</h2>
				</div>
				<div id="thadd_cont">
					<form method="post" enctype="multipart/form-data"
						action="./theaterAdd" class="thadd_form">
						<input type="hidden" name="bid" value="btest02">
						<span>영화관 이름</span><br>
						<input type="text" title="영화관 이름" name="thname"><br>
						<span>주소(오시는 길)</span><br>
						<input type="text" title="오시는 길" name="thplace"><br>
						<span>영화관 연락처</span><br>
						<input type="text" title="연락처" name="thtel"><br>
						<span>주차 안내</span><br>
						<textarea cols="100" rows="5" name="thpark"></textarea><br>
						<span>영화관 소개</span><br>
						<textarea cols="100" rows="5" name="thintro"></textarea><br>
						<span>지역 코드</span>
						<select id="reNum" name="reNum"> 
							<option value="11" selected>서울</option>
							<option value="23">인천</option>
							<option value="31">경기</option>
							<option value="32">강원</option>
							<option value="33">충북</option>
							<option value="34">충남</option>
							<option value="35">전북</option>
							<option value="36">전남</option>
							<option value="37">경북</option>
							<option value="38">경남</option>
							<option value="39">제주</option>
						</select><br>
						<span>로고 이미지</span>
							<label for="logoFile">업로드</label>
							<input type="file" name="logoFiles" id="logoFile" multiple>
							<input type="text" class="logo-name" value="파일선택" readonly>
							<!-- 업로드할 파일이 있으면 1, 없으면 0 -->
							<input type="hidden" id="logo-check" value="0" name="logoCheck"><br>
						<span>영화관 사진</span>
							<label for="theaterFile">업로드</label>
							<input type="file" name="theaterFiles" id="theaterFile" multiple>
							<input type="text" class="theater-name" value="파일선택" readonly>
							<!-- 업로드할 파일이 있으면 1, 없으면 0 -->
							<input type="hidden" id="theater-check" value="0" name="theaterCheck"><br>
							<input type="submit" class="thadd-btn" value="등록" id="uploadBtn">
					</form>
 				</div>	
			</div>	
		</div>
	</div>
</div>
</section>
</body>
</html>