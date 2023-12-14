package com.culture.demo.domain;

import java.util.Map;

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
	
	
	private String cnt;
	private String step;
	private String prinfClctAgrYn; //개인정보 수집동의 필수 Y
	private String carrInfoClctAgrYn;//개인정보 수집동의 선택
	
	//submit, save, delete.ajax
	private int tcCdNo; //041989
	private int appDt; //20231213
	private int tcTpCd; //1
	private String tcNm;
	private int lscCd; //01
	private int bday; //20231120
	private int hpStNo; //010
	private int hpMidNo; //1234
	private int hpLastNo;
	private String email; //SIST 
	private String emailAddrCd; //sist.com
	private int pstno; //우편번호
	private String addr; //주소
	private String dtlAddr; // 상세주소 ~호
	private String selfIntrdnCont; // 자기소개 
	
	private Map<String, String> tceduList; //전공
	private int schlClCd; //0
	private int grdtClCd; //2021
	private int yy; //2021
	private String schlNm; //롯데대학교
	private String mjrNm; //롯데전공
	
	private Map<String, String> tchistList; //경력사항
	private String histPlcNm;
	private int histStDt; //경력시작
	private int histEndDt; //경력끝
	
	private Map<String, String> tcawrdList; //수상내역
	private String issueAgncNm;
	private String issueNm;
	private int issueDt;
	
	private Map<String, String> tcauthctfList;//자격증
	//private String issueAgncNm;
	//private String issueNm;
	//private int issueDt;
	
	private int frstHopeBrchCd; //:"0001",
	private int frstHopeBrchCdNm; //:"본점",
	private int secHopeBrchCd; //0028",
	private int secHopeBrchCdNm; //건대스타시티점",
	private int frstLrclsCtegryCd; //"01",
	private int frstLrclsCtegryCdNm; //"성인강좌",
	private int frstMdclsCtegryCd; //"0103",
	private int secLrclsCtegryCd; //"01",
	private int secLrclsCtegryCdNm; //"성인강좌",
	private int secMdclsCtegryCd; //"0104",
	private int hopeDaywVal; //":"1",
	private int hopeDaywValNm; //"월요일",
	private int hopeStHh; //"01",
	private int hopeStMi; //"00",
	private int hopeEndHh; //"02",
	private int hopeEndMi; //"10",
	private int lectIntrdnCont; //:"김롯데 강좌 소개입니다."}
		
}
