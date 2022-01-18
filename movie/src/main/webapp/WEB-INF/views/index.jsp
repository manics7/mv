<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie | eivoM</title>
<link rel="stylesheet" type="text/css" href="resource/css/index.css">
<script type="text/javascript" src="resource/js/jquery-3.6.0.min.js"></script>

</head>
<body>

	<div id="body_wrap">
<nav>
<jsp:include page="main_header.jsp"></jsp:include>
</nav>
		<div id="section_wrap">
			<div id="main_wrap">
				<div id="box_office">
					<div id="box-office-title">현재상영작</div>
					<div id="more-movie">

						<a style="color: white;" href="./currentMovieList">
							더많은 영화보기 
							<img alt="" src="https://img.megabox.co.kr/static/pc/images/common/ico/ico-more-cross-gray.png">
						</a>
					</div>
					<div id="box-office-list">
						<ol id="box-list">
							<c:forEach var="boxOfficeList" items="${mvOfficial }" begin="0" end="3">
								<li class="box-office-list">
									<a href="./movieDetail?movie_cd=${boxOfficeList.movie_cd }">
										<img src="${boxOfficeList.poster }" title="${boxOfficeList.movie_nm }">
									</a>
									<div class='btn_box'>
										<a href="#" data-toggle="modal" data-target="#rsrvModal" data-movieCd="${boxOfficeList.movie_cd }" id="modal" style="color: white;"> 예매 </a>
									</div>
								</li>
							</c:forEach>
						</ol>
					</div>
					<div id="search-box">
						<div class="search-cell">
							<div>
								<form name="mainMovieSearch" action="./currentMovieList" method="get">
									<input type="text" placeholder="영화명을 입력해주세요!" name="mainMovieSearch">
									<button></button>
								</form>
							</div>
						</div>
						<div class="search-cell">
							<a href="totalMovie"> 상영시간표 </a>
						</div>
						<div class="search-cell">

							<a href="./currentMovieList">
								박스오피스
							</a>
						</div>
						<div class="search-cell">
							<a href="#"> 빠른예매 </a>
						</div>
					</div>
				</div>
			</div>
			<div id="section_container">
				<div id="event_wrap">
					<!-- <div>
						<h2>이벤트</h2>
					</div> -->
					
					<div></div>
				</div>
				<div class="notice_box">
					<h2 class="notice_box_title">공지사항</h2>
					<ul>
						<li>
							<a href="#">
								<span class="text">[기타]개인정보처리방침 변경 안내</span>
								<span class="date">2020.10.20</span>
							</a>
						</li>
					</ul>
					<a class="more_1" href="#">더보기</a>
				</div>
				<div class="que_box">
					<h2 class="que_box_title">자주 묻는 질문</h2>
					<ul>
						<li>
							<a href="#">
								<span class="text">[기타]개인정보처리방침 변경 안내</span>
								<span class="date">2020.10.20</span>
							</a>
						</li>
					</ul>
					<a class="more_2" href="#">더보기</a>
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
	$(document).ready(function(){
		
		//메시지 출력 부분
		var msg = "${msg}"
		var info = "${userInfo.m_name}"
		
		if(msg != ""){
			alert(msg);
		}
		
		//$('.search_rayer').hide();
		//$('.menuitem_4_search_theater').mouseover(function(){
			//console.log("내려간다.");
			//$('.search_rayer').slideDown();
		//});
		
	
		//메시지 출력 부분
		var msg = "${msg}";
		var info = "${userInfo.m_name}"
		$('.search_close').mouseup(function(){
			$('.search_rayer').hide();
		})
		
		var bg = "${mvOfficial[0].poster}"
		console.log(bg);
		$('#main_wrap').css("background-image", "linear-gradient(rgba(0, 0, 0, 0.91), rgba(0, 0, 0, 0.91)), URL(" + bg + ")");
		
	})
		
		
		
	</script>
</html>