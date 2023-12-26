package com.culture.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culture.demo.domain.InquiryDTO;
import com.culture.demo.mapper.MypageInquiryMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class InquiryServiceImpl implements InquiryService{

	@Autowired
	private MypageInquiryMapper mypageInquiryMapper;
	
	@Override
	public int deleteInquiry(int personal_faq_sq) throws SQLException, ClassNotFoundException {
		log.info("InquiryServiceImpl.java > deleteInquiry...");
		try {
			
			mypageInquiryMapper.deleteInquiry(personal_faq_sq);
            return 1; // 성공 시 1 반환
        } catch (Exception e) {
            log.error("Error cancelling waiting: " + e.getMessage());
            return 0; // 실패 시 0 반환
        }
    }
	
	@Override
	public String createInquiryListHtml(int member_sq, InquiryDTO params) throws SQLException, ClassNotFoundException {
		log.info("InquiryServiceImpl.java > createInquiryListHtml...");

		List<InquiryDTO> list = mypageInquiryMapper.getInquiryList(member_sq, params);
		
		StringBuilder html = new StringBuilder();
		int totCnt = mypageInquiryMapper.totCnt(member_sq, params);
		
		if(list.isEmpty() || list.size() == 0) {
			html.append(" <div class=\"no_srch_area no_pb\" data-tot-cnt=\"0\">\r\n"
					+ "			<div class=\"no_srch_div\">\r\n"
					+ "				<p class=\"txt f_h2\">\r\n"
					+ "					<span class=\"normal_value\">문의글이 없습니다.</span>\r\n"
					+ "				</p>\r\n"
					+ "			</div>\r\n"
					+ "		</div>");				
		}else {
			for(InquiryDTO InquiryDTO : list) {
				html.append("	<a href=\"javascript:\" class=\"notice_list typeB\" data-tot-cnt="+totCnt+" onclick=\"mypageInquiryCtrl.detail2(this , "+ InquiryDTO.getPersonal_faq_sq() +");\">\r\n"
						+ "		<div class=\"title\">\r\n"
						+ "			<span class=\"important\">"+ InquiryDTO.getFaq_status()+"</span>\r\n"
						+ "				<p class=\"txt\">"+InquiryDTO.getFaq_title()+"</p>\r\n"
						+ "		</div>\r\n"
						+ "		<div class=\"type_div\">\r\n"
						+ "			<p class=\"type\">"+InquiryDTO.getBranch_nm()+"</p>\r\n"
						+ "			<p class=\"type\">"+InquiryDTO.getFaq_tp()+"</p>\r\n"
						+ "			<p class=\"type\">"+InquiryDTO.getFaq_dt()+"</p>\r\n"
						+ "		</div>\r\n"
						+ "	</a>\r\n");			
			}
		}
		return html.toString();
	}

	@Override
	public int insertInquiry(InquiryDTO params) throws SQLException, ClassNotFoundException {
		
		log.info("InquiryServiceImpl.java > insertInquiry...");
	    try {   
	        int rowCount = mypageInquiryMapper.insertInquiry(params);
	        return rowCount; // 성공 시 영향을 받은 행의 수 반환
	    } catch (Exception e) {
	        log.error("Error inserting inquiry: " + e.getMessage());
	        return 0; // 실패 시 0 반환
	    }
	}

	@Override
	public List<InquiryDTO> getInquiryView(int member_sq, int personal_faq_sq) throws SQLException, ClassNotFoundException {
		
		log.info("InquiryServiceImpl.java > createInquiryViewHtml...");
		
        List<InquiryDTO> list = null;
		try {
			list = mypageInquiryMapper.getInquiryView(member_sq, personal_faq_sq);
		} catch (SQLException e) {
			System.out.println("licontroller view > list error : " + list);
			e.printStackTrace();
		}
		
		return list;
	}
	
}
