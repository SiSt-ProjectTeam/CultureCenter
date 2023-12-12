package com.culture.demo.service;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culture.demo.domain.ClassDTO;
import com.culture.demo.domain.MainLectSearchDTO;
import com.culture.demo.domain.SearchBranchDTO;
import com.culture.demo.mapper.AppSearchMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AppSearchServiceImpl implements AppSearchService {
	
	@Autowired
	private AppSearchMapper appSearchMapper;
	
	// 강좌 정보 가져오기
	@Override
	public List<ClassDTO> selectClassList(@Param("branch_id") int branch_id, @Param("searchBranchDTO") SearchBranchDTO searchBranchDTO,
										  @Param("yyl") String[] yyl,
										  @Param("lectcll") String[] lectcll,
										  @Param("lectstl") String[] lectstl,
										  @Param("dayl") String[] dayl,
										  @Param("timel") String[] timel,
										  @Param("amtl") String[] amtl) throws Exception {
		log.info("AppSearchServiceImpl.selectBranchList() 호출");
		return appSearchMapper.selectClassList(branch_id, searchBranchDTO, yyl, lectcll, lectstl, dayl, timel, amtl);
	}
	
	// 강좌 목록 ajax html 생성
	@Override
	public String LecHTML(int branch_id, SearchBranchDTO searchBranchDTO, String[] yyl, String[] lectcll, String[] lectstl, String[] dayl, String[] timel, String[] amtl, HttpServletRequest request) throws Exception {
		log.info("AppSearchServiceImpl.LecHTML() 호출");
		StringBuilder html = new StringBuilder();
		List<ClassDTO> list = selectClassList(branch_id, searchBranchDTO, yyl, lectcll, lectstl, dayl, timel, amtl);
		int totCnt = list.size();
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
				
				html.append("<div class=\"card_list_v\" data-tot-cnt=\""+totCnt+"\">\r\n");
				html.append("	<a href=\"/resourecs/application/search/view.do?branch_id="+dto.getBranch_id()+"&amp;yy="+dto.getOpen_year()+"&amp;lectSmsterCd="+dto.getOpen_smst_id()+"&amp;lectCd="+dto.getClass_semester_sq()+"\" class=\"lec_list\">\r\n");
				html.append("		<div class=\"img_box\">\r\n");
				html.append("			<div class=\"img_resize_w img\">\r\n");
				html.append("				<img src=\""+uploadRealPath+"/thumbnail/"+dto.getClass_img()+"\" alt=\""+dto.getClass_img()+"\">\r\n");
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
		return this.appSearchMapper.selectMainClassList(mainLectSearchDTO);
	}

	
	@Override
	public String mainLecHTML(MainLectSearchDTO mainLectSearchDTO, HttpServletRequest request) throws Exception {
		log.info("AppSearchServiceImpl.mainLecHTML() 호출");
		StringBuilder html = new StringBuilder();
		List<ClassDTO> list = selectClassList(0, null, null, null, null, null, null, null);
		String uploadRealPath = request.getServletContext().getRealPath("/upload");
		
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
					html.append("	<a href=\"/resourecs/application/search/view.do?branch_id="+dto.getBranch_id()+"&amp;yy="+dto.getOpen_year()+"&amp;lectSmsterCd="+dto.getOpen_smst_id()+"&amp;lectCd="+dto.getClass_semester_sq()+"\" class=\"lec_list\">\r\n");
					html.append("      <div class=\"img_resize_w img\">");
					html.append("		  <img src=\""+uploadRealPath+"/thumbnail/"+dto.getClass_img()+"\" alt=\""+dto.getClass_img()+"\">\r\n");
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

			case "new": /* 신규 강좌 */
				break;
				
			default: /* 카테고리별 강좌 */
				break;
			} // switch
			
		} // if(!list.isEmpty())
		return html.toString();
	}
	
}
