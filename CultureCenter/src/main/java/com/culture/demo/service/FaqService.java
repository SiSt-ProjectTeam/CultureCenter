package com.culture.demo.service;

import java.util.List;
import com.culture.demo.domain.FaqDTO;

public interface FaqService {
	
	//자주 묻는 질문 조회 + 검색
	public List<FaqDTO> getSeacrchFaqList(FaqDTO faqDTO, int faq_tp_id, String q);
	
	//자주 묻는 질문 html
	public String createFaqHtml(FaqDTO dto);
	
}
