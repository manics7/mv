<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test page</title>
</head>
<body>

				<form action="./insertMovie" class="write-form" method="post" enctype="multipart/form-data">
					<h2 class="login-header">글쓰기</h2>
					<!-- 로그인한 id(숨김), 제목, 내용, 파일 -->
					<input type="text" name="mvname" autofocus="autofocus">
					<input type="number" name="mvtime" class="write-input" placeholder="시간" >
					<div class="filebox">
						<!-- 파일 입력 처리 영역 -->
						<label for="file">업로드</label>
						<input type="file" name="files" id="file" multiple="multiple"> <!-- 속성에 multiple="multiple" 하면 파일 여러개 등록 가능 -->
						<input type="text" class="upload-name" value="파일 선택" readonly="readonly">
						<!-- 업로드할 파일이 있으면 1, 없으면 0 -->
						<input type="hidden" id="filecheck" value="0" name="fileCheck">
					</div>
					<div class="btn-area">
						<input type="submit" class="btn-write" value="W">
						<input type="reset" class="btn-write" value="R">
						<input type="button" class="btn-write" value="B" onclick="location.href='./list?pageNum=${pageNum}'">
					</div>
				</form>

</body>

	<script src="resource/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		
	$('#file').on("change", function() {
		// 파일 입력창에서 선택한 파일 목록 가져오기
		var files = $("#file")[0].files;
		console.log(files);
		
		var fileName = "";
		
		if(files.length > 1) {
			fileName = files[0].name + " 외 " + (files.length - 1) + "개";
		}
		else if(files.length == 1) {
			fileName = files[0].name
		}
		
		$(".upload-name").val(fileName);
		
		if(fileName == "") { // 파일 취소 시
			$("#filecheck").val(0);
			$(".upload-name").val("파일 선택");
		}
		else { // 파일 선택 시 
			$("#filecheck").val(1);
		}
		
	});
	
	</script>	

</html>