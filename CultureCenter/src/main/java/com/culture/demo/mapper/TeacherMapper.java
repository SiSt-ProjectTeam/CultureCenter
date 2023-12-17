package com.culture.demo.mapper;

import com.culture.demo.domain.TeacherDTO;

public interface TeacherMapper {
	
	//TEACHER 테이블에 추가
	public int insertTeacher(TeacherDTO teacherDTO);
	
	//AWARDS 테이블에 추가
	public int insertAwards(TeacherDTO teacherDTO);
	
	//CARRER 테이블에 추가
	public int insertCarrer(TeacherDTO teacherDTO);
	
	//CERTIFICATE 테이블에 추가
	public int insertCertificate(TeacherDTO teacherDTO);
	
	//EDUCATION 테이블에 추가
	public int insertEducation(TeacherDTO teacherDTO);
	
	//APPLY_CLASS 테이블에 추가
	public int insertApplyClass(TeacherDTO teacherDTO);

}
