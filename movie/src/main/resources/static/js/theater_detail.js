$(document).ready(function () {

  
    console.log("getDate : " + getDate());
    function getDate() {
        $.ajax({
            type: "GET"
            , url: "/getDate"
            , success: function (data) {
                var html = "";
                data.forEach(function (item, index) {

                    var date = item.date
                    var dayOfWeek = item.dayOfWeek;
                    color = (dayOfWeek == '일' ? "text-danger" : dayOfWeek == "토" ? "text-primary" : "");
                    day = date.substr(8, 2);
                    html += "<li class='list-group-item my-0 py-2 dateBtn " + color + " font-weight-bold'  date=" + date + " style ='cursor : pointer;'    >" + day + " (" + dayOfWeek + ")</li>";
                });
                $(".day_paging ul").html(html);
            }, error: function (err) {
                //console.log("err:", err)
            }
        });
    }


    $(document).on('click', ".dateBtn", function () {
        var date = $(this).attr("date");
        $.ajax({
            type: "GET"
            , url: "getSchedulelist?schDate=" + date + "&thCode=" + ${ th_code }
            , success : function (data) {
                var html = "";

                for (i = 0; i < data.length; i++) {

                    for (j = 0; j < data[i].schedule.scheduleDetail.length; j++) {
                        html += "<li><div>"

                        html += "<p class='stime'>" + data[i].schedule.scheduleDetail[j].schDetailStart + "</p>"
                        html += "<p class='etime'>" + data[i].schedule.scheduleDetail[j].schDetailEnd + "</p>"
                        html += "<p class='seat'><b>" + data[i].schedule.scheduleDetail[j].rsrvSeatCnt
                            + "</b>/ " + data[i].room.seatCnt + " 석</p>"
                        html + "</div></li>"
                        html += "<p class = mv_info>" + data[i].room.roomName + "-" + data[i].room.roomClass + "</p>"
                        html += "<p class = mv_title>" + data[i].movieOfficial.movieNm + "</p>"
                    }
                }
                $(".movie_schedule_list ul").html(html);
            }
            , error : function (err) {
                //console.log("err:", err)
            }
            });

});
    
    });



