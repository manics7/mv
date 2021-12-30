<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>영화 보러 가자~</title>
<link rel="stylesheet" type="text/css" href="resource/css/hf.css">

<link rel="stylesheet" type="text/css" href="resource/css/index.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<link rel="stylesheet" type="text/css" href="resource/css/bootstrap.css">
<script src="resource/js/jquery-3.6.0.min.js"></script>
<script src="resource/js/bootstrap.bundle.js"></script>
<script src="resource/js/jquery.serializeObject.js"></script>


</head>
<body>
<div class="main_wrap">

	<div class="slider" style="border: none;">
    <div>
    	<img src="https://caching2.lottecinema.co.kr/lotte_image/2021/Clifford/1224/Clifford_1920774.jpg"
    		 data-video="https://caching2.lottecinema.co.kr/lotte_image/2021/Clifford/1224/Clifford_1280720.mp4"
    		 alt="클리포드 더 빅 레드독 사랑받는 만큼 커져요 1월 12일 대개봉 전체관람가">
    </div>
    <div>
    	<img src="https://caching2.lottecinema.co.kr/lotte_image/2021/Ts/Ts_1920774.jpg"
			 data-video="https://caching2.lottecinema.co.kr/lotte_image/2021/Ts/Ts_1280720.mp4"
    		 alt="특송 1월 12일 대개봉 새해를 여는 첫 번째 범죄 오락 액션 15세 이상 관람가">
    </div>
    <div>
    	<img src="https://caching2.lottecinema.co.kr/lotte_image/2021/Mat/1221/Mat_1920774.jpg"
    		 data-video="https://caching2.lottecinema.co.kr/lotte_image/2021/Mat/1221/Mat_1280720.mp4"
    		 alt="매트릭스 리저렉션 SF 액션 블록버스터의 전설 12월 22일 대개봉 모든것이 시작된 곳 전설이 부활한다 15세이상관람가">
    </div>
</div>

	<header style="position: absolute; top: 0; left: 0;">
		<!-- --------------- header --------------- -->
		<jsp:include page="header.jsp"></jsp:include>
	</header>

	<section>
	 	<div id="border">
	 	</div>
	 	<div id="movie_wrap">
	 		<div id="movie_boxoffice">
 				박스오피스
	 		</div>
	 		<div class="boxoffice_list">
	 			
	 		</div>
	 	</div>
	 	<div id="event">
	 	</div>
	 	
	</section>
	
	<footer>
		<!-- --------------- footer --------------- -->
		<jsp:include page="footer.jsp"></jsp:include>
	</footer>

</div>

</body>

  <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script type="text/javascript">
	$(function() {
		//메시지 출력 부분
		var msg = "${msg}";
		if(msg != ""){
			alert(msg);
		}
		
		var userInfo =  "${userInfo}";  
		// 로그인하면 로그인 정보 출력, 헤더 메뉴 변경
		
		if(userInfo != "") {
			$("#userName").html(userInfo + " 님");
			$(".suc").css("display", "inline-block");
			$(".nomal").css("display", "none");
		}
		
		$(document).ready(function(){
		      $('.slider').bxSlider();
		    });
		
	});
</script>

</html>