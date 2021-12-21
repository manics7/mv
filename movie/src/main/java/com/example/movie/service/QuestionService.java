package com.example.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movie.common.AmazonS3Util;
import com.example.movie.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	AmazonS3Util amazonS3Service;
	
	@Autowired
	QuestionRepository questionRepository;
	
}
