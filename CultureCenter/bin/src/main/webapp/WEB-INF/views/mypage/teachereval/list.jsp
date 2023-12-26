<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="teacherEvalForm" data-page-type="list">
    <input type="hidden" id="pageIndex" name="pageIndex" value="1"/>
    <input type="hidden" id="listCnt" name="listCnt" value="10"/>
    <input type="hidden" id="initIndex" name="initIndex" value="1"/>

    <div class="cont_wrap">
        <div class="cont_inner no_pb">
            <div class="page_title_area">
                <div class="inner">
                    <div class="top_area">
                        <a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a>
                        <a href="javascript:" class="tit_div arrow" title="페이지 이동 팝업 열기">
                            <p class="tit f_h1">만족도 평가 <span class="more_tit"></span></p>
                        </a>
                        <div class="tit_popup">
                            <div class="pop_wrap">
                                <div class="pop_cont">
                                    <div class="for_padding">
                                        <div class="scroll_area">
                                            <div class="branch">
    	<a class="" href="/mypage/member/info.do"><p class="f_desc">회원정보변경</p></a>
       	<a class="" href="/mypage/atlct/list.do"><p class="f_desc">수강내역 조회</p></a>
       	<a class="" href="/mypage/waiting/list.do"><p class="f_desc">대기자 조회</p></a>
       	<a class="" href="/mypage/freebie/appList.do"><p class="f_desc">사은품 신청</p></a>
       	<a class="" href="/mypage/freebie/detailList.do"><p class="f_desc">사은품 신청내역</p></a>
       	<a class="" href="/mypage/coupon/list.do"><p class="f_desc">나의 쿠폰</p></a>
       	<a class="" href="/mypage/myreview/list.do"><p class="f_desc">나의 수강후기</p></a>
       	<a class="active" href="/mypage/teachereval/list.do"><p class="f_desc">만족도 평가</p></a>
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
                <div class="inner">
                    <div class="satisfy_w">
                        <div class="dot_txt_box">
                            <p class="dot_txt">전반적인 강좌에 대한 별점과 한줄평을 남기실 수 있습니다.</p>
                            <p class="dot_txt">남겨주신 별점과 한줄평은 외부에 노출되지 않으며, 추후 강좌기획 시 참고용으로 활용될 예정입니다.</p>
                        </div>

                        <div class="filter_bar_area no_padding">
                            <div class="fixed_mobile_w">
                                <div class="fixed_mobile">
                                    <div class="filter_bar_div">
                                        <div class="left">
                                            <p class="f_caption2">전체 <span id="totCnt" class="point"></span></p>
                                        </div>
                                        </div>
                                </div>
                            </div>
                        </div>

                        <div id="listContainer" class="thum_list_w">
                            <div class="thum_list_w" data-tot-cnt="0">
					            <div class="no_srch_area no_pb">
					                <div class="no_srch_div">
					                <p class="txt f_h2"><span class="normal_value">신청강좌가 없습니다.</span></p>
					                </div>
					            </div>
					        </div>
                        </div>
                        <div id="moreBtn" class="more_btn_w" style="display:none;">
                            <a href="javascript:" class="more_btn no_motion">
                                <span>더보기 <strong class="total"></strong></span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<div id="insertPopup" class="layer_popup register" style="display:none">
    
</div>

<script type="text/javascript" src="/resources/common/js/teacher/teacherCommon.js"></script>
<script type="text/javascript" src="/resources/common/js/mypage/teacherEvalController.js"></script>