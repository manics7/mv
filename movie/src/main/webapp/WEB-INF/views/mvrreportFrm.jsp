<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고관리</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="resource/css/queboard/queboard.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	//메시지 출력 부분
	var msg = "${msg}";
	if(msg != ""){
		alert(msg);
	});
</script>
</head>
<body>
    <div class="wrap">
        <nav>
        <jsp:include page="admin_header.jsp"></jsp:include>
        </nav>


        <div class="main_wrap">
            <!-- 상단 메뉴 부분 -->
            <div class="cont_wrap">
                <!-- 게시글 리스트 -->
                <div class="cont_sidebar">
                <jsp:include page="adminpage_sidebar.jsp"/>
                </div>
                <div class="container queboard">
                    <div class="card shadow">
                        <!--
    
    <div>
        <h4>회원정보로 검색한 작성 글</h4>
        ${mbLIst}
    </div>
    -->
                        <div class="card-body qqueboard_con">
                            <h4 class="card-title">관리자 신고관리</h4>
                            <table class="table table-hover" id='board_list'>
                                <thead>
                                    <!-- <tr>
                                        <th class="text-center d-none d-md-table-cell">글번호</th>
                                        <th class="text-center d-none d-md-table-cell">제목</th>
                                        <th class="text-center d-none d-md-table-cell">작성날짜</th>
                                        <th class="text-center d-none d-md-table-cell">진행사항</th>
                                        <th class="text-center d-none d-md-table-cell">작성자</th>
                                        <th class="text-center d-none d-md-table-cell">답변하기</th>
                                    </tr> -->
                                    <tr>
                                        <th class="text-center">신고자ID</th>
                                        <th class="text-center">신고 사유</th>
                                        <th class="text-center">내용</th>
                                        <th class="text-center">작성자ID</th>
                                        <th class="text-center">신고일</th>
                                        <th class="text-center">처리상태</th>
                                        
                                        <!--
                                        
                                        <th class="text-center">답변하기</th>
                                        
                                          -->
                                        
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- 이부분은 검색 결과 출력되는 부분 -->
                                   
<c:choose>
						<c:when test="${not empty rpList}">
							<c:forEach var="rpitem" items="${rpList}">
							<tr>
								<td class="text-center d-none d-md-table-cell">${rpitem.rp_m_id}</td>
									<td class="text-center d-none d-md-table-cell">${rpitem.rp_why}</td>
									<td class="text-center d-none d-md-table-cell">${rpitem.rp_contents}</td>
									<td class="text-center d-none d-md-table-cell">${rpitem.rpt_m_id}</td>
									<td class="text-center d-none d-md-table-cell"><fmt:formatDate
											value="${rpitem.rp_date}" pattern="yyyy-MM-dd" /></td>
									<td class="text-center d-none d-md-table-cell">
								<c:choose> 
									<c:when test="${rpitem.rp_state == '0'}">
										<button id="btn1" onclick="delCheck(${rpitem.movie_review})">미처리</button>
									</c:when> 
									<c:otherwise>
										처리완료
									</c:otherwise> 
								</c:choose> 
								
									</td>

								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
						<td class="text-center d-none d-md-table-cell">신고 요청이 없습니다.</td>
						</c:otherwise>
						
						</c:choose>
                                </tbody>
                            </table>

                            <div class="d-none d-md-block">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item">${paging}</li>
                                </ul>
                            </div>
<!--  
  <div class="d-block d-md-none">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item"><a href="#" class="page-link">이전</a></li>
                                    <li class="page-item"><a href="#" class="page-link">다음</a></li>
                                </ul>
                            </div>

-->
                          
                            <!-- href="resource/css/home.css" -->
                        </div>
                    </div>
                </div>

            </div>
        </div>

    </div>
    <div class="footer_wrap">
        <footer></footer>

    </div>






</body>
</html>