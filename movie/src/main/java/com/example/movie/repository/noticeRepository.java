package com.example.movie.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.Notice;

@Repository
public interface noticeRepository extends JpaRepository<Notice, Integer> {

	Page<Notice> findByNoticeTitleContaining(String noticeTitle, Pageable pageable);

}
