package com.example.movie.dto;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Table(name = "RESERVATION")
@Entity
@NoArgsConstructor
public class Reservation {

	@Id
	@Column(name="RSRV_NO", columnDefinition="예매번호")
	private String rsrvNo;
	
	@NonNull
	@Column(name="SCH_NO", columnDefinition="일정번호")
	private String schNo;
	
	@NonNull
	@Column(name="M_ID", columnDefinition="공지내용")
	private String mId;
	
	@Column(name="RSRV_DATE", columnDefinition="작성일")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date regDate;
	
	@Column(name="ADULT_CNT", columnDefinition="성인인원")
	private Integer adultCnt;	
	
	@NonNull
	@Column(name="YOUTH_CNT", columnDefinition="청소년인원")
	private Integer youthCnt;

	@NonNull
	@Column(name="PRICE", columnDefinition="금액")
	private Integer price;

	
	@OneToMany
	@JoinColumn(name = "RSRV_NO")
	private List<ReservationSeat> reservationSeat;
	
}
