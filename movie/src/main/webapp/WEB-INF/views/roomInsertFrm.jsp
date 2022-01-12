<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상영관 등록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="resource/css/room.css">
<script type="text/javascript">
$(function(){
	//메시지 출력 부분
	var msg = "${msg}";
	if(msg != ""){
		alert(msg);
	}
});
</script>
</head>
<body>
<section>
<div class="detail">
	<div class="inner">
		<div id="page_wrap">
			<div id="side">
				<h2><a href="#">Business Page</a></h2>
				<ul id="bupage_list">
					<li><a id="bupage_menu" href="#">영화관 관리</a></li>
					<li><a id="bupage_menu" href="#">영화 관리</a></li>
					<li><a id="bupage_menu" href="#">상영관 관리</a></li>
					<li><a id="bupage_menu" href="#">상영 일정 관리</a></li>
					<li><a id="bupage_menu" href="#">이벤트 관리</a></li>
				</ul>
			</div>
            <div class="room_div">
                <h3 id="room_title2">상영관 등록</h3>
                <form action="./roomInsert" class="seat_form" method="post" onsubmit="goSubmit()">
                    <fieldset class="title_set1">
                        <legend>
                            <div class="num_box"><b>1</b></div>
                            영화관 및 상영관 이름을 지정하세요.
                        </legend>
                        <!-- theaterList로 값 받아오기 나중에 -->
                    <a>영화관 이름</a> <input type="text" id="theater_name" name="th_code" value="${businessInfo.}" readonly><br>
                    <a>상영관 번호</a> <input type="number" id="room_no" name="room_no" required><br>
                    <a>상영관 이름</a> <input type="text" id="room_name" name="room_name" required>
                    </fieldset>
                    <fieldset class="title_set2">
                        <legend>
                            <div class="num_box"><b>2</b></div>
                            상영관 종류와 좌석을 설정하세요.
                        </legend>
                    <a>상영관 종류</a>
                    <select name="room_class" class="th_select" required>
                        <option value="-10" selected>상영관 종류</option>
                        <option value="2D">2D</option>
                        <option value="3D">3D</option>
                        <option value="4D">4D</option>
                        <option value="IMAX">IMAX</option>
                    </select><br>
                    <a>상영관 좌석</a>
                    <input type="number" placeholder="행" id="seat_row" name="room_row" min="1" max="20">
                    <input type="number" placeholder="열" id="seat_col" name="room_col" min="1" max="20"><br>
                    <input type="button" id="seatset" value="설정" onclick="seatCal()">
                    <input type="reset" id="seatreset" value="초기화" onclick="deleteDiv()">
                    </fieldset>
                    <div class="seat_total">
                        <fieldset id="seatsel">
                            <legend>
                                <div class="num_box"><b>3</b></div>
                                복도 및 사용불가 좌석을 클릭하여 선택하세요.
                            </legend>
                            <a>총 좌석 수</a><input type="number" id="total" name="seat_cnt" readonly>
                            <div class="seat-wrapper" id="seat-wrapper"></div>
                        </fieldset>
                    </div>
                    <div class="seat_sub">
                    	<input type="hidden" name="seatno" id="seatno">
                    	<input type="hidden" name="seatNot" id="seatNot">
                        <input type="submit" id="seatsub" value="등록">
                    </div>
                </form>
            </div>
		</div>
	</div>
</div>
</section>
</body>

<script type="text/javascript">
    var t; //행
    var n; //열
    var total; //총 좌석 수(복도, 사용불가 제외)
    var px = 40; //seat 하나당 px 사이즈
    var selectedSeats;
    
    function seatCal(){
        t=document.getElementById("seat_row").value;
        n=document.getElementById("seat_col").value;
        
        if(t > 20) {
        	t = 20;
        	document.getElementById("seat_row").value = 20;
        }
        if(n > 20) {
        	n = 20;
        	document.getElementById("seat_col").value = 20;
        }
        
        let test = [];
        let seatNos = new Array();
        //let selectedSeats = new Array();
        selectedSeats = new Array();
        let selectedSeatsMap = [];
        const seatWrapper = document.querySelector(".seat-wrapper");
        let clicked = "";
        let div = "";
    
        //스크린 화면 div 추가
        const screen = document.createElement("div");
        const newText = document.createTextNode("SCREEN");
        screen.appendChild(newText);
        screen.className = "screen_div";
        screen.style = "width:" + (t*px) + "px;";
        seatWrapper.append(screen);

        for (let i = 1; i <= n; i++) {
            div = document.createElement("div");
            seatWrapper.append(div);
            for (let j = 1; j <= t; j++) {
                const input = document.createElement('input');
                input.type = "button";
                input.className = "seats";
                input.classList = "seat";
                //input.name = "seatno";
                //3중포문을 사용하지 않기위해 
                mapping(input, i, j);
                div.append(input);
                seatNos.push(input.value);
                total = t * n;
                document.getElementById("total").value = total;

                //설정 버튼 비활성화
                const target = document.getElementById('seatset');
                target.disabled = true;

                input.addEventListener('click', function(e) {
                    console.log(e.target.value);
                    //중복방지 함수
                    selectedSeats = selectedSeats.filter((element, index) => selectedSeats.indexOf(element) != index);
    
                    //click class가 존재할때(제거해주는 toggle)
                    if (input.classList.contains("clicked")) {
                        input.classList.remove("clicked");
                        clicked = document.querySelectorAll(".clicked");
                 
                        selectedSeats.splice(selectedSeats.indexOf(e.target.value), 1);
                        clicked.forEach((data) => {
                            selectedSeats.push(data.value);
                        });
                    //click class가 존재하지 않을때 (추가해주는 toggle)
                    } else {
                        input.classList.add("clicked");
                        clicked = document.querySelectorAll(".clicked");
                       
                        clicked.forEach((data) => {
                            selectedSeats.push(data.value);
                        })
                    }
                    console.log(selectedSeats);
                    total = t * n - selectedSeats.length;
                    document.getElementById("total").value = total;
                })
            }
        }
        document.getElementById('seatno').value = seatNos;
        console.log('seatNos' + seatNos);
    }
        function mapping(input, i, j) {
            if (i === 1) {
                input.value = "A" + j;
            } else if (i === 2) {
                input.value = "B" + j;
            } else if (i === 3) {
                input.value = "C" + j;
            } else if (i === 4) {
                input.value = "D" + j;
            } else if (i === 5) {
                input.value = "E" + j;
            } else if (i === 6) {
                input.value = "F" + j;
            } else if (i === 7) {
                input.value = "G" + j;
            } else if (i === 8) {
                input.value = "H" + j;
            } else if (i === 9) {
                input.value = "I" + j;
            } else if (i === 10) {
                input.value = "J" + j;
            } else if (i === 11) {
                input.value = "K" + j;
            } else if (i === 12) {
                input.value = "L" + j;
            } else if (i === 13) {
                input.value = "M" + j;
            } else if (i === 14) {
                input.value = "N" + j;
            } else if (i === 15) {
                input.value = "O" + j;
            } else if (i === 16) {
                input.value = "P" + j;
            } else if (i === 17) {
                input.value = "Q" + j;
            } else if (i === 18) {
                input.value = "R" + j;
            } else if (i === 19) {
                input.value = "S" + j;
            } else if (i === 20) {
                input.value = "T" + j;
            }
        }

        function deleteDiv() {
            var div = document.getElementById('seat-wrapper');
            while ( div.hasChildNodes() ) { 
                div.removeChild( div.firstChild );
            }
            //설정 버튼 활성화
            const target = document.getElementById('seatset');
            target.disabled = false;
        } 
        
     function goSubmit(){
    	 console.log(selectedSeats);
    	 var s = document.getElementById('seatNot');
    	 s.value = selectedSeats;
     }   
</script>
</html>