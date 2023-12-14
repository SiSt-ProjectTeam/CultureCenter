package com.culture.demo.service;

import java.util.Map;

public interface TeacherService {

	//개인정보 수집창
	public String createTeacherHtml();
	
	//강사신청 정보 입력창 이동
	public Map<String, String> teacherInfoOk(Map<String, String> step);
	
	//강사신청 정보 입력창 HTML
	public String createInsertHtml();
	
}
