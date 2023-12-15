package com.culture.demo.service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.culture.demo.domain.MemberDTO;
import com.culture.demo.mapper.MemberMapper;
import com.culture.demo.mapper.PaymentMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService{

	private MemberMapper memberMapper;
	private PaymentMapper paymentMapper;
	
	// 회원 정보 조회(동반수강자 포함)
	@Override
	public MemberDTO getMemberWithChild(int member_sq) throws SQLException, ClassNotFoundException {
		log.info(">>PaymentServiceImpl.getMemberWithChild() ...");
		return memberMapper.selectMemberWithChild(member_sq);
	}
	
	// 수강자변경시 list html
	@Override
	public String createCourDetailWHtml(Map<String, String> paramMap) {
		log.info(">>PaymentServiceImpl.createCourDetailWHtml() ...");
		int cnt = Integer.parseInt(paramMap.get("cnt"));
		String[] names = paramMap.get("korNm").split(",");
		String[] fmlyRelCds = paramMap.get("fmlyRelCd").split(",");
		String[] fmlyRelCdNms = paramMap.get("fmlyRelCdNm").split(",");
		String[] sexCds = paramMap.get("sexCd").split(",");
		String[] bdays = paramMap.get("bday").split(",");
		String type = paramMap.get("type");
		int classFee = Integer.parseInt(paramMap.get("lectAmt"));
		int exCharge = Integer.parseInt(paramMap.get("optnAmt"));
		
		StringBuilder html = new StringBuilder();
		html.append("<div data-rslt-cd='1' data-type='"+paramMap.get("type")+"'></div>");
		for (int i = 0; i < cnt; i++) {
			html.append("<div class=\"cour_detail\" data-kor-nm=\""+names[i]+"\" data-fmly-rel-cd=\""+fmlyRelCds[i]+"\"\r\n"
					+ "data-fmly-rel-cd-nm=\""+fmlyRelCdNms[i]+"\" data-bday=\""+bdays[i]+"\" data-sex-cd=\""+sexCds[i]+"\">\r\n"
					+ "<div class=\"left\">\r\n"
					+ "	<div class=\"tit f_body1\">"+names[i]+"("+fmlyRelCdNms[i]+")"+"</div>\r\n"
					+ "	<div class=\"flex_btn_wrap\">\r\n");
			if(i==0) {
				html.append("<a style=\"background-color: #e0f55c;\" class=\"border_btn\"\r\n"
						+ "		href=\"javascript:\" role=\"button\"\r\n"
						+ "		onclick=\"payment.openFmlyPopup(this, '"+type+"')\">\r\n");
				if(type=="child") html.append("<span style=\"color: #000;\">자녀회원 선택</span>");
				else html.append("<span style=\"color: #000;\">수강자 변경/추가</span>");
			}else {
				html.append("<a class=\"border_btn\" href=\"javascript:\" role=\"button\" onclick=\"payment.deleteActlAtlctNple(this)\">\r\n"
						+ "			                  <span>삭제</span>\r\n"
						+ "			                </a>");
			}
			html.append("	</a>\r\n"
					+ "	</div>\r\n"
					+ "</div>\r\n"
					+ "<div class=\"right\">\r\n"
					+ "	<ul class=\"txt_wrap\">\r\n"
					+ "		<li class=\"f_body3\">\r\n"
					+ "			<div class=\"txt_con\">\r\n"
					+ "				<div class=\"tit\">강좌료</div>\r\n"
					+ "				<div class=\"txt\">\r\n"
					+ "					<p>"+String.format("%,d",classFee)+"원</p>\r\n"
					+ "				</div>\r\n"
					+ "			</div>\r\n"
					+ "		</li>\r\n");
			if( exCharge!= 0 ) {
				html.append("<li class=\"f_body3\">\r\n"
						+ "									<div class=\"txt_con\">\r\n"
						+ "										<div class=\"tit\">재료비/대여료</div>\r\n"
						+ "										<div class=\"txt\">\r\n"
						+ "											<p>"+String.format("%,d", exCharge)+"원</p>\r\n"
						+ "										</div>\r\n"
						+ "									</div>\r\n"
						+ "								</li>\r\n");
			}
			html.append("		<li class=\"expected\">\r\n"
					+ "			<div class=\"txt_con\">\r\n"
					+ "				<div class=\"tit\">결제예정 금액</div>\r\n"
					+ "				<div class=\"txt\">\r\n"
					+ "					<p>"+String.format("%,d",classFee+exCharge)+"원</p>\r\n"
					+ "				</div>\r\n"
					+ "			</div>\r\n"
					+ "		</li>\r\n"
					+ "	</ul>\r\n"
					+ "</div>\r\n"
					+ "</div>");
		}
		return html.toString();
	}
	// 학기별강좌 - 수강가능인원  
	@Override
	public int matchPeopleTotAv(int detail_class_sq, int cnt) throws Exception {
		log.info(">>PaymentServiceImpl.matchPeopleTotAv() ...");
		return paymentMapper.cntPeopleTotAv(detail_class_sq,cnt);
	}
	// 수강결제신청하는 강좌가 수강신청한 강좌인지
	@Override
	public int matchClassOrder(int member_sq, int detail_class_sq) throws Exception {
		log.info(">>PaymentServiceImpl.matchClassOrder() ...");
		return paymentMapper.cntClassOrderMatch(member_sq,detail_class_sq);
	}
	
}
