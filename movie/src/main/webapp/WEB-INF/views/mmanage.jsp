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
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>

<body>


	<div class="wrap">
		<nav>
			<jsp:include page="admin_header.jsp"></jsp:include>
		</nav>
		<div class="main_wrap">
			<div class="cont_wrap">
				<div class="cont_sidebar">
					<jsp:include page="adminpage_sidebar.jsp" />
				</div>
				<div class="container queboard">
					<div class="card shadow">
						<div class="member_top_btn_wrap">
							<div class="member_top_btn">
								<a class="btn_nomal" style="background: #f16a1a; color: white;"
									href="./mmanage?pageNum=1">일반회원</a> 
									<a href="#" class="btn_nomal">불량회원</a> 
									<a class="busbtn" href="./getBulist?pageNum=1">사업자</a>
									<button onclick="deletemember()">test</button>
							</div>



						</div>
						<form id="nomalmemberform" name="searchFrm" action="./memberSelect?pageNum=1"
							method="post">
							<div class="card-body">


								<div class="member_top">
									<h4 class="card-title yellow underline">회원관리</h4>
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
											<th class="text-center d-none d-md-table-cell">경고횟수</th>
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
														<td class="text-center d-none d-md-table-cell"><a href='javascript:void(0);' onclick="findInfo('${mitem.m_id}','${mitem.m_tel}','${mitem.m_name}','${mitem.m_birth}','${mitem.m_warning}')">${mitem.m_id}</a>  
														</td>
														<td><a href='board_read.html'>${mitem.m_name}</a></td>
														<td class="text-center d-none d-md-table-cell">${mitem.m_tel}
														</td>
														<td class="text-center d-none d-md-table-cell">${mitem.m_birth}
														</td>
														<td class="text-center d-none d-md-table-cell">${mitem.m_warning}</td>

														<td class="text-center d-none d-md-table-cell"><a
															type="button" href="./mboardSelect?m_id=${mitem.m_id}">확인</a></td>

															
														<td class="text-center d-none d-md-table-cell"><a class=""
															href="#" onclick="deletemember('${mitem.m_id}')">삭제</a></td>
													</tr>
<!-- ./admindelMember?m_id=${mitem.m_id} -->
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

									</tbody>
								</table>

								<div class="d-none d-md-block">
									<ul class="pagination justify-content-center">
										<li class="page-item">${paging}</li>
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



	<div class="footer_wrap">
		<footer>
			<!-- 이부분에 푸터부분 들어감 -->
		</footer>

	</div>
</body>
<script defer type="text/javascript">
/*
		getbulist();
		
		function getbulist(){
			$.ajax({
				type : "POST",
				url : 
			})
		}
 
 
 */

	
 function findInfo(m_id, m_tel, m_name, m_birth, m_warning){
	var m_id = m_id;
	var m_tel = m_tel;
	var m_ame = m_name;
	var m_birth = m_birth;
	var m_warning = m_warning;
	
		swal({
			title : "유저 정보",
			text : "\n유저 아이디 :" + m_id +  "\n\n유저 이름 :" + m_name + "\n\n유저 HP :"+m_tel +"\n\n경고 횟수 :"+m_warning ,			
			icon : "info",
			confirmButtonText: true,
			className: "red-bg",
			});
	}
 
 
 function deletemember(m_id) {
	 var m_id = m_id;
	 swal({
		  title: "정말 삭제 하시겠습니까?",
		  text: "OK버튼을 누르시면" + m_id + "회원이 삭제됩니다",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then(function () {       
    $.ajax({
        type: "GET",
        url: "./admindelMember?m_id="+m_id,
        cache: false,
        success: function(response) {
            swal(
            "Sccess!",
            "Your note has been saved!",
            "success"
            )
        },
        failure: function (response) {
            swal(
            "Internal Error",
            "Oops, your note was not saved.", // had a missing comma
            "error"
            )
        }
    });
 }
		}
 /*
 /alDeleteMem"
  function deletemember() {
	 swal({
		  title: "정말 삭제 하시겠습니까?",
		  text: "OK버튼을 누르시면 해당 회원이 삭제됩니다",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		
 }
	
 
 .then((willDelete) => {
	
				$.ajax({
					type: "GET"
					,url: "/alDeleteMem"
					,success:
						   swal("해당 회원이 삭제 되었습니다.", {
							      icon: "success",
							    }),error :swal("삭제를 취소 하였습니다.")
				}),

		})
 
 
 
 */
	


/*
function movebulist(pageNum){
	console.log(pageNum);
	
	var movebulist = $("#nomalmemberform").serializeObject();
	
	$.ajax({
		url:"/getBulist",
		type :"post",
		data:movebulist,
		dataType: "json",
		success: function(data){ //data에 사업자폼 적제
			var dlist = "";
			var getbulist = data.getbulist; //받아온 데이터를 data에 적제 
			dlist = "<form>" 
			$("#nomalmemberform").html()
		}

	})
}

*/

</script>
</html>