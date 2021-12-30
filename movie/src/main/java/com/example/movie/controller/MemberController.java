package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.MemberDto;
import com.example.movie.service.MemberService;

import lombok.extern.java.Log;

package com.example.movie.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.ModelAndView;

import com.example.movie.dto.quesboardDto;
import com.example.movie.service.MemberService;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.MemberDto;
import com.example.movie.service.MemberService;

import lombok.extern.java.Log;

@Controller
@Log
public class MemberController {
	private final static Logger LOG = Logger.getGlobal();
  @Autowired
	private MemberService mServ;
	
	private ModelAndView mv;

	// pageNum에 들어오는 데이터
	// 1. null - url에 페이지번호를 작성하지 않을 때
	// 첫번째 페이지가 보여지는 상황.(로그인한 직후)
	// 2. 페이지 번호 숫자.

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
	
	@GetMapping
	public String deleteMember(String m_id) {
		//1. 회원 게시글 보기 
		//2. 회원 게시글 삭제 
		//3. 회원 정보 삭제 
		//또는 회원 회원 게시글 및 회원 정보 삭제 
	
		String view = mServ.deletemember(m_id);
		
		return view;	
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
	@GetMapping("/thDetail")
	public ModelAndView thDetail(thdetailDto thdto) {
		
		ModelAndView mv = mServ.thdetailInsert(thdto);
		
		return mv;
	
	// 이용자 회원가입
		@PostMapping("memberInsert")
		public String memberInsert(MemberDto member, RedirectAttributes rttr) {

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