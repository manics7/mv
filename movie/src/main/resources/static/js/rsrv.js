	
	var fnObj = {		
		// 초기화면 세팅 데이터
		defaultData : { 
			movieList : {}
			,theaterList : {}
			,dateList  : {}
			,schDateList : {}		
			,isBack : ''	
			,rsrvNo : ''
		},
		 // 극장이나 영화정보 화면에서 선택시 넘오는 데이터
		ReceivedData : {
			movieCd : ''
			,thCode :''
			,schDate:''
			,schDetailSeq : ''
		}
		// 화면에서 선택한 값들
		,selectData : { 
			movieCd : ''
			,thCode :''
			,date : ''
			,timeIdx : '' //뒤로가기 시간선택 인덱스
			,schCode : ''
			,schDetailSeq : ''
			
		}			
		,init : function(e){			

			e.stopImmediatePropagation();
		
			fnObj.initData();
			if(fnObj.defaultData.isBack.length > 0){
				
				fnObj.getTimeList(e);
			}
			$("#areaList > ul li:first").click();			
			
		}	
		
		,initData : function() {
		
			$.ajax({
				type : "GET"
				,url : "/getSchedule"      		
				,success : function(res) {
					
					fnObj.defaultData.movieList = res.movieList;
					fnObj.defaultData.theaterList = res.theaterList;
					fnObj.defaultData.dateList = res.dateList;
					fnObj.defaultData.schDateList = res.schDateList;
					var movieCd = fnObj.ReceivedData.movieCd;
					var thCode = fnObj.ReceivedData.thCode;
					var schDate = fnObj.ReceivedData.schDate;
					

					fnObj.setMovieList(res.movieList);
					fnObj.setTheaterList(res.theaterList);
					fnObj.setDateList(res.dateList);			
					
					
					if(movieCd != ''){
						$("#movieList li[moviecd=" +movieCd + " ]" ).click();
					}	
					if(thCode != ''){
						$("#theaterList li[thcode=" +thCode + " ]" ).click();
					}	
					if(thCode != ''){
						$("#dateList li[date=" +schDate + " ]" ).click();
					}						
					
					$("#areaList > ul li:first").addClass("selected");
					//좌석선택에서 뒤로가기 했을떄 기존선택남겨주기
					if(fnObj.defaultData.isBack.length > 0){
						fnObj.backRsrv();
					}
								
				},error : function(err) {
					//console.log("err:", err)
				}
			});
		}
		,resetData : function(){		

			fnObj.ReceivedData.movieCd = "";
			fnObj.ReceivedData.thCode = "";
			fnObj.ReceivedData.schDate = "";
			fnObj.ReceivedData.schDetailSeq = "";
			fnObj.selectData.movieCd = '';			
			fnObj.selectData.thCode = '';			
			fnObj.selectData.date = '';			
			fnObj.selectData.schCode = '';			
			fnObj.selectData.schDetailSeq = '';
			fnObj.selectData.timeIdx = '';
			fnObj.defaultData.isBack = '';
			
			//결제완료후 리셋버튼 눌럿을떄는 삭제x
			if($("#rsrvComplete").val() == 1){
			return;
			}	
			if(fnObj.defaultData.rsrvNo > 0){
				fnObj.deleteRsrv();
			}		
			fnObj.defaultData.rsrvNo = '';
		}		
		,backRsrv: function(){
	
			var movieCd = fnObj.selectData.movieCd;
			var thCode = fnObj.selectData.thCode;
			var date = fnObj.selectData.date;
			if(movieCd.length > 0){
				$('#movieList li[movieCd=' + movieCd + ' ] ' ).click();
				$('#theaterList li[thCode=' + thCode + ' ] ' ).click();
				$('#dateList li[date=' + date + ' ] ' ).click();		
			}
				
		}
		,setMovieList : function(data){
			
			var html = "";			
			data.forEach(function(item, index){
				html += "<li class='list-group-item my-0 py-2'  movieCd=" +item.movieCd +" >"
				+"<i class='pr-1'><img src='resource/images/icon/"+item.watchGradeNm+".png'></i> "+item.movieNm +"</li>";	
			});
			$("#movieList ul").html(html);
		}
		,setTheaterList : function(data){	
			var html = "";
			
			data.forEach(function(item, index){		
				html += "<li class='list-group-item my-0 py-2'  thCode=" +item.thCode +" thAreacode= "+item.thAreacode+" >"+item.thName +"</li>";		
			});
			$("#theaterList ul").html(html);
		
		}
		,setDateList : function(data){
			var year, day, month, color;	
			var html = "";
			 
			data.forEach(function(item, index){
			
				var date = item.date
				var dayOfWeek =item.dayOfWeek;						
				color = (dayOfWeek == '일' ?  "text-danger" : dayOfWeek == "토" ? "text-primary"  : "");
				year = date.substr(0,4);
				day  = date.substr(8,2);		
						
				if(index == 0){
				html+="<span class='list-group-item my-0 py-2'><h5><strong>"+date.substr(5,2) +"<br> " +year +"</strong></h5></span>";
					month = date.substr(5,2);
				}else	if(date.replaceAll("-","").substr(4,4).replaceAll == "0101" || date.replaceAll("-","").substr(6,2) == "01"){
					html+="<span class='list-group-item my-0 py-2'><h5><strong>"+date.substr(5,2) +"<br> " +year +"</strong></h5></span>";
				}									
				html+=  "<li class='list-group-item my-0 py-2 " +color+" font-weight-bold'  date=" +date +" >"+dayOfWeek +" "+ day+"</li>";							
			});
		
			$("#dateList ul").html(html);
		}
		,getTimeList : function(e){		
			
			var params = $.param(fnObj.selectData);
			if(params == null){
				return;
			}
			$.ajax({
				type : "GET"
				,url : "/getTimeList?"+params      		
				,success : function(res) {
					var html= "";
					var title = "";
					var status ="";
					var rsrvSeatCnt = 0;
					
					for(var i=0; i<res.length; i++){			

						if(res[i].room.roomNo != title){
							html += "<span class='list-group-item my-0 py-2'><b>" +res[i].room.roomName+' '+res[i].room.roomClass+ "</b></span>";
						}
						title = res[i].room.roomName+'\u00A0'+res[i].room.roomClass;
						
						for(var j=0; j<res[i].scheduleDetail.length; j++){
						
							rsrvSeatCnt = res[i].room.seatCnt-res[i].scheduleDetail[j].rsrvSeatCnt 
							status = res[i].scheduleDetail[j].schStatus == 'deadline'  ? res[i].scheduleDetail[j].schStatus : '';						
							var disabled = '';
							if( status != ''){
								status = " [예매종료]";		
								disabled = "disabled"
							}							
							html += "<input type='hidden' name='schCode' value="+res[i].schCode+" />"
							html += "<input type='hidden' name='schDetailSeq' value="+res[i].scheduleDetail[j].schDetailSeq+" />"						
							html += "<li class='list-group-item my-0 py-2 "+disabled+"'  roomnm ="+ title + "><b>"+res[i].scheduleDetail[j].schDetailStart+"</b>~"+ res[i].scheduleDetail[j].schDetailEnd													
							html += status + " <span class='align-right'><b class='text-warning'>"+rsrvSeatCnt+"</b>/"+res[i].room.seatCnt+"석</span></li>";	
						}
					}
					$("#timeList ul").html(html);					
					
					var schDetailSeq = fnObj.ReceivedData.schDetailSeq;
					var timeSeq = $("#timeList input[name='schDetailSeq']");
					for(var i = 0; i<timeSeq.length; i++){
						if($(timeSeq[i]).val() ==schDetailSeq)
						$(timeSeq[i]).next("li:first").click();
					}
					
					
					if(fnObj.defaultData.isBack.length > 0){
						$('#timeList li').eq(fnObj.selectData.timeIdx).click();
					}
										
				},error : function(err) {
					console.log("err:", err)
				}
			});
		}
		,selectItem : function(e, _this){		
	
			e.stopImmediatePropagation();
			
			var params = $.param(fnObj.selectData);
			if(params == null){
				return;
			}
			$.ajax({
				type : "GET",
				url : "/selectSchList?"+params,	       		
				success : function(res) {
					if($(_this).attr("date")){
					$("#movieList li, #theaterList li").addClass("disable");	
						res.forEach(function(item){				
							$("#movieList li[movieCd=" +item.movieCd+ "]").css("cursor","pointer").removeClass("disable");	
							$("#theaterList li[thCode=" +item.thCode+ "]").css("cursor","pointer").removeClass("disable");	
						});
					}
						
					if($(_this).attr("thCode")){
						$("#movieList li, #dateList li").addClass("disable");			
						res.forEach(function(item){				
							$("#movieList li[movieCd=" +item.movieCd+ "]").css("cursor","pointer").removeClass("disable");	
							$("#dateList li[date=" +item.schDate+ "]").css("cursor","pointer").removeClass("disable");	
						});
					}
						
					if($(_this).attr("movieCd")){
						$("#theaterList li, #dateList li").addClass("disable");		
						//$("#theaterList li, #dateList li").attr("disable",true);		
						res.forEach(function(item){
							$("#theaterList li[thCode=" +item.thCode+ "]").css("cursor","pointer").removeClass("disable");	
							$("#dateList li[date=" +item.schDate+ "]").css("cursor","pointer").removeClass("disable");	
						});	
					}	
															
										
					var selectCnt = $("#movieList li.selected, #theaterList li.selected, #dateList li.selected").length;
					if(selectCnt == 3){
					fnObj.getTimeList();
					}
							
				},
				err : function(err) {
					console.log("err:", err)
				}
			});
		}
		,mask : function (){
		  //화면의 높이와 너비를 구한다.
		    var maskHeight = $(document).height();  
		    var maskWidth = $(window).width();  
			
		    //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
		    $('#mask').css({'width':758,'height':637});  
			
		    //애니메이션 효과
		    $('#mask').fadeIn(100);      
		    $('#mask').fadeTo(100,0.3);    
		}
		, getSeat : function(e){			
			
			$.ajax({
				type : "POST"
				,url : "/getSeat"
				,data : {"schCode" : fnObj.selectData.schCode, "schDetailSeq" : fnObj.selectData.schDetailSeq} 
				,success : function(res) {
					//console.log(res);
					var row = res.room.roomRow;
					var col = res.room.roomCol;
					var rsrvSeatNoList =res.rsrvSeatNoList;
					var seatList =res.seatList;
					var html = "";
					var matrix = [];
					var status = '';
					var seatNo = '';
					
					if(res.watchGrade == '청소년 관람불가'|| res.watchGrade == '18'){
						$("#youthCnt").css("display","none")
					}
					
					//화면에 보여줄 좌석을 한줄씩 만든다.
					for(var i=0; i<seatList.length; i++){							
						//로우생성
						var rowIdx = Math.floor(i/col);
						if(!matrix[rowIdx]){ 
                    		matrix[rowIdx]=[];
                    	}
                    	//로우 담을 열추가
                    	matrix[rowIdx].push({
                   				index:i
                   				, row:rowIdx
                   				, seatNo : seatList[i].seatNo
                   				, seatStatus : seatList[i].seatStatus
                   		});
                   		
					}
					//console.log(matrix);					
					for(var i=0; i<matrix.length; i++){
						html += "<div class='row'>";
						html += "<div class='col-sm-12 text-center'><span class='mr-2'><b>"+String.fromCharCode(65 + i) +"</b></span>";
					
							for(var j=0; j<matrix[i].length; j++){	
								
								status = matrix[i][j].seatStatus == 0 ? 'terrain'  : 'possible';								
								seatNo = status == 'terrain' ? ' ' : matrix[i][j].seatNo; 
								
								for(var k=0; k<rsrvSeatNoList.length; k++){
									status = ( rsrvSeatNoList[k] == matrix[i][j].seatNo ? 'reservation' : status);
								}
								html += "<a class='btn btn-outline-dark mx-1 my-1 px-0 "+ status +" ' href='#'  seatNo="+seatNo+" >"
								+ seatNo + "</a>";
							}
							
						html +=  "</div>";
						html +=  "</div>";
					}
					
					$("#seat").html(html);
					
					$(".terrain").addClass("disabled");
					var cnt = Number($("#adultCnt .active").text())+Number($("#youthCnt .active").text());
					if(cnt > 0){
						return;
					}
					
					fnObj.mask();
						
					
				},
				err : function(err) {
					console.log("err:", err)
				}
			});	
		}
		, insertReservation : function(e){
			
			var adultCnt = $("#adultCnt .active").text(); 
			var youthCnt = $("#youthCnt .active").text();	
			var selectSeat = $("#seat .active"); 
			var price = $("#price").text().replace(",","");
			var seatNo = [];
			
			for(var i=0; i<selectSeat.length; i++){
				var no = $(selectSeat[i]).attr("seatNo");
				seatNo.push(no);
			}
			console.log(seatNo);
			
			var params =	{
						"schCode" : fnObj.selectData.schCode
						,"schDetailSeq" : fnObj.selectData.schDetailSeq
						,"date" : fnObj.selectData.date
						,"adultCnt" : adultCnt
						,"youthCnt" : youthCnt
						,"price" : price
						,"seatNo" : seatNo
					}
			
			$.ajax({						
				type : "POST"
				,url : "/insertReservation"
				,data : params
				,success : function(res) {
					
					fnObj.defaultData.rsrvNo = res.rsrvNo;
				
					$('#rsrvModal .modal-content').load("rsrvPayment",function(){
					fnObj.getReservationInfo(e,  res.rsrvNo)
					});
				
					setTimeout(function(){				
						
					}, 1000); 
						
				}
				,error: function (error) {
				console.log(error.responseJSON.message);
					if(error.responseJSON.message){
						var msg = error.responseJSON.message + "\n" + "좌석을 다시 선택해주세요.";
						modalShow('alertModal',msg);
						fnObj.getSeat(e);
						$("#selectSeat input").val("");
						$("#selectSeat input").css("background-color","");
						$("#price").text("");
					} 
		       }
			});	
			
		

			
		
		}			
		, deleteRsrv : function(){
			$.ajax({						
				type : "DELETE"
				,url : "/deleteRsrv"
				,data : {"rsrvNo" : fnObj.defaultData.rsrvNo}
				,success : function(res) {		
				}
				,error: function (error) {
				console.log(error);
		       }		
			});
			
			 fnObj.defaultData.rsrvNo = '';	
					
		}
		, getReservationInfo : function(e, rsrvNo){	
			
			e.stopImmediatePropagation();
				
			$.ajax({						
				type : "POST"
				,url : "/getReservationInfo"
				,data : {"rsrvNo" : rsrvNo}
				,dataType : "json"
				,success : function(res) {	
					var movie = res.schedule.movieOfficial;
					var theater = res.schedule.theater;
					var date = res.reservation.rsrvDate.replaceAll("-","") + " (" +res.dayOfWeek+")";
					var adultCnt = res.reservation.adultCnt;
					var youthCnt = res.reservation.youthCnt;
					var adultName = adultCnt > 0 ? "일반("+ adultCnt +") " : ""
					var youthName = youthCnt > 0 ? "청소년("+youthCnt+") " : ""
					var seatNo = []
					
					$("#orderId").val(res.reservation.rsrvDate.replaceAll("-","")+"-"+res.reservation.rsrvNo+"-" +movie.movieCd);
					$("#movieNm").val(movie.movieNm);
					$("#seatCnt").val(res.reservation.reservationSeat.length);
					$("#amount").val(res.reservation.price);
					$("#rsrvNo").val(res.reservation.rsrvNo);
					
					for(var i=0; i<res.reservation.reservationSeat.length; i++){
						seatNo.push(res.reservation.reservationSeat[i].seatNo)
					}					
						$("#movieNm").text(movie.movieNm)	;
						$("#theaterNm").text(theater.thName);		
						$("#roomNm").text(res.room.roomName + " " + res.room.roomClass);		
						$("#schDate").text(date);		
						$("#schTime").text(res.ScheduleDetail.schDetailStart +" ~ "+ res.ScheduleDetail.schDetailEnd);		
						$("#tikecCnt").text(adultName+ " " + youthName);
						$("#seatNo").text(seatNo.toString().replaceAll(",",", "));
						$("#price").text(res.reservation.price.toLocaleString('ko-KR') +" 원");
				}
				,error: function (error) {
				console.log(error.responseText);
		       }
			});
			
		}
		, reservationCancel : function(e){	
		
			$.ajax({						
				type : "POST"
				,url : "/reservationCancel"
				,data : {"rsrvNo" : fnObj.defaultData.rsrvNo}
				,dataType : "json"
				,success : function(res) {	
					
				}
				,error: function (error) {
				console.log(error.responseText);
		       }
			});
		}
	}
	

	//모달창열기
	$(document).on('click',"#modal",function(e) {
		$('#rsrvModal .modal-content').load("rsrv", fnObj.init(e));
		
	});
	
	
	//좌석선택 페이지 로드
	$(document).on('click',"#rsrvSeat",function(e) {
			if($("li.selected").length < 5){
			modalShow('alertModal');
			return;
		}
		$('#rsrvModal .modal-content').load("rsrvSeat");
		setTimeout(function(){				
			fnObj.getSeat(e)
		}, 100); 
	
	});	
	
	//결제선택 페이지 로드
	$(document).on('click',"#rsrvPayment",function(e) {
	
		var adultCnt = $("#adultCnt .active").text(); 
		var youthCnt = $("#youthCnt .active").text();	
		var sumCnt = Number(youthCnt)+Number(adultCnt);
		var seatList =  $("#seat .active").length; 
		if(sumCnt != seatList){
			var msg = "관람인원과 선택 좌석 수가 동일하지 않습니다."
			modalShow('alertModal',msg);
			return;
		}
		fnObj.insertReservation(e);

	});	
	
	//결제화면에서 뒤로가기
	$(document).on('click',"#backSeat",function(e) {		
		$('#rsrvModal .modal-content').load("rsrvSeat", fnObj.getSeat(e));
		
		if($("#rsrvComplete").val() == 1){
			return;
		}			
		if(fnObj.defaultData.rsrvNo > 0){
			fnObj.deleteRsrv();	
		};	
			
	});				

	//리셋
	$(document).on('click',"#reset",function(e) {
		$('#rsrvModal .modal-content').load("rsrv");
		fnObj.resetData();
		fnObj.init(e);				
	});	
	 //모달 닫힐때;	
	 $(document).on('hidden.bs.modal','#rsrvModal', function () {
		fnObj.resetData();
	  });
	
		
	//모달화면 보였을때 
	 $(document).on('shown.bs.modal','#rsrvModal', function (e) {
	 var selectCnt = $("#movieList li.selected, #theaterList li.selected, #dateList li.selected").length;
	if(selectCnt  > 0){
		return;
	}
		fnObj.init(e);
		var target = $(e.relatedTarget);		
		var movieCd = $(target).data("moviecd");
		var thCode = $(target).data("thcode");
		var schDate = $(target).data("schdate");
		var schDetailSeq = $(target).data("schdetailseq");
		fnObj.ReceivedData.movieCd = movieCd;
		fnObj.ReceivedData.thCode = thCode;
		fnObj.ReceivedData.schDate = schDate;
		fnObj.ReceivedData.schDetailSeq = schDetailSeq;
		
	 });
	
	
	//영화검색
	$(document).on('keyup',"#searchMovie",function() {
		 //var value =$(this).val().toUpperCase();
		 //console.log($("#movieList ul > li"));
	});

	//영화, 극장검색
	function filter(id,target){
	    
	    var value, item;
	    
	    //영화관검색하면 무조건 전체로 변경
	    if(target == 'theaterList'){
	    	$("#areaList > ul li:first").addClass("list-group-item-warning");
	    	$("#areaList > ul li:first").click();
	    }
	    
	    value =$(id).val().toUpperCase();	    
	   	item = $("#"+target+" > ul > li");
	    item.each(function(){
			if($(this).text().toUpperCase().indexOf(value) > -1){
				$(this).css("display","flex");
			}else{
				$(this).css("display","none");
			}       
	    })	
	}
	
	//선택시 색상변경
	$(document).on('click',"#movieList li,#areaList li,#theaterList li	,#dateList li,#timeList li"	
	
		,function() {		
			
			$(this).addClass("selected"); //클릭된 부분을 상단에 정의된 CCS인 selected클래스로 적용
			//$(this).css("color","#212529");			
			$(this).css("background-color","#f16a1a");
			$(this).css("color","white");
			
			$(this).each(function(){	         
		   		$(this).siblings().removeClass("selected"); //siblings:형제요소들,    removeClass:선택된 클래스의 특성을 없앰
		   		$(this).siblings().css("background-color","");
		   		$(this).css("color","");
			});
	});
	//일정에 없는 영화 선택시 모달창열기
	$(document).on('click', "li.disable", function(e){		
		modalShow("confirmModal");
		
		
	});

	//영화 클릭시 영화코드 저장
	$(document).on("click", "#movieList li" , function(e){
		var movieCd= $(this).attr("movieCd");
		var src = '';
		
		$("#movieNm").text($(this).text());
		var movieList = fnObj.defaultData.movieList;		
		
		movieList.forEach(function(item, index){
			 if(item.movieCd == movieCd){
			 	item.poster == null ?  $("#thumnail").html("<img class='img-fluid'>")
			 	 : $("#thumnail").html("<img class='img-fluid' src="+ item.poster+" >");	
			 }
		});  
		
		fnObj.selectData.movieCd = movieCd;
		
		fnObj.selectItem(e, this);
	});
	
	//극장 클릭시 극장코드 저장
	$(document).on("click", "#theaterList li" , function(e){
		var thCode= $(this).attr("thCode");
		$("#theaterNm").text($(this).text());
		fnObj.selectData.thCode = thCode;		
		
		fnObj.selectItem(e, this);
		
	});
	
	//날짜 저장
	$(document).on("click", "#dateList li" , function(e){
		var date= $(this).attr("date");
		var year =  date.substr(0,4);
		var month = date.substr(5,2)
		var day  = date.substr(8,2);		
		var dayOfWeek = $(this).text().substr(0,1)	
		$("#schDate").text(year+"."+month+"."+day+" (" + dayOfWeek+")");
		fnObj.selectData.date = date;
		
		fnObj.selectItem(e, this);
	});
	
	//시간선택 시 선택정보에 값 출력
	$(document).on("click", "#timeList li" , function(){
		fnObj.selectData.timeIdx = $(this).index("#timeList li"); 
		fnObj.selectData.schCode = $(this).prev().prev().val(); //schCode
		fnObj.selectData.schDetailSeq = $(this).prev().val(); //schDetailSeq
		
		$("#roomNm").text($(this).attr("roomnm"));
		$("#schTime").text($(this).text().substr(0,11));
	});
	
	//지역선택 시 극장목록 검색
	$(document).on("click", "#areaList li" , function(){

		var theaterList = $("#theaterList li");		
		var areaCode = $(this).attr("id").substr(-2);
	    
	    theaterList.each(function(){
			if($(this).attr("thareacode") == areaCode || areaCode == '00'){
				$(this).css("display","flex");
			}else{
				$(this).css("display","none");
			}       
	    })	   
	});
	
	//뒤로가기
	$(document).on("click", "#back" , function(e){
		fnObj.defaultData.isBack = 'back';
		$('#rsrvModal .modal-content').load("rsrv", fnObj.init(e));
			
	});

	//인원수 체크
	$(document).on("click", "#youthCnt a, #adultCnt a" , function(e){
			
		$(this).addClass("active");
		$(this).siblings().removeClass("active"); 
		
		var adultCnt = parseInt($("#adultCnt .active").text()); 
		var youthCnt = parseInt($("#youthCnt .active").text());	
		var sumCnt = parseInt(youthCnt)+parseInt(adultCnt);
		var seatList =  $("#seat .active"); 
		
		if(sumCnt > 0) {
			$("#mask").hide();
		}else{
			fnObj.mask();
		}
			
		var youthPrice = Number(youthCnt*7000);
		var adultPrice = Number(adultCnt*9000);
		
		if(seatList.length > 0 && seatList.length > sumCnt){
			modalShow("alertCnt");
		}
		
		if(sumCnt > 8 ){
			if($(this).parent().attr("id") == 'adultCnt'){
				$("#adultCnt a").removeClass("active");
				$("#adultCnt a").eq(0).addClass("active");
				$("#price").html(youthPrice.toLocaleString('ko-KR'));
			}else{
				$("#youthCnt a").removeClass("active");
				$("#youthCnt a").eq(0).addClass("active");
				$("#price").html(adultPrice.toLocaleString('ko-KR'));
			}
			var msg = "최대 예매 가능한 인원수는 8명 까지 입니다.";
			modalShow('alertModal', msg);
			$("#price").html(Number(youthPrice+adultPrice).toLocaleString('ko-KR'));
			return;
		}
		$("#price").html(Number(youthPrice+adultPrice).toLocaleString('ko-KR'));
		
	});

	//좌석선택
	$(document).on("click", "#seat a" , function(e){

		$(this).toggleClass("active");
			 
		var selectCnt = Number($("#adultCnt .active").text())+Number($("#youthCnt .active").text());
		var seatList =  $("#seat .active"); 
		var selectSeat = $("input[name='seatNo']");
		
		//console.log(seatList.length + "/" + selectCnt);
		if(seatList.length > selectCnt){			 
			$(this).removeClass("active");
			return;
		}
		
		for(var i=0; i<selectSeat.length; i++){
			//	console.log($(selectSeat).eq(i).val()  +" /  액티브좌석번호 " +  $(seatList[i]).attr("seatNo"));			
			$(selectSeat).eq(i).val($(seatList[i]).attr("seatNo"));			
			if($(selectSeat).eq(i).val() == $(seatList[i]).attr("seatNo")){
				$(selectSeat).eq(i).css({"background-color":"#f16a19","color":"white"})
			}else{
				$(selectSeat).eq(i).css({"background-color":"","color":""})
			}
		}
	});
	
	$(document).on("mouseenter", "#seat a" , function(){
		//$(this).css("background-color","red");
		//$(this).next().css("background-color","red");
	});
	
	$(document).on("mouseout", "#seat a" , function(){
		//$(this).css("background-color","");
		//$(this).next().css("background-color","");
	});
	
	//모달닫기
	function modalClose(id){
	$("#"+id).modal('hide')
	}
	//모달열기
	function modalShow(id,msg){
		$("#"+id).modal('show')
		$("#"+id+" .modal-body").html(msg);
	}
	
	//결제팝업
	function popup(url){
	
		var _width = '650';
   		var _height = '580';		 
	    // 팝업을 가운데 위치시키기 위해 아래와 같이 값 구하기
	    var _left = Math.ceil(( window.screen.width - _width )/2);
    	var _top = Math.ceil(( window.screen.height - _height )/2); 
		var strWindowFeatures = "menubar=no,location=no,resizable=no,scrollbars=no,status=no";  		
	
	 	window.open(url, 'popup_window', 'width='+ _width +', height='+ _height +', left=' + _left + ', top='+ _top, strWindowFeatures);	 
	}
	
	// 카카오페이 결제정보 url 불러오기
	$(document).on("click", "#kakaoPay" , function(){

		var url = "";
		var rsrvNo = $("#rsrvNo").val();
		var orderId =	$("#orderId").val();
		var movieNm = $("#movieNm").val();
		var seatCnt =	$("#seatCnt").val();
		var amount = $("#amount").val();
		
		var params = {"orderId" : orderId
			,"movieNm" : movieNm
			,"seatCnt" : seatCnt
			,"amount" : amount
			,"rsrvNo" : rsrvNo
		}	
	
		$.ajax({						
		type : "POST"
		,url : "/kakaoPay"
		,async :false
		,dataType : "json"
		,data : {"orderId" : orderId
			,"movieNm" : movieNm
			,"seatCnt" : seatCnt
			,"amount" : amount
			,"rsrvNo" : rsrvNo
		}	
		,success : function(res) {	
			console.log(res);
			url = res.next_redirect_pc_url;
			popup(url);
					   	
			}			
		});
	});
	
	
	//카카오후 성공후 예매완료 페이지 로드
	function loadRsrvComplete(rsrvNo){
		$('.modal-content').load("reservationComplete",setRsrvComplete(rsrvNo));
	}
	
	//예매완료페이지  세팅정보
	function setRsrvComplete(rsrvNo){
		
		$.ajax({						
				type : "POST"
				,url : "/getReservationInfo"
				,data : {"rsrvNo" : rsrvNo}
				,dataType : "json"
				,success : function(res) {	
					
					$("#rsrvNo").val(res.reservation.rsrvNo);
					
					var movie = res.schedule.movieOfficial;
					var theater = res.schedule.theater;
					var date = res.reservation.rsrvDate.replaceAll("-",".") + " (" +res.dayOfWeek+")";
					var adultCnt = res.reservation.adultCnt;
					var youthCnt = res.reservation.youthCnt;
					var adultName = adultCnt > 0 ? "일반("+ adultCnt +") " : ""
					var youthName = youthCnt > 0 ? "청소년("+youthCnt+") " : ""
					var seatNo = []
					
					for(var i=0; i<res.reservation.reservationSeat.length; i++){
						seatNo.push(res.reservation.reservationSeat[i].seatNo)
					}					
						$("#orderId").text(res.reservation.rsrvDate.replaceAll("-","")+"-"+res.reservation.rsrvNo+"-" +movie.movieCd);
						$("#movieNm").text(movie.movieNm)	;
						$("#theaterNm").text(theater.thName + "  " + res.room.roomName);		
						$("#rsrvDate").text(date + " " + res.ScheduleDetail.schDetailStart +" ~ "+ res.ScheduleDetail.schDetailEnd);		
						$("#tikecCnt").text(adultName+ " " + youthName);
						$("#seatNo").text(seatNo.toString().replaceAll(",",", "));
						$("#amount").text(res.reservation.price.toLocaleString('ko-KR') +" 원");
						$("#amount2").text(res.reservation.price.toLocaleString('ko-KR') +" 원");
				}
				,error: function (error) {
				console.log(error.responseText);
		       }
			});
		
	}
	
	//예매취소
	$(document).on("click", "#rsrvCancel" , function(){
		$.ajax({						
				type : "POST"
				,url : "/paymentCancel"
				,data : "rsrvNo="+$("#rsrvNo").val() 
				//,dataType : "json"
				,success : function(res) {	
					
					$('.modal-content').load("reservationCancel",setRsrvComplete($("#rsrvNo").val()));
				}
				,error: function (error) {
				console.log(error.responseText);
		       }
		});
	});
		
	//결제성공후 모달창에 페이지 로드
	$(document).on('click',"#reservationComplete",function(e) {
		$('#rsrvModal .modal-content').load("reservationComplete");
	});
	
	
	$(document).on('click',"#confirm",function(e) {
		fnObj.resetData();
		fnObj.init(e);	
		modalClose("confirmModal");
	});