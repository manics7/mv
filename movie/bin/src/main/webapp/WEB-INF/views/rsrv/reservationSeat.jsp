<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resource/css/bootstrap.css">
<script src="resource/js/jquery-3.6.0.min.js"></script>
<script src="resource/js/jquery.serializeObject.js"></script>
<script src="resource/js/bootstrap.bundle.js"></script>
<style type="text/css">

.btn-warning {background-color: #f16a19; color: white; border: none;}
.btn-warning:hover {background-color: #f16a19; color: white; cursor: pointer; border: none;}
.btn-warning:not(:hover) {background-color: #f16a19; color: white; border: none;}

.modal-header {background-color: #1d1d1d;}
.modal-content {min-height: 733px;}

.cnt-info {min-height: 575px;}
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
#adultCnt label, #youthCnt label
 {
 outline-style: none; 
outline: none !important;
box-shadow: none;

}
#adultCnt label:hover, #youthCnt label:hover {background-color: #f16a19; color: white;}
#adultCnt label:not(:disabled):not(.disabled).active
, #youthCnt label:not(:disabled):not(.disabled).active {background-color: #f16a19; color: white; border: none;}

#status li {border: none;}

#seat {
 overflow: auto;
 height: auto; 
 max-height: 405px;  
}
#seat .btn {
	width: 45px;
	height: 40px;	
	border: 0px solid rgb(255, 255, 255);
	color: rgb(255, 255, 255); 
	border-radius: 3px;
	outline: none !important;
	box-shadow: none;
}
#seat .btn:not(:disabled):not(.disabled):active, 
#seat .btn:not(:disabled):not(.disabled).active, .show > #seat .btn.dropdown-toggle {
	background-color: rgb(204, 115, 225); 
}
#seat .btn:hover {
	background-color: rgb(204, 115, 225); 
	border: none; 
	outline-style: none; 
	outline: none;  
	text-decoration: none;
	 color: #fff !important;
 }


.possible {
background-color: rgb(200, 200, 200);
}

.terrain{
color: black;
background-color: black;
}
.terrain:hover{
	outline: none !important;
	box-shadow: none;
	text-decoration: none;
}
.reservation{
pointer-events: none;
background-color: rgb(51, 51, 51); 
}
#selectSeat .btn{pointer-events: none;}



</style>
<script type="text/javascript">


$(doucment).ready(function(){

})


</script>
</head>
<div class="modal-content">
	<div class="modal-header text-white">
		<h5 class="modal-title ml-auto">
			<strong>빠른예매</strong>
		</h5>
		<div class="ml-auto">
			<button class="btn btn-outline-light bg-dark text-white font-weight-bold"
				id="reset"
			>초기화</button>
			<button class="btn btn-outline-light bg-dark text-white font-weight-bold"
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
							<div class="row">								
								<p class="mb-1 ml-1">일반</p>
								 <div class="btn-group btn-group-toggle" data-toggle="buttons" id="adultCnt" aria-label="First group">
								 <label class="btn btn-outline-secondary ml-1 active">
								      <input type="radio" name="options" id="option0" value="0" checked> 0
								  </label>
								  <label class="btn btn-outline-secondary ml-1">
								      <input type="radio" name="options" id="option1" value="1"> 1
								  </label>
								  <label class="btn btn-outline-secondary ml-1">
								    <input type="radio" name="options" id="option2" value="2"> 2
								  </label>
								  <label class="btn btn-outline-secondary ml-1">
								    <input type="radio" name="options" id="option3" value="3"> 3
								    </label>
								     <label class="btn btn-outline-secondary ml-1">
								    <input type="radio" name="options" id="option4"  value="4"> 4
								  </label>
								  <label class="btn btn-outline-secondary ml-1">
								    <input type="radio" name="options" id="option5" value="5"> 5
								  </label>
								  <label class="btn btn-outline-secondary ml-1">
								    <input type="radio" name="options" id="option6" value="6"> 6
								    </label>
								    	  <label class="btn btn-outline-secondary ml-1">
								    <input type="radio" name="options" id="option7" value="7">7
								  </label>
								  <label class="btn btn-outline-secondary ml-1">
								    <input type="radio" name="options" id="option8" value="8"> 8
								  </label>							 
								</div>		
							</div>				 
							 <div class="row">
							 	<p class="mt-2 mb-1 ml-1">청소년</p>
							  <div class="btn-group btn-group-toggle" data-toggle="buttons" id="youthCnt" aria-label="second group">
							  <label class="btn btn-outline-secondary ml-1 active">
							      <input type="radio" name="options2" id="option0" value="0" checked> 0
							  </label>
							  <label class="btn btn-outline-secondary ml-1">
							      <input type="radio" name="options2" id="option1" value="1"> 1
							  </label>
							  <label class="btn btn-outline-secondary ml-1">
							    <input type="radio" name="options2" id="option2" value="2"> 2
							  </label>
							  <label class="btn btn-outline-secondary ml-1">
							    <input type="radio" name="options2" id="option3" value="3"> 3
							    </label>
							     <label class="btn btn-outline-secondary ml-1">
							    <input type="radio" name="options2" id="option4" value="4"> 4
							  </label>
							  <label class="btn btn-outline-secondary ml-1">
							    <input type="radio" name="options2" id="option5" value="5"> 5
							  </label>
							  <label class="btn btn-outline-secondary ml-1">
							    <input type="radio" name="options2" id="option6" value="6"> 6
							    </label>
							    	  <label class="btn btn-outline-secondary ml-1">
							    <input type="radio" name="options2" id="option7"  value="7">7
							  </label>
							  <label class="btn btn-outline-secondary ml-1">
							    <input type="radio" name="options2" id="option8" value="8"> 8
							  </label>							 
							</div>	
							 </div>
						</div>
						 
						<div class="col-md-12 px-auto">
								<button  type="button" class="btn btn-lg btn-block btn-dark" id="back" >뒤로가기</button>
						</div>
					
				</div>
				
				<div class="col-sm-8 border-right">					
					<div class="row">
						<div class="col-md-12">
							<h5 class="align text-center">
								<strong class="">좌석선택</strong>
							</h5>
						</div>
					</div>
					<div class="row align-items-center" style="min-height: 405px;">
						<div class="col-md-12" id="seat">							
						</div>
					</div>
					<div class="row ">
						<div class="col-md-6 mx-auto" id="status">
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
						<div class="col-md-4" id="selectSeat">
							<p class="pb-1 mb-1">선택한 좌석</p>
							<div class="btn-group mr-2" role="group" >					 	
							   <input type="button" class="btn btn-outline-secondary btn-lg mr-1"  name="seatNo" value="-" />
							   <input type="button" class="btn btn-outline-secondary btn-lg mr-1"  name="seatNo" value="-" />
							   <input type="button" class="btn btn-outline-secondary btn-lg mr-1"  name="seatNo" value="-" />
							   <input type="button" class="btn btn-outline-secondary btn-lg mr-1"  name="seatNo" value="-" />
							 </div>						 
							 <p class="mt-2 mb-1 ml-1"></p>
							 <div class="btn-group mr-2" role="group" >					 	
							   <input type="button" class="btn btn-outline-secondary btn-lg mr-1"  name="seatNo" value="-" />
							   <input type="button" class="btn btn-outline-secondary btn-lg mr-1"  name="seatNo" value="-" />
							   <input type="button" class="btn btn-outline-secondary btn-lg mr-1"  name="seatNo" value="-" />
							   <input type="button" class="btn btn-outline-secondary btn-lg mr-1"  name="seatNo" value="-" />
							 </div>				
						</div>
						<div class="col-md-3">
							<h5 class="px-0 text-right">총&nbsp;<span id="price">0</span>&nbsp;원 </h5>
						</div>
						<div class="col-md-5">
							<button  type="button" class="btn btn-lg btn-block btn-warning" id="">결제수단 ></button>
						</div>
					</div>

				</div>
			</div>
		</div>

	</div>

</div>


