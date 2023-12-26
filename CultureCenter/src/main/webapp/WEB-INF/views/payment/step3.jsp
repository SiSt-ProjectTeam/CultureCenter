<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="cont_wrap">
  <div class="cont_inner no_pb">
    <div class="page_title_area">
      <div class="inner">
        <div class="top_area">
          <a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a>
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
          <!-- 상단 step 알림 -->
          <div class="process_con">
            <div class="process_wrap" title="3. 결제완료">
              <div class="process_div check two_law">
                <p class="num"><span>1</span></p>
                <p class="txt">수강정보 확인</p>
              </div>
              <div class="process_div check two_law">
                <p class="num"><span>2</span></p>
                <p class="txt">결제정보 확인</p>
              </div>
              <div class="process_div on">
                <p class="num"><span>3</span></p>
                <p class="txt">수강신청 완료</p>
              </div>
            </div>
            <div class="txt_box">
              <p class="tit f_h2">수강신청이 정상적으로 완료되었습니다.</p>
              <p class="txt f_body4">결제하신 강좌내역은 MY 문화센터 > 수강내역 조회에서<br class="only_pc">
                수강내역 조회 및 취소, 수강증을 확인하실 수 있습니다.</p>
            </div>
          </div>
          
          <div class="sub_inner_w">
            <!-- 총결제금액 -->
            <div class="sub_inner">
              <div class="sub_tit_area">
                <p class="f_h2">총 결제금액</p>
              </div>
              <c:set var="info" value="${list.get(0)}" /> 
              <c:if test="${info.tot_cnt != 0}">
              <script>
              	if(${info.tot_cnt}== -1) {
              		$("div.total_price_info").addClass("no_cost no_sale");
              	}else if(${info.tot_cnt}== -2){
              		$("div.total_price_info").addClass("no_sale");
              	}else if(${info.tot_cnt}== -3){
              		$("div.total_price_info").addClass("no_cost");
              	}
              </script>
              </c:if>
              <!-- total_price_info에 no_cost 클래스가 붙으면, 재료비/대여로 옵션이 해제된 경우입니다 -->
              <!-- total_price_info에 no_sale 클래스가 붙으면, 할인금액이 없는 경우입니다 -->
              <!-- total_price_info에 no_sale, no_cost 클래스가 붙으면, equal icon도 사라집니다 -->
              <div class="total_price_info">
              <fmt:formatNumber value="${info.class_fee}" pattern="#,##0" var="class_fee"/>
			  <fmt:formatNumber value="${info.ex_charge}" pattern="#,##0" var="ex_charge"/>
			  <fmt:formatNumber value="${info.class_fee + info.ex_charge}" pattern="#,##0" var="tot_fee"/>
			  <fmt:formatNumber value="${info.order_amt - info.total_amt}" pattern="#,##0" var="discount"/>
                <div class="price_list">
                  <div class="txt_con">
                    <p class="tit f_body3">강좌료 합계</p>
                    <p class="price f_body1">${class_fee}원</p>
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
                    <a href="javascript:" class="tit drop_down f_body3 no_data" title="총 할인금액 상세내역 열기">할인금액 합계 상세</a><!-- 2022-11-23 텍스트 수정 -->
                    </div>
                    <div class="txt txt_drop drop_type no_data">
                      <p class="red_txt f_body1 price">(-) ${discount}원</p>
                      <a href="javascript:" class="drop_btn"></a>
                    </div>
                  </div>
                  <div class="hide_con_w">
                  	</div>
                </div>
                
                <c:if test="${info.ex_charge != 0}">
                <div class="cost_icon plus">
                </div>
                <div class="price_list material">
                  <div class="txt_con">
                    <p class="tit f_body3">재료비/대여료 합계</p>
                    <p class="price f_body1">${ex_charge }원</p>
                  </div>
                  <p class="caution f_caption1">* 재료비 또는 대여료 옵션을 선택했을
                    경우 재료비/대여료에 표시 됩니다.</p>
                </div>
                </c:if>
                
                <div class="cost_icon equal">
                </div>
                <div class="price_list total_price">
                  <div class="txt_con">
                    <p class="tit f_btn">총 결제금액</p>
                    <div class="pay_wrap">
                      <p class="pay f_h2">${tot_fee}<span class="unit">원</span></p>
                      <p class="txt f_body4">신용카드 : ${tot_fee}원</p>
                      </div>
                  </div>
                </div>
              </div>
            </div>
            
            <!-- show_line 클래스가 sub_inner에 있으면 PC에서도 line이 보입니다  -->
            <div class="sub_inner show_line nobt_line"> <!-- 2022-12-12 클래스 추가-->
              <div class="sub_tit_area">
                <div class="left">
                  <p class="f_h2">수강신청 내역</p>
                </div>
              </div>
              <!-- 수강신청내역 -->
              <!-- 2022-12-16 디자인변경 구조수정-->
              <div class="course_history_w">
                <c:forEach items="${list}" var="dto" begin="1">
              	<div class="cour_his_list">
              		  <!-- 수강 강좌 정보 -->
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
		                          <p class="dd f_body1">${dto.class_schedule} </p>
	                        </li>
	                      </ul>
	                      <c:set value="${dto.personalList.size()}" var="size"/>
	                      <fmt:formatNumber value="${dto.class_fee}" pattern="#,##0" var="class_fee"/>
			  			  <fmt:formatNumber value="${dto.ex_charge}" pattern="#,##0" var="ex_charge"/>
			  			  <c:set value="${( (dto.class_fee + dto.ex_charge) *size  - dto.total_amt ) /size }" var="sale_amt"/>
			  			  <fmt:formatNumber value="${sale_amt }" pattern="#,##0" var="discount"/>
	                      <fmt:formatNumber value="${dto.class_fee +dto.ex_charge - sale_amt }" pattern="#,##0" var="tot_feediv"/>
	                      
	                      <ul class="txt_wrap">
	                        <li class="dl f_body2">
	                          <p class="dt">강좌료<span class="colon">:</span></p>
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
	                  <!-- 수강자정보 -->
	                  <!-- plural 클래스가 있으면, cour_detail이 2개이상 -->
	                  <c:if test="${size != 1}">
		         			<script>
		         			$(function() {
		         			      $("div.cour_detail_w#course${dto.detail_class_sq}").addClass("plural");
		         			});
		         			</script>
		              </c:if>
	                  <div class="cour_detail_w"  id="course${dto.detail_class_sq}">
	                  
	                  <c:forEach items="${dto.personalList}" var="mDto">
	                  	<div class="cour_detail">
		                      <div class="left">
		                          <div class="tit f_body1">${mDto.children_nm}</div>
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
		                          <li class="optional f_body3">
		                            <div class="txt_con">
		                              <div class="tit">할인금액</div>
		                              <div class="txt">
		                                <div class="btn_box">
		                                  <p class="red_txt">(-)${discount}원</p>
		                                </div>
		                              </div>
		                            </div>
		                            <div class="opt_name">
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
		                          <li class="total_pay">
		                            <div class="txt_con">
		                              <div class="tit">주문금액</div><!-- 2022-12-09 텍스트 수정 -->
		                              <div class="txt">
		                                <p>${tot_feediv}원</p>
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
              <!-- // 2022-12-16 디자인변경 구조수정-->
              
            </div>
          </div>
        </div>
   
        <div class="flex_btn_wrap margin_large">
          <a class="border_btn" href="/" role="button">
            <span>홈으로</span>
          </a>
          <a class="b_color_btn" href="/mypage/atlct/list.do" role="button">
            <span>수강내역 조회</span>
          </a>
        </div>
        
        <!-- pop_drop을 누르면 open_pop이 열립니다 -->
        <!-- 2022-12-15 구조수정-->
        <div class="white_inner open_pop">
          <div class="sub_inner no_mt">
            <p class="big_title f_h2 only_pc">할인금액 합계 상세</p><!-- 2022-11-23 텍스트 수정 -->
            
            </div>
          <a class="btn_close" href="javascript:" title="닫기">
            <span class="blind"></span>
          </a>
        </div>
        <!-- // 2022-12-15 구조수정-->
      </div>
    </div>
  </div>
</div>

<script type="text/javascript" src="/resources/common/js/payment/payment.js"></script>