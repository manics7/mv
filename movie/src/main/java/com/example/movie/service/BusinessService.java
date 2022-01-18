package com.example.movie.service;

import java.io.BufferedInputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.common.AwsS3;
import com.example.movie.dto.BusinessDto;
import com.example.movie.dto.EventDto;
import com.example.movie.dto.MovieDto;
import com.example.movie.dto.MovieOfficialDto;
import com.example.movie.dto.RoomDto;
import com.example.movie.dto.ScheduleDetailDto;
import com.example.movie.dto.ScheduleDto;
import com.example.movie.dto.SeatDto;
import com.example.movie.dto.TheaterDto;
import com.example.movie.entity.MovieOfficial;
import com.example.movie.entity.Schedule;
import com.example.movie.entity.ScheduleDetail;
import com.example.movie.entity.TempMovie;
import com.example.movie.mapper.BusinessMapper;
import com.example.movie.repository.MovieOfficialRepository;
import com.example.movie.repository.ScheduleDetailRepository;
import com.example.movie.repository.ScheduleRepository;
import com.example.movie.repository.TempMovieRepository;
import com.example.movie.utill.PagingUtil;

@Service
public class BusinessService {

	@Autowired
	private BusinessMapper buMapper;
	@Autowired
	private HttpSession session;
	@Autowired
	private AwsS3 awsS3;

	@Autowired
	TempMovieRepository tempMovieRepository;

	@Autowired
	MovieOfficialRepository movieOfficialRepository;

	@Autowired
	ScheduleRepository scheduleRepository;

	@Autowired
	ScheduleDetailRepository scheduleDetailRepository;
	
	@Value("${aws.s3.bucket}")
	private String bucket;

	@Value("${aws.s3.bucketURL}")
	private String bucketURL;

	ModelAndView mv;

	// 이용자 회원가입 아이디 중복체크
	public String buIdCheck(String bid) {

		String res = null;

		int cnt = buMapper.buIdCheck(bid);
		if(cnt == 0) 
		{
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
					String LfileName = awsS3.getFileURL(bucket, logoName.get(i));

					//dto에 넣기
					theater.setTh_logo(LfileName);
				}

				//영화관 사진 파일의 이름 가져오기
				List<MultipartFile> theaterFiles = multi.getFiles("theaterFiles");
				List<String> theaterName = awsS3.uploadFile(theaterFiles);

				//영화관 사진 파일이 있으면 파일 이름을 dto에 담기
				for(int i = 0; i < theaterName.size(); i++) {
					String TfileName = awsS3.getFileURL(bucket, theaterName.get(i));
					
					if(i==0) {
						theater.setTh_image1(TfileName);	
					}else if(i==1) {
						theater.setTh_image2(TfileName);
					}else if(i==2){
						theater.setTh_image3(TfileName);
					}
				}
			} 
			//dto에 담은 내용을 mapper로 넘기기  
			buMapper.theaterAdd(theater);

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
	
	//영화관 수정하기
	@Transactional
	public String theaterUpdate(MultipartHttpServletRequest multi, RedirectAttributes rttr) {
		String view = null;
		String msg = null;

		//데이터 추출
		String id = multi.getParameter("bid");//사업자 아이디
		String code1 = multi.getParameter("th_code");
		int code = Integer.parseInt(code1);
		String name = multi.getParameter("thname");//영화관 이름
		String place = multi.getParameter("thplace");//위치 정보(오시는 길)
		String tel = multi.getParameter("thtel");//영화관 번호
		String parking = multi.getParameter("thpark");//주차 안내
		String introduce = multi.getParameter("thintro");//영화관 소개
		String regeion = multi.getParameter("reNum");//지역 코드
		String check = multi.getParameter("logoCheck");//로고 이미지
		String check2 = multi.getParameter("theaterCheck");//영화관 사진
		
		//텍스트 내용을 dto에 담기
		TheaterDto theater = new TheaterDto();
		theater.setB_id(id);//사업자 아이디
		theater.setTh_code(code);
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
					String LfileName = awsS3.getFileURL(bucket, logoName.get(i));

					//dto에 넣기
					theater.setTh_logo(LfileName);
				}

				//영화관 사진 파일의 이름 가져오기
				List<MultipartFile> theaterFiles = multi.getFiles("theaterFiles");
				List<String> theaterName = awsS3.uploadFile(theaterFiles);

				//영화관 사진 파일이 있으면 파일 이름을 dto에 담기
				for(int i = 0; i < theaterName.size(); i++) {
					String TfileName = awsS3.getFileURL(bucket, theaterName.get(i));
					
					if(i==0) {
						theater.setTh_image1(TfileName);	
					}else if(i==1) {
						theater.setTh_image2(TfileName);
					}else if(i==2){
						theater.setTh_image3(TfileName);
					}
				}
			} 
			
			buMapper.theaterUpdate(theater);
			
			//영화관 정보 페이지로 이동
			view = "redirect:theater";
			msg = "수정 성공";

		} catch (Exception e) {
			e.printStackTrace();
			//다시 등록 페이지
			view = "redirect:thUpdate";
			msg = "수정 실패";
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return view;
	}
	
	//영화관 수정 페이지
	public ModelAndView thUpdate(int th_code) {
			
		TheaterDto thDto = buMapper.thUpdateInfo(th_code);
		
		mv = new ModelAndView();
		
		mv.addObject("thDto", thDto);
		mv.setViewName("./th/thUpdate");
		
		return mv;
	}

	//사업자페이지 사업자정보 (극장이름) 등.. 출력
	public ModelAndView businessPage() {

		mv = new ModelAndView();

		BusinessDto bDto = (BusinessDto)session.getAttribute("businessInfo");
		String Bid = bDto.getB_id();

		//List<String> thnList = new ArrayList<String>();
		String thName = buMapper.selectThNameByBid(Bid);

		mv.addObject("thName", thName);

		mv.setViewName("businessPage");

		return mv;
	}

	//상영관 목록 가져오기
	public ModelAndView getRoomList() {
		mv = new ModelAndView();

		List<RoomDto> roomList = buMapper.getRoomList();

		mv.addObject("roomList", roomList);

		mv.setViewName("roomList");

		return mv;
	}

	//상영관 삭제하기
	@Transactional
	public String roomDelete(int roomseq, RedirectAttributes rttr) {
		String view = null;

		try {
			buMapper.RoomDelete(roomseq);
			
			view = "redirect:roomList";
			rttr.addFlashAttribute("msg", "삭제 성공");
		} catch (Exception e) {
			view = "redirect:roomList";
			rttr.addFlashAttribute("msg", "삭제 실패");
		}

		return view;
	}

	//상영관 등록 페이지 이동
	public ModelAndView roomInsertFrm() {
		mv = new ModelAndView();

		String bId;
		BusinessDto bDto = (BusinessDto)session.getAttribute("businessInfo");
		bId = bDto.getB_id();

		List<TheaterDto> theaterList = buMapper.getTheaterList(bId);
		
		mv.addObject("thInfo", theaterList);
		
		mv.setViewName("roomInsertFrm");

		return mv;
	}

	//상영관 등록 처리
	@Transactional
	public String roomInsert(HttpServletRequest request,
			RedirectAttributes rttr) {
		String view = null;
		String msg = null;

		int roomno = Integer.parseInt(request.getParameter("room_no"));
		int thcode = Integer.parseInt(request.getParameter("th_code"));
		String roclass = request.getParameter("room_class");
		String roname = request.getParameter("room_name");
		int roomrow = Integer.parseInt(request.getParameter("room_row"));
		int roomcol = Integer.parseInt(request.getParameter("room_col"));
		int seatcnt = Integer.parseInt(request.getParameter("seat_cnt"));

		String[] seatNoArray = request.getParameterValues("seatno");
		String[] seatNotArray = request.getParameterValues("seatNot");
		int col = 1;
		int row = 1;
		//배열 자르기
		String seatNoAll = seatNoArray[0];
		seatNoArray = seatNoAll.split(",");
		//배열 자르기
		String seatNotAll = seatNotArray[0];
		seatNotArray = seatNotAll.split(",");

		RoomDto roDto = new RoomDto();
		roDto.setRoom_no(roomno);
		roDto.setTh_code(thcode);
		roDto.setRoom_class(roclass);
		roDto.setRoom_name(roname);
		roDto.setRoom_row(roomrow);
		roDto.setRoom_col(roomcol);
		roDto.setSeat_cnt(seatcnt);

		try {
			buMapper.roomInsert(roDto);

			for(int i = 0; i <= (roomrow*roomcol)-1; i++) {
				SeatDto seDto = new SeatDto();
				seDto.setTh_code(thcode);
				seDto.setRoom_no(roomno);

				String seatNo = seatNoArray[i];
				seDto.setSeat_no(seatNo);

				if(col <= roomcol) {
					seDto.setSeat_col(col);
					col++;
				}
				else {
					col=1;
					seDto.setSeat_col(col);
					col++;
					row++;
				}
				if(row <= roomrow) {
					seDto.setSeat_row(row);
				}

				for(int j = 0; j <= seatNotArray.length-1; j++) {
					String seatNot = seatNotArray[j];
					if(seatNo.equals(seatNot)) {
						seDto.setSeat_status(0);
						break;
					} else {
						seDto.setSeat_status(1);
					}
				}

				buMapper.seatInsert(seDto);
			}

			//buMapper.seatUpdate(seDto);
			view = "redirect:roomList";
			msg = "상영관 등록 완료";
		} catch (Exception e) {
			view = "redirect:roomInsertFrm";
			msg = "상영관 등록 실패";
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

		mv.setViewName("./th/theater");

		return mv;
	}
	
	//영화관, 영화, 상영관 검색(상영시간표를 등록하기 위해서) 
	public ModelAndView getInfoList() {
		mv = new ModelAndView();

		//영화관 검색
		String bId;
		BusinessDto bDto = (BusinessDto)session.getAttribute("businessInfo");
		bId = bDto.getB_id();

		List<TheaterDto> theaterList = new ArrayList<TheaterDto>();
		theaterList = buMapper.getTheaterList(bId);

		//영화
		List<MovieOfficialDto> movieList = new ArrayList<MovieOfficialDto>();
		movieList = buMapper.getMovieList();

		//상영관
		List<RoomDto> roomList = new ArrayList<RoomDto>();
		
		roomList = buMapper.getRoomInfoList(theaterList.get(0).getTh_code());
		
		mv.addObject("movieList", movieList);
		mv.addObject("roomList", roomList);
		mv.addObject("theaterList", theaterList);

		mv.setViewName("./sche/scheduleAdd");

		return mv;
	}
	
	//상영시간표 등록
	@Transactional
	public String testInsert(Date roomStartTime, Date roomEndTime, Integer thcode, 

    List<String> mvcode, Integer room, String mvdate, String wait) {
	
		//상영관 시작 시간을 date에서 calendar로 변환
		Calendar startCalendar = Calendar.getInstance();		
		startCalendar.setTime(roomStartTime);

		//상영관 종료 시간을 date에서 calendar로 변환
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(roomEndTime);
		
		Calendar movieEndCalendar = Calendar.getInstance();
		movieEndCalendar.setTime(roomStartTime);
		
		//대기 시간
		int waittingTime;
		waittingTime = Integer.parseInt(wait);
		
		for(int i = 0; i < mvcode.size(); i++) {
			//영화코드(숫자)를 받은 변수 mvcd
			String mvcd = mvcode.get(i);

			//받아온 영화코드로 관리자가 등록한 영화 테이블 내용 검색
			Optional<MovieOfficial> mv = movieOfficialRepository.findById(mvcd);
			
			//받아 온 상영날짜를 date 형태로 변환
			SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
			Date movieDate;
			movieDate = dateFormat.parse(mvdate);
			
			//상영시간표 dto에 넣음
			Schedule schedule = new Schedule();
			schedule.setMovieCd(mv.get().getMovieCd());//영화코드
			schedule.setThCode(thcode);//영화관코드
			schedule.setRoomNo(room);//상영관 번호
			schedule.setSchDate(movieDate);//상영날짜
			schedule.setSchTime(waittingTime);//상영 전 대기시간
			
			//상영시간표를 db에 넣기
			scheduleRepository.save(schedule);
			if(mv.isPresent()) {
				

				//받아 온 상영날짜를 date 형태로 변환
				SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
				Date movieDate;
				try {
					movieDate = dateFormat.parse(mvdate);

					//대기 시간
					int waittingTime;
					waittingTime = Integer.parseInt(wait);
					
					Schedule schedule = new Schedule();
					
					Optional<Schedule> schOpt = Optional.ofNullable(scheduleRepository.findByMovieCdAndThCodeAndRoomNoAndSchDate(mvcd, thcode, room, movieDate));
					if(schOpt.isEmpty()) {
						//상영시간표 dto에 넣음
						
						schedule.setMovieCd(mv.get().getMovieCd());//영화코드
						schedule.setThCode(thcode);//영화관코드
						schedule.setRoomNo(room);//상영관 번호
						schedule.setSchDate(movieDate);//상영날짜
						schedule.setSchTime(waittingTime);//상영 전 대기시간
					}else {
						schedule= schOpt.get();
					}
					

					//영화 러닝타임 가져옴
					int runningTime = mv.get().getShowTm();

					//러닝타임 + 휴식(대기) 시간
					int realTime = runningTime + waittingTime;	

					//영화 끝난 시간 = 상영관 시작시간 + 영화 러닝타임
					movieEndCalendar.add(Calendar.MINUTE, runningTime);
					
					//영화 종료 시간이 상영관 종료 시간을 넘을 경우 값을 넣지 않는다
					if(movieEndCalendar.before(endCalendar)) {

						//상영시간표를 db에 넣기
						scheduleRepository.save(schedule);		
						
						//Optional<List<ScheduleDetail>> detailOpt	= Optional.ofNullable(scheduleDetailRepository.findBySchCode(schedule.getSchCode()))							
							
												
							//영화 종료 시간을 calendar에서 date로
							Date movieEndTime = new Date(movieEndCalendar.getTimeInMillis());
							//영화 시작 시간을 calendar에서 date로
							Date movieStartTime = new Date(startCalendar.getTimeInMillis());
							//상영시간표 상세
							ScheduleDetail scheduleDetail = new ScheduleDetail();

							scheduleDetail.setSchCode(schedule.getSchCode());//상영시간표 키
							scheduleDetail.setSchDetailStart(movieStartTime);//영화 시작 시간
							scheduleDetail.setSchDetailEnd(movieEndTime);//영화 종료 시간

							//상영시간표 상세를 db에 넣기
							scheduleDetailRepository.save(scheduleDetail);

							//영화 시작 시간 = 저번 영화 시간 + (러닝 타임 + 휴식 시간)
							movieEndCalendar.add(Calendar.MINUTE, waittingTime);
							startCalendar.add(Calendar.MINUTE, realTime);

				}	
			}
		}
		
		//상영시간표 목록 페이지로 이동
		String view = "redirect:schedule";

		return view;
	}
	
	//상영시간표 목록을 출력
	public ModelAndView getScheduleList(Integer pageNum) {
		mv = new ModelAndView();
		
		//세션에 저장되어 있는 사업자 아이디
		String bId;
		BusinessDto bDto = (BusinessDto)session.getAttribute("businessInfo");
		bId = bDto.getB_id();
				
		//로그인한 사업자와 일치하는 영화관의 코드
		int theaterCode = buMapper.getTheaterCode(bId);
		
		// null or 페이지 번호
		int num = (pageNum == null) ? 1 : pageNum;
		int listCnt = 5;
				
		// 게시글 목록 가져오기
		Map<String, Integer> pmap = new HashMap<String, Integer>();
		pmap.put("num", num);
		pmap.put("lcnt", listCnt);
		pmap.put("th_code", theaterCode);
		
		//상영시간표 
		List<ScheduleDto> scheduleCode = new ArrayList<ScheduleDto>();
		scheduleCode = buMapper.getScheduleCode(pmap);
		
		List<ScheduleDto> movieCodeList = new ArrayList<ScheduleDto>();
		List<MovieOfficialDto> movieNameList = new ArrayList<MovieOfficialDto>();
		List<ScheduleDto> mvCodeList = new ArrayList<ScheduleDto>();
		List<MovieOfficialDto> mvNameList = new ArrayList<MovieOfficialDto>();
		
		//상영날짜
		List<ScheduleDto> scheduleDateList = new ArrayList<ScheduleDto>();
		List<String> screeningDate = new ArrayList<String>();
		
		//상영시작시간
		List<ScheduleDetailDto> startTimeList = new ArrayList<ScheduleDetailDto>();
		List<String> movieStart = new ArrayList<String>();
		
		//상영종료시간
		List<ScheduleDetailDto> endTimeList = new ArrayList<ScheduleDetailDto>();
		List<String> movieEnd = new ArrayList<String>();
		
		//상영관 번호, 상영관명, 상영관 종류
		List<ScheduleDto> roomNoList = new ArrayList<ScheduleDto>();
		List<RoomDto> scheduleRoomList = new ArrayList<RoomDto>();
		List<RoomDto> roomList = new ArrayList<RoomDto>();		
	
		for(int a = 0; a < scheduleCode.size(); a++) {
			ScheduleDto codeDto = scheduleCode.get(a);
			int schCode = codeDto.getSch_code();
			
			//상영관 번호, 상영관명, 상영관 종류
			roomNoList = buMapper.getRoomCode(schCode);
			for (int i = 0; i < roomNoList.size(); i++) {
				ScheduleDto roomNoDto = roomNoList.get(i);
				int roomNo = roomNoDto.getRoom_no();
				int thCode = codeDto.getTh_code();
				RoomDto rodto = buMapper.getScheduleRoomList(thCode, roomNo);
				scheduleRoomList.add(rodto);
			}
			
			
			//영화 코드
			movieCodeList = buMapper.getMovieCode(schCode);
			ScheduleDto movieCodeDto = movieCodeList.get(0);
			mvCodeList.add(movieCodeDto);
			
			//영화명
			String movieCode = movieCodeDto.getMovie_cd();
			
			movieNameList = buMapper.getMovieNameList(movieCode);
			MovieOfficialDto movieNameDto = movieNameList.get(0);
			mvNameList.add(movieNameDto);
			
			//상영날짜
			scheduleDateList = buMapper.getScheduleDateList(schCode);
			ScheduleDto scheduleDto = scheduleDateList.get(0);
			Date scheduleDate = scheduleDto.getSch_date();
			String dateList = DateFormatUtils.format(scheduleDate, "yyyy-MM-dd");
			
			screeningDate.add(dateList);
				
				//상영 시작 시간
				startTimeList = buMapper.getScheduleStartTime(schCode);
				
				//상영종료시간
				endTimeList = buMapper.getScheduleEndTime(schCode);
				
				//시작시간
				ScheduleDetailDto startDto = startTimeList.get(0);
				Date startTime = startDto.getSch_detail_start();
				String startList = DateFormatUtils.format(startTime, "HH:mm");
				
				movieStart.add(startList);
				//종료시간
				ScheduleDetailDto endDto = endTimeList.get(0);
				Date endTime = endDto.getSch_detail_end();
				String endList = DateFormatUtils.format(endTime, "HH:mm");
				
				movieEnd.add(endList);
		}
		mv.addObject("scheduleCode", scheduleCode);
		mv.addObject("scheduleRoomList", scheduleRoomList);
		mv.addObject("mvNameList", mvNameList);
		mv.addObject("screeningDate", screeningDate);
		mv.addObject("movieStart", movieStart);
		mv.addObject("movieEnd", movieEnd);

		// 페이징 처리
		String pageHtml = getPaging(num, "schedule");
		mv.addObject("paging", pageHtml);

		session.setAttribute("pageNum", num);
		
		mv.setViewName("sche/schedule");
		return mv;
	}
	
	// 페이징 처리
	private String getPaging(int num, String listName) {
		String pageHtml = null;
		
		//세션에 저장되어 있는 사업자 아이디
		String bId;
		BusinessDto bDto = (BusinessDto)session.getAttribute("businessInfo");
		bId = bDto.getB_id();
						
		//로그인한 사업자와 일치하는 영화관의 코드
		int theaterCode = buMapper.getTheaterCode(bId);

		// 전체 글 개수 구하기(DAO)
		int maxNum = buMapper.getScheduleCount(theaterCode);
		mv.addObject("maxNum", maxNum);
		// 한 페이지에 보여질 페이지 번호 개수
		int pageCnt = 5;

		PagingUtil paging = new PagingUtil(maxNum, num, 5, pageCnt, listName);

		pageHtml = paging.makePaging();

		return pageHtml;
	}
	
	//상영시간표 삭제
	@Transactional
	public String scheduleDelete(int sch_code, RedirectAttributes rttr) {
		String view = null;
		
		try {
			buMapper.scheduleDelete(sch_code);
			buMapper.scheduleDetailDelete(sch_code);
			
			view = "redirect:schedule";
			rttr.addFlashAttribute("msg", "삭제 성공");
		} catch (Exception e) {
			view = "redirect:schedule";
			rttr.addFlashAttribute("msg", "삭제 실패");
		}
		
		return view;
	}
	
	// 사업자 영화목록 임시 저장
	@Transactional
	public List<TempMovie> insertApiMovie(String date) throws Exception{

		JSONParser jsonparser = new JSONParser();
		JSONObject jsonobject = (JSONObject)jsonparser.parse(readUrl(date));
		JSONObject json =  (JSONObject) jsonobject.get("movieListResult");
		JSONArray array = (JSONArray)json.get("movieList");
		for(int i = 0 ; i < array.size(); i++){

			JSONObject entity = (JSONObject)array.get(i);
			String movieCd = (String) entity.get("movieCd");
			String movieNm = (String) entity.get("movieNm");
			String repGenreNm = (String) entity.get("repGenreNm");
			String openDt = (String) entity.get("openDt");
			String genreAlt = (String) entity.get("genreAlt");

			if(!repGenreNm.equals("성인물(에로)") && !repGenreNm.equals("멜로/로맨스") && !repGenreNm.equals("드라마")) {

//				System.out.print(movieCd + " / ");
//				System.out.print(movieNm + ", ");
//				System.out.print(repGenreNm + ", ");
//				System.out.println(openDt);

				TempMovie tempMovie = new TempMovie();


				tempMovie.setMovieCd(movieCd);
				tempMovie.setMovieNm(movieNm);
				tempMovie.setRepGenreNm(repGenreNm);
				tempMovie.setOpenDt(openDt);
				tempMovie.setGenreAlt(genreAlt);

				tempMovieRepository.save(tempMovie);

			}

		}

		//List<TempMovie> tempMovie = tempMovieRepository.findByOpenDtLessThanEqualOrderByOpenDtDesc(date.replaceAll("-", ""));
		List<TempMovie> tempMovie = tempMovieRepository.findByOpenDtLessThanEqual(date.replaceAll("-", ""));
		tempMovie = tempMovie.stream()
				.sorted(Comparator.comparing(TempMovie::getOpenDt)
				.reversed()).collect(Collectors.toList());
		return tempMovie;

	}

	// 사업자 영화목록 임시 저장'' 
	private String readUrl(String date) throws Exception {

		String Date = date.substring(0, 4);

		BufferedInputStream reader = null;

		try {
			URL url = new URL(
					//                   "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/"
					//                   + "searchDailyBoxOfficeList.json"
					//                   + "?key=558ab07b627efd244c134a66c1d278c9"
					//                   + "&targetDt=" + testDate);
					"http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json"
					+ "?key=558ab07b627efd244c134a66c1d278c9"
					+ "&curPage=1&itemPerPage=100"
					+ "&openStartDt=" + Date);
			reader = new BufferedInputStream(url.openStream());
			StringBuffer buffer = new StringBuffer();
			int i;
			byte[] b = new byte[4096];
			while( (i = reader.read(b)) != -1){
				buffer.append(new String(b, 0, i));
			}
			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	// 사업자 영화 등록
	@Transactional
	public String movieInsertProc(MultipartHttpServletRequest multi, RedirectAttributes rttr) throws ParseException {

		String view = null;
		String msg = null;

		String bar = "/";

		String movie_cd = multi.getParameter("movie_cd");
		String movie_nm = multi.getParameter("movie_nm");

		String openDt = multi.getParameter("open_dt");
		String open_Dt = openDt.substring(0, 4) + bar
				+ openDt.substring(4, 6) + bar
				+ openDt.substring(6);
		SimpleDateFormat dateT = new SimpleDateFormat("yyyy/MM/dd");
		Date open_dt = dateT.parse(open_Dt);

		String genre_nm = multi.getParameter("genre_nm");

		String show_Tm = multi.getParameter("show_tm");
		int show_tm = Integer.parseInt(show_Tm);

		String directors = multi.getParameter("directors");
		String actors = multi.getParameter("actors");
		String show_types = multi.getParameter("show_types");
		String watch_grade_nm = multi.getParameter("watch_grade_nm");
		String check = multi.getParameter("fileCheck");

		MovieDto movieDto = new MovieDto();

		movieDto.setMovie_cd(movie_cd);
		movieDto.setMovie_nm(movie_nm);
		movieDto.setOpen_dt(open_dt);
		movieDto.setGenre_nm(genre_nm);
		movieDto.setShow_tm(show_tm);
		movieDto.setDirectors(directors);
		movieDto.setActors(actors);
		movieDto.setShow_types(show_types);
		movieDto.setWatch_grade_nm(watch_grade_nm);
		
		try {

			if(check.equals("1")) {

				//로고 파일의 이름 가져오기
				List<MultipartFile> posterFile = multi.getFiles("poster");	
				List<String> posterName = awsS3.uploadFile(posterFile);

				//로고 사진 파일이 있으면 파일 이름을 dto에 담기
				for(int i = 0; i < posterName.size(); i ++) {
					String poster = awsS3.getFileURL(bucket, posterName.get(i));

					//dto에 넣기
					movieDto.setPoster(poster);
				}
			}
			
			// 사업자 영화등록에 필요한 th_code를 session에서 b_id로 찾아오기
			BusinessDto bDto = (BusinessDto)session.getAttribute("businessInfo");
			
			String bId = bDto.getB_id();
			
			int th_code =0;
			try {
				th_code = buMapper.getThcode(bId);
			} catch (Exception e) {
				th_code=0;
			}
			if(th_code != 0) {
				
				movieDto.setTh_code(th_code);
				buMapper.movieInsertProc(movieDto);
				
				view = "redirect:businessPage";
				msg = "영화 등록 성공";
			}
			else {
				view = "redirect:movieInsert"; 
				msg = "영화관을 먼저 등록해주세요!";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			view = "redirect:movieInsert";
			msg = "영화 등록 실패";
		}
		
		rttr.addFlashAttribute("msg", msg);

		return view;
	}

	//이벤트 관리 페이지 이동
	public ModelAndView getEventList() {
		mv = new ModelAndView();
		
		List<EventDto> eventList = buMapper.getEventList();

		mv.addObject("eventList", eventList);

		mv.setViewName("eventList");
		
		return mv;
	}

	//이벤트 등록 페이지 이동
	public ModelAndView eventInsertFrm() {
		mv = new ModelAndView();

		mv.setViewName("eventInsertFrm");
		
		return mv;
	}

} // class end
