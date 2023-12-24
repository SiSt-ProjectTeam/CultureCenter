<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="cont_wrap">
	<div class="cont_inner no_pb">
		<!-- 2022-11-23 구조 수정 -->
		<form id="searchIntegration" name="searchIntegration">
			<input type="hidden" id="brchCdList" name="brchCdList" value=""/>
			<input type="hidden" id="yyList" name="yyList" value=""/>
			<input type="hidden" id="lectClCdList" name="lectClCdList" value=""/>
			<input type="hidden" id="lectStatCdList" name="lectStatCdList" value=""/>
			<input type="hidden" id="stDaywCdList" name="stDaywCdList" value=""/>
			<input type="hidden" id="timeTypeList" name="timeTypeList" value=""/>
			<input type="hidden" id="amtTypeList" name="amtTypeList" value=""/>
			<input type="hidden" id="stAmt" name="stAmt" value=""/>
			<input type="hidden" id="endAmt" name="endAmt" value=""/>
			<input type="hidden" id="q2" name="q2" value=""/>
			<input type="hidden" id="orderSet" name="orderSet" value="C"/>
			<input type="hidden" id="pageIndex" name="pageIndex" value="1"/>
			<input type="hidden" id="initIndex" name="initIndex" value="1"/>
			<input type="hidden" id="listCnt" name="listCnt" value="20"/>
		
			<div class="page_title_area">
				<div class="inner">
					<div class="top_area">
						<a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a>
						<div class="tit_div">
							<p class="tit f_h1">검색결과</p>
						</div>
					</div>
				</div>
				<div class="form_search">
					<div class="form_search_div">
							<input type="text" style="display:none;">
							<input type="text" placeholder="검색어를 입력하세요" id="q" name="q" onkeyup="if(window.event.keyCode == 13) integration.fn_search();" title="검색어 입력" value="${ param.q }">
							<div class="input_btn_wrap">
								<button type="button" class="btn_delete" title="검색어 지우기"></button>
								<button type="button" class="btn_search" title="검색" onclick="integration.fn_search();"></button>
							</div>
						
					</div>
				</div>
				<div class="m_pop_dimd"></div>
			</div>
		<!-- // 2022-11-23 구조 수정 -->
		</form>
		<div class="page_cont_area no_pt">
			<div class="inner">
				<div class="filter_bar_area">
					<div class="fixed_mobile_w">
						<div class="fixed_mobile">
						
							<div class="filter_bar_div">
								<div class="left">
									<p class="f_caption2"><span class="point" id="totCnt">0개</span>의 강좌</p>
								</div>
								<div class="right">
									<div class="btn_area">
										<div class="btn_wrap srch_btn_wrap">
											<a href="javascript:integration.popResize();" class="btn detail_srch_btn " title="상세검색 팝업 열기"><span>상세검색</span></a>
											<!-- <div class="info_box">
												<p class="info_txt">상세검색을 통해 원하시는 지점과 강좌를 찾아보세요.</p>
												<button type="button" class="close_btn"><img src="/common/images/btn-detail-search-close.svg" alt=""></button>
											</div> -->
										</div>
										<div class="btn_wrap">
											<div class="filter_open_area">
												<a href="javascript:" class="btn order_btn filter_popup_btn" title="컨텐츠정렬 팝업 열기"><div class="order_txt">강의시작일순</div></a>
											</div>
											<div class="dimd"></div>
											<div class="filter_list_wrap">
												<div class="tit_area">
													<p class="tit"></p>
													<a href="javascript:" role="button" class="close"></a>
												</div>
												<div class="scroll_wrap">
													<a class="txt f_caption2 " href="javascript:integration.order('A');">마감임박순</a>
													<a class="txt f_caption2 " href="javascript:integration.order('B');">접수인원순</a>
													<a class="txt f_caption2 on" href="javascript:integration.order('C');">강의시작일순</a>
													<a class="txt f_caption2 " href="javascript:integration.order('D');">낮은가격순</a>
													<a class="txt f_caption2 " href="javascript:integration.order('E');">높은가격순</a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							</div>
					</div>
				</div>
				<!-- trans_direction_div 클래스가 붙으면 세로형이다가 가로형으로 변경됌 -->
				<div class="list_box_div trans_direction_div" id="listContainer">
					
				</div>
				<div class="more_btn_w" id="moreBtn" style="display:none;">
					<a href="javascript:" class="more_btn no_motion">
						<span>강좌 더보기 <strong class="total"></strong></span>
					</a>
				</div>
				
				<div class="srch_popup">
					<div class="dimd"></div>
					<div class="pop_wrap">
						<div class="for_padding">
							<div class="scroll_area">
								<div class="srch_con">
									<p class="tit f_body1">상세검색</p>
									<div class="srch_detail_area">
										<div class="form_search">
											<input type="text" name="q2" onkeyup="if(window.event.keyCode == 13) integration.search_btn();" placeholder="강좌명 or 강사명으로 검색" title="강좌명 or 강사명으로 검색입력" value="">
											<div class="input_btn_wrap">
												<button type="button" class="btn_delete" title="지우기"></button>
												<button type="button" class="btn_search" onclick="integration.search_btn();" title="검색"></button>
													</div>
												</div>
											</div>
											<div class="accordion_type">
												<div class="list_div ">
													<a class="list" href="javascript:">
														<div class="txt_box hide">
															<p class="f_body4">지점</p>
															<span class="num">1</span>
														</div>
													</a>
													
													<div class="hide_con">
														<div class="divide_wrap" id="brchCdListArea">
															<c:choose>
																<c:when test="${not empty bmap}">
																	 <c:forEach var="entry" items="${bmap}">
																		<div class="divide_wrap">
																			<div class="toggle_area">
																				<div class="txt_box hide">
																					
																					<p class="f_caption1">${entry.key}</p>
																					<span class="num">0</span>
																				</div>
																			</div>
																			<div class="filter_btn_list">
																				<c:forEach var="dto" items="${entry.value}">
														                        	<a href="javascript:" data-brch-cd="${dto.branch_id}" class="btn "><span>${dto.branch_nm}</span></a>
														                        </c:forEach>
																			</div>
																		</div>
														            </c:forEach>
														        </c:when>
														    </c:choose>
															
															</div>
													</div>
												</div>
												
												<div class="list_div ">
													<a class="list" href="javascript:">
														<div class="txt_box hide">
															<p class="f_body4">학기</p>
															<span class="num">1</span>
														</div>
													</a>
													<div class="hide_con">
														<div class="filter_btn_list" id="yyListArea">
															<a href="javascript:" class="btn " data-yy="2023" data-lect-smster-cd="4"><span>2023년 겨울학기</span></a>
															<a href="javascript:" class="btn " data-yy="2023" data-lect-smster-cd="3"><span>2023년 가을학기</span></a>
															</div>
													</div>
												</div>
												<div class="list_div ">
													<a class="list" href="javascript:">
														<div class="txt_box hide">
															<p class="f_body4">강좌구분</p>
															<span class="num">1</span>
														</div>
													</a>
													<div class="hide_con">
														<div class="filter_btn_list" id="lectClCdListArea">
															<a href="javascript:" data-lect-cl-cd="1" class="btn "><span>정기</span></a>
															<a href="javascript:" data-lect-cl-cd="2" class="btn "><span>단기</span></a>
															<a href="javascript:" data-lect-cl-cd="3" class="btn "><span>특강</span></a>
															</div>
													</div>
												</div>
												<div class="list_div ">
													<a class="list" href="javascript:">
														<div class="txt_box hide">
															<p class="f_body4">강좌상태</p>
															<span class="num">1</span>
														</div>
													</a>
													<div class="hide_con">
														<div class="filter_btn_list" id="lectStatCdListArea">
															<a href="javascript:" class="btn " data-lect-stat-cd="1"><span>접수예정</span></a>
															<a href="javascript:" class="btn " data-lect-stat-cd="2"><span>접수중</span></a>
															<a href="javascript:" class="btn " data-lect-stat-cd="3"><span>지점문의</span></a>
															<a href="javascript:" class="btn " data-lect-stat-cd="4"><span>대기접수</span></a>
															<a href="javascript:" class="btn " data-lect-stat-cd="5"><span>접수마감</span></a>
															<a href="javascript:" class="btn " data-lect-stat-cd="6"><span>강의종료</span></a>
															<a href="javascript:" class="btn " data-lect-stat-cd="7"><span>접수불가</span></a>
														</div>
													</div>
												</div>
												<div class="list_div ">
													<a class="list" href="javascript:">
														<div class="txt_box hide">
															<p class="f_body4">요일</p>
															<span class="num">1</span>
														</div>
													</a>
													<div class="hide_con">
														<div class="filter_btn_list" id="stDaywCdListArea">
															<p class="txt_box f_caption1">평일</p>
																<a href="javascript:" class="btn " data-st-dayw-cd="1"><span>월요일</span></a>
																<a href="javascript:" class="btn " data-st-dayw-cd="2"><span>화요일</span></a>
																<a href="javascript:" class="btn " data-st-dayw-cd="3"><span>수요일</span></a>
																<a href="javascript:" class="btn " data-st-dayw-cd="4"><span>목요일</span></a>
																<a href="javascript:" class="btn " data-st-dayw-cd="5"><span>금요일</span></a>
																<p class="txt_box f_caption1">주말</p>
																<a href="javascript:" class="btn " data-st-dayw-cd="6"><span>토요일</span></a>
																<a href="javascript:" class="btn " data-st-dayw-cd="7"><span>일요일</span></a>
														</div>
													</div>
												</div>
												<div class="list_div ">
													<a class="list" href="javascript:">
														<div class="txt_box hide">
															<p class="f_body4">시간대</p>
															<span class="num">1</span>
														</div>
													</a>
													<div class="hide_con">
														<div class="filter_btn_list" id="timeTypeListArea">
															<p class="txt_box f_caption1">오전</p>
															<a href="javascript:" class="btn " data-time-type="A"><span>~ 10:00</span></a>
															<a href="javascript:" class="btn "" data-time-type="B"><span>10:00 ~ 12:00</span></a>
															<p class="txt_box f_caption1">오후</p>
																<a href="javascript:" class="btn "" data-time-type="C"><span>12:00 ~ 14:00</span></a>
																<a href="javascript:" class="btn "" data-time-type="D"><span>14:00 ~ 16:00</span></a>
																<a href="javascript:" class="btn "" data-time-type="E"><span>16:00 ~ 18:00</span></a>
																<a href="javascript:" class="btn "" data-time-type="F"><span>18:00 ~ 20:00</span></a>
																<a href="javascript:" class="btn "" data-time-type="G"><span>20:00 ~</span></a>
														</div>
													</div>
												</div>
												<div class="list_div ">
													<a class="list" href="javascript:">
														<div class="txt_box hide">
															<p class="f_body4">수강료</p>
															
															<span class="num">0</span>
														</div>
													</a>
													<div class="hide_con">
														<div class="filter_btn_list" id="amtTypeListArea">
															<a href="javascript:" class="btn " data-amt-type="A"><span>무료</span></a>
															<a href="javascript:" class="btn " data-amt-type="B"><span>5천원 이하</span></a>
															<a href="javascript:" class="btn " data-amt-type="C"><span>5천원 ~ 1만원 이하</span></a>
															<a href="javascript:" class="btn " data-amt-type="D"><span>1만원 ~ 3만원 이하</span></a>
															<a href="javascript:" class="btn " data-amt-type="E"><span>3만원 ~ 5만원 이하</span></a>
															<a href="javascript:" class="btn " data-amt-type="F"><span>5만원 ~ 10만원 이하</span></a>
															<a href="javascript:" class="btn " data-amt-type="G"><span>10만원 이상</span></a>
															<p class="txt_box f_caption1">직접입력</p>
															
															<div class="price_input_wrap btn">
																<div class="form_input">
																	<input type="text" title="검색될 최소 금액 입력" placeholder="최소금액" value="" maxlength="14" name="stAmt" oninput="this.value=this.value.replace(/[^0-9]/g,'');this.value=fnc.fn_numberComma(this.value);">
																</div>
																<span class="tilde">~</span>
																<div class="form_input">
																	<input type="text" title="검색될 최대 금액 입력" placeholder="최대금액" value="" maxlength="14" name="endAmt" oninput="this.value=this.value.replace(/[^0-9]/g,'');this.value=fnc.fn_numberComma(this.value);">
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<a class="btn_close" href="javascript:" title="닫기"><span class="blind"></span></a>
										</div>
									</div>
									
									<div class="reset_wrap">
										<a href="javascript:" class="reset_btn">
											<span>초기화</span>
										</a>
										<a class="filter_btn" href="javascript:integration.search_btn();">
											<span>강좌 보기</span>
										</a>
									</div>
								</div>
							</div>
						</div>
				</div>
			</div>
		</div>
     </div>
<script type="text/javascript" src="/resources/common/js/application/integration/integrationList.js"></script>