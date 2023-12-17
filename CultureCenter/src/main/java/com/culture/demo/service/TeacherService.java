package com.culture.demo.service;

import java.util.List;
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
	//public TeacherDTO submitTeacherInfo(TeacherDTO teacherDTO);
	
	//트랜잭션
	public void insert(TeacherDTO teacherDTO);
	
	////강사신청완료 시 step3 페이지 이동
	public String createSubmitHtml();
	
	//제휴사 신청 페이지
	public String createCooperationHtml();
	
	//개인정보 수집 동의 여부
	public Map<String, String> deleteOk(Map<String, List<String>> step);
	
}
