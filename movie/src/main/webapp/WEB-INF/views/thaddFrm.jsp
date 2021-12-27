<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화관 등록</title>
<link rel="stylesheet" href="resource/css/theaterList.css">
<script src="resource/js/jquery-3.6.0.min.js"></script>
<script src="resource/js/jquery.serializeObject.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		//업로드할 파일을 선택하면 'upload-name' 요소에
		//파일 이름을 출력하고, 'fileCheck' 요소의 value를
		//1로 변경
		$("#file").on("change", function() {
			//파일 입력창에서 선택한 파일 목록 가져오기
			var files = $("#file")[0].files;
			console.log(files);

			var fileName = "";

			if (files.length > 1) {//하나 이상의 파일 선택 시
				fileName = files[0].name + " 외 " + (files.length - 1) + "개";
			} else if (files.length == 1) {
				fileName = files[0].name;
			}

			$(".upload-name").val(fileName);

			//fileCheck 부분 변경
			if (fileName == "") {//파일 취소 시.
				$("#filecheck").val(0);
				$(".upload-name").val("파일선택");
			} else {//파일 선택 시.
				$("#filecheck").val(1);
			}
		});
		
		$("#file2").on("change", function() {
			//파일 입력창에서 선택한 파일 목록 가져오기
			var files = $("#file2")[0].files;
			console.log(files);

			var fileName = "";

			if (files.length > 1) {//하나 이상의 파일 선택 시
				fileName = files[0].name + " 외 " + (files.length - 1) + "개";
			} else if (files.length == 1) {
				fileName = files[0].name;
			}

			$(".upload-name2").val(fileName);

			//fileCheck 부분 변경
			if (fileName == "") {//파일 취소 시.
				$("#filecheck2").val(0);
				$(".upload-name2").val("파일선택");
			} else {//파일 선택 시.
				$("#filecheck2").val(1);
			}
		});

		$("#uploadBtn").on("click", function() {
			console.log("upload");
			//var form = $('#uploadForm')[0]
			var formData = new FormData();

			var files = $("#file")[0].files;

			for (i = 0; i < files.length; i++) {
				formData.append("uploadFile", files[i]);
			}

			$.ajax({
				type : "POST",
				//url : "/fileUpload",
				url : "/fileUpload2",
				enctype : 'multipart/form-data',
				processData : false,
				contentType : false,
				data : formData,
				success : function(res) {
					console.log(res);
				},
				err : function(err) {
					console.log("err:", err)
				}
			});
		});
		
		$("#uploadBtn2").on("click", function() {
			console.log("upload");
			//var form = $('#uploadForm')[0]
			var formData = new FormData();

			var files = $("#file2")[0].files;

			for (i = 0; i < files.length; i++) {
				formData.append("uploadFile2", files[i]);
			}

			$.ajax({
				type : "POST",
				//url : "/fileUpload",
				url : "/fileUpload2",
				enctype : 'multipart/form-data',
				processData : false,
				contentType : false,
				data : formData,
				success : function(res) {
					console.log(res);
				},
				err : function(err) {
					console.log("err:", err)
				}
			});
		});

	})
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
						<input type="hidden" name="bid" value="btest03">
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
						<label for="file">업로드</label>
						<input type="file" name="files" id="file" multiple>
						<input type="text" class="upload-name" value="파일선택" readonly>
						<!-- 업로드할 파일이 있으면 1, 없으면 0 -->
						<input type="hidden" id="filecheck" value="0" name="fileCheck"><br>
						<span>영화관 사진</span>
						<label for="file">업로드</label>
						<input type="file" name="files" id="file2" multiple>
						<input type="text" class="upload-name2" value="파일선택" readonly>
						<!-- 업로드할 파일이 있으면 1, 없으면 0 -->
						<input type="hidden" id="filecheck2" value="0" name="fileCheck2"><br>
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