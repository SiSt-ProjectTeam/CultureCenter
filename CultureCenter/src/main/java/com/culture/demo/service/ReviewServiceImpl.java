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
	public String ReviewHTML(FrmSearchDTO frmSearchDTO) throws SQLException {
		log.info(">ReviewServiceImpl.ReviewHTML() 호출");
		
		
		int branch_id = frmSearchDTO.getBrchCd();
		String orderSet = frmSearchDTO.getOrderSet();
		String q = frmSearchDTO.getQ();
		List<ReviewDTO> list = reviewMapper.getReviewList(orderSet, branch_id, q, frmSearchDTO);
		
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
							  html.append("<div class=\"thum_list_wrap\" data-tot-cnt=\""+totCnt+"\"> \r\n"
									  );
									  html.append("	<a href=\"/dtl.do?branch_id="+dto.getBranch_id()+"&amp;smstId="+dto.getOpen_smst_id()+
											  		"&amp;classId="+dto.getClass_id()+"&amp;reviewSq="+dto.getReview_sq()+
											  		"&amp;member_sq="+dto.getMember_sq()+"&amp;commentCnt="+dto.getComment_cnt()+
											  		"\" class=\"thum_list\" onclick=\"reviewCtrl.detail(this);\" data-brch-cd=\"0017\" data-yy=\"2023\" data-lect-smster-cd=\"2\" data-lect-cd=\"0687\" data-tc-cd-no=\"014986\" data-mbr-no=\"190976379\"> \r\n");
									  html.append("		<div class=\"thum_wrap\">\r\n");
									  html.append("			<div class=\"img_resize_w thum_box reverse\">\r\n");
									  html.append("				<img id=\"review_img\"\r\n");
									  html.append("				src=\"/"+dto.getClass_img()+
															"\"alt=\""+dto.getClass_img()+"\">\r\n");
									  html.append("			</div>\r\n"); html.append("		</div>\r\n");
									  html.append("		<div class=\"txt_wrap\">\r\n");
									  html.append("			<div class=\"thum_left\">\r\n");
									  html.append("				<div class=\"label_div\">\r\n");
									  html.append("					<p class=\"label small black_gray\">"+dto.
									  getBranch_nm()+"</p>\r\n"); html.append("				</div>\r\n");
									  html.append("				<p class=\"title\">"+dto.getReview_title()+
									  "</p>\r\n");
									  html.append("					<p class=\"sub_title limit_line f_body2\">"
									  +dto.getClass_nm()+"</p>\r\n"); html.append("			</div>\r\n");
									  html.append("				<div class=\"thum_right\">\r\n");
									  html.append("					<div class=\"star_rating\">\r\n");
									  html.append("						<span class=\"star\">"+dto.getRating()+"</span>\r\n"); html.append("					</div>\r\n");
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

	
}
