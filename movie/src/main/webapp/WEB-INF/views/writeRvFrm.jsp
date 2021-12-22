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
<link rel="stylesheet" href="">
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
	<div class="content">
		<form action="./reviewWrite" class="write-form" method="post" enctype="multipart/form-data">
			<h2 class="login-header">글쓰기</h2>
			<!-- 로그인 유저, 제목, 내용, 파일 처리 -->
			<!-- name의 value는 DTO의 변수명과 일치하게 -->
			<input type="hidden" name="mid" value="viu97">
			
			<select>
				<option value="">영화관 선택</option>
				<c:forEach var="thitem" items="${thList}">
					<option value="thname">${thitem.thname}</option>
				</c:forEach>
			</select>
			
			<input type="text" class="write-input" name="btitle" autofocus placeholder="제목" required>
				<textarea rows="15" name="bcontents" placeholder="내용입력" class="write-input ta"></textarea>
				<div class="filebox">
				<!-- 파일 입력 처리 영역 -->
					<label for="file">UPLOAD</label>
					<input type="file" name="files" id="file" multiple>
					<input type="text" class="upload-name" value="파일선택" readonly>
					<input type="hidden" id="filecheck" value="0" name="fileCheck">
				</div>
				<div class="btn-area">
					<input type="submit" class="btn-write" value="W">
					<input type="reset" class="btn-write" value="R">
					<input type="button" class="btn-write" value="B" onclick="location.href='./rlist?pageNum=${pageNum}'">
				</div>
		</form>
	</div>
	</section>
</body>
</html>