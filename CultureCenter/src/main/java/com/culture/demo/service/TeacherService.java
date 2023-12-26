package com.culture.demo.service;

import java.util.Map;

import com.culture.demo.domain.TawardsDTO;
import com.culture.demo.domain.TcareerDTO;
import com.culture.demo.domain.TcertificateDTO;
import com.culture.demo.domain.TeacherDTO;
import com.culture.demo.domain.TeducationDTO;

public interface TeacherService {

	//개인정보 수집 동의 여부
	public Map<String, Object> clausePrivacyOk(Map<String, Object> clausePrivacyData, int memberSq);
	
	//강사신청 정보입력 html
	public String createTeacherInfoHtml();
	
	//강사신청 제출
	public int submitTeacherInfo(Map<String, Object> submitDataString, String teacherImg, int memberSq) throws Exception;

	//강사신청완료 시 step3 html
	public String createSubmitHtml();
	
	//제휴사 신청 페이지
	public String createCooperationHtml();
	//apply_class에 강사신청정보가 있는지 확인
	public Map<String, String> applyClassCheck(int memberSq);
	
	//teacher에 강사신청정보가 있는지 확인
	public Map<String, String> teacherCheck(int memberSq);
	
	//apply_class와 teacher를 동시에 조회해 teacher 에만 있는지 확인
	public String applyTeacherCheck(TeacherDTO teacherDTO, int memberSq);
	
	//임시저장
	public Map<String, Object> saveTeacherInfo(Map<String, Object> saveDataList, String teacherImg, int memberSq) throws Exception;
	
	//강사신청서 작성 중 삭제
	public Map<String, Object> deleteOk(int memberSq);
	
	//임시저장 글 가져오기
	public TeacherDTO getSaveTeacherInfo(int memberSq);
	public TawardsDTO getSaveTeacherAwardsInfo(int memberSq);
	public TcareerDTO getSaveTeachercareerInfo(int memberSq);
	public TeducationDTO getSaveTeacherEducationInfo(int memberSq);
	public TcertificateDTO getSaveTeacherCertificateInfo(int memberSq);
	
}
