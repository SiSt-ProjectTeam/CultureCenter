package com.culture.demo.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {
	
	//TEACHER 테이블에 추가
	public int insertTeacher(@Param("submitList") Map<String, Object> submitList, @Param("teacherImg") String teacherImg, @Param("memberSq") int memberSq);
	
	//AWARDS 테이블에 추가
	public int insertAwards(@Param("tcawrdList") List<HashMap<String, Object>> tcawrdList, @Param("memberSq") int memberSq);

	//CARRER 테이블에 추가
	public int insertCarrer(@Param("tcawrdList")List<HashMap<String, Object>> tchistList, @Param("memberSq") int memberSq);
	
	//CERTIFICATE 테이블에 추가
	public int insertCertificate(@Param("tcawrdList")List<HashMap<String, Object>> tcauthctfList, @Param("memberSq") int memberSq);
	
	//EDUCATION 테이블에 추가
	public int insertEducation(@Param("tcawrdList")List<HashMap<String, Object>> tceduList, @Param("memberSq") int memberSq);
	
	//APPLY_CLASS 테이블에 추가
	public int insertApplyClass(@Param("submitList") Map<String, Object> submitList, @Param("resultList") List<String> resultList, @Param("memberSq") int memberSq);

}
