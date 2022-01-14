package com.example.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.movie.entity.Payment;
import com.example.movie.repository.PaymentRepository;
import com.example.movie.vo.KakaoPayApprovalVO;

@Service
public class PaymenteService {

	@Autowired
	PaymentRepository paymentRepository;
	
	@Transactional
	public Integer insertPayment(KakaoPayApprovalVO kakaoPayApprovalVO) {
		// TODO Auto-generated method stub
	
		Payment payment = Payment.builder()
									.tid(kakaoPayApprovalVO.getTid())
									.rsrvNo(kakaoPayApprovalVO.getRsrvNo())
									.partner_order_id(kakaoPayApprovalVO.getPartner_order_id())
									.partner_user_id(kakaoPayApprovalVO.getPartner_user_id())
									.payment_method_type(kakaoPayApprovalVO.getPayment_method_type())
									.item_name(kakaoPayApprovalVO.getItem_name())
									.total(kakaoPayApprovalVO.getAmount().getTotal())		
									.tax_free(kakaoPayApprovalVO.getAmount().getTax_free())
									.vat(kakaoPayApprovalVO.getAmount().getVat())
									.discount(kakaoPayApprovalVO.getAmount().getDiscount())
									.quantity(kakaoPayApprovalVO.getQuantity())									
									.build();
		
		paymentRepository.save(payment);
		
		return payment.getRsrvNo();
	}

	public Payment getPayment(Integer rsrvNo) {

		Payment payment = paymentRepository.findByRsrvNo(rsrvNo);
		return payment;
	}

	
}
