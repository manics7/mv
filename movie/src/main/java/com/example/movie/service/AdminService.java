package com.example.movie.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.BusinessDto;
import com.example.movie.dto.MemberDto;
import com.example.movie.dto.quesReplyDto;
import com.example.movie.dto.ques_replyDto;
import com.example.movie.dto.quesboardDto;
import com.example.movie.mapper.AdminMapper;
import com.example.movie.util.PagingUtil;

@Service
public class AdminService {

	@Autowired
	public AdminMapper aMap;
	public int listCnt = 4;
	
	public ModelAndView mv;


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

		List<quesboardDto> readqlist = aMap.getboardRead(ques_no);

		return readqlist;
	}

	public List<BusinessDto> getbulist(Integer pageNum) {

		HashMap<String, Integer> busmap = new HashMap<String, Integer>();
		busmap.put("pageNum", pageNum);
		busmap.put("lcnt", listCnt);

		List<BusinessDto> buslist = aMap.getbuslist(busmap);




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

	public ModelAndView selectpopupmem(String m_id) {
		MemberDto mdto = new MemberDto();
		mv = new ModelAndView();
		
		mdto = aMap.getMemberSelect(m_id);
		
		mv.addObject("mem_popup", mdto);
		
		mv.setViewName("mmanage");
		
		return mv;
	}


}
