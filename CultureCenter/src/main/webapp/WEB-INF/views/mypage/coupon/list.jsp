<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="cont_wrap">
	<div class="cont_inner no_pb">
		<!-- 2022-11-23 구조 수정 -->
		<div class="page_title_area">
			<div class="inner">
				<div class="top_area">
					<a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a>
					<a href="javascript:" class="tit_div arrow" title="페이지 이동 팝업 열기">
						<p class="tit f_h1">나의 쿠폰 <span class="more_tit"></span></p>
					</a>
					<div class="tit_popup">
						<div class="pop_wrap">
							<div class="pop_cont">
								<div class="for_padding">
									<div class="scroll_area">
										<div class="branch">
    	<a class="" href="/mypage/member/info.do"><p class="f_desc">회원정보변경</p></a>
       	<a class="" href="/mypage/atlct/list.do"><p class="f_desc">수강내역 조회</p></a>
       	<a class="" href="/mypage/waiting/list.do"><p class="f_desc">대기자 조회</p></a>
       	<a class="" href="/mypage/freebie/appList.do"><p class="f_desc">사은품 신청</p></a>
       	<a class="" href="/mypage/freebie/detailList.do"><p class="f_desc">사은품 신청내역</p></a>
       	<a class="active" href="/mypage/coupon/list.do"><p class="f_desc">나의 쿠폰</p></a>
       	<a class="" href="/mypage/myreview/list.do"><p class="f_desc">나의 수강후기</p></a>
       	<a class="" href="/mypage/teachereval/list.do"><p class="f_desc">만족도 평가</p></a>
      	<a class="" href="/mypage/lectureCertf/list.do"><p class="f_desc">수강증</p></a>
       	<a class="" href="/mypage/inquiry/list.do"><p class="f_desc">1:1 문의</p></a>
		</div></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="flex_input_wrap">
				<div class="form_input">
					<input type="text" id="rndmCpnNo" maxlength="20" placeholder="쿠폰 번호를 입력해주세요" oninput="$(this).val($(this).val().replace(/[^a-zA-Z0-9]/g, ''))"/>
					<div class="input_btn_wrap">
						<button type="button" class="btn_delete" title="쿠폰번호 지우기"></button>
					</div>
				</div>
				<a class="s_color_btn" href="javascript:mypageCoupon.saveRandom()"><span>등록</span>
				</a>
			</div>
			<div class="m_pop_dimd"></div>
		</div>
		<!-- // 2022-11-23 구조 수정 -->
		<div class="page_cont_area">
			<div class="inner">
				<div class="filter_bar_area no_padding">
					<div class="fixed_mobile_w">
						<div class="fixed_mobile">
							<div class="filter_bar_div">
								<div class="left">
									<p class="f_caption2">
										나의 쿠폰 <span class="point" id="totCnt">0</span>개
									</p>
								</div>
								<div class="right">
									<div class="btn_area">
										<div class="btn_wrap">
											<div class="filter_open_area">
												<a href="javascript:"
													class="btn dropdown_btn filter_popup_btn"
													title="사용가능 팝업 열기"><div class="order_txt">사용가능</div></a>
											</div>
											<div class="dimd"></div>
											<div class="filter_list_wrap">
												<div class="tit_area">
													<p class="tit">전체</p>
													<a href="javascript:" role="button" class="close"></a>
												</div>
												<div class="scroll_wrap">
													<a class="txt f_caption2 useStatCd on" href="javascript:" onclick="mypageCoupon.changeCd(this)" data-use-stat-cd="00">사용가능</a> 
													<a class="txt f_caption2 useStatCd" href="javascript:" onclick="mypageCoupon.changeCd(this)" data-use-stat-cd="10">사용완료</a> 
													<a class="txt f_caption2 useStatCd" href="javascript:" onclick="mypageCoupon.changeCd(this)" data-use-stat-cd="20">기간만료</a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="couponList">
					<div class="no_srch_area no_pb">
						<div class="no_srch_div">
							<p class="txt f_h2">
								<span class="normal_value">검색결과가 없습니다.</span>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="layer_popup" id="cpnExplPop" style="display:none">
	<div class="pop_wrap">
		<div class="pop_head">
			<p class="title">쿠폰 설명</p>
		</div>
		<div class="pop_cont">
			<div class="for_padding">
				<div class="scroll_area">
					<div class="txt_con">
						<div class="info_dot_txt" id="detailCont">
						</div>
					</div>
				</div>
			</div>
		</div>
		<a class="btn_close" href="javascript:" title="닫기">
			<span class="blind"></span>
		</a>
	</div>
</div>

<div class="layer_popup" id="cpnBrchPop" style="display:none">
	<div class="pop_wrap">
		<div class="pop_head">
			<p class="title">사용 가능 지점</p>
		</div>
		<div class="pop_cont">
			<div class="for_padding">
				<div class="scroll_area">
					<div class="txt_con">
						<p class="txt" id="detailCont"></p>
						<p class="f_caption2">* 해당 쿠폰은 위 안내된 지점들에 한해서만 사용이 가능합니다.</p>
					</div>
				</div>
			</div>
		</div>
		<a class="btn_close" href="javascript:" title="닫기">
			<span class="blind"></span>
		</a>
	</div>
</div>

<div class="layer_popup" id="cpnApplPop" style="display:none">
	<div class="pop_wrap">
		<div class="pop_head">
			<p class="title">사용 가능 강좌</p>
		</div>
		<div class="pop_cont">
			<div class="for_padding">
				<div class="scroll_area">
					<div class="txt_con">
						<div class="info_dot_txt" id="detailCont">
						</div>
						<p class="f_caption2">* 해당 쿠폰은 위 안내된 강좌에 한해서만 사용이 가능합니다.</p>
					</div>
				</div>
			</div>
		</div>
		<a class="btn_close" href="javascript:" title="닫기">
			<span class="blind"></span>
		</a>
	</div>
</div>

<script type="text/javascript" src="/resources/common/js/mypage/mypageCoupon.js"></script>