<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resource/css/home.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>파일테스트</h1>

<form action="/file" class="write-form"
			method="post" enctype="multipart/form-data">
			<label for="file">추가파일</label>
				<input type="file" name="files" id="file" multiple>
				<input class="upload-name" value="파일선택"
					readonly>
				<input type="hidden" id="filecheck" value="0"
					name="fileCheck">
					<input type="submit" class="btn-write">
</form>

</body>
</html>