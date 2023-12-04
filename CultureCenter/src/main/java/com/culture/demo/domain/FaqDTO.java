package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaqDTO {
	
	private int faq_sq;				//자주하는문의 아이디
	private int faq_tp_id;			//문의유형아이디
	private int faq_tp;				//문의유형
	private String faq_title;		//문의제목
	private String faq_content;		//문의내용

}
