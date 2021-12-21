<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 보러 가자~</title>
<link rel="stylesheet" type="text/css" href="resource/css/home.css">
<link rel="stylesheet" type="text/css" href="resource/css/hf.css">
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

	})

	function sel() {
		var formData = new FormData();
		$.ajax({
			type : "GET",
			url : "/getFileURL",
			success : function(res) {
				console.log(res);
				$("#img").attr("src", res);
			},
			err : function(err) {
				console.log("err:", err)
			}
		});
	}

	function create() {
		var formData = new FormData();
		$.ajax({
			type : "GET",
			url : "/fileTest",
			success : function(res) {
				console.log(res);
			},
			err : function(err) {
				console.log("err:", err)
			}
		});
	}
	function del() {
		var formData = new FormData();
		$.ajax({
			type : "GET",
			url : "/fileDelTest",
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
<body>


	<header>
		<!-- --------------- header --------------- -->
		<jsp:include page="header.jsp"></jsp:include>
	</header>

	<h1>파일테스트</h1>

	<form method="POST" enctype="multipart/form-data" id="fileUploadForm">
		<div class="filebox">
			<!-- 파일 입력 처리 영역 -->
			<label for="file">업로드</label> <input type="file" name="files" id="file" multiple>
			<input type="text" class="upload-name" value="파일선택" readonly>
			<!-- 업로드할 파일이 있으면 1, 없으면 0 -->
			<input type="hidden" id="filecheck" value="0" name="fileCheck">
		</div>
		<div class="btn-area">
			<input type="button" class="btn-write" value="파일 업로드" id="uploadBtn"> 
			<input type="reset" class="btn-write" value="초기화">

		</div>
		<div class="btn-area">
			<input type="button" value="파일읽어오기" onclick="sel()"> 
			<input type="button"	value="폴더생성" onclick="create()"
			> <input type="button" value=삭제 onclick="del()">
		</div>
		<img id="img" alt="" src="">
	</form>

</body>
</html>