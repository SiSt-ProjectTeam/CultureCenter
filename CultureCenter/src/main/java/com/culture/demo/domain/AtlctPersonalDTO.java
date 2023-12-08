package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtlctPersonalDTO {
	
	// 수강내역  order_detail
	private int order_detail_sq;				// 수강내역 번호 
	private String children_nm;				// 수강자 이름
	private String cancel_dt;				// 취소(환불)일시
	private int cancel_amt;					// 취소(환불)금액
	private int receipt_status_id;			// 접수상태 아이디
	private int cancel_reason_id;				// 결제취소사유 아이디
	
	// 결제취소사유 cancel_reason
	private String cancel_reason;			// detail - 결제취소사유
	
	// 수강내역 접수상태  order_receipt_status
	private String receipt_status;			// 접수상태
	
	// 학기별 강좌 테이블  class_by_semester
	private int class_fee;					// 수강료
	private int ex_charge;					// 재료비/대여료
	
}
