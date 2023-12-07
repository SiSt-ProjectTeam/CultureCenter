<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ko">

<!-- head -->
<tiles:insertAttribute name="head" />

<body class="">
	<div id="wrap" data-is-app="" data-is-mobile="" data-is-login="Y">
	
		<!-- header -->
		<tiles:insertAttribute name="header" />
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d427a7c1718f571750db2822740d0465&autoload=false"></script>
		<div class="cont_wrap">
			<div class="cont_inner no_pb">
				<form id="frmSearch" name="frmSearch">
					<input type="hidden" id="brchCd" name="brchCd" value="" /> 
					<input type="hidden" id="brchAreaCd" name="brchAreaCd" value="1" />
				</form>
				<div class="page_title_area">
					<div class="inner">
						<div class="top_area">
							<a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a> 
							<a href="javascript:" class="tit_div arrow" title="페이지 이동 팝업 열기">
								<p class="tit f_h1">지점안내<span class="more_tit"></span></p>
							</a>
							<div class="tit_popup">
								<div class="pop_wrap">
									<div class="pop_cont">
										<div class="for_padding">
											<div class="scroll_area">
												<div class="branch">
													<a class="active" href="/information/branch/list.do"><p class="f_desc">지점안내</p></a> 
													<a class="" href="/information/application/index.do"><p class="f_desc">강사지원·제휴신청</p></a> 
													<a class="" href="/information/faq/list.do"><p class="f_desc">자주하는 문의</p></a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="m_pop_dimd"></div>
				</div>
				<div class="page_cont_area no_pt">
					<div class="inner">
						<div class="tab_func_area">
							<div class="filter_bar_area">
								<div class="fixed_mobile_w">
									<div class="fixed_mobile">
										<div class="border_tab_area tab_btn_area">
											<div class="swiper-container">
												<div class="swiper-wrapper">
													<a href="javascript:branchCtrl.tabClCd(1);"
														class="btn swiper-slide on" data-cd="1"><span>서울점</span></a>
													<a href="javascript:branchCtrl.tabClCd(2);"
														class="btn swiper-slide " data-cd="2"><span>수도권점</span></a>
													<a href="javascript:branchCtrl.tabClCd(3);"
														class="btn swiper-slide " data-cd="3"><span>지방점</span></a>
												</div>
											</div>
										</div>
										<div class="thr_dep_area wide" id="listArea">
											<!-- 내용 영역 -->
										</div>
									</div>
								</div>
							</div>
							<!-- ajax -->
							<div class="tab_con_area" id="dtlArea"></div>
						</div>
					</div>
				</div>
				<!-- 	</form>	 -->
			</div>
		</div>

		<script type="text/javascript" src="/resources/common/js/branch/branchCtrl.js"></script>
		<!-- footer -->
	</div>
	<!--  end wrap -->

	<script type="text/javascript" src="/resources/common/netfunnel/netfunnel.js" charset="UTF-8"></script>

	<script>
		if (true) {
			common.logOutTimer.start();
		}
		commonScript.headerFooterFn();
		commonScript.formChkFn();
	</script>

</body>
</html>