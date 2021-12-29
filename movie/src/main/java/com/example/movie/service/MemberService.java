package com.example.movie.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.MemberDto;
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
	private MemberMapper mDao;
	@Autowired
	private HttpSession session;

	private ModelAndView mv;



	public String loginProc(MemberDto member, RedirectAttributes rttr) {

		String view = null;
		String msg = null;

		//로그인 한 아이디가 회원 아이디인지와 비밀번호를 가져오는 작업
		String encPwd = mDao.pwdSelect(member.getM_id());

		if(encPwd != null) {

			if(member.getM_pw().equals(encPwd)) {
				//로그인 성공. -> 세션에 로그인 정보를 저장.
				//회원 정보 가져와서 세션에 저장. member 재활용.
				member = mDao.memberSelect(member.getM_id());

				session.setAttribute("mb", member);

				//로그인 다음 페이지로 이동 : 게시글 목록 페이지.
				//게시판 목록 화면용 url - list
				view = "redirect:mypage";
			}
			else {
				//비밀번호 오류.
				view = "redirect:loginFrm";
				msg = "비밀번호 틀림";
			}
		}
		else {
			view = "redirect:loginFrm";
			msg = "아이디 없음";
		}

		rttr.addFlashAttribute("msg", msg);

		return view;
	}
	
	public ModelAndView memberUpdateFrm() {
		mv = new ModelAndView();
		
		MemberDto mem = (MemberDto)session.getAttribute("mb");
		
		mv.addObject("member",mem);
		
		mv.setViewName("memberUpdateFrm");
		
		return mv;
	}
	
	//문의글 리스트
	public ModelAndView selectQuestion(Integer pageNum, int listCnt, String View) {
		mv = new ModelAndView();

		int num = (pageNum == null)? 1 : pageNum;

		MemberDto member = (MemberDto)session.getAttribute("mb");

		String id = member.getM_id();

		/*	Map<String, Integer> pmap = 
				new HashMap<String, Integer>();
		pmap.put("num", num);
		pmap.put("lcnt", listCnt);
		 */
		List<QuestionDto> aqList = mDao.selectQuestion(id);

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
		int maxNum = mDao.selectQuestionCnt(id);
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
	}
	private String getPaging(int num, int listCnt,String view,int maxNum) {
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

		MemberDto member = (MemberDto)session.getAttribute("mb");
		String id = member.getM_id();
		
		//id로 리뷰들 가져옴
		List<mvReviewDto> apmvrList = mDao.selectpmvReview(id);

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
				//삭제한걸 자꾸 재정렬해서; 0번째 값 삭제로 고정
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

				String mvname = mDao.selectMovieName(movieCd);
			
				mvReviewDto mvr = amvr;
				//옮기고 영화이름 추가
				mvr.setMvName(mvname);
				//영화이름 추가한거 리스트에 추가
				pmvrList.add(mvr);

			}

		}

		mv.addObject("mvrList",pmvrList);
		//전체 리뷰 갯수
		int maxNum = mDao.selectpmvReviewCnt(id);
		//페이징 처리.
		String pageHtml = getPaging(num,listCnt,View,maxNum);
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
			mDao.delMvReview(mvrnum);
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
		
		MemberDto member = (MemberDto)session.getAttribute("mb");
		String id = member.getM_id();
		
		List<mvReviewDto> mvrList = mDao.selectpmvReview(id);
			
		//검색처리
		for(int i = 0; i <= mvrList.size()-1; i++) {
			
			mvReviewDto amvrDto = mvrList.get(i);
			
			String movieCd = amvrDto.getMv_review_moviecd();
			
			String mvName = mDao.selectMovieName(movieCd);
			
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
		
		String pageHtml = getPaging(num,listCnt,View,maxNum);
		mv.addObject("paging", pageHtml);
		mv.setViewName("pmvReviewFrm");
		
		return mv;
	}


	//마이페이지 예매/결제 내역 출력.
/*	public ModelAndView selectPurchase (Integer pageNum, int listCnt, String View) {

		int num = (pageNum == null)? 1 : pageNum;

		mv = new ModelAndView();
		
		//세션에서id가져옴
		MemberDto member = (MemberDto)session.getAttribute("mb");
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
}//classs




