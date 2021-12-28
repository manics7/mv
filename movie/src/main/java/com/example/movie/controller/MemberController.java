package com.example.movie.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.MemberDto;
import com.example.movie.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService mServ;
	
	private ModelAndView mv;
	
	
	@GetMapping("loginFrm")
	public String loginFrm() {
		
		return "loginFrm";
	}
	
	@GetMapping("mypage")
	public ModelAndView mypage(Integer pageNum) {
		
		String view = "mypage";
		
		int listCnt = 3;
		
		mv = mServ.selectQuestion(pageNum,listCnt,view);
		
		return mv;
	}
	@GetMapping("pmvReviewFrm")
	public ModelAndView pmvReviewFrm(Integer pageNum) {
		
		String view = "pmvReviewFrm";

		int listCnt = 10;
		
		mv = mServ.pmvReviewFrm(pageNum,listCnt,view);
		
		return mv;
		
	}
	/* 보류한다함
	@GetMapping("purchaseFrm")
	public ModelAndView purchaseFrm (Integer pageNum) {
		int listCnt = 10;
		
		String View = "purchaseFrm";
		
		mv = mServ.selectPurchase(pageNum,listCnt,View);
		
		return mv;
	}
	@GetMapping("purchaseCancelFrm")
	public ModelAndView purchaseCancelFrm (Integer pageNum) {
		int listCnt = 10;
		
		String View = "purchaseCancelFrm";
		
		mv = mServ.selectPurchase(pageNum,listCnt,View);
		
		return mv;
	}*/
	@GetMapping("questionFrm")
	public ModelAndView questionFrm(Integer pageNum) {
		
		int listCnt = 10;
		
		String View = "questionFrm";
		
		mv = mServ.selectQuestion(pageNum,listCnt,View);
		
		return mv;
	}
	
	@GetMapping("memberUpdateFrm")
	public ModelAndView memberUpdateFrm() {
		
		mv = mServ.memberUpdateFrm();
		
		return mv;
	}
	
	@PostMapping("loginProc")
	public String loginProc(MemberDto member, 
			RedirectAttributes rttr) {
		
		String view = mServ.loginProc(member, rttr);
		
		return view;
	}
	
}
