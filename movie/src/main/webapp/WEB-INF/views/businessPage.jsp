<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 페이지</title>
</head>
<body>

	<header>
		
	</header>

	<section>
		<h1>  사업자 페이지에요  </h1>
			<ul>
				<li class="suc" id="buName"><a href="#">마이페이지</a></li>
				<li id="login_btn" class="nomal"><a href="#" class="login_btn" style="cursor: pointer;">로그인</a></li>
				<li class="nomal"><a href="#">회원가입</a></li>
				<li class="suc"><a href="./bu_logout">로그아웃</a></li>
				<li class="suc"><a href="./theater">빠른예매</a></li>
			</ul>
	</section>

	<footer>
		<!-- --------------- footer --------------- -->
		<jsp:include page="footer.jsp"></jsp:include>
	</footer>

</body>

	<script src="resource/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		
		$(function () {

				var busiInfo = "${businessInfo.b_name}";
				
				if(busiInfo != "") {
					$("#buName").html("사업자 " + busiInfo + " 님");
					$(".suc").css("display", "inline-block");
					$(".nomal").css("display", "none");
				}
				
		})
	
	</script>

</html>