<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화관 등록</title>
<link rel="stylesheet" href="resource/css/theaterList.css">
<script src="resource/js/jquery-3.6.0.min.js"></script>
<script src="resource/js/jquery.serializeObject.js"></script>
<script type="text/javascript">
//$(document).ready(function() {
	
	
	
//}
</script>
</head>
<body>
<section>
<div class="detail">
	<div class="inner">
		<div id="page_wrap">
			<div id="side">
			<%@ include file="../business_sidebar.jsp"  %>
<!-- 				<h2><a href="#">Business Page</a></h2> -->
<!-- 				<ul id="bupage_list"> -->
<!-- 					<li><a id="bupage_menu" href="#">영화관 관리</a></li> -->
<!-- 					<li><a id="bupage_menu" href="#">영화 관리</a></li> -->
<!-- 					<li><a id="bupage_menu" href="#">상영관 관리</a></li> -->
<!-- 					<li><a id="bupage_menu" href="#">상영 일정 관리</a></li> -->
<!-- 					<li><a id="bupage_menu" href="#">이벤트 관리</a></li> -->
<!-- 				</ul> -->
			</div>
			<div id="schadd_content">
				<div id="schadd_title">
					<h2>상영시간표 등록</h2>
				</div>
				<div id="schadd_text">
					<form method="post" action="./scheduleInsert" class="schadd_form">
						<!-- 영화관 이름 -->			
						<c:forEach var="theaterList" items="${theaterList}">
							<input type="hidden" name="thcode" value="${theaterList.th_code}">
							<div>${theaterList.th_name}</div>
						</c:forEach>
						<!-- 상영할 영화 -->
						<select name="mvcode" class="mv_select">
							<option value="-10">영화 선택</option>
							<c:forEach var="movieList" items="${movieList}">
								<option value="${movieList.movie_Cd}">
									${movieList.movie_Nm}
								</option>
							</c:forEach>
						</select>
						<select name="mvcode" class="mv_select">
							<option value="-10">영화 선택</option>
							<c:forEach var="movieList" items="${movieList}">
								<option value="${movieList.movie_Cd}">
									${movieList.movie_Nm}
								</option>
							</c:forEach>
						</select>
						<select name="mvcode" class="mv_select">
							<option value="-10">영화 선택</option>
							<c:forEach var="movieList" items="${movieList}">
								<option value="${movieList.movie_Cd}">
									${movieList.movie_Nm}
								</option>
							</c:forEach>
						</select>
						<select name="mvcode" class="mv_select">
							<option value="-10">영화 선택</option>
							<c:forEach var="movieList" items="${movieList}">
								<option value="${movieList.movie_Cd}">
									${movieList.movie_Nm}
								</option>
							</c:forEach>
						</select>
						<select name="mvcode" class="mv_select">
							<option value="-10">영화 선택</option>
							<c:forEach var="movieList" items="${movieList}">
								<option value="${movieList.movie_Cd}">
									${movieList.movie_Nm}
								</option>
							</c:forEach>
						</select>
						<!-- 상영할 상영관 -->
						<select name="room" class="room_select">
							<option value="-10">상영관 선택</option>
							<c:forEach var="roomList" items="${roomList}">
								<option value="${roomList.room_seq}">
									${roomList.room_no}[${roomList.room_name}]
								</option>
							</c:forEach>
						</select>
						<br>
						<!-- 상영 날짜(date) -->
						<p>상영 날짜</p>
						<input type="date" title="상영 날짜" name="mvdate" id="mvdate">
						<!-- 휴식(대기) 시간(number) -->
						<p>상영 전 대기 시간</p>
						<input type="text" name="wait">
						<!-- 영화 상영 시작 시간 -->
						<p>상영 시작 시간</p>
						<input type="datetime-local" title="상영 시작 시간" name="roomStartTime" id="roomStartTime">
						<!-- 영화 상영 종료 시간 -->
						<p>상영 종료 시간</p>
						<input type="datetime-local" title="상영 종료 시간" name="roomEndTime" id="roomEndTime">
						<input type="submit">
					</form>
 				</div>	
			</div>	
		</div>
	</div>
</div>
</section>
</body>
</html>