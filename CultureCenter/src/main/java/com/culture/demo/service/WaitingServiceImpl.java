package com.culture.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culture.demo.domain.WaitingListDTO;
import com.culture.demo.mapper.MypageWaitingMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class WaitingServiceImpl implements WaitingService{

	@Autowired
	private MypageWaitingMapper mypageWaitingListMapper;
	// 장바구니 목록 html 작성
	@Override
	public String createWaitingHtml(int member_sq, WaitingListDTO params) throws SQLException, ClassNotFoundException {
		log.info(">>WaitingService.createWaitingHtml() ... ");

		String branch_nm = params.getBranch_nm();

		List<WaitingListDTO> list = mypageWaitingListMapper.getWaitingList(member_sq, branch_nm);
		
		StringBuilder html = new StringBuilder();
		int totCnt = list.size();
		if(list.isEmpty() || list.size() == 0) {
			html.append(" <div class=\"no_srch_area no_pb\" data-tot-cnt=\"0\">\r\n"
					+ "			<div class=\"no_srch_div\">\r\n"
					+ "				<p class=\"txt f_h2\"><span class=\"normal_value\">검색결과가 없습니다.</span></p>\r\n"
					+ "			</div>\r\n"
					+ "		</div>");
					
		}else {
			for(WaitingListDTO WaitingListDTO : list) {
				html.append("<div class=\"cour_his_list \" data-tot-cnt="+totCnt+" data-atlct-rsv-no=\"2330012nolNK\" \r\n"
						+ "	data-lect-nm=\""+(WaitingListDTO.getClass_nm())+"\" data-optn-nm=\"\" data-lect-st-dtm=\"" + (WaitingListDTO.getSchedule_start_dt())+ "\" data-lect-st=\"" + (WaitingListDTO.getStart_time()) + "\">\r\n"
						+ "	<div class=\"info_area lime\">\r\n"
						+ "                 <div class=\"txt_box\">\r\n"
						+ "                   <p class=\"f_body4\">대기 신청 중인 강좌입니다.</p>\r\n"
						+ "                 </div>\r\n"
						+ "                 <div class=\"flex_btn_wrap small_btn\">\r\n"
						+ "                   <a class=\"border_btn\" href=\"javascript:\" onclick=\"mypage_waiting.moveCancel(this)\">\r\n"
						+ "                     <span class=\"f_btn\">대기취소</span>\r\n"
						+ "                   </a>\r\n"
						+ "                 </div>	\r\n"
						+ "               </div>\r\n"
						+ "		<div class=\"cour_top_area\" data-brch-cd=\"0012\" data-yy=\"2023\" data-lect-smster-cd=\"3\" data-lect-cd=\"0240\" data-optn-seqno=\"\" data-lect-tp-cd=\"01\" data-pbl-pmprcust-brch-cd=\"\" data-pbl-pmprcust-lect-cd=\"\" data-pbl-pmprcust-brch-cd-nm=\"\">\r\n"
						+ "             <div class=\"left\">\r\n"
						+ "               <div class=\"label_div\">\r\n"
						+ "                 <p class=\"label small lime\">"+(WaitingListDTO.getClass_st())+"</p>\r\n"
						+ "                 <p class=\"label small black_gray\">"+ (WaitingListDTO.getBranch_nm())+"</p>\r\n"
						+ "               </div>\r\n"
						+ "               <!-- 2023-03-03 a태그 추가 park-->\r\n"
						+ "               <p class=\"tit f_h2\">\r\n"
						+ "                 <a href=\"${dto.강좌 URL}\">"+ (WaitingListDTO.getClass_nm())+"</a>\r\n"
						+ "               </p>\r\n"
						+ "             </div>\r\n"
						+ "             <div class=\"right\">\r\n"
						+ "               <ul class=\"txt_wrap\">\r\n"
						+ "                 <li class=\"dl f_body2\">\r\n"
						+ "                   <p class=\"dd f_body1\">"+ (WaitingListDTO.getTeacher_nm())+"</p>\r\n"
						+ "                 </li>\r\n"
						+ "                 <li class=\"dl f_body2\">\r\n"
						+ "                   <p class=\"dd f_body1\">"+ (WaitingListDTO.getSmst_nm())+"</p>\r\n"
						+ "                 </li>\r\n"
						+ "                 <li class=\"dl f_body2\">\r\n"
						+ "                   <p class=\"dd f_body1\">"+ (WaitingListDTO.getSchedule_start_dt().substring(0, 10) +"~"+ WaitingListDTO.getSchedule_end_dt().substring(0, 10))+"("+ WaitingListDTO.getWeek() +
						         				")"+(WaitingListDTO.getStart_time().substring(0, 2) +":"+ WaitingListDTO.getStart_time().substring(2, 4)  +"~"+ WaitingListDTO.getEnd_time().substring(0, 2) +":"+ WaitingListDTO.getEnd_time().substring(2, 4))+"</p>\r\n"
						+ "                 </li>\r\n"
						+ "               </ul>\r\n"
						+ "             </div>\r\n"
						+ "           </div>\r\n"
						+ "           <div class=\"cour_detail_w \">\r\n"
						+ "           	<div class=\"cour_detail \" data-actl-atlct-nple-seqno=\"1\" data-actl-atlct-nple-nm=\""+(WaitingListDTO.getChildren_nm())+"\" data-fmly-rel-cd=\"02\" data-fmly-rel-cd-nm=\"자녀\" data-bday=\"20150101\" data-sex-cd=\"M\" >\r\n"
						+ "                       <div class=\"txt_wrap\">\r\n"
						+ "                          <li>\r\n"
						+ "	                      <div class=\"tit tit_wrap\">\r\n"
						+ "	                      	<p>"+(WaitingListDTO.getChildren_nm())+"\r\n");
				if(WaitingListDTO.getMember_nm() == WaitingListDTO.getChildren_nm()) {
						html.append("<p>(본인)</p>\r\n");
				} else {
						html.append("<p>(자녀)</p>\r\n");					
				}
						html.append("	                        </p>\r\n"
						+ "                            <p class=\"sub f_body4 red_txt\">\r\n"
						+ "                            내 앞에 <span>"+(WaitingListDTO.getLate_sq_rank())+"</span>명이 대기중 입니다.\r\n"
						+ "                            </p>\r\n"
						+ "                          </div>\r\n"
						+ "	                   </li>\r\n"
						+ "                          <li>\r\n"
						+ "                           <div class=\"txt\">\r\n"
						+ "                             <div class=\"left f_body4\">\r\n"
						+ "                               <p>강좌료</p>\r\n"
						+ "                             </div>\r\n"
						+ "		                              <div class=\"right f_body4\">\r\n"
						+ "		                                <p>"+(WaitingListDTO.getClass_fee())+" 원</p>\r\n"
						+ "		                              </div>\r\n");
				if(WaitingListDTO.getEx_charge() != 0) {					
						html.append(" <div class=\"left f_body4\">\r\n"
						+ "		          <p>재료비/대여료</p>\r\n"
						+ "		      </div>\r\n"
						+ "		      <div class=\"right f_body4\">\r\n"
						+ "		      	<p>"+(WaitingListDTO.getEx_charge())+"</p>\r\n"
						+ "		      </div>\r\n"
						);
				}
						html.append("				          </div>\r\n"
						+ "		                          </li>\r\n"
						+ "		                          <li class=\"total_pay\">\r\n"
						+ "		                            <div class=\"txt_con\">\r\n"
						+ "		                              <div class=\"tit\">결제예정 금액</div>\r\n"
						+ "		                              <div class=\"txt\">\r\n"
						+ "		                                <p>"+(WaitingListDTO.getClass_fee() + WaitingListDTO.getEx_charge())+"원</p>\r\n"
						+ "		                              </div>\r\n"
						+ "		                            </div>\r\n"
						+ "		                          </li>\r\n"
						+ "		                        </div>\r\n"
						+ "	                        </div>\r\n"
						+ "	                	</div>\r\n"
						+ "                    </div>");
			}
		}
		return html.toString();
	}


}
