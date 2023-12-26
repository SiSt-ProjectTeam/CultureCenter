<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="cont_wrap">
			<form id="searchFreebie" name="searchFreebie">
				<input type="hidden" id="brchCd" name="brchCd" value="0006"/>
				<input type="hidden" id="pageIndex" name="pageIndex" value="1"/>
				<input type="hidden" id="initIndex" name="initIndex" value="1"/>
				<input type="hidden" id="listCnt" name="listCnt" value="20"/>
			</form>
			<div class="cont_inner no_pb">
				<div class="page_title_area">
					<div class="inner">
						<div class="top_area">
							<a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a>
							<a href="javascript:" class="tit_div arrow" title="페이지 이동 팝업 열기">
								<p class="tit f_h1">사은품 신청 <span class="more_tit"></span></p>
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
       	<a class="active" href="/mypage/freebie/appList.do"><p class="f_desc">사은품 신청</p></a>
       	<a class="" href="/mypage/freebie/detailList.do"><p class="f_desc">사은품 신청내역</p></a>
       	<a class="" href="/mypage/coupon/list.do"><p class="f_desc">나의 쿠폰</p></a>
       	<a class="" href="/mypage/myreview/list.do"><p class="f_desc">나의 수강후기</p></a>
       	<a class="" href="/mypage/teachereval/list.do"><p class="f_desc">만족도 평가</p></a>
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
						<div class="filter_bar_area no_padding">
							<div class="fixed_mobile_w">
								<div class="fixed_mobile">
									<div class="filter_bar_div">
										<div class="left">
											<p class="f_caption2">전체 <span class="point" id="totCnt">0개</span></p>
										</div>
										<div class="right">
											<div class="btn_area">
												<div class="btn_wrap">
													<div class="filter_open_area">
														<a href="javascript:" class="btn dropdown_btn filter_popup_btn" title="컨텐츠정렬 팝업 열기"><div class="order_txt"></div></a>
													</div>
													<div class="dimd"></div>
													<div class="filter_list_wrap">
														<div class="tit_area">
															<p class="tit">지점</p>
															<a href="javascript:" role="button" class="close"></a>
														</div>
														<div class="scroll_wrap">
														
															<a class="txt f_caption2 " href="javascript:appList.setBranch('0002')">잠실점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0001')">본점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0013')">강남점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0028')">건대스타시티점</a>
																<a class="txt f_caption2 on" href="javascript:appList.setBranch('0006')">관악점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0340')">김포공항점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0022')">노원점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0026')">미아점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0010')">영등포점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0004')">청량리점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0344')">인천점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0399')">동탄점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0335')">구리점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0008')">분당점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0349')">수원점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0336')">안산점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0011')">일산점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0334')">중동점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0341')">평촌점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0005')">부산본점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0333')">광복점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0007')">광주점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0023')">대구점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0012')">대전점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0016')">동래점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0354')">마산점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0024')">상인점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0027')">센텀시티점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0015')">울산점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0025')">전주점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0017')">창원점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0014')">포항점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0361')">롯데몰군산점</a>
																<a class="txt f_caption2 " href="javascript:appList.setBranch('0350')">롯데몰광명점</a>
																</div>
													</div>
													
													<script type="text/javascript">
													  	var title = document.querySelector(".cont_wrap .filter_list_wrap .scroll_wrap a[class$=on]").innerText;
														document.querySelector(".cont_wrap .filter_open_area .order_txt").innerText = title;
													</script>
													
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="thum_list_w" id="listContainer">
							<div class="inner" data-tot-cnt="0">
								<div class="no_srch_area no_pb">
									<div class="no_srch_div">
										<p class="txt f_h2">
											검색결과가 없습니다.
										</p>
									</div>
								</div>
							</div>
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
     <script type="text/javascript" src="/resources/common/js/mypage/freebie/appList.js"></script>