package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FrmUpdateDTO {
	
	private String csrfPreventionSalt;
	private String atlctRsvNo;
	private String actlAtlctNpleSeqno;
	private String rfndRsnCd;
	private String rfndClCd;
	private String rfndStatCd;
	private String rfndChnlCd;

} // class
