<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="cont_wrap" data-page-type="list">
	<form id="frm_search" name="frm_search">
		<input type="hidden" id="pageIndex" name="pageIndex" value="1"/>
		<input type="hidden" id="type" name="type" value="${ empty frmSearchDTO.type ? 'cmplt' : frmSearchDTO.type }" />
		<input type="hidden" id="lectSmsterCd" name="lectSmsterCd" value="${ frmSearchDTO.lectSmsterCd }" />
		<input type="hidden" id="yy" name="yy" value="${frmSearchDTO.yy}" />
		<input type="hidden" id="q" name="q" value="${frmSearchDTO.q}" />
		<input type="hidden" id="atlctRsvNo" name="atlctRsvNo" value="0" />
		<input type="hidden" id="initIndex" name="initIndex" value="1"/>
		<input type="hidden" id="listCnt" name="listCnt" value="2"/>		
		
		<input type="hidden" id="prevSesterYy" name="prevSesterYy" value="${frmSearchDTO.prevSesterYy}"/>
		<input type="hidden" id="prevSesterLectSmsterCd" name="prevSesterLectSmsterCd" value="${frmSearchDTO.prevSesterLectSmsterCd}"/>	
	</form>

	<div class="cont_inner no_pb">
		<div class="page_title_area">
			<div class="inner">
				<div class="top_area">
					<a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a> <a
						href="javascript:" class="tit_div arrow" title="페이지 이동 팝업 열기">
						<p class="tit f_h1">
							수강내역 조회 <span class="more_tit"></span>
						</p>
					</a>
					<div class="tit_popup">
						<div class="pop_wrap">
							<div class="pop_cont">
								<div class="for_padding">
									<div class="scroll_area">
										<div class="branch">
    	<a class="" href="/mypage/member/info.do"><p class="f_desc">회원정보변경</p></a>
       	<a class="active" href="/mypage/atlct/list.do"><p class="f_desc">수강내역 조회</p></a>
       	<a class="" href="/mypage/waiting/list.do"><p class="f_desc">대기자 조회</p></a>
       	<a class="" href="/mypage/freebie/appList.do"><p class="f_desc">사은품 신청</p></a>
       	<a class="" href="/mypage/freebie/detailList.do"><p class="f_desc">사은품 신청내역</p></a>
       	<a class="" href="/mypage/coupon/list.do"><p class="f_desc">나의 쿠폰</p></a>
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
			<div class="m_pop_dimd"></div>
		</div>

		<div class="page_cont_area">
			<!-- dual_list클래스가 있으면 cour_his_list가 2개로 분할됩니다 -->
			<div class="inner pay_info dual_list">
				<div class="course_view">
					<div class="dot_txt_box">
						<p class="dot_txt">데스크에서 접수한 강좌의 경우 방문 시에만 취소 가능합니다. (결제한 카드 및 영수증 지참 필수)</p>
	                    <p class="dot_txt">카드결제 취소 처리의 경우 환불 접수 후 실제 취소 승인은 카드사 사정에 따라 처리 기간이 상이할 수 있습니다.</p>
	                    <p class="dot_txt">재료 준비가 필요한 일부 강좌(요리, 공예, 플라워 등)는 강좌 시작일의 3일 전까지 취소 가능합니다.</p>
	                    <p class="dot_txt">강의시작일 이후 취소 시에는 환불규정에 의거하여 환불 차감액을 제외한 나머지 금액이 부분환불 됩니다.</p>
	                    <p class="dot_txt">결제/취소관련 문의는 1:1문의 이용 또는 유선으로 가능합니다.</p>
					</div>
					<div class="flex_btn_wrap beige_small">
						<a class="border_btn" href="/mypage/lectureCertf/list.do"> <span>수강증 보기</span></a>
						<a class="border_btn" href="/mypage/myreview/list.do"> <span>수강후기 보기</span></a>
					</div>
					<div class="tab_func_area">
						<div class="filter_bar_area no_padding">
		                    <div class="border_tab_area">
		                      <div class="swiper-container">
		                        <div class="swiper-wrapper">
		                          <a href="/mypage/atlct/list.do?type=cmplt" class="swiper-slide ${ frmSearchDTO.type eq 'rfnd' ? '' : 'on' }"><span>수강내역 조회</span></a>
		                          <a href="/mypage/atlct/list.do?type=rfnd" class="swiper-slide ${ frmSearchDTO.type eq 'rfnd' ? 'on' : '' }"><span>취소내역 조회</span></a>
		                        </div>
		                      </div>
		                    </div>
		                </div>
						<div class="tab_con_area">
							<div class="form_search_w">
								<div class="form_search">
									<div class="form_search_div">
										<input type="text" placeholder="주문번호/강좌명으로 검색하세요" title="주문번호/강좌명 입력" name="q" onkeydown="mypage_atlct.keydownAtlctRsvNo(this, event, 'rfnd')" value="${frmSearchDTO.q}">
										<div class="input_btn_wrap">
											<button type="button" class="btn_delete" title="지우기"></button>
											<button type="button" class="btn_search" title="검색" onclick="mypage_atlct.list('rfnd', 1);"></button>
										</div>
									</div>
								</div>
							</div>
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
												<div class="btn_area link_select">
													<div class="btn_wrap">
														<div class="filter_open_area">
															<a href="javascript:" class="btn dropdown_btn filter_popup_btn" title="컨텐츠정렬 팝업 열기">
																<div class="order_txt">전체 연도</div>
															</a>
														</div>
														<div class="dimd"></div>
														<div class="filter_list_wrap">
															<div class="tit_area">
																<p class="tit">연도</p>
																<a href="javascript:" role="button" class="close"></a>
															</div>
															<div class="scroll_wrap">
																<a class="txt f_caption2 on" href="javascript:" onclick="mypage_atlct.changeYy(this, 'rfnd', '')">전체 연도</a>
																<a class="txt f_caption2 " href="javascript:" onclick="mypage_atlct.changeYy(this, 'rfnd', 2023)">2023년</a>
																<a class="txt f_caption2 " href="javascript:" onclick="mypage_atlct.changeYy(this, 'rfnd', 2022)">2022년</a>
																<a class="txt f_caption2 " href="javascript:" onclick="mypage_atlct.changeYy(this, 'rfnd', 2021)">2021년</a>
																<a class="txt f_caption2 " href="javascript:" onclick="mypage_atlct.changeYy(this, 'rfnd', 2020)">2020년</a>
																<a class="txt f_caption2 " href="javascript:" onclick="mypage_atlct.changeYy(this, 'rfnd', 2019)">2019년</a>
																<a class="txt f_caption2 " href="javascript:" onclick="mypage_atlct.changeYy(this, 'rfnd', 2018)">2018년</a>
																<a class="txt f_caption2 " href="javascript:" onclick="mypage_atlct.changeYy(this, 'rfnd', 2017)">2017년</a>
																</div>
														</div>
													</div>
													<div class="btn_wrap">
														<div class="filter_open_area">
															<a href="javascript:" class="btn dropdown_btn filter_popup_btn" title="컨텐츠정렬 팝업 열기">
																<div class="order_txt">전체 학기</div>
															</a>
														</div>
														<div class="dimd" style="display:none;"></div>
														<div class="filter_list_wrap" style="display:none;">
															<div class="tit_area">
																<p class="tit">전체 학기</p>
																<a href="javascript:" role="button" class="close"></a>
															</div>
															<!-- 2022-12-01 구조 수정 -->
															<div class="scroll_wrap">
																<a class="txt f_caption2 on" href="javascript:" onclick="mypage_atlct.changeSmster(this, 'rfnd', '')">전체 학기</a>
																<a class="txt f_caption2 " href="javascript:" onclick="mypage_atlct.changeSmster(this, 'rfnd', '1')">봄학기</a>
																<a class="txt f_caption2 " href="javascript:" onclick="mypage_atlct.changeSmster(this, 'rfnd', '2')">여름학기</a>
																<a class="txt f_caption2 " href="javascript:" onclick="mypage_atlct.changeSmster(this, 'rfnd', '3')">가을학기</a>
																<a class="txt f_caption2 " href="javascript:" onclick="mypage_atlct.changeSmster(this, 'rfnd', '4')">겨울학기</a>
																</div>
															<!-- // 2022-12-01 구조 수정 -->
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="course_history_w" id="listContainer">
										
							</div>
							<div class="more_btn_w" id="moreBtn" style="display:none;">
								<a href="javascript:" class="more_btn no_motion"> <span>더보기
										<strong class="total"></strong>
								</span>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="/resources/common/js/mypage/mypage_atlct.js"></script>