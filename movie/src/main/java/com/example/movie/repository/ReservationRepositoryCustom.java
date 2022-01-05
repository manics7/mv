package com.example.movie.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.QSchedule;
import com.example.movie.entity.QScheduleDetail;
import com.example.movie.entity.Schedule;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;
	
	@Autowired
	EntityManager entityManager;

	private QSchedule schedule = new QSchedule("sch");
	private QScheduleDetail schDetail=  new QScheduleDetail("schDetail");
	
	public List<Schedule> selectSchList(String movieCd ,Integer thCode, String schDate){
		List<Schedule> schList =  jpaQueryFactory
				.selectFrom(schedule)
				.where(eqSchDate(schDate)
						.or(eqThcode(thCode))
						.or(eqMovieCd(movieCd))
				).orderBy(schedule.movieOfficial.movieNm.asc())		
				.fetch();
		
		return schList;
	}	
	
	public List<Schedule> getSchduleTime(String movieCd, Integer thCode, String schDate) {
		
		LocalDateTime now  = LocalDateTime.now();
		//시작시간에서  10분 뒤까진 목록에서 보여줄 있도록 +10분추가
		Date date =new Date();

		if(!schDate.isEmpty()) {
			now = LocalDateTime.parse(schDate+" 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			date = java.sql.Date.valueOf(now.toLocalDate());
			
		}	
		
		
		List<Schedule> schList = jpaQueryFactory
				.select(schedule)
				.from(schedule)
				.innerJoin(schDetail)
					.on(schedule.schCode.eq(schDetail.schCode))
				.where(schDetail.schDetailStart.goe(date)
							
						)
				
				.fetch();
		return schList;
	}

	public List<Schedule> getTimeList(){
		
		LocalDateTime now  = LocalDateTime.now();
		//시작시간에서  10분 뒤까진 목록에서 보여줄 있도록 +10분추가
		Date date =new Date();

		
		String jpql = "SELECT sch.schCode, sch.schDate, sch.movieCd, sch.thCode  "
				+ " FROM Schedule sch "
				+ " JOIN SCHEDULE_DETAIL schd"
				+ " ON sch.schCode = schd.schCode"
				+ " WHERE sch.schDate = :schDate"
				+"  AND TO_CHAR(schd.sch_detail_start + 10/(24*60),'YYYYMMDD HH24MISS')   >= TO_CHAR(SYSTIMESTAMP,'YYYYMMDD HH24MISS')";
		TypedQuery<Schedule> query = entityManager.createQuery(jpql, Schedule.class);
		query.setParameter("schDate", date);
		return query.getResultList();
	}
	
	
	
	public BooleanExpression eqMovieCd(String movieCd) {
		return movieCd == null ? null : schedule.movieCd.eq(movieCd);		
	}

	public BooleanExpression eqThcode(Integer thCode) {
		return thCode == null ? null : schedule.thCode.eq(thCode);
	}
	public BooleanExpression eqSchDate(String schDate) {
		if(schDate == null) {
			return null;
		}
		Date date = getDate(schDate);
		return schedule.schDate.eq(date);		 
	}
	
	public Date getDate(String schDate) {
		LocalDateTime dateTime  = LocalDateTime.now();
		Date date =new Date();

		if(!schDate.isEmpty()) {
			dateTime = LocalDateTime.parse(schDate+" 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			date = java.sql.Date.valueOf(dateTime.toLocalDate());
			
		}		
		return date;
	}
}
