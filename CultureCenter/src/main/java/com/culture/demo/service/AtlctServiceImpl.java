package com.culture.demo.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.culture.demo.domain.AtlctDTO;
import com.culture.demo.domain.AtlctPersonalDTO;
import com.culture.demo.domain.FrmSearchDTO;
import com.culture.demo.mapper.AtlctMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class AtlctServiceImpl implements AtlctService {
	
	private AtlctMapper atlctMapper;
	
	// 1. 수강 신청하기
	

	// 2. 수강 취소하기
	@Override
	public boolean cancelAtlct( int member_sq, int order_sq, String orderDetailSqs, int cancelAmt, int cancelReasonId ) throws SQLException{
		return false;
	}

	
	// 3. 수강내역 조회
	@Override
	public ArrayList<AtlctDTO> getAtlctList( FrmSearchDTO frmSearchDTO, int member_sq ) throws SQLException{
		log.info("> AtlctServiceImpl.getAtlctList...");
		
		return this.atlctMapper.selectAtlctList( frmSearchDTO, member_sq );
	}


	// 4. 수강내역 html 생성
	@Override
	public String createAtlctHtml( FrmSearchDTO frmSearchDTO, int member_sq ) throws SQLException {
		log.info("> AtlctServiceImpl.createAtlctHtml...");
		
		StringBuilder html = new StringBuilder();
		ArrayList<AtlctDTO> atlctList = getAtlctList( frmSearchDTO, member_sq );
		
	    if (atlctList.isEmpty()) {
	        html.append( "<div class=\"no_srch_area no_pb\" data-tot-cnt=\"0\">\r\n" );
	        html.append( "	<div class=\"no_srch_div\">\r\n" );
	        html.append( "  	<p class=\"txt f_h2\"><span class=\"normal_value\">검색결과가 없습니다.</span></p>\r\n" );
	        html.append( "  </div>\r\n" );
	        html.append( "</div>" );
			
		} else {
			for(AtlctDTO atlct : atlctList){
				html.append( "<div class=\"cour_his_list more_list\" data-tot-cnt=\"" + atlct.getTot_cnt() + "\">\r\n" );
			    html.append( "<div class=\"info_area\">\r\n" );
			    html.append( "	<div class=\"txt_box\">\r\n" );
			    html.append( "		<p class=\"f_body4\">주문번호 " + atlct.getOrder_sq() + "</p>\r\n" );
			    html.append( "		<p class=\"f_body4\">결제일 " + atlct.getOrder_dt() + "</p>\r\n" );
			    html.append( "	</div>\r\n" );
			    html.append( "	<div class=\"flex_btn_wrap small_btn\">\r\n" );
			    html.append( "		<a class=\"border_btn\" href=\"javascript:\" onclick=\"mypage_atlct.moveDtl(" + atlct.getOrder_sq() + ")\">\r\n" );
			    html.append( "			<span class=\"f_btn\">내역보기</span>\r\n" );
			    html.append( "		</a>\r\n" );
			    html.append( "	</div>\r\n" );
			    html.append( "</div>\r\n" );
			    html.append( "<div class=\"content_w\">\r\n" );
			    html.append( "  <div class=\"cour_top_area\">\r\n" );
			    html.append( "		<div class=\"left\">\r\n" );
			    html.append( "			<div class=\"label_div\"><p class=\"label small border\">" + atlct.getBranch_nm() + "</p></div>\r\n" );
			    html.append( "			<p class=\"tit f_h2\">\r\n" );
			    html.append( "				<a href=\"/application/search/view.do?brchCd=" + atlct.getBranch_tp_id() + "&amp;yy=" + atlct.getOpen_year() + "&amp;lectSmsterCd=" + atlct.getOpen_smst_id() + "&amp;lectCd=" + atlct.getDetail_class_sq() + "\">" + atlct.getClass_nm() + "</a>\r\n" );
			    html.append( "			</p>\r\n" );
			    html.append( "		</div>\r\n" );
			    html.append( "		<div class=\"right\">\r\n" );
			    html.append( "			<ul class=\"txt_wrap\">\r\n" );
			    html.append( "				<li class=\"dl f_body2\"><p class=\"dd f_body1\">" + atlct.getTeacher_nm() + "</p></li>\r\n" );
			    html.append( "				<li class=\"dl f_body2\"><p class=\"dd f_body1\">" + atlct.getOpen_year() + " " + atlct.getSmst_nm() + "</p></li>\r\n" );
			    html.append( "				<li class=\"dl f_body2\"><p class=\"dd f_body1\">" + atlct.getClass_schedule() + "</p></li>\r\n" );
			    html.append( "			</ul>\r\n" );
			    html.append( "			<ul class=\"txt_wrap\">\r\n" );
			    html.append( "				<li class=\"dl f_body2\">\r\n" );
			    html.append( "					<p class=\"dt\">강좌료<span class=\"colon\">:</span></p><p class=\"dd f_body1\">" + atlct.getClass_fee() + "원</p>\r\n" );
			    html.append( "				</li>\r\n" );
			    
			    if (atlct.getEx_charge() > 0) {
			    	html.append( "				<li class=\"dl f_body2\">\r\n" );
				    html.append( "					<p class=\"dt\">재료비/대여료<span class=\"colon\">:</span></p><p class=\"dd f_body1\">" + atlct.getEx_charge() + "원<span>(재료비)</span></p>\r\n" );
				    html.append( "				</li>\r\n" );
				}
			    html.append( "			</ul>\r\n" );
			    
			    if (frmSearchDTO.getType().equals("cmplt")) {
			    	 html.append( "  	<div class=\"full_btn_w\"><div class=\"btn_box\"><div class=\"flex_btn_wrap\">\r\n" );
			         html.append( "      	<a class=\"s_color_btn gray\" href=\"/application/search/list.do?type=branch&amp;brchCd=" + atlct.getBranch_tp_id() + "&amp;tcCdNo=" + atlct.getTeacher_sq() + "\" style=\"background: #e0f55c;\">\r\n" );
			         html.append( "        		<span style=\"font-size:14px; line-height:38px;\">이 강사의 강좌 더보기</span>\r\n" );
			         html.append( "      	</a>\r\n" );
			         html.append( "  	</div></div></div>\r\n" );
				} // if
			    
	            html.append( "		</div>\r\n" );
			    html.append( "	</div>\r\n" );
			    html.append( "	<div class=\"cour_detail_w plural \">\r\n" );
			    
			    boolean isFirst = true;
			    for (AtlctPersonalDTO atlctPersonalDTO : atlct.getPersonalList()) {
					if (isFirst) {
				        html.append( "		<div class=\"cour_detail\">\r\n" );
				        isFirst = !isFirst;
					} else {
				        html.append( "		<div class=\"cour_detail hiding_con\">\r\n" );
					}
					
				    html.append( "			<div class=\"txt_wrap\">\r\n" );
				    html.append( "				<li>\r\n" );
				    html.append( "					<div class=\"tit\"><p>" + atlctPersonalDTO.getChildren_nm() + "</p></div>\r\n" );
				    html.append( "				</li>\r\n" );
				    html.append( "				<li>\r\n" );
				    html.append( "					<div class=\"txt\">\r\n" );
				    html.append( "						<div class=\"left f_body4\"><p>접수상태</p></div>\r\n" );
				    html.append( "						<div class=\"right f_body4\"><p>" + atlctPersonalDTO.getReceipt_status() + "</p></div>\r\n" );
				    html.append( "					</div>\r\n" );
				    html.append( "				</li>\r\n" );
				    html.append( "				<li>\r\n" );
				    html.append( "					<div class=\"txt\">\r\n" );
				    
				    if (frmSearchDTO.getType().equals("rfnd")) {
				    	html.append( "					<div class=\"left f_body4\"><p>취소(환불)금액</p></div>\r\n" );
				        html.append( "					<div class=\"right f_body4\"><p>" + atlctPersonalDTO.getCancel_amt() + "원</p></div>\r\n" );
					} else {
				    	html.append( "					<div class=\"left f_body4\"><p>주문금액</p></div>\r\n" );
				        html.append( "					<div class=\"right f_body4\"><p>" + (atlctPersonalDTO.getClass_fee()+atlctPersonalDTO.getEx_charge()) + "원</p></div>\r\n" );					
					} // if
				    		        
				    html.append( "					</div>\r\n" );
				    html.append( "				</li>\r\n" );
				    html.append( "			</div>\r\n" );
				    html.append( "		</div>\r\n" );
			    
				} // for
			    
			    html.append( "		</div></div>\r\n" );
			    
			    // 강좌가 2개 이상이거나 강좌가 1개인데 실수강자가 여러명인경우
			    if (atlct.getPersonalList().size() > 1) {
			    	html.append( "	<a href=\"javascript:\" class=\"txt drop_type\">\r\n");
				    html.append( "		<p class=\"f_btn\">정보 더보기</p>\r\n");
				    html.append( "		<span class=\"drop_btn\"></span>\r\n");
				    html.append( "	</a>" );
				} // if
			    html.append( "</div>" );
			    
			}; // atlctList.forEach()
			
		} // if
		
		return html.toString();
	    
	} // createAtlctHtml()
}
