package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionDTO {
	
	private int class_id;
	private int detail_class_sq;
	private int ex_charge;
	private String mon;
	private String tue;
	private String wed;
	private String thu;
	private String fri;
	private String sat;
	private String sun;
	private String start_time;	
	private String end_time;	
	private int class_cnt;
	private String schedule_start_dt;	
	private String schedule_end_dt;
	private String reception_start_dt;	
	private String reception_end_dt;		
	
	private String day;
	private String classDuration;
	private String classTime;
	private String recepDuration;
	
}
