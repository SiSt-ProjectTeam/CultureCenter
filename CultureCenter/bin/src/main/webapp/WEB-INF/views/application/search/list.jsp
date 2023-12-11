<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="cont_wrap">
	<form id="searchBranch" name="searchBranch">
		<input type="hidden" id="type" name="type" value="branch"/>
		<input type="hidden" id="brchCd" name="brchCd" value="0002"/>
		<input type="hidden" id="lrclsCtegryCd" name="lrclsCtegryCd" value=""/>
		<input type="hidden" id="mdclsCtegryCd" name="mdclsCtegryCd" value=""/>
		<input type="hidden" id="smclsCtegryCd" name="smclsCtegryCd" value=""/>
		<input type="hidden" id="yyList" name="yyList" value=""/>
		<input type="hidden" id="lectClCdList" name="lectClCdList" value=""/>
		<input type="hidden" id="lectStatCdList" name="lectStatCdList" value=""/>
		<input type="hidden" id="stDaywCdList" name="stDaywCdList" value=""/>
		<input type="hidden" id="timeTypeList" name="timeTypeList" value=""/>
		<input type="hidden" id="amtTypeList" name="amtTypeList" value=""/>
		<input type="hidden" id="stAmt" name="stAmt" value=""/>
		<input type="hidden" id="endAmt" name="endAmt" value=""/>
		<input type="hidden" id="q" name="q" value=""/>
		<input type="hidden" id="orderSet" name="orderSet" value="C"/>
		<input type="hidden" id="pageIndex" name="pageIndex" value="1"/>
		<input type="hidden" id="initIndex" name="initIndex" value="1"/>
		<input type="hidden" id="listCnt" name="listCnt" value="20"/>
		<input type="hidden" id="tcCdNo" name="tcCdNo" value=""/>
	</form>
				
	<div class="cont_inner no_pb">
		<div class="page_title_area no_pb">
			<div class="inner">
				<div class="top_area move_area">
					<a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a>
					<a href="javascript:" class="tit_div arrow" title="지점 팝업 열기">
						<p class="tit f_h1"><span class="more_tit"></span></p>
					</a>
					<div class="tit_popup">
						<div class="pop_wrap">
							<div class="pop_cont">
								<div class="for_padding">
									<div class="scroll_area">
										<div class="branch">
												<p class="sub_tit f_caption1">서울점</p>
												<a class="active" href="/application/search/list.do?type=branch&brchCd=0002"><p class="f_desc">잠실점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0001"><p class="f_desc">본점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0013"><p class="f_desc">강남점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0028"><p class="f_desc">건대스타시티점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0006"><p class="f_desc">관악점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0340"><p class="f_desc">김포공항점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0022"><p class="f_desc">노원점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0026"><p class="f_desc">미아점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0010"><p class="f_desc">영등포점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0004"><p class="f_desc">청량리점</p></a>
												</div>
										<div class="branch">
												<p class="sub_tit f_caption1">수도권점</p>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0344"><p class="f_desc">인천점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0399"><p class="f_desc">동탄점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0335"><p class="f_desc">구리점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0008"><p class="f_desc">분당점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0349"><p class="f_desc">수원점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0336"><p class="f_desc">안산점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0011"><p class="f_desc">일산점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0334"><p class="f_desc">중동점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0341"><p class="f_desc">평촌점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0350"><p class="f_desc">롯데몰광명점</p></a>
												</div>
										<div class="branch">
												<p class="sub_tit f_caption1">지방점</p>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0005"><p class="f_desc">부산본점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0333"><p class="f_desc">광복점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0007"><p class="f_desc">광주점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0023"><p class="f_desc">대구점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0012"><p class="f_desc">대전점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0016"><p class="f_desc">동래점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0354"><p class="f_desc">마산점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0024"><p class="f_desc">상인점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0027"><p class="f_desc">센텀시티점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0015"><p class="f_desc">울산점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0025"><p class="f_desc">전주점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0017"><p class="f_desc">창원점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0014"><p class="f_desc">포항점</p></a>
												<a class="" href="/application/search/list.do?type=branch&brchCd=0361"><p class="f_desc">롯데몰군산점</p></a>
												</div>
										<script type="text/javascript">
										  	var title = document.querySelector(".cont_wrap .tit_popup .scroll_area a[class=active] .f_desc").innerText;
											document.querySelector(".cont_wrap .inner .f_h1").prepend(title);
										</script>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="m_pop_dimd"></div>
		</div>
		<div class="page_cont_area no_pt">
			<div class="lecture_slide_area">
				<div class="inner">
					<div class="lecture_img_swiper">
						<div class="swiper-container">
							<div class="swiper-wrapper">
								<div class="swiper-slide card_list_img">
										<a class="card_img" href="/application/exhibition/view.do?exhSeqno=536">
											<div class="img_resize_w img">
												<img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/10/202310190258476520.png" alt="JANDI_Screenshot_2023-10-19 14.45.21.png">
											</div>
											<div class="txt_box">
												<p class="subject f_h2">Cosy Winter</p>
												<p class="course f_body1">겨울이라는 계절</p>
											</div>
										</a>
									</div>
								<div class="swiper-slide card_list_img">
										<a class="card_img" href="/application/exhibition/view.do?exhSeqno=537">
											<div class="img_resize_w img">
												<img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/10/202310190304391840.png" alt="JANDI_Screenshot_2023-10-19 15.03.28.png">
											</div>
											<div class="txt_box">
												<p class="subject f_h2">Digging Momentum</p>
												<p class="course f_body1">몰입에 진심인 사람들</p>
											</div>
										</a>
									</div>
								<div class="swiper-slide card_list_img">
										<a class="card_img" href="/application/exhibition/view.do?exhSeqno=552">
											<div class="img_resize_w img">
												<img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/10/202310231219145370.png" alt="JANDI_Screenshot_2023-10-23 12.15.46.png">
											</div>
											<div class="txt_box">
												<p class="subject f_h2">New Year, New Life</p>
												<p class="course f_body1">2024년을 준비하는 시간</p>
											</div>
										</a>
									</div>
								<div class="swiper-slide card_list_img">
										<a class="card_img" href="/application/exhibition/view.do?exhSeqno=559">
											<div class="img_resize_w img">
												<img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/10/202310230335519210.png" alt="JANDI_Screenshot_2023-10-23 12.40.16.png">
											</div>
											<div class="txt_box">
												<p class="subject f_h2">브랜드와 함께하는 원데이 클래스</p>
												<p class="course f_body1">일상에 아름다움을 더하는 시간</p>
											</div>
										</a>
									</div>
								<div class="swiper-slide card_list_img">
										<a class="card_img" href="/application/exhibition/view.do?exhSeqno=553">
											<div class="img_resize_w img">
												<img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/10/202310231225235960.png" alt="JANDI_Screenshot_2023-10-20 10.02.52.png">
											</div>
											<div class="txt_box">
												<p class="subject f_h2">Happy Christmas</p>
												<p class="course f_body1">소중한 추억 만들기</p>
											</div>
										</a>
									</div>
								<div class="swiper-slide card_list_img">
										<a class="card_img" href="/application/exhibition/view.do?exhSeqno=361">
											<div class="img_resize_w img">
												<img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/10/202310241049179785.png" alt="JANDI_Screenshot_2023-10-24 10.47.58.png">
											</div>
											<div class="txt_box">
												<p class="subject f_h2">꿈을 키우는 키즈 랜드</p>
												<p class="course f_body1">상상력을 키워주는 체험학습</p>
											</div>
										</a>
									</div>
								<div class="swiper-slide card_list_img">
										<a class="card_img" href="/application/exhibition/view.do?exhSeqno=111">
											<div class="img_resize_w img">
												<img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/10/202310241107015332.png" alt="JANDI_Screenshot_2023-10-24 11.04.58.png">
											</div>
											<div class="txt_box">
												<p class="subject f_h2">꾸러기 호기심 탐험대</p>
												<p class="course f_body1">현장에서 만나는 신나는 체험학습</p>
											</div>
										</a>
									</div>
								</div>
							<div class="swiper-pagination"></div>
						</div>								
					</div>
				</div>
			</div>
			<div class="inner">
							<div class="filter_bar_area">
								<div class="tab_func_area">
									<div class="border_tab_area tab_btn_area">
										<div class="swiper-container">
											<div class="swiper-wrapper">
												<a href="javascript:search.oneDepthCtgy_click('');" class="btn swiper-slide on total" ><span>전체</span></a>
												<a href="javascript:search.oneDepthCtgy_click('01');" class="btn swiper-slide " ><span>성인강좌</span></a>
													<a href="javascript:search.oneDepthCtgy_click('02');" class="btn swiper-slide " ><span>영·유아강좌</span></a>
													<a href="javascript:search.oneDepthCtgy_click('03');" class="btn swiper-slide " ><span>아동강좌</span></a>
													</div>
										</div>
									</div>
									<div class="tab_con_area">
										<div class="circle_sel_swiper con ">
													<div class="swiper-container">
														<div class="swiper-wrapper">
															<a href="javascript:search.twoDepthCtgy_click('');" class="swiper-slide total on">
																<p class="img only_pc img_resize_w"><img src="/common/images/img-two-depth-circle-00.jpg" alt=""></p>
																<p class="img only_mobile img_resize_w"><img src="/common/images/img-two-depth-circle-00-m.jpg" alt=""></p>
																<p class="txt">전체</p>
															</a>
												<a href="javascript:search.twoDepthCtgy_click('0101');" class="swiper-slide ">
																<p class="img img_resize_w"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260533029151.jpg" alt="메인_성인_공예.jpg"></p>
																<p class="txt">공예</p>
															</a>
																
													<a href="javascript:search.twoDepthCtgy_click('0102');" class="swiper-slide ">
																<p class="img img_resize_w"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260533193383.jpg" alt="메인_성인_노래.jpg"></p>
																<p class="txt">노래</p>
															</a>
																
													<a href="javascript:search.twoDepthCtgy_click('0103');" class="swiper-slide ">
																<p class="img img_resize_w"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260533306673.jpg" alt="메인_성인_댄스.jpg"></p>
																<p class="txt">댄스</p>
															</a>
																
													<a href="javascript:search.twoDepthCtgy_click('0104');" class="swiper-slide ">
																<p class="img img_resize_w"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260542406471.jpg" alt="메인_성인_드로잉.jpg"></p>
																<p class="txt">드로잉</p>
															</a>
																
													<a href="javascript:search.twoDepthCtgy_click('0105');" class="swiper-slide ">
																<p class="img img_resize_w"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260545030363.jpg" alt="메인_성인_라이프스타일.jpg"></p>
																<p class="txt">라이프스타일</p>
															</a>
																
													<a href="javascript:search.twoDepthCtgy_click('0106');" class="swiper-slide ">
																<p class="img img_resize_w"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260546325223.jpg" alt="메인_성인_악기.jpg"></p>
																<p class="txt">악기</p>
															</a>
																
													<a href="javascript:search.twoDepthCtgy_click('0107');" class="swiper-slide ">
																<p class="img img_resize_w"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260547571393.jpg" alt="메인_성인_어학.jpg"></p>
																<p class="txt">어학</p>
															</a>
																
													<a href="javascript:search.twoDepthCtgy_click('0108');" class="swiper-slide ">
																<p class="img img_resize_w"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260549229583.jpg" alt="메인_성인_인문학.jpg"></p>
																<p class="txt">인문학</p>
															</a>
																
													<a href="javascript:search.twoDepthCtgy_click('0109');" class="swiper-slide ">
																<p class="img img_resize_w"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260551002553.jpg" alt="메인_성인_재테크.jpg"></p>
																<p class="txt">재테크</p>
															</a>
																
													<a href="javascript:search.twoDepthCtgy_click('0111');" class="swiper-slide ">
																<p class="img img_resize_w"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260554154173.jpg" alt="메인_성인_쿠킹.jpg"></p>
																<p class="txt">쿠킹</p>
															</a>
																
													<a href="javascript:search.twoDepthCtgy_click('0112');" class="swiper-slide ">
																<p class="img img_resize_w"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260556304753.jpg" alt="메인_성인_피트니스.jpg"></p>
																<p class="txt">피트니스</p>
															</a>
																
													</div>
													</div>
													<button type="button" class="drop_btn"></button>
												</div>
											<div class="circle_sel_swiper con ">
													<div class="swiper-container">
														<div class="swiper-wrapper">
															<a href="javascript:search.twoDepthCtgy_click('');" class="swiper-slide total on">
																<p class="img only_pc img_resize_w"><img src="/common/images/img-two-depth-circle-00.jpg" alt=""></p>
																<p class="img only_mobile img_resize_w"><img src="/common/images/img-two-depth-circle-00-m.jpg" alt=""></p>
																<p class="txt">전체</p>
															</a>
												<a href="javascript:search.twoDepthCtgy_click('0202');" class="swiper-slide ">
																<p class="img img_resize_w"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260558211673.jpg" alt="메인_영유아_오감발달.jpg"></p>
																<p class="txt">오감발달</p>
															</a>
																
													<a href="javascript:search.twoDepthCtgy_click('0201');" class="swiper-slide ">
																<p class="img img_resize_w"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260558354653.jpg" alt="메인_아동_창의체험.jpg"></p>
																<p class="txt">창의·체험</p>
															</a>
																
													<a href="javascript:search.twoDepthCtgy_click('0203');" class="swiper-slide ">
																<p class="img img_resize_w"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260558478873.jpg" alt="메인_영유아_음악미술.jpg"></p>
																<p class="txt">음악·미술</p>
															</a>
																
													<a href="javascript:search.twoDepthCtgy_click('0204');" class="swiper-slide ">
																<p class="img img_resize_w"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260559050132.jpg" alt="메인_영유아_언어사회성.jpg"></p>
																<p class="txt">언어·사회성</p>
															</a>
																
													<a href="javascript:search.twoDepthCtgy_click('0205');" class="swiper-slide ">
																<p class="img img_resize_w"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260559206703.jpg" alt="메인_영유아_신체활동.jpg"></p>
																<p class="txt">신체활동</p>
															</a>
																
													</div>
													</div>
													<button type="button" class="drop_btn"></button>
												</div>
											<div class="circle_sel_swiper con ">
													<div class="swiper-container">
														<div class="swiper-wrapper">
															<a href="javascript:search.twoDepthCtgy_click('');" class="swiper-slide total on">
																<p class="img only_pc img_resize_w"><img src="/common/images/img-two-depth-circle-00.jpg" alt=""></p>
																<p class="img only_mobile img_resize_w"><img src="/common/images/img-two-depth-circle-00-m.jpg" alt=""></p>
																<p class="txt">전체</p>
															</a>
												<a href="javascript:search.twoDepthCtgy_click('0302');" class="swiper-slide ">
																<p class="img img_resize_w"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260559321243.jpg" alt="메인_아동_신체활동.jpg"></p>
																<p class="txt">신체활동</p>
															</a>
																
													<a href="javascript:search.twoDepthCtgy_click('0303');" class="swiper-slide ">
																<p class="img img_resize_w"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260559439523.jpg" alt="메인_영유아_창의체험.jpg"></p>
																<p class="txt">창의·체험</p>
															</a>
																
													<a href="javascript:search.twoDepthCtgy_click('0301');" class="swiper-slide ">
																<p class="img img_resize_w"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260600003284.jpg" alt="메인_아동_음악미술.jpg"></p>
																<p class="txt">음악·미술</p>
															</a>
																
													<a href="javascript:search.twoDepthCtgy_click('0304');" class="swiper-slide ">
																<p class="img img_resize_w"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260600132862.jpg" alt="메인_아동_언어사회성.jpg"></p>
																<p class="txt">언어·사회성</p>
															</a>
																
													</div>
													</div>
													<button type="button" class="drop_btn"></button>
												</div>
											</div>
								</div>
								<div class="fixed_mobile_w">
									<div class="fixed_mobile">
										<div class="thr_dep_area_w">
											
											<div class="thr_dep_area " data-mdcls-ctegry-cd="0101"><!-- on이 붙으면 보임  -->
																<div class="swiper-container">
																	<div class="swiper-wrapper">
																		<a href="javascript:search.threeDepthCtgy_click('');" class="swiper-slide on"><span>전체</span></a>
															<a href="javascript:search.threeDepthCtgy_click('010101');" class="swiper-slide "><span>플라워</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010102');" class="swiper-slide "><span>도예</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010103');" class="swiper-slide "><span>가죽</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010104');" class="swiper-slide "><span>캔들/비누</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010105');" class="swiper-slide "><span>자수</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010106');" class="swiper-slide "><span>나무/라탄</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010108');" class="swiper-slide "><span>기타</span></a>
																</div>
																	</div>
																</div>
														<div class="thr_dep_area " data-mdcls-ctegry-cd="0102"><!-- on이 붙으면 보임  -->
																<div class="swiper-container">
																	<div class="swiper-wrapper">
																		<a href="javascript:search.threeDepthCtgy_click('');" class="swiper-slide on"><span>전체</span></a>
															<a href="javascript:search.threeDepthCtgy_click('010201');" class="swiper-slide "><span>노래교실</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010203');" class="swiper-slide "><span>성악</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010204');" class="swiper-slide "><span>기타</span></a>
																</div>
																	</div>
																</div>
														<div class="thr_dep_area " data-mdcls-ctegry-cd="0103"><!-- on이 붙으면 보임  -->
																<div class="swiper-container">
																	<div class="swiper-wrapper">
																		<a href="javascript:search.threeDepthCtgy_click('');" class="swiper-slide on"><span>전체</span></a>
															<a href="javascript:search.threeDepthCtgy_click('010302');" class="swiper-slide "><span>라인댄스</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010304');" class="swiper-slide "><span>한국무용</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010305');" class="swiper-slide "><span>밸리댄스</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010306');" class="swiper-slide "><span>기타</span></a>
																</div>
																	</div>
																</div>
														<div class="thr_dep_area " data-mdcls-ctegry-cd="0104"><!-- on이 붙으면 보임  -->
																<div class="swiper-container">
																	<div class="swiper-wrapper">
																		<a href="javascript:search.threeDepthCtgy_click('');" class="swiper-slide on"><span>전체</span></a>
															<a href="javascript:search.threeDepthCtgy_click('010401');" class="swiper-slide "><span>유화</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010403');" class="swiper-slide "><span>색연필</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010404');" class="swiper-slide "><span>수채화</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010405');" class="swiper-slide "><span>서예</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010406');" class="swiper-slide "><span>데셍</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010407');" class="swiper-slide "><span>민화</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010409');" class="swiper-slide "><span>캘리그라피</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010408');" class="swiper-slide "><span>기타</span></a>
																</div>
																	</div>
																</div>
														<div class="thr_dep_area " data-mdcls-ctegry-cd="0105"><!-- on이 붙으면 보임  -->
																<div class="swiper-container">
																	<div class="swiper-wrapper">
																		<a href="javascript:search.threeDepthCtgy_click('');" class="swiper-slide on"><span>전체</span></a>
															<a href="javascript:search.threeDepthCtgy_click('010502');" class="swiper-slide "><span>뷰티</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010505');" class="swiper-slide "><span>명상</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010506');" class="swiper-slide "><span>기타</span></a>
																</div>
																	</div>
																</div>
														<div class="thr_dep_area " data-mdcls-ctegry-cd="0106"><!-- on이 붙으면 보임  -->
																<div class="swiper-container">
																	<div class="swiper-wrapper">
																		<a href="javascript:search.threeDepthCtgy_click('');" class="swiper-slide on"><span>전체</span></a>
															<a href="javascript:search.threeDepthCtgy_click('010601');" class="swiper-slide "><span>건반악기</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010602');" class="swiper-slide "><span>관악기</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010603');" class="swiper-slide "><span>현악기</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010604');" class="swiper-slide "><span>타악기</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010605');" class="swiper-slide "><span>기타</span></a>
																</div>
																	</div>
																</div>
														<div class="thr_dep_area " data-mdcls-ctegry-cd="0107"><!-- on이 붙으면 보임  -->
																<div class="swiper-container">
																	<div class="swiper-wrapper">
																		<a href="javascript:search.threeDepthCtgy_click('');" class="swiper-slide on"><span>전체</span></a>
															<a href="javascript:search.threeDepthCtgy_click('010701');" class="swiper-slide "><span>영어</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010702');" class="swiper-slide "><span>중국어</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010703');" class="swiper-slide "><span>일본어</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010705');" class="swiper-slide "><span>기타</span></a>
																</div>
																	</div>
																</div>
														<div class="thr_dep_area " data-mdcls-ctegry-cd="0108"><!-- on이 붙으면 보임  -->
																<div class="swiper-container">
																	<div class="swiper-wrapper">
																		<a href="javascript:search.threeDepthCtgy_click('');" class="swiper-slide on"><span>전체</span></a>
															<a href="javascript:search.threeDepthCtgy_click('010801');" class="swiper-slide "><span>미술사</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010802');" class="swiper-slide "><span>음악사</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010803');" class="swiper-slide "><span>문학</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010804');" class="swiper-slide "><span>철학</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010805');" class="swiper-slide "><span>사주명리학</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010806');" class="swiper-slide "><span>기타</span></a>
																</div>
																	</div>
																</div>
														<div class="thr_dep_area " data-mdcls-ctegry-cd="0109"><!-- on이 붙으면 보임  -->
																<div class="swiper-container">
																	<div class="swiper-wrapper">
																		<a href="javascript:search.threeDepthCtgy_click('');" class="swiper-slide on"><span>전체</span></a>
															<a href="javascript:search.threeDepthCtgy_click('010901');" class="swiper-slide "><span>주식</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010902');" class="swiper-slide "><span>부동산</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010903');" class="swiper-slide "><span>금융지식</span></a>
																<a href="javascript:search.threeDepthCtgy_click('010905');" class="swiper-slide "><span>기타</span></a>
																</div>
																	</div>
																</div>
														<div class="thr_dep_area " data-mdcls-ctegry-cd="0111"><!-- on이 붙으면 보임  -->
																<div class="swiper-container">
																	<div class="swiper-wrapper">
																		<a href="javascript:search.threeDepthCtgy_click('');" class="swiper-slide on"><span>전체</span></a>
															<a href="javascript:search.threeDepthCtgy_click('011101');" class="swiper-slide "><span>한식</span></a>
																<a href="javascript:search.threeDepthCtgy_click('011102');" class="swiper-slide "><span>일식/중식</span></a>
																<a href="javascript:search.threeDepthCtgy_click('011103');" class="swiper-slide "><span>양식</span></a>
																<a href="javascript:search.threeDepthCtgy_click('011106');" class="swiper-slide "><span>베이킹/디저트</span></a>
																<a href="javascript:search.threeDepthCtgy_click('011107');" class="swiper-slide "><span>음료/술</span></a>
																<a href="javascript:search.threeDepthCtgy_click('011108');" class="swiper-slide "><span>기타</span></a>
																</div>
																	</div>
																</div>
														<div class="thr_dep_area " data-mdcls-ctegry-cd="0112"><!-- on이 붙으면 보임  -->
																<div class="swiper-container">
																	<div class="swiper-wrapper">
																		<a href="javascript:search.threeDepthCtgy_click('');" class="swiper-slide on"><span>전체</span></a>
															<a href="javascript:search.threeDepthCtgy_click('011201');" class="swiper-slide "><span>요가</span></a>
																<a href="javascript:search.threeDepthCtgy_click('011202');" class="swiper-slide "><span>필라테스</span></a>
																<a href="javascript:search.threeDepthCtgy_click('011203');" class="swiper-slide "><span>발레</span></a>
																<a href="javascript:search.threeDepthCtgy_click('011204');" class="swiper-slide "><span>스트레칭</span></a>
																<a href="javascript:search.threeDepthCtgy_click('011205');" class="swiper-slide "><span>자세교정</span></a>
																<a href="javascript:search.threeDepthCtgy_click('011206');" class="swiper-slide "><span>기타</span></a>
																</div>
																	</div>
																</div>
														<div class="thr_dep_area " data-mdcls-ctegry-cd="0202"><!-- on이 붙으면 보임  -->
																<div class="swiper-container">
																	<div class="swiper-wrapper">
																		<a href="javascript:search.threeDepthCtgy_click('');" class="swiper-slide on"><span>전체</span></a>
															</div>
																	</div>
																</div>
														<div class="thr_dep_area " data-mdcls-ctegry-cd="0201"><!-- on이 붙으면 보임  -->
																<div class="swiper-container">
																	<div class="swiper-wrapper">
																		<a href="javascript:search.threeDepthCtgy_click('');" class="swiper-slide on"><span>전체</span></a>
															</div>
																	</div>
																</div>
														<div class="thr_dep_area " data-mdcls-ctegry-cd="0203"><!-- on이 붙으면 보임  -->
																<div class="swiper-container">
																	<div class="swiper-wrapper">
																		<a href="javascript:search.threeDepthCtgy_click('');" class="swiper-slide on"><span>전체</span></a>
															</div>
																	</div>
																</div>
														<div class="thr_dep_area " data-mdcls-ctegry-cd="0204"><!-- on이 붙으면 보임  -->
																<div class="swiper-container">
																	<div class="swiper-wrapper">
																		<a href="javascript:search.threeDepthCtgy_click('');" class="swiper-slide on"><span>전체</span></a>
															</div>
																	</div>
																</div>
														<div class="thr_dep_area " data-mdcls-ctegry-cd="0205"><!-- on이 붙으면 보임  -->
																<div class="swiper-container">
																	<div class="swiper-wrapper">
																		<a href="javascript:search.threeDepthCtgy_click('');" class="swiper-slide on"><span>전체</span></a>
															</div>
																	</div>
																</div>
														<div class="thr_dep_area " data-mdcls-ctegry-cd="0302"><!-- on이 붙으면 보임  -->
																<div class="swiper-container">
																	<div class="swiper-wrapper">
																		<a href="javascript:search.threeDepthCtgy_click('');" class="swiper-slide on"><span>전체</span></a>
															</div>
																	</div>
																</div>
														<div class="thr_dep_area " data-mdcls-ctegry-cd="0303"><!-- on이 붙으면 보임  -->
																<div class="swiper-container">
																	<div class="swiper-wrapper">
																		<a href="javascript:search.threeDepthCtgy_click('');" class="swiper-slide on"><span>전체</span></a>
															</div>
																	</div>
																</div>
														<div class="thr_dep_area " data-mdcls-ctegry-cd="0301"><!-- on이 붙으면 보임  -->
																<div class="swiper-container">
																	<div class="swiper-wrapper">
																		<a href="javascript:search.threeDepthCtgy_click('');" class="swiper-slide on"><span>전체</span></a>
															</div>
																	</div>
																</div>
														<div class="thr_dep_area " data-mdcls-ctegry-cd="0304"><!-- on이 붙으면 보임  -->
																<div class="swiper-container">
																	<div class="swiper-wrapper">
																		<a href="javascript:search.threeDepthCtgy_click('');" class="swiper-slide on"><span>전체</span></a>
															</div>
																	</div>
																</div>
														</div>
										
										<div class="filter_bar_div">
											<div class="left">
												<p class="f_caption2"><span class="point" id="totCnt">0개</span>의 강좌</p>
											</div>
											<div class="right">
												<div class="btn_area">
													<div class="btn_wrap srch_btn_wrap">
														<a href="javascript:search.popResize();" class="btn detail_srch_btn " title="상세검색 팝업 열기"><span>상세검색</span></a>
														<!-- <div class="info_box">
															<p class="info_txt">상세검색을 통해 원하시는 지점과 강좌를 찾아보세요.</p>
															<button type="button" class="close_btn"><img src="/common/images/btn-detail-search-close.svg" alt=""></button>
														</div> -->
													</div>
													<div class="btn_wrap">
														<div class="filter_open_area">
														
															<a href="javascript:" class="btn order_btn filter_popup_btn" title="컨텐츠정렬 팝업 열기">
																<div class="order_txt">강의시작일순</div>
															</a>
														</div>
														<div class="dimd"></div>
														<div class="filter_list_wrap">
															<div class="tit_area">
																<p class="tit"></p>
																<a href="javascript:" role="button" class="close"></a>
															</div>
															<div class="scroll_wrap">
																<a class="txt f_caption2 " href="javascript:search.order('A');">마감임박순</a>
																<a class="txt f_caption2 " href="javascript:search.order('B');">접수인원순</a>
																<a class="txt f_caption2 on" href="javascript:search.order('C');">강의시작일순</a>
																<a class="txt f_caption2 " href="javascript:search.order('D');">낮은가격순</a>
																<a class="txt f_caption2 " href="javascript:search.order('E');">높은가격순</a>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										
										</div>
								</div>
							</div>
							<div class="list_box_div trans_direction_div" id="listContainer">
								
								
							</div>

							<div class="more_btn_w" id="moreBtn" style="display:none;"><!-- 2022-11-29 class 수정 -->
								<a href="javascript:" class="more_btn no_motion">
									<span>강좌 더보기 <strong class="total"></strong></span>
								</a>
							</div>

							<div class="srch_popup">
								<div class="dimd"></div>
								<div class="pop_wrap">
									<div class="for_padding">
										<div class="scroll_area">
											<div class="srch_con">
												<p class="tit f_body1">상세검색</p>
												<div class="srch_detail_area">
													<div class="form_search">
														<input type="text" name="q" onkeyup="if(window.event.keyCode == 13) search.search_btn();" placeholder="강좌명 or 강사명으로 검색" title="강좌명 or 강사명으로 검색입력" value="">
														<div class="input_btn_wrap">
															<button type="button" class="btn_delete" title="지우기"></button>
															<button type="button" class="btn_search" title="검색" onclick="search.search_btn();"></button>
														</div>
													</div>
												</div>
												<div class="accordion_type">
													<div class="list_div ">
														<a class="list" href="javascript:">
															<div class="txt_box hide">
																<p class="f_body4">학기</p>
																<span class="num">1</span>
															</div>
														</a>
														<div class="hide_con">
															<div class="filter_btn_list" id="yyListArea">
																<a href="javascript:" class="btn " data-yy="2023" data-lect-smster-cd="4"><span>2023년 겨울학기</span></a>
																<a href="javascript:" class="btn " data-yy="2023" data-lect-smster-cd="3"><span>2023년 가을학기</span></a>
																</div>
														</div>
													</div>
													<div class="list_div ">
														<a class="list" href="javascript:">
															<div class="txt_box hide">
																<p class="f_body4">강좌구분</p>
																<span class="num">1</span>
															</div>
														</a>
														<div class="hide_con">
															<div class="filter_btn_list" id="lectClCdListArea">
																<a href="javascript:" data-lect-cl-cd="1" class="btn "><span>정기</span></a>
																<a href="javascript:" data-lect-cl-cd="2" class="btn "><span>단기</span></a>
																<a href="javascript:" data-lect-cl-cd="3" class="btn "><span>특강</span></a>
																</div>
														</div>
													</div>
													<div class="list_div ">
														<a class="list" href="javascript:">
															<div class="txt_box hide">
																<p class="f_body4">강좌상태</p>
																<span class="num">1</span>
															</div>
														</a>
														<div class="hide_con">
															<div class="filter_btn_list" id="lectStatCdListArea">
																<a href="javascript:" class="btn " data-lect-stat-cd="01"><span>접수예정</span></a>
																	<a href="javascript:" class="btn " data-lect-stat-cd="02"><span>기존회원접수</span></a>
																	<a href="javascript:" class="btn " data-lect-stat-cd="03"><span>접수중</span></a>
																	<a href="javascript:" class="btn " data-lect-stat-cd="04"><span>지점문의</span></a>
																	<a href="javascript:" class="btn " data-lect-stat-cd="05"><span>대기접수</span></a>
																	<a href="javascript:" class="btn " data-lect-stat-cd="06"><span>접수마감</span></a>
																	<a href="javascript:" class="btn " data-lect-stat-cd="07"><span>강의종료</span></a>
																	<a href="javascript:" class="btn " data-lect-stat-cd="08"><span>접수불가</span></a>
																	</div>
														</div>
													</div>
													<div class="list_div ">
														<a class="list" href="javascript:">
															<div class="txt_box hide">
																<p class="f_body4">요일</p>
																<span class="num">1</span>
															</div>
														</a>
														<div class="hide_con">
															<div class="filter_btn_list" id="stDaywCdListArea">
																<p class="txt_box f_caption1">평일</p>
																	<a href="javascript:" class="btn " data-st-dayw-cd="1"><span>월요일</span></a>
																	<a href="javascript:" class="btn " data-st-dayw-cd="2"><span>화요일</span></a>
																	<a href="javascript:" class="btn " data-st-dayw-cd="3"><span>수요일</span></a>
																	<a href="javascript:" class="btn " data-st-dayw-cd="4"><span>목요일</span></a>
																	<a href="javascript:" class="btn " data-st-dayw-cd="5"><span>금요일</span></a>
																	<p class="txt_box f_caption1">주말</p>
																	<a href="javascript:" class="btn " data-st-dayw-cd="6"><span>토요일</span></a>
																	<a href="javascript:" class="btn " data-st-dayw-cd="7"><span>일요일</span></a>
															</div>
														</div>
													</div>
													<div class="list_div ">
														<a class="list" href="javascript:">
															<div class="txt_box hide">
																<p class="f_body4">시간대</p>
																<span class="num">1</span>
															</div>
														</a>
														<div class="hide_con">
															<div class="filter_btn_list" id="timeTypeListArea">
																<p class="txt_box f_caption1">오전</p>
																<a href="javascript:" class="btn " data-time-type="A"><span>~ 10:00</span></a>
																<a href="javascript:" class="btn "" data-time-type="B"><span>10:00 ~ 12:00</span></a>
																<p class="txt_box f_caption1">오후</p>
																	<a href="javascript:" class="btn "" data-time-type="C"><span>12:00 ~ 14:00</span></a>
																	<a href="javascript:" class="btn "" data-time-type="D"><span>14:00 ~ 16:00</span></a>
																	<a href="javascript:" class="btn "" data-time-type="E"><span>16:00 ~ 18:00</span></a>
																	<a href="javascript:" class="btn "" data-time-type="F"><span>18:00 ~ 20:00</span></a>
																	<a href="javascript:" class="btn "" data-time-type="G"><span>20:00 ~</span></a>
															</div>
														</div>
													</div>
													<div class="list_div ">
														<a class="list" href="javascript:">
															<div class="txt_box hide">
																<p class="f_body4">수강료</p>
																
																<span class="num">0</span>
															</div>
														</a>
														<div class="hide_con">
															<div class="filter_btn_list" id="amtTypeListArea">
																<a href="javascript:" class="btn " data-amt-type="A"><span>무료</span></a>
																<a href="javascript:" class="btn " data-amt-type="B"><span>5천원 이하</span></a>
																<a href="javascript:" class="btn " data-amt-type="C"><span>5천원 ~ 1만원 이하</span></a>
																<a href="javascript:" class="btn " data-amt-type="D"><span>1만원 ~ 3만원 이하</span></a>
																<a href="javascript:" class="btn " data-amt-type="E"><span>3만원 ~ 5만원 이하</span></a>
																<a href="javascript:" class="btn " data-amt-type="F"><span>5만원 ~ 10만원 이하</span></a>
																<a href="javascript:" class="btn " data-amt-type="G"><span>10만원 이상</span></a>
																<p class="txt_box f_caption1">직접입력</p>
																
																<div class="price_input_wrap btn">
																	<div class="form_input">
																		<input type="text" title="검색될 최소 금액 입력" placeholder="최소금액" value="" maxlength="14" name="stAmt" oninput="this.value=this.value.replace(/[^0-9]/g,'');this.value=fnc.fn_numberComma(this.value);">
																	</div>
																	<span class="tilde">~</span>
																	<div class="form_input">
																		<input type="text" title="검색될 최대 금액 입력" placeholder="최대금액" value="" maxlength="14" name="endAmt" oninput="this.value=this.value.replace(/[^0-9]/g,'');this.value=fnc.fn_numberComma(this.value);">
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
												<a class="btn_close" href="javascript:" title="닫기"><span class="blind"></span></a>
											</div>
										</div>
										
										<div class="reset_wrap">
											<a href="javascript:" class="reset_btn">
												<span>초기화</span>
											</a>
											<a class="filter_btn" href="javascript:search.search_btn();">
												<span>강좌 보기</span>
											</a>
										</div>
									</div>
								</div>
							</div>
						</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="/common/js/application/search/searchList_branch.js"></script>