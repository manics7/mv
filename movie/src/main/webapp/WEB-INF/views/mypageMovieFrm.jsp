<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 본 영화</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="resource/css/queboard/queboard.css">




<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	//state == 1 ? state.innerHtml("답변완료") : state.innerHtml("미완료");
</script>

</head>
<body>
    <div class="wrap">
        <nav>
        <jsp:include page="mypage_header.jsp"></jsp:include>
        </nav>


        <div class="main_wrap">
            <!-- 상단 메뉴 부분 -->
            <div class="cont_wrap">
                <!-- 게시글 리스트 -->
                <div class="cont_sidebar">
                <jsp:include page="mypage_sidebar.jsp"/>
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
                            			
				<div class="card-body">
					<h4 class="card-title">내가 본 영화</h4>
					
				</div>
		
                           <table class="table table-hover" id='board_list'>
						<thead>
							<tr>
							<!-- 	<th class="text-center d-none d-md-table-cell">번호</th> -->
								<th class="text-center d-none d-md-table-cell">영화명</th>
								<th class="text-center d-none d-md-table-cell">극장명</th>
								<th class="text-center d-none d-md-table-cell">날짜</th>
								
							</tr>
						</thead>
						<tbody>
						<!-- 검색 처리 -->
						<c:choose>
							<c:when test="${not empty qList}">
								<c:forEach var="qitem" items="${qList}">
									<tr>
							<!-- 	<td class="text-center d-none d-md-table-cell">${mvritem.mv_review}</td> -->
										<td class="text-center d-none d-md-table-cell">${qitem.mvname}</td>
									<td class="text-center d-none d-md-table-cell">${qitem.thname}</td>
									<td class="text-center d-none d-md-table-cell"><fmt:formatDate value="${qitem.sch_date}"
										pattern="yyyy-MM-dd"/>
									</td>
									</tr>
								</c:forEach>
						</c:when>
						<c:otherwise>
							<td class="text-center d-none d-md-table-cell">감상한 영화가 없습니다.</td>
						</c:otherwise>
						
						</c:choose>
						
						
							<!-- <c:forEach var="mitem" items="${mseList}">
								<tr>
									<td><c:if test="${mseList == 'null'}">존재 하지 않는 회원 입니다.</c:if></td>
									<td class="text-center d-none d-md-table-cell">${mitem.m_id}</td>
									<td><a href='board_read.html'>${mitem.m_name}</a></td>
									<td class="text-center d-none d-md-table-cell">${mitem.m_phone}</td>
									<td class="text-center d-none d-md-table-cell">${mitem.m_addr}</td>
									<td class="text-center d-none d-md-table-cell">${mitem.m_birth}</td>
									<td class="text-center d-none d-md-table-cell"><a type="button" href="./mboardSelect?m_id=${mitem.m_id}">확인</a></td>
									<td class="text-center d-none d-md-table-cell"><a
										href="./deleteMember">삭제</a></td>
								</tr>
							</c:forEach>
							  -->
							


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