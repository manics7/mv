<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QUESTION BOARD</title>
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
                        
                            <h4 style="display:flex;  justify-content: space-between;" class="card-title">1:1문의 <button style="background: #f16a1a; color: white; border: none; outline: none; cursor: pointer; padding: 4px; font-size: 14px; margin-right: 50px;">글쓰기</button>  </h4>
                            
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
                                        <th class="text-center">글번호</th>
                                        <th class="text-center">제목</th>
                                        <th class="text-center">문의유형</th>
                                        <th class="text-center">작성날짜</th>
                                        <th class="text-center">상태</th>
                                        
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
									<td class="text-center d-none d-md-table-cell">${qitem.ques_no}</td>
									<td class="text-center d-none d-md-table-cell"><a
                                                    href='/questionContents?ques_no=${qitem.ques_no}'>${qitem.ques_title}</a></td>
									<td class="text-center d-none d-md-table-cell">일반</td>
									<td class="text-center d-none d-md-table-cell"><fmt:formatDate value="${qitem.ques_date}"
										pattern="yyyy-MM-dd"/></td>
									<td class="text-center d-none d-md-table-cell">
									<c:choose>
										<c:when test="${qitem.ques_state == '0'}">
											미답변
										</c:when>
										<c:when test="${qitem.ques_state == '1'}">
											답변완료
										</c:when>
									</c:choose>
									</td>
								</tr>
									<c:if test="${qitem.ques_state == '1'}">
													<tr style="display: flex; justify-content: flex-start; padding-left: 150px; overflow: none;">
								<td class="text-center d-none d-md-table-cell" style="height: 50px; line-height: 26px; width: 500px; text-align: left;">
								
								<a href="memReadQuesRe?ques_no=${qitem.ques_no}">🌝${qitem.ques_reply_title}</a>   </td>
								</tr>
										</c:if>
					
							</c:forEach>
						</c:when>
						<c:otherwise>
						<td class="text-center d-none d-md-table-cell">문의 내역이 없습니다.</td>
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