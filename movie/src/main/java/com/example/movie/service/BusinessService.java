package com.example.movie.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	// 사용자 회원가입
	@Transactional
	public String businessInsert(BusinessDto business, RedirectAttributes rttr) {
		String view = null;
		String msg = null;
		
		try {
			bMapper.businessInsert(business);
			
			view = "redirect:/";
//			msg = "사업자 회원가입 성공";
		} catch (Exception e) {
			 e.printStackTrace();
			view = "redirect:bu_joinFrm";
//			msg = "회원가입 실패";
		}
		
		return view;
	}
	
}
