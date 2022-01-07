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
import com.example.movie.dto.ReplyDto;
import com.example.movie.dto.TheaterDto;
import com.example.movie.mapper.BoardMapper;
import com.example.movie.utill.PagingUtil2;

import lombok.extern.java.Log;

@Service
@Log
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
		
		String listName = "rlist?";
		
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
		String pageHtml = getPaging(num, listName);
		mv.addObject("paging", pageHtml);
		
		session.setAttribute("pageNum", num);
		
		//jsp 파일 이름 지정
		mv.setViewName("reviewList");
		
		return mv;
	}
	
	//검색한 게시글 불러오기(원래)
	public ModelAndView rvSearchList(BoardDto bDto, Integer pageNum) {
		log.info("rvSearchList()");
		mv = new ModelAndView();
		
		String listName = "slist?type="+ bDto.getType() +"&keyword="+ bDto.getKeyword() +"&";
		
		int num = (pageNum == null) ? 1: pageNum;
		
		bDto.setNum(num);
		bDto.setLcnt(listCnt);

		List<BoardDto> sList = bMapper.selectSearchList(bDto);

		mv.addObject("sList", sList);
		
		//페이징 처리
		String pageHtml = getPaging2(bDto, listName);
		mv.addObject("paging", pageHtml);
		
		session.setAttribute("pageNum", num);

		mv.setViewName("searchRvList");

		return mv;
	}
	
	//페이징 처리
	private String getPaging(int num, String listName) {
		String pageHtml = null;

		//전체 글 개수 구하기(DAO)
		int maxNum = bMapper.getrvBoardCnt();
		mv.addObject("maxNum", maxNum);
		//한 페이지에 보여질 페이지 번호 개수
		int pageCnt = 5;
	
		PagingUtil2 paging = new PagingUtil2(maxNum, num,
				listCnt, pageCnt, listName);

		pageHtml = paging.makePaging();

		return pageHtml;
	}
	
	//검색 페이징 처리
	private String getPaging2(BoardDto bDto, String listName) {
		String pageHtml = null;

		//전체 글 개수 구하기(DAO)
		int maxNum = bMapper.getrvSearchCnt(bDto);
		mv.addObject("maxNum", maxNum);
		int num = bDto.getNum();
		//한 페이지에 보여질 페이지 번호 개수
		int pageCnt = 5;

		PagingUtil2 paging = new PagingUtil2(maxNum, num,
				listCnt, pageCnt, listName);

		pageHtml = paging.makePaging();

		return pageHtml;
	}
	
	//영화관 이름 불러오기
	public ModelAndView rvTheaterList() {
		mv = new ModelAndView();
		
		List<TheaterDto> thList = bMapper.getTHList();
		
		mv.addObject("thList", thList);
		
		System.out.println("thList = " + thList);
		
		mv.setViewName("writeRvFrm");
		
		return mv;
	}
	
	//게시글 작성 처리
	@Transactional
	public String rvBoardInsert(MultipartHttpServletRequest multi,
								RedirectAttributes rttr) {
		String view = null;
		String msg = null;
		
		String id = multi.getParameter("mid");
		int thcode = Integer.parseInt(multi.getParameter("thcode"));
		String title = multi.getParameter("rtitle");
		String content = multi.getParameter("rcontent");
		content = content.trim();
		
		BoardDto bDto = new BoardDto();
		bDto.setMid(id);
		bDto.setThcode(thcode);
		bDto.setRtitle(title);
		bDto.setRcontent(content);
		
		try {
			bMapper.rvBoardInsert(bDto);
			view = "redirect:rlist";
			msg = "글 작성 완료";
		} catch (Exception e) {
			e.printStackTrace();
			view = "redirect:writeRvFrm";
			msg = "글 작성 실패";
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return view;
	}
	
	//게시글 본문으로 이동
	public ModelAndView getRvContent(Integer rnum) {
		mv = new ModelAndView();
		
		//조회수 1 증가
		bMapper.viewUpdate(rnum);
		
		//글 내용 가져오기
		BoardDto bDto = bMapper.getRvContent(rnum);
		//파일 목록 가져오기
		//댓글 목록 가져오기
		List<ReplyDto> reList = bMapper.getReList(rnum);
		
		System.out.println("reList= " + reList);
		
		mv.addObject("bDto", bDto);
		mv.addObject("reList", reList);
		
		mv.setViewName("reviewContent");
		
		return mv;
	}
	
	//게시글 수정 페이지 이동
	public ModelAndView updateRvFrm(int rnum,
			RedirectAttributes rttr) {
		mv = new ModelAndView();
		
		List<TheaterDto> thList = bMapper.getTHList();
		
		mv.addObject("thList", thList);	
		
		BoardDto bDto = bMapper.getRvContent(rnum);
		
		//멤버 세션 처리 나중에
		
		mv.addObject("bDto", bDto);
		mv.setViewName("updateRvFrm");
		
		return mv;
	}
	
	//게시글 수정 처리
	@Transactional
	public String reviewUpdate(MultipartHttpServletRequest multi,
			RedirectAttributes rttr) {
		String view = null;
		
		String id = multi.getParameter("mid");
		String rnum = multi.getParameter("rnum");
		String thcode = multi.getParameter("thcode");
		String title = multi.getParameter("rtitle");
		String content = multi.getParameter("rcontent");
		content = content.trim();
		
		BoardDto bDto = new BoardDto();

		bDto.setMid(id);
		bDto.setRnum(Integer.parseInt(rnum));
		bDto.setThcode(Integer.parseInt(thcode));
		bDto.setRtitle(title);
		bDto.setRcontent(content);
		
		try {
			bMapper.RvUpdate(bDto);
			rttr.addFlashAttribute("msg", "수정 성공");
		} catch (Exception e) {
			//e.printStackTrace();
			rttr.addFlashAttribute("msg", "수정 실패");
		}
		
		view = "redirect:content?rnum=" + rnum;
		
		return view;
	}

	//게시글 삭제 처리
	@Transactional
	public String reviewDelete(int rnum, RedirectAttributes rttr) {
		String view = null;
		
		try {
			bMapper.RvboardDelete(rnum);
			view = "redirect:rlist";
			rttr.addFlashAttribute("msg", "삭제 성공");
		} catch (Exception e) {
			view = "redirect:content?rnum=" + rnum;
			rttr.addFlashAttribute("msg", "삭제 실패");
		}
		
		return view;
	}
	
	//댓글 작성하기
	@Transactional
	public Map<String, List<ReplyDto>> replyInsert(ReplyDto reDto) {
		Map<String, List<ReplyDto>> remap = null;
		
		try {
			bMapper.replyInsert(reDto);
			List<ReplyDto> reList = bMapper.getReList(reDto.getRnum());
			remap = new HashMap<String, List<ReplyDto>>();
			remap.put("reList", reList);
		} catch (Exception e) {
			remap = null;
		}
		
		return remap;
	}
	
	
	
	
} //class end