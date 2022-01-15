<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
<style type="text/css">
li {
	list-style: none;
}

a {
	text-decoration: none;
	color: black;
}

/*  */
section {
	width: 100%;
}

div.inner {
	width: 1024px;
	margin: 0 auto;
	position: relative;
	box-sizing: border-box;
	background-color: #fff;
	padding: 40px 0 80px;
}

div#page_wrap {
	padding-top: 40px;
	display: flex;
	align-items: flex-start;
	justify-content: space-between;
}

/* 사업자 페이지 메뉴 크기 */
div#side {
	width: 184px;
	height: 450px;
}

/* business page */
#side>h2 {
	padding: 58px 0 16px 24px;
	height: 42px;
	font-size: 18px;
	color: #fff;
	background:
		url(https://www.indieartcinema.com/static/media/img-mypage.d9557dd7.svg)
		no-repeat 0 0;
	font-weight: 700;
	margin-bottom: 0px;
	padding-bottom: 0px;
}

#side>h2>a {
	color: #fff;
}

/* 메뉴 리스트 */
ul#bupage_list {
	border: 1px solid #e1e1e1;
	padding-top: 22px;
	margin-top: 0px;
	padding-left: 0px;
}

ul#bupage_list>li {
	height: 67px;
	font-size: 15px;
	color: #333;
	padding-left: 30px;
}

ul>li>a#bupage_menu {
	padding: 12px 24px 14px;
	display: block;
}

ul>li>a#bupage_menu:hover {
	color: #f77500;
	font-weight: 700;
}

/* 메뉴에 마우스 올렸을때 밑줄이 나타나는 부분 */
ul>li>a#bupage_menu {
	text-decoration: none;
	display: inline-block;
	padding: 15px 0;
	position: relative;
}

ul>li>a#bupage_menu:after {
	background: none repeat scroll 0 0 transparent;
	bottom: 0;
	content: "";
	display: block;
	height: 2px;
	left: 50%;
	position: absolute;
	background: #f77500;
	transition: width 0.3s ease 0s, left 0.3s ease 0s;
	width: 0;
}

ul>li>a#bupage_menu:hover:after {
	width: 100%;
	left: 0;
}

.sidebar_title {
	height: 104px;
}
</style>
</head>
<body>
	<div id="side">
		<h2 style="height: 104px;" class="sidebar_title">
			<a href="businessPage">Business Page</a>
		</h2>
		<ul id="bupage_list">
			<li><a id="bupage_menu" href="theater">영화관 관리</a></li>
			<li><a id="bupage_menu" href="#">영화 관리</a></li>
			<li><a id="bupage_menu" href="#">상영관 관리</a></li>
			<li><a id="bupage_menu" href="schedule">상영 일정 관리</a></li>
			<li><a id="bupage_menu" href="#">이벤트 관리</a></li>
		</ul>
	</div>
</body>
</html>