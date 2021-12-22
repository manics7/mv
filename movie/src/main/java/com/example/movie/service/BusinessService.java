package com.example.movie.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.BusinessDto;
import com.example.movie.mapper.BusinessMapper;

@Service
public class BusinessService {

	@Autowired
	private BusinessMapper bMapper;
	@Autowired
	private HttpSession session;
	
	// 사업자 회원가입
	@Transactional
	public String businessInsert(BusinessDto business, RedirectAttributes rttr) {
		String view = null;
		String msg = null;
		
		// 비밀번호 암호화 처리
		// Spring Security에서 제공하는 암호화 인코더 사용
		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		
		// Dto에서 비밀번호를 꺼내고, 인코더를 사용해서 암호화
		String encBPw = pwEncoder.encode(business.getB_id());
		
		// 인코딩한 비밀번호를 Dto에 설정
		business.setB_pw(encBPw);
		
		try {
			bMapper.businessInsert(business);
			
			view = "redirect:/";
//			msg = "사업자 회원가입 성공";
		} catch (Exception e) {
			// e.printStackTrace();
			view = "redirect:bu_joinFrm";
//			msg = "회원가입 실패";
		}
		
//		rttr.addFlashAttribute("msg", msg);
		
		return view;
	}

	// 사업자 로그인
	public String bu_loginProc(BusinessDto business, RedirectAttributes rttr) {
		String view = null;
		String msg = null;
		
		String b_pw = bMapper.getb_pw(business.getB_pw());
		
		if(b_pw != null) {
			BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
			
			if(enc.matches(business.getB_pw(), b_pw)) {
				// 로그인 성공 - 세션에 회원 정보 저장, business				
				business = bMapper.getBusiness(business.getB_id());
				
				// business 정보를 세션에 저장
				session.setAttribute("businessInfo", business);
				
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
		
		return view;
	}
}
