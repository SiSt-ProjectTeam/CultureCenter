<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 
header에 data-url 부분 다름 아래로 바꿔줘야함 
<div class="m_navi_bar " data-url="/mypage/cart/list.do"> 
-->
<div class="cont_wrap shopbag_w">

	<form id="frm_search" name="frm_search">
		<input type="hidden" id="pageIndex" name="pageIndex" value="${param.pageIndex}" />
		<input type="hidden" id="brchCd" name="brchCd" value="" />
		<input type="hidden" id="brchNm" name="brchNm" value="" />
	</form>
 
	<div class="cont_inner no_pb">
		<!-- 상단부 알림창 -->
		<div class="page_title_area">
			<div class="inner">
				<div class="top_area">
					<a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a>
					<div class="tit_div">
						<p class="tit f_h1">장바구니</p>
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
											목록 <span class="point">${list.size()}개</span>
										</p>
									</div>
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
														<a class="txt f_caption2 on"
															href="javascript:mypage_cart.changeBrchCd('', '')">전체지점</a>
														<p class="sub_tit only_mobile">서울점</p>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0002', '잠실점')">잠실점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0001', '본점')">본점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0013', '강남점')">강남점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0028', '건대스타시티점')">건대스타시티점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0006', '관악점')">관악점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0340', '김포공항점')">김포공항점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0022', '노원점')">노원점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0026', '미아점')">미아점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0010', '영등포점')">영등포점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0004', '청량리점')">청량리점</a>
														<p class="sub_tit only_mobile">수도권점</p>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0344', '인천점')">인천점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0399', '동탄점')">동탄점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0335', '구리점')">구리점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0008', '분당점')">분당점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0349', '수원점')">수원점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0336', '안산점')">안산점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0011', '일산점')">일산점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0334', '중동점')">중동점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0341', '평촌점')">평촌점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0350', '롯데몰광명점')">롯데몰광명점</a>
														<p class="sub_tit only_mobile">지방점</p>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0005', '부산본점')">부산본점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0333', '광복점')">광복점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0007', '광주점')">광주점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0023', '대구점')">대구점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0012', '대전점')">대전점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0016', '동래점')">동래점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0354', '마산점')">마산점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0024', '상인점')">상인점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0027', '센텀시티점')">센텀시티점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0015', '울산점')">울산점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0025', '전주점')">전주점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0017', '창원점')">창원점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0014', '포항점')">포항점</a>
														<a class="txt f_caption2 "
															href="javascript:mypage_cart.changeBrchCd('0361', '롯데몰군산점')">롯데몰군산점</a>
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
					<div class="course_history_w" data-mbr-grde-cd="" data-mvg-blstr-cd="">
						<c:choose>
							<c:when test="${empty list}">
								<div class="no_srch_area no_pb">
									<div class="no_srch_div">
										<p class="txt f_h2">
											<span class="normal_value">장바구니가 비어있습니다.</span>
										</p>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<c:forEach items="${list}" var="dto" varStatus="i">							
									<div class="cour_his_list"
									    data-lect-stlm-amt="${dto.ex_charge + dto.class_fee}"
										data-cart-seqno="${dto.detail_class_sq}" data-brch-cd="${dto.branch_id}" data-yy="${dto.open_year}"
										data-lect-smster-cd="${dto.open_smst_id}" data-lect-cd="${class_id}"
										data-lect-stat-cd="${dto.class_st_id}" data-mvg-dsply-use-yn="N"
										data-optn-seqno=""
										data-lect-nm="${dto.class_nm}"
										data-optn-use-yn=N 
										data-pbl-pmprcust-parnt-brch-cd="" 
										data-pbl-pmprcust-parnt-lect-cd=""
										data-pbl-pmprcust-parnt-brch-cd-nm="" 
										data-atlct-dupl-yn="N"
										data-lect-dupl-yn="N" 
										data-optn-validate-yn="Y">
										
										<div class="cour_top_area">
											<div class="form_checkbox">
												<input type="checkbox" id="shopbagAgree${i.index+1}" name=""
													onclick="mypage_cart.clickCheckbox(this)"
													autocomplete="off"><label for="shopbagAgree${i.index+1}"></label>
											</div>
											<div class="left">
												<div class="left_info">
													<div class="label_div">
														<p class="label small lime">${dto.class_st}</p>
														<p class="label small black_gray">${dto.branch_nm}</p>
													</div>
													<p class="tit f_h2">
														<a href="/application/search/view.do?brchCd=0001&yy=2023&lectSmsterCd=4&lectCd=0520">
															<span>${dto.class_nm}</span>
														</a>
													</p>
												</div>
											</div>
											<div class="right">
												<ul class="txt_wrap">
													<li class="dl f_body2">
														<p class="dt only_pc">강사명</p>
														<p class="dd f_body1">${dto.teacher_nm}</p>
													</li>
													<li class="dl f_body2">
														<p class="dt only_pc">학기명</p>
														<p class="dd f_body1">${dto.open_year}년 ${dto.smst_nm}</p>
													</li>
													<li class="dl f_body2">
														<p class="dt only_pc">강좌정보</p>
														<p class="dd f_body1"><fmt:formatDate value="${dto.schedule_start_dt}" pattern="yyyy.MM.dd"/>  ~ <fmt:formatDate value="${dto.schedule_end_dt}" pattern="yyyy.MM.dd"/>
															(${dto.day}) ${dto.start_time}~${dto.end_time} / ${dto.class_cnt}회</p>
													</li>
												</ul>
												<ul class="txt_wrap">
													<li class="dl f_body2">
														<p class="dt">강좌료</p>
														<p class="dd f_body1"><fmt:formatNumber value="${dto.class_fee}" type="currency" currencySymbol=""/>원</p>
													</li>
												
													<c:if test="${not empty dto.ex_charge and dto.ex_charge != '0'}">
														<li class="dl f_body2">
							                            	<p class="dt">재료비/대여료</p>
							                            	<p class="dd f_body1"><fmt:formatNumber value="${dto.ex_charge}" type="currency" currencySymbol=""/>원</p>
										                </li>
													</c:if>
													
													<li class="dl total_pay f_body2">
														<p class="dt">총금액</p>
														<p class="dd f_body1"><fmt:formatNumber value="${dto.ex_charge + dto.class_fee}" type="currency" currencySymbol=""/>원</p>
													</li>
												</ul>
											</div>
											<a href="javascript:" class="f_btn delete_btn" role="button"
												onclick="mypage_cart.removeCart(this)"></a>
										</div>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>


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
	<input type="hidden" name="csrfPreventionSalt" value="wNMtwqBUuEtJJBjwBN1o" />
	<input type="hidden" name="brchCd" value="" />
	<input type="hidden" name="yy" value="" />
	<input type="hidden" name="lectSmsterCd" value="" /> 
	<input type="hidden" name="lectCd" value="" /> 
	<input type="hidden" name="optnSeqno" value="" /> 
	<input type="hidden" name="optnUseYn" value="" />
</form>

<script type="text/javascript" src="/resources/common/js/mypage/mypage_cart.js"></script>
