<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie | eivoM</title>
<link rel="stylesheet" type="text/css" href="resource/css/index.css">
</head>
<body>

	<div id="body_wrap">
		<header>
			<jsp:include page="header.jsp"></jsp:include>		
		</header>
		
		<div id="section_wrap">
			<div id="main_wrap">
				<div id="box_office">
					<div id="box-office-title">
						박스오피스
					</div>
					<div id="more-movie">
						<a href="#">
							더많은 영화보기 
							<img alt="" src="https://img.megabox.co.kr/static/pc/images/common/ico/ico-more-cross-gray.png">
						</a>
					</div>
					<div id="box-office-list">
						<ol id="box-list">
							<li class="box-office-list">
								<a href="">
									<img src="https://img.megabox.co.kr/SharedImg/2021/12/16/ixl5QxDVs5Gn8nQN3rslK8BUmgFVklj8_420.jpg" alt="스파이더맨: 노 웨이 홈" class="poster" onerror="noImg(this, 'main');">
								</a>
								<div class="btn_box">
									<a href="">
										예매
									</a>
								</div>
							</li>
							<li class="box-office-list">
								<a href="">
									<img src="https://img.megabox.co.kr/SharedImg/2021/12/27/25gHj5XQ6zm8zB5VYpJQSH9b6veVrN2n_420.jpg" alt="킹스맨: 퍼스트 에이전트" class="poster" onerror="noImg(this, 'main');">
								</a>
								<div class="btn_box">
									<a href="">
										예매
									</a>
								</div>
							</li>
							<li class="box-office-list">
								<a href="">
									<img src="https://img.megabox.co.kr/SharedImg/2021/12/10/IfzIFvSnuJV9ZS0W7yfmq89V6hWGKC0s_420.jpg" alt="해피 뉴 이어" class="poster" onerror="noImg(this, 'main');">
								</a>
								<div class="btn_box">
									<a href="">
										예매
									</a>
								</div>
							</li>
							<li class="box-office-list">
								<a href="">
									<img src="https://img.megabox.co.kr/SharedImg/2021/12/07/DE9SaAwBXRBXWLSf7lRBlWAn51gZxN9A_420.jpg" alt="경관의 피" class="poster" onerror="noImg(this, 'main');">
								</a>
								<div class="btn_box">
									<a href="">
										예매
									</a>
								</div>
							</li>
						</ol>
					</div>
					<div id="search-box">
						<div class="search-cell">
							1
						</div>
						<div class="search-cell">
							2
						</div>
						<div class="search-cell">
							3
						</div>
						<div class="search-cell">
							4
						</div>
					</div>
				</div>
			</div>
			<div id="section_container"
				
			</div>
		</div>
		
		<footer>
			<jsp:include page="footer.jsp"></jsp:include>
		</footer>
	</div>
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
		var userInfo = "${userInfo.mName}";
		
		if(userInfo != "") {
			$("#userName").html(userInfo + " 님");
			$(".suc").css("display", "inline-block");
			$(".nomal").css("display", "none");
		}
	</script>
</html>