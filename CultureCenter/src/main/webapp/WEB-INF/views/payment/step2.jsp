<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>	
<div class="cont_wrap">
  <div class="cont_inner no_pb">
    <div class="page_title_area">
      <div class="inner">
        <div class="top_area">
          <div class="tit_div">
            <p class="tit f_h1">수강결제</p>
          </div>
        </div>
      </div>
      <div class="m_pop_dimd"></div>
    </div>

    <div class="page_cont_area">
      <div class="inner pay_info">
        <div class="pay_step">
          <div class="process_wrap" title="2. 결제정보 확인">
            <div class="process_div check two_law">
              <p class="num"><span>1</span></p>
              <p class="txt">수강정보 확인</p>
            </div>
            <div class="process_div on">
              <p class="num"><span>2</span></p>
              <p class="txt">결제정보 확인</p>
            </div>
            <div class="process_div">
              <p class="num"><span>3</span></p>
              <p class="txt">수강신청 완료</p>
            </div>
          </div>
          <div class="sub_inner_w">
            <div class="sub_inner">
              <div class="sub_tit_area">
                <div class="left">
                  <p class="f_h2">수강자 정보</p>
                  <a href="/payment/payment_step3.do">step3 이동</a>
                </div>
              </div>
              
              <!-- 수강정보 -->
              <div class="course_history_w" data-brch-cd="${branchCd}" data-atlct-type="${atlctType}">
              <c:forEach items="${list}" var="payList">
             	<c:set var="dto" value="${payList.dto}"/>
             	<fmt:formatNumber value="${dto.class_fee}" pattern="#,##0" var="class_fee"/>
			  	<fmt:formatNumber value="${dto.ex_charge}" pattern="#,##0" var="ex_charge"/>
			  	<fmt:formatNumber value="${dto.class_fee + dto.ex_charge}" pattern="#,##0" var="tot_fee"/>
              	<div class="cour_his_list"  data-brch-cd="${dto.branch_id}"
					data-yy="${dto.open_year}" data-lect-smster-cd="${dto.open_smst_id}"
					data-lect-cd="${dto.detail_class_sq}" 
					 data-lect-amt="${dto.class_fee}"
					data-lect-tp-cd="${payList.lectTpCd}" 
              		data-optn-seqno="" data-optn-amt="${dto.ex_charge}"
              		data-pbl-pmprcust-parnt-brch-cd="" 
              		data-pbl-pmprcust-parnt-lect-cd="" 
              		data-pbl-pmprcust-parnt-brch-cd-nm="">
              		<div class="cour_top_area">
		                    <div class="left">
		                      <div class="label_div">
		                       <p class="label small border">${dto.branch_nm}</p>
							</div>
								<p class="tit f_h2">${dto.class_nm}</p>
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
		                          <p class="dd f_body1">
									${dto.schedule_start_dt} ~ ${dto.schedule_end_dt} (${dto.day}) 
									${dto.start_time}~${dto.end_time}
									/ ${dto.class_cnt}회
								  </p>
		                        </li>
		                      </ul>
		                      <ul class="txt_wrap">
								<li class="dl f_body2">
									<p class="dt">강좌료</p>
									<p class="dd f_body1">${class_fee}원</p>
								</li>
								<c:if test="${dto.ex_charge != 0}">
									<li class="dl f_body2">
										<p class="dt">재료비/대여료</p>
										<p class="dd f_body1">${ex_charge}원</p>
									</li>
								</c:if>
							  </ul>
		                    </div>
		                </div>
		                <!-- plural 클래스가 있으면, cour_detail이 2개이상 -->
		         		<c:if test="${payList.arrActlAtlctNple.size() != 1}">
		         			<script>
		         			$(function() {
		         			      $("div.cour_detail_w#course${dto.detail_class_sq}").addClass("plural");
		         			});
		         			</script>
		         		</c:if>
		            <div class="cour_detail_w" id="course${dto.detail_class_sq}">
		                <c:forEach items="${payList.arrActlAtlctNple}" var="mDto">
		                	<div class="cour_detail"  
			                	data-actl-atlct-nple-nm="${mDto.actlAtlctNpleNm}" 
			                	data-fmly-rel-cd="${mDto.fmlyRelCd}" 
			                	data-fmly-rel-cd-nm="${mDto.fmlyRelCdNm}" 
			                	data-bday="${mDto.bday}" 
			                	data-sex-cd="${mDto.sexCd}">
		                		<div class="left">
				                    <div class="tit f_body1">${mDto.actlAtlctNpleNm}(${mDto.fmlyRelCdNm})</div>
				                </div>
				                   <div class="right">
				                      <ul class="txt_wrap">
				                         <li class="f_body3">
				                           <div class="txt_con">
				                             <div class="tit">강좌료</div>
				                             <div class="txt">
				                               <p>${class_fee}원</p>
				                             </div>
				                           </div>
				                         </li>
				                       <c:if test="${dto.ex_charge != 0}">
				                         <li class="f_body3">
				                        	<div class="txt_con">
				                             <div class="tit">재료비/대여료</div>
				                             <div class="txt">
				                               <p>${ex_charge}원</p>
				                             </div>
				                           </div>
									     </li>
									   </c:if>
			                             <li class="optional f_body3">
			                               <div class="txt_con">
			                                 <div class="tit">할인금액</div>
			                                 <div class="txt">
			                                   <p class="red_txt">(-)0원</p>
			                                 </div>
			                               </div>
			                               <div class="opt_name">
			                               </div>
			                               <div class="flex_btn_wrap">
			                                 <a style="background-color:#e0f55c;" 
			                                 class="border_btn dcBtn disabled" 
			                                 href="javascript:" role="button" 
	                                       	  data-brch-cd="0002" data-yy="2023" 
	                                       	  data-lect-smster-cd="4" data-lect-cd="0547" 
	                                       	  data-lrcls-ctegry-cd="01" 
	                                       	  data-mdcls-ctegry-cd="0105" 
	                                       	  data-smcls-ctegry-cd="010502"
	                                       	  data-lect-cl-cd="3" 
	                                       	  data-lect-amt="3000" 
	                                       	  data-optn-amt="0">
	                                           <span style="color:#000;">할인수단 선택</span>
	                                         </a>
			                               </div>
			                             </li>
			                             <li class="total_pay">
				                           <div class="txt_con">
				                             <div class="tit">결제예정 금액</div>
				                             <div class="txt">
				                               <p>${tot_fee}원</p>
				                             </div>
				                           </div>
				                         </li>
				                   	  </ul> 
				            	   </div>
		                	</div>
		                </c:forEach>
		            </div>
              	</div>
              </c:forEach>
              </div>
           </div>
            
        	<!-- 엘포인트 조회 및 포인트 입력 시 -->
            <div class="sub_inner show_line">
              <div class="sub_tit_area">
                <p class="f_h2">할인혜택</p>
              </div>
              <div class="payment_methods">
                <div class="left">
                  <div class="flex_box">
                    <p class="f_body1">L.POINT</p>
                    <div class="point_txt_area">
                   		<a href="javascript:" class="arrow_btn" onclick="payment.getRemainLPoint();"><span>조회하기</span></a>
                      	<p class="point_txt" style="display:none;">보유 : <span class="remaining"></span>점</p>
                    </div>
                  </div>
                  <div class="flex_input_wrap">
                    <div class="form_input">
                      <input type="text" id="lpntUseAmt" name="lpntUseAmt" placeholder="0점" oninput="payment.inputLpoint(this)" onfocusout="payment.focusoutLpoint(this)" autocomplete="off" disabled>
                    </div>
                    <a class="s_color_btn" id="calcBtn" href="javascript:" role="button" onclick="payment.calcPay();" >
                      <span>사용</span>
                    </a>
                    <a class="s_color_btn" id="cnclBtn" href="javascript:" role="button" onclick="payment.cnclPay();" style="display:none;">
                      <span>취소</span>
                    </a>
                  </div>
                  <p class="info_txt">포인트 사용은 10점 단위로 사용 가능합니다.</p>
                </div>
              </div>
            </div>
            <!-- // 2023-02-13 추가 -->
            
            
            <!-- show_line 클래스가 sub_inner에 있으면 PC에서도 line이 보입니다  -->
            <div class="sub_inner nobt_line">
              <div class="sub_tit_area">
                <p class="f_h2">총 결제금액</p>
              </div>
              <!-- total_price_info에 no_cost 클래스가 붙으면, 재료비/대여로 옵션이 해제된 경우입니다 -->
              <!-- total_price_info에 no_sale 클래스가 붙으면, 할인금액이 없는 경우입니다 -->
              <div class="total_price_info no_cost "><!-- no_sale -->
                <div class="price_list before_sales">
                  <div class="txt_con">
                    <p class="tit f_body3">강좌료 합계</p>
                    <p class="price f_body1">6,000원</p>
                  </div>
                  <p class="caution f_caption1">* 재료비 또는 대여료 옵션 금액을 제외한 원 강좌료 금액이 표시 됩니다.</p>
                </div>
                <!-- cost_icon에 plus, minus, equal의 각각 이미지가 들어갑니다 -->
                <div class="cost_icon minus">
                </div>
                <div class="price_list sales">
                  <div class="txt_con">
                    <!-- pop_drop 부분 클릭시 pc에서만 팝업창 오픈  -->
                    <div class="pop_drop">
                      <a href="javascript:" class="tit f_body3 sale_drop no_data" title="할인금액 합계 상세 열기">할인금액 합계</a><!-- drop_down -->
                    </div>
                    <div class="txt txt_drop drop_type no_data">
                      <p class="red_txt f_body1 price">(-) 0원</p>
                      <a href="javascript:" class="drop_btn"></a>
                    </div>
                  </div>
                  <div class="hide_con_w">
                    <div class="hide_con" style="display:none;">
                      <ul class="price_wrap">
                        <li class="f_body4">
                          <p class="name">할인</p>
                          <div class="price">-원</div>
                        </li>
                        <li class="f_caption2 detail">
                          <p class="name">적용 된 할인금액이 없습니다.</p>
                          <div class="price"> -원</div>
                        </li>
                      </ul>
                    </div>
                    <div class="hide_con" style="display:none;">
                      <ul class="price_wrap">
                        <li class="f_body4">
                          <p class="name">쿠폰할인</p>
                          <div class="price">-원</div>
                        </li>
                        <li class="f_caption2 detail">
                          <p class="name">적용 된 할인금액이 없습니다.</p>
                          <div class="price"> -원</div>
                        </li>
                      </ul>
                    </div>
                    <div class="hide_con" style="display:none;">
                      <ul class="price_wrap">
                        <li class="f_body4">
                          <p class="name">할인혜택</p>
                          <div class="price">-원</div>
                        </li>
                        <li class="f_caption2 detail">
                          <p class="name">적용 된 할인금액이 없습니다.</p>
                          <div class="price"> -원</div>
                        </li>
                      </ul>
                    </div>
                  </div>
                  <p class="caution f_caption1">* 강좌료에서 할인받은 할인금액이 표시 됩니다.</p>
                </div>
                <div class="cost_icon plus">
                </div>
                <div class="price_list material">
                  <div class="txt_con">
                    <p class="tit f_body3">재료비/대여료 합계</p>
                    <p class="price f_body1">0원</p>
                  </div>
                  <p class="caution f_caption1">* 재료비 또는 대여료 옵션을 선택했을 경우 재료비/대여료에 표시 됩니다.</p>
                </div>
                <div class="cost_icon equal">
                </div>
                <div class="price_list total_price">
                  <div class="txt_con">
                    <p class="tit f_btn">총 결제금액</p>
                    <div class="pay_wrap">
                      <p class="pay f_h2">6,000<span class="unit">원</span></p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="sub_inner nobt_line mthd_inner"> <!-- 2022-11-28 클래스 추가 --> <!-- 2022-12-12 클래스명 변경-->
              <div class="sub_tit_area" >
                <p class="f_h2">결제수단</p>
              </div>
              <div class="payment_methods" >
                <div class="right">
                  <!-- <p class="f_body1 only_pc">결제</p> -->
                  <div class="form_radio ">
                    <input type="radio" id="credit" name="" checked>
                    <label for="credit">신용카드</label>
                  </div>
                  <p class="dot_txt">바우처카드(롯데 아이행복카드, 국민 행복카드, BC 청년 디딤돌 카드, 디딤돌바우처카드 등)는
                    카드사 정책에 따라 다수강좌 묶음결제 시 추후 부분 취소가 불가합니다.</p>
                  <p class="dot_txt">충전식카드(복지카드, 포인트카드, 기프트카드 등)로 결제 시 추후 카드의 유효기간이 만료된 후에는 환불 처리가 불가합니다.</p>
                </div>
              </div>
              <div class="dot_txt_box">
                <p class="f_body1">유의사항 안내</p>
                <p class="dot_txt">상기 환불 기준은 정기강좌 기준이며, 1日 강좌는 개강 3일 전까지 환불 가능합니다.</p>
                <p class="dot_txt">재료비가 있는 강좌의 경우 최소 3일 이전까지만 전액 환불 가능합니다.</p>
                <p class="dot_txt">강좌의 환불은 학기 개강일 이전 전액 환불 및 변경이 가능하며, 다음학기로 강좌 연기는 불가능합니다.</p>
                <p class="dot_txt">또한 강좌 개시일 이후 본인 사유에 의한 환불의 경우 소비자 분쟁해결기준(공정거래위원회 고시 제 2008-3호)에 의거하여 환불해 드립니다.
                </p>
                <!-- 2022-11-24 텍스트 수정 -->
                <div class="dot_txt">
                  <p>1개월 이내 강좌</p>
                  <p>‣ 수강시간 1/3 경과 전 환불 시 수강료 2/3 환급</p>
                  <p>‣ 수강시간 1/2 경과 전 환불 시 수강료 1/2 환급</p>
                  <p>‣ 수강시간 1/2 경과 후 수강료 미 환급</p>
                </div>
                <!-- // 2022-11-24 텍스트 수정 -->
                <div class="dot_txt">
                  <p>1개월 초과 강좌</p>
                  <p>‣ 1개월 이내 강좌 기준 적용 + 잔여월 수강료 전액 환급</p>
                </div>
              </div>
              <!-- 2023-03-08 추가 -->
              <div class="pay_agree_con_w">
              	<!-- 2023-03-17 수정 -->
                <div class="form_checkbox">
                  <input type="checkbox" id="argee1" name="">
                  <label for="argee1">주문내역 확인 동의 <a href="javascript:" class="view_btn" onclick="payment.openTermsPopup(this)">보기</a></label>
                </div>
<!--                 <div class="pay_agree_con"> -->
<!--                   <p class="tit">주문내역 확인 동의 <a href="javascript:" onclick="payment.openTermsPopup(this)">보기</a></p> -->
<!--                 </div> -->
<!--                 <div class="pay_agree_con"> -->
<!--                   <p class="tit">결제서비스 이용약관 동의</p> -->
<!--                   <p class="sub_tit">전자금융거래 이용약관 <a href="javascript:">보기</a></p> -->
<!--                   <p class="sub_tit">개인정보 수집 및 이용동의 <a href="javascript:">보기</a></p> -->
<!--                   <p class="sub_tit">개인정보 제공 및 위탁동의 <a href="javascript:">보기</a></p> -->
<!--                 </div> -->
              </div>
            </div>
          </div>
        </div>
        <!-- pop_drop을 누르면 open_pop이 열립니다 -->
        <div class="white_inner open_pop">
          <div class="sub_inner no_mt">
            <p class="big_title f_h2 only_pc">할인금액 합계 상세</p><!-- 2022-11-23 텍스트 수정 -->
            <div class="gray_cash_box grde_box">
              <div class="row">
                <div class="left">
                  <p class="txt f_body4">할인</p>
                </div>
                <div class="right">
                  <p class="txt f_body4">-원</p>
                </div>
              </div>
              <div class="row discount_low">
                <div class="left">
                  <p class="txt f_caption2">적용 된 할인금액이 없습니다.</p><!-- 2022-11-23 텍스트 수정 -->
                </div>
                <div class="right">
                  <p class="txt f_caption2">-원</p>
                </div>
              </div>
            </div>
            <div class="gray_cash_box cpn_box">
              <div class="row">
                <div class="left">
                  <p class="txt f_body4">쿠폰할인</p>
                </div>
                <div class="right">
                  <p class="txt f_body4">-원</p>
                </div>
              </div>
              <div class="row discount_low">
                <div class="left">
                  <p class="txt f_caption2">적용 된 할인금액이 없습니다.</p>
                </div>
                <div class="right">
                  <p class="txt f_caption2">-원</p>
                </div>
              </div>
            </div>
            <div class="gray_cash_box lpnt_box">
              <div class="row">
                <div class="left">
                  <p class="txt f_body4">할인혜택</p>
                </div>
                <div class="right">
                  <p class="red_txt f_body4">-원</p>
                </div>
              </div>
              <div class="row stair_low">
                <div class="left">
                  <p class="txt f_caption2">적용 된 할인금액이 없습니다.</p>
                </div>
                <div class="right">
                  <p class="txt f_caption2">-원</p>
                </div>
              </div>
            </div>
          </div>
          <a class="btn_close" href="javascript:" title="닫기">
            <span class="blind"></span>
          </a>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- payment_step클래스가 있으면 버튼2개폼 -->
<div class="payment_bar payment_step">
  <!-- 2022-11-25 구조 수정 -->
  <div class="payment_w">
    <div class="inner">
      <div class="terms_for_msg hide">
        <div class="msg_div f_body2">
          <div class="txt">
            신청한 강좌의 세부 정보를 최종 확인하였으며, 
<div>결제에 동의합니다.(전자상거래법 제 8조 제2항)</div></div>
        </div>
      </div>
      <div class="payment_con">
        <!-- <div class="txt_box">
          <div class="form_checkbox">
            <input type="checkbox" id="argee1" name="">
            <label for="argee1">위 주문내용을 확인하였으며, 구매동의 합니다.</label>
          </div>
          <a href="javascript:" class="dropdown_btn"><span></span></a>
        </div> -->
        <div class="txt_box">
          <div class="form_checkbox agree_chk_txt">
            <input type="checkbox" id="argeeBtn1" name="">
            <label for="argeeBtn1">위 내용을 모두 확인하였으며, 결제에 동의합니다.</label>
          </div>
        </div>
        <div class="flex_btn_wrap">
        	<a class="border_btn" href="javascript:" onclick="history.back();">
        		<span>이전</span>
			</a>
			     <!-- href="javascript:" onclick="requestPay();" -->
          <a id="totLectStlmAmt" class="b_color_btn" 
	       	  href="javascript:" onclick="payment.submitStep2Frm()"
	          data-tot-lect-stlm-amt=""  
	          data-lpnt-use-amt="0" 
	          data-tot-grde-dc-amt="0" 
	          data-tot-cpn-dc-amt="0" 
	          data-gs-lect-amt="" data-gs-add-amt="0">
            <span>6,000원 결제</span>
          </a>
          <script>
          $(function(){
        	  payment.payment_tot2();
          });
          </script>
        </div>
      </div>
    </div>
  </div>
  <!-- // 2022-11-25 구조 수정 -->
</div>

<div id="lpointOnlnCertPopup" class="layer_popup" style="display:none;" data-use-point-yn="N" data-onln-cert-yn="N">
  <div class="pop_wrap w800 full">
    <div class="pop_head">
      <p class="title">L.POINT 온라인 사용인증 서비스</p>
    </div>
    <div class="pop_cont">
      <div class="for_padding">
        <div class="scroll_area">
          <p class="notice_txt f_body2">안전한 L.POINT 결제를 위해 고객님의 포인트 카드 정보를 확인합니다. </p>
          <div class="sub_inner">
            <div class="data_input_wrap">
              <div class="row">
                <div class="th">
                  <p class="tit f_body1">L.POINT 비밀번호</p>
                </div>
                <div class="td">
                  <div class="form_input">
                    <input type="password" id="strPasswd" placeholder="6~8자리 입력">
                    <div class="input_btn_wrap">
                      <button type="button" class="btn_delete" title="L.POINT 비밀번호 지우기"></button>
                    </div>
                  </div>
                  <p class="input_arrow_btn">
                    <a href="https://www.lpoint.com/app/point/LHPG100200.do" class="arrow_btn"
                      target="_blank"><span>L.POINT 비밀번호 설정</span></a>
                  </p>
                </div>
              </div>
            </div>
            <div class="dot_txt_box">
              <p class="dot_txt">L.POINT 비밀번호 입력란에는 소유하고 계신 L.POINT 카드 비밀번호를 입력해 주시기 바랍니다.</p>
              <p class="dot_txt">L.POINT 비밀번호는 롯데카드(신용카드) 비밀번호 (4자리)와 별도로 영 / 숫자 혼합 6~8자리입니다.</p>
              <p class="dot_txt">5회 이상 비밀번호 입력 오류 시, 새로운 비밀번호를 등록해 주셔야 합니다.</p>
            </div>
            <div class="flex_btn_wrap">
              <a class="border_btn" href="javascript:" onclick="payment.closePopup(this)">
                <span>취소</span>
              </a>
              <a class="b_color_btn" href="javascript:" onclick="payment.validateLPointPwd(this)">
                <span>확인</span>
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

<div id="pgPopup" class="layer_popup" style="display:none;">
	<div class="pop_wrap w800 full">
		<div class="pop_head">
			<p class="title">NICE PAY</p>
		</div>
		<div class="pop_cont">
			<div class="for_padding">
				<div class="scroll_area">
		        	<!-- iframe -->
		        	<div class="pay_img">
		        	
		        	</div>
				</div>
			</div>
		</div>
		<a class="btn_close" href="javascript:payment.nicepayClose()" title="닫기">
			<span class="blind"></span>
		</a>
	</div>
</div>

<div id="termsPopup" class="layer_popup" style="display:none;">
	<div class="pop_wrap">
		<div class="pop_head">
			<p class="title">주문내역 확인 동의</p>
		</div>
		<div class="pop_cont">
			<div class="for_padding">
				<div class="scroll_area">
					<div class="txt_con">
						<!-- <div class="info_dot_txt">
							<p class="dot_txt">일부 상품에 한하여 적용 가능합니다</p>
							<p class="dot_txt">쿠폰끼리는 중복 적용이 불가합니다. 포인트 및 간편 결제 청구 할인과는 중복사용 가능합니다</p>
							<p class="dot_txt">본 쿠폰은 당사 사정에 따라 사전 고지 없이 변경 또는 조기 종료될 수 있습니다.</p>
							<p class="dot_txt">주문 취소로 인한 최소 구매 금액 조건 미달 시, 쿠폰 할인 금액 차감 후 환불 처리됩니다.</p>
							<p class="dot_txt">해당 쿠폰은 사용한 후 재발급 받아 반복 사용이 가능합니다.</p>
						</div> -->
						신청한 강좌의 세부 정보를 최종 확인하였으며, 
<div>결제에 동의합니다.(전자상거래법 제 8조 제2항)</div></div>
				</div>
			</div>
		</div>
		<a class="btn_close" href="javascript:" title="닫기">
			<span class="blind"></span>
		</a>
	</div>
</div>
<!-- 결제금액 0원 일때 -->
<form id="frm_zeropay" name="frm_zeropay" method="POST" action="/payment/zeroPaymentResult.do">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" name="atlctRsvNo" value="" />
	<input type="hidden" name="lpntUseAmt" value="" />
	<input type="hidden" name="strPasswd" value="" />
	<input type="hidden" name="strCardNo" value="" />
	<input type="hidden" name="brchCd" value="" />
	<input type="hidden" name="strCoprMemstrNo" value="" />
	<input type="hidden" name="gsStlmAmt" value="" />
</form>
<!-- 결제창 input data -->
<form id="frm_temp" name="frm_temp" method="POST" action="/payment/payment_request.do" target="pgIframe">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" name="atlctRsvNo" value="" />
	<input type="hidden" name="lpntUseAmt" value="" />
	<input type="hidden" name="strPasswd" value="" />
	<input type="hidden" name="strCardNo" value="" />
	<input type="hidden" name="brchCd" value="" />
	<input type="hidden" name="strCoprMemstrNo" value="" />
	<input type="hidden" name="crdStlmAmt" value="" />
	
	<input type="hidden" name="goodsName" value="" />
	<input type="hidden" name="merchantID" value="" />
	<input type="hidden" name="moid" value="" />
	<input type="hidden" name="buyerName" value="" />
	<input type="hidden" name="pgMctKeyVal" value="" />
	<input type="hidden" name="mbrNo" value="" />
	<input type="hidden" name="mbrGrdeCd" value="" />
	<input type="hidden" name="mvgBlstrCd" value="" />
	<input type="hidden" name="mbrId" value="" />
</form>
<!-- step3 form -->
<c:if test="${not empty html}">
${html}
</c:if>
<form id="frm_success" name="frm_success" method="POST" action="/payment/payment_step3.do">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" name="atlctRsvNo" value="" />
</form>

<script type="text/javascript" src="/resources/common/js/payment/payment.js"></script>