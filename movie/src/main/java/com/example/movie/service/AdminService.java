package com.example.movie.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.BusinessDto;
import com.example.movie.dto.MemberDto;
import com.example.movie.dto.quesReplyDto;
import com.example.movie.dto.ques_replyDto;
import com.example.movie.dto.quesboardDto;
import com.example.movie.mapper.AdminMapper;


import com.example.movie.utill.PagingUtil;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;


import com.example.movie.dto.reportMvReviewDto;


@Service
public class AdminService {

	
	@Autowired 
	private AdminMapper aMapper;
	@Autowired
	private HttpSession session;
	
	private ModelAndView mv;
	
	private String getPaging(int num, int listCnt,String view,int maxNum) {
		String pageHtml = null;

		
		int pageCnt = 3;


		PagingUtil paging = new PagingUtil(maxNum, num, listCnt, 
				pageCnt, view);

		pageHtml = paging.makePaging();

		return pageHtml;
	}
	
	public ModelAndView reportedReview (Integer pageNum) {
		mv = new ModelAndView();

		int num = (pageNum == null)? 1 : pageNum;
		int listCnt = 6;
		
		int maxNum = aMapper.selectReportMvReviewCnt();
		
		String view = "reportFrm";
		
		//게시글 목록 가져오기
		Map<String, Integer> pmap = 
				new HashMap<String, Integer>();
		pmap.put("num", num);
		pmap.put("lcnt", listCnt);
		
		List<reportMvReviewDto> rpList = aMapper.selectReportMvReview(pmap);
		/* 글내용 가져옴
		List<reportMvReviewDto> arpList = new ArrayList<reportMvReviewDto>();
		
		
		  for(int i = 0; i <= rpList.size()-1; i++) {
			
			reportMvReviewDto rpDto = rpList.get(i);
			
			int mvrNum = rpDto.getMvrnum();
			
			String contents = aMapper.selectMvReviewByReviewNum(mvrNum);
			
			rpDto.setContents(contents);
			
			arpList.add(rpDto);
		}*/

		//화면에 전송.
		mv.addObject("rpList", rpList);

		String pageHtml = getPaging(num,listCnt,view,maxNum);
		mv.addObject("paging", pageHtml);
		
		//세션에 페이지번호 저장
		//글작성 화면, 글내용 상세보기 화면 등에서 다시 목록으로
		//돌아갈때 보고 있던 페이지가 나오도록 하기 위해.
		session.setAttribute("pageNum", num);
		
		//jsp 파일 이름 지정
		mv.setViewName(view);
		
		
		return mv;
	}
	@Transactional
	public String delAdminMvReview(int mvrNum,RedirectAttributes rttr) {
		
		String view = "redirect:reportFrm";
		
		String rptId = aMapper.selectIdFromMvReview(mvrNum);
		
		try {
			
			aMapper.updateRpMvReviewState(mvrNum);
			
			aMapper.delMvReview(mvrNum);
			
			aMapper.updateWarning(rptId);
			
			//aMapper.delMvReviewReport(mvrNum);
			
			
			rttr.addFlashAttribute("msg","삭제성공");
			
		} catch(Exception e) {
			rttr.addFlashAttribute("msg","삭제 실패");
		}
				
		return view;
	}

	public ModelAndView reportedReviewSort(Integer pageNum) {
		
		mv = new ModelAndView();
		
		int num = (pageNum == null)? 1 : pageNum;
		
		int listCnt = 6;
		
		Map<String, Integer> pmap = 
				new HashMap<String, Integer>();
		pmap.put("num", num);
		pmap.put("lcnt", listCnt);
		List<reportMvReviewDto> rpList = new ArrayList<reportMvReviewDto>();
		
		rpList = aMapper.selectReportMvReviewSort(pmap);

		String view = "sortByState";
		
		int maxNum = aMapper.selectReportMvReviewCnt();
		
		mv.addObject("rpList", rpList);
		String pageHtml = getPaging(num,listCnt,view,maxNum);
		mv.addObject("paging", pageHtml);
		
		//세션에 페이지번호 저장
		//글작성 화면, 글내용 상세보기 화면 등에서 다시 목록으로
		//돌아갈때 보고 있던 페이지가 나오도록 하기 위해.
		session.setAttribute("pageNum", num);
		
		//jsp 파일 이름 지정
		mv.setViewName("reportFrm");
		
		Integer sort = 0;
		
		session.setAttribute("sort", sort);
		
		return mv;
	}

	@Autowired
	public AdminMapper aMap;
	public int listCnt = 4;


	public List<quesboardDto> getQboardList(Integer pageNum) {


		pageNum = (pageNum == null) ? 1 : pageNum;
		System.out.println("pageNum = "+pageNum);

		Map<String, Integer> qmap = new HashMap<String, Integer>();

		qmap.put("num", pageNum);

		qmap.put("lcnt", listCnt );
		System.out.println("lcnt = "+listCnt);

		List<quesboardDto> qList = aMap.getQuesList(qmap);

		System.out.println("qList = "+qList);

		return qList;
	}

	public String getpaging(int num) {
		String pageHtml = null;

		int maxNum = aMap.getBoardCnt();

		int pageCnt = 10;

		String listName = "quesboard";

		PagingUtil paging = new PagingUtil(maxNum, num, listCnt, 
				pageCnt, listName);
		//PagingUtil paging = new PagingUtil(maxNum, pageCnt, maxNum, pageCnt, listName);

		pageHtml = paging.makePaging();

		return pageHtml;
	}

	public List<quesboardDto> getboardRead(int ques_no) {

		List<quesboardDto> readqlist = aMapper.getboardRead(ques_no);
		
		return readqlist;
	}

	public List<BusinessDto> getbulist(Integer pageNum) {

		HashMap<String, Integer> busmap = new HashMap<String, Integer>();
		busmap.put("pageNum", pageNum);
		busmap.put("lcnt", listCnt);

		List<BusinessDto> buslist = aMapper.getbuslist(busmap);




		return buslist;
	}

	public String busgetpaging(Integer pageNum) {
		String pageHtml = null;

		int maxNum = aMap.getBusCnt();

		int pageCnt = 10;

		String listName = "mmanageBu";

		PagingUtil paging = new PagingUtil(maxNum, pageNum, listCnt, 
				pageCnt, listName);
		//PagingUtil paging = new PagingUtil(maxNum, pageCnt, maxNum, pageCnt, listName);

		pageHtml = paging.makePaging();

		return pageHtml;

	}

	public quesReplyDto selectQuesReply(int ques_no) {

		quesReplyDto qrDto = aMap.selectQuesReply(ques_no);

		return qrDto;
	}

	public String ques_replyInsert(Integer ques_no, RedirectAttributes rttr) {

		String view = null;
		String msg = null;

		ques_replyDto qrdto = new ques_replyDto();
		//해당 게시글 번호 자동 저장 
		qrdto.setQues_reply_no(ques_no);
		//

		return view;
	}

	public ModelAndView quesboard_reply_insert(ques_replyDto qrdto) {
		ModelAndView mv = new ModelAndView();
		try {
			aMap.insert_ques_reply(qrdto);
			mv.setViewName("redirect:quesboard?pageNum=1");	
			System.out.println("mv = "+mv);
		} catch (Exception e) {
			mv.setViewName("redirect:quesboard?pageNum=1");
		}
		return mv;
	}

//	public ModelAndView selectpopupmem(String m_id) {
//		MemberDto mdto = new MemberDto();
//		ModelAndView mv = new ModelAndView();
//		
//		mdto = aMap.getMemberSelect(m_id);
//		
//		mv.addObject("mem_popup", mdto);
//		
//		mv.setViewName("mmanage");
//		
//		return mv;
//}
}