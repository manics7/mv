package com.example.movie.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.movie.common.ErrorResponse;
import com.example.movie.common.KakaoPay;

@Controller
public class PaymentController {
	
	@Autowired
	KakaoPay kakaoPay;

   @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleControllerException(Exception e)
    {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        //errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@PostMapping("/kakaoPay")
	@ResponseBody
	public String kakaoPayReady(@RequestParam Map<String, String> params) throws IOException {
		String kakaoPayUrl =  kakaoPay.kakaoPayReady(params);
		return kakaoPayUrl;		
	}
	
	@GetMapping("/kakaoPaySuccess")
	public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, @RequestParam("tid") String tid,  Model model) throws IOException {

		kakaoPay.kakaoPayInfo(pg_token,tid);
		
		return "/";
	}
	
	@GetMapping("/kakaoPayCancel")
	public String kakaoPayCancel() {
		return "/";
	}
	
	@GetMapping("/kakaoPayFail")
	public String kakaoPayFail() {
		return "/";
	}
}