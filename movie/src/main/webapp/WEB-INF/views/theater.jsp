<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 영화관 관리</title>
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
				<div id="th_title">
					<h2>내 영화관</h2>
				</div>
				<div id="th_cont">
					<button id="th-btn" onclick="location.href='./thaddFrm'">등록</button>
				</div>					
			</div>
		</div>
	</div>
</div>
</section>
</body>
</html>