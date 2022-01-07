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
					
					<!-- 영화 관람평 목록 -->
					
					<table>
						<tr>
							<td>작성자</td>
							<td>후기</td>
							<td>작성일</td>						
						</tr>
					</table>
					<table id="reviewList">
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
					<!-- 영화 관람평 작성 -->
					<form id="reviewMovie">
						<input type="text" placeholder="관람평을 작성해 주세요" id="reviewComment" name="mv_review_comment" onkeypress="if(event.keyCode==13){return false;}">
						<input type="button" onclick="reviewMovie(${mvOfficial.movie_cd})" value="작성">
					</form>
				</div>
			</div>
		</div>
	</div>
	
		<jsp:include page="footer.jsp"></jsp:include>
</body>
	<script src="resource/js/jquery-3.6.0.min.js"></script>
	<script src="resource/js/jquery.serializeObject.js"></script>
	<script type="text/javascript">
		
		// 관람평 ajax로 처리
		function reviewMovie(movie_cd) {
			console.log("movie_cd" + movie_cd);
			
			var reviewMovieList = $("#reviewMovie").serializeObject();
			reviewMovieList.mv_review_moviecd = movie_cd;
			reviewMovieList.mv_review_id = "${userInfo.m_id}"
			console.log(reviewMovieList);
			
			// controller에 ajax로 전송
			$.ajax({
				url: "insertReviewMovie",
				type: "post",
				data: reviewMovieList,
				dataType: "json",
				success: function(result){
					var reviewList = "";
					var reviewMv = result.reviewMovieList;
					console.log(reviewMv);
					
					for(var i = 0; i < reviewMv.length; i++) {
						reviewList += "<tr>"
								   +	"<td>" + reviewMv[i].mv_review_id + "</td>"
								   +	"<td>" + reviewMv[i].mv_review_comment + "</td>"
								   +	"<td>" + reviewMv[i].mv_review_date + "</td>"
								   +  "</tr>";
					} // for end
					$("#reviewList").html(reviewList);
					$("#reviewComment").val("");
				}, // success end
				error: function(error) {
					console.log(error);
					alert("댓글 입력 실패");
				} // error end
			}) // ajax end
		} // function end	
	
	</script>
	
</html>