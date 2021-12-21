package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.movie.dto.Notice;
import com.example.movie.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	NoticeService noticeService;
	
	@GetMapping("/notice")
	public String notice() {
		return "notice/notice";
	}
	
	@GetMapping("/getNotice")
	@ResponseBody
	public Page<Notice> getNotice(Pageable pageable){
		Page<Notice> noticeList = noticeService.getNoticeList(pageable);
		return noticeList;
	}
}
