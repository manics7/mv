<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사업자정보 수정</title>
    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="resource/css/mypage_modifymem.css">
    <style type="text/css">
    button {
	cursor: pointer;
}
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
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
            <!--이부분에 상단매뉴 들어감.-->


        </nav>
        <div class="main_wrap">
            <div class="cont_wrap">
                <div class="cont_sidebar">
                <jsp:include page="mypage_sidebar.jsp"></jsp:include>
                </div>
                <div class="container modify_mem_wrap">
                    <h2 class="modify_mem_title">회원 정보 수정</h2>
                    <div class="profile_box">
                        <div class="imt_box">
                            <img src="resource/images/modify_mem_profile.svg" alt="">
                        </div>
                        <p class="profile_title">${businessInfo.b_id}</p>
                    </div>
                    <div class="member_info">
                        <div class="top">
                            <h3>기본정보</h3>
                            <span class="required">필수항목</span>
                        </div>
                        <ul style="padding-left: 0;">
                            <li>
                                <div>
                                    <span class="required">대표자 이름</span>
                                </div>
                                <div>
                                	${businessInfo.b_name}
                                    <button class="form_btn"
                                        style="width: 68px; height: 40px; padding:  10px; margin-right: 8px;">변경</button>
                                    <input type="hidden" name="" value="">
                                    <input type="hidden" name="" value="">
                                </div>


                            </li>
                            <li>
                                <div>
                                    <span class="required">사업자 등록 번호</span>
                                </div>
                                <div>
                                    <span>${businessInfo.b_num}</span>
                                </div>
                            </li>
                            <li>
                                <div style="width: 110px;">
                                    <span class="required">연락처</span>
                                </div>
                                <span style="margin-right: 8px;">${businessInfo.b_tel}</span>
                                <button class="form_btn" style="width: 68px; height: 40px; padding:  10px;">변경</button>

                            </li>
                            <li>
                                <div>
                                    <span class="required">이메일</span>
                                </div>
                                <div>
                                    <input type="text" name="m_mail" value="${businessInfo.b_email}"> <br>
                                    <p class="t1" id="input_capslock_notice04" style="display: none; color: red;">* Caps
                                        Lock이 눌려 있습니다.</p>
                                </div>
                            </li>
                            <li>
                                <div>
                                    <span>비밀번호</span>
                                </div>
                                <div>
                                    <button class="form_btn"
                                        style="width: 68px; height: 40px; padding:  10px; margin-right: 8px;">변경</button>
                                    <i style="font-style: normal; color: #767676;"></i>
                                </div>
                            </li>
                            <!-- <li class="addrlist">
                                <div><span>주소</span></div>

                                <!-- <div class="addr_form1_wrap">

                                </div> 
                                <div style="width: 680px; height: 151px;">
                                    <input class="addrform_1" type="text" style="width: 100px; margin-right: 8px;">
                                    <button class="form_btn" style="width: 123px; height: 40px; padding: 10px;">우편번호
                                        검색</button>
                                    <div class="addrdiv_2">
                                        <input type="text" id="Addr1" name="Addr1" readonly value="">
                                    </div>
                                    <div class="addrdiv_3">
                                        <input type="text" id="Addr2" name="Addr2" value="">
                                    </div>
                                </div>
                            </li> -->
                        </ul>
                    </div>
                    <div class="simple_login" style="display: block;">
                        <h3 class="simple_login_title">간편로그인 계정 연동</h3>
                        <table class="simple_login_table">
                            <thead style="background-color: #f4f4f4;">
                                <th style="width: 15%;">구분</th>
                                <th style="width: auto;">연동정보</th>
                                <th style="width: 15%;">연결</th>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>네이버</td>
                                    <td>연결된 계정정보가 없습니다.</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="btn_box">
                        <button onclick="businessUpdate()" class="btn_on" style="color: white;">변경</button>
                    </div>


                </div>
            </div>
        </div>
    </div>


    <!-- 이부분에 푸터부분 들어감 -->

    <div class="footer_wrap">
        <footer>

        </footer>

    </div>
</body>
<script type="text/javascript">
function businessUpdate(){
	var conf = confirm("수정 하시겠습니까?");
	
	if(conf == true){
		location.href='./businessUpdateProc';
	}
}
</script>
</html>