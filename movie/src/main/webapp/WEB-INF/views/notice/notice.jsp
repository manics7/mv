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
<style type="text/css">
a {
	text-decoration: none !important;
	color: black;
}
</style>
<script type="text/javascript">


$(document).ready(function() {

	fnObj.getNotice();
	
	
	$("#searchParam").keydown(function(e) {
		  if (e.keyCode === 13) {
		    e.preventDefault();
		  };
		});

	
});



var fnObj = {
		 searchParam : "",	
		 page : {
			pageNo : 0
			,pageCnt : 5 // 보여줄 최대 페이지 갯수
			,size : 10
			,sort :  "noticeNo,desc"
		},
		 getNotice : function(pageNo) {
			var paramObj = {	
					searchParam : $("#searchParam").val()
					,page : pageNo || fnObj.page.pageNo
					,size : fnObj.page.size
					,sort : fnObj.page.sort
			}
			var params = $.param(paramObj);
			console.log("params = " +params);
			$.ajax({
				type : "GET",
				url : "/getNotice?"+params,	       		
				success : function(res) {
					console.log(res);
					var list = res.content;
					var pageHtml = "";
					var listHtml = "";
					var pageCnt = res.totalPages <fnObj.page.pageCnt ? res.totalPages : fnObj.page.pageCnt;
					var currentPage = res.number == 0 ? 1 : res.number+1;  // pageable은 인덱스0부터 하지만 페이지번호는 1부터 출력
					var pageGroup =   Math.ceil(currentPage / pageCnt) < 0 
												? Math.ceil(currentPage / pageCnt+1) : Math.ceil(currentPage / pageCnt) // 페이지 그룹
			
					//console.log("currentPage = " + currentPage);
					//console.log("pageGroup = " + pageGroup);
					
			
					var start = (pageGroup * pageCnt) - (pageCnt - 1);					
					var end  = pageGroup * pageCnt >= res.totalPages ? res.totalPages : pageGroup * pageCnt;
								
					console.log("start = " + start);
					console.log("end = " + end);
										
					//게시판
					for(var i = 0; i<res.content.length; i++ ){						
						listHtml += "<tr onclick='noticeWriteFrm(" + list[i].noticeNo + " )' style='cursor:pointer'>";
						listHtml += "<td>"+list[i].noticeNo+"</td>";
						listHtml += "<td>"+list[i].noticeTitle+"</td>";
						listHtml += "<td>"+list[i].noticeContent+"</td>";
						listHtml += "<td>"+list[i].noticeClass + "</td>";
						listHtml += "<td>"+list[i].regDate +"</td>";
						listHtml += "<td>"+list[i].viewCnt +"</td>";						
						listHtml += "</tr>";
					}					
					
					$("tbody").html(listHtml);					
					
					
					//페이지번호
					if(start-1 > 0){
						pageHtml +="<li class='page-item'><a class='page-link' href='javascript:fnObj.getNotice("+parseInt(start-2)+")'>이전</a></li>";
					}	
										
					for(var i = start; i<=end; i++){
						if(i == currentPage){	
							pageHtml += "<li class='page-item active'><a class='page-link' onclick='javascript:fnObj.getNotice("+parseInt(i-1)+")'>"+i+"<span class='sr-only'>(current)</span></a></li>";
						}else{
							pageHtml += "<li class='page-item'><a class='page-link' href='javascript:fnObj.getNotice("+parseInt(i-1)+")'>"+i+"</a></li>";
						}						
					}
					
					if(res.totalPages > end){
						pageHtml +="<li class='page-item'><a class='page-link' href='javascript:fnObj.getNotice("+parseInt(end)+")'>다음</a></li>";
					}
					
					$("#paging").html(pageHtml);
				
				},
				err : function(err) {
					console.log("err:", err)
				},
			});
		}		
	}




function noticeWriteFrm(noticeNo){
	location.href="noticeWriteFrm?noticeNo="+noticeNo
}


</script>
</head>


<!--  nav  -->

<nav style="padding-top: 35px; padding-bottom: 35px; background: #1d1d1d;">
<jsp:include page="../admin_header.jsp"></jsp:include>


</nav>
<!--  /nav  -->


<!--  1차 wrap  -->
<div class="main_wrap" style="width: 100%; margin-top: 60px;">
	<div class="cotainer_wrap"
		style="display: flex; justify-content: space-around; margin-top: 60px; width: 1024px; margin: 0 auto;">
		<div class="cont_sidebar">
			<jsp:include page="../adminpage_sidebar.jsp" />
		</div>


		<div class="container">
			<div class="row" style="min-height: 600px;">
				<div class="col-md-11 ml-auto">
					<h5 class="text-center mt-3">공지사항</h5>
					<form class="form-inline d-flex justify-content-end" method="GET" action="saveNotice">
						<div class="form-group mx-sm-3 mb-2">

							<label for="searchText" class="sr-only">검색</label> <input type="text"
								class="form-control" id="searchParam" name="searchText"
							>
						</div>
						<button type="button" class="btn btn-secondary mb-2"
							onclick="javascript:fnObj.getNotice();"
						>검색</button>
					</form>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">번호</th>
								<th scope="col">제목</th>
								<th scope="col">내용</th>
								<th scope="col">구분</th>
								<th scope="col">등록일</th>
								<th scope="col">조회수</th>
							</tr>
						</thead>
						<tbody>


						</tbody>
					</table>
					<nav aria-label="..." class="text-right">
						<a href="noticeWriteFrm" class="btn btn-secondary mb-2 text-right" id="write">글쓰기</a>
						<ul id="paging" class="pagination justify-content-center"></ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
</div>

	<footer style="margin-top:134px ">
			<jsp:include page="../footer.jsp"></jsp:include>
		</footer>

</body>
</html>