<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>현재상영작</title>
<link rel="stylesheet" type="text/css" href="resource/css/currentMovieList.css">
</head>
<body>
	<nav>
	<jsp:include page="main_header.jsp"></jsp:include>
	</nav>
	
	
	<div id="section_wrap">
		<div id="titleMovieList">
			<div class="title">
				현재상영작fff
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
									<div>
										<!-- 마우스 올리면 상세 내용 보여짐 -->
										<!-- 클릭시 영화 상세페이지 이동
										 -->
										<a href="./movieDetail?movie_cd=${movieList.movie_cd }">
											<div>
												${movieList.movie_content }
											</div>
											<div>
												평점!
											</div>
										</a>
									</div>
								</div>
								<div 제목>
									<p>${movieList.watch_grade_nm }</p>
									<p>${movieList.movie_nm }</p>
								</div>
								<div 개봉일>
									${movieList.open_dt }
								</div>
								<div class="btn_box">
									<button>
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
</html>