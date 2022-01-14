<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
	integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm"
	crossorigin="anonymous">
	<script type="text/javascript" src="resource/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="resources/js/search_theater.js"></script>

<title>Document</title>

<style>
.search_rayer {
	width: 1197px;
	height: 318px;
	background-color: #202020;
	width: 100%;
	padding: 24px 0 32px;
	position: fixed;
	left: 0;
	top: 90px;
	z-index: 20;
}

.inner {
	width: 1024px;
	margin: 0 auto;
	padding: 0 15px;
}

.search_box {
	width: 100%;
	height: 56px;
	background-color: #333;
	border-radius: 4px;
	display: flex;
	justify-content: center;
	align-items: center;
	position: relative;
}

.search_box>input {
	color: #fff;
	background: transparent;
	border: none;
	text-align: center;
	width: 180px;
}

.search_box>input::placeholder {
	color: #fff;
	font-weight: 600;
}

.local_list {
	display: flex;
	margin-top: 32px;
	align-items: center;
	justify-content: center;
	font-size: 24px;
	color: #f16a1a;
}

.local_result {
	margin-top: 35px;
	<!-- -->
	
}

.local_list>li {
	margin-right: 24px;
	list-style: none;
}

.local_list>li>a {
	font-weight: 700;
	text-decoration: none;
	color: #f16a1a;
	opacity: 0.5;
}

.local_list>li>a:hover {
	font-weight: 700;
	text-decoration: none;
	color: #f16a1a;
	opacity: 1;
	border-bottom: 2px solid #f16a1a;
}

.theater_loc active {
	font-weight: 700;
	text-decoration: none;
	color: #f16a1a;
	opacity: 1;
	border-bottom: 2px solid #f16a1a;
}

.local_result>ul {
	display: flex;
	justify-content: left;
	flex-wrap: wrap;
}

.local_result>ul>li {
	min-width: 112px;
	margin: 0 16px 18px 0;
	list-style: none;
}

.local_result>ul>li>a {
	color: white;
	text-decoration: none;
	color: #fff;
	font-size: 14px;
}

.search_close {
	background: #f16a1a;
	color: #fff;
	width: 80px;
	height: 40px;
	border-radius: 5px;
	text-align: center;
	font-weight: 600;
	outline-style: none;
	border: none;
	position: absolute;
	right: 10px;
}
</style>
</head>

<body>
	<div class="search_rayer">
		<div class="inner">
			<div class="search_box">
				<i style="color: lightgray;" class="fas fa-search"></i> <input
					type="text" placeholder="영화관찾기" onkeyup="filter(this);">
				<button class="search_close">닫기</button>
			</div>
			<div>
				<ul class="local_list">
					<li><a class="theater_loc active" href="#"><span>전체</span></a></li>
					<li><a class="theater_loc" href="#"><span>서울</span></a></li>
					<li><a class="theater_loc" href="#"><span>경기</span></a></li>
					<li><a class="theater_loc" href="#"><span>인천</span></a></li>
					<li><a class="theater_loc" href="#"><span>충남</span></a></li>
					<li><a class="theater_loc" href="#"><span>대구</span></a></li>
					<li><a class="theater_loc" href="#"><span>경북</span></a></li>
					<li><a class="theater_loc" href="#"><span>경남</span></a></li>
					<li><a class="theater_loc" href="#"><span>전북</span></a></li>
					<li><a class="theater_loc" href="#"><span>전주</span></a></li>
				</ul>
				<div class="local_result">
					<ul>
					<!--
					
					<c:forEach var="thCList" items="${thCodeList}">
						<li><a href="./theaterDetailPage?th_code=${thCList.th_code}">${thCList.th_name}</a></li>
						</c:forEach>
					
					
					  -->
					
						
					</ul>
				</div>
				
				
			</div>
		</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	//영화, 극장검색
	function filter(id, target) {

		var value, item;

		value = $(id).val().toUpperCase();
		item = $(".local_result li");
		item.each(function() {
			if ($(this).text().toUpperCase().indexOf(value) > -1) {
				$(this).css("display", "flex");
			} else {
				$(this).css("display", "none");
			}
		})
	}
	window.addEventListener("DOMContentLoaded", function() {
						const headerElement = document
								.getElementsByClassName("search_rayer")[0];
						const items = headerElement
								.getElementsByClassName("theater_loc");
						const detailBoxs = document
								.getElementsByClassName("local_result");

						let lastClickdetailBox = detailBoxs[0];

						for (let i = 0; i < items.length; i++) {
							const id = i;
							items[id].onclick = function() {
								lastClickdetailBox.classList.remove("active");
								detailBoxs[id].classList.add("active");
								lastClickdetailBox = detailBoxs[id];
							}
							detailBoxs[id].getElementsByClassName("close-bt")[0].onclick = function() {
								detailBoxs[id].classList.remove("active");
								isClick = false
							}
						}
					});
</script>

</html>