<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
li {
	list-style: none;
}

a {
	text-decoration: none;
}

.admin_nav_wrap {
	width: 100%;
	background-color: #1d1d1d;
}

.admin_nav {
	margin: 0 auto;
	display: flex;
	width: 1024px;
	justify-content: space-around;
	background-color: #1d1d1d;
}

.admin_nav_item {
	
}

.admin_nav_item>a {
	color: white;
	margin-top: 40px;
}

.logo {
	border: 2px solid white;
	display: flex;
}

.logo1 {
	font-weight: 600;
}

.logo2 {
	border-left: 2px solid #ffffff;
	font-weight: 100;
}

.logo1, .logo2 {
	color: white;
	padding: 1.2px;
}

/* .logo2 {
            color: white;
        } */
.underline {
	line-height: 1.2;
	font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica,
		"Apple Color Emoji", Arial, sans-serif, "Segoe UI Emoji",
		"Segoe UI Symbol";
	font-size: 1em;
	font-weight: 600;
	background-image: linear-gradient(transparent calc(100% - 3px), #000 3px);
	background-repeat: no-repeat;
	background-size: 0% 100%;
	transition: background-size 0.8s;
	color: #000;
	cursor: pointer;
}

@media ( min-width : 1000px) {
	.underline {
		font-size: 1em;
	}
}

.underline.yellow {
	background-image: linear-gradient(transparent 60%, #f16a1a 40%);
}

.underline:hover {
	background-size: 100% 100%;
}
</style>
<title>Document</title>
</head>

<body>
	<div class="admin_nav_wrap">
		<ul class="admin_nav">
			<li class="admin_nav_item logo">
				<div style="cursor: pointer;" onclick="location.href='/'" class="logo1">&nbsp;Admin&nbsp;</div>
				<div class="logo2">&nbsp;Page&nbsp;</div>
			</li>
			<li class="admin_nav_item cont1"><a class="yellow underline"
				href="./mmanage?pageNum=1">회원관리</a></li>
			<li class="admin_nav_item cont2"><a class="yellow underline"
				href="./adminMovieList">영화관리</a></li>
			<li class="admin_nav_item cont3"><a class="yellow underline"
				href="./quesboard?pageNum=1">문의관리</a></li>
			<li class="admin_nav_item cont5"><a class="yellow underline"
				href="questionFrm">신고관리</a></li>
				<li class="admin_nav_item cont5"><a class="yellow underline"
				href="./logout">로그아웃</a></li>

		</ul>

	</div>


</body>

</html>