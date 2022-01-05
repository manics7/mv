<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>영화가 좋다</title>
<link rel="stylesheet" type="text/css" href="resource/css/movieDetail.css">
</head>

<body>

		<jsp:include page="header.jsp"></jsp:include>
	
	<div id="wrap">
		<div id="background">
			<div id="container">
<%-- 				<img alt="${mvOfficial.movie_nm }" src="${mvOfficial.poster }" title="${mvOfficial.movie_nm }"> --%>
					<div id="info">
						<p>${mvOfficial.movie_nm }</p><br>
						<p>${mvOfficial.watch_grade_nm } | ${mvOfficial.open_dt } | ${mvOfficial.genre_nm } | ${mvOfficial.show_tm }</p>
					</div>
					<div id="poster_btn">
						<div>
							예매하기
						</div>
					</div>
				
			</div>
		</div>
		<div>
			<div>
				<div id="menu">
					<ul>
						<li>기본정보</li>						
						<li>상영 영화관</li>
						<li>관람평</li>
					</ul>
				</div>
				<div id="info">
					
				</div>
				<div id="영화관">
					
				</div>
				<div id="review">
					<!-- 영화 관람평 작성 -->
					<form action="">
						<input type="text" placeholder="관람평을 작성해 주세요">
						<input type="button" onclick="reviewMovie(${userInfo.m_name})" value="작성">
					</form>
					<!-- 영화 관람평 목록 -->
					<table>
						<tr>
							<td>작성자</td>
							<td>후기</td>
							<td>작성일</td>						
						</tr>
						<c:forEach var="reviewMovie" items="${reviewMovie}">
							<tr>
								<td>${reviewMovie.mv_review_id }</td>
								<td>${reviewMovie.mv_review_comment }</td>
								<td>
									<fmt:formatDate value="${reviewMovie.mv_review_date }" pattern="MM-dd HH:mm"></fmt:formatDate>
								</td>
							</tr>	
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
	
		<jsp:include page="footer.jsp"></jsp:include>
</body>
	
	<script src="resource/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		
	</script>
	
</html>