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
	public List<BranchDTO> getBranchList(int branch_tp_id) {	
		log.info("> BranchInfoServiceImpl.selectBranchList ...");		
		return this.branchInfoMapper.getBranchList( branch_tp_id);
	}

	//지점 목록 html
	@Override
	public String createBranchHtml(int branch_tp_id) {
		log.info("> BranchInfoServiceImpl.createBranchHtml......");

		StringBuilder html = new StringBuilder();
		List<BranchDTO> branchList = getBranchList( branch_tp_id);

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
	@Override
	public List<BranchDTO> getBranchInfo(int branch_id) {
		log.info("> BranchInfoServiceImpl.selectBranchList ...");
		return this.branchInfoMapper.getBranchInfo(branch_id);
	}

	//지점 정보 html
	@Override
	public String createBranchInfoHtml(int branch_id) {
		log.info("> BranchInfoServiceImpl.createBranchInfoHtml ...");

		StringBuilder html = new StringBuilder();
		List<BranchDTO> branchList = getBranchInfo(branch_id);
		BranchDTO branch = new BranchDTO();

		String branchId = String.valueOf(branch.getBranch_id());

		//마지막에 반환할 map 지점정보(K) - 이미지5장(V)
		Map<List<String>, List<String>> branchMap = new HashMap<>();

		//이미지 저장
		List<String> imgList = new ArrayList<>();

		int i = 1;

		while (!branchList.isEmpty()) {

			//지점 정보 담을 곳
			List<Object> branchInfo = new ArrayList<>();
			branchInfo.addAll(branchList);

			// 지점 이미지 추가
			imgList.add(branch.getBranch_img());

			//5장 담을 Map
			Map<String, String> imgMap = null;


			// 5개 단위로 이미지를 그룹화 //5개가 아닐 때
			if (imgList.size() % 5 == 1) {

				imgMap= new HashMap<>();
				branchInfo = new ArrayList<>();
				branchInfo.add(branchId);
				imgList = new ArrayList<String>();
				imgList.add(branch.getBranch_img());

				// 사진이 5개일 때
			}else if (imgList.size() % 5 != 1) {


			}//if


			i++;
		}//while

		return html.toString();
	}

}













