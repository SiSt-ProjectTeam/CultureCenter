package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassDTO {
	
	// 지점 / branch
	private int branch_id;			// 지점 아이디
	private int branch_tp_id;		// 지점 유형 아이디
	private String branch_nm;		// 지점 이름
	
	// 지점유형 / branch_type	
	private String branch_tp;		// 지점 유형
	
}
