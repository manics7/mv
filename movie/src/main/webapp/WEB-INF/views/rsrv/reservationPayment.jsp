<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resource/css/bootstrap.css">

<style type="text/css">

.modal-header-bg {background-color: #1d1d1d;}
.modal-content {max-height: 750px;}

.form-control:focus {
    outline: 0 !important;
    border-color: initial;
    box-shadow: none;
}

.btn-success:hover, .btn-success:active, .btn-success:focus {
  color: #ffffff !important;
  background-color: #1F2838 !important;
  border-color: #494F57 !important;
}

.btn-warning {background-color: #f16a19; color: white; border: none;}
.btn-warning:hover {background-color: #f16a19; color: white; cursor: pointer; border: none;}
.btn-warning:not(:hover) {background-color: #f16a19; color: white; border: none;}
.text-warning {color: #f16a1a;}

.modal-header {background-color: #1d1d1d;}

.btn-warning {
	background-color: #f16a19;
	color: white;
	border: none;
}

.btn-warning:hover {
	background-color: #f16a19;
	color: white;
	cursor: pointer;
	border: none;
}

.btn-warning:not(:hover) {
	background-color: #f16a19;
	color: white;
	border: none;
}

.collapsible-link::before {
	content: '';
	width: 14px;
	height: 2px;
	background: #333;
	position: absolute;
	top: calc(50% - 1px);
	right: 1rem;
	display: block;
	transition: all 0.3s;
}

/* Vertical line */
.collapsible-link::after {
	content: '';
	width: 2px;
	height: 14px;
	background: #333;
	position: absolute;
	top: calc(50% - 7px);
	right: calc(1rem + 6px);
	display: block;
	transition: all 0.3s;
}

.collapsible-link[aria-expanded='true']::after {
	transform: rotate(90deg) translateX(-1px);
}

.collapsible-link[aria-expanded='true']::before {
	transform: rotate(180deg);
}

.btn-warning {
	background-color: #f16a19;
	color: white;
	border: none;
}

.btn-warning:hover {
	background-color: #f16a19;
	color: white;
	cursor: pointer;
	border: none;
}

.btn-warning:not(:hover) {
	background-color: #f16a19;
	color: white;
	border: none;
}

#payment-group .btn {
	height: 40px;
	width: 150px;
}
#accordionExample { height: 570px;}
#accordionExample .card{ height: 550px; border: none;}


#movieInfo {height: 535px; border: none;}

</style>

<script type="text/javascript">

</script>

</head>
<div class="modal-content">
	<div class="modal-header text-white modal-header-bg">
		<h5 class="modal-title ml-auto">
			<strong>빠른예매</strong>
		</h5>
		<div class="ml-auto">
			<button
				class="btn btn-outline-light bg-dark text-white font-weight-bold"
				id="reset">초기화</button>
			<button
				class="btn btn-outline-light bg-dark text-white font-weight-bold"
				data-dismiss="modal">X</button>
		</div>
	</div>

	<div class="modal-body pb-2">
		<div class="conainner">
			<div class="row">
				<div class="col-sm-8 border-right">
					<div id="accordionExample" class="accordion">
						
						<!-- <div class="card">
								<div id="headingOne" class="card-header bg-white shadow-sm border-0">
								<h5 class="mb-0 font-weight-bold">
									<span data-toggle="collapse" data-target="#collapseOne"
										aria-expanded="true" aria-controls="collapseOne"
										class="d-block position-relative text-dark text-uppercase collapsible-link py-2">
										01 할인</span>
								</h5>
							</div>
							<div id="collapseOne" aria-labelledby="headingOne" data-parent="#accordionExample" class="collapse show">
								<div class="card-body"></div>														
							</div>
						</div> -->
						<div class="card">						
							<div id="headingTwo" class="card-header bg-white shadow-sm border-0">
								<h5 class="mb-0 font-weight-bold">
									<span data-toggle="collapse" data-target="#collapseTwo"
										aria-expanded="true" aria-controls="collapseTwo"
										class="d-block position-relative text-dark text-uppercase collapsible-link py-2">
										01 최종결제 수단</span>
								</h5>
							</div>
							<div id="collapseTwo" aria-labelledby="headingTwo" data-parent="#accordionExample" class="collapse show">
								<div class="card-body pl-2">
									<p class="font-weight-light m-0"></p>
									<div class="btn-group btn-group-toggle" data-toggle="buttons" id="payment-group" >  
										<label class="btn btn-outline-secondary active">
											<input type="radio" name="options" id="option1" checked>카드(번호입력)
										</label>
										<label class="btn btn-outline-secondary">
											<input type="radio" name="options" id="option2"> 카드(간편결제)
										</label>
										<label class="btn btn-outline-secondary">
											<input type="radio" name="options" id="option3" onclick=""> 카카오페이
										</label>
										<label class="btn btn-outline-secondary">
											<input type="radio" name="options" id="option4"> 페이코
										</label>
									</div>								
								</div>		
								<div class="col-md-12">
									<form >										
									 	 <div class="form-group row">
										 	<label for="cardGubun" class="col-2">카드 구분</label>				
										 	<div class="col-sm-10 px-4">	
												<input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1" value="option1" checked>
												<label class="form-check-label" for="exampleRadios1"> 일반 </label>
											</div>									 
										</div>
										 <div class="form-group row">
										 	<label for="inputState" class="col-2">카드종류</label>
										 	<div class="col-sm-10 pl-0">													
										     	<select id="inputState" class="form-check-control">
										   	  		<option selected>일반</option>
										     	</select>		
										     </div>					 
										</div>
									   <div class="form-group row">
									     <label for="inputCity" class="col-2">카드번호</label>										    
									     <input type="number" class="form-control col-1 mr-1" id="cardNum1" maxlength="4"  >
									     <input type="number" class="form-control col-1 mr-1" id="cardNum1" maxlength="4">
									     <input type="number" class="form-control col-1 mr-1 " id="cardNum1" maxlength="4">
									     <input type="number" class="form-control col-1" id="cardNum1" maxlength="4">
									  </div>
									  <div class="form-group row">
									     <label for="inputCity" class="col-2">비밀번호</label>										    
									     <input type="number" class="form-control col-1 mr-1" id="cardNum1" maxlength="4">
									 	 <label for="inputCity" class="col-2 pt-2">**</label>					
									  </div>
									   <div class="form-group row">
									     <label for="inputCity" class="col-2">유효기간</label>										    
									     <input type="number" class="form-control mr-1 col-2" id="cardNum1" placeholder="MM" maxlength="2">
									     <input type="number" class="form-control mr-1 col-2" id="cardNum1" placeholder="YY" maxlength="2">
									  </div>
									  <div class="form-group row">
									     <label for="inputCity" class="col-2">생년월일</label>										    
									     <input type="number" class="form-control mr-1 col-2" id="cardNum1" placeholder="생년월일 6자리" maxlength="6">
									  </div>
									</form>
								</div>						
							</div>

						</div>
					</div>
					<div class="col-md-12">
						<button type="button" class="btn btn-lg btn-dark" id="backSeat">&lt; 인원/좌석 선택</button>
					</div>
				
				</div>
				<div class="col-sm-4">					
					<div class="card pt-3" id="movieInfo" >
						<h5 class="align text-center pb-3">
							<strong>최종결제정보</strong>
						</h5>
						<div class="row no-gutters">
							<div class="col-md-5 pl-3" id="thumnail">
								<img class="img-fluid" src="https://img.cgv.co.kr/Movie/Thumbnail/Poster/000082/82479/82479_320.jpg">
							</div>
							<div class="col-md-7">
								<div class="card-body pt-1">
									<h6 class="card-subtitle font-weight-bold" id="movieNm"></h6>
									<p class="card-text mt-2 mb-2" id="theaterNm"></p>
									<p class="card-text mb-2" id="roomNm"></p>
									<p class="card-text mb-2" id="schDate"></p>
									<p class="card-text mb-2" id="schTime"></p>
									<p class="card-text mb-2" id="tikecCnt"></p>
									<p class="card-text mb-2" id="seatNo"></p>
								</div>
							</div>														
						</div>	
					</div>
					<div class="row ">
					<!-- 	<div class="col-8"><p>남은 결제금액</p></div>
						<div class="col-4"><p>7000 원</p></div> -->
						<div class="col-8"><h5 class="font-weight-bold">총 결제금액</h5></div>
						<div class="col-4"><h5 class="font-weight-bold" style="color: #f16a1a;" id="price">7000 원</h5></div>	
						<div class="col-md-12 text-center">						
							<button type="button" class="btn btn-lg btn-block btn-warning" id="rsrvPayment">결제수단 ></button>
						</div>						
					</div>
				</div>
			</div>
		</div>


	</div>
</div>



