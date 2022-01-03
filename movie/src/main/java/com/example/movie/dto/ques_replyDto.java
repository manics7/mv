package com.example.movie.dto;

import java.sql.Timestamp;


import lombok.Data;

@Data
public class ques_replyDto {
private int ques_reply_no;
private int ques_no;
private String ques_reply_cont;
private String ques_reply_date;
private String ques_reply_title;
}
