package com.example.movie.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.example.movie.sample.BonusDto;
import com.example.movie.sample.BonusMapper;
import com.example.movie.sample.Emp;
import com.example.movie.sample.EmpMapper;
import com.example.movie.sample.EmpRepository;
import com.example.movie.sample.Test;
import com.example.movie.sample.Test1Repository;

import lombok.extern.java.Log;

@Service
@Log
public class EmpService {

	private ModelAndView mv;
	
	@Autowired
	private EmpMapper empMapper;
	
	@Autowired
	private BonusMapper bonusMapper;
	
	// asdfasdf
	
	@Autowired
	private EmpRepository empRepository;
	
	@Autowired
	private Test1Repository test1Repository;

	public ModelAndView getEmp() {
		mv = new ModelAndView();
		List<Emp> list = new ArrayList<Emp>();
		Optional<Emp> emp = empRepository.findById(7369);
		list.add(emp.get());
		mv.addObject("list", list);
		mv.setViewName("test");
		return mv;
	}
	
	public ModelAndView getEmpList() {
		// TODO Auto-generated method stub
		mv = new ModelAndView();
		List<Emp> list = new ArrayList<Emp>();
		try {
			 list = empMapper.empList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		mv.addObject("list", list);
		mv.setViewName("test");
		return mv;
	}
	
	public ModelAndView getBounusList() {
		mv = new ModelAndView();
		List<BonusDto> list = new ArrayList<BonusDto>();
		try {
			 list = bonusMapper.getList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		mv.addObject("list", list);
		mv.setViewName("test");
		return mv;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void insertMybatis() throws Exception {
		for (int i = 0; i < 10; i++) {
			BonusDto dto = new BonusDto();
			dto.setEname("aaaa");
			
				if(i == 5) {
					//dto.setEname(null);
					throw new Exception();
					
				}
				bonusMapper.insertBonus(dto);
				// TODO: handle exception
			
			log.info(i+"        ////////////////////////////////////////");
		}
	}
	
	public ModelAndView getEmpList2() {
		mv = new ModelAndView();
		List<Emp> list =  empRepository.findAll();
		mv.addObject("list", list);
		mv.setViewName("test");
		return mv;
	}
	
	@Transactional
	public void jpaInsertTest() throws Exception {
		
		for (int i = 0; i < 10; i++) {
			Test test = new Test();
			test.setNm("i");
			test1Repository.save(test);
			if(i == 5) {
				throw new Exception("트랜잭션 테스트");
			}
			
		}
	}
}
