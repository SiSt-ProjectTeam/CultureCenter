package com.culture.demo.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
	
	private int member_sq;
	private int detail_class_sq;
	private Date reg_dt;
	
    private String start_time;
    private String end_time;
    private int class_cnt;
    private int open_smst_id;
    private String open_year;
    private String schedule_start_dt;
    private String schedule_end_dt;
    private int class_fee;
    private int ex_charge;
    private String smst_nm;
    
    private int class_id;
    private String class_nm;
    
    private int class_st_id;
    private String class_st;
    
    private int branch_id;
    
    private String branch_nm;
    private String teacher_nm;
    
    // 수업요일
    private String day; // 월~일
    
    // 삭제하기 위한 data
    private String type; // all, check ..
    private String cartSeqno; // 삭제하려하는 장바구니 세부강좌번호
    
}
