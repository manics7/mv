package com.example.movie.service;

import java.util.HashMap;
import java.util.Map;

import com.example.movie.dto.quesboardDto;

import com.example.movie.mapper.AdminMapper;

	
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.MemberDto;
import com.example.movie.dto.MovieOfficialDto;
import com.example.movie.dto.ReviewMovieDto;
import com.example.movie.mapper.AdminMapper;
import com.example.movie.dto.QuestionDto;
import com.example.movie.dto.mvReviewDto;
import com.example.movie.dto.mypagePaymentDto;
import com.example.movie.dto.paymentDto;
import com.example.movie.dto.reservationDto;
import com.example.movie.mapper.MemberMapper;
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

	
	public ModelAndView memberUpdateFrm() {
		mv = new ModelAndView();
		
		MemberDto mem = (MemberDto)session.getAttribute("userInfo");
		
		String birth = mem.getM_birth();
		// 생일 문자열 뒤에 00:00:00을 없애기 위한 반복문
		for(int i = 10; i <= birth.length()-1; i++) {
		
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
			for(int i = 0; i <= apmvrList.size()-1; i++) {
				
				for(int j = i+1; j <= apmvrList.size()-1; j++) {
					
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

	public ModelAndView mvReviewSearch(String mvname) {
		
		MemberDto member = (MemberDto)session.getAttribute("userInfo");
		String id = member.getM_id();
		
		List<mvReviewDto> mvrList = mMapper.selectpmvReview(id);
			
		//검색처리
		for(int i = 0; i <= mvrList.size()-1; i++) {
			
			mvReviewDto amvrDto = mvrList.get(i);
			
			String movieCd = amvrDto.getMv_review_moviecd();
			
			String mvName = mMapper.selectMovieName(movieCd);
			
			mvReviewDto mvr = amvrDto;
			//옮기고 영화이름 추가
			mvr.setMvName(mvName);
			
			if(!mvname.equals(mvName)) {
				
				mvrList.remove(i);
				i--;
			}
		}
		mv.addObject("mvrList",mvrList);
		
		int maxNum = 1;
		int num =1;
		int listCnt =10;
		String View = "pmvReviewFrm";
		//페이징 처리.
		
		String pageHtml = getPaging1(num,listCnt,View,maxNum);
		mv.addObject("paging", pageHtml);
		mv.setViewName("pmvReviewFrm");
		
		return mv;
	}


	//마이페이지 예매/결제 내역 출력.
/*	public ModelAndView selectPurchase (Integer pageNum, int listCnt, String View) {

		int num = (pageNum == null)? 1 : pageNum;

		mv = new ModelAndView();
		
		//세션에서id가져옴
		MemberDto member = (MemberDto)session.getAttribute("userInfo");
		String id = member.getM_id();


		//마이페이지에 출력할 것이므로 ...가져온 id에맞는 예매한 예매리스트
		List<reservationDto> rsrvList = mDao.selectRsrvByid(id);
		List<Integer> rsrvnoList = new ArrayList<Integer>();
		
		//예매리스트에서 예매번호만 따로 리스트로 만듦
		for(int i = 0; i <= rsrvList.size()-1; i++) {

			reservationDto rsrvDto = rsrvList.get(i);
			int rsrvno = rsrvDto.getRsrv_no();

			rsrvnoList.add(rsrvno);
		}
		//결제내역 리스트
		List<paymentDto> apList = new ArrayList<paymentDto>();

	//결제내역 테이블에 mid가 없어서.. id로 가져온 예매번호들로 결제내역들가져옴
		for(int i = 0; i <= rsrvnoList.size()-1; i++) {

			int rsrvno = rsrvnoList.get(i);

			paymentDto pDto = mDao.selectPaymentByRsrvno(rsrvno);

			apList.add(pDto);

		}
		
		//결제 취소 tid내역 리스트
		List<String> acpList = new ArrayList<String>();

		for(int i = 0; i <= apList.size()-1; i++) {

			paymentDto pDto = apList.get(i);

			String tid = pDto.getTid();

			String ctid = mDao.selectPaymentCancel(tid);

			if(ctid != null) {
				acpList.add(ctid);
			}
		}

	
	if(View.equals("purchaseFrm")) {//결제내역 페이지 에서 호출했을경우
	
//id에맞는 예매번호, 그 예매번호에 맞는 결제리스트에서 취소된 결제내역은 리스트에서 삭제후 저장
		for(int i = 0; i <= apList.size()-1; i++) {

			paymentDto pDto = apList.get(i);
			
			String tid = pDto.getTid();

			for(int j = 0; j <= acpList.size()-1; j++) {

				String ctid = acpList.get(j);

				if(tid.equals(ctid)) {
					apList.remove(i);
					i--;
				}
			}
		}
	}
	else {//결제 취소페이지에서 호출했을 경우
		
		//가져온 결제내역 apList 복사
		List<paymentDto>ap1List = apList;
		
		//apList 초기화
		apList = new ArrayList<paymentDto>();
		
//결제내역 tid 가 취소내역tid에 있을경우(일치할경우) apList에 추가 후 저장
		for(int i = 0; i <= ap1List.size()-1; i++) {

			paymentDto pDto = ap1List.get(i);
			
			String tid = pDto.getTid();

			for(int j = 0; j <= acpList.size()-1; j++) {

				String ctid = acpList.get(j);

				if(tid.equals(ctid)) {
					apList.add(pDto);
				}
			}
		}
	}

		List<mypagePaymentDto> mpList = new ArrayList<mypagePaymentDto>();

		//극장이름,영화등을 함께 묶어서 출력하기위한 for문
		for(int i = 0; i <= apList.size()-1; i++) {

			paymentDto pDto = apList.get(i);

			mypagePaymentDto mpDto = new mypagePaymentDto();

			//예매번호
			String prsno = pDto.getRsrv_no();
			//예매번호로 에매테이블에서 스케줄번호찾기
			int schno = mDao.selectSchno(prsno);
			//스케줄번호로 스케줄테이블에서 극장코드 찾기
			int thcode = mDao.selectThcode(schno);
			//극장코드로 극장테이블에서 출력할 극장이름 찾기
			String thname = mDao.selectThname(thcode);
			//스케줄번호로 스케줄테이블에서 무비코드 찾기
			String mvcd = mDao.selectMoviecode(schno);
			//무비코드로 출력할 영화이름찾기 
			String mvname = mDao.selectMovieName(mvcd);

			mpDto.setMvname(mvname);
			mpDto.setThname(thname);
			mpDto.setAmount(pDto.getAmount());
			mpDto.setApproved_at(pDto.getApproved_at());
			mpDto.setRsrv_no(prsno);

			mpList.add(mpDto);
		}
		//전체 글 갯수
		int maxNum = mpList.size();
		//옮길 리스트
		List<mypagePaymentDto> nmpList = new ArrayList<mypagePaymentDto>();
		//가져온 리스트가 있을때만 페이징처리
		if(!mpList.isEmpty()) {
			
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
				mpList.remove(j);
			}


			for(int i = 0; i <= list; i++) {
				//nmpList로 옮기고 저장
				if(mpList.size() <= list) {

					list = mpList.size()-1;
			//size가 출력해야할list보다 작으면 그만큼만 출력하도록 함 안할시 오류뜸  
				}
				mypagePaymentDto mpd = mpList.get(i);

				nmpList.add(mpd);
			}
		}

		mv.addObject("qList",nmpList);

		//페이징 처리.
		String pageHtml = getPaging(num,listCnt,View,maxNum);
		mv.addObject("paging", pageHtml);

		//세션에 페이지번호 저장
		//글작성 화면, 글내용 상세보기 화면 등에서 다시 목록으로
		//돌아갈때 보고 있던 페이지가 나오도록 하기 위해.
		session.setAttribute("pageNum", num);

		//호출한곳에 맞는 view로 반환
		mv.setViewName(View);



		return mv;
	}*/



//	private ModelAndView mv;

	private int listCnt = 4;//페이지 당 게시글 개수

//	public ModelAndView getMemberList(Integer pageNum) {
	public List<MemberDto> getMemberList(Integer pageNum) {
		// TODO Auto-generated method stub
//		mv = new ModelAndView();
//		pageNum = null;
		//null 또는 페이지 번호가 pageNum으로 넘어옴.
//		int num = (pageNum == null) ? 1 : pageNum;
		//버튼을 눌러 이동한 직 후 null이 넘어옴.
System.out.println("pageNum"+pageNum);		
		//회원 목록 가져오기
		Map<String, Integer> mmap = 
				new HashMap<String, Integer>();
//		mmap.put("num", num);
		mmap.put("num", pageNum);
		mmap.put("lcnt", listCnt);
		//차후 view(jsp)에서 페이지 당 글 개수를 입력받아서
	    //설정하는 부분을 처리하여 10 대신 사용.
		//페이지 당 게시글 개수와 페이지넘버 넣는것.
		System.out.println("listCnt = "+listCnt);			
		List<MemberDto> mList = mMapper.getList(mmap);
System.out.println("mList.size = "+mList.size());		
System.out.println("BoardCnt = "+mMapper.getBoardCnt()); //전체 글 개수 가져오는 mapper
//페이징 처리
//String pageHtml = getPaging(pageNum);

		return mList;
		
	/*
		//페이징 처리.
		String pageHtml = getPaging(num);
		mv.addObject("paging", pageHtml);
		
		//세션에 페이지번호 저장 할 필요는 여기서 없을거같음.
		
		//jsp 파일 이름 지정
		mv.setViewName("mmanage");
		
		return mv;
*/
	}
	
	//paging
//	private String getPaging(int num) {
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

	public ModelAndView mboardSelect(String m_id) {
		//mMap.getmboardSelect(m_id);
		ModelAndView mv = new ModelAndView();
		
		
		List<quesboardDto> mbList = aMapper.getboardSelect(m_id);
		mv.addObject("mbLIst", mbList);
		
		System.out.println("mbList = "+mbList);
		return mv;
	}

	public String getsearchPaging(String m_id) {
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
				
				// member 정보를 세션에 저장
				session.setAttribute("userInfo", member);
				
				view = "redirect:/";
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
		MovieOfficialDto mvOfficialDto = aMapper.movieDetail(movie_cd);
		// 관람평 목록 가져오기
		List<ReviewMovieDto> reviewMovie = aMapper.reviewMovie(movie_cd);
		
		mv.addObject("mvOfficial", mvOfficialDto);
		mv.addObject("reviewMovie", reviewMovie);
		
		mv.setViewName("movieDetail");
		
		return mv;
	}
	
}
