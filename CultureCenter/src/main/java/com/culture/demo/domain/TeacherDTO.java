package com.culture.demo.domain;

import java.util.List;

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

	private List<TeducationDTO> tceduList;		//강사 학력
	private List<TcareerDTO> tchistList; 		//경력사항
	private List<TawardsDTO> tcawrdList; 		//수상내역
	private List<TcertificateDTO> tcauthctfList;//자격증

	private String imgpre;
	private int tcCdNo; 						//신청한순서번호
	private String prinfClctAgrYn; 				//개인정보 수집동의 필수
	private String carrInfoClctAgrYn;			//개인정보 수집동의 선택	
	private String appDt;						//신청일 SYSDATE
	private int tcTpCd; 						//사업자여부
	private String txfrBizprYn;					//면세사업자여부
	private String tcNm;						//강사 이름
	private int lscCd; 							//양음력
	private String bday;						//생년월일
	private String hpStNo; 						//휴대폰 시작 번호 010
	private String hpMidNo; 					//휴대폰 두번째 번호
	private String hpLastNo;					//휴대폰 마지막 번호
	private String email; 						//이메일 아이디
	private String emailAddrCd; 				//이메일 주소
	private int pstno; 							//우편번호
	private String addr; 						//주소
	private String dtlAddr; 					//상세주소
	private String selfIntrdnCont; 				//자기소개 
	private int frstHopeBrchCd; 				//첫번째 희망지점코드
	private String frstHopeBrchCdNm; 			//첫번째 희망지점
	private int secHopeBrchCd; 					//두번째 희망지점코드
	private String secHopeBrchCdNm; 			//두번째 희망지점
	private int frstLrclsCtegryCd; 				//첫번째 희망강좌코드 대분류
	private String frstLrclsCtegryCdNm; 		//강좌대분류명
	private int frstMdclsCtegryCd; 				//첫번째 희망강좌코드 중분류
	private int secLrclsCtegryCd; 				//두번째 희망강좌코드 대분류
	private String secLrclsCtegryCdNm; 			//강좌대분류명
	private int secMdclsCtegryCd; 				//두번째 희망강좌코드 중분류	
	private String hopeDaywVal;					//희망요일코드
	private String hopeDaywValNm; 				//희망요일이름
	private int hopeStHh; 						//희망시작시간
	private int hopeStMi; 						//희망시작분
	private int hopeEndHh; 						//희망종료시간
	private int hopeEndMi; 						//희망종료분
	private String lectIntrdnCont; 				//강좌소개		
	
	private CommonsMultipartFile file;			//프로필 사진 업로드
	private String filesrc;						
	
}
