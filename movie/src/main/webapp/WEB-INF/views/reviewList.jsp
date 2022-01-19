<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화관 후기 게시판</title>
<link rel="stylesheet" href="resource/css/review.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<script src="https://kit.fontawesome.com/27f70b73f3.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	//메시지 출력 부분
	var msg = "${msg}";
	if(msg != ""){
		alert(msg);
	}
	//로그인 안 했을 때 글쓰기 버튼 비활성화
	$(".wr-btn").show();
	
	var mid = "${userInfo.m_id}";
	
	if(mid == null || mid == ""){
		$(".wr-btn").hide();
	}
});
</script>
</head>
<body>
<header>
	<jsp:include page="main_header.jsp"></jsp:include>
</header>
	<div class="rv_header">
		<div class="rv_info">
			<a id="rv_info">총 ${maxNum}개의 게시물이 있습니다.</a>
		</div>
        <div class="rv_search">
        	<form name="search_form" action="./slist" method="get">
            	<select name="type" class="rv_category">
                	<option selected value="rtitle" name="rtitle">제목</option>
                	<option value="mid" name="mid">작성자</option>
                	<option value="th_name" name="th_name">영화관</option>
            	</select>
            	<input type="text" id="search_text" name="keyword" placeholder="검색어를 입력하세요.">
            	<i class="fa fa-search" aria-hidden="true">
					<input type="submit" value="검색" class="fa-search-btn">
				</i>
				
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
			</tr>
			
			<!-- 전체 목록 -->
			<c:forEach var="bitem" items="${bList}">
				<tr class="data_row">
					<td class="t_no p-10">${bitem.ronum}</td>
					<td class="t_theater p-10">${bitem.th_name}</td>
					<td class="t_title p-30">
						<a id="t_title" href="./content?rnum=${bitem.rnum}">
							${bitem.rtitle}
						</a>
					</td>
					<td class="t_date p-10">
						<fmt:formatDate value="${bitem.rdate}"
							pattern="yy/MM/dd" />
					</td>
					<td class="t_id p-10">${bitem.mid}</td>
					<td class="t_view p-10">${bitem.rview}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
    <div class="btn-area1">
        <button class="wr-btn" onclick="location.href='./writeRvFrm'">글쓰기</button>
    </div>
	<div class="btn-area2">
		<div class="paging">${paging}</div>
	</div>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>