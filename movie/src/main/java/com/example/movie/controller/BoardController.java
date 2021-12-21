package com.example.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.movie.dto.BoardDto;
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
	
	//검색한 게시글 목록
	@PostMapping("/slist")
	public ModelAndView RvSearchList(BoardDto bDto) {
		mv = bServ.rvSearchList(bDto);
		
		return mv;
	}
	
} //class end