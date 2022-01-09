<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>adminpage_sidebar</title>
<link rel="stylesheet" href="resource/css/mypage_sidebar.css">
</head>
<body>

	<div id="side">
		<h2 style="height: 104px">
			<a href="adminPage">Admin Page</a>
		</h2>
		<ul id="bupage_list">
			<li><a id="bupage_menu" href="./mmanage?pageNum=1">회원관리</a></li>
			<li><a id="bupage_menu" href="adminMovieList">영화관리</a></li>
			<li><a id="bupage_menu" href="pmvReviewFrm">영화관관리</a></li>
			<li><a id="bupage_menu" href="./quesboard?pageNum=1">문의관리</a></li>
			<li><a id="bupage_menu" href="mvrreportFrm">신고관리</a></li>
		</ul>
	</div>

</body>
</html>