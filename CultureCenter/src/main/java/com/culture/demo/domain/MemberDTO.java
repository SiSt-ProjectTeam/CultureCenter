package com.culture.demo.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
	
	    private int member_sq;
		private String id;
		private String pw;
	    private String name;
	    private String email;
	    private String phone;
	    private LocalDate birth_dt;
	    private String addr;
	    private String car_no;
	    
	    private int branch_id;
	    private int point;
	    private int basket_cnt;
	    private int order_class_cnt;
	    private int late_class_cnt;
	    private int complete_class_cnt;
	    private int review_cnt;
	    private int faq_cnt;	    
	    
} // class
