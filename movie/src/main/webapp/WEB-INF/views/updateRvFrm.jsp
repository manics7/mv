<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<link rel="stylesheet" href="resource/css/review.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	//컨트롤러에서 전달하는 메시지 출력.
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
		<form action="./boardRvUpdate" class="write-form"
			method="post" enctype="multipart/form-data">

			<input type="text" class="write-input"
			name="rtitle" autofocus placeholder="제목"
			value="${bDto.rtitle}" required>

			<select name="th_code" class="th_select">
				<c:forEach var="thitem" items="${thList}">
					<c:if test="${thitem.th_name eq bDto.th_name}">
						<option value="${thitem.th_code}" selected>${thitem.th_name}</option>
					</c:if>
					<c:if test="${thitem.th_name ne bDto.th_name}">
						<option value="${thitem.th_code}">${thitem.th_name}</option>
					</c:if>
				</c:forEach>
			</select>
			<input type="hidden" name="mid" value="${userInfo.m_id}">
			<input type="hidden" name="rnum" value="${bDto.rnum}">
			
			<textarea rows="15" name="rcontent"
				placeholder="내용을 적어주세요..."
				class="write-textarea">${bDto.rcontent}</textarea>
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
				<input class="btn-write" type="submit" value="수정하기">
				<input class="btn-write" type="reset" value="초기화하기">
				<input class="btn-write" type="button" value="목록으로"
					onclick="javascript:history.back();">
			</div>
		</form>
	</div>
	</section>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>