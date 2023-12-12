package com.culture.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.culture.demo.domain.BranchDTO;
import com.culture.demo.mapper.BranchInfoMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class BranchInfoServiceImpl implements BranchInfoService{

	private BranchInfoMapper branchInfoMapper;

	//지점 목록 가져오기
	@Override
	public List<BranchDTO> getBranchList(int brchAreaCd) {	
		log.info("> BranchInfoServiceImpl.selectBranchList ...");		
		return this.branchInfoMapper.getBranchList(brchAreaCd);
	}

	//지점 목록 html
	@Override
	public String createBranchHtml(BranchDTO branchDTO) {
		log.info("> BranchInfoServiceImpl.createBranchHtml......");

		StringBuilder html = new StringBuilder();
		List<BranchDTO> branchList = getBranchList(branchDTO.getBrchAreaCd());

		html.append("<div class=\"swiper-container\">\r\n");
		html.append("    <div class=\"swiper-wrapper honeyBee\">\r\n");

		for (BranchDTO branch : branchList) {
			html.append("        <a href=\"javascript:\" onclick=\"branchCtrl.selectBrch(this, '"+ branch.getBranch_id()+"')\" data-brchCd=\"")
			.append(branch.getBranch_id()+ "\" class=\"btn swiper-slide useBrch")
			.append(branch.getBranch_id()== 1 ? " on" : "")  
			.append("\"><span>"+branch.getBranch_nm()+"</span></a>\r\n");
		}

		html.append("    </div>\r\n");
		html.append("</div>\r\n");
		//html.append("<button type=\"button\" class=\"drop_btn\"></button>");

		return html.toString();
	}

	
	//지점 정보 가져오기
	
	@Override public List<BranchDTO> getBranchInfo(int brchCd) {
		log.info("> BranchInfoServiceImpl.selectBranchList ..."); 
		return this.branchInfoMapper.getBranchInfo(brchCd); }
	
	
	//지점 정보 html	
	@Override public String createBranchInfoHtml(BranchDTO branchDTO) {
	log.info("> BranchInfoServiceImpl.createBranchInfoHtml ...");
	
	StringBuilder html = new StringBuilder(); 
	List<BranchDTO> branchList = getBranchInfo(branchDTO.getBrchCd()); 

	
	
	html.append("<script type=\"text/javascript\">\r\n"
			+ "				/*===========================================================================*/\r\n"
			+ "				//점별 지도 생성 \r\n"
			+ "				/*===========================================================================*/\r\n"
			+ "			\r\n"
			+ "					kakao.maps.load(function()  {\r\n"
			+ "			\r\n"
			+ "					var lat = \"" + branchDTO.getLat() +"\"; //위도\r\n"
			+ "					var lng = \""+ branchDTO.getLng() +"\"; //경도\r\n"
			+ "			\r\n"
			+ "					var mapContainer = document.getElementById('map'); // 지도를 표시할 div \r\n"
			+ "					var mapOption = { // 지도 옵션 \r\n"
			+ "						center : new kakao.maps.LatLng(lat,lng), // 지도의 중심좌표\r\n"
			+ "						level : 3\r\n"
			+ "					// 지도의 확대 레벨\r\n"
			+ "					};\r\n"
			+ "			\r\n"
			+ "					var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다\r\n"
			+ "			\r\n"
			+ "					var iwContent = '<div style=\"width:135px;text-align:center;margin:5px 5px 5px 5px;font:12px/1.5 sans-serif\"><strong>롯데문화센터"+ branchDTO.getBranch_nm() + " </strong></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다\r\n"
			+ "					iwPosition = new kakao.maps.LatLng(lat,lng), //인포윈도우 표시 위치입니다\r\n"
			+ "					iwRemoveable = false; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다\r\n"
			+ "			\r\n"
			+ "					// 인포윈도우를 생성하고 지도에 표시합니다\r\n"
			+ "					var infowindow = new kakao.maps.InfoWindow({\r\n"
			+ "						map : map, // 인포윈도우가 표시될 지도\r\n"
			+ "						position : iwPosition,\r\n"
			+ "						content : iwContent,\r\n"
			+ "						removable : iwRemoveable\r\n"
			+ "					});\r\n"
			+ "				})\r\n"
			+ "		\r\n"
			+ "			</script>			\r\n"
			+ "				<div class=\"con on\">\r\n"
			+ "					<div class=\"sub_con_section\">\r\n"
			+ "						<div class=\"store_info_area\">\r\n"
			+ "							<div class=\"store_info_swiper\">\r\n"
			+ "								<div class=\"swiper-container\">\r\n"
			+ "										<div class=\"swiper-wrapper\">`");
	
	
	
	
	
	html.append("<a href=\"javascript:\" class=\"swiper-slide\">\r\n"
			+ "			                <p class=\"img img_resize_w\">\r\n"
			+ "			                    <img src=\"http://localhost:80/CultureCentert/upload/branch/${지점대분류}/${지점소분류}/${elem}\" alt=\"${elem}\">\r\n"
			+ "			                </p>\r\n"
			+ "			             </a>");
	
	
	html.append("</div>\r\n"
			+ "			<!-- 							<div class=\"swiper-button-next\"></div> -->\r\n"
			+ "			<!-- 							<div class=\"swiper-button-prev\"></div> -->\r\n"
			+ "										<div class=\"swiper-pagination\"></div>\r\n"
			+ "								</div>\r\n"
			+ "							</div>\r\n"
			+ "							<div class=\"store_txt_wrap\">\r\n"
			+ "								<p class=\"txt f_body2\">\r\n"
			+ "									${data[brchCd].branchInfo.branch_addr}<br class=\"only_pc\"></p>\r\n"
			+ "								<p class=\"txt f_body2\">${data[brchCd].branchInfo.tel}</p>\r\n"
			+ "								<p class=\"txt f_body2\">${data[brchCd].branchInfo.classroom_nm}</p>\r\n"
			+ "							</div>\r\n"
			+ "						</div>\r\n"
			+ "					</div>\r\n"
			+ "					<!-- 2023-02-13 지점안내 내 기획전 영역 삭제 -->\r\n"
			+ "					<!---->\r\n"
			+ "					<!-- 2023-02-13 지점안내 내 기획전 영역 삭제 -->\r\n"
			+ "					<div class=\"sub_con_section\">\r\n"
			+ "						<p class=\"sub_tit_area f_h2\">${data[brchCd].branchInfo.branch_nm} 오시는 길</p>\r\n"
			+ "						<p class=\"map_area\">\r\n"
			+ "							<div id=\"map\" style=\"width: 100%; height: 470px;\"/>\r\n"
			+ "						</p>\r\n"
			+ "					</div>\r\n"
			+ "				</div>");
	
	return html.toString(); 
	}

}
