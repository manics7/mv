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

	//회원 정보 출력
	@GetMapping("/mmanage")
	public ModelAndView mmanageFrm(String pageNum) throws Exception {
		System.out.println("page_num = "+pageNum);
		LOG.info("info Log = " + pageNum);

		ModelAndView mv = new ModelAndView();

		List<MemberDto> mList = mServ.getMemberList(Integer.parseInt(pageNum));

		mv.addObject("mList", mList);

		String pageHtml = mServ.getPaging(Integer.parseInt(pageNum));
		mv.addObject("paging", pageHtml);


		mv.setViewName("mmanage");

		return mv;
	}
	//회원정보 검색 시 해당 회원 목록 m_id로 가져오기 
	@PostMapping("/memberSelect")
	public ModelAndView memberSelect(String m_id) {
		mv = mServ.memberSelect(m_id);
		System.out.println("m_id = "+m_id);
		String pageHtml = mServ.getsearchPaging(m_id);
		mv.addObject("paging", pageHtml);

		mv.setViewName("mmanage");

		return mv;

	}
	//회원정보로 1대1문의 글 가져오기 (m_id로 검색)
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