package com.example.movie.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.movie.mapper.BusinessMapper;
import com.example.movie.repository.MovieOfficialRepository;
import com.example.movie.repository.ScheduleDetailRepository;
import com.example.movie.repository.ScheduleRepository;
import com.example.movie.common.AwsS3;
import com.example.movie.dto.BusinessDto;
import com.example.movie.dto.MovieOfficialDto;
import com.example.movie.dto.RoomDto;
import com.example.movie.dto.TheaterDto;
import com.example.movie.entity.MovieOfficial;
import com.example.movie.entity.Schedule;
import com.example.movie.entity.ScheduleDetail;
import com.example.movie.dto.RoomDto;
import com.example.movie.dto.SeatDto;
import com.example.movie.mapper.BusinessMapper;

@Service
public class BusinessService {
	
	@Autowired
	private BusinessMapper buMapper;
	@Autowired
	private HttpSession session;
	@Autowired
	private AwsS3 awsS3;
	
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
					
					//dto에 넣기
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

			view = "redirect:roomlist";
			rttr.addFlashAttribute("msg", "삭제 성공");
		} catch (Exception e) {
			view = "redirect:roomlist";
			rttr.addFlashAttribute("msg", "삭제 실패");
		}

		return view;
	}

	//상영관 등록 페이지 이동
	public ModelAndView roomInsertFrm() {
		mv = new ModelAndView();

		mv.setViewName("roomInsertFrm");

		return mv;
	}

	//상영관 등록 처리
	@Transactional
	public String roomInsert(HttpServletRequest request,
			RedirectAttributes rttr) {
		String view = null;
		String msg = null;
		
		int roomno = Integer.parseInt(request.getParameter("roomno"));
		int thcode = Integer.parseInt(request.getParameter("thcode"));
		String roclass = request.getParameter("roclass");
		String roname = request.getParameter("roname");
		int roomrow = Integer.parseInt(request.getParameter("roomrow"));
		int roomcol = Integer.parseInt(request.getParameter("roomcol"));
		int seatcnt = Integer.parseInt(request.getParameter("seatcnt"));
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
				seDto.setThcode(thcode);
				seDto.setRoomno(roomno);

				String seatNo = seatNoArray[i];
				seDto.setSeatno(seatNo);

				if(col <= roomcol) {
					seDto.setSeatcol(col);
					col++;
				}
				else {
					col=1;
					seDto.setSeatcol(col);
					col++;
					row++;
				}
				if(row <= roomrow) {
					seDto.setSeatrow(row);
				}

				for(int j = 0; j <= seatNotArray.length-1; j++) {
					String seatNot = seatNotArray[j];
					if(seatNo.equals(seatNot)) {
						seDto.setSeatstat(0);
						break;
					} else {
						seDto.setSeatstat(1);
					}
				}

				buMapper.seatInsert(seDto);
			}

			//buMapper.seatUpdate(seDto);
			view = "redirect:roomlist";
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
		
		mv.setViewName("th/theater");
		
		return mv;
	}
	
	//영화 검색 
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
		roomList = buMapper.getRoomList();
		
		mv.addObject("movieList", movieList);
		mv.addObject("roomList", roomList);
		mv.addObject("theaterList", theaterList);
		
		mv.setViewName("sche/scheduleAdd");
		
		return mv;
	}

	public String testInsert(Date roomStartTime, Date roomEndTime, Integer thcode, 
			String[] mvcode, Integer room, String mvdate, String wait) {
		
		//상영관 시작 시간을 date에서 calendar로 변환
		Calendar startCalendar = Calendar.getInstance();		
		startCalendar.setTime(roomStartTime);
		
		//상영관 종료 시간을 date에서 calendar로 변환
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(roomEndTime);
		
		//localDateTime localDateTime = LocalDateTime.now();
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		//상영관 시작 시간
		//LocalDateTime roomStartTime = LocalDateTime.parse(starttime, formatter);
		//상영관 종료 시간
		//LocalDateTime roomEndTime = LocalDateTime.parse(endtime, formatter);
		
		for(int i = 0; i < mvcode.length; i++) {
			//영화코드(숫자)를 받은 변수 mvcd
			String mvcd = mvcode[i];
			
			//받아온 영화코드로 관리자가 등록한 영화 테이블 내용 검색
			Optional<MovieOfficial> mv = movieOfficialRepository.findById(mvcd);
			if(mv.isPresent()) {//내용이 있으면?
				
				//받아 온 상영날짜를 date 형태로 변환
				SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
				Date movieDate;
				try {
					movieDate = dateFormat.parse(mvdate);
					
					//대기 시간
					int waittingTime;
					waittingTime = Integer.parseInt(wait);
					
					//상영시간표 dto에 넣음
					Schedule schedule = new Schedule();
					schedule.setMovieCd(mv.get().getMovieCd());//영화코드
					schedule.setThCode(thcode);//영화관코드
					schedule.setRoomNo(room);//상영관 번호
					schedule.setSchDate(movieDate);//상영날짜
					schedule.setSchTime(waittingTime);//상영 전 대기시간
					
					//영화 러닝타임 가져옴
					int runningTime = mv.get().getShowTm();
					
					//러닝타임 + 휴식(대기) 시간
					int realTime = runningTime + waittingTime;	
					
					//영화 끝난 시간 = 상영관 시작시간 + 영화 러닝타임
					Calendar movieEndCalendar = startCalendar;
					movieEndCalendar.add(Calendar.MINUTE, runningTime);
					//LocalDateTime movieEndTime = roomStartTime.plusMinutes(runningTime);
					
					//영화 종료 시간이 상영관 종료 시간을 넘을 경우 값을 넣지 않는다
					if(movieEndCalendar.before(endCalendar)) {
					
						//상영시간표를 db에 넣기
						scheduleRepository.save(schedule);
					
						//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
					
						//영화 종료 시간을 calendar에서 date로 변환(db에 넣기 위함)
						Date movieEndTime = new Date(movieEndCalendar.getTimeInMillis());
						
						//String mvStartTime = roomStartTime.toString();
						//Date movieStartTime = format.parse(mvStartTime);
					
						//영화 시작 시간을 calendar에서 date로 변환(db에 넣기 위함)
						Date movieStartTime = new Date(startCalendar.getTimeInMillis());
					
						//String mvEndTime = movieEndTime.toString();
						//Date dmovieEndTime = format.parse(mvEndTime);
					
						//상영시간표 상세
						ScheduleDetail scheduleDetail = new ScheduleDetail();
					
						scheduleDetail.setSchCode(schedule.getSchCode());//상영시간표 키
						scheduleDetail.setSchDetailStart(movieStartTime);//영화 시작 시간
						scheduleDetail.setSchDetailEnd(movieEndTime);//영화 종료 시간
					
						//상영시간표 상세를 db에 넣기
						scheduleDetailRepository.save(scheduleDetail);
					
						//영화 시작 시간 = 저번 영화 시간 + (러닝 타임 + 휴식 시간)
						startCalendar.add(Calendar.MINUTE, realTime);
					
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
		
		String view = "redirect:schedule";
		
		return view;
	
	}
}