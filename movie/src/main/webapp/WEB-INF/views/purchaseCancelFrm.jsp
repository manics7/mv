<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예매 취소내역</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="resource/css/mmanagestyle/mmanage.css">

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
                    <div class="member_top_btn_wrap">
							<div class="member_top_btn">
								<a class="btn_nomal" 
									href ="purchaseFrm">예매내역</a> 
									<a class="busbtn" href ="purchaseCancelFrm" style="background: #f16a1a; color: white;">예매취소내역</a>
							</div>



						</div>
                        <!--
    
    <div>
        <h4>회원정보로 검색한 작성 글</h4>
        ${mbLIst}
    </div>
    -->
                        <div class="card-body qqueboard_con">
                            <h4 class="card-title">예매내역</h4>
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
                                        <th class="text-center">극장명</th>
                                        <th class="text-center">영화명</th>
                                        <th class="text-center">예매번호</th>
                                        <th class="text-center">예매일</th>
                                        <th class="text-center">가격</th>
                                        
                                        <!--
                                        
                                        <th class="text-center">답변하기</th>
                                        
                                          -->
                                        
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- 이부분은 검색 결과 출력되는 부분 -->
                                   

<c:choose>
						<c:when test="${not empty qList}">
						<c:forEach var="qitem" items="${qList}">
								<tr>
									<td class="text-center d-none d-md-table-cell">${qitem.thname}</td>
									<td class="text-center d-none d-md-table-cell">${qitem.mvname}</td>
									<td class="text-center d-none d-md-table-cell">${qitem.rsrv_no}</td>
									<td class="text-center d-none d-md-table-cell"><fmt:formatDate value="${qitem.rsrv_date}"
										pattern="yyyy-MM-dd"/></td>
									<td class="text-center d-none d-md-table-cell">
									${qitem.price}
									</td>
								</tr>
									
					
							</c:forEach>
						</c:when>
						<c:otherwise>
						<td class="text-center d-none d-md-table-cell">예매 내역이 없습니다.</td>
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