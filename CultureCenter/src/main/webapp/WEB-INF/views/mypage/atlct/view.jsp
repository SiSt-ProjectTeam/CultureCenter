<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="cont_wrap" data-page-type="view">
	<form id="frm_update" name="frm_update">
		<input type="hidden" name="csrfPreventionSalt" value="t4o3WqPtK4rgZj48oaDy" />
		<input type="hidden" id="atlctRsvNo" name="atlctRsvNo" value="${frmSearchDTO.atlctRsvNo}" />
		<input type="hidden" id="actlAtlctNpleSeqno" name="actlAtlctNpleSeqno" value="" />
		<input type="hidden" id="rfndRsnCd" name="rfndRsnCd" value="" />
		<input type="hidden" id="rfndClCd" name="rfndClCd" value="" />
		<input type="hidden" id="rfndStatCd" name="rfndStatCd" value="" />
		<input type="hidden" id="rfndChnlCd" name="rfndChnlCd" value="O" />
	</form>
	<div class="cont_inner no_pb">
		<div class="page_title_area">
			<div class="inner">
				<div class="top_area">
					<a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a>
					<div class="tit_div">
						<p class="tit f_h1">수강내역 상세</p>
					</div>
				</div>
			</div>
			<div class="m_pop_dimd"></div>
		</div>

		<div class="page_cont_area">
			<div class="inner pay_info">
				<div class="sub_inner_w">
					<div class="sub_inner narrow_margin">
						<div class="sub_tit_area">
							<div class="left">
								<p class="f_h2">주문정보</p>
							</div>
							</div>
						<div class="form_table">
							<table>
								<caption></caption>
								<colgroup>
									<col width="25%">
									<col width="75%">
								</colgroup>
								<tbody>
									<c:forEach var="atlctDTO" items="${atlctList}" begin="0" end="0">
										<tr>
											<th><p class="f_body1">주문번호</p></th>
											<td class="ta_left"><p class="f_body2">${atlctDTO.order_sq} </p></td>
										</tr>
										<tr>
											<th><p class="f_body1">결제채널</p></th>
											<td class="ta_left"><p class="f_body2">온라인</p></td>
										</tr>
										<tr>
											<th><p class="f_body1">결제일</p></th>
											<td class="ta_left"><p class="f_body2">${atlctDTO.order_dt}</p></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="sub_inner">
						<div class="sub_tit_area">
							<div class="left">
								<p class="f_h2">수강내역</p>
							</div>
							<div class="right">
								<div class="flex_btn_wrap">
									<c:if test="${!allRfndCk}">
										<a class="border_btn" href="javascript:mypage_atlct.openCnclPopup(true)" role="button"> <span>전체취소</span></a>
										<a class="border_btn" href="javascript:mypage_atlct.openCnclPopup(false)" role="button"> <span>취소</span></a>
									</c:if>
								</div>
							</div>
						</div>
						<div class="course_history_w">
						<c:forEach var="atlctDTO" items="${atlctList}">
							<div class="cour_his_list">
			                      <div class="cour_top_area">
			                        <div class="left">
			                          <div class="label_div">
			                            <p class="label small border">${atlctDTO.branch_nm}</p>
			                            </div>
			                          <p class="tit f_h2">
			                          	<a href="/application/search/view.do?branchCd=${atlctDTO.branch_id}&yy=${atlctDTO.open_year}&lectSmsterCd=${atlctDTO.open_smst_id}&lectCd=${atlctDTO.detail_class_sq}">${atlctDTO.class_nm}</a>
			                          </p>
			                        </div>
			                        <div class="right">
			                          <ul class="txt_wrap">
			                            <li class="dl f_body2">
			                              <p class="dt only_pc">강사명</p>
			                              <p class="dd f_body1">${atlctDTO.teacher_nm}</p>
			                            </li>
			                            <li class="dl f_body2">
			                              <p class="dt only_pc">학기명</p>
			                              <p class="dd f_body1">${atlctDTO.open_year}년 ${atlctDTO.smst_nm}</p>
			                            </li>
			                            <li class="dl f_body2">
			                              <p class="dt only_pc">강좌정보</p>
			                              <p class="dd f_body1">${atlctDTO.class_schedule}</p>
			                            </li>
			                          </ul>
			                          <ul class="txt_wrap">
			                            <li class="dl f_body2">
			                              <p class="dt">강좌료<span class="colon">:</span></p>
			                              <p class="dd f_body1">${atlctDTO.class_fee}원</p>
			                            </li>
			                            </ul>
			                        </div>
			                      </div>
			                      <div class="cour_detail_w plural">
				                    <c:forEach var="atlctPersonalDTO" items="${atlctDTO.personalList}">
				                    	<div class="cour_detail check_detail">
					                      <div class="left">
					                      	<div class="form_checkbox agree_chk" data-rfnd-optn-use-yn="N" data-free-rfnd-yn="Y" data-fgft-app-no="" data-recp-yn="" data-wdw-yn="">
							                    <c:if test="${ atlctPersonalDTO.receipt_status_id < 4 }">
							                    	<input type="checkbox" id="actlAtlctNpleSeqno${ atlctPersonalDTO.order_detail_sq }" name="rfndChk" value="${ atlctPersonalDTO.order_detail_sq }">
							                    </c:if>
							                    <label for="actlAtlctNpleSeqno${ atlctPersonalDTO.order_detail_sq }">${ atlctPersonalDTO.children_nm }</label>
							                </div>
					                      </div>
					                      <div class="right">
					                      	                              
					                          <c:choose>
						                          <c:when test="${ atlctPersonalDTO.receipt_status_id == 4 }">
						                          	<ul class="txt_wrap">
							                          	<li class="f_body3">
								                            <div class="txt_con">
								                              <div class="tit">접수상태</div>
								                              <div class="txt">
								                                <p>${ atlctPersonalDTO.receipt_status }</p>
								                              </div>
								                            </div>
								                          </li>
								                          
								                          <li class="f_body3">
								                        <div class="txt_con">
								                          <div class="tit">취소(환불)일 </div>
								                          <div class="txt">
								                            <p>${ atlctPersonalDTO.cancel_dt }</p>
								                          </div>
								                        </div>
								                      </li>
								                      <li class="f_body3">
								                        <div class="txt_con show"><!-- 2022-12-19 class 추가 -->
								                          <div class="tit">취소(환불)금액</div>
								                          <div class="txt drop_type">
								                            <p>${ atlctPersonalDTO.cancel_amt }원</p>
								                            <a href="javascript:" class="drop_btn"></a>
								                          </div>
								                        </div>
								                        
								                        <div class="hide_con_w">
										                    <div class="hide_con">
										                      <ul class="price_wrap">
										                        <li class="f_body4">
										                          <p class="name">강좌료</p>
										                          <div class="price">0원</div>
										                        </li>
										                      </ul>
										                    </div>
											              <c:if test="${ atlctPersonalDTO.ex_charge } > 0">
											                  <div class="hide_con">
											                    <ul class="price_wrap">
											                      <li class="f_body4">
												                        <p class="name">재료비/대여료</p>
												                        <div class="price">0원</div>
											                      </li>
											                    </ul>
											                  </div>
											              </c:if>
										                </div>
								                      </li>
								                      </ul>
								                        
								                     <p class="caution f_caption2">취소(환불) 사유 : ${ atlctPersonalDTO.cancel_reason }</p>
						                          </c:when>
						                          <c:otherwise>
						                          	<ul class="txt_wrap">
								                      <li class="f_body3">
								                        <div class="txt_con">
								                          <div class="tit">접수상태</div>
								                          <div class="txt">
								                            <p>${ atlctPersonalDTO.receipt_status }</p>
								                          </div>
								                        </div>
								                      </li>
										              <li class="f_body3">
											              <div class="txt_con show"><!-- 2022-12-19 class 추가 -->
											                <div class="tit">주문금액</div><!-- 2022-11-23 텍스트 수정 -->
											                <div class="txt drop_type">
											                  <p>${ atlctPersonalDTO.class_fee + atlctPersonalDTO.ex_charge }원</p>
											                  <a href="javascript:" class="drop_btn"></a>
											                </div>
											              </div>
											              <div class="hide_con_w">
											                <div class="hide_con">
											                  <ul class="price_wrap">
											                    <li class="f_body4">
											                      <p class="name">강좌료</p>
											                      <div class="price">${ atlctPersonalDTO.class_fee }원</div>
											                      <c:if test="${ atlctPersonalDTO.ex_charge } > 0">
												                      <p class="name">재료비/대여료</p>
												                      <div class="price">${ atlctPersonalDTO.ex_charge }원</div>
											                      </c:if>
											                    </li>
											                    </ul>
											              	</div>
											              </div>
										              </li>
								                    </ul>
						                          </c:otherwise>
					                          	</c:choose>
					                          	
					                        </div>
					                    </div>
				                    </c:forEach>
			                      </div>
			                    </div>
						
						</c:forEach>
										
						</div>
					</div>
					
					<c:forEach var="atlctDTO" items="${atlctList}" begin="0" end="0">
						<!-- show_line 클래스가 sub_inner에 있으면 PC에서도 line이 보입니다  -->
						<div class="sub_inner show_line">
							<div class="sub_tit_area">
								<div class="left">
									<p class="f_h2">결제정보</p>
								</div>
								<div class="right"></div>
							</div>
							
							<div class="payment_type">
								<div class="left">
									<ul class="txt_wrap">
										<li class="f_body2">
												<div class="txt">
													<p class="f_body4">신용카드</p>
													<p class="txt_detail f_caption2">
							                              <span></span>
							                              <a class="border_btn" href="https://npg.nicepay.co.kr/issue/IssueLoader.do?type=0&amp;TID=${atlctDTO.tid}" onclick="window.open(this.href, '_blank', 'popup'); return false;" role="button">							                                <span>영수증</span>
							                              </a>
							                            </p>
													</div>
												<p class="price">${atlctDTO.total_amt}원</p>
											</li>
										</ul>
								</div>
								<div class="right">
									<div class="total_price">
										<p class="f_btn">총 결제금액</p>
										<!-- 2022-11-28 텍스트 변경 -->
										<div class="pay_wrap">
											<p class="pay f_h2">
												${atlctDTO.total_amt}<span class="unit">원</span>
											</p>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class="sub_inner">
						  <div class="sub_tit_area">
						    <div class="left">
						      <p class="f_h2">L.POINT 적립내역</p>
						    </div>
						    <div class="right"></div>
						  </div>
						  <div class="payment_type">
						    <div class="left">
						      <ul class="txt_wrap">
						      	<li class="f_body2">
							          <div class="txt">
							            <p class="f_body4">구매적립</p>
							          </div>
							          <p class="price">${atlctDTO.add_point}원</p>
							        </li>
						      	</ul>
						    </div>
						    <div class="right"></div>
						  </div>
						</div>
					</c:forEach>
		            <div class="flex_btn_wrap no_pb margin_large">
		              <a class="border_btn" href="/mypage/atlct/list.do?pageIndex=${frmSearchDTO.pageIndex}&type=${frmSearchDTO.type}&lectSmsterCd=${frmSearchDTO.lectSmsterCd}&yy=${frmSearchDTO.yy}&q=${frmSearchDTO.q}&atlctRsvNo=${frmSearchDTO.atlctRsvNo}&initIndex=${frmSearchDTO.initIndex}&listCnt=${frmSearchDTO.listCnt}&prevSesterYy=${frmSearchDTO.prevSesterYy}&prevSesterLectSmsterCd=${frmSearchDTO.prevSesterLectSmsterCd}">
		                <span>이전</span>
		              </a>
		            </div>
				</div>
			</div>
		</div>
		
	</div>
</div>

<!-- 결제취소 팝업 레이어 -->
<div id="rfnd30" class="layer_popup" style="display:none">
	<div class="pop_wrap w800 full">
		<div class="pop_head">
			<p class="title">결제취소</p>
		</div>
		<div class="pop_cont fixed_out_pop">
			<div class="for_padding narrow_padding">
				<div class="scroll_area">
					<p class="notice_txt f_body2">환불 규정에 따라 환불 환불차감금액이 발생하지 않습니다.<br />취소하시겠습니까?</p>
					<div class="sub_inner">
						<div class="dot_txt_box">
							<p class="refund_condition">1개월 이내 강좌</p>
							<p class="dot_txt">수강시간 1/3 경과 전 환불 시 수강료 2/3 환급</p>
							<p class="dot_txt">수강시간 1/2 경과 전 환불 시 수강료 1/2 환급</p>
							<p class="dot_txt">수강시간 1/2 경과 후 수강료 미 환급</p>
						</div>
						<div class="dot_txt_box">
							<p class="refund_condition">1개월 초과 강좌</p>
							<p class="dot_txt">1개월 이내 강좌 기준 + 잔여월 수강료 전액 환급</p>
						</div>
						<div class="dot_txt_box">
							<p class="refund_condition">결제 취소(환불)에 대한 사유를 선택해 주세요.</p>
							<div class="form_select_div w100p">
								<div class="open_area">
									<a class="btn_open" href="javascript:">
										<span>선택</span>
									</a>
								</div>
								<div class="dimd"></div>
								<div class="list_wrap rfndRsn">
									<div class="tit_area">
										<p class="tit">결제 취소(환불) 사유 선택</p>
										<a href="javascript:" role="button" class="close"></a>
									</div>
									<div class="scroll_wrap">
										<a class="btn_link" href="javascript:" data-rfnd-rsn-cd="1"><span>고객변심</span></a>
										<a class="btn_link" href="javascript:" data-rfnd-rsn-cd="2"><span>컴플레인</span></a>
										<a class="btn_link" href="javascript:" data-rfnd-rsn-cd="3"><span>이사</span></a>
										<a class="btn_link" href="javascript:" data-rfnd-rsn-cd="4"><span>강사불만</span></a>
										<a class="btn_link" href="javascript:" data-rfnd-rsn-cd="5"><span>강좌불만</span></a>
										<a class="btn_link" href="javascript:" data-rfnd-rsn-cd="6"><span>폐강</span></a>
										<a class="btn_link" href="javascript:" data-rfnd-rsn-cd="7"><span>기타</span></a>
										<a class="btn_link" href="javascript:" data-rfnd-rsn-cd="8"><span>강좌변경</span></a>
										<a class="btn_link" href="javascript:" data-rfnd-rsn-cd="9"><span>결제형태 변경</span></a>
										</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="pay_cancel_btn_w fixed_out_btn">
				<p class="f_body1">선택하신 강좌의 온라인 결제를 취소하시겠습니까?</p>
				<div class="flex_box">
					<a class="border_btn" href="javascript:mypage_atlct.closeCnclPopup()">
						<span>아니오</span>
					</a>
					<a class="b_color_btn" href="javascript:" onclick="mypage_atlct.updateCncl(this, '30', null, 'N')">
						<span>예</span>
					</a>
				</div>
			</div>
		</div>
		<a class="btn_close" href="javascript:" title="닫기">
			<span class="blind"></span>
		</a>
	</div>
</div>

<script type="text/javascript" src="/resources/common/js/mypage/mypage_atlct.js"></script>
