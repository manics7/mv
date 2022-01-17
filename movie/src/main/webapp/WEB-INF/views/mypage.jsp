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
<title>마이페이지</title>
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
	
	<style type="text/css">
	nav{
	width: 100%;
	}
	
	</style>
</head>

<body>
<nav>
	<jsp:include page="mypage_header.jsp"></jsp:include>
	</nav>
	<div class="wrap">
	
		<div class="main_wrap_wrap">
			<div class="main_wrap">
			<div class="sidebar">
				<jsp:include page="mypage_sidebar.jsp"></jsp:include>
				</div>
			<!-- 
			
			<div id="side">
					<h2><a href="mypage">My Page</a></h2>
					<ul id="bupage_list">
						<li><a id="bupage_menu" href="purchaseFrm">예매/구매내역</a></li>
						<li><a id="bupage_menu" href="watcheMovieFrm">내가 본 영화</a></li>
						<li><a id="bupage_menu" href="pmvReviewFrm">내가 쓴 감상평</a></li>
						<li><a id="bupage_menu" href="questionFrm">1:1문의</a></li>
					</ul>
				</div>
				
			
			 -->
					
				<div class="page_cont_wrap">
					<div class="user_info">
						<div class="profile_box">
							<div class="imgbox">
								<img src="resource/images/profileimag.svg" alt="">
							</div>
							<div class="profile_txt">
								<p class="m_id">${userInfo.m_name}님</p>
								<p>환영합니다.</p>

							</div>
						</div>
						<a class="txt_box" href="memberUpdateFrm">개인정보 수정</a>

					</div>
					<div class="main_point_box">
						<div class="point_box point1">
							<p class="tit">선호극장 ▶</p>
							<ol>${userInfo.m_like}</ol>
						</div>
						<div class="point_box point2">
							<p class="tit">영화 할인쿠폰 ▶</p>
							<ol>0장
							</ol>
						</div>
						<div class="point_box point3">
							<p class="tit">고객센터 ▶</p>
							<ol>
								<li>예매문의 031-8017-8332</li>
								<li>평일 10:00 ~ 12:00, 13:00 ~ 18:00</li>
								<li>영화예매/영화안내는 지원하지 않습니다.</li>
							</ol>
						</div>
					</div>
					<div class="tbl_list">
						<h3>내 문의내역</h3>
						<div class="question_table">
							<table class="table table-hover" id='board_list'>
								<thead>
									<tr>
										<th class="text-center d-none d-md-table-cell">문의번호</th>
										<th class="text-center d-none d-md-table-cell">제목</th>
										<th class="text-center d-none d-md-table-cell">문의유형</th>
										<th class="text-center d-none d-md-table-cell">등록일</th>
										<th class="text-center d-none d-md-table-cell">상태</th>

									</tr>
								</thead>
								<tbody>
									<!-- 검색 처리 -->
									<c:choose>
										<c:when test="${not empty qList}">
											<c:forEach var="qitem" items="${qList}">
												<tr>
													<td class="text-center d-none d-md-table-cell">${qitem.ques_no}</td>
													<td class="text-center d-none d-md-table-cell"><a
                                                    href='/questionContents?ques_no=${qitem.ques_no}'>${qitem.ques_title}</a></td></td>
													<td class="text-center d-none d-md-table-cell">일반</td>
													<td class="text-center d-none d-md-table-cell"><fmt:formatDate
															value="${qitem.ques_date}" pattern="yyyy-MM-dd" /></td>
													<td class="text-center d-none d-md-table-cell">
													<c:choose>
										<c:when test="${qitem.ques_state == '0'}">
											미답변
										</c:when>
										<c:when test="${qitem.ques_state == '1'}">
											답변완료
										</c:when>
									</c:choose></td>
												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<td class="text-center d-none d-md-table-cell">문의 내역이
												없습니다.</td>
										</c:otherwise>
									
									</c:choose>
								</tbody>
							</table>
						</div>
					</div>

				</div>
			</div>


		</div>


	</div>
</body>

</html>
