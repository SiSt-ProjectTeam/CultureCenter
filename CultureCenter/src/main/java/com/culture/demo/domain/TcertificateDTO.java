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
	
}
