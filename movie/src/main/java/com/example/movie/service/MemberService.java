package com.example.movie.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.memberDto;
import com.example.movie.mapper.MemberMapper;

@Service
public class MemberService {
	@Autowired
	private MemberMapper mMapper;
	@Autowired
	private HttpSession session;
	
	ModelAndView mv;
	
	// 이용자 회원가입
	@Transactional
	public String memberInsert(memberDto member, RedirectAttributes rttr) {
		String view = null;
//		String msg = null;
		
		try {
			mMapper.memberInsert(member);
			
			view = "redirect:/";
//			msg = "회원가입 성공";
		} catch (Exception e) {
			 e.printStackTrace();
			view = "redirect:joinFrm";
//			msg = "회원가입 실패";
		}
		
//		rttr.addFlashAttribute("msg", msg);
		
		return view;
	}
}
