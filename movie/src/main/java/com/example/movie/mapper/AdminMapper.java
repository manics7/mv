package com.example.movie.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.movie.config.MybatisMapper;
import com.example.movie.dto.BusinessDto;
import com.example.movie.dto.quesReplyDto;
import com.example.movie.dto.ques_replyDto;
import com.example.movie.dto.quesboardDto;

public interface AdminMapper  extends MybatisMapper {

	List<quesboardDto> getQuesList(Map<String, Integer> qmap);

	int getBoardCnt();

	List<quesboardDto> getboardSelect(String m_id);

	List<quesboardDto> getboardRead(int ques_no);

	List<BusinessDto> getbuslist(HashMap<String, Integer> busmap);

	int getBusCnt();

	quesReplyDto selectQuesReply(int ques_no);

	boolean insert_ques_reply(ques_replyDto qrdto);
	
	
	
}
