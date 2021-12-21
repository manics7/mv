<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화관 후기 게시판</title>
<link rel="stylesheet" type="text/css" href="resource/css/review.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<script src="https://kit.fontawesome.com/27f70b73f3.js" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){
	//메시지 출력
	var msg = "${msg}";
	if(msg != ""){
		alert(msg);
	}
});
</script>
</head>
<body>
	<h2 id="rv_title">REVIEW</h2>
	<div class="rv_header">
		<div class="rv_info">
			<a>총 ${maxNum}개의 게시물이 있습니다.</a>
		</div>
        <div class="rv_search">
        	<form name="search_form" action="./slist" method="post">
            	<select name="type">
                	<option selected value="rtitle" name="rtitle">제목</option>
                	<option value="mid" name="mid">작성자</option>
                	<option value="thname" name="thname">영화관</option>
            	</select>
            	<input type="text" id="search_text" name="keyword" placeholder="검색어를 입력하세요.">
            	<input type="submit" value="검색">
            </form>
        </div>
	</div>

	<div class="rv_section">
		<table class="rv_table">
			<tr class="title-row">
				<th class="t_no p-10">번호</th>
				<th class="t_theater p-10">영화관</th>
				<th class="t_title p-30">제목</th>
				<th class="t_date p-10">작성일</th>
				<th class="t_id p-10">작성자</th>
				<th class="t_view p-10">조회수</th>
				<th class="t_like p-10">추천수</th>
			</tr>
			
			<!-- 검색 결과 목록 -->
			<c:forEach var="bitem" items="${sList}">
				<tr class="data_row">
					<td class="t_no p-10">${bitem.rnum}</td>
					<td class="t_theater p-10">${bitem.thname}</td>
					<td class="t_title p-30">${bitem.rtitle}</td>
					<td class="t_date p-10">
						<fmt:formatDate value="${bitem.rdate}"
							pattern="yy/MM/dd" />
					</td>
					<td class="t_id p-10">${bitem.mid}</td>
					<td class="t_view p-10">${bitem.rview}</td>
					<td class="t_like p-10">${bitem.rlike}</td>
				</tr>
			</c:forEach>
			
			<!-- 전체 목록222 -->
			<c:forEach var="bitem" items="${bList}">
				<tr class="data_row">
					<td class="t_no p-10">${bitem.rnum}</td>
					<td class="t_theater p-10">${bitem.thname}</td>
					<td class="t_title p-30">${bitem.rtitle}</td>
					<td class="t_date p-10">
						<fmt:formatDate value="${bitem.rdate}"
							pattern="yy/MM/dd" />
					</td>
					<td class="t_id p-10">${bitem.mid}</td>
					<td class="t_view p-10">${bitem.rview}</td>
					<td class="t_like p-10">${bitem.rlike}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
    <div class="btn-area1">
        <button class="wr-btn" onclick="location.href='./writeFrm'">글쓰기</button>
    </div>
	<div class="btn-area2">
		<div class="paging">${paging}</div>
	</div>
</body>
</html>