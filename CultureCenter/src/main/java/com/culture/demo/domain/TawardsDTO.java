package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TawardsDTO {

	private int member_sq;
	private String publisher;
	private String awards_history;
	private String awards_dt;
	
}
