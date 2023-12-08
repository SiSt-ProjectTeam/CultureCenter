<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="main cont_wrap">
	<div class="cont_inner">
		<!-- 비주얼 -->
		<section class="visual_wrap">
			<div class="visual_area">
				<div class="visual_div">
					<div class="swiper-container">
						<div class="swiper-wrapper">
							<a href="https://culture.lotteshopping.com/community/notice/view.do?brchCd=&amp;clCd=8&amp;notcSeqno=285&amp;" class="swiper-slide" target="_blank">
									<div class="bg_div" data-swiper-parallax-x="100%" data-swiper-parallax-scale="1.2">
										
										<p class="bg only_pc">
											<img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/10/202310170301016700.jpg" alt="겨울학기 메인배너 (pc).jpg">
												</p>
										<!-- 분기처리 -->
										<p class="bg only_mobile">
											<img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/10/202310170435591521.jpg" alt="겨울학기 메인배너 (mo).jpg">
												</p>
										</div>
									<div class="txt_area">
										<p class="title" style="color:;">Remember WINTER<br/>겨울학기 접수안내</p>
										<div class="txt_wrap">
											<div class="txt_div">
												<p class="txt f_body1" style="color:;">[자세히보기]</p>
											</div>
										</div>
										</div>
									</a>
							</div>
						<div class="swiper-pagination"></div>
						<div class="swiper-button-next"></div>
						<div class="swiper-button-prev"></div>
					</div>
				</div>
				<div class="control_area">
					<div class="num_div">
						<!-- <p class="current_num">1</p> -->
						<p class="progress_bar">
							<span class="bar"></span>
						</p>
						<!-- <p class="total_num"></p> -->
					</div>
					<button class="visual_btn pause" type="button"></button>
				</div>
			</div>
		</section>
		<!-- 추천강좌 -->
		<section class="lecture_wrap scroll_motion recommend">
			<div class="inner">
				<div class="tit_area">
					<p class="tit">추천강좌</p>
					<p class="sub_tit">
						엄선된 강좌를 <br />소개합니다
					</p>
				</div>
			</div>
			<div class="lecture_list_w">
				<div class="swiper-container">
					<div class="swiper-wrapper" id="recommendationContainer"></div>
					<div class="swiper-pagination"></div>
				</div>
			</div>
		</section>
		<!-- 강좌 카테고리 -->
		<section class="category_wrap scroll_motion">
			<div class="bg category">
				<div class="inner">
					<div class="tit_area">
						<p class="tit">강좌 카테고리</p>
						<p class="sub_tit">
							일상을 빛낼 취향을 <br />발견하세요!
						</p>
					</div>
				</div>
				<div class="half_wrap">
					<div class="left">
						<div class="form_select_div round">
							<div class="open_area">
								<a class="btn_open" href="javascript:" title="강좌 카테고리 팝업 열기">
									<span></span>
								</a>
							</div>
							<div class="dimd"></div>
							<div class="list_wrap">
								<div class="tit_area">
									<p class="tit">강좌</p>
									<a href="javascript:" role="button" class="close"></a>
								</div>
								<div class="scroll_wrap">
									<div class="btn_list">
												<p class="tit">성인강좌</p>
												<a class="btn_link" href="javascript:mainCtrl.getCategoryClassList();" data-lrcls-ctegry-cd="01"
															data-mdcls-ctegry-cd="0101"><span>공예</span></a>
													<a class="btn_link" href="javascript:mainCtrl.getCategoryClassList();" data-lrcls-ctegry-cd="01"
															data-mdcls-ctegry-cd="0102"><span>노래</span></a>
													<a class="btn_link" href="javascript:mainCtrl.getCategoryClassList();" data-lrcls-ctegry-cd="01"
															data-mdcls-ctegry-cd="0103"><span>댄스</span></a>
													<a class="btn_link" href="javascript:mainCtrl.getCategoryClassList();" data-lrcls-ctegry-cd="01"
															data-mdcls-ctegry-cd="0104"><span>드로잉</span></a>
													<a class="btn_link" href="javascript:mainCtrl.getCategoryClassList();" data-lrcls-ctegry-cd="01"
															data-mdcls-ctegry-cd="0105"><span>라이프스타일</span></a>
													<a class="btn_link" href="javascript:mainCtrl.getCategoryClassList();" data-lrcls-ctegry-cd="01"
															data-mdcls-ctegry-cd="0106"><span>악기</span></a>
													<a class="btn_link" href="javascript:mainCtrl.getCategoryClassList();" data-lrcls-ctegry-cd="01"
															data-mdcls-ctegry-cd="0107"><span>어학</span></a>
													<a class="btn_link" href="javascript:mainCtrl.getCategoryClassList();" data-lrcls-ctegry-cd="01"
															data-mdcls-ctegry-cd="0108"><span>인문학</span></a>
													<a class="btn_link" href="javascript:mainCtrl.getCategoryClassList();" data-lrcls-ctegry-cd="01"
															data-mdcls-ctegry-cd="0109"><span>재테크</span></a>
													<a class="btn_link" href="javascript:mainCtrl.getCategoryClassList();" data-lrcls-ctegry-cd="01"
															data-mdcls-ctegry-cd="0110"><span>커리어</span></a>
													<a class="btn_link" href="javascript:mainCtrl.getCategoryClassList();" data-lrcls-ctegry-cd="01"
															data-mdcls-ctegry-cd="0111"><span>쿠킹</span></a>
													<a class="btn_link" href="javascript:mainCtrl.getCategoryClassList();" data-lrcls-ctegry-cd="01"
															data-mdcls-ctegry-cd="0112"><span>피트니스</span></a>
													</div>
										<div class="btn_list">
												<p class="tit">영·유아강좌</p>
												<a class="btn_link" href="javascript:mainCtrl.getCategoryClassList();" data-lrcls-ctegry-cd="02"
															data-mdcls-ctegry-cd="0202"><span>오감발달</span></a>
													<a class="btn_link" href="javascript:mainCtrl.getCategoryClassList();" data-lrcls-ctegry-cd="02"
															data-mdcls-ctegry-cd="0201"><span>창의·체험</span></a>
													<a class="btn_link" href="javascript:mainCtrl.getCategoryClassList();" data-lrcls-ctegry-cd="02"
															data-mdcls-ctegry-cd="0203"><span>음악·미술</span></a>
													<a class="btn_link" href="javascript:mainCtrl.getCategoryClassList();" data-lrcls-ctegry-cd="02"
															data-mdcls-ctegry-cd="0204"><span>언어·사회성</span></a>
													<a class="btn_link" href="javascript:mainCtrl.getCategoryClassList();" data-lrcls-ctegry-cd="02"
															data-mdcls-ctegry-cd="0205"><span>신체활동</span></a>
													</div>
										<div class="btn_list">
												<p class="tit">아동강좌</p>
												<a class="btn_link" href="javascript:mainCtrl.getCategoryClassList();" data-lrcls-ctegry-cd="03"
															data-mdcls-ctegry-cd="0302"><span>신체활동</span></a>
													<a class="btn_link" href="javascript:mainCtrl.getCategoryClassList();" data-lrcls-ctegry-cd="03"
															data-mdcls-ctegry-cd="0303"><span>창의·체험</span></a>
													<a class="btn_link" href="javascript:mainCtrl.getCategoryClassList();" data-lrcls-ctegry-cd="03"
															data-mdcls-ctegry-cd="0301"><span>음악·미술</span></a>
													<a class="btn_link" href="javascript:mainCtrl.getCategoryClassList();" data-lrcls-ctegry-cd="03"
															data-mdcls-ctegry-cd="0304"><span>언어·사회성</span></a>
													</div>
										</div>
							</div>
						</div>
						
						<div class="img_con_w fir">						
							<div class="img_con">
								<p class="img_resize_w">
									<img src="" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260533029151.jpg" alt="메인_성인_공예.jpg">
								</p>
							</div>
							<div class="img_con">
								<p class="img_resize_w">
									<img src="" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260533193383.jpg" alt="메인_성인_노래.jpg">
								</p>
							</div>
							<div class="img_con">
								<p class="img_resize_w">
									<img src="" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260533306673.jpg" alt="메인_성인_댄스.jpg">
								</p>
							</div>
							<div class="img_con">
								<p class="img_resize_w">
									<img src="" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260542406471.jpg" alt="메인_성인_드로잉.jpg">
								</p>
							</div>
							<div class="img_con">
								<p class="img_resize_w">
									<img src="" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260545030363.jpg" alt="메인_성인_라이프스타일.jpg">
								</p>
							</div>
							<div class="img_con">
								<p class="img_resize_w">
									<img src="" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260546325223.jpg" alt="메인_성인_악기.jpg">
								</p>
							</div>
							<div class="img_con">
								<p class="img_resize_w">
									<img src="" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260547571393.jpg" alt="메인_성인_어학.jpg">
								</p>
							</div>
							<div class="img_con">
								<p class="img_resize_w">
									<img src="" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260549229583.jpg" alt="메인_성인_인문학.jpg">
								</p>
							</div>
							<div class="img_con">
								<p class="img_resize_w">
									<img src="" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260551002553.jpg" alt="메인_성인_재테크.jpg">
								</p>
							</div>
							<div class="img_con">
								<p class="img_resize_w">
									<img src="" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260552512823.jpg" alt="메인_성인_커리어.jpg">
								</p>
							</div>
							<div class="img_con">
								<p class="img_resize_w">
									<img src="" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260554154173.jpg" alt="메인_성인_쿠킹.jpg">
								</p>
							</div>
							<div class="img_con">
								<p class="img_resize_w">
									<img src="" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260556304753.jpg" alt="메인_성인_피트니스.jpg">
								</p>
							</div>
							<div class="img_con">
								<p class="img_resize_w">
									<img src="" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260558211673.jpg" alt="메인_영유아_오감발달.jpg">
								</p>
							</div>
							<div class="img_con">
								<p class="img_resize_w">
									<img src="" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260558354653.jpg" alt="메인_아동_창의체험.jpg">
								</p>
							</div>
							<div class="img_con">
								<p class="img_resize_w">
									<img src="" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260558478873.jpg" alt="메인_영유아_음악미술.jpg">
								</p>
							</div>
							<div class="img_con">
								<p class="img_resize_w">
									<img src="" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260559050132.jpg" alt="메인_영유아_언어사회성.jpg">
								</p>
							</div>
							<div class="img_con">
								<p class="img_resize_w">
									<img src="" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260559206703.jpg" alt="메인_영유아_신체활동.jpg">
								</p>
							</div>
							<div class="img_con">
								<p class="img_resize_w">
									<img src="" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260559321243.jpg" alt="메인_아동_신체활동.jpg">
								</p>
							</div>
							<div class="img_con">
								<p class="img_resize_w">
									<img src="" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260559439523.jpg" alt="메인_영유아_창의체험.jpg">
								</p>
							</div>
							<div class="img_con">
								<p class="img_resize_w">
									<img src="" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260600003284.jpg" alt="메인_아동_음악미술.jpg">
								</p>
							</div>
							<div class="img_con">
								<p class="img_resize_w">
									<img src="" data-src="https://culture.lotteshopping.com/files/CUL_ONL/2023/4/202304260600132862.jpg" alt="메인_아동_언어사회성.jpg">
								</p>
							</div>
						</div>
					</div>
					<div class="right">
						<div class="category_con_w">
							<div class="category_con" id="categoyContainer"></div>
						</div>
					</div>
					
					<script type="text/javascript">
						var btnLen = $(".category_wrap .form_select_div .scroll_wrap .btn_list .btn_link").length
						var randVal = Math.floor(Math.random() * btnLen);
					
						var title = $(".category_wrap .form_select_div .scroll_wrap .btn_list .btn_link:eq( " + randVal + ")").addClass("on").find("span").text();
									$(".category_wrap .form_select_div .open_area span").text(title);
						if($(".category_wrap .img_con_w").length > 0) {
							$(".category_wrap .img_con_w .img_con").eq(randVal).find('.img_resize_w img').attr('src', $(".category_wrap .img_con_w .img_con").eq(randVal).find('.img_resize_w img').data().src);
							$(".category_wrap .img_con_w .img_con").eq(randVal).addClass("on").show();
						}						
						$(".category_wrap .bg,.category").removeClass("category").addClass("category" + randVal);
						
					</script>
				</div>
			</div>
		</section>
		<!-- 신규강좌 -->
		<section class="lecture_wrap scroll_motion">
			<div class="inner">
				<div class="tit_area">
					<p class="tit">
						신규강좌</p>
					<p class="sub_tit">
						새롭게 개설된 강좌를 <br />지금 확인해보세요
					</p>
				</div>
			</div>
			<div class="lecture_list_w">
				<div class="swiper-container">
					<div class="swiper-wrapper" id="newContainer"></div>
					<div class="swiper-pagination"></div>
				</div>
			</div>
		</section>
		<!-- 이벤트 -->
		<section class="event_wrap scroll_motion">
			<div class="tit_area">
				<p class="tit">혜택</p>
				<p class="sub_tit">회원님을 위한 <br />혜택을 알려드립니다</p>
			</div>
			<div class="event_slide_wrap">
				<div class="swiper-container">
					<div class="swiper-wrapper">
						<a href="https://youtu.be/bun9qtEJ5Os?si=mlVFkjgRQc_-fNU-" class="swiper-slide" target="_blank">
								<div class="img_wrap" data-swiper-parallax-x="100%" data-swiper-parallax-scale="1.2">
									<div class="only_pc">
										<p class="img"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/11/202311130351092710.jpg" alt=""></p><!-- pc용 이미지 -->
									</div>
									<div class="only_mobile">
										<p class="img"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/11/202311130351092550.jpg" alt=""></p><!-- mobile용 이미지 -->
									</div>
									</div>
							</a>
						<a href="https://www.lottemuseum.com/" class="swiper-slide" target="_blank">
								<div class="img_wrap" data-swiper-parallax-x="100%" data-swiper-parallax-scale="1.2">
									<div class="only_pc">
										<p class="img"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/10/202310250855547080.jpg" alt=""></p><!-- pc용 이미지 -->
									</div>
									<div class="only_mobile">
										<p class="img"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/10/202310250855546460.jpg" alt=""></p><!-- mobile용 이미지 -->
									</div>
									</div>
							</a>
						<a href="https://bit.ly/3ryhAl5" class="swiper-slide" target="_blank">
								<div class="img_wrap" data-swiper-parallax-x="100%" data-swiper-parallax-scale="1.2">
									<div class="only_pc">
										<p class="img"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/7/202307260934301371.jpg" alt=""></p><!-- pc용 이미지 -->
									</div>
									<div class="only_mobile">
										<p class="img"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/7/202307260934301211.jpg" alt=""></p><!-- mobile용 이미지 -->
									</div>
									</div>
							</a>
						<a href="https://www.saengong.com/" class="swiper-slide" target="_blank">
								<div class="img_wrap" data-swiper-parallax-x="100%" data-swiper-parallax-scale="1.2">
									<div class="only_pc">
										<p class="img"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/3/202303280529591813.jpg" alt=""></p><!-- pc용 이미지 -->
									</div>
									<div class="only_mobile">
										<p class="img"><img src="https://culture.lotteshopping.com/files/CUL_ONL/2023/3/202303280529591652.jpg" alt=""></p><!-- mobile용 이미지 -->
									</div>
									</div>
							</a>
						</div>
				</div>
				<div class="swiper-button-next"></div>
				<div class="swiper-button-prev"></div>
				<div class="control_area">
					<div class="swiper-pagination"></div>
					<p class="progress_bar"><span class="bar"></span></p>
					<button class="visual_btn pause" type="button"></button>
				</div>
			</div>
		</section>
		<!-- 다양한 소식 -->
		<section class="news_wrap scroll_motion">
				<div class="tit_area">
					<a href="/community/notice/list.do" class="tit">공지사항</a>
				</div>
				<div class="news_list">
					<a href="/community/notice/view.do?notcSeqno=330" class="news">
							<p class="tit">[이벤트] 캐치! 티니핑 복숭아맛 음료 사은품 증정</p>
							<p class="txt">
									





■ 뮤지컬복합극 루돌프의 크리스마스 1일 특강신청 시

캐치! 티니핑복숭아맛 음료 200mL (1종) 증정

- 증정 기간 : 12/9(토)




</p>
							<p class="date">2023.11.23</p>
						</a>
					<a href="/community/notice/view.do?notcSeqno=285" class="news">
							<p class="tit">[공지사항] 23년 겨울학기 안내</p>
							<p class="txt">
									23년 겨울학기안내


지역별 접수기간


[수도권점]

기존회원: 2023.10.25 (수) 10:30~ 선착순 마감

신규회원:2023.10.26(목) 10:30~ 선착순 마감


기존회원할인 10% (10.25~11.14)

8주 이상 정기강좌, 최대 2강좌에 한함



[서울ㆍ지방점]

기존회원: 2023.10.26 (목) 10:30~ 선착순 마감

신규회원:2023.10.27 (금) 10:30~ 선착순 마감


기존회원할인 10% (10.26~11.15)

8주 이상 정기강좌, 최대 2강좌에 한함


※ 노원점 리뉴얼 OPEN 1월 말 예정
겨울학기 강좌접수 추후 별도 공지


강좌기간

2023.12.1(금) ~ 2024.2.29(목)


문의

각점 문화센터 데스크
(점별 운영시간 상이)

.
.
.


매거진Vol.8발행
Remember WINTER




Lifestyle LAB 매거진은
동시대다양한 사람들의 삶을 연구하며
그에 맞춰 다채로운 취향을 쌓을 수 있도록 돕고자 합니다.

8호는&amp;lsquo;간직하고 싶은 순간&amp;rsquo;에 대해 다룹니다.
겨울을 맞아 나의 몸과 마음을 살피고,
한해의 기억을 돌아보며
다시금 의미를 찾는 시간이 되기를 바랍니다.


Cover Story
붙잡아 두지 않으면
기억은 쉬이 흘러가기 마련입니다.

이번 겨울에는 특별한 순간을
나만의 방식으로 기록해 보면 어떨까요

한장의 필름에 정성스레 남기는 마음을
자몽사진관의 시선으로 담아냅니다.





Lifestyle LAB 매거진은
전국 문화센터 로비에서 만나볼 수 있으며(한정수량)

롯데문화센터 홈페이지
웹매거진 카테고리를 통해
일부 콘텐츠가 공개됩니다.
</p>
							<p class="date">2023.10.13</p>
						</a>
					<a href="/community/notice/view.do?notcSeqno=172" class="news">
							<p class="tit">[공지사항] 정기휴점 출입동선 안내</p>
							<p class="txt">
									
롯데마트 주차장에 주차

마트 무빙워크 홀을 지나쳐
아쿠아몰 고객용 엘리베이터 9&amp;middot;10호기를 이용하시면 됩니다

* B2F 210~212번 주차편리
* B3F 314~316번 주차편리





남포역 8번 출구 우측 계단으로 올라오시면
롯데마트 정문이 보입니다

롯데마트 정문 옆 아쿠아몰
직원 출입 통로로 출입하여
고객용 엘리베이터9&amp;middot;10호기를 이용하시면 됩니다

* 롯데마트 들어가시면 연결 X



문의 |문화센터
051-678-2351~3
(운영시간 10:30~19:00)
</p>
							<p class="date">2023.05.19</p>
						</a>
					<a href="/community/notice/view.do?notcSeqno=336" class="news">
							<p class="tit">[공지사항] 백화점 오픈 전 문화센터 이동 경로</p>
							<p class="txt">
									백화점오픈전이동 경로 안내


문화센터 이용 고객님께서는아래 출입구 동선을 통하여 이동해주시길 바랍니다.

고맙습니다.


# 대중교통 이용 고객

수원역 환승센터 &gt; 롯데몰 2층 정문 Gate 1번 &gt;
스타벅스 왼편에 위치한 전망용 엘레베이터 이용

※ 수원점 리뉴얼 진행중으로 이동동선이 변동 될 수 있으며
안전팀의 안내에 따라 엘레베이터 이용 및 이동 부탁드립니다.

★ 엘레베이터 가동 시작시간 10:00


#자차이용고객

지하 2층 주차구역 V3, V4 주차 &gt; 정면의 전망용 엘레베이터 1,2호기 이용

★ 엘레베이터 가동 시작시간 10:00





☎ 문의 031) 8066-2630~1 (롯데백화점 수원점 7층 문화센터)
</p>
							<p class="date">2023.11.29</p>
						</a>
					</div>
				<p class="more_news">
					<a href="/community/notice/list.do">롯데문화센터의 다양한 소식을 확인해보세요!</a>
				</p>
			</section>
		</div>
</div>
<script type="text/javascript" src="/resources/common/js/main/mainCtrl.js"></script>
<script type="text/javascript">

	mainCtrl.getRecommendationClassList();

	mainCtrl.getCategoryClassList();

	mainCtrl.getNewClassList();
</script>