<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header class="pc">
	<div class="logo">
		<a href="/index.do" title="롯데문화센터 메인으로 이동"></a>
	</div>

	<nav>
		<div class="for_bg"></div>
		<div class="gnb_w">
			<ul class="gnb">
				<li><a class="one_depth " href="javascript:">수강신청</a>
					<div class="two_pack">
						<ul class="two_dep_div">
							<li><a class="two_depth class_srch" href="javascript:"target="_self">강좌검색</a></li>
							<li><a class="two_depth " href="/information/guide/settle/list.do" target="_self">수강신청·변경·취소안내</a></li>
							<li><a class="two_depth " href="/information/guide/online/list.do" target="_self">온라인 신청가이드</a></li>
						</ul>
						<div class="img_div">
							<p class="img on">
								<img src="/resources/common/images/img-gnb-use-information-01.jpg" alt="">
							</p>
							<p class="img on">
								<img src="/resources/common/images/img-gnb-use-information-02.jpg" alt="">
							</p>
							<p class="img on">
								<img src="/resources/common/images/img-gnb-use-information-03.jpg" alt="">
							</p>
						</div>
					</div></li>
				<li><a class="one_depth " href="javascript:">이용안내</a>
					<div class="two_pack">
						<ul class="two_dep_div">
							<li><a class="two_depth " href="/information/branch/list.do" target="_self">지점안내</a></li>
							<li><a class="two_depth " href="/information/application/index.do" target="_self">강사지원·제휴신청</a></li>
							<li><a class="two_depth " href="/information/faq/list.do" target="_self">자주하는 문의</a></li>
						</ul>
						<div class="img_div">
							<p class="img on">
								<img src="/resources/common/images/img-gnb-use-information-01.jpg" alt="">
							</p>
							<p class="img on">
								<img src="/resources/common/images/img-gnb-use-information-02.jpg" alt="">
							</p>
							<p class="img on">
								<img src="/resources/common/images/img-gnb-use-information-03.jpg" alt="">
							</p>
						</div>
					</div></li>
				<li><a class="one_depth " href="javascript:">커뮤니티</a>
					<div class="two_pack">
						<ul class="two_dep_div">
							<li><a class="two_depth " href="/community/notice/list.do" target="_self">공지사항/이벤트</a></li>
							<li><a class="two_depth " href="/community/review/list.do" target="_self">수강후기</a></li>
							<li><a class="two_depth " href="/community/magazine/list.do" target="_self">LIFESTYLE LAB 매거진</a></li>
						</ul>
						<div class="img_div">
							<p class="img on">
								<img src="/resources/common/images/img-gnb-community-01.jpg" alt="">
							</p>
							<p class="img on">
								<img src="/resources/common/images/img-gnb-community-02.jpg" alt="">
							</p>
							<p class="img on">
								<img src="/resources/common/images/img-gnb-community-03.jpg" alt="">
							</p>
						</div>
					</div></li>
			</ul>
		</div>
	</nav>
	<div class="util_area">
		<p class="srch_icon">
			<a href="javascript:" title="관심있는 강좌 검색"> 
				<span class="only_pc">관심있는 강좌를 찾아보세요</span> <span class="only_mobile">관심 강좌 찾기</span>
			</a>
		</p>
		<sec:authorize access="isAuthenticated()">
			<p class="mypage_icon">
				<a href="javascript:" title="마이페이지" onclick="common.getCount(this);"></a>
			</p>
		</sec:authorize>		
		
		<p class="cart_icon on">
			<!-- 장바구니에 담긴 갯수가 있을 경우 on class 추가 -->
			<sec:authorize access="isAuthenticated()">
				<a href="/mypage/cart/list.do" title="장바구니"></a>
				<span class="cart_num">2</span>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<a href="javascript:fnc.moveLoginPage(true);" title="장바구니"></a>
				<span class="cart_num">0</span>
			</sec:authorize>
		</p>
		
		<!-- 
		<p class="admin_icon">
			<a href="/administrator/index.do" title="관리자페이지"></a>
			<a href="javascript:fnc.moveLogout();" title="로그아웃"></a>
		</p>	
		 -->			
		 
		<sec:authorize access="isAnonymous()">
		  <p class="login_icon ">
		   	<a href="javascript:fnc.moveLoginPage();" title="로그인"></a>
		  </p>
		</sec:authorize>
		
		<sec:authorize access="isAuthenticated()">
		  <sec:authentication property="principal" var="principal"/>
		  <form action="/login/logout.do" method="post" id="frmLogout">			            
			  <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
			  <p class="login_icon logout">
				<a href="javascript:fnc.moveLogout();" title="로그아웃"></a>
			  </p>
		  </form> 
		</sec:authorize>
	</div>

	<div class="header_srch_pop_area">
		<p class="inner_dimd"></p>
		<div class="srch_con">
			<div class="srch_inner">
				<p class="f_h1">
					관심있는 강좌를 <br>찾아보세요
				</p>
				<div class="form_search_w">
					<div class="form_search">
						<div class="form_search_div">
							<input type="text" placeholder="검색어를 입력하세요"
								onkeyup="if(window.event.keyCode == 13) common.integrationSearch(this);">
							<div class="input_btn_wrap">
								<button type="button" class="btn_delete" title="지우기"></button>
								<button type="button" class="btn_search" title="검색" onclick="common.integrationSearch(this);"></button>
							</div>
						</div>
					</div>
				</div>

				<div class="recent_keyword_area" style="display: none;">
					<div class="top_area">
						<p class="tit">최근 검색어</p>
					</div>

					<div class="srch_data_div">
						<a href="javascript:common.integrationRemove('');" class="reset_data" role="button"><span>선택 초기화</span></a>
						<div class="srch_data_list swiper-container no_swiping">
							<div class="swiper-wrapper"></div>
						</div>
					</div>
				</div>
			</div>
			<a class="btn_close" href="javascript:"></a>
		</div>
	</div>

	<div class="mypage_pop_area">
		<p class="inner_dimd" style="display: none;"></p>
		<div class="for_padding">
			<div class="scroll_area">
				<div class="mypage_con">
					<a href="javascript:fnc.moveLogout()" class="logout_txt"><span>로그아웃</span></a>
					<div class="mypage_inner">
						<p class="name">
							<a href="/mypage/member/info.do"><span id="name"></span></a>
						</p>
						<div class="mypage_info_w">
							<a href="javascript:common.showItrstPop()" class="mypage_info">
								<p class="f_caption2">관심지점</p>
								<p class="info"><span id="Intrbranch"></span></p>
							</a>
							<a href="/mypage/coupon/list.do" class="mypage_info">
								<p class="f_caption2">나의 쿠폰</p>
								<p class="info"><span id="cpnCnt">0</span></p>
							</a>
							<a href="javascript:" class="mypage_info">
								<p class="f_caption2">POINT</p>
								<p class="info"><span id="pointSpan"></span></p>
							</a>
						</div>
						<div class="mypage_div">
							<a href="/mypage/cart/list.do" class="mypage_box on">
								<p class="icon"><img src="/resources/common/images/icon-mypage-class-cart.png" alt=""></p>
								<p class="f_caption2">장바구니</p>
								<p class="num" id="cartCnt"></p>
							</a>
							<a href="/mypage/atlct/list.do" class="mypage_box">
								<p class="icon"><img src="/resources/common/images/icon-mypage-class-history.png" alt=""></p>
								<p class="f_caption2">수강내역</p>
								<p class="num" id="atlctCnt"></p>
							</a>
							<a href="/mypage/waiting/list.do" class="mypage_box">
								<p class="icon"><img src="/resources/common/images/icon-mypage-waiting-list.png" alt=""></p>
								<p class="f_caption2">대기자조회</p>
								<p class="num" id="waitingCnt"></p>
							</a>
							<a href="/mypage/lectureCertf/list.do" class="mypage_box">
								<p class="icon"><img src="/resources/common/images/icon-mypage-class-certificate-of-study.png" alt=""></p>
								<p class="f_caption2">수강증</p>
								<p class="num" id="certfCnt"></p>
							</a>
							<a href="/mypage/myreview/list.do" class="mypage_box">
								<p class="icon"><img src="/resources/common/images/icon-mypage-class-review-course.png" alt=""></p>
								<p class="f_caption2">수강후기</p>
								<p class="num" id="rvwCnt"></p>
							</a>
							<a href="/mypage/teachereval/list.do" class="mypage_box eval">
								<p class="icon"><img src="/resources/common/images/icon-mypage-satisfaction-evaluation.png" alt=""></p>
								<p class="f_caption2">만족도 평가</p>
								<p class="num" id="tcevlCnt">0</p>
							</a>
						</div>
						<div class="mypage_list">
							<a href="/mypage/inquiry/list.do" class="list inquiry">
								<span class="f_body1">1:1 문의</span>
							</a>
							<a href="/mypage/freebie/appList.do" class="list freebie">
								<span class="f_body1">사은품 신청</span>
							</a>
							<a href="/mypage/freebie/detailList.do" class="list freebie_list">
								<span class="f_body1">사은품 신청내역</span>
							</a>
							</div>
					</div>
					<a class="btn_close" href="javascript:"></a>
				</div>
			</div>
		</div>
	</div>
	<div class="place_pop_area ">
		<p class="inner_dimd" style="display: none;"></p>
		<div class="for_padding">
			<div class="scroll_area">
				<div class="place_con">
					<div class="place_inner">
						<p class="title">나의 관심지점 설정</p>
						<div class="dot_txt_box">
							<p class="tit">관심지점은 1개만 선택 가능합니다</p>
							<p class="txt">설정하신 관심지점은 추천 강좌 안내 시에 활용되며, 관심지점 외에 지점에서 진행하는 강좌들도 정상적으로 수강 가능합니다.</p>
						</div>
						<div class="accordion_type fir" id="accordion_branch">
							<div class="list_div on">
								<a class="list" href="javascript:" role="button">
									<p class="f_body4">서울점</p>
								</a>
								<div class="hide_con">
									<div class="btn_flex_box">
										<a href="javascript:" data-cd="2" data-nm="잠실점" class="place_btn brchBtn "><span>잠실점</span></a> 
										<a href="javascript:" data-cd="1" data-nm="본점" class="place_btn brchBtn "><span>본점</span></a> 
										<a href="javascript:" data-cd="7" data-nm="강남점" class="place_btn brchBtn "><span>강남점</span></a> 
										<a href="javascript:" data-cd="8" data-nm="건대스타시티점" class="place_btn brchBtn "><span>건대스타시티점</span></a> 
										<a href="javascript:" data-cd="9" data-nm="관악점" class="place_btn brchBtn "><span>관악점</span></a> 
										<a href="javascript:" data-cd="10" data-nm="김포공항점" class="place_btn brchBtn "><span>김포공항점</span></a> 
										<a href="javascript:" data-cd="11" data-nm="노원점" class="place_btn brchBtn "><span>노원점</span></a> 
										<a href="javascript:" data-cd="12" data-nm="미아점" class="place_btn brchBtn "><span>미아점</span></a> 
										<a href="javascript:" data-cd="13" data-nm="영등포점" class="place_btn brchBtn "><span>영등포점</span></a> 
										<a href="javascript:" data-cd="14" data-nm="청량리점" class="place_btn brchBtn "><span>청량리점</span></a>
									</div>
								</div>
							</div>
							<div class="list_div on">
								<a class="list" href="javascript:" role="button">
									<p class="f_body4">수도권점</p>
								</a>
								<div class="hide_con">
									<div class="btn_flex_box">
										<a href="javascript:" data-cd="3" data-nm="인천점" class="place_btn brchBtn "><span>인천점</span></a> 
										<a href="javascript:" data-cd="4" data-nm="동탄점" class="place_btn brchBtn "><span>동탄점</span></a> 
										<a href="javascript:" data-cd="15" data-nm="구리점" class="place_btn brchBtn "><span>구리점</span></a> 
										<a href="javascript:" data-cd="16" data-nm="분당점" class="place_btn brchBtn "><span>분당점</span></a> 
										<a href="javascript:" data-cd="17" data-nm="수원점" class="place_btn brchBtn "><span>수원점</span></a> 
										<a href="javascript:" data-cd="18" data-nm="안산점" class="place_btn brchBtn "><span>안산점</span></a> 
										<a href="javascript:" data-cd="19" data-nm="일산점" class="place_btn brchBtn "><span>일산점</span></a> 
										<a href="javascript:" data-cd="20" data-nm="중동점" class="place_btn brchBtn "><span>중동점</span></a> 
										<a href="javascript:" data-cd="21" data-nm="평촌점" class="place_btn brchBtn "><span>평촌점</span></a> 
										<a href="javascript:" data-cd="22" data-nm="롯데몰광명점" class="place_btn brchBtn "><span>롯데몰광명점</span></a>
									</div>
								</div>
							</div>
							<div class="list_div on">
								<a class="list" href="javascript:" role="button">
									<p class="f_body4">지방점</p>
								</a>
								<div class="hide_con">
									<div class="btn_flex_box">
										<a href="javascript:" data-cd="5" data-nm="부산본점" class="place_btn brchBtn "><span>부산본점</span></a> 
										<a href="javascript:" data-cd="6" data-nm="광복점" class="place_btn brchBtn "><span>광복점</span></a> 
										<a href="javascript:" data-cd="23" data-nm="광주점" class="place_btn brchBtn "><span>광주점</span></a> 
										<a href="javascript:" data-cd="24" data-nm="대구점" class="place_btn brchBtn "><span>대구점</span></a> 
										<a href="javascript:" data-cd="25" data-nm="대전점" class="place_btn brchBtn "><span>대전점</span></a> 
										<a href="javascript:" data-cd="26" data-nm="동래점" class="place_btn brchBtn "><span>동래점</span></a> 
										<a href="javascript:" data-cd="27" data-nm="마산점" class="place_btn brchBtn "><span>마산점</span></a> 
										<a href="javascript:" data-cd="28" data-nm="상인점" class="place_btn brchBtn "><span>상인점</span></a> 
										<a href="javascript:" data-cd="29" data-nm="센텀시티점" class="place_btn brchBtn "><span>센텀시티점</span></a> 
										<a href="javascript:" data-cd="30" data-nm="울산점" class="place_btn brchBtn "><span>울산점</span></a> 
										<a href="javascript:" data-cd="31" data-nm="전주점" class="place_btn brchBtn "><span>전주점</span></a> 
										<a href="javascript:" data-cd="32" data-nm="창원점" class="place_btn brchBtn "><span>창원점</span></a> 
										<a href="javascript:" data-cd="33" data-nm="포항점" class="place_btn brchBtn "><span>포항점</span></a> 
										<a href="javascript:" data-cd="34" data-nm="롯데몰군산점" class="place_btn brchBtn "><span>롯데몰군산점</span></a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<a href="javascript:" class="prev_btn" onclick="$(this).next().click();"></a>
					<a href="javascript:" class="btn_close"></a>
				</div>
			</div>
			<div class="save_btn">
				<a href="javascript:common.saveItrst()"> <span>저장</span>
				</a>
			</div>
		</div>
	</div>
	
	<div class="srch_gate_pop_area">
		<div class="class_gate_w tab_func_area">
			<div class="top_area">
				<p class="title">수강신청</p>
			</div>
			<div class="scroll_area">
				<div class="inner">
					<div class="tab_btn_area">
						<a href="javascript:" class="btn on">
				          <span class="only_pc">지점으로 찾기</span>
				          <span class="only_mobile">지점</span>
				        </a>
				        <a href="javascript:" class="btn">
				          <span class="only_pc">강좌로 찾기</span>
				          <span class="only_mobile">강좌</span>
				          <!-- <span class="only_mobile" onclick="dataImg()">강좌</span> -->
				        </a>
					</div>
					<div class="tab_con_area">
						<div class="con on">
							<div class="content only_pc">
								<div class="list_w">

									<c:choose>
								        <c:when test="${not empty bmap}">
								            <c:forEach var="entry" items="${bmap}">
								                <div class="list">
								                    <p class="f_body3">${entry.key}</p>
								                    <div class="txt_w">
								                        <c:forEach var="dto" items="${entry.value}">
								                                <p class="txt">
								                                    <a href="/application/search/list.do?type=branch&brchCd=${dto.branch_id}" class="f_body1">${dto.branch_nm}</a>
								                                </p>
								                        </c:forEach>
								                    </div>
								                </div>
								            </c:forEach>
								        </c:when>
								    </c:choose>
              </div>
              
              <div class="img_w">
                <div class="img_div">
                
                	<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="_DSF8620.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="로비.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="_DSF9387.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="로비보정.JPG"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="로비_DSF9158 copy211.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="_DSF0050.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="_DSF1679.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="_DSF1444.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="DSF9351 999.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="_DSF0411.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="인천점_5.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="_DSF9863.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="구리5.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="_DSF8078.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="_DSF8406.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="as_LAB1.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="_DSF0220.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="5.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="5.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="_DSF9549.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="4. 9강의실.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="_DSF2623.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="7.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="요리교실.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="_DSF6576.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="_DSF5028.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="05 LAB1.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="_DSF7251.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="_DSF3215.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="_DSF8047.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="_DSF6247.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="_DSF5.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="_DSF8423.jpg"></p>
               			<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt="LAB6.jpg"></p>
               			</div>
              </div>
              
            </div>
            <div class="content only_mobile">
              <div class="accordion_type fir">
              
              	<div class="list_div on">
	                  <a class="list" href="javascript:" role="button">
	                    <p class="f_body4">서울점</p>
	                  </a>
	                  <div class="hide_con">
	                    <div class="btn_flex_box">
                    		<a href="javascript:" data-brch-cd="0002" class="place_btn brchBtn"><span>잠실점</span></a>
	                      	<a href="javascript:" data-brch-cd="0001" class="place_btn brchBtn"><span>본점</span></a>
	                      	<a href="javascript:" data-brch-cd="0013" class="place_btn brchBtn"><span>강남점</span></a>
	                      	<a href="javascript:" data-brch-cd="0028" class="place_btn brchBtn"><span>건대스타시티점</span></a>
	                      	<a href="javascript:" data-brch-cd="0006" class="place_btn brchBtn"><span>관악점</span></a>
	                      	<a href="javascript:" data-brch-cd="0340" class="place_btn brchBtn"><span>김포공항점</span></a>
	                      	<a href="javascript:" data-brch-cd="0022" class="place_btn brchBtn"><span>노원점</span></a>
	                      	<a href="javascript:" data-brch-cd="0026" class="place_btn brchBtn"><span>미아점</span></a>
	                      	<a href="javascript:" data-brch-cd="0010" class="place_btn brchBtn"><span>영등포점</span></a>
	                      	<a href="javascript:" data-brch-cd="0004" class="place_btn brchBtn"><span>청량리점</span></a>
	                      	</div>
	                  </div>
	                </div>
                <div class="list_div on">
	                  <a class="list" href="javascript:" role="button">
	                    <p class="f_body4">수도권점</p>
	                  </a>
	                  <div class="hide_con">
	                    <div class="btn_flex_box">
                    		<a href="javascript:" data-brch-cd="0344" class="place_btn brchBtn"><span>인천점</span></a>
	                      	<a href="javascript:" data-brch-cd="0399" class="place_btn brchBtn"><span>동탄점</span></a>
	                      	<a href="javascript:" data-brch-cd="0335" class="place_btn brchBtn"><span>구리점</span></a>
	                      	<a href="javascript:" data-brch-cd="0008" class="place_btn brchBtn"><span>분당점</span></a>
	                      	<a href="javascript:" data-brch-cd="0349" class="place_btn brchBtn"><span>수원점</span></a>
	                      	<a href="javascript:" data-brch-cd="0336" class="place_btn brchBtn"><span>안산점</span></a>
	                      	<a href="javascript:" data-brch-cd="0011" class="place_btn brchBtn"><span>일산점</span></a>
	                      	<a href="javascript:" data-brch-cd="0334" class="place_btn brchBtn"><span>중동점</span></a>
	                      	<a href="javascript:" data-brch-cd="0341" class="place_btn brchBtn"><span>평촌점</span></a>
	                      	<a href="javascript:" data-brch-cd="0350" class="place_btn brchBtn"><span>롯데몰광명점</span></a>
	                      	</div>
	                  </div>
	                </div>
                <div class="list_div on">
	                  <a class="list" href="javascript:" role="button">
	                    <p class="f_body4">지방점</p>
	                  </a>
	                  <div class="hide_con">
	                    <div class="btn_flex_box">
                    		<a href="javascript:" data-brch-cd="0005" class="place_btn brchBtn"><span>부산본점</span></a>
	                      	<a href="javascript:" data-brch-cd="0333" class="place_btn brchBtn"><span>광복점</span></a>
	                      	<a href="javascript:" data-brch-cd="0007" class="place_btn brchBtn"><span>광주점</span></a>
	                      	<a href="javascript:" data-brch-cd="0023" class="place_btn brchBtn"><span>대구점</span></a>
	                      	<a href="javascript:" data-brch-cd="0012" class="place_btn brchBtn"><span>대전점</span></a>
	                      	<a href="javascript:" data-brch-cd="0016" class="place_btn brchBtn"><span>동래점</span></a>
	                      	<a href="javascript:" data-brch-cd="0354" class="place_btn brchBtn"><span>마산점</span></a>
	                      	<a href="javascript:" data-brch-cd="0024" class="place_btn brchBtn"><span>상인점</span></a>
	                      	<a href="javascript:" data-brch-cd="0027" class="place_btn brchBtn"><span>센텀시티점</span></a>
	                      	<a href="javascript:" data-brch-cd="0015" class="place_btn brchBtn"><span>울산점</span></a>
	                      	<a href="javascript:" data-brch-cd="0025" class="place_btn brchBtn"><span>전주점</span></a>
	                      	<a href="javascript:" data-brch-cd="0017" class="place_btn brchBtn"><span>창원점</span></a>
	                      	<a href="javascript:" data-brch-cd="0014" class="place_btn brchBtn"><span>포항점</span></a>
	                      	<a href="javascript:" data-brch-cd="0361" class="place_btn brchBtn"><span>롯데몰군산점</span></a>
	                      	</div>
	                  </div>
	                </div>
                </div>
            </div>
            
            </div>
          <div class="con">
          	<div class="content only_pc">
              <div class="list_w">
              
              	<c:choose>
			        <c:when test="${not empty cmap}">
			            <c:forEach var="entry" items="${cmap}">
			                <div class="list">
			                    <p class="f_body3">${entry.key}</p>
			                    <div class="txt_w">
			                        <c:forEach var="dto" items="${entry.value}">
		                                <p class="txt">
		                                    <a href="/application/search/list.do?type=category&lrclsCtegryCd=${dto.lrclsCtegryCd}&mdclsCtegryCd=${dto.mdclsCtegryCd}" class="f_body1">${dto.mdclsCtegry}</a>
		                                </p>
			                        </c:forEach>
			                    </div>
			                </div>
			            </c:forEach>
			        </c:when>
			    </c:choose>
			    
	                </div>
              <div class="img_w">
                <div class="img_div">
                  
                  	<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_공예.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_노래.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_댄스.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_드로잉.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_라이프스타일.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_악기.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_어학.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_인문학.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_재테크.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_커리어.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_쿠킹.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_피트니스.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-lecture.jpg" alt="메인_영유아_오감발달.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-lecture.jpg" alt="메인_아동_창의체험.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-lecture.jpg" alt="메인_영유아_음악미술.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-lecture.jpg" alt="메인_영유아_언어사회성.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-lecture.jpg" alt="메인_영유아_신체활동.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-lecture.jpg" alt="메인_아동_신체활동.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-lecture.jpg" alt="메인_영유아_창의체험.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-lecture.jpg" alt="메인_아동_음악미술.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-lecture.jpg" alt="메인_아동_언어사회성.jpg"></p>
		                  		</div>
              </div>
            </div>
            <div class="content only_mobile">
              <div class="accordion_type fir">
              	
              	<div class="list_div on">
		                  <a class="list" href="javascript:" role="button">
		                    <p class="f_body4">성인강좌</p>
		                  </a>
		                  <div class="hide_con">
		                    <div class="circle_menu_w">
		                    	<a href="javascript:" class="circle_menu" data-lrcls-ctegry-cd="01" data-mdcls-ctegry-cd="0101">
				                        <div class="circle img_resize_w">
				                          <img src="" alt="메인_성인_공예.jpg" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260533029151.jpg" class="dataImg">
				                        </div>
				                        <p class="menu">공예</p>
				                      </a>
			                      <a href="javascript:" class="circle_menu" data-lrcls-ctegry-cd="01" data-mdcls-ctegry-cd="0102">
				                        <div class="circle img_resize_w">
				                          <img src="" alt="메인_성인_노래.jpg" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260533193383.jpg" class="dataImg">
				                        </div>
				                        <p class="menu">노래</p>
				                      </a>
			                      <a href="javascript:" class="circle_menu" data-lrcls-ctegry-cd="01" data-mdcls-ctegry-cd="0103">
				                        <div class="circle img_resize_w">
				                          <img src="" alt="메인_성인_댄스.jpg" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260533306673.jpg" class="dataImg">
				                        </div>
				                        <p class="menu">댄스</p>
				                      </a>
			                      <a href="javascript:" class="circle_menu" data-lrcls-ctegry-cd="01" data-mdcls-ctegry-cd="0104">
				                        <div class="circle img_resize_w">
				                          <img src="" alt="메인_성인_드로잉.jpg" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260542406471.jpg" class="dataImg">
				                        </div>
				                        <p class="menu">드로잉</p>
				                      </a>
			                      <a href="javascript:" class="circle_menu" data-lrcls-ctegry-cd="01" data-mdcls-ctegry-cd="0105">
				                        <div class="circle img_resize_w">
				                          <img src="" alt="메인_성인_라이프스타일.jpg" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260545030363.jpg" class="dataImg">
				                        </div>
				                        <p class="menu">라이프스타일</p>
				                      </a>
			                      <a href="javascript:" class="circle_menu" data-lrcls-ctegry-cd="01" data-mdcls-ctegry-cd="0106">
				                        <div class="circle img_resize_w">
				                          <img src="" alt="메인_성인_악기.jpg" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260546325223.jpg" class="dataImg">
				                        </div>
				                        <p class="menu">악기</p>
				                      </a>
			                      <a href="javascript:" class="circle_menu" data-lrcls-ctegry-cd="01" data-mdcls-ctegry-cd="0107">
				                        <div class="circle img_resize_w">
				                          <img src="" alt="메인_성인_어학.jpg" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260547571393.jpg" class="dataImg">
				                        </div>
				                        <p class="menu">어학</p>
				                      </a>
			                      <a href="javascript:" class="circle_menu" data-lrcls-ctegry-cd="01" data-mdcls-ctegry-cd="0108">
				                        <div class="circle img_resize_w">
				                          <img src="" alt="메인_성인_인문학.jpg" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260549229583.jpg" class="dataImg">
				                        </div>
				                        <p class="menu">인문학</p>
				                      </a>
			                      <a href="javascript:" class="circle_menu" data-lrcls-ctegry-cd="01" data-mdcls-ctegry-cd="0109">
				                        <div class="circle img_resize_w">
				                          <img src="" alt="메인_성인_재테크.jpg" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260551002553.jpg" class="dataImg">
				                        </div>
				                        <p class="menu">재테크</p>
				                      </a>
			                      <a href="javascript:" class="circle_menu" data-lrcls-ctegry-cd="01" data-mdcls-ctegry-cd="0110">
				                        <div class="circle img_resize_w">
				                          <img src="" alt="메인_성인_커리어.jpg" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260552512823.jpg" class="dataImg">
				                        </div>
				                        <p class="menu">커리어</p>
				                      </a>
			                      <a href="javascript:" class="circle_menu" data-lrcls-ctegry-cd="01" data-mdcls-ctegry-cd="0111">
				                        <div class="circle img_resize_w">
				                          <img src="" alt="메인_성인_쿠킹.jpg" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260554154173.jpg" class="dataImg">
				                        </div>
				                        <p class="menu">쿠킹</p>
				                      </a>
			                      <a href="javascript:" class="circle_menu" data-lrcls-ctegry-cd="01" data-mdcls-ctegry-cd="0112">
				                        <div class="circle img_resize_w">
				                          <img src="" alt="메인_성인_피트니스.jpg" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260556304753.jpg" class="dataImg">
				                        </div>
				                        <p class="menu">피트니스</p>
				                      </a>
			                      </div>
		                  </div>
		                </div>
	                <div class="list_div on">
		                  <a class="list" href="javascript:" role="button">
		                    <p class="f_body4">영·유아강좌</p>
		                  </a>
		                  <div class="hide_con">
		                    <div class="circle_menu_w">
		                    	<a href="javascript:" class="circle_menu" data-lrcls-ctegry-cd="02" data-mdcls-ctegry-cd="0202">
				                        <div class="circle img_resize_w">
				                          <img src="" alt="메인_영유아_오감발달.jpg" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260558211673.jpg" class="dataImg">
				                        </div>
				                        <p class="menu">오감발달</p>
				                      </a>
			                      <a href="javascript:" class="circle_menu" data-lrcls-ctegry-cd="02" data-mdcls-ctegry-cd="0201">
				                        <div class="circle img_resize_w">
				                          <img src="" alt="메인_아동_창의체험.jpg" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260558354653.jpg" class="dataImg">
				                        </div>
				                        <p class="menu">창의·체험</p>
				                      </a>
			                      <a href="javascript:" class="circle_menu" data-lrcls-ctegry-cd="02" data-mdcls-ctegry-cd="0203">
				                        <div class="circle img_resize_w">
				                          <img src="" alt="메인_영유아_음악미술.jpg" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260558478873.jpg" class="dataImg">
				                        </div>
				                        <p class="menu">음악·미술</p>
				                      </a>
			                      <a href="javascript:" class="circle_menu" data-lrcls-ctegry-cd="02" data-mdcls-ctegry-cd="0204">
				                        <div class="circle img_resize_w">
				                          <img src="" alt="메인_영유아_언어사회성.jpg" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260559050132.jpg" class="dataImg">
				                        </div>
				                        <p class="menu">언어·사회성</p>
				                      </a>
			                      <a href="javascript:" class="circle_menu" data-lrcls-ctegry-cd="02" data-mdcls-ctegry-cd="0205">
				                        <div class="circle img_resize_w">
				                          <img src="" alt="메인_영유아_신체활동.jpg" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260559206703.jpg" class="dataImg">
				                        </div>
				                        <p class="menu">신체활동</p>
				                      </a>
			                      </div>
		                  </div>
		                </div>
	                <div class="list_div on">
		                  <a class="list" href="javascript:" role="button">
		                    <p class="f_body4">아동강좌</p>
		                  </a>
		                  <div class="hide_con">
		                    <div class="circle_menu_w">
		                    	<a href="javascript:" class="circle_menu" data-lrcls-ctegry-cd="03" data-mdcls-ctegry-cd="0302">
				                        <div class="circle img_resize_w">
				                          <img src="" alt="메인_아동_신체활동.jpg" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260559321243.jpg" class="dataImg">
				                        </div>
				                        <p class="menu">신체활동</p>
				                      </a>
			                      <a href="javascript:" class="circle_menu" data-lrcls-ctegry-cd="03" data-mdcls-ctegry-cd="0303">
				                        <div class="circle img_resize_w">
				                          <img src="" alt="메인_영유아_창의체험.jpg" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260559439523.jpg" class="dataImg">
				                        </div>
				                        <p class="menu">창의·체험</p>
				                      </a>
			                      <a href="javascript:" class="circle_menu" data-lrcls-ctegry-cd="03" data-mdcls-ctegry-cd="0301">
				                        <div class="circle img_resize_w">
				                          <img src="" alt="메인_아동_음악미술.jpg" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260600003284.jpg" class="dataImg">
				                        </div>
				                        <p class="menu">음악·미술</p>
				                      </a>
			                      <a href="javascript:" class="circle_menu" data-lrcls-ctegry-cd="03" data-mdcls-ctegry-cd="0304">
				                        <div class="circle img_resize_w">
				                          <img src="" alt="메인_아동_언어사회성.jpg" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260600132862.jpg" class="dataImg">
				                        </div>
				                        <p class="menu">언어·사회성</p>
				                      </a>
			                      </div>
		                  </div>
		                </div>
	                </div>
            </div>
            </div>
        </div>
        <div class="tab_img_area">
          <p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-place.jpg" alt=""></p>
          <p class="img img_resize_w"><img src="/resources/common/images/img-class-search-gate-lecture.jpg" alt=""></p>
        </div>
      </div>
    </div>
    <div class="btn_wrap">
      <a id="lectureCntBnt" class="filter_btn" href="javascript:">
        <span>강좌 보기</span>
      </a>
    </div>
  </div>
  <a class="btn_close" href="javascript:"></a>
</div>

	<script>
		/* function dataImg(){
			
			$.each($('.dataImg'), function(index, item){
				var src = $(item).attr('src');
				
				if( src == "" ){
					$(item).attr('src', $(item).data().src);
					
				}
					
			});
			imgResizingFn();		
		} */
	</script>

</header>

<div class="m_navi_bar " data-url="/index.do">
	<div class="navi_wrap">
		<a href="/" class="navi_btn">
			<p class="icon">
				<img src="/resources/common/images/icon-navigation-home.png" alt="">
			</p>
			<p class="txt">홈</p>
		</a> 
		<a href="javascript:" class="navi_btn mypage_icon" onclick="fnc.moveLoginPage(true)">
			<p class="icon">
				<img src="/resources/common/images/icon-navigation-mypage.png" alt="">
			</p>
			<p class="txt">마이페이지</p>
		</a> 
		<a href="javascript:" class="navi_btn class_srch"> 
			<p class="icon">
				<img src="/resources/common/images/icon-navigation-class.png" alt="">
			</p>
			<p class="txt">강좌검색</p>
		</a> 
		<a href="/mypage/cart/list.do" class="navi_btn">
			<p class="icon ">
				<!-- 장바구니에 담긴 갯수가 있을 경우 on class 추가 -->
				<span class="cart_num"></span> 
				<img src="/resources/common/images/icon-navigation-cart.png" alt="">
			</p>
			<p class="txt">장바구니</p>
		</a> 
		<a href="javascript:" class="navi_btn all_menu_btn">
			<p class="icon">
				<img src="/resources/common/images/icon-navigation-menu.png" alt="">
			</p>
			<p class="txt">전체메뉴</p>
		</a>
	</div>

	<div class="all_menu_pop" style="height: 846px;">
		<!-- 2022-12-01, 2022-12-02 전체 구조 수정 -->
		<div class="scroll_area">
			<div class="inner">
				<ul class="all_menu">
					<li class="menu">
						<a href="javascript:" class="one_d"><span>수강신청</span></a>
						<ul class="two_d_w">
							<li class="two_d"><a class="class_srch" href="javascript:" target="_self"><span>강좌검색</span></a></li>
							<li class="two_d"><a class="" href="/information/guide/settle/list.do" target="_self"><span>수강신청·변경·취소안내</span></a></li>
							<li class="two_d"><a class="" href="/information/guide/online/list.do" target="_self"><span>온라인 신청가이드</span></a></li>
						</ul>
					</li>
					<li class="menu">
						<a href="javascript:" class="one_d"><span>이용안내</span></a>
						<ul class="two_d_w">
							<li class="two_d"><a class="" href="/information/branch/list.do" target="_self"><span>지점안내</span></a></li>
							<li class="two_d"><a class="" href="/information/application/index.do" target="_self"><span>강사지원·제휴신청</span></a></li>
							<li class="two_d"><a class="" href="/information/faq/list.do" target="_self"><span>자주하는 문의</span></a></li>
						</ul>
					</li>
					<li class="menu">
						<a href="javascript:" class="one_d"><span>커뮤니티</span></a>
						<ul class="two_d_w">
							<li class="two_d"><a class="" href="/community/notice/list.do" target="_self"><span>공지사항/이벤트</span></a></li>
							<li class="two_d"><a class="" href="/community/review/list.do" target="_self"><span>수강후기</span></a></li>
							<li class="two_d"><a class="" href="/community/magazine/list.do" target="_self"><span>LIFESTYLE LAB 매거진</span></a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
		<div class="bottom_area">
			<!-- <p class="txt">일상을 빛낼 <br>다양한 강좌를 <br>만나 보세요</p> -->
			<p class="login_txt">
				<a href="javascript:fnc.moveLoginPage()">로그인</a>
			</p>
		</div>
		<!-- // 2022-12-01, 2022-12-02 전체 구조 수정 -->
		<a class="btn_close" href="javascript:"></a>
	</div>

	<div class="all_menu_dimd"></div>
	<!-- 2022-12-05 추가 -->
</div>
