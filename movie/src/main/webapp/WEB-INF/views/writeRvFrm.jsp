<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화관 후기 작성</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="resource/css/review.css">
<script type="text/javascript">
$(function(){
	//메시지 출력 부분
	var msg = "${msg}";
	if(msg != ""){
		alert(msg);
	}
});
</script>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
	<section>
	<div class="rv_content">
		<form action="./reviewWrite" class="write-form" method="post" enctype="multipart/form-data">
			<!-- 로그인 유저, 제목, 내용, 파일 처리 -->
			<!-- name의 value는 DTO의 변수명과 일치하게 -->
			<input type="hidden" name="mid" value="${userInfo.m_id}">
            <input type="text" class="write-input" name="rtitle" autofocus placeholder="제목을 입력하세요." required>

			<select name="th_code" class="th_select">
				<option value="-10">영화관 선택</option>
				<c:forEach var="thitem" items="${thList}">
					<option value="${thitem.th_code}">${thitem.th_name}</option>
				</c:forEach>
			</select>
		
				<textarea rows="15" name="rcontent" placeholder="내용을 입력하세요." class="write-textarea"></textarea>
				<div class="filebox">
				<!-- 파일 입력 처리 영역 -->
					<label for="file">UPLOAD</label>
					<input type="file" name="files" id="file" multiple>
					<input type="text" class="upload-name" value="파일선택" readonly>
					<input type="hidden" id="filecheck" value="0" name="fileCheck">
				</div>
				<div class="btn-area">
					<input type="submit" class="btn-write" value="등록하기">
					<input type="reset" class="btn-write" value="초기화하기">
					<input type="button" class="btn-write" value="목록으로" onclick="location.href='./rlist?pageNum=${pageNum}'">
				</div>
		</form>
	</div>
	</section>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
<script type="text/javascript">
//업로드할 파일을 선택하면 'upload-name' 요소에
//파일 이름을 출력하고, 'fileCheck' 요소의 value를
//1로 변경
$("#file").on("change", function(){
	//파일 입력창에서 선택한 파일 목록 가져오기
	var files = $("#file")[0].files;
	console.log(files);
	
	var fileName = "";
	
	if(files.length > 1){//하나 이상의 파일 선택 시
		fileName = files[0].name + " 외 " 
			+ (files.length - 1) + "개";
	}
	else if(files.length == 1){
		fileName = files[0].name; 
	}
	
	$(".upload-name").val(fileName);
	
	//fileCheck 부분 변경
	if(fileName == ""){//파일 취소 시.
		$("#filecheck").val(0);
		$(".upload-name").val("파일선택");
	}
	else {//파일 선택 시.
		$("#filecheck").val(1);
	}
});
</script>
</html>