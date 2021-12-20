package com.example.movie.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.example.movie.dto.BoardDto;
import com.example.movie.mapper.BoardMapper;
import com.example.movie.util.PagingUtil;

@Service
public class BoardService {
	@Autowired
	private BoardMapper bMapper;
	@Autowired
	private HttpSession session;
	
	private ModelAndView mv;
	
	private int listCnt = 10; //페이지당 게시글 개수
	
	//게시글 목록 가져오기
	public ModelAndView getRvBoardList(Integer pageNum) {
		mv = new ModelAndView();
		
		//null or 페이지 번호
		int num = (pageNum == null)? 1 : pageNum;
		
		//게시글 목록 가져오기
		Map<String, Integer> pmap = new HashMap<String, Integer>();
		pmap.put("num", num);
		pmap.put("lcnt", listCnt);
		
		List<BoardDto> bList = bMapper.getRvList(pmap);
		
		//화면에 전송
		mv.addObject("bList", bList);
		
		//페이징 처리
		String pageHtml = getPaging(num);
		mv.addObject("paging", pageHtml);
		
		session.setAttribute("pageNum", num);
		
		//jsp 파일 이름 지정
		mv.setViewName("reviewList");
		
		return mv;
	}
	
	//페이징 처리
	private String getPaging(int num) {
		String pageHtml = null;
		
		//전체 글 개수 구하기(DAO)
		int maxNum = bMapper.getrvBoardCnt();
		mv.addObject("maxNum", maxNum);
		//한 페이지에 보여질 페이지 번호 개수
		int pageCnt = 5;
		String listName = "rlist";
		
		PagingUtil paging = new PagingUtil(maxNum, num,
				listCnt, pageCnt, listName);
		
		pageHtml = paging.makePaging();
		
		return pageHtml;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
} //class end