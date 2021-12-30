<<<<<<< HEAD
=======
	
	var fnObj = {		
		data : {
			date : "${rsrvDate}"
			,selectData : {}
			,defaultData: {
				//movieList : {}
				//,theaterList {}
				//,dateList {}
			}
		}
		,init : function(){						
			this.initData();
			$("#sidoList > ul li:first").addClass("selected");
		}
		,drawDate : function(){
			$.ajax({
				type : "GET"
				,url : "/getDate"      		
				,success : function(res) {
					console.log(res)
					var data = res.date
					var year, day, month, color	
					var html = ""
					 
					data.forEach(function(item, index){
					
						var date = item.date.replaceAll("-","");
						var dayOfWeek =item.dayOfWeek;						
						color = (dayOfWeek == '일' ?  "text-danger" : dayOfWeek == "토" ? "text-primary"  : "");
						year = date.substr(0,4);
						day  = date.substr(6,2);		
								
						if(index == 0){
							html+="<span class='list-group-item'><h5><strong>"+date.substr(4,2) +"<br> " +year +"</strong></h5></span>";
							month = date.substr(4,2);
						}
						
						//다른년도
						if(date.substr(4,4) == "0101" || date.substr(6,2) == "01"){
							html+="<span class='list-group-item'><h5><strong>"+date.substr(4,2) +"<br> " +year +"</strong></h5></span>";
						}									
						html+=  "<li class='list-group-item " +color+" font-weight-bold' >"+dayOfWeek +" "+ day+"</li>";							
					});
				
					$("#dateList ul").html(html);
			
				},error : function(err) {
					console.log("err:", err)
				}
			});
		}
		,initData : function() {
			$.ajax({
				type : "GET"
				,url : "/getSchedule"      		
				,success : function(res) {
					
					fnObj.setMovieList(res.movieList);
					fnObj.setTheaterList(res.theaterList);
					fnObj.setDateList(res.dateList);
					
					fnObj.data.defaultData = res;
					
					//fnObj.data.defaultData.movieList = res.movieList;
					//fnObj.data.defaultData.theaterList = res.theaterList;
					//fnObj.data.defaultData.dateList = res.dateList;
					
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
			
				var date = item.date.replaceAll("-","");
				var dayOfWeek =item.dayOfWeek;						
				color = (dayOfWeek == '일' ?  "text-danger" : dayOfWeek == "토" ? "text-primary"  : "");
				year = date.substr(0,4);
				day  = date.substr(6,2);		
						
				if(index == 0){
				html+="<span class='list-group-item my-0 py-2'><h5><strong>"+date.substr(4,2) +"<br> " +year +"</strong></h5></span>";
					month = date.substr(4,2);
				}
				
				//다른년도
				if(date.substr(4,4) == "0101" || date.substr(6,2) == "01"){
					html+="<span class='list-group-item my-0 py-2'><h5><strong>"+date.substr(4,2) +"<br> " +year +"</strong></h5></span>";
				}									
				html+=  "<li class='list-group-item my-0 py-2 " +color+" font-weight-bold'  date=" +date +" >"+dayOfWeek +" "+ day+"</li>";							
			});
		
			$("#dateList ul").html(html);
		}
		,selectItem : function(){		
			var paramObj = {	
					movieCode : fnObj.data.selectData.movieCd
					,thCode : fnObj.data.selectData.thCode
					,date : fnObj.data.selectData.date
			}
			var params = $.param(paramObj);
			$.ajax({
				type : "GET",
				url : "/selectSchList?"+params,	       		
				success : function(res) {
							
				},
				err : function(err) {
					console.log("err:", err)
				}
			});
		}
	}
	
	//모달창열기
	$(document).on('click',"#modal",function() {
		$('#rsrvModal .modal-content').load("rsrv");
		$('#rsrvModal').modal("show");
		fnObj.init();
	});
		
	
	//좌석선택 페이지 로드
	$(document).on('click',"#rsrvSeat",function() {
		$('#rsrvModal .modal-content').load("rsrvSeat");
		$('#rsrvModal').modal();	
	});
>>>>>>> branch 'master' of https://github.com/manics7/mv.git


<<<<<<< HEAD
 
 
=======
	//영화, 극장검색
	function filter(id,target){
	    
	    var value, item;
	    
	    //영화관검색하면 무조건 전체로 변경
	    if(target == 'theaterList'){
	    	//$("#sidoList > ul li:first").addClass("list-group-item-warning");
	    	$("#sidoList > ul li:first").click();
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
			$(this).addClass("selected"); //클릭된 부분을 상단에 정의된 CCS인 selected클래스로 적용
			$(this).css("color","white"); 
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
		fnObj.data.selectData = {"movieCd" : movieCd};		
		var movieList = fnObj.data.defaultData.movieList;		
		movieList.forEach(function(item, index){
			 if(item.movieCd == movieCd){
			 	$("#thumnail").html("<img class='img-fluid' src="+ item.poster+" >");				
			 }
		});  
		
		fnObj.selectItem();
	});
	
	//극장 클릭시 극장코드 저장
	$(document).on("click", "#theaterList li" , function(){
		var thCode= $(this).attr("thCode");
		fnObj.data.selectData = {"thCode" : thCode};
		$("#theaterNm").text($(this).text());
		fnObj.selectItem();
	});
	
	//날짜 저장
	$(document).on("click", "#dateList li" , function(){
		var date= $(this).attr("date");
		var year =  date.substr(0,4);
		var month = date.substr(4,2)
		var day  = date.substr(6,2);	
		var dayOfWeek = $(this).text().substr(0,1)	
		fnObj.data.selectData = {"date" : date};
		$("#schDate").text(year+"."+month+"."+day+" (" + dayOfWeek+")");
		fnObj.selectItem();
	});
	
	
	

	
	
>>>>>>> branch 'master' of https://github.com/manics7/mv.git
