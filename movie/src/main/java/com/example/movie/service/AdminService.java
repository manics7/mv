package com.example.movie.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.servlet.ModelAndView;

import com.example.movie.dto.quesboardDto;
import com.example.movie.mapper.AdminMapper;
import com.example.movie.util.PagingUtil;


import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public AdminMapper qMap;

	public int listCnt = 5;

	public List<quesboardDto> getQboardList(Integer pageNum) {

		//ModelAndView mv = new ModelAndView();
		pageNum = (pageNum == null) ? 1 : pageNum;
		System.out.println("pageNum = "+pageNum);

		Map<String, Integer> qmap = new HashMap<String, Integer>();

		qmap.put("num", pageNum);

		qmap.put("lcnt", listCnt );
		System.out.println("lcnt = "+listCnt);

		List<quesboardDto> qList = qMap.getQuesList(qmap);

		//mv.setViewName(null);
		System.out.println("qList = "+qList);

		return qList;
	}

	public String getpaging(int num) {
		String pageHtml = null;

		int maxNum = qMap.getBoardCnt();

		int pageCnt = 10;

		String listName = "quesboard";

		PagingUtil paging = new PagingUtil(maxNum, num, listCnt, 
				pageCnt, listName);
		//PagingUtil paging = new PagingUtil(maxNum, pageCnt, maxNum, pageCnt, listName);

		pageHtml = paging.makePaging();

		return pageHtml;
	}

	public List<quesboardDto> getboardRead(String ques_title) {
		
		List<quesboardDto> readqlist = qMap.getboardRead(ques_title);
		
		return readqlist;
	}


}
