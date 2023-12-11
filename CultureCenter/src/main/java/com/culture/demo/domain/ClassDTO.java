package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassDTO {
	
	// 지점 / branch
	private int branch_id;			// 지점 아이디
	private int branch_tp_id;		// 지점 유형 아이디
	private String branch_nm;		// 지점 이름
	
	// 지점유형 / branch_type	
	private String branch_tp;		// 지점 유형
	
	// 대분류 / large_category
	private String lrclsCtegryCd;	// 대분류코드
	private String lrclsCtegry;		// 대분류명
	
	// 중분류 / medium_category
	private String mdclsCtegryCd;	// 중분류코드
	private String mdclsCtegry;		// 중분류명
	
	// 강좌 테이블  class
	private int class_id;				// 강좌 아이디
	private int class_div_id;			// 강좌 구분 아이디
	private int class_type_id;			// 강좌 유형 아이디
	private int class_st_id;			// 강좌 상태 아이디
	private int member_sq;				// 강사 회원 번호
	private String class_nm;			// 강좌 이름
	private String class_img;			// 강좌사진(썸네일)
	private String detail_img;			// 상세이미지
	private String class_content;		// 강좌설명
	private String class_contnet_smp;	// 강좌설명(간단)
	private String class_sched_desc;	// 강의일정
	private String supplies;			// 준비물/특이사항
	private String class_regist_dt;		// 강좌등록일
	private String target_div;			// 대상 구분
	
	// 학기별 강좌 테이블  class_by_semester
	private int class_semester_sq;		// 학기별 강좌 번호
	private int open_year;				// 개강 연도 			<<- DB에서는 Date 타입
	private int open_smst_id;			// 개강 학기 아이디
	private String schedule_start_dt;	// 강의 일정 시작		<<- DB에서는 Date 타입
	private String schedule_end_dt;		// 강의 일정 끝		<<- DB에서는 Date 타입
	private int people_tot;				// 강의 정원
	private int classroom_id;			// 강의실 아이디
	private int class_duration;			// 강의 시간
	private int class_fee;				// 수강료
	private int ex_charge;				// 재료비/대여료
	private String reception_start_dt;	// 접수기간 시작
	private String reception_end_dt;		// 접수기간 끝
	
	// 세부강좌 테이블  detail_class
	private int detail_class_sq;		// 세부 강좌 번호
	private String start_time;			// 강의 시작 시간
	private String end_time;			// 강의 종료 시간
	private int class_cnt;				// 강의 횟수
	private String mon;					// 월요일
	private String tue;					// 화요일
	private String wed;					// 수요일
	private String thu;					// 목요일
	private String fri;					// 금요일
	private String sat;					// 토요일
	private String sun;					// 일요일
	
	// 강좌 상태 class_state
	private String class_st;			// 강좌 상태
	
	// 강좌 구분 class_division
	private String class_div;			// 강좌 구분

	// 학기 semester
	private String smst_nm;				// 학기명
	
	// 강사 teacher
	private int teacher_sq;				// 강사 번호
	private String teacher_nm;
	
	private String classTime;			// 강의 시간
	private String receptionPeriod;		// 접수 기간
	
}
