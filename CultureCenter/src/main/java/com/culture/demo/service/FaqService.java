package com.culture.demo.service;

import java.util.List;

import com.culture.demo.domain.FaqDTO;

public interface FaqService {

	//전체 목록 조회
	public List<FaqDTO> getFaqAllList(int faq_tp_id);
	
	//선택 목록 조회
	public List<FaqDTO> getFaqList(int faq_tp_id);
	
	//자주 묻는 질문 조회 + 검색어
	public List<FaqDTO> getSeacrchFaqList(int faq_tp_id, String q);
	
	//자주 묻는 질문 html
	public String createFaqHtml(int faq_tp_id, String q);
	
}
