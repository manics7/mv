<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="resource/css/rsrv.css">
<link rel="stylesheet" type="text/css" href="resource/css/bootstrap.css">



<script type="text/javascript">

</script>
<!-- histoty Modal-->
<div class="modal-content">
	<div class="modal-header text-white modal-header-bg">
		<h5 class="modal-title ml-auto" ><strong>빠른예매</strong></h5>
		<div class="ml-auto">
			<button type="button" class="btn btn-outline-light bg-dark text-white" id="reset">예매 다시하기</button>
			<!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
			<button class="btn btn-outline-light bg-dark text-white font-weight-bold" type="button" data-dismiss="modal">X</button>
		</div>
	</div>
		
	<div class="modal-body pb-2">
			 <div class="conainner">
			 	<div class="row">
			 		<div class="col-md-4 px-0 border-right">
			 			<h5 class="align text-center"><strong>영화</strong></h5>
				         <div class="input-group col-md-12">
				 			 <div class="input-group-append">
	                   		 	<div class="input-group-text border-right-0 bg-transparent"><i><img alt="" src="resource/images/icon/search.svg"></i></div>
	                		</div>
	                		<input class="form-control border-left-0 border" type="search" placeholder="영화관 검색" id="search" onkeyup="filter(this,'theaterList');">	                
            			</div>
				        
				        <div class="col-md-12 mt-2" id="mvSelectBox">				     
				        	<ul class="nav nav-tabs">
							  <li class="nav-item">
							    <a class="nav-link active" href="#">가나다순</a>
							  </li>
							  <!-- <li class="nav-item active">
							    <a class="nav-link">예매율순</a>							   
							  </li> -->						
							  <li class="nav-item">
							    <a class="nav-link" ></a>
							  </li>
							  <li class="nav-item">
							    <a class="nav-link disabled"></a>
							  </li>
							</ul>   				        				        	
				        </div>				        
						  <div class="col-md-12 mt-2 px-0 overflow-auto" id="movieList">
						    <ul class="list-group list-group-flush"></ul>
						  </div>						
			 		</div>
			 		
			 		<div class="col-md-3 border-right">
			 			<h5 class="align text-center"><strong>영화관</strong></h5>
			 			 <div class="input-group pr-0 pl-0">
				 			 <div class="input-group-append">
	                   		 	<div class="input-group-text border-right-0 bg-transparent"><i><img alt="" src="resource/images/icon/search.svg"></i></div>
	                		</div>
	                		<input class="form-control border-left-0 border" type="search" placeholder="영화관 검색" id="search" onkeyup="filter(this,'theaterList');">	                
            			</div>
							            				          
				        
			 			<div class="row mt-2">
				 			<div class="col-4 px-0" id="areaList">			
				 				 <ul class="list-group">
						           <li class="list-group-item my-0 py-2" id="areaCode-00">전체</li>
						           <li class="list-group-item my-0 py-2" id="areaCode-11">서울</li>
						           <li class="list-group-item my-0 py-2" id="areaCode-21">부산</li>
						           <li class="list-group-item my-0 py-2" id="areaCode-22">대구</li>
						           <li class="list-group-item my-0 py-2" id="areaCode-23">인천</li>
						           <li class="list-group-item my-0 py-2" id="areaCode-24">광주</li>
						           <li class="list-group-item my-0 py-2" id="areaCode-25">대전</li>
						           <li class="list-group-item my-0 py-2" id="areaCode-26">울산</li>
						           <li class="list-group-item my-0 py-2" id="areaCode-29">세종</li>
						           <li class="list-group-item my-0 py-2" id="areaCode-31">경기</li>
						           <li class="list-group-item my-0 py-2" id="areaCode-32">강원</li>
						           <li class="list-group-item my-0 py-2" id="areaCode-33">충북</li>
						           <li class="list-group-item my-0 py-2" id="areaCode-34">충남</li>
						           <li class="list-group-item my-0 py-2" id="areaCode-35">전북</li>
						           <li class="list-group-item my-0 py-2" id="areaCode-36">전남</li>
						           <li class="list-group-item my-0 py-2" id="areaCode-37">경북</li>
						           <li class="list-group-item my-0 py-2" id="areaCode-38">경남</li>
						           <li class="list-group-item my-0 py-2" id="areaCode-39">제주</li>
						         </ul> 				
			 				</div>
			 				<div class="col-8 pl-0 pr-0" id="theaterList">			 				
					 			<ul class="list-group "></ul>			
			 				</div>
				 		</div>	
			 		</div>
			 		<div class="col-md-5">
			 			<div class="row">
			 				<div class="col-md-4 border-right pl-0 pr-0">
			 					<div class="col pl-0 pr-0" id="dateList">
			 						<h5 class="align text-center"><strong>날짜</strong></h5>
				 					<ul class="list-group list-group-flush text-center" ></ul>	
						         </div>
			 				</div>
			 				<div class="col-md-8 pl-0 pr-0" >
			 					<div class="col-md-12 pl-0 pr-0">
			 						<h5 class="align text-center"><strong>상영시간</strong></h5>			 						
			 					</div>
			 					<div class="col-md-12 pl-0 pr-0" id="timeList">	
				 					<ul class="list-group list-group-flush"></ul>
						         </div>	
						         <div class="card mt-2 pl-0 pr-0 mb-1" id="movieInfo">
								  <div class="row no-gutters">
								    <div class="col-md-5 pl-3" id="thumnail"></div>
								    <div class="col-md-7">
								      <div class="card-body pt-1">
								       <h6 class="card-subtitle font-weight-bold" id="movieNm"></h6>
								        <p class="card-text mt-2 mb-2" id="theaterNm"></p>
								        <p class="card-text mb-2" id="roomNm"></p>
								        <p class="card-text mb-2" id="schDate"></p>
								        <p class="card-text mb-2" id="schTime"></p>
								      </div>
								    </div>
								  </div>
								</div>
						         <div class="col-md-12">						    
						     	   <button type="button" class="btn btn-lg btn-block btn-warning" id="rsrvSeat">인원/좌석 선택 ></button>		
						         </div> 
						         
			 				</div>
			 			</div>
			 		</div>
			 	</div>
			 </div>
 
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
        선택하신 영화관에 원하시는 상영스케쥴이 없습니다. 계속하시겠습니까?(선택한 날짜 및 시간이 해제됩니다.)
      </div>
      <div class="modal-footer">        
      	<div class="mr-auto ml-auto">
      	   <button type="button" class="btn btn-primary" id="confirm">확인</button>
     	   <button type="button" class="btn btn-secondary" onclick="javascript:modalClose('confirmModal');"> 취소</button>
      	</div>       
      </div>
    </div>
  </div>
</div>	


<div class="modal fade" id="alertModal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">알림</h5>
        <button type="button" class="close" aria-label="Close" onclick="javascript:modalClose('alertModal');">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        영화관, 영화, 날짜, 상영시간을 선택해주세요.
      </div>
      <div class="modal-footer">        
        <button type="button" class="btn btn-secondary mr-auto ml-auto" onclick="javascript:modalClose('alertModal');">확인</button>
      </div>
    </div>
  </div>
</div>	


<div class="modal fade" id="kakaoPayModal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">알림</h5>
        <button type="button" class="close" aria-label="Close" onclick="javascript:modalClose('kakaoPayModal');">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       	
      </div>
      <div class="modal-footer">        
        <button type="button" class="btn btn-secondary mr-auto ml-auto" onclick="javascript:modalClose('kakaoPayModal');">확인</button>
      </div>
    </div>
  </div>
  
</div>	

