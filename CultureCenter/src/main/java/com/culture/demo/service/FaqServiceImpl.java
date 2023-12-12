package com.culture.demo.service;

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
	
	//자주 묻는 질문 조회 + 검색
	@Override
	public List<FaqDTO> getSeacrchFaqList(FaqDTO faqDTO, int faq_tp_id, String q) {
		log.info("> FaqServiceImpl.getSeacrchFaqList ...");
		return this.faqMapper.getSeacrchFaqList(faqDTO, faq_tp_id, q);
	}


	//자주 묻는 질문 html
	@Override
	public String createFaqHtml(FaqDTO dto) {
		log.info("> FaqServiceImpl.createFaqHtml......");

		StringBuilder html = new StringBuilder();
		List<FaqDTO> faqList = getSeacrchFaqList(dto, dto.getClCd(), dto.getQ());

		if (faqList.isEmpty()) { 

			html.append("<div class=\"page_cont_area\" data-tot-cnt=\"0\">\r\n");
			html.append("   <div class=\"inner\">\r\n");
			html.append("       <div class=\"no_srch_area no_pb\">\r\n");
			html.append("           <div class=\"no_srch_div\">\r\n");
			html.append("               <p class=\"txt f_h2\"><span class=\"srch_value\">").append(dto.getQ()).append("</span>에 대한<br>검색결과가 없어요.</p>\r\n");
			html.append("           </div>\r\n");
			html.append("       </div>\r\n");
			html.append("   </div>\r\n");
			html.append("</div>");
			
		} else{

			for (FaqDTO faq : faqList) {
				
				html.append("<div class=\"list_div\" data-tot-cnt=\"" +  faq.getTot_cnt() +  "\">\r\n");
				html.append("    <a class=\"list\" href=\"javascript:\" role=\"button\" data-tot-cnt=\"\">\r\n");
				html.append("        <p class=\"f_body1\">\r\n");
				html.append("            ").append(faq.getFaq_title()).append("</p>\r\n");
				html.append("    </a>\r\n");
				html.append("    <div class=\"hide_con\">\r\n");
				html.append("        <p class=\"f_body2\">").append(faq.getFaq_content()).append("</p>\r\n");
				html.append("    </div>\r\n");
				html.append("</div>\r\n");
			}
		}
		
		return html.toString();
	}
}
