<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>읽기창</title>
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

	<div class="container" style="margin-top:100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
					<form action="board_read.html" method="post">
					<div class="form-group">
						<label for="board_subject">제목</label>
						<input type="text" id="board_subject" name="board_subject" class="form-control"/>
					</div>
					<div class="form-group">
						<label for="board_content">내용</label>
						<textarea id="board_content" name="board_content" class="form-control" rows="10" style="resize:none"></textarea>
					</div>
					<div class="form-group">
						<label for="board_file">첨부 이미지</label>
						<input type="file" id="board_file" name="board_file" class="form-control" accept="image/*"/>
					</div>
					<div class="form-group">
						<div class="text-right">
							<button type="submit" class="btn btn-primary">작성하기</button>
						</div>
					</div>
					
					</form>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>

	<div class="container-fluid bg-dark text-white"
		style="margin-top: 50px; padding-top: 30px; padding-bottom: 30px">
		<div class="container">
			<p>http://www.sehun.co.kr</p>
			<p>읽기창</p>
			<p>사업자번호 : 000-000-222</p>
		</div>
	</div>

</body>
</html>