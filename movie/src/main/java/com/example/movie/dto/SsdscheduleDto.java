package com.example.movie.dto;

import java.util.Date;

import lombok.Data;

@Data
public class SsdscheduleDto {
private int sch_code;
private Date sch_date;
private int sch_detail_start;
private int sch_detail_end;

}
