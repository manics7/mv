package com.example.movie.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@DynamicInsert // null 컬럼 입력 제외
@DynamicUpdate  // null 컬럼 업데이트 제외
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	
	@Id
	@Column(name="TID", columnDefinition="결제고유번호")
	private String tid; 
	
	//@Column(name="AID", columnDefinition="요청고유번호")
//	private String aid; 
	
	@Column(name="RSRV_NO", columnDefinition="글번호")
	private Integer rsrvNo; //예매키
	
//	private String cid; // 가맹점코드(테스트코드)
	
	@Column(name="PARTNER_ORDER_ID", columnDefinition="가맹점주문번호")
	private String partner_order_id; 
	
	@Column(name="PARTNER_USER_ID", columnDefinition="주문자")
	private String partner_user_id; 
	
	@Column(name="PAYMENT_METHOD_TYPE", columnDefinition="결제방법")
	private String payment_method_type;   
	
	@Column(name="ITEM_NAME", columnDefinition="상품이름")
	private String item_name; 
	
	//@Column(name="ITEM_CODE", columnDefinition="상품코드")
	//private String item_code;  
	
	//@Column(name="ITEM_CODE", columnDefinition="결제요청시간")
	//private Date created_at; // 

	@Column(name="TOTAL", columnDefinition="전체 결제금액")
	private Integer total;
	@Column(name="QUANTITY", columnDefinition="전체 결제금액")
	private Integer quantity;
	
	@Column(name="TAX_FREE", columnDefinition="비과세 금액")
	private Integer tax_free;
	@Column(name="VAT", columnDefinition="부가세 금액")
	private Integer vat; //부가세 금액
//	private Integer point; //사용한 포인트 금액
	@Column(name="DISCOUNT", columnDefinition="할인 금액")
	private Integer discount; //할인 금액


	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="APPROVED_AT", columnDefinition="결제승인시간")
	private Date approved_at; 
	
//	@Column(name="PURCHASE_CORP", columnDefinition="매입 카드사 한글명")
//	private String purchase_corp; 
	
//	private String purchase_corp_code; //매입 카드사 코드
//	private String issuer_corp; //카드 발급사 한글명
//	private String issuer_corp_code;//카드 발급사 코드
//	private String bin; //카드 BIN
//	private String card_type; //카드 타입
//	private String install_month; //할부 개월 수
//	@Column(name="APPROVED_ID", columnDefinition="카드사 승인번호")
//	private String approved_id; 
	
//	private String card_mid; //	카드사 가맹점 번호
//	private String interest_free_install; //무이자할부 여부(Y/N)
//	private String card_item_code; //카드 상품 코드

	

}
