<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 쓴 감상평</title>
<style type="text/css">
div {
}

.page {
	margin: 15%;
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
	border : 1px solid gray;
	background-color: lightgray;
}

.title-row{
	border : 1px solid gray;
}
.title-row>p{
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

</script>
</head>
<body>
	<div class="page">
		<div class="title-row">
			<p>
				<b>내가 쓴 감상평</b>
			</p>
			<div class="t-no p-10">번호</div>
			<div class="t-title p-15">영화명</div>
			<div class="t-name p-30">내용</div>
			<div class="t-date p-30">등록일</div>
			<div class="t-view p-15">평점</div>
		</div>
		<c:forEach var="mvritem" items="${mvrList}">
			<div class="data-row">
				<div class="t-no p-10">${mvritem.mv_review_num}</div>
				<div class="t-title p-15">
					<!-- 상세보기 화면 이동 url + 게시글번호 -->
					<a href="./contents?qno=${mvritem.mv_review_num}"> ${mvritem.mv_name} </a>
				</div>
				<div class="t-name p-30">${mvritem.mv_review_comment}</div>
				<div class="t-date p-30">
					<fmt:formatDate value="${mvritem.mv_review_date}"
						pattern="yyyy-MM-dd" />
				</div>
				<div class="t-view p-15">${mvritem.mv_review_score}</div>
			</div>
		</c:forEach>
		
		<div id="btn1">
			<a href="memberUpdateFrm">예매/구매내역</a>
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