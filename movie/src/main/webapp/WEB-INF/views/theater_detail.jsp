<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>theater_detail</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<link rel="stylesheet" href="resource/css/theater_detail/custome.css">
<link rel="stylesheet" href="resource/css/theater_detail/styles.css">
<!-- FontAwesome CDN-->
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p&libraries=services"
	crossorigin="anonymous" />
	
	<style>
	a{text-decoration: none;
	color: black;}
   i img{
    width: 10px;
    height: 10px;
    
    }
.que_box_title>ul>li>a:hover{
text-decoration: none;
background: transparent;
color: black;

}

.header_btn1:hover{
background: #f16a1a;
color: white;
}

	
	
	
	</style>
	
	<script type="text/javascript" src="resource/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=599889030d8acd127e1969cf1dfccf0f&libraries=services"></script>
	<script type="text/javascript" >
	
	
	
	
	$(document).ready(function () {
		getDate();
		  
	    console.log("getDate : " + getDate());
	    function getDate() {
	        $.ajax({
	            type: "GET"
	            , url: "/getDate"
	            , success: function (data) {
	                var html = "";
	                data.forEach(function (item, index) {

	                    var date = item.date
	                    var dayOfWeek = item.dayOfWeek;
	                    color = (dayOfWeek == '일' ? "text-danger" : dayOfWeek == "토" ? "text-primary" : "");
	                    day = date.substr(8, 2);
	                    html += "<li class='list-group-item my-0 py-2 dateBtn " + color + " font-weight-bold'  date=" + date + " style ='cursor : pointer;'    >" + day + " (" + dayOfWeek + ")</li>";
	                });
	                $(".day_paging ul").html(html);
	                $("#theaterDataList li").eq(0).click()
	            }, error: function (err) {
	                //console.log("err:", err)
	            }
	        });
	       
	    }


	    $(document).on('click', ".dateBtn", function () {
	        var date = $(this).attr("date");
	        $.ajax({
	            type: "GET"
	            , url: "getSchedulelist?schDate=" + date + "&thCode=" + ${ th_code }
	            , success : function (data) {
	                var html = "";

	                for (i = 0; i < data.length; i++) {

	                    for (j = 0; j < data[i].schedule.scheduleDetail.length; j++) {
	                        html += "<li><div>"

	                        html += "<p class='stime'>" + data[i].schedule.scheduleDetail[j].schDetailStart + "</p>"
	                        html += "<p class='etime'>" + data[i].schedule.scheduleDetail[j].schDetailEnd + "</p>"
	                        html += "<p class='seat'><b>" + data[i].schedule.scheduleDetail[j].rsrvSeatCnt
	                            + "</b>/ " + data[i].room.seatCnt + " 석</p>"
	                        html + "</div></li>"
	                        html += "<p class = mv_info>" + data[i].room.roomName + "-" + data[i].room.roomClass + "</p>"
	                        html += "<p class = mv_title>" + data[i].movieOfficial.movieNm + "</p>"
	                    }
	                }
	                $(".movie_schedule_list ul").html(html);
	            }
	            , error : function (err) {
	                //console.log("err:", err)
	            }
	            });

	});
	    
	    });



	</script>

	</head>
	
<body>
	<!-- Responsive navbar-->
	<!--   -->
	<nav>
	<jsp:include page="main_header.jsp"></jsp:include>
	</nav>
	

	<!-- Header - set the background image for the header in the line below-->
	<header class="py-5 bg-image-full main_header main_header">
		<div class="text-center my-5">
			<h1 class="text-white fs-3 fw-bolder main_header_title"
				style="font-size: 20px;">${thinfoDto.th_name}</h1>
			<img class="img-fluid rounded-circle mb-4 main_header_logo"
				src="${thinfoDto.th_logo}" alt="..." />
			<!--
                <p class="text-white-50 mb-0">Landing Page Template</p>
            -->
		</div>
		<!-- <div class="main_btn_wrap">
            <div class="main_btn">

                <button class="header_btn1">영화관홈</button>
                <button class="header_btn1">예매하기</button>
                <button class="header_btn1">상영시간표</button>

            </div>

        </div> -->


	</header>
	<!-- Content section-->
	<div class="tab_list_wrap"></div>
	<section class="tab_list">
		<div class="container my-5">
			<div class="row justify-content-center">
				<div>
					<!--(custome css)메인 상단 매뉴 추가-->
					<div class="main_btn_wrap">
						<div class="main_btn">
							<button class="header_btn1">영화관홈</button>
							<button data-toggle="modal" data-target="#rsrvModal" data-movieCd=""  data-thcode=""  id="modal" style="text-decoration: none;" class="header_btn1 reserBtn">예매하기</button>
							<button class="header_btn1 scheduleBtn">상영시간표</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- (custom css) 메인 베너-->
	<div class="main_banner_wrap">
		<div class="main_banner_img"
			style="background-image: url('https://source.unsplash.com/4ulffa6qoKA/1200x800')">
		</div>
	</div>
	<!-- Content section-->
	<!-- (custom css)메인 영화 리스트-->
	<div class="main_movielist_wrap">
		<section class="main_movielist">
			<!--부트스트랩으로 py5라고 있었음.-->
			<div class="main_movielist_top">
				<a href="#" style="color: black;">현재상영중</a> <a href="#"
					style="color: gray;">상영예정작</a>
			</div>
			<div class="main_movielist_cont_wrap">
			<div class="main_movielist_cont">
			<jsp:include page="mainMovieSlide.jsp"></jsp:include>
			
			
			
			<!--  
			<div class="movie_list_item item1">
					<a href="#" class="thum">
						<div class="img">
							<img src="imges/movie_list_img1.jpg" alt="">
						</div>
						<div class="info">
							<div class="subj" title="드라이브 마이 카">드라이브 마이 카</div>
							<div class="grade">
								<span><i class="fas fa-star"></i></span> <span>일반</span> <strong>5.0</strong>
								<span class="talker">평론가</span> <strong>4.3</strong>
							</div>
						</div>
					</a> <a href="#" class="movie_list_item_reserbtn">예매하기</a>
				</div>
			
			-->
				
				
				
				<!-- 
				
					<c:forEach var ="thdtail" items= "${theatedetail}" varStatus="status">
				<div class="movie_list_item item2">
					<a href="#" class="thum">
						<div class="img">
							<img src="${theatedetail[status.index].movieOfficial.poster}" alt="">
						</div>
						<div class="info">
							<div class="subj" title="드라이브 마이 카">${theatedetail[status.index].movieOfficial.movieNm}</div>
							<div class="grade">
								<span><i class="fas fa-star"></i></span> <span>${status.index}</span> <strong>5.0</strong>
								<span class="talker">평론가</span> <strong>4.3</strong>
							</div>
						</div>
					</a> <a href="#" class="movie_list_item_reserbtn">예매하기</a>
				</div>
				</c:forEach>
				 -->
				
			
				<!--  
				<div class="movie_list_item item3">
					<a href="#" class="thum">
						<div class="img">
							<img src="imges/movie_list_img3.jpg" alt="">
						</div>
						<div class="info">
							<div class="subj" title="드라이브 마이 카">드라이브 마이 카</div>
							<div class="grade">
								<span><i class="fas fa-star"></i></span> <span>일반</span> <strong>5.0</strong>
								<span class="talker">평론가</span> <strong>4.3</strong>
							</div>
						</div>
					</a> <a href="#" class="movie_list_item_reserbtn">예매하기</a>
				</div>
				-->
			</div>
			</div>
			

		</section>
	</div>

	<div class="screening_schedule_wrap">
		<section id="reserSchedule" class="screening_schedule">
			<!--부트스트랩으로 py5라고 있었음.-->
			<a class="cinema_timetable">
				<h2>상영시간표</h2>
			</a>
			<!-- 상영일정 페이징 부분 부트스트랩 페이징 컴포넌트 가져올까 생각중...-->

			<div class="day_paging">
				<ul class="pagination pagination-lg" id="theaterDataList">
				<!--<li class="page-item" name="datebtn"><a class="page-link" href="#">&laquo;</a>
					</li>
					<li class="page-item" name="datebtn"><a class="page-link" href="#">22(수)</a>
					</li>
					<li class="page-item" name="datebtn"><a class="page-link" href="#">23(수)</a>
					</li>
					<li class="page-item" name="datebtn"><a class="page-link" href="#">24(목)</a>
					</li>
					<li class="page-item"><a class="page-link" href="#">25(금)</a>
					</li>
					<li class="page-item"><a class="page-link" href="#">26(토)</a>
					</li>
					<li class="page-item"><a class="page-link" href="#">27(일)</a>
					</li>
					<li class="page-item"><a class="page-link" href="#">28(월)</a>
					</li>
					<li class="page-item"><a class="page-link" href="#">29(화)</a>
					</li>
					<li class="page-item"><a class="page-link" href="#">&raquo;</a>
					</li>
					<li>${theatedetail[0].schedule.scheduleDetail}</li>
				    -->
				    
				</ul>
			</div>
			
			<!--  
			
			 html += "<li><div>"

	                        html += "<p class='stime'>" + data[i].schedule.scheduleDetail[j].schDetailStart + "</p>"
	                        html += "<p class='etime'>" + data[i].schedule.scheduleDetail[j].schDetailEnd + "</p>"
	                        html += "<p class='seat'><b>" + data[i].schedule.scheduleDetail[j].rsrvSeatCnt
	                            + "</b>/ " + data[i].room.seatCnt + " 석</p>"
	                        html + "</div></li>"
	                        html += "<p class = mv_info>" + data[i].room.roomName + "-" + data[i].room.roomClass + "</p>"
	                        html += "<p class = mv_title>" + data[i].movieOfficial.movieNm + "</p>"
			
			 -->
<div class="movie_schedule_list">
				<ul>

<li>
			<!--
					<li>
						<div>
							<p class="stime">10:00</p>
							<p class="etime">11:33</p>
							<p class="seat">
								<b>48</b> / 51 석
							</p>
						</div>
						<p class="mv_info">1관 - 2D</p>
						<p class="mv_title">너에게 가는 길</p>
					</li>
-->
					
				</ul>
			</div>
		</section>
	</div>
	<div class="cont_box_wrap_wrap">
		<section class="cont_box_wrap">
			<div class="notice_box">
				<h2 class="notice_box_title">공지사항</h2>
				<ul>
					<li><a style="color: black;" href="#"> <span class="text">[기타]개인정보처리방침 변경
								안내</span> <span class="date">2020.10.20</span>
					</a></li>
					<li><a style="color: black;" href="#"><span class="text">[기타]개인정보처리방침 변경
								안내</span> <span class="date">2020.10.20</span> </a></li>
				</ul>
				<a class="more_1" href="#">더보기</a>
			</div>
			<div class="que_box">
				<h2 class="que_box_title">자주 묻는 질문</h2>
				<ul>
					<li><a style="color: black;" href="#"> <span class="text">[기타]개인정보처리방침 변경
								안내</span> <span class="date">2020.10.20</span>
					</a></li>
					<li><a style="color: black;" href="#"><span class="text">[기타]개인정보처리방침 변경
								안내</span> <span class="date">2020.10.20</span> </a></li>
				</ul>
				<a class="more_2" href="#">더보기</a>
			</div>
		</section>
	</div>
	<div class="location_box_wrap">
		<h2 class="location_box_title">위치안내</h2>
		<section class="location_box">
			<div class="map_box" id="map">
			
				<!-- 여기 지도 여기 지도 여기 지도 여기 지도 여기 지도 여기 지도 -->
			
			</div>
			<div class="loc_list">
				<h3>오시는길</h3>
				<p>${thinfoDto.th_location}</p>
				<h3>연락처</h3>
				<p>070-8801-6436 | 010-5949-6438</p>
				<h3>주차안내</h3>
				<p>${thinfoDto.th_parking} <br>
					${thinfoDto.th_name}은 주차장이 없습니다. <br> <br> <br> <br> 가까운
					주차장으로는 창동 공영주차장, bnk경남은행 주차장이 있습니다. <br> 주차권은 따로 나가지 않습니다.
				</p>
			</div>
		</section>
		<!-- 영화관 상세정보 받아지는 어떤 값으로 받아지는지 테스트 코드. -->
		<p style="z-index: 10; border: 1px solid lightgray; width: 200px; white-space: nomal; word-break: nomal; padding: 13px; background: gray; color: white;" > <c:out value="값 태스트 : ${theatedetail[0].theater}"></c:out></p> 
	</div>

	<div class="cinema_pic_wrap">
		<h2 class="cinema_pic_title">영화관 사진</h2>
		<section class="cenema_pic">
			<!-- <div class="img_box1_wrap"></div> -->
			<div class="img_box box1">
				<img src="${thinfoDto.th_image1}" alt=""> <a href="#">${thinfoDto.th_name}
					상영관</a>
			</div>
			<div class="img_box box2">
				<img src="${thinfoDto.th_image2}" alt=""> <a href="#">1관
					상영관</a>
			</div>
			<div class="img_box box3">
				<img src="${thinfoDto.th_image3}" alt=""> <a href="#">1관
					상영관</a>
			</div>
		</section>
	</div>

	<!-- Footer-->
	<footer class="py-5">
			<jsp:include page="footer.jsp"></jsp:include>
	</footer>
	<!-- Bootstrap core JS-->
	
	<!-- Core theme JS-->

</body>
<script type="text/javascript">

//scroll to id functionization
function scrollIntoView(selector) {
  const scrollTo = document.querySelector(selector);
  scrollTo.scrollIntoView({ behavior: 'smooth' });
}

//Handle click on "contac me" button on home
const goToSchedule = document.querySelector('.scheduleBtn');
goToSchedule.addEventListener('click', (event) => {
  scrollIntoView('#reserSchedule');
});

$(document).ready(function($) {

    $(".scheduleBtn").click(function(event){         

            event.preventDefault();

            $('html,body').animate({scrollTop:$(this.hash).offset().top}, 500);

    });

});



$(document).ready(function(){
	
})
/*-----색상변경----------*/


//선택시 색상변경
	$(document).on('click',"#theaterDataList li"	
	
		,function() {		
			
			$(this).addClass("selected"); //클릭된 부분을 상단에 정의된 CCS인 selected클래스로 적용
			//$(this).css("color","#212529");			
			$(this).css("background-color","#f16a1a");
			$(this).css("color","white");
			
			$(this).each(function(){	         
		   		$(this).siblings().removeClass("selected"); //siblings:형제요소들,    removeClass:선택된 클래스의 특성을 없앰
		   		$(this).siblings().css("background-color","");
		   		$(this).css("color","");
			});
	});
	



/*----------------여기부터 위치정보 스크립트-------------------*/

var mapContainer = document.getElementById('map'); // 지도를 표시할 div 
var mapOption = {
    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};  

//지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

//주소로 좌표를 검색합니다
geocoder.addressSearch('${thinfoDto.th_location}', function(result, status) {

// 정상적으로 검색이 완료됐으면 
 if (status === kakao.maps.services.Status.OK) {

    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

    // 결과값으로 받은 위치를 마커로 표시합니다
    var marker = new kakao.maps.Marker({
    	map: map,
        position: coords
    });

    // 인포윈도우로 장소에 대한 설명을 표시합니다
    var infowindow = new kakao.maps.InfoWindow({
//         content: '<div style="width:150px;text-align:center;padding:6px 0;">우리 영화관</div>'
    });
    infowindow.open(map, marker);

    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
    map.setCenter(coords);
}  
});

</script>

</html>