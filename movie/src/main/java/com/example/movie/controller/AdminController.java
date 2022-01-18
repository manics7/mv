
package com.example.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.BusinessDto;
import com.example.movie.dto.quesReplyDto;
import com.example.movie.dto.quesboardDto;
import com.example.movie.mapper.AdminMapper;
import com.example.movie.service.AdminService;
import com.example.movie.service.MemberService;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService aServ;
	
	private ModelAndView mv;
	@Autowired
	private MemberService mServ;
	
	//신고페이지(영화리뷰)
	@GetMapping("mvrreportFrm")
	public ModelAndView mvrreportFrm(Integer pageNum) {
		
		mv = aServ.reportedmvReview(pageNum);
		
		return mv;
	}
	//신고페이지(영화관리뷰)
		@GetMapping("boardreportFrm")
		public ModelAndView boardreportFrm(Integer pageNum) {
			
			mv = aServ.reportedReview(pageNum);
			
			return mv;
		}
	
	//영화리뷰 삭제처리 (신고)
	@GetMapping("delAdminMvReview")
	public String delAdminMvReview(int movie_review,RedirectAttributes rttr) {
		
		/*int mvrnum = Integer.parseInt(mv_review);*/
		
		String view = aServ.delAdminMvReview(movie_review,rttr);
		
		return view;
	}
	//영화관리뷰게시글 삭제(신고)
	@GetMapping("delAdminReview")
	public String delAdminReview (int review_num,RedirectAttributes rttr) {
		
		String view = aServ.delAdminReview(review_num,rttr);
		
		return view;
	}
	
	//문의사항 목록 출력 
	@GetMapping("/quesboard")
	public ModelAndView quesboard(Integer pageNum) {
		ModelAndView mv = new ModelAndView();
		pageNum = (pageNum == null) ? 1 : pageNum;

		List<quesboardDto> qList = aServ.getQboardList(pageNum);

		mv.addObject("qlist", qList);

		//페이징 처리.
		String pageHtml = aServ.getpagingQuesBoard(pageNum);

		mv.addObject("paging", pageHtml);


		mv.setViewName("quesboard");

		System.out.println("qlist = "+qList);
		return mv;
	}

	//문의사항 상세보기 
	@GetMapping("/requeboard_read")
	public ModelAndView requeboardRead(int ques_no,Integer view) {
		
		int num = (view == null)? 0 : view;
		
		ModelAndView mv = new ModelAndView();
		System.out.println("ques_no = "+ques_no);
		
		//문의번호로 가져옴
		quesboardDto readqlist = aServ.getboardRead(ques_no);
		
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
		mv.setViewName("quesboard_replywrite"); 		
		return mv;
	}
	
	//문의사항 답변 달기 
	@PostMapping("/quesboard_reply_insert")
	public String quesboard_reqly_insert(quesReplyDto qrdto, RedirectAttributes rttr) {
		System.out.println("qrdto"+qrdto);
		
		String view = aServ.quesboard_reply_insert(qrdto, rttr);
		return view;
	}
		
	//관리자 입장에서 등록 된 영화 상세보기
	@GetMapping("admin_movie_read")
	public ModelAndView movieDetail(int mv_seq) {
		
		mv = aServ.admin_movie_read(mv_seq);
		
		return mv;

	}
		
	//사업자회원 정보 출력	
	@GetMapping("getBulist")
	public ModelAndView getbulist(Integer pageNum) {
		System.out.println("Business pageNum = "+pageNum);
		
		ModelAndView mv = new ModelAndView();
		//사업자 정보 인출
		mv = aServ.getbulist(pageNum);

	
		return mv;
	}

	//영화등록 페이지
	@GetMapping("adminMovieList")
	public ModelAndView adminMovieList(Integer pageNum) {
		
		mv = aServ.adminMovieList(pageNum);
		
		return mv;
	}
	//영화등록 등록처리
	@PostMapping("movieOfficialInsert")
	public String movieOfficialInsert(MultipartHttpServletRequest multi,RedirectAttributes rttr) {
		
		String view = aServ.movieOfficialInsert(multi,rttr);
		
		return view;
	}
	
	//관리자 가 자신의 답변 확인.
	@GetMapping("adminReadQuesRe")
	public ModelAndView adminReadQuesRe(int ques_no) {
		mv = new ModelAndView();
		mv = mServ.memReadQuesRe(ques_no, 1);
	return mv;
}
	// 관리자 페이지로 이동
	@GetMapping("adminPage")
	public String adminPage() {
		
		return "adminPage";
	}
	@ResponseBody
	@GetMapping("alDeleteMem")
	public ModelAndView alDeleteMem(String m_id) {
		
		mv = new ModelAndView();
		mv = aServ.alDeleteMem(m_id);
		mv.setViewName("mmanage");
		
		return mv;
	}
	
}