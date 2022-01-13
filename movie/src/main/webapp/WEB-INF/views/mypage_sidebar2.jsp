<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>adminpage_sidebar2</title>
<link rel="stylesheet" href="resource/css/mypage_sidebar.css">
</head>
<body>

	<div id="side">
		<h2 style="height: 104px">
			<a href="adminPage">My Page</a>
		</h2>
		<ul id="bupage_list">
			<li><a id="bupage_menu" href="./mmanage?pageNum=1">예매/구매내역</a></li>

			<li><a id="bupage_menu" href="adminMovieList">내가 본 영화</a></li>
			<li><a id="bupage_menu" href="pmvReviewFrm">내가 쓴 감상평</a></li>
			<li><a id="bupage_menu" href="./questionFrm">1:1문의</a></li>
			<li><a id="bupage_menu" href="mvrreportFrm">신고관리</a></li>
		</ul>
	</div>

</body>
</html>