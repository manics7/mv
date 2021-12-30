package com.example.movie;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.movie.dto.MovieOfficial;
import com.example.movie.dto.QMovieOfficial;
//import com.example.movie.dto.QMember;
//import com.example.movie.dto.QMovieOfficial;
import com.example.movie.dto.Schedule;
import com.example.movie.repository.MovieOfficialRepository;
import com.example.movie.repository.ScheduleRepositoy;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import oracle.net.aso.m;
import oracle.net.aso.q;

@RunWith(SpringJUnit4ClassRunner.class)
@PropertySource("classpath:aws.properties")
@SpringBootTest
@Transactional
@Commit
class QueryTest {
	
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@Autowired
	ScheduleRepositoy scheduleRepositoy;
	
	@Autowired
	MovieOfficialRepository movieOfficialRepository;
	
	@Test
	public void contextLoads() {
		LocalDate now = LocalDate.now();
		String date= now.toString();	
		LocalDateTime dateTime = LocalDateTime.parse(date+" 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));		
		LocalDate lastDate= now.plusWeeks(2);//2주간 마지막날
		
		Date startDate = java.sql.Date.valueOf(dateTime.toLocalDate());
		Date endDate = java.sql.Date.valueOf(lastDate);
		
		List<Schedule> scheduleList = scheduleRepositoy.findBySchDateBetween(startDate, endDate);

		//스케쥴에서 영화,극장코드 중복제거해서 가져오기
		List<String> movieCdList = scheduleList.stream().map(Schedule::getMovieCd).distinct().collect(Collectors.toList());
		List<MovieOfficial> movieList = movieOfficialRepository.findAllById(movieCdList)
				.stream().sorted(Comparator.comparing(MovieOfficial::getMovieNm))
				.collect(Collectors.toList());
	
		
		for (int i = 0; i < movieList.size(); i++) {
			System.out.println("/////////////////////////////////"+movieList.get(i).getMovieNm());
		}
		
		EntityManager em = entityManagerFactory.createEntityManager();
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
		QMovieOfficial qMovieOfficial = new QMovieOfficial("m");
		
		//방법1
		String query  = "select * from movieOfficial where movieNm = :movieNm";
		List<MovieOfficial> sortMovieList = jpaQueryFactory
															.select(qMovieOfficial)
															.from(qMovieOfficial)
															.orderBy(qMovieOfficial.movieNm.asc())
															.fetch();
															
				
		System.out.println(sortMovieList.size());
					
	}
	
	

}
