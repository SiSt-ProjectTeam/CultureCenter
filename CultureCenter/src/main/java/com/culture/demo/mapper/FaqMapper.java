package com.culture.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.culture.demo.domain.FaqDTO;

public interface FaqMapper {

	//전체 목록 조회
	public List<FaqDTO> getFaqAllList(int faq_tp_id);
	
	//선택 목록 조회
	public List<FaqDTO> getFaqList(int faq_tp_id);
	
	//자주 묻는 질문 조회 + 검색어
	public List<FaqDTO> getSeacrchFaqList(@Param("faq_tp_id") int faq_tp_id, @Param("q") String q);
	
	//자주 묻는 질문 html
	public String createFaqHtml(@Param("faq_tp_id") int faq_tp_id, @Param("q") String q);
}
