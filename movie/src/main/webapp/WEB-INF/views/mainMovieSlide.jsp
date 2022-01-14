<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://unpkg.com/swiper@7/swiper-bundle.min.css" />
    <style>
        .theater_wrap_wrap {
            width: 1200px;
        }

        .slider-type-theater {
            position: relative;
            padding: 40px 38px;
            overflow: hidden;
            margin: 0 auto;
            width: 1024px;
        }

        .swiper-slide {
            margin-right: 15px;
            /* opacity: 1; */
            transition: opacity 0.3s;
            background: cornflowerblue;
            width: 400px;
            height: 300px;
        }



        img {
            width: 181px;
            height: 200px;
        }


        .main_movielist_cont_wrap {
            margin: 0 auto;
            position: relative;
            overflow: hidden;
            list-style: none;
            padding: 0;
            z-index: 1;
            width: 100%;
            height: 520px;
	
            overflow: hidden;


        }

        .main_movielist_cont {
            margin-top: 33px;
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
            text-align: center;
            overflow: hidden;


        }

        .movie_list_item {
            background-color: white;
            width: 177.6px;
            height: 402px;
        }

        .img {
            width: 100%;
            height: 285.99px;
        }

        .movie_list_item>a>div>img {
            width: 100%;
            height: 254.18px;
        }

        .subj {
            width: 100%;
            height: 23.59px;
            font-weight: 400;
            text-align: left;
        }

        .fa-star {
            color: #ebcf34;
        }

        .grade>strong {
            color: #f16a1a;
        }

        .talker::before {}

        .movie_list_item_reserbtn {
            width: 100%;
            height: 50px;
            font-size: 14px;
            line-height: 47px;
            text-align: center;
            font-weight: 600;
            transition: all .3s ease;
            background-color: transparent;
            border: 1px solid #f16a1a;
            color: #f16a1a;
            display: block;
            cursor: pointer;
            text-decoration: none;
            margin-top: 10px;

        }

        .movie_list_item_reserbtn:hover {
            color: white;
        }
    </style>
</head>

<body>


    <div class=".main_movielist_cont_wrap slider-type-theater" style="padding-top: 0;">
        <div class="swiper-container swiper-container-initialized swiper-container-horizontal .main_movielist_cont">
            <div class="swiper-wrapper">
            	<c:forEach var ="thdtail" items= "${theatedetail}" varStatus="status">
				<div class="swiper-slide movie_list_item item2">
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
                <div class="swiper-slide movie_list_item">
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
                <div class="swiper-slide movie_list_item">
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
                <div class="swiper-slide movie_list_item">
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
                <div class="swiper-slide movie_list_item">
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
                <div class="swiper-slide movie_list_item">
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
                <div class="swiper-slide movie_list_item">
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
                <div class="swiper-slide movie_list_item">
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
                <div class="swiper-slide movie_list_item">
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
            </div>
        </div>
        <div class="btn">
            <div class="swiper-button-next"></div>
            <div class="swiper-button-prev"></div>
        </div>

    </div>

    </div>
    </div>
    </div>

</body>
<script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>
<script>

    document.addEventListener("DOMContentLoaded", function () {

        var mySwiper = new Swiper('.swiper-container', {
            slidesPerView: 5,
            slidesPerGroup: 1,
            // observer: false,
            // observeParents: false,
            spaceBetween: 30,
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
            // breakpoints: {
            //     1280: {
            //         slidesPerView: 5,
            //         slidesPerGroup: 5,
            //     },
            //     720: {
            //         slidesPerView: 1,
            //         slidesPerGroup: 1,
            //     }
            // }
        });

    });


</script>

</html>