package com.culture.demo.service;

import java.sql.SQLException;
import java.util.List;

import com.culture.demo.domain.FrmSearchDTO;
import com.culture.demo.domain.NoticeDTO;

public interface NoticeService {
	
	// 메인페이지 공지 정보 가져오기
	List<NoticeDTO> getMainNoticeList() throws Exception;
	
	// 공지사항/이벤트 ajax
	String noticeHTML(FrmSearchDTO frmSearchDTO) throws SQLException, ClassNotFoundException;
}
