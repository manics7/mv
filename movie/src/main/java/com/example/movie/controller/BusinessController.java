
package com.example.movie.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.BusinessDto;
import com.example.movie.entity.TempMovie;
import com.example.movie.repository.TempMovieRepository;
import com.example.movie.service.BusinessService;

import lombok.extern.java.Log;


@Controller
@Log
public class BusinessController {

	@Autowired
	private BusinessService buServ;

	@Autowired
	private TempMovieRepository tempMovieRepository;
	
	private ModelAndView mv;

	// 사업자 회원가입
	@PostMapping("businessInsert")
	public String businessInsert(BusinessDto business, RedirectAttributes rttr) {

		String view = buServ.businessInsert(business, rttr);

		return view;
	}



	// 사업자 회원가입 아이디 중복체크
	@GetMapping(value = "buIdCheck", produces = "application/text; charset=utf-8")
	@ResponseBody
	public String buIdCheck(String bid) {
		String res = buServ.buIdCheck(bid);

		return res;
	}

	// 사업자 로그인
	@PostMapping("bu_loginProc")
	public String bu_loginProc(BusinessDto business, RedirectAttributes rttr) {

		String view = buServ.bu_loginProc(business, rttr);

		return view;
	}

	// 사업자 메인 페이지 이동
	@GetMapping("businessPage")
	public String businessPage() {

		return "businessPage";
	}

	// 사업자 로그아웃
	@GetMapping("bu_logout")
	public String bu_logout() {

		String view = buServ.bu_logout();

		return view;
	}

	@GetMapping("businessUpdateFrm")
	public String businessUpdateFrm() {
		String view = "businessUpdateFrm";

		return view;
	}

	//영화관 등록 페이지
	@GetMapping("theaterAdd")
	public String thaddFrm() {

		return "th/theaterAdd";
	}

	//영화관 등록
	@PostMapping("theaterInsert")
	public String theaterInsert(MultipartHttpServletRequest multi, RedirectAttributes rttr) {

		String view = buServ.theaterInsert(multi, rttr);

		return view;
	}

	//영화관 정보 페이지
	@GetMapping("theater")
	public ModelAndView theater() {

		mv = buServ.getTheaterList();

		return mv;
	}	

	//상영 시간표 목록 페이지
	@GetMapping("schedule")
	public String schedule() {

		return "sche/schedule";
	}

	//상영 시간표 등록 페이지
	@GetMapping("scheduleAdd")
	public ModelAndView scheduleAdd() {

		mv = buServ.getInfoList();

		return mv;
	}

	//상영시간표 등록
	@PostMapping("scheduleInsert")
	public String scheduleInsert(@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date roomStartTime, 
			@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date roomEndTime, 
			Integer thcode, String mvcode[], Integer room, String mvdate, String wait) {

		String view = buServ.testInsert(roomStartTime, roomEndTime, thcode, mvcode, room, mvdate, wait);

		return view;
	}

	//상영관 목록 이동
	@GetMapping("roomlist")
	public ModelAndView roomList() {
		mv = buServ.getRoomList();

		return mv;
	}

	//상영관 삭제
	@GetMapping("roomDelete")
	public String roomDelete(int roomseq,
			RedirectAttributes rttr) {
		String view = buServ.roomDelete(roomseq, rttr);

		return view;
	}

	//상영관 등록 페이지 이동
	@GetMapping("roomInsertFrm")
	public ModelAndView roomInsertFrm() {
		mv = buServ.roomInsertFrm();

		return mv;
	}

	//상영관 등록 처리
	@PostMapping("roomInsert")
	//public String roomInsert(MultipartHttpServletRequest multi,
	public String roomInsert(HttpServletRequest request,
			RedirectAttributes rttr) {
		//String view = buServ.roomInsert(multi, rttr);
		String view = buServ.roomInsert(request, rttr);

		return view;
	}

	// 사업자 영화등록 페이지 이동
	@GetMapping("movieInsert")
	public String movieInsert() {
		
		return "movieInsert";
	}

	// 사업자 api에서 상영목록 임시저장
	@GetMapping("insertApiMovie")
	@ResponseBody
	public List<TempMovie> insertApiMovie(String date) throws Exception {
		
		List<TempMovie> tempMovie = buServ.insertApiMovie(date);
		
		return tempMovie;
	}
	
	// 사업자 임시 목록 출력
	@GetMapping("tempApiList")
	public List<TempMovie> tempApiList(String date) {
		
		List<TempMovie> tempMovie =  tempMovieRepository.findByOpenDtLessThanEqual(date.replaceAll("-", ""));
		
		return tempMovie;
	}
} // class end
