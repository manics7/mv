package com.example.movie.dto;

import lombok.Data;

@Data
public class BoardFileDto {
	private int review_file_no; //파일 번호
	private int review_num; //게시글 번호
	private String review_file_name; //파일 이름
}
