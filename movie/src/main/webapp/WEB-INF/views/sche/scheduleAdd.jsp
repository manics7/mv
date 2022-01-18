<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화관 등록</title>
<link rel="stylesheet" href="resource/css/theaterList.css">
<link rel="stylesheet" href="resource/css/schedule.css">
<script src="resource/js/jquery-3.6.0.min.js"></script>
<script src="resource/js/jquery.serializeObject.js"></script>
<script type="text/javascript">
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
			<jsp:include page="../business_sidebar.jsp"></jsp:include>
			<div id="schadd_content">
				<div id="schadd_title">
					<h2>상영시간표 등록</h2>
				</div>
				<form method="post" action="./scheduleInsert" class="schadd_form">
				<div id="schadd_text">
						<!-- 영화관 이름 -->			
						<c:forEach var="theaterList" items="${theaterList}">
							<input type="hidden" name="thcode" value="${theaterList.th_code}">
						</c:forEach>
						<!-- 상영할 영화 -->
						<div id ="movie_select_title">
							<p>회차별 상영 영화</p>
						</div>
						<div id="warning">
							<p>유의사항 : 상영관이 운영되지 않는 시간일 경우 선택한 영화는 등록되지 않습니다.</p>
						</div><br>
						<div id="mv_select_num1">
							<p>1회차</p>
						</div>
						<select name="mvcode" class="mv_select">
							<option value="-10">영화 선택</option>
							<c:forEach var="movieList" items="${movieList}">
								<option value="${movieList.movie_cd}">
									${movieList.movie_nm}
								</option>
							</c:forEach>
						</select>
						<div id="mv_select_num2">
							<p>2회차</p>
						</div>
						<select name="mvcode" class="mv_select">
							<option value="-10">영화 선택</option>
							<c:forEach var="movieList" items="${movieList}">
								<option value="${movieList.movie_cd}">
									${movieList.movie_nm}
								</option>
							</c:forEach>
						</select>
						<div id="mv_select_num3">
							<p>3회차</p>
						</div>
						<select name="mvcode" class="mv_select">
							<option value="-10">영화 선택</option>
							<c:forEach var="movieList" items="${movieList}">
								<option value="${movieList.movie_cd}">
									${movieList.movie_nm}
								</option>
							</c:forEach>
						</select><br>
						<div id="mv_select_num4">
							<p>4회차</p>
						</div>
						<select name="mvcode" class="mv_select">
							<option value="-10">영화 선택</option>
							<c:forEach var="movieList" items="${movieList}">
								<option value="${movieList.movie_cd}">
									${movieList.movie_nm}
								</option>
							</c:forEach>
						</select>
						<div id="mv_select_num5">
							<p>5회차</p>
						</div>
						<select name="mvcode" class="mv_select">
							<option value="-10">영화 선택</option>
							<c:forEach var="movieList" items="${movieList}">
								<option value="${movieList.movie_cd}">
									${movieList.movie_nm}
								</option>
							</c:forEach>
						</select>
						<div id="mv_select_num6">
							<p>6회차</p>
						</div>
						<select name="mvcode" class="mv_select">
							<option value="-10">영화 선택</option>
							<c:forEach var="movieList" items="${movieList}">
								<option value="${movieList.movie_cd}">
									${movieList.movie_nm}
								</option>
							</c:forEach>
						</select>
						<div id ="room_select_title">
							<p>상영할 상영관</p>
						</div>
						<!-- 상영할 상영관 -->
						<select name="room" class="room_select">
							<option value="-10">상영관 선택</option>
							<c:forEach var="roomList" items="${roomList}">
								<option value="${roomList.room_no}">
									${roomList.room_no}(${roomList.room_name})[${roomList.room_class}]
								</option>
							</c:forEach>
						</select>
						<br>
						<!-- 상영 날짜(date) -->
						<div id ="date_select_title">
							<p>상영 날짜</p>
						</div>
						<input type="date" title="상영 날짜" name="mvdate" id="mvdate"><br>
						<!-- 휴식(대기) 시간(number) -->
						<div id ="time_select_title">
							<p>다음 상영 전 대기시간</p>
						</div>
						<input type="text" name="wait" placeholder="(예) 20">
						<span>(분 단위로 숫자만 입력)</span><br>
						<!-- 영화 상영 시작 시간 -->
						<div id ="start_select_title">
							<p>상영관 시작 시간</p>
						</div>
						<input type="datetime-local" title="상영 시작 시간" name="roomStartTime" id="roomStartTime"><br>
						<!-- 영화 상영 종료 시간 -->
						<div id ="end_select_title">
							<p>상영관 종료 시간</p>
						</div>
						<input type="datetime-local" title="상영 종료 시간" name="roomEndTime" id="roomEndTime">
 					</div>
 					<div id="schadd_btn_wrap">
 						<input type="submit" id="schadd_btn" value="등록">
 					</div>
 				</form>
			</div>	
		</div>
	</div>
</div>
</section>
<footer>
<jsp:include page="../footer.jsp"></jsp:include>
</footer>
</body>
</html>