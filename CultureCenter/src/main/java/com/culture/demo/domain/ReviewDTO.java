package com.culture.demo.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReviewDTO {
   // 리뷰테이블
   private int review_sq; // 게시물번호
   private int teacher_sq; // 강사(회원)번호
   private int member_sq; // 회원번호
   private int rating; // 평점
   private Date date_writingout_dt; // 작성날짜
   private String review_title; // 후기제목
   private String review_content; // 후기내용
   private String review_img; // 후기이미지
   private int comment_cnt; // 댓글수
   private int writing_year; // 후기작성년도
   
   private int branch_id; // 지점아이디
   private String name; // 이름(중간에 *표시)
   private String real_name; // 이름
   private String branch_nm; // 지점명
   private int branch_tp_id;

   private String smst_nm; // 학기
   private String class_nm; // 강좌명
   private int current_page; // 더보기
   private int tot_review_sq; // 게시물 총 갯수
   
   
   // 후기 상세보기
   private String teacher_nm; // 강사이름
   private String lrclsctegery; // 대분류
   private String mdclsctegery; // 중분류
   private String smclsctegery; // 소분류
   private Date schedule_start_dt; // 강좌시작일
   private Date schedule_end_dt; // 강좌종료일
   private int class_id; // 강좌 아이디
   private String class_st; // 강좌상태
   private String class_img; // 강좌썸네일
   private int lectCd;
   private int sortSeqno;	// 내가 쓴 댓글 삭제번호
   
   // 리뷰 댓글 테이블
   private int comment_sq; // 댓글번호
   private String write_dt; // 댓글 작성날짜
   private String comment_content; // 댓글내용
   
   // 학기별 강좌 테이블
   private int class_semester_sq; // 학기별 강좌 번호
   private int open_smst_id; // 개강학기 아이디
   private String open_year; // 개강 연도 
  
   // frmSearch
   private String pageIndex;
   private String lectSmsterCd;
   private String q;
   private String initIndex;
   private String listCnt;
   private String orderSet;
   
   private int tot_cnt;  // 후기 총 갯수
 
}
