<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 상영관 관리</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="resource/css/room.css">
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
<nav style="background: black; padding-top: 35px; padding-bottom: 35px;">
<jsp:include page="business_header.jsp"></jsp:include>
</nav>
<section>
<div class="detail">
	<div class="inner">
		<div id="page_wrap">
		<div id="side">
		<jsp:include page="business_sidebar.jsp"></jsp:include>
		
		</div>
		<!-- 
		
		<div id="side">
				<h2><a href="#" style="color: black;">Business Page</a></h2>
				<ul id="bupage_list">
					<li><a id="bupage_menu" href="#">영화관 관리</a></li>
					<li><a id="bupage_menu" href="#">영화 관리</a></li>
					<li><a id="bupage_menu" href="#">상영관 관리</a></li>
					<li><a id="bupage_menu" href="#">상영 일정 관리</a></li>
					<li><a id="bupage_menu" href="#">이벤트 관리</a></li>
				</ul>
			</div>
		
		
		 -->
			            <div class="room_div">
                <h3 id="room_title">상영관 목록</h3>
                    <table class="rv_table">
                        <tr class="title-row">
                            <th class="t_no p-10">영화관명</th>
                            <th class="t_theater p-10">상영관명</th>
                            <th class="t_title p-10">총 좌석 수</th>
                            <th class="t_date p-10">상영관 종류</th>
                            <th class="t_id p-10">삭제 처리</th>
                        </tr>
                
                        <!-- 상영관 목록이 없을 때 -->
                        <c:if test="${empty roomList}">
                            <tr class="data_row">
                                <td colspan="5">등록된 상영관이 없습니다.</td>
                            </tr>
                        </c:if>
                        
                        <!-- 상영관 목록 출력 -->
                        <c:forEach var="room" items="${roomList}">
                            <tr class="data_row">
                                <td class="t_no p-10">${room.thname}</td>
                                <td class="t_theater p-10">${room.room_no}관[${room.room_name}]</td>
                                <td class="t_title p-10">총 ${room.seat_cnt}석</td>
                                <td class="t_date p-10">${room.room_class}</td>
                                <td class="t_id p-10">
                                    <button class="btn-del"
                                    onclick="delCheck(${room.room_seq})">삭제</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                <button class="btn-room" onclick="location.href='./roomInsertFrm'">등록</button>
            </div>
		</div>
	</div>
</div>
</section>
		<footer>
			<jsp:include page="footer.jsp"></jsp:include>
		</footer>
</body>
<script type="text/javascript">
function delCheck(roomseq){
	var conf = confirm("삭제하시겠습니까?");
	
	if(conf == true){
		location.href='./roomDelete?roomseq=' + roomseq;
	}
}
</script>
</html>