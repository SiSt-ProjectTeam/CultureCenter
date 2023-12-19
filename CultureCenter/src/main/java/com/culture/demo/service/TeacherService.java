package com.culture.demo.service;

import java.util.Map;

import com.culture.demo.domain.TeacherDTO;

public interface TeacherService {

	//개인정보 수집 html
	public String createClausePrivacyHtml();
	
	//개인정보 수집 동의 여부
	public Map<String, String> clausePrivacyOk(Map<String, String> step);
	
	//강사신청 정보입력 html
	public String createTeacherInfoHtml();
	
	//강사신청 제출
	public TeacherDTO submitTeacherInfo(TeacherDTO teacherDTO);
	
}
