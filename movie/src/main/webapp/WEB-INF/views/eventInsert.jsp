<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 등록</title>
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
                <h3>이벤트 등록</h3>
				<form action="">
					<input type="hidden" name="e_num">
					<input type="hidden" name="th_code">
					<a>이벤트 제목</a> <input type="text" name="e_title" class="e_title"><br>
					<a class="e_text">이벤트 내용</a><textarea></textarea><br>
					<a>이벤트 기간</a> <input type="date" name="e_start"> - <input type="date" name="e_end"><br>
					<!-- <a>이벤트 대상</a> <input type="text" name="e_target"><br> -->
					<a>이벤트 종류</a>
					<select name="e_category">
						<option value="청소년 할인">청소년 할인</option>
						<option value="국가 유공자 할인">국가 유공자 할인</option>
						<option value="수능 할인">수능 할인</option>
						<option value="장애인 할인">장애인 할인</option>
					</select><br>
					<a>이벤트 할인율</a> <input type="text" name="e_sale"><br>
					<input type="file" name="e_file" class="e_file">
					<input type="submit" class="e_sub_btn" value="등록">
				</form>
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