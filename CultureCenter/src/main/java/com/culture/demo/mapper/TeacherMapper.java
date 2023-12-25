package com.culture.demo.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.culture.demo.domain.BranchDTO;
import com.culture.demo.domain.TawardsDTO;
import com.culture.demo.domain.TcareerDTO;
import com.culture.demo.domain.TcertificateDTO;
import com.culture.demo.domain.TeacherDTO;
import com.culture.demo.domain.TeducationDTO;

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
	
	//APPLY_CLASS 테이블에 중복 지원 확인
	public String duplicateApplicaionCheck(int memberSq);
	
	//TEACHER 테이블에 중복 지원 확인
	public String duplicateTeacherCheck(int memberSq);
	
	//APPLY_CLASS에 없고 TEACHER에 있는지 확인
	public String noApplyClassOkTeacher(int memberSq);
	
	//임시저장 테이블에 저장되어 있는 회원인지 확인
	public String temporaryOk(int memberSq);
	
	//APPLY_CLASS 테이블과 TEACHER 테이블 동시에 있는 회원인지 확인
	public String applyTeacher(int memberSq);
	
	//임시저장 테이블에 임시저장
	public int saveTeacherInfo(@Param("saveDataList") Map<String, Object> saveDataList, @Param("teacherImg") String teacherImg, @Param("memberSq") int memberSq);

	//임시저장 테이블 삭제
	public void deleteTeacherInfo(int memberSq);
	
	//임시저장 글 불러오기
	public TeacherDTO saveOpen(int memberSq);
	public TawardsDTO saveAwardsOpen(int memberSq);
	public TcareerDTO saveCareerOpen(int memberSq);
	public TeducationDTO saveEducationOpen(int memberSq);
	public TcertificateDTO saveCertificateOpen(int memberSq);
}
