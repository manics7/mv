package com.example.movie.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.MemberDto;
import com.example.movie.mapper.MemberMapper;

@Service
public class MemberService {
	@Autowired
	private MemberMapper mMapper;
	@Autowired
	private HttpSession session;
	
	ModelAndView mv;
	
	// 이용자 회원가입 아이디 중복체크
		public String idCheck(String mid) {

			String res = null;
			
			// mapper에서 카운트 0 또는 1
			int cnt = mMapper.idCheck(mid);
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
	public String loginProc(MemberDto member, RedirectAttributes rttr) {
		String view = null;
		String msg = null;
		
		// pw = 암호화되어 저장된 비밀번호, encPw
		String pw = mMapper.getPw(member.getM_id());
		
		if(pw != null) {
			BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
			
			if(enc.matches(member.getM_pw(), pw)) {
				// 로그인 성공 - 세션에 회원 정보 저장, member
				member = mMapper.getMember(member.getM_id());
				
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
