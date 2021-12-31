<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 상영일정 관리</title>
<link rel="stylesheet" href="resource/css/theaterList.css">
</head>
<body>
<section>
<div class="detail">
	<div class="inner">
		<div id="page_wrap">
			<div id="side">
				<h2><a href="#">Business Page</a></h2>
				<ul id="bupage_list">
					<li><a id="bupage_menu" href="#">영화관 관리</a></li>
					<li><a id="bupage_menu" href="#">영화 관리</a></li>
					<li><a id="bupage_menu" href="#">상영관 관리</a></li>
					<li><a id="bupage_menu" href="#">상영 일정 관리</a></li>
					<li><a id="bupage_menu" href="#">이벤트 관리</a></li>
				</ul>
			</div>
			<div id="content">
				<c:choose>
					<c:when test="${empty theaterList}">
						<div id="th_title">
							<h2>상영 시간표</h2>
						</div>
						<div id="th_cont">
							<p>등록된 상영 일정이 없습니다.</p>
						</div>
						<button id="sch-btn" onclick="location.href='./scheduleAdd'">등록</button>
					</c:when>
					<c:when test="${!empty theaterList}">
						
						<button id="th-btn" onclick="location.href='./thUpdate'">수정</button>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
</div>
</section>
</body>
</html>