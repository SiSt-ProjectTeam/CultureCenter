package com.culture.demo.domain;

import java.time.LocalDate;
import java.util.List;

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
	    private String m_birth_dt; //2023.12.12 형식
	    private String addr;
	    private String car_no;
	    
	    private int branch_id;
	    private String branch_nm;
	    private int point;
	    private int basket_cnt;
	    private int order_class_cnt;
	    private int late_class_cnt;
	    private int complete_class_cnt;
	    private int review_cnt;
	    private int faq_cnt;	    
	    
	    // 동반수강자 자녀list
	    private List<ChildrenDTO> childrenList; 
} // class
