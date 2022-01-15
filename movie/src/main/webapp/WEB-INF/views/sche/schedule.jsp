<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 상영일정 관리</title>
<link rel="stylesheet" href="resource/css/theaterList.css">
<link rel="stylesheet" href="resource/css/schedule.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="js/scripts.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
	<section>
		<div class="detail">
			<div class="inner">
				<div id="page_wrap">
					<div id="side">
						<h2>
							<a href="#">Business Page</a>
						</h2>
						<ul id="bupage_list">
							<li><a id="bupage_menu" href="./theater">영화관 관리</a></li>
							<li><a id="bupage_menu" href="#">영화 관리</a></li>
							<li><a id="bupage_menu" href="#">상영관 관리</a></li>
							<li><a id="bupage_menu" href="./schedule">상영 일정 관리</a></li>
							<li><a id="bupage_menu" href="#">이벤트 관리</a></li>
						</ul>
					</div>
					<div id="content">
						<div id="sch_title">
							<h2>상영 시간표</h2>
						</div>
						<div id="sch_content">
							<c:choose>
								<c:when test="${empty scheduleList}">
									<div id="sch_cont">
										<p>등록된 상영 일정이 없습니다.</p>
									</div>
									<div id="scheduleAdd-btn">
										<button id="sch-btn" onclick="location.href='./scheduleAdd'">등록</button>
									</div>
'								</c:when>
								<c:when test="${!empty scheduleList}">
									<table class="sch_table">
										<tr class="title-row">
											<th class="room_no p-10">상영관 번호</th>
											<th class="room_name p-20">상영관명</th>
											<th class="mv_name p-10">영화명</th>
											<th class="sch_date p-20">상영 날짜</th>
											<th class="sch_time p-20">상영 시간</th>
											<th class="sch_id p-10">삭제 처리</th>
										</tr>
										<c:forEach var="scheduleList" items="scheduleList">
										<tr class="data-row">
											<td class="room_no p-10"></td>
											<td class="room_name p-20"></td>
											<td class="mv_name p-10"></td>
											<td class="sch_date p-20"></td>
											<td class="sch_time p-20">
											</td>
											<td class="sch_id p-10">
												<button class="btn-del">삭제</button>
											</td>
										</tr>
										</c:forEach>
									</table>
								</c:when>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>

</html>















