package com.example.movie.utill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {

	public static Date strToDate(String strDate) {
		
		if (isDate(strDate) == false) {
			throw new IllegalArgumentException("잘못된 형식입니다. " + strDate);
		}		
		LocalDateTime dateTime  = LocalDateTime.now();
		Date date =new Date();
		
		dateTime = LocalDateTime.parse(strDate+" 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		date = java.sql.Date.valueOf(dateTime.toLocalDate());
		return date;
	}
	
	public static boolean isDate(String strDate) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
		dateFormat.setLenient(false); //false일경우 처리시 입력한 값이 잘못된 형식일 시 오류가 발생
		try {
			dateFormat.parse(strDate); //대상 값 포맷에 적용되는지 확인			
		} catch (ParseException e) {
			return false;
		}		
		return true;
	}

}
