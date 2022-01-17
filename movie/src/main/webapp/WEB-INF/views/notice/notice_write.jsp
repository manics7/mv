<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="stylesheet" href="resource/css/bootstrap.css">
<script src="resource/js/jquery-3.6.0.min.js"></script>
<script src="resource/js/jquery.serializeObject.js"></script>
<script src="resource/js/bootstrap.bundle.js"></script>
<script type="text/javascript">





function deleteNotice(noticeNo){
	var conf = confirm("삭제하시겠습니까?");	
	if(conf == true){
	location.href="deleteNotice?noticeNo="+noticeNo;
	}
}

</script>

</head>
<!--  nav  -->
<nav style="padding-top: 35px; padding-bottom: 35px; background: black;">
	<jsp:include page="../admin_header.jsp"></jsp:include>
</nav>
<!--  /nav  -->


<div class="main_wrap" style="width: 100%; margin-top: 60px;">
	<div class="cotainer_wrap"
		style="display: flex; justify-content: space-around; margin-top: 60px; width: 1024px; margin: 0 auto;"
	>
		<div class="cont_sidebar">
			<jsp:include page="../adminpage_sidebar.jsp" />
		</div>

		<div class="container">
			<div class="row">
				<div class="col-md-11 ml-auto">
					<form action="saveNotice" method="post">

						<input type="hidden" name="noticeNo" value="${notice.noticeNo}">

						<div class="form-group">
							<label for="title">게시글번호</label> <input type="text" class="form-control"
								id="noticeTitle" value="${notice.noticeTitle }" readonly="readonly"
							> <label for="title">제목</label> <input type="text" class="form-control"
								id="noticeTitle" name="noticeTitle" value="${notice.noticeTitle }"
							> <label for="title">구분</label> <select class="form-control" name="noticeClass"
								id="noticeClass"
							>
								<option value="극장">극장</option>
								<option value="이벤트">이벤트</option>
								<option value="기타">기타</option>
								<option value="시스템점검">시스템점검</option>
							</select>
						</div>
						<div class="form-group">
							<label for="exampleFormControlTextarea1">공지내용</label>
							<textarea class="form-control" id="exampleFormControlTextarea1"
								name="noticeContent" rows="10"
							>${notice.noticeContent}</textarea>
						</div>
						<div class="form-group text-center">
							<a class="btn btn-primary" href="notice">뒤로가기</a>
							<button type="submit" class="btn btn-primary">저장</button>
							<c:if test="${notice.noticeNo !=null}">
								<button type="button" class="btn btn-primary"
									onclick="deleteNotice(${notice.noticeNo})"
								>삭제</button>
							</c:if>

						</div>
					</form>
				</div>
			</div>

		</div>
	</div>
</div>
<footer>
	<jsp:include page="../footer.jsp"></jsp:include>
</footer>
<script type="text/javascript">

$(document).ready(function() {
	
	var noticeClass = '${notice.noticeClass}'
	if(noticeClass.length > 0){
		$("#noticeClass").val(noticeClass).prop("selected", true);
	}
	
	
});

</script>
</body>
</html>