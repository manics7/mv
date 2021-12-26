package com.example.movie.dto;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@DynamicInsert // null 컬럼 입력 제외
@DynamicUpdate  // null 컬럼 업데이트 제외
@Entity
@NoArgsConstructor // 기본생성자 생성
@ToString // toString() 함수 자동생성
public class Schedule {

	@Id
	@Column(name="SCH_CODE", columnDefinition="상영 시간 키")
	private String schCode;
	
	@Column(name="TH_CODE", columnDefinition="영화관코드")
	private Integer thCode;
	
	@Column(name="ROOM_NO", columnDefinition="상영관코드")
	private String roomNo;

	@Column(name="MOVIECD", columnDefinition="영화코드")
	private String movieCd;
	
	@Column(name="SCH_DATE", columnDefinition="상영날짜")
	private Date schDate;
	
	@Column(name="SCH_TIME", columnDefinition="대기 시간")
	private Integer schTime;
	 
	// cascade 특정 엔티티를 영속상태(영속성 컨텍스트에서 저장)로 만들 때 연관된 엔티티도 함꼐 영속 상태로 만들떄 사용 	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)   
	@JoinColumn(name = "SCH_CODE", referencedColumnName = "SCH_CODE"	, insertable=false, updatable=false, nullable = false)
	private List<ScheduleDetail> scheduleDetail;
	
	@OneToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "MOVIECD", referencedColumnName = "MOVIECD"
						, insertable = false, updatable = false, nullable = false)
	private MovieOfficial movieOfficial;
	
}
