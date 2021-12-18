package com.example.movie.sample;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.movie.common.AwsS3;

@Controller
public class TestController {

	private HttpStatus httpStatus;
	
	@Autowired
	AwsS3 s3;
	
	@Autowired
	private EmpService empService;

	@RequestMapping("/test") public ModelAndView test() throws Exception{
		ModelAndView mv = new ModelAndView("test"); 

		//mv = empService.getEmpList();
		//mv = empService.getBounusList();
		//mv = empService.getEmpList2();
		mv = empService.getEmp();
		return mv; 
	}

	@RequestMapping("/test2") public ModelAndView test2() throws Exception{
		ModelAndView mv = new ModelAndView("test"); 

		//mv = empService.getEmpList();
		mv = empService.getBounusList();
		//mv = empService.getEmpList2();

		return mv; 
	}

	@RequestMapping("/test3") public ModelAndView transactiontest() throws Exception{
		ModelAndView mv = new ModelAndView("test"); 

		//empService.jpaInsertTest();
		empService.insertMybatis();

		return mv; 
	}

	//@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping("/file")
	public void upload(@RequestPart List<MultipartFile> files) throws Exception {
		List<String> list = new ArrayList<>();
		for (MultipartFile file : files) {
			Resource rs = file.getResource();
			String originalfileName = file.getOriginalFilename();
			
			File dest = new File(originalfileName);
			s3.upload(dest, originalfileName);
		}
	}
	
	//@RequestMapping("/file")
	public void fileTest() {
		
		//s3.upload(null, null)
	}

}
