package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.movie.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService bServ;
	
	private ModelAndView mv;
	
	//영화관 후기 게시글 목록
	@GetMapping("rlist")
	public ModelAndView RvBoardList(Integer pageNum) {
		mv = bServ.getRvBoardList(pageNum);
		
		return mv;
	}
	
	
	
} //class end