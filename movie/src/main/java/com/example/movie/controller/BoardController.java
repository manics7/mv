package com.example.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.BoardDto;
import com.example.movie.dto.TheaterDto;
import com.example.movie.service.BoardService;

import lombok.extern.java.Log;

@Controller
@Log
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
	@GetMapping("slist")
	public ModelAndView RvSearchList(BoardDto bDto, Integer pageNum) {
		log.info("RvSearchList() - " + bDto.getKeyword());
		mv = bServ.rvSearchList(bDto, pageNum);
		
		return mv;
	}
	
	//게시글 작성 페이지로 이동
	@GetMapping("writeRvFrm")
	public ModelAndView writeRvFrm() {
		mv = bServ.rvTheaterList();
		
		return mv;
	}
	
	//게시글 작성 처리
	@PostMapping("reviewWrite")
	public String reviewWrite(MultipartHttpServletRequest multi,
							RedirectAttributes rttr) {
		String view = bServ.rvBoardInsert(multi, rttr);
		
		return view;
	}
	
	//게시글 본문 페이지로 이동
	@GetMapping("content")
	public ModelAndView reviewContent(Integer rnum) {
		mv = bServ.getRvContent(rnum);
		
		return mv;
	}
	
	//게시글 수정 페이지로 이동
	@GetMapping("updateRvFrm")
	public ModelAndView updateRvFrm(int rnum,
			RedirectAttributes rttr) {
		mv = bServ.updateRvFrm(rnum, rttr);
		
		return mv;
	}
	
	//게시글 수정 처리
	@PostMapping("boardRvUpdate")
	public String reviewUpdate(MultipartHttpServletRequest multi,
			RedirectAttributes rttr) {
		String view = bServ.reviewUpdate(multi, rttr);
		
		return view;
	}
	
} //class end