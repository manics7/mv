	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" type="text/css" href="resource/css/joinFrm.css">
</head>
<body>
	<section id="joinFrm">
		<div id="join_wrap">
			<div id="logo_box">
				<!-- 메인 페이지 이동용 logo -->
				<a href="./"><img alt="" src="resource/images/logo_black.png"></a>
			</div>
			<div id="title_box">
				<h1>회원가입</h1>
			</div>
			<div>
				<form name="joinFrm" action="./memberInsert" method="post" onsubmit="return joinCheck()">
					<table id="join_table">
						<tr>
							<th>아이디</th>
						</tr>
						<tr>
							<td>
								<input type="text" id="mid" name="m_id" autofocus="autofocus">
								<input type="button" id="duCheck" onclick="check_id()" value="중복확인">
							</td>
						</tr>
						<tr>
							<th>비밀번호</th>
						</tr>
						<tr>
							<td><input type="password" name="m_pw" id="pw" onchange="check_pw()"></td>
						</tr>
						<tr>
							<th>비밀번호 확인</th>
						</tr>
						<tr>	
							<td>
								<input type="password" id="pw2" onchange="check_pw()">
								<p id="checkPw"></p>
							</td>
						</tr>
						<tr>
							<th>이름</th>
						</tr>
						<tr>	
							<td><input type="text" name="m_name"></td>
						</tr>
						<tr>
							<th>휴대전화번호</th>
						</tr>
						<tr>	
							<td><input type="number" name="m_tel" placeholder="  예) 01063269166"></td>
						</tr>
						<tr>
							<th>이메일</th>
						</tr>
						<tr>	
							<td><input type="text" name="m_email" placeholder=" 예) dnckd18@nate.com"></td>
						</tr>
						<tr>
							<th>생년월일</th>
						</tr>
						<tr>	
							<td><input type="number" name="m_birth" placeholder="  예) 931004"></td>
						</tr>
						<tr>
							<th>성별</th>
						</tr>
						<tr>	
							<td>
								남<input type="radio" name="m_gender" value="1">
								여<input type="radio" name="m_gender" value="2">
							</td>
						</tr>
						<tr>
							<th>즐겨찾는 영화관</th>
						</tr>
						<tr>	
							<td>
								<select name="m_like">
									<option value="" selected="selected" disabled="disabled" hidden=""></option>
									<option value="1">인천 1극장</option>
									<option value="2">인천 2극장</option>
									<option value="3">인천 3극장</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								<button type="submit">회원가입</button>
							</td>
						</tr>
					</table>
				</form>		
			</div>
		</div>
	</section>
</body>
	
	<script src="resource/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
	
		var msg = "${msg}";
		if(msg != ""){
			alert(msg);
		}
	
		// 아이디 중복체크 반환 변수
		var idOk = false;
		// 아이디 중복 체크 확인
		function check_id() {
			var idid = $("#mid").val();
			
			if(idid == "") {
				alert("아이디를 입력하세요");
				$("#mid").focus();
				return;
			}
			
			var checkId = {"mid":idid};
			
			// ajax로 전송
			$.ajax({
				url: "idCheck",
				type: "get",
				data: checkId,
				success: function(result) {
					if(result == "ok") {
						alert("사용 가능한 아이디 입니다");
						idOk = true;
					}
					else {
						alert("중복된 아이디 입니다");
						$("#mid").val("");
						$("#mid").focus();
						idOk = false;
					}
				},
				error: function(error) {
					console.log(error);
					idOk = false;
				}
			});
		}
		
		// 비밀번호 일치 확인
		function check_pw() {
		// 비밀번호 일치 확인 반환 변수	
		var pwOk = false;
			
			if(document.getElementById('pw').value !='' && document.getElementById('pw2').value !='') {
				if(document.getElementById('pw').value == document.getElementById('pw2').value) {
					document.getElementById('checkPw').innerHTML = '비밀번호가 일치합니다';
					document.getElementById('checkPw').style.display = 'inline-block';
					document.getElementById('checkPw').style.color = 'blue';
					pwOk = true;
				}
				else {
					document.getElementById('checkPw').innerHTML = '비밀번호가 일치하지 않습니다';
					document.getElementById('checkPw').style.display = 'inline-block';
					document.getElementById('checkPw').style.color = 'red';
					pwOk = false;
				}
			}
			else{
				document.getElementById('checkPw').innerHTML = '비밀번호를 확인해주세요';
				document.getElementById('checkPw').style.display = 'inline-block';
				document.getElementById('checkPw').style.color = 'red';
				pwOk = false;
			}
			return pwOk;
		}
		
		// 회원가입 버튼, 최종 확인
		function joinCheck() {
			if(idOk == false && pwOk == false) {
				alert("아이디 중복체크 또는 비밀번호를 확인 해주세요");
				return false;
			}
			return true;
		}
	</script>
</html>