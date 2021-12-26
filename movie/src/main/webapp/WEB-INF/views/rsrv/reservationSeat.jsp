<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<link rel="stylesheet" type="text/css" href="resource/css/rsrv.css">
</head>
<!-- histoty Modal-->
<div class="modal-content">
	<div class="modal-header text-white">
		<h5 class="modal-title ml-auto"><strong>빠른예매</strong></h5>
		<div class="ml-auto">
			<button type="button" class="btn btn-outline-light bg-dark text-white font-weight-bold" id="reset">초기화</button>
			<!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
			<button class="btn btn-outline-light bg-dark text-white font-weight-bold" type="button" data-dismiss="modal">X</button>
		</div>
	</div>
		
	<div class="modal-body">
			 <div class="conainner">
			 	<div class="row">
			 		<div class="col-sm-4 border-right">
			 			<h5 class="align text-center"><strong>인원선택</strong></h5>
			 			<p>최대 8인까지 선택가능</p>
				        
				        				        		        
						  <div class="col-sm-12 mt-2 pr-0 overflow-auto" id="movieList">
						    <ul class="list-group list-group-flush">
					           <li class="list-group-item">Lorem</li>
					           
					           <li class="list-group-item">Dolor</li>
					         </ul>
						  </div>
						
			 		</div>
			 		
			 		<div class="col-sm-8 border-right">
			 			<h5 class="align text-center"><strong>좌석선택</strong></h5>
			 			 <div class="input-group pr-0 pl-0">
				 			 <div class="input-group-append">
	                   		 	<div class="input-group-text border-right-0 bg-transparent"><i><img alt="" src="resource/images/icon/search.svg"></i></div>
	                		</div>
	                		<input class="form-control border-left-0 border" type="search" value="영화관 검색" id="example-search-input">	                
            			</div>
							            				          
				        
			 			<div class="row mt-2">
				 			<div class="col-5 pl-0 pr-0" id="sidoList">			
				 				 <ul class="list-group">
						           <li class="list-group-item">전체</li>
						           <li class="list-group-item">서울</li>
						           <li class="list-group-item">부산</li>
						           <li class="list-group-item">대구</li>
						           <li class="list-group-item">인천</li>
						           <li class="list-group-item">광주</li>
						           <li class="list-group-item">대전</li>
						           <li class="list-group-item">울산</li>
						           <li class="list-group-item">세종</li>
						           <li class="list-group-item">경기도</li>
						           <li class="list-group-item">강원도</li>
						           <li class="list-group-item">충청북도</li>
						           <li class="list-group-item">충청남도</li>
						           <li class="list-group-item">전라북도</li>
						           <li class="list-group-item">전라남도</li>
						           <li class="list-group-item">경상북도</li>
						           <li class="list-group-item">경상남도</li>
						           <li class="list-group-item">제주도</li>
						         </ul> 				
			 				</div>
			 				<div class="col-7 pl-0 pr-0" id="theaterList">			 				
					 			<ul class="list-group ">
						           <li class="list-group-item">Lorem</li>
						           <li class="list-group-item">Ipsum</li>
						           <li class="list-group-item">Dolor</li>
						           <li class="list-group-item">Dolor</li>
						           <li class="list-group-item">Dolor</li>
						           <li class="list-group-item">Dolor</li>
						           <li class="list-group-item">Dolor</li>
						           <li class="list-group-item">Dolor</li>
						           <li class="list-group-item">Dolor</li>
						           <li class="list-group-item">Dolor</li>
						           <li class="list-group-item">Dolor</li>
						           <li class="list-group-item">Dolor</li>
						           <li class="list-group-item">Dolor</li>
						           <li class="list-group-item">Dolor</li>
						           <li class="list-group-item">Dolor</li>
						           <li class="list-group-item">Dolor</li>
						           <li class="list-group-item">Dolor</li>
						           <li class="list-group-item">Dolor</li>
						         </ul>			
			 				</div>
				 		</div>	
			 		</div>
			 		
			 	</div>
			 </div>
 
    </div>
</div>

