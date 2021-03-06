<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
</head>

<body>
	<!-- Responsive navbar-->
	<div class="navbar_wrap">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark navbar">
			<div class="container">
				<a class="navbar-brand" href="#!">인디&아트|시네마</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link" href="#!">영화관찾기</a></li>
						<li class="nav-item"><a class="nav-link" href="#!">영화검색</a></li>
						<li class="nav-item"><a class="nav-link" href="#!">마이페이지</a></li>
						<li class="nav-item"><a class="nav-link" href="#!">로그아웃</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="#!">빠른예매</a></li>
					</ul>
				</div>
			</div>
		</nav>

	</div>

	<!-- Header - set the background image for the header in the line below-->
	<header class="py-5 bg-image-full main_header main_header">
		<div class="text-center my-5">
			<h1 class="text-white fs-3 fw-bolder main_header_title"
				style="font-size: 20px;">${thdetail.th_name}</h1>
			<img class="img-fluid rounded-circle mb-4 main_header_logo"
				src="${thdetail.th_logo}" alt="..." />
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
							<button class="header_btn1">예매하기</button>
							<button class="header_btn1">상영시간표</button>
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
			<div class="main_movielist_cont">
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
				<div class="movie_list_item item2">
					<a href="#" class="thum">
						<div class="img">
							<img src="imges/movie_list_img2.jpg" alt="">
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
				<div class="movie_list_item item4">
					<a href="#" class="thum">
						<div class="img">
							<img src="imges/movie_list_img4.jpg" alt="">
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
				<div class="movie_list_item item5">
					<a href="#" class="thum">
						<div class="img">
							<img src="imges/movie_list_img5.jpg" alt="">
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
			</div>

		</section>
	</div>

	<div class="screening_schedule_wrap">
		<section class="screening_schedule">
			<!--부트스트랩으로 py5라고 있었음.-->
			<a class="cinema_timetable">
				<h2>상영시간표</h2>
			</a>
			<!-- 상영일정 페이징 부분 부트스트랩 페이징 컴포넌트 가져올까 생각중...-->

			<div class="day_paging">
				<ul class="pagination pagination-lg">
					<li class="page-item disabled"><a class="page-link" href="#">&laquo;</a>
					</li>
					<li class="page-item activec"><a class="page-link" href="#">22(수)</a>
					</li>
					<li class="page-item"><a class="page-link" href="#">23(수)</a>
					</li>
					<li class="page-item"><a class="page-link" href="#">24(목)</a>
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
				</ul>
			</div>

			<div class="movie_schedule_list">
				<ul>
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
				</ul>
			</div>
		</section>
	</div>
	<div class="cont_box_wrap_wrap">
		<section class="cont_box_wrap">
			<div class="notice_box">
				<h2 class="notice_box_title">공지사항</h2>
				<ul>
					<li><a href="#"> <span class="text">[기타]개인정보처리방침 변경
								안내</span> <span class="date">2020.10.20</span>
					</a></li>
					<li><a href="#"><span class="text">[기타]개인정보처리방침 변경
								안내</span> <span class="date">2020.10.20</span> </a></li>
				</ul>
				<a class="more_1" href="#">더보기</a>
			</div>
			<div class="que_box">
				<h2 class="que_box_title">자주 묻는 질문</h2>
				<ul>
					<li><a href="#"> <span class="text">[기타]개인정보처리방침 변경
								안내</span> <span class="date">2020.10.20</span>
					</a></li>
					<li><a href="#"><span class="text">[기타]개인정보처리방침 변경
								안내</span> <span class="date">2020.10.20</span> </a></li>
				</ul>
				<a class="more_2" href="#">더보기</a>
			</div>
		</section>
	</div>
	<div class="location_box_wrap">
		<h2 class="location_box_title">위치안내</h2>
		<section class="location_box">
			<div class="map_box"></div>
			<div class="loc_list">
				<h3>오시는길</h3>
				<p>"경상남도 창원시 마산합포구 동서북 14길 24, 3층 씨네아트 리좀"</p>
				<h3>연락처</h3>
				<p>070-8801-6436 | 010-5949-6438</p>
				<h3>주차안내</h3>
				<p>
					"씨네아트 리좀은 주차장이 없습니다." <br> <br> <br> <br> "가까운
					주차장으로는 창동 공영주차장, bnk경남은행 주차장이 있습니다." <br> "주차권은 따로 나가지 않습니다."
				</p>
			</div>
		</section>
	</div>

	<div class="cinema_pic_wrap">
		<h2 class="cinema_pic_title">영화관 사진</h2>
		<section class="cenema_pic">
			<!-- <div class="img_box1_wrap"></div> -->
			<div class="img_box box1">
				<img src="/imges/cinema_pic1.jpg" alt=""> <a href="#">1관
					상영관</a>
			</div>
			<div class="img_box box2">
				<img src="/imges/cinema_pic2.jpg" alt=""> <a href="#">1관
					상영관</a>
			</div>
			<div class="img_box box3">
				<img src="/imges/cinema_pic3.jpg" alt=""> <a href="#">1관
					상영관</a>
			</div>
		</section>
	</div>

	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your
				Website 2021</p>
		</div>
	</footer>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
</body>

</html>