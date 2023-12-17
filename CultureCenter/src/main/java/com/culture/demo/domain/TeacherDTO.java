package com.culture.demo.domain;

import java.util.List;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.fasterxml.jackson.annotation.JsonRawValue;
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

	
	//강사 지원서 제출 form data
	@JsonRawValue
	private List<TceduList> tceduList;		//강사 학력
	
	@JsonRawValue
	private List<TchistList> tchistList; 		//경력사항
	
	@JsonRawValue
	private List<TcawrdList> tcawrdList; 		//수상내역
	
	@JsonRawValue
	private List<TcauthctfList> tcauthctfList; 	//자격증
		
	private int tcCdNo; 						//신청한순서번호
	private String prinfClctAgrYn; 				//개인정보 수집동의 필수
	private String carrInfoClctAgrYn;			//개인정보 수집동의 선택	
	private int appDt;							//신청일 SYSDATE
	private int tcTpCd; 						//사업자여부
	private String txfrBizprYn;					//면세사업자여부
	private String tcNm;						//강사 이름
	private int lscCd; 							//양음력
	private int bday;							//생년월일
	private int hpStNo; 						//휴대폰 시작 번호 010
	private int hpMidNo; 						//휴대폰 두번째 번호
	private int hpLastNo;						//휴대폰 마지막 번호
	private String email; 						//이메일 아이디
	private String emailAddrCd; 				//이메일 주소
	private int pstno; 							//우편번호
	private String addr; 						//주소
	private String dtlAddr; 					//상세주소
	private String selfIntrdnCont; 				//자기소개 
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
	private CommonsMultipartFile file;			//프로필 사진 업로드
	private String filesrc;						//수정
	
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TceduList{				//강사 학력		
		private int schlClCd; 						//학교선택(고등학교,전문대 등)
		private int grdtClCd; 						//졸업상태선택
		private int yy; 							//졸업일
		private String schlNm; 						//대학교 이름
		private String mjrNm; 						//전공명		
	}
	
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TchistList{					//경력사항		
		private String histPlcNm;					//회사명
		private int histStDt; 						//경력시작
		private int histEndDt; 						//경력끝
	}
	
		
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TcawrdList{				//수상내역		
		private String issueAgncNm;					//발행기관
		private String issueNm;						//이름
		private int issueDt;						//취득일, 수상일
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TcauthctfList{				//수상내역		
		private String issueAgncNm;					//발행기관
		private String issueNm;						//이름
		private int issueDt;						//취득일, 수상일
	}
	
}
