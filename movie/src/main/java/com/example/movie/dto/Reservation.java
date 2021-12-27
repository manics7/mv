package com.example.movie.dto;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@DynamicInsert // null 컬럼 입력 제외
@DynamicUpdate  // null 컬럼 업데이트 제외
// @Table(name = "RESERVATION") 클래스랑 이름같으면 필요없는듯 
@Entity
@NoArgsConstructor // 기본생성자 생성
@ToString // toString() 함수 자동생성
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //  @OneToMany 같은 관계 설정으로 무한로딩될때 해결법 
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="RSRV_NO", columnDefinition="예매번호")
	private Integer rsrvNo;
	
	@NonNull
	@Column(name="SCH_NO", columnDefinition="일정번호")
	private Integer schNo;
	
	@NonNull
	@Column(name="M_ID", columnDefinition="아이디")
	private String mId;
	
	@Column(name="RSRV_DATE", columnDefinition="작성일")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date rsrvDate;
	
	@Column(name="ADULT_CNT", columnDefinition="성인인원")
	private Integer adultCnt;	
	
	@NonNull
	@Column(name="YOUTH_CNT", columnDefinition="청소년인원")
	private Integer youthCnt;

	@NonNull
	@Column(name="PRICE", columnDefinition="금액")
	private Integer price;

	
	@OneToMany(fetch = FetchType.LAZY) //지연로딩 필요할때만 실행
	@JoinColumn(name = "RSRV_NO")
	private List<ReservationSeat> reservationSeat;
	
	@NotFound(action=NotFoundAction.IGNORE) // 조인 관계에서 엔티티가 매핑이 안되거나 찾지 못할때 NotFoundAction 에러 무시하는 어노테이션 
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name="M_ID", referencedColumnName="M_ID", insertable=false, updatable=false)  // insertable , updatable Reservation 저장시 멤버는 인서트,업데이트 제외
	private Member member;
	
}
