package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeducationDTO {
	
	private int member_sq;
	private int university_tp_id;
	private String university_nm;
	private String major;
	private int graduate_state_id;
	private String graduate_year;
	
	private String university_tp;
	private String graduate_state;
	
	
	private int schlClCd; 						//학교선택(고등학교,전문대 등)
	private int grdtClCd; 						//졸업상태선택
	private int yy; 							//졸업일
	private String schlNm; 						//대학교 이름
	private String mjrNm; 						//전공명	
	
}
