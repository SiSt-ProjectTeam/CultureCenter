package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FrmUpdateDTO {
	
	private String csrfPreventionSalt;	// CSRF(Cross-Site Request Forgery) 공격을 방어하기 위한 토큰이 저장된 필드입니다. 
										// 이는 서버에 대한 요청이 유효하고 안전한 것임을 확인하는 데 사용됩니다.
	
	private String atlctRsvNo;			// 주문 번호
	private String actlAtlctNpleSeqno;	// 환불하고자 하는 수강자 arr
	private String rfndRsnCd;			// 환불 이유 코드
	private String rfndClCd;			// 환불 처리 유형 코드
	private String rfndStatCd;			// 환불 상태 코드 (30/50/0)
	private String rfndChnlCd;			// 환불 채널 코드 (O)

} // class
