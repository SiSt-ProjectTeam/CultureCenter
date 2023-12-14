package com.culture.demo.mapper;

import java.sql.SQLException;
import java.util.List;

import com.culture.demo.domain.NoticeDTO;

public interface NoticeMapper {

	// 1. 메인페이지 공지사항 리스트
	public List<NoticeDTO> selectMainNoticeList() throws ClassNotFoundException, SQLException;
	
}
