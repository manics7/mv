package com.example.movie.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.movie.common.AwsS3;
import com.example.movie.dto.BusinessDto;
import com.example.movie.dto.MovieDto;
import com.example.movie.dto.MovieOfficialDto;
import com.example.movie.dto.mvReviewDto;
import com.example.movie.dto.quesReplyDto;
import com.example.movie.dto.quesboardDto;
import com.example.movie.mapper.AdminMapper;
import com.example.movie.utill.PagingUtil;

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
	@Autowired
	private AwsS3 AwsS3;
	
	@Value("${aws.s3.bucket}")
	private String bucket;

	@Value("${aws.s3.bucketURL}")
	private String bucketURL;

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


	public int listCnt = 5;

	public List<quesboardDto> getQboardList(Integer pageNum) {

		//ModelAndView mv = new ModelAndView();
		pageNum = (pageNum == null) ? 1 : pageNum;
		System.out.println("pageNum = "+pageNum);

		Map<String, Integer> qmap = new HashMap<String, Integer>();

		qmap.put("num", pageNum);

		qmap.put("lcnt", listCnt );
		System.out.println("lcnt = "+listCnt);

		List<quesboardDto> qList = aMapper.getQuesList(qmap);

		//mv.setViewName(null);
		System.out.println("qList = "+qList);

		return qList;
	}

	public String getpaging(int num) {
		String pageHtml = null;

		int maxNum = aMapper.getquesBoardCnt();

		int pageCnt = 10;

		String listName = "quesboard";

		PagingUtil paging = new PagingUtil(maxNum, num, listCnt, 
				pageCnt, listName);
		//PagingUtil paging = new PagingUtil(maxNum, pageCnt, maxNum, pageCnt, listName);

		pageHtml = paging.makePaging();

		return pageHtml;
	}

	public List<quesboardDto> getboardRead(int ques_no) {

		List<quesboardDto> readqlist = aMapper.getquesboardRead(ques_no);

		return readqlist;
	}


		// 현재상영작 목록 페이지 이동(현재상영작 불러오기)
		public ModelAndView getMovieList() {
			mv = new ModelAndView();
			List<MovieOfficialDto> movieList = aMapper.getMovieList();

			mv.addObject("movieList", movieList);
			mv.setViewName("currentMovieList");

			return mv;
		}

		//문의답변 전송
		public quesReplyDto selectQuesReply(int ques_no) {

			quesReplyDto qrDto = aMapper.selectQuesReply(ques_no);

			return qrDto;
		}
		//관리자 영화등록 페이지 리스트
		public ModelAndView adminMovieList(Integer pageNum) {

			int num = (pageNum == null)? 1 : pageNum;
			int listCnt = 4;
			int maxNum;
			
			mv = new ModelAndView();

			List<MovieDto> movieList = aMapper.selectMovieRequest();
			List<MovieDto> movieList1 = new ArrayList<MovieDto>();

			List<MovieOfficialDto> movieOfList = aMapper.selectMvOfficial();

			for(int i = 0; i <= movieList.size()-1; i++) {

				MovieDto mvDto1 = movieList.get(i);

				//중복요청 출력할 리스트에서 제거
				for(int j = i+1; j <= movieList.size()-1; j++) {

					MovieDto mvDto2 = movieList.get(j);

					String mvc1 = mvDto1.getMovie_cd();
					String mvc2 = mvDto2.getMovie_cd();

					if(mvc1.equals(mvc2)) {
						movieList.remove(j);
						j--;
					}
				}

				for(int k = 0; k <= movieOfList.size()-1; k++) {

					MovieOfficialDto mOfDto = movieOfList.get(k);

					String mvc1 = mvDto1.getMovie_cd();
					String mvc2 = mOfDto.getMovie_cd();

					if(mvc1.equals(mvc2)) {
						//이미 등록된 영화 상태값 1	
						mvDto1.setState(1);
						movieList.set(i, mvDto1);

						//이미 등록된 영화요청 DB에서 지움
						/*	aMapper.delMvRequest(mvc1);
					movieList.remove(i);*/
					}

				}
			}
			maxNum = movieList.size();

			//////페이징처리 필요
			if(!movieList.isEmpty()) {

				int page = (num -1) * listCnt;

				int forpage = page-1;

				int list = listCnt -1 ;

				Collections.reverse(movieList);

				for(int i = 0; i <= forpage; i++) {

					if(page <= 0) {
						break;
					}
					int j=0;
					movieList.remove(j);

				}
				for(int i = 0; i <= list; i++) {
					//pmvrList로 옮기고 저장
					if(movieList.size() <= list) {

						list = movieList.size()-1;
						//size가 출력해야할list보다 적으면 그만큼만 출력하도록 함 안할시 오류뜸  
					}
					MovieDto amvr = movieList.get(i);

					movieList1.add(amvr);

				}
			}


			String view = "adminMovieList";

			String pageHtml = getPaging(num,listCnt,view,maxNum);
			mv.addObject("paging", pageHtml);

			//세션에 페이지번호 저장
			//글작성 화면, 글내용 상세보기 화면 등에서 다시 목록으로
			//돌아갈때 보고 있던 페이지가 나오도록 하기 위해.
			session.setAttribute("pageNum", num);



			mv.addObject("mvList", movieList1);
			mv.setViewName("adminMovieList");

			return mv;



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

			int maxNum = aMapper.getBusCnt();

			int pageCnt = 10;

			String listName = "mmanageBu";

			PagingUtil paging = new PagingUtil(maxNum, pageNum, listCnt, 
					pageCnt, listName);
			//PagingUtil paging = new PagingUtil(maxNum, pageCnt, maxNum, pageCnt, listName);

			pageHtml = paging.makePaging();

			return pageHtml;
		}
//1대1문의 답변(사용안함)
//	public ModelAndView quesboard_replywrite(quesReplyDto qrdto) {
//		mv = new ModelAndView();
//		mv = aMapper.quesboard_replywrite(qrdto);
//		return mv;
//	}

		public ModelAndView admin_movie_read(int mv_seq) {
			
			mv = new ModelAndView();
			
			MovieDto mvDto = aMapper.selectMovieBySeq(mv_seq);
			
			mv.addObject("movie", mvDto);
			
			mv.setViewName("admin_movie_read");
			
			return mv;
		}
		
		//영화 등록
		@Transactional
		public String movieOfficialInsert(MultipartHttpServletRequest multi,RedirectAttributes rttr) {
			
			String view = null;
			String msg = "?";
			
			MovieOfficialDto mvofficialDto = new MovieOfficialDto();
			
			
			String movie_content = multi.getParameter("movie_content");
			String check = multi.getParameter("fileCheck");
			movie_content = movie_content.trim();
			
			String seq = multi.getParameter("movie_seq");
			
			int mv_seq = Integer.parseInt(seq);
			
			MovieDto mvDto = aMapper.selectMovieBySeq(mv_seq);
		
			mvofficialDto.setMovie_cd(mvDto.getMovie_cd());
			mvofficialDto.setMovie_nm(mvDto.getMovie_nm());
			mvofficialDto.setMovie_content(movie_content);
			mvofficialDto.setShow_tm(mvDto.getShow_tm());
			mvofficialDto.setOpen_dt(mvDto.getOpen_dt());
			mvofficialDto.setGenre_nm(mvDto.getGenre_nm());
			mvofficialDto.setDirectors(mvDto.getDirectors());
			mvofficialDto.setActors(mvDto.getActors());
			mvofficialDto.setShow_types(mvDto.getShow_types());
			mvofficialDto.setWatch_grade_nm(mvDto.getWatch_grade_nm());
		
				
			if(check.equals("1")){
				//로고 파일의 이름 가져오기
				List<MultipartFile> imgFiles = multi.getFiles("logoFiles");	
				List<String> imgName = AwsS3.uploadFile(imgFiles);
				
				//로고 사진 파일이 있으면 파일 이름을 dto에 담기
				for(int i = 0; i < imgName.size(); i++) {
					
					String LfileName = AwsS3.getFileURL(bucket, bucketURL+imgName.get(i));
					if(i==0) {
						mvofficialDto.setPoster(LfileName);
					}
					if(i==1) {
						mvofficialDto.setStillcut1(LfileName);
					}
					if(i==2) {
						mvofficialDto.setStillcut2(LfileName);
					}
					if(i==3) {
						mvofficialDto.setStillcut3(LfileName);
					}
					if(i==4) {
						mvofficialDto.setStillcut4(LfileName);
					}
					if(i==5) {
						mvofficialDto.setStillcut5(LfileName);
					}
					else {
						msg = "파일은 6개까지만 등록 가능합니다.";
						break;
					}
				}
				
			}
				aMapper.adminMovieInsert(mvofficialDto);
				msg = "등록 성공";
			
				
			
			
	
			view = "redirect:adminMovieList";
			rttr.addFlashAttribute("msg", msg);
			
			return view;
		}

		//1대1 문의 답변(사용함)(하는중)
	public String quesboard_reply_insert(quesReplyDto qrdto, RedirectAttributes rttr) {
		String view = null;	
		String msg = null;
		try {
			qrdto = aMapper.insertReplyWrite(qrdto);
			msg = "성공";
		} catch (Exception e) {
			view = "redirect:quesboard";
			msg = "실패";
		}
		//qrdto = aMapper.insertReplyWrite(qrdto);
		view = "redirect:quesboard";
		rttr.addFlashAttribute("msg",msg);
		return view;
		}
	}
