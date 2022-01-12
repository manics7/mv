<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resource/css/bootstrap.css">
<script type="text/javascript"  src="resource/js/rsrv.js"></script>
<style type="text/css">

.btn-warning {background-color: #f16a19; color: white; border: none;}
.btn-warning:hover {background-color: #f16a19; color: white; cursor: pointer; border: none;}
.btn-warning:not(:hover) {background-color: #f16a19; color: white; border: none;}

.modal-header-bg {background-color: #1d1d1d;}
.modal-content {max-height: 750px;}

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

#adultCnt .btn:hover, #youthCnt .btn:hover {background-color: #f16a19; color: white; }
#adultCnt .btn:not(:disabled):not(.disabled):active, #youthCnt .btn:not(:disabled):not(.disabled):active
, #adultCnt .btn:not(:disabled):not(.disabled).active, #youthCnt .btn:not(:disabled):not(.disabled).active
, .show > #adultCnt .btn.dropdown-toggle , .show > #youthCnt .btn.dropdown-toggle {
	background-color: #f16a19; color: white; border: none;
}

#adultCnt .btn ,#youthCnt .btn {
width: 33px;
	height: 38px;	
	border-radius: 1px;
	outline: none !important;
	box-shadow: none;
	font-size: 1em;
}


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
#selectSeat .btn{
pointer-events: none;
width: 45px;
	height: 40px;	
	border-radius: 3px;
	outline: none !important;
	box-shadow: none;
	font-size: 1em;
}

#mask {  
  position:absolute;  
  left:0;
  top:0;
  z-index:9000;  
  background-color:#000;  
  display:none;  
}

</style>
<script type="text/javascript">


$(doucment).ready(function(){
	
})

</script>
</head>
<div class="modal-content">
	<div class="modal-header text-white modal-header-bg">
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

	<div class="modal-body pb-2 pt-0">
		<div class="conainner">
			<div class="row">
				<div class="col-sm-4 border-right" >
						<div class="col-md-12 mx-0 px-0 cnt-info" >
								<h5 class="align text-center pt-2"><strong>인원선택</strong></h5>
								<p class="text-center">최대 8인까지 선택가능</p>
							<div class="row"  >	
								<div class="col-md-12" id="adultCnt">
									<p class="mb-1">일반</p>
									<a class="btn btn-outline-dark px-0 active" href='#'  >0</a>
									<a class="btn btn-outline-dark px-0" href='#'  >1</a>
									<a class="btn btn-outline-dark px-0" href='#'  >2</a>
									<a class="btn btn-outline-dark px-0" href='#'  >3</a>
									<a class="btn btn-outline-dark px-0" href='#'  >4</a>
									<a class="btn btn-outline-dark px-0" href='#'  >5</a>
									<a class="btn btn-outline-dark px-0" href='#'  >6</a>
									<a class="btn btn-outline-dark px-0" href='#'  >7</a>
									<a class="btn btn-outline-dark px-0" href='#'  >8</a>
								</div>				
							</div>				 
							 <div class="row">
							 	<div class="col-md-12" id="youthCnt">
									<p class="mt-2 mb-1">청소년</p>
									<a class="btn btn-outline-dark px-0 active" href='#'  >0</a>
									<a class="btn btn-outline-dark px-0" href='#'  >1</a>
									<a class="btn btn-outline-dark px-0" href='#'  >2</a>
									<a class="btn btn-outline-dark px-0" href='#'  >3</a>
									<a class="btn btn-outline-dark px-0" href='#'  >4</a>
									<a class="btn btn-outline-dark px-0" href='#'  >5</a>
									<a class="btn btn-outline-dark px-0" href='#'  >6</a>
									<a class="btn btn-outline-dark px-0" href='#'  >7</a>
									<a class="btn btn-outline-dark px-0" href='#'  >8</a>
								</div>							
							 </div>
						</div>
						 
						<div class="col-md-12 px-auto">
								<button  type="button" class="btn btn-lg btn-block btn-dark" id="back" >  &lt; 뒤로가기</button>
						</div>
					
				</div>
				
					<div class="col-sm-8 border-right">			
					<div id="mask">
					        <p class="white-text">관람하실 인원을 선택해주세요.</p>
					   </div>
						<div class="row">
							<div class="col-md-12 pt-2">
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
						<div class="row align-items-end mt-3">
							<div class="col-md-4" id="selectSeat">
								<p class="pb-1 mb-1">선택한 좌석</p>
								<div class="btn-group mr-2" role="group" >					 	
								   <input type="button" class="btn btn-outline-secondary btn-lg mr-1 px-0"  name="seatNo" value="" />
								   <input type="button" class="btn btn-outline-secondary btn-lg mr-1 px-0"  name="seatNo" value="" />
								   <input type="button" class="btn btn-outline-secondary btn-lg mr-1 px-0"  name="seatNo" value="" />
								   <input type="button" class="btn btn-outline-secondary btn-lg mr-1 px-0"  name="seatNo" value="" />
								 </div>						 
								 <p class="mt-2 mb-1 ml-1"></p>
								 <div class="btn-group mr-2" role="group" >					 	
								   <input type="button" class="btn btn-outline-secondary btn-lg mr-1 px-0"  name="seatNo" value="" />
								   <input type="button" class="btn btn-outline-secondary btn-lg mr-1 px-0"  name="seatNo" value="" />
								   <input type="button" class="btn btn-outline-secondary btn-lg mr-1 px-0"  name="seatNo" value="" />
								   <input type="button" class="btn btn-outline-secondary btn-lg mr-1 px-0"  name="seatNo" value="" />
								 </div>				
							</div>
							<div class="col-md-3">
								<h5 class="px-0 text-right">총&nbsp;<span id="price">0</span>&nbsp;원 </h5>
							</div>
							<div class="col-md-5">
								<button  type="button" class="btn btn-lg btn-block btn-warning" id="rsrvPayment">결제수단 ></button>
							</div>
						</div>
	
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
        <p>관람인원과 선택 좌석 수가 동일하지 않습니다.</p>
        
      </div>
      <div class="modal-footer">        
        <button type="button" class="btn btn-secondary mr-auto ml-auto" onclick="javascript:modalClose('alertModal');">확인</button>
      </div>
    </div>
  </div>
</div>	

