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
<link rel="stylesheet" href="resource/css/theaterList.css">
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
<nav>
<jsp:include page="../business_header.jsp"></jsp:include>
</nav>
<section>
<div class="detail">
	<div class="inner">
		<div id="page_wrap">
			<div id="side">
				<h2><a href="./businessPage">Business Page</a></h2>
				<ul id="bupage_list">
					<li><a id="bupage_menu" href="./theater">영화관 관리</a></li>
					<li><a id="bupage_menu" href="./movieInsert">영화 관리</a></li>
					<li><a id="bupage_menu" href="./roomList">상영관 관리</a></li>
					<li><a id="bupage_menu" href="./schedule">상영 일정 관리</a></li>
					<li><a id="bupage_menu" href="./eventList">이벤트 관리</a></li>
				</ul>
			</div>
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
						<div id="thLogo">
								<!-- 첨부파일이 있을 경우 -->
     							<c:if test="${!empty theaterList.th_logo}">
         						<!-- 이미지 파일 미리보기 -->
            						<img src="${theaterList.th_logo}">
      							</c:if>
						</div>
						<div id="th_title">
							<h2>${theaterList.th_name}</h2>
						</div>
						<div id="theater_cont">
							<div id="th_intro_title">
								<p>영화관 소개</p>
							</div>
							<div id="th_intro_cont">
								<p>${theaterList.th_introduce}</p>
							</div>
							<div id="th_tel_title">
								<p>연락처</p>
							</div>
							<div id="th_tel_cont">
								<p>${theaterList.th_tel}</p>
							</div><br>
							<div id="th_location_title">
								<p>주소</p>
							</div>
							<div id="th_location_cont">
								<p>${theaterList.th_location}</p>
							</div><br>
							<div id="th_parking_title">
								<p>주차안내</p>
							</div>
							<div id="th_parking_cont">
								<p>${theaterList.th_parking}</p>
							</div><br>
							<div id="th_image_title">
								<p>영화관 사진</p>
							</div>
							<div id="theater_images">
								<!-- 첨부파일이 있을 경우 -->
     							<c:if test="${!empty theaterList.th_image}">
         						<!-- 이미지 파일 미리보기 -->
            						<img src="${theaterList.th_image}" width="200">
      							</c:if>
							</div>
						</div>
						<div id="btn_wrap">
							<button id="th-btn" onclick="location.href='./thUpdate?th_code=${theaterList.th_code}'">수정</button>
						</div>
						</c:forEach>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
</div>
</section>
</body>
</html>