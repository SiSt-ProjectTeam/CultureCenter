package com.culture.demo.domain;

import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
	
	private int member_sq;
	private String profil_img; //강사 프로필 사진
	private String teacher_nm;
	private String teacher_intro;
	
	//submit, save, delete.ajax
	private String cnt;
	private String step;
	private String prinfClctAgrYn; 				//개인정보 수집동의 필수
	private String carrInfoClctAgrYn;			//개인정보 수집동의 선택
	
	private int tcCdNo; 
	private int appDt;
	private int tcTpCd; 
	private String tcNm;
	private int lscCd; 
	private int bday;
	private int hpStNo; 						//휴대폰 시작 번호 010
	private int hpMidNo; 						//휴대폰 두번째 번호
	private int hpLastNo;						//휴대폰 마지막 번호
	private String email; 						//이메일 아이디
	private String emailAddrCd; 				//이메일 주소
	private int pstno; 							//우편번호
	private String addr; 						//주소
	private String dtlAddr; 					//상세주소
	private String selfIntrdnCont; 				//자기소개 
	
	private Map<String, String> tceduList; 		//전공
	private int schlClCd; 					
	private int grdtClCd; 					
	private int yy; 						
	private String schlNm; 						//대학교 이름
	private String mjrNm; 						//전공명
	
	private Map<String, String> tchistList; 	//경력사항
	private String histPlcNm;
	private int histStDt; 						//경력시작
	private int histEndDt; 						//경력끝
	
	private Map<String, String> tcawrdList; 	//수상내역
	private Map<String, String> tcauthctfList;	//자격증
	private String issueAgncNm;
	private String issueNm;
	private int issueDt;
	
	private int frstHopeBrchCd; 				//첫번째 희망지점코드
	private int frstHopeBrchCdNm; 				//첫번째 희망지점
	private int secHopeBrchCd; 					//두번째 희망지점코드
	private int secHopeBrchCdNm; 				//두번째 희망지점
	private int frstLrclsCtegryCd; 				//첫번째 희망강좌코드 대분류
	private int frstLrclsCtegryCdNm; 			//강좌대분류명
	private int frstMdclsCtegryCd; 				//첫번째 희망강좌코드 중분류
	private int secLrclsCtegryCd; 				//두번째 희망강좌코드 대분류
	private int secLrclsCtegryCdNm; 			//강좌대분류명
	private int secMdclsCtegryCd; 				//두번째 희망강좌코드 중분류
	private int hopeDaywVal;
	private int hopeDaywValNm; 					//희망요일
	private int hopeStHh; 						//희망시작시간
	private int hopeStMi; 						//희망시작분
	private int hopeEndHh; 						//희망종료시간
	private int hopeEndMi; 						//희망종료분
	private int lectIntrdnCont; 				//강좌소개
		
	private CommonsMultipartFile file;
}
