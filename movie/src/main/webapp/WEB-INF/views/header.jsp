<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
	<div id="top_wrap">
		<div id="top_menu">
			<a href="./" id="top_logo"><img alt="" src="resource/images/logo.png"></a>
			<ul>

				<li class="search_th"><a href="#" class="se_th" style="cursor: pointer;">영화관찾기</a></li>
				<li><a href="#">영화검색</a></li>
				<li><a href="#">영화관후기</a></li>
				<li class="suc" id="userName"><a href="#">마이페이지</a></li>
				<li id="login_btn" class="nomal"><a href="#" class="login_btn" style="cursor: pointer;">로그인</a></li>
				<li class="nomal"><a href="#">회원가입</a></li>
				<li class="suc"><a href="./logout">로그아웃</a></li>
				<li class="suc"><a href="#" data-toggle="modal" data-target="#rsrvModal" data-movie="127"  data-thcode="1"  data-id="100" id="modal">빠른예매</a></li>

			</ul>
		</div>
		<div id="sub_menu">
			<div>
				<!-- 서치 박스 -->
				<input class="search_box">
			</div>
			<div>
				<!-- 지역 -->
				<span>전체</span>
				<span>서울</span>
				<span>경기</span>
				<span>인천</span>
				<span>강원</span>
				<span>충북</span>
				<span>충남</span>
				<span>전북</span>
				<span>전남</span>
				<span>경북</span>
				<span>경남</span>
				<span>제주</span>			
			</div>
			<ul>
				<!-- 생기면 추가 -->
				<li><a href="#">광주</a></li>
				<li><a href="#">광주</a></li>
				<li><a href="#">광주</a></li>
				<li><a href="#">광주</a></li>
				<li><a href="#">광주</a></li>
				<li><a href="#">test del</a></li>
				
			</ul>
		</div>
	</div>
	

	<!-- 로그인 창 -->
	
		<div id="login_bg">
			<div id="login_box">
				<div class="login_box_header">
					<h2 id="login_title">로그인</h2>
					<button class="close_btn">
						close button
					</button>
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
						아이디 : <input name="m_id"><br>
						<!-- name = Dto와 이름을 같이 -->
						비밀번호 : <input type="password" name="m_pw"><br>
						<button class="login_button">로그인</button><br>
						<a>아이디/비밀번호 찾기</a> | 
						<a href="./joinFrm">회원가입</a>
					</div>
				</form>
				<form action="./bu_loginProc" method="post">
					<div id="business_tap" class="login_tap">
						사업자 아이디 : <input name="b_id"><br>
						비밀번호 : <input type="password" name="b_pw"><br>
						<button class="login_button">로그인</button><br>
						<a>아이디/비밀번호 찾기</a> | 
						<a href="./bu_joinFrm">회원가입</a>
					</div>
				</form>
			</div>
		</div>	

<script type="text/javascript">
	$(function() {
		// 영화관 검색 클릭시 하단 메뉴 출력
		$(".search_th").click(function() {
			$("#sub_menu").slideToggle(300);
		});
		
		// a태그, 클릭시 주소창 # 없앰
		$(".se_th").removeAttr("href");
		$(".login_btn").removeAttr("href");
		
		// 로그인 클릭시 모달 창 띄움
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
		
	});
</script>
	<div class="modal fade" id="rsrvModal" tabindex="-1" role="dialog"	aria-labelledby="label" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog modal-xl modal-dialog-centered" role="document">
			<div class="modal-content"></div>
		</div>
	</div> 
	

<script type="text/javascript"  src="resource/js/rsrv.js"></script>