package com.example.movie.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.TheaterDto;
import com.example.movie.mapper.BusinessMapper;

@Service
public class BusinessService {
	
	@Autowired
	private AmazonS3Service amazonS3Service;
	
	@Autowired
	private BusinessMapper buMapper;
	
	@Value("${aws.s3.bucket}")
	private String bucket;

	@Value("${aws.s3.bucketURL}")
	private String bucketURL;
	
	
	@Transactional
	public String theaterInsert(MultipartHttpServletRequest multi, RedirectAttributes rttr) {
		String view = null;
		String msg = null;
		
		//데이터 추출
		String id = multi.getParameter("bid");//사업자 아이디
		String name = multi.getParameter("thname");//영화관 이름
		String place = multi.getParameter("thplace");//위치 정보(오시는 길)
		String tel = multi.getParameter("thtel");//영화관 번호
		String parking = multi.getParameter("thpark");//주차 안내
		String introduce = multi.getParameter("thintro");//영화관 소개
		String regeion = multi.getParameter("reNum");//지역 코드
		String check = multi.getParameter("fileCheck");//로고 이미지
		String check2 = multi.getParameter("fileCheck2");//영화관 사진
		
		//textarea는 실제 데이터 앞 뒤에 공백이 발생하는 경우가 있어서
		parking = parking.trim();
		introduce = introduce.trim();
		
		//텍스트 내용을 dto에 담기
		TheaterDto theater = new TheaterDto();
		theater.setB_id(id);//사업자 아이디
		theater.setTh_name(name);//영화관 이름
		theater.setTh_location(place);//위치 정보
		theater.setTh_tel(tel);//영화관 번호
		theater.setTh_parking(parking);//주차 안내
		theater.setTh_introduce(introduce);//영화관 소개
		theater.setTh_areacode(regeion);//지역 코드
		
		try {
			//업로드 파일이 있을 경우
			//check는 로고 이미지, check2는 영화관 사진
			
			if(check.equals("1") || check2.equals("1")) {
	
				List<MultipartFile> files = multi.getFiles("files");                                                    
				List<String> fn = amazonS3Service.uploadFile(files);
					
				for(int i = 0; i < fn.size(); i++) {
					String fileName = amazonS3Service.getFileURL(bucket, bucketURL+fn.get(i));
						
					theater.setTh_logo(fileName);
				}
			}
			
					
			//dto에 담은 내용을 mapper로 넘기기
			buMapper.theaterAdd(theater);                                                                          
			view = "redirect:theater";
			msg = "등록 성공";
			
		} catch (Exception e) {
			e.printStackTrace();
			view = "redirect:thaddFrm";
			msg = "등록 실패";
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return view;
	}
}


