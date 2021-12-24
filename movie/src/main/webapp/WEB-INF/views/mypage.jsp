<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style type="text/css">
div {
	border: 1px solid black;
}

.page {
	margin: 15%;
	border: 1px solid black;
}

a {
	text-decoration: none;
}

#btn2 {
	float: right;
	border: 1px solid black;
}

#btn1 {
	float: left;
	size: 10px;
}

.main_point_box>div {
	width: 33.1%;
	height: 150px;
	float: left;
}

.title-row>div {
	display: block;
	float: left;
	height: 30px;
	font-size: 16px;
	line-height: 1.6;
	text-align: center;

}
.data-row>div{
	float: left;
	height: 30px;
	font-size: 16px;
	line-height: 1.6;
	text-align: center;
}

.p-10 {
	width: 8%;
}

.p-15 {
	width: 15%;
}

.p-30 {
	width: 30%;
}

.btn-area {
	margin-top: 10%;
	clear: both;
}

</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		var lname = "${mb.m_name}";
		$("#mname").html(lname + "님,");
	});
</script>
</head>
<body>
	<div class="page">
		<div id="btn2">
			<a href="memberUpdateFrm">회원정보 수정</a>
		</div>
		<div id="mname"></div>
		<div>환영합니다.</div>

		<div class="main_point_box">
			<div class="pointbox1">
				<p>선호 극장</p>
			</div>
			<div class="pointbox2">
				<p>영화 할인쿠폰</p>
				<a>0장</a>
			</div>
			<div class="pointbox2">
				<p>고객센터</p>
				<ol>
					<li>예매문의 031-8017-8332</li>
					<li>평일 10:00 ~ 12:00, 13:00 ~ 18:00</li>
					<li>영화예매/영화안내는 지원하지 않습니다.</li>
				</ol>
			</div>
		</div>

		<div class="title-row">
			<p>
				<b>내 문의내역</b>
			</p>
			<div class="t-no p-10">번호</div>
			<div class="t-title p-15">문의유형</div>
			<div class="t-name p-30">제목</div>
			<div class="t-date p-30">등록일</div>
			<div class="t-view p-15">답변상태</div>
		</div>
		<c:forEach var="qitem" items="${qList}">
			<div class="data-row">
				<div class="t-no p-10">${qitem.ques_no}</div>
				<div class="t-name p-15">일반</div>
				<div class="t-title p-30">
					<!-- 상세보기 화면 이동 url + 게시글번호 -->
					<a href="./contents?qno=${qitem.ques_no}"> ${qitem.ques_title} </a>
				</div>
				<div class="t-date p-30">
					<fmt:formatDate value="${qitem.ques_date}"
						pattern="yyyy-MM-dd" />
				</div>
				<div class="t-view p-15">${qitem.ques_state}</div>
			</div>
		</c:forEach>
		
		<div id="btn1">
			<a href="purchaseFrm">예매/구매내역</a>
		</div>
		<div id="btn1">
			<a href="memberCouponFrm">영화 할인 쿠폰</a>
		</div>
		<div id="btn1">
			<a href="watcheMovieFrm">내가 본 영화</a>
		</div>
		<div id="btn1">
			<a href="pmvReviewFrm">내가 쓴 감상평</a>
		</div>
		<div id="btn1">
			<a href="questionFrm">1:1문의</a>
		</div>
		
		<div class="btn-area">
				<div class="paging">${paging}</div>
		</div>
	</div>

</body>
</html>