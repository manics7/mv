package com.example.movie.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.movie.entity.Notice;
import com.example.movie.repository.noticeRepository;

@Service
public class NoticeService {

	@Autowired
	private noticeRepository noticeRepository;
	
	public Page<Notice> getNoticeList(String searchParam, Pageable pageable) {
		Page<Notice> noticeList = null;
		if(searchParam != null) {
			noticeList = noticeRepository.findByNoticeTitleContaining(searchParam, pageable);
		}else {
			noticeList = noticeRepository.findAll(pageable);
		}
		
		return noticeList;
	}

	public void saveNotice(Notice notice) {
		if(notice.getViewCnt() == null) {
			notice.setViewCnt(0);
		}
		noticeRepository.save(notice);
	}

	public void deleteNotice(Notice notice) {
		noticeRepository.delete(notice);		
	}

	public Notice selectNotice(Integer noticeNo) {
		
		Notice notice = new Notice();
		if(noticeNo != null){
			Optional<Notice> noticeOpt = noticeRepository.findById(noticeNo);
			
			if(noticeOpt.isPresent()) {
				notice = noticeOpt.get();
			
				notice.setViewCnt(notice.getViewCnt()+1);
				noticeRepository.save(notice);
			}
		}
		
		return notice;
	}
}
