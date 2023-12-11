<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="cont_wrap">
	<div class="cont_inner no_pb">
		<form id="frmSearch" name="frmSearch">
			<input type="hidden" id="clCd" name="clCd" value="" /> 
			<input type="hidden" id="faqSeqno" name="faqSeqno" value="" /> 
			<input type="hidden" id="pageIndex" name="pageIndex" value="1" /> 
			<input type="hidden" id="initIndex" name="initIndex" value="1" /> 
			<input type="hidden" id="listCnt" name="listCnt" value="10" />
			<div class="page_title_area">
				<div class="inner">
					<div class="top_area">
						<a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a> 
						<a href="javascript:" class="tit_div arrow" title="페이지 이동 팝업 열기">
						<p class="tit f_h1">자주하는 문의<span class="more_tit"></span></p>
						</a>
						<div class="tit_popup">
							<div class="pop_wrap">
								<div class="pop_cont">
									<div class="for_padding">
										<div class="scroll_area">
											<div class="branch">
												<a class="" href="/information/branch/list.do"><p class="f_desc">지점안내</p></a> 
												<a class="" href="/information/application/index.do"><p class="f_desc">강사지원·제휴신청</p></a> 
												<a class="active" href="/information/faq/list.do"><p class="f_desc">자주하는 문의</p></a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="form_search">
					<div class="form_search_div">
						<input type="text" name="q" value="" placeholder="검색어를 입력해주세요" title="검색어 입력" onkeyup="if(window.event.keyCode == 13) faqCtrl.fn_search();">
						<div class="input_btn_wrap">
							<button type="button" class="btn_delete" title="지우기"></button>
							<button type="button" class="btn_search" title="검색" onclick="faqCtrl.search_btn();"></button>
						</div>
					</div>
				</div>
				<div class="m_pop_dimd"></div>
			</div>
			<!-- // 2022-11-23 구조 수정 -->
		</form>
		<div class="page_cont_area">
			<div class="inner">
				<div class="inquiry_slide_w">
					<p class="title f_h2">
						<span></span>자주 묻는 질문
					</p>
					<div class="inquiry_slide">
						<div class="swiper-container">
							<div class="swiper-wrapper">
								<div class="swiper-slide">
									<p class="tit f_body1">인터넷 회원가입을 하려고 하는데 L.POINT가입을 꼭
										해야하나요?</p>
									<p class="txt f_body2">롯데문화센터는 롯데그룹 통합 회원제인 L.POINT 제휴 사이트로
										L.POINT에서 회원가입을 하셔야 하며, L.POINT에 등록된 ID와 비밀번호로 이용할 수 있습니다.</p>
									<a href="javascript:" class="more f_body1">더보기</a>
								</div>
								<div class="swiper-slide">
									<p class="tit f_body1">수강신청 기간은 어떻게 되나요?</p>
									<p class="txt f_body2">일반적으로 수강신청은 매년 1/4/7/10월 4주차경
										봄/여름/가을/겨울학기 접수가 시작됩니다. 학기별, 기존ㆍ신규회원별로 날짜가 상이하오니 미리 롯데문화센터
										홈페이지 공지사항 확인 또는 해당 점에 접수 일정을 문의바랍니다.</p>
									<a href="javascript:" class="more f_body1">더보기</a>
								</div>
								<div class="swiper-slide">
									<p class="tit f_body1">로그인 오류 시 어떻게 대처해야하나요?</p>
									<p class="txt f_body2">로그인 오류 시 이용하시는 점포의 데스크로 문의해주시기 바랍니다.
										로그인 오류 화면 내 표기되는 오류코드를 확인해주시면 조금 더 원활한 해결이 가능합니다.</p>
									<a href="javascript:" class="more f_body1">더보기</a>
								</div>
								<div class="swiper-slide">
									<p class="tit f_body1">문화센터 회원 주차는 어떻게 지원되나요?</p>
									<p class="txt f_body2">수업 1일전까지 마이페이지&gt;이름 클릭&gt;차량번호 등록 시
										수업 당일에 자동으로 등록됩니다. 1강좌 진행 시 3시간 제공, 강의시간 2시간 30분 초과 시
										강의시간+1시간이 제공됩니다. 단, 잠실/관악/포항/상인/전주/건대스타시티/마산/군산점은 자동등록이 적용되지
										않으므로 지점에 문의해주시길 바랍니다.</p>
									<a href="javascript:" class="more f_body1">더보기</a>
								</div>
								<div class="swiper-slide">
									<p class="tit f_body1">자녀 등 가족 추가, 동반 수강자 추가는 어떻게 하나요?</p>
									<p class="txt f_body2">만 14세 미만의 자녀는 보호자가 마이페이지에서 자녀회원으로
										추가하여 수강 신청할수 있습니다. 만 14세 이상의 경우 동반 수강자가 별도 L.POINT 회원 가입 및
										롯데문화센터 회원 가입 후 데스크에서 방문하시면 본인 확인 후 등록해드립니다.</p>
									<a href="javascript:" class="more f_body1">더보기</a>
								</div>
							</div>
							<div class="swiper-pagination"></div>
						</div>
						<div class="swiper-button-prev"></div>
						<div class="swiper-button-next"></div>
					</div>
				</div>
				<div class="inquiry_con tab_func_area">
					<div class="filter_bar_area">
						<div class="border_tab_area">
							<div class="swiper-container">
								<div class="swiper-wrapper tab_btn_area">
									<a href="javascript:" class="swiper-slide  on btn" onclick="faqCtrl.tabClCd('');"><span>전체</span></a> 
									<a href="javascript:" data-clCd="1" class="swiper-slide btn" onclick="faqCtrl.tabClCd('1');">회원가입</a> 
									<a href="javascript:" data-clCd="2" class="swiper-slide btn" onclick="faqCtrl.tabClCd('2');">수강신청·취소</a> 
									<a href="javascript:" data-clCd="3" class="swiper-slide btn" onclick="faqCtrl.tabClCd('3');">강좌·강사</a> 
									<a href="javascript:" data-clCd="4" class="swiper-slide btn" onclick="faqCtrl.tabClCd('4');">지점·홈페이지</a> 
									<a href="javascript:" data-clCd="5" class="swiper-slide btn" onclick="faqCtrl.tabClCd('5');">기타</a>
								</div>
							</div>
						</div>
					</div>
					<div class="tab_con_area">
						<div class="con on">
							<div class="accordion_type one_open listContainer" id="listContainer"></div>
						</div>
						<div class="con ">
							<div class="accordion_type one_open listContainer" id="listContainer"></div>
						</div>
						<div class="con ">
							<div class="accordion_type one_open listContainer" id="listContainer"></div>
						</div>
						<div class="con ">
							<div class="accordion_type one_open listContainer" id="listContainer"></div>
						</div>
						<div class="con ">
							<div class="accordion_type one_open listContainer" id="listContainer"></div>
						</div>
					</div>
					<div class="more_btn_w" id="moreBtn" style="display: none;">
						<a href="javascript:" class="more_btn no_motion"> <span>더보기<strong class="total"></strong></span></a>
					</div>
					<div class="dot_txt_box">
						<p class="dot_txt">자주하는 문의에서 찾을 수 없는 답변은 <span class="dark">1:1 문의</span>를 통해 궁금한 점을 보내 주시기 바랍니다.</p>
						<p class="dot_txt">답변 내용은 마이페이지의 <span class="dark">1:1 문의</span>에서 확인하실 수 있습니다.</p>
						<p class="inquiry_btn">
							<a href="/mypage/inquiry/list.do" class="arrow_btn"> 
							<span><span class="icon"></span> 1:1문의</span>
							</a>
						</p>
					</div>
				</div>
			</div>
		</div>
		<!-- 	</form>	 -->
	</div>
</div>

<script type="text/javascript" src="/resources/common/js/information/faqCtrl.js"></script>