package com.example.movie.vo;


import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@DynamicInsert // null 컬럼 입력 제외
@DynamicUpdate  // null 컬럼 업데이트 제외
//@Entity
@NoArgsConstructor // 기본생성자 생성
@ToString // toString() 함수 자동생성
public class KakaoPayApprovalVO {
	//response 
	private String aid; //요청고유번호 주문번호
	private String tid; // 결제고유번호
	private String cid; // 가맹점코드(테스트코드)
	private String partner_order_id; //가맹점주문번호
	private String partner_user_id; // 주문자
	private String payment_method_type;  // 결제방법
	private String item_name; //상품이름, 
	private String item_code; // 상품코드
	private Date created_at; // 결제요청시간
	private Date approved_at; // 결제승인시간
	
	private AmountVO amountVO;
	private CardVO cardVO;
	
	

}
