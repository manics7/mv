<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resource/css/bootstrap.css">
<script type="text/javascript">
window.opener.open("about:blank", "popup_window").close();
opener.parent.loadRsrvComplete('${rsrvNo}');
</script>	
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

.btn-warning {
	background-color: #f16a19;
	color: white;
	border: none;
}
#movieBox{ min-height: 400px;}
#movieInfo card-body {height:300px; border: none; padding-bottom: 0; margin-bottom: 0;}
#info {min-height:130px}
#info .card {min-height:100px}
#info .card-body {min-height:100px;}

.info_title {width: 70px;}

.list-group li {border: none;}
#amount {color: #f16a1a}

</style>

</head>
<input type="hidden" id="rsrvComplete" value="1">
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
				onclick="javascript:modalClose('rsrvModal');">X</button>
		</div>
	</div>
	<div class="modal-body pb-2">
		<div class="conainner">
			<div class="row">
					<div class="col-sm-7">	
					<div class="row">
						<div class="col-md-12">
							<div class="card pt-3 pb-3"  id="movieBox">
								<h5 class="align text-center">
									<img alt="" src="resource/images/icon/v.png">
									<strong>예매가 완료 되었습니다.</strong>
								</h5>
								<div class="row no-gutters" id="movieInfo">
									<div class="col-md-5 pl-2 " id="thumnail">
										<img class="img-fluid poster" src="https://img.cgv.co.kr/Movie/Thumbnail/Poster/000082/82479/82479_320.jpg">
									</div>
									<div class="col-md-7">
										<div class="card-body pt-1 pr-1">
											<ul class="list-group list-group-horizontal">
											  <li class="list-group-item py-2 px-0 px-0 info_title">예매번호</li>
											  <li class="list-group-item py-2 pl-3  font-weight-bold" 	id="orderId"></li>
											</ul>
											<ul class="list-group list-group-horizontal">
											  <li class="list-group-item py-2 px-0 info_title">영화</li>
											  <li class="list-group-item py-2 pl-3 font-weight-bold" id="movieNm"></li>
											</ul>
											<ul class="list-group list-group-horizontal">
											  <li class="list-group-item py-2 px-0 info_title">영화관</li>
											  <li class="list-group-item py-2 pl-3 font-weight-bold" id="theaterNm"></li>
											</ul>
											<ul class="list-group list-group-horizontal">
											  <li class="list-group-item py-2 px-0 info_title">일시</li>
											  <li class="list-group-item py-2 pl-3 font-weight-bold" id="rsrvDate"></li>
											</ul>
											<ul class="list-group list-group-horizontal">
											  <li class="list-group-item py-2 px-0 info_title" >인원</li>
											  <li class="list-group-item py-2 pl-3 font-weight-bold" id="tikecCnt"></li>
											</ul>
											<ul class="list-group list-group-horizontal">
											  <li class="list-group-item py-2 px-0 pl-0 mr-2 info_title">좌석</li>
											  <li class="list-group-item py-2 pl-2 font-weight-bold"  id="seatNo"></li>
											</ul>
											<ul class="list-group list-group-horizontal">
											  <li class="list-group-item py-2 px-0 info_title" >걸제금액</li>
											  <li class="list-group-item py-2 pl-3 font-weight-bold"  id="amount"></li>
											</ul>
											<ul class="list-group list-group-horizontal">
											  <li class="list-group-item py-2 px-0 info_title">결제수단</li>
											  <li class="list-group-item py-2 pl-3 font-weight-bold">카카오페이&nbsp;<span id="amount2"></span></li>
											</ul>							
										</div>
									</div>														
								</div>							
							</div>
						</div>
					</div>				
					
					<div class="row" id="info" >
						<div class="col-md-12">					
							<div class="card w-100 bg-light mt-3" >
							  <div class="card-body pt-1 pb-2">
							    <h6 class="card-title font-weight-bold pt-3">예매 유의사항</h6>
							    <p class="card-text">01. 영화 상영 및 시간표는 영화관 사정에 의해 변경될 수 있습니다.</p>
							    <p class="card-text">02. 온라인 에매 및 취소는 영화 상영시작 20분 전까지 가능합니다.</p>
							  </div>
							</div>						
						</div>					
					</div>		
					<div class="row">
						<div class="col-md-12 text-center pt-3">
							<button type="button" class="btn btn-lg btn-outline-secondary" id="rsrvCancel">예매취소 </button>
							<button type="button" class="btn btn-lg btn-outline-secondary ml-2">예매정보 출력 </button>
						</div>						
					</div>
									
				</div>
				<div class="col-md-5">
					<img class="img-fluid" src="resource/images/saveourcinema.jpg">
				</div>
			</div>
			
		</div>
	</div>
</div>


