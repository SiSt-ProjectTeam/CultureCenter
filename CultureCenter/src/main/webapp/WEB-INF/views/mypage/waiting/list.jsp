<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="ko">

<body class="main">
			
		<form id="frm_submit" name="frm_submit">
			<input type="hidden" name="csrfPreventionSalt"
				value="5J8tc1q8uIX3wqnjNd1M" /> <input type="hidden" name="jsonStr"
				value="" /> <input type="hidden" name="atlctRsvNo" value="" /> <input
				type="hidden" name="atlctType" value="waiting" />
		</form>

		<form id="frm_cancel" name="frm_cancel">
			<input type="hidden" name="atlctRsvNo" value="" /> <input
				type="hidden" name="lectNm" value="" /> <input type="hidden"
				name="optnNm" value="" /> <input type="hidden" name="lectStDtm"
				value="" /> <input type="hidden" name="lectSt" value="" />
		</form>

		<div class="cont_wrap">
			<form id="frm_search" name="frm_search">
				<input type="hidden" id="pageIndex" name="pageIndex" value="1" />  <input
					type="hidden" id="brchNm" name="brchNm" value="" /> <input
					type="hidden" id="initIndex" name="initIndex" value="1" /> <input
					type="hidden" id="listCnt" name="listCnt" value="2" />
			</form>

			<div class="cont_inner no_pb">
				<div class="page_cont_area">
					<div class="inner pay_info dual_list">
						<div class="wating_w">
							<div class="filter_bar_area no_padding">
								<div class="fixed_mobile_w">
									<div class="fixed_mobile">
										<div class="filter_bar_div">
											<div class="left">
												<p class="f_caption2">
													전체 <span class="point" id="totCnt">0개</span>
												</p>
											</div>
											<div class="right">
												<div class="btn_area">
													<div class="btn_wrap">
														<div class="filter_open_area">
															<a href="javascript:"
																class="btn dropdown_btn filter_popup_btn"
																title="컨텐츠정렬 팝업 열기"><span class="order_txt">전체지점</span></a>
														</div>
														<div class="dimd"></div>
														<div class="filter_list_wrap">
															<div class="tit_area">
																<p class="tit">지점</p>
																<a href="javascript:" role="button" class="close"></a>
															</div>
															<div class="scroll_wrap">
																<a class="txt f_caption2 on"
																	href="javascript:mypage_waiting.changebrchNm('')">전체지점</a>
																<p class="sub_tit only_mobile">서울점</p>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('잠실점')">잠실점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('본점')">본점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('강남점')">강남점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('건대스타시티점')">건대스타시티점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('관악점')">관악점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('김포공항점')">김포공항점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('노원점')">노원점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('미아점')">미아점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('영등포점')">영등포점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('청량리점')">청량리점</a>
																<p class="sub_tit only_mobile">수도권점</p>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('인천점')">인천점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('동탄점')">동탄점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('구리점')">구리점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('분당점')">분당점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('수원점')">수원점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('안산점')">안산점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('일산점')">일산점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('중동점')">중동점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('평촌점')">평촌점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('롯데몰광명점')">롯데몰광명점</a>
																<p class="sub_tit only_mobile">지방점</p>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('부산본점')">부산본점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('광복점')">광복점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('광주점')">광주점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('대구점')">대구점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('대전점')">대전점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('동래점')">동래점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('마산점')">마산점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('상인점')">상인점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('센텀시티점')">센텀시티점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('울산점')">울산점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('전주점')">전주점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('창원점')">창원점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('포항점')">포항점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebrchNm('롯데몰군산점')">롯데몰군산점</a>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="course_history_w" id="listContainer"></div>
						</div>
						<div class="more_btn_w" id="moreBtn" style="display:none;">
							<a href="javascript:" class="more_btn no_motion"> 
								<span>더보기<strong class="total"></strong></span>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>

		<script type="text/javascript" src="/resources/common/js/mypage/mypage_waiting.js"></script>
