<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="cont_wrap" style="min-height: 359px;">
	<div class="cont_inner no_pb" style="min-height: 467px;">
		<form id="frmSearch" name="frmSearch" action="/community/notice/view.do" >
			<input type="hidden" id="brchCd" 	 name="brchCd"	   value=""/>
		    <input type="hidden" id="noticeEvent" name="clCd" 		value="8"/>
			<input type="hidden" id="notcSeqno"	 name="notcSeqno"  value="" />
			<input type="hidden" name="q" value=""/>
			<input type="hidden" id="pageIndex"  name="pageIndex"  value="1"/>
			<input type="hidden" id="initIndex"  name="initIndex"  value="1"/>
			<input type="hidden" id="listCnt" 	 name="listCnt"    value="10"/>
		</form>
		<!-- 2022-11-23 구조 수정 -->
			<div class="page_title_area">
				<div class="inner">
					<div class="top_area">
						<a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a> 
						<a href="javascript:" class="tit_div arrow" title="페이지 이동 팝업 열기">
							<p class="tit f_h1">공지사항/이벤트<span class="more_tit"></span></p>
						</a>
						<div class="tit_popup">
							<div class="pop_wrap">
								<div class="pop_cont">
									<div class="for_padding">
										<div class="scroll_area">
											<div class="branch">
		<a class="active" href="/community/notice/list.do"><p class="f_desc">공지사항/이벤트</p></a>	
			<a class="" href="/community/review/list.do"><p class="f_desc">수강후기</p></a>	
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
<input type="text" name="q" placeholder="검색어를 입력해주세요" id="q" title="검색어 입력" onkeyup="noticeCtrl.queryOnkeyup();">
						
						<div class="input_btn_wrap">
							<button type="button" class="btn_delete" title="지우기"></button>
							<button type="button" class="btn_search" title="검색" onclick="noticeCtrl.search_btn();"></button>
						</div>
					</div>
				</div>
				<div class="m_pop_dimd"></div>
			</div>
<!-- 		</form> -->
		<!-- // 2022-11-23 구조 수정 -->
		<div class="page_cont_area">
			<div class="inner">
				<div class="tab_func_area">
					<div class="filter_bar_area no_padding">
						<div class="border_tab_area tab_btn_area">
							<div class="swiper-container">
								<div class="swiper-wrapper">
									<a href="javascript:noticeCtrl.tabClCd(8);" data-clCd="8" class="btn swiper-slide on">공지사항</a>
		                            <%-- <a href="javascript:noticeCtrl.tabClCd('4');" data-clCd="${clCdList.clCd}" class="btn swiper-slide ">이벤트</a> --%>
		                            <a href="javascript:noticeCtrl.tabClCd(4);" data-clCd="4" class="btn swiper-slide ">이벤트</a>
		                            </div>
							</div>
						</div>
					</div>
					<div class="tab_con_area">
						<div class="con on">
							<div class="filter_bar_area no_padding">
								<div class="fixed_mobile_w">
									<div class="fixed_mobile">
										<div class="filter_bar_div">
											<div class="left">
												<p class="f_caption2">
													전체 <span class="point" id="totCnt"></span>
												</p>
											</div>
											<!--  공통 코드 지점 조회 영역 -->
											<div class="right">
												<div class="btn_area">
													<div class="btn_wrap">
														<div class="filter_open_area">
															<a href="javascript:" class="btn dropdown_btn filter_popup_btn" title="컨텐츠정렬 팝업 열기">
																<div class="order_txt">전체</div>
															</a>
														</div>
														<div class="dimd"></div>
														<div class="filter_list_wrap">
															<div class="tit_area">
																<p class="tit">지점</p>
																<a href="javascript:" role="button" class="close"></a>
															</div>
															<div class="scroll_wrap">
																<a class="txt f_caption2 on" href="javascript:noticeCtrl.selectBrch('${brchList.branch_id}');">전체지점</a>
									                    <p class="sub_tit only_mobile">서울점</p>
										                <c:forEach items="${bList}" var="brchList">
                                                    		<c:if test="${brchList.branch_tp_id eq 1 }">                                
                                                        		<a class="txt f_caption2 " href="javascript:noticeCtrl.selectBrch('${brchList.branch_id}');" data-brch-cd="${brchList.branch_id}">${ brchList.branch_nm }</a>
                                                            </c:if>                                                   
                                                    	</c:forEach>
                                                    	
                                                        <p class="sub_tit only_mobile">수도권점</p>
                                                        <c:forEach items="${bList}" var="brchList">
                                                    			<c:if test="${brchList.branch_tp_id eq 2 }"> 
                                                        			<a class="txt f_caption2 " href="javascript:noticeCtrl.selectBrch('${brchList.branch_id}');" data-brch-cd="${brchList.branch_id}">${ brchList.branch_nm }</a>
                                                        		</c:if>
                                                        </c:forEach>
                                                        
                                                        <p class="sub_tit only_mobile">지방점</p>
                                                        <c:forEach items="${bList}" var="brchList">
                                                    		<c:if test="${brchList.branch_tp_id eq 3 }"> 
                                                        		<a class="txt f_caption2 " href="javascript:noticeCtrl.selectBrch('${brchList.branch_id}');" data-brch-cd="${brchList.branch_id}">${ brchList.branch_nm }</a>
                                                        	</c:if>
                                                    	</c:forEach>
										                            </div>
														</div>
													</div>
												</div>
											</div>
											<!--  공통 코드 지점 조회 영역 -->
										</div>
									</div>
								</div>
							</div>
							<div class="notice_list_w" id="listContainer">

							</div>
							<div class="more_btn_w" id="moreBtn" style="display:none;">
								<a href="javascript:" class="more_btn no_motion"> 
									<span>더보기<strong class="total"></strong></span>
								</a>
							</div>
						</div>
</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="/resources/common/js/community/noticeCtrl.js"></script>
			