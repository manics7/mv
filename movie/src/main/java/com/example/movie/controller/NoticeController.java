package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.movie.entity.Notice;
import com.example.movie.service.NoticeService;

import lombok.extern.java.Log;

@Log
@Controller
public class NoticeController {

	@Autowired
	NoticeService noticeService;
		
	@GetMapping("/notice")
	public String notice(Pageable pageable) {
		return "notice/notice";
	}		
	
	@GetMapping("/getNotice")
	@ResponseBody
	public Page<Notice> getNotice(@RequestParam String searchParam,  Pageable pageable){				
		Page<Notice> noticeList = noticeService.getNoticeList(searchParam, pageable);
		return noticeList;
	}
	
	
	
	@GetMapping("/noticeWriteFrm")
	public ModelAndView noticeWriteFrm(Integer noticeNo) {
		ModelAndView mv = new ModelAndView("notice/notice_write");
		Notice notice = noticeService.selectNotice(noticeNo);
		mv.addObject("notice", notice); 
		return mv;
	}		
	
	@PostMapping("/saveNotice")
	public String saveNotice(Notice notice) {
		noticeService.saveNotice(notice);
		return "redirect:notice";
	}		
	
	@GetMapping("/deleteNotice")
	public String noticeWrite(Notice notice) {
		noticeService.deleteNotice(notice);
		return "redirect:notice";
	}		
	
	
	
}
