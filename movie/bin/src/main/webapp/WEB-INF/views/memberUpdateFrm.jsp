<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보 수정</title>
<style type="text/css">
	.page{
		margin: 15%;
		border: 1px solid black;
	}
	.page>div{
		margin-top: 15px;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	//컨트롤러에서 전달하는 메시지 출력.
	var msg = "${msg}";
	if(msg != ""){
		alert(msg);
	});
</script>
</head>
<body>
<div class="page">
	<div id="mid"></div>
	<div>기본정보</div>
	<form action="./memberUpdateProc" method="post">
	<div>이름: </div>
	<div>생년월일: </div>
	<div>휴대폰 번호: </div>
	<div>이메일: <span id="memail"> </span></div>
	<div>비밀번호</div>
	</form>
	<div>
		<button class="memberUpdatelast">변경</button>
		<button class="memberUpdatelast">회원탈퇴</button>
	</div>

</div>

</body>
</html>