package com.example.movie.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.MemberDto;
import com.example.movie.dto.MovieOfficialDto;
import com.example.movie.dto.QuestionDto;
import com.example.movie.dto.ReviewMovieDto;
import com.example.movie.dto.SsdscheduleDto;
import com.example.movie.dto.TheaterDto;
import com.example.movie.dto.ThmovieDto;
import com.example.movie.dto.mvReviewDto;
import com.example.movie.dto.quesReplyDto;
import com.example.movie.dto.quesboardDto;
import com.example.movie.dto.reservationDto;
import com.example.movie.entity.MovieOfficial;
import com.example.movie.entity.Room;
import com.example.movie.entity.Schedule;
import com.example.movie.entity.ScheduleDetail;
import com.example.movie.entity.Theater;
import com.example.movie.mapper.AdminMapper;
import com.example.movie.mapper.MemberMapper;
import com.example.movie.repository.MovieOfficialRepository;
import com.example.movie.repository.ReservationRepositoryCustom;
import com.example.movie.repository.RoomRepository;
import com.example.movie.repository.ScheduleDetailRepository;
import com.example.movie.repository.ScheduleRepository;
import com.example.movie.repository.TheaterRepository;
import com.example.movie.utill.PagingUtil;

@Service
public class MemberService {

	@Autowired
	private HttpSession session;
	@Autowired
	private AdminMapper aMapper;
	@Autowired
	private MemberMapper mMapper;

	private ModelAndView mv;
	@Autowired
	ScheduleRepository scheduleRepository;

	@Autowired
	MovieOfficialRepository movieOfficialRepository;

	@Autowired
	TheaterRepository theaterRepository;

	@Autowired
	RoomRepository roomRepository;

	@Autowired 
	ScheduleDetailRepository scheduleDetailRepository;

	@Autowired
	ReservationRepositoryCustom reservationRepositoryCustom;

	public ModelAndView memberUpdateFrm() {
		mv = new ModelAndView();

		MemberDto mem = (MemberDto)session.getAttribute("userInfo");

		String birth = mem.getM_birth();
		// 생일 문자열 뒤에 00:00:00을 없애기 위한 반복문
		for(int i = 10; i < birth.length(); i++) {

			birth = birth.substring(0,i);
		}

		mem.setM_birth(birth);

		mv.addObject("member",mem);

		mv.setViewName("memberUpdateFrm");

		return mv;
	}

	//문의글 리스트
	public ModelAndView selectQuestion(Integer pageNum, int listCnt, String View) {
		mv = new ModelAndView();

		int num = (pageNum == null)? 1 : pageNum;

		MemberDto member = (MemberDto)session.getAttribute("userInfo");

		String id = member.getM_id();

		/*	Map<String, Integer> pmap = 
				new HashMap<String, Integer>();
		pmap.put("num", num);
		pmap.put("lcnt", listCnt);
		 */
		List<QuestionDto> aqList = mMapper.selectQuestion(id);

		List<QuestionDto> qList = new ArrayList<QuestionDto>();
		//가져온 글리스트가 있을때만 페이징처리
		if(!aqList.isEmpty()) {

			int page = (num -1) * listCnt;

			int forpage = page-1;

			int list = listCnt -1 ;

			Collections.reverse(aqList);

			for(int i = 0; i <= forpage; i++) {
				//pagenum에 해당하지않는 출력되지 않아야 할 앞의 게시물 리스트에서 삭제

				if(page <= 0) {
					//1페이지일 경우 page가 0이 되는바람에 forpage가 음수
					break;
				}

				int j=0;
				//삭제한걸 자꾸 재정렬해서 0번째 값 삭제로 고정
				aqList.remove(j);
			}


			for(int i = 0; i <= list; i++) {
				//qList로 옮기고 저장
				if(aqList.size() <= list) {

					list = aqList.size()-1;
					//size가 출력해야할list보다 작으면 그만큼만 출력하도록
				}
				QuestionDto que = aqList.get(i);

				qList.add(que);
			}
			
			for(int i = 0; i< qList.size(); i++) {
				QuestionDto qDto = qList.get(i);
				int qnum = qDto.getQues_no();
				int ques_state = qDto.getQues_state();
				
				if(ques_state == 1) {
					quesReplyDto qrDto = aMapper.selectQuesReply(qnum);
					
					int quesReplyNum = qrDto.getQues_reply_no();
					String quesReplyTitle = qrDto.getQues_reply_title();
					
					qDto.setQues_reply_no(quesReplyNum);
					qDto.setQues_reply_title(quesReplyTitle);
					
					qList.remove(i);
					qList.add(i,qDto);
				}
			}
		} 
		
		mv.addObject("qList",qList);
		//전체 글 갯수
		int maxNum = mMapper.selectQuestionCnt(id);
		//페이징 처리.
		String pageHtml = getPaging1(num,listCnt,View,maxNum);
		mv.addObject("paging", pageHtml);

		//세션에 페이지번호 저장
		//글작성 화면, 글내용 상세보기 화면 등에서 다시 목록으로
		//돌아갈때 보고 있던 페이지가 나오도록 하기 위해.
		session.setAttribute("pageNum", num);

		//호출한곳에 맞는 view로 반환
		mv.setViewName(View);

		return mv;
	}
	
	private String getPaging1(int num, int listCnt,String view,int maxNum) {
		String pageHtml = null;

		int pageCnt = 3;

		//맞는 view로 listName 수정
		//String listName = view;

		PagingUtil paging = new PagingUtil(maxNum, num, listCnt, 
				pageCnt, view);

		pageHtml = paging.makePaging();

		return pageHtml;
	}
	
	//영화리뷰리스트
	public ModelAndView pmvReviewFrm(Integer pageNum,int listCnt,String View) {

		mv = new ModelAndView();

		int num = (pageNum == null)? 1 : pageNum;

		MemberDto member = (MemberDto)session.getAttribute("userInfo");
		String id = member.getM_id();

		//id로 리뷰들 가져옴
		List<mvReviewDto> apmvrList = mMapper.selectpmvReview(id);

		List<mvReviewDto> pmvrList = new ArrayList<mvReviewDto>();		

		//가져온 글리스트가 있을때만 페이징처리
		if(!apmvrList.isEmpty()) {

			int page = (num -1) * listCnt;

			int forpage = page-1;

			int list = listCnt -1 ;
			//mv_review번호를 비교해 큰수 부터(시퀀스 최신순)리스트 정렬
			for(int i = 0; i < apmvrList.size(); i++) {

				for(int j = i+1; j < apmvrList.size(); j++) {

					mvReviewDto mvr1 = apmvrList.get(i);

					int mvrnum1 = mvr1.getMv_review();

					mvReviewDto mvr2 = apmvrList.get(j);

					int mvrnum2 = mvr2.getMv_review();

					if(mvrnum1 < mvrnum2) {
						mvReviewDto mvr3 = mvr1;

						apmvrList.set(i, mvr2);

						apmvrList.set(j, mvr3);
					}
				}
			}

			for(int i = 0; i <= forpage; i++) {
				//pagenum에 해당하지않는 출력되지 않아야 할 앞의 게시물 리스트에서 삭제

				if(page <= 0) {
					//1페이지일 경우 page가 0이 되는바람에 forpage가 음수로 되어 반복문난리남.
					break;
				}

				int j=0;
				//0번째 값 삭제로 고정
				apmvrList.remove(j);
			}

			for(int i = 0; i <= list; i++) {
				//pmvrList로 옮기고 저장
				if(apmvrList.size() <= list) {

					list = apmvrList.size()-1;
					//size가 출력해야할list보다 적으면 그만큼만 출력하도록 함 안할시 오류뜸  
				}
				mvReviewDto amvr = apmvrList.get(i);

				String movieCd = amvr.getMv_review_moviecd();

				String mvname = mMapper.selectMovieName(movieCd);

				mvReviewDto mvr = amvr;
				//옮기고 영화이름 추가
				mvr.setMvName(mvname);
				//영화이름 추가한거 리스트에 추가
				pmvrList.add(mvr);
			}
		}

		mv.addObject("mvrList",pmvrList);
		//전체 리뷰 갯수
		int maxNum = mMapper.selectpmvReviewCnt(id);
		//페이징 처리.
		String pageHtml = getPaging1(num,listCnt,View,maxNum);
		mv.addObject("paging", pageHtml);

		//세션에 페이지번호 저장
		//글작성 화면, 글내용 상세보기 화면 등에서 다시 목록으로
		//돌아갈때 보고 있던 페이지가 나오도록 하기 위해.
		session.setAttribute("pageNum", num);

		mv.setViewName(View);

		return mv;
	}

	@Transactional
	public String delMvReview(int mvrnum,RedirectAttributes rttr) {
		String view = null;

		try {
			mMapper.delMvReview(mvrnum);
			view = "redirect:pmvReviewFrm";

			rttr.addFlashAttribute("msg", "삭제 성공");
		}catch(Exception e) {
			view = "redirect:pmvReviewFrm";
			rttr.addFlashAttribute("msg", "삭제 실패");
		}

		//String view = "redirect:pmvReviewFrm";

		return view;
	}

	//마이페이지 예매/결제 내역 출력.
	public ModelAndView selectPurchase (Integer pageNum, int listCnt, String View) {

		int num = (pageNum == null)? 1 : pageNum;

		mv = new ModelAndView();

		//세션에서id가져옴
		MemberDto member = (MemberDto)session.getAttribute("userInfo");
		String id = member.getM_id();

		//마이페이지에 출력할 것이므로 ...가져온 id에맞는 예매한 예매리스트
		List<reservationDto> rsrvList = mMapper.selectRsrvByid(id);

		
		if(View.equals("purchaseCancelFrm")) {//결제취소페이지 호출
			for(int i = 0; i < rsrvList.size(); i++) {

				reservationDto rsrvDto = rsrvList.get(i);

				int state = rsrvDto.getRsrv_status();
				//결제취소 == state 1, 0일경우 출력할 리스트에서 제거
				if(state == 0) {
					rsrvList.remove(i);
					i--;
				}
			}
		}
		else {//결제내역 페이지 에서 호출했을경우

			//id에맞는취소된 예매내역은 리스트에서 삭제후 저장
			for(int i = 0; i < rsrvList.size(); i++) {

				reservationDto rsrvDto = rsrvList.get(i);

				int state = rsrvDto.getRsrv_status();
				//결제취소 == state 1, 1일경우 출력할 리스트에서 제거
				if(state == 1) {
					rsrvList.remove(i);
					i--;
				}
			}
		}
		if(!rsrvList.isEmpty()) {
			//극장이름,영화등을 함께 묶어서 출력하기위한 for문
			for(int i = 0; i < rsrvList.size(); i++) {

				reservationDto rsrvDto = rsrvList.get(i);
				reservationDto schDate = new reservationDto();

				//예매번호로 에매테이블에서 스케줄번호찾기
				int schno = rsrvDto.getSch_code();
				//스케줄번호로 스케줄테이블에서 극장코드 찾기
				int thcode = mMapper.selectThcode(schno);
				//극장코드로 극장테이블에서 출력할 극장이름 찾기
				String thname = mMapper.selectThname(thcode);
				//스케줄번호로 스케줄테이블에서 무비코드 찾기
				String mvcd = mMapper.selectMoviecode(schno);
				//스케줄번호로 스케줄테이블에서 스케줄시간찾기
				schDate = mMapper.selectSchTime(schno);
				Timestamp sch_date = schDate.getSch_date();
				//무비코드로 출력할 영화이름찾기 
				String mvname = mMapper.selectMovieName(mvcd);
				
				rsrvDto.setMvname(mvname);
				rsrvDto.setThname(thname);
				rsrvDto.setSch_date(sch_date);

				rsrvList.remove(i);
				rsrvList.add(i,rsrvDto);

			}
		}
		//전체 글 갯수
		int maxNum = rsrvList.size();
		//옮길 리스트
		List<reservationDto> rsrvPList = new ArrayList<reservationDto>();
		//가져온 리스트가 있을때만 페이징처리
		if(!rsrvList.isEmpty()) {

			//삭제해야할 리스트 갯수.. (현재 페이지말고 앞의 페이지 갯수 * 페이지별 게시글 수)
			int page = (num -1) * listCnt;

			//리스트,배열은 0번부터 시작이므로 1빼준다
			int forpage = page-1;
			int list = listCnt -1 ;

			//현재pagenum에 해당하지않는 출력되지 않아야 할 앞의 게시물 리스트에서 삭제
			for(int i = 0; i <= forpage; i++) {

				if(page <= 0) {
					//1페이지일 경우 page가 0이 되는바람에 forpage가 음수
					break;
				}

				int j=0;
				//0번째 값 삭제로 고정
				rsrvList.remove(j);
			}


			for(int i = 0; i <= list; i++) {
				//nmpList로 옮기고 저장
				if(rsrvList.size() <= list) {

					list = rsrvList.size()-1;
					//size가 출력해야할list보다 작으면 그만큼만 출력하도록 함 안할시 오류뜸  
				}
				reservationDto rsrvDto = rsrvList.get(i);

				rsrvPList.add(rsrvDto);
			}
		}

		mv.addObject("qList",rsrvPList);

		//페이징 처리.
		String pageHtml = getPaging1(num,listCnt,View,maxNum);
		mv.addObject("paging", pageHtml);

		//세션에 페이지번호 저장
		//글작성 화면, 글내용 상세보기 화면 등에서 다시 목록으로
		//돌아갈때 보고 있던 페이지가 나오도록 하기 위해.
		session.setAttribute("pageNum", num);

		//호출한곳에 맞는 view로 반환
		mv.setViewName(View);

		return mv;
	}

	private int listCnt = 4;//페이지 당 게시글 개수
	//회원 정보 출력을 위한 인출
	public List<MemberDto> getMemberList(Integer pageNum) {

		pageNum = (pageNum == null) ? 1 : pageNum;
		System.out.println("pageNum"+pageNum);		
		Map<String, Integer> mmap = 
				new HashMap<String, Integer>();
		mmap.put("num", pageNum);
		mmap.put("lcnt", listCnt);

		System.out.println("listCnt = "+listCnt);			
		List<MemberDto> mList = mMapper.getList(mmap);
		System.out.println("mList.size = "+mList.size());		
		System.out.println("BoardCnt = "+mMapper.getBoardCnt()); //전체 글 개수 가져오는 mapper

		return mList;
	}
	//회원정보 목록 페이징 처리.
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
		System.out.println("listCnt = "+listCnt);
		System.out.println("num = "+num);
		System.out.println("회원전체:"+maxNum);
		System.out.println("pageCnt(하단에 보여질 페이지넘버) = "+pageCnt);
		System.out.println("pageHTML = "+pageHtml);

		return pageHtml;
	}

	public String deletemember(RedirectAttributes rttr) {

		String msg = null;
		String view = null;

		MemberDto mem = (MemberDto)session.getAttribute("userInfo");
		String m_id = mem.getM_id();

		try {

			mMapper.deleteMember(m_id);

			session.invalidate();
			msg = "삭제 성공";

			view = "redirect:/";

		} catch(Exception e) {

			msg="삭제 실패";

			view = "redirect:mypage";
		}

		rttr.addFlashAttribute("msg", msg);

		return view;
	}

	public ModelAndView memberSelect(String m_id) {

		ModelAndView mv = new ModelAndView();

		List<MemberDto> mselectList = mMapper.selectMember(m_id);

		System.out.println("검색 결과 = "+mselectList);
		mv.addObject("mList", mselectList);
		return mv;
	}
	//회원 작성 1대1문의글 출력.(m_id로 검색)
	public ModelAndView mboardSelect(String m_id) {
		//mMap.getmboardSelect(m_id);
		ModelAndView mv = new ModelAndView();
		List<quesboardDto> mbList = aMapper.getquesboardSelect(m_id);
		mv.addObject("qlist", mbList);

		System.out.println("mbList = "+mbList);
		return mv;
	}
	//검색한 회원 1대1 작성 글 목록 페이징 처리
	public String getsearchPaging(String m_id) {
		System.out.println();
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
				if(member.getM_id().equals("admin")) {
					view = "redirect:adminPage";
				}
				else {
					view = "redirect:/";
				}
				
				// member 정보를 세션에 저장
				session.setAttribute("userInfo", member);
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

	// 영화 상세 페이지 이동
	public ModelAndView movieDetail(String movie_cd) {

		mv = new ModelAndView();

		// 영화 상세 정보
		MovieOfficialDto mvOfficialDto = mMapper.movieDetail(movie_cd);
		// 관람평 목록 가져오기
		List<ReviewMovieDto> reviewMovie = mMapper.reviewMovie(movie_cd);
		List<TheaterDto> theaterCode = mMapper.getTheaterCode(movie_cd);
		
		mv.addObject("mvOfficial", mvOfficialDto);
		mv.addObject("reviewMovie", reviewMovie);
		mv.addObject("theaterName", theaterCode);

		mv.setViewName("movieDetail");

		return mv;
	}

	// 이용자 관람평 작성 및 목록 출력
	@Transactional
	public Map<String, List<ReviewMovieDto>> insertReviewMovie(ReviewMovieDto reviewMovieDto) {
		Map<String, List<ReviewMovieDto>> reviewListMap = null;

		try {
			// 이용자 관람평 작성
			mMapper.insertReviewMovie(reviewMovieDto);

			// 이용자 관람평 목록 다시 검색
			List<ReviewMovieDto> reviewMovieList = mMapper.selectReviewMovieList(reviewMovieDto.getMv_review_moviecd()); 

			reviewListMap = new HashMap<String, List<ReviewMovieDto>>();
			reviewListMap.put("reviewMovieList", reviewMovieList);
		} catch (Exception e) {
			e.printStackTrace();
			reviewListMap = null;
		}
		return reviewListMap;
	}

	public List<Map<String, Object>> getSch(Integer thCode) {
		//스케쥴을 언제부터 언제까지 가져올 것인지 세
		LocalDate now = LocalDate.now();
		String date= now.toString();	
		LocalDateTime dateTime = LocalDateTime.parse(date+" 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));		
		LocalDate lastDate= now.plusWeeks(2);//2주 마지막날

		Date startDate = java.sql.Date.valueOf(dateTime.toLocalDate());
		Date endDate = java.sql.Date.valueOf(lastDate);


		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Schedule> schduleList = scheduleRepository.findByThCodeAndSchDateBetween(thCode,startDate, endDate);
		for(int i = 0; i < schduleList.size(); i++) {
			String movieCd = schduleList.get(i).getMovieCd();
			Integer thcode = schduleList.get(i).getThCode();
			Integer roomNo = schduleList.get(i).getRoomNo();
			Integer schCode = schduleList.get(i).getSchCode();

			Map<String, Object> map = new HashMap<String, Object>();
			Optional<MovieOfficial> movieOfficialOpt = movieOfficialRepository.findById(movieCd);

			Optional<Theater> theaterOpt = theaterRepository.findById(thCode);
			Theater theater = theaterOpt.orElse(null);
			Room room = roomRepository.findByThCodeAndRoomNo(thCode, roomNo);			
			MovieOfficial movieOfficial = movieOfficialOpt.orElse(null);

			List<ScheduleDetail> scheduleDetail = scheduleDetailRepository.findBySchCode(schCode);
			schduleList.get(i).setScheduleDetail(scheduleDetail);

			for(int j = 0; j < scheduleDetail.size(); j++) {
				Integer schDetailSeq = scheduleDetail.get(j).getSchDetailSeq();

				List<String> seatNo = reservationRepositoryCustom.getRsrvSeatNoList(schCode, schDetailSeq);
				scheduleDetail.get(j).setRsrvSeatCnt(seatNo.size());

			}
			map.put("schedule", schduleList.get(i));
			map.put("room", room);
			map.put("movieOfficial", movieOfficial);
			map.put("theater", theater);
			list.add(map);
		}
		return list;

	}

	public List<Map<String, Object>> getSchedule(Date schDate, Integer thCode) {
		List<Schedule> schduleList = scheduleRepository.findByThCodeAndSchDate(thCode,schDate);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(int i = 0; i < schduleList.size(); i++) {
			String movieCd = schduleList.get(i).getMovieCd();
			Integer thcode = schduleList.get(i).getThCode();
			Integer roomNo = schduleList.get(i).getRoomNo();
			Integer schCode = schduleList.get(i).getSchCode();
			
			Optional<Theater> theaterOpt = theaterRepository.findById(thCode);
			Theater theater = theaterOpt.orElse(null);
			
			Map<String, Object> map = new HashMap<String, Object>();
			Optional<MovieOfficial> movieOfficialOpt = movieOfficialRepository.findById(movieCd);

			Room room = roomRepository.findByThCodeAndRoomNo(thCode, roomNo);			
			MovieOfficial movieOfficial = movieOfficialOpt.orElse(null);

			List<ScheduleDetail> scheduleDetail = scheduleDetailRepository.findBySchCode(schCode);
			schduleList.get(i).setScheduleDetail(scheduleDetail);

			for(int j = 0; j < scheduleDetail.size(); j++) {
				Integer schDetailSeq = scheduleDetail.get(j).getSchDetailSeq();

				List<String> seatNo = reservationRepositoryCustom.getRsrvSeatNoList(schCode, schDetailSeq);
				scheduleDetail.get(j).setRsrvSeatCnt(seatNo.size());

			}
			map.put("schedule", schduleList.get(i));
			map.put("room", room);
			map.put("movieOfficial", movieOfficial);
			map.put("theater", theater);
			list.add(map);
		}
		return list;
	}

	// 현재상영작 목록 페이지 이동(현재상영작 불러오기)
	public ModelAndView getMovieList(String mainMovieSearch) {
		mv = new ModelAndView();
		List<MovieOfficialDto> movieList = mMapper.getMovieList(mainMovieSearch);
		
		mv.addObject("movieList", movieList);
		mv.setViewName("currentMovieList");

		return mv;
	}

	public List<TheaterDto> selectThcode() {
		List<TheaterDto> thCodeList = mMapper.seletThkey();
		//mv = mMapper.seletThkey();
		
		return thCodeList;
	}

	public ModelAndView memReadQuesRe(int ques_no, int view) {
		
		quesReplyDto qrDto = aMapper.selectQuesReply(ques_no);
		if(view == 0) {
			mv.setViewName("memberQuesReplyRead");
			
		}else{
			mv.setViewName("AdminQuesReplyRead");
		}
		mv.addObject("readqrDto", qrDto);
		return mv;
	}

	// 통합 영화 목록 출력
	public List<Map<String, Object>> totalMovieList() {

		//스케쥴을 언제부터 언제까지 가져올 것인지 세
		LocalDate now = LocalDate.now();
		String date= now.toString();	
		LocalDateTime dateTime = LocalDateTime.parse(date+" 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));		
		LocalDate lastDate= now.plusWeeks(1);//1주 마지막날

		Date startDate = java.sql.Date.valueOf(dateTime.toLocalDate());
		Date endDate = java.sql.Date.valueOf(lastDate);


		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		List<Schedule> schduleList = scheduleRepository.findBySchDateBetween(startDate, endDate);
		for(int i = 0; i < schduleList.size(); i++) {
			
		}

		return null;
	}

	public String adminDeleteMember(String m_id, RedirectAttributes rttr) {
		String msg = null;
		String view = null;
		try {
			mMapper.deleteMember(m_id);	
			msg = "회원 삭제 성공";
		} catch (Exception e) {
		msg = "삭제 실패";
		}
		rttr.addFlashAttribute("msg", msg);
		view = "redirect:mmanage";
		return view;
	}

	// 박스오피스 목록
	public ModelAndView boxOffice() {
		List<MovieOfficialDto> boxOffice = mMapper.getBoxOfficeList();
			
		mv = new ModelAndView();
		
		mv.addObject("mvOfficial", boxOffice);
		mv.setViewName("index");
		
		return mv;
	}
}
