package com.example.movie.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.BoardDto;
import com.example.movie.dto.TheaterDto;
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
	
	//검색한 게시글 불러오기
	public ModelAndView rvSearchList(BoardDto bDto) {
		mv = new ModelAndView();
		
		List<BoardDto> sList = bMapper.selectSearchList(bDto);
		
		mv.addObject("sList", sList);
		
		mv.setViewName("reviewList");
		
		return mv;
	}
	
	//영화관 이름 불러오기
	public ModelAndView rvTheaterList() {
		mv = new ModelAndView();
		
		List<TheaterDto> thList = bMapper.getTHList();
		System.out.println("thList ="+thList);
		mv.addObject("thList", thList);
		
		mv.setViewName("writeRvFrm");
		
		
		return mv;
	}
	
	//게시글 작성 처리
	@Transactional
	public String rvBoardInsert(MultipartHttpServletRequest multi,
								RedirectAttributes rttr) {
		String view = null;
		String msg = null;
		
		String thname = multi.getParameter("thname");
		String id = multi.getParameter("mid");
		String title = multi.getParameter("rtitle");
		String content = multi.getParameter("rcontent");
		content = content.trim();
		
		BoardDto bDto = new BoardDto();
		bDto.setThname(thname);
		bDto.setMid(id);
		bDto.setRtitle(title);
		bDto.setRcontent(content);
		
		try {
			bMapper.rvBoardInsert(bDto);
			view = "redirect:rlist";
			msg = "글 작성 완료";
		} catch (Exception e) {
			view = "redirect:writeRvFrm";
			msg = "글 작성 실패";
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return view;
	}
	
	
	
	
	
	
	
	
	
	
	
	
} //class end