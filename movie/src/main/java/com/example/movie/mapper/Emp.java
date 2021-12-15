package com.example.movie.mapper;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "emp")
@NoArgsConstructor
public class Emp  {

	@Id
	@Column(name="EMPNO")
	private Integer empNo; 
	//@Column(name="ENAME")
	private String ename;
	//@Column(name="JOB")
	private String job;
	//@Column(name="MGR")
	private Integer mgr;
	//@Column(name="HIREDATE")
	private Date hiredate;
	//@Column(name="SAL")
	private Integer sal;
	//@Column(name="COMM")
	private Integer comm;
	//@Column(name="DEPTNO")
	private Integer deptno;
	
	
}
