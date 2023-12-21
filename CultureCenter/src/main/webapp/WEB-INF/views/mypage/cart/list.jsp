<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
header에 data-url 부분 다름 아래로 바꿔줘야함 
<div class="m_navi_bar " data-url="/mypage/cart/list.do"> 
-->
<div class="cont_wrap shopbag_w">


	<form id="frm_search" name="frm_search">
		<input type="hidden" id="pageIndex" name="pageIndex" value="${params.pageIndex}" />
		<input type="hidden" id="brchCd" name="brchCd" value="${params.brchCd}" />
		<input type="hidden" id="brchNm" name="brchNm" value="${params.brchNm}" />
	</form>
 
	<div class="cont_inner no_pb">
		<!-- 상단부 알림창 -->
		<div class="page_title_area">
			<div class="inner">
				<div class="top_area">
					<a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a>
					<div class="tit_div">
						<p class="tit f_h1">장바구니</p>                        <!--  ↓ 이거가 세부강좌번호 -->
						<a href="javascript:" onclick="fnc.cartBtn('1', '2023', '4', '37', '3')">37트니트니(유아) 장바구니추가</a><br>
						<a href="javascript:" onclick="fnc.cartBtn('1', '2023', '4', '3', '3')">3펜화..(성인) 장바구니추가</a>
					</div>
				</div>
			</div>
			<div class="txt_wrap">
				<p class="txt f_desc">결제는 지점별로만 가능하며, 장바구니에 담긴 강좌는 최대 30일까지
					보관됩니다.</p>
			</div>
			<div class="m_pop_dimd"></div>
		</div>

		<div class="page_cont_area">
			<div class="inner pay_info">
				<div class="shopbag_w">

					<!-- filter_bar 목록개수, 지점search -->
					<div class="filter_bar_area no_padding">
						<div class="fixed_mobile_w">
							<div class="fixed_mobile">
								<div class="filter_bar_div">
									<div class="left">
										<p class="f_caption2">
											목록 <span class="point"></span>
										</p>
									</div>
									<!-- 지점 -->
									<div class="right">
										<div class="btn_area">
											<div class="btn_wrap">
												<div class="filter_open_area">
													<a href="javascript:"
														class="btn dropdown_btn filter_popup_btn"
														title="컨텐츠정렬 팝업 열기"> <span class="order_txt">전체지점</span>
													</a>
												</div>
												<div class="dimd"></div>
												<div class="filter_list_wrap">
													<div class="tit_area">
														<p class="tit">지점</p>
														<a href="javascript:" role="button" class="close"></a>
													</div>
													<div class="scroll_wrap">
														<a class="txt f_caption2 on" href="javascript:mypage_cart.changeBrchCd('0','')">전체지점</a>
														<p class="sub_tit only_mobile">서울점</p>
														<c:forEach items="${brchList}" var="brchDto">
															    <c:if test="${brchDto.branch_tp_id eq 1}">
																	<a class="txt f_caption2"  href="javascript:mypage_cart.changeBrchCd('${brchDto.branch_id}', '${brchDto.branch_nm}')">${brchDto.branch_nm}</a>
																</c:if>
														</c:forEach>
														<p class="sub_tit only_mobile">수도권점</p>
														<c:forEach items="${brchList}" var="brchDto">
															    <c:if test="${brchDto.branch_tp_id eq 2}">
																	<a class="txt f_caption2"  href="javascript:mypage_cart.changeBrchCd('${brchDto.branch_id}', '${brchDto.branch_nm}')">${brchDto.branch_nm}</a>
																</c:if>
														</c:forEach>
														<p class="sub_tit only_mobile">지방점</p>
														<c:forEach items="${brchList}" var="brchDto">
															    <c:if test="${brchDto.branch_tp_id eq 3}">
																	<a class="txt f_caption2"  href="javascript:mypage_cart.changeBrchCd('${brchDto.branch_id}', '${brchDto.branch_nm}')">${brchDto.branch_nm}</a>
																</c:if>
														</c:forEach>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- Utils_box 전체선택, 선택삭제 -->
					<div class="utils_box">
						<div class="form_checkbox">
							<input type="checkbox" id="allSelect" name=""
								onclick="mypage_cart.clickAllCheckbox(this)" autocomplete="off">
							<label for="allSelect">전체선택</label>
						</div>
						<a href="javascript:mypage_cart.removeCartCheck()"
							class="f_btn delete_btn" role="button"> <span>선택삭제</span>
						</a>
					</div>

					<!-- 장바구니 목록 -->
					<div class="course_history_w" data-mbr-grde-cd="" data-mvg-blstr-cd=""></div>

					<!-- 장바구니 비우기 -->
					<div class="remove_btn_w">
						<a href="javascript:mypage_cart.removeCartList()"
							class="remove_bag f_btn" role="button"><span>장바구니 비우기</span>
						</a>
					</div>

					<!-- 하단부 알림창 -->
					<div class="dot_txt_box">
						<p class="f_body1">
							<span class="caution"></span>알려드립니다!
						</p>
						<p class="dot_txt">수강자가 본인이 아니거나 가족 수강자일 경우, 결제 페이지에서 수강자 변경 및
							추가하실 수 있습니다.</p>
						<!-- 2022-12-09 텍스트 수정 -->
						<p class="dot_txt">강좌 개강 후 ‘현장 상담’을 통해 수강등록 상담을 받으실 수 있습니다.</p>
						<p class="dot_txt">신청하신 강좌는 최소 정원에 미달되거나 사정에 의해 폐강 될 수 있으니 양해
							바랍니다.</p>
					</div>

					<!-- 강좌 더보기 이동 -->
					<div class="flex_btn_wrap margin_large">
						<a class="border_btn"
							href="/application/search/list.do?type=branch&brchCd=0002"> <span>강좌
								더보기</span>
						</a>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>

<!--결제창 -->
<div class="payment_bar shopbag">
	<div class="payment_w">
		<div class="inner">
			<div class="payment_con">
				<div class="txt_box">
					<div class="txt f_body4">
						<p>
							<span class="num_case">0건</span> <span>결제 예정 금액</span>
						</p>
					</div>
					<div class="all_price">
						<p>
							<span class="price">0</span><span class="f_body3">원</span>
						</p>
					</div>
				</div>
				<div class="flex_btn_wrap">
					<a class="b_color_btn" href="javascript:mypage_cart.payment()">
						<span>결제하기</span>
					</a>
				</div>
			</div>
		</div>
	</div>
</div>
<!--결제하기 클릭시 날릴 form태그 -->
<form id="frm_submit" name="frm_submit" action="/payment/step1.do" method="POST">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" name="brchCd" value="" />
	<input type="hidden" name="yy" value="" />
	<input type="hidden" name="lectSmsterCd" value="" /> 
	<input type="hidden" name="lectCd" value="" /> 
	<input type="hidden" name="optnSeqno" value="" /> 
	<input type="hidden" name="optnUseYn" value="" />
	<input type="hidden" name="lectDetailSq" value="" />
</form>

<script type="text/javascript" src="/resources/common/js/mypage/mypage_cart.js"></script>


