package com.culture.demo.service;

import java.util.Map;

public interface TeacherService {

	//개인정보 수집 html
	public String createClausePrivacyHtml();
	
	//개인정보 수집 동의 여부
	public Map<String, String> clausePrivacyOk(Map<String, String> clausePrivacyData);
	
	//강사신청 정보입력 html
	public String createTeacherInfoHtml();
	
	//강사신청 제출 트랜잭션
	public int submitTeacherInfo(Map<String, Object> submitDataString) throws Exception;

	//강사신청완료 시 step3 페이지 html
	public String createSubmitHtml();
	
	//제휴사 신청 페이지
	public String createCooperationHtml();
	
	//강사신청서 작성 중 삭제
	public Map<String, String> deleteOk(Map<String, String> deleteData);
	
}
