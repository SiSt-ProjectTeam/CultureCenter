package com.culture.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.culture.demo.domain.FaqDTO;
import com.culture.demo.mapper.FaqMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class FaqServiceImpl implements FaqService{

	private FaqMapper faqMapper;

	//검색어 + 목록
	@Override
	public List<FaqDTO> getSeacrchFaqList(int faq_tp_id, String q) {
		log.info("> FaqServiceImpl.getFaqList ...");
		return this.faqMapper.getSeacrchFaqList(faq_tp_id, q);
	}
	
	//전체 목록
	@Override
	public List<FaqDTO> getFaqAllList(int faq_tp_id) {
		log.info("> FaqServiceImpl.getFaqList ...");
		return this.faqMapper.getFaqAllList(faq_tp_id);
	}	

	//선택 목록
	@Override
	public List<FaqDTO> getFaqList(int faq_tp_id) {
		log.info("> FaqServiceImpl.getFaqList ...");
		return this.faqMapper.getFaqList(faq_tp_id);
	}
	
	@Override
	public String createFaqHtml(int faq_tp_id, String q) {
		log.info("> FaqServiceImpl.createFaqHtml......");

		StringBuilder html = new StringBuilder();
		List<FaqDTO> faqList = new ArrayList<FaqDTO>();

		if (faq_tp_id > 0) {
			//faqList = this.getSeacrchFaqList(faq_tp_id, q);
			faqList = this.getFaqList(faq_tp_id);
		} else if (faq_tp_id == 0) {
			faqList = this.getFaqAllList(faq_tp_id);
		}
		
		html.append("<div class=\"faq-container\">");
		
		for (FaqDTO faq : faqList) {
			html.append("<div class=\"list_div\" data-tot-cnt=\"27\">\r\n");
			html.append("    <a class=\"list\" href=\"javascript:\" role=\"button\" data-tot-cnt=\"\">\r\n");
			html.append("        <p class=\"f_body1\">\r\n");
			html.append("            ").append(faq.getFaq_title()).append("</p>\r\n");
			html.append("    </a>\r\n");
			html.append("    <!-- 상세영역 -->\r\n");
			html.append("    <div class=\"hide_con\">\r\n");
			html.append("        <p class=\"f_body2\">").append(faq.getFaq_content()).append("</p>\r\n");
			html.append("    </div>\r\n");
			html.append("    <!-- 상세영역 -->\r\n");
			html.append("</div>");
		}
		
		html.append("</div>");

		return html.toString();
	}



}
