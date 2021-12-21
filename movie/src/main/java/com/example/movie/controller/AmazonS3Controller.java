package com.example.movie.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.movie.common.AmazonS3Util;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor //  final이나 @NonNull인 필드 값만 파라미터로 받는 생성자를 만들어줌.
public class AmazonS3Controller {

	private final AmazonS3Util amazonS3Service;

	// Amazon S3에 파일만 업로드
	@PostMapping("/fileUpload")
	@ResponseBody
	public Map<String,String> uploadFile(@RequestPart(value = "uploadFile") List<MultipartFile> multipartFiles) {
		
		Map<String,String> map = amazonS3Service.uploadFile(multipartFiles);		
		return map;
	}

	// 파일+다른값이 필요할때 
	@PostMapping("/fileUpload2")
	@ResponseBody
	public void uploadFile2(MultipartHttpServletRequest multipartFiles) {
		//return ApiResponse.success(awsS3Service.uploadImage(multipartFile));
		List<MultipartFile> files = multipartFiles.getFiles("uploadFile");
		Map<String,String> map = amazonS3Service.uploadFile(files);
		
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
