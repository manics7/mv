<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 영화관 관리</title>
<link rel="stylesheet" href="resource/css/theaterList.css">
<link rel="stylesheet" href="resource/css/theater.css">
</head>
<body>
<section>
<div class="detail">
	<div class="inner">
		<div id="page_wrap">
			<div id="side">
				<h2><a href="./businessPage">Business Page</a></h2>
				<ul id="bupage_list">
					<li><a id="bupage_menu" href="./theater">영화관 관리</a></li>
					<li><a id="bupage_menu" href="#">영화 관리</a></li>
					<li><a id="bupage_menu" href="#">상영관 관리</a></li>
					<li><a id="bupage_menu" href="./schedule">상영 일정 관리</a></li>
					<li><a id="bupage_menu" href="#">이벤트 관리</a></li>
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