package com.culture.demo.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {
	
	//TEACHER 테이블에 추가
	public int insertTeacher(Map<String, Object> submitList);
	
	//AWARDS 테이블에 추가
	public int insertAwards(List<HashMap<String, Object>> tcawrdList);

	//CARRER 테이블에 추가
	public int insertCarrer(List<HashMap<String, Object>> tchistList);
	
	//CERTIFICATE 테이블에 추가
	public int insertCertificate(List<HashMap<String, Object>> tcauthctfList);
	
	//EDUCATION 테이블에 추가
	public int insertEducation(List<HashMap<String, Object>> tceduList);
	
	//APPLY_CLASS 테이블에 추가
	public int insertApplyClass(@Param("submitList") Map<String, Object> submitList, @Param("resultList") List<String> resultList);

}
