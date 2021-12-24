package com.example.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movie.common.AwsS3;
import com.example.movie.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	AwsS3 awsS3;
	
	@Autowired
	QuestionRepository questionRepository;
	
}
