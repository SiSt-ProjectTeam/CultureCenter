package com.culture.demo.service;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culture.demo.domain.ChildrenDTO;
import com.culture.demo.domain.ClassDTO;
import com.culture.demo.domain.ClassFormDTO;
import com.culture.demo.domain.MainLectSearchDTO;
import com.culture.demo.domain.MemberDTO;
import com.culture.demo.domain.ReviewDTO;
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

	// 강좌 정보 가져오기 - 지점으로 찾기
	@Override
	public List<ClassDTO> selectClassList(int branch_id, SearchBranchDTO searchBranchDTO
			, String[] yyl, String[] lectcll, String[] lectstl, String[] dayl, String[] timel, String[] amtl) throws Exception {
		log.info("AppS earchServiceImpl.selectBranchList() 호출");
		return this.appSearchMapper.selectClassList(branch_id, searchBranchDTO, yyl, lectcll, lectstl, dayl, timel, amtl);
	}

	// 강좌 목록 ajax html 생성 - 지점으로 찾기
	@Override
	public String lecHTML(int branch_id, SearchBranchDTO searchBranchDTO, String[] yyl, String[] lectcll, String[] lectstl, String[] dayl, String[] timel, String[] amtl) throws Exception {
		log.info("AppSearchServiceImpl.LecHTML() 호출");
		StringBuilder html = new StringBuilder();
		List<ClassDTO> list = selectClassList(branch_id, searchBranchDTO, yyl, lectcll, lectstl, dayl, timel, amtl);

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
				html.append("	<a href=\"/application/search/view.do?branch_id="+dto.getBranch_id()+"&amp;yy="+dto.getOpen_year()+"&amp;lectSmsterCd="+dto.getOpen_smst_id()+"&amp;lectCd="+dto.getDetail_class_sq()+"\" class=\"lec_list\">\r\n");
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
				if(dto.getClass_st().equals("접수중")) // 강좌 상태 == '접수중'인 강좌만 장바구니 버튼 출력
					html.append("		<button type=\"button\" class=\"cart\" onclick=\"fnc.cartBtn('"+dto.getBranch_id()+"', '"+dto.getOpen_year()+"', '"+dto.getOpen_smst_id()+"', '"+dto.getDetail_class_sq()+"', '"+dto.getClass_st_id()+"');\"></button>");
				html.append("	</div>");
				html.append("</div>");
			}
		}
		String result = html.toString();
		return result;
	}

	// 강좌 정보 가져오기 - 강좌로 찾기
	@Override
	public List<ClassDTO> selectCateClassList(SearchBranchDTO searchCategoryDTO, String[] brchCdl, String[] yyl, String[] lectcll,
			String[] lectstl, String[] dayl, String[] timel, String[] amtl) throws Exception {
		log.info("AppSearchServiceImpl.selectCateClassList() 호출");
		return this.appSearchMapper.selectCateClassList(searchCategoryDTO, brchCdl, yyl, lectcll, lectstl, dayl, timel, amtl);
	}

	// 강좌 목록 ajax html 생성 - 강좌로 찾기
	@Override
	public String cateLecHTML(SearchBranchDTO searchCategoryDTO, String[] brchCdl, String[] yyl, String[] lectcll,
			String[] lectstl, String[] dayl, String[] timel, String[] amtl) throws Exception {
		log.info("AppSearchServiceImpl.cateLecHTML() 호출");
		StringBuilder html = new StringBuilder();
		List<ClassDTO> list = selectCateClassList(searchCategoryDTO, brchCdl, yyl, lectcll, lectstl, dayl, timel, amtl);

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
				html.append("	<a href=\"/application/search/view.do?branch_id="+dto.getBranch_id()+"&amp;yy="+dto.getOpen_year()+"&amp;lectSmsterCd="+dto.getOpen_smst_id()+"&amp;lectCd="+dto.getDetail_class_sq()+"\" class=\"lec_list\">\r\n");
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
				if(dto.getClass_st().equals("접수중")) // 강좌 상태 == '접수중'인 강좌만 장바구니 버튼 출력
					html.append("		<button type=\"button\" class=\"cart\" onclick=\"fnc.cartBtn('"+dto.getBranch_id()+"', '"+dto.getOpen_year()+"', '"+dto.getOpen_smst_id()+"', '"+dto.getDetail_class_sq()+"', '"+dto.getClass_st_id()+"');\"></button>");
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
					if(dto.getClass_st().equals("접수중"))
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
					if(dto.getClass_st().equals("접수중"))
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
					if(dto.getClass_st().equals("접수중"))
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
			html.append("			<img class=\"teacherWebPath\" src=\"/upload/teacher/"+dto.getProfil_img()+"\" alt=\"dto.getProfil_img()\">");
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
	
	public List<ClassDTO> selectInteList(SearchBranchDTO searchBranchDTO, String[] brchCdl, String[] yyl, String[] lectcll,
			String[] lectstl, String[] dayl, String[] timel, String[] amtl) throws Exception {
		log.info("AppSearchServiceImpl.selectInteList() 호출");
		return this.appSearchMapper.selectInteList(searchBranchDTO, brchCdl, yyl, lectcll, lectstl, dayl, timel, amtl);
	}

	@Override
	public String inteHTML(SearchBranchDTO searchBranchDTO, String[] brchCdl, String[] yyl, String[] lectcll,
			String[] lectstl, String[] dayl, String[] timel, String[] amtl) throws Exception {
		log.info("AppSearchServiceImpl.selectClassInfo() 호출");
		StringBuilder html = new StringBuilder();
		List<ClassDTO> list = selectInteList(searchBranchDTO, brchCdl, yyl, lectcll, lectstl, dayl, timel, amtl);

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
				html.append("	<a href=\"/application/search/view.do?branch_id="+dto.getBranch_id()+"&amp;yy="+dto.getOpen_year()+"&amp;lectSmsterCd="+dto.getOpen_smst_id()+"&amp;lectCd="+dto.getDetail_class_sq()+"\" class=\"lec_list\">\r\n");
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
				if(dto.getClass_st().equals("접수중")) // 강좌 상태 == '접수중'인 강좌만 장바구니 버튼 출력
					html.append("		<button type=\"button\" class=\"cart\" onclick=\"fnc.cartBtn('"+dto.getBranch_id()+"', '"+dto.getOpen_year()+"', '"+dto.getOpen_smst_id()+"', '"+dto.getDetail_class_sq()+"', '"+dto.getClass_st_id()+"');\"></button>");
				html.append("	</div>");
				html.append("</div>");
			}
		}
		String result = html.toString();
		return result;
	}
	
	@Override
	public List<ReviewDTO> getReviewList(ClassFormDTO classFormDTO) throws Exception {
		log.info("AppSearchServiceImpl.getReviewList() 호출");
		return this.appSearchMapper.getReviewList(classFormDTO);
	}

	@Override
	public String reviewListHTML(ClassFormDTO classFormDTO) throws Exception {
		log.info("AppSearchServiceImpl.reviewListHTML() 호출");
		List<ReviewDTO> list = getReviewList(classFormDTO);
		StringBuilder html = new StringBuilder();

		if(list.isEmpty() ) {
			html.append("<div class=\"no_srch_area no_pb\" data-tot-cnt=\"0\">");
			html.append("			<div class=\"no_srch_div\">");
			html.append("				<p class=\"txt f_h2\">");
			html.append("					<span class=\"normal_value\">작성된 수강후기가 없습니다.</span>>\r\n"); 
			html.append("				</p>");
			html.append("			</div>");
			html.append("		</div>");
		}else {
			for(ReviewDTO dto : list ) {
				html.append("<div class=\"thum_list_wrap\" data-tot-cnt=\""+dto.getTot_cnt()+"\">");
				html.append("	<a href=\"javascript:search.reviewDtl('"+dto.getBranch_id()+"', '"+dto.getOpen_year()+"', '"+dto.getOpen_smst_id()+"', '"+dto.getDetail_class_sq()+"', '"+dto.getTeacher_sq()+"', '"+dto.getMember_sq()+"', this);\" class=\"thum_list\">");
				html.append("		<div class=\"txt_wrap\">");
				html.append("			<div class=\"thum_left\">");
				html.append("				<div class=\"label_div\">");
				html.append("					<p class=\"label small black_gray\">"+dto.getBranch_nm()+"</p>");
				html.append("				</div>");
				html.append("				<p class=\"title\">"+dto.getClass_nm()+"</p>");
				html.append("				<p class=\"sub_title limit_line f_body2\">"+dto.getReview_title()+"</p>");
				html.append("			</div>");
				html.append("			<div class=\"thum_right\">");
				html.append("				<div class=\"star_rating\">");
				for(int i=0; i<5; i++ ) {
					if(i < dto.getRating()) html.append("<span class=\"star\"></span>\r\n");
					else html.append("<span class=\"star blank\"></span>\r\n");
				}
				html.append("				</div>");
				html.append("				<div class=\"type_div\">");
				html.append("					<p class=\"type f_caption2\">"+dto.getName()+"</p>");
				html.append("					<p class=\"type f_caption2\">"+dto.getDate_writingout_dt()+"</p>");
				html.append("					<p class=\"comment_num f_caption2\">"+dto.getComment_cnt()+"</p>");
				html.append("				</div>");
				html.append("			</div>");
				html.append("		</div>");
				html.append("	</a>");
				html.append("</div>");
			}
		}

		return html.toString();
	}
	
	@Override
	public ReviewDTO getReviewDtl(int brchCd, int yy, int lectSmsterCd, int lectCd, int tcNo, int mbrNo) throws Exception {
		log.info("AppSearchServiceImpl.getReviewDtl() 호출");
		return this.appSearchMapper.getReviewDtl(brchCd, yy, lectSmsterCd, lectCd, tcNo, mbrNo);
	}

	@Override
	public String reviewDtlHTML(int brchCd, int yy, int lectSmsterCd, int lectCd, int tcNo, int mbrNo) throws Exception {
		log.info("AppSearchServiceImpl.reviewDtlHTML() 호출");
		ReviewDTO dto = getReviewDtl(brchCd, yy, lectSmsterCd, lectCd, tcNo, mbrNo);

		StringBuilder html = new StringBuilder();
		html.append("<div class=\"thum_list_w\">");
		html.append("	<div class=\"thum_list_wrap\">");
		html.append("		<div class=\"thum_title\">수강 정보</div>");
		html.append("		<a href=\"/application/search/view.do?brchCd="+dto.getBranch_id()+"&yy="+dto.getOpen_year()+"&lectSmsterCd="+dto.getOpen_smst_id()+"&lectCd="+dto.getDetail_class_sq()+"\" class=\"thum_list\">");
		html.append("			<div class=\"thum_wrap\">");
		html.append("				<div class=\"thum_box img_resize_w\">");
		html.append("					<img src=\"/upload/thumbnail/"+dto.getClass_img()+"\" alt=\"upload.jpg\">");
		html.append("				</div>");
		html.append("			</div>");
		html.append("			<div class=\"txt_wrap\">");
		html.append("				<div class=\"thum_left\">");
		html.append("					<div class=\"label_div\">");
		html.append("						<p class=\"label small border\">"+dto.getLrclsctegery()+"</p>");
		html.append("						<p class=\"label small border\">"+dto.getMdclsctegery()+"</p>");
		html.append("					</div>");
		html.append("					<p class=\"title limit_line_two\">"+dto.getClass_nm()+"</p>");
		html.append("				</div>");
		html.append("				<div class=\"thum_right\">");
		html.append("					<div class=\"type_div\">");
		html.append("						<p class=\"type f_caption2\">"+dto.getBranch_nm()+"</p>");
		html.append("						<p class=\"type f_caption2\">"+dto.getTeacher_nm()+"</p>");
		html.append("						<p class=\"type contour f_caption2\">"+dto.getOpen_year()+"년 "+dto.getSmst_nm()+"</p>");
		html.append("						<p class=\"type contour f_caption2\">"+dto.getSchedule_start_dt()+" ~ "+dto.getSchedule_end_dt()+"</p>");
		html.append("					</div>");
		html.append("				</div>");
		html.append("			</div>");
		html.append("		</a>");
		html.append("	</div>");
		html.append("</div>");
		if(dto.getReview_img() != null) {
			html.append("<p class=\"img\">");
			html.append("	<img src=\"/upload/review/"+dto.getReview_img()+"\" alt=\"upload.jpg\">");
			html.append("</p>");
		}
		html.append("<div class=\"flex_box\">");
		html.append("	<div class=\"type_div\">");
		html.append("		<p class=\"type\">"+dto.getName()+"</p>");
		html.append("		<p class=\"type\">"+dto.getWritingout_dt()+"</p>");
		html.append("	</div>");
		html.append("	<div class=\"star_rating\">");
		for(int i=0; i<5; i++ ) {
			if(i < dto.getRating()) html.append("<span class=\"star\"></span>\r\n");
			else html.append("<span class=\"star blank\"></span>\r\n");
		}
		html.append("	</div>");
		html.append("</div>");
		html.append("<p class=\"url_link\">");
		html.append("	<a target=\"_blank\" href=\"//\"></a>");
		html.append("</p>");
		html.append("<div class=\"txt_con\">");
		html.append("	<p class=\"title\">"+dto.getReview_title()+"</p>");
		html.append("	<p class=\"con_txt\">"+dto.getReview_content()+"</p>");
		html.append("</div>");
		
		return html.toString();
	}

	@Override
	public MemberDTO selectMemberInfo(int member_sq) {
		log.info("AppSearchServiceImpl.selectMemberInfo() 호출");
		return this.appSearchMapper.selectMemberInfo(member_sq);
	}

	@Override
	public List<ChildrenDTO> selectChildInfo(int member_sq) {
		log.info("AppSearchServiceImpl.selectChildInfo() 호출");
		return this.appSearchMapper.selectChildInfo(member_sq);
	}

}
