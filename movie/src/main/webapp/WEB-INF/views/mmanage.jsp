<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>MEMBER MANAGE</title>
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
</head>

<body>


	<div class="wrap">
		<nav>
			<!--이부분에 상단매뉴 들어감.-->
		</nav>
		<div class="main_wrap">
			<div class="cont_wrap">
				<div class="cont_sidebar"></div>
				<div class="container queboard">
					<div class="card shadow">
						<form name="searchFrm" action="./memberSelect?pageNum=1"
							method="post">
							<div class="card-body">
								<div class="member_top_btn_wrap">
									<div class="member_top_btn">
										<button onClick="./mmanage?pageNum=1">일반회원</button>
										<button>불량회원</button>
										<button onClick="./getBulist?pageNum=1">사업자</button>
									</div>

								</div>

								<div class="member_top">
									<h4 class="card-title">회원관리</h4>
									<div class="input_box">
										<input type="text" placeholder="ID 입력" name="m_id"> <input
											type="submit" value="검색">
									</div>

								</div>

								<table class="table table-hover" id='board_list'>
									<thead>
										<tr>
											<th class="text-center d-none d-md-table-cell">회원ID</th>
											<th class="text-center d-none d-md-table-cell">이름</th>
											<th class="text-center d-none d-md-table-cell">HP</th>

											<th class="text-center d-none d-md-table-cell">생년월일</th>
											<th class="text-center d-none d-md-table-cell">신고횟수</th>
											<th class="text-center d-none d-md-table-cell">회원 게시글 확인</th>
											<th class="text-center d-none d-md-table-cell">회원 탈퇴처리</th>
										</tr>
									</thead>
									<tbody>
										<!-- 검색 처리 -->
										<c:choose>

											<c:when test="${not empty mList}">
												<c:forEach var="mitem" items="${mList}">
													<tr>
														<td class="text-center d-none d-md-table-cell">${mitem.m_id}
														</td>
														<td><a href='board_read.html'>${mitem.m_name}</a></td>
														<td class="text-center d-none d-md-table-cell">${mitem.m_tel}
														</td>
														<td class="text-center d-none d-md-table-cell">${mitem.m_birth}
														</td>
														<td class="text-center d-none d-md-table-cell">0</td>
														
														<td class="text-center d-none d-md-table-cell"><a
															type="button" href="./mboardSelect?m_id=${mitem.m_id}">확인</a></td>
														<td class="text-center d-none d-md-table-cell"><a
															href="./deleteMember">삭제</a></td>
													</tr>

												</c:forEach>
											</c:when>
											<c:otherwise>
												<td class="text-center d-none d-md-table-cell">일치하는 회원
													정보가 없습니다.</td>
											</c:otherwise>

										</c:choose>

										<!--  

                               <c:forEach var="mitem" items="${mseList}">
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


										<!--  -->
									</tbody>
								</table>

								<div class="d-none d-md-block">
									<ul class="pagination justify-content-center">
										<li class="page-item">${paging}</li>
									</ul>
								</div>

								<div class="d-block d-md-none">
									<ul class="pagination justify-content-center">
										<li class="page-item"><a href="#" class="page-link">이전</a></li>
										<li class="page-item"><a href="#" class="page-link">다음</a></li>
									</ul>
								</div>
							</div>

						</form>

					</div>

				</div>
			</div>
		</div>


	</div>
	<!-- 상단 메뉴 부분 -->


	<!-- 게시글 리스트 -->
	</div>



	<div class="footer_wrap">
		<footer>
			<!-- 이부분에 푸터부분 들어감 -->
		</footer>

	</div>
</body>

</html>