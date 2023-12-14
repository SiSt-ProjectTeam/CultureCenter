package com.culture.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.culture.demo.domain.FrmSearchDTO;
import com.culture.demo.domain.ReviewDTO;
import com.culture.demo.mapper.ReviewMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
@Service
@Log4j
@AllArgsConstructor
@RequestMapping("/community/review/*")
public class ReviewServiceImpl implements ReviewService{
	
	private ReviewMapper reviewMapper;
/*
	@Override
	public ArrayList<ReviewDTO> getReviewList(FrmSearchDTO frmSearchDTO) throws SQLException {
		log.info(">ReviewServiceImpl.getList() 호출");
		return this.ReviewMapper.getReivewList(frmSearchDTO);
	}
*/
	
	// 후기 정보 조회
	/*
	@Override
	public List<ReviewDTO> getReviewList(int branch_id,String orderSet, FrmSearchDTO frmSearchDTO) throws SQLException {
		log.info(">ReviewServiceImpl.getReviewList() 호출");
		return reviewMapper.getReviewList(branch_id, orderSet, frmSearchDTO);
	}
	 */
	
	// 후기 ajax html
	@Override
	public String reviewHTML(FrmSearchDTO frmSearchDTO) throws SQLException {
		log.info(">ReviewServiceImpl.ReviewHTML() 호출");
		
		
		int branch_id = frmSearchDTO.getBrchCd();
		//List<ReviewDTO> list = reviewMapper.getReviewList(orderSet, branch_id, q, frmSearchDTO);
		List<ReviewDTO> list = reviewMapper.getReviewList(branch_id, frmSearchDTO);
		
		StringBuilder html = new StringBuilder();
		int totCnt = list.size(); // 게시물 총 갯수
			
			  if(list.isEmpty() ) {
			  html.append("<div class=\"no_srch_area no_pb\" data-tot-cnt=\"0\">\r\n");
			  html.append("	<div class=\"no_srch_div\">\r\n");
			  html.append("	   <p class=\"txt f_h2\">\r\n");
			  html.append("			<span class=\"normal_value\">검색결과가 없습니다.</span>\r\n"); 
			  html.append("		</p>\r\n"); 
			  html.append("    </div>\r\n");
			  html.append("</div>\r\n"); 
			  
			  }else {
				  		for(ReviewDTO dto : list ) {
							  html.append("<div class=\"thum_list_wrap\" data-tot-cnt=\""+totCnt+"\"> \r\n");
							  html.append("<a href=\"javascript:\" class=\"thum_list\" onclick=\"reviewCtrl.detail(this);\"");
							  		  html.append("<a href=\"javascript:\" class=\"thum_list\" onclick=\"reviewCtrl.detail(this);\r\n");
									 // html.append("	<a href=\"/dtl.do?pageIndex="+dto.getPageIndex()+"&amp;listCnt="+dto.getListCnt()+"&amp;initIndex="+dto.getInitIndex()+"&amp;brchCd="+dto.getBranch_id()+"&yy="+dto.getOpen_year()+"&amp;lectSmsterCd="
									 //		  	+dto.getOpen_smst_id()+"&amp;lectCd="+dto.getLectCd()+"&amp;tcCdNo="+dto.getTeacherMember_sq()+"&amp;memberNo="+dto.getMember_sq()+"&amp;sortSeqno="+dto.getSortSeqno()+"&amp;cmntCont="+dto.getComment_content()+"\" class=\"thum_list\"
							  		  html.append("onclick=\"reviewCtrl.detail(this);\" data-brch-cd=\"0017\" data-yy=\"2023\" data-lect-smster-cd=\"2\" data-lect-cd=\"0687\" data-tc-cd-no=\"014986\" data-mbr-no=\"190976379\"> \r\n");
							  		  html.append("\" class=\"thum_list\" onclick=\"reviewCtrl.detail(this);\" data-brch-cd="+dto.getBranch_id()+" data-yy="+dto.getOpen_year()+" data-lect-smster-cd="+dto.getLectSmsterCd()+" data-lect-cd="+dto.getLectCd()+" data-tc-cd-no="+dto.getTeacherMember_sq()+" data-mbr-no="+dto.getMember_sq()+"> \r\n");
									  html.append("		<div class=\"thum_wrap\">\r\n");
									  html.append("			<div class=\"img_resize_w thum_box reverse\">\r\n");
									  html.append("				<img id=\"review_img\"\r\n");
									  html.append("				src=\"/"+dto.getClass_img()+"\"alt=\""+dto.getClass_img()+"\">\r\n");
									  html.append("			</div>\r\n");
									  html.append("		</div>\r\n");
									  html.append("		<div class=\"txt_wrap\">\r\n");
									  html.append("			<div class=\"thum_left\">\r\n");
									  html.append("				<div class=\"label_div\">\r\n");
									  html.append("					<p class=\"label small black_gray\">"+dto.getBranch_nm()+"</p>\r\n");
									  html.append("				</div>\r\n");
									  html.append("				<p class=\"title\">"+dto.getReview_title()+"</p>\r\n");
									  html.append("					<p class=\"sub_title limit_line f_body2\">"+dto.getClass_nm()+"</p>\r\n"); 
									  html.append("			</div>\r\n");
									  html.append("				<div class=\"thum_right\">\r\n");
									  html.append("					<div class=\"star_rating\">\r\n");
									  html.append("						<span class=\"star\"></span>".repeat(dto.getRating()) + "\r\n");
									  html.append("					</div>\r\n");
									  html.append("						<div class=\"type_div\">\r\n");
									  html.append("							<p class=\"type f_caption2\">"+dto.getName()+"</p>\r\n");
									  html.append("							<p class=\"type f_caption2\">"+dto.getDate_writingout_dt()+"</p>\r\n");
									  html.append("							<p class=\"comment_num f_caption2\">"+dto.getComment_cnt()+"</p>\r\n");
									  html.append("						</div>\r\n");
									  html.append("					</div>\r\n");
									  html.append("				</div>\r\n");
									  html.append("	</a>\r\n");
									  html.append("</div>");
					  }
			  }

			return html.toString();
	}
	
	// 수강후기 상세페이지
	@Override
	public ReviewDTO dtlReview(ReviewDTO dto) throws Exception {
		log.info(">ReviewServiceImpl.dtlReview() 호출");
		return this.reviewMapper.dtlReview(dto);
	}

	
}
