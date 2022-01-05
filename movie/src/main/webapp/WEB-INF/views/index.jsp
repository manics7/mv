<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie | eivoM</title>
<link rel="stylesheet" type="text/css" href="resource/css/index.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<link rel="stylesheet" type="text/css" href="resource/css/bootstrap.css">
<script src="resource/js/jquery-3.6.0.min.js"></script>
<script src="resource/js/bootstrap.bundle.js"></script>
<script src="resource/js/jquery.serializeObject.js"></script>
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
							<div>
								<input type="text" placeholder="영화명을 입력해주세요!">
								<button></button>
							</div>
						</div>
						<div class="search-cell">
							<a href="#">
								상영시간표
							</a>
						</div>
						<div class="search-cell">
							<a href="#">
								박스오피스
							</a>
						</div>
						<div class="search-cell">
							<a href="#">
								빠른예매
							</a>
						</div>
					</div>
				</div>
			</div>
			<div id="section_container">
				<div id="event_wrap">
					<div>
						<h2>이벤트</h2>
					</div>
					<div>
						
					</div>
				</div>
				<div id="notice_wrap">
					<div>
						<h2>공지사항</h2>
					</div>
					<div>
						
					</div>
				</div>
				<div id="question">
					<div>
						<h2>문의사항</h2>
					</div>
					<div>
						
					</div>
				</div>
				<div id="question">
					<div>
						<h2>  <a href="./movetestsehun"></a>   테스트페이지 이동</h2>
					</div>
					<div>
						
					</div>
				</div>
			</div>
		</div>
		
		<footer>
			<jsp:include page="footer.jsp"></jsp:include>
		</footer>
	</div>
</body>
	<script src="resource/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		//메시지 출력 부분
		var msg = "${msg}";
		var info = "${userInfo.m_name}"
		
		if(msg != ""){
			alert(msg + info + " 님!");
		}
	</script>
</html>