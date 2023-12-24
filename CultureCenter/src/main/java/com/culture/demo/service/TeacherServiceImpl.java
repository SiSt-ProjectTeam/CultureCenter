package com.culture.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.culture.demo.domain.TeacherDTO;
import com.culture.demo.mapper.TeacherMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	private TeacherMapper teacherMapper;

	//개인정보 수집 동의 여부
	@Override
	public Map<String, Object> clausePrivacyOk(Map<String, Object> clausePrivacyData, int memberSq) {
		log.info("> TeacherServiceImpl.clausePrivacyOk......");		
		Map<String , Object> okMap = new HashMap<String, Object>();	
		
		if (clausePrivacyData.get("prinfClctAgrYn").equals("Y")) {
			okMap = new HashMap<String, Object>();

			String temporaryContent = teacherMapper.temporaryOk(memberSq);
			String teacherCheck = teacherMapper.noApplyClassOkTeacher(memberSq);
			String alreadyApplication = teacherMapper.applyTeacher(memberSq);		

			if (temporaryContent.equals("Y")) {				
				okMap.put("errorCode", 1);				
			} else {		
				
				if (alreadyApplication.equals("Y")) {			
					okMap.put("errorCode", 2);					
				} else if (teacherCheck.equals("Y")) {				
					okMap.put("errorCode", 3);
					okMap.put("cnt", 1);					
				} else {				
					okMap.put("cnt", 1);
					okMap.put("errorCode", 0);
				}
			}
		}
		return okMap;
	}

	//ApplyClass 테이블 체크
	@Override
	public Map<String, String> applyClassCheck(int memberSq) {
		log.info("> TeacherServiceImpl.applyClassCheck......");
		
		Map<String, String> okMap = new HashMap<String, String>();
		String check = teacherMapper.duplicateApplicaionCheck(memberSq);
		
		okMap.put("cnt", check.equals("Y") ? "1" : "0");

		return okMap;
	}

	//Teacher 테이블 체크
	@Override
	public Map<String, String> teacherCheck(int memberSq) {
		log.info("> TeacherServiceImpl.teacherCheck......");
		
		Map<String, String> okMap = new HashMap<String, String>();		
		String check = teacherMapper.duplicateApplicaionCheck(memberSq);
		
		okMap.put("cnt", check.equals("Y") ? "1" : "0");
		
		return okMap;
	}


	//APPLY, TEACHER 동시 조회
	@Override
	public String applyTeacherCheck(TeacherDTO teacherDTO, int memberSq) {
		log.info("> TeacherServiceImpl.applyTeacherCheck......");

		Map<String, Object> okMap = new HashMap<String, Object>();
		
		String temporaryContent = teacherMapper.temporaryOk(memberSq);
		String teacherCheck = teacherMapper.noApplyClassOkTeacher(memberSq);
		String alreadyApplication = teacherMapper.applyTeacher(memberSq);
		
		if (temporaryContent.equals("Y")) {			
			okMap.put("errorCode", 1);			
		} else {		
			if (alreadyApplication.equals("Y")) {			
				okMap.put("errorCode", 2);				
			} else if (teacherCheck.equals("Y")) {				
				okMap.put("errorCode", 3);
				okMap.put("cnt", 1);				
			} else {			
				okMap.put("cnt", 1);
				okMap.put("errorCode", 0);
			}
		}

		TeacherDTO dto = getSaveTeacherInfo(memberSq);			

		StringBuilder html = new StringBuilder();
		
		String bday = (dto.getBday() != null) ? dto.getBday().replace("-", "").substring(0, dto.getBday().length() - 11) : "";			
		String txfrBizprYn = (dto.getTxfrBizprYn() != null) ? dto.getTxfrBizprYn() : "";
		
		if (okMap.get("errorCode").equals(2)) {
			
			html.append("<script>\r\n");
			html.append("    function init_alert(){\r\n");
			html.append("        alert(\"이미 강사지원을 완료하였습니다.\");\r\n");
			html.append("    }\r\n");
			html.append("</script>\r\n");			
			html.append("</script>\r\n");
			html.append("<div class=\"for_padding\" data-step=\"3\">\r\n");
			html.append("    <div class=\"scroll_area\">\r\n");
			html.append("        <div class=\"process_wrap\" title=\"3. 제출완료\">\r\n");
			html.append("            <div class=\"process_div check two_law\">\r\n");
			html.append("                <p class=\"num\"><span>1</span></p>\r\n");
			html.append("                <p class=\"txt\">개인정보 활용 <br class=\"only_mobile\" />동의</p>\r\n");
			html.append("            </div>\r\n");
			html.append("            <div class=\"process_div check\">\r\n");
			html.append("                <p class=\"num\"><span>2</span></p>\r\n");
			html.append("                <p class=\"txt\">강사정보 작성</p>\r\n");
			html.append("            </div>\r\n");
			html.append("            <div class=\"process_div on\">\r\n");
			html.append("                <p class=\"num\"><span>3</span></p>\r\n");
			html.append("                <p class=\"txt\">제출완료</p>\r\n");
			html.append("            </div>\r\n");
			html.append("        </div>\r\n");
			html.append("        <div class=\"dot_txt_box complete\">\r\n");
			html.append("            <!-- <p class=\"icon\">🎉</p> -->\r\n");
			html.append("            <p class=\"f_body1\">강사지원 제출이 완료되었습니다.</p>\r\n");
			html.append("            <p class=\"f_body4\">담당자 확인후 개별 연락 예정입니다. 감사합니다.</p><!-- 2023-01-16 텍스트 수정 -->\r\n");
			html.append("        </div>\r\n");
			html.append("        <div class=\"flex_btn_wrap\">\r\n");
			html.append("            <a href=\"javascript:\" class=\"border_btn\" onclick=\"teacherRequestCtrl.close();\"><span>닫기</span></a>\r\n");
			html.append("        </div>\r\n");
			html.append("    </div>\r\n");
			html.append("</div>");
			
		} else if (okMap.get("errorCode").equals(1)) {
	
			html.append("<script>\r\n");
			html.append("    function init_alert() {\r\n");
			html.append("        alert(\"강사 지원 정보가 존재합니다.\");\r\n");
			html.append("    }\r\n");
			html.append("</script>\r\n");
			
			html.append("<form id=\"submit_form\" enctype=\"multipart/form-data\">\r\n"
					+ "    <input type=\"hidden\" id=\"form\" name=\"form\" value=\"\"/>\r\n"
					+ "</form>\r\n"
					+ "<div class=\"for_padding\" data-step=\"2\">\r\n"
					+ "    <div class=\"scroll_area\">\r\n"
					+ "        <form id=\"request_form\">\r\n"
					+ "            <input type=\"hidden\" name=\"tcCdNo\" value=\"\"/>\r\n"
					+ "            <input type=\"hidden\" name=\"prinfClctAgrYn\" value=\""+ dto.getPrinfClctAgrYn() +"\"/>\r\n"
					+ "            <input type=\"hidden\" name=\"carrInfoClctAgrYn\" value=\""+ dto.getCarrInfoClctAgrYn() +"\"/>\r\n"
					+ "            <input type=\"hidden\" name=\"appDt\" value=\""+ dto.getAppDt() +"\"/>\r\n"
					+ "            <div class=\"process_wrap\" title=\"2. 강사정보 작성\">\r\n"
					+ "                <div class=\"process_div check two_law\">\r\n"
					+ "                    <p class=\"num\">\r\n"
					+ "                        <span>1</span>\r\n"
					+ "                    </p>\r\n"
					+ "                    <p class=\"txt\">\r\n"
					+ "                        개인정보 활용 <br class=\"only_mobile\"/>동의\r\n"
					+ "                    </p>\r\n"
					+ "                </div>\r\n"
					+ "                <div class=\"process_div on\">\r\n"
					+ "                    <p class=\"num\">\r\n"
					+ "                        <span>2</span>\r\n"
					+ "                    </p>\r\n"
					+ "                    <p class=\"txt\">강사정보 작성</p>\r\n"
					+ "                </div>\r\n"
					+ "                <div class=\"process_div\">\r\n"
					+ "                    <p class=\"num\">\r\n"
					+ "                        <span>3</span>\r\n"
					+ "                    </p>\r\n"
					+ "                    <p class=\"txt\">제출완료</p>\r\n"
					+ "                </div>\r\n"
					+ "            </div>\r\n"
					+ "            <div class=\"dot_txt_box\">\r\n"
					+ "                <p class=\"f_body1\">롯데문화센터에서는 분야별 수준 높은 교육문화 컨텐츠와 열정적인 전문 강사님을 모십니다.</p>\r\n"
					+ "                <!-- 2023-01-10 이모지 삭제 -->\r\n"
					+ "            </div>\r\n"
					+ "            <div class=\"sub_inner\">\r\n"
					+ "                <div class=\"sub_tit_area\">\r\n"
					+ "                    <div class=\"left\">\r\n"
					+ "                        <p class=\"pop_sec_tit\">강사 기본정보</p>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"right\">\r\n"
					+ "                        <p class=\"f_caption2\">\r\n"
					+ "                            <span class=\"red_txt\">*</span>\r\n"
					+ "                            표시는 필수 기재 항목입니다.\r\n"
					+ "                        </p>\r\n"
					+ "                    </div>\r\n"
					+ "                </div>\r\n"
					+ "                <div class=\"data_input_wrap\">\r\n"
					+ "                    <div class=\"row\">\r\n"
					+ "                        <div class=\"th\">\r\n"
					+ "                            <p class=\"tit f_body1 essential\">프로필 사진</p>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"td\">\r\n"
					+ "                            <div class=\"upload_div complete\">\r\n"
					+ "                                <input type=\"hidden\" id=\"phtFileId\" name=\"phtFileId\" value=\""+ dto.getImgpre() +"\"/>\r\n"
					+ "                                <input type=\"file\" style=\"display: none;\" id='phtFileId' name=\"phtFileId\" accept=\".jpg, .jpeg, .png, .gif\" data-max-size=\"10240\" onchange=\"tcCommon.setImgPreview(this);\"/>\r\n"
					+ "                                <!-- 사진 업로드 완료시 complete class 추가 -->\r\n"
					+ "                                <a href=\"javascript:\" class=\"upload_btn photo\" onclick=\"tcCommon.upload_onclick(this);\">\r\n"
					+ "                                    <span>사진 업로드</span>\r\n"
					+ "                                </a>\r\n"
					+ "                                <div class=\"upload_img\">\r\n"
					+ "                                    <div class=\"img_div\">\r\n"
					+ "                                        <p class=\"img\">\r\n"
					+ "                                            <img name=\"imagePreview\" src=\"/img/"+ dto.getImgpre() +"\" alt=\"\">\r\n"
					+ "                                        </p>\r\n"
					+ "                                        <a href=\"javascript:\" class=\"delete_btn\" role=\"button\" onclick=\"tcCommon.deleteFile(this);\">\r\n"
					+ "                                            <span>삭제</span>\r\n"
					+ "                                        </a>\r\n"
					+ "                                    </div>\r\n"
					+ "                                </div>\r\n"
					+ "                            </div>\r\n"
					+ "                            <div class=\"caption_txt_w\">\r\n"
					+ "                                <p class=\"dot_txt\">사진 등록은 필수입니다.</p>\r\n"
					+ "                                <p class=\"dot_txt\">jpg, gif, png 파일만 가능합니다.</p>\r\n"
					+ "                                <p class=\"dot_txt\">용량 제한은 10Mb 이하입니다.</p>\r\n"
					+ "                                <p class=\"dot_txt\">업로드된 이미지는 임시저장 후 확인 가능합니다.</p>\r\n"
					+ "                            </div>\r\n"
					+ "                        </div>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"row\">\r\n"
					+ "                        <div class=\"th\">\r\n"			
					+ "                            <p class=\"tit f_body1 essential\">사업자 여부</p>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"td small\">\r\n"
					+ "                            <div class=\"radio_flex_box\">\r\n"
					+ "                                <div class=\"form_radio\">\r\n"
					+ "                                    <input type=\"radio\" id=\"tcTpCd1\" name=\"tcTpCd\" value=\"1\""+ (dto.getTcTpCd() == 1 ? "checked" : "") +"  onchange=\"teacherRequestCtrl.tcTpCdOnchange(this);\">\r\n"
					+ "                                    <label for=\"tcTpCd1\">개인</label>\r\n"
					+ "                                </div>\r\n"
					+ "                                <div class=\"form_radio\">\r\n"
					+ "                                    <input type=\"radio\" id=\"tcTpCd2\" name=\"tcTpCd\" value=\"2\""+ (dto.getTcTpCd() == 2 ? "checked" : "") +" onchange=\"teacherRequestCtrl.tcTpCdOnchange(this);\">\r\n"
					+ "                                    <label for=\"tcTpCd2\">개인사업자</label>\r\n"
					+ "                                </div>\r\n"
					+ "                                <div class=\"form_radio\">\r\n"
					+ "                                    <input type=\"radio\" id=\"tcTpCd3\" name=\"tcTpCd\" value=\"3\""+ (dto.getTcTpCd() == 3 ? "checked" : "") +" onchange=\"teacherRequestCtrl.tcTpCdOnchange(this);\">\r\n"
					+ "                                    <label for=\"tcTpCd3\">법인사업자</label>\r\n"
					+ "                                </div>\r\n"
					+ "                                <div class=\"form_checkbox\">\r\n");
					
					if (dto.getTxfrBizprYn() != null) {
						html.append("                                    <input type=\"checkbox\" id=\"txfrBizprYn\" name=\"txfrBizprYn\""+ (txfrBizprYn.equals("Y") ? "checked" : "") +" value=\""+ txfrBizprYn +"\">\r\n");
						
					} else {
						html.append("                                    <input type=\"checkbox\" id=\"txfrBizprYn\" name=\"txfrBizprYn\" value=\""+ txfrBizprYn +"\">\r\n");
					};
					
					html.append("                                    <!-- 개인 선택시 disabled 추가해주세요. -->\r\n"
					+ "                                    <label for=\"txfrBizprYn\">면세사업자인 경우 체크해 주세요.</label>\r\n"
					+ "                                </div>\r\n"
					+ "                            </div>\r\n"
					+ "                        </div>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"row\">\r\n"
					+ "                        <div class=\"th\">\r\n"
					+ "                            <p class=\"tit f_body1 essential\">강사명</p>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"td\">\r\n"
					+ "                            <div class=\"form_input\">\r\n"
					+ "                                <input type=\"text\" name=\"tcNm\" id=\"tcNm\" placeholder=\"예) 김롯데\" value=\""+ dto.getTcNm() +"\">\r\n"
					+ "                                <div class=\"input_btn_wrap\">\r\n"
					+ "                                    <a href=\"javascript:\" role=\"button\" class=\"btn_delete\" title=\"강사명 지우기\"></a>\r\n"
					+ "                                </div>\r\n"
					+ "                            </div>\r\n"
					+ "                        </div>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"row\">\r\n"
					+ "                        <div class=\"th\">\r\n"
					+ "                            <p id=\"bdayEssential\" class=\"tit f_body1 essential\">생년월일</p>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"td small\">\r\n"
					+ "                            <div class=\"radio_flex_box\">\r\n"
					+ "                                <div class=\"form_radio\">\r\n"
					+ "                                    <input type=\"radio\" id=\"lscCd1\" name=\"lscCd\" value=\"01\" "+ (dto.getLscCd() == 01 ? "checked" : "") +">\r\n"
					+ "                                    <label for=\"lscCd1\">양력</label>\r\n"
					+ "                                </div>\r\n"
					+ "                                <div class=\"form_radio\">\r\n"
					+ "                                    <input type=\"radio\" id=\"lscCd2\" name=\"lscCd\" value=\"02\" "+ (dto.getLscCd() == 02 ? "checked" : "") +">\r\n"
					+ "                                    <label for=\"lscCd2\">음력</label>\r\n"
					+ "                                </div>\r\n"
					+ "                            </div>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"td\">\r\n"
					+ "                            <div class=\"form_input\">\r\n"
					+ "                                <input type=\"text\" name=\"bday\" id=\"bday\" placeholder=\"예) 19980125\" data-type=\"date\" data-old-value=\"19980125\" value=\""+ bday +"\" maxlength=\"8\" oninput=\"fnc.checkMaxLength(this);\" onkeyup=\"tcCommon.checkNumberOnkeyup(this);\">\r\n"
					+ "                                <div class=\"input_btn_wrap\">\r\n"
					+ "                                    <a href=\"javascript:\" role=\"button\" class=\"btn_delete\" title=\"생년월일 지우기\"></a>\r\n"
					+ "                                </div>\r\n"
					+ "                            </div>\r\n"
					+ "                        </div>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"row\">\r\n"
					+ "                        <div class=\"th\">\r\n"
					+ "                            <p class=\"tit f_body1 essential\">휴대전화</p>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"td\">\r\n"
					+ "                            <div class=\"input_wrap\">\r\n"
					+ "                                <div class=\"input_div\">\r\n"
					+ "                                    <div class=\"form_select_div change\">\r\n"
					+ "                                        <div class=\"open_area\">\r\n"
					+ "                                            <input type=\"hidden\" name=\"hpStNo\" id=\"hpStNo\" value=\""+ dto.getHpStNo() +"\"/>\r\n"
					+ "                                            <a class=\"btn_open\" href=\"javascript:\">\r\n"
					+ "                                                <span>010</span>\r\n"
					+ "                                            </a>\r\n"
					+ "                                        </div>\r\n"
					+ "                                        <div class=\"dimd\"></div>\r\n"
					+ "                                        <div class=\"list_wrap\">\r\n"
					+ "                                            <div class=\"tit_area\">\r\n"
					+ "                                                <p class=\"tit\">휴대전화</p>\r\n"
					+ "                                                <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div class=\"scroll_wrap\">\r\n"
					+ "                                                <a class=\"btn_link on\" href=\"javascript:\" data-value=\"010\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>010</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"011\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>011</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"016\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>016</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"017\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>017</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"018\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>018</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"019\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>019</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                    <div class=\"form_input\">\r\n"
					+ "                                        <input type=\"text\" maxlength=\"4\" name=\"hpMidNo\" id=\"hpMidNo\" data-old-value=\"0125\" value=\""+ dto.getHpMidNo() +"\" placeholder=\"예) 0000\" oninput=\"fnc.checkMaxLength(this);\" onkeyup=\"tcCommon.checkNumberOnkeyup(this);\">\r\n"
					+ "                                        <div class=\"input_btn_wrap\">\r\n"
					+ "                                            <button type=\"button\" class=\"btn_delete\" title=\"휴대전화 중간번호 지우기\"></button>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                    <div class=\"form_input\">\r\n"
					+ "                                        <input type=\"text\" maxlength=\"4\" name=\"hpLastNo\" id=\"hpLastNo\" data-old-value=\"0808\" value=\""+ dto.getHpLastNo() +"\" placeholder=\"예) 0000\" oninput=\"fnc.checkMaxLength(this);\" onkeyup=\"tcCommon.checkNumberOnkeyup(this);\">\r\n"
					+ "                                        <div class=\"input_btn_wrap\">\r\n"
					+ "                                            <button type=\"button\" class=\"btn_delete\" title=\"휴대전화 마지막번호 지우기\"></button>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                </div>\r\n"
					+ "                            </div>\r\n"
					+ "                        </div>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"row\">\r\n"
					+ "                        <div class=\"th\">\r\n"
					+ "                            <p class=\"tit f_body1 essential\">이메일</p>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"td\">\r\n"
					+ "                            <div class=\"input_wrap\">\r\n"
					+ "                                <div class=\"input_div email\">\r\n"
					+ "                                    <div class=\"form_input\">\r\n"
					+ "                                        <input type=\"text\" id=\"email\" name=\"email\" value=\""+ dto.getEmail() +"\" placeholder=\"예) lotte\" data-old-value=\"shy\" onkeyup=\"tcCommon.checkEmailOnkeyup1(this);\">\r\n"
					+ "                                        <div class=\"input_btn_wrap\">\r\n"
					+ "                                            <button type=\"button\" class=\"btn_delete\" title=\"이메일 지우기\"></button>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                    <span>@</span>\r\n"
					+ "                                    <!-- 2023-02-09 추가 -->\r\n"
					+ "                                    <div class=\"form_input\">\r\n"
					+ "                                        <input type=\"text\" id=\"emailAddrCd\" name=\"emailAddrCd\" value=\""+ dto.getEmailAddrCd() +"\" placeholder=\"직접입력\" data-old-value=\"naver.com\" onkeyup=\"tcCommon.checkEmailOnkeyup2(this);\">\r\n"
					+ "                                        <div class=\"input_btn_wrap\">\r\n"
					+ "                                            <button type=\"button\" class=\"btn_delete\" title=\"이메일 지우기\"></button>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                    <div class=\"form_select_div change\">\r\n"
					+ "                                        <div class=\"open_area\">\r\n"
					+ "                                            <input type=\"hidden\" value=\"naver.com\"/>\r\n"
					+ "                                            <a class=\"btn_open\" href=\"javascript:\">\r\n"
					+ "                                                <span>네이버</span>\r\n"
					+ "                                            </a>\r\n"
					+ "                                        </div>\r\n"
					+ "                                        <div class=\"dimd\"></div>\r\n"
					+ "                                        <div class=\"list_wrap\">\r\n"
					+ "                                            <div class=\"tit_area\">\r\n"
					+ "                                                <p class=\"tit\">이메일</p>\r\n"
					+ "                                                <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div class=\"scroll_wrap\">\r\n"
					+ "                                                <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n"
					+ "                                                    <span>직접입력</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link on\" href=\"javascript:\" data-value=\"hanmail.net\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n"
					+ "                                                    <span>다음(한메일)</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link on\" href=\"javascript:\" data-value=\"naver.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n"
					+ "                                                    <span>네이버</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link on\" href=\"javascript:\" data-value=\"nate.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n"
					+ "                                                    <span>네이트</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link on\" href=\"javascript:\" data-value=\"yahoo.co.kr\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n"
					+ "                                                    <span>야후!코리아</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link on\" href=\"javascript:\" data-value=\"yahoo.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n"
					+ "                                                    <span>야후!yahoo.com</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link on\" href=\"javascript:\" data-value=\"dreamwiz.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n"
					+ "                                                    <span>드림위즈</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link on\" href=\"javascript:\" data-value=\"korea.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n"
					+ "                                                    <span>코리아</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link on\" href=\"javascript:\" data-value=\"empal.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n"
					+ "                                                    <span>엠팔</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link on\" href=\"javascript:\" data-value=\"gmail.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n"
					+ "                                                    <span>구글G메일</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link on\" href=\"javascript:\" data-value=\"hanafos.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n"
					+ "                                                    <span>하나포스</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link on\" href=\"javascript:\" data-value=\"paran.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n"
					+ "                                                    <span>파란</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link on\" href=\"javascript:\" data-value=\"freechal.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n"
					+ "                                                    <span>프리첼</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link on\" href=\"javascript:\" data-value=\"hahanet.net\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n"
					+ "                                                    <span>하하넷</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link on\" href=\"javascript:\" data-value=\"hotmail.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n"
					+ "                                                    <span>핫메일</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link on\" href=\"javascript:\" data-value=\"kornet.net\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n"
					+ "                                                    <span>코넷</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link on\" href=\"javascript:\" data-value=\"lycos.co.kr\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n"
					+ "                                                    <span>라이코스</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link on\" href=\"javascript:\" data-value=\"netian.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n"
					+ "                                                    <span>네띠앙</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link on\" href=\"javascript:\" data-value=\"origo.net\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n"
					+ "                                                    <span>오리고</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link on\" href=\"javascript:\" data-value=\"unitel.co.kr\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n"
					+ "                                                    <span>유니텔</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link on\" href=\"javascript:\" data-value=\"chollian.net\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n"
					+ "                                                    <span>천리안</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                </div>\r\n"
					+ "                            </div>\r\n"
					+ "                        </div>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"row\">\r\n"
					+ "                        <div class=\"th\">\r\n"
					+ "                            <p class=\"tit f_body1 essential\">주소</p>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"td\">\r\n"
					+ "                            <div class=\"input_wrap\">\r\n"
					+ "                                <div class=\"input_div\">\r\n"
					+ "                                    <div class=\"form_input postcode disabled\">\r\n"
					+ "                                        <!-- 2022-11-28 disabled class 추가 (값이 들어갈 경우 class 삭제 부탁드립니다) -->\r\n"
					+ "                                        <input type=\"text\" placeholder=\"우편번호\" name=\"pstno\" id=\"pstno\" value=\""+ dto.getPstno() +"\" readonly>\r\n"
					+ "                                    </div>\r\n"
					+ "                                    <a class=\"s_color_btn\" href=\"javascript:\" onclick=\"teacherRequestCtrl.searchAddr();\">\r\n"
					+ "                                        <span>검색</span>\r\n"
					+ "                                    </a>\r\n"
					+ "                                </div>\r\n"
					+ "                                <div class=\"input_div\">\r\n"
					+ "                                    <div class=\"form_input w100p disabled\">\r\n"
					+ "                                        <!-- 2022-11-28 disabled class 추가 (값이 들어갈 경우 class 삭제 부탁드립니다) -->\r\n"
					+ "                                        <input type=\"text\" placeholder=\"기본주소\" name=\"addr\" id=\"addr\" value=\""+ dto.getAddr() +"\" readonly>\r\n"
					+ "                                    </div>\r\n"
					+ "                                </div>\r\n"
					+ "                                <div class=\"input_div\">\r\n"
					+ "                                    <div class=\"form_input w100p \">\r\n"
					+ "                                        <input type=\"text\" placeholder=\"상세주소 (건물, 아파트, 동/호수 선택 입력)\" name=\"dtlAddr\" id=\"dtlAddr\" value=\""+ dto.getDtlAddr() +"\">\r\n"
					+ "                                        <div class=\"input_btn_wrap\">\r\n"
					+ "                                            <button type=\"button\" class=\"btn_delete\" title=\"상세주소 지우기\"></button>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                </div>\r\n"
					+ "                            </div>\r\n"
					+ "                        </div>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"row\">\r\n"
					+ "                        <div class=\"th\">\r\n"
					+ "                            <p class=\"tit f_body1\">강사소개</p>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"td\">\r\n"
					+ "                            <div class=\"form_textarea\">\r\n"
					+ "                                <div class=\"wrap_for_msg\">\r\n"
					+ "                                    <textarea name=\"selfIntrdnCont\" id=\"\" cols=\"\" rows=\"\" placeholder=\"강사 본인에 대한 소개 내용을 간략히 작성해주세요.\" data-maxlength=\"2000\" onkeyup=\"tcCommon.textareaOnkeyup(this)\">" + dto.getSelfIntrdnCont() +"</textarea>\r\n"
					+ "                                </div>\r\n"
					+ "                                <p class=\"check_byte\">\r\n"
					+ "                                    <span class=\"r_byte\">0</span>\r\n"
					+ "                                    /\r\n"
					+ "                                    <span class=\"l_byte\">2000</span>\r\n"
					+ "                                </p>\r\n"
					+ "                            </div>\r\n"
					+ "                        </div>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"row_tit\">\r\n"
					+ "                        <p class=\"tit\">학력 및 경력 정보</p>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"row\">\r\n"
					+ "                        <div class=\"th\">\r\n"
					+ "                            <p class=\"tit f_body1\">학력사항</p>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"td\">\r\n"
					+ "                            <div class=\"input_wrap\">\r\n"
					+ "                                <div class=\"plus_div_w\"></div>\r\n"
					+ "                                <div class=\"input_div w100p\">\r\n"
					+ "                                    <div class=\"form_select_div\">\r\n"
					+ "                                        <div class=\"open_area\">\r\n"
					+ "                                            <input type=\"hidden\" id=\"schlClCd\" name=\"tceduList[][schlClCd]\" value=\"\"/>\r\n"
					+ "                                            <a class=\"btn_open\" href=\"javascript:\">\r\n"
					+ "                                                <span>학교 선택</span>\r\n"
					+ "                                            </a>\r\n"
					+ "                                        </div>\r\n"
					+ "                                        <div class=\"dimd\"></div>\r\n"
					+ "                                        <div class=\"list_wrap\">\r\n"
					+ "                                            <div class=\"tit_area\">\r\n"
					+ "                                                <p class=\"tit\">학교</p>\r\n"
					+ "                                                <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div class=\"scroll_wrap\">\r\n"
					+ "                                                <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>학교 선택</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link\" href=\"javascript:\" data-value=\"0\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>고등학교</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link\" href=\"javascript:\" data-value=\"1\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>전문대학교</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link\" href=\"javascript:\" data-value=\"2\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>대학교</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link\" href=\"javascript:\" data-value=\"3\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>대학원(석사)</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link\" href=\"javascript:\" data-value=\"4\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>대학원(박사)</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                    <div class=\"form_select_div\">\r\n"
					+ "                                        <div class=\"open_area\">\r\n"
					+ "                                            <input type=\"hidden\" id=\"grdtClCd\" name=\"tceduList[][grdtClCd]\" value=\"\"/>\r\n"
					+ "                                            <a class=\"btn_open\" href=\"javascript:\">\r\n"
					+ "                                                <span>졸업상태 선택</span>\r\n"
					+ "                                            </a>\r\n"
					+ "                                        </div>\r\n"
					+ "                                        <div class=\"dimd\"></div>\r\n"
					+ "                                        <div class=\"list_wrap\">\r\n"
					+ "                                            <div class=\"tit_area\">\r\n"
					+ "                                                <p class=\"tit\">졸업상태</p>\r\n"
					+ "                                                <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div class=\"scroll_wrap\">\r\n"
					+ "                                                <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>졸업상태 선택</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"0\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>졸업</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>수료</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>중퇴</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"3\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>재학중</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                    <div class=\"form_select_div\">\r\n"
					+ "                                        <div class=\"open_area\">\r\n"
					+ "                                            <input type=\"hidden\" id=\"yy\" name=\"tceduList[][yy]\" value=\"\"/>\r\n"
					+ "                                            <a class=\"btn_open\" href=\"javascript:\">\r\n"
					+ "                                                <span>년도 선택</span>\r\n"
					+ "                                            </a>\r\n"
					+ "                                        </div>\r\n"
					+ "                                        <div class=\"dimd\"></div>\r\n"
					+ "                                        <div class=\"list_wrap\">\r\n"
					+ "                                            <div class=\"tit_area\">\r\n"
					+ "                                                <p class=\"tit\">년도</p>\r\n"
					+ "                                                <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div class=\"scroll_wrap\">\r\n"
					+ "                                                <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>년도 선택</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2023\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2023</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2022\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2022</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2021\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2021</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2020\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2020</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2019\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2019</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2018\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2018</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2017\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2017</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2016\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2016</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2015\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2015</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2014\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2014</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2013\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2013</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2012\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2012</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2011\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2011</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2010\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2010</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2009\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2009</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2008\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2008</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2007\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2007</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2006\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2006</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2005\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2005</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2004\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2004</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2003\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2003</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2002\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2002</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2001\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2001</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2000\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>2000</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1999\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1999</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1998\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1998</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1997\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1997</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1996\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1996</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1995\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1995</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1994\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1994</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1993\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1993</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1992\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1992</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1991\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1991</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1990\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1990</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1989\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1989</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1988\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1988</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1987\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1987</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1986\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1986</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1985\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1985</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1984\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1984</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1983\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1983</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1982\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1982</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1981\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1981</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1980\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1980</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1979\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1979</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1978\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1978</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1977\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1977</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1976\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1976</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1975\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1975</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1974\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1974</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1973\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1973</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1972\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1972</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1971\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1971</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1970\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1970</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1969\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1969</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1968\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1968</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1967\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1967</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1966\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1966</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1965\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1965</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1964\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1964</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1963\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1963</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1962\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1962</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1961\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1961</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1960\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                    <span>1960</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                </div>\r\n"
					+ "                                <div class=\"input_div\">\r\n"
					+ "                                    <div class=\"form_input w100p\">\r\n"
					+ "                                        <input type=\"text\" placeholder=\"학교명을 입력해주세요.\" id=\"schlNm\" name=\"tceduList[][schlNm]\">\r\n"
					+ "                                        <div class=\"input_btn_wrap\">\r\n"
					+ "                                            <button type=\"button\" class=\"btn_delete\" title=\"학교명 지우기\"></button>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                </div>\r\n"
					+ "                                <div class=\"input_div\">\r\n"
					+ "                                    <div class=\"form_input w100p\">\r\n"
					+ "                                        <input type=\"text\" placeholder=\"전공을 입력해주세요.\" id=\"mjrNm\" name=\"tceduList[][mjrNm]\">\r\n"
					+ "                                        <div class=\"input_btn_wrap\">\r\n"
					+ "                                            <button type=\"button\" class=\"btn_delete\" title=\"전공 지우기\"></button>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                </div>\r\n"
					+ "                                <div class=\"input_div\">\r\n"
					+ "                                    <a class=\"s_color_btn gray\" href=\"javascript:\" data-value=\"edu\" onclick=\"tcRqst2HistCtrl.addInfo(this);\">\r\n"
					+ "                                        <span>추가</span>\r\n"
					+ "                                    </a>\r\n"
					+ "                                </div>\r\n"
					+ "                            </div>\r\n"
					+ "                        </div>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"row\">\r\n"
					+ "                        <div class=\"th\">\r\n"
					+ "                            <p class=\"tit f_body1\">경력사항</p>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"td\">\r\n"
					+ "                            <div class=\"input_wrap\">\r\n"
					+ "                                <div class=\"plus_div_w\"></div>\r\n"
					+ "                                <div class=\"input_div\">\r\n"
					+ "                                    <div class=\"form_input w100p\">\r\n"
					+ "                                        <input type=\"text\" placeholder=\"기관명을 입력해주세요.\" id=\"histPlcNm\" name=\"tchistList[][histPlcNm]\">\r\n"
					+ "                                        <div class=\"input_btn_wrap\">\r\n"
					+ "                                            <button type=\"button\" class=\"btn_delete\" title=\"기관명 지우기\"></button>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                </div>\r\n"
					+ "                                <div class=\"input_div\">\r\n"
					+ "                                    <div class=\"form_input w100p tit_input\">\r\n"
					+ "                                        <p class=\"tit\">시작일</p>\r\n"
					+ "                                        <input type=\"text\" maxlength=\"8\" placeholder=\"예) 19980125\" id=\"histStDt\" name=\"tchistList[][histStDt]\" data-type=\"date\" data-old-value=\"\" oninput=\"fnc.checkMaxLength(this);\" onkeyup=\"tcCommon.checkNumberOnkeyup(this);\">\r\n"
					+ "                                        <div class=\"input_btn_wrap\">\r\n"
					+ "                                            <button type=\"button\" class=\"btn_delete\" title=\"경력사항 시작일 지우기\"></button>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                    <div class=\"form_input w100p tit_input\">\r\n"
					+ "                                        <p class=\"tit\">종료일</p>\r\n"
					+ "                                        <input type=\"text\" maxlength=\"8\" placeholder=\"예) 19980125\" id=\"histEndDt\" name=\"tchistList[][histEndDt]\" data-type=\"date\" data-old-value=\"\" oninput=\"fnc.checkMaxLength(this);\" onkeyup=\"tcCommon.checkNumberOnkeyup(this);\">\r\n"
					+ "                                        <div class=\"input_btn_wrap\">\r\n"
					+ "                                            <button type=\"button\" class=\"btn_delete\" title=\"경력사항 종료일 지우기\"></button>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                </div>\r\n"
					+ "                                <div class=\"input_div\">\r\n"
					+ "                                    <a class=\"s_color_btn gray\" href=\"javascript:\" data-value=\"hist\" onclick=\"tcRqst2HistCtrl.addInfo(this);\">\r\n"
					+ "                                        <span>추가</span>\r\n"
					+ "                                    </a>\r\n"
					+ "                                </div>\r\n"
					+ "                            </div>\r\n"
					+ "                        </div>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"row\">\r\n"
					+ "                        <div class=\"th\">\r\n"
					+ "                            <p class=\"tit f_body1\">수상내역</p>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"td\">\r\n"
					+ "                            <div class=\"input_wrap\">\r\n"
					+ "                                <div class=\"plus_div_w\"></div>\r\n"
					+ "                                <div class=\"input_div\">\r\n"
					+ "                                    <div class=\"form_input w100p\">\r\n"
					+ "                                        <input type=\"text\" id=\"awrdIssueAgncNm\" name=\"tcawrdList[][issueAgncNm]\" placeholder=\"발행기관을 입력해주세요.\">\r\n"
					+ "                                        <div class=\"input_btn_wrap\">\r\n"
					+ "                                            <button type=\"button\" class=\"btn_delete\" title=\"발행기관 지우기\"></button>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                </div>\r\n"
					+ "                                <div class=\"input_div\">\r\n"
					+ "                                    <div class=\"form_input w100p\">\r\n"
					+ "                                        <input type=\"text\" id=\"awrdIssueNm\" name=\"tcawrdList[][issueNm]\" placeholder=\"수상내역을 입력해주세요.\">\r\n"
					+ "                                        <div class=\"input_btn_wrap\">\r\n"
					+ "                                            <button type=\"button\" class=\"btn_delete\" title=\"수상내역 지우기\"></button>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                </div>\r\n"
					+ "                                <div class=\"input_div\">\r\n"
					+ "                                    <div class=\"form_input w100p tit_input\">\r\n"
					+ "                                        <p class=\"tit\">수상일</p>\r\n"
					+ "                                        <input type=\"text\" maxlength=\"8\" id=\"awrdIssueDt\" name=\"tcawrdList[][issueDt]\" placeholder=\"예) 19980125\" data-type=\"date\" data-old-value=\"\" oninput=\"fnc.checkMaxLength(this);\" onkeyup=\"tcCommon.checkNumberOnkeyup(this);\">\r\n"
					+ "                                        <div class=\"input_btn_wrap\">\r\n"
					+ "                                            <button type=\"button\" class=\"btn_delete\" title=\"수상일 지우기\"></button>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                </div>\r\n"
					+ "                                <div class=\"input_div\">\r\n"
					+ "                                    <a class=\"s_color_btn gray\" href=\"javascript:\" data-value=\"awrd\" onclick=\"tcRqst2HistCtrl.addInfo(this);\">\r\n"
					+ "                                        <span>추가</span>\r\n"
					+ "                                    </a>\r\n"
					+ "                                </div>\r\n"
					+ "                            </div>\r\n"
					+ "                        </div>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"row\">\r\n"
					+ "                        <div class=\"th\">\r\n"
					+ "                            <p class=\"tit f_body1\">자격증</p>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"td\">\r\n"
					+ "                            <div class=\"input_wrap\">\r\n"
					+ "                                <div class=\"plus_div_w\"></div>\r\n"
					+ "                                <div class=\"input_div\">\r\n"
					+ "                                    <div class=\"form_input w100p\">\r\n"
					+ "                                        <input type=\"text\" id=\"athctfIssueAgncNm\" name=\"tcauthctfList[][issueAgncNm]\" placeholder=\"발행기관을 입력해주세요.\">\r\n"
					+ "                                        <div class=\"input_btn_wrap\">\r\n"
					+ "                                            <button type=\"button\" class=\"btn_delete\" title=\"발행기관 지우기\"></button>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                </div>\r\n"
					+ "                                <div class=\"input_div\">\r\n"
					+ "                                    <div class=\"form_input w100p\">\r\n"
					+ "                                        <input type=\"text\" id=\"athctfIssueNm\" name=\"tcauthctfList[][issueNm]\" placeholder=\"자격증명을 입력해주세요.\">\r\n"
					+ "                                        <div class=\"input_btn_wrap\">\r\n"
					+ "                                            <button type=\"button\" class=\"btn_delete\" title=\"자격증명 지우기\"></button>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                </div>\r\n"
					+ "                                <div class=\"input_div\">\r\n"
					+ "                                    <div class=\"form_input w100p tit_input\">\r\n"
					+ "                                        <p class=\"tit\">취득일</p>\r\n"
					+ "                                        <input type=\"text\" maxlength=\"8\" id=\"athctfIssueDt\" name=\"tcauthctfList[][issueDt]\" placeholder=\"예) 19980125\" data-old-value=\"\" oninput=\"fnc.checkMaxLength(this);\" onkeyup=\"tcCommon.checkNumberOnkeyup(this);\">\r\n"
					+ "                                        <div class=\"input_btn_wrap\">\r\n"
					+ "                                            <button type=\"button\" class=\"btn_delete\" title=\"취득일 지우기\"></button>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                </div>\r\n"
					+ "                                <div class=\"input_div\">\r\n"
					+ "                                    <a class=\"s_color_btn gray\" href=\"javascript:\" data-value=\"athctf\" onclick=\"tcRqst2HistCtrl.addInfo(this);\">\r\n"
					+ "                                        <span>추가</span>\r\n"
					+ "                                    </a>\r\n"
					+ "                                </div>\r\n"
					+ "                            </div>\r\n"
					+ "                        </div>\r\n"
					+ "                    </div>\r\n"
					+ "                    <script type=\"text/javascript\" src=\"/resources/common/js/information/teacherApp/teacherRequest2_hist.js\"></script>\r\n"
					+ "                    <div class=\"row_tit\">\r\n"
					+ "                        <p class=\"tit\">출강 정보</p>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"row\">\r\n"
					+ "                        <div class=\"th\">\r\n"
					+ "                            <p class=\"tit f_body1 essential\">희망지점</p>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"td\">\r\n"
					+ "                            <div class=\"input_wrap\">\r\n"
					+ "                                <div class=\"input_div\">\r\n"
					+ "                                    <div class=\"form_select_w\">\r\n"
					+ "                                        <p class=\"sub_tit f_caption1\">1지망</p>\r\n"
					+ "                                        <div class=\"form_select_div change\">\r\n"
					+ "                                            <div class=\"open_area\">\r\n"
					+ "                                                <input type=\"hidden\" id=\"frstHopeBrchCd\" name=\"frstHopeBrchCd\" value=\""+ dto.getFrstHopeBrchCd() +"\"/>\r\n"
					+ "                                                <input type=\"hidden\" id=\"frstHopeBrchCdNm\" name=\"frstHopeBrchCdNm\" value=\""+ dto.getFrstHopeBrchCdNm() +"\"/>\r\n"
					+ "                                                <a class=\"btn_open\" href=\"javascript:\">\r\n"
					+ "                                                    <span>잠실점</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div class=\"dimd\"></div>\r\n"
					+ "                                            <div class=\"list_wrap\">\r\n"
					+ "                                                <div class=\"tit_area\">\r\n"
					+ "                                                    <p class=\"tit\">1지망</p>\r\n"
					+ "                                                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n"
					+ "                                                </div>\r\n"
					+ "                                                <div class=\"scroll_wrap\">\r\n"
					+ "                                                    <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>선택</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link on\" href=\"javascript:\" data-value=\"0002\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>잠실점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0001\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>본점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0013\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>강남점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0028\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>건대스타시티점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0006\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>관악점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0340\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>김포공항점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0022\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>노원점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0026\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>미아점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0010\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>영등포점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0004\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>청량리점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0344\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>인천점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0399\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>동탄점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0335\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>구리점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0008\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>분당점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0349\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>수원점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0336\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>안산점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0011\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>일산점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0334\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>중동점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0341\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>평촌점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0005\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>부산본점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0333\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>광복점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0007\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>광주점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0023\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>대구점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0012\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>대전점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0016\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>동래점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0354\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>마산점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0024\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>상인점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0027\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>센텀시티점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0015\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>울산점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0025\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>전주점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0017\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>창원점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0014\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>포항점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0361\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>롯데몰군산점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0350\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>롯데몰광명점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0009\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>부평점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0018\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>안양점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0020\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>인천점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0003\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>인천점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                </div>\r\n"
					+ "                                            </div>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                    <div class=\"form_select_w\">\r\n"
					+ "                                        <p class=\"sub_tit f_caption1\">2지망</p>\r\n"
					+ "                                        <div class=\"form_select_div change\">\r\n"
					+ "                                            <div class=\"open_area\">\r\n"
					+ "                                                <input type=\"hidden\" id=\"secHopeBrchCd\" name=\"secHopeBrchCd\" value=\""+ dto.getSecHopeBrchCd() +"\"/>\r\n"
					+ "                                                <input type=\"hidden\" id=\"secHopeBrchCdNm\" name=\"secHopeBrchCdNm\" value=\""+ dto.getSecHopeBrchCdNm() +"\"/>\r\n"
					+ "                                                <a class=\"btn_open\" href=\"javascript:\">\r\n"
					+ "                                                    <span>본점</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div class=\"dimd\"></div>\r\n"
					+ "                                            <div class=\"list_wrap\">\r\n"
					+ "                                                <div class=\"tit_area\">\r\n"
					+ "                                                    <p class=\"tit\">2지망</p>\r\n"
					+ "                                                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n"
					+ "                                                </div>\r\n"
					+ "                                                <div class=\"scroll_wrap\">\r\n"
					+ "                                                    <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>선택</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0002\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>잠실점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link on\" href=\"javascript:\" data-value=\"0001\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>본점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0013\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>강남점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0028\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>건대스타시티점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0006\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>관악점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0340\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>김포공항점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0022\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>노원점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0026\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>미아점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0010\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>영등포점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0004\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>청량리점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0344\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>인천점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0399\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>동탄점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0335\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>구리점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0008\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>분당점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0349\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>수원점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0336\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>안산점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0011\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>일산점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0334\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>중동점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0341\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>평촌점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0005\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>부산본점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0333\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>광복점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0007\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>광주점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0023\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>대구점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0012\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>대전점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0016\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>동래점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0354\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>마산점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0024\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>상인점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0027\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>센텀시티점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0015\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>울산점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0025\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>전주점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0017\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>창원점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0014\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>포항점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0361\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>롯데몰군산점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0350\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>롯데몰광명점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0009\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>부평점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0018\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>안양점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0020\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>인천점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0003\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>인천점</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                </div>\r\n"
					+ "                                            </div>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                </div>\r\n"
					+ "                            </div>\r\n"
					+ "                        </div>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"row\">\r\n"
					+ "                        <div class=\"th\">\r\n"
					+ "                            <p class=\"tit f_body1 essential\">희망분야</p>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"td\">\r\n"
					+ "                            <div class=\"input_wrap\">\r\n"
					+ "                                <div class=\"input_div wrap\">\r\n"
					+ "                                    <div id=\"firstLrDiv\" class=\"form_select_w\">\r\n"
					+ "                                        <p class=\"sub_tit f_caption1\">1지망</p>\r\n"
					+ "                                        <div class=\"form_select_div change\">\r\n"
					+ "                                            <div class=\"open_area\">\r\n"
					+ "                                                <input type=\"hidden\" id=\"frstLrclsCtegryCd\" name=\"frstLrclsCtegryCd\" value=\""+ dto.getFrstLrclsCtegryCd() +"\"/>\r\n"
					+ "                                                <input type=\"hidden\" id=\"frstLrclsCtegryCdNm\" name=\"frstLrclsCtegryCdNm\" value=\""+ dto.getFrstLrclsCtegryCdNm() +"\"/>\r\n"
					+ "                                                <a class=\"btn_open\" href=\"javascript:\">\r\n"
					+ "                                                    <span>성인강좌</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div class=\"dimd\"></div>\r\n"
					+ "                                            <div class=\"list_wrap\">\r\n"
					+ "                                                <div class=\"tit_area\">\r\n"
					+ "                                                    <p class=\"tit\">1지망</p>\r\n"
					+ "                                                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n"
					+ "                                                </div>\r\n"
					+ "                                                <div class=\"scroll_wrap\">\r\n"
					+ "                                                    <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\">\r\n"
					+ "                                                        <span>선택</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link on\" href=\"javascript:\" data-value=\"01\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\">\r\n"
					+ "                                                        <span>성인강좌</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"02\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\">\r\n"
					+ "                                                        <span>영·유아강좌</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"03\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\">\r\n"
					+ "                                                        <span>아동강좌</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                </div>\r\n"
					+ "                                            </div>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                    <div id=\"firstMdDiv\" class=\"form_select_w\">\r\n"
					+ "                                        <p class=\"sub_tit f_caption1\">1지망 상세</p>\r\n"
					+ "                                        <div class=\"form_select_div change\">\r\n"
					+ "                                            <div class=\"open_area\">\r\n"
					+ "                                                <input type=\"hidden\" id=\"frstMdclsCtegryCd\" name=\"frstMdclsCtegryCd\" value=\""+ dto.getFrstMdclsCtegryCd() +"\"/>\r\n"
					+ "                                                <a class=\"btn_open\" href=\"javascript:\">\r\n"
					+ "                                                    <span>노래</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div class=\"dimd\"></div>\r\n"
					+ "                                            <div class=\"list_wrap\">\r\n"
					+ "                                                <div class=\"tit_area\">\r\n"
					+ "                                                    <p class=\"tit\">1지망 상세</p>\r\n"
					+ "                                                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n"
					+ "                                                </div>\r\n"
					+ "                                                <div class=\"scroll_wrap\">\r\n"
					+ "                                                    <a class=\"btn_link default\" href=\"javascript:\" data-ctegry-id=\"\" data-value=\"\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\">\r\n"
					+ "                                                        <span>선택</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0101\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>공예</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link on\" data-ctegry-id=\"01\" data-value=\"0102\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>노래</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0103\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>댄스</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0104\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>드로잉</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0105\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>라이프스타일</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0106\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>악기</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0107\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>어학</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0108\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>인문학</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0109\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>재테크</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0110\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>커리어</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0111\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>쿠킹</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0112\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>피트니스</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"02\" data-value=\"0202\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n"
					+ "                                                        <span>오감발달</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"02\" data-value=\"0201\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n"
					+ "                                                        <span>창의·체험</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"02\" data-value=\"0203\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n"
					+ "                                                        <span>음악·미술</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"02\" data-value=\"0204\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n"
					+ "                                                        <span>언어·사회성</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"02\" data-value=\"0205\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n"
					+ "                                                        <span>신체활동</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"03\" data-value=\"0302\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n"
					+ "                                                        <span>신체활동</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"03\" data-value=\"0303\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n"
					+ "                                                        <span>창의·체험</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"03\" data-value=\"0301\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n"
					+ "                                                        <span>음악·미술</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"03\" data-value=\"0304\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n"
					+ "                                                        <span>언어·사회성</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                </div>\r\n"
					+ "                                            </div>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                    <div id=\"secLrDiv\" class=\"form_select_w\">\r\n"
					+ "                                        <p class=\"sub_tit f_caption1\">2지망</p>\r\n"
					+ "                                        <div class=\"form_select_div change\">\r\n"
					+ "                                            <div class=\"open_area\">\r\n"
					+ "                                                <input type=\"hidden\" id=\"secLrclsCtegryCd\" name=\"secLrclsCtegryCd\" value=\""+ dto.getSecLrclsCtegryCd() +"\"/>\r\n"
					+ "                                                <input type=\"hidden\" id=\"secLrclsCtegryCdNm\" name=\"secLrclsCtegryCdNm\" value=\""+ dto.getSecLrclsCtegryCdNm() +"\"/>\r\n"
					+ "                                                <a class=\"btn_open\" href=\"javascript:\">\r\n"
					+ "                                                    <span>성인강좌</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div class=\"dimd\"></div>\r\n"
					+ "                                            <div class=\"list_wrap\">\r\n"
					+ "                                                <div class=\"tit_area\">\r\n"
					+ "                                                    <p class=\"tit\">2지망</p>\r\n"
					+ "                                                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n"
					+ "                                                </div>\r\n"
					+ "                                                <div class=\"scroll_wrap\">\r\n"
					+ "                                                    <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\">\r\n"
					+ "                                                        <span>선택</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link on\" href=\"javascript:\" data-value=\"01\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\">\r\n"
					+ "                                                        <span>성인강좌</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"02\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\">\r\n"
					+ "                                                        <span>영·유아강좌</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"03\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\">\r\n"
					+ "                                                        <span>아동강좌</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                </div>\r\n"
					+ "                                            </div>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                    <div id=\"secMdDiv\" class=\"form_select_w\">\r\n"
					+ "                                        <p class=\"sub_tit f_caption1\">2지망 상세</p>\r\n"
					+ "                                        <div class=\"form_select_div change\">\r\n"
					+ "                                            <div class=\"open_area\">\r\n"
					+ "                                                <input type=\"hidden\" id=\"secMdclsCtegryCd\" name=\"secMdclsCtegryCd\" value=\""+ dto.getSecMdclsCtegryCd() +"\"/>\r\n"
					+ "                                                <a class=\"btn_open\" href=\"javascript:\">\r\n"
					+ "                                                    <span>댄스</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div class=\"dimd\"></div>\r\n"
					+ "                                            <div class=\"list_wrap\">\r\n"
					+ "                                                <div class=\"tit_area\">\r\n"
					+ "                                                    <p class=\"tit\">2지망 상세</p>\r\n"
					+ "                                                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n"
					+ "                                                </div>\r\n"
					+ "                                                <div class=\"scroll_wrap\">\r\n"
					+ "                                                    <a class=\"btn_link default\" href=\"javascript:\" data-ctegry-id=\"\" data-value=\"\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\">\r\n"
					+ "                                                        <span>선택</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0101\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>공예</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0102\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>노래</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link on\" data-ctegry-id=\"01\" data-value=\"0103\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>댄스</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0104\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>드로잉</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0105\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>라이프스타일</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0106\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>악기</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0107\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>어학</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0108\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>인문학</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0109\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>재테크</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0110\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>커리어</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0111\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>쿠킹</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0112\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"\">\r\n"
					+ "                                                        <span>피트니스</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"02\" data-value=\"0202\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n"
					+ "                                                        <span>오감발달</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"02\" data-value=\"0201\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n"
					+ "                                                        <span>창의·체험</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"02\" data-value=\"0203\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n"
					+ "                                                        <span>음악·미술</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"02\" data-value=\"0204\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n"
					+ "                                                        <span>언어·사회성</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"02\" data-value=\"0205\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n"
					+ "                                                        <span>신체활동</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"03\" data-value=\"0302\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n"
					+ "                                                        <span>신체활동</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"03\" data-value=\"0303\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n"
					+ "                                                        <span>창의·체험</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"03\" data-value=\"0301\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n"
					+ "                                                        <span>음악·미술</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" data-ctegry-id=\"03\" data-value=\"0304\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n"
					+ "                                                        <span>언어·사회성</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                </div>\r\n"
					+ "                                            </div>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                </div>\r\n"
					+ "                            </div>\r\n"
					+ "                        </div>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"row\">\r\n"
					+ "                        <div class=\"th\">\r\n"
					+ "                            <p class=\"tit f_body1 essential\">희망요일</p>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"td small\">\r\n"
					+ "                            <div class=\"filter_btn_list\" id=\"hopeDaywValDiv\">\r\n"
					+ "                                <input type=\"hidden\" id=\"hopeDaywVal\" name=\"hopeDaywVal\" value=\""+ dto.getHopeDaywVal() +"\"/>\r\n"
					+ "                                <input type=\"hidden\" id=\"hopeDaywValNm\" name=\"hopeDaywValNm\" value=\""+ dto.getHopeDaywValNm() +"\"/>\r\n"
					+ "                                <a href=\"javascript:\" class=\"total btn \" data-value=\"\" onclick=\"teacherRequestCtrl.selOptClick_dayw(this);\">\r\n"
					+ "                                    <span>전체</span>\r\n"
					+ "                                </a>\r\n"
					+ "                                <a class=\"btn on\" href=\"javascript:\" data-value=\"1\" onclick=\"teacherRequestCtrl.selOptClick_dayw(this);\">\r\n"
					+ "                                    <span>월요일</span>\r\n"
					+ "                                </a>\r\n"
					+ "                                <a class=\"btn on\" href=\"javascript:\" data-value=\"2\" onclick=\"teacherRequestCtrl.selOptClick_dayw(this);\">\r\n"
					+ "                                    <span>화요일</span>\r\n"
					+ "                                </a>\r\n"
					+ "                                <a class=\"btn on\" href=\"javascript:\" data-value=\"3\" onclick=\"teacherRequestCtrl.selOptClick_dayw(this);\">\r\n"
					+ "                                    <span>수요일</span>\r\n"
					+ "                                </a>\r\n"
					+ "                                <a class=\"btn on\" href=\"javascript:\" data-value=\"4\" onclick=\"teacherRequestCtrl.selOptClick_dayw(this);\">\r\n"
					+ "                                    <span>목요일</span>\r\n"
					+ "                                </a>\r\n"
					+ "                                <a class=\"btn on\" href=\"javascript:\" data-value=\"5\" onclick=\"teacherRequestCtrl.selOptClick_dayw(this);\">\r\n"
					+ "                                    <span>금요일</span>\r\n"
					+ "                                </a>\r\n"
					+ "                                <a class=\"btn on\" href=\"javascript:\" data-value=\"6\" onclick=\"teacherRequestCtrl.selOptClick_dayw(this);\">\r\n"
					+ "                                    <span>토요일</span>\r\n"
					+ "                                </a>\r\n"
					+ "                                <a class=\"btn on\" href=\"javascript:\" data-value=\"7\" onclick=\"teacherRequestCtrl.selOptClick_dayw(this);\">\r\n"
					+ "                                    <span>일요일</span>\r\n"
					+ "                                </a>\r\n"
					+ "                            </div>\r\n"
					+ "                        </div>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"row\">\r\n"
					+ "                        <div class=\"th\">\r\n"
					+ "                            <p class=\"tit f_body1 essential\">희망시간</p>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"td\">\r\n"
					+ "                            <div class=\"input_wrap\">\r\n"
					+ "                                <div class=\"input_div time\">\r\n"
					+ "                                    <div class=\"form_select_w\">\r\n"
					+ "                                        <p class=\"sub_tit f_caption1\">시작</p>\r\n"
					+ "                                        <div class=\"form_select_div change\">\r\n"
					+ "                                            <div class=\"open_area\">\r\n"
					+ "                                                <input type=\"hidden\" id=\"hopeStHh\" name=\"hopeStHh\" value=\""+ dto.getHopeStHh() +"\"/>\r\n"
					+ "                                                <a class=\"btn_open\" href=\"javascript:\">\r\n"
					+ "                                                    <span>00시</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div class=\"dimd\"></div>\r\n"
					+ "                                            <div class=\"list_wrap\">\r\n"
					+ "                                                <div class=\"tit_area\">\r\n"
					+ "                                                    <p class=\"tit\">시작 시</p>\r\n"
					+ "                                                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n"
					+ "                                                </div>\r\n"
					+ "                                                <div class=\"scroll_wrap\">\r\n"
					+ "                                                    <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link on\" href=\"javascript:\" data-value=\"00\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>00시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"01\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>01시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"02\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>02시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"03\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>03시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"04\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>04시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"05\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>05시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"06\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>06시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"07\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>07시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"08\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>08시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"09\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>09시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"10\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>10시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"11\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>11시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"12\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>12시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"13\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>13시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"14\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>14시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"15\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>15시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"16\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>16시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"17\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>17시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"18\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>18시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"19\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>19시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"20\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>20시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"21\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>21시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"22\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>22시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"23\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>23시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"24\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>24시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                </div>\r\n"
					+ "                                            </div>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                    <div class=\"form_select_w\">\r\n"
					+ "                                        <div class=\"form_select_div change\">\r\n"
					+ "                                            <div class=\"open_area\">\r\n"
					+ "                                                <input type=\"hidden\" id=\"hopeStMi\" name=\"hopeStMi\" value=\""+ dto.getHopeStMi() +"\"/>\r\n"
					+ "                                                <a class=\"btn_open\" href=\"javascript:\">\r\n"
					+ "                                                    <span>05분</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div class=\"dimd\"></div>\r\n"
					+ "                                            <div class=\"list_wrap\">\r\n"
					+ "                                                <div class=\"tit_area\">\r\n"
					+ "                                                    <p class=\"tit\">시작 분</p>\r\n"
					+ "                                                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n"
					+ "                                                </div>\r\n"
					+ "                                                <div class=\"scroll_wrap\">\r\n"
					+ "                                                    <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"00\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>00분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link on\" href=\"javascript:\" data-value=\"05\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>05분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"10\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>10분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"15\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>15분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"20\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>20분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"25\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>25분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"30\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>30분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"35\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>35분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"40\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>40분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"45\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>45분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"50\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>50분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"55\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>55분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                </div>\r\n"
					+ "                                            </div>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                    <p class=\"to_txt\">~</p>\r\n"
					+ "                                    <div class=\"form_select_w\">\r\n"
					+ "                                        <p class=\"sub_tit f_caption1\">종료</p>\r\n"
					+ "                                        <div class=\"form_select_div change\">\r\n"
					+ "                                            <div class=\"open_area\">\r\n"
					+ "                                                <input type=\"hidden\" id=\"hopeEndHh\" name=\"hopeEndHh\" value=\""+ dto.getHopeEndHh() +"\"/>\r\n"
					+ "                                                <a class=\"btn_open\" href=\"javascript:\">\r\n"
					+ "                                                    <span>01시</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div class=\"dimd\"></div>\r\n"
					+ "                                            <div class=\"list_wrap\">\r\n"
					+ "                                                <div class=\"tit_area\">\r\n"
					+ "                                                    <p class=\"tit\">종료 시</p>\r\n"
					+ "                                                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n"
					+ "                                                </div>\r\n"
					+ "                                                <div class=\"scroll_wrap\">\r\n"
					+ "                                                    <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"00\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>00시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link on\" href=\"javascript:\" data-value=\"01\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>01시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"02\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>02시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"03\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>03시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"04\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>04시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"05\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>05시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"06\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>06시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"07\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>07시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"08\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>08시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"09\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>09시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"10\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>10시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"11\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>11시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"12\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>12시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"13\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>13시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"14\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>14시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"15\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>15시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"16\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>16시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"17\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>17시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"18\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>18시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"19\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>19시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"20\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>20시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"21\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>21시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"22\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>22시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"23\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>23시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"24\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>24시</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                </div>\r\n"
					+ "                                            </div>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                    <div class=\"form_select_w\">\r\n"
					+ "                                        <div class=\"form_select_div change\">\r\n"
					+ "                                            <div class=\"open_area\">\r\n"
					+ "                                                <input type=\"hidden\" id=\"hopeEndMi\" name=\"hopeEndMi\" value=\""+ dto.getHopeEndMi() +"\"/>\r\n"
					+ "                                                <a class=\"btn_open\" href=\"javascript:\">\r\n"
					+ "                                                    <span>20분</span>\r\n"
					+ "                                                </a>\r\n"
					+ "                                            </div>\r\n"
					+ "                                            <div class=\"dimd\"></div>\r\n"
					+ "                                            <div class=\"list_wrap\">\r\n"
					+ "                                                <div class=\"tit_area\">\r\n"
					+ "                                                    <p class=\"tit\">종료 분</p>\r\n"
					+ "                                                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n"
					+ "                                                </div>\r\n"
					+ "                                                <div class=\"scroll_wrap\">\r\n"
					+ "                                                    <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"00\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>00분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"05\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>05분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"10\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>10분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"15\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>15분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link on\" href=\"javascript:\" data-value=\"20\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>20분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"25\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>25분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"30\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>30분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"35\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>35분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"40\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>40분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"45\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>45분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"50\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>50분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"55\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                                                        <span>55분</span>\r\n"
					+ "                                                    </a>\r\n"
					+ "                                                </div>\r\n"
					+ "                                            </div>\r\n"
					+ "                                        </div>\r\n"
					+ "                                    </div>\r\n"
					+ "                                </div>\r\n"
					+ "                            </div>\r\n"
					+ "                        </div>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"row\">\r\n"
					+ "                        <div class=\"th\">\r\n"
					+ "                            <p class=\"tit f_body1\">강좌 소개</p>\r\n"
					+ "                        </div>\r\n"
					+ "                        <div class=\"td\">\r\n"
					+ "                            <div class=\"form_textarea\">\r\n"
					+ "                                <div class=\"wrap_for_msg\">\r\n"
					+ "                                    <textarea name=\"lectIntrdnCont\" id=\"\" cols=\"\" rows=\"\" placeholder=\"본인의 강좌에 대한 소개 내용을 간략히 작성해주세요.\" data-maxlength=\"200\" onkeyup=\"tcCommon.textareaOnkeyup(this)\">"+ dto.getLectIntrdnCont() +"</textarea>\r\n"
					+ "                                </div>\r\n"
					+ "                                <p class=\"check_byte\">\r\n"
					+ "                                    <span class=\"r_byte\">0</span>\r\n"
					+ "                                    /\r\n"
					+ "                                    <span class=\"l_byte\">200</span>\r\n"
					+ "                                </p>\r\n"
					+ "                            </div>\r\n"
					+ "                        </div>\r\n"
					+ "                    </div>\r\n"
					+ "                </div>\r\n"
					+ "                <div class=\"dot_txt_box\">\r\n"
					+ "                    <p class=\"f_body1\">유의사항</p>\r\n"
					+ "                    <!-- 2023-02-13 이모지 삭제 -->\r\n"
					+ "                    <p class=\"dot_txt\">위 등록한 내용과 사실이 다를 경우 강좌개설이 취소될 수 있습니다.</p>\r\n"
					+ "                    <p class=\"dot_txt\">‘임시저장’은 수정이 가능한 상태이며 문화센터에는 지원되지 않습니다. ‘지원하기’를 클릭해야 정상적으로 지원 완료됩니다.</p>\r\n"
					+ "                    <p class=\"dot_txt\">학력/경력, 자격증, 수상내역, 강의계획서 입력을 위해서는 선택정보 수집이용에 동의하셔야 합니다.</p>\r\n"
					+ "                    <!-- 2023-01-16 텍스트 수정 -->\r\n"
					+ "                </div>\r\n"
					+ "                <div class=\"flex_btn_wrap\">\r\n"
					+ "                    <a class=\"border_btn\" href=\"javascript:\" onclick=\"teacherRequestCtrl.cancel(2);\">\r\n"
					+ "                        <span>지원취소</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"border_btn\" href=\"javascript:\" onclick=\"teacherRequestCtrl.save();\">\r\n"
					+ "                        <span>임시저장</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"b_color_btn\" href=\"javascript:\" onclick=\"teacherRequestCtrl.next(2);\">\r\n"
					+ "                        <span>다음</span>\r\n"
					+ "                    </a>\r\n"
					+ "                </div>\r\n"
					+ "            </div>\r\n"
					+ "        </form>\r\n"
					+ "    </div>\r\n"
					+ "</div>\r\n"
					+ "<div id=\"eduInfo\" class=\"plus_div\" style=\"display: none;\">\r\n"
					+ "    <div class=\"input_div w100p\">\r\n"
					+ "        <div class=\"form_select_div change\">\r\n"
					+ "            <div class=\"open_area\">\r\n"
					+ "                <input type=\"hidden\" name=\"tceduList[][schlClCd]\" value=\"$schlClCd$\"/>\r\n"
					+ "                <a class=\"btn_open\" href=\"javascript:\">\r\n"
					+ "                    <span>$schlClCdNm$</span>\r\n"
					+ "                </a>\r\n"
					+ "            </div>\r\n"
					+ "            <div class=\"dimd\"></div>\r\n"
					+ "            <div class=\"list_wrap\">\r\n"
					+ "                <div class=\"tit_area\">\r\n"
					+ "                    <p class=\"tit\">학교</p>\r\n"
					+ "                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n"
					+ "                </div>\r\n"
					+ "                <div class=\"scroll_wrap\">\r\n"
					+ "                    <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>학교 선택</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link\" href=\"javascript:\" data-value=\"0\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>고등학교</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link\" href=\"javascript:\" data-value=\"1\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>전문대학교</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link\" href=\"javascript:\" data-value=\"2\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>대학교</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link\" href=\"javascript:\" data-value=\"3\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>대학원(석사)</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link\" href=\"javascript:\" data-value=\"4\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>대학원(박사)</span>\r\n"
					+ "                    </a>\r\n"
					+ "                </div>\r\n"
					+ "            </div>\r\n"
					+ "        </div>\r\n"
					+ "        <div class=\"form_select_div change\">\r\n"
					+ "            <div class=\"open_area\">\r\n"
					+ "                <input type=\"hidden\" name=\"tceduList[][grdtClCd]\" value=\"$grdtClCd$\"/>\r\n"
					+ "                <a class=\"btn_open\" href=\"javascript:\">\r\n"
					+ "                    <span>$grdtClCdNm$</span>\r\n"
					+ "                </a>\r\n"
					+ "            </div>\r\n"
					+ "            <div class=\"dimd\"></div>\r\n"
					+ "            <div class=\"list_wrap\">\r\n"
					+ "                <div class=\"tit_area\">\r\n"
					+ "                    <p class=\"tit\">졸업상태</p>\r\n"
					+ "                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n"
					+ "                </div>\r\n"
					+ "                <div class=\"scroll_wrap\">\r\n"
					+ "                    <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>졸업상태 선택</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>졸업</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>수료</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>중퇴</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"3\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>재학중</span>\r\n"
					+ "                    </a>\r\n"
					+ "                </div>\r\n"
					+ "            </div>\r\n"
					+ "        </div>\r\n"
					+ "        <div class=\"form_select_div change\">\r\n"
					+ "            <div class=\"open_area\">\r\n"
					+ "                <input type=\"hidden\" name=\"tceduList[][yy]\" value=\"$yy$\"/>\r\n"
					+ "                <a class=\"btn_open\" href=\"javascript:\">\r\n"
					+ "                    <span>$yyNm$</span>\r\n"
					+ "                </a>\r\n"
					+ "            </div>\r\n"
					+ "            <div class=\"dimd\"></div>\r\n"
					+ "            <div class=\"list_wrap\">\r\n"
					+ "                <div class=\"tit_area\">\r\n"
					+ "                    <p class=\"tit\">년도</p>\r\n"
					+ "                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n"
					+ "                </div>\r\n"
					+ "                <div class=\"scroll_wrap\">\r\n"
					+ "                    <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>년도 선택</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2023\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2023</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2022\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2022</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2021\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2021</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2020\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2020</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2019\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2019</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2018\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2018</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2017\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2017</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2016\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2016</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2015\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2015</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2014\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2014</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2013\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2013</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2012\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2012</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2011\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2011</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2010\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2010</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2009\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2009</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2008\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2008</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2007\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2007</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2006\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2006</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2005\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2005</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2004\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2004</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2003\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2003</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2002\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2002</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2001\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2001</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2000\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>2000</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1999\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1999</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1998\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1998</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1997\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1997</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1996\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1996</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1995\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1995</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1994\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1994</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1993\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1993</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1992\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1992</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1991\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1991</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1990\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1990</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1989\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1989</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1988\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1988</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1987\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1987</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1986\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1986</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1985\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1985</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1984\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1984</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1983\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1983</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1982\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1982</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1981\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1981</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1980\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1980</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1979\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1979</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1978\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1978</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1977\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1977</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1976\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1976</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1975\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1975</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1974\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1974</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1973\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1973</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1972\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1972</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1971\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1971</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1970\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1970</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1969\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1969</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1968\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1968</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1967\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1967</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1966\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1966</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1965\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1965</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1964\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1964</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1963\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1963</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1962\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1962</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1961\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1961</span>\r\n"
					+ "                    </a>\r\n"
					+ "                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1960\" onclick=\"tcCommon.selOptClick(this);\">\r\n"
					+ "                        <span>1960</span>\r\n"
					+ "                    </a>\r\n"
					+ "                </div>\r\n"
					+ "            </div>\r\n"
					+ "        </div>\r\n"
					+ "    </div>\r\n"
					+ "    <div class=\"input_div\">\r\n"
					+ "        <div class=\"form_input w100p\">\r\n"
					+ "            <input type=\"text\" placeholder=\"학교명을 입력해주세요.\" name=\"tceduList[][schlNm]\" value=\"$schlNm$\">\r\n"
					+ "            <div class=\"input_btn_wrap\">\r\n"
					+ "                <button type=\"button\" class=\"btn_delete\" title=\"학교명 지우기\"></button>\r\n"
					+ "            </div>\r\n"
					+ "        </div>\r\n"
					+ "    </div>\r\n"
					+ "    <div class=\"input_div\">\r\n"
					+ "        <div class=\"form_input w100p\">\r\n"
					+ "            <input type=\"text\" placeholder=\"전공을 입력해주세요.\" name=\"tceduList[][mjrNm]\" value=\"$mjrNm$\">\r\n"
					+ "            <div class=\"input_btn_wrap\">\r\n"
					+ "                <button type=\"button\" class=\"btn_delete\" title=\"전공 지우기\"></button>\r\n"
					+ "            </div>\r\n"
					+ "        </div>\r\n"
					+ "    </div>\r\n"
					+ "    <div class=\"delete_btn\">\r\n"
					+ "        <a href=\"javascript:\" class=\"btn del_info\">\r\n"
					+ "            <span>삭제하기</span>\r\n"
					+ "        </a>\r\n"
					+ "    </div>\r\n"
					+ "</div>\r\n"
					+ "<div id=\"histInfo\" class=\"plus_div\" style=\"display: none;\">\r\n"
					+ "    <div class=\"input_div\">\r\n"
					+ "        <div class=\"form_input w100p\">\r\n"
					+ "            <input type=\"text\" placeholder=\"기관명을 입력해주세요.\" name=\"tchistList[][histPlcNm]\" value=\"$histPlcNm$\">\r\n"
					+ "            <div class=\"input_btn_wrap\">\r\n"
					+ "                <button type=\"button\" class=\"btn_delete\" title=\"기관명 지우기\"></button>\r\n"
					+ "            </div>\r\n"
					+ "        </div>\r\n"
					+ "    </div>\r\n"
					+ "    <div class=\"input_div\">\r\n"
					+ "        <div class=\"form_input w100p tit_input\">\r\n"
					+ "            <p class=\"tit\">시작일</p>\r\n"
					+ "            <input type=\"text\" maxlength=\"8\" placeholder=\"예) 19980125\" name=\"tchistList[][histStDt]\" data-type=\"date\" data-old-value=\"$histStDt$\" value=\"$histStDt$\" oninput=\"fnc.checkMaxLength(this);\" onkeyup=\"tcCommon.checkNumberOnkeyup(this);\">\r\n"
					+ "            <div class=\"input_btn_wrap\">\r\n"
					+ "                <button type=\"button\" class=\"btn_delete\" title=\"경력사항 시작일 지우기\"></button>\r\n"
					+ "            </div>\r\n"
					+ "        </div>\r\n"
					+ "        <div class=\"form_input w100p tit_input\">\r\n"
					+ "            <p class=\"tit\">종료일</p>\r\n"
					+ "            <input type=\"text\" maxlength=\"8\" placeholder=\"예) 19980125\" name=\"tchistList[][histEndDt]\" data-type=\"date\" data-old-value=\"$histEndDt$\" value=\"$histEndDt$\" oninput=\"fnc.checkMaxLength(this);\" onkeyup=\"tcCommon.checkNumberOnkeyup(this);\">\r\n"
					+ "            <div class=\"input_btn_wrap\">\r\n"
					+ "                <button type=\"button\" class=\"btn_delete\" title=\"경력사항 종료일 지우기\"></button>\r\n"
					+ "            </div>\r\n"
					+ "        </div>\r\n"
					+ "        <div class=\"delete_btn\">\r\n"
					+ "            <a href=\"javascript:\" class=\"btn del_info\">\r\n"
					+ "                <span>삭제하기</span>\r\n"
					+ "            </a>\r\n"
					+ "        </div>\r\n"
					+ "    </div>\r\n"
					+ "</div>\r\n"
					+ "<div id=\"awrdInfo\" class=\"plus_div\" style=\"display: none;\">\r\n"
					+ "    <div class=\"input_div\">\r\n"
					+ "        <div class=\"form_input w100p\">\r\n"
					+ "            <input type=\"text\" placeholder=\"발행기관을 입력해주세요.\" name=\"tcawrdList[][issueAgncNm]\" value=\"$issueAgncNm$\">\r\n"
					+ "            <div class=\"input_btn_wrap\">\r\n"
					+ "                <button type=\"button\" class=\"btn_delete\" title=\"발행기관 지우기\"></button>\r\n"
					+ "            </div>\r\n"
					+ "        </div>\r\n"
					+ "    </div>\r\n"
					+ "    <div class=\"input_div\">\r\n"
					+ "        <div class=\"form_input w100p\">\r\n"
					+ "            <input type=\"text\" placeholder=\"수상내역을 입력해주세요.\" name=\"tcawrdList[][issueNm]\" value=\"$issueNm$\">\r\n"
					+ "            <div class=\"input_btn_wrap\">\r\n"
					+ "                <button type=\"button\" class=\"btn_delete\" title=\"수상내역 지우기\"></button>\r\n"
					+ "            </div>\r\n"
					+ "        </div>\r\n"
					+ "    </div>\r\n"
					+ "    <div class=\"input_div\">\r\n"
					+ "        <div class=\"form_input w100p tit_input\">\r\n"
					+ "            <p class=\"tit\">수상일</p>\r\n"
					+ "            <input type=\"text\" maxlength=\"8\" placeholder=\"예) 19980125\" name=\"tcawrdList[][issueDt]\" data-type=\"date\" data-old-value=\"$awrdIssueDt$\" value=\"$awrdIssueDt$\" oninput=\"fnc.checkMaxLength(this);\" onkeyup=\"tcCommon.checkNumberOnkeyup(this);\">\r\n"
					+ "            <div class=\"input_btn_wrap\">\r\n"
					+ "                <button type=\"button\" class=\"btn_delete\" title=\"수상일 지우기\"></button>\r\n"
					+ "            </div>\r\n"
					+ "        </div>\r\n"
					+ "    </div>\r\n"
					+ "    <div class=\"delete_btn\">\r\n"
					+ "        <a href=\"javascript:\" class=\"btn del_info\">\r\n"
					+ "            <span>삭제하기</span>\r\n"
					+ "        </a>\r\n"
					+ "    </div>\r\n"
					+ "</div>\r\n"
					+ "<div id=\"athctfInfo\" class=\"plus_div\" style=\"display: none;\">\r\n"
					+ "    <div class=\"input_div\">\r\n"
					+ "        <div class=\"form_input w100p\">\r\n"
					+ "            <input type=\"text\" placeholder=\"발행기관을 입력해주세요.\" name=\"tcauthctfList[][issueAgncNm]\" value=\"$issueAgncNm$\">\r\n"
					+ "            <div class=\"input_btn_wrap\">\r\n"
					+ "                <button type=\"button\" class=\"btn_delete\" title=\"발행기관 지우기\"></button>\r\n"
					+ "            </div>\r\n"
					+ "        </div>\r\n"
					+ "    </div>\r\n"
					+ "    <div class=\"input_div\">\r\n"
					+ "        <div class=\"form_input w100p\">\r\n"
					+ "            <input type=\"text\" placeholder=\"자격증명을 입력해주세요.\" name=\"tcauthctfList[][issueNm]\" value=\"$issueNm$\">\r\n"
					+ "            <div class=\"input_btn_wrap\">\r\n"
					+ "                <button type=\"button\" class=\"btn_delete\" title=\"자격증명 지우기\"></button>\r\n"
					+ "            </div>\r\n"
					+ "        </div>\r\n"
					+ "    </div>\r\n"
					+ "    <div class=\"input_div\">\r\n"
					+ "        <div class=\"form_input w100p tit_input\">\r\n"
					+ "            <p class=\"tit\">취득일</p>\r\n"
					+ "            <input type=\"text\" maxlength=\"8\" placeholder=\"예) 19980125\" name=\"tcauthctfList[][issueDt]\" data-type=\"date\" data-old-value=\"$issueDt$\" value=\"$issueDt$\" oninput=\"fnc.checkMaxLength(this);\" onkeyup=\"tcCommon.checkNumberOnkeyup(this);\">\r\n"
					+ "            <div class=\"input_btn_wrap\">\r\n"
					+ "                <button type=\"button\" class=\"btn_delete\" title=\"취득일 지우기\"></button>\r\n"
					+ "            </div>\r\n"
					+ "        </div>\r\n"
					+ "    </div>\r\n"
					+ "    <div class=\"delete_btn\">\r\n"
					+ "        <a href=\"javascript:\" class=\"btn del_info\">\r\n"
					+ "            <span>삭제하기</span>\r\n"
					+ "        </a>\r\n"
					+ "    </div>\r\n"
					+ "</div>");		
		
			
		}else if (okMap.get("errorCode").equals(3)) {//이미 강사인사람
			
			
			html.append("<script>\r\n");
			html.append("    function init_alert() {\r\n");
			html.append("        alert(\"이미 강사 권한이 있습니다.\");\r\n");
			html.append("    }\r\n");
			html.append("</script>\r\n");		
			html.append("</script>\r\n");
			html.append("<div class=\"for_padding\" data-step=\"3\">\r\n");
			html.append("    <div class=\"scroll_area\">\r\n");
			html.append("        <div class=\"process_wrap\" title=\"3. 제출완료\">\r\n");
			html.append("            <div class=\"process_div check two_law\">\r\n");
			html.append("                <p class=\"num\"><span>1</span></p>\r\n");
			html.append("                <p class=\"txt\">개인정보 활용 <br class=\"only_mobile\" />동의</p>\r\n");
			html.append("            </div>\r\n");
			html.append("            <div class=\"process_div check\">\r\n");
			html.append("                <p class=\"num\"><span>2</span></p>\r\n");
			html.append("                <p class=\"txt\">강사정보 작성</p>\r\n");
			html.append("            </div>\r\n");
			html.append("            <div class=\"process_div on\">\r\n");
			html.append("                <p class=\"num\"><span>3</span></p>\r\n");
			html.append("                <p class=\"txt\">제출완료</p>\r\n");
			html.append("            </div>\r\n");
			html.append("        </div>\r\n");
			html.append("        <div class=\"dot_txt_box complete\">\r\n");
			html.append("            <!-- <p class=\"icon\">🎉</p> -->\r\n");
			html.append("            <p class=\"f_body1\">이미 강사 권한이 있습니다.</p>\r\n");
			html.append("            <p class=\"f_body4\">강의 관련 개별 연락 예정입니다. 감사합니다.</p><!-- 2023-01-16 텍스트 수정 -->\r\n");
			html.append("        </div>\r\n");
			html.append("        <div class=\"flex_btn_wrap\">\r\n");
			html.append("            <a href=\"javascript:\" class=\"border_btn\" onclick=\"teacherRequestCtrl.close();\"><span>닫기</span></a>\r\n");
			html.append("        </div>\r\n");
			html.append("    </div>\r\n");
			html.append("</div>");
			
		}else {
			
			html.append("<div class=\"for_padding\" data-step=\"1\">\r\n");
			html.append("    <div class=\"scroll_area\">\r\n");
			html.append("        <div class=\"process_wrap\" title=\"1. 개인정보 활용 동의\">\r\n");
			html.append("            <div class=\"process_div on two_law\">\r\n");
			html.append("                <p class=\"num\"><span>1</span></p>\r\n");
			html.append("                <p class=\"txt\">개인정보 활용 <br class=\"only_mobile\" />동의</p>\r\n");
			html.append("            </div>\r\n");
			html.append("            <div class=\"process_div\">\r\n");
			html.append("                <p class=\"num\"><span>2</span></p>\r\n");
			html.append("                <p class=\"txt\">강사정보 작성</p>\r\n");
			html.append("            </div>\r\n");
			html.append("            <div class=\"process_div\">\r\n");
			html.append("                <p class=\"num\"><span>3</span></p>\r\n");
			html.append("                <p class=\"txt\">제출완료</p>\r\n");
			html.append("            </div>\r\n");
			html.append("        </div>\r\n");
			html.append("        <div class=\"dot_txt_box\">\r\n");
			html.append("            <p class=\"f_body1\">강사지원시 알려드려요</p><!-- 2023-01-10 이모지 삭제 -->\r\n");
			html.append("            <p class=\"dot_txt\">강사 지원을 위한 개인정보 수집 및 활용 동의 철회는 지원점 (출강 희망점) 담당자와 유선 및 1:1 상담을 통해서 본인\r\n");
			html.append("                확인 절차를 거친 후 동의 철회가 가능합니다.</p>\r\n");
			html.append("            <p class=\"dot_txt\">강사 지원 시에는 해당점과 강사 지원 및 강좌 관련 사항을 협의하시기 바랍니다.</p>\r\n");
			html.append("            <p class=\"dot_txt\">아래 개인 정보에 대해서는 강좌 운영 외 다른 용도로는 사용하지 않습니다.</p>\r\n");
			html.append("        </div>\r\n");
			html.append("        <div class=\"sub_inner\">\r\n");
			html.append("            <div class=\"sub_tit_area\">\r\n");
			html.append("                <div class=\"left\">\r\n");
			html.append("                    <p class=\"pop_sec_tit\">개인정보 수집·이용 동의서</p>\r\n");
			html.append("                </div>\r\n");
			html.append("                <div class=\"right\">\r\n");
			html.append("                    <!-- <p class=\"f_caption2\"><span class=\"red_txt\">*</span> 표시는 필수 기재 항목입니다.</p> // 2023-01-16 삭제 -->\r\n");
			html.append("                </div>\r\n");
			html.append("            </div>\r\n");
			html.append("            <div class=\"form_table_w\">\r\n");
			html.append("                <form id=\"request_form\">\r\n");
			html.append("                    <div class=\"table_div\">\r\n");
			html.append("                        <div class=\"form_checkbox agree_chk\">\r\n");
			html.append("                            <input type=\"checkbox\" id=\"agreeChk1\" name=\"prinfClctAgrYn\" value=\"Y\">\r\n");
			html.append("                            <label for=\"agreeChk1\">개인정보 수집항목, 수집목적 및 보유/이용 기간 <span class=\"red_txt\">(필수)</span></label><!-- 2023-01-16 수정 -->\r\n");
			html.append("                        </div>\r\n");
			html.append("                        <div class=\"form_table gray\">\r\n");
			html.append("                            <table>\r\n");
			html.append("                                <caption>테이블 캡션 내용이 들어갑니다.</caption>\r\n");
			html.append("                                <colgroup>\r\n");
			html.append("                                    <col width=\"33%\">\r\n");
			html.append("                                    <col width=\"33%\">\r\n");
			html.append("                                    <col width=\"34%\">\r\n");
			html.append("                                </colgroup>\r\n");
			html.append("                                <thead>\r\n");
			html.append("                                    <tr>\r\n");
			html.append("                                        <th>수집 항목</th>\r\n");
			html.append("                                        <th>목적</th>\r\n");
			html.append("                                        <th>보유 및 이용 기간</th>\r\n");
			html.append("                                    </tr>\r\n");
			html.append("                                </thead>\r\n");
			html.append("                                <tbody>\r\n");
			html.append("                                    <tr>\r\n");
			html.append("                                        <td rowspan=\"2\">\r\n");
			html.append("                                            <p class=\"f_body2\">프로필 사진, 강사명, 생년월일, 휴대전화, 이메일, 주소</p><!-- 2022-12-08, 2023-01-16, 2023-03-13 수정 -->\r\n");
			html.append("                                        </td>\r\n");
			html.append("                                        <td rowspan=\"2\">\r\n");
			html.append("                                            <p class=\"f_body2\">강사지원 확인 및 강사료 지급 등을 위한 개인 식별</p><!-- 2023-01-16 텍스트 수정 -->\r\n");
			html.append("                                        </td>\r\n");
			html.append("                                        <td>\r\n");
			html.append("                                            <p class=\"bold\">지원자 요청 시 즉시 파기</p><!-- 2023-03-13 class 수정 -->\r\n");
			html.append("                                        </td>\r\n");
			html.append("                                    </tr>\r\n");
			html.append("                                    <tr>\r\n");
			html.append("                                        <td>\r\n");
			html.append("                                            <p class=\"bold\">강사 지원 후 강좌 미진행時 6개월 까지</p><!-- 2023-03-13 class 수정 -->\r\n");
			html.append("                                        </td>\r\n");
			html.append("                                    </tr>\r\n");
			html.append("                                </tbody>\r\n");
			html.append("                            </table>\r\n");
			html.append("                        </div>\r\n");
			html.append("                        <div class=\"caption_txt_w\">\r\n");
			html.append("                            <p class=\"dot_txt\">본인은 롯데백화점 문화센터에 제휴 신청을 희망하며, 개인정보의 수집 내용을 이해하고 동의합니다.</p>\r\n");
			html.append("                            <p class=\"dot_txt\">개인정보 수집 동의 거부하실 수 있으며, 다만 이 경우 강사지원이 제한 됩니다.</p>\r\n");
			html.append("                        </div>\r\n");
			html.append("                    </div>\r\n");
			html.append("                    <div class=\"table_div\">\r\n");
			html.append("                        <div class=\"form_checkbox agree_chk\">\r\n");
			html.append("                            <input type=\"checkbox\" id=\"agreeChk2\" name=\"carrInfoClctAgrYn\" value=\"Y\">\r\n");
			html.append("                            <label for=\"agreeChk2\">개인정보 수집항목, 수집목적 및 보유/이용 기간 (선택)</label><!-- 2023-01-16 수정 -->\r\n");
			html.append("                        </div>\r\n");
			html.append("                        <div class=\"form_table gray\">\r\n");
			html.append("                            <table>\r\n");
			html.append("                                <caption>테이블 캡션 내용이 들어갑니다.</caption>\r\n");
			html.append("                                <colgroup>\r\n");
			html.append("                                    <col width=\"33%\">\r\n");
			html.append("                                    <col width=\"33%\">\r\n");
			html.append("                                    <col width=\"34%\">\r\n");
			html.append("                                </colgroup>\r\n");
			html.append("                                <thead>\r\n");
			html.append("                                    <tr>\r\n");
			html.append("                                        <th>수집 항목</th>\r\n");
			html.append("                                        <th>목적</th>\r\n");
			html.append("                                        <th>보유 및 이용 기간</th>\r\n");
			html.append("                                    </tr>\r\n");
			html.append("                                </thead>\r\n");
			html.append("                                <tbody>\r\n");
			html.append("                                    <tr>\r\n");
			html.append("                                        <td rowspan=\"2\">\r\n");
			html.append("                                            <p class=\"f_body2\">학력사항, 경력사항, 수상내역, 자격증</p><!-- 2023-02-15, 2023-03-13 텍스트 수정 -->\r\n");
			html.append("                                        </td>\r\n");
			html.append("                                        <td rowspan=\"2\">\r\n");
			html.append("                                            <p class=\"f_body2\">강사 출강 자격 검토 및 고객대상 강사정보 제공</p><!-- 2023-01-16 텍스트 수정 -->\r\n");
			html.append("                                        </td>\r\n");
			html.append("                                        <td>\r\n");
			html.append("                                            <p class=\"bold\">지원자 요청 시 즉시 파기</p><!-- 2023-03-13 class 수정 -->\r\n");
			html.append("                                        </td>\r\n");
			html.append("                                    </tr>\r\n");
			html.append("                                    <tr>\r\n");
			html.append("                                        <td>\r\n");
			html.append("                                            <p class=\"bold\">강사 지원 후 강좌 미진행時 6개월 까지</p><!-- 2023-03-13 class 수정 -->\r\n");
			html.append("                                        </td>\r\n");
			html.append("                                    </tr>\r\n");
			html.append("                                </tbody>\r\n");
			html.append("                            </table>\r\n");
			html.append("                        </div>\r\n");
			html.append("                    </div>\r\n");
			html.append("                </form>\r\n");
			html.append("            </div>\r\n");
			html.append("            <div class=\"flex_btn_wrap\">\r\n");
			html.append("                <a class=\"border_btn\" href=\"javascript:\" onclick=\"teacherRequestCtrl.cancel(1);\">\r\n");
			html.append("                    <span>지원취소</span>\r\n");
			html.append("                </a>\r\n");
			html.append("                <a class=\"b_color_btn\" href=\"javascript:\" onclick=\"teacherRequestCtrl.next(1);\">\r\n");
			html.append("                    <span>다음</span>\r\n");
			html.append("                </a>\r\n");
			html.append("            </div>\r\n");
			html.append("        </div>\r\n");
			html.append("    </div>\r\n");
			html.append("</div>\r\n");
			
		}	
		return html.toString();
	}
	
	//강사신청서 제출
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public int submitTeacherInfo( Map<String, Object> submitList, String teacherImg, int memberSq) throws Exception {
		log.info("> TeacherServiceImpl.submitTeacherInfo......");
		
		System.out.println("submitList : " + submitList );
		
		List<HashMap<String, Object>> tceduList = (List<HashMap<String, Object>>) submitList.get("tceduList");		
		List<HashMap<String, Object>> tchistList = (List<HashMap<String, Object>>) submitList.get("tchistList");
		List<HashMap<String, Object>> tcawrdList = (List<HashMap<String, Object>>) submitList.get("tcawrdList");
		List<HashMap<String, Object>> tcauthctfList = (List<HashMap<String, Object>>) submitList.get("tcauthctfList");

		String hopeDaywVal = (String) submitList.get("hopeDaywVal");
		List<String> resultList = new ArrayList<String>();
		StringBuilder resultBuilder = new StringBuilder();	
		int index = 1;		
		for (int i = 0; i <7; i++) {
			if (index < 8) {resultBuilder.append(hopeDaywVal.contains(String.valueOf(index)) ? "Y" : "N");}
			index ++;
		}
		
		String result = resultBuilder.toString();
		
		for (int i = 0; i < 7; i++) {
			resultList.add(i, String.valueOf(result.charAt(i)));
		}
		
		this.teacherMapper.insertTeacher(submitList, teacherImg, memberSq);
		this.teacherMapper.insertAwards(tcawrdList, memberSq);
		this.teacherMapper.insertCarrer(tchistList, memberSq);
		this.teacherMapper.insertCertificate(tcauthctfList, memberSq);
		this.teacherMapper.insertEducation(tceduList, memberSq);		
		this.teacherMapper.insertApplyClass(submitList, resultList, memberSq);
		
		return 1;
	}	

	//임시저장글 저장
	@Override
	public Map<String, Object> saveTeacherInfo(Map<String, Object> saveDataList, String teacherImg, int memberSq) {
		log.info(">TeacherServiceImpl.saveTeacherInfo... saveDataList: " + saveDataList + ", memberSq: " + memberSq);
		
		this.teacherMapper.saveTeacherInfo(saveDataList, teacherImg, memberSq);
		Map<String , Object> okMap = new HashMap<String, Object>();	
		okMap.put("cnt", "1");
		
		return okMap;
	}
	
	//임시저장글 삭제
	@Override
	public Map<String, Object> deleteOk(int memberSq) {
		log.info(">TeacherServiceImpl.deleteOk.......");
		
		this.teacherMapper.deleteTeacherInfo(memberSq);	
		Map<String, Object> deleteMap = new HashMap<String, Object>();		
		deleteMap.put("cnt", "1");
	
		return deleteMap;
	}
	
	//강사신청 정보입력 html (step 2)
	@Override
	public String createTeacherInfoHtml() {	
		log.info("> TeacherServiceImpl.createTeacherInfoHtml......");

		StringBuilder html = new StringBuilder();
		int startYear = 1960;
		int endYear = 2023;

			html.append("<form id=\"submit_form\" enctype=\"multipart/form-data\">\r\n");
			html.append("    <input type=\"hidden\" id=\"form\" name=\"form\" value=\"\"/>\r\n");
			html.append("</form>\r\n");
			html.append("<div class=\"for_padding\" data-step=\"2\">\r\n");
			html.append("    <div class=\"scroll_area\">\r\n");
			html.append("        <form id=\"request_form\">\r\n");
			html.append("            <input type=\"hidden\" id=\"imgPre\" name=\"imgPre\" >\r\n");
			html.append("            <input type=\"hidden\" name=\"tcCdNo\" value=\"041989\"/>\r\n");
			html.append("            <input type=\"hidden\" name=\"prinfClctAgrYn\" value=\"Y\"/>\r\n");
			html.append("            <input type=\"hidden\" name=\"carrInfoClctAgrYn\" value=\"Y\"/>\r\n");
			html.append("            <input type=\"hidden\" name=\"appDt\" value=\"20231117\"/>\r\n");
			html.append("            <div class=\"process_wrap\" title=\"2. 강사정보 작성\">\r\n");
			html.append("                <div class=\"process_div check two_law\">\r\n");
			html.append("                    <p class=\"num\">\r\n");
			html.append("                        <span>1</span>\r\n");
			html.append("                    </p>\r\n");
			html.append("                    <p class=\"txt\">\r\n");
			html.append("                        개인정보 활용 <br class=\"only_mobile\"/>동의\r\n");
			html.append("                    </p>\r\n");
			html.append("                </div>\r\n");
			html.append("                <div class=\"process_div on\">\r\n");
			html.append("                    <p class=\"num\">\r\n");
			html.append("                        <span>2</span>\r\n");
			html.append("                    </p>\r\n");
			html.append("                    <p class=\"txt\">강사정보 작성</p>\r\n");
			html.append("                </div>\r\n");
			html.append("                <div class=\"process_div\">\r\n");
			html.append("                    <p class=\"num\">\r\n");
			html.append("                        <span>3</span>\r\n");
			html.append("                    </p>\r\n");
			html.append("                    <p class=\"txt\">제출완료</p>\r\n");
			html.append("                </div>\r\n");
			html.append("            </div>\r\n");
			html.append("            <div class=\"dot_txt_box\">\r\n");
			html.append("                <p class=\"f_body1\">롯데문화센터에서는 분야별 수준 높은 교육문화 컨텐츠와 열정적인 전문 강사님을 모십니다.</p>\r\n");
			html.append("                <!-- 2023-01-10 이모지 삭제 -->\r\n");
			html.append("            </div>\r\n");
			html.append("            <div class=\"sub_inner\">\r\n");
			html.append("                <div class=\"sub_tit_area\">\r\n");
			html.append("                    <div class=\"left\">\r\n");
			html.append("                        <p class=\"pop_sec_tit\">강사 기본정보</p>\r\n");
			html.append("                    </div>\r\n");
			html.append("                    <div class=\"right\">\r\n");
			html.append("                        <p class=\"f_caption2\">\r\n");
			html.append("                            <span class=\"red_txt\">*</span>\r\n");
			html.append("                            표시는 필수 기재 항목입니다.\r\n");
			html.append("                        </p>\r\n");
			html.append("                    </div>\r\n");
			html.append("                </div>\r\n");
			html.append("                <div class=\"data_input_wrap\">\r\n");
			html.append("                    <div class=\"row\">\r\n");
			html.append("                        <div class=\"th\">\r\n");
			html.append("                            <p class=\"tit f_body1 essential\">프로필 사진</p>\r\n");
			html.append("                        </div>\r\n");
			html.append("                        <div class=\"td\">\r\n");
			html.append("                            <div class=\"upload_div \">\r\n");
			html.append("                                <input type=\"file\" style=\"display: none;\" name=\"phtFileId\" accept=\".jpg, .jpeg, .png, .gif\" data-max-size=\"10240\" onchange=\"tcCommon.setImgPreview(this);\"/>\r\n");
			html.append("                                <!-- 사진 업로드 완료시 complete class 추가 -->\r\n");
			html.append("                                <a href=\"javascript:\" class=\"upload_btn photo\" onclick=\"tcCommon.upload_onclick(this);\">\r\n");
			html.append("                                    <span>사진 업로드</span>\r\n");
			html.append("                                </a>\r\n");
			html.append("                                <div class=\"upload_img\">\r\n");
			html.append("                                    <div class=\"img_div\">\r\n");
			html.append("                                        <p class=\"img\">\r\n");
			html.append("                                            <img name=\"imagePreview\" src=\"\" alt=\"\">\r\n");
			html.append("                                        </p>\r\n");
			html.append("                                        <a href=\"javascript:\" class=\"delete_btn\" role=\"button\" onclick=\"tcCommon.deleteFile(this);\">\r\n");
			html.append("                                            <span>삭제</span>\r\n");
			html.append("                                        </a>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                </div>\r\n");
			html.append("                            </div>\r\n");
			html.append("                            <div class=\"caption_txt_w\">\r\n");
			html.append("                                <p class=\"dot_txt\">사진 등록은 필수입니다.</p>\r\n");
			html.append("                                <p class=\"dot_txt\">jpg, gif, png 파일만 가능합니다.</p>\r\n");
			html.append("                                <p class=\"dot_txt\">용량 제한은 10Mb 이하입니다.</p>\r\n");
			html.append("                                <p class=\"dot_txt\">업로드된 이미지는 임시저장 후 확인 가능합니다.</p>\r\n");
			html.append("                            </div>\r\n");
			html.append("                        </div>\r\n");
			html.append("                    </div>\r\n");
			html.append("                    <div class=\"row\">\r\n");
			html.append("                        <div class=\"th\">\r\n");
			html.append("                            <p class=\"tit f_body1 essential\">사업자 여부</p>\r\n");
			html.append("                        </div>\r\n");
			html.append("                        <div class=\"td small\">\r\n");
			html.append("                            <div class=\"radio_flex_box\">\r\n");
			html.append("                                <div class=\"form_radio\">\r\n");
			html.append("                                    <input type=\"radio\" id=\"tcTpCd1\" name=\"tcTpCd\" value=\"1\" checked onchange=\"teacherRequestCtrl.tcTpCdOnchange(this);\">\r\n");
			html.append("                                    <label for=\"tcTpCd1\">개인</label>\r\n");
			html.append("                                </div>\r\n");
			html.append("                                <div class=\"form_radio\">\r\n");
			html.append("                                    <input type=\"radio\" id=\"tcTpCd2\" name=\"tcTpCd\" value=\"2\" onchange=\"teacherRequestCtrl.tcTpCdOnchange(this);\">\r\n");
			html.append("                                    <label for=\"tcTpCd2\">개인사업자</label>\r\n");
			html.append("                                </div>\r\n");
			html.append("                                <div class=\"form_radio\">\r\n");
			html.append("                                    <input type=\"radio\" id=\"tcTpCd3\" name=\"tcTpCd\" value=\"3\" onchange=\"teacherRequestCtrl.tcTpCdOnchange(this);\">\r\n");
			html.append("                                    <label for=\"tcTpCd3\">법인사업자</label>\r\n");
			html.append("                                </div>\r\n");
			html.append("                                <div class=\"form_checkbox\">\r\n");
			html.append("                                    <input type=\"checkbox\" id=\"txfrBizprYn\" name=\"txfrBizprYn\" value=\"Y\" disabled>\r\n");
			html.append("                                    <!-- 개인 선택시 disabled 추가해주세요. -->\r\n");
			html.append("                                    <label for=\"txfrBizprYn\">면세사업자인 경우 체크해 주세요.</label>\r\n");
			html.append("                                </div>\r\n");
			html.append("                            </div>\r\n");
			html.append("                        </div>\r\n");
			html.append("                    </div>\r\n");
			html.append("                    <div class=\"row\">\r\n");
			html.append("                        <div class=\"th\">\r\n");
			html.append("                            <p class=\"tit f_body1 essential\">강사명</p>\r\n");
			html.append("                        </div>\r\n");
			html.append("                        <div class=\"td\">\r\n");
			html.append("                            <div class=\"form_input\">\r\n");
			html.append("                                <input type=\"text\" name=\"tcNm\" id=\"tcNm\" placeholder=\"예) 김롯데\" value=\"김롯데\">\r\n");
			html.append("                                <div class=\"input_btn_wrap\">\r\n");
			html.append("                                    <a href=\"javascript:\" role=\"button\" class=\"btn_delete\" title=\"강사명 지우기\"></a>\r\n");
			html.append("                                </div>\r\n");
			html.append("                            </div>\r\n");
			html.append("                        </div>\r\n");
			html.append("                    </div>\r\n");
			html.append("                    <div class=\"row\">\r\n");
			html.append("                        <div class=\"th\">\r\n");
			html.append("                            <p id=\"bdayEssential\" class=\"tit f_body1 essential\">생년월일</p>\r\n");
			html.append("                        </div>\r\n");
			html.append("                        <div class=\"td small\">\r\n");
			html.append("                            <div class=\"radio_flex_box\">\r\n");
			html.append("                                <div class=\"form_radio\">\r\n");
			html.append("                                    <input type=\"radio\" id=\"lscCd1\" name=\"lscCd\" value=\"01\" checked>\r\n");
			html.append("                                    <label for=\"lscCd1\">양력</label>\r\n");
			html.append("                                </div>\r\n");
			html.append("                                <div class=\"form_radio\">\r\n");
			html.append("                                    <input type=\"radio\" id=\"lscCd2\" name=\"lscCd\" value=\"02\">\r\n");
			html.append("                                    <label for=\"lscCd2\">음력</label>\r\n");
			html.append("                                </div>\r\n");
			html.append("                            </div>\r\n");
			html.append("                        </div>\r\n");
			html.append("                        <div class=\"td\">\r\n");
			html.append("                            <div class=\"form_input\">\r\n");
			html.append("                                <input type=\"text\" name=\"bday\" id=\"bday\" placeholder=\"예) 19980125\" data-type=\"date\" data-old-value=\"\" value=\"20231120\" maxlength=\"8\" oninput=\"fnc.checkMaxLength(this);\" onkeyup=\"tcCommon.checkNumberOnkeyup(this);\">\r\n");
			html.append("                                <div class=\"input_btn_wrap\">\r\n");
			html.append("                                    <a href=\"javascript:\" role=\"button\" class=\"btn_delete\" title=\"생년월일 지우기\"></a>\r\n");
			html.append("                                </div>\r\n");
			html.append("                            </div>\r\n");
			html.append("                        </div>\r\n");
			html.append("                    </div>\r\n");
			html.append("                    <div class=\"row\">\r\n");
			html.append("                        <div class=\"th\">\r\n");
			html.append("                            <p class=\"tit f_body1 essential\">휴대전화</p>\r\n");
			html.append("                        </div>\r\n");
			html.append("                        <div class=\"td\">\r\n");
			html.append("                            <div class=\"input_wrap\">\r\n");
			html.append("                                <div class=\"input_div\">\r\n");
			html.append("                                    <div class=\"form_select_div change\">\r\n");
			html.append("                                        <div class=\"open_area\">\r\n");
			html.append("                                            <input type=\"hidden\" name=\"hpStNo\" id=\"hpStNo\" value=\"010\"/>\r\n");
			html.append("                                            <a class=\"btn_open\" href=\"javascript:\">\r\n");
			html.append("                                                <span>010</span>\r\n");
			html.append("                                            </a>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                        <div class=\"dimd\"></div>\r\n");
			html.append("                                        <div class=\"list_wrap\">\r\n");
			html.append("                                            <div class=\"tit_area\">\r\n");
			html.append("                                                <p class=\"tit\">휴대전화</p>\r\n");
			html.append("                                                <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                            <div class=\"scroll_wrap\">\r\n");
			html.append("                                                <a class=\"btn_link on\" href=\"javascript:\" data-value=\"010\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                    <span>010</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"011\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                    <span>011</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"016\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                    <span>016</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"017\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                    <span>017</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"018\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                    <span>018</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"019\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                    <span>019</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                    <div class=\"form_input\">\r\n");
			html.append("                                        <input type=\"text\" maxlength=\"4\" name=\"hpMidNo\" id=\"hpMidNo\" data-old-value=\"\" value=\"1234\" placeholder=\"예) 0000\" oninput=\"fnc.checkMaxLength(this);\" onkeyup=\"tcCommon.checkNumberOnkeyup(this);\">\r\n");
			html.append("                                        <div class=\"input_btn_wrap\">\r\n");
			html.append("                                            <button type=\"button\" class=\"btn_delete\" title=\"휴대전화 중간번호 지우기\"></button>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                    <div class=\"form_input\">\r\n");
			html.append("                                        <input type=\"text\" maxlength=\"4\" name=\"hpLastNo\" id=\"hpLastNo\" data-old-value=\"\" value=\"5678\" placeholder=\"예) 0000\" oninput=\"fnc.checkMaxLength(this);\" onkeyup=\"tcCommon.checkNumberOnkeyup(this);\">\r\n");
			html.append("                                        <div class=\"input_btn_wrap\">\r\n");
			html.append("                                            <button type=\"button\" class=\"btn_delete\" title=\"휴대전화 마지막번호 지우기\"></button>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                </div>\r\n");
			html.append("                            </div>\r\n");
			html.append("                        </div>\r\n");
			html.append("                    </div>\r\n");
			html.append("                    <div class=\"row\">\r\n");
			html.append("                        <div class=\"th\">\r\n");
			html.append("                            <p class=\"tit f_body1 essential\">이메일</p>\r\n");
			html.append("                        </div>\r\n");
			html.append("                        <div class=\"td\">\r\n");
			html.append("                            <div class=\"input_wrap\">\r\n");
			html.append("                                <div class=\"input_div email\">\r\n");
			html.append("                                    <div class=\"form_input\">\r\n");
			html.append("                                        <input type=\"text\" id=\"email\" name=\"email\" value=\"SIST\" placeholder=\"예) lotte\" data-old-value=\"\" onkeyup=\"tcCommon.checkEmailOnkeyup1(this);\">\r\n");
			html.append("                                        <div class=\"input_btn_wrap\">\r\n");
			html.append("                                            <button type=\"button\" class=\"btn_delete\" title=\"이메일 지우기\"></button>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                    <span>@</span>\r\n");
			html.append("                                    <!-- 2023-02-09 추가 -->\r\n");
			html.append("                                    <div class=\"form_input\">\r\n");
			html.append("                                        <input type=\"text\" id=\"emailAddrCd\" name=\"emailAddrCd\" value=\"sist.com\" placeholder=\"직접입력\" data-old-value=\"\" onkeyup=\"tcCommon.checkEmailOnkeyup2(this);\">\r\n");
			html.append("                                        <div class=\"input_btn_wrap\">\r\n");
			html.append("                                            <button type=\"button\" class=\"btn_delete\" title=\"이메일 지우기\"></button>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                    <div class=\"form_select_div \">\r\n");
			html.append("                                        <div class=\"open_area\">\r\n");
			html.append("                                            <input type=\"hidden\" value=\"\"/>\r\n");
			html.append("                                            <a class=\"btn_open\" href=\"javascript:\">\r\n");
			html.append("                                                <span>직접입력</span>\r\n");
			html.append("                                            </a>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                        <div class=\"dimd\"></div>\r\n");
			html.append("                                        <div class=\"list_wrap\">\r\n");
			html.append("                                            <div class=\"tit_area\">\r\n");
			html.append("                                                <p class=\"tit\">이메일</p>\r\n");
			html.append("                                                <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                            <div class=\"scroll_wrap\">\r\n");
			html.append("                                                <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n");
			html.append("                                                    <span>직접입력</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"hanmail.net\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n");
			html.append("                                                    <span>다음(한메일)</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"naver.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n");
			html.append("                                                    <span>네이버</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"nate.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n");
			html.append("                                                    <span>네이트</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"yahoo.co.kr\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n");
			html.append("                                                    <span>야후!코리아</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"yahoo.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n");
			html.append("                                                    <span>야후!yahoo.com</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"dreamwiz.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n");
			html.append("                                                    <span>드림위즈</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"korea.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n");
			html.append("                                                    <span>코리아</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"empal.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n");
			html.append("                                                    <span>엠팔</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"gmail.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n");
			html.append("                                                    <span>구글G메일</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"hanafos.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n");
			html.append("                                                    <span>하나포스</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"paran.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n");
			html.append("                                                    <span>파란</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"freechal.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n");
			html.append("                                                    <span>프리첼</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"hahanet.net\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n");
			html.append("                                                    <span>하하넷</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"hotmail.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n");
			html.append("                                                    <span>핫메일</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"kornet.net\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n");
			html.append("                                                    <span>코넷</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"lycos.co.kr\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n");
			html.append("                                                    <span>라이코스</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"netian.com\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n");
			html.append("                                                    <span>네띠앙</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"origo.net\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n");
			html.append("                                                    <span>오리고</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"unitel.co.kr\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n");
			html.append("                                                    <span>유니텔</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"chollian.net\" onclick=\"tcCommon.selOptClick_email(this);\">\r\n");
			html.append("                                                    <span>천리안</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                </div>\r\n");
			html.append("                            </div>\r\n");
			html.append("                        </div>\r\n");
			html.append("                    </div>\r\n");
			html.append("                    <div class=\"row\">\r\n");
			html.append("                        <div class=\"th\">\r\n");
			html.append("                            <p class=\"tit f_body1 essential\">주소</p>\r\n");
			html.append("                        </div>\r\n");
			html.append("                        <div class=\"td\">\r\n");
			html.append("                            <div class=\"input_wrap\">\r\n");
			html.append("                                <div class=\"input_div\">\r\n");
			html.append("                                    <div class=\"form_input postcode disabled\">\r\n");
			html.append("                                        <!-- 2022-11-28 disabled class 추가 (값이 들어갈 경우 class 삭제 부탁드립니다) -->\r\n");
			html.append("                                        <input type=\"text\" placeholder=\"우편번호\" name=\"pstno\" id=\"pstno\" value=\"\" readonly>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                    <a class=\"s_color_btn\" href=\"javascript:\" onclick=\"teacherRequestCtrl.searchAddr();\">\r\n");
			html.append("                                        <span>검색</span>\r\n");
			html.append("                                    </a>\r\n");
			html.append("                                </div>\r\n");
			html.append("                                <div class=\"input_div\">\r\n");
			html.append("                                    <div class=\"form_input w100p disabled\">\r\n");
			html.append("                                        <!-- 2022-11-28 disabled class 추가 (값이 들어갈 경우 class 삭제 부탁드립니다) -->\r\n");
			html.append("                                        <input type=\"text\" placeholder=\"기본주소\" name=\"addr\" id=\"addr\" value=\"\" readonly>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                </div>\r\n");
			html.append("                                <div class=\"input_div\">\r\n");
			html.append("                                    <div class=\"form_input w100p disabled\">\r\n");
			html.append("                                        <input type=\"text\" placeholder=\"상세주소 (건물, 아파트, 동/호수 선택 입력)\" name=\"dtlAddr\" id=\"dtlAddr\" value=\"101호\" readonly>\r\n");
			html.append("                                        <div class=\"input_btn_wrap\">\r\n");
			html.append("                                            <button type=\"button\" class=\"btn_delete\" title=\"상세주소 지우기\"></button>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                </div>\r\n");
			html.append("                            </div>\r\n");
			html.append("                        </div>\r\n");
			html.append("                    </div>\r\n");
			html.append("                    <div class=\"row\">\r\n");
			html.append("                        <div class=\"th\">\r\n");
			html.append("                            <p class=\"tit f_body1\">강사소개</p>\r\n");
			html.append("                        </div>\r\n");
			html.append("                        <div class=\"td\">\r\n");
			html.append("                            <div class=\"form_textarea\">\r\n");
			html.append("                                <div class=\"wrap_for_msg\">\r\n");
			html.append("                                    <textarea name=\"selfIntrdnCont\" id=\"\" cols=\"\" rows=\"\" placeholder=\"강사 본인에 대한 소개 내용을 간략히 작성해주세요.\" data-maxlength=\"2000\" onkeyup=\"tcCommon.textareaOnkeyup(this)\">김롯데 강사소개입니다.</textarea>\r\n");
			html.append("                                </div>\r\n");
			html.append("                                <p class=\"check_byte\">\r\n");
			html.append("                                    <span class=\"r_byte\">0</span>\r\n");
			html.append("                                    /\r\n");
			html.append("                                    <span class=\"l_byte\">2000</span>\r\n");
			html.append("                                </p>\r\n");
			html.append("                            </div>\r\n");
			html.append("                        </div>\r\n");
			html.append("                    </div>\r\n");
			html.append("                    <div class=\"row_tit\">\r\n");
			html.append("                        <p class=\"tit\">학력 및 경력 정보</p>\r\n");
			html.append("                    </div>\r\n");
			html.append("                    <div class=\"row\">\r\n");
			html.append("                        <div class=\"th\">\r\n");
			html.append("                            <p class=\"tit f_body1\">학력사항</p>\r\n");
			html.append("                        </div>\r\n");
			html.append("                        <div class=\"td\">\r\n");
			html.append("                            <div class=\"input_wrap\">\r\n");
			html.append("                                <div class=\"plus_div_w\"></div>\r\n");
			html.append("                                <div class=\"input_div w100p\">\r\n");
			html.append("                                    <div class=\"form_select_div\">\r\n");
			html.append("                                        <div class=\"open_area\">\r\n");
			html.append("                                            <input type=\"hidden\" id=\"schlClCd\" name=\"tceduList[][schlClCd]\" value=\"\"/>\r\n");
			html.append("                                            <a class=\"btn_open\" href=\"javascript:\">\r\n");
			html.append("                                                <span>학교 선택</span>\r\n");
			html.append("                                            </a>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                        <div class=\"dimd\"></div>\r\n");
			html.append("                                        <div class=\"list_wrap\">\r\n");
			html.append("                                            <div class=\"tit_area\">\r\n");
			html.append("                                                <p class=\"tit\">학교</p>\r\n");
			html.append("                                                <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                            <div class=\"scroll_wrap\">\r\n");
			html.append("                                                <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                    <span>학교 선택</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link\" href=\"javascript:\" data-value=\"1\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                    <span>고등학교</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link\" href=\"javascript:\" data-value=\"2\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                    <span>전문대학교</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link\" href=\"javascript:\" data-value=\"3\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                    <span>대학교</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link\" href=\"javascript:\" data-value=\"4\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                    <span>대학원(석사)</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link\" href=\"javascript:\" data-value=\"5\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                    <span>대학원(박사)</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                    <div class=\"form_select_div\">\r\n");
			html.append("                                        <div class=\"open_area\">\r\n");
			html.append("                                            <input type=\"hidden\" id=\"grdtClCd\" name=\"tceduList[][grdtClCd]\" value=\"1\"/>\r\n");
			html.append("                                            <a class=\"btn_open\" href=\"javascript:\">\r\n");
			html.append("                                                <span>졸업상태 선택</span>\r\n");
			html.append("                                            </a>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                        <div class=\"dimd\"></div>\r\n");
			html.append("                                        <div class=\"list_wrap\">\r\n");
			html.append("                                            <div class=\"tit_area\">\r\n");
			html.append("                                                <p class=\"tit\">졸업상태</p>\r\n");
			html.append("                                                <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                            <div class=\"scroll_wrap\">\r\n");
			html.append("                                                <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                    <span>졸업상태 선택</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"1\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                    <span>졸업</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"2\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                    <span>수료</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"3\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                    <span>중퇴</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                                <a class=\"btn_link \" href=\"javascript:\" data-value=\"4\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                    <span>재학중</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                    <div class=\"form_select_div\">\r\n");
			html.append("                                        <div class=\"open_area\">\r\n");
			html.append("                                            <input type=\"hidden\" id=\"yy\" name=\"tceduList[][yy]\" value=\"\"/>\r\n");
			html.append("                                            <a class=\"btn_open\" href=\"javascript:\">\r\n");
			html.append("                                                <span>년도 선택</span>\r\n");
			html.append("                                            </a>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                        <div class=\"dimd\"></div>\r\n");
			html.append("                                        <div class=\"list_wrap\">\r\n");
			html.append("                                            <div class=\"tit_area\">\r\n");
			html.append("                                                <p class=\"tit\">년도</p>\r\n");
			html.append("                                                <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                            <div class=\"scroll_wrap\">\r\n");
			html.append("                                                <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                    <span>년도 선택</span>\r\n");
			html.append("                                                </a>\r\n");
			
			for (int year = endYear; year >= startYear; year--) {
			    html.append(" 	<a class=\"btn_link \" href=\"javascript:\" data-value=\"").append(year).append("\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			    html.append("	<span>").append(year).append("</span>\r\n");
			    html.append("	</a>\r\n");
			}
			
			html.append("                                            </div>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                </div>\r\n");
			html.append("                                <div class=\"input_div\">\r\n");
			html.append("                                    <div class=\"form_input w100p\">\r\n");
			html.append("                                        <input type=\"text\" placeholder=\"학교명을 입력해주세요.\" id=\"schlNm\" value=\"롯데대학교\" name=\"tceduList[][schlNm]\">\r\n");
			html.append("                                        <div class=\"input_btn_wrap\">\r\n");
			html.append("                                            <button type=\"button\" class=\"btn_delete\" title=\"학교명 지우기\"></button>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                </div>\r\n");
			html.append("                                <div class=\"input_div\">\r\n");
			html.append("                                    <div class=\"form_input w100p\">\r\n");
			html.append("                                        <input type=\"text\" placeholder=\"전공을 입력해주세요.\" id=\"mjrNm\" value=\"롯데전공\"name=\"tceduList[][mjrNm]\">\r\n");
			html.append("                                        <div class=\"input_btn_wrap\">\r\n");
			html.append("                                            <button type=\"button\" class=\"btn_delete\" title=\"전공 지우기\"></button>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                </div>\r\n");
			html.append("                                <div class=\"input_div\">\r\n");
			html.append("                                    <a class=\"s_color_btn gray\" href=\"javascript:\" data-value=\"edu\" onclick=\"tcRqst2HistCtrl.addInfo(this);\">\r\n");
			html.append("                                        <span>추가</span>\r\n");
			html.append("                                    </a>\r\n");
			html.append("                                </div>\r\n");
			html.append("                            </div>\r\n");
			html.append("                        </div>\r\n");
			html.append("                    </div>\r\n");
			html.append("                    <div class=\"row\">\r\n");
			html.append("                        <div class=\"th\">\r\n");
			html.append("                            <p class=\"tit f_body1\">경력사항</p>\r\n");
			html.append("                        </div>\r\n");
			html.append("                        <div class=\"td\">\r\n");
			html.append("                            <div class=\"input_wrap\">\r\n");
			html.append("                                <div class=\"plus_div_w\"></div>\r\n");
			html.append("                                <div class=\"input_div\">\r\n");
			html.append("                                    <div class=\"form_input w100p\">\r\n");
			html.append("                                        <input type=\"text\" placeholder=\"기관명을 입력해주세요.\" id=\"histPlcNm\" value=\"롯데\" name=\"tchistList[][histPlcNm]\">\r\n");
			html.append("                                        <div class=\"input_btn_wrap\">\r\n");
			html.append("                                            <button type=\"button\" class=\"btn_delete\" title=\"기관명 지우기\"></button>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                </div>\r\n");
			html.append("                                <div class=\"input_div\">\r\n");
			html.append("                                    <div class=\"form_input w100p tit_input\">\r\n");
			html.append("                                        <p class=\"tit\">시작일</p>\r\n");
			html.append("                                        <input type=\"text\" maxlength=\"8\" placeholder=\"예) 19980125\" value=\"19980125\" id=\"histStDt\" name=\"tchistList[][histStDt]\" data-type=\"date\" data-old-value=\"20200101\" oninput=\"fnc.checkMaxLength(this);\" onkeyup=\"tcCommon.checkNumberOnkeyup(this);\">\r\n");
			html.append("                                        <div class=\"input_btn_wrap\">\r\n");
			html.append("                                            <button type=\"button\" class=\"btn_delete\" title=\"경력사항 시작일 지우기\"></button>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                    <div class=\"form_input w100p tit_input\">\r\n");
			html.append("                                        <p class=\"tit\">종료일</p>\r\n");
			html.append("                                        <input type=\"text\" maxlength=\"8\" placeholder=\"예) 19980125\"  value=\"19980130\" id=\"histEndDt\" name=\"tchistList[][histEndDt]\" data-type=\"date\" data-old-value=\"20231121\" oninput=\"fnc.checkMaxLength(this);\" onkeyup=\"tcCommon.checkNumberOnkeyup(this);\">\r\n");
			html.append("                                        <div class=\"input_btn_wrap\">\r\n");
			html.append("                                            <button type=\"button\" class=\"btn_delete\" title=\"경력사항 종료일 지우기\"></button>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                </div>\r\n");
			html.append("                                <div class=\"input_div\">\r\n");
			html.append("                                    <a class=\"s_color_btn gray\" href=\"javascript:\" data-value=\"hist\" onclick=\"tcRqst2HistCtrl.addInfo(this);\">\r\n");
			html.append("                                        <span>추가</span>\r\n");
			html.append("                                    </a>\r\n");
			html.append("                                </div>\r\n");
			html.append("                            </div>\r\n");
			html.append("                        </div>\r\n");
			html.append("                    </div>\r\n");
			html.append("                    <div class=\"row\">\r\n");
			html.append("                        <div class=\"th\">\r\n");
			html.append("                            <p class=\"tit f_body1\">수상내역</p>\r\n");
			html.append("                        </div>\r\n");
			html.append("                        <div class=\"td\">\r\n");
			html.append("                            <div class=\"input_wrap\">\r\n");
			html.append("                                <div class=\"plus_div_w\"></div>\r\n");
			html.append("                                <div class=\"input_div\">\r\n");
			html.append("                                    <div class=\"form_input w100p\">\r\n");
			html.append("                                        <input type=\"text\" id=\"awrdIssueAgncNm\" name=\"tcawrdList[][issueAgncNm]\" value=\"롯데\" placeholder=\"발행기관을 입력해주세요.\">\r\n");
			html.append("                                        <div class=\"input_btn_wrap\">\r\n");
			html.append("                                            <button type=\"button\" class=\"btn_delete\" title=\"발행기관 지우기\"></button>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                </div>\r\n");
			html.append("                                <div class=\"input_div\">\r\n");
			html.append("                                    <div class=\"form_input w100p\">\r\n");
			html.append("                                        <input type=\"text\" id=\"awrdIssueNm\" name=\"tcawrdList[][issueNm]\" value=\"롯데\" placeholder=\"수상내역을 입력해주세요.\">\r\n");
			html.append("                                        <div class=\"input_btn_wrap\">\r\n");
			html.append("                                            <button type=\"button\" class=\"btn_delete\" title=\"수상내역 지우기\"></button>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                </div>\r\n");
			html.append("                                <div class=\"input_div\">\r\n");
			html.append("                                    <div class=\"form_input w100p tit_input\">\r\n");
			html.append("                                        <p class=\"tit\">수상일</p>\r\n");
			html.append("                                        <input type=\"text\" maxlength=\"8\" id=\"awrdIssueDt\" name=\"tcawrdList[][issueDt]\" placeholder=\"예) 19980125\"  value=\"19980125\" data-type=\"date\" data-old-value=\"20200202\" oninput=\"fnc.checkMaxLength(this);\" onkeyup=\"tcCommon.checkNumberOnkeyup(this);\">\r\n");
			html.append("                                        <div class=\"input_btn_wrap\">\r\n");
			html.append("                                            <button type=\"button\" class=\"btn_delete\" title=\"수상일 지우기\"></button>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                </div>\r\n");
			html.append("                                <div class=\"input_div\">\r\n");
			html.append("                                    <a class=\"s_color_btn gray\" href=\"javascript:\" data-value=\"awrd\" onclick=\"tcRqst2HistCtrl.addInfo(this);\">\r\n");
			html.append("                                        <span>추가</span>\r\n");
			html.append("                                    </a>\r\n");
			html.append("                                </div>\r\n");
			html.append("                            </div>\r\n");
			html.append("                        </div>\r\n");
			html.append("                    </div>\r\n");
			html.append("                    <div class=\"row\">\r\n");
			html.append("                        <div class=\"th\">\r\n");
			html.append("                            <p class=\"tit f_body1\">자격증</p>\r\n");
			html.append("                        </div>\r\n");
			html.append("                        <div class=\"td\">\r\n");
			html.append("                            <div class=\"input_wrap\">\r\n");
			html.append("                                <div class=\"plus_div_w\"></div>\r\n");
			html.append("                                <div class=\"input_div\">\r\n");
			html.append("                                    <div class=\"form_input w100p\">\r\n");
			html.append("                                        <input type=\"text\" id=\"athctfIssueAgncNm\" name=\"tcauthctfList[][issueAgncNm]\" value=\"롯데\"placeholder=\"발행기관을 입력해주세요.\">\r\n");
			html.append("                                        <div class=\"input_btn_wrap\">\r\n");
			html.append("                                            <button type=\"button\" class=\"btn_delete\" title=\"발행기관 지우기\"></button>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                </div>\r\n");
			html.append("                                <div class=\"input_div\">\r\n");
			html.append("                                    <div class=\"form_input w100p\">\r\n");
			html.append("                                        <input type=\"text\" id=\"athctfIssueNm\" name=\"tcauthctfList[][issueNm]\" value=\"롯데자격증\" placeholder=\"자격증명을 입력해주세요.\">\r\n");
			html.append("                                        <div class=\"input_btn_wrap\">\r\n");
			html.append("                                            <button type=\"button\" class=\"btn_delete\" title=\"자격증명 지우기\"></button>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                </div>\r\n");
			html.append("                                <div class=\"input_div\">\r\n");
			html.append("                                    <div class=\"form_input w100p tit_input\">\r\n");
			html.append("                                        <p class=\"tit\">취득일</p>\r\n");
			html.append("                                        <input type=\"text\" maxlength=\"8\" id=\"athctfIssueDt\" name=\"tcauthctfList[][issueDt]\" placeholder=\"예) 19980125\" value=\"19980125\"  data-old-value=\"20200101\" oninput=\"fnc.checkMaxLength(this);\" onkeyup=\"tcCommon.checkNumberOnkeyup(this);\">\r\n");
			html.append("                                        <div class=\"input_btn_wrap\">\r\n");
			html.append("                                            <button type=\"button\" class=\"btn_delete\" title=\"취득일 지우기\"></button>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                </div>\r\n");
			html.append("                                <div class=\"input_div\">\r\n");
			html.append("                                    <a class=\"s_color_btn gray\" href=\"javascript:\" data-value=\"athctf\" onclick=\"tcRqst2HistCtrl.addInfo(this);\">\r\n");
			html.append("                                        <span>추가</span>\r\n");
			html.append("                                    </a>\r\n");
			html.append("                                </div>\r\n");
			html.append("                            </div>\r\n");
			html.append("                        </div>\r\n");
			html.append("                    </div>\r\n");
			html.append("                    <script type=\"text/javascript\" src=\"/resources/common/js/information/teacherApp/teacherRequest2_hist.js\"></script>\r\n");
			html.append("                    <div class=\"row_tit\">\r\n");
			html.append("                        <p class=\"tit\">출강 정보</p>\r\n");
			html.append("                    </div>\r\n");
			html.append("                    <div class=\"row\">\r\n");
			html.append("                        <div class=\"th\">\r\n");
			html.append("                            <p class=\"tit f_body1 essential\">희망지점</p>\r\n");
			html.append("                        </div>\r\n");
			html.append("                        <div class=\"td\">\r\n");
			html.append("                            <div class=\"input_wrap\">\r\n");
			html.append("                                <div class=\"input_div\">\r\n");
			html.append("                                    <div class=\"form_select_w\">\r\n");
			html.append("                                        <p class=\"sub_tit f_caption1\">1지망</p>\r\n");
			html.append("                                        <div class=\"form_select_div \">\r\n");
			html.append("                                            <div class=\"open_area\">\r\n");
			html.append("                                                <input type=\"hidden\" id=\"frstHopeBrchCd\" name=\"frstHopeBrchCd\" value=\"0001\"/>\r\n");
			html.append("                                                <input type=\"hidden\" id=\"frstHopeBrchCdNm\" name=\"frstHopeBrchCdNm\" value=\"0002\"/>\r\n");
			html.append("                                                <a class=\"btn_open\" href=\"javascript:\">\r\n");
			html.append("                                                    <span>선택</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                            <div class=\"dimd\"></div>\r\n");
			html.append("                                            <div class=\"list_wrap\">\r\n");
			html.append("                                                <div class=\"tit_area\">\r\n");
			html.append("                                                    <p class=\"tit\">1지망</p>\r\n");
			html.append("                                                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n");
			html.append("                                                </div>\r\n");
			html.append("                                                <div class=\"scroll_wrap\">\r\n");
			html.append("                                                    <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>선택</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0002\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>잠실점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0001\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>본점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0013\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>강남점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0028\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>건대스타시티점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0006\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>관악점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0340\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>김포공항점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0022\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>노원점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0026\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>미아점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0010\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>영등포점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0004\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>청량리점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0344\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>인천점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0399\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>동탄점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0335\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>구리점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0008\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>분당점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0349\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>수원점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0336\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>안산점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0011\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>일산점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0334\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>중동점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0341\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>평촌점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0005\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>부산본점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0333\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>광복점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0007\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>광주점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0023\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>대구점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0012\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>대전점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0016\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>동래점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0354\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>마산점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0024\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>상인점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0027\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>센텀시티점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0015\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>울산점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0025\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>전주점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0017\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>창원점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0014\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>포항점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0361\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>롯데몰군산점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0350\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>롯데몰광명점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0009\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>부평점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0018\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>안양점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0020\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>인천점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0003\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>인천점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"9998\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>이지_테스트</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                </div>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                    <div class=\"form_select_w\">\r\n");
			html.append("                                        <p class=\"sub_tit f_caption1\">2지망</p>\r\n");
			html.append("                                        <div class=\"form_select_div \">\r\n");
			html.append("                                            <div class=\"open_area\">\r\n");
			html.append("                                                <input type=\"hidden\" id=\"secHopeBrchCd\" name=\"secHopeBrchCd\" value=\"\"/>\r\n");
			html.append("                                                <input type=\"hidden\" id=\"secHopeBrchCdNm\" name=\"secHopeBrchCdNm\" value=\"\"/>\r\n");
			html.append("                                                <a class=\"btn_open\" href=\"javascript:\">\r\n");
			html.append("                                                    <span>선택</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                            <div class=\"dimd\"></div>\r\n");
			html.append("                                            <div class=\"list_wrap\">\r\n");
			html.append("                                                <div class=\"tit_area\">\r\n");
			html.append("                                                    <p class=\"tit\">2지망</p>\r\n");
			html.append("                                                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n");
			html.append("                                                </div>\r\n");
			html.append("                                                <div class=\"scroll_wrap\">\r\n");
			html.append("                                                    <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>선택</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0002\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>잠실점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0001\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>본점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0007\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>강남점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0008\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>건대스타시티점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0009\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>관악점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0010\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>김포공항점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0011\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>노원점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0012\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>미아점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0013\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>영등포점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0014\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>청량리점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0003\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>인천점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0004\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>동탄점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0015\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>구리점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0016\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>분당점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0017\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>수원점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0018\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>안산점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0019\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>일산점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0020\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>중동점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0021\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>평촌점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0005\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>부산본점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0006\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>광복점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0023\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>광주점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0024\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>대구점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0025\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>대전점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0026\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>동래점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0027\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>마산점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0028\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>상인점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0029\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>센텀시티점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0030\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>울산점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0031\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>전주점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0032\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>창원점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0033\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>포항점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0034\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>롯데몰군산점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0022\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>롯데몰광명점</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"9998\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>이지_테스트</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                </div>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                </div>\r\n");
			html.append("                            </div>\r\n");
			html.append("                        </div>\r\n");
			html.append("                    </div>\r\n");
			html.append("                    <div class=\"row\">\r\n");
			html.append("                        <div class=\"th\">\r\n");
			html.append("                            <p class=\"tit f_body1 essential\">희망분야</p>\r\n");
			html.append("                        </div>\r\n");
			html.append("                        <div class=\"td\">\r\n");
			html.append("                            <div class=\"input_wrap\">\r\n");
			html.append("                                <div class=\"input_div wrap\">\r\n");
			html.append("                                    <div id=\"firstLrDiv\" class=\"form_select_w\">\r\n");
			html.append("                                        <p class=\"sub_tit f_caption1\">1지망</p>\r\n");
			html.append("                                        <div class=\"form_select_div \">\r\n");
			html.append("                                            <div class=\"open_area\">\r\n");
			html.append("                                                <input type=\"hidden\" id=\"frstLrclsCtegryCd\" name=\"frstLrclsCtegryCd\" value=\"\"/>\r\n");
			html.append("                                                <input type=\"hidden\" id=\"frstLrclsCtegryCdNm\" name=\"frstLrclsCtegryCdNm\" value=\"\"/>\r\n");
			html.append("                                                <a class=\"btn_open\" href=\"javascript:\">\r\n");
			html.append("                                                    <span>선택</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                            <div class=\"dimd\"></div>\r\n");
			html.append("                                            <div class=\"list_wrap\">\r\n");
			html.append("                                                <div class=\"tit_area\">\r\n");
			html.append("                                                    <p class=\"tit\">1지망</p>\r\n");
			html.append("                                                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n");
			html.append("                                                </div>\r\n");
			html.append("                                                <div class=\"scroll_wrap\">\r\n");
			html.append("                                                    <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\">\r\n");
			html.append("                                                        <span>선택</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"01\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\">\r\n");
			html.append("                                                        <span>성인강좌</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"02\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\">\r\n");
			html.append("                                                        <span>영·유아강좌</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"03\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\">\r\n");
			html.append("                                                        <span>아동강좌</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                </div>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                    <div id=\"firstMdDiv\" class=\"form_select_w\">\r\n");
			html.append("                                        <p class=\"sub_tit f_caption1\">1지망 상세</p>\r\n");
			html.append("                                        <div class=\"form_select_div \">\r\n");
			html.append("                                            <div class=\"open_area\">\r\n");
			html.append("                                                <input type=\"hidden\" id=\"frstMdclsCtegryCd\" name=\"frstMdclsCtegryCd\" value=\"\"/>\r\n");
			html.append("                                                <a class=\"btn_open\" href=\"javascript:\">\r\n");
			html.append("                                                    <span>선택</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                            <div class=\"dimd\"></div>\r\n");
			html.append("                                            <div class=\"list_wrap\">\r\n");
			html.append("                                                <div class=\"tit_area\">\r\n");
			html.append("                                                    <p class=\"tit\">1지망 상세</p>\r\n");
			html.append("                                                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n");
			html.append("                                                </div>\r\n");
			html.append("                                                <div class=\"scroll_wrap\">\r\n");
			html.append("                                                    <a class=\"btn_link default\" href=\"javascript:\" data-ctegry-id=\"\" data-value=\"\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\">\r\n");
			html.append("                                                        <span>선택</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0101\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>공예</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0102\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>노래</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0103\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>댄스</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0104\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>드로잉</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0105\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>라이프스타일</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0106\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>악기</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0107\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>어학</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0108\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>인문학</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0109\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>재테크</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0110\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>커리어</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0111\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>쿠킹</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0112\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>피트니스</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"02\" data-value=\"0202\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>오감발달</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"02\" data-value=\"0201\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>창의·체험</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"02\" data-value=\"0203\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>음악·미술</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"02\" data-value=\"0204\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>언어·사회성</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"02\" data-value=\"0205\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>신체활동</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"03\" data-value=\"0302\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>신체활동</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"03\" data-value=\"0303\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>창의·체험</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"03\" data-value=\"0301\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>음악·미술</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"03\" data-value=\"0304\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>언어·사회성</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                </div>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                    <div id=\"secLrDiv\" class=\"form_select_w\">\r\n");
			html.append("                                        <p class=\"sub_tit f_caption1\">2지망</p>\r\n");
			html.append("                                        <div class=\"form_select_div \">\r\n");
			html.append("                                            <div class=\"open_area\">\r\n");
			html.append("                                                <input type=\"hidden\" id=\"secLrclsCtegryCd\" name=\"secLrclsCtegryCd\" value=\"\"/>\r\n");
			html.append("                                                <input type=\"hidden\" id=\"secLrclsCtegryCdNm\" name=\"secLrclsCtegryCdNm\" value=\"\"/>\r\n");
			html.append("                                                <a class=\"btn_open\" href=\"javascript:\">\r\n");
			html.append("                                                    <span>선택</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                            <div class=\"dimd\"></div>\r\n");
			html.append("                                            <div class=\"list_wrap\">\r\n");
			html.append("                                                <div class=\"tit_area\">\r\n");
			html.append("                                                    <p class=\"tit\">2지망</p>\r\n");
			html.append("                                                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n");
			html.append("                                                </div>\r\n");
			html.append("                                                <div class=\"scroll_wrap\">\r\n");
			html.append("                                                    <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\">\r\n");
			html.append("                                                        <span>선택</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"01\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\">\r\n");
			html.append("                                                        <span>성인강좌</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"02\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\">\r\n");
			html.append("                                                        <span>영·유아강좌</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"03\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\">\r\n");
			html.append("                                                        <span>아동강좌</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                </div>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                    <div id=\"secMdDiv\" class=\"form_select_w\">\r\n");
			html.append("                                        <p class=\"sub_tit f_caption1\">2지망 상세</p>\r\n");
			html.append("                                        <div class=\"form_select_div \">\r\n");
			html.append("                                            <div class=\"open_area\">\r\n");
			html.append("                                                <input type=\"hidden\" id=\"secMdclsCtegryCd\" name=\"secMdclsCtegryCd\" value=\"\"/>\r\n");
			html.append("                                                <a class=\"btn_open\" href=\"javascript:\">\r\n");
			html.append("                                                    <span>선택</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                            <div class=\"dimd\"></div>\r\n");
			html.append("                                            <div class=\"list_wrap\">\r\n");
			html.append("                                                <div class=\"tit_area\">\r\n");
			html.append("                                                    <p class=\"tit\">2지망 상세</p>\r\n");
			html.append("                                                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n");
			html.append("                                                </div>\r\n");
			html.append("                                                <div class=\"scroll_wrap\">\r\n");
			html.append("                                                    <a class=\"btn_link default\" href=\"javascript:\" data-ctegry-id=\"\" data-value=\"\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\">\r\n");
			html.append("                                                        <span>선택</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0101\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>공예</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0102\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>노래</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0103\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>댄스</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0104\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>드로잉</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0105\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>라이프스타일</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0106\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>악기</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0107\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>어학</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0108\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>인문학</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0109\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>재테크</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0110\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>커리어</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0111\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>쿠킹</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"01\" data-value=\"0112\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>피트니스</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"02\" data-value=\"0202\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>오감발달</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"02\" data-value=\"0201\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>창의·체험</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"02\" data-value=\"0203\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>음악·미술</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"02\" data-value=\"0204\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>언어·사회성</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"02\" data-value=\"0205\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>신체활동</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"03\" data-value=\"0302\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>신체활동</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"03\" data-value=\"0303\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>창의·체험</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"03\" data-value=\"0301\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>음악·미술</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" data-ctegry-id=\"03\" data-value=\"0304\" href=\"javascript:\" onclick=\"teacherRequestCtrl.selOptClick_ctgry(this);\" style=\"display:none;\">\r\n");
			html.append("                                                        <span>언어·사회성</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                </div>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                </div>\r\n");
			html.append("                            </div>\r\n");
			html.append("                        </div>\r\n");
			html.append("                    </div>\r\n");
			html.append("                    <div class=\"row\">\r\n");
			html.append("                        <div class=\"th\">\r\n");
			html.append("                            <p class=\"tit f_body1 essential\">희망요일</p>\r\n");
			html.append("                        </div>\r\n");
			html.append("                        <div class=\"td small\">\r\n");
			html.append("                            <div class=\"filter_btn_list\" id=\"hopeDaywValDiv\">\r\n");
			html.append("                                <input type=\"hidden\" id=\"hopeDaywVal\" name=\"hopeDaywVal\" value=\"\"/>\r\n");
			html.append("                                <input type=\"hidden\" id=\"hopeDaywValNm\" name=\"hopeDaywValNm\" value=\"\"/>\r\n");
			html.append("                                <a href=\"javascript:\" class=\"total btn on\" data-value=\"\" onclick=\"teacherRequestCtrl.selOptClick_dayw(this);\">\r\n");
			html.append("                                    <span>전체</span>\r\n");
			html.append("                                </a>\r\n");
			html.append("                                <a class=\"btn \" href=\"javascript:\" data-value=\"1\" onclick=\"teacherRequestCtrl.selOptClick_dayw(this);\">\r\n");
			html.append("                                    <span>월요일</span>\r\n");
			html.append("                                </a>\r\n");
			html.append("                                <a class=\"btn \" href=\"javascript:\" data-value=\"2\" onclick=\"teacherRequestCtrl.selOptClick_dayw(this);\">\r\n");
			html.append("                                    <span>화요일</span>\r\n");
			html.append("                                </a>\r\n");
			html.append("                                <a class=\"btn \" href=\"javascript:\" data-value=\"3\" onclick=\"teacherRequestCtrl.selOptClick_dayw(this);\">\r\n");
			html.append("                                    <span>수요일</span>\r\n");
			html.append("                                </a>\r\n");
			html.append("                                <a class=\"btn \" href=\"javascript:\" data-value=\"4\" onclick=\"teacherRequestCtrl.selOptClick_dayw(this);\">\r\n");
			html.append("                                    <span>목요일</span>\r\n");
			html.append("                                </a>\r\n");
			html.append("                                <a class=\"btn \" href=\"javascript:\" data-value=\"5\" onclick=\"teacherRequestCtrl.selOptClick_dayw(this);\">\r\n");
			html.append("                                    <span>금요일</span>\r\n");
			html.append("                                </a>\r\n");
			html.append("                                <a class=\"btn \" href=\"javascript:\" data-value=\"6\" onclick=\"teacherRequestCtrl.selOptClick_dayw(this);\">\r\n");
			html.append("                                    <span>토요일</span>\r\n");
			html.append("                                </a>\r\n");
			html.append("                                <a class=\"btn \" href=\"javascript:\" data-value=\"7\" onclick=\"teacherRequestCtrl.selOptClick_dayw(this);\">\r\n");
			html.append("                                    <span>일요일</span>\r\n");
			html.append("                                </a>\r\n");
			html.append("                            </div>\r\n");
			html.append("                        </div>\r\n");
			html.append("                    </div>\r\n");
			html.append("                    <div class=\"row\">\r\n");
			html.append("                        <div class=\"th\">\r\n");
			html.append("                            <p class=\"tit f_body1 essential\">희망시간</p>\r\n");
			html.append("                        </div>\r\n");
			html.append("                        <div class=\"td\">\r\n");
			html.append("                            <div class=\"input_wrap\">\r\n");
			html.append("                                <div class=\"input_div time\">\r\n");
			html.append("                                    <div class=\"form_select_w\">\r\n");
			html.append("                                        <p class=\"sub_tit f_caption1\">시작</p>\r\n");
			html.append("                                        <div class=\"form_select_div \">\r\n");
			html.append("                                            <div class=\"open_area\">\r\n");
			html.append("                                                <input type=\"hidden\" id=\"hopeStHh\" name=\"hopeStHh\" value=\"\"/>\r\n");
			html.append("                                                <a class=\"btn_open\" href=\"javascript:\">\r\n");
			html.append("                                                    <span>시</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                            <div class=\"dimd\"></div>\r\n");
			html.append("                                            <div class=\"list_wrap\">\r\n");
			html.append("                                                <div class=\"tit_area\">\r\n");
			html.append("                                                    <p class=\"tit\">시작 시</p>\r\n");
			html.append("                                                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n");
			html.append("                                                </div>\r\n");
			html.append("                                                <div class=\"scroll_wrap\">\r\n");
			html.append("                                                    <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"00\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>00시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"01\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>01시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"02\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>02시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"03\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>03시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"04\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>04시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"05\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>05시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"06\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>06시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"07\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>07시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"08\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>08시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"09\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>09시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"10\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>10시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"11\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>11시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"12\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>12시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"13\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>13시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"14\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>14시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"15\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>15시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"16\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>16시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"17\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>17시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"18\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>18시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"19\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>19시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"20\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>20시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"21\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>21시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"22\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>22시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"23\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>23시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"24\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>24시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                </div>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                    <div class=\"form_select_w\">\r\n");
			html.append("                                        <div class=\"form_select_div \">\r\n");
			html.append("                                            <div class=\"open_area\">\r\n");
			html.append("                                                <input type=\"hidden\" id=\"hopeStMi\" name=\"hopeStMi\" value=\"\"/>\r\n");
			html.append("                                                <a class=\"btn_open\" href=\"javascript:\">\r\n");
			html.append("                                                    <span>분</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                            <div class=\"dimd\"></div>\r\n");
			html.append("                                            <div class=\"list_wrap\">\r\n");
			html.append("                                                <div class=\"tit_area\">\r\n");
			html.append("                                                    <p class=\"tit\">시작 분</p>\r\n");
			html.append("                                                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n");
			html.append("                                                </div>\r\n");
			html.append("                                                <div class=\"scroll_wrap\">\r\n");
			html.append("                                                    <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"00\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>00분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"05\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>05분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"10\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>10분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"15\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>15분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"20\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>20분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"25\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>25분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"30\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>30분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"35\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>35분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"40\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>40분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"45\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>45분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"50\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>50분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"55\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>55분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                </div>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                    <p class=\"to_txt\">~</p>\r\n");
			html.append("                                    <div class=\"form_select_w\">\r\n");
			html.append("                                        <p class=\"sub_tit f_caption1\">종료</p>\r\n");
			html.append("                                        <div class=\"form_select_div \">\r\n");
			html.append("                                            <div class=\"open_area\">\r\n");
			html.append("                                                <input type=\"hidden\" id=\"hopeEndHh\" name=\"hopeEndHh\" value=\"\"/>\r\n");
			html.append("                                                <a class=\"btn_open\" href=\"javascript:\">\r\n");
			html.append("                                                    <span>시</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                            <div class=\"dimd\"></div>\r\n");
			html.append("                                            <div class=\"list_wrap\">\r\n");
			html.append("                                                <div class=\"tit_area\">\r\n");
			html.append("                                                    <p class=\"tit\">종료 시</p>\r\n");
			html.append("                                                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n");
			html.append("                                                </div>\r\n");
			html.append("                                                <div class=\"scroll_wrap\">\r\n");
			html.append("                                                    <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"00\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>00시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"01\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>01시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"02\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>02시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"03\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>03시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"04\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>04시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"05\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>05시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"06\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>06시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"07\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>07시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"08\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>08시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"09\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>09시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"10\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>10시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"11\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>11시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"12\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>12시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"13\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>13시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"14\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>14시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"15\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>15시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"16\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>16시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"17\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>17시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"18\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>18시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"19\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>19시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"20\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>20시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"21\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>21시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"22\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>22시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"23\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>23시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"24\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>24시</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                </div>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                    <div class=\"form_select_w\">\r\n");
			html.append("                                        <div class=\"form_select_div \">\r\n");
			html.append("                                            <div class=\"open_area\">\r\n");
			html.append("                                                <input type=\"hidden\" id=\"hopeEndMi\" name=\"hopeEndMi\" value=\"\"/>\r\n");
			html.append("                                                <a class=\"btn_open\" href=\"javascript:\">\r\n");
			html.append("                                                    <span>분</span>\r\n");
			html.append("                                                </a>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                            <div class=\"dimd\"></div>\r\n");
			html.append("                                            <div class=\"list_wrap\">\r\n");
			html.append("                                                <div class=\"tit_area\">\r\n");
			html.append("                                                    <p class=\"tit\">종료 분</p>\r\n");
			html.append("                                                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n");
			html.append("                                                </div>\r\n");
			html.append("                                                <div class=\"scroll_wrap\">\r\n");
			html.append("                                                    <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"00\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>00분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"05\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>05분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"10\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>10분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"15\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>15분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"20\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>20분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"25\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>25분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"30\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>30분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"35\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>35분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"40\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>40분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"45\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>45분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"50\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>50분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"55\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                                                        <span>55분</span>\r\n");
			html.append("                                                    </a>\r\n");
			html.append("                                                </div>\r\n");
			html.append("                                            </div>\r\n");
			html.append("                                        </div>\r\n");
			html.append("                                    </div>\r\n");
			html.append("                                </div>\r\n");
			html.append("                            </div>\r\n");
			html.append("                        </div>\r\n");
			html.append("                    </div>\r\n");
			html.append("                    <div class=\"row\">\r\n");
			html.append("                        <div class=\"th\">\r\n");
			html.append("                            <p class=\"tit f_body1\">강좌 소개</p>\r\n");
			html.append("                        </div>\r\n");
			html.append("                        <div class=\"td\">\r\n");
			html.append("                            <div class=\"form_textarea\">\r\n");
			html.append("                                <div class=\"wrap_for_msg\">\r\n");
			html.append("                                    <textarea name=\"lectIntrdnCont\" id=\"\" cols=\"\" rows=\"\" placeholder=\"본인의 강좌에 대한 소개 내용을 간략히 작성해주세요.\" data-maxlength=\"200\" onkeyup=\"tcCommon.textareaOnkeyup(this)\">김롯데 강좌 소개입니다.</textarea>\r\n");
			html.append("                                </div>\r\n");
			html.append("                                <p class=\"check_byte\">\r\n");
			html.append("                                    <span class=\"r_byte\">0</span>\r\n");
			html.append("                                    /\r\n");
			html.append("                                    <span class=\"l_byte\">200</span>\r\n");
			html.append("                                </p>\r\n");
			html.append("                            </div>\r\n");
			html.append("                        </div>\r\n");
			html.append("                    </div>\r\n");
			html.append("                </div>\r\n");
			html.append("                <div class=\"dot_txt_box\">\r\n");
			html.append("                    <p class=\"f_body1\">유의사항</p>\r\n");
			html.append("                    <!-- 2023-02-13 이모지 삭제 -->\r\n");
			html.append("                    <p class=\"dot_txt\">위 등록한 내용과 사실이 다를 경우 강좌개설이 취소될 수 있습니다.</p>\r\n");
			html.append("                    <p class=\"dot_txt\">‘임시저장’은 수정이 가능한 상태이며 문화센터에는 지원되지 않습니다. ‘지원하기’를 클릭해야 정상적으로 지원 완료됩니다.</p>\r\n");
			html.append("                    <p class=\"dot_txt\">학력/경력, 자격증, 수상내역, 강의계획서 입력을 위해서는 선택정보 수집이용에 동의하셔야 합니다.</p>\r\n");
			html.append("                    <!-- 2023-01-16 텍스트 수정 -->\r\n");
			html.append("                </div>\r\n");
			html.append("                <div class=\"flex_btn_wrap\">\r\n");
			html.append("                    <a class=\"border_btn\" href=\"javascript:\" onclick=\"teacherRequestCtrl.cancel(2);\">\r\n");
			html.append("                        <span>지원취소</span>\r\n");
			html.append("                    </a>\r\n");
			html.append("                    <a class=\"border_btn\" href=\"javascript:\" onclick=\"teacherRequestCtrl.save();\">\r\n");
			html.append("                        <span>임시저장</span>\r\n");
			html.append("                    </a>\r\n");
			html.append("                    <a class=\"b_color_btn\" href=\"javascript:\" onclick=\"teacherRequestCtrl.next(2);\">\r\n");
			html.append("                        <span>다음</span>\r\n");
			html.append("                    </a>\r\n");
			html.append("                </div>\r\n");
			html.append("            </div>\r\n");
			html.append("        </form>\r\n");
			html.append("    </div>\r\n");
			html.append("</div>\r\n");
			html.append("<div id=\"eduInfo\" class=\"plus_div\" style=\"display: none;\">\r\n");
			html.append("    <div class=\"input_div w100p\">\r\n");
			html.append("        <div class=\"form_select_div change\">\r\n");
			html.append("            <div class=\"open_area\">\r\n");
			html.append("                <input type=\"hidden\" name=\"tceduList[][schlClCd]\" value=\"$schlClCd$\"/>\r\n");
			html.append("                <a class=\"btn_open\" href=\"javascript:\">\r\n");
			html.append("                    <span>$schlClCdNm$</span>\r\n");
			html.append("                </a>\r\n");
			html.append("            </div>\r\n");
			html.append("            <div class=\"dimd\"></div>\r\n");
			html.append("            <div class=\"list_wrap\">\r\n");
			html.append("                <div class=\"tit_area\">\r\n");
			html.append("                    <p class=\"tit\">학교</p>\r\n");
			html.append("                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n");
			html.append("                </div>\r\n");
			html.append("                <div class=\"scroll_wrap\">\r\n");
			html.append("                    <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                        <span>학교 선택</span>\r\n");
			html.append("                    </a>\r\n");
			html.append("                    <a class=\"btn_link\" href=\"javascript:\" data-value=\"0\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                        <span>고등학교</span>\r\n");
			html.append("                    </a>\r\n");
			html.append("                    <a class=\"btn_link\" href=\"javascript:\" data-value=\"1\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                        <span>전문대학교</span>\r\n");
			html.append("                    </a>\r\n");
			html.append("                    <a class=\"btn_link\" href=\"javascript:\" data-value=\"2\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                        <span>대학교</span>\r\n");
			html.append("                    </a>\r\n");
			html.append("                    <a class=\"btn_link\" href=\"javascript:\" data-value=\"3\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                        <span>대학원(석사)</span>\r\n");
			html.append("                    </a>\r\n");
			html.append("                    <a class=\"btn_link\" href=\"javascript:\" data-value=\"4\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                        <span>대학원(박사)</span>\r\n");
			html.append("                    </a>\r\n");
			html.append("                </div>\r\n");
			html.append("            </div>\r\n");
			html.append("        </div>\r\n");
			html.append("        <div class=\"form_select_div change\">\r\n");
			html.append("            <div class=\"open_area\">\r\n");
			html.append("                <input type=\"hidden\" name=\"tceduList[][grdtClCd]\" value=\"$grdtClCd$\"/>\r\n");
			html.append("                <a class=\"btn_open\" href=\"javascript:\">\r\n");
			html.append("                    <span>$grdtClCdNm$</span>\r\n");
			html.append("                </a>\r\n");
			html.append("            </div>\r\n");
			html.append("            <div class=\"dimd\"></div>\r\n");
			html.append("            <div class=\"list_wrap\">\r\n");
			html.append("                <div class=\"tit_area\">\r\n");
			html.append("                    <p class=\"tit\">졸업상태</p>\r\n");
			html.append("                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n");
			html.append("                </div>\r\n");
			html.append("                <div class=\"scroll_wrap\">\r\n");
			html.append("                    <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                        <span>졸업상태 선택</span>\r\n");
			html.append("                    </a>\r\n");
			html.append("                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"0\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                        <span>졸업</span>\r\n");
			html.append("                    </a>\r\n");
			html.append("                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"1\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                        <span>수료</span>\r\n");
			html.append("                    </a>\r\n");
			html.append("                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"2\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                        <span>중퇴</span>\r\n");
			html.append("                    </a>\r\n");
			html.append("                    <a class=\"btn_link \" href=\"javascript:\" data-value=\"3\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                        <span>재학중</span>\r\n");
			html.append("                    </a>\r\n");
			html.append("                </div>\r\n");
			html.append("            </div>\r\n");
			html.append("        </div>\r\n");
			html.append("        <div class=\"form_select_div change\">\r\n");
			html.append("            <div class=\"open_area\">\r\n");
			html.append("                <input type=\"hidden\" name=\"tceduList[][yy]\" value=\"$yy$\"/>\r\n");
			html.append("                <a class=\"btn_open\" href=\"javascript:\">\r\n");
			html.append("                    <span>$yyNm$</span>\r\n");
			html.append("                </a>\r\n");
			html.append("            </div>\r\n");
			html.append("            <div class=\"dimd\"></div>\r\n");
			html.append("            <div class=\"list_wrap\">\r\n");
			html.append("                <div class=\"tit_area\">\r\n");
			html.append("                    <p class=\"tit\">년도</p>\r\n");
			html.append("                    <a href=\"javascript:\" role=\"button\" class=\"close\"></a>\r\n");
			html.append("                </div>\r\n");
			html.append("                <div class=\"scroll_wrap\">\r\n");
			html.append("                    <a class=\"btn_link default\" href=\"javascript:\" data-value=\"\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			html.append("                        <span>년도 선택</span>\r\n");
			html.append("                    </a>\r\n");

			for (int year = endYear; year >= startYear; year--) {
			    html.append(" 	<a class=\"btn_link \" href=\"javascript:\" data-value=\"").append(year).append("\" onclick=\"tcCommon.selOptClick(this);\">\r\n");
			    html.append("	<span>").append(year).append("</span>\r\n");
			    html.append("	</a>\r\n");
			}
			
			html.append("                </div>\r\n");
			html.append("            </div>\r\n");
			html.append("        </div>\r\n");
			html.append("    </div>\r\n");
			html.append("    <div class=\"input_div\">\r\n");
			html.append("        <div class=\"form_input w100p\">\r\n");
			html.append("            <input type=\"text\" placeholder=\"학교명을 입력해주세요.\" name=\"tceduList[][schlNm]\" value=\"$schlNm$\">\r\n");
			html.append("            <div class=\"input_btn_wrap\">\r\n");
			html.append("                <button type=\"button\" class=\"btn_delete\" title=\"학교명 지우기\"></button>\r\n");
			html.append("            </div>\r\n");
			html.append("        </div>\r\n");
			html.append("    </div>\r\n");
			html.append("    <div class=\"input_div\">\r\n");
			html.append("        <div class=\"form_input w100p\">\r\n");
			html.append("            <input type=\"text\" placeholder=\"전공을 입력해주세요.\" name=\"tceduList[][mjrNm]\" value=\"$mjrNm$\">\r\n");
			html.append("            <div class=\"input_btn_wrap\">\r\n");
			html.append("                <button type=\"button\" class=\"btn_delete\" title=\"전공 지우기\"></button>\r\n");
			html.append("            </div>\r\n");
			html.append("        </div>\r\n");
			html.append("    </div>\r\n");
			html.append("    <div class=\"delete_btn\">\r\n");
			html.append("        <a href=\"javascript:\" class=\"btn del_info\">\r\n");
			html.append("            <span>삭제하기</span>\r\n");
			html.append("        </a>\r\n");
			html.append("    </div>\r\n");
			html.append("</div>\r\n");
			html.append("<div id=\"histInfo\" class=\"plus_div\" style=\"display: none;\">\r\n");
			html.append("    <div class=\"input_div\">\r\n");
			html.append("        <div class=\"form_input w100p\">\r\n");
			html.append("            <input type=\"text\" placeholder=\"기관명을 입력해주세요.\" name=\"tchistList[][histPlcNm]\" value=\"$histPlcNm$\">\r\n");
			html.append("            <div class=\"input_btn_wrap\">\r\n");
			html.append("                <button type=\"button\" class=\"btn_delete\" title=\"기관명 지우기\"></button>\r\n");
			html.append("            </div>\r\n");
			html.append("        </div>\r\n");
			html.append("    </div>\r\n");
			html.append("    <div class=\"input_div\">\r\n");
			html.append("        <div class=\"form_input w100p tit_input\">\r\n");
			html.append("            <p class=\"tit\">시작일</p>\r\n");
			html.append("            <input type=\"text\" maxlength=\"8\" placeholder=\"예) 19980125\" name=\"tchistList[][histStDt]\" data-type=\"date\" data-old-value=\"20200101\" value=\"20231111\" oninput=\"fnc.checkMaxLength(this);\" onkeyup=\"tcCommon.checkNumberOnkeyup(this);\">\r\n");
			html.append("            <div class=\"input_btn_wrap\">\r\n");
			html.append("                <button type=\"button\" class=\"btn_delete\" title=\"경력사항 시작일 지우기\"></button>\r\n");
			html.append("            </div>\r\n");
			html.append("        </div>\r\n");
			html.append("        <div class=\"form_input w100p tit_input\">\r\n");
			html.append("            <p class=\"tit\">종료일</p>\r\n");
			html.append("            <input type=\"text\" maxlength=\"8\" placeholder=\"예) 19980125\" name=\"tchistList[][histEndDt]\" data-type=\"date\" data-old-value=\"20200101\" value=\"20231111\" oninput=\"fnc.checkMaxLength(this);\" onkeyup=\"tcCommon.checkNumberOnkeyup(this);\">\r\n");
			html.append("            <div class=\"input_btn_wrap\">\r\n");
			html.append("                <button type=\"button\" class=\"btn_delete\" title=\"경력사항 종료일 지우기\"></button>\r\n");
			html.append("            </div>\r\n");
			html.append("        </div>\r\n");
			html.append("        <div class=\"delete_btn\">\r\n");
			html.append("            <a href=\"javascript:\" class=\"btn del_info\">\r\n");
			html.append("                <span>삭제하기</span>\r\n");
			html.append("            </a>\r\n");
			html.append("        </div>\r\n");
			html.append("    </div>\r\n");
			html.append("</div>\r\n");
			html.append("<div id=\"awrdInfo\" class=\"plus_div\" style=\"display: none;\">\r\n");
			html.append("    <div class=\"input_div\">\r\n");
			html.append("        <div class=\"form_input w100p\">\r\n");
			html.append("            <input type=\"text\" placeholder=\"발행기관을 입력해주세요.\" name=\"tcawrdList[][issueAgncNm]\" value=\"롯데\">\r\n");
			html.append("            <div class=\"input_btn_wrap\">\r\n");
			html.append("                <button type=\"button\" class=\"btn_delete\" title=\"발행기관 지우기\"></button>\r\n");
			html.append("            </div>\r\n");
			html.append("        </div>\r\n");
			html.append("    </div>\r\n");
			html.append("    <div class=\"input_div\">\r\n");
			html.append("        <div class=\"form_input w100p\">\r\n");
			html.append("            <input type=\"text\" placeholder=\"수상내역을 입력해주세요.\" name=\"tcawrdList[][issueNm]\" value=\"롯데상\">\r\n");
			html.append("            <div class=\"input_btn_wrap\">\r\n");
			html.append("                <button type=\"button\" class=\"btn_delete\" title=\"수상내역 지우기\"></button>\r\n");
			html.append("            </div>\r\n");
			html.append("        </div>\r\n");
			html.append("    </div>\r\n");
			html.append("    <div class=\"input_div\">\r\n");
			html.append("        <div class=\"form_input w100p tit_input\">\r\n");
			html.append("            <p class=\"tit\">수상일</p>\r\n");
			html.append("            <input type=\"text\" maxlength=\"8\" placeholder=\"예) 19980125\" name=\"tcawrdList[][issueDt]\" data-type=\"date\" data-old-value=\"20200101\" value=\"20231111\" oninput=\"fnc.checkMaxLength(this);\" onkeyup=\"tcCommon.checkNumberOnkeyup(this);\">\r\n");
			html.append("            <div class=\"input_btn_wrap\">\r\n");
			html.append("                <button type=\"button\" class=\"btn_delete\" title=\"수상일 지우기\"></button>\r\n");
			html.append("            </div>\r\n");
			html.append("        </div>\r\n");
			html.append("    </div>\r\n");
			html.append("    <div class=\"delete_btn\">\r\n");
			html.append("        <a href=\"javascript:\" class=\"btn del_info\">\r\n");
			html.append("            <span>삭제하기</span>\r\n");
			html.append("        </a>\r\n");
			html.append("    </div>\r\n");
			html.append("</div>\r\n");
			html.append("<div id=\"athctfInfo\" class=\"plus_div\" style=\"display: none;\">\r\n");
			html.append("    <div class=\"input_div\">\r\n");
			html.append("        <div class=\"form_input w100p\">\r\n");
			html.append("            <input type=\"text\" placeholder=\"발행기관을 입력해주세요.\" name=\"tcauthctfList[][issueAgncNm]\" value=\"롯데\">\r\n");
			html.append("            <div class=\"input_btn_wrap\">\r\n");
			html.append("                <button type=\"button\" class=\"btn_delete\" title=\"발행기관 지우기\"></button>\r\n");
			html.append("            </div>\r\n");
			html.append("        </div>\r\n");
			html.append("    </div>\r\n");
			html.append("    <div class=\"input_div\">\r\n");
			html.append("        <div class=\"form_input w100p\">\r\n");
			html.append("            <input type=\"text\" placeholder=\"자격증명을 입력해주세요.\" name=\"tcauthctfList[][issueNm]\" value=\"롯데자격증\">\r\n");
			html.append("            <div class=\"input_btn_wrap\">\r\n");
			html.append("                <button type=\"button\" class=\"btn_delete\" title=\"자격증명 지우기\"></button>\r\n");
			html.append("            </div>\r\n");
			html.append("        </div>\r\n");
			html.append("    </div>\r\n");
			html.append("    <div class=\"input_div\">\r\n");
			html.append("        <div class=\"form_input w100p tit_input\">\r\n");
			html.append("            <p class=\"tit\">취득일</p>\r\n");
			html.append("            <input type=\"text\" maxlength=\"8\" placeholder=\"예) 19980125\" name=\"tcauthctfList[][issueDt]\" data-type=\"date\" data-old-value=\"$issueDt$\" value=\"$issueDt$\" oninput=\"fnc.checkMaxLength(this);\" onkeyup=\"tcCommon.checkNumberOnkeyup(this);\">\r\n");
			html.append("            <div class=\"input_btn_wrap\">\r\n");
			html.append("                <button type=\"button\" class=\"btn_delete\" title=\"취득일 지우기\"></button>\r\n");
			html.append("            </div>\r\n");
			html.append("        </div>\r\n");
			html.append("    </div>\r\n");
			html.append("    <div class=\"delete_btn\">\r\n");
			html.append("        <a href=\"javascript:\" class=\"btn del_info\">\r\n");
			html.append("            <span>삭제하기</span>\r\n");
			html.append("        </a>\r\n");
			html.append("    </div>\r\n");
			html.append("</div>\r\n");
			html.append("");		
		
			return html.toString();

	}

	//강사신청완료 시 step3 페이지 html
	@Override
	public String createSubmitHtml() {
		log.info("> TeacherServiceImpl.createSubmitHtml......");

		StringBuilder html = new StringBuilder();
		
			html.append("<script>\r\n");
			html.append("    function init_alert() {\r\n");
			html.append("        alert(\"강사 지원 정보가 존재합니다.\");\r\n");
			html.append("    }\r\n");
			html.append("</script>\r\n");
			html.append("<div class=\"for_padding\" data-step=\"3\">\r\n");
			html.append("    <div class=\"scroll_area\">\r\n");
			html.append("        <div class=\"process_wrap\" title=\"3. 제출완료\">\r\n");
			html.append("            <div class=\"process_div check two_law\">\r\n");
			html.append("                <p class=\"num\"><span>1</span></p>\r\n");
			html.append("                <p class=\"txt\">개인정보 활용 <br class=\"only_mobile\" />동의</p>\r\n");
			html.append("            </div>\r\n");
			html.append("            <div class=\"process_div check\">\r\n");
			html.append("                <p class=\"num\"><span>2</span></p>\r\n");
			html.append("                <p class=\"txt\">강사정보 작성</p>\r\n");
			html.append("            </div>\r\n");
			html.append("            <div class=\"process_div on\">\r\n");
			html.append("                <p class=\"num\"><span>3</span></p>\r\n");
			html.append("                <p class=\"txt\">제출완료</p>\r\n");
			html.append("            </div>\r\n");
			html.append("        </div>\r\n");
			html.append("        <div class=\"dot_txt_box complete\">\r\n");
			html.append("            <!-- <p class=\"icon\">🎉</p> -->\r\n");
			html.append("            <p class=\"f_body1\">강사지원 제출이 완료되었습니다.</p>\r\n");
			html.append("            <p class=\"f_body4\">담당자 확인후 개별 연락 예정입니다. 감사합니다.</p><!-- 2023-01-16 텍스트 수정 -->\r\n");
			html.append("        </div>\r\n");
			html.append("        <div class=\"flex_btn_wrap\">\r\n");
			html.append("            <a href=\"javascript:\" class=\"border_btn\" onclick=\"teacherRequestCtrl.close();\"><span>닫기</span></a>\r\n");
			html.append("        </div>\r\n");
			html.append("    </div>\r\n");
			html.append("</div>");
			
		return html.toString();
	}

	@Override
	public String createCooperationHtml() {
		log.info("> TeacherServiceImpl.createCooperationHtml......");
		
		StringBuilder html = new StringBuilder();
		
		html.append("<div class=\"for_padding\" data-step=\"1\">\r\n"
				+ "    <div class=\"scroll_area\">\r\n"
				+ "        <div class=\"process_wrap\" title=\"1. 개인정보 활용 동의\">\r\n"
				+ "            <div class=\"process_div on two_law\">\r\n"
				+ "                <p class=\"num\"><span>1</span></p>\r\n"
				+ "                <p class=\"txt\">개인정보 활용 <br class=\"only_mobile\" />동의</p>\r\n"
				+ "            </div>\r\n"
				+ "            <div class=\"process_div\">\r\n"
				+ "                <p class=\"num\"><span>2</span></p>\r\n"
				+ "                <p class=\"txt\">업체정보 작성</p>\r\n"
				+ "            </div>\r\n"
				+ "            <div class=\"process_div\">\r\n"
				+ "                <p class=\"num\"><span>3</span></p>\r\n"
				+ "                <p class=\"txt\">제출완료</p>\r\n"
				+ "            </div>\r\n"
				+ "        </div>\r\n"
				+ "        <div class=\"dot_txt_box\">\r\n"
				+ "            <p class=\"f_body1\">제휴 신청시 알려드려요</p><!-- 2023-01-11 이모지 삭제 -->\r\n"
				+ "            <!-- 2023-01-16 수정 -->\r\n"
				+ "            <p class=\"dot_txt\">콘텐츠ㆍ서비스ㆍ비지니스ㆍ마케팅ㆍ광고 등 Lifestyle LAB과 함께할 파트너사의 소중한 제안을 기다리고 있습니다.</p>\r\n"
				+ "            <!-- // 2023-01-16 수정 -->\r\n"
				+ "        </div>\r\n"
				+ "        <div class=\"sub_inner\">\r\n"
				+ "            <div class=\"sub_tit_area\">\r\n"
				+ "                <div class=\"left\">\r\n"
				+ "                    <p class=\"pop_sec_tit\">개인정보 수집·이용 동의서</p>\r\n"
				+ "                </div>\r\n"
				+ "                <div class=\"right\">\r\n"
				+ "                    <!-- <p class=\"f_caption2\"><span class=\"red_txt\">*</span> 표시는 필수 기재 항목입니다.</p> // 2023-01-16 삭제 -->\r\n"
				+ "                </div>\r\n"
				+ "            </div>\r\n"
				+ "            <div class=\"form_table_w\">\r\n"
				+ "                <div class=\"table_div\">\r\n"
				+ "                    <div class=\"form_checkbox agree_chk\">\r\n"
				+ "                        <input type=\"checkbox\" id=\"agreeChk1\" name=\"\">\r\n"
				+ "                        <label for=\"agreeChk1\">개인정보 수집항목, 수집목적 및 보유/이용 기간 <span class=\"red_txt\">(필수)</span></label><!-- 2023-01-16 수정 -->\r\n"
				+ "                    </div>\r\n"
				+ "                    <div class=\"form_table gray\">\r\n"
				+ "                        <table>\r\n"
				+ "                            <caption>테이블 캡션 내용이 들어갑니다.</caption>\r\n"
				+ "                            <colgroup>\r\n"
				+ "                                <col width=\"33%\">\r\n"
				+ "                                <col width=\"33%\">\r\n"
				+ "                                <col width=\"34%\">\r\n"
				+ "                            </colgroup>\r\n"
				+ "                            <thead>\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <th>수집 항목</th>\r\n"
				+ "                                    <th>목적</th>\r\n"
				+ "                                    <th>보유 및 이용 기간</th>\r\n"
				+ "                                </tr>\r\n"
				+ "                            </thead>\r\n"
				+ "                            <tbody>\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td rowspan=\"2\">\r\n"
				+ "                                        <p class=\"f_body2\">업체명, 담당자명, 연락처, 이메일</p><!-- 2023-03-13 텍스트 수정 -->\r\n"
				+ "                                    </td>\r\n"
				+ "                                    <td rowspan=\"2\">\r\n"
				+ "                                        <p class=\"f_body2\">고객 대상 강사 기본정보의 제공</p>\r\n"
				+ "                                    </td>\r\n"
				+ "                                    <td>\r\n"
				+ "                                        <p class=\"bold\">지원자 요청 시 즉시 파기</p><!-- 2023-03-13 class 수정 -->\r\n"
				+ "                                    </td>\r\n"
				+ "                                </tr>\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td>\r\n"
				+ "                                        <p class=\"bold\">강사 지원 후 강좌 미진행時 6개월 까지</p><!-- 2023-03-13 class 수정 -->\r\n"
				+ "                                    </td>\r\n"
				+ "                                </tr>\r\n"
				+ "                            </tbody>\r\n"
				+ "                        </table>\r\n"
				+ "                    </div>\r\n"
				+ "                    <div class=\"caption_txt_w\">\r\n"
				+ "                        <p class=\"dot_txt\">본인은 롯데백화점 문화센터에 제휴 신청을 희망하며, 개인정보의 수집 내용을 이해하고 동의합니다.</p>\r\n"
				+ "                        <p class=\"dot_txt\">개인정보 수집 동의를 거부하실 수 있으며, 이 경우 제휴신청이 제한 됩니다.</p><!-- 2023-01-16 텍스트 수정 -->\r\n"
				+ "                    </div>\r\n"
				+ "                </div>\r\n"
				+ "            </div>\r\n"
				+ "            <div class=\"flex_btn_wrap\">\r\n"
				+ "                <a class=\"border_btn\" href=\"javascript:\" onclick=\"cooperRequestCtrl.cancel();\">\r\n"
				+ "                    <span>취소</span>\r\n"
				+ "                </a>\r\n"
				+ "                <a class=\"b_color_btn\" href=\"javascript:\" onclick=\"cooperRequestCtrl.next(1);\">\r\n"
				+ "                    <span>다음</span>\r\n"
				+ "                </a>\r\n"
				+ "            </div>\r\n"
				+ "        </div>\r\n"
				+ "    </div>\r\n"
				+ "</div>");
		
		return html.toString();
	}

	@Override
	public TeacherDTO getSaveTeacherInfo(int memberSq) {
		log.info("> TeacherServiceImpl.getSaveTeacherInfo......");
		return this.teacherMapper.saveOpen(memberSq);
	}

}
