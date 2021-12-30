<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상영관 등록</title>
<style>
    .seat {
        width: 30px;
        height: 30px;
    }
    .clicked {
        background-color: red;
        color: white;
    }
    td{
        border : 1px solid black;
    }
</style>
<link rel="stylesheet" href="sidebar.css">
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
                    <a>영화관 이름</a>
                    <input type="text" value="${thitem.thname}" readonly><br>

                    <a>상영관 번호</a>
                    <input type="number" id="room_no"><br>

                    <a>상영관 이름</a>
                    <input type="text" id="room_name"><br>

                    <a>상영관 종류</a>
                    <select name="thcode" class="th_select">
                        <option value="-10">상영관 종류 선택</option>
                        <option>2D</option>
                        <option>3D</option>
                        <option>4D</option>
                        <option>IMAX</option>
                    </select><br>

                <form>
                    <a>상영관 좌석</a>
                    <input type="number" id="row" min="1" max="6"><br>
	                <input type="number" id="col" min="1" max="6"><br>
                    총 좌석 수 : <input type="number" id="total" readonly><br>
                    <input type="button" value="설정" onclick="seatCal()">
                    <input type="reset" value="리셋">
                    <input type="button" value="삭제" onclick="deleteDiv()">
                    <div class="screen"></div>
                    <div class="seat-wrapper" id="seat-wrapper"></div>
                </form>
            </div>
		</div>
	</div>
</div>
</section>
</body>
<script src="resource/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    var t; //행
    var n; //열
    var total; //총 좌석 수(복도, 사용불가 제외 )
    
    function seatCal(){
        t=document.getElementById("row").value;
        n=document.getElementById("col").value;
        let test = [];
        let selectedSeats = new Array();
        let selectedSeatsMap = [];
        const seatWrapper = document.querySelector(".seat-wrapper");
        let clicked = "";
        let div = "";
    

        for (let i = 0; i < n; i++) {
            div = document.createElement("div");
            seatWrapper.append(div);
            div.className = "divs";
            for (let j = 0; j < t; j++) {
                const input = document.createElement('input');
                input.type = "button";
                input.className = "seats";
                input.classList = "seat";
                //3중포문을 사용하지 않기위해 
                mapping(input, i, j);
                div.append(input);
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
                    //console.log(total);
                })
            }
        }
    }
        function mapping(input, i, j) {
            if (i === 0) {
                input.value = "A" + j;
            } else if (i === 1) {
                input.value = "B" + j;
            } else if (i === 2) {
                input.value = "C" + j;
            } else if (i === 3) {
                input.value = "D" + j;
            } else if (i === 4) {
                input.value = "E" + j;
            } else if (i === 5) {
                input.value = "F" + j;
            } else if (i === 6) {
                input.value = "G" + j;
            }
        }

        function deleteDiv() {
             const div = document.getElementsByClassName('.seat-wrapper');
  
             div.remove();
        } 
    </script>

</html> 