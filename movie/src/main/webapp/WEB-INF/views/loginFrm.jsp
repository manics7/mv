<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loginFrm</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	//메시지 출력 부분
	var msg = "${msg}";
	if(msg != ""){
		alert(msg);
	}
});
</script>
</head>
<body>
<form action="loginProc" method="post">
	<input type="text" name="m_id"><br>
	<input type="password" name="m_pw"><br>
	<input type="submit" value="로그인">
</form>

<a href="reportFrm">신고관리</a>
</body>
</html>