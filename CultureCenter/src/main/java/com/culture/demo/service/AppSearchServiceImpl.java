package com.culture.demo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culture.demo.domain.ClassDTO;
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
	public String LecHTML(int branch_id, SearchBranchDTO searchBranchDTO, String[] yyl, String[] lectcll, String[] lectstl, String[] dayl, String[] timel, String[] amtl) throws Exception {
		log.info("AppSearchServiceImpl.LecHTML() 호출");
		StringBuilder html = new StringBuilder();
		List<ClassDTO> list = selectClassList(branch_id, searchBranchDTO, yyl, lectcll, lectstl, dayl, timel, amtl);
		int totCnt = list.size();
		
		if(list.isEmpty()) {
			html.append("<div class=\"no_srch_area no_pb\" data-tot-cnt=\"0\">\r\n");
			html.append("	<div class=\"no_srch_div\">\r\n");
			html.append("		<p class=\"txt f_h2\">\r\n");
			html.append("			<span class=\"normal_value\">진행중인 강좌가 없습니다.</span>\r\n");
			html.append("		</p>\r\n");
			html.append("	</div>\r\n");
		} else {
			for(ClassDTO dto : list) {
				html.append("<div class=\"card_list_v\" data-tot-cnt=\""+totCnt+"\">\r\n");
				html.append("	<a href=\"/resourecs/application/search/view.do?branch_id="+dto.getBranch_id()+"&amp;yy="+dto.getOpen_year()+"&amp;lectSmsterCd="+dto.getOpen_smst_id()+"&amp;lectCd="+dto.getClass_semester_sq()+"\" class=\"lec_list\">\r\n");
				html.append("		<div class=\"img_box\">\r\n");
				html.append("			<div class=\"img_resize_w img\">\r\n");
				//html.append("				<img src=\""+realPath+"/"+dto.getClass_img()+"\" alt=\""+dto.getClass_img()+"\">\r\n");
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
				html.append("					<p class=\"type\">"+dto.getTeacher_nm()+"</p>\r\n");
				html.append("				</div>\r\n");
				html.append("				<p class=\"time\">"+dto.getClassTime()+", 총 "+dto.getClass_cnt()+"회</p>\r\n");
				html.append("			</div>\r\n");
				html.append("		</div>\r\n");
				html.append("	</a>\r\n");
				html.append("	<div class=\"bottom_info\">\r\n");
				html.append("		<p class=\"price\">"+dto.getClass_fee()+"원</p>\r\n");
				html.append("	</div>");
				html.append("</div>");
			}
		}
		String result = html.toString();
		return result;
	}

}
