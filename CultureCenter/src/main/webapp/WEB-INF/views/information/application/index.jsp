<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body class="">
	<div id="wrap" data-is-app="" data-is-mobile="" data-is-login="Y">
		<div class="cont_wrap">
			<div class="cont_inner no_pb">
				<div class="page_title_area">
					<div class="inner">
						<div class="top_area">
							<a href="javascript:fnc.back();" class="page_prev_btn"
								title="뒤로가기"></a> <a href="javascript:" class="tit_div arrow"
								title="페이지 이동 팝업 열기">
								<p class="tit f_h1">
									강사지원·제휴신청<span class="more_tit"></span>
								</p>
							</a>
							<div class="tit_popup">
								<div class="pop_wrap">
									<div class="pop_cont">
										<div class="for_padding">
											<div class="scroll_area">
												<div class="branch">
													<a class="" href="/information/branch/list.do"><p
															class="f_desc">지점안내</p></a> <a class="active"
														href="/information/application/index.do"><p
															class="f_desc">강사지원·제휴신청</p></a> <a class=""
														href="/information/faq/list.do"><p class="f_desc">자주하는
															문의</p></a>
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

				<div class="page_cont_area no_pb">
					<div class="divide_area">
						<div class="inner">
							<div class="left box active">
								<a href="javascript:"
									onclick="requestIndexCtrl.getLayer('teacher');">
									<div class="img_resize_w img">
										<img src="/resources/common/images/@img-userinfo-divide-left.jpg" alt="">
									</div>
									<div class="inner_box">
										<p class="txt">
											롯데문화센터 <br class="only_pc">강사지원
										</p>
										<p class="desc_txt">분야별 수준 높은 교육문화 컨텐츠와 열정적인 전문 강사님을 모십니다.</p>
										<!-- 2023-01-16 텍스트 수정 -->
										<p class="arrow_btn">
											<span>지원하기</span>
										</p>
									</div>
								</a>
							</div>
							<div class="right box">
								<a href="javascript:"
									onclick="requestIndexCtrl.getLayer('cooper');">
									<div class="img_resize_w img">
										<img src="/resources/common/images/@img-userinfo-divide-right.jpg"
											alt="">
									</div>
									<div class="inner_box">
										<p class="txt">
											롯데문화센터 <br class="only_pc">제휴신청
										</p>
										<p class="desc_txt">
											콘텐츠·서비스·비즈니스· 마케팅·광고 등 <br>Lifestyle LAB과 함께할 파트너사의 <br>소중한
											제안을 기다리고 있습니다.
										</p>
										<p class="arrow_btn">
											<span>신청하기</span>
										</p>
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id="application_popup" class="layer_popup" style="display: none">
			<div class="pop_wrap w800 full">
				<div class="pop_head">
					<p id="popupTitle" class="title">롯데문화센터 강사 지원하기</p>
				</div>
				<div id="popupContent" class="pop_cont"></div>
				<a class="btn_close" href="javascript:" title="닫기"> <span
					class="blind"></span>
				</a>
			</div>
		</div>

		<script type="text/javascript"
			src="/resources/common/js/teacher/teacherCommon.js"></script>
		<script type="text/javascript"
			src="/resources/common/js/information/teacherApp/requestIndex.js"></script>

		<script type="text/javascript"
			src="/resources/common/js/information/teacherApp/teacherRequest.js"></script>
		<script
			src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery-serialize-object/2.5.0/jquery.serialize-object.min.js"></script>

		<script type="text/javascript"
			src="/resources/common/js/information/teacherApp/cooperationRequest.js"></script>

		<form id="frmSso" method="post">
			<input type="hidden" name="ssoTkn" id="ssoTkn" value="" /> <input
				type="hidden" name="acesTkn" id="acesTkn" value="" /> <input
				type="hidden" name="rnwTkn" id="rnwTkn" value="" /> <input
				type="hidden" name="onlCstTpC" id="onlCstTpC" value="" /> <input
				type="hidden" name="frnYn" id="frnYn" value="" /> <input
				type="hidden" name="rspClac" id="rspClac" value="" /> <input
				type="hidden" name="rspC" id="rspC" value="" /> <input
				type="hidden" name="rspDtc" id="rspDtc" value="" /> <input
				type="hidden" name="rspMsgCn" id="rspMsgCn" value="" /> <input
				type="hidden" name="rtnUrl" id="rtnUrl" value="" />
		</form>

		<script type="text/javascript" src="/resources/common/js/member/sso.js"></script>
	</div>
	<!--  end wrap -->

	<script type="text/javascript" src="/resources/common/netfunnel/netfunnel.js"
		charset="UTF-8"></script>

	<script>
		if(true)
		{
			common.logOutTimer.start();
		}
		commonScript.headerFooterFn();
		commonScript.formChkFn();
	</script>

</body>