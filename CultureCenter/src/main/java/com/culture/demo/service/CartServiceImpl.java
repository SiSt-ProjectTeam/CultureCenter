package com.culture.demo.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culture.demo.domain.CartDTO;
import com.culture.demo.domain.FrmSearchDTO;
import com.culture.demo.mapper.CartMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class CartServiceImpl implements CartService{

	@Autowired
	private CartMapper cartMapper;

	// 장바구니 추가
	@Override
	public int insert(int member_sq, int detail_class_sq) throws SQLException, ClassNotFoundException{
		log.info(">>CartService.insert() ... ");
		return cartMapper.insert(member_sq,detail_class_sq); 
	}
	
	// 장바구니 목록 html 작성
	@Override
	public String createCartHtml(int member_sq, FrmSearchDTO params) throws SQLException, ClassNotFoundException {
		log.info(">>CartService.createCartHtml() ... ");

		int branch_id = params.getBrchCd();
		String lectDetailSq = ""; //getCarts매개변수 세부강좌번호 0 == 전체
		List<CartDTO> list = cartMapper.getCarts(member_sq,branch_id,lectDetailSq);
		
		StringBuilder html = new StringBuilder();
		html.append("<script>\r\n"
				+ "	  $(\".f_caption2 .point\").text(\""+list.size()+"개\");\r\n"
				+ "  </script>");
		if(list.isEmpty() || list.size() == 0) {
			html.append("<div class=\"no_srch_area no_pb\">\r\n"
					+ "		<div class=\"no_srch_div\">\r\n"
					+ "			<p class=\"txt f_h2\">\r\n"
					+ "				<span class=\"normal_value\">장바구니가 비어있습니다.</span>\r\n"
					+ "			</p>\r\n"
					+ "		</div>\r\n"
					+ "	</div>");			
		}else {
			int i=1;
			for(CartDTO cartDTO : list) {
				html.append("<div class=\"cour_his_list\"\r\n"
						+ "    data-lect-stlm-amt=\"" + (cartDTO.getClass_fee() + cartDTO.getEx_charge()) + "\"\r\n"
						+ "    data-cart-seqno=\"" + cartDTO.getDetail_class_sq() + "\"\r\n"
						+ "    data-brch-cd=\"" + cartDTO.getBranch_id() + "\" data-yy=\"" + cartDTO.getOpen_year() + "\"\r\n"
						+ "    data-lect-smster-cd=\"" + cartDTO.getOpen_smst_id() + "\" data-lect-cd=\"" + cartDTO.getClass_id() + "\"\r\n"
						+ "    data-lect-stat-cd=\"" + cartDTO.getClass_st_id() + "\" data-mvg-dsply-use-yn=\"N\"\r\n"
						+ "    data-optn-seqno=\"\" data-lect-nm=\"" + cartDTO.getClass_nm() + "\" data-optn-use-yn=N\r\n"
						+ "    data-pbl-pmprcust-parnt-brch-cd=\"\" data-pbl-pmprcust-parnt-lect-cd=\"\"\r\n"
						+ "    data-pbl-pmprcust-parnt-brch-cd-nm=\"\" data-atlct-dupl-yn=\"N\"\r\n"
						+ "    data-lect-dupl-yn=\"N\" data-optn-validate-yn=\"Y\">\r\n"
						+ "\r\n"
						+ "    <div class=\"cour_top_area\">\r\n"
						+ "        <div class=\"form_checkbox\">\r\n"
						+ "            <input type=\"checkbox\" id=\"shopbagAgree"+(i)+"\" name=\"\"\r\n"
						+ "                onclick=\"mypage_cart.clickCheckbox(this)\" autocomplete=\"off\"><label\r\n"
						+ "                for=\"shopbagAgree"+(i++)+"\"></label>\r\n"
						+ "        </div>\r\n"
						+ "        <div class=\"left\">\r\n"
						+ "            <div class=\"left_info\">\r\n"
						+ "                <div class=\"label_div\">\r\n"
						+ "                    <p class=\"label small lime\">" + cartDTO.getClass_st() + "</p>\r\n"
						+ "                    <p class=\"label small black_gray\">" + cartDTO.getBranch_nm() + "</p>\r\n"
						+ "                </div>\r\n"
						+ "                <p class=\"tit f_h2\">\r\n"
						+ "                    <a href=\"/application/search/view.do?brchCd=" + cartDTO.getBranch_id() + "&yy=" + cartDTO.getOpen_year() + "&lectSmsterCd=" + cartDTO.getOpen_smst_id() + "&lectCd=" + cartDTO.getClass_id() + "\">\r\n"
						+ "                        <span>"+cartDTO.getClass_nm()+"</span>\r\n"
						+ "                    </a>\r\n"
						+ "                </p>\r\n"
						+ "            </div>\r\n"
						+ "        </div>\r\n"
						+ "        <div class=\"right\">\r\n"
						+ "            <ul class=\"txt_wrap\">\r\n"
						+ "                <li class=\"dl f_body2\">\r\n"
						+ "                    <p class=\"dt only_pc\">강사명</p>\r\n"
						+ "                    <p class=\"dd f_body1\">" + cartDTO.getTeacher_nm() + "</p>\r\n"
						+ "                </li>\r\n"
						+ "                <li class=\"dl f_body2\">\r\n"
						+ "                    <p class=\"dt only_pc\">학기명</p>\r\n"
						+ "                    <p class=\"dd f_body1\">"+cartDTO.getOpen_year()+"년 "+cartDTO.getSmst_nm()+"</p>\r\n"
						+ "                </li>\r\n"
						+ "                <li class=\"dl f_body2\">\r\n"
						+ "                    <p class=\"dt only_pc\">강좌정보</p>\r\n"
						+ "                    <p class=\"dd f_body1\">\r\n"+cartDTO.getSchedule_start_dt()+" ~ "+cartDTO.getSchedule_end_dt()+""
						+ "                        (" + cartDTO.getDay() + ") "
						+ ""+cartDTO.getStart_time()+""
						+ "~"+ cartDTO.getEnd_time()+""
						+ " / " + cartDTO.getClass_cnt() + "회\r\n"
						+ "                    </p>\r\n"
						+ "                </li>\r\n"
						+ "            </ul>\r\n"
						+ "			   <ul class=\"txt_wrap\">\r\n"
						+ "                <li class=\"dl f_body2\">\r\n"
						+ "                    <p class=\"dt\">강좌료</p>\r\n"
						+ "                    <p class=\"dd f_body1\">"
						+ "						"+String.format("%,d",cartDTO.getClass_fee())+"원\r\n"
						+ "                    </p>\r\n"
						+ "                </li>\r\n"
						+ "\r\n");
				if(cartDTO.getEx_charge() != 0) {
					html.append("             <li class=\"dl f_body2\">\r\n"
							+ "                    <p class=\"dt\">재료비/대여료</p>\r\n"
							+ "                    <p class=\"dd f_body1\">\r\n"
							+ "						"+String.format("%,d",cartDTO.getEx_charge())+"원\r\n"
							+ "                    </p>\r\n"
							+ "                </li>\r\n");					
				}
				html.append("                <li class=\"dl total_pay f_body2\">\r\n"
						+ "                    <p class=\"dt\">총금액</p>\r\n"
						+ "                    <p class=\"dd f_body1\">\r\n"
						+ "						"+String.format("%,d",cartDTO.getClass_fee()+cartDTO.getEx_charge())+"원\r\n"
						+ "                    </p>\r\n"
						+ "                </li>\r\n"
						+ "            </ul>\r\n"
						+ "        </div>\r\n"
						+ "        <a href=\"javascript:\" class=\"f_btn delete_btn\" role=\"button\"\r\n"
						+ "            onclick=\"mypage_cart.removeCart(this)\"></a>\r\n"
						+ "    </div>\r\n"
						+ "</div>");
			}
		}
		return html.toString();
	}
	
	// 장바구니 목록
	@Override
	public List<CartDTO> getList(int member_sq, String lectDetailSq) throws SQLException, ClassNotFoundException {
		log.info(">>CartService.getList() ... ");
		int branch_id = 0; // getCarts의 매개변수 0 == 전체
		return cartMapper.getCarts(member_sq,branch_id,lectDetailSq);
	}
	
	// 장바구니 삭제
	@Override
	public int delete(int member_sq, String type, String cartSeqno) throws SQLException, ClassNotFoundException {
		log.info(">>CartService.delete() ... ");
		return cartMapper.delete(member_sq,type,cartSeqno);
	}
	// 장바구니 자동삭제
	@Override
	public void autoDelete() throws SQLException, ClassNotFoundException {
		Date now = new Date();
		log.info(">>CartService.autoDelete() ... now : "+now);
		
		cartMapper.autoDelete();
	}
}
