<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap"
	rel="stylesheet">
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/4bf78bd3aa.js"
	crossorigin="anonymous"></script>
<title>main</title>
</head>
<body>
	<h1>TestPage</h1>
<div style="border : 1px solid gray; padding: 3.5px; width: 200px;">

<a href="./mmanage?pageNum=1">회원관리</a> <br>
	<a href="./quesboard?pageNum=1">문의게시판</a><br>
	<a href="./thDetail">영화관 상세 페이지</a><br>
	<a href="./roomInsertFrm">좌석정보입력 테스트</a><br>
</div> <br>

	<form action="./thDetail">
		영화 이름 : <input type="text" name="movie_nm"><br> 
		상영 날짜 : <input type="text" name="sch_date"><br>
		시작 시간 : <input type="text" name="sch_detail_start"><br> 
		종료 시간 : <input type="text" name="sch_detail_end"><br> <br>
		<input type="submit" value="상세페이지 이동">
	</form>
</body>
</html>

<!-- 

<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap"
	rel="stylesheet">
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/4bf78bd3aa.js"
	crossorigin="anonymous"></script>
<title>main</title>
</head>
<body>
	<h1>TestPage</h1>
<div style="border : 1px solid gray; padding: 3.5px; width: 200px;">

<a href="./mmanage?pageNum=1">회원관리</a> <br>
	<a href="./quesboard?pageNum=1">문의게시판</a><br>
	<a href="./thDetail">영화관 상세 페이지</a><br>
</div> <br>

	<form action="./thDetail">
		영화 이름 : <input type="text" name="movie_nm"><br> 
		상영 날짜 : <input type="text" name="sch_date"><br>
		시작 시간 : <input type="text" name="sch_detail_start"><br> 
		종료 시간 : <input type="text" name="sch_detail_end"><br> <br>
		<input type="submit" value="상세페이지 이동">
	</form>

 -->

