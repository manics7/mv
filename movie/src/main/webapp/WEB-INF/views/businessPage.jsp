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
		<!-- --------------- header --------------- -->
		<jsp:include page="header.jsp"></jsp:include>	
	</header>

	<section>
		<h1>  사업자 페이지에요  </h1>
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
					$("#userName").html("사업자 " + busiInfo + " 님");
					$(".suc").css("display", "inline-block");
					$(".nomal").css("display", "none");
				}
				
		})
	
	</script>

</html>