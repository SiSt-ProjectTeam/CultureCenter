<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ko">
	<head>
		
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<title>		
			지점안내 > 이용안내 > 롯데문화센터</title>
    	<link rel="shortcut icon" href="/common/images/favicon.ico" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
		<meta name="keywords" content="롯데문화센터"/>
		<meta name="description" content="롯데문화센터입니다."/>
		<meta property="og:site_name" content="롯데문화센터" id="og-sitename-value"/>
		<meta property="og:type" content="website" id="og-type-value"/>
		<meta property="og:url" content="https://culture.lotteshopping.com/information/branch/list.do" id="og-url-value"/>
		<meta property="og:title" content="" id="og-title-value"/>
		<meta property="og:description" content="롯데문화센터입니다." id="og-description-value"/>
		
		<meta property="og:image" content="https://culture.lotteshopping.com/common/images/img-sns-share-thumbnail.jpg" id="og-image-value"/>
		
		<link rel="apple-touch-icon-precomposed" href="/common/images/favicon.ico" />
		<link rel="stylesheet" href="/common/css/swiper.css" />
		<link rel="stylesheet" href="/common/css/fonts.css" />
		<link rel="stylesheet" href="/common/css/common.css" />
		<link rel="stylesheet" href="/common/css/main.css" />
		<link rel="stylesheet" href="/common/css/style.css" />
		<script async src="https://www.googletagmanager.com/gtag/js?id=G-K23DFMHQMJ"></script>
		<script>
		  window.dataLayer = window.dataLayer || [];
		  function gtag(){dataLayer.push(arguments);}
		  gtag('js', new Date());
		
		  gtag('config', 'G-K23DFMHQMJ');
		</script>
		<script type="text/javascript" src="/common/js/jquery-1.12.4.min.js"></script>
		<script type="text/javascript" src="/common/js/gsap.min.js"></script>
		<script type="text/javascript" src="/common/js/ScrollTrigger.min.js"></script>
		<script type="text/javascript" src="/common/js/swiper.min.js"></script>
		<script type="text/javascript" src="/common/js/script.js"></script>
		<script type="text/javascript" src="/common/js/fnc.js"></script> 
		<script type="text/javascript" src="/common/js/common.js"></script>
        <script type="text/javascript" src="/common/js/lotte.sso.api.js"></script>
<!-- 		<script type="text/javascript" src="/common/js/lib/clipboard.min.js"></script> -->
        <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
	</head>
	<body class="">
		<div id="wrap" data-is-app="" data-is-mobile="" data-is-login="Y">
			
			
        		
			<header class="pc">
				
				<div class="logo"><a href="/" title="롯데문화센터 메인으로 이동"></a></div>
				
				<nav>
					<div class="for_bg"></div>
					<div class="gnb_w">
						<ul class="gnb">
							<li>
										<a class="one_depth " href="javascript:">수강신청</a>
										<div class="two_pack">
														<ul class="two_dep_div">
															<li>
																<a class="two_depth class_srch" href="javascript:" target="_self">강좌검색</a>
															</li>
															<li>
																<a class="two_depth " href="/information/guide/settle/list.do" target="_self">수강신청·변경·취소안내</a>
															</li>
															<li>
																<a class="two_depth " href="/information/guide/online/list.do" target="_self">온라인 신청가이드</a>
															</li>
															</ul>
														<div class="img_div">															
															<p class="img on"><img src="/common/images/img-gnb-application-class-01.jpg" alt=""></p>
															<p class="img on"><img src="/common/images/img-gnb-application-class-02.jpg" alt=""></p>
															<p class="img on"><img src="/common/images/img-gnb-application-class-03.jpg" alt=""></p>
															</div>
													</div>
												</li>
								<li>
										<a class="one_depth " href="javascript:">이용안내</a>
										<div class="two_pack">
														<ul class="two_dep_div">
															<li>
																<a class="two_depth " href="/information/branch/list.do" target="_self">지점안내</a>
															</li>
															<li>
																<a class="two_depth " href="/information/application/index.do" target="_self">강사지원·제휴신청</a>
															</li>
															<li>
																<a class="two_depth " href="/information/faq/list.do" target="_self">자주하는 문의</a>
															</li>
															</ul>
														<div class="img_div">															
															<p class="img on"><img src="/common/images/img-gnb-use-information-01.jpg" alt=""></p>
															<p class="img on"><img src="/common/images/img-gnb-use-information-02.jpg" alt=""></p>
															<p class="img on"><img src="/common/images/img-gnb-use-information-03.jpg" alt=""></p>
															</div>
													</div>
												</li>
								<li>
										<a class="one_depth " href="javascript:">커뮤니티</a>
										<div class="two_pack">
														<ul class="two_dep_div">
															<li>
																<a class="two_depth " href="/community/notice/list.do" target="_self">공지사항/이벤트</a>
															</li>
															<li>
																<a class="two_depth " href="/community/review/list.do" target="_self">수강후기</a>
															</li>
															<li>
																<a class="two_depth " href="/community/magazine/list.do" target="_self">LIFESTYLE LAB 매거진</a>
															</li>
															</ul>
														<div class="img_div">															
															<p class="img on"><img src="/common/images/img-gnb-community-01.jpg" alt=""></p>
															<p class="img on"><img src="/common/images/img-gnb-community-02.jpg" alt=""></p>
															<p class="img on"><img src="/common/images/img-gnb-community-03.jpg" alt=""></p>
															</div>
													</div>
												</li>
								</ul>
					</div>
				</nav><div class="util_area">
					<p class="srch_icon">
						<a href="javascript:" title="관심있는 강좌 검색">
							<span class="only_pc">관심있는 강좌를 찾아보세요</span>
							<span class="only_mobile">관심 강좌 찾기</span>
						</a>
					</p>
					<p class="mypage_icon">
							<a href="javascript:" title="마이페이지" onclick="common.getCount(this);"></a>
						</p>
					<p class="cart_icon on"><!-- 장바구니에 담긴 갯수가 있을 경우 on class 추가 -->
						<a href="/mypage/cart/list.do" title="장바구니"></a>
						<span class="cart_num">2</span>
					</p>
					<p class="login_icon logout"><!-- 로그아웃일 경우 logout class 추가 -->
				    	<a href="javascript:fnc.moveLogout();" title="로그아웃"></a>
					</p>
				</div>
				
				<div class="header_srch_pop_area">
  					<p class="inner_dimd"></p>
					<div class="srch_con">
						<div class="srch_inner">
							<p class="f_h1">관심있는 강좌를 <br>찾아보세요</p>
							<div class="form_search_w">
								<div class="form_search">
									<div class="form_search_div">
										<input type="text" placeholder="검색어를 입력하세요" onkeyup="if(window.event.keyCode == 13) common.integrationSearch(this);">
										<div class="input_btn_wrap">
											<button type="button" class="btn_delete" title="지우기"></button>
											<button type="button" class="btn_search" title="검색" onclick="common.integrationSearch(this);"></button>
										</div>
									</div>
								</div>
							</div>
 
							<div class="recent_keyword_area" style="display:none;">
								<div class="top_area">
									<p class="tit">최근 검색어</p>
								</div>
								
								<div class="srch_data_div">
					          		<a href="javascript:common.integrationRemove('');" class="reset_data" role="button"><span>선택 초기화</span></a>
						          	<div class="srch_data_list swiper-container no_swiping">
						            	<div class="swiper-wrapper">
							              
							            </div>
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
										<a href="/mypage/member/info.do"><span>유희진</span></a>
									</p>
									<div class="mypage_info_w">
										<a href="javascript:common.showItrstPop()" class="mypage_info">
											<p class="f_caption2">관심지점</p>
											<p class="info"><span>잠실점</span></p>
										</a>
										<a href="/mypage/coupon/list.do" class="mypage_info">
											<p class="f_caption2">나의 쿠폰</p>
											<p class="info" ><span id="cpnCnt"></span></p>
										</a>
										<a href="javascript:" class="mypage_info">
											<p class="f_caption2">L.POINT</p>
											<p class="info blue_txt">
												<span id="lpointSpan" onclick="javascript:common.getLpoint()">조회하기</span>
											</p>
										</a>
									</div>
									<div class="mypage_div">
										<a href="/mypage/cart/list.do" class="mypage_box on">
											<p class="icon"><img src="/common/images/icon-mypage-class-cart.png" alt=""></p>
											<p class="f_caption2">장바구니</p>
											<p class="num" id="cartCnt">2</p>
										</a>
										<a href="/mypage/atlct/list.do" class="mypage_box">
											<p class="icon"><img src="/common/images/icon-mypage-class-history.png" alt=""></p>
											<p class="f_caption2">수강내역</p>
											<p class="num" id="atlctCnt"></p>
										</a>
										<a href="/mypage/waiting/list.do" class="mypage_box">
											<p class="icon"><img src="/common/images/icon-mypage-waiting-list.png" alt=""></p>
											<p class="f_caption2">대기자조회</p>
											<p class="num" id="waitingCnt"></p>
										</a>
										<a href="/mypage/lectureCertf/list.do" class="mypage_box">
											<p class="icon"><img src="/common/images/icon-mypage-class-certificate-of-study.png" alt=""></p>
											<p class="f_caption2">수강증</p>
											<p class="num" id="certfCnt"></p>
										</a>
										<a href="/mypage/myreview/list.do" class="mypage_box">
											<p class="icon"><img src="/common/images/icon-mypage-class-review-course.png" alt=""></p>
											<p class="f_caption2">수강후기</p>
											<p class="num" id="rvwCnt"></p>
										</a>
										<a href="/mypage/teachereval/list.do" class="mypage_box eval">
											<p class="icon"><img src="/common/images/icon-mypage-satisfaction-evaluation.png" alt=""></p>
											<p class="f_caption2">만족도 평가</p>
											<p class="num" id="tcevlCnt"></p>
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
						<p class="inner_dimd" style="display:none;"></p>
						<div class="for_padding">
							<div class="scroll_area">
								<div class="place_con">
									<div class="place_inner">
										<p class="title">나의 관심지점 설정</p>
										<div class="dot_txt_box">
											<p class="tit">관심지점은 1개만 선택 가능합니다</p>
											<p class="txt">설정하신 관심지점은 추천 강좌 안내 시에 활용되며, 관심지점 외에 지점에서 진행하는 강좌들도 정상적으로 수강 가능합니다.</p>
										</div>
										<div class="accordion_type fir">
											<div class="list_div on">
												<a class="list" href="javascript:" role="button">
													<p class="f_body4">서울점</p>
												</a>
												<div class="hide_con">
													<div class="btn_flex_box">
														<a href="javascript:" data-cd="0002" data-nm="잠실점" class="place_btn brchBtn on"><span>잠실점</span></a>
														<a href="javascript:" data-cd="0001" data-nm="본점" class="place_btn brchBtn "><span>본점</span></a>
														<a href="javascript:" data-cd="0013" data-nm="강남점" class="place_btn brchBtn "><span>강남점</span></a>
														<a href="javascript:" data-cd="0028" data-nm="건대스타시티점" class="place_btn brchBtn "><span>건대스타시티점</span></a>
														<a href="javascript:" data-cd="0006" data-nm="관악점" class="place_btn brchBtn "><span>관악점</span></a>
														<a href="javascript:" data-cd="0340" data-nm="김포공항점" class="place_btn brchBtn "><span>김포공항점</span></a>
														<a href="javascript:" data-cd="0022" data-nm="노원점" class="place_btn brchBtn "><span>노원점</span></a>
														<a href="javascript:" data-cd="0026" data-nm="미아점" class="place_btn brchBtn "><span>미아점</span></a>
														<a href="javascript:" data-cd="0010" data-nm="영등포점" class="place_btn brchBtn "><span>영등포점</span></a>
														<a href="javascript:" data-cd="0004" data-nm="청량리점" class="place_btn brchBtn "><span>청량리점</span></a>
														</div>
												</div>
											</div>
											<div class="list_div on">
												<a class="list" href="javascript:" role="button">
													<p class="f_body4">수도권점</p>
												</a>
												<div class="hide_con">
													<div class="btn_flex_box">
														<a href="javascript:" data-cd="0344" data-nm="인천점" class="place_btn brchBtn "><span>인천점</span></a>
														<a href="javascript:" data-cd="0399" data-nm="동탄점" class="place_btn brchBtn "><span>동탄점</span></a>
														<a href="javascript:" data-cd="0335" data-nm="구리점" class="place_btn brchBtn "><span>구리점</span></a>
														<a href="javascript:" data-cd="0008" data-nm="분당점" class="place_btn brchBtn "><span>분당점</span></a>
														<a href="javascript:" data-cd="0349" data-nm="수원점" class="place_btn brchBtn "><span>수원점</span></a>
														<a href="javascript:" data-cd="0336" data-nm="안산점" class="place_btn brchBtn "><span>안산점</span></a>
														<a href="javascript:" data-cd="0011" data-nm="일산점" class="place_btn brchBtn "><span>일산점</span></a>
														<a href="javascript:" data-cd="0334" data-nm="중동점" class="place_btn brchBtn "><span>중동점</span></a>
														<a href="javascript:" data-cd="0341" data-nm="평촌점" class="place_btn brchBtn "><span>평촌점</span></a>
														<a href="javascript:" data-cd="0350" data-nm="롯데몰광명점" class="place_btn brchBtn "><span>롯데몰광명점</span></a>
														</div>
												</div>
											</div>
											<div class="list_div on">
												<a class="list" href="javascript:" role="button">
													<p class="f_body4">지방점</p>
												</a>
												<div class="hide_con">
													<div class="btn_flex_box">
														<a href="javascript:" data-cd="0005" data-nm="부산본점" class="place_btn brchBtn "><span>부산본점</span></a>
														<a href="javascript:" data-cd="0333" data-nm="광복점" class="place_btn brchBtn "><span>광복점</span></a>
														<a href="javascript:" data-cd="0007" data-nm="광주점" class="place_btn brchBtn "><span>광주점</span></a>
														<a href="javascript:" data-cd="0023" data-nm="대구점" class="place_btn brchBtn "><span>대구점</span></a>
														<a href="javascript:" data-cd="0012" data-nm="대전점" class="place_btn brchBtn "><span>대전점</span></a>
														<a href="javascript:" data-cd="0016" data-nm="동래점" class="place_btn brchBtn "><span>동래점</span></a>
														<a href="javascript:" data-cd="0354" data-nm="마산점" class="place_btn brchBtn "><span>마산점</span></a>
														<a href="javascript:" data-cd="0024" data-nm="상인점" class="place_btn brchBtn "><span>상인점</span></a>
														<a href="javascript:" data-cd="0027" data-nm="센텀시티점" class="place_btn brchBtn "><span>센텀시티점</span></a>
														<a href="javascript:" data-cd="0015" data-nm="울산점" class="place_btn brchBtn "><span>울산점</span></a>
														<a href="javascript:" data-cd="0025" data-nm="전주점" class="place_btn brchBtn "><span>전주점</span></a>
														<a href="javascript:" data-cd="0017" data-nm="창원점" class="place_btn brchBtn "><span>창원점</span></a>
														<a href="javascript:" data-cd="0014" data-nm="포항점" class="place_btn brchBtn "><span>포항점</span></a>
														<a href="javascript:" data-cd="0361" data-nm="롯데몰군산점" class="place_btn brchBtn "><span>롯데몰군산점</span></a>
														</div>
												</div>
											</div>
											</div>
									</div>
									<a href="javascript:" class="prev_btn" onclick="$(this).next().click();"></a><a href="javascript:"  class="btn_close"></a>
								</div>
							</div>
							<div class="save_btn">
								<a href="javascript:common.saveItrst()">
									<span>저장</span>
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
              
              <div class="list">
               		<p class="f_body3">서울점</p>
               		<div class="txt_w">
	               		<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0002" class="f_body1">잠실점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0001" class="f_body1">본점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0013" class="f_body1">강남점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0028" class="f_body1">건대스타시티점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0006" class="f_body1">관악점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0340" class="f_body1">김포공항점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0022" class="f_body1">노원점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0026" class="f_body1">미아점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0010" class="f_body1">영등포점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0004" class="f_body1">청량리점</a></p>
	                  	</div>
               	</div>
              <div class="list">
               		<p class="f_body3">수도권점</p>
               		<div class="txt_w">
	               		<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0344" class="f_body1">인천점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0399" class="f_body1">동탄점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0335" class="f_body1">구리점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0008" class="f_body1">분당점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0349" class="f_body1">수원점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0336" class="f_body1">안산점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0011" class="f_body1">일산점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0334" class="f_body1">중동점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0341" class="f_body1">평촌점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0350" class="f_body1">롯데몰광명점</a></p>
	                  	</div>
               	</div>
              <div class="list">
               		<p class="f_body3">지방점</p>
               		<div class="txt_w">
	               		<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0005" class="f_body1">부산본점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0333" class="f_body1">광복점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0007" class="f_body1">광주점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0023" class="f_body1">대구점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0012" class="f_body1">대전점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0016" class="f_body1">동래점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0354" class="f_body1">마산점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0024" class="f_body1">상인점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0027" class="f_body1">센텀시티점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0015" class="f_body1">울산점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0025" class="f_body1">전주점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0017" class="f_body1">창원점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0014" class="f_body1">포항점</a></p>
	                  	<p class="txt"><a href="/application/search/list.do?type=branch&brchCd=0361" class="f_body1">롯데몰군산점</a></p>
	                  	</div>
               	</div>
              </div>
              
              <div class="img_w">
                <div class="img_div">
                
                	<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="_DSF8620.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="로비.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="_DSF9387.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="로비보정.JPG"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="로비_DSF9158 copy211.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="_DSF0050.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="_DSF1679.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="_DSF1444.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="DSF9351 999.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="_DSF0411.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="인천점_5.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="_DSF9863.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="구리5.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="_DSF8078.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="_DSF8406.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="as_LAB1.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="_DSF0220.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="5.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="5.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="_DSF9549.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="4. 9강의실.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="_DSF2623.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="7.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="요리교실.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="_DSF6576.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="_DSF5028.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="05 LAB1.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="_DSF7251.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="_DSF3215.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="_DSF8047.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="_DSF6247.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="_DSF5.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="_DSF8423.jpg"></p>
               			<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt="LAB6.jpg"></p>
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
              
              	<div class="list">
		                  <p class="f_body3">성인강좌</p>
		                  <div class="txt_w">
		                  	<p class="txt"><a href="/application/search/list.do?type=category&lrclsCtegryCd=01&mdclsCtegryCd=0101" class="f_body1">공예</a></p>
		                  		<p class="txt"><a href="/application/search/list.do?type=category&lrclsCtegryCd=01&mdclsCtegryCd=0102" class="f_body1">노래</a></p>
		                  		<p class="txt"><a href="/application/search/list.do?type=category&lrclsCtegryCd=01&mdclsCtegryCd=0103" class="f_body1">댄스</a></p>
		                  		<p class="txt"><a href="/application/search/list.do?type=category&lrclsCtegryCd=01&mdclsCtegryCd=0104" class="f_body1">드로잉</a></p>
		                  		<p class="txt"><a href="/application/search/list.do?type=category&lrclsCtegryCd=01&mdclsCtegryCd=0105" class="f_body1">라이프스타일</a></p>
		                  		<p class="txt"><a href="/application/search/list.do?type=category&lrclsCtegryCd=01&mdclsCtegryCd=0106" class="f_body1">악기</a></p>
		                  		<p class="txt"><a href="/application/search/list.do?type=category&lrclsCtegryCd=01&mdclsCtegryCd=0107" class="f_body1">어학</a></p>
		                  		<p class="txt"><a href="/application/search/list.do?type=category&lrclsCtegryCd=01&mdclsCtegryCd=0108" class="f_body1">인문학</a></p>
		                  		<p class="txt"><a href="/application/search/list.do?type=category&lrclsCtegryCd=01&mdclsCtegryCd=0109" class="f_body1">재테크</a></p>
		                  		<p class="txt"><a href="/application/search/list.do?type=category&lrclsCtegryCd=01&mdclsCtegryCd=0110" class="f_body1">커리어</a></p>
		                  		<p class="txt"><a href="/application/search/list.do?type=category&lrclsCtegryCd=01&mdclsCtegryCd=0111" class="f_body1">쿠킹</a></p>
		                  		<p class="txt"><a href="/application/search/list.do?type=category&lrclsCtegryCd=01&mdclsCtegryCd=0112" class="f_body1">피트니스</a></p>
		                  		</div>
		                </div>
	                <div class="list">
		                  <p class="f_body3">영·유아강좌</p>
		                  <div class="txt_w">
		                  	<p class="txt"><a href="/application/search/list.do?type=category&lrclsCtegryCd=02&mdclsCtegryCd=0202" class="f_body1">오감발달</a></p>
		                  		<p class="txt"><a href="/application/search/list.do?type=category&lrclsCtegryCd=02&mdclsCtegryCd=0201" class="f_body1">창의·체험</a></p>
		                  		<p class="txt"><a href="/application/search/list.do?type=category&lrclsCtegryCd=02&mdclsCtegryCd=0203" class="f_body1">음악·미술</a></p>
		                  		<p class="txt"><a href="/application/search/list.do?type=category&lrclsCtegryCd=02&mdclsCtegryCd=0204" class="f_body1">언어·사회성</a></p>
		                  		<p class="txt"><a href="/application/search/list.do?type=category&lrclsCtegryCd=02&mdclsCtegryCd=0205" class="f_body1">신체활동</a></p>
		                  		</div>
		                </div>
	                <div class="list">
		                  <p class="f_body3">아동강좌</p>
		                  <div class="txt_w">
		                  	<p class="txt"><a href="/application/search/list.do?type=category&lrclsCtegryCd=03&mdclsCtegryCd=0302" class="f_body1">신체활동</a></p>
		                  		<p class="txt"><a href="/application/search/list.do?type=category&lrclsCtegryCd=03&mdclsCtegryCd=0303" class="f_body1">창의·체험</a></p>
		                  		<p class="txt"><a href="/application/search/list.do?type=category&lrclsCtegryCd=03&mdclsCtegryCd=0301" class="f_body1">음악·미술</a></p>
		                  		<p class="txt"><a href="/application/search/list.do?type=category&lrclsCtegryCd=03&mdclsCtegryCd=0304" class="f_body1">언어·사회성</a></p>
		                  		</div>
		                </div>
	                </div>
              <div class="img_w">
                <div class="img_div">
                  
                  	<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_공예.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_노래.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_댄스.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_드로잉.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_라이프스타일.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_악기.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_어학.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_인문학.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_재테크.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_커리어.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_쿠킹.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-lecture.jpg" alt="메인_성인_피트니스.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-lecture.jpg" alt="메인_영유아_오감발달.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-lecture.jpg" alt="메인_아동_창의체험.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-lecture.jpg" alt="메인_영유아_음악미술.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-lecture.jpg" alt="메인_영유아_언어사회성.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-lecture.jpg" alt="메인_영유아_신체활동.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-lecture.jpg" alt="메인_아동_신체활동.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-lecture.jpg" alt="메인_영유아_창의체험.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-lecture.jpg" alt="메인_아동_음악미술.jpg"></p>
		                  		<p class="img img_resize_w"><img src="/common/images/img-class-search-gate-lecture.jpg" alt="메인_아동_언어사회성.jpg"></p>
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
          <p class="img img_resize_w"><img src="/common/images/img-class-search-gate-place.jpg" alt=""></p>
          <p class="img img_resize_w"><img src="/common/images/img-class-search-gate-lecture.jpg" alt=""></p>
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
			
			<div class="m_navi_bar " data-url="/information/branch/list.do">
	<div class="navi_wrap">
		<a href="/" class="navi_btn">
			<p class="icon">
				<img src="/common/images/icon-navigation-home.png" alt="">
			</p>
			<p class="txt">홈</p>
		</a> 
		
		<a href="javascript:" class="navi_btn mypage_icon" onclick="common.getCount(this)">
			<p class="icon">
				<img src="/common/images/icon-navigation-mypage.png" alt="">
			</p>
			<p class="txt">마이페이지</p>
		</a> 
		<a href="javascript:" class="navi_btn class_srch">
			<p class="icon">
				<img src="/common/images/icon-navigation-class.png" alt="">
			</p>
			<p class="txt">강좌검색</p>
		</a> 
		<a href="/mypage/cart/list.do" class="navi_btn">
			<p class="icon on">
				<!-- 장바구니에 담긴 갯수가 있을 경우 on class 추가 -->
				<span class="cart_num">2</span> <img
					src="/common/images/icon-navigation-cart.png" alt="">
			</p>
			<p class="txt">장바구니</p>
		</a> 
		<a href="javascript:" class="navi_btn all_menu_btn">
			<p class="icon">
				<img src="/common/images/icon-navigation-menu.png" alt="">
			</p>
			<p class="txt">전체메뉴</p>
		</a>
	</div>

	<div class="all_menu_pop" style="height: 846px;">
		<!-- 2022-12-01, 2022-12-02 전체 구조 수정 -->
		<div class="scroll_area">
			<div class="inner">
				<ul class="all_menu">
				<li class="menu"><a href="javascript:" class="one_d"><span>수강신청</span></a>
							<ul class="two_d_w">
								<li class="two_d"><a class="class_srch" href="javascript:" target="_self"><span>강좌검색</span></a></li>
								<li class="two_d"><a class="" href="/information/guide/settle/list.do" target="_self"><span>수강신청·변경·취소안내</span></a></li>
								<li class="two_d"><a class="" href="/information/guide/online/list.do" target="_self"><span>온라인 신청가이드</span></a></li>
								</ul>
							</li>
						<li class="menu"><a href="javascript:" class="one_d"><span>이용안내</span></a>
							<ul class="two_d_w">
								<li class="two_d"><a class="" href="/information/branch/list.do" target="_self"><span>지점안내</span></a></li>
								<li class="two_d"><a class="" href="/information/application/index.do" target="_self"><span>강사지원·제휴신청</span></a></li>
								<li class="two_d"><a class="" href="/information/faq/list.do" target="_self"><span>자주하는 문의</span></a></li>
								</ul>
							</li>
						<li class="menu"><a href="javascript:" class="one_d"><span>커뮤니티</span></a>
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
				<a href="javascript:fnc.moveLogout()">로그아웃</a>
			</p>
		</div>
		<!-- // 2022-12-01, 2022-12-02 전체 구조 수정 -->
		<a class="btn_close" href="javascript:"></a>
	</div>

	<div class="all_menu_dimd"></div>
	<!-- 2022-12-05 추가 -->
</div><script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d427a7c1718f571750db2822740d0465&autoload=false"></script>
<div class="cont_wrap">
	<div class="cont_inner no_pb">
		<form id="frmSearch" name="frmSearch">
	    <input type="hidden" id="brchCd" 	 name="brchCd"	   value=""/>
		<input type="hidden" id="brchAreaCd" name="brchAreaCd" value="S"/>
		</form>			
	    <div class="page_title_area">
			<div class="inner">
				<div class="top_area">
					<a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a>
					<a href="javascript:" class="tit_div arrow" title="페이지 이동 팝업 열기">
						<p class="tit f_h1">지점안내<span class="more_tit"></span></p>
					</a>
					<div class="tit_popup">
						<div class="pop_wrap">
							<div class="pop_cont">
								<div class="for_padding">
									<div class="scroll_area">
										<div class="branch">
		<a class="active" href="/information/branch/list.do"><p class="f_desc">지점안내</p></a>	
			<a class="" href="/information/application/index.do"><p class="f_desc">강사지원·제휴신청</p></a>	
			<a class="" href="/information/faq/list.do"><p class="f_desc">자주하는 문의</p></a>	
			</div></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="m_pop_dimd"></div>
		</div>
		<div class="page_cont_area no_pt">
			<div class="inner">
				<div class="tab_func_area">
					<div class="filter_bar_area">
						<div class="fixed_mobile_w">
							<div class="fixed_mobile">
								<div class="border_tab_area tab_btn_area">
									<div class="swiper-container">
										<div class="swiper-wrapper">
											<a href="javascript:branchCtrl.tabClCd('S');" class="btn swiper-slide on" data-cd="S"><span>서울점</span></a>
				                            <a href="javascript:branchCtrl.tabClCd('M');" class="btn swiper-slide " data-cd="M"><span>수도권점</span></a>
				                            <a href="javascript:branchCtrl.tabClCd('C');" class="btn swiper-slide " data-cd="C"><span>지방점</span></a>
				                            </div>
									</div>
								</div>
								<div class="thr_dep_area wide" id="listArea">
								<!-- 내용 영역 -->		
								</div>
							</div>
						</div>
					</div>
					<!-- ajax -->
					<div class="tab_con_area" id="dtlArea">
						
					</div>
				</div>
			</div>
		</div>
<!-- 	</form>	 -->
	</div>
</div>

<script type="text/javascript" src="/common/js/branch/branchCtrl.js"></script>
<footer>
			
				<div class="inner">
					<div class="top_area">
						<div class="left">
							<p class="title">무엇을 도와드릴까요?</p>
							<div class="inquiry_div">
								<p class="inquiry">
									<a href="/information/faq/list.do" target="_blank">자주묻는 질문</a>
								</p>
								<p class="inquiry">
									<a href="/information/guide/settle/list.do" target="_blank">수강신청·변경·취소안내</a>
								</p>
								<p class="inquiry">
									<a href="/information/guide/online/list.do" target="_blank">온라인 신청가이드</a>
								</p>
							</div>
						</div>
						<div class="right">
							<div class="call_div">
								<p class="tit">백화점 대표전화</p>
								<p class="call">1577.0001</p>
								<p class="txt">백화점 대표전화<br>(평일 09~18시. 토/일요일 및 공휴일 휴무)</p>
							</div>
							<div class="call_div">
								<p class="tit">L.POINT 콜센터</p>
								<p class="call">1899.8900</p>
								<p class="txt">L.POINT 콜센터<br>(평일 09~18시. 토/일요일 및 공휴일 휴무)</p>
							</div>
						</div>
					</div>
					<div class="bottom_area">
						<div class="left">
							<div class="link_area">
								<a href="https://www.lotteshopping.com/etc/personalinfo" title="새창" target="_blank" class="link personal_info">개인정보처리방침</a>
								<a href="https://www.lotteshopping.com/etc/operate" title="새창" target="_blank"  class="link">영상정보처리운영관리방침</a>
								<a href="https://www.lotteshopping.com/etc/terms" title="새창" target="_blank"  class="link">이용약관</a>
							</div>
							<p class="address">롯데쇼핑(주) 주소 : 서울특별시 중구 소공동 1번지 (서울시 중구 남대문로 81)<span class="name">대표이사 : 정준호</span></p>
							<p class="copyright">COPYRIGHT © LOTTESHOPPING.CO.,LTD. ALL RIGHTS RESERVED</p>
						</div>
						<div class="right">
							<div class="site_box">
								<a href="javascript:commonScript.openPopupFn('#familyPop', $(this));" title="롯데 관계사 사이트 팝업 열기"><span>롯데 관계사 사이트</span></a>
							</div>
							<p class="lotteon_logo"><img src="/common/images/logo-lotte.png" alt=""></p>
						</div>
					</div>
				</div>
			
			</footer>
			<!-- 관계사 사이트 팝업 -->
			<div class="layer_popup" id="familyPop" style="display: none; top: 0px;">
  <div class="pop_wrap w800" style="transform: translate(0px, 0px); height: 692px; margin-left: -400px; margin-top: -346px;">
    <div class="pop_head">
      <p class="f_h2">롯데 관계사 사이트</p>
    </div>
    <div class="pop_cont" style="transform: translate(0px, 0px); height: 620px;">
      <div class="for_padding">
        <div class="scroll_area">
          <div class="footer_site opacity_txt_list">
            <p class="link"><a class="txt" href="http://www.lotte.co.kr/main.do" title="새창" target="_blank">롯데지주</a></p>
            <p class="link"><a class="txt" href="http://www.lotteins.co.kr" title="새창" target="_blank">롯데손해보험</a></p>
            <p class="link"><a class="txt" href="http://www.ldcc.co.kr/" title="새창" target="_blank">롯데제과</a></p>
            <p class="link"><a class="txt" href="https://www.mybi.co.kr/" title="새창" target="_blank">마이비</a></p>
            <p class="link"><a class="txt" href="http://www.lottecon.co.kr/" title="새창" target="_blank">롯데건설</a></p>
            <p class="link"><a class="txt" href="http://www.lotteintl.co.kr/" title="새창" target="_blank">롯데상사</a></p>
            <p class="link"><a class="txt" href="http://www.lottejtb.com/" title="새창" target="_blank">롯데제이티비</a></p>
            <p class="link"><a class="txt" href="http://www.charlottetheater.co.kr/" title="새창" target="_blank">샤롯데시어터</a></p>
            <p class="link"><a class="txt" href="https://www.lotteglogis.com/" title="새창" target="_blank">롯데글로벌로지스</a></p>
            <p class="link"><a class="txt" href="http://www.lottesuper.co.kr/" title="새창" target="_blank">롯데슈퍼</a></p>
            <p class="link"><a class="txt" href="https://www.lotternd.com/KOR/" title="새창" target="_blank">롯데중앙연구소</a></p>
            <p class="link"><a class="txt" href="http://www.angelinus.com/" title="새창" target="_blank">엔젤리너스</a></p>
            <p class="link"><a class="txt" href="http://www.lottelem.co.kr/" title="새창" target="_blank">롯데기공</a></p>
            <p class="link"><a class="txt" href="http://www.lottecinema.co.kr/" title="새창" target="_blank">롯데시네마</a></p>
            <p class="link"><a class="txt" href="http://job.lotte.co.kr/LotteRecruit/Lotte1.aspx" title="새창" target="_blank">롯데채용센터</a></p>
            <p class="link"><a class="txt" href="https://store-kr.uniqlo.com/" title="새창" target="_blank">유니클로</a></p>
            <p class="link"><a class="txt" href="http://www.lottebp.com/main.html" title="새창" target="_blank">롯데비피화학</a></p>
            <p class="link"><a class="txt" href="http://www.asahibeerk.com/" title="새창" target="_blank">롯데아사히주류</a></p>
            <p class="link"><a class="txt" href="http://mall.lottechilsung.co.kr/" title="새창" target="_blank">롯데칠성음료(음료BG)</a></p>
            <p class="link"><a class="txt" href="https://www.cashbee.co.kr/" title="새창" target="_blank">이비카드</a></p>
            <p class="link"><a class="txt" href="https://www.lotterentacar.net" title="새창" target="_blank">롯데렌탈</a></p>
            <p class="link"><a class="txt" href="https://www.lotteal.co.kr/" title="새창" target="_blank">롯데알미늄</a></p>
            <p class="link"><a class="txt" href="http://www.lotteliquor.com/" title="새창" target="_blank">롯데칠성음료(주류BG)</a></p>
            <p class="link"><a class="txt" href="https://www.canon-bs.co.kr/main/" title="새창" target="_blank">캐논코리아비즈니스솔루션</a></p>
            <p class="link"><a class="txt" href="http://www.llc.co.kr/" title="새창" target="_blank">롯데로지스틱스</a></p>
            <p class="link"><a class="txt" href="http://www.lottemcc.com/" title="새창" target="_blank">롯데엠시시</a></p>
            <p class="link"><a class="txt" href="http://www.lotteadms.com/" title="새창" target="_blank">롯데첨단소재</a></p>
            <p class="link"><a class="txt" href="http://www.7-eleven.co.kr/" title="새창" target="_blank">코리아세븐</a></p>
            <p class="link"><a class="txt" href="http://www.lohbs.co.kr/" title="새창" target="_blank">롯데롭스</a></p>
            <p class="link"><a class="txt" href="http://www.lotteworld.com/gate.html" title="새창" target="_blank">롯데월드</a></p>
            <p class="link"><a class="txt" href="https://www.lottecard.co.kr/" title="새창" target="_blank">롯데카드</a></p>
            <p class="link"><a class="txt" href="http://www.krispykreme.co.kr/" title="새창" target="_blank">크리스피크림도넛</a></p>
            <p class="link"><a class="txt" href="http://www.lotteria.com/" title="새창" target="_blank">롯데리아</a></p>
            <p class="link"><a class="txt" href="https://www.lottelmsc.com/" title="새창" target="_blank">롯데유통사업본부</a></p>
            <p class="link"><a class="txt" href="http://www.lottecap.com/" title="새창" target="_blank">롯데캐피탈</a></p>
            <p class="link"><a class="txt" href="http://toysrus.lottemart.com/index.do" title="새창" target="_blank">토이저러스</a></p>
            <p class="link"><a class="txt" href="http://www.lottemart.com/index.do" title="새창" target="_blank">롯데마트</a></p>
            <p class="link"><a class="txt" href="http://www.lotteacademy.co.kr/" title="새창" target="_blank">롯데인재개발원</a></p>
            <p class="link"><a class="txt" href="http://www.lottechem.com/" title="새창" target="_blank">롯데케미칼</a></p>
<!--             <p class="link"><a class="txt" href="http://www.tgif.co.kr" title="새창" target="_blank">T.G.I.Friday’s</a></p> -->
            <p class="link"><a class="txt" href="http://kor.lottedfs.com/kr" title="새창" target="_blank">롯데면세점</a></p>
            <p class="link"><a class="txt" href="http://www.lottedevelop.com" title="새창" target="_blank">롯데자산개발</a></p>
            <p class="link"><a class="txt" href="http://www.lottefoods.co.kr/" title="새창" target="_blank">롯데푸드</a></p>
            <p class="link"><a class="txt" href="http://www.fujifilm.co.kr/" title="새창" target="_blank">한국후지필름</a></p>
            <p class="link"><a class="txt" href="https://www.lpoint.com/" title="새창" target="_blank">롯데멤버스</a></p>
            <p class="link"><a class="txt" href="http://www.giantsclub.com/html/" title="새창" target="_blank">롯데자이언츠</a></p>
            <p class="link"><a class="txt" href="http://www.e-himart.co.kr/" title="새창" target="_blank">롯데하이마트</a></p>
            <p class="link"><a class="txt" href="http://www.hanpay.net/portal2nd/index.do" title="새창" target="_blank">한페이시스</a></p>
            <p class="link"><a class="txt" href="https://www.lwt.co.kr/lottepnd/main.do" title="새창" target="_blank">롯데물산</a></p>
            <p class="link"><a class="txt" href="https://www.lottefoundation.or.kr/" title="새창" target="_blank">롯데재단</a></p>
            <p class="link"><a class="txt" href="http://www.lottehotel.com/global/ko/" title="새창" target="_blank">롯데호텔</a></p>
<!--             <p class="link"><a class="txt" href="javascript:">현대정보기술</a></p> -->
            <p class="link"><a class="txt" href="http://store.lotteshopping.com/" title="새창" target="_blank">롯데백화점</a></p>
            <p class="link"><a class="txt" href="http://www.lottefinechem.com/" title="새창" target="_blank">롯데정밀화학</a></p>
            <p class="link"><a class="txt" href="http://www.lotteimall.com/" title="새창" target="_blank">롯데홈쇼핑</a></p>
            <p class="link"><a class="txt" href="http://www.skyhill.co.kr/" title="새창" target="_blank">롯데스카이힐CC</a></p>
            <p class="link"><a class="txt" href="http://www.ldcc.co.kr/" title="새창" target="_blank">롯데정보통신</a></p>
            <p class="link"><a class="txt" href="https://www.daehong.com/" title="새창" target="_blank">대홍기획</a></p>
          </div>
        </div>
      </div>
    </div>
    <a class="btn_close" href="javascript:" title="닫기">
      <span class="blind"></span>
    </a>
  </div>
</div><!-- sns공유하기 팝업 -->
			<script type="text/javascript" src="/common/js/lib/clipboard.min.js"></script>
        
<div class="layer_popup" id="sharePop" style="display: none; top: 0px;">
	<div class="pop_wrap" style="transform: translate(0px, 0px); height: 242px; margin-left: -240px; margin-top: -121px;">
		<div class="pop_head">
			<p class="title">공유하기</p>
		</div>
		<div class="pop_cont" style="transform: translate(0px, 0px); height: 170px;">
			<div class="for_padding">
				<div class="scroll_area">
					<div class="share_area dSocialShare">
						<a href="javascript:" id="btnKakao" class="share" data-key="d427a7c1718f571750db2822740d0465"> <!-- onclick="sharePop.setKakao(this)"  -->
							<p class="img"><img src="/common/images/icon-sns-kakao-talk.png" alt=""></p>
							<p class="txt">카카오톡</p>
						</a>
						<a href="javascript:" id="btnFaceBook" class="share"> <!-- onclick="sharePop.setFaceBook(this)" -->
							<p class="img"><img src="/common/images/icon-sns-facebook.png" alt=""></p>
							<p class="txt">페이스북</p>
						</a>
						<a href="javascript:" id="btnTwitter" class="share"> <!--  onclick="sharePop.setTwitter(this)" -->
							<p class="img"><img src="/common/images/icon-sns-twiiter.png" alt=""></p>
							<p class="txt">트위터</p>
						</a>
						<a href="javascript:" class="share" id="btnClipBoard"><!-- onclick="sharePop.setClipboard(this)"  -->
							<p class="img"><img src="/common/images/icon-sns-link-copy.png" alt=""></p>
							<p class="txt">URL복사</p>
						</a>
					</div>
				</div>
			</div>
		</div>
		<a class="btn_close" href="javascript:" title="닫기">
			<span class="blind"></span>
		</a>
	</div>
</div>

<script type="text/javascript" src="/common/js/sharePop.js"></script><div class="layer_popup logout" id="logOutGuide" style="display: none;">
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
										30분 동안 서비스 이용이 없어 고객님의 <br>
										안전한 사이트 이용을 위해 자동 로그아웃 됩니다. <br>
										계속하시려면 로그인 시간을 연장해 주세요.
									</p>
								</div>
								<div class="btn_area">
									<div class="btn_wrap">
										<a class="border_btn" href="javascript:fnc.moveLogout()"> <span>로그아웃</span></a>
									</div>
									<div class="btn_wrap">
										<a class="b_color_btn" href="javascript:common.delayLogOut()"> <span>로그인연장</span></a>
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
	<input type="hidden" name="ssoTkn" 	  id="ssoTkn"  	value="" /> 
	<input type="hidden" name="acesTkn"   id="acesTkn" 	value="" /> 
	<input type="hidden" name="rnwTkn"	  id="rnwTkn"  	value="" /> 
	<input type="hidden" name="onlCstTpC" id="onlCstTpC"value="" /> 
	<input type="hidden" name="frnYn" 	  id="frnYn" 	value="" /> 
	<input type="hidden" name="rspClac"   id="rspClac" 	value="" /> 
	<input type="hidden" name="rspC" 	  id="rspC" 	value="" /> 
	<input type="hidden" name="rspDtc" 	  id="rspDtc"   value="" /> 
	<input type="hidden" name="rspMsgCn"  id="rspMsgCn" value="" />
	<input type="hidden" name="rtnUrl" 	  id="rtnUrl" value="" />
</form>

<script type="text/javascript" src="/common/js/member/sso.js"></script></div>
<!--  end wrap -->

	<script type="text/javascript" src="/common/netfunnel/netfunnel.js" charset="UTF-8"></script>
	
	<script>
		if(true)
		{
			common.logOutTimer.start();
		}
		commonScript.headerFooterFn();
		commonScript.formChkFn();
	</script>
		
	</body>
</html>