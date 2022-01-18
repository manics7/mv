
package com.example.movie.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ModelAndView businessPage() {
		mv= new ModelAndView();
		
		mv = buServ.businessPage();
		
		
		return mv;
	}

	// 사업자 로그아웃
	@GetMapping("bu_logout")
	public String bu_logout() {

		String view = buServ.bu_logout();

		return view;
	}

	//영화관 등록 페이지 이동
	@GetMapping("theaterAdd")
	public String thaddFrm() {

		return "./th/theaterAdd";
	}
	
	//영화관 수정 페이지 이동
	@GetMapping("thUpdate")
	public ModelAndView thUpdate(int th_code) {
		
		mv = new ModelAndView();
		
		mv = buServ.thUpdate(th_code);
		
		return mv;
	}
	
	//영화관 수정
	@PostMapping("thUpdateFrm")
	public String thUpdateFrm(MultipartHttpServletRequest multi,
			RedirectAttributes rttr) {
		String view = buServ.theaterUpdate(multi, rttr);
		
		return view;
	}
	
	//영화관 등록
	@PostMapping("theaterInsert")
	public String theaterInsert(MultipartHttpServletRequest multi, RedirectAttributes rttr) {

		String view = buServ.theaterInsert(multi, rttr);

		return view;
	}
	
	//영화관 정보 페이지 이동
	@GetMapping("theater")
	public ModelAndView theater() {

		mv = buServ.getTheaterList();

		return mv;
	}	
	//상영 시간표 목록 페이지 이동
	@GetMapping("schedule")
	public ModelAndView schedule(Integer pageNum) {
			
		mv = buServ.getScheduleList(pageNum);
		return mv;
	}
	
	//상영 시간표 등록 페이지 이동
	@GetMapping("scheduleAdd")
	public ModelAndView scheduleAdd() {

		mv = buServ.getInfoList();

		return mv;
	}

	//상영시간표 등록
	@PostMapping("scheduleInsert")
	public String scheduleInsert(@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date roomStartTime, 
			@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date roomEndTime, 
			Integer thcode, @RequestParam List<String> mvcode, Integer room, String mvdate, String wait) throws ParseException {
		String view = buServ.testInsert(roomStartTime, roomEndTime, thcode, mvcode, room, mvdate, wait);

		return view;
	}
	//상영시간표 삭제
	@GetMapping("scheduleDelete")
	public String scheduleDelete(int sch_code, RedirectAttributes rttr) {
		String view = buServ.scheduleDelete(sch_code, rttr);
		
		return view;
	}

	//상영관 목록 이동
	@GetMapping("roomList")
	public ModelAndView roomList(String bId) {
		mv = buServ.getRoomList(bId);

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
		
		List<TempMovie> tempMovie = tempMovieRepository.findByOpenDtLessThanEqual(date.replaceAll("-", ""));
		
		return tempMovie;
	}
	
	// 사업자 영화 등록
	@PostMapping("movieInsertProc")
	public String movieInsertProc(MultipartHttpServletRequest multi, RedirectAttributes rttr) throws ParseException {
		
		String view = buServ.movieInsertProc(multi, rttr);
		
		return view;
	}
	
	//이벤트 관리 페이지 이동
	@GetMapping("eventList")
	public ModelAndView eventList() {
		mv = buServ.getEventList();

		return mv;
	}
	
	//이벤트 등록 페이지 이동
	@GetMapping("eventInsertFrm")
	public ModelAndView eventInsertFrm() {
		mv = buServ.eventInsertFrm();

		return mv;
	}
	
} // class end
