package com.example.movie.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.movie.common.ErrorResponse;
import com.example.movie.service.ReservationService;
import com.example.movie.service.ScheduleService;

@Controller
public class ReservationController {
	
	@Autowired
	ScheduleService ScheduleService;
	
	@Autowired
	ReservationService reservationService;
	
	ModelAndView modelAndView;

   @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleControllerException(Exception e)
    {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        //errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@GetMapping("/rsrv")
	public ModelAndView reservation() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("rsrv/reservation");
		return mv;
	}
	
	@GetMapping("/rsrvSeat")
	public String reservationSeat() {		
		return "rsrv/reservationSeat";
	}
	
	@PostMapping("/getSeat")
	@ResponseBody
	public Map<String, Object> getSeat(Integer schCode ,Integer schDetailSeq) throws Exception {
		Map<String, Object>  map = reservationService.getSeat(schCode, schDetailSeq);
		return map;
	}
	
	//좌석선택 후 결제화면 이동시 선택한 좌석킵 처리를 위해서 예약정보 저장, 결제 전에 뒤로가면 다시 삭제처리
	@PostMapping("/reservation")
	@ResponseBody
	public Map<String, Integer> reservation(@RequestParam(required = false) Map<String, Object> params, @RequestParam(name = "seatNo[]" ,required = false) List<String> seatNo) throws Exception{
		Map<String, Integer>  map =reservationService.insertReservation(params, seatNo); 
		return map;
	}
		
	//결제화면에서 뒤로가기 했을때 예약자료+예약좌석 전부삭제
	@RequestMapping(value = "/deleteRsrv", method = RequestMethod.DELETE) 
	public void deleteRsrv(Integer rsrvNo) throws Exception {
		reservationService.deleteReservation(rsrvNo);
	}
	
	@GetMapping("/rsrvPayment")
	public String rsrvPayment() {
		return "rsrv/reservationPayment";
	}
	
	//결제화면에 보여줄 정보를 가져온다.
	@PostMapping("/getPaymentInfo")
	@ResponseBody
	public Map<String, Object> getPaymentInfo(Integer rsrvNo) {
		Map<String, Object>  map = reservationService.getPaymentInfo(rsrvNo);
		return map;
	}
	
	@GetMapping("/kakaoPay")
	@ResponseBody
	public String kakaoPay() throws IOException {
		
		URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Authorization", "KakaoAK fa40fe44b0a731a95f5562eded86e507");
		connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		connection.setDoOutput(true);
		
		String params = "cid=TC0ONETIME"
				+ "&partner_order_id=partner_order_id"
				+ "&partner_user_id=partner_user_id"
				+ "&item_name=1984 최동원"
				+ "&quantity=1"
				+ "&total_amount=2200"
				+ "&tax_free_amount=0"
				+ "&approval_url=https://localhost/success"
				+ "&fail_url=https://localhost/fail"
				+ "&cancel_url=https://localhost/cancel";
				
		
		OutputStream outputStream = connection.getOutputStream();
		DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
		dataOutputStream.writeBytes(params);
		dataOutputStream.close();
		
		int result = connection.getResponseCode();
		InputStream inputStream;
		
		if(result == 200) {
			inputStream = connection.getInputStream();
		}else {
			inputStream = connection.getErrorStream();
		}
		
		InputStreamReader inputStreamReader =  new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		return bufferedReader.readLine();
	}

	
	
}