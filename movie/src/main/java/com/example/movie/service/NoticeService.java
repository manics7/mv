package com.example.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.movie.dto.Notice;
import com.example.movie.repository.noticeRepository;

@Service
public class NoticeService {

	@Autowired
	private noticeRepository noticeRepository;

	public Page<Notice> getNoticeList(Pageable pageable) {
		Page<Notice> noticeList = noticeRepository.findAll(pageable);
		return noticeList;
	}
}
