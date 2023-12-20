package com.culture.demo.service;

import java.util.List;

import com.culture.demo.domain.NoticeDTO;

public interface NoticeSearchService {

	// 공지사항 / 이벤트 여부 가져오기
	List<NoticeDTO> getClCd();
}
