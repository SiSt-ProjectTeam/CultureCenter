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
	
}
