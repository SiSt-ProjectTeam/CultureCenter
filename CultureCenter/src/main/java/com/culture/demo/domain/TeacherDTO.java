package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
	
	private int member_sq;
	private String profil_img;
	private String teacher_nm;
	private String teacher_intro;
	
	private int cnt;
	private String prinfClctAgrYn; //개인정보 수집동의 필수 Y
	private String carrInfoClctAgrYn;//개인정보 수집동의 선택
	
	//delete.ajax
	private int tcCdNo;
	private int appDt; //20231213
	private int tcTpCd; //1
	private int lscCd; //01
	private int htStNo; //010
	
	//
	private String ssoTkn;
	private String acesTkn;
	private String rnwTkn;
	private String onlCstTpC;
	private String frnYn;
	private String rspClac;
	private String rspC;
	private String rspDtc;
	private String rspMsgCn;
	private String rtnUrl;
	
	
	/*
	private int businessTpId;		// 개인사업자 유형id
	private String businessTp;		// 개인사업자유형
	private String taxFreeAv;		// 면제사업자 유무
	private String profilImg;		// 프로필사진
	private String teacherNm;		// 강사명
	private String tel;				// 전화번호
	private Date birthDt;			// 생년월일
	private String lunarSolar;		// 음력양력
	private String email;			// 이메일
	private String teacherIntro;	// 강사설명
	private String zipcode;			// 우편번호
	private String address;			// 주소
	private String addrDetail;		// 상세주소
	*/
}
