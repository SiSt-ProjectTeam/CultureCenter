package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TcareerDTO {
	
	private int member_sq;
	private String publisher;
	private String start_dt;
	private String end_dt;
	
}
