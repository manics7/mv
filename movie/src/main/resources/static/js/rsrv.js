	
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

	//리셋
	$(document).on('click',"#reset",function() {
		$('#rsrvModal .modal-content').load("rsrv");
		$('#rsrvModal').modal();		
		fnObj.init();
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
	$(document).on('click',"#movieList > ul li,#sidoList > ul li,#theaterList > ul li	,#dateList > ul li,#timeList > ul li"	
	
		,function() {					
			$(this).addClass("selected"); //클릭된 부분을 상단에 정의된 CCS인 selected클래스로 적용
			$(this).each(function(){	         
		   		$(this).siblings().removeClass("selected"); //siblings:형제요소들,    removeClass:선택된 클래스의 특성을 없앰
			});
	});
	
	
	
var fnObj = {		
		data : {
			date : "${rsrvDate}"
		}
		,init : function(){			
			this.setting();
		}
		,setting : function(){
			$.ajax({
				type : "GET"
				,url : "/getData"      		
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
	}
	
	