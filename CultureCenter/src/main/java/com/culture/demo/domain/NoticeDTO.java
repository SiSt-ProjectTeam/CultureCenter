package com.culture.demo.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO {
	
	private int notice_sq;			// 게시물번호
	private int branch_id;			// 지점아이디
	private String mustread_fl;		// 필독여부
	private LocalDate write_dt;		// 작성일자
	private String posting_title;	// 게시물 제목
	private String posting_content;	// 게시물 내용
	private String notice_event;	// 공지사항 내용
	
}