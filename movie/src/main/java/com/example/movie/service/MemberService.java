package com.example.movie.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.Member;
import com.example.movie.dto.MemberDto;
import com.example.movie.dto.quesboardDto;
import com.example.movie.dto.thdetailDto;
import com.example.movie.mapper.AdminMapper;
import com.example.movie.mapper.MemberMapper;
import com.example.movie.util.PagingUtil;


@Service
public class MemberService {

	
	@Autowired
	private AdminMapper aMap;
	
  @Autowired
	private MemberMapper mMapper;
	
  @Autowired
	private HttpSession session;
	
	ModelAndView mv;
	
//	private ModelAndView mv;

	private int listCnt = 4;//페이지 당 게시글 개수

//	public ModelAndView getMemberList(Integer pageNum) {
	public List<MemberDto> getMemberList(Integer pageNum) {
		// TODO Auto-generated method stub
//		mv = new ModelAndView();
//		pageNum = null;
		//null 또는 페이지 번호가 pageNum으로 넘어옴.
		pageNum = (pageNum == null) ? 1 : pageNum;
		//버튼을 눌러 이동한 직 후 null이 넘어옴.
System.out.println("pageNum"+pageNum);		
		//회원 목록 가져오기
		Map<String, Integer> mmap = 
				new HashMap<String, Integer>();
//		mmap.put("num", num);
		mmap.put("num", pageNum);
		mmap.put("lcnt", listCnt);
		//차후 view(jsp)에서 페이지 당 글 개수를 입력받아서
	    //설정하는 부분을 처리하여 10 대신 사용.
		//페이지 당 게시글 개수와 페이지넘버 넣는것.
		System.out.println("listCnt = "+listCnt);			
		List<MemberDto> mList = mMapper.getList(mmap);
System.out.println("mList.size = "+mList.size());		
System.out.println("BoardCnt = "+mMapper.getBoardCnt()); //전체 글 개수 가져오는 mapper
//페이징 처리
//String pageHtml = getPaging(pageNum);

		return mList;
		
	/*
		//페이징 처리.
		String pageHtml = getPaging(num);
		mv.addObject("paging", pageHtml);
		
		//세션에 페이지번호 저장 할 필요는 여기서 없을거같음.
		
		//jsp 파일 이름 지정
		mv.setViewName("mmanage");
		
		return mv;
*/
	}
	
	//paging
//	private String getPaging(int num) {
	public String getPaging(int num) {
		String pageHtml = null;

		//전체 글 개수 구하기(DAO) -> MAPPER 거쳐서 102라는 숫자가 나옴.
		int maxNum = mMapper.getBoardCnt();
		//한 페이지에 보여질 페이지 번호 개수 (하단에 조그맣게)
		int pageCnt = 10;
		String listName = "mmanage";
		//utill의 페이징 처리하는 곳에 maxNum(전체 글개수), num(페이지번호), listCnt(페이지 당 글 개수)를 파라미터로 보내서 처리함.
		PagingUtil paging = new PagingUtil(maxNum, num, listCnt, 
				pageCnt, listName);

		pageHtml = paging.makePaging();

		System.out.println("pageCnt(하단에 보여질 페이지넘버) = "+pageCnt);
		System.out.println("pageHTML = "+pageHtml);
		
		return pageHtml;
	}

	
	
	public String deletemember(String m_id) {
		
		mMapper.deleteMember(m_id);
		
		return null;
	}

	public ModelAndView memberSelect(String m_id) {
		
	ModelAndView mv = new ModelAndView();
	
	List<MemberDto> mselectList = mMapper.selectMember(m_id);
		
	
	System.out.println("검색 결과 = "+mselectList);
	mv.addObject("mList", mselectList);
		return mv;
	}

	public ModelAndView mboardSelect(String m_id) {
		//mMap.getmboardSelect(m_id);
		ModelAndView mv = new ModelAndView();
		
		
		List<quesboardDto> mbList = aMap.getboardSelect(m_id);
		mv.addObject("mbLIst", mbList);
		
		System.out.println("mbList = "+mbList);
		return mv;
	}

	public String getsearchPaging(String m_id) {
		String pageHtml = null;
		int num = 1;
		//전체 글 개수 구하기(DAO) -> MAPPER 거쳐서 102라는 숫자가 나옴.
		int maxNum = mMapper.getsearchBoardCnt(m_id); // 회원 id로 작성한 글 갯수 출력.
		//한 페이지에 보여질 페이지 번호 개수 (하단에 조그맣게)
		int pageCnt = 5;
		String listName = "mmanage";
		//utill의 페이징 처리하는 곳에 maxNum(전체 글개수), num(페이지번호), listCnt(페이지 당 글 개수)를 파라미터로 보내서 처리함.
		PagingUtil paging = new PagingUtil(maxNum, num, listCnt, 
				pageCnt, listName);

		pageHtml = paging.makePaging();

		System.out.println("pageCnt(하단에 보여질 페이지넘버) = "+pageCnt);
		System.out.println("pageHTML = "+pageHtml);
		
		return pageHtml;
	}

	public ModelAndView thdetailInsert(thdetailDto thdto) {
		ModelAndView mv = new ModelAndView();
		List<thdetailDto> thdetailList = mMapper.thdetailInsert(thdto);
		mv.addObject("thdetail", thdetailList);
		return mv;
	}
			
	// 이용자 회원가입 아이디 중복체크
		public String idCheck(String m_id) {

			String res = null;
			
			// mapper에서 카운트 0 또는 1
			int cnt = mMapper.idCheck(m_id);
			if(cnt == 0) {
				res = "ok";
			}
			else {
				res = "fail";
			}
			return res;
		}
	
	// 이용자 회원가입
	@Transactional
	public String memberInsert(MemberDto member, RedirectAttributes rttr) {
		String view = null;
		String msg = null;
		
		// 비밀번호 암호화 처리
		// Spring Security에서 제공하는 암호화 인코더 사용
		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		
		// Dto에서 비밀번호를 꺼내고, 인코더를 사용해서 암호화
		String encPw = pwEncoder.encode(member.getM_pw());
		
		// 인코딩한 비밀번호를 Dto에 설정
		member.setM_pw(encPw);
		
		try {
			mMapper.memberInsert(member);
			
			view = "redirect:/";
			msg = "회원가입 성공";
		} catch (Exception e) {
			// e.printStackTrace();
			view = "redirect:joinFrm";
			msg = "회원가입 실패";
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return view;
	}

	// 이용자 로그인
	public String loginProc(Member member, RedirectAttributes rttr) {
		String view = null;
		String msg = null;
		
		// pw = 암호화되어 저장된 비밀번호, encPw
		String pw = mMapper.getPw(member.getMId());
		
		if(pw != null) {
			BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
			
			if(enc.matches(member.getMPw(), pw)) {
				// 로그인 성공 - 세션에 회원 정보 저장, member
				member = mMapper.getMember(member.getMId());
				
				// member 정보를 세션에 저장
				session.setAttribute("userInfo", member);
				
				view = "redirect:/";
			}
			else {
				view = "redirect:/";
				msg = "아이디 또는 비밀번호가 다릅니다";
			}
		}
		else {
			view = "redirect:/";
			msg = "아이디 또는 비밀번호가 다릅니다";
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return view;
	}


	// 이용자 로그아웃
	public String logout() {
		
		String view = "redirect:/";
		
		session.invalidate();
		
		return view;
	}
	
}
