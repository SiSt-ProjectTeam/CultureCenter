package com.culture.demo.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.culture.demo.domain.FrmSearchDTO;
import com.culture.demo.domain.NoticeDTO;

public interface NoticeMapper {

	// 1. 메인페이지 공지사항 리스트
	public List<NoticeDTO> selectMainNoticeList() throws ClassNotFoundException, SQLException;
	
	// 공지사항/이벤트 목록 리스트
	public List<NoticeDTO> getNoticeList(@Param("branch_id") int branch_id, @Param("dto") FrmSearchDTO frmSearchDTO);
}
