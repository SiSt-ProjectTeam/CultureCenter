package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TcertificateDTO {
	
	private int member_sq;
	private String publisher;
	private String certificate_nm;
	private String acquisition_dt;
	
	private String issueAgncNm;					//발행기관
	private String issueNm;						//이름
	private int issueDt;						//취득일, 수상일
	
}
