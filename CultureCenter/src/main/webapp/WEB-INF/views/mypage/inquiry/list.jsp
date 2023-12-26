<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>

<div class="cont_wrap">
	<div class="cont_inner no_pb">
		<form id="frmInquiry" name="frmInquiry" data-page-type="list">
			<input type="hidden" id="faq_status" 	 name="faq_status"	   value=""/>
		    <input type="hidden" id="pageIndex"  name="pageIndex"  value="1"/>
			<input type="hidden" id="initIndex"  name="initIndex"  value="1"/>
			<input type="hidden" id="listCnt" 	 name="listCnt"    value="2"/>
		</form>

		<div class="page_title_area">
			<div class="inner">
				<div class="top_area">
					<a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a> 
					<a href="javascript:" class="tit_div arrow" title="페이지 이동 팝업 열기">
						<p class="tit f_h1">1:1 문의 <span class="more_tit"></span></p>
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
       	<a class="" href="/mypage/freebie/appList.do"><p class="f_desc">사은품 신청</p></a>
       	<a class="" href="/mypage/freebie/detailList.do"><p class="f_desc">사은품 신청내역</p></a>
       	<a class="" href="/mypage/coupon/list.do"><p class="f_desc">나의 쿠폰</p></a>
       	<a class="" href="/mypage/myreview/list.do"><p class="f_desc">나의 수강후기</p></a>
       	<a class="" href="/mypage/teachereval/list.do"><p class="f_desc">만족도 평가</p></a>
      	<a class="" href="/mypage/lectureCertf/list.do"><p class="f_desc">수강증</p></a>
       	<a class="active" href="/mypage/inquiry/list.do"><p class="f_desc">1:1 문의</p></a>
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
												<a href="javascript:" class="btn dropdown_btn filter_popup_btn" title="컨텐츠정렬 팝업 열기"><div class="order_txt">전체</div></a>
											</div>
											<div class="dimd"></div>
											<div class="filter_list_wrap">
												<div class="tit_area">
													<p class="tit">접수상태</p>
													<a href="javascript:" role="button" class="close"></a>
												</div>
												<div class="scroll_wrap">
														<a class="txt f_caption2 on" href="javascript:mypageInquiryCtrl.selectStatus('');">전체</a>
						                                <a class="txt f_caption2" href="javascript:mypageInquiryCtrl.selectStatus('접수중');">접수중</a>
						                                <a class="txt f_caption2" href="javascript:mypageInquiryCtrl.selectStatus('답변완료');">답변완료</a>
												</div>
											</div>
										</div>
									</div>
								</div>
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
				<!-- flex_btn_wrap 클래스가 있으면 모바일 width flex됌 -->
				<div class="flex_btn_wrap no_pb margin_large">
					<a class="border_btn" href="/information/faq/list.do" target="_self"> <span>자주하는 문의</span></a> 
					<a class="b_color_btn" href="javascript:mypageInquiryCtrl.openPopup('#inquiryRegPop');"role="buttion"> <span>1:1문의 작성하기</span></a>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="layer_popup inquiry_register" id="inquiryRegPop"
	style="display: none;">
	<div class="pop_wrap w800 full">
		<div class="pop_head">
			<p class="title">1:1 문의 등록</p>
		</div>
		<div class="pop_cont">
			<div class="for_padding">
				<div class="scroll_area">
					<form id="inquiryRegFrm" method="post">
						<input type="hidden" id="branch_id" name="branch_id" value="" /> 
						<input type="hidden" id="faq_tp_id" name="faq_tp_id" value="" />
						<div class="dot_txt_box">
							<p class="dot_txt">고객서비스의 [자주하는 문의]에서 자주 질문하는 답변을 보실 수 있습니다.</p>
							<p class="dot_txt">자주하는 문의에 없는 질문은 1:1 문의를 해주시면 빠른 시일 안에 답변을
								보내드리겠습니다.</p>
							<p class="dot_txt">답변 내용은 마이페이지의 [1:1 문의]에서 확인하실 수 있습니다.</p>
						</div>
						<div class="sub_inner">
							<div class="data_input_wrap">
								<div class="row">
									<div class="th">
										<p class="tit">제목</p>
									</div>
									<div class="td">
										<div class="form_input">
											<input type="text" name="faq_title" placeholder="제목을 입력해주세요" autocomplete="off" maxlength="50">
											<div class="input_btn_wrap">
												<button type="button" class="btn_delete" title="제목 지우기"></button>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="th">
										<p class="tit">문의 유형</p>
									</div>
									<div class="td">
										<div class="form_select_div cd1">
											<div class="open_area">
												<a class="btn_open" href="javascript:"> <span>선택</span>
												</a>
											</div>
											<div class="dimd"></div>
											<div class="list_wrap">
												<div class="scroll_wrap select_div cd1">
													<a class="btn_link default" href="javascript:"
														onclick="mypageInquiryCtrl.selectDiv(this, '')"><span>선택</span></a>
													<a class="btn_link cd1" href="javascript:"
															onclick="mypageInquiryCtrl.selectDiv(this, '1')"><span>회원가입</span></a>
													<a class="btn_link cd1" href="javascript:"
															onclick="mypageInquiryCtrl.selectDiv(this, '3')"><span>수강신청</span></a>
													<a class="btn_link cd1" href="javascript:"
															onclick="mypageInquiryCtrl.selectDiv(this, '4')"><span>강좌/강사</span></a>
													<a class="btn_link cd1" href="javascript:"
															onclick="mypageInquiryCtrl.selectDiv(this, '5')"><span>환불/취소</span></a>
													<a class="btn_link cd1" href="javascript:"
															onclick="mypageInquiryCtrl.selectDiv(this, '6')"><span>홈페이지</span></a>
													<a class="btn_link cd1" href="javascript:"
															onclick="mypageInquiryCtrl.selectDiv(this, '7')"><span>기타</span></a>
													</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="th">
										<p class="tit">지점</p>
									</div>
									<div class="td">
										<div class="form_select_div cd2">
											<div class="open_area">
												<a class="btn_open" href="javascript:"> <span>선택</span>
												</a>
											</div>
											<div class="dimd"></div>
											<div class="list_wrap">
												<div class="scroll_wrap select_div cd2">
													<a class="btn_link default" href="javascript:"
														onclick="mypageInquiryCtrl.selectDiv(this, '')"><span>선택</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0002')"><span>잠실점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0001')"><span>본점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0013')"><span>강남점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0028')"><span>건대스타시티점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0006')"><span>관악점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0340')"><span>김포공항점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0022')"><span>노원점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0026')"><span>미아점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0010')"><span>영등포점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0004')"><span>청량리점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0344')"><span>인천점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0399')"><span>동탄점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0335')"><span>구리점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0008')"><span>분당점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0349')"><span>수원점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0336')"><span>안산점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0011')"><span>일산점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0334')"><span>중동점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0341')"><span>평촌점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0005')"><span>부산본점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0333')"><span>광복점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0007')"><span>광주점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0023')"><span>대구점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0012')"><span>대전점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0016')"><span>동래점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0354')"><span>마산점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0024')"><span>상인점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0027')"><span>센텀시티점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0015')"><span>울산점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0025')"><span>전주점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0017')"><span>창원점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0014')"><span>포항점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0361')"><span>롯데몰군산점</span></a>
													<a class="btn_link" href="javascript:" onclick="mypageInquiryCtrl.selectDiv(this, '0350')"><span>롯데몰광명점</span></a>
													<!-- 												<a class="btn_link" href="javascript:"><span>선택2</span></a> -->
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="th">
										<p class="tit">문의 내용</p>
									</div>
									<div class="td">
										<div class="form_textarea">
											<div class="wrap_for_msg">
												<textarea name="faq_detail" id="" cols="" rows="" placeholder="문의 내용을 입력해주세요." data-maxlength="250" onkeyup="mypageInquiryCtrl.textareaOnkeyup(this)"></textarea>
											</div>
											<p class="check_byte">
			                                    <span class="r_byte">0</span>
			                                    /
			                                    <span class="l_byte">250</span>
			                                </p>
										</div>
									</div>
								</div>
							</div>
							<div class="flex_btn_wrap">
								<a class="border_btn"
									href="javascript:mypageInquiryCtrl.close();"> <span>취소하기</span>
								</a> <a class="b_color_btn"
									href="javascript:mypageInquiryCtrl.save();"> <span>등록하기</span>
								</a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<a class="btn_close" href="javascript:mypageInquiryCtrl.close();" title="닫기"> <span class="blind"></span></a>
	</div>
</div>

<script type="text/javascript" src="/resources/common/js/mypage/mypageInquiryCtrl.js"></script>