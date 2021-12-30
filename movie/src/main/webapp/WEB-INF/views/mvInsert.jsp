<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Insert</title>
</head>
<body>

<form action="./mvInsertProc" method="post">
 theater <input name="thCode" type="hidden" value="1">
 movie <input name="movieNm">
 open date <input name="openDt">
 genre <input name="genreNm">
 director <input name="directors">
 actor <input name="actors">
 show type
 <select name="showTypes">
 	<option label="2D" value="1"></option>
 	<option label="4DX" value="2"></option>
 </select>
 watch grade <input name="watchGradeNm">
 <input type="submit">	
</form>
 
</body>
</html>