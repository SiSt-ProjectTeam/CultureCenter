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
public class ReviewServiceImpl implements ReviewService{
	
	private ReviewMapper reviewMapper;

	
	// 후기 ajax html
	@Override
	public String reviewHTML(FrmSearchDTO frmSearchDTO) throws SQLException {
		log.info(">ReviewServiceImpl.ReviewHTML() 호출");
		
		
		int branch_id = frmSearchDTO.getBrchCd();
		//List<ReviewDTO> list = reviewMapper.getReviewList(orderSet, branch_id, q, frmSearchDTO);
		List<ReviewDTO> list = reviewMapper.getReviewList(branch_id, frmSearchDTO);
		
		StringBuilder html = new StringBuilder();
			
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
							  		 html.append(" <div class=\"thum_list_wrap\" data-tot-cnt=\""+dto.getTot_cnt()+"\"> \r\n");
							  		 html.append(" <a href=\"javascript:\" class=\"thum_list\" onclick=\"reviewCtrl.detail(this);\"\r\n");
							  		 html.append(" data-brch-cd=\""+dto.getBranch_id()+"\"data-yy=\""+dto.getWriting_year()+"\"data-lect-smster-cd=\""+dto.getOpen_smst_id()+"\"data-lect-cd=\""+dto.getClass_semester_sq()+"\"data-tc-cd-no=\""+dto.getTeacher_sq()+"\"data-mbr-no=\""+dto.getMember_sq()+"\" > \r\n");
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
	public List<ReviewDTO> dtlReview(ReviewDTO reviewDTO) throws SQLException, ClassNotFoundException{
		log.info(">ReviewServiceImpl.dtlReview() 호출");
		return reviewMapper.dtlReview(reviewDTO);
		
	}
	
	// 댓글 ajax
	@Override
	public String commtHTML(int review_sq, FrmSearchDTO frmSearchDTO) throws SQLException, ClassNotFoundException {
		log.info(">ReviewServiceImpl.commtHTML() 호출");
		List<ReviewDTO> list = reviewMapper.getCommtList(review_sq, frmSearchDTO);
		StringBuilder html = new StringBuilder();
		int totCnt = list.size(); // 댓글 총 갯수
		
		if(list.isEmpty() ) {
			  html.append("<div data-tot-cnt=\"0\"></div>");
		}else {
			for(ReviewDTO dto : list ) {
				html.append("<div class=\"review_list\" data-tot-cnt=\""+totCnt+"\">\r\n");
				html.append("                <div class=\"flex_box\">\r\n");
				html.append("                    <div class=\"writer_info\">\r\n");
				html.append("                        <div class=\"type_div\">\r\n");
				html.append("                            <p class=\"type\">"+dto.getName()+"</p>\r\n");
				html.append("                            <p class=\"type\">"+dto.getWrite_dt()+"</p>\r\n");
				html.append("                        </div>\r\n");
				html.append("                        <a href=\"javascript:\" class=\"comment_remove f_caption1\" role=\"btn\" onclick=\"reviewCtrl.deleteCmnt(this);\" data-sort-seqno=\"8\">삭제</a>\r\n");
				html.append("                    </div>\r\n");
				html.append("                </div>\r\n");
				html.append("                <p class=\"comment f_body2\">"+dto.getComment_content()+"</p>\r\n");
				html.append("</div>");
			}// for
		} // if
		return html.toString();
		
	} // commtHTML

	
	
}// ServiceImpl
