<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 본문</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="resources/css/style.css">
<script type="text/javascript">
$(function(){
	//메시지 출력 부분
	var msg = "${msg}";
	if(msg != ""){
		alert(msg);
	}
	
	//수정/삭제 버튼 처리(본인의 글이 아니면 수정/삭제 불가)
	$("#upbtn").hide();
	$("#delbtn").hide();
	
	var mid = "viu97";
	var bid = "${bDto.mid}";
	
	if(mid == bid){
		$("#upbtn").show();
		$("#delbtn").show();
		$("#likebtn").hide();
	}
	
});
</script>
</head>
<body>
<table>
				<tr height="30">
					<span><a>신고하기</a></span>
				</tr>
				<tr height="30">
					<td width="100" bgcolor="pink" 
						align="center">NUM</td>
					<td colspan="5">${bDto.rnum}</td>
				</tr>
				<tr height="30">
					<td width="100" bgcolor="pink" 
						align="center">THEATER</td>
					<td colspan="5">${bDto.thname}</td>
				</tr>
				<tr height="30">
					<td bgcolor="pink" align="center">
						WRITER</td>
					<td width="150">${bDto.mid}</td>
					<td bgcolor="pink" align="center">
						DATE</td>
					<td width="200">
						<fmt:formatDate value="${bDto.rdate}"
							pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td bgcolor="pink" align="center">
						VIEWS</td>
					<td width="100">${bDto.rview}</td>
				</tr>
				<tr height="30">
					<td bgcolor="pink" align="center">
						TITLE</td>
					<td colspan="5">${bDto.rtitle}</td>
				</tr>
				<tr height="200">
					<td bgcolor="pink" align="center">
						CONTENTS</td>
					<td colspan="5" bgcolor="white">
						${bDto.rcontent}</td>
				</tr>
				<tr>
					<td colspan="6" align="center">
						<button class="btn-write" id="upbtn"
							onclick="location.href='./updateRvFrm?rnum=${bDto.rnum}'">U</button>
						<button class="btn-write" id="delbtn"
							onclick="delCheck(${bDto.rnum})">D</button>
						<button class="btn-sub"
							onclick="location.href='./rlist?pageNum=${pageNum}'">B</button>
					</td>
				</tr>
				<tr>
					<td colspan="6" align="center">
						<button id="likebtn">LIKE ${bDto.rlike}</button>
					</td>
				</tr>
</table>
			<!-- 댓글 작성 양식 -->
			<form id="replyFrm">
				<textarea rows="3" class="write-input ta"
					name="r_contents" id="comment"
					placeholder="댓글을 적어주세요~"></textarea>
				<input type="button" value="댓글 전송"
					class="btn-write" onclick="replyInsert(${board.bnum})"
					style="width: 100%; margin-bottom: 30px;">
			</form>
			<!-- 댓글 목록 보기 -->
			<table style="width: 100%"><!-- 제목 테이블 -->
				<tr bgcolor="pink" align="center" height="30">
					<td width="20%">WRITER</td>
					<td width="50%">CONTENTS</td>
					<td width="30%">DATE</td>
				</tr>
			</table>
			<table id="rtable" style="width: 100%"><!-- 목록 테이블 -->
				<c:forEach var="r" items="${rList}">
				<tr>
					<td width="20%">${r.r_id}</td>
					<td width="50%">${r.r_contents}</td>
					<td width="30%">
						<fmt:formatDate value="${r.r_date}"
							pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				</c:forEach>
			</table>
</body>
<script src="resources/js/jquery.serializeObject.js"></script>
<script type="text/javascript">
function delCheck(rnum){
	var conf = confirm("삭제하시겠습니까?");
	
	if(conf == true){
		location.href='./deleteRv?rnum=' + rnum;
	}
}
</script>
</html>