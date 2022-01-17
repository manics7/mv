<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>현재상영작</title>
<link rel="stylesheet" type="text/css" href="resource/css/currentMovieList.css">
</head>

<body>
<div class="nav_wrap">
<div class="nav_wrap_cont" ></div>
<jsp:include page="main_header.jsp"></jsp:include>
</div>


	<div id="section_wrap">

		<div id="titleMovieList">
		
		
		
			<div class="title">
				현재상영작
			</div>
		</div>
		<div id="main_wrap">
			<div id="movie_wrap">
				<div id="current_movieList">
					<ol id="movie_list">
						<c:forEach var="movieList" items="${movieList }">
							<li class="movie-list">
								<div class="poster_contain">
									<div class="poster_box">
										<img src="${movieList.poster }" alt="${movieList.movie_nm }" class="poster" onerror="noImg(this, 'main');">
									</div>
									<div class="movieSummary">
										<!-- 마우스 올리면 상세 내용 보여짐 -->
										<!-- 클릭시 영화 상세페이지 이동 -->
										<a href="./movieDetail?movie_cd=${movieList.movie_cd }">
											<div class="movieContent">
												<div class="summary">
													<span>${movieList.movie_content }</span>
												</div>
												<div class="movieRate">
													<span>평점!</span>
												</div> 
											</div>
										</a>
									</div>
								</div>
								<div class="movieTitle">
<!-- 									 -->
									<c:choose>
										<c:when test="${movieList.watch_grade_nm eq '12' }">
											<p class="watchGrade" style="background-image: url(https://img.megabox.co.kr/static/pc/images/common/txt/txt-age-12.png)">
										</c:when>
										<c:when test="${movieList.watch_grade_nm eq '15' }">
											<p class="watchGrade" style="background-image: url(https://img.megabox.co.kr/static/pc/images/common/txt/txt-age-15.png)">
										</c:when>
										<c:when test="${movieList.watch_grade_nm eq '18' }">
											<p class="watchGrade" style="background-image: url(https://img.megabox.co.kr/static/pc/images/common/txt/txt-age-18.png)">
										</c:when>
										<c:otherwise>
											<p class="watchGrade" style="background-image: url(https://img.megabox.co.kr/static/pc/images/common/txt/txt-age-all.png)">
										</c:otherwise>
									</c:choose>
									<p class="movieNm">${movieList.movie_nm }</p>
								</div>
								<div class="movieOpenDt">
									개봉일  
									<fmt:formatDate value="${movieList.open_dt }" pattern="yyyy.MM.dd"/> 
								</div>
								<div class="btn_box">
									<button data-toggle="modal" data-target="#rsrvModal" data-movieCd="${movieList.movie_cd }" id="modal" style="cursor: pointer;">
										예매
									</button>
								</div>
							</li>
						</c:forEach>
					</ol>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="footer.jsp"></jsp:include>

</body>

	<script src="resource/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
	
// 		$(".poster_box").hover(function() {
// 			$(this).next().css("display", "flex");
// 		}, function() {
// 			$(this).next().css("display", "none");
// 		})
		
	</script>

</html>