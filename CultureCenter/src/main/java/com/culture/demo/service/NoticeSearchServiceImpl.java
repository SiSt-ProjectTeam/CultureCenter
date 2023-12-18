package com.culture.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.culture.demo.domain.NoticeDTO;
import com.culture.demo.mapper.NoticeSearchMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class NoticeSearchServiceImpl implements NoticeSearchService {
	
	private NoticeSearchMapper noticeSearchMapper;
	public List<NoticeDTO> getClCd() {
	log.info("> LecSearchServiceImpl.getBranch() 호출");
		return noticeSearchMapper.getClCd();
	}

}
