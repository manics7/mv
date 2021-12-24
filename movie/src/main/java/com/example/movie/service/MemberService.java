package com.example.movie.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.MemberDto;
import com.example.movie.dto.QuestionDto;
import com.example.movie.dto.mvReviewDto;
import com.example.movie.dto.mypageMvReviewDto;
import com.example.movie.dto.mypagePaymentDto;
import com.example.movie.dto.paymentCancelDto;
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

			for(int i = 0; i <= forpage; i++) {
		//pagenum에 해당하지않는 출력되지 않아야 할 앞의 게시물 리스트에서 삭제
				
				if(page <= 0) {
		//1페이지일 경우 page가 0이 되는바람에 forpage가 음수로 되어 반복문난리남.
					break;
				}
				
				int j=0;
		//삭제한걸 자꾸 재정렬해서; 0번째 값 삭제로 고정
				aqList.remove(j);
			}
			
			
			for(int i = 0; i <= list; i++) {
		//qList로 옮기고 저장
				if(aqList.size() < list) {
					
					list = aqList.size()-1;
		//size가 출력해야할list보다 작으면 그만큼만 출력하도록 함 안할시 오류뜸  
				}
				QuestionDto que = aqList.get(i);
				
				qList.add(que);
			}
		}
		
		mv.addObject("qList",qList);
		
		//페이징 처리.
		String pageHtml = getPaging(num,listCnt,View);
		mv.addObject("paging", pageHtml);
		
		//세션에 페이지번호 저장
		//글작성 화면, 글내용 상세보기 화면 등에서 다시 목록으로
		//돌아갈때 보고 있던 페이지가 나오도록 하기 위해.
		session.setAttribute("pageNum", num);
		
		//호출한곳에 맞는 view로 반환
		mv.setViewName(View);
		
		
		
		return mv;
	}
	private String getPaging(int num, int listCnt,String view) {
		String pageHtml = null;
		
		//전체 글 개수 구하기(DAO)
		int maxNum = 0;
		
		MemberDto member = (MemberDto)session.getAttribute("mb");
		
		String id = member.getM_id();	
		
		if(view.equals("mypage")) {
			
		maxNum = mDao.selectQuestionCnt(id);
		//한 페이지에 보여질 페이지 번호 개수
		}
		if(view.equals("questionFrm")) {
			
		maxNum = mDao.selectQuestionCnt(id);
		}
		
		if(view.equals("pmvReviewFrm")) {
			
		maxNum = mDao.selectpmvReviewCnt(id);
		}
		
		int pageCnt = 2;
		
		//돌려써먹기위해 맞는 view로 listName 수정
		String listName = view;
		
		PagingUtil paging = new PagingUtil(maxNum, num, listCnt, 
				pageCnt, listName);
		
		pageHtml = paging.makePaging();
		
		return pageHtml;
	}
	
	public ModelAndView pmvReviewFrm(Integer pageNum,int listCnt,String View) {
		
		mv = new ModelAndView();
		
		int num = (pageNum == null)? 1 : pageNum;
		
		MemberDto member = (MemberDto)session.getAttribute("mb");
		
		String id = member.getM_id();
		
		List<mvReviewDto> apmvrList = mDao.selectpmvReview(id);
		
		List<mypageMvReviewDto> pmvrList = new ArrayList<mypageMvReviewDto>();
		
		
		//가져온 글리스트가 있을때만 페이징처리
		if(!apmvrList.isEmpty()) {
			
			int page = (num -1) * listCnt;
			
			int forpage = page-1;
			
			int list = listCnt -1 ;

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
				if(apmvrList.size() < list) {
					
					list = apmvrList.size()-1;
		//size가 출력해야할list보다 적으면 그만큼만 출력하도록 함 안할시 오류뜸  
				}
				mvReviewDto amvr = apmvrList.get(i);
				
				String movieCd = amvr.getMv_review_of_mv_num();
				
				String mvname = mDao.selectMovieName(movieCd);
		//영화이름 같이출력하려고 따로 dto만든후 추가해서 보냄. 꼭 이렇게 해야하는가
				mypageMvReviewDto mvr = new mypageMvReviewDto();
				
				mvr.setMv_name(mvname);
				mvr.setMv_review_comment(amvr.getMv_review_comment());
				mvr.setMv_review_date(amvr.getMv_review_date());
				mvr.setMv_review_id(amvr.getMv_review_id());
				mvr.setMv_review_num(amvr.getMv_review_num());
				mvr.setMv_review_of_mv_num(amvr.getMv_review_of_mv_num());
				mvr.setMv_review_score(amvr.getMv_review_score());
				
				pmvrList.add(mvr);
				
			}
			
		}
		
		mv.addObject("mvrList",pmvrList);
		
		//페이징 처리.
		String pageHtml = getPaging(num,listCnt,View);
		mv.addObject("paging", pageHtml);
		
		//세션에 페이지번호 저장
		//글작성 화면, 글내용 상세보기 화면 등에서 다시 목록으로
		//돌아갈때 보고 있던 페이지가 나오도록 하기 위해.
		session.setAttribute("pageNum", num);
		
		
		mv.setViewName(View);
		
		return mv;
	}
	
	//마이페이지 예매/결제 내역 출력
	public ModelAndView selectPurchase (Integer pageNum, int listCnt, String View) {
		
		mv = new ModelAndView();
		
		List<paymentDto> apList = mDao.selectPayment();
		
		List<paymentCancelDto> cpList = mDao.selectPaymentCancel();
		
		paymentDto pDto = new paymentDto();
		
		mypagePaymentDto mpDto = new mypagePaymentDto();
		
		List<mypagePaymentDto> mpList = new ArrayList<mypagePaymentDto>();
		
		//전체 결제리스트에서 취소된 결제내역 없앰
		for(int i = 0; i <= apList.size()-1; i++) {
			
			pDto = apList.get(i);
			String atid = pDto.getTid();
			
			for(int j = 0; j <= apList.size()-1; j++) {
				
				paymentCancelDto cpDto = cpList.get(j);
				String ctid = cpDto.getTid();
				
				if(atid.equals(ctid)) {
					apList.remove(i);
					i--;
				}
			}
		}
		//극장이름,영화등을 함께 출력하기위한 for문
		for(int i = 0; i <= apList.size()-1; i++) {
			
			pDto = apList.get(i);
			
			//예매번호
			String prsno = pDto.getRsrv_no();
			//예매번호에서 스케줄번호찾기
			String schno = mDao.selectSchno(prsno);
			//스케줄번호로 극장코드 찾기
			String thcode = mDao.selectThcode(schno);
			//극장코드로 출력할 극장이름 찾기
			String thname = mDao.selectThname(thcode);
			//스케줄번호로 무비코드 찾기
			String mvcd = mDao.selectMoviecode(schno);
			//무비코드로 출력할 영화이름찾기 
			String mvname = mDao.selectMovieName(mvcd);
			
			mpDto.setMvname(mvname);
			mpDto.setThname(thname);
			mpDto.setAmount(pDto.getAmount());
			mpDto.setApproved_at(pDto.getApproved_at());
			mpDto.setPayment_method_type(pDto.getPayment_method_type());
			mpDto.setQuantity(pDto.getQuantity());
			mpDto.setRsrv_no(pDto.getRsrv_no());
			
			mpList.add(mpDto);
		}
		
		//이제 mpList로 페이징처리를 해보자! ^^
		
		return mv;
	}
	

	
}//class
