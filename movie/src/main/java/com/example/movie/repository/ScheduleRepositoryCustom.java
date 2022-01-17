package com.example.movie.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.QSchedule;
import com.example.movie.entity.QScheduleDetail;
import com.example.movie.entity.Schedule;
import com.example.movie.entity.ScheduleDetail;
import com.example.movie.utill.DateUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Autowired
	EntityManager entityManager;

	private QSchedule schedule = new QSchedule("sch");
	private QScheduleDetail schDetail=  new QScheduleDetail("schd");
	
	//상영시간여부 상관없이 영화,극장,일자로 해당 스케쥴을 가져온다.	
	public List<Schedule> selectSchList(String movieCd ,Integer thCode, String schDate){
		
		LocalDateTime now =LocalDateTime.now().minusMinutes(10);
		Date date = java.sql.Timestamp.valueOf(now);

		List<Schedule> schList =  jpaQueryFactory
				.selectFrom(schedule)
				.join(schDetail)
				.on(schedule.schCode.eq(schDetail.schCode))
				.where(eqMovieCd(movieCd).and(eqThcode(thCode).and(eqSchDate(schDate))))
				.where(eqMovieCd(movieCd).and(eqThcode(thCode))
						.or(eqMovieCd(movieCd)).and(eqSchDate(schDate))						
						.or(eqThcode(thCode).and(eqSchDate(schDate)))
						.or(eqThcode(thCode).and(eqMovieCd(movieCd)))
						.or(eqSchDate(schDate).and(eqMovieCd(movieCd)))
						.or(eqSchDate(schDate).and(eqThcode(thCode)))
						)
				.where(eqSchDate(schDate)
						.or(eqThcode(thCode))
						.or(eqMovieCd(movieCd))
						)		
				.where(schDetail.schDetailStart.goe(date))
				//		.where(schDetail.schDetailStart.goe(date).and(schDetail.schDetailStart.loe(new Date())))

				.orderBy(schedule.schCode.asc() ,schedule.movieOfficial.movieNm.asc(), schedule.theater.thName.asc())
				.fetch();

		return schList;
	}	
	
	
	//시작시간에서  10분 뒤까진 목록에서 보여줄 있도록 +10분추가
		public List<Schedule> getDefaultScheule(Date startDate, Date endDate){		

			String jpql = "SELECT sch"
					+ " FROM Schedule sch "
					+ " JOIN ScheduleDetail schd"
					+ " 	ON sch.schCode = schd.schCode"
			//		+ " WHERE TO_CHAR(schd.schDetailEnd,'YYYYMMDD HH24MISS')  >= TO_CHAR(SYSTIMESTAMP ,'YYYYMMDD HH24MISS')"
					+ " WHERE TO_CHAR(schd.schDetailStart + 10/(24*60),'YYYYMMDD HH24MISS')   >= TO_CHAR(SYSTIMESTAMP,'YYYYMMDD HH24MISS')"
				//	+ " AND TO_CHAR(schd.schDetailStart + 10/(24*60),'YYYYMMDD HH24MISS')  BETWEEN TO_CHAR(:startDate ,'YYYYMMDD HH24MISS') AND TO_CHAR(:endDate ,'YYYYMMDD HH24MISS')"
					+ " ORDER BY schd.schDetailSeq asc, sch.schCode asc";
			TypedQuery<Schedule> query = entityManager.createQuery(jpql, Schedule.class);		
		//	query.setParameter("startDate", startDate);		 
		//.setParameter("endDate", endDate);		 
			return query.getResultList();

		}
	

	//시작시간에서  10분 뒤까진 목록에서 보여줄 있도록 +10분추가
	public List<Schedule> getScheule(String movieCd, Integer thCode, String schDate){		
		Date date =new Date();
		date = DateUtil.strToDate(schDate);

		String jpql = "SELECT sch"
				+ " FROM Schedule sch "
				+ " JOIN ScheduleDetail schd"
				+ " 	ON sch.schCode = schd.schCode"
				+ " WHERE sch.schDate = :schDate"
				+ " AND sch.movieCd = :movieCd"
				+ " AND sch.thCode = :thCode"
				+ " AND TO_CHAR(schd.schDetailStart + 10/(24*60),'YYYYMMDD HH24MISS')   >= TO_CHAR(SYSTIMESTAMP,'YYYYMMDD HH24MISS')"
				+ " ORDER BY schd.schDetailSeq asc, sch.schCode asc";
		TypedQuery<Schedule> query = entityManager.createQuery(jpql, Schedule.class);		
		query.setParameter("movieCd", movieCd);
		query.setParameter("thCode", thCode);
		query.setParameter("schDate", date);		 
		return query.getResultList();

	}
	//시간목록
	public List<ScheduleDetail> getschDetail(Integer schCode, String movieCd, Integer thCode, String schDate){		
		Date date =new Date();
		date = DateUtil.strToDate(schDate);

		String jpql = "SELECT schd"
				+ " FROM Schedule sch "
				+ " JOIN ScheduleDetail schd"
				+ " 	ON sch.schCode = schd.schCode"
				+ " WHERE sch.schDate = :schDate"
				+ " AND sch.schCode =:schCode"
				+ " AND sch.movieCd = :movieCd"
				+ " AND sch.thCode = :thCode"
				+ " AND TO_CHAR(schd.schDetailStart + 10/(24*60),'YYYYMMDD HH24MISS')   >= TO_CHAR(SYSTIMESTAMP,'YYYYMMDD HH24MISS')"
				+ " ORDER BY schd.schCode, schd.schDetailSeq";
		TypedQuery<ScheduleDetail> query = entityManager.createQuery(jpql, ScheduleDetail.class);		
		query.setParameter("schCode", schCode);
		query.setParameter("movieCd", movieCd);
		query.setParameter("thCode", thCode);
		query.setParameter("schDate", date);		 
		return query.getResultList();

	}
	
	//시작시간에서  10분 뒤까진 목록에서 보여줄 있도록 +10분추가
	public List<ScheduleDetail> getTotalMovieTimeList(Integer schCode, String movieCd, String schDate){		
		Date date =new Date();
		date = DateUtil.strToDate(schDate);

		String jpql = "SELECT schd"
				+ " FROM Schedule sch "
				+ " JOIN ScheduleDetail schd"
				+ " 	ON sch.schCode = schd.schCode"
				+ " WHERE sch.schDate = :schDate"
				+ " AND sch.schCode =:schCode"
				+ " AND sch.movieCd = :movieCd"
				+ " AND TO_CHAR(schd.schDetailStart + 10/(24*60),'YYYYMMDD HH24MISS')   >= TO_CHAR(SYSTIMESTAMP,'YYYYMMDD HH24MISS')"
				+ " ORDER BY schd.schCode, schd.schDetailSeq";
		TypedQuery<ScheduleDetail> query = entityManager.createQuery(jpql, ScheduleDetail.class);		
		query.setParameter("schCode", schCode);
		query.setParameter("movieCd", movieCd);
		query.setParameter("schDate", date);		 
		return query.getResultList();

	}

	public BooleanBuilder eqMovieCd(String movieCd) {
		if(StringUtils.isEmpty(movieCd)) {
			return new BooleanBuilder();
		}		
		return new BooleanBuilder(schedule.movieCd.eq(movieCd));		
	}

	public BooleanBuilder eqThcode(Integer thCode) {

		if(thCode == null) {
			return new BooleanBuilder();
		}			
		return new BooleanBuilder(schedule.thCode.eq(thCode));
	}
	public BooleanBuilder eqSchDate(String schDate) {
		if(schDate.isEmpty()) {
			return new BooleanBuilder();
		}
		Date date = DateUtil.strToDate(schDate);
		return new BooleanBuilder(schedule.schDate.eq(date));							 
	}
	
	public BooleanBuilder goeSchDate(String schDate) {
		if(schDate.isEmpty()) {
			return new BooleanBuilder();
		}
		Date date = DateUtil.strToDate(schDate);
		return new BooleanBuilder(schedule.schDate.goe(date));							 
	}
	
	public BooleanBuilder goeSchDetailStart(Date date) {
		if(date == null) {
			return new BooleanBuilder();
		}
		return new BooleanBuilder(schDetail.schDetailStart.goe(date));							 
	}

}
