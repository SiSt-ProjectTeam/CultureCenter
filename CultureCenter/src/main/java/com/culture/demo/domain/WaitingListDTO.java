package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaitingListDTO {
	
	private String branch_nm;
	private String member_nm;
	private String class_nm;
	private String teacher_nm;
	private String smst_nm;

	private String schedule_start_dt;
	private String schedule_end_dt;
	private String start_time;
	private String end_time;
	private String week;

	private String children_nm;
	private String class_st;

	private int late_sq;
	private int late_sq_rank;
	private String member_sq;
	private int class_fee;
	private int ex_charge;   
	    
} // class
