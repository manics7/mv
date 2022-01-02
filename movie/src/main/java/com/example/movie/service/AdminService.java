package com.example.movie.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.example.movie.dto.MvOfficialDto;
import com.example.movie.mapper.AdminMapper;

@Service
public class AdminService {

	@Autowired
	private AdminMapper aMapper;
	
	@Autowired
	private HttpSession session;
	
	private ModelAndView mv;
	
	// 현재상영작 목록 페이지 이동(현재상영작 불러오기)
	public ModelAndView getMovieList() {
		mv = new ModelAndView();
		
		List<MvOfficialDto> movieList = aMapper.getMovieList();
		
		mv.addObject("movieList", movieList);
		mv.setViewName("currentMovieList");
		
		return mv;
	}

}
