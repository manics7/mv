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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.common.ErrorResponse;
import com.example.movie.common.KakaoPay;
import com.example.movie.entity.Payment;
import com.example.movie.service.PaymenteService;
import com.example.movie.service.ReservationService;
import com.example.movie.vo.KakaoPayApprovalVO;
import com.example.movie.vo.KakaoPayCancelVO;
import com.example.movie.vo.KakaoPayReadyVO;

@Controller
public class PaymentController {
	
	@Autowired
	KakaoPay kakaoPay;
	
	@Autowired
	PaymenteService paymenteService;
	
	@Autowired
	ReservationService reservationService;
	
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
	public KakaoPayReadyVO kakaoPayReady(@RequestParam Map<String, String> params) throws IOException {
		KakaoPayReadyVO kakaoPayReadyVO =  kakaoPay.kakaoPayReady(params);
		return kakaoPayReadyVO;		
	}
	
	@GetMapping("/kakaoPaySuccess")
	public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model, RedirectAttributes rttr) throws IOException, InterruptedException {

		KakaoPayApprovalVO kakaoPayApprovalVO = kakaoPay.kakaoPayInfo(pg_token);
		
		int rsrvNo = paymenteService.insertPayment(kakaoPayApprovalVO);
		
		//ModelAndView modelAndView = new ModelAndView();
		//modelAndView.addObject("res",reservationService.getPaymentInfo(rsrvNo));
		//modelAndView.addObject("rsrvNo",rsrvNo);
		//modelAndView.setViewName("rsrv/reservationComplete");
		//rttr.addAttribute("res", reservationService.getPaymentInfo(rsrvNo));
		model.addAttribute("rsrvNo",rsrvNo);		
		//return "redirect:/reservationComplete";
		//return  "rsrv/reservationComplete";
		return "rsrv/reservationComplete";
	}
	
	//결창에서 완료전에 창닫았을때
	@GetMapping("/kakaoPayCancel")
	public String kakaoPayCancel() {
		return "rsrv/cancle";
	}
	
	//결제 실패
	@GetMapping("/kakaoPayFail")
	public String kakaoPayFail() {
		return "/";
	}	
	
	//결제완료후 예매완료페이지를 모달창에 로드
	@GetMapping("/reservationComplete")
	public String reservationComplete() {
		return "rsrv/reservationComplete";
	}	
	//카카오 결제 취소 -> 예매취소처리
	@PostMapping("paymentCencel")
	public String paymentCencel(Integer rsrvNo, Model model) throws IOException {
		Payment payment =  paymenteService.getPayment(rsrvNo);
		KakaoPayCancelVO kakaoPayCancelVO = kakaoPay.kakaoPayCancel(payment);
		reservationService.reservationCancel(rsrvNo);
		model.addAttribute("rsrvNo",rsrvNo);
		return "rsrv/reservationCancel";
	}			
	
	//결제완료후 예매취소페이지를 모달창에 로드
	@GetMapping("/reservationCancel")
	public String reservationCancel() {
		return "rsrv/reservationCancel";
	}	
}