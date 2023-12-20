package com.culture.demo.mapper;

import java.util.List;

import com.culture.demo.domain.NoticeDTO;

public interface NoticeSearchMapper {

	// 공지사항 / 이벤트 여부 가져오기
	List<NoticeDTO> getClCd();
}
