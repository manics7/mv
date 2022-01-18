<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="resource/css/mypage.css">
<title>사업자페이지</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="resource/css/theaterList.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>

<body>
	<nav>
	<jsp:include page="business_header.jsp"></jsp:include>
	</nav>
	<div class="wrap">

		<div class="main_wrap_wrap">
			<div class="main_wrap">
			<div class="sidebar">
				<jsp:include page="./business_sidebar.jsp"></jsp:include>
			</div>
				<div class="page_cont_wrap">
					<div class="user_info">
						<div class="profile_box">
							<div class="imgbox">
								<img src="resource/images/profileimag.svg" alt="">
							</div>
							<div class="profile_txt">
								<p class="m_id">${businessInfo.b_name}님</p>
								<p>환영합니다.</p>

							</div>
						</div>
						<a class="txt_box" href="businessUpdateFrm">개인정보 수정</a>

					</div>
					<div class="main_point_box">
						<div class="point_box point1">
							<p class="tit">등록 영화관 ▶</p>
							<ol>${thName}</ol>
						</div>
						<div class="point_box point2">
							<p class="tit">등록 - ▶</p>
							<ol></ol>
						</div>
						<div class="point_box point3">
							<p class="tit">고객센터 ▶</p>
							<ol>
								<li>사업자문의 031-8017-8332</li>
								<li>평일 10:00 ~ 12:00, 13:00 ~ 18:00</li>
								<li>영화예매/영화안내는 지원하지 않습니다.</li>
							</ol>
						</div>
					</div>
				
						</div>
					</div>

				</div>
			</div>
				<div class="footer_wrap">
		<footer>
			<!-- 이부분에 푸터부분 들어감 -->
		</footer>

	</div>
</body>

</html>