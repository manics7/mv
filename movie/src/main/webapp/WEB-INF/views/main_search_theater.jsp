<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
	top: 100px;
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

.local_list {
	display: flex;
	margin-top: 32px;
	align-items: center;
	justify-content: center;
	font-size: 24px;
	color: #f16a1a;
}

.local_result{
margin-top: 35px;
}


.local_list>li {
	margin-right: 24px;
	list-style: none;
}

.local_list>li>a {
	font-weight: 700;
	text-decoration: none;
	color: #f16a1a;
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
</style>
</head>

<body>
	<div class="search_rayer">
		<div class="inner">
			<div class="search_box">
				<button>검색</button>
				<input type="text" placeholder="영화관찾기" onkeyup="filter(this);">
			</div>
			<div>
				<ul class="local_list">
					<li><a href="#"><span>전체</span></a></li>
					<li><a href="#"><span>서울</span></a></li>
					<li><a href="#"><span>경기</span></a></li>
					<li><a href="#"><span>인천</span></a></li>
					<li><a href="#"><span>충남</span></a></li>
					<li><a href="#"><span>대구</span></a></li>
					<li><a href="#"><span>경북</span></a></li>
					<li><a href="#"><span>경남</span></a></li>
					<li><a href="#"><span>전북</span></a></li>
					<li><a href="#"><span>전주</span></a></li>
				</ul>
				<div class="local_result">
					<ul>
						<li><a href="./theaterinsert?th_code=3">광주극장</a></li>
						<li><a href="./theaterinsert?th_code=3">광주독립영화관</a></li>
						<li><a href="./theaterinsert?th_code=3">더솔아트시네마</a></li>
						<li><a href="./theaterinsert?th_code=3">동성아트홀</a></li>
						<li><a href="./theaterinsert?th_code=3">씨네아트 리홈</a></li>
						<li><a href="./theaterinsert?th_code=3">아트하우스모모</a></li>
						<li><a href="./theaterinsert?th_code=3">인도중앙시네마</a></li>
						<li><a href="./theaterinsert?th_code=3">에무시네마</a></li>
						<li><a href="./theaterinsert?th_code=3">오오극장</a></li>
						<li><a href="./theaterinsert?th_code=3">인디플러스 포항</a></li>
						<li><a href="./theaterinsert?th_code=3">인디플러스 천안</a></li>
						<li><a href="./theaterinsert?th_code=3">인천 미림극장</a></li>
						<li><a href="./theaterinsert?th_code=3">광주디지털독립영화관</a></li>
						<li><a href="./theaterinsert?th_code=3">판타스틱큐</a></li>
						<li><a href="./theaterinsert?th_code=3">필름포럼</a></li>
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
        item.each(function () {
            if ($(this).text().toUpperCase().indexOf(value) > -1) {
                $(this).css("display", "flex");
            } else {
                $(this).css("display", "none");
            }
        })
    }


</script>

</html>