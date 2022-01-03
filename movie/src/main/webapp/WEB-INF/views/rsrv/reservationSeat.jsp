<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resource/css/bootstrap.css">
<link rel="stylesheet" href="resource/css/rsrv.css">
<script src="resource/js/jquery-3.6.0.min.js"></script>
<script src="resource/js/jquery.serializeObject.js"></script>
<script src="resource/js/bootstrap.bundle.js"></script>
<style type="text/css">
//.btn-outline-secondary:not(:hover) {border-color:#495057; cursor: pointer; border: none;}
.btn-outline-secondary:hover {background-color: #f16a19; color: white; border: none;}
#selectSeat button{pointer-events: none;}
.modal-content {min-height: 733px;}

.cnt-info {min-height: 575px;}
.fill {   min-height: 100%;   height: 100%;}
.font-white {color: white;}
.circle1 {
    width: 15px;
    height: 15px; 
    border-radius: 70%;
    overflow: hidden;
    background-color: rgb(200, 200, 200);
}
.circle2 {
    width: 15px;
    height: 15px; 
    border-radius: 70%;
    overflow: hidden;
    background-color: rgb(204, 115, 225);
}
.circle3 {
    width: 15px;
    height: 15px; 
    border-radius: 70%;
    overflow: hidden;
    background-color: rgb(61, 63, 81);
}
.list-group li {border: none;}

</style>
<script type="text/javascript">



</script>
<head>
<link rel="stylesheet" type="text/css" href="resource/css/rsrv.css">
</head>

<div class="modal-content">
	<div class="modal-header text-white">
		<h5 class="modal-title ml-auto">
			<strong>좌석선택</strong>
		</h5>
		<div class="ml-auto">
			<button type="button" class="btn btn-outline-light bg-dark text-white font-weight-bold"
				id="reset"
			>초기화</button>
			<button class="btn btn-outline-light bg-dark text-white font-weight-bold" type="button"
				data-dismiss="modal"
			>X</button>
		</div>
	</div>

	<div class="modal-body">
		<div class="conainner">
			<div class="row">
				<div class="col-sm-4 border-right" >
						<div class="col-md-12 mx-0 px-0 cnt-info" >
							<h5 class="align text-center"><strong>인원선택</strong></h5>
							<p class="text-center">최대 8인까지 선택가능</p>
							<p class="mb-1 ml-1">일반</p>
							 <div class="btn-group mr-2" role="group" aria-label="adult group">					 	
							   <button type="button" class="btn btn-outline-secondary mx-1">1</button>
							   <button type="button" class="btn btn-outline-secondary mx-1">2</button>
							   <button type="button" class="btn btn-outline-secondary mx-1">3</button>
							   <button type="button" class="btn btn-outline-secondary mx-1">4</button>
							   <button type="button" class="btn btn-outline-secondary mx-1">5</button>
							   <button type="button" class="btn btn-outline-secondary mx-1">6</button>
							   <button type="button" class="btn btn-outline-secondary mx-1">7</button>
							   <button type="button" class="btn btn-outline-secondary mx-1">8</button>
							 </div>						 
							 <p class="mt-2 mb-1 ml-1">청소년</p>
							 <div class="btn-group mr-2" role="group" aria-label="youth group">					 	
							   <button type="button" class="btn btn-outline-secondary mx-1">1</button>
							   <button type="button" class="btn btn-outline-secondary mx-1">2</button>
							   <button type="button" class="btn btn-outline-secondary mx-1">3</button>
							   <button type="button" class="btn btn-outline-secondary mx-1">4</button>
							   <button type="button" class="btn btn-outline-secondary mx-1">5</button>
							   <button type="button" class="btn btn-outline-secondary mx-1">6</button>
							   <button type="button" class="btn btn-outline-secondary mx-1">7</button>
							   <button type="button" class="btn btn-outline-secondary mx-1">8</button>
							 </div>
						</div>
						 
						<div class="col-md-12 p-auto">
								<button type="button" class="btn btn-lg btn-block btn-dark" id="">뒤로가기</button>
						</div>
					
				</div>
				
				<div class="col-sm-8 border-right">					
					<div class="row">
						<div class="col-md-12">
							<h5 class="align text-center">
								<strong class="font-white">좌석선택</strong>
							</h5>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12" style="min-height: 400px; background-color: gray;">
							
						</div>
					</div>
					<div class="row ">
						<div class="col-md-6 mx-auto">
							<ul class="list-group list-group-horizontal">
							  <li class="list-group-item pl-1 px-2"><div class="circle1 align-middle mt-1"></div></li>
							  <li class="list-group-item pl-1"> 일반석</li>
							  <li class="list-group-item pl-1 px-2"><div class="circle2 align-middle mt-1"></div></li>
							  <li class="list-group-item pl-1"> 현재선택</li>
							  <li class="list-group-item pl-1 px-2"><div class="circle3 align-middle mt-1"></div></li>
							  <li class="list-group-item pl-1"> 예매좌석</li>
							</ul>						
						</div>
					</div>
					<div class="row align-items-end">
						<div class="col-md-7" id="selectSeat">
							<p class="pb-1 mb-1">선택한 좌석</p>
							<div class="btn-group mr-2" role="group" aria-label="adult group">					 	
							   <button type="button" class="btn btn-outline-secondary btn-lg mr-1" disabled name="seatNo">-</button>
							   <button type="button" class="btn btn-outline-secondary btn-lg mr-1" disabled name="seatNo">-</button>
							   <button type="button" class="btn btn-outline-secondary btn-lg mr-1" disabled name="seatNo">-</button>
							   <button type="button" class="btn btn-outline-secondary btn-lg mr-1" disabled name="seatNo">-</button>							 			 
							 </div>						 
							 <p class="mt-2 mb-1 ml-1"></p>
							 <div class="btn-group mr-2" role="group" aria-label="youth group">					 	
							   <button type="button" class="btn btn-outline-secondary btn-lg mr-1" disabled name="seatNo">-</button>
							   <button type="button" class="btn btn-outline-secondary btn-lg mr-1" disabled name="seatNo">-</button>
							   <button type="button" class="btn btn-outline-secondary btn-lg mr-1" disabled name="seatNo">-</button>
							   <button type="button" class="btn btn-outline-secondary btn-lg mr-1" disabled name="seatNo">-</button>				
							 </div>				
						</div>
						<div class="col-md-2">
							<p class="text-center px-0">총<span>0</span>원 </p>
						</div>
						<div class="col-md-3">
							<button type="button" class="btn btn-lg btn-block btn-dark" id="">결제수단 ></button>
						</div>
					</div>

				</div>
			</div>
		</div>

	</div>

</div>


