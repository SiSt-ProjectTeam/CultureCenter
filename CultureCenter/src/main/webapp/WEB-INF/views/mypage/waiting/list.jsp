<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ko">

<body class="">
	<div id="wrap" data-is-app="" data-is-mobile="" data-is-login="Y">
<%-- 
			<%@ include file="/WEB-INF/views/inc/head.jsp"%>
			<%@ include file="/WEB-INF/views/inc/header.jsp"%>

 --%>
		<form id="frm_submit" name="frm_submit">
			<input type="hidden" name="csrfPreventionSalt"
				value="5J8tc1q8uIX3wqnjNd1M" /> <input type="hidden" name="jsonStr"
				value="" /> <input type="hidden" name="atlctRsvNo" value="" /> <input
				type="hidden" name="atlctType" value="waiting" />
		</form>

		<form id="frm_cancel" name="frm_cancel">
			<input type="hidden" name="atlctRsvNo" value="" /> <input
				type="hidden" name="lectNm" value="" /> <input type="hidden"
				name="optnNm" value="" /> <input type="hidden" name="lectStDtm"
				value="" /> <input type="hidden" name="lectSt" value="" />
		</form>

		<div class="cont_wrap">
			<form id="frm_search" name="frm_search">
				<input type="hidden" id="pageIndex" name="pageIndex" value="1" /> <input
					type="hidden" id="branch_nm" name="branch_nm" value="" /> <input
					type="hidden" id="initIndex" name="initIndex" value="1" /> <input
					type="hidden" id="listCnt" name="listCnt" value="6" />
			</form>

			<div class="cont_inner no_pb">
				<div class="page_title_area">
					<div class="inner">
						<div class="top_area">
							<a href="javascript:fnc.back();" class="page_prev_btn"
								title="뒤로가기"></a> <a href="javascript:" class="tit_div arrow"
								title="페이지 이동 팝업 열기">
								<p class="tit f_h1">
									대기자 조회 <span class="more_tit"></span>
								</p>
							</a>
							<div class="tit_popup">
								<div class="pop_wrap">
									<div class="pop_cont">
										<div class="for_padding">
											<div class="scroll_area">
												<div class="branch">
													<a class="" href="/mypage/member/info.do"><p
															class="f_desc">회원정보변경</p></a> <a class=""
														href="/mypage/atlct/list.do"><p class="f_desc">수강내역
															조회</p></a> <a class="active" href="/mypage/waiting/list.do"><p
															class="f_desc">대기자 조회</p></a> <a class=""
														href="/mypage/freebie/appList.do"><p class="f_desc">사은품
															신청</p></a> <a class="" href="/mypage/freebie/detailList.do"><p
															class="f_desc">사은품 신청내역</p></a> <a class=""
														href="/mypage/coupon/list.do"><p class="f_desc">나의
															쿠폰</p></a> <a class="" href="/mypage/myreview/list.do"><p
															class="f_desc">나의 수강후기</p></a> <a class=""
														href="/mypage/teachereval/list.do"><p class="f_desc">만족도
															평가</p></a> <a class="" href="/mypage/lectureCertf/list.do"><p
															class="f_desc">수강증</p></a> <a class=""
														href="/mypage/inquiry/list.do"><p class="f_desc">1:1
															문의</p></a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="m_pop_dimd"></div>
				</div>
				<div class="page_cont_area">
					<div class="inner pay_info dual_list">
						<div class="wating_w">
							<div class="dot_txt_box">
								<p class="dot_txt">기존 회원의 수강신청 취소로 접수가 가능해지면 대기순번에 따라
									문자(카카오톡)로 알려드리며 알림 후 12시간 동안 수강신청이 가능합니다.</p>
								<p class="dot_txt">대기강좌의 수강기간이 종료 시 대기강좌 목록에서 최대 3개월까지 보관
									됩니다.</p>
								<p class="dot_txt">대기접수 상태에서 추가로 동반 수강자 선택이 필요하거나, 재료비 또는
									대여료 추가해야 할 경우 이전 대기접수 신청 건을 삭제 후 재신청 하셔야 합니다.</p>
							</div>
							<div class="filter_bar_area no_padding">
								<div class="fixed_mobile_w">
									<div class="fixed_mobile">
										<div class="filter_bar_div">
											<div class="left">
												<p class="f_caption2">
													전체 <span class="point" id="totCnt">0개</span>
												</p>
											</div>
											<div class="right">
												<div class="btn_area">
													<div class="btn_wrap">
														<div class="filter_open_area">
															<a href="javascript:"
																class="btn dropdown_btn filter_popup_btn"
																title="컨텐츠정렬 팝업 열기"><span class="order_txt">전체지점</span></a>
														</div>
														<div class="dimd"></div>
														<div class="filter_list_wrap">
															<div class="tit_area">
																<p class="tit">지점</p>
																<a href="javascript:" role="button" class="close"></a>
															</div>
															<div class="scroll_wrap">
																<a class="txt f_caption2 on"
																	href="javascript:mypage_waiting.changebranch_nm('')">전체지점</a>
																<p class="sub_tit only_mobile">서울점</p>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('잠실점')">잠실점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('본점')">본점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('강남점')">강남점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('건대스타시티점')">건대스타시티점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('관악점')">관악점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('김포공항점')">김포공항점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('노원점')">노원점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('미아점')">미아점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('영등포점')">영등포점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('청량리점')">청량리점</a>
																<p class="sub_tit only_mobile">수도권점</p>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('인천점')">인천점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('동탄점')">동탄점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('구리점')">구리점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('분당점')">분당점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('수원점')">수원점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('안산점')">안산점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('일산점')">일산점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('중동점')">중동점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('평촌점')">평촌점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('롯데몰광명점')">롯데몰광명점</a>
																<p class="sub_tit only_mobile">지방점</p>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('부산본점')">부산본점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('광복점')">광복점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('광주점')">광주점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('대구점')">대구점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('대전점')">대전점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('동래점')">동래점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('마산점')">마산점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('상인점')">상인점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('센텀시티점')">센텀시티점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('울산점')">울산점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('전주점')">전주점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('창원점')">창원점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('포항점')">포항점</a>
																<a class="txt f_caption2 "
																	href="javascript:mypage_waiting.changebranch_nm('롯데몰군산점')">롯데몰군산점</a>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="course_history_w" id="listContainer"></div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<script type="text/javascript"
			src="/resources/common/js/mypage/mypage_waiting.js"></script>


		<!-- 관계사 사이트 팝업 -->
		<div class="layer_popup" id="familyPop"
			style="display: none; top: 0px;">
			<div class="pop_wrap w800"
				style="transform: translate(0px, 0px); height: 692px; margin-left: -400px; margin-top: -346px;">
				<div class="pop_head">
					<p class="f_h2">롯데 관계사 사이트</p>
				</div>
				<div class="pop_cont"
					style="transform: translate(0px, 0px); height: 620px;">
					<div class="for_padding">
						<div class="scroll_area">
							<div class="footer_site opacity_txt_list">
								<p class="link">
									<a class="txt" href="http://www.lotte.co.kr/main.do" title="새창"
										target="_blank">롯데지주</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lotteins.co.kr" title="새창"
										target="_blank">롯데손해보험</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.ldcc.co.kr/" title="새창"
										target="_blank">롯데제과</a>
								</p>
								<p class="link">
									<a class="txt" href="https://www.mybi.co.kr/" title="새창"
										target="_blank">마이비</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lottecon.co.kr/" title="새창"
										target="_blank">롯데건설</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lotteintl.co.kr/" title="새창"
										target="_blank">롯데상사</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lottejtb.com/" title="새창"
										target="_blank">롯데제이티비</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.charlottetheater.co.kr/"
										title="새창" target="_blank">샤롯데시어터</a>
								</p>
								<p class="link">
									<a class="txt" href="https://www.lotteglogis.com/" title="새창"
										target="_blank">롯데글로벌로지스</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lottesuper.co.kr/" title="새창"
										target="_blank">롯데슈퍼</a>
								</p>
								<p class="link">
									<a class="txt" href="https://www.lotternd.com/KOR/" title="새창"
										target="_blank">롯데중앙연구소</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.angelinus.com/" title="새창"
										target="_blank">엔젤리너스</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lottelem.co.kr/" title="새창"
										target="_blank">롯데기공</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lottecinema.co.kr/" title="새창"
										target="_blank">롯데시네마</a>
								</p>
								<p class="link">
									<a class="txt"
										href="http://job.lotte.co.kr/LotteRecruit/Lotte1.aspx"
										title="새창" target="_blank">롯데채용센터</a>
								</p>
								<p class="link">
									<a class="txt" href="https://store-kr.uniqlo.com/" title="새창"
										target="_blank">유니클로</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lottebp.com/main.html"
										title="새창" target="_blank">롯데비피화학</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.asahibeerk.com/" title="새창"
										target="_blank">롯데아사히주류</a>
								</p>
								<p class="link">
									<a class="txt" href="http://mall.lottechilsung.co.kr/"
										title="새창" target="_blank">롯데칠성음료(음료BG)</a>
								</p>
								<p class="link">
									<a class="txt" href="https://www.cashbee.co.kr/" title="새창"
										target="_blank">이비카드</a>
								</p>
								<p class="link">
									<a class="txt" href="https://www.lotterentacar.net" title="새창"
										target="_blank">롯데렌탈</a>
								</p>
								<p class="link">
									<a class="txt" href="https://www.lotteal.co.kr/" title="새창"
										target="_blank">롯데알미늄</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lotteliquor.com/" title="새창"
										target="_blank">롯데칠성음료(주류BG)</a>
								</p>
								<p class="link">
									<a class="txt" href="https://www.canon-bs.co.kr/main/"
										title="새창" target="_blank">캐논코리아비즈니스솔루션</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.llc.co.kr/" title="새창"
										target="_blank">롯데로지스틱스</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lottemcc.com/" title="새창"
										target="_blank">롯데엠시시</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lotteadms.com/" title="새창"
										target="_blank">롯데첨단소재</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.7-eleven.co.kr/" title="새창"
										target="_blank">코리아세븐</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lohbs.co.kr/" title="새창"
										target="_blank">롯데롭스</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lotteworld.com/gate.html"
										title="새창" target="_blank">롯데월드</a>
								</p>
								<p class="link">
									<a class="txt" href="https://www.lottecard.co.kr/" title="새창"
										target="_blank">롯데카드</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.krispykreme.co.kr/" title="새창"
										target="_blank">크리스피크림도넛</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lotteria.com/" title="새창"
										target="_blank">롯데리아</a>
								</p>
								<p class="link">
									<a class="txt" href="https://www.lottelmsc.com/" title="새창"
										target="_blank">롯데유통사업본부</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lottecap.com/" title="새창"
										target="_blank">롯데캐피탈</a>
								</p>
								<p class="link">
									<a class="txt" href="http://toysrus.lottemart.com/index.do"
										title="새창" target="_blank">토이저러스</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lottemart.com/index.do"
										title="새창" target="_blank">롯데마트</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lotteacademy.co.kr/" title="새창"
										target="_blank">롯데인재개발원</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lottechem.com/" title="새창"
										target="_blank">롯데케미칼</a>
								</p>
								<!--             <p class="link"><a class="txt" href="http://www.tgif.co.kr" title="새창" target="_blank">T.G.I.Friday’s</a></p> -->
								<p class="link">
									<a class="txt" href="http://kor.lottedfs.com/kr" title="새창"
										target="_blank">롯데면세점</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lottedevelop.com" title="새창"
										target="_blank">롯데자산개발</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lottefoods.co.kr/" title="새창"
										target="_blank">롯데푸드</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.fujifilm.co.kr/" title="새창"
										target="_blank">한국후지필름</a>
								</p>
								<p class="link">
									<a class="txt" href="https://www.lpoint.com/" title="새창"
										target="_blank">롯데멤버스</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.giantsclub.com/html/"
										title="새창" target="_blank">롯데자이언츠</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.e-himart.co.kr/" title="새창"
										target="_blank">롯데하이마트</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.hanpay.net/portal2nd/index.do"
										title="새창" target="_blank">한페이시스</a>
								</p>
								<p class="link">
									<a class="txt" href="https://www.lwt.co.kr/lottepnd/main.do"
										title="새창" target="_blank">롯데물산</a>
								</p>
								<p class="link">
									<a class="txt" href="https://www.lottefoundation.or.kr/"
										title="새창" target="_blank">롯데재단</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lottehotel.com/global/ko/"
										title="새창" target="_blank">롯데호텔</a>
								</p>
								<!--             <p class="link"><a class="txt" href="javascript:">현대정보기술</a></p> -->
								<p class="link">
									<a class="txt" href="http://store.lotteshopping.com/"
										title="새창" target="_blank">롯데백화점</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lottefinechem.com/" title="새창"
										target="_blank">롯데정밀화학</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.lotteimall.com/" title="새창"
										target="_blank">롯데홈쇼핑</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.skyhill.co.kr/" title="새창"
										target="_blank">롯데스카이힐CC</a>
								</p>
								<p class="link">
									<a class="txt" href="http://www.ldcc.co.kr/" title="새창"
										target="_blank">롯데정보통신</a>
								</p>
								<p class="link">
									<a class="txt" href="https://www.daehong.com/" title="새창"
										target="_blank">대홍기획</a>
								</p>
							</div>
						</div>
					</div>
				</div>
				<a class="btn_close" href="javascript:" title="닫기"> <span
					class="blind"></span>
				</a>
			</div>
		</div>
		<!-- sns공유하기 팝업 -->
		<script type="text/javascript" src="/resources/common/js/lib/clipboard.min.js"></script>

		<div class="layer_popup" id="sharePop"
			style="display: none; top: 0px;">
			<div class="pop_wrap"
				style="transform: translate(0px, 0px); height: 242px; margin-left: -240px; margin-top: -121px;">
				<div class="pop_head">
					<p class="title">공유하기</p>
				</div>
				<div class="pop_cont"
					style="transform: translate(0px, 0px); height: 170px;">
					<div class="for_padding">
						<div class="scroll_area">
							<div class="share_area dSocialShare">
								<a href="javascript:" id="btnKakao" class="share"
									data-key="d427a7c1718f571750db2822740d0465"> <!-- onclick="sharePop.setKakao(this)"  -->
									<p class="img">
										<img src="/resources/common/images/icon-sns-kakao-talk.png" alt="">
									</p>
									<p class="txt">카카오톡</p>
								</a> <a href="javascript:" id="btnFaceBook" class="share"> <!-- onclick="sharePop.setFaceBook(this)" -->
									<p class="img">
										<img src="/resources/common/images/icon-sns-facebook.png" alt="">
									</p>
									<p class="txt">페이스북</p>
								</a> <a href="javascript:" id="btnTwitter" class="share"> <!--  onclick="sharePop.setTwitter(this)" -->
									<p class="img">
										<img src="/resources/common/images/icon-sns-twiiter.png" alt="">
									</p>
									<p class="txt">트위터</p>
								</a> <a href="javascript:" class="share" id="btnClipBoard">
									<!-- onclick="sharePop.setClipboard(this)"  -->
									<p class="img">
										<img src="/resources/common/images/icon-sns-link-copy.png" alt="">
									</p>
									<p class="txt">URL복사</p>
								</a>
							</div>
						</div>
					</div>
				</div>
				<a class="btn_close" href="javascript:" title="닫기"> <span
					class="blind"></span>
				</a>
			</div>
		</div>

		<script type="text/javascript" src="/resources/common/js/sharePop.js"></script>
		<div class="layer_popup logout" id="logOutGuide"
			style="display: none;">
			<div class="pop_wrap">
				<div class="pop_cont">
					<div class="for_padding">
						<div class="scroll_area">
							<div class="txt_con">
								<div class="sub_tit">
									<p class="point f_h1" id="limitTime">60초</p>
									<p>자동 로그아웃 예정입니다.</p>
								</div>
								<p class="desc">
									30분 동안 서비스 이용이 없어 고객님의 <br> 안전한 사이트 이용을 위해 자동 로그아웃 됩니다. <br>
									계속하시려면 로그인 시간을 연장해 주세요.
								</p>
							</div>
							<div class="btn_area">
								<div class="btn_wrap">
									<a class="border_btn" href="javascript:fnc.moveLogout()"> <span>로그아웃</span></a>
								</div>
								<div class="btn_wrap">
									<a class="b_color_btn" href="javascript:common.delayLogOut()">
										<span>로그인연장</span>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<a class="btn_close" href="javascript:" title="닫기"> <span
					class="blind"></span>
				</a>
			</div>
		</div>

		<a class="btn_top" href="javascript:" title="페이지 상단으로 이동"></a>
		<div id="dimdBg" class="dimd"></div>



		<form id="frmSso" method="post">
			<input type="hidden" name="ssoTkn" id="ssoTkn" value="" /> <input
				type="hidden" name="acesTkn" id="acesTkn" value="" /> <input
				type="hidden" name="rnwTkn" id="rnwTkn" value="" /> <input
				type="hidden" name="onlCstTpC" id="onlCstTpC" value="" /> <input
				type="hidden" name="frnYn" id="frnYn" value="" /> <input
				type="hidden" name="rspClac" id="rspClac" value="" /> <input
				type="hidden" name="rspC" id="rspC" value="" /> <input
				type="hidden" name="rspDtc" id="rspDtc" value="" /> <input
				type="hidden" name="rspMsgCn" id="rspMsgCn" value="" /> <input
				type="hidden" name="rtnUrl" id="rtnUrl" value="" />
		</form>

		<script type="text/javascript" src="/resources/common/js/member/sso.js"></script>
	</div>
	<!--  end wrap -->

	<script type="text/javascript" src="/resources/common/netfunnel/netfunnel.js"
		charset="UTF-8"></script>

	<script>
		if (true) {
			common.logOutTimer.start();
		}
		commonScript.headerFooterFn();
		commonScript.formChkFn();
	</script>

</body>
</html>