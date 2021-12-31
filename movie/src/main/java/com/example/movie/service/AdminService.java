package com.example.movie.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.servlet.ModelAndView;

import com.example.movie.dto.BusinessDto;
import com.example.movie.dto.quesboardDto;
import com.example.movie.mapper.AdminMapper;
import com.example.movie.util.PagingUtil;

@Service
public class AdminService {

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



}
