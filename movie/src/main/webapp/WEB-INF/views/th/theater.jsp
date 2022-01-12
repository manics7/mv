<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 영화관 관리</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="resource/css/theater.css">
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
<section>
<div class="detail">
	<div class="inner">
		<div id="page_wrap">
			<jsp:include page="business_sidebar.jsp"></jsp:include>
			<div id="th_content">
				<c:choose>
					<c:when test="${empty theaterList}">
						<div id="th_title">
							<h2>내 영화관</h2>
						</div>
						<div id="th_cont">
							<p>등록된 영화관이 없습니다.</p>
						</div>
						<div id="theateradd-btn">	
							<button id="th-btn" onclick="location.href='./theaterAdd'">등록</button>
						</div>
					</c:when>
					<c:when test="${!empty theaterList}">
						<c:forEach var="theaterList" items="${theaterList}">
						<div id="th_title">
							<h2>${theaterList.th_name}</h2>
						</div>
						<div id="th_cont">
							<div id="thLogo">	
							</div>
							<span>영화관 소개</span><br>
							<div id="theater_info">${theaterList.th_introduce}</div>
							<span>주소</span><br>
							<div id="theater_location">${theaterList.th_location}</div>
							<span>주차안내</span><br>
							<div id="theater_parking">${theaterList.th_parking}</div>
							<div id="theater_images">
								<span>사진</span><br>
								<!-- <div id="theater_image">${theaterList.th_image}</div>
								<div id="theater_image">${theaterList.th_image}</div>
								<div id="theater_image">${theaterList.th_image}</div>
								 -->
							</div>
						</div>
						<button id="th-btn" onclick="location.href='./thUpdate'">수정</button>
						<button id="th-btn" onclick="delCheck(${theaterList.th_code})">삭제</button>
						</c:forEach>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
</div>
</section>
</body>
<script type="text/javascript">
function delCheck(th_code){
	var conf = confirm("삭제하시겠습니까?");
	
	if(conf == true){
		location.href='./theaterDelete?th_code=' + th_code;
	}
}
</script>
</html>