<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ko">
	<!-- head 부분 -->
        
	<form id="reviewForm" data-page-type="list">
    <input type="hidden" id="pageIndex" name="pageIndex" value="1"/>
    <input type="hidden" id="listCnt" name="listCnt" value="5"/>
    <input type="hidden" id="initIndex" name="initIndex" value="1"/>
    <input type="hidden" name="q" value="">
    <input type="hidden" name="orderSet" value="" />
    <input type="hidden" name="brchCd" value="" />
</form>

<div class="cont_wrap">
    <div class="cont_inner no_pb">
        <!-- 2022-11-23 구조 수정 -->
        <div class="page_title_area">
            <div class="inner">
                <div class="top_area">
                    <a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a>
                    <a href="javascript:" class="tit_div arrow" title="페이지 이동 팝업 열기">
                        <p class="tit f_h1">수강후기<span class="more_tit"></span></p>
                    </a>
                    <div class="tit_popup">
                        <div class="pop_wrap">
                            <div class="pop_cont">
                                <div class="for_padding">
                                    <div class="scroll_area">
                                        <div class="branch">
		<a class="" href="/community/notice/list.do"><p class="f_desc">공지사항/이벤트</p></a>	
			<a class="active" href="/community/review/list.do"><p class="f_desc">수강후기</p></a>	
			<a class="" href="/community/magazine/list.do"><p class="f_desc">LIFESTYLE LAB 매거진</p></a>	
			</div></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form_search">
                <div class="form_search_div">
                    <input type="text" placeholder="제목과 내용으로 검색하세요" title="제목이나 내용 입력" id="q" onkeyup="reviewCtrl.queryOnkeyup();">
                    <div class="input_btn_wrap">
                        <button type="button" class="btn_delete" title="지우기"></button>
                        <button type="button" class="btn_search" title="검색" onclick="reviewCtrl.search();"></button>
                    </div>
                </div>
            </div>
            <div class="m_pop_dimd"></div>
        </div>
        <!-- // 2022-11-23 구조 수정 -->

        <div class="page_cont_area">
            <div class="inner">
                <div class="course_review_slide_w">
                        <div class="course_review_slide">
                            <div class="swiper-container">
                                <div class="swiper-wrapper">
                                    <div class="swiper-slide card_list_v">
                                            <a href="javascript:" class="lec_list" onclick="reviewCtrl.detail(this);"
                                                data-brch-cd="0333"
                                                data-yy="2023"
                                                data-lect-smster-cd="1"
                                                data-lect-cd="2981"
                                                data-tc-cd-no="008610"
                                                data-mbr-no="801961834           " >
                                                <div class="small_label wide HOT">
                                                    </div>
                                                <div class="img_resize_w img">
                                                    <img src="https://culture.lotteshopping.com/files/CUL_ONL/OLD/COMMON/IMAGES/LECT_IMG/54/2981/20230308172728.jpg" alt="20230308172728.jpg" />
                                                </div>
                                                <div class="con">
                                                    <div class="tag_area">
                                                        <div class="label_div">
                                                            <p class="label small black_gray">광복점</p>
                                                        </div>
                                                        <div class="star_rating">
                                                            <span class="star"></span>
                                                            <span class="star"></span>
                                                            <span class="star"></span>
                                                            <span class="star"></span>
                                                            <span class="star"></span>
                                                            </div>
                                                    </div>
                                                    <p class="tit limit_line">마수진선생님과 함께하는 수업 최고예요!</p>
                                                    <p class="sub_tit limit_line">[봄학기]토요 리틀잼 놀이잼 오감놀이터[13~19개월]</p>
                                                    <div class="type_div">
                                                        <p class="type f_caption2">김*정</p>
                                                        <p class="type f_caption2">2023.03.27</p>
                                                        <p class="comment_num f_caption2">0</p>
                                                    </div>
                                                </div>
                                            </a>
                                        </div>
                                    <div class="swiper-slide card_list_v">
                                            <a href="javascript:" class="lec_list" onclick="reviewCtrl.detail(this);"
                                                data-brch-cd="0015"
                                                data-yy="2023"
                                                data-lect-smster-cd="1"
                                                data-lect-cd="3910"
                                                data-tc-cd-no="008610"
                                                data-mbr-no="202631520           " >
                                                <div class="small_label wide HOT">
                                                    </div>
                                                <div class="img_resize_w img">
                                                    <img src="https://culture.lotteshopping.com/files/CUL_ONL/OLD/COMMON/IMAGES/LECT_IMG/81/3910/20210721150157.jpg" alt="20210721150157.jpg" />
                                                </div>
                                                <div class="con">
                                                    <div class="tag_area">
                                                        <div class="label_div">
                                                            <p class="label small black_gray">울산점</p>
                                                        </div>
                                                        <div class="star_rating">
                                                            <span class="star"></span>
                                                            <span class="star"></span>
                                                            <span class="star"></span>
                                                            <span class="star"></span>
                                                            <span class="star"></span>
                                                            </div>
                                                    </div>
                                                    <p class="tit limit_line">마수진쌤 너무 감사해요ㅎㅎㅎ &#40;사심가득한 찐후기&#41;</p>
                                                    <p class="sub_tit limit_line">[봄학기]리틀잼 놀이잼 오감놀이터,16~30개월</p>
                                                    <div class="type_div">
                                                        <p class="type f_caption2">유*진</p>
                                                        <p class="type f_caption2">2023.03.24</p>
                                                        <p class="comment_num f_caption2">0</p>
                                                    </div>
                                                </div>
                                            </a>
                                        </div>
                                    <div class="swiper-slide card_list_v">
                                            <a href="javascript:" class="lec_list" onclick="reviewCtrl.detail(this);"
                                                data-brch-cd="0349"
                                                data-yy="2022"
                                                data-lect-smster-cd="4"
                                                data-lect-cd="2113"
                                                data-tc-cd-no="036791"
                                                data-mbr-no="202491721           " >
                                                <div class="small_label wide HOT">
                                                    </div>
                                                <div class="img_resize_w img">
                                                    <img src="https://culture.lotteshopping.com/files/CUL_ONL/OLD/COMMON/IMAGES/LECT_IMG/32/2113/20220724174450.jpg" alt="20220724174450.jpg" />
                                                </div>
                                                <div class="con">
                                                    <div class="tag_area">
                                                        <div class="label_div">
                                                            <p class="label small black_gray">수원점</p>
                                                        </div>
                                                        <div class="star_rating">
                                                            <span class="star"></span>
                                                            <span class="star"></span>
                                                            <span class="star"></span>
                                                            <span class="star"></span>
                                                            <span class="star"></span>
                                                            </div>
                                                    </div>
                                                    <p class="tit limit_line">한식 요리 맛짱</p>
                                                    <p class="sub_tit limit_line">[겨울학기]한식, 맛깔나는 가정 요리</p>
                                                    <div class="type_div">
                                                        <p class="type f_caption2">노*란</p>
                                                        <p class="type f_caption2">2023.03.06</p>
                                                        <p class="comment_num f_caption2">0</p>
                                                    </div>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                <div class="swiper-pagination"></div>
                            </div>
                            <!-- 2023-03-29 삭제
                            <div class="swiper-button-prev"></div>
                            <div class="swiper-button-next"></div>
                            // 2023-03-29 삭제 -->
                        </div>
                    </div>
                <div class="filter_bar_area no_padding">
                    <div class="fixed_mobile_w">
                        <div class="fixed_mobile">
                            <div class="filter_bar_div">
                                <div class="left">
                                    <p class="f_caption2">전체 <span id="totCnt" class="point"></span></p>
                                </div>
                                <div class="right">
                                    <div class="btn_area">
                                        <div class="btn_wrap">
                                            <div class="filter_open_area">
                                                <input type="hidden" id="orderSet" value="A" />
                                                <a href="javascript:" class="btn order_btn filter_popup_btn" title="컨텐츠정렬 팝업 열기">
                                                    <div class="order_txt">최신순</div>
                                                </a>
                                            </div>
                                            <div class="dimd"></div>
                                            <div class="filter_list_wrap">
                                                <div class="tit_area">
                                                    <p class="tit"></p>
                                                    <a href="javascript:" role="button" class="close"></a>
                                                </div>
                                                <div class="scroll_wrap">
                                                    <a class="txt f_caption2 on" data-value="A" href="javascript:" onclick="reviewCtrl.searchOpt(this);">최신순</a>
                                                    <a class="txt f_caption2" data-value="B" href="javascript:" onclick="reviewCtrl.searchOpt(this);">평점순</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="btn_wrap">
                                            <div class="filter_open_area">
                                                <input type="hidden" id="brchCd" value="" />
                                                <a href="javascript:" class="btn dropdown_btn filter_popup_btn" title="컨텐츠정렬 팝업 열기">
                                                    <div class="order_txt">전체지점</div>
                                                </a>
                                            </div>
                                            <div class="dimd"></div>
                                            <div class="filter_list_wrap">
                                                <div class="tit_area">
                                                    <p class="tit">지점</p>
                                                    <a href="javascript:" role="button" class="close"></a>
                                                </div>
                                                <div class="scroll_wrap">
                                                <!-- <a class="txt f_caption2" data-value="0002" href="javascript:" onclick="reviewCtrl.searchOpt(this);">{ i }</a> -->
                                                    <a class="txt f_caption2 on" href="javacript:" onclick="reviewCtrl.searchOpt(this);">전체지점</a>
                                                     <c:if test="${ not empty getBranch }">
                                                     	
                                                		<c:forEach items="${ getBranch }" var="blist">
                                                    	<p class="sub_tit only_mobile">서울점</p>
                                                    	<a class="txt f_caption2" data-value="0002" href="/community/review/list.do?type=branch&brchCd=${blist.branch_nm}" onclick="reviewCtrl.searchOpt(this);">${ blist }</a>
                                                    <!-- 
                                                    <p class="sub_tit only_mobile">서울점</p>
                                                        <a class="txt f_caption2" data-value="0002" href="javascript:" onclick="reviewCtrl.searchOpt(this);">잠실점</a>
                                                        <a class="txt f_caption2" data-value="0001" href="javascript:" onclick="reviewCtrl.searchOpt(this);">본점</a>
                                                        <a class="txt f_caption2" data-value="0013" href="javascript:" onclick="reviewCtrl.searchOpt(this);">강남점</a>
                                                        <a class="txt f_caption2" data-value="0028" href="javascript:" onclick="reviewCtrl.searchOpt(this);">건대스타시티점</a>
                                                        <a class="txt f_caption2" data-value="0006" href="javascript:" onclick="reviewCtrl.searchOpt(this);">관악점</a>
                                                        <a class="txt f_caption2" data-value="0340" href="javascript:" onclick="reviewCtrl.searchOpt(this);">김포공항점</a>
                                                        <a class="txt f_caption2" data-value="0022" href="javascript:" onclick="reviewCtrl.searchOpt(this);">노원점</a>
                                                        <a class="txt f_caption2" data-value="0026" href="javascript:" onclick="reviewCtrl.searchOpt(this);">미아점</a>
                                                        <a class="txt f_caption2" data-value="0010" href="javascript:" onclick="reviewCtrl.searchOpt(this);">영등포점</a>
                                                        <a class="txt f_caption2" data-value="0004" href="javascript:" onclick="reviewCtrl.searchOpt(this);">청량리점</a>
                                                        <p class="sub_tit only_mobile">수도권점</p>
                                                        <a class="txt f_caption2" data-value="0344" href="javascript:" onclick="reviewCtrl.searchOpt(this);">인천점</a>
                                                        <a class="txt f_caption2" data-value="0399" href="javascript:" onclick="reviewCtrl.searchOpt(this);">동탄점</a>
                                                        <a class="txt f_caption2" data-value="0335" href="javascript:" onclick="reviewCtrl.searchOpt(this);">구리점</a>
                                                        <a class="txt f_caption2" data-value="0008" href="javascript:" onclick="reviewCtrl.searchOpt(this);">분당점</a>
                                                        <a class="txt f_caption2" data-value="0349" href="javascript:" onclick="reviewCtrl.searchOpt(this);">수원점</a>
                                                        <a class="txt f_caption2" data-value="0336" href="javascript:" onclick="reviewCtrl.searchOpt(this);">안산점</a>
                                                        <a class="txt f_caption2" data-value="0011" href="javascript:" onclick="reviewCtrl.searchOpt(this);">일산점</a>
                                                        <a class="txt f_caption2" data-value="0334" href="javascript:" onclick="reviewCtrl.searchOpt(this);">중동점</a>
                                                        <a class="txt f_caption2" data-value="0341" href="javascript:" onclick="reviewCtrl.searchOpt(this);">평촌점</a>
                                                        <a class="txt f_caption2" data-value="0350" href="javascript:" onclick="reviewCtrl.searchOpt(this);">롯데몰광명점</a>
                                                        <p class="sub_tit only_mobile">지방점</p>
                                                        <a class="txt f_caption2" data-value="0005" href="javascript:" onclick="reviewCtrl.searchOpt(this);">부산본점</a>
                                                        <a class="txt f_caption2" data-value="0333" href="javascript:" onclick="reviewCtrl.searchOpt(this);">광복점</a>
                                                        <a class="txt f_caption2" data-value="0007" href="javascript:" onclick="reviewCtrl.searchOpt(this);">광주점</a>
                                                        <a class="txt f_caption2" data-value="0023" href="javascript:" onclick="reviewCtrl.searchOpt(this);">대구점</a>
                                                        <a class="txt f_caption2" data-value="0012" href="javascript:" onclick="reviewCtrl.searchOpt(this);">대전점</a>
                                                        <a class="txt f_caption2" data-value="0016" href="javascript:" onclick="reviewCtrl.searchOpt(this);">동래점</a>
                                                        <a class="txt f_caption2" data-value="0354" href="javascript:" onclick="reviewCtrl.searchOpt(this);">마산점</a>
                                                        <a class="txt f_caption2" data-value="0024" href="javascript:" onclick="reviewCtrl.searchOpt(this);">상인점</a>
                                                        <a class="txt f_caption2" data-value="0027" href="javascript:" onclick="reviewCtrl.searchOpt(this);">센텀시티점</a>
                                                        <a class="txt f_caption2" data-value="0015" href="javascript:" onclick="reviewCtrl.searchOpt(this);">울산점</a>
                                                        <a class="txt f_caption2" data-value="0025" href="javascript:" onclick="reviewCtrl.searchOpt(this);">전주점</a>
                                                        <a class="txt f_caption2" data-value="0017" href="javascript:" onclick="reviewCtrl.searchOpt(this);">창원점</a>
                                                        <a class="txt f_caption2" data-value="0014" href="javascript:" onclick="reviewCtrl.searchOpt(this);">포항점</a>
                                                        <a class="txt f_caption2" data-value="0361" href="javascript:" onclick="reviewCtrl.searchOpt(this);">롯데몰군산점</a>
                                                         -->
                                                	</c:forEach>
                                            	 </c:if>
                                                        </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="listContainer" class="thum_list_w">

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

<script type="text/javascript" src="/common/js/teacher/teacherCommon.js"></script>
<script type="text/javascript" src="/common/js/community/reviewController.js"></script>
			
<!--  end wrap -->

	<script type="text/javascript" src="/resources/common/netfunnel/netfunnel.js" charset="UTF-8"></script>
	
	<script>
		if(true)
		{
			common.logOutTimer.start();
		}
		commonScript.headerFooterFn();
		commonScript.formChkFn();
	</script>
	
	<script>

	</script>
</html>