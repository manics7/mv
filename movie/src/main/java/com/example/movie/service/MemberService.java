package com.example.movie.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.example.movie.dto.MemberDto;
import com.example.movie.dto.quesboardDto;
import com.example.movie.mapper.AdminMapper;
import com.example.movie.mapper.MemberMapper;
import com.example.movie.util.PagingUtil;


@Service
public class MemberService {
	@Autowired
	private MemberMapper mMap;
	
	@Autowired
	private AdminMapper aMap;
	
	
	
//	private ModelAndView mv;

	private int listCnt = 10;//페이지 당 게시글 개수

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
		List<MemberDto> mList = mMap.getList(mmap);
System.out.println("mList.size = "+mList.size());		
System.out.println("BoardCnt = "+mMap.getBoardCnt()); //전체 글 개수 가져오는 mapper
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
		int maxNum = mMap.getBoardCnt();
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

	public String deletemember(String m_id) {
		
		mMap.deleteMember(m_id);
		
		return null;
	}

	public ModelAndView memberSelect(String m_id) {
		
	ModelAndView mv = new ModelAndView();
	
	List<MemberDto> mselectList = mMap.selectMember(m_id);
		
	System.out.println("검색 결과 = "+mselectList);
	mv.addObject("mseList", mselectList);
		
		
		return mv;
	}

	public ModelAndView mboardSelect(String m_id) {
		//mMap.getmboardSelect(m_id);
		ModelAndView mv = new ModelAndView();
		
		
		List<quesboardDto> mbList = aMap.getboardSelect(m_id);
		mv.addObject("mbLIst", mbList);
		
		System.out.println("mbList = "+mbList);
		return mv;
	}
	
	


}
