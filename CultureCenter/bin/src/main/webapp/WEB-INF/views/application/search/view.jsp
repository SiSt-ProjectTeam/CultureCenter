<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>			
<!-- 공동모객 여부 -->
<div class="cont_wrap">
	<div class="cont_inner no_pb">
		<div class="page_title_area white_ver only_mobile">
			<div class="inner">
				<div class="top_area">
					<a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a>
					<!-- 2022-11-23 수정 -->
					<div class="share_area">
						<a href="javascript:commonScript.openPopupFn('#sharePop');" class="share_btn" title="공유하기"></a>
					</div>
					<!-- // 2022-11-23 수정 -->
				</div>
			</div>
		</div>
				
		<div class="page_cont_area no_padding"><!-- 2022-11-29 no_pt 클래스 삭제, no_padding 클래스 추가 -->
			<div class="top_visual full only_mobile">
				<p class="bg_img">
					
					<img class="webPath" src="/upload/detail/${ dto.detail_img }" alt="${ dto.detail_img }">
				</p>
			</div>
			<div class="bg_inner pd_bot"><!-- pd_bot 클래스 추가 -->
				<div class="inner">
					<div class="right_box_fix_area anchor_func_area">
						<div class="fixed_content_area">
							<div class="fix_box_area">
								<!-- 2022-11-24 course_popup 한번 더 감쌈 -->
								<div class="shadow_div">
									<div class="course_popup" id="one">
										<div class="pop_wrap">
											<div class="pop_cont">
												<div class="for_padding">
													<div class="scroll_area">
														<div class="pop_head">
															<div class="top_area">
																<div class="label_div">
																	<p class="label large lectStatNm">${ dto.class_st }</p>
																	<p class="label large border lectClNm">${ dto.class_div }</p>
																</div>
																<div class="share_btn_wrap only_pc">
																	<a href="javascript:commonScript.openPopupFn('#sharePop');" class="share_btn" title="공유하기"></a>
																</div>
															</div>
															<div class="tit_box">
																<p class="thum_img only_pc">
																	<img class="webPath" src="/upload/thumbnail/${ dto.class_img }" alt="${ dto.class_img }"/>
																</p>
																<div class="txt_wrap">
																	<p class="tit lectNm">${ dto.class_nm }</p>
																	<p class="desc lectExpl"></p>
																</div>														
															</div>
														</div>
														<div class="filter_bar_area only_mobile no_padding">
															<div class="fixed_mobile_w">
																<div class="fixed_mobile">
																	<div class="border_tab_area anchor_btn_area">
																		<div class="swiper-container">
																			<div class="swiper-wrapper">
																				<a href="javascript:" class="anchor_btn swiper-slide on"><span>강좌정보</span></a>
																				<a href="javascript:" class="anchor_btn swiper-slide"><span>강좌소개</span></a>
																				<a href="javascript:" class="anchor_btn swiper-slide"><span>수강후기</span></a>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
														<div class="box_con">
															<div class="content_area">
																<div class="anchor_con">
																	<p class="sub_tit f_h2">강좌정보</p>
																	<c:choose>
																		<c:when test="${ classDtl.optionList.size() > 1 }">
																			<div class="form_txt_wrap" id="selectLect">
																				<p class="txt">강좌를 선택하세요.</p>
																				<div class="form_select_div change">
																					<!-- 2022-11-25 round class 삭제 -->
																					<div class="open_area">
																						<a class="btn_open" href="javascript:">
																							<div class="option_div">
																								<span>선택하세요</span>
																							</div>
																						</a>
																					</div>
																					<div class="dimd"></div>
																					<div class="list_wrap">
																						<div class="tit_area">
																							<p class="tit">강좌정보</p>
																							<a href="javascript:" role="button" class="close"></a>
																						</div>
																						<div class="scroll_wrap">
																							<a class="btn_link on" href="javascript:search.classInfoSet('${ classDtl.branch_id }', '${ classDtl.open_year }', '${ classDtl.open_smst_id }', '${ dto.detail_class_sq }', 'close');">
																								<div class="option_div">
																									<span>선택하세요</span>
																								</div>
																							</a>
																							<c:forEach var="cdto" items="${ classDtl.optionList }">
																								<a class="btn_link" href="javascript:search.classInfoSet('${ classDtl.branch_id }', '${ classDtl.open_year }', '${ classDtl.open_smst_id }', '${ cdto.detail_class_sq }');">
																									<div class="option_div">
																										<span>${ cdto.day }</span> <span>${ cdto.start_time }~${ cdto.end_time }</span> <span>${ cdto.class_cnt }회</span>
																									</div>
																								</a>
																							</c:forEach>

																						</div>
																					</div>
																				</div>
																			</div>

																		</c:when>
																	</c:choose>

																	
																	
																	<div class="data_box">
																		<div class="selected_info">
																			<dl>
																				<dt>지점</dt>
																				<dd class="brchNm">${ dto.branch_nm }</dd>
																			</dl>
																			<dl>
																				<dt>강좌구분</dt>
																				<dd class="lectClNm">${ dto.class_div }</dd>
																			</dl>
																			<dl>
																				<dt>학기</dt>
																				<dd class="yy">${ dto.open_year }년 ${ dto.smst_nm }</dd>
																			</dl>
																			<dl>
																				<dt>강사명</dt>
																				<dd>
																					<a href="javascript:search.teacherSet($(this));" id="tcBtn" data-tc-cd-no="${ dto.member_sq }" class="arrow_btn">
																						<span class="tcNm">${ dto.name }</span>
																					</a>
																				</dd>
																			</dl>
																			<dl>
																				<dt>강의기간</dt>
																				<dd class="lectStDtm">${ lectStDtm }</dd>
																			</dl>
																			<dl>
																				<dt>강의시간</dt>
																				<dd class="lectTime">
																					<p>${ lectTime }</p>
																					</dd>
																			</dl>
																			<dl>
																				<dt>강의횟수/정원</dt>
																				<dd class="lectNbcnt">${ dto.class_cnt }회/${ dto.people_tot }명</dd>
																			</dl>
																			<dl>
																				<dt>강의실</dt>
																				<dd class="lectRmNm">${ classDtl.classroom_nm }</dd>
																			</dl>
																			<dl>
																				<dt>수강료</dt>
																				<dd class="lectAmt">
																					${ fee }원
																						</dd>
																			</dl>
																			<dl>
																				<dt>대상구분</dt>
																				<dd class="objClNm">${ dto.target_div }</dd>
																			</dl>
																			<dl>
																				<dt>접수기간</dt>
																				<dd class="rceptPrdStDt">${ rceptPrdStDt }</dd>
																			</dl>
																			<dl>
																				<dt>문의처</dt>
																				<dd class="telno">${ dto.tel }</dd>
																			</dl>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="fixed_btn_area btn_area" style="display:block;">
													<!-- 수강신청, 대기신청, 지점문의, 접수마감 -->
													<div class="single_btn_area one_layer"> 
														<div class="btn_wrap">
															<a class="sign_btn" href="javascript:search.btnCheck();">
																
																<span class="lectStatCd"></span>
																
															</a> 
														</div>
													</div>				
												</div>
											</div>
										</div>
										<div class="dimd_div"></div><!-- 2022-11-24 클래스 추가 -->
									</div>
									<!-- 일반/공동모객 강좌 -->
									
									<div class="course_popup list multiple"><!-- 2022-11-23 hide 클래스 삭제 -->
										<form id="classForm" name="classForm">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
											<input type="hidden" id="brchCd" name="brchCd" value=""/>
											<input type="hidden" id="yy" name="yy" value=""/>
											<input type="hidden" id="lectSmsterCd" name="lectSmsterCd" value=""/>
											<input type="hidden" id="lectCd" name="lectCd" value="${ classDtl.detail_class_sq }"/>
											<input type="hidden" id="lectStatCd" name="lectStatCd" value="${ classDtl.class_st_id }"/>
											<input type="hidden" id="optnUseYn" name="optnUseYn" value=""/>
											<input type="hidden" id="mvgDsplyUseYn" name="mvgDsplyUseYn" value=""/>
											<input type="hidden" id="lectNm" name="lectNm" value=""/>
											<input type="hidden" id="optnTypCd" name="optnTypCd" value=""/>
											<input type="hidden" id="lectAmt" name="lectAmt" value=""/>
											<input type="hidden" id="optnSeqno" name="optnSeqno" value=""/>
											<input type="hidden" id="optnNm" name="optnNm" value=""/>
											<input type="hidden" id="optnAmt" name="optnAmt" value=""/>
											<input type="hidden" id="lectStDtm" name="lectStDtm" value=""/>
											<input type="hidden" id="lectSt" name="lectStDtm" value=""/>
											<input type="hidden" id="partRfndPsblYn" name="partRfndPsblYn" value=""/>
											<input type="hidden" id="pblPmprcustParntBrchCd" name="pblPmprcustParntBrchCd" value="" />
											<input type="hidden" id="pblPmprcustParntLectCd" name="pblPmprcustParntLectCd" value="" />
											<input type="hidden" id="pageIndex" name="pageIndex" value="1"/>
											<input type="hidden" id="initIndex" name="initIndex" value="1"/>
											<input type="hidden" id="listCnt" name="listCnt" value="10"/>
											<input type="hidden" id="groupLectTpCd" name="groupLectTpCd" value=""/>
											<input type="hidden" id="groupLectClCd" name="groupLectClCd" value=""/>
											<input type="hidden" id="lectDetailSq" name="lectDetailSq" value="${ classDtl.detail_class_sq }"/>
										</form>
										<div class="pop_wrap">
											<div class="pop_cont">
												<div class="for_padding">
													<div class="scroll_area">
														<div class="pop_head">
															<div class="top_area">
																<a href="javascript:" class="box_prev_btn" title="뒤로가기"></a>
															</div>
														</div>
															
														<div class="box_con" id="two">
															<div class="content_area">
																<div class="form_txt_wrap" id="optionArea" style="display:none;">
																	<p class="txt">재료비/대여료 선택</p>
																	<div class="form_select_div change">
																		<div class="open_area">
																			<a class="btn_open" href="javascript:">
																				<span>옵션선택</span>
																			</a>
																		</div>
																		<div class="dimd"></div>
																		<div class="list_wrap">
																			<div class="tit_area">
																				<p class="tit">옵션정보</p>
																				<a href="javascript:" role="button" class="close"></a>
																			</div>
																			<div class="scroll_wrap">
																				<a class="btn_link default" href="javascript:search.optionSet('', '', '', '');"><span>옵션선택</span></a>
																				
																				<a class="btn_link" data-optn-seqno="11653" href="javascript:search.optionSet('재료비/대여료', ${ ex }, '11653', 'N');">
																						<span>재료비/대여료 (+${ exCharge }원)</span>
																				</a>
																			</div>
																		</div>
																	</div>
																</div>
															
																<div class="selected_box">
																	<div class="content_txt">
																		<p class="name">${ classDtl.class_nm }</p>
																		<div class="option_div">
																			</div>
																	</div>
																	<p class="price_txt">
																		<span class="price">${ fee }</span>원
																			</p>
																</div>
															</div>
														</div>
													</div>
												</div>
												<!-- 일반/공동모객강좌, 다회차강좌, 순회강좌 버튼 -->
												<div class="total_sum_area btn_area">
													<div class="btn_txt_wrap">
														<div class="txt_div">
															<p class="sum_txt">총 주문 금액</p>
															<p class="price_txt">
																<span class="price">${ fee }</span> 원
																	</p>
														</div>
														<div class="btn_wrap">
															<a class="cart_btn" href="javascript:search.cartBtn()" role="button" style="display:block;">
																<span class="blind">장바구니</span>
															</a>
															<a class="sign_btn b_color_btn" href="javascript:search.paymentBtn();">
																<span></span>
															</a>
														</div>
													</div>
													
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- // 2022-11-24 course_popup 한번 더 감쌈 -->
								<div class="box_dimd"></div>
							</div>
						</div>
						
						<div class="flow_txt_area">
							<div class="anchor_con info_img_inner">
								<p class="sub_tit f_h2">강좌소개</p>

								<div class="info_img_txt">
									<p class="img"><img class="only_pc" src="/upload/detail/${ dto.detail_img }" alt="${ dto.detail_img }" alt="${ dto.detail_img }">
									<img class="only_mobile" src="/upload/detail/${ dto.detail_img }" alt="${ dto.detail_img }" alt="${ dto.detail_img }"></p>
														<div class="txt_box">
															${ dto.class_content }</div>
													</div>
								
								<script type="text/javascript">
									//<![CDATA[
									function contentHtml() {
										var body = $('#contemtIframe').contents().find('body').html();
										var httpsUrl = $('#contemtIframe').data("httpsUrl");
									  	$(".cont_wrap .flow_txt_area .info_img_txt").html(body);
									  	
										$(".cont_wrap .flow_txt_area .info_img_txt").find("img").each(function() {
									  		$(this).attr("src", $(this).attr("src").replaceAll("https://culture.dpt.co.kr/LDCS/", httpsUrl + "/files/CUL_ONL/OLD/COMMON/")); 
									  	}); 
									}
									//]]>
								</script>
								<div class="more_btn_wrap ta_center only_mobile" style="display:none;">
									<a href="javascript:" class="more_btn">
										<span>강좌소개 더보기</span>
									</a>
								</div>

								<span class="content_border only_mobile"></span>
								<div class="mobile_acco_div open"><!-- 2022-12-05 클래스 추가 -->
									<div class="accordion_type">
										<div class="list_div">
											<a class="list" href="javascript:">
												<div class="txt_box hide">
													<p class="sub_txt f_h2">강의일정</p>
													<span class="num">0</span>
												</div>
											</a>
											<div class="hide_con">
												<div class="info_txt_wrap">
															${ dto.class_sched_desc }
														</div>
													</div>
										</div>
										<div class="list_div">
											<a class="list" href="javascript:">
												<div class="txt_box hide">
													<p class="sub_txt f_h2">준비물/특이사항</p>
													<span class="num">0</span>
												</div>
											</a>
											<div class="hide_con">
												<div class="info_dot_txt">
													${ classDtl.supplies }
													</div>
											</div>
										</div>
										<div class="list_div">
											<a class="list" href="javascript:">
												<div class="txt_box hide">
													<p class="sub_txt f_h2">유의사항</p>
													<span class="num">0</span>
												</div>
											</a>
											<div class="hide_con">
												<div class="info_dot_txt">
													<p class="dot_txt">강좌 취소 및 환불은 수업 참여여부와 상관없이 [평생교육법 시행령]에 의거해 처리됩니다.</p>
													<p class="dot_txt">일반 강좌는 개강 1일 전까지 취소 및 환불 가능하며, 당일 취소는 적용되지 않습니다.</p>
													<p class="dot_txt">재료 준비가 필요한 일부 강좌는 강좌 시작일의 3일 전까지만 전액 취소가 가능합니다.</p>
													<p class="dot_txt">수강신청 인원이 미달될 경우 강좌가 폐강될 수 있으며, 수강료는 전액 환불됩니다.</p>
													<p class="dot_txt">회원정보에서 실제수강자명과 핸드폰 번호를 반드시 확인해주세요.</p>
													<p class="dot_txt">영유아 강좌는 아이와 보호자 1인만 참여 가능합니다.</p>
													<p class="dot_txt">강의 당일 무료주차를 위하여 개강 전일까지 회원정보수정 메뉴를 통해 차량번호 등록 바랍니다. (일부 점포 제외)</p>
													<p class="dot_txt">문의 : 해당 점 데스크 (운영시간 점별 상이)</p>
												</div>
											</div>
										</div>
									</div>
									<script type="text/javascript">
									  	$(".cont_wrap .mobile_acco_div .list_div .hide_con").each(function() {
									  		if($(this).html().trim() == "" || ($(this).find(".info_dot_txt").html() !== undefined && $(this).find(".info_dot_txt").html().trim() == "")) {
									  			$(this).closest(".list_div").hide();
									  		} 
									  	});
									</script>
								</div>
							</div>
							<div class="anchor_con review_box">
								<div class="sub_tit review_txt_div">
									<p class="f_h2">수강후기</p>
								</div>
								<div class="thum_list_w no_thum" id="listContainer">
									
								</div>

								<div class="more_btn_w" id="moreBtn" style="display:none;">
									<a href="javascript:" class="more_btn no_motion">
										<span>더보기 <strong class="total"></strong></span>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
      
<div class="layer_popup instructor_intro_pop">
    <div class="pop_wrap w800 full">
      <div class="pop_head">
        <p class="title">강사소개</p>
      </div>
      <div class="pop_cont">
        <div class="for_padding">
          <div class="scroll_area" id="teacherView">
            
          </div>
        </div>
      </div>
      <a class="btn_close" href="javascript:" title="닫기">
        <span class="blind"></span>
      </a>
    </div>
</div>  


<div class="layer_popup" id="waitPopup" data-pbl-yn="Y">
	<div class="pop_wrap w800 full">
		<div class="pop_head">
			<p class="title">대기접수 등록</p>
		</div>
		<div class="pop_cont">
			<div class="for_padding">
				<div class="scroll_area">
					<div class="sub_tit_area">
						<p class="pop_sec_tit">강좌정보</p>
					</div>
					<div class="info_box">
						<div class="con">
							<div class="label_div">
								<p class="label large border brchNm">${ classDtl.branch_nm }</p>
									</div>
							<p class="tit lectNm">${ classDtl.class_nm }</p>
							<div class="info_con">
								<div class="type_div">
									<p class="type lectSmsterNm">${ classDtl.smst_nm }</p>
									<p class="type tcNm">${ classDtl.name }</p>
								</div>
								<p class="time lectSt">${ lectTime }, 총 ${ classDtl.class_cnt }회 </p>
								<p class="sub_tit lectAmt2">강좌료 ${ fee }원
										</p>
								<p class="txt optionAmt"></p>
							</div>
						</div>
					</div>
					<div class="sub_inner">
						<div class="sub_tit_area">
							<p class="pop_sec_tit">실수강자</p>
						</div>
						<div class="form_table gray">
									<table>
										<caption>테이블 캡션 내용이 들어갑니다.</caption>
										<colgroup>
											<col width="17%">
											<col width="20%">
											<col width="20%">
											<col width="26%">
											<col width="17%">
										</colgroup>
										<thead>
											<tr>
												<th>선택</th>
												<th>이름</th>
												<th>관계</th>
												<th>생년월일</th>
												<th>성별</th>
											</tr>
										</thead>
										<tbody>
										
											<tr>
													<td>
														<div class="form_checkbox">
															<input type="checkbox" id="student0" name="student" data-kor-nm="유희진" data-fmly-rel-cd="00" data-bday="19970921" data-fmly-rel-cd-nm="본인" data-sex-cd="F">
															<label for="student0"></label>
														</div>
													</td>
													<td>
														<p class="f_body2">유희진</p>
													</td>
													<td>
														<p class="f_body2">본인</p>
													</td>
													<td>
														<p class="f_body2">1997.09.21</p>
													</td>
													<td>
														<p class="f_body2">여성</p>
													</td>
												</tr>
											<tr>
															<td>
																<div class="form_checkbox">
																	<input type="checkbox" id="student1" name="student" data-kor-nm="ddddd" data-fmly-rel-cd="02" data-bday="20150101" data-fmly-rel-cd-nm="자녀" data-sex-cd="M">
																	<label for="student1"></label>
																</div>
															</td>
															<td>
																<p class="f_body2">ddddd</p>
															</td>
															<td>
																<p class="f_body2">자녀</p>
															</td>
															<td>
																<p class="f_body2">2015.01.01</p>
															</td>
															<td>
																<p class="f_body2">남성</p>
															</td>
														</tr>
													<tr>
															<td>
																<div class="form_checkbox">
																	<input type="checkbox" id="student2" name="student" data-kor-nm="ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ" data-fmly-rel-cd="02" data-bday="20201023" data-fmly-rel-cd-nm="자녀" data-sex-cd="M">
																	<label for="student2"></label>
																</div>
															</td>
															<td>
																<p class="f_body2">ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ</p>
															</td>
															<td>
																<p class="f_body2">자녀</p>
															</td>
															<td>
																<p class="f_body2">2020.10.23</p>
															</td>
															<td>
																<p class="f_body2">남성</p>
															</td>
														</tr>
													</tbody>
									</table>
								</div>
							<div class="flex_btn_wrap">
							<a class="border_btn" href="javascript:$('#waitPopup .btn_close').click();">
								<span>취소하기</span>
							</a>
							<a class="b_color_btn" href="javascript:search.waitingApplBtn();">
								<span>대기신청하기</span>
							</a>
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

<div class="layer_popup" id="reviewPopup" style="display: none">
	<div class="pop_wrap w800 full">
		<div class="pop_head">
			<p class="title">수강후기상세</p>
			<!-- 2022-11-30 텍스트 수정 -->
		</div>
		<div class="pop_cont">
			<div class="for_padding">
				<div class="scroll_area">
					<div class="review_detail_con"></div>
					<div class="flex_btn_wrap">
						<a class="border_btn" href="javascript:$('#reviewPopup .btn_close').click();"> <span>닫기</span>
						</a>
					</div>
				</div>
			</div>
		</div>
		<a class="btn_close" href="javascript:" title="닫기"> <span class="blind"></span>
		</a>
	</div>
</div>
<input type="hidden" name="cst" value="${classDtl.class_st_id}">
<input type="hidden" name="optionList" value="${classDtl.optionList}">

<script>
	//강좌상태별 아이콘 색
	// 강좌상태별 버튼
	var cst = $('input[name=cst]').val(); // 강좌 상태 코드
	var className = "";
	var addText = "";
	var classText = "";

	if(cst == 1) {
		className = "black";
	}
	else if(cst == 2) {
		className = "lime";
		addText = "수강 신청하기";
		console.log(addText);
		classText = "sign_btn b_color_btn";
		$(".course_popup .total_sum_area .sign_btn span").text("수강신청");
	} else if(cst == 4) {
		className = "gray";
		addText = "대기 신청하기";
		classText = "sign_btn border_btn"
		$(".course_popup .total_sum_area .sign_btn span").text("대기신청");
	} else if(cst == "5") {
		className = "gray";
		addText = "접수마감";
		classText = "sign_btn b_color_btn disabled"
	} else if(cst == "3") {
		className = "light_gray";
		addText = "지점문의";
		classText = "sign_btn b_color_btn disabled"
	} else {
		className = "light_gray";
		$(".fixed_btn_area").hide();
	}
	$(".lectStatNm").addClass(className)
	$(".lectStatCd").text(addText);
	$(".lectStatCd").closest("a").attr("class", classText).closest(".fixed_btn_area").show();
	console.log($("#lectDetailSq").val());
	
</script>
<script type="text/javascript" src="/resources/common/js/application/search/searchView.js"></script>