package com.example.movie.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Theater_detailDto {
public String movie_nm;
Timestamp sch_date;
Timestamp sch_time;
Timestamp sch_detail_start;
Timestamp sch_detail_end;
}
