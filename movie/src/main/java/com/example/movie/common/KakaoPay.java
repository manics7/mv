package com.example.movie.common;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.movie.dto.MemberDto;
import com.example.movie.entity.KakaoPayDto;
import com.example.movie.entity.Payment;


@Component
public class KakaoPay {

	private static final String HOST = "https://kapi.kakao.com";
	private static final String APPROVAL_URL = "http://localhost/kakaoPaySuccess"; // 결제 성공 시 redirect url
	private static final String FAIL_URL = "http://localhost/kakaoPayFail"; // 결제 실패시
	private static final String CANCEL_URL = "http://localhost/kakaoPayCancel"; // 결제 취소시
	
	@Autowired
	private HttpSession httpSession;
	
	private Map<String, String> param;
	
	private KakaoPayDto kakaoPayDto;

	
	//카카오 결제준비
	public String kakaoPayReady(Map<String, String> map) throws IOException {

		param = map;
		RestTemplate restTemplate = new RestTemplate();		
		MemberDto memberDto = (MemberDto) httpSession.getAttribute("userInfo");
		
		String id  = "partner_user_id";
		if(memberDto != null) {
			 id = memberDto.getM_id();
		}
		
		// 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "fa40fe44b0a731a95f5562eded86e507");
        //headers.add("Content-type", "application/x-www-form-urlencoded;charset=UTF-8"); // restTemplate 알아서해준다고함
        
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", map.get("orderId")); //주문번호  rsrv_date +  rsrv_no + movie_Cd
        params.add("partner_user_id", id);
        params.add("item_name", map.get("movieNm")); //상품명
        params.add("quantity", map.get("seatCnt"));  // 수량
        params.add("total_amount", map.get("amount")); // 총금액
        params.add("tax_free_amount", "0");  // 비과세 금액
        params.add("approval_url",APPROVAL_URL); //성공시 url 
        params.add("cancel_url", CANCEL_URL); // 취소시 url
        params.add("fail_url", FAIL_URL); // 실패시 url

        //헤더와 바디 연결
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        
  		try {		
			
  			//KakaoPayDto kakaoPayDto = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayDto.class);
  			//return kakaoPayDto.getNext_redirect_pc_url();
  			String result = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, String.class);
			return result;
  			
			
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
				
			
		
        return restTemplate.getErrorHandler().toString();

	}
	
	public Payment kakaoPayInfo(String pg_token, String tid) throws IOException {
		 RestTemplate restTemplate = new RestTemplate();
		 
	        // 서버로 요청할 Header
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Authorization", "KakaoAK " + "fa40fe44b0a731a95f5562eded86e507");
	 
	        MemberDto memberDto = (MemberDto) httpSession.getAttribute("userInfo");
			
			String id  = "partner_user_id";
			if(memberDto != null) {
				 id = memberDto.getM_id();
			}
			
	        
	        
	        // 서버로 요청할 Body
	        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	        params.add("cid", "TC0ONETIME");
	        params.add("tid",  tid);
	        params.add("partner_order_id", param.get("orderId"));
	        params.add("partner_user_id", id);
	        params.add("pg_token", pg_token);
	        params.add("total_amount", param.get("amount"));
	        
	        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
	        
	        try {
	        	Payment payment = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, Payment.class);
	           // log.info("" + kakaoPayApprovalVO);
	          
	          //  return kakaoPayApprovalVO;
	        	return payment;
	        } catch (RestClientException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        return null;
	}

}
