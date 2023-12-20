<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
              <!-- total_price_info에 no_cost 클래스가 붙으면, 재료비/대여로 옵션이 해제된 경우입니다 -->
              <!-- total_price_info에 no_sale 클래스가 붙으면, 할인금액이 없는 경우입니다 -->
              <!-- total_price_info에 no_sale, no_cost 클래스가 붙으면, equal icon도 사라집니다 -->
              <div class="total_price_info no_cost">
                <div class="price_list">
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
                    <a href="javascript:" class="tit drop_down f_body3 no_data" title="총 할인금액 상세내역 열기">할인금액 합계 상세</a><!-- 2022-11-23 텍스트 수정 -->
                    </div>
                    <div class="txt txt_drop drop_type no_data">
                      <p class="red_txt f_body1 price">(-) 0원</p>
                      <a href="javascript:" class="drop_btn"></a>
                    </div>
                  </div>
                  <div class="hide_con_w">
                  	</div>
                </div>
                
                <div class="cost_icon plus">
                </div>
                <div class="price_list material">
                  <div class="txt_con">
                    <p class="tit f_body3">재료비/대여료 합계</p>
                    <p class="price f_body1">0원</p>
                  </div>
                  <p class="caution f_caption1">* 재료비 또는 대여료 옵션을 선택했을
                    경우 재료비/대여료에 표시 됩니다.</p>
                </div>
                <div class="cost_icon equal">
                </div>
                <div class="price_list total_price">
                  <div class="txt_con">
                    <p class="tit f_btn">총 결제금액</p>
                    <div class="pay_wrap">
                      <p class="pay f_h2">6,000<span class="unit">원</span></p>
                      <p class="txt f_body4">신용카드 : 6,000원</p>
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
              	<div class="cour_his_list">
              		  <!-- 수강 강좌 정보 -->
	                  <div class="cour_top_area">
	                    <div class="left">
	                      <div class="label_div">
	                        <p class="label small border">잠실점</p>
	                      </div>
	                      <p class="tit f_h2">[2/28] 베네피트와 함께하는 겨울철 모공케어 동안메이크업</p>
	                    </div>
	                    <div class="right">
	                      <ul class="txt_wrap">
	                        <li class="dl f_body2">
	                          <p class="dt only_pc">강사명</p>
	                          <p class="dd f_body1">잠실점</p>
	                        </li>
	                        <li class="dl f_body2">
	                          <p class="dt only_pc">학기명</p>
	                          <p class="dd f_body1">2023년 겨울학기</p>
	                        </li>
	                        <li class="dl f_body2">
	                          <p class="dt only_pc">강좌정보</p>
	                          <p class="dd f_body1">02 28 2024 ~ 2024.02.28 (수) 11:00~12:00 / 1회 </p>
	                        </li>
	                      </ul>
	                      <ul class="txt_wrap">
	                        <li class="dl f_body2">
	                          <p class="dt">강좌료<span class="colon">:</span></p>
	                          <p class="dd f_body1">3,000원</p>
	                        </li>
	                        </ul>
	                    </div>
	                  </div>
	                  <!-- 수강자정보 -->
	                  <!-- plural 클래스가 있으면, cour_detail이 2개이상 -->
	                  <div class="cour_detail_w plural">
	                  	<div class="cour_detail">
		                      <div class="left">
		                        <div class="tit f_body1">유희진(본인)</div>
		                      </div>
		                      <div class="right">
		                        <ul class="txt_wrap">
		                          <li class="f_body3">
		                            <div class="txt_con">
		                              <div class="tit">강좌료</div>
		                              <div class="txt">
		                                <p>3,000원</p>
		                              </div>
		                            </div>
		                          </li>
		                          <li class="optional f_body3">
		                            <div class="txt_con">
		                              <div class="tit">할인금액</div>
		                              <div class="txt">
		                                <div class="btn_box">
		                                  <p class="red_txt">(-)0원</p>
		                                </div>
		                              </div>
		                            </div>
		                            <div class="opt_name">
		                            	</div>
		                          </li>
		                          <li class="f_body3">
		                            <div class="txt_con">
		                              <div class="tit">재료비/대여료</div>
		                              <div class="txt">
		                                <p>0원</p>
		                              </div>
		                            </div>
		                            <div class="opt_name">
		                              <p></p>
		                            </div>
		                          </li>
		                          <li class="total_pay">
		                            <div class="txt_con">
		                              <div class="tit">주문금액</div><!-- 2022-12-09 텍스트 수정 -->
		                              <div class="txt">
		                                <p>3,000원</p>
		                              </div>
		                            </div>
		                          </li>
		                        </ul>
		                      </div>
		                    </div>
	                  	<div class="cour_detail">
		                      <div class="left">
		                        <div class="tit f_body1">ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ(자녀)</div>
		                      </div>
		                      <div class="right">
		                        <ul class="txt_wrap">
		                          <li class="f_body3">
		                            <div class="txt_con">
		                              <div class="tit">강좌료</div>
		                              <div class="txt">
		                                <p>3,000원</p>
		                              </div>
		                            </div>
		                          </li>
		                          <li class="optional f_body3">
		                            <div class="txt_con">
		                              <div class="tit">할인금액</div>
		                              <div class="txt">
		                                <div class="btn_box">
		                                  <p class="red_txt">(-)0원</p>
		                                </div>
		                              </div>
		                            </div>
		                            <div class="opt_name">
		                            	</div>
		                          </li>
		                          <li class="f_body3">
		                            <div class="txt_con">
		                              <div class="tit">재료비/대여료</div>
		                              <div class="txt">
		                                <p>0원</p>
		                              </div>
		                            </div>
		                            <div class="opt_name">
		                              <p></p>
		                            </div>
		                          </li>
		                          <li class="total_pay">
		                            <div class="txt_con">
		                              <div class="tit">주문금액</div><!-- 2022-12-09 텍스트 수정 -->
		                              <div class="txt">
		                                <p>3,000원</p>
		                              </div>
		                            </div>
		                          </li>
		                        </ul>
		                      </div>
		                    </div>
	                  	</div>
	                </div>
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