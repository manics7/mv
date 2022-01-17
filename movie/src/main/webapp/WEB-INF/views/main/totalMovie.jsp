<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상영 시간표</title>
<style type="text/css">
	//.text-warning {color: #f16a19;}
	a { text-decoration: none!important;
		margin: 0!important;
		padding:0!important;
	}
	
	ul, li, p{
	list-style: none;
	color: #333;
	text-decoration: none;
	}
	
	.disable{
    pointer-events:auto;
    opacity:0.3;
     background-color: #DDD;
    color: #999;
	}
	
	p.selected, li.selected {
	color: white!important;
	font-weight:bold;
	background-color: #f16a19!important;
	}
	.with_150 { width: 150px;}
	.disabled {border: none; list-style: none; }
	.font_green {color:  #037b94;}
	.font_orange {color:  #f16a19;}
	#schTimeList span:visited {color:  #f16a19;}
	.width_200 {width: 200px;}
	
	#movieBox {min-height: 375px;}
	#schDateList li {border-left: none; border-right: none;}
	
	#schTimeList ul {border-bottom: 1px solid rgba(0, 0, 0, 0.125); }
	.time_title {border-bottom: 1px solid rgba(0, 0, 0, 0.125) ;}
	
	//#areaList > ul > li:hover  {background-color: #f16a1a; color: white;}
	//#dateList > ul > li:hover  {background-color: #f16a1a; color: white;}
	#schTimeList > ul > li:hover  {background-color: #f16a1a; color: white;}
	#schTimeList span:not(hover)  {color: #f16a1a;}
	
}
</style>

</head>
<body>
<header>
			<jsp:include page="../header.jsp"></jsp:include>
		</header>
<div class="container">

	<div class="row" >
		<div class="col-md-12 " id="movieBox"  >
			<h5 class="mb-3 mt-3 font-weight-bold">영화리스트</h5>
			<div class="row">
				<div class="col-md-9">
					<div class="row row-cols-3 pl-3" id="schMovieList"></div>
				</div>
				
					
				<div class="col-md-3 text-center" ><img class="img-fluid" id="poster" alt="" ></div>
			</div>
			
				
		</div>
	</div>
	<div class="row">
		<div class="col-md-12 mt-3 mb-3" >
			<h5 class="font-weight-bold mt-3 mb-3"><span class="font_green" id="movieNm"></span> 상영시간표</h5>
			<ul  class="list-group list-group-horizontal"  id="schDateList"></ul>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12" >
			<ul class="list-group list-group-horizontal" id="areaList">
			  <!-- <li class="list-group-item py-2 px-2 w-100  selected " id="areaCode-00">전체</li> -->
			  <li class="list-group-item py-2 px-2 w-100" id="areaCode-11">서울</li>
			  <li class="list-group-item py-2 px-2 w-100" id="areaCode-31">경기</li>
			  <li class="list-group-item py-2 px-2 w-100" id="areaCode-23">인천</li>
			  <li class="list-group-item py-2 px-2 w-100" id="areaCode-25,33,34,29">대전/충청/세종</li>
			  <li class="list-group-item py-2 px-2 w-100 "  id="areaCode-21,22,37,38">부산/대구/경상</li>
			  <li class="list-group-item py-2 px-2 w-100 "  id="areaCode-24,35,36">광주/전라</li>
			  <li class="list-group-item py-2 px-2 w-100 "  id="areaCode-32,39">강원/제주</li>
			</ul>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12 mb-5"  id="schTimeList" >
					
		</div>
		<div class="col-md-12 text-center mt-5" id="location_result" style="display: none;" >
			<div class="mt-5">
				<p class="pt-5">해당 지역에 상영 시간표가 없습니다.</p>
				<p>다른지역을 선택해 주세요.</p>
			</div>
			
		</div>
	</div>
</div>
	

</body>

<script src="resource/js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="resource/css/bootstrap.css">
<script src="resource/js/bootstrap.bundle.js"></script>
<script type="text/javascript"  src="resource/js/rsrv.js"></script>
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
							var attr = '';
							if(i==0){
						//		attr ="selected";								
							}
							html+="<div class='col-md-4 px-0 my-0'>"
							html+= "<p class='list-group-item my-0 "+attr+"'  movieCd="+ res.movieList[i].movieCd + " > "+res.movieList[i].movieNm +" </p>"
							html+= "<input type='hidden' name='mvPoster' value="+res.movieList[i].poster + ">"
							html+="</div>"
						}
						$("#schMovieList").html(html);
					
						html= "";
						for(var i=0; i<res.dateList.length; i++){
							var dayOfWeek =res.dateList[i].dayOfWeek;						
							var color = (dayOfWeek == '일' ?  "text-danger" : dayOfWeek == "토" ? "text-primary"  : "");
							var attr = '';
							if(i==0){
							//	attr ="selected";								
							}
							html+= "<li class='list-group-item w-100 text-center "+color+ " "+attr + " font-weight-bold' schDate="+ res.dateList[i].date +  " > "+res.dateList[i].date.substr(8,2) + "<br>" + res.dateList[i].dayOfWeek  + " </li>" 
						}
						$("#schDateList").html(html);
						
					//	$("#movieList p").eq(0).click();
					//$("#schDateList li").eq(0).click();
						//$("#theaterList li").eq(0).click();
							
						html= "";
						//for(var i=0; i<res.theaterList.length; i++){
						//	html+= "<li thCode="+ res.theaterList[i].thCode +"> "+res.theaterList[i].thName + " </>" 
						//}
						//$("#theaterList").html(html);
						
					
					},error : function(err) {
						//console.log("err:", err)
					}
				});
			}
	
	
			
			//영화, 날짜, 극장을 선택한 조건으로 스케쥴정보를 가져온다.
			function selectItem(e){
				e.stopImmediatePropagation();
				
				var params = {
						"movieCd" : movieCd
						,"schDate" : schDate
				}
				
				console.log(params)
				
				
				$.ajax({
					type : "GET"
//					,url : "/selectSchedule?movieCd="+movieCd+"&thCode="+thCode+"&schDate="+schDate      		
					,url : "/selectSchedule?"+$.param(params)      		
					,success : function(res) {
						var html = "";
					 	
						if($("#schDateList li").hasClass("selected")){				
							$("#schMovieList p, #theaterList li").addClass("disable");	
							res.forEach(function(item){				
								$("#schMovieList p[movieCd=" +item.movieCd+ "]").css("cursor","pointer").removeClass("disable");	
						//		$("#theaterList li[thCode=" +item.thCode+ "]").css("cursor","pointer").removeClass("disable");	
							});
						}
			 
						
						if($("#schMovieList p").hasClass("selected")){
							$("#theaterList li, #schDateList li").addClass("disable");		
							res.forEach(function(item){
							//	$("#theaterList li[thCode=" +item.thCode+ "]").css("cursor","pointer").removeClass("disable");	
								$("#schDateList li[schDate=" +item.schDate+ "]").css("cursor","pointer").removeClass("disable");	
							});											
						}	 
						var selectCnt = $("#schMovieList p.selected, #schDateList li.selected").length;
						if(selectCnt == 2){
						getTimeList();
						}

					},error : function(err) {
						console.log("err:", err)
					}
				});
			}
			

			function getTimeList(){
				
				var params = {
						"movieCd" : movieCd
						,"schDate" : schDate
				}
								
				$.ajax({
					type : "GET"
					,url : "/totalMovieTimeList?"+$.param(params)      		
					,success : function(res) {
						var html = "";
						var thCode = "";
						var status = "";
						var schDate = $("#schDateList .selected").attr("schDate");
						var movieCd = $("#schMovieList .selected").attr("movieCd");
						
						if(res != null){
							thCode != res[0].theater.thcode;
							for(var i=0; i<res.length; i++){							
							
								if(thCode != res[i].theater.thCode){
									thCode = res[i].theater.thCode;
									html += "<h6 class='font-weight-bold mt-3 py-3 time_title' th_areacode="+res[i].theater.thAreacode+">"+ res[i].theater.thName +" </h6>"
								}								
								html += "<ul class='list-group list-group-horizontal' th_areacode="+res[i].theater.thAreacode+">";
								html += "<li class='list-group-item mt-2 mb-4 py-3 px-3 font-weight-bold color_333 width_200 disabled' style='border:none;' ''> "+res[i].room.roomName +" <br> 총 "+res[i].room.seatCnt+"석 </li>"
								html += "<li class='list-group-item mt-2 mr-3 mb-4 py-4 px-3  disabled' style='background-color:#f2f4f5;width:100px; border-left:1px solid rgba(0, 0, 0, 0.125);'> "+ res[i].room.roomClass +"  </li>"
								for(var j=0; j<res[i].scheduleDetail.length; j++){	
									var disabled = "";
									var status = "";
									var seatCnt = res[i].room.seatCnt
									var rsrvCnt = res[i].scheduleDetail[j].rsrvSeatCnt;
									status = res[i].scheduleDetail[j].schStatus == 'deadline'  ? res[i].scheduleDetail[j].schStatus : '';						
									
									if( status != ''){
										status = "[예매종료]";		
										disabled = "disabled";		
									}
									
									html +=	 "<li class='list-group-item mt-2 mb-4 py-3 px-3 text-center "+disabled+"' schCode="+res[i].scheduleDetail[j].schCode  
									+ " data-toggle='modal' data-target='#rsrvModal' data-moviecd="+movieCd+"  data-thcode="+res[i].theater.thCode
									+ " data-schdate="+schDate+" data-schDetailSeq="+res[i].scheduleDetail[j].schDetailSeq+"  id='modal' "
									+ " style='width:150px; border-left:1px solid rgba(0, 0, 0, 0.125);style='cursor:pointer;'> "
									+ res[i].scheduleDetail[j].schDetailStart + "~"+res[i].scheduleDetail[j].schDetailEnd
									+ "<br><span class='font-weight-bold'>"+ (seatCnt-rsrvCnt) +"</span>석<br> <span style='color:red;'> "+status+"</span>"
								/* 	html += "<a class='"+disabled+"' data-toggle='modal' data-target='#rsrvModal' data-moviecd="+movieCd+"  data-thcode="+res[i].theater.thCode
									+ " data-schdate="+schDate+" data-schDetailSeq="+res[i].scheduleDetail[j].schDetailSeq+"  id='modal'  style='cursor:pointer;text-decoration: none;' data-status=" +status+">"
									
									+"<li class='list-group-item mt-2 mb-4 py-3 px-3 text-center' schCode="+res[i].scheduleDetail[j].schCode+"  style='width:150px; border-left:1px solid rgba(0, 0, 0, 0.125);'> "
									+ res[i].scheduleDetail[j].schDetailStart + "~"+res[i].scheduleDetail[j].schDetailEnd
									+ "<br><span class='font_orange font-weight-bold'>"+ (seatCnt-rsrvCnt) +"</span>석<br> <span style='color:red;'> "+status+"</span>" */
									
									+"</li>"
									+"</a>"
								}
								html +="</ul>";
							}
							$("#schTimeList").html(html);						
							$("#areaList li").eq(0).click();
						}
					

					},error : function(err) {
						console.log("err:", err)
					}
				});
			}			
			
			//선택시 색상변경
			$(document).on('click',"#schDateList li, #theaterList li, #areaList li"		
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
			
			
			//선택시 색상변경
			$(document).on('click',"#schMovieList p"		
				,function() {		
				var _this = this;
					$(_this).addClass("selected"); //클릭된 부분을 상단에 정의된 CCS인 selected클래스로 적용
					$(_this).css("background-color","#f16a1a");
					$(_this).css("color","white");
					
					$("#schMovieList p").each(function(){	         
				   		$("#schMovieList p").not(_this).removeClass("selected"); //siblings:형제요소들,    removeClass:선택된 클래스의 특성을 없앰
				   		$("#schMovieList p").not(_this).css("background-color","");
				   		$("#schMovieList p").not(_this).css("color","");
					});
			});
			
			
			$(document).on('click',"#schTimeList li"		
					,function() {		
						var _this = this;
						$(_this).addClass("selected"); 
						$(_this).css("background-color","#f16a1a");
						$(_this).css("color","white");
						$(_this).children("span:first").css("color","white");
						
						$("#schTimeList li").each(function(){	         
							$("#schTimeList li").not(_this).removeClass("selected"); 
							$("#schTimeList li").not(_this).css("background-color","");
							$("#schTimeList li").not(_this).css("color","");
							$("#schTimeList li").not(_this).children("span").css("color","");
						});
				});
			

			//일정에 없는 영화 선택시 모달창열기
			$(document).on('click', "li.disable, p.disable", function(e){		
				e.stopImmediatePropagation();
				modalShow("confirmModal");
			});
			//일정에 없는 영화 선택시 모달창에서 확인 누르면 초기화
			$(document).on('click', "#confirm", function(e){
				modalClose("confirmModal");	
				location.href="totalMovie";
			});
						
			
			//영화 선택시
			$(document).on("click", "#schMovieList p" , function(e){
				movieCd = 	$(this).attr("movieCd");
				$("#movieNm").text($(this).text());
				$("#poster").attr("src",$(this).next().val());
				selectItem(e);
			});
			
			//날짜 선택시
			$(document).on("click", "#schDateList li" , function(e){
				schDate = $(this).attr("schDate");
				selectItem(e);		
				
			});
			
			//극장 선택시
			$(document).on("click", "#theaterList li" , function(){
			//	selectItem();
			});
			

			//모달닫기
			function modalClose(id){
			$("#"+id).modal('hide')
			}
			//모달열기
			function modalShow(id){
				$("#"+id).modal('show')
			}
			
			//지역선택 시 극장목록 검색
			$(document).on("click", "#areaList li" , function(){

				selectArea();
			    
			});
			function selectArea(){
				var timeList = $("ul[th_areacode]");		
				var areaCode = $("#areaList li.selected").attr("id").substr(9,11)
			
				///if(areaCode == "00"){
				//	$(timeList[i]).prev().css("display","flex");
				//	$(timeList[i]).css("display","flex");
					
				//	return;
				//}
				var check = false;
				
				for(var i=0; i<timeList.length; i++){
					console.log("areaCode = " + areaCode + " / " + $(timeList[i]).attr("th_areacode"))
					if(areaCode.indexOf($(timeList[i]).attr("th_areacode")) != -1){
						$(timeList[i]).prev().css("display","flex");
						$(timeList[i]).css("display","flex");
						check = true;
					}else{
						$(timeList[i]).prev().css("display","none");
						$(timeList[i]).css("display","none");						
					}
				}
				
				if(check == false){
					$("#location_result").css("display","block");
					$("#location_result").css({"background" : "url(resource/images/bg-re-img-film.png) top center no-repeat"});
				}else{
					$("#location_result").css("display","none");
				}
				
				
				
			}
						
			//모달창열기
			$(document).on('click',"#totalModal",function(e) {
				if($(this).attr("data-status").length > 0){
					e.preventDefault();
				}
				$('#rsrvModal .modal-content').load("rsrv", fnObj.init(e));
			});
			$(document).on('mouseover',"#schTimeList li, #schTimeList span",function(e) {
				$(this).children("span").css("color","white")
			});
			$(document).on('mouseout',"#schTimeList li, #schTimeList span",function(e) {
				$(this).children("span").css("color","")
			});
			

		});
	
	</script>
	
	
	<div class="modal fade" id="rsrvModal" tabindex="-1" role="dialog"	aria-labelledby="label" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog modal-xl modal-dialog-centered" role="document">
			<div class="modal-content"></div>
		</div>
	</div>
	
	<div class="modal fade" id="confirmModal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">알림</h5>
        <button type="button" class="close" aria-label="Close" onclick="javascript:modalClose('confirmModal');">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        선택하신 상영스케줄이 없습니다. 계속하시겠습니까?(선택한 날짜 및 시간이 해제됩니다.)
      </div>
      <div class="modal-footer">        
      	<div class="mr-auto ml-auto">
      	   <button type="button" class="btn btn-primary" id="confirm">확인</button>
     	   <button type="button" class="btn btn-secondary" data-dismiss="modal" > 취소</button>
      	</div>       
      </div>
    </div>
  </div>
</div>	
	
</html>