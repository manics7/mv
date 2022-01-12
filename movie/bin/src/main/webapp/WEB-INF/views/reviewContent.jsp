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
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="resource/css/review.css">
</head>
<body>
<section>
<h2 id="rv_title">REVIEW</h2>
<div class="rv_content">
	<button id="report_btn">신고하기</button>
    <div id="popupDiv"> <!-- 팝업창 -->
        <button id="popCloseBtn">X</button>
		<h1>게시글 신고 사유 선택</h1>
		<form action="./reportRvInsert" class="reportRv_form" method="post">
			<input type="checkbox" id="checkmark1" class="checkmark" name="" value="욕설/비방">
			<label for="checkmark1" id="check01">욕설/비방</label>
			<input type="checkbox" id="checkmark2" class="checkmark" value="음란성">
			<label for="checkmark2" id="check02">음란성</label>
			<input type="checkbox" id="checkmark3" class="checkmark" value="홍보/상업성">
			<label for="checkmark3" id="check03">홍보/상업성</label>
			<input type="checkbox" id="checkmark4" class="checkmark" value="도배">
			<label for="checkmark4" id="check04">도배</label>
			<input type="checkbox" id="checkmark5" class="checkmark" value="스포일러">
			<label for="checkmark5" id="check05">스포일러</label>
			<input type="checkbox" id="checkmark6" class="checkmark" value="기타">
			<label for="checkmark6" id="check06">기타</label>
			<button type="submit">신고하기</button>
		</form>
    </div>
    <div>${bDto.rnum}</div>
	<div id="rvtitle">${bDto.rtitle}</div>
	<div id="rvtitle2">${bDto.mid} | ${bDto.rdate} | ${bDto.rview}</div>
	<hr>
	<div id="rvcontent">${bDto.rcontent}</div>
	<div class="btn-area">
		<button id="likebtn">LIKE<br>${bDto.rlike}</button>
		<button class="btn-write" id="upbtn"
			onclick="location.href='./updateRvFrm?rnum=${bDto.rnum}'">수정</button>
		<button class="btn-write" id="delbtn"
			onclick="delCheck(${bDto.rnum})">삭제</button>
		<button class="btn-write" id="backbtn"
			onclick="location.href='./rlist?pageNum=${pageNum}'">돌아가기</button>
	</div>
	<!-- 댓글 작성 양식 -->
	<form id="reFrm">
		<textarea rows="3" class="write-input ta"
			name="recontent" id="comment"
			placeholder="댓글을 적어주세요."></textarea>
		<a id="reply_count">0 / 1000byte</a>
		<input type="button" value="댓글 전송" id="reply_post_btn"
			class="btn-write" onclick="replyInsert(${bDto.rnum})">
	</form>
	<!-- 댓글 목록 보기 -->
	<hr>
	<table style="width: 100%"><!-- 제목 테이블 -->
		<tr bgcolor="pink" align="center" height="30">
			<td width="20%">WRITER</td>
			<td width="50%">CONTENTS</td>
			<td width="30%">DATE</td>
		</tr>
	</table>
	<table id="re_table" style="width: 100%"><!-- 목록 테이블 -->
		<c:forEach var="ritem" items="${reList}">
			<tr>
				<td width="20%">${ritem.mid}</td>
				<td width="50%">${ritem.recontent}</td>
				<td width="30%">
					<fmt:formatDate value="${ritem.redate}"
						pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</section>
</body>

<script>
    $(document).ready(function(){
        
        $("#report_btn").click(function(event){  //팝업 Open 버튼 클릭 시 
             $("#popupDiv").css({
                "top": (($(window).height()-$("#popupDiv").outerHeight())/2+$(window).scrollTop())+"px",
                "left": (($(window).width()-$("#popupDiv").outerWidth())/2+$(window).scrollLeft())+"px"
                //팝업창을 가운데로 띄우기 위해 현재 화면의 가운데 값과 스크롤 값을 계산하여 팝업창 CSS 설정
             }); 
            $("#popupDiv").css("display","block"); //팝업창 display block
            
            $("body").css("overflow","hidden");//body 스크롤바 없애기
        });
        
        $("#popCloseBtn").click(function(event){
            $("#popup_mask").css("display","none"); //팝업창 뒷배경 display none
            $("#popupDiv").css("display","none"); //팝업창 display none
            $("body").css("overflow","auto");//body 스크롤바 생성
        });
    });

    $(document).ready(function() {
 
	$('input[type="checkbox"][class="checkmark"]').click(function(){
		if($(this).prop('checked')){
			$('input[type="checkbox"][class="checkmark"]').prop('checked',false);
			$(this).prop('checked',true);
			}
		});
	});
</script>

<script src="resource/js/jquery.serializeObject.js"></script>
<script type="text/javascript">
function replyInsert(rnum) {
	console.log(rnum);
	
	var replyFrm = $("#reFrm").serializeObject();
	replyFrm.rnum = rnum;
	replyFrm.mid = "viu97";

	$.ajax({
		url: "replyIns",
		type: "post",
		data: replyFrm,
		dataType: "json",
		success: function(result){
			var dlist = "";
			var replyList = result.reList;
			console.log(replyList);
			
			for(var i = 0; i < replyList.length; i++) {
				dlist += "<tr>"
					+ "<td width='20%'>" + replyList[i].mid + "</td>"
					+ "<td width='50%'>" + replyList[i].recontent + "</td>"
					+ "<td width='30%'>" + replyList[i].redate + "</td>"
					+ "</tr>";
			}
			
			$("#re_table").html(dlist);
			$("#comment").val("");
		},
		error: function(error){
			console.log(error);
			alert("댓글 입력 실패");
		}
	});
}

function delCheck(rnum){
	var conf = confirm("삭제하시겠습니까?");
	
	if(conf == true){
		location.href='./deleteRv?rnum=' + rnum;
	}
}
</script>
</html>