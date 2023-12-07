package com.culture.demo.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtlctDTO {
	    
	    // 주문  class_order
	    private int order_sq;					// 주문번호
	    private String order_dt;				// 결제일
	    private int order_amt;					// detail - 결제금액
	    private int add_point;					// detail - 적립된 포인트
	    private int total_amt;					// detail - 총 결제금액	

		// 세부강좌 테이블  detail_class
		private int detail_class_sq;			// 세부 강좌 번호
		private String class_schedule;			// 강의 기간 / 요일 / 시간 / 횟수
	    
		// 학기별 강좌 테이블  class_by_semester
		private String open_year;				// 개강 연도
		private int open_smst_id;				// 개강 학기 아이디
		private String smst_nm;					// 개강 학기명
		private int class_fee;					// 수강료
		private int ex_charge;					// 재료비/대여료
	    
	    // 강좌 테이블  class
		private String class_nm;				// 강좌 이름
		
		// 회원 테이블 member
		private int teacher_sq;				 	// 강사 회원 번호
		private String teacher_nm;				// 강사 이름
		
	    // 지점 branch
	 	private int branch_tp_id;				// 지점 유형 아이디
	 	private String branch_nm;				// 지점명

	 	private List<AtlctPersonalDTO> personalList;
} // class
