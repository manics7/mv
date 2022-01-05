package com.example.movie.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.example.movie.dto.RoomDto;
import com.example.movie.dto.SeatDto;
import com.example.movie.dto.TestDto;
import com.example.movie.mapper.BusinessMapper;

@Service
public class BusinessService {
	@Autowired
	private BusinessMapper buMapper;
	@Autowired
	private HttpSession session;
	@Autowired
	private AwsS3 awsS3;
	
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
		roDto.setRoomno(roomno);
		roDto.setThcode(thcode);
		roDto.setRoclass(roclass);
		roDto.setRoname(roname);
		roDto.setRoomrow(roomrow);
		roDto.setRoomcol(roomcol);
		roDto.setSeatcnt(seatcnt);

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
	
}
