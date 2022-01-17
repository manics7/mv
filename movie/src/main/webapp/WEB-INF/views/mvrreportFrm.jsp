<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>영화리뷰 신고목록</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="resource/css/mmanagestyle/mmanage.css">
<style type="text/css">
.busbtn5{
	outline : 0;
	width: 100px;
	height: 50px;
	line-height:50px;
	background: transparent;
	border: 1px solid lightgray;
	cursor: pointer;
}
.busbtn5:hover{
	background: #f16a1a;
	color: white;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>

<body>


	<div class="wrap">
		<nav>
			<jsp:include page="admin_header.jsp"></jsp:include>
		</nav>
		<div class="main_wrap">
			<div class="cont_wrap">
				<div class="cont_sidebar">
					<jsp:include page="adminpage_sidebar.jsp" />
				</div>
				<div class="container queboard">
					<div class="card shadow">
						<div class="member_top_btn_wrap">
							<div class="member_top_btn">
								<a class="btn_nomal" style="background: #f16a1a; color: white;"
									href="./mvrreportFrm">영화리뷰신고</a> 
									<a href="boardreportFrm" class="btn_nomal">게시글신고</a> 
									<a class="busbtn" href="replyreportFrm">댓글신고</a>
							</div>



						</div>
							<div class="card-body">


								<div class="member_top">
									<h4 class="card-title yellow underline">관리자 신고관리</h4>
									<div class="input_box">
										
									</div>

								</div>

								<table class="table table-hover" id='board_list'>
									<thead>
										<tr>
											<th class="text-center d-none d-md-table-cell">신고자ID</th>
											<th class="text-center d-none d-md-table-cell">신고 사유</th>
											<th class="text-center d-none d-md-table-cell">내용</th>

											<th class="text-center d-none d-md-table-cell">작성자ID</th>
											<th class="text-center d-none d-md-table-cell">신고일</th>	
											<th class="text-center d-none d-md-table-cell">처리상태</th>
										</tr>
									</thead>
									<tbody>
										<!-- 검색 처리 -->
										<c:choose>

											<c:when test="${not empty rpList}">
							<c:forEach var="rpitem" items="${rpList}">
							<tr>
								<td class="text-center d-none d-md-table-cell">${rpitem.rp_m_id}</td>
									<td class="text-center d-none d-md-table-cell">${rpitem.rp_why}</td>
									<td class="text-center d-none d-md-table-cell">${rpitem.rp_contents}</td>
									<td class="text-center d-none d-md-table-cell">${rpitem.rpt_m_id}</td>
									<td class="text-center d-none d-md-table-cell"><fmt:formatDate
											value="${rpitem.rp_date}" pattern="yyyy-MM-dd" /></td>
									<td class="text-center d-none d-md-table-cell">
								<c:choose> 
									<c:when test="${rpitem.rp_state == '0'}">
										<button id="btn1" class="busbtn5" onclick="delCheck(${rpitem.movie_review})">미처리</button>
									</c:when> 
									<c:otherwise>
										처리완료
									</c:otherwise> 
								</c:choose> 
								
									</td>

								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
						<td class="text-center d-none d-md-table-cell">신고 요청이 없습니다.</td>
						</c:otherwise>
						
						</c:choose>

										<!--  

                               <c:forEach var="mitem" items="${mseList}">
                                        <tr>
                                            <td><c:if test="${mseList == 'null'}">존재 하지 않는 회원 입니다.</c:if></td>
                                            <td class="text-center d-none d-md-table-cell">${mitem.m_id}</td>
                                            <td><a href='board_read.html'>${mitem.m_name}</a></td>
                                            <td class="text-center d-none d-md-table-cell">${mitem.m_phone}</td>
                                            <td class="text-center d-none d-md-table-cell">${mitem.m_addr}</td>
                                            <td class="text-center d-none d-md-table-cell">${mitem.m_birth}</td>
                                            <td class="text-center d-none d-md-table-cell"><a type="button" href="./mboardSelect?m_id=${mitem.m_id}">확인</a></td>
                                            <td class="text-center d-none d-md-table-cell"><a
                                                href="./deleteMember">삭제</a></td>
                                        </tr>
                                    </c:forEach>
-->

									</tbody>
								</table>

								<div class="d-none d-md-block">
									<ul class="pagination justify-content-center">
										<li class="page-item">${paging}</li>
									</ul>
								</div>
							</div>

					</div>

				</div>
			</div>
		</div>


	</div>
	<!-- 상단 메뉴 부분 -->


	<!-- 게시글 리스트 -->



	<div class="footer_wrap">
		<footer>
			<jsp:include page="footer.jsp"></jsp:include>
		</footer>

	</div>
</body>
<script type="text/javascript">
function delCheck(movie_review){
	var conf = confirm("삭제하시겠습니까?");
	
	if(conf == true){
		location.href='./delAdminMvReview?movie_review=' + movie_review;
	}
}
</script>
</html>