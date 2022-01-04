package com.example.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.BusinessDto;
import com.example.movie.dto.quesReplyDto;
import com.example.movie.dto.ques_replyDto;
import com.example.movie.dto.quesboardDto;
import com.example.movie.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	public AdminService aServ;
	public ModelAndView mv;

	@GetMapping("/quesboard")
	public ModelAndView quesboard(Integer pageNum) {
		ModelAndView mv = new ModelAndView();

		List<quesboardDto> qList = aServ.getQboardList(pageNum);

		mv.addObject("qlist", qList);

		//페이징 처리.
		String pageHtml = aServ.getpaging(pageNum);

		mv.addObject("paging", pageHtml);


		mv.setViewName("quesboard");

		System.out.println("qlist = "+qList);
		return mv;
	}
	//	@GetMapping("/requeboard_read")
	//	public ModelAndView requeboardRead(String ques_title) {
	//		ModelAndView mv = new ModelAndView();
	//		System.out.println("ques_title = "+ques_title);
	//
	//		List<quesboardDto> readqlist = aServ.getboardRead(ques_title);
	//
	//		mv.addObject("qrlist", readqlist);
	//		mv.setViewName("requeboard_read");
	//		System.out.println("readqlist = "+readqlist);
	//		return mv;
	//	}



	//문의사항 상세보기 
	@GetMapping("/requeboard_read")
	public ModelAndView requeboardRead(int ques_no,Integer view) {

		int num = (view == null)? 0 : view;

		ModelAndView mv = new ModelAndView();
		System.out.println("ques_no = "+ques_no);

		//문의번호로 가져옴
		List<quesboardDto> readqlist = aServ.getboardRead(ques_no);

		//가져온거 어차피 1개니까 0번째로 꺼냄
		quesboardDto qDto = readqlist.get(0);

		//문의글 상태. 0 = 미답변,1=답변완료
		int state = qDto.getQues_state();

		//답변 완료일시
		if(state == 1) {

			//문의번호로 답변 찾아와서 저장
			quesReplyDto qrDto = aServ.selectQuesReply(ques_no);

			mv.addObject("reply",qrDto);
		}

		mv.addObject("qrlist", readqlist);

		//호출한곳(마이페이지,관리자페이지)에 따라 다른 viewname 설정 후 반환
		if(num == 0) {
			mv.setViewName("requeboard_read");
		}
		else {
			mv.setViewName("questionContents");
		}
		System.out.println("readqlist = "+readqlist);
		return mv;
	}


	//문의글 작성 폼으로 넘어가면서 문의번호 넘기기 
	@GetMapping("quesboard_rewrite")
	public ModelAndView quesboard_rewrite(Integer ques_no) {
		System.out.println("ques_no = "+ques_no);
		ModelAndView mv = new ModelAndView();
		int q = ques_no;
		mv.addObject("ques_no", q);
		mv.setViewName("quesboard_rewrite"); 		
		return mv;
	}

	@PostMapping("/quesboard_reply_insert")
	public ModelAndView quesboard_reqly_insert(ques_replyDto qrdto) {
		ModelAndView mv = new ModelAndView();
		mv = aServ.quesboard_reply_insert(qrdto);
		return mv;
	}


	//사업자회원 정보 출력 
	@GetMapping("getBulist")
	public ModelAndView getbulist(Integer pageNum) {
		System.out.println("Business pageNum = "+pageNum);
		ModelAndView mv = new ModelAndView();
		//사업자 정보 인출
		List<BusinessDto> busList = aServ.getbulist(pageNum);

		//페이징처리
		String pageHtml = aServ.busgetpaging(pageNum);

		//mv에 데이터 투입
		mv.addObject("paging", pageHtml);
		mv.addObject("busList", busList);
		mv.setViewName("mmanageBu");

		return mv;
	}
	@PostMapping(value="mempopup_ctl",
			produces = "application/json; charset=utf-8")
	public ModelAndView mempopup_ctl(String m_id) {
		ModelAndView mv = new ModelAndView();
		System.out.println("m_id = " + m_id);
		mv = aServ.selectpopupmem(m_id);
		return mv;
	}



}
