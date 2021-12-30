package com.example.movie.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.common.AwsS3;
import com.example.movie.dto.BusinessDto;
import com.example.movie.dto.Movie;
import com.example.movie.dto.TestDto;
import com.example.movie.mapper.BusinessMapper;
import com.example.movie.repository.MovieRepository;

@Service

public class BusinessService {
	@Autowired
	private BusinessMapper buMapper;
	@Autowired
	private HttpSession session;
	@Autowired
	private AwsS3 awsS3;
	
	@Autowired
	private MovieRepository movieRepository;
	
	ModelAndView mv;
	
	// 이용자 회원가입 아이디 중복체크
	public String buIdCheck(String bid) {
		
		String res = null;
		
		int cnt = buMapper.buIdCheck(bid);
		if(cnt == 0) {
			res = "ok";
		}
		else {
			res = "fail";
		}
		
		return res;
	}
	
	// 사업자 회원가입
	@Transactional
	public String businessInsert(BusinessDto business, RedirectAttributes rttr) {
		String view = null;
		String msg = null;
		
		// 비밀번호 암호화 처리
		// Spring Security에서 제공하는 암호화 인코더 사용
		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		
		// Dto에서 비밀번호를 꺼내고, 인코더를 사용해서 암호화
		String encBPw = pwEncoder.encode(business.getB_pw());
		
		// 인코딩한 비밀번호를 Dto에 설정
		business.setB_pw(encBPw);
		
		try {
			buMapper.businessInsert(business);
			
			view = "redirect:/";
			msg = "사업자 회원가입 성공";
		} catch (Exception e) {
			// e.printStackTrace();
			view = "redirect:bu_joinFrm";
			msg = "회원가입 실패";
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return view;
	}

	// 사업자 로그인
	public String bu_loginProc(BusinessDto business, RedirectAttributes rttr) {
		String view = null;
		String msg = null;
		
		// b_pw = 암호화되어 저장된 비밀번호, encBPw		
		String b_pw = buMapper.getb_pw(business.getB_id());
		
		if(b_pw != null) {
			BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
			
			if(enc.matches(business.getB_pw(), b_pw)) {
				// 로그인 성공 - 세션에 회원 정보 저장, business				
				business = buMapper.getBusiness(business.getB_id());
				
				// business 정보를 세션에 저장
				session.setAttribute("businessInfo", business);
				
				view = "redirect:businessPage";
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

	// 사업자 로그아웃
	public String bu_logout() {
		
		String view = "redirect:/";
		
		session.invalidate();
		
		return view;
	}

	// test영화 등록	
	public String insertMovie(MultipartHttpServletRequest multi, RedirectAttributes rttr) {
		
		String view = null;
		String msg = null;
		
		// multi에서 데이터 추출
		String mvname = multi.getParameter("mvname");
		String mvtime = multi.getParameter("mvtime");
		String files = multi.getParameter("files");
		String check = multi.getParameter("fileCheck");
		
		// 게시글 내용 dto에 담아서 dao(mapper)로 보냄
		TestDto tDto = new TestDto();
		tDto.setMvname(mvname);
		tDto.setMvtime(mvtime);
		
		try {
			buMapper.insertMovie(tDto);
			
			if(check.equals("1")) {
				List<MultipartFile> multipartFiles = multi.getFiles("");
				awsS3.uploadFile(multipartFiles);
			}
			
			view = "redirect:/";
			msg = "작성 완료!";
		} catch (Exception e) {
			e.printStackTrace();
			view = "redirect:test";
			msg = "작성 실패!";
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return null;
	}

	public String mvInsertProc(Movie movie, RedirectAttributes rttr) {
		String view = null;
		String msg = null;
				
		try {
			movieRepository.save(movie);
			
			view = "redirect:/";
			msg = "등록 성공";
			
		} catch (Exception e) {
			 e.printStackTrace();
			view = "redirect:/";
			msg = "등록 실패";
		}
		
		return view;
	}
	
}
