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

		for (int i = 0; i < branchList.size(); i++) {
			BranchDTO branch = branchList.get(i);
			html.append("        <a href=\"javascript:\" onclick=\"branchCtrl.selectBrch(this, '" + branch.getBranch_id() + "')\" data-brchCd=\"")
			.append(branch.getBranch_id() + "\" class=\"btn swiper-slide useBrch")
			.append(i == 0 && branch.getBranch_tp_id() == i ? " on" : "") 
			.append("\"><span>" + branch.getBranch_nm() + "</span></a>\r\n");
		}

		html.append("    </div>\r\n");
		html.append("</div>\r\n");
		html.append("<button type=\"button\" class=\"drop_btn\"></button>");

		return html.toString();
	}


	//지점 정보 가져오기

	@Override public List<BranchDTO> getBranchInfo(int brchCd) {
		log.info("> BranchInfoServiceImpl.selectBranchList ..."); 
		System.out.println(brchCd);
		return this.branchInfoMapper.getBranchInfo(brchCd); }


	//지점 정보 html	
	@Override public String createBranchInfoHtml(BranchDTO branchDTO) {
		log.info("> BranchInfoServiceImpl.createBranchInfoHtml ...");

		StringBuilder html = new StringBuilder(); 
		List<BranchDTO> branchList = getBranchInfo(branchDTO.getBrchCd()); 
		
		//int brchCd = branchDTO.getBrchCd();
		
		List<String> imgList = new ArrayList<String>();
		imgList.add(branchDTO.getBranch_img());
		
		Map<Integer, List<String>> branchImgMap = new HashMap<Integer, List<String>>();
		
		for (String img : imgList) {
			
		}
		
		
		
		
		
		
		
		
		
		
		if (branchList.isEmpty()) {

		}else {

			for (BranchDTO dto : branchList) {
				
				//지점 지도
				html.append("<script type=\"text/javascript\">\r\n");
				html.append("				/*===========================================================================*/\r\n");
				html.append("				//점별 지도 생성 \r\n");
				html.append("				/*===========================================================================*/\r\n");
				html.append("			\r\n");
				html.append("					kakao.maps.load(function()  {\r\n");
				html.append("			\r\n");
				html.append("					var lat = \"" + dto.getLat() +"\"; //위도\r\n");
				html.append("					var lng = \""+ dto.getLng() +"\"; //경도\r\n");
				html.append("			\r\n");
				html.append("					var mapContainer = document.getElementById('map'); // 지도를 표시할 div \r\n");
				html.append("					var mapOption = { // 지도 옵션 \r\n");
				html.append("						center : new kakao.maps.LatLng(lat,lng), // 지도의 중심좌표\r\n");
				html.append("						level : 3\r\n");
				html.append("					// 지도의 확대 레벨\r\n");
				html.append("					};\r\n");
				html.append("			\r\n");
				html.append("					var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다\r\n");
				html.append("			\r\n");
				html.append("					var iwContent = '<div style=\"width:135px;text-align:center;margin:5px 5px 5px 5px;font:12px/1.5 sans-serif\"><strong>롯데문화센터"
												+ dto.getBranch_nm() + " </strong></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다\r\n");
				html.append("					iwPosition = new kakao.maps.LatLng(lat,lng), //인포윈도우 표시 위치입니다\r\n");
				html.append("					iwRemoveable = false; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다\r\n");
				html.append("			\r\n");
				html.append("					// 인포윈도우를 생성하고 지도에 표시합니다\r\n");
				html.append("					var infowindow = new kakao.maps.InfoWindow({\r\n");
				html.append("						map : map, // 인포윈도우가 표시될 지도\r\n");
				html.append("						position : iwPosition,\r\n");
				html.append("						content : iwContent,\r\n");
				html.append("						removable : iwRemoveable\r\n");
				html.append("					});\r\n");
				html.append("				})\r\n");
				html.append("		\r\n");
				html.append("			</script>			\r\n");
				
				

				// 폴더 내의 이미지
				for (String imageName : imgList) {
					// 이미지 파일의 URL 생성
					//String imageUrl = String.format("http://localhost/display.do?filename=%s&brand=100", dto.getBranch_img());		
					
					html.append("				<div class=\"con on\">\r\n");
					html.append("					<div class=\"sub_con_section\">\r\n");
					html.append("						<div class=\"store_info_area\">\r\n");
					html.append("							<div class=\"store_info_swiper\">\r\n");
					html.append("								<div class=\"swiper-container\">\r\n");
					html.append("										<div class=\"swiper-wrapper\">");
					html.append("<a href=\"javascript:\" class=\"swiper-slide\">");
					html.append("<p class=\"img img_resize_w\">");
					html.append("<img src=\"/upload/branch/1/1/" + dto.getBranch_img() + "\" alt=\""+dto.getBranch_img()+"\">");
					html.append("</p>");
					html.append("</a>");
					html.append("</div>\r\n");
					//html.append("			<!-- 							<div class=\"swiper-button-next\"></div> -->\r\n");
					//html.append("			<!-- 							<div class=\"swiper-button-prev\"></div> -->\r\n");
					html.append("										<div class=\"swiper-pagination\"></div>\r\n");
					html.append("								</div>\r\n");
					html.append("							</div>\r\n");
					
				}//for
				
				html.append("							<div class=\"store_txt_wrap\">\r\n");
				html.append("								<p class=\"txt f_body2\">\r\n"
							+ dto.getBranch_addr() + "<br class=\"only_pc\"></p>\r\n");
				html.append("								<p class=\"txt f_body2\">"+ dto.getTel()+ "</p>\r\n");
				html.append("								<p class=\"txt f_body2\">"+ dto.getClassroom_nm() +"</p>\r\n");
				html.append("							</div>\r\n");
				html.append("						</div>\r\n");
				html.append("					</div>\r\n");
				html.append("					<!-- 2023-02-13 지점안내 내 기획전 영역 삭제 -->\r\n");
				html.append("					<!---->\r\n");
				html.append("					<!-- 2023-02-13 지점안내 내 기획전 영역 삭제 -->\r\n");
				html.append("					<div class=\"sub_con_section\">\r\n");
				html.append("						<p class=\"sub_tit_area f_h2\">"+ dto.getBranch_nm() + " 오시는 길</p>\r\n");
				html.append("						<p class=\"map_area\">\r\n");
				html.append("							<div id=\"map\" style=\"width: 100%; height: 470px;\"/>\r\n");
				html.append("						</p>\r\n");
				html.append("					</div>\r\n");
				html.append("				</div>");
				
			}//for
				
		}//if
	return html.toString(); 
	
	}
	
}
