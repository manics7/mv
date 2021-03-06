package com.example.movie.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.MemberDto;
import com.example.movie.dto.MovieDto;
import com.example.movie.dto.QuestionDto;
import com.example.movie.dto.ReviewMovieDto;
import com.example.movie.dto.TheaterDto;
import com.example.movie.entity.Notice;
import com.example.movie.entity.Schedule;
import com.example.movie.mapper.MemberMapper;
import com.example.movie.service.MemberService;
import com.example.movie.service.NoticeService;
import com.example.movie.service.ScheduleService;

import lombok.extern.java.Log;

@Controller
@Log
public class MemberController {
	

	@Autowired
	private MemberService mServ;
	@Autowired
	private AdminController aCon;

	private ModelAndView mv;
	@Autowired
	private NoticeService noticeService; 
	
	
	@Autowired
	private MemberMapper mMapper;
	
	@Autowired
	ScheduleService scheduleService;

	private final static Logger LOG = Logger.getGlobal();

	@GetMapping("mypage")
	public ModelAndView mypage(Integer pageNum) {
	

		String view ="mypage";

		int listCnt = 2;

		mv = mServ.selectQuestion(pageNum,listCnt,view);

		return mv;
	}
	//마이페이지 회원탈퇴
	@GetMapping("delMember")
	public String delMember(RedirectAttributes rttr) {

		String view = mServ.deletemember(rttr);

		return view;
	}
	////미완
	@GetMapping("memberUpdateProc")
	public String memberUpdateProc() {
		String view = "mypage";
		return view;
	}

	@GetMapping("pmvReviewFrm")
	public ModelAndView pmvReviewFrm(Integer pageNum) {

		String view = "pmvReviewFrm";

		int listCnt = 4;

		mv = mServ.pmvReviewFrm(pageNum,listCnt,view);

		return mv;

	}
	@GetMapping("questionFrm")
	public ModelAndView questionFrm(Integer pageNum) {

		int listCnt = 4;

		String View = "questionFrm";
		

		mv = mServ.selectQuestion(pageNum,listCnt,View);

		return mv;
	}
	//문의글 작성 페이지 이동
	@GetMapping("questionWriteFrm")
	public String questionWriteFrm() {
		
		return"questionWriteFrm";
	}
	
	
	//문의글 작성 처리
	@PostMapping("questionWrite")
	public String questionWrite(QuestionDto quesdto,RedirectAttributes rttr) {
		String view = mServ.questionWrite(quesdto,rttr);
		
		return view;
	}

	@GetMapping("questionContents")
	public ModelAndView questionContents(int ques_no) {
		

		Integer view = 1;

		mv = aCon.requeboardRead(ques_no,view);
		

		return mv;
	}
	@GetMapping("delMvReview")
	public String delMvReview(int mv_review,RedirectAttributes rttr) {

		/*int mvrnum = Integer.parseInt(mv_review);*/

		String view = mServ.delMvReview(mv_review,rttr);

		return view;
	}
	
	//내가본영화
	@GetMapping("mypageMovieFrm")
	public ModelAndView mypageMovieFrm(Integer pageNum) {
		mv = new ModelAndView();
		String view = "mypageMovieFrm";
		
		mv = mServ.selectPurchase(pageNum, 4, view);
		
		return mv;
	}
	
	@GetMapping("purchaseFrm")
	public ModelAndView purchaseFrm (Integer pageNum) {
		int listCnt = 4;

		String View = "purchaseFrm";

		mv = mServ.selectPurchase(pageNum,listCnt,View);

		return mv;
	}
	@GetMapping("purchaseCancelFrm")
	public ModelAndView purchaseCancelFrm (Integer pageNum) {
		int listCnt = 4;

		String View = "purchaseCancelFrm";

		mv = mServ.selectPurchase(pageNum,listCnt,View);

		return mv;
	}

	//회원 정보 출력
	@GetMapping("/mmanage")
	public ModelAndView mmanageFrm(Integer pageNum) throws Exception {
		int num = (pageNum == null)? 1 : pageNum;
		System.out.println("page_num = "+ pageNum);
		LOG.info("info Log = " + pageNum);

		ModelAndView mv = new ModelAndView();

		List<MemberDto> mList = mServ.getMemberList(num);

		mv.addObject("mList", mList);

		String pageHtml = mServ.getPaging(num);
		mv.addObject("paging", pageHtml);


		mv.setViewName("mmanage");

		return mv;
	}
	//회원정보 검색 시 해당 회원 목록 m_id로 가져오기 
	@PostMapping("/memberSelect")
	public ModelAndView memberSelect(String m_id) {
		mv = mServ.memberSelect(m_id);
		System.out.println("m_id = "+m_id);
		String pageHtml = mServ.getsearchPaging(m_id);
		mv.addObject("paging", pageHtml);

		mv.setViewName("mmanage");

		return mv;
	}

	//회원정보로 1대1문의 글 가져오기 (m_id로 검색)
	@GetMapping("/mboardSelect")
	public ModelAndView mboardSelect(String m_id) {

		System.out.println("테스트 검색어 m_id = "+m_id);
		mv = mServ.mboardSelect(m_id);
		
		//페이징 처리 할 예정
		mv.setViewName("quesboard");

		return mv;
	}

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
		log.info("loginProc()");
		String view = mServ.loginProc(member, rttr);

		return view;
	}

	// 이용자 로그아웃
	@GetMapping("logout")
	public String logout() {

		String view = mServ.logout();

		return view;
	}
	
	// 이용자 관람평 작성
	@PostMapping(value = "insertReviewMovie", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, List<ReviewMovieDto>> insertReviewMovie(ReviewMovieDto reviewMovieDto) {
		
		Map<String, List<ReviewMovieDto>> insertReviewMap = mServ.insertReviewMovie(reviewMovieDto);
		
		return insertReviewMap;
	}
	//영화관 상세 페이지로 이동.
	@GetMapping("accessTheaterDetailPage")
	public ModelAndView theater_detail(Integer th_code, Pageable pageable) {
		List<Map<String, Object>> list = mServ.getSch(th_code);
		TheaterDto thDto = mMapper.getThinfoList(th_code);
		Page<Notice> noticeList = noticeService.getNoticeList("", pageable);
		mv = new ModelAndView();
		mv.addObject("noticeList", noticeList);
		mv.addObject("theatedetail", list);
		mv.addObject("th_code",th_code);
		mv.addObject("thinfoDto", thDto);
		mv.setViewName("theater_detail");
		return mv;
	}
	
	@GetMapping("getDate")
	@ResponseBody
	public List<Map<String, String>> getDate(){
		
		List<Map<String, String>> map = scheduleService.getDatesDaysWeek(2);
		
		return map;
	}
	
	@GetMapping("getSchedulelist")
	@ResponseBody
	public List<Map<String, Object>> getSchedule(@DateTimeFormat(pattern = "yyyy-MM-dd") Date schDate,Integer thCode){
		List<Map<String, Object>> map = mServ.getSchedule(schDate,thCode);
		
		return map;
	}

	//회원이 1대1문의사항 답변을 확인 할 떄
	@GetMapping("memReadQuesRe")
	public ModelAndView readQuesRe(int ques_no) {
		mv = new ModelAndView();
		int view = 0;
		mv = mServ.memReadQuesRe(ques_no, view);
		return mv;
	}

	// 현재상영작 목록 페이지 이동(현재상영작 불러오기)
	@GetMapping("currentMovieList")
	public ModelAndView currentMovieList(String mainMovieSearch) {

		mv = mServ.getMovieList(mainMovieSearch);
		
		return mv;
	}
	
	// 통합 영화 목록 이동
	@GetMapping("totalMovie")
	public String totalMovie() {
		
		return "main/totalMovie";
	}
	
	// 통합 영화 목록 출력
	@GetMapping("totalMovieList")
	@ResponseBody
	public  Map<String, Object> totalMovieList() {
		//List<Map<String, String>> map = scheduleService.getDatesDaysWeek(1);
		//List<Map<String, Object>> totalMovieList = mServ.totalMovieList();
		 Map<String, Object> map =scheduleService.getSchedule();
		
		return map;
	}
	
	// 통합 영화 목록 시간 출력
	@GetMapping("totalMovieTimeList")
	@ResponseBody
	public List<Schedule> getTotalMovieTimeList(String movieCd, String schDate) {
		//List<Map<String, String>> map = scheduleService.getDatesDaysWeek(1);
		List<Schedule> list  = scheduleService.getTotalMovieTimeList(movieCd, schDate);
		
		return list;
	}
	
	@GetMapping("selectSchedule")
	@ResponseBody
	public List<Schedule> selectSchedule(String movieCd, Integer thCode, String schDate) {
		//List<Map<String, String>> map = scheduleService.getDatesDaysWeek(1);
		//List<Map<String, Object>> totalMovieList = mServ.totalMovieList();
		// Map<String, Object> map =scheduleService.selectSchedule(movieCd, thCode, schDate);
	
		List<Schedule> list = scheduleService.selectSchList(movieCd, thCode, schDate);
		return list;
	}
	
	// 영화 상세 페이지 이동
		@GetMapping("movieDetail")
		public ModelAndView movieDetail(String movie_cd) {
			
			mv = mServ.movieDetail(movie_cd);
			
			return mv;
		}
	
	//관리자 입장에서 회원 삭제.
	@GetMapping("admindelMember")
	public String admindelMember(String m_id, RedirectAttributes rttr) {
	    String view = mServ.adminDeleteMember(m_id,rttr);
		return view;
	}
}