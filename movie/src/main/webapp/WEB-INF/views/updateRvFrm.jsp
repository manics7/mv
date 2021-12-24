<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="resources/css/style.css">
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
<div class="content">
<form action="./boardRvUpdate" class="write-form"
method="post" enctype="multipart/form-data">
	<h2 class="login-header">글수정</h2>
	<input type="hidden" name="mid" value="viu97">
	<input type="hidden" name="rnum" value="${bDto.rnum}">
	<select name="thcode">
		<option value="${bDto.thcode}" selected>${bDto.thname}</option>
		<option>-----------</option>
		<c:forEach var="thitem" items="${thList}">
			<option value="${thitem.thcode}">${thitem.thname}</option>
		</c:forEach>
	</select>
	<input type="text" class="write-input"
		name="rtitle" autofocus placeholder="제목"
		value="${bDto.rtitle}" required>
	<textarea rows="15" name="rcontent"
		placeholder="내용을 적어주세요..."
		class="write-input ta">${bDto.rcontent}</textarea>
	<div class="filebox">
		<div id="bfile" class="befor-file" style="margin-bottom: 10px;">
			<c:if test="${empty bfList}">
				<label style="width: 100%">첨부 파일 없음</label>
			</c:if>
			<c:if test="${!empty bfList}">
				<c:forEach var="file" items="${bfList}">
				<label style="width: 100%;" onclick="del('${file.bf_sysname}')">
					${file.bf_oriname}
				</label>
				</c:forEach>
			</c:if>
		</div>
		<label for="file">추가파일</label>
		<input type="file" name="files" id="file" multiple>
		<input class="upload-name" value="파일선택"
			readonly>
		<input type="hidden" id="filecheck" value="0"
			name="fileCheck">
	</div>
	<div class="btn-area">
		<input class="btn-write" type="submit" value="U">
		<input class="btn-write" type="reset" value="R">
		<input class="btn-write" type="button" value="B"
			onclick="javascript:history.back();">
	</div>
</form>
</div>
</body>
</html>