package com.culture.demo.service;


public interface TeacherService {

	//개인정보 수집창
	public String createTeacherHtml();
	
	//강사신청 정보 입력창
	public String teacherInfoOk(int cnt, String prinfClctAgrYn, String carrInfoClctAgrYn);
	
}
