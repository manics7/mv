<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화관 수정</title>
<link rel="stylesheet" href="resource/css/theaterList.css">
<link rel="stylesheet" href="resource/css/theater.css">
<script src="resource/js/jquery-3.6.0.min.js"></script>
<script src="resource/js/jquery.serializeObject.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	//'로고' 파일을 선택할 경우
	$("#logoFile").on("change", function() {
		//로고 사진 파일 입력창에서 선택한 파일의 목록 가져오기
		var Lfiles = $("#logoFile")[0].files;
		console.log(Lfiles);
		
		var LfileName = "";
		
		//'파일선택' 부분에 파일 이름 표시
		if(Lfiles.length > 1) {//한 개 이상의 파일을 선택할 경우
			LfileName = Lfiles[0].name + " 외 " + (Lfiles.length - 1) + "개";
		} else if (Lfiles.length == 1) {
			LfileName = Lfiles[0].name;
		}
		
		$(".logo-name").val(LfileName);
		
		//파일이 선택 됐는지 안 됐는지 체크하는 부분
		if(LfileName == "") {//취소할 경우
			$("#logo-check").val(0);
			$(".logo-name").val("파일선택");
		} else {//파일을 선택할 경우
			$("#logo-check").val(1);
		}
		
	});
	
	//'영화관 사진' 파일을 선택할 경우
	$("#theaterFile").on("change", function() {
		//영화관 사진 파일 입력창에서 선택한 파일의 목록 가져오기
		var Tfiles = $("#theaterFile")[0].files;
		console.log(Tfiles);
		
		var TfileName = "";
		
		//'파일선택' 부분에 파일 이름 표시
		if(Tfiles.length > 1) {//하나 이상의 파일을 선택할 경우
			TfileName = Tfiles[0].name + " 외 " + (Tfiles.length - 1) + "개";
		} else if(Tfiles.length == 1) {
			TfileName = Tfiles[0].name;
		}
		
		$(".theater-name").val(TfileName);
		
		//파일이 선택 됐는지 안 됐는지 체크하는 부분
		if(TfileName == "") {//취소할 경우
			$("#theater-check").val(0);
			$(".theater-name").val("파일선택");
		} else {//파일을 선택할 경우
			$("#theater-check").val(1);
		}
		
	});
	
	//메시지 출력 부분
	var msg = "${msg}";
	if(msg != ""){
		alert(msg);
	}
	
}); 
</script>
</head>
<body>
<nav>
<jsp:include page="../business_header.jsp"></jsp:include>
</nav>
<section>
<div class="detail">
	<div class="inner">
		<div id="page_wrap">
			<jsp:include page="../business_sidebar.jsp"></jsp:include>
            <div id="thadd_content">
                    <!-- 페이지 타이틀(영화관 등록)-->
                    <div id="thadd_title">
                        <h2>영화관 등록</h2>
                    </div>
                    <!-- 타이틀 밑에 올 내용-->
                    <div id="thadd_cont">
                        <form method="post" enctype="multipart/form-data"
                            action="./thUpdateFrm" class="thadd_form">
                            <input type="hidden" name="bid" value="${businessInfo.b_id}">
                            <input type="hidden" name="th_code" value="${thDto.th_code}">
                            <!-- 로고 사진 등록-->
                            <div id="thadd_logo">
                                <div id="thadd_num">1</div>
                                <div id="thadd_info_title">
                                    <p>로고 사진</p>
                                </div>
                                <div id="logo_wrap">
                                    <div id="thfileadd_title">
                                        <p>우리 영화관을 대표하는 로고 사진을 등록해주세요!</p>
                                    </div>
                                    <label for="logoFile">업로드</label>
                                    <input type="file" name="logoFiles" id="logoFile" value="${thDto.th_logo}" multiple>
                                    <input type="text" class="logo-name" value="${thDto.th_logo}" readonly>
                                    <!-- 업로드할 파일이 있으면 1, 없으면 0 -->
                                    <input type="hidden" id="logo-check" value="0" name="logoCheck">
                                </div>
                            </div>
                            <div id="thadd_image">
                                <div id="thadd_num">2</div>
                                <div id="thadd_info_title">
                                   <p>영화관 사진</p>
                                </div>
                                <div id="image_wrap">
                                    <div id="thfileadd_title">
                                        <p>우리 영화관을 보여줄 수 있는 사진을 등록해주세요!</p>
                                    </div>
                                    <label for="theaterFile">업로드</label>
                                    <input type="file" name="theaterFiles" id="theaterFile" value="${thDto.th_image1}" multiple>
                                    <input type="file" name="theaterFiles" id="theaterFile" value="${thDto.th_image2}" multiple>
                                    <input type="file" name="theaterFiles" id="theaterFile" value="${thDto.th_image3}" multiple>
                                    <input type="text" class="theater-name" value="${thDto.th_image1}" readonly>
                                    <input type="text" class="theater-name" value="${thDto.th_image2}" readonly>
                                    <input type="text" class="theater-name" value="${thDto.th_image3}" readonly>
                                    <!-- 업로드할 파일이 있으면 1, 없으면 0 -->
                                    <input type="hidden" id="theater-check" value="0" name="theaterCheck"><br>
                                </div>
                            </div>
                            <div id="thadd_detailinfo">
                                <div id="thadd_num">3</div>
                                <div id="thadd_info_title">상세 정보</div>
                                <div id="detailinfo_wrap">
                                    <div id="detailinfo_title">
                                        <p class="title_css">영화관 이름</p>
                                    </div>
                                    <input type="text" title="영화관 이름" name="thname" id="td_name" value="${thDto.th_name}"><br>
                                    <div id="detailinfo_title">
                                        <p>영화관 소개</p>
                                    </div><br>
                                    <textarea cols="100" rows="5" name="thintro">${thDto.th_introduce}</textarea><br>
                                    <div id="detailinfo_title">
                                        <p>주소</p>
                                    </div>
                                    <input type="text" title="오시는 길" name="thplace" value="${thDto.th_location}"><br>
                                    <div id="detailinfo_title">
                                        <p>연락처</p>
                                    </div>
                                    <input type="text" title="연락처" name="thtel" value="${thDto.th_tel}"><br>
                                    <div id="detailinfo_title">
                                        <p>주차 안내</p>
                                    </div><br>
                                    <textarea cols="100" rows="5" name="thpark">${thDto.th_parking}</textarea><br>
                                    <div id="detailinfo_title">
                                        <p>지역 코드</p>
                                    </div>
                                    <select id="reNum" name="reNum"> 
                                        <option value="11" selected>서울</option>
                                        <option value="23">인천</option>
                                        <option value="31">경기</option>
                                        <option value="32">강원</option>
                                        <option value="33">충북</option>
                                        <option value="34">충남</option>
                                        <option value="35">전북</option>
                                        <option value="36">전남</option>
                                        <option value="37">경북</option>
                                        <option value="38">경남</option>
                                        <option value="39">제주</option>
                                    </select>
                                </div>
                            </div>
                            <br>
                                <input type="submit" class="thadd-btn" value="수정" id="uploadBtn">
                        </form>
                    </div>
                </div>
		</div>
	</div>
</div>
</section>
<footer>
<jsp:include page="../footer.jsp"></jsp:include>
</footer>
</body>
</html>