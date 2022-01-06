	
	var fnObj = {		
		defaultData : {
			movieList : {}
			,theaterList : {}
			,dateList  : {}
			,schDateList : {}		
			,history : ''	
		},
		ReceivedData : {
			movieCd : ''
			,thCode :''
		}
		,selectData : {
			movieCd : ''
			,thCode :''
			,date : ''
			,time : ''
			,schCode : ''
			,schDetailSeq : ''
			
		}			
		,init : function(){			
			fnObj.initData();
			if(fnObj.defaultData.history.length > 0){
				
				fnObj.getTimeList();
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
					
					fnObj.setMovieList(res.movieList);
					fnObj.setTheaterList(res.theaterList);
					fnObj.setDateList(res.dateList);			
					
					
					if(movieCd != ''){
						$('#movieList li[movieCd=' +movieCd + ' ] ' ).click();
					}	
					$("#areaList > ul li:first").addClass("selected");
					
					fnObj.history();			
				},error : function(err) {
					//console.log("err:", err)
				}
			});
		}
		,resetData : function(){
			fnObj.ReceivedData.movieCd = "";
			fnObj.ReceivedData.thCode = "";
			fnObj.selectData.movieCd = '';			
			fnObj.selectData.thCode = '';			
			fnObj.selectData.date = '';			
			fnObj.selectData.schCode = '';			
			fnObj.selectData.schDetailSeq = '';
			fnObj.selectData.time = '';
			fnObj.defaultData.history = '';
		}		
		,history: function(){
	
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
				+"<i class='pr-1'><img src='resource/images/icon/grade_"+item.watchGradeNm+".png'></i> "+item.movieNm +"</li>";	
			});
			$("#movieList ul").html(html);
		}
		,setSido : function() {
			
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
		,getTimeList : function(){		
			
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
						
							if( status != ''){
								status = " [예매종료]";		
							}							
							html += "<input type='hidden' name='schCode' value="+res[i].schCode+" />"
							html += "<input type='hidden' name='schDetailSeq' value="+res[i].scheduleDetail[j].schDetailSeq+" />"						
							html += "<li class='list-group-item my-0 py-2'  roomnm ="+ title + "><b>"+res[i].scheduleDetail[j].schDetailStart+"</b>~"+ res[i].scheduleDetail[j].schDetailEnd													
							html += status + " <span class='align-right'><b class='text-warning'>"+rsrvSeatCnt+"</b>/"+res[i].room.seatCnt+"석</span></li>";	
						}
					}
					$("#timeList ul").html(html);					
					
					if(fnObj.defaultData.history.length > 0){
						$('#timeList li').eq(fnObj.selectData.time).click();
					}
										
				},error : function(err) {
					console.log("err:", err)
				}
			});
		}
		,selectItem : function(){		
			
			var params = $.param(fnObj.selectData);
			if(params == null){
				return;
			}
			$.ajax({
				type : "GET",
				url : "/selectSchList?"+params,	       		
				success : function(res) {
									
					if($("#dateList li").hasClass("selected")){				
						$("#movieList li, #theaterList li").addClass("disabled");	
						res.forEach(function(item){				
							$("#movieList li[movieCd=" +item.movieCd+ "]").css("cursor","pointer").removeClass("disabled");	
							$("#theaterList li[thCode=" +item.thCode+ "]").css("cursor","pointer").removeClass("disabled");	
						});
					}
					
					if($("#theaterList li").hasClass("selected")){			
						$("#movieList li, #dateList li").addClass("disabled");			
						res.forEach(function(item){				
							$("#movieList li[movieCd=" +item.movieCd+ "]").css("cursor","pointer").removeClass("disabled");	
							$("#dateList li[date=" +item.schDate+ "]").css("cursor","pointer").removeClass("disabled");	
						});
					}
					
					if($("#movieList li").hasClass("selected")){
						$("#theaterList li, #dateList li").addClass("disabled");		
						res.forEach(function(item){
							$("#theaterList li[thCode=" +item.thCode+ "]").css("cursor","pointer").removeClass("disabled");	
							$("#dateList li[date=" +item.schDate+ "]").css("cursor","pointer").removeClass("disabled");	
						});											
					}	
					$("li.disabled")
					
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
		
		, getSeat : function(){
			
			$.ajax({
				type : "POST"
				,url : "/getSeat"
				,data : {"schCode" : fnObj.selectData.schCode, "schDetailSeq" : fnObj.selectData.schDetailSeq}	       		
				,success : function(res) {
					console.log(res);
					var row = res.room.roomRow;
					var col = res.room.roomCol;
					var rsrvSeatNoList =res.rsrvSeatNoList;
					var seatList =res.seatList;
					var html = "";
					var matrix = [];
					var status = '';
					var seatNo = '';
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
					console.log(matrix);					
					for(var i=0; i<matrix.length; i++){
						html += "<div class='row'>";
						html += "<div class='col-sm-12 text-center'><span class='mr-2'><b>"+String.fromCharCode(65 + i) +"</b></span>";
					
							for(var j=0; j<matrix[i].length; j++){	
								
								status = matrix[i][j].seatStatus == 1 ? 'terrain'  : 'possible';								
								seatNo = status == 'terrain' ? ' ' : matrix[i][j].seatNo; 
								
								for(var k=0; k<rsrvSeatNoList.length; k++){
									status = ( rsrvSeatNoList[k] == matrix[i][j].seatNo ? 'reservation' : status);
								}
								html += "<a class='btn btn-outline-dark mx-1 my-1 px-0 "+ status +" ' href='#'  seatNo="+seatNo+" data-toggle='button'  >"
								+ seatNo + "</a>";
							}
							
						html +=  "</div>";
						html +=  "</div>";
					}
					
					$("#seat").html(html);
					
					$(".terrain").addClass("disabled");
				},
				err : function(err) {
					console.log("err:", err)
				}
			});	
		}		
		
	}
	
	//모달창열기
	$(document).on('click',"#modal",function(event) {
		$('#rsrvModal .modal-content').load("rsrv");		
	});
	//좌석선택 페이지 로드
	$(document).on('click',"#rsrvSeat",function() {
		
		$('#rsrvModal .modal-content').load("rsrvSeat", fnObj.getSeat);
		//$('#rsrvModal .modal-content').load("rsrvSeat", {"schCode" : fnObj.selectData.schCode, "schDetailSeq" : fnObj.selectData.schDetailSeq});
		//$('#rsrvModal').modal();	
	});			

	//리셋
	$(document).on('click',"#reset",function() {
		$('#rsrvModal .modal-content').load("rsrv");		
		fnObj.resetData();
		fnObj.init();				
	});	
	
	 $('#rsrvModal').on('hidden.bs.modal', function () {
		fnObj.resetData();
	  });
	
	//모달화면 보였을때 
	$('#rsrvModal').on('shown.bs.modal', function (event) {
		fnObj.init();
		var target = $(event.relatedTarget);		
		var movieCd = $(target).data("movie");
		var thCode = $(target).data("thcode");
		//fnObj.ReceivedData.movieCd = movieCd;
		//fnObj.ReceivedData.thCode = thCode;
		
	 });
	
	$(document).on('keyup',"#searchMovie",function() {
		 //var value =$(this).val().toUpperCase();
		 //console.log($("#movieList ul > li"));
	});
	
	$(document).on('click',"ul.disabled",function(event) {
		//$('#confirm').modal();
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
	
	//영화 클릭시 영화코드 저장
	$(document).on("click", "#movieList li" , function(){
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
		
		fnObj.selectItem();
	});
	
	//극장 클릭시 극장코드 저장
	$(document).on("click", "#theaterList li" , function(){
		var thCode= $(this).attr("thCode");
		$("#theaterNm").text($(this).text());
		fnObj.selectData.thCode = thCode;
		
		fnObj.selectItem();
		
	});
	
	//날짜 저장
	$(document).on("click", "#dateList li" , function(){
		var date= $(this).attr("date");
		var year =  date.substr(0,4);
		var month = date.substr(5,2)
		var day  = date.substr(8,2);		
		var dayOfWeek = $(this).text().substr(0,1)	
		$("#schDate").text(year+"."+month+"."+day+" (" + dayOfWeek+")");
		fnObj.selectData.date = date;
		
		fnObj.selectItem();
	});
	
	//시간선택 시 선택정보에 값 출력
	$(document).on("click", "#timeList li" , function(){
		fnObj.selectData.time = $(this).index("#timeList li"); 
		fnObj.selectData.schCode = $(this).prev().prev().val(); //schCode
		fnObj.selectData.schDetailSeq = $(this).prev().prev().val(); //schDetailSeq
		
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


	$(document).on("click", "#confirm" , function(){
	});	
	
	$(document).on("click", "#back" , function(){
		fnObj.defaultData.history = 'history';
		$('#rsrvModal .modal-content').load("rsrv",fnObj.init());		
	});
	//$("#youth button")
	$(document).on("click", "#youthCnt, #adultCnt" , function(e){
	
		var youthCnt = $('#youthCnt input:radio:checked').val();
		var adultCnt = $('#adultCnt input:radio:checked').val();
		var sumCnt = Number(youthCnt)+Number(adultCnt);
		
		var youthPrice = Number(youthCnt*7000);
		var adultPrice = Number(adultCnt*9000);
		
		if(sumCnt > 8 ){
			if($(this).attr("aria-label") == 'First group'){
				$("#adultCnt label").removeClass("active");
				$("#adultCnt label").eq(0).addClass("active");
				$("#price").html(youthPrice.toLocaleString('ko-KR'));
			}else{				
				$("#youthCnt label").removeClass("active");
				$("#youthCnt label").eq(0).addClass("active"); 
				$("#price").html(adultPrice.toLocaleString('ko-KR'));
			}		
			e.preventDefault();
			alert("최대 8명");
			
			return;
		}
	
		$("#price").html(Number(youthPrice+adultPrice).toLocaleString('ko-KR'));
				
	
	});
	
