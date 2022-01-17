<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 목록</title>
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
<section>
<div class="detail">
	<div class="inner">
		<div id="page_wrap">
			<div id="side">
				<h2><a href="#">Business Page</a></h2>
				<ul id="bupage_list">
					<li><a id="bupage_menu" href="#">영화관 관리</a></li>
					<li><a id="bupage_menu" href="#">영화 관리</a></li>
					<li><a id="bupage_menu" href="#">상영관 관리</a></li>
					<li><a id="bupage_menu" href="#">상영 일정 관리</a></li>
					<li><a id="bupage_menu" href="#">이벤트 관리</a></li>
				</ul>
			</div>
            <div class="room_div">
                <h3 id="room_title">이벤트 목록</h3>
                    <table class="rv_table">
                        <tr class="title-row">
                            <th class="t_no p-10">번호</th>
                            <th class="t_title p-30">이벤트명</th>
                            <th class="t_del p-10">삭제 처리</th>
                        </tr>
                
                        <!-- 상영관 목록이 없을 때 -->
                        <c:if test="${empty eventList}">
                            <tr class="data_row">
                                <td colspan="3">등록된 이벤트가 없습니다.</td>
                            </tr>
                        </c:if>
                        
                        <!-- 상영관 목록 출력 -->
                        <c:forEach var="event" items="${eventList}">
                            <tr class="data_row">
                                <td class="t_no p-10">No.${event.event_num}</td>
                                <td class="t_title p-30">${event.event_title}</td>
                                <td class="t_del p-10">
                                    <button class="btn-del"
                                    onclick="delCheck(${event.event_num})">삭제</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                <button class="btn-room" onclick="location.href='./eventInsertFrm'">등록</button>
            </div>
		</div>
	</div>
</div>
</section>
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