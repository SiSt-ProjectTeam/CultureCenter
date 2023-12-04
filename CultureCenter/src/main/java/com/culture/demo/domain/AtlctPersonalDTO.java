package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtlctPersonalDTO {
	
	// 수강내역  order_detail
	private int orderDetailSq;				// 수강내역 번호 
	private String childrenNm;				// 수강자 이름
	private String cancelDt;				// 취소(환불)일시
	private int cancelAmt;					// 취소(환불)금액
	private int receiptStatusId;			// 접수상태 아이디
	private int cancelReasonId;				// 결제취소사유 아이디
	
	// 결제취소사유 cancel_reason
	private String cancelReason;			// detail - 결제취소사유
	
	// 수강내역 접수상태  order_receipt_status
	private String receiptStatus;			// 접수상태
	
	// 학기별 강좌 테이블  class_by_semester
	private int classFee;					// 수강료
	private int exCharge;					// 재료비/대여료
	
}
