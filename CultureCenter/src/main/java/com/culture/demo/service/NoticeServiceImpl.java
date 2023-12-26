package com.culture.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.culture.demo.domain.FrmSearchDTO;
import com.culture.demo.domain.NoticeDTO;
import com.culture.demo.mapper.NoticeMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class NoticeServiceImpl implements NoticeService {

	private NoticeMapper noticeMapper;
	
	@Override
	public List<NoticeDTO> getMainNoticeList() throws Exception {
		log.info("> NoticeServiceImpl.getMainNoticeList...");
		System.out.println();
		List<NoticeDTO> dto = this.noticeMapper.selectMainNoticeList();
		System.out.println();
		
		return dto;
	}
	
	// 공지사항/이벤트 ajax
	@Override
	public String noticeHTML(FrmSearchDTO frmSearchDTO) throws SQLException, ClassNotFoundException {
		log.info("> NoticeServiceImpl.noitceHTML...");
			
		int branch_id = frmSearchDTO.getBrchCd();		
		List<NoticeDTO> list = this.noticeMapper.getNoticeList(branch_id, frmSearchDTO); 
		
		StringBuilder html = new StringBuilder();		
		if (list.isEmpty()) {
			html.append("<div class=\"no_srch_area no_pb\" data-tot-cnt=\"0\">\r\n");
			html.append("	<div class=\"no_srch_div\">\r\n");
			html.append("		<p class=\"txt f_h2\">\r\n");
			html.append("			<span class=\"srch_value\">검색결과가 없습니다.</span>\r\n");
			html.append("		</p>\r\n");
			html.append("	</div>\r\n");
			html.append("</div>");
		}else {
			for(NoticeDTO dto : list ) {
				html.append("<a href=\"javascript:$('#notcSeqno').val('"+dto.getNotice_sq()+"'); $('#frmSearch').submit();\" class=\"notice_list typeB\" data-tot-cnt=\""+dto.getTot_cnt()+"\" data-seq=\""+dto.getNotice_sq()+"\">\r\n");
				html.append("	<div class=\"title\">\r\n");
				html.append("		<p>"+dto.getPosting_title()+"</p>\r\n");
				html.append("	</div>\r\n");
				html.append("	<div class=\"type_div\">\r\n");
				html.append("		<p class=\"type\">"+dto.getWrite_dt()+"</p>\r\n");
				html.append("	</div>\r\n");
				html.append("</a>");
			} // for
			
		} // if
		
		return html.toString();		
 	} // noticeHTML
	
	
	// 공지사항 상세 정보 가져오기
	public NoticeDTO getNotice(int notcSeqno) {
		log.info("> NoticeServiceImpl.getNotice...");		
		NoticeDTO noticeDTO = this.noticeMapper.getNotice(notcSeqno);
		
		return noticeDTO;
	};

} // NoticeServiceImpl
