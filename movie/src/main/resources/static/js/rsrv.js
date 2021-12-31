	
	var fnObj = {		
		defaultData : {
			movieList : {}
			,theaterList : {}
			,dateList  : {}
			,schDateList : {}
			,sidoCode : [
				{ code : 0 , name :"전국"}
				,{ code :11 , name :"서울"}
				,{ code :21 , name :"부산"}
				,{ code :22 , name :"대구"}
				,{ code :22 , name :"대구"}
				,{ code :23 , name :"인천"}
				,{ code :24 , name :"광주"}
				,{ code :25 , name :"대전"}
				,{ code :25 , name :"대전"}
				,{ code :26 , name :"울산"}
				,{ code :29 , name :"세종"}
				,{ code :31 , name :"경기"}
				,{ code :32 , name :"강원"}
				,{ code :33 , name :"충북"}
				,{ code :34 , name :"충남"}
				,{ code :35 , name :"전북"}
				,{ code :36 , name :"전남"}
				,{ code :37 , name :"경북"}
				,{ code :38 , name :"경남"}
				,{ code :39 , name :"제주"}				
			]			
		},
		ReceivedData : {
			movieCd : ''
			,thCode :''
		}
		,selectData : {
			movieCd : ''
			,thCode :''
			,date : ''
		}					
		,init : function(){
			
			this.selectData.movieCd = '';			
			this.selectData.thCode = '';			
			this.selectData.date = '';			
			this.initData();
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
					$("#sidoList > ul li:first").addClass("selected");		
				
					
				},error : function(err) {
					console.log("err:", err)
				}
			});
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
				html += "<li class='list-group-item my-0 py-2'  thCode=" +item.thCode +" >"+item.thName +"</li>";		
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
		,setTimeList : function(){		
			var paramObj = {	
					movieCd : fnObj.selectData.movieCd
					,thCode : fnObj.selectData.thCode
					,date : fnObj.selectData.date
			}
			var params = $.param(paramObj);
			if(params == null){
				return;
			}
			$.ajax({
				type : "GET"
				,url : "/getSchduleTime?"+params      		
				,success : function(res) {
					
					
					//res.forEach(function(item){
						
					//})
					
					
				},error : function(err) {
					console.log("err:", err)
				}
			});
		}
		,selectItem : function(){		
			var paramObj = {	
					movieCd : fnObj.selectData.movieCd
					,thCode : fnObj.selectData.thCode
					,date : fnObj.selectData.date
			}
			var params = $.param(paramObj);
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
					
					var selectCnt = $("#movieList li.selected, #theaterList li.selected, #dateList li.selected").length;
					if(selectCnt == 3){
						fnObj.setTimeList();
					}
							
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
	
	//모달화면 보였을때 
	$('#rsrvModal').on('shown.bs.modal', function (event) {
		fnObj.init();
		var target = $(event.relatedTarget);		
		var movieCd = $(target).data("movie");
		var thCode = $(target).data("thcode");
		//console.log(movieCd + "/ " + thCode );
		//fnObj.ReceivedData.movieCd = movieCd;
		//fnObj.ReceivedData.thCode = thCode;
		
	 });
	 $('#rsrvModal').on('hidden.bs.modal', function () {
		fnObj.ReceivedData.movieCd = "";
		fnObj.ReceivedData.thCode = "";
	  });
		
	
	//좌석선택 페이지 로드
	$(document).on('click',"#rsrvSeat",function() {
		$('#rsrvModal .modal-content').load("rsrvSeat");
		$('#rsrvModal').modal();	
	});

	//리셋
	$(document).on('click',"#reset",function() {
		$('#rsrvModal .modal-content').load("rsrv");
		$('#rsrvModal').modal();		
		//setTimeout(2000,function(){
		fnObj.init();
		//})
		
	});
	
	$(document).on('keyup',"#searchMovie",function() {
		 //var value =$(this).val().toUpperCase();
		 //console.log($("#movieList ul > li"));
	});
		

	//영화, 극장검색
	function filter(id,target){
	    
	    var value, item;
	    
	    //영화관검색하면 무조건 전체로 변경
	    if(target == 'theaterList'){
	    	//$("#sidoList > ul li:first").addClass("list-group-item-warning");
	    	//$("#sidoList > ul li:first").click();
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
	$(document).on('click',"#movieList li,#sidoList li,#theaterList li	,#dateList li,#timeList li"	
	
		,function() {		
			if($(this).hasClass("selected")){
				//$(this).removeClass("selected");
				//$(this).css("color","#212529");
			}else{
				$(this).addClass("selected"); //클릭된 부분을 상단에 정의된 CCS인 selected클래스로 적용
				$(this).css("color","white");
			}
			 
			$(this).each(function(){	         
		   		$(this).siblings().removeClass("selected"); //siblings:형제요소들,    removeClass:선택된 클래스의 특성을 없앰
		   		$(this).siblings().css("color","");
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
		var month = date.substr(4,2)
		var day  = date.substr(6,2);	
		var dayOfWeek = $(this).text().substr(0,1)	
		$("#schDate").text(year+"."+month+"."+day+" (" + dayOfWeek+")");
		fnObj.selectData.date = date;
		
		fnObj.selectItem();
	});
	
	
	

	
	