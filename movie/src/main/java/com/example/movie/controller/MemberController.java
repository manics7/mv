package com.example.movie.controller;


import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.MemberDto;
import com.example.movie.service.MemberService;

import lombok.extern.java.Log;

@Controller
@Log
public class MemberController {

	@Autowired
	private MemberService mServ;
	@Autowired
	private AdminController aCon;
	
	private ModelAndView mv;
	
	private final static Logger LOG = Logger.getGlobal();
	
	@GetMapping("mypage")
	public ModelAndView mypage(Integer pageNum) {
		
		String view ="mypage";
		
		int listCnt = 2;
		
		mv = mServ.selectQuestion(pageNum,listCnt,view);
		
		return mv;
	}
	@GetMapping("delMember")
	public String delMember(RedirectAttributes rttr) {
		
		String view = mServ.deletemember(rttr);
		
		return view;
	}
	////미완
	@GetMapping("memberUpdateProc")
	public String memberUpdateProc() {
		String view = "mypage";
		return view;
	}
	
	@GetMapping("pmvReviewFrm")
	public ModelAndView pmvReviewFrm(Integer pageNum) {
		
		String view = "pmvReviewFrm";

		int listCnt = 10;
		
		mv = mServ.pmvReviewFrm(pageNum,listCnt,view);
		
		return mv;
		
	}
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
	
	@GetMapping("questionContents")
	public ModelAndView questionContents(int ques_no) {
		
		Integer view = 1;
		
		mv = aCon.requeboardRead(ques_no,view);
		
		return mv;
	}
	@GetMapping("delMvReview")
	public String delMvReview(int mv_review,RedirectAttributes rttr) {
		
		/*int mvrnum = Integer.parseInt(mv_review);*/
		
		String view = mServ.delMvReview(mv_review,rttr);
		
		return view;
	}	
	
	
	@PostMapping("mvReviewSearch")
	public ModelAndView mvReviewSearch(String mvname) {
		
		mv = mServ.mvReviewSearch(mvname);
		
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


	@GetMapping("/mmanage")
	public ModelAndView mmanageFrm(String pageNum) throws Exception {
		LOG.info("info Log = " + pageNum);

		ModelAndView mv = new ModelAndView();

		// pageNum에 들어오는 데이터
		// 1. null - url에 페이지번호를 작성하지 않을 때
		// 첫번째 페이지가 보여지는 상황.(버튼을 눌러 이동한 직후)
		// 2. 페이지 번호 숫자.

//		mv = mServ.getMemberList(Integer.parseInt(pageNum));

		List<MemberDto> mList = mServ.getMemberList(Integer.parseInt(pageNum));
		//System.out.println(mList+"mList");
		// 화면에 전송.
		mv.addObject("mList", mList);
		// 여기에 다른 받고싶은 데이터 리스트 있음 추가.

		// 페이징 처리.
		String pageHtml = mServ.getPaging(Integer.parseInt(pageNum));
		mv.addObject("paging", pageHtml);

		// 세션에 페이지번호 저장 할 필요는 여기서 없을거같음.

		// jsp 파일 이름 지정
		mv.setViewName("mmanage");

		return mv;
	}
	
	@PostMapping("/memberSelect")
	public ModelAndView memberSelect(String m_id) {
		mv = mServ.memberSelect(m_id);
		System.out.println("m_id = "+m_id);
		
//		Integer pageNum = 1;
		
		String pageHtml = mServ.getsearchPaging(m_id);
		mv.addObject("paging", pageHtml);
		
		mv.setViewName("mmanage");
		
	
		
		return mv;
		
	}
	@GetMapping("/mboardSelect")
	public ModelAndView mboardSelect(String m_id) {
	
		System.out.println("테스트 검색어 m_id = "+m_id);
		mv = mServ.mboardSelect(m_id);
		
		
		mv.setViewName("quesboard");
		
		return mv;
	}


	
	// 이용자 회원가입
		@PostMapping("memberInsert")
		public String memberInsert(MemberDto member, RedirectAttributes rttr) {
			log.info("memberInsert()");
			String view = mServ.memberInsert(member, rttr);
			
			return view;
		}
	
	// 이용자 회원가입 아이디 중복체크
	@GetMapping(value = "idCheck", produces = "application/text; charset=utf-8")
	@ResponseBody
	public String idCheck(String mid) {
		
		String res = mServ.idCheck(mid);
		
		return res;
	}
	
	// 이용자 로그인
	@PostMapping("loginProc")
	public String loginProc(MemberDto member, RedirectAttributes rttr) {
		log.info("loginProc()");
		String view = mServ.loginProc(member, rttr);
		
		return view;
	}
	
	// 이용자 로그아웃
	@GetMapping("logout")
	public String logout() {
		
		String view = mServ.logout();
		
		return view;

	}
	
}