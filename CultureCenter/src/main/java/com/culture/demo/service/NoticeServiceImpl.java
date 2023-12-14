package com.culture.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

}
