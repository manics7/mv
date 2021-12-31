<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화관 등록</title>
<link rel="stylesheet" href="resource/css/theaterList.css">
<script src="resource/js/jquery-3.6.0.min.js"></script>
<script src="resource/js/jquery.serializeObject.js"></script>
<script type="text/javascript">

</script>
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
			<div id="schadd_content">
				<div id="schadd_title">
					<h2>상영시간표 등록</h2>
				</div>
				<div id="schadd_text">
					<form method="get" action="./scheduleInsert" class="schadd_form">
						<!-- 영화관 이름 -->
						<input type="hidden" name="thName" value="${theaterList.thName}">
						<div>${theaterList.thName}</div>
						<!-- 상영할 영화 -->
						<select id=""></select>
						<!-- 상영할 상영관 -->
						<select></select>
						<!-- 상영 날짜 -->
						<input type="date" title="상영 날짜">
						<!-- 영화 상영 시작 시간 -->
						<span>처음 상영 시작 시간</span>
						<input type="text" title="상영 시작 시간">
						<!-- 영화 상영 종료 시간 -->
						<span>
						</span>
						<!-- 휴식(대기) 시간 -->
						
												
						
					</form>
 				</div>	
			</div>	
		</div>
	</div>
</div>
</section>
</body>
</html>