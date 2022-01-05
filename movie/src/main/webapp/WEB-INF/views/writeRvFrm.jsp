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
	
	//로그인한 회원 정보 및 로그아웃 출력
	var lname = "${mb.m_name}";
	$("#mname").html(lname + "님");
	$(".suc").css("display", "block");
	$(".bef").css("display", "none");
});
</script>
</head>
<body>
	<section>
        <h2 id="rv_title">REVIEW</h2>
	<div class="rv_content">
		<form action="./reviewWrite" class="write-form" method="post" enctype="multipart/form-data">
			<!-- 로그인 유저, 제목, 내용, 파일 처리 -->
			<!-- name의 value는 DTO의 변수명과 일치하게 -->
			<input type="hidden" name="mid" value="viu97">
			
            <input type="text" class="write-input" name="rtitle" autofocus placeholder="제목을 입력하세요." required>

			<select name="thcode" class="th_select">
				<option value="-10">영화관 선택</option>
				<c:forEach var="thitem" items="${thList}">
					<option value="${thitem.thcode}">${thitem.thname}</option>
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
</body>
</html>