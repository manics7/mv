package com.example.movie.mapper;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.MemberDto;
import com.example.movie.dto.MovieDto;
import com.example.movie.dto.MovieOfficialDto;
import com.example.movie.dto.QuestionDto;
import com.example.movie.dto.ReviewMovieDto;
import com.example.movie.dto.SsdscheduleDto;
import com.example.movie.dto.TheaterDto;
import com.example.movie.dto.Theater_detailDto;
import com.example.movie.dto.ThmovieDto;
import com.example.movie.dto.mvReviewDto;
import com.example.movie.dto.paymentDto;
import com.example.movie.dto.reservationDto;


public interface MemberMapper  extends MybatisMapper {

	//id로 작성한 문의사항 리스트 
	public List<QuestionDto> selectQuestion(String id);
	//id로 작성한 영화리뷰 리스트
	public List<mvReviewDto> selectpmvReview(String id);
	//id에맞는 예매내역 리스트
	public List<reservationDto> selectRsrvByid(String id);

	// id로 작성한 문의사항 글 갯수
	public int selectQuestionCnt(String id);
	// id로 작성한 영화리뷰 갯수
	public int selectpmvReviewCnt(String id);

	//영화코드로 영화이름 검색;
	public String selectMovieName(String mvcd);
	//에매번호로 결제내역 검색
	public paymentDto selectPaymentByRsrvno(int rsrvno);
	//취소한 결제내역 검색
	public String selectPaymentCancel(String tid);

	//스케줄번호로 극장코드검색
	public int selectThcode (int schno);
	//스케줄번호로 schTime검색
	public reservationDto selectSchTime(int schno);
	//극장코드로 극장이름검색
	public String selectThname (int thcode);
	//스케줄번호로 영화코드 검색
	public String selectMoviecode (int schno);
	//영화리뷰 삭제
	public boolean delMvReview(int mvrnum);

	//회원정보 목록 가져오기
	List<MemberDto> getList(Map<String, Integer> mmap);
	//전체 글 개수 구하기
	int getBoardCnt();
	//회원 삭제 쿼리 
	public boolean deleteMember(String m_id);
	//회원 검색 쿼리
	List<MemberDto> selectMember(String m_id);

	//void getmboardSelect(String m_id);
	int getsearchBoardCnt(String m_id);

	// 이용자 회원가입 아이디 중복체크
	public int idCheck(String mid);

	// 이용자 회원 가입
	public void memberInsert(MemberDto member);

	// 이용자 로그인
	public String getPw(String m_id);

	// 이용자 검색(세션 저장용?)
	public MemberDto getMember(String m_id);

	// 이용자 관람평 작성
	public void insertReviewMovie(ReviewMovieDto reviewMovieDto);

	// 이용자 관람평 목록 다시 검색
	public List<ReviewMovieDto> selectReviewMovieList(String mv_review_moviecd);
	//영화관 상세정보 출력
	//public List<ThmovieDto> (Integer th_code);
	//영화관 상세정보에 들어갈 영화 스캐줄
	public List<SsdscheduleDto> selectmovieschedule();
	public List<ThmovieDto> inserttheaterinfo(Integer th_code2);
	//public List<Theater_detailDto> selectmovieschedule();

	// 현재상영작 불러오기
	public List<MovieOfficialDto> getMovieList(String mainMovieSearch);

	// 이용자 상영하는 영화관 목록
	public List<TheaterDto> getTheaterCode(String movie_cd);

	// 영화 상세 페이지 이동
	public MovieOfficialDto movieDetail(String movie_cd);

	// 관람평 목록 가져오기
	public List<ReviewMovieDto> reviewMovie(String movie_cd);
	
	// 메인 페이지 박스오피스 목록
	public List<MovieOfficialDto> getBoxOfficeList();

	public List<TheaterDto> seletThkey();
	//public void adminDeleteMember(String m_id);
	public TheaterDto getThinfoList(Integer th_code);
	
	//문의글작성
	public void questionWrite(QuestionDto quesDto);
	
	
}