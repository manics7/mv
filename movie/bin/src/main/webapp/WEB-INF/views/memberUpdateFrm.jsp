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
	.memberUpdate{
		float: right;
	}
	.page>div{
		margin-top: 15px;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">

$(function(){
	var lid = "${mb.m_id}";
	var lname = "${mb.m_name}";
	var lbirth = "${mb.m_birth}";
	var ltel = "${mb.m_tel}";
	var lemail = "${mb.m_email}";
	
	$("#mid").html(lid);
	$("#mname").html(lname);
	$("#mbirth").html(lbirth);
	$("#mtel").html(ltel);
	$("#memail").html(lemail);
});
</script>
</head>
<body>
<div class="page">
	<div id="mid"></div>
	<div>기본정보</div>
	<div>이름: <span id="mname"> </span><button class="memberUpdate">변경</button></div>
	<div>생년월일: <span id="mbirth"> </span></div>
	<div>휴대폰 번호: <span id="mtel"> </span><button class="memberUpdate">변경</button></div>
	<div>이메일: <span id="memail"> </span><button class="memberUpdate">변경</button></div>
	<div>비밀번호<button class="memberUpdate">변경</button></div>

	<div>
		<button class="memberUpdatelast">변경</button>
		<button class="memberUpdatelast">회원탈퇴</button>
	</div>

</div>

</body>
</html>