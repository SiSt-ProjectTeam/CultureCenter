<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        		
<form id="reviewForm" data-page-type="dtl">
    <input type="hidden" id="pageIndex" name="pageIndex" value="1"/>
    <input type="hidden" id="listCnt" name="listCnt" value="10"/>
    <input type="hidden" id="initIndex" name="initIndex" value="1"/>
    
    <input type="hidden" name="brchCd" value="${reviewDTO.branch_id}" />
    <input type="hidden" name="yy" value="${reviewDTO.writing_year}" />
    <input type="hidden" name="lectSmsterCd" value="${reviewDTO.open_smst_id}" />
    <input type="hidden" name="lectCd" value="${reviewDTO.class_semester_sq}" />
    <input type="hidden" name="tcCdNo" value="${reviewDTO.teacher_sq}" />
    <input type="hidden" name="memberNo" value="${reviewDTO.member_sq}" />

    <input type="hidden" name="sortSeqno" value="" />
    <input type="hidden" name="cmntCont" value="" />
</form>

<div class="cont_inner no_pb">
    <div class="page_title_area only_mobile">
        <div class="inner">
            <div class="top_area">
                <a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a>
                <!-- 2022-11-23 추가 -->
                <div class="share_area only_mobile">
                    <a href="javascript:commonScript.openPopupFn('#sharePop');" class="share_btn" title="공유하기"></a>
                </div>
                <!-- // 2022-11-23 추가 -->
            </div>
        </div>
    </div>
    <div class="page_cont_area">
        <div class="inner">
            <div class="view_con_w">
                <div class="view_con review_con">
                    <div class="top_area">
                        <div class="type_div">
                            <p class="type">${reviewDTO.name}</p>
                            <p class="type">${reviewDTO.date_writingout_dt}</p>
                        </div>
                        <div class="share_area only_pc">
                            <!-- 2022-11-23 class 추가 -->
                            <a href="javascript:commonScript.openPopupFn('#sharePop');" class="share_btn" title="공유하기"></a>
                        </div>
                        <p class="title">${reviewDTO.review_title}</p>
                        <div class="star_rating">
                            <span class="star"></span>
                            <span class="star"></span>
                            <span class="star"></span>
                            <span class="star"></span>
                            <span class="star"></span>
                            </div>
                    </div>
                    <div class="content">
                        <div class="txt f_body2">
                            ${reviewDTO.review_content}<br> <!-- 2023-02-09 br추가, 링크추가-->
                            <a target="_blank" href="https://" class="link_txt f_caption2"></a>
                        </div>
                        <div class="thum_list_w">
                            <div class="thum_list_wrap">
                                <div class="thum_title f_h2">수강 정보</div>
                                <a href="/application/search/view.do?brchCd=0344&yy=2023&lectSmsterCd=3&lectCd=0591" class="thum_list" >
                                    <div class="thum_wrap">
                                        <div class="thum_box img_resize_w">
                                            <img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/7/202307220532033520.jpg" alt="20190121163307.jpg">
                                        </div>
                                    </div>
                                    <div class="txt_wrap">
                                        <div class="thum_left">
                                            <div class="label_div">
                                                <p class="label small light_gray">${reviewDTO.class_st}</p>
                                                <p class="label small border">${reviewDTO.lrclsctegery}</p>
                                                <p class="label small border">${reviewDTO.mdclsctegery}</p>
                                            </div>
                                            <p class="title limit_line_two">${reviewDTO.class_nm}</p>
                                        </div>
                                        <div class="thum_right">
                                            <div class="type_div">
                                                <p class="type f_caption2">${reviewDTO.branch_nm}</p>
                                                <p class="type f_caption2">${reviewDTO.teacher_nm}</p>
                                                <p class="type contour f_caption2">${reviewDTO.class_st}</p>
                                                <p class="type contour f_caption2">${reviewDTO.schedule_start_dt} ~ ${reviewDTO.schedule_end_dt}</p>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 2023-02-13 추가 -->
            <div class="flex_btn_wrap">
                <a class="border_btn" href="/community/review/list.do">
                    <span>목록으로</span>
                </a>
            </div>
            <!-- // 2023-02-13 추가 -->
            <div class="comment_wrap">
                <div class="comment_con">
                    <div class="title f_h2">댓글 <span id="totCnt" class="highlight"></span></div>
                    <div class="dot_txt_box">
                        <p class="dot_txt">광고, 욕설, 악의적 비방, 허위사실 기재 등의 내용 등록 시 관리자에 의해 삭제 될 수 있습니다.</p>
                        <p class="dot_txt">작성하신 수강후기는 1년간 보관 됩니다.</p>
                        <p class="dot_txt">고객님의 소중한 개인정보 보호를 위하여 별도 연락처 등의 기재를 삼가해주시기 바랍니다.</p>
                    </div>
                    <!-- pc일때는 input, btn 형식 -->
                    <div class="flex_input_wrap">
                        <div class="pc_form">
                            <div class="form_input">
                                <input type="text" placeholder="댓글을 입력해주세요" id="cmntCont1" onkeyup="reviewCtrl.registOnkeyup(this);">
                                <div class="input_btn_wrap">
                                    <button type="button" class="btn_delete" title="댓글 지우기"></button>
                                </div>
                            </div>
                            <a class="s_color_btn" href="javascript:void(0);" role="button" onclick="reviewCtrl.registCmnt();">
                                <span>등록</span>
                            </a>
                        </div>
                        <a class="b_color_btn mobile_form" href="javascript:void(0);" title="댓글작성 팝업 열기" onclick="reviewCtrl.openPopupCmnt();">
                            <span>댓글 작성</span>
                        </a>
                    </div>
                    <div class="review_box">
                        <div id="listContainer" class="review_list_w">

                            
                        </div>
                    </div>
                </div>
                <div class="more_comment_btn">
                    <a href="javascript:void(0);" class="f_btn" id="moreBtn">
                        <span>댓글 더보기</span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="cmntPopup" class="layer_popup write_comment" style="display:none">
    <div class="pop_wrap">
        <div class="pop_head">
            <p class="title">댓글 작성</p>
        </div>
        <div class="pop_cont">
            <div class="for_padding">
                <div class="scroll_area">
                    <div class="form_input">
                        <input type="text" placeholder="댓글을 입력해주세요" id="cmntCont2" onkeyup="reviewCtrl.registOnkeyup(this);">
                        <div class="input_btn_wrap">
                            <button type="button" class="btn_delete" title="댓글 지우기"></button>
                        </div>
                    </div>
                    <div class="flex_btn_wrap">
                        <a class="b_color_btn" href="javascript:void(0);" onclick="reviewCtrl.registCmnt();">
                        <span>등록하기</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <a class="btn_close" href="javascript:void(0);" title="닫기">
            <span class="blind"></span>
        </a>
    </div>
</div>


<script type="text/javascript" src="/resources/common/js/community/reviewController.js"></script>
			<!-- 관계사 사이트 팝업 -->


			<a href="/mypage/myreview/list.do" class="review_write">
					<span>
						<span class="icon"></span>
						<span class="txt">나의 수강후기</span>
					</span>
				</a>