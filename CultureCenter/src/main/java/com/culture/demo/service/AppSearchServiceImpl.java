package com.culture.demo.service;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culture.demo.domain.ClassDTO;
import com.culture.demo.domain.MainLectSearchDTO;
import com.culture.demo.domain.SearchBranchDTO;
import com.culture.demo.domain.TawardsDTO;
import com.culture.demo.domain.TcareerDTO;
import com.culture.demo.domain.TcertificateDTO;
import com.culture.demo.domain.TeacherDTO;
import com.culture.demo.domain.TeducationDTO;
import com.culture.demo.mapper.AppSearchMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AppSearchServiceImpl implements AppSearchService {
	
	@Autowired
	private AppSearchMapper appSearchMapper;
	
	// 강좌 정보 가져오기
	@Override
	public List<ClassDTO> selectClassList(int branch_id, SearchBranchDTO searchBranchDTO
		, String[] yyl, String[] lectcll, String[] lectstl, String[] dayl, String[] timel, String[] amtl) throws Exception {
		log.info("AppSearchServiceImpl.selectBranchList() 호출");
		return this.appSearchMapper.selectClassList(branch_id, searchBranchDTO, yyl, lectcll, lectstl, dayl, timel, amtl);
	}
	
	// 강좌 목록 ajax html 생성
	@Override
	public String LecHTML(int branch_id, SearchBranchDTO searchBranchDTO, String[] yyl, String[] lectcll, String[] lectstl, String[] dayl, String[] timel, String[] amtl, HttpServletRequest request) throws Exception {
		log.info("AppSearchServiceImpl.LecHTML() 호출");
		StringBuilder html = new StringBuilder();
		List<ClassDTO> list = selectClassList(branch_id, searchBranchDTO, yyl, lectcll, lectstl, dayl, timel, amtl);
		
		String uploadRealPath = request.getServletContext().getRealPath("/upload");
		log.info("!!!!!!!!!!!!!!!!!! uploadRealPath : " + uploadRealPath);
		
		if(list.isEmpty()) {
			html.append("<div class=\"no_srch_area no_pb\" data-tot-cnt=\"0\">\r\n");
			html.append("	<div class=\"no_srch_div\">\r\n");
			html.append("		<p class=\"txt f_h2\">\r\n");
			html.append("			<span class=\"normal_value\">진행중인 강좌가 없습니다.</span>\r\n");
			html.append("		</p>\r\n");
			html.append("	</div>\r\n");
		} else {
			for(ClassDTO dto : list) {
				String avDay = (dto.getMon().equals("Y")?"월":"") + (dto.getTue().equals("Y")?"화":"") + (dto.getWed().equals("Y")?"수":"") + (dto.getThu().equals("Y")?"목":"")
						+ (dto.getFri().equals("Y")?"금":"") + (dto.getSat().equals("Y")?"토":"") + (dto.getSun().equals("Y")?"일":"") ;
				String schedule = String.format("%s %s:%s~%s:%s", avDay, dto.getStart_time().substring(0, 2), dto.getStart_time().substring(2), dto.getEnd_time().substring(0, 2), dto.getEnd_time().substring(2));
				
				DecimalFormat decimalFormat = new DecimalFormat("#,###");
				String fee = decimalFormat.format(dto.getClass_fee());
				
				html.append("<div class=\"card_list_v\" data-tot-cnt=\""+dto.getTot_cnt()+"\">\r\n");
				html.append("	<a href=\"/application/search/view.do?branch_id="+dto.getBranch_id()+"&amp;yy="+dto.getOpen_year()+"&amp;lectSmsterCd="+dto.getOpen_smst_id()+"&amp;lectCd="+dto.getClass_semester_sq()+"\" class=\"lec_list\">\r\n");
				html.append("		<div class=\"img_box\">\r\n");
				html.append("			<div class=\"img_resize_w img\">\r\n");
				html.append("				<img src=\"/upload/thumbnail/"+dto.getClass_img()+"\" alt=\""+dto.getClass_img()+"\">\r\n");
				html.append("			</div>\r\n");
				html.append("		</div>\r\n");
				html.append("		<div class=\"con\">\r\n");
				html.append("			<div class=\"label_div\">\r\n");
				html.append("				<p class=\"label small "+ (dto.getClass_st().equals("접수중") ? "lime" : "gray") +"\">"+dto.getClass_st()+"</p>\r\n");
				html.append("				<p class=\"label small black_gray\">"+dto.getBranch_nm()+"</p>\r\n");
				html.append("			</div>\r\n");
				html.append("			<p class=\"tit limit_line_two\">"+dto.getClass_nm()+"</p>\r\n");
				html.append("			<div class=\"info_con\">\r\n");
				html.append("				<div class=\"type_div\">\r\n");
				html.append("					<p class=\"type\">"+dto.getSmst_nm()+"</p>\r\n");
				html.append("					<p class=\"type\">"+dto.getName()+"</p>\r\n");
				html.append("				</div>\r\n");
				html.append("				<p class=\"time\">"+schedule+", 총 "+dto.getClass_cnt()+"회</p>\r\n");
				html.append("			</div>\r\n");
				html.append("		</div>\r\n");
				html.append("	</a>\r\n");
				html.append("	<div class=\"bottom_info\">\r\n");
				html.append("		<p class=\"price\">"+fee+"원</p>\r\n");
				html.append("	</div>");
				html.append("</div>");
			}
		}
		String result = html.toString();
		return result;
	}


	// 메인페이지 강좌 정보 가져오기
	@Override
	public List<ClassDTO> getMainClassList(MainLectSearchDTO mainLectSearchDTO) throws Exception {
		log.info("AppSearchServiceImpl.getMainClassList() 호출");
		return this.appSearchMapper.mainSelectClassList(mainLectSearchDTO);
	}

	
	@Override
	public String mainLecHTML(MainLectSearchDTO mainLectSearchDTO, HttpServletRequest request) throws Exception {
		log.info("AppSearchServiceImpl.mainLecHTML() 호출");
		StringBuilder html = new StringBuilder();
		List<ClassDTO> list = getMainClassList(mainLectSearchDTO);
		
		String avDay = null;
		String schedule = null;
		String fee = null;
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		
		if(!list.isEmpty()) {
			switch (mainLectSearchDTO.getPath()) {
			case "recommendation": /* 추천 강좌 */
				for(ClassDTO dto : list) {
					avDay = (dto.getMon().equals("Y")?"월":"") + (dto.getTue().equals("Y")?"화":"") + (dto.getWed().equals("Y")?"수":"") + (dto.getThu().equals("Y")?"목":"")
							+ (dto.getFri().equals("Y")?"금":"") + (dto.getSat().equals("Y")?"토":"") + (dto.getSun().equals("Y")?"일":"") ;
					schedule = String.format("%s %s:%s~%s:%s", avDay, dto.getStart_time().substring(0, 2), dto.getStart_time().substring(2), dto.getEnd_time().substring(0, 2), dto.getEnd_time().substring(2));
					
					fee = decimalFormat.format(dto.getClass_fee());
					
					html.append("<div class=\"swiper-slide card_list_v\">");
					html.append("	<a href=\"/application/search/view.do?branch_id="+dto.getBranch_id()+"&amp;yy="+dto.getOpen_year()+"&amp;lectSmsterCd="+dto.getOpen_smst_id()+"&amp;lectCd="+dto.getClass_semester_sq()+"\" class=\"lec_list\">");
					html.append("      <div class=\"img_resize_w img\">");
					html.append("		  <img src=\"/upload/thumbnail/"+dto.getClass_img()+"\" alt=\""+dto.getClass_img()+"\">");
					html.append("      </div>");
					html.append("      <div class=\"con\">");
					html.append("         <div class=\"label_div\">");
					html.append("			 <p class=\"label large "+ (dto.getClass_st().equals("접수중") ? "lime" : "gray") +"\">"+dto.getClass_st()+"</p>");
					html.append("            <p class=\"label large border\">공동모객</p>");
					html.append("         </div>");
					html.append("         <p class=\"tit\">"+dto.getClass_nm()+"</p>");
					html.append("         <div class=\"info_con\">");
					html.append("            <div class=\"type_div\">");
					html.append("               <p class=\"type\">"+dto.getSmst_nm()+"</p>");
					html.append("               <p class=\"type\">"+dto.getName()+"</p>");
					html.append("            </div>");
					html.append("			 <p class=\"time\">"+schedule+", 총 "+dto.getClass_cnt()+"회</p>");
					html.append("         </div>");
					html.append("      </div>");
					html.append("   </a>");
					html.append("   <div class=\"bottom_info\">");
					html.append("	   <p class=\"price\">"+fee+"원</p>");
					html.append("      <button type=\"button\" class=\"cart\" onclick=\"fnc.cartBtn("+dto.getBranch_id()+","+dto.getOpen_year()+","+dto.getOpen_smst_id()+","+dto.getDetail_class_sq()+","+dto.getClass_st_id()+");\"></button>");
					html.append("   </div>");
					html.append("</div>");
				} // for
				break;

			case "new": /* 신규 강좌 */
				for(ClassDTO dto : list) {
					avDay = (dto.getMon().equals("Y")?"월":"") + (dto.getTue().equals("Y")?"화":"") + (dto.getWed().equals("Y")?"수":"") + (dto.getThu().equals("Y")?"목":"")
							+ (dto.getFri().equals("Y")?"금":"") + (dto.getSat().equals("Y")?"토":"") + (dto.getSun().equals("Y")?"일":"") ;
					schedule = String.format("%s %s:%s~%s:%s", avDay, dto.getStart_time().substring(0, 2), dto.getStart_time().substring(2), dto.getEnd_time().substring(0, 2), dto.getEnd_time().substring(2));
					
					fee = decimalFormat.format(dto.getClass_fee());
					
					html.append("<div class=\"swiper-slide card_list_v\" data-tot-cnt=\""+ dto.getTot_cnt() +"\">");
					html.append("	<a href=\"/application/search/view.do?branch_id="+dto.getBranch_id()+"&amp;yy="+dto.getOpen_year()+"&amp;lectSmsterCd="+dto.getOpen_smst_id()+"&amp;lectCd="+dto.getClass_semester_sq()+"\" class=\"lec_list\">");
					html.append("      <p class=\"small_label wide NEW\"></p>");
					html.append("      <div class=\"img_resize_w img\">");
					html.append("		  <img src=\"/upload/thumbnail/"+dto.getClass_img()+"\" alt=\""+dto.getClass_img()+"\">");
					html.append("      </div>");
					html.append("      <div class=\"con\">");
					html.append("         <div class=\"label_div\">");
					html.append("			 <p class=\"label large "+ (dto.getClass_st().equals("접수중") ? "lime" : "gray") +"\">"+dto.getClass_st()+"</p>");
					html.append("            <p class=\"label large black_gray\">"+dto.getBranch_nm()+"</p>");
					html.append("         </div>");
					html.append("         <p class=\"tit\">"+dto.getClass_nm()+"</p>");
					html.append("         <div class=\"info_con\">");
					html.append("            <div class=\"type_div\">");
					html.append("               <p class=\"type\">"+dto.getSmst_nm()+"</p>");
					html.append("               <p class=\"type\">"+dto.getName()+"</p>");
					html.append("            </div>");
					html.append("			 <p class=\"time\">"+schedule+", 총 "+dto.getClass_cnt()+"회</p>");
					html.append("         </div>");
					html.append("      </div>");
					html.append("   </a>");
					html.append("   <div class=\"bottom_info\">");
					html.append("	   <p class=\"price\">"+fee+"원</p>");
					html.append("      <button type=\"button\" class=\"cart\" onclick=\"fnc.cartBtn("+dto.getBranch_id()+","+dto.getOpen_year()+","+dto.getOpen_smst_id()+","+dto.getDetail_class_sq()+","+dto.getClass_st_id()+");\"></button>");
					html.append("   </div>");
					html.append("</div>");
				} // for
				break;
				
			default: /* 카테고리별 강좌 */
				for(ClassDTO dto : list) {
					avDay = (dto.getMon().equals("Y")?"월":"") + (dto.getTue().equals("Y")?"화":"") + (dto.getWed().equals("Y")?"수":"") + (dto.getThu().equals("Y")?"목":"")
							+ (dto.getFri().equals("Y")?"금":"") + (dto.getSat().equals("Y")?"토":"") + (dto.getSun().equals("Y")?"일":"") ;
					schedule = String.format("%s %s:%s~%s:%s", avDay, dto.getStart_time().substring(0, 2), dto.getStart_time().substring(2), dto.getEnd_time().substring(0, 2), dto.getEnd_time().substring(2));
					
					fee = decimalFormat.format(dto.getClass_fee());
					
					html.append("<div class=\"card_list_h\">");
					html.append("	<a href=\"/application/search/view.do?branch_id="+dto.getBranch_id()+"&amp;yy="+dto.getOpen_year()+"&amp;lectSmsterCd="+dto.getOpen_smst_id()+"&amp;lectCd="+dto.getClass_semester_sq()+"\" class=\"lec_list\">\r\n");
					html.append("      <div class=\"img_wrap\">");
					html.append("      	 <div class=\"img_resize_w img\">");
					html.append("		  <img src=\"/upload/thumbnail/"+dto.getClass_img()+"\" alt=\""+dto.getClass_img()+"\">\r\n");
					html.append("        </div>");
					html.append("      </div>");
					html.append("      <div class=\"con\">");
					html.append("         <div class=\"label_div\">");
					html.append("			 <p class=\"label large "+ (dto.getClass_st().equals("접수중") ? "lime" : "gray") +"\">"+dto.getClass_st()+"</p>");
					html.append("            <p class=\"label large black_gray\">"+dto.getBranch_nm()+"</p>");
					html.append("         </div>");
					html.append("         <p class=\"tit\">"+dto.getClass_nm()+"</p>");
					html.append("         <div class=\"info_con\">");
					html.append("            <div class=\"type_div\">");
					html.append("               <p class=\"type\">"+dto.getSmst_nm()+"</p>");
					html.append("               <p class=\"type\">"+dto.getName()+"</p>");
					html.append("            </div>");
					html.append("			 <p class=\"time\">"+schedule+", 총 "+dto.getClass_cnt()+"회</p>\r\n");
					html.append("         </div>");
					html.append("      </div>");
					html.append("   </a>");
					html.append("   <div class=\"bottom_info\">");
					html.append("	   <p class=\"price\">"+fee+"원</p>\r\n");
					html.append("      <button type=\"button\" class=\"cart\" onclick=\"fnc.cartBtn("+dto.getBranch_id()+","+dto.getOpen_year()+","+dto.getOpen_smst_id()+","+dto.getDetail_class_sq()+","+dto.getClass_st_id()+");\"></button>");
					html.append("   </div>");
					html.append("</div>");
				} // for
				break;
			} // switch
			
		} // if(!list.isEmpty())
		return html.toString();
	}
	
	// 강좌 상세정보 가져오기
	@Override
	public ClassDTO DetailClassInfo(int branch_id, int yy, int lectSmsterCd, int lectCd) throws Exception {
		log.info("AppSearchServiceImpl.DetailClassInfo() 호출");
		return this.appSearchMapper.DetailClassInfo(branch_id, yy, lectSmsterCd, lectCd);
	}

	// 강사 정보 가져오기 - 학력
	@Override
	public List<TeducationDTO> eduInfo(int member_sq) throws Exception {
		log.info("AppSearchServiceImpl.eduInfo() 호출");
		return this.appSearchMapper.eduInfo(member_sq);
	}

	// 강사 정보 가져오기 - 경력
	@Override
	public List<TcareerDTO> careerInfo(int member_sq) throws Exception {
		log.info("AppSearchServiceImpl.careerInfo() 호출");
		return this.appSearchMapper.careerInfo(member_sq);
	}

	// 강사 정보 가져오기 - 수상
	@Override
	public List<TawardsDTO> awardInfo(int member_sq) throws Exception {
		log.info("AppSearchServiceImpl.awardInfo() 호출");
		return this.appSearchMapper.awardInfo(member_sq);
	}

	// 강사 정보 가져오기 - 자격
	@Override
	public List<TcertificateDTO> certiInfo(int member_sq) throws Exception {
		log.info("AppSearchServiceImpl.certiInfo() 호출");
		return this.appSearchMapper.certiInfo(member_sq);
	}
	
	// 강사 프로필 정보 가져오기
	@Override
	public TeacherDTO selectTeacherInfo(int member_sq) throws Exception {
		log.info("AppSearchServiceImpl.selectTeacherInfo() 호출");
		log.info("member_sq : " + member_sq);
		return this.appSearchMapper.selectTeacherInfo(member_sq);
	}
	
	// 강사 프로필 정보 ajax html 생성
	@Override
	public String teacherHTML(int member_sq) throws Exception {
		log.info("AppSearchServiceImpl.teacherHTML() 호출");
		TeacherDTO dto = selectTeacherInfo(member_sq);
		List<TeducationDTO> edu = eduInfo(member_sq);
		List<TcareerDTO> career = careerInfo(member_sq);
		List<TawardsDTO> award = awardInfo(member_sq);
		List<TcertificateDTO> certi = certiInfo(member_sq);
		
		StringBuilder html = new StringBuilder();
		
		html.append("<div class=\"tit_area\">");
		html.append("	<div class=\"instructor_img teacherHasImg has_img\">");
		html.append("		<!-- 강사님 사진이 있을 경우 has_img 클래스 추가해주세요 -->");
		html.append("		<p class=\"img_resize_w img\">");
		if(dto.getProfil_img() != null)
			html.append("			<img class=\"teacherWebPath\" src=\"https://culture.lotteshopping.com/files/CUL_ONL/OLD/COMMON/IMAGES/TECH/036845.jpg\" alt=\"036845.jpg\">");
		html.append("			</p>");
		html.append("	</div>");
		html.append("	<div class=\"tit_wrap\">");
		html.append("		<p class=\"title f_desc lectNm\"></p>");
		html.append("		<p class=\"instructor_name tcNm\">"+ dto.getTeacher_nm() +"</p>");
		html.append("	</div>");
		html.append("</div>");
		html.append("<div class=\"dot_txt_box\" style=\"display:none;\">");
		html.append("	<p class=\"f_body2 teacherSelfIntrdnCont\">"+ dto.getTeacher_intro() +"</p>");
		html.append("</div>");
		html.append("<div class=\"sub_inner\">");
		html.append("	<div class=\"sub_tit_area\">");
		html.append("		<p class=\"tit f_h2\">학력 및 경력</p>");
		html.append("	</div>");
		html.append("	<div class=\"instructor_info\">");
		html.append("		<div class=\"info_list teacherInfo1\">");
		if(!edu.isEmpty()) {
			for(TeducationDTO edto : edu) {
				html.append("			<div class=\"list_title edu\">");
				html.append("					<div class=\"title\">");
				html.append("						<div class=\"label_div\">");
				html.append("							<p class=\"label small black\">학력</p>");
				html.append("						</div>");
				html.append("						<p class=\"f_body1\">"+ edto.getUniversity_nm() + " " + edto.getMajor() + "</p>");
				html.append("					</div>");
				html.append("					<div class=\"type_div\">");
				html.append("						<p class=\"type\">"+ edto.getGraduate_state() +"</p>");
				html.append("						<p class=\"type\">"+ edto.getGraduate_year() +"</p>");
				html.append("					</div>");
				html.append("				</div>");
			}
		}
		if(!career.isEmpty()) {
			for(TcareerDTO cdto : career) {
				html.append("			<div class=\"list_title career\">");
				html.append("					<div class=\"title\">");
				html.append("						<div class=\"label_div\">");
				html.append("							<p class=\"label small black\">경력</p>");
				html.append("						</div>");
				html.append("						<p class=\"f_body1\">"+ cdto.getPublisher() +"</p>");
				html.append("					</div>");
				html.append("					<div class=\"type_div\">");
				html.append("						<p class=\"type\">"+ cdto.getStart_dt().substring(0, 10) + "~" + cdto.getEnd_dt().substring(0, 10) + "</p>");
				html.append("					</div>");
				html.append("				</div>");
			}
		}
		html.append("			</div>");
		html.append("	</div>");
		html.append("</div>");
		html.append("<div class=\"sub_inner\">");
		html.append("	<div class=\"sub_tit_area\">");
		html.append("		<p class=\"tit f_h2\">수상내역 및 자격증</p>");
		html.append("	</div>");
		html.append("	<div class=\"instructor_info\">");
		html.append("		<div class=\"info_list teacherInfo2\">");
		if(!award.isEmpty()) {
			for(TawardsDTO adto : award) {
				html.append("			<div class=\"list_title edu\">");
				html.append("					<div class=\"title\">");
				html.append("						<div class=\"label_div\">");
				html.append("							<p class=\"label small black\">수상내역</p>");
				html.append("						</div>");
				html.append("						<p class=\"f_body1\">"+ adto.getAwards_history() +"</p>");
				html.append("					</div>");
				html.append("					<div class=\"type_div\">");
				html.append("						<p class=\"type\">"+ adto.getPublisher() +"</p>");
				html.append("						<p class=\"type\">"+ adto.getAwards_dt().substring(0, 10) +"</p>");
				html.append("					</div>");
				html.append("				</div>");
			}
		}
		if(!certi.isEmpty()) {
			for(TcertificateDTO rdto : certi) {
				html.append("				<div class=\"list_title career\">");
				html.append("					<div class=\"title\">");
				html.append("						<div class=\"label_div\">");
				html.append("							<p class=\"label small black\">자격증</p>");
				html.append("						</div>");
				html.append("						<p class=\"f_body1\">"+ rdto.getCertificate_nm() +"</p>");
				html.append("					</div>");
				html.append("					<div class=\"type_div\">");
				html.append("						<p class=\"type\">"+ rdto.getPublisher() +"</p>");
				html.append("						<p class=\"type\">"+ rdto.getAcquisition_dt().substring(0, 10) +"</p>");
				html.append("					</div>");
				html.append("				</div> ");
			}
		}
		html.append("			</div>");
		html.append("	</div>");
		html.append("</div>");
		html.append("<div class=\"flex_btn_wrap\">");
		html.append("	<a href=\"javascript:$('.instructor_intro_pop .btn_close').click();\" class=\"border_btn\"> <span>닫기</span>");
		html.append("	</a>");
		html.append("</div>");
		
		String result = html.toString();
		return result;
	}

	
	// 강좌 상세보기 + 옵션 정보
	@Override
	public ClassDTO selectClassInfo(int branch_id, int yy, int lectSmsterCd, int lectCd) throws Exception {
		log.info("AppSearchServiceImpl.selectClassInfo() 호출");
		return this.appSearchMapper.selectClassInfo(branch_id, yy, lectSmsterCd, lectCd);
	}




	
}
