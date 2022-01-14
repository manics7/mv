<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 영화 등록</title>
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
                            <h4 class="card-title">관리자 영화 등록</h4>
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
                                        <th class="text-center">영화코드</th>
                                        <th class="text-center">영화이름</th>
                                        <th class="text-center">제작년도</th>
                                        <th class="text-center">상태</th>
                                        
                                        <!--
                                        
                                        <th class="text-center">답변하기</th>
                                        
                                          -->
                                        
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- 이부분은 검색 결과 출력되는 부분 -->
                                   
<c:choose>
						<c:when test="${not empty mvList}">
						<c:forEach var="mvitem" items="${mvList}">
								<tr>
									<td class="text-center d-none d-md-table-cell">${mvitem.movie_cd}</td>
									<td class="text-center d-none d-md-table-cell">
									<a href='/admin_movie_read?mv_seq=${mvitem.mv_seq}'>${mvitem.movie_nm}</a>
									</td>
									<td class="text-center d-none d-md-table-cell"><fmt:formatDate value="${mvitem.open_dt}"
										pattern="yyyy-MM-dd"/></td>
							
									<td class="text-center d-none d-md-table-cell">
									<c:choose>
										<c:when test="${mvitem.state == '0'}">
											미등록
										</c:when>
										<c:when test="${mvitem.state == '1'}">
											등록완료
										</c:when>
									</c:choose>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
						<td class="text-center d-none d-md-table-cell">영화 요청이 없습니다.</td>
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