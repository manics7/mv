<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화관 후기 수정</title>
<link rel="stylesheet" href="resource/css/review.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="resource/css/main.css" />
<noscript>
	<link rel="stylesheet" href="resource/css/noscript.css" />
</noscript>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
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
	<jsp:include page="main_header.jsp"></jsp:include>
</header>
	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">

			<!-- Post -->
			<section class="post">
				<!-- <header class="major">
					<h1>Elements<br />
						Reference</h1>
				</header> -->
				<!-- WriteForm-->

				<!-- Form -->
				<h2>Review</h2>

					<form action="./boardRvUpdate" class="write-form" method="post" enctype="multipart/form-data">
						<!-- 로그인 유저, 제목, 내용, 파일 처리 -->
						<!-- name의 value는 DTO의 변수명과 일치하게 -->
						<input type="hidden" name="mid" value="${userInfo.m_id}">
						<input type="hidden" name="rnum" value="${bDto.rnum}">
						<div class="row gtr-uniform">
							<!-- <div class="col-6 col-12-xsmall">
								<input type="email" name="demo-email" id="demo-email" value="" placeholder="Email" />
							</div> -->
							<!-- Break -->
							<div class="col-2 col-12-xsmall">
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
							</div>
							<div class="col-10 col-12-xsmall">
								<input type="text" class="write-input" name="rtitle" autofocus placeholder="제목" value="${bDto.rtitle}" required>
							</div>
							<!-- Break -->
							<div class="col-12">
								<textarea name="rcontent" id="demo-message" placeholder="내용을 입력하세요." rows="15">${bDto.rcontent}</textarea>
							</div>
							<div class="filebox">
								<c:if test="${empty bfDto}">
									<label style="width: 100%">첨부 파일 없음</label>
								</c:if>
								<c:if test="${!empty bfDto}">
									<!-- 이미지 파일 미리보기 -->
									<c:forEach var="f" items="${bfDto}">
										<img id="img_pre" src="${f.review_file_name}" width="100" height="100">
									</c:forEach>
									<input type="file" name="files" id="file" multiple>
									<input type="hidden" class="upload-name" value="파일변경" readonly>
									<input type="hidden" id="filecheck" value="0" name="fileCheck">	
								</c:if>
							</div>
							<!-- Break -->
							<div class="col-12">
								<ul class="actions">
									<li><input type="submit" value="수정하기" class="primary" /></li>
									<li><input type="button" value="목록으로" class="primary" onclick="javascript:history.back();"></li>
								</ul>
							</div>
						</div>
					</form>
				</section>
			</div>
		</div>
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