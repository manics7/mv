<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상영 시간표</title>
<style type="text/css">
	ul li {
	list-style: none;
	}
	
	.disable{
    pointer-events:auto;
    opacity:0.3;
     background-color: #DDD;
    color: #999;
	}
}
</style>
</head>
<body>

<div class="container">
	<div class="row" >
		<div class="col-md-12" id="movieList" >
			<h6>영화리스트</h6>
			<ul>
			
			</ul>			
		</div>
	</div>
	<div class="row">
		<div class="col-md-12" >
			<h6>날짜목록</h6>
			<ul id="dateList"></ul>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-12" >
			<h6>극장</h6>
			<ul id="theaterList"></ul>
		</div>
	</div>
</div>
	

</body>

<script src="resource/js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="resource/css/bootstrap.css">
<script src="resource/js/bootstrap.bundle.js"></script>
	<script type="text/javascript">
	 
		$(document).ready(function() {
			
			var movieCd;
			var thCode;
			var schDate;
			
			 intit();
			function intit() {
				$.ajax({
					type : "GET"
					,url : "/totalMovieList"      		
					,success : function(res) {
						var html = "";
						for(var i=0; i<res.movieList.length; i++){
							html+= "<li movieCd="+ res.movieList[i].movieCd +"> "+res.movieList[i].movieNm +" </>" 
						}
						$("#movieList ul").html(html);
					
						html= "";
						for(var i=0; i<res.dateList.length; i++){
							html+= "<li schDate="+ res.dateList[i].date +"> "+res.dateList[i].date + "/" + res.dateList[i].dayOfWeek  + " </>" 
						}
						$("#dateList").html(html);
						
						//$("#movieList li").eq(0).click();
						//$("#dateList li").eq(0).click();
						//$("#theaterList li").eq(0).click();
							
						html= "";
						for(var i=0; i<res.theaterList.length; i++){
							html+= "<li thCode="+ res.theaterList[i].thCode +"> "+res.theaterList[i].thName + " </>" 
						}
						$("#theaterList").html(html);
						
					
					},error : function(err) {
						//console.log("err:", err)
					}
				});
			}
			//영화, 날짜, 극장을 선택한 조건으로 스케쥴정보를 가져온다.
			function selectItem(){
			
				var params = {
						"movieCd" : movieCd
						,"thCode"  : thCode
						,"schDate" : schDate
				}
				
				console.log(params)
				
				
				$.ajax({
					type : "GET"
//					,url : "/selectSchedule?movieCd="+movieCd+"&thCode="+thCode+"&schDate="+schDate      		
					,url : "/selectSchedule?"+$.param(params)      		
					,success : function(res) {
						var html = "";
					/* 	
						if($("#dateList li").hasClass("selected")){				
							$("#movieList li, #theaterList li").addClass("disable");	
							res.forEach(function(item){				
								$("#movieList li[movieCd=" +item.movieCd+ "]").css("cursor","pointer").removeClass("disable");	
								$("#theaterList li[thCode=" +item.thCode+ "]").css("cursor","pointer").removeClass("disable");	
							});
						}
						
						if($("#theaterList li").hasClass("selected")){			
							$("#movieList li, #dateList li").addClass("disable");			
							res.forEach(function(item){				
								$("#movieList li[movieCd=" +item.movieCd+ "]").css("cursor","pointer").removeClass("disable");	
								$("#dateList li[date=" +item.schDate+ "]").css("cursor","pointer").removeClass("disable");	
							});
						}
						
						if($("#movieList li").hasClass("selected")){
							$("#theaterList li, #dateList li").addClass("disable");		
							//$("#theaterList li, #dateList li").attr("disable",true);		
							res.forEach(function(item){
								$("#theaterList li[thCode=" +item.thCode+ "]").css("cursor","pointer").removeClass("disable");	
								$("#dateList li[date=" +item.schDate+ "]").css("cursor","pointer").removeClass("disable");	
							});											
						}	 */


					},error : function(err) {
						console.log("err:", err)
					}
				});
			}
			

			//영화 선택시
			$(document).on("click", "#movieList li" , function(){
				movieCd = $(this).attr("movieCd");
				selectItem();
			});
			
			//날짜 선택시
			$(document).on("click", "#dateList li" , function(){
				schDate = $(this).attr("schDate");
				selectItem();
			});
			
			//극장 선택시
			$(document).on("click", "#theaterList li" , function(){
				thCode = $(this).attr("thCode");
				selectItem();
			});
			
			//선택시 색상변경
			$(document).on('click',"#movieList li,#dateList li, #theaterList li ,#timeList li"		
				,function() {		
					
					$(this).addClass("selected"); //클릭된 부분을 상단에 정의된 CCS인 selected클래스로 적용
					$(this).css("background-color","#f16a1a");
					$(this).css("color","white");
					
					$(this).each(function(){	         
				   		$(this).siblings().removeClass("selected"); //siblings:형제요소들,    removeClass:선택된 클래스의 특성을 없앰
				   		$(this).siblings().css("background-color","");
				   		$(this).css("color","");
					});
			});
			
		});
	
	</script>
	
</html>