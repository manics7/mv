package com.example.movie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.movie.service.AmazonS3Service;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor //  final이나 @NonNull인 필드 값만 파라미터로 받는 생성자를 만들어줌.
public class AmazonS3Controller {

	private final AmazonS3Service amazonS3Service;
	
	 // Amazon S3에 파일 업로드
	@PostMapping("/file")
	@ResponseBody
	public List<String> uploadFile(@RequestPart(value = "uploadFile") List<MultipartFile> multipartFiles) {
		//return ApiResponse.success(awsS3Service.uploadImage(multipartFile));
		List<String> fileNameList = amazonS3Service.uploadFile(multipartFiles);
		if(multipartFiles.size() == fileNameList.size()) {

		}

		return fileNameList;
	}

	//@ResponseStatus(HttpStatus.CREATED)
	@GetMapping("/fileTest")
	@ResponseBody
	public String test() throws Exception {
		
		amazonS3Service.test();
		return "Ok";
	}
	
	@GetMapping("/getFileURL")
	@ResponseBody
	public String getFileURL() throws Exception {
		
		String url = amazonS3Service.getUrlTest();
		return url;
	}
}
