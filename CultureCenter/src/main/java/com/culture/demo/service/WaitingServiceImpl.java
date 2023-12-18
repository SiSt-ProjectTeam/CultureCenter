package com.culture.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culture.demo.domain.FrmSearchDTO;
import com.culture.demo.domain.WaitingListDTO;
import com.culture.demo.mapper.MypageWaitingMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class WaitingServiceImpl implements WaitingService{

	@Autowired
	private MypageWaitingMapper mypageWaitingListMapper;
	
	@Transactional(rollbackFor = Throwable.class) // 모든 예외 발생시 트랜잭션 처리로 롤백
	@Override
	public int deleteWaiting(int late_sq) throws SQLException, ClassNotFoundException {
		log.info("WaitingServiceImpl.java > deleteWaiting...");
		try {
			
            mypageWaitingListMapper.deleteChildren(late_sq);
            mypageWaitingListMapper.deleteClass(late_sq);
            return 1; // 성공 시 1 반환
        } catch (Exception e) {
            log.error("Error cancelling waiting: " + e.getMessage());
            return 0; // 실패 시 0 반환
        }
    }
	
	@Override
	public String createWaitingHtml(int member_sq, FrmSearchDTO params) throws SQLException, ClassNotFoundException {
		log.info("WaitingServiceImpl.java > createWaitingHtml...");

		List<WaitingListDTO> list = mypageWaitingListMapper.getWaitingList(member_sq, params);
		
		StringBuilder html = new StringBuilder();
		int totCnt = mypageWaitingListMapper.totCnt(member_sq, params);
		
		
		
		if(list.isEmpty() || list.size() == 0) {
			html.append(" <div class=\"no_srch_area no_pb\" data-tot-cnt=\"0\">\r\n"
					+ "			<div class=\"no_srch_div\">\r\n"
					+ "				<p class=\"txt f_h2\"><span class=\"normal_value\">검색결과가 없습니다.</span></p>\r\n"
					+ "			</div>\r\n"
					+ "		</div>");
					
		}else {
			for(WaitingListDTO WaitingListDTO : list) {
				Integer classfee = (WaitingListDTO.getChildren_nm().split(",").length) * WaitingListDTO.getClass_fee();
				Integer ex_charge = (WaitingListDTO.getChildren_nm().split(",").length) * WaitingListDTO.getEx_charge();
				Integer totalpay = (WaitingListDTO.getChildren_nm().split(",").length) * (WaitingListDTO.getClass_fee() + WaitingListDTO.getEx_charge());
				String ClassFee = classfee.toString().replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
				String ExCharge = ex_charge.toString().replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
				String TotalPay = totalpay.toString().replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
				
				
				html.append("<div class=\"cour_his_list \" data-tot-cnt="+totCnt+" data-atlct-rsv-no=\"2330012nolNK\" \r\n"
						+ "	data-lect-nm=\""+(WaitingListDTO.getClass_nm())+"\" data-optn-nm=\"\" data-lect-st-dtm=\"" + (WaitingListDTO.getSchedule_start_dt())+ "\" data-lect-st=\"" + (WaitingListDTO.getStart_time()) + "\""
						+ "	data-late-sq=\""+ WaitingListDTO.getLate_sq() +"\">\r\n"
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
						+ "		<div class=\"cour_top_area\" data-brch-cd=\"\" data-yy=\"\" data-lect-smster-cd=\"\" data-lect-cd=\"\" data-optn-seqno=\"\" data-lect-tp-cd=\"\" data-pbl-pmprcust-brch-cd=\"\" data-pbl-pmprcust-lect-cd=\"\" data-pbl-pmprcust-brch-cd-nm=\"\">\r\n"
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
						+ "           	<div class=\"cour_detail \" data-actl-atlct-nple-seqno=\"\" data-actl-atlct-nple-nm=\""+(WaitingListDTO.getChildren_nm())+"\" data-fmly-rel-cd=\"\" data-fmly-rel-cd-nm=\"\" data-bday=\"\" data-sex-cd=\"\" >\r\n"
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
						+ "                            내 앞에 <span>"+(WaitingListDTO.getLate_sq_rank()-1)+"</span>명이 대기중 입니다.\r\n"
						+ "                            </p>\r\n"
						+ "                          </div>\r\n"
						+ "	                   </li>\r\n"
						+ "                          <li>\r\n"
						+ "                           <div class=\"txt\">\r\n"
						+ "                             <div class=\"left f_body4\">\r\n"
						+ "                               <p>강좌료</p>\r\n"
						+ "                             </div>\r\n"
						+ "		                              <div class=\"right f_body4\">\r\n"
						+ "		                                <p>"+(ClassFee)+" 원</p>\r\n"
						+ "		                              </div>\r\n");
				if(WaitingListDTO.getEx_charge() != 0) {					
						html.append(" <div class=\"left f_body4\">\r\n"
						+ "		          <p>재료비/대여료</p>\r\n"
						+ "		      </div>\r\n"
						+ "		      <div class=\"right f_body4\">\r\n"
						+ "		      	<p>"+(ExCharge)+"</p>\r\n"
						+ "		      </div>\r\n"
						);
				}
						html.append("				          </div>\r\n"
						+ "		                          </li>\r\n"
						+ "		                          <li class=\"total_pay\">\r\n"
						+ "		                            <div class=\"txt_con\">\r\n"
						+ "		                              <div class=\"tit\">결제예정 금액</div>\r\n"
						+ "		                              <div class=\"txt\">\r\n"
						+ "		                                <p>"+(TotalPay)+"원</p>\r\n"
						+ "		                              </div>\r\n"
						+ "		                            </div>\r\n"
						+ "		                          </li>\r\n"
						+ "		                        </div>\r\n"
						+ "	                        </div>\r\n"
						+ "	                	</div>\r\n"
						+ "                    </div>"
						);
			}
		}
		return html.toString();
	}

}
