<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head >
<meta charset="UTF-8">
<link rel="stylesheet" href="resource/css/bootstrap.css">
<script src="resource/js/jquery-3.6.0.min.js"></script>
<script src="resource/js/jquery.serializeObject.js"></script>
<script src="resource/js/bootstrap.bundle.js"></script>
<style type="text/css">



</style>
<script type="text/javascript">


$(document).ready(function() {
	
});


</script>
</head>
<!-- histoty Modal-->
<div class="modal-content">
	<div class="modal-header">
	    <h5 class="modal-title" id="historyModalLabel">빠른예매</h5>
	    <button type="button" class="btn btn-secondary my-2 my-sm-0">초기화</button>
	    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
	        <span aria-hidden="true">×</span>
	    </button>
	</div>
	<div class="modal-body">
	        <div class="container">
	        
	        <button type="button" class="btn btn-secondary my-2 my-sm-0" id="rsrvSeat" onclick="rsrvSeat()">좌석선택</button>
	        </div>
	</div>
	<div class="modal-footer">
	    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
	</div>
</div>
<div class="modal fade" id="MoaModal" tabindex="-1" role="dialog"	aria-labelledby="historyModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl" role="document">
			<div class="modal-content"></div>
		</div>
	</div>
<<<<<<< HEAD
=======
		
	<div class="modal-body">
			 <div class="conainner">
			 	<div class="row">
			 		<div class="col-md-4 border-right">
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
				        	<ul class="list-group list-group-horizontal">
							  <li class="list-group-item"><a href="#">가나다순</a></li>
							  <li class="list-group-item"><a href="#">예매율순</a></li>
							  <li class="list-group-item"></li>
							</ul>
				        </div>				        
						  <div class="col-md-12 mt-2 pr-0 overflow-auto" id="movieList">
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
			 						<p class="lead text-center" ><b>1관 2D</b></p>
			 					</div>
			 					<div class="col-md-12 pl-0 pr-0" id="timeList">	
				 					<ul class="list-group list-group-flush"></ul>
						         </div>	
						         <div class="card mt-4 pl-0 pr-0" id="movieInfo">
								  <div class="row no-gutters">
								    <div class="col-md-5 pl-3" id="thumnail"></div>
								    <div class="col-md-7">
								      <div class="card-body pt-1">
								       <h6 class="card-subtitle font-weight-bold" id="movieNm"></h6>
								        <p class="card-text mt-2 mb-2" id="theaterNm"></p>
								        <p class="card-text mb-2" id="roomNm">1관 2D</p>
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
</html>
>>>>>>> branch 'master' of https://github.com/manics7/mv.git


</html>