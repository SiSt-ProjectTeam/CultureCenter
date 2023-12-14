package com.culture.demo.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO {

	// 지점 branch
	private int branch_tp_id;					// 지점 타입 아이디(대분류:서울,수도권,지방)					 	 	
	private String branch_nm;					// 지점명(소분류:지점명)
	private int branch_id;						// 지점 아이디
	private String branch_addr;					// 지점 주소
	private String tel;							// 지점 전화번호
	private double lat;							// 지점 위도
	private double lng;							// 지점 경도
	
	private List<String> branch_img;			// 지점 사진
	private String classroom_nm;				// 지점 강의실
	
	private int brchAreaCd;
	private int brchCd;
 	
}
