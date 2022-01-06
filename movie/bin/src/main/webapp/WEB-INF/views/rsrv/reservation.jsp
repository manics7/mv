<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head >
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="resource/css/rsrv.css">
<script type="text/javascript">

	       
$(document).ready(function() {

		fnObj.init();
	
});


</script>
</head>
<!-- histoty Modal-->
<div class="modal-content">
	<div class="modal-header text-white">
		<h5 class="modal-title ml-auto"><strong>빠른예매</strong></h5>
		<div class="ml-auto">
			<button type="button" class="btn btn-outline-light bg-dark text-white" id="reset">예매 다시하기</button>
			<!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
			<button class="btn btn-outline-light bg-dark text-white font-weight-bold" type="button" data-dismiss="modal">X</button>
		</div>
	</div>
		
	<div class="modal-body">
			 <div class="conainner">
			 	<div class="row">
			 		<div class="col-md-4 px-0 border-right">
			 			<h5 class="align text-center"><strong>영화</strong></h5>
				         <div class="input-group col-md-12">
				           <span class="input-group-append">
				               <button class="btn btn-outline-secondary border-right-0 border" type="button">
				                   <i><img alt="" src="resource/images/icon/search.svg"> </i>
				               </button>
				            </span>
				            <input class="form-control border-left-0 border" type="search" placeholder="영화명 검색" id="search" onkeyup="filter(this,'movieList');">					          
				        </div>
				        
				        <div class="col-md-12 mt-2" id="mvSelectBox">				        
				        	<ul class="nav nav-tabs">
							  <li class="nav-item">
							    <a class="nav-link active" href="#">가나다순</a>
							  </li>
							  <li class="nav-item dropdown">
							    <a class="nav-link"  href="#" >예매율순</a>							   
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
				 			<div class="col-4 px-0" id="sidoList">			
				 				 <ul class="list-group">
						           <li class="list-group-item my-0 py-2">전체</li>
						           <li class="list-group-item my-0 py-2">서울</li>
						           <li class="list-group-item my-0 py-2">부산</li>
						           <li class="list-group-item my-0 py-2">대구</li>
						           <li class="list-group-item my-0 py-2">인천</li>
						           <li class="list-group-item my-0 py-2">광주</li>
						           <li class="list-group-item my-0 py-2">대전</li>
						           <li class="list-group-item my-0 py-2">울산</li>
						           <li class="list-group-item my-0 py-2">세종</li>
						           <li class="list-group-item my-0 py-2">경기</li>
						           <li class="list-group-item my-0 py-2">강원</li>
						           <li class="list-group-item my-0 py-2">충북</li>
						           <li class="list-group-item my-0 py-2">충남</li>
						           <li class="list-group-item my-0 py-2">전북</li>
						           <li class="list-group-item my-0 py-2">전남</li>
						           <li class="list-group-item my-0 py-2">경북</li>
						           <li class="list-group-item my-0 py-2">경남</li>
						           <li class="list-group-item my-0 py-2">제주</li>
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
				 					<ul class="list-group list-group-flush">
				 					<p class="lead text-center" ><b name="roomNm">1관 2D</b></p>
				 					</ul>
						         </div>	
						         <div class="card mt-4 pl-0 pr-0" id="movieInfo">
								  <div class="row no-gutters">
								    <div class="col-md-5 pl-3" id="thumnail"></div>
								    <div class="col-md-7">
								      <div class="card-body pt-1">
								       <h6 class="card-subtitle font-weight-bold" id="movieNm"></h6>
								        <p class="card-text mt-2 mb-2" id="theaterNm"></p>
								        <p class="card-text mb-2" name="roomNm">1관 2D</p>
								        <p class="card-text mb-2" id="schDate"></p>
								        <p class="card-text mb-2" id="schTime">11:30 ~13:22</p>
								      </div>
								    </div>
								  </div>
								</div>
						         <div class="col-md-12 mt-2">
						     	    <button type="button" class="btn btn-lg btn-block btn-warning" id="rsrvSeat">인원/좌석 선택 ></button>
						         </div> 
						         
			 				</div>
			 			</div>
			 		</div>
			 	</div>
			 </div>
 
    </div>
</div>
<div class="modal fade" id="rsrvSeat" tabindex="-1" role="dialog"	aria-labelledby="label" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog modal-xl modal-dialog-centered" role="document">
			<div class="modal-content"></div>
		</div>
	</div>

</html>
