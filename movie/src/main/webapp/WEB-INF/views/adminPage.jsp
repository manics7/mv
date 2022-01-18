<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>AdminPage</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="resource/css/mmanagestyle/mmanage.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>

<body>

	<div class="wrap">
	
	 
	<nav>
	<jsp:include page="admin_header.jsp"></jsp:include>
</nav>
		<div class="main_wrap">
			<div class="cont_wrap">
				<div class="cont_sidebar">
					<jsp:include page="adminpage_sidebar.jsp" />
				</div>
				<div class="container queboard" style="padding: 100px 0;">
					<div class="card shadow">
	
						<form name="searchFrm" action="./memberSelect?pageNum=1"
							method="post">
							<div class="card-body">


								<div class="member_top" style="justify-content: space-around;">
									<h4 class="card-title" style="text-align: center;">관리자페이지</h4>
								</div>

								

								<div class="d-none d-md-block">
									<ul class="pagination justify-content-center">
										<li class="page-item">${paging}</li>
									</ul>
								</div>

								<div class="d-block d-md-none">
									<ul class="pagination justify-content-center">
										<li class="page-item"><a href="#" class="page-link">이전</a></li>
										<li class="page-item"><a href="#" class="page-link">다음</a></li>
									</ul>
								</div>
							</div>

						</form>

					</div>

				</div>
			</div>
		</div>


	</div>
	<!-- 상단 메뉴 부분 -->


	<!-- 게시글 리스트 -->
	</div>



	<div class="footer_wrap">
	<footer>
			<jsp:include page="footer.jsp"></jsp:include>
		</footer>

	</div>
</body>

</html>