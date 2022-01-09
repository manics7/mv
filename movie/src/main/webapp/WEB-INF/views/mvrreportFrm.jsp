<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고 관리</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
	//메시지 출력 부분
	var msg = "${msg}";
	if(msg != ""){
		alert(msg);
	});
</script>
</head>
<body>

	<!-- 상단 메뉴 부분 -->
	<nav
		class="navbar navbar-expand-md bg-dark navbar-dark fixed-top shadow-lg">
		<a class="navbar-brand" href="index.html">MVTI</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navMenu">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navMenu">
			<ul class="navbar-nav">
				<li class="nav-item"><a href="board_main.html" class="nav-link">매뉴1</a>
				</li>
				<li class="nav-item"><a href="board_main.html" class="nav-link">매뉴2</a>
				</li>
				<li class="nav-item"><a href="board_main.html" class="nav-link">매뉴3</a>
				</li>
				<li class="nav-item"><a href="board_main.html" class="nav-link">매뉴4</a>
				</li>
			</ul>

			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a href="login.html" class="nav-link">로그인</a>
				</li>
				<li class="nav-item"><a href="join.html" class="nav-link">회원가입</a>
				</li>
				<li class="nav-item"><a href="modify_user.html"
					class="nav-link">정보수정</a></li>
				<li class="nav-item"><a href="index.html" class="nav-link">로그아웃</a>
				</li>
			</ul>
		</div>
	</nav>

	<!-- 게시글 리스트 -->
	<div class="container" style="margin-top: 100px">
		<div class="card shadow">
			<form name="searchFrm" action="mvReviewSearch" method="post">
				<div class="card-body">
					<h4 class="card-title">신고관리</h4>
					<input type="text" placeholder="ID 입력" name="mvname"> <input
						type="submit" value="검색">
				</div>
			</form>
					<table class="table table-hover" id='board_list'>
						<thead>
							<tr>
							<!-- 	<th class="text-center d-none d-md-table-cell">번호</th> -->
								<th class="text-center d-none d-md-table-cell">신고자ID</th>
								<th class="text-center d-none d-md-table-cell">신고 사유</th>
								<th class="text-center d-none d-md-table-cell">리뷰 내용</th>
								<th class="text-center d-none d-md-table-cell">작성자ID</th>
								<th class="text-center d-none d-md-table-cell">신고일</th>
								<th class="text-center d-none d-md-table-cell">처리상태</th>
								<th class="text-center d-none d-md-table-cell">관리</th>
							</tr>
						</thead>
						<tbody>
						<!-- 검색 처리 -->
						<c:choose>
						<c:when test="${not empty rpList}">
							<c:forEach var="rpitem" items="${rpList}">
								<tr>
									<td class="text-center d-none d-md-table-cell">${rpitem.rpid}</td>
									<td class="text-center d-none d-md-table-cell">${rpitem.rpwhy}</td>
									<td class="text-center d-none d-md-table-cell">${rpitem.contents}</td>
									<td class="text-center d-none d-md-table-cell">${rpitem.rptid}</td>
									<td class="text-center d-none d-md-table-cell"><fmt:formatDate
											value="${rpitem.rpdate}" pattern="yyyy-MM-dd" /></td>
									<td class="text-center d-none d-md-table-cell">
								<c:choose> 
									<c:when test="${rpitem.state == '0'}">
										미처리
									</c:when> 
									<c:otherwise>
										처리완료
									</c:otherwise> 
								</c:choose> 
								
									</td>
									<td class="text-center d-none d-md-table-cell">
									<c:choose> 
									<c:when test="${rpitem.state == '0'}">
										<button id="btn1" onclick="delCheck(${rpitem.mvrnum})">경고 및 삭제</button>
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
							<td class="text-center d-none d-md-table-cell">신고 정보가 없습니다.</td>
						</c:otherwise>

					</c:choose>
						
						
							<!-- <c:forEach var="mitem" items="${mseList}">
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
						<!-- <div class="paging">${paging}</div>  -->
						<div class="pagination justify-content-center">
							<div class="page-item">${paging}</div>
						</div>
					</div>

					<div class="d-block d-md-none">
						<ul class="pagination justify-content-center">
							<li class="page-item"><a href="#" class="page-link">이전</a></li>
							<li class="page-item"><a href="#" class="page-link">다음</a></li>
						</ul>
					</div>
			
		</div>
	</div>

	<div class="container-fluid bg-dark text-white"
		style="margin-top: 50px; padding-top: 30px; padding-bottom: 30px">
		<div class="container">
			<p>sehun</p>
			<p>게시판</p>
			<p>사업자번호 : 000-000-000</p>
		</div>
	</div>
</body>
<script type="text/javascript">
function delCheck(mv_review){
	var conf = confirm("삭제하시겠습니까?");
	
	if(conf == true){
		location.href='./delAdminMvReview?mv_review=' + mv_review;
	}
}
</script>
</html>