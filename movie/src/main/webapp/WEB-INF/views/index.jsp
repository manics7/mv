<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 보러 가자~</title>
<link rel="stylesheet" type="text/css" href="resource/css/hf.css">
<script src="resource/js/jquery-3.6.0.min.js"></script>
<script src="resource/js/jquery.serializeObject.js"></script>
<script type="text/javascript">
$(document).ready(function() {
		
});
</script>
</head>
	<header>
		<!-- --------------- header --------------- -->
		<jsp:include page="header.jsp"></jsp:include>
	</header>

	<section>
		<div style="width: 100%; height: 1000px;">
		
		</div>
	<div id="container">
		
		<div class="items">
		 	<div class="item active">
		 		<img alt="" src="https://caching2.lottecinema.co.kr/lotte_image/2021/West/West_1920774.jpg">
		 	</div>
		 	<div class="item">
		 		<img alt="" src="https://caching2.lottecinema.co.kr/lotte_image/2021/Kings/1221/Kings_1920774.jpg">
		 	</div>
		 	<div class="item">
		 		<img alt="" src="https://caching2.lottecinema.co.kr/lotte_image/2021/Mat/1221/Mat_1920774.jpg">
		 	</div>
		 	<div class="item">
		 		<img alt="" src="https://caching2.lottecinema.co.kr/lotte_image/2021/Mat/1221/Mat_1920774.jpg">
		 	</div>
	 	</div>
	 	
	 	<div class="stepper">
	 		<div class="step active-step"></div>
	 		<div class="step"></div>
	 		<div class="step"></div>
	 		<div class="step"></div>
	 	</div>
	 		<button class="prev">prev</button>
			<button class="next">next</button>
		</div> 	
	</div>
		
	 
	</section>
	
	<footer>
		<!-- --------------- footer --------------- -->
		<jsp:include page="footer.jsp"></jsp:include>
	</footer>

</body>

<script src="resource/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		//메시지 출력 부분
		var msg = "${msg}";
		if(msg != ""){
			alert(msg);
		}
		
		// 로그인하면 로그인 정보 출력, 헤더 메뉴 변경
		// "${userInfo.m_name}" - userInfo는 service에서 session에 저장한 이름
		var userInfo = "${userInfo.m_name}";
		
		if(userInfo != "") {
			$("#userName").html(userInfo + " 님");
			$(".suc").css("display", "inline-block");
			$(".nomal").css("display", "none");
		}
		
	});
</script>

</html>