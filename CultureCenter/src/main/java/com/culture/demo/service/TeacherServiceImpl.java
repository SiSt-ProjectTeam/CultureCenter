package com.culture.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class TeacherServiceImpl implements TeacherService{
	
	@Override
	public String createTeacherHtml() {
		
		log.info("> TeacherServiceImpl.createTeacherHtml......");

		StringBuilder html = new StringBuilder();
			
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
		
		return html.toString();
	}

	/*
	@Override
	public String teacherInfoOk(int cnt, String prinfClctAgrYn, String carrInfoClctAgrYn) {
	    
	    return 
	}
	 */
}
