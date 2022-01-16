<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>영화가 좋다</title>
<link rel="stylesheet" type="text/css"
	href="resource/css/movieDetail.css">
</head>

<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div id="wrap">
		<div id="background">
			<div id="container">
				<div id="bgPoster"
					style="background-image: url(${mvOfficial.poster })">
					<div id="movieInfoDiv">
						<div id="movieInfoWrap">
							<p id="movieDetailTitle">${mvOfficial.movie_nm }</p>
							<p id="movieDetailInfo" class="detailInfo">
								<c:choose>
									<c:when test="${mvOfficial.watch_grade_nm eq '12' }">
										<p class="watchGrade"
											style="background-image: url(https://img.megabox.co.kr/static/pc/images/common/txt/txt-age-12.png)">
									</c:when>
									<c:when test="${mvOfficial.watch_grade_nm eq '15' }">
										<p class="watchGrade"
											style="background-image: url(https://img.megabox.co.kr/static/pc/images/common/txt/txt-age-15.png)">
									</c:when>
									<c:when test="${mvOfficial.watch_grade_nm eq '18' }">
										<p class="watchGrade"
											style="background-image: url(https://img.megabox.co.kr/static/pc/images/common/txt/txt-age-18.png)">
									</c:when>
									<c:otherwise>
										<p class="watchGrade"
											style="background-image: url(https://img.megabox.co.kr/static/pc/images/common/txt/txt-age-all.png)">
									</c:otherwise>
								</c:choose>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; |
								<fmt:formatDate value="${mvOfficial.open_dt }"
									pattern="yyyy.MM.dd" />
								| ${mvOfficial.genre_nm } | ${mvOfficial.show_tm }분
							</p>
							<br>
							<p id="Director" class="detailInfo">
								<span>감독 </span> ${mvOfficial.directors }
							</p>
							<p id="actor" class="detailInfo">
								<span>배우 </span> ${mvOfficial.actors }
							</p>
							<br>
							<button data-toggle="modal" data-target="#rsrvModal" data-movieCd="${movieList.movie_cd }" id="modal" style="cursor: pointer;">
								예매하기
							</button>
						</div>
						<div id="posterBtn">
							<div id="detailPoster">
								<img alt="" src="${mvOfficial.poster }">
							</div>
						</div>
					</div>
				</div>
				<div id="menuWrap">
					<div id="menu">
						<ul>
							<li id="info">기본정보</li>
							<li id="theaterList">상영 영화관</li>
							<li id="review">관람평</li>
							<li id="stillcut">스틸컷</li>
						</ul>
					</div>
					<div class="info">
						<div>
							<h3>감독 및 배우</h3>
							<span>감독</span> ${mvOfficial.directors }<br>
							<span>배우</span> ${mvOfficial.actors }<br>
							
							
							<h3>줄거리</h3>
							${mvOfficial.movie_content }
<!-- 							내용 입력 할건데요~? -->
						</div>
					</div>
					<div class="theaterList">
						<ul  id="theaterTitle">
							<c:forEach var="theaterNames" items="${theaterName }">
								<li class="theaterTitle">
									<div class="titleWrap">
										<a href="" data-toggle="modal" data-target="#rsrvModal" data-movieCd="${mvOfficial.movie_cd }"
										   data-thcode="${theaterNames.th_code }"  data-id="100" id="modal">
											<div class="theaterLogo">
												<img alt="" src="${theaterNames.th_logo }">
											</div>
											<div class="theaterName">
												${theaterNames.th_name }
											</div>										
										</a>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
					<div class="review">
						<div>
							<h3>영화 후기</h3>

							<!-- 영화 관람평 목록 -->
							<table>
								<tr>
									<td>
										${userInfo.m_id }
									</td>
									
											<!-- 영화 관람평 작성 -->
											<c:choose>
												<c:when test="${empty userInfo.m_id }">
													<td colspan="3">
														<input type="text" placeholder="로그인을 해주세요" readonly="readonly">
													</td>		
												</c:when>
												<c:when test="${!empty userInfo.m_id }">
													<td>
														<form id="reviewMovie">
															<input type="text" placeholder="관람평을 작성해 주세요" id="reviewComment" name="mv_review_comment" onkeypress="if(event.keyCode==13){return false;}">
														</td>
														<td>
															<input type="button" onclick="reviewMovieF(${mvOfficial.movie_cd})" value="작성" id="write">
														</form>		
													</td>	
												</c:when>
											</c:choose>
									
								</tr>
								<tr>
									<td class="writer">작성자</td>
									<td class="reviewContent">후기</td>
									<td class="writeDate">작성일</td>
								</tr>
							</table>
							<table id="reviewList">
								<c:forEach var="reviewMovie" items="${reviewMovie}">
									<tr>
										<td class="writer">${reviewMovie.mv_review_id }</td>
										<td class="reviewContent">${reviewMovie.mv_review_comment }</td>
										<td class="writeDate">
											<fmt:formatDate value="${reviewMovie.mv_review_date }" pattern="MM-dd HH:mm"></fmt:formatDate>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
					<div class="stillcut">
						<ul>
							<li>
								<img alt="" src="${mvOfficial.poster }">
							</li>
							<li>
								<img alt="" src="${mvOfficial.stillcut1 }">
							</li>
							<li>
								<img alt="" src="${mvOfficial.stillcut2 }">
							</li>
							<li>
								<img alt="" src="${mvOfficial.stillcut3 }">
							</li>
							<li>
								<img alt="" src="${mvOfficial.stillcut4 }">
							</li>
							<li>
								<img alt="" src="${mvOfficial.stillcut5 }">
							</li>
						</ul>
					</div>
				</div>
			</div>
			<!-- container end -->
		</div>
	</div>

<%-- 			<jsp:include page="footer.jsp"></jsp:include> --%>

</body>
<script src="resource/js/jquery-3.6.0.min.js"></script>
<script src="resource/js/jquery.serializeObject.js"></script>
<script type="text/javascript">
		// 관람평 ajax로 처리
		function reviewMovieF(movie_cd) {	
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
		}; // function end	
		
			$(document).on("click", "#info", function() {
				$(".info").css("display", "inline-block");
				$("#info").css("border", "1.5px solid #f16a1a");
				$("#info").css("border-bottom", "0");
				$(".theaterList").css("display", "none");
				$("#theaterList").css("border-style", "solid solid solid solid");
				$("#theaterList").css("border-width", "0.5px 0 1.5px 0");
				$("#theaterList").css("border-color", "#ebebeb #ebebeb #f16a1a #ebebeb");
				$(".review").css("display", "none");
				$("#review").css("border-style", "solid solid solid solid");
				$("#review").css("border-width", "0.5px 0 1.5px 0");
				$("#review").css("border-color", "#ebebeb #ebebeb #f16a1a #ebebeb");
				$(".stillcut").css("display", "none");
				$("#stillcut").css("border-style", "solid solid solid solid");
				$("#stillcut").css("border-width", "0.5px 0 1.5px 0");
				$("#stillcut").css("border-color", "#ebebeb #ebebeb #f16a1a #ebebeb");
			});
			$(document).on("click", "#theaterList", function() {
				$(".theaterList").css("display", "inline-block");
				$("#theaterList").css("border", "1.5px solid #f16a1a");
				$("#theaterList").css("border-bottom", "0");
				$(".info").css("display", "none");
				$("#info").css("border-style", "solid solid solid solid");
				$("#info").css("border-width", "0.5px 0 1.5px 0");
				$("#info").css("border-color", "#ebebeb #ebebeb #f16a1a #ebebeb");
				$(".review").css("display", "none");
				$("#review").css("border-style", "solid solid solid solid");
				$("#review").css("border-width", "0.5px 0 1.5px 0");
				$("#review").css("border-color", "#ebebeb #ebebeb #f16a1a #ebebeb");
				$(".stillcut").css("display", "none");
				$("#stillcut").css("border-style", "solid solid solid solid");
				$("#stillcut").css("border-width", "0.5px 0 1.5px 0");
				$("#stillcut").css("border-color", "#ebebeb #ebebeb #f16a1a #ebebeb");
			});
			$("#review").click(function() {
				$(".review").css("display", "inline-block");
				$("#review").css("border", "1.5px solid #f16a1a");
				$("#review").css("border-bottom", "0");
				$(".theaterList").css("display", "none");
				$("#theaterList").css("border-style", "solid solid solid solid");
				$("#theaterList").css("border-width", "0.5px 0 1.5px 0");
				$("#theaterList").css("border-color", "#ebebeb #ebebeb #f16a1a #ebebeb");
				$(".info").css("display", "none");
				$("#info").css("border-style", "solid solid solid solid");
				$("#info").css("border-width", "0.5px 0 1.5px 0");
				$("#info").css("border-color", "#ebebeb #ebebeb #f16a1a #ebebeb");
				$(".stillcut").css("display", "none");
				$("#stillcut").css("border-style", "solid solid solid solid");
				$("#stillcut").css("border-width", "0.5px 0 1.5px 0");
				$("#stillcut").css("border-color", "#ebebeb #ebebeb #f16a1a #ebebeb");				
				});	
			$(document).on("click", "#stillcut", function() {
				$(".stillcut").css("display", "inline-block");
				$("#stillcut").css("border", "1.5px solid #f16a1a");
				$("#stillcut").css("border-bottom", "0");
				$(".theaterList").css("display", "none");
				$("#theaterList").css("border-style", "solid solid solid solid");
				$("#theaterList").css("border-width", "0.5px 0 1.5px 0");
				$("#theaterList").css("border-color", "#ebebeb #ebebeb #f16a1a #ebebeb");
				$(".review").css("display", "none");
				$("#review").css("border-style", "solid solid solid solid");
				$("#review").css("border-width", "0.5px 0 1.5px 0");
				$("#review").css("border-color", "#ebebeb #ebebeb #f16a1a #ebebeb");
				$(".info").css("display", "none");
				$("#info").css("border-style", "solid solid solid solid");
				$("#info").css("border-width", "0.5px 0 1.5px 0");
				$("#info").css("border-color", "#ebebeb #ebebeb #f16a1a #ebebeb");			
			});
		
	</script>

</html>