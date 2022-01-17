<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<link rel="stylesheet" type="text/css" href="resource/css/hf.css">



<div id="header_wrap">
	<div class="contain_wrap">
		<a href="/"> <img alt="" src="resource/images/logo.png" id="logo">
		</a>
		<div id="header_top">
			<div id="header_left">
				<a href="#">공지사항</a>
				<a href="#">고객센터</a>
			</div>
			<div id="header_right">
				<div id="login_before">
				
					<a href="#" id="login_btn">로그인</a>
					<a href="#">회원가입</a>
				</div>
				<div id="login_after">
				<a style= "color: white;" class="mypage" href="./mypage">MYpage</a>
					<a href="#" id="userName"></a>
					<a href="./logout">로그아웃</a>
				</div>
			</div>
		</div>
		<div id="header_bottom">
			<ol>
				<li><a href="./currentMovieList">영화</a></li>
				<li><a href="theater_detail">영화관</a></li>
				<li><a href="/rlist">영화관후기</a></li>
				<li><a href="#" class="menuitem_4_search_theater">영화관찾기</a></li>
				<li><a href="#">이벤트</a></li>
				<li><a href="#" data-toggle="modal" data-target="#rsrvModal" data-movieCd="127"  data-thcode="1"  data-id="100" id="modal">빠른예매</a></li>
			</ol>
		</div>
	</div>
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
					<input class="login_input" placeholder="아이디" name="m_id">
					<input class="login_input" placeholder="비밀번호" type="password" name="m_pw">
					<button class="login_button">로그인</button>
					<a>아이디/비밀번호 찾기</a> &nbsp;|&nbsp; <a href="./joinFrm">회원가입</a>
				</div>
				<!-- name = Dto와 이름을 같이 -->
			</div>
		</form>
		<form action="./bu_loginProc" method="post">
			<div id="business_tap" class="login_tap">
				<div class="login_wrap">
					<input class="login_input" placeholder="아이디" name="b_id">
					<input class="login_input" placeholder="비밀번호" type="password" name="b_pw">
					<button class="login_button">로그인</button>
					<a>아이디/비밀번호 찾기</a> &nbsp;|&nbsp; <a href="./bu_joinFrm">회원가입</a>
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
	$("#user_tap").css("display", "inline-block");
	$("#type_user").css("border-bottom", "2px solid #f16a1a");
	$("#business_tap").css("display", "none");
	$("#type_business").css("border-bottom", "2px solid #717171");
});
$("#type_business").click(function() {
	$("#user_tap").css("display", "none");
	$("#type_user").css("border-bottom", "2px solid #717171");
	$("#business_tap").css("display", "inline-block");
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
</script>
