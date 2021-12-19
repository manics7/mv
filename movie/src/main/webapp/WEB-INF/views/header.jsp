<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<div id="top_wrap">
		<div id="top_menu">
			<a href="#" id="top_logo"> logo </a>
			<ul>
				<li class="search_th"><button>영화관 찾기</button></li>
				<li><a href="#">영화 검색</a></li>
				<li><a href="#">영화관 후기</a></li>
				<li class="suc"><a href="#">마이페이지</a></li>
				<li class="nomal"><a href="#" class="nomal">로그인</a></li>
				<li class="nomal"><a href="#" class="nomal">회원 가입</a></li>
				<li class="suc"><a href="#">빠른 예매</a></li>
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

<script src="resource/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".search_th").click(function() {
			$("#sub_menu").toggle();
		})
	})
</script>