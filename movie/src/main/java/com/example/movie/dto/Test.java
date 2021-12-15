package com.example.movie.dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "TEST1")
@NoArgsConstructor
public class Test  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="NO")
	private Integer no; 
	//@Column(name="ENAME")
	private String nm;

	
	
}
