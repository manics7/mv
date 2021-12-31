package com.example.movie.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.movie.mapper.BusinessMapper;
import com.example.movie.common.AwsS3;
import com.example.movie.dto.BusinessDto;
import com.example.movie.dto.TheaterDto;



@Service
public class BusinessService {
	
	@Autowired
	private BusinessMapper buMapper;
	@Autowired
	private HttpSession session;
	@Autowired
	private AwsS3 awsS3;
	
	@Value("${aws.s3.bucket}")
	private String bucket;

	@Value("${aws.s3.bucketURL}")
	private String bucketURL;
	
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
	
	//영화관 등록
	@Transactional
	public String theaterInsert(MultipartHttpServletRequest multi, RedirectAttributes rttr) {
		String view = null;
		String msg = null;
		
		//데이터 추출
		String id = multi.getParameter("bid");//사업자 아이디
		String name = multi.getParameter("thname");//영화관 이름
		String place = multi.getParameter("thplace");//위치 정보(오시는 길)
		String tel = multi.getParameter("thtel");//영화관 번호
		String parking = multi.getParameter("thpark");//주차 안내
		String introduce = multi.getParameter("thintro");//영화관 소개
		String regeion = multi.getParameter("reNum");//지역 코드
		String check = multi.getParameter("logoCheck");//로고 이미지
		String check2 = multi.getParameter("theaterCheck");//영화관 사진
		
		//textarea는 실제 데이터 앞 뒤에 공백이 발생하는 경우가 있어서
		parking = parking.trim();
		introduce = introduce.trim();
		
		//텍스트 내용을 dto에 담기
		TheaterDto theater = new TheaterDto();
		theater.setB_id(id);//사업자 아이디
		theater.setTh_name(name);//영화관 이름
		theater.setTh_location(place);//위치 정보
		theater.setTh_tel(tel);//영화관 번호
		theater.setTh_parking(parking);//주차 안내
		theater.setTh_introduce(introduce);//영화관 소개
		theater.setTh_areacode(regeion);//지역 코드
		
		try {
			//업로드 파일이 있을 경우
			//check는 로고 이미지, check2는 영화관 사진
			
			if(check.equals("1") || check2.equals("1")) {
				
				//로고 파일의 이름 가져오기
				List<MultipartFile> logoFiles = multi.getFiles("logoFiles");	
				List<String> logoName = awsS3.uploadFile(logoFiles);
				
				//로고 사진 파일이 있으면 파일 이름을 dto에 담기
				for(int i = 0; i < logoName.size(); i++) {
					String LfileName = awsS3.getFileURL(bucket, bucketURL+logoName.get(i));
					
					theater.setTh_logo(LfileName);
				}
				
				//영화관 사진 파일의 이름 가져오기
				List<MultipartFile> theaterFiles = multi.getFiles("theaterFiles");
				List<String> theaterName = awsS3.uploadFile(theaterFiles);
				
				//영화관 사진 파일이 있으면 파일 이름을 dto에 담기
				for(int i = 0; i < theaterName.size(); i++) {
					String TfileName = awsS3.getFileURL(bucket, bucketURL+theaterName.get(i));
					
					theater.setTh_image(TfileName);
					
					//dto에 담은 내용을 mapper로 넘기기  
					buMapper.theaterAdd(theater); 
				}
			}
			//영화관 정보 페이지로 이동
			view = "redirect:theater";
			msg = "등록 성공";
			
		} catch (Exception e) {
			e.printStackTrace();
			//다시 등록 페이지
			view = "redirect:theaterAdd";
			msg = "등록 실패";
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return view;
	}
	
	//영화관 검색
	public ModelAndView getTheaterList() {
		mv = new ModelAndView();
		String bId;
		BusinessDto bDto = (BusinessDto)session.getAttribute("businessInfo");
		bId = bDto.getB_id();
		
		List<TheaterDto> theaterList = new ArrayList<TheaterDto>();
		
		theaterList = buMapper.getTheaterList(bId);
		
		System.out.println("theaterList = " + theaterList);
		
		mv.addObject("theaterList", theaterList);
		
		mv.setViewName("th/theater");
		
		return mv;
	}
	
}