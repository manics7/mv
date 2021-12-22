	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	
	<div>
		<!-- 메인 페이지 이동용 logo -->
	</div>
	
	<section>
		<div>
			<h1>회원가입</h1>
		</div>
		<div>
			<form name="joinFrm" action="./memberInsert" method="post">
				<table>
					<tr>
						<th>아이디</th>
						<td><input type="text" name="m_id"></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="m_pw"></td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td><input type="password"></td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" name="m_name"></td>
					</tr>
					<tr>
						<th>휴대전화번호</th>
						<td><input type="number" name="m_tel"></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="text" name="m_email"></td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td><input type="number" name="m_birth"></td>
					</tr>
					<tr>
						<th>성별</th>
						<td><input type="number" name="m_gender"></td>
					</tr>
					<tr>
						<th>즐겨찾는 영화관</th>
						<td><input type="text" name="m_like"></td>
					</tr>
					<tr>
						<th colspan="2">
							<button type="submit">회원가입</button>
						</th>
					</tr>
				</table>
			</form>		
		</div>
	</section>

</body>
</html>