<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MEMBER MANAGE</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
	//회원 작성글 팝업 형태로 출력.
</script>
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
			<form name="searchFrm" action="./memberSelect" method="post">
				<div class="card-body">
					<h4 class="card-title">회원관리</h4>
					<input type="text" placeholder="ID 입력" name="m_id"> <input
						type="submit" value="검색">
					<table class="table table-hover" id='board_list'>
						<thead>
							<tr>
								<th class="text-center d-none d-md-table-cell">회원ID</th>
								<th class="text-center d-none d-md-table-cell">이름</th>
								<th class="text-center d-none d-md-table-cell">HP</th>
								<th class="text-center d-none d-md-table-cell">주소</th>
								<th class="text-center d-none d-md-table-cell">생년월일</th>
								<th class="text-center d-none d-md-table-cell">회원 게시글 확인</th>
								<th class="text-center d-none d-md-table-cell">회원 탈퇴처리</th>
							</tr>
						</thead>
						<tbody>
							<!-- 이부분은 검색 -->
							<c:forEach var="mitem" items="${mseList}">
								<tr>
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


							<c:forEach var="mitem" items="${mList}">

								<tr>
									<td class="text-center d-none d-md-table-cell">${mitem.m_id}</td>
									<td><a href='board_read.html'>${mitem.m_name}</a></td>
									<td class="text-center d-none d-md-table-cell">${mitem.m_phone}</td>
									<td class="text-center d-none d-md-table-cell">${mitem.m_addr}</td>
									<td class="text-center d-none d-md-table-cell">${mitem.m_birth}</td>
									<td class="text-center d-none d-md-table-cell"><a href="#">확인</a></td>
									<td class="text-center d-none d-md-table-cell"><a
										href="./deleteMember">삭제</a></td>

								</tr>
							</c:forEach>
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

			</form>

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
</html>