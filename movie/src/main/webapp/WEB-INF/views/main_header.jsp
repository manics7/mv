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
	padding-top: 35px;
	padding-bottom: 35px;
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
<link rel="stylesheet" type="text/css" href="resource/css/hf.css">
<script type="text/javascript" src="resource/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	getSearchTheaterData();
	function getSearchTheaterData(){
		
		$.ajax({
			type: "GET"
			,url: "/searchTheater"
			,success:function(data){
				var html = "";
				for(i = 0; i < data.length; i++){
				var th_code = data[i].th_code;
				var th_name = data[i].th_name;
				html += "<li><a href='./accessTheaterDetailPage?th_code="+th_code+"'>"+th_name+"</a></li>"
				}
				$(".local_result ul").html(html);
			}	
			,error : function(err){
				console.log("err",err)
			}
		})	//ajax End
	}//functiong End
	
	//<li><a href="./theaterDetailPage?th_code=${thCList.th_code}">${thCList.th_name}</a></li>
});//functiong End
</script>
<title>Document</title>
</head>

<body>
	<div class="admin_nav_wrap">
		<ul class="admin_nav">
			<li class="admin_nav_item logo">
				<div class="logo1">&nbsp;MVTI&nbsp;</div>
				<div class="logo2">&nbsp;MOVIE&nbsp;</div>
			</li>
			<li class="admin_nav_item cont1"><a class="yellow underline" style="color: white;"
				href="./mmanage?pageNum=1">영화</a></li>
			<li class="admin_nav_item cont2"><a class="yellow underline"style="color: white;"
				href="./adminMovieList">영화관</a></li>
			<li class="admin_nav_item cont3"><a class="yellow underline"style="color: white;"
				href="./quesboard?pageNum=1">영화관후기</a></li>
			<li class="admin_nav_item cont4"><a class="yellow underline menuitem_4_search_theater" style="color: white;"
				href="pmvReviewFrm">영화관 찾기</a></li>
			<li class="admin_nav_item cont5"><a class="yellow underline"
				data-toggle="modal" data-target="#rsrvModal" data-movieCd=""  data-thcode=""  id="modal" style="text-decoration: none;">빠른예매</a></li>
				
				<li><div id="login_before">
				
					<a href="#" id="login_btn" style="color: white;">로그인/</a>
					<a href="#" style="color: white;">회원가입</a>
				</div>
				<div id="login_after">
				<a style= "color: white;" class="mypage" href="./mypage">MYpage</a>
					<a href="#" id="userName"></a>
					<a href="./logout">로그아웃</a>
				</div></li>

		</ul>

	</div>
	<!-- 로그인 창 -->
<div id="login_bg">
	<div id="login_box">
		<div class="login_box_header">
			<h2 id="login_title">로그인</h2>
			<button class="close_btn">close button</button>
		</div>
		<div id="type">
			<div id="type_user" class="login_type">
				<span style="cursor: pointer">이용자 로그인</span>
			</div>
			<div id="type_business" class="login_type">
				<span style="cursor: pointer">사업자 로그인</span>
			</div>
		</div>
		<form action="./loginProc" method="post">
			<div id="user_tap" class="login_tap">
				<div class="login_wrap">
					<input class="login_input" placeholder="회원 아이디" name="m_id">
					<input class="login_input" placeholder="비밀번호" type="password" name="m_pw">
					<button class="login_button">로그인</button><br>
					<a>아이디/비밀번호 찾기</a> &nbsp;|&nbsp; <a href="./joinFrm">회원가입</a>
				</div>
				<div>
					<img alt="" src="https://www.indieartcinema.com/assets/img/saveourcinema.jpg">
				</div>
				<!-- name = Dto와 이름을 같이 -->
			</div>
		</form>
		<form action="./bu_loginProc" method="post">
			<div id="business_tap" class="login_tap">
				<div class="login_wrap">
					<input class="login_input" placeholder="사업자 아이디" name="b_id">
					<input class="login_input" placeholder="비밀번호" type="password" name="b_pw">
					<button class="login_button">로그인</button><br>
					<a>아이디/비밀번호 찾기</a> &nbsp;|&nbsp; <a href="./bu_joinFrm">회원가입</a>
				</div>
				<div>
					<img alt="" src="https://www.indieartcinema.com/assets/img/saveourcinema.jpg">
				</div>
			</div>
		</form>
	</div>
</div>
	<div class="modal fade" id="rsrvModal" tabindex="-1" role="dialog"	aria-labelledby="label" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog modal-xl modal-dialog-centered" role="document">
			<div class="modal-content"></div>
		</div>
	</div> 
			<div class="search_theater">
		<jsp:include page="main_search_theater.jsp"></jsp:include>
		</div>
	
	


</body>
<script src="resource/js/jquery-3.6.0.min.js"></script>
<script src="resource/js/bootstrap.bundle.js"></script>
<script type="text/javascript"  src="resource/js/rsrv.js"></script>
<script type="text/javascript">
//로그인 클릭시 모달 창 띄움
$("#login_btn").click(function() {
	$("#login_bg").show();
});

// 로그인 창에서 x버튼 클릭시 창 닫음
$(".close_btn").click(function() {
	$("#login_bg").css("display", "none");
});

// 이용자, 사업자 클릭시 탭 이동
$("#type_user").click(function() {
	$("#user_tap").css("display", "flex");
	$("#type_user").css("border-bottom", "2px solid #f16a1a");
	$("#business_tap").css("display", "none");
	$("#type_business").css("border-bottom", "2px solid #717171");
});
$("#type_business").click(function() {
	$("#user_tap").css("display", "none");
	$("#type_user").css("border-bottom", "2px solid #717171");
	$("#business_tap").css("display", "flex");
	$("#type_business").css("border-bottom", "2px solid #f16a1a");
});

//로그인하면 로그인 정보 출력, 헤더 메뉴 변경
// "${userInfo.m_name}" - userInfo는 service에서 session에 저장한 이름
var userInfo = "${userInfo.m_name}";


if(userInfo != "") {
	$("#userName").html(userInfo);
	$("#login_after").css("display", "inline-block");
	$("#login_before").css("display", "none");
	$(".mypage").css("display","inline-block");
}

	
	$('.search_rayer').hide();
	$('.menuitem_4_search_theater').mouseover(function(){
		console.log("내려간다.");
		$('.search_rayer').slideDown();
	});
	

	$('.search_close').mouseup(function(){
		$('.search_rayer').hide();
	})
	




</script>
</html>