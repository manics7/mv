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
	
	var mid = "${userInfo.m_id}";
	var bid = "${bDto.mid}";
	
	if(mid == bid){
		$("#upbtn").show();
		$("#delbtn").show();
	}
});
</script>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<section class="rv_section2">
<div class="rv_content">
	<button id="report_btn">신고하기</button>
    <div id="popupDiv"> <!-- 팝업창 -->
        <button id="popCloseBtn">X</button>
		<h1>게시글 신고 사유 선택</h1>
		<form action="./rpWhyInsert" class="rp_why_form" method="post">
			<input type="checkbox" id="checkmark1" class="checkmark" name="rp_why" value="욕설비방">
			<label for="checkmark1" id="check01">욕설/비방</label>
			<input type="checkbox" id="checkmark2" class="checkmark" name="rp_why" value="음란성">
			<label for="checkmark2" id="check02">음란성</label>
			<input type="checkbox" id="checkmark3" class="checkmark" name="rp_why" value="홍보상업성">
			<label for="checkmark3" id="check03">홍보/상업성</label>
			<input type="checkbox" id="checkmark4" class="checkmark" name="rp_why" value="도배">
			<label for="checkmark4" id="check04">도배</label>
			<input type="checkbox" id="checkmark5" class="checkmark" name="rp_why" value="스포일러">
			<label for="checkmark5" id="check05">스포일러</label>
			<input type="checkbox" id="checkmark6" class="checkmark" name="rp_why" value="기타">
			<label for="checkmark6" id="check06">기타</label>
			<input type="number" name="review_num" value="${bDto.rnum}">
			<input type="text" name="rp_m_id" value="${userInfo.m_id}"><!--신고자 아이디-->
			<input type="text" name="rpt_m_id" value="${bDto.mid}"><!--신고 당하는 아이디-->
			<button type="submit">신고하기</button>
		</form>
    </div>
    <div>${bDto.rnum}</div>
	<div id="rvtitle"><a id="rv_thname">${bDto.th_name}</a> ${bDto.rtitle}</div>
	<div id="rvtitle2">${bDto.mid} | ${bDto.rdate} | ${bDto.rview}</div>
	<hr>
	<div id="rvcontent">${bDto.rcontent}</div>
	
		<!-- 첨부파일이 없을 경우 -->
		<c:if test="${empty bfDto}">
			첨부된 파일이 없습니다.
		</c:if>
		<!-- 첨부파일이 있을 경우 -->
		<c:if test="${!empty bfDto}">
			<!-- 이미지 파일 미리보기 -->
			<c:forEach var="f" items="${bfDto}">
				<img src="${f.review_file_name}" width="100">
			</c:forEach>
		</c:if>
	
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
			<td width="40%">CONTENTS</td>
			<td width="30%">DATE</td>
			<td width="10%">신고 처리</td>
		</tr>
	</table>
	<table id="re_table" style="width: 100%"><!-- 목록 테이블 -->
		<c:forEach var="ritem" items="${reList}">
			<tr>
				<td width="20%">${ritem.mid}</td>
				<td width="40%">${ritem.recontent}</td>
				<td width="30%">
					<fmt:formatDate value="${ritem.redate}"
						pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td width="10%"><button id="report_btn">신고하기</button></td>
			</tr>
		</c:forEach>
	</table>
</div>
</section>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
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
	replyFrm.mid = "${userInfo.m_id}";

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