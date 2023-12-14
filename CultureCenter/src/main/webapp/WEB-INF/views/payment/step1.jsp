<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>	
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
			
<form id="frm_submit" name="frm_submit">
	<input type="hidden" name="csrfPreventionSalt" value="" />
	<input type="hidden" name="jsonStr" value="" />
	<input type="hidden" name="atlctType" value="normal" />
</form>
<div class="cont_wrap">
  <div class="cont_inner no_pb">
    <div class="page_title_area">
      <div class="inner">
        <div class="top_area">
          <a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a>
          <div class="tit_div">
            <p class="tit f_h1">수강결제</p>
          </div>
        </div>
      </div>
      <div class="m_pop_dimd"></div>
    </div>

    <div class="page_cont_area">
      <div class="inner pay_info">
        <div class="pay_step">
          <div class="process_wrap">
            <div class="process_div on">
              <p class="num"><span>1</span></p>
              <p class="txt">수강정보 확인</p>
            </div>
            <div class="process_div">
              <p class="num"><span>2</span></p>
              <p class="txt">결제정보 확인</p>
            </div>
            <div class="process_div">
              <p class="num"><span>3</span></p>
              <p class="txt">수강신청 완료</p>
            </div>
          </div>
          <!-- start 결제할 수강정보 -->
          <div class="sub_inner_w">
            <div class="sub_inner">
	<div class="sub_tit_area">
		<div class="left">
			<p class="f_h2">수강자 정보</p>
		</div>
	</div>
<!-- 수강정보 -->
<div class="course_history_w">
	<c:forEach items="${list}" var="dto">
	<fmt:formatNumber value="${dto.class_fee}" pattern="#,##0" var="class_fee"/>
	<fmt:formatNumber value="${dto.ex_charge}" pattern="#,##0" var="ex_charge"/>
	<fmt:formatNumber value="${dto.class_fee + dto.ex_charge}" pattern="#,##0" var="tot_fee"/>
	
		<div class="cour_his_list" data-brch-cd="${dto.branch_id}"
				data-yy="${dto.open_year}" data-lect-smster-cd="${dto.open_smst_id}"
				data-lect-cd="${dto.detail_class_sq}" data-obj-cl-cd="01"
				data-lect-nm="${dto.class_nm}" data-lect-amt="${dto.class_fee}"
				data-class-st-id="${dto.class_st_id}"
				data-optn-typ-cd-nm="재료비/대여료" data-optn-nm=""
				data-optn-amt="${dto.ex_charge}" data-optn-seqno=""
				data-optn-use-yn="${empty dto.ex_charge ? 'Y':'N'}"
				data-lect-tp-cd="" data-pbl-pmprcust-brch-cd=""
				data-pbl-pmprcust-lect-cd="" data-pbl-pmprcust-brch-cd-nm="">
			<div class="cour_top_area">
					<div class="left">
						<div class="label_div">
							<p class="label small border">${dto.branch_nm}</p>
						</div>
						<p class="tit f_h2">${dto.class_nm}</p>
					</div>
					<div class="right">
						<ul class="txt_wrap">
							<li class="dl f_body2">
								<p class="dt only_pc">강사명</p>
								<p class="dd f_body1">${dto.teacher_nm}</p>
							</li>
							<li class="dl f_body2">
								<p class="dt only_pc">학기명</p>
								<p class="dd f_body1">${dto.open_year}년 ${dto.smst_nm}</p>
							</li>
							<li class="dl f_body2">
								<p class="dt only_pc">강좌정보</p>
								<p class="dd f_body1">
								${dto.schedule_start_dt} ~ ${dto.schedule_end_dt} (${dto.day}) 
								${dto.start_time}~${dto.end_time}
								/ ${dto.class_cnt}회</p>
							</li>
						</ul>
						<ul class="txt_wrap">
							<li class="dl f_body2">
								<p class="dt">강좌료</p>
								<p class="dd f_body1">${class_fee}원</p>
							</li>
							<c:if test="${dto.ex_charge != 0}">
								<li class="dl f_body2">
									<p class="dt">재료비/대여료</p>
									<p class="dd f_body1">${ex_charge}원</p>
								</li>
							</c:if>
						</ul>
					</div>
				</div>
			<!-- 수강자 정보 -->
			<div class="cour_detail_w">
				 <div class="cour_detail" data-kor-nm="${mDto.name}" data-fmly-rel-cd="00"
						data-fmly-rel-cd-nm="본인" data-bday="${mDto.m_birth_dt}" data-sex-cd="">
						<div class="left">
							<div class="tit f_body1">${mDto.name}(본인)</div>
							<div class="flex_btn_wrap">
								<c:choose>
								<c:when test="${dto.lrclsctegrycd eq '01'}">
								<a style="background-color: #e0f55c;" class="border_btn"
									href="javascript:" role="button"
									onclick="payment.openFmlyPopup(this, 'fmly')">
								<span style="color: #000;">수강자 변경/추가</span>
								</a>
								</c:when>
								<c:otherwise>
								<a style="background-color: #e0f55c;" class="border_btn"
									href="javascript:" role="button"
									onclick="payment.openFmlyPopup(this, 'child')">
								<span style="color: #000;">자녀회원 선택</span>
								</a>
								</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="right">
							<ul class="txt_wrap">
								<li class="f_body3">
									<div class="txt_con">
										<div class="tit">강좌료</div>
										<div class="txt">
											<p>${class_fee}원</p>
										</div>
									</div>
								</li>
								<c:if test="${dto.ex_charge != 0}">
								<li class="f_body3">
									<div class="txt_con">
										<div class="tit">재료비/대여료</div>
										<div class="txt">
											<p>${ex_charge}원</p>
										</div>
									</div>
								</li>
								</c:if>
								<li class="expected">
									<div class="txt_con">
										<div class="tit">결제예정 금액</div>
										<div class="txt">
											<p>${tot_fee}원</p>
										</div>
									</div>
								</li>
							</ul>
						</div>
					</div>
			</div>
		</div>
	</c:forEach>
</div>

              	
              <!-- 유의사항 -->
              <div class="dot_txt_box">
                <p class="f_body1">유의사항</p>
                <p class="dot_txt">수강자 본인 또는 동반 수강자 변경/추가를 원하시면 수강자 옆 버튼 [수강자 변경/추가]을 선택하세요.</p>
                <p class="dot_txt">영·유아, 아동 강좌의 실수강자를 등록하시려면 [자녀회원 선택]을 선택하세요.</p>
                <p class="dot_txt">동반 수강자 등록은 직계 가족까지만 가능합니다.</p>
                <p class="dot_txt">신청하신 강좌는 최소 정원에 미달되거나 사정에 의해 폐강 될 수 있으니 양해 바랍니다.</p>
              </div>
              
            </div>
    
            <!-- no_data클래스가 있으면 아래에 보더없음 -->
            <!-- 동반수강자 -->
			<div class="sub_inner ">
							<div class="sub_tit_area">
								<a href="javascript:" class="dropdown_btn">
									<div class="left">
										<p class="f_h2">동반 수강자 정보</p>
									</div>
									<div class="right">
										<span></span>
									</div>
								</a>
							</div>
							
							<div class="hide_list">
								<div class="flex_btn_wrap">
									<a class="border_btn" href="javascript:" role="button"
										onclick="payment.addChild()"> <span>자녀회원 추가하기</span>
									</a>
								</div>
								<div class="info_txt" id="familyList">
									<c:choose>
									<c:when test="${empty mDto.childrenList}">
										<div class="no_srch_area no_pb">
											<div class="no_srch_div">
												<p class="txt f_h2">
													<span class="normal_value">등록된 정보가 없습니다.</span>
												</p>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<c:forEach items="${mDto.childrenList}" var="childDto">
										<div class="info_list">
											<div class="writer_info">
												<p class="item_name f_body1">${childDto.children_nm}</p>
												<a href="javascript:" class="comment_remove f_caption1"
													onclick="payment.deleteFmly(this)" data-fmly-seqno="${childDto.children_sq}"
													data-kor-nm="${childDto.children_nm}" data-fmly-rel-cd="${childDto.member_sq}"
													data-sex-cd="${childDto.gender}" data-bday="${childDto.child_birth_dt}">삭제</a>
											</div>
											<div class="type_div">
												<p class="type">자녀</p>
												<p class="type">${childDto.child_birth_dt}</p>
												<p class="type">${childDto.realGender}</p>
											</div>
										</div>
										</c:forEach>
									</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
			</div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- payment_step클래스가 있으면 버튼2개폼 -->
<div class="payment_bar payment_step">
  <!-- 2022-11-23 구조 수정 -->
  <div class="payment_w">
    <div class="inner">
      <div class="payment_con">
        <div class="txt_box">
          <div class="txt">
            <p class="f_body4">총 결제예정금액</p>
          </div>
          <div class="all_price">
            <p><span class="price"></span><span class="f_body3">원</span></p>
          </div>
          <script> // 총결제금액
          $(function() {
       	  	payment.payment_tot();
		  });
          </script>
        </div>
        <div class="flex_btn_wrap">
          <a class="border_btn" href="javascript:fnc.back();">
            <span>이전</span>
          </a>
          <a class="b_color_btn" href="javascript:" onclick="payment.submitStep1Frm()">
            <span>다음단계</span>
          </a>
        </div>
      </div>
    </div>
  </div>
  <!-- // 2022-11-23 구조 수정 -->
</div>

<!-- "수강자 변경/추가" 팝업 -->
<div id="fmlyPopup" class="layer_popup" style="display:none;">
	<div class="pop_wrap w800 full">
		<div class="pop_head">
			<p class="title">수강자 변경/추가</p>
		</div>
		<div class="pop_cont">
			<div class="for_padding">
				<div class="scroll_area">
					<div class="dot_txt_box no_mt">
						<p class="dot_txt">실제 수강하실 수강생을 선택하세요.</p>
						<p class="dot_txt">수강할 동반 수강자를 선택 후 하단 [확인] 버튼을 클릭 하시면 창이 닫히고 저장 내용이 수강결제 페이지에 반영됩니다.</p>
						<p class="dot_txt">유 · 아동강좌(5세 이상), 영아강좌(5세 미만)는 반드시 자녀회원 등록 후 수강신청이 가능합니다.<br />(14세 미만 직계가족만 자녀회원 등록이 가능합니다.)</p>
					</div>
					<div class="sub_inner">
						<div class="form_table gray">
							<table>
								<caption>테이블 캡션 내용이 들어갑니다.</caption>
								<colgroup>
									<col width="17%">
									<col width="20%">
									<col width="20%">
									<col width="26%">
									<col width="17%">
								</colgroup>
								<thead>
									<tr>
										<th>선택</th>
										<th>이름</th>
										<th>관계</th>
										<th>생년월일</th>
										<th>성별</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<div class="form_checkbox">
												<input type="checkbox" id="student0" name="" data-kor-nm="${mDto.name}" data-fmly-rel-cd="${mDto.member_sq}" data-bday="${mDto.m_birth_dt}" data-fmly-rel-cd-nm="본인" data-sex-cd="">
												<label for="student0"></label>
											</div>
										</td>
										<td>
											<p class="f_body2">${mDto.name}</p>
										</td>
										<td>
											<p class="f_body2">본인</p>
										</td>
										<td>
											<p class="f_body2">${mDto.m_birth_dt}</p>
										</td>
										<td>
											<p class="f_body2">-</p>
										</td>
									</tr>
									<c:if test="${mDto.childrenList.size() > 0}">
										<c:forEach items="${mDto.childrenList}" var="childDto" varStatus="i">
											<tr>
												<td>
													<div class="form_checkbox">
														<input type="checkbox" id="student${i.index+1}" name="" data-kor-nm="${childDto.children_nm}" data-fmly-rel-cd="${childDto.member_sq}" data-bday="${childDto.child_birth_dt}" data-fmly-rel-cd-nm="자녀" data-sex-cd="${childDto.gender}" >
														<label for="student${i.index+1}"></label>
													</div>
												</td>
												<td>
													<p class="f_body2">${childDto.children_nm}</p>
												</td>
												<td>
													<p class="f_body2">자녀</p>
												</td>
												<td>
													<p class="f_body2">${childDto.child_birth_dt}</p>
												</td>
												<td>
													<p class="f_body2">${childDto.realGender}</p>
												</td>
											</tr>
										</c:forEach>	
									</c:if>
									</tbody>
							</table>
						</div>
						<div class="flex_btn_wrap">
							<a class="border_btn" href="javascript:" onclick="payment.closePopup(this)">
								<span>취소하기</span>
							</a>
							<a class="b_color_btn" href="javascript:" onclick="payment.updateActlAtlctNple(this, 'fmly')">
								<span>저장하기</span>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<a class="btn_close" href="javascript:" title="닫기">
			<span class="blind"></span>
		</a>
	</div>
</div>

<!-- "자녀회원 선택" 팝업 -->
<div id="childPopup" class="layer_popup" style="display:none;">
  <div class="pop_wrap w800 full">
    <div class="pop_head">
      <p class="title">자녀회원 선택</p>
    </div>
    <div class="pop_cont">
      <div class="for_padding">
        <div class="scroll_area">
          <div class="dot_txt_box no_mt">
            <p class="dot_txt">실제 수강하실 수강생을 선택하세요.</p>
            <p class="dot_txt">유 · 아동강좌(5세 이상), 영아강좌(5세 미만)는 반드시 자녀회원 등록 후 수강신청이 가능합니다.<br />(14세 미만 직계가족만 자녀회원 등록이 가능합니다.)</p>
          </div>
          <div class="sub_inner">
            <div class="form_table gray">
              <table>
                <caption>테이블 캡션 내용이 들어갑니다.</caption>
                <colgroup>
                  <col width="17%">
                  <col width="20%">
                  <col width="20%">
                  <col width="26%">
                  <col width="17%">
                </colgroup>
                <thead>
                  <tr>
                    <th>선택</th>
                    <th>이름</th>
                    <th>관계</th>
                    <th>생년월일</th>
                    <th>성별</th>
                  </tr>
                </thead>
                <tbody>
                	<c:choose>
                	<c:when test="${mDto.childrenList.size() > 0}">
					<c:forEach items="${mDto.childrenList}" var="childDto" varStatus="i">
						<tr>
							<td>
								<div class="form_checkbox no_txt">
									<input type="checkbox" id="childStudent${i.index+1}" name="" 
									data-kor-nm="${childDto.children_nm}" 
									data-fmly-rel-cd="${childDto.member_sq}" 
									data-bday="${childDto.child_birth_dt}" 
									data-fmly-rel-cd-nm="자녀" 
									data-sex-cd="${childDto.gender}" >
									<label for="childStudent${i.index+1}"></label>
								</div>
							</td>
							<td>
								<p class="f_body2">${childDto.children_nm}</p>
							</td>
							<td>
								<p class="f_body2">자녀</p>
							</td>
							<td>
								<p class="f_body2">${childDto.child_birth_dt}</p>
							</td>
							<td>
								<p class="f_body2">${childDto.realGender}</p>
							</td>
						</tr>
					</c:forEach>
					</c:when>
					<c:otherwise>
					<tr>
						<td colspan="5">
							<p class="f_body2">등록된 정보가 없습니다.</p>
						</td>
					</tr>
					</c:otherwise>
					</c:choose>
                </tbody>
              </table>
            </div>
            <div class="flex_btn_wrap">
              <a class="border_btn" href="javascript:" onclick="payment.closePopup(this)">
                <span>취소하기</span>
              </a>
              <a class="b_color_btn" href="javascript:" onclick="payment.updateActlAtlctNple(this, 'child')">
                <span>저장하기</span>
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
    <a class="btn_close" href="javascript:" title="닫기">
      <span class="blind"></span>
    </a>
  </div>
</div>

<!-- "자녀회원 추가" 팝업 -->
<div class="layer_popup add_children" id="addFamilyPop" style="display: none; top: 0px; height: 943px;" tabindex="0">
	<div class="pop_wrap w800 full" style="transform: translate(0px, 0px); margin-left: -400px; margin-top: -377px; height: 754px;">
		<div class="pop_head">
			<p class="title">자녀회원 추가하기</p>
		</div>
		<div class="pop_cont" style="transform: translate(0px, 0px); height: 682px;">
			<div class="for_padding">
				<div class="scroll_area">
					<form id="addFamilyFrm" method="post">
					<p class="notice_txt f_body2">만 14세 미만의 아동 및 미성년자 수강 시 법정 대리인의 개인정보 수집 및 취급위탁업무에 동의가 필요합니다.</p>
					<p class="notice_txt f_body2">회원님의 동의를 통해 수집된 동반 수강자의 정보는 수강신청 및 의사소통의 용도로만 사용됩니다.</p>
					<div class="sub_inner">
						<div class="sub_tit_area">
							<div class="left">
								<p class="pop_sec_tit">자녀회원 정보</p>
							</div>
							<div class="right">
								<p class="f_caption2">
									<span class="softly_txt">*</span> 모든 항목은 필수입력 사항입니다.
								</p>
							</div>
						</div>
						<div class="data_input_wrap">
							<div class="row">
								<div class="th">
									<p class="tit f_body1">이름</p>
								</div>
								<div class="td">
									<div class="form_input">
										<input type="text" name="children_nm" placeholder="동반 수강자의 이름을 입력해주세요." oninput="$(this).val($(this).val().replace(/[^(가-힣ㄱ-ㅎㅏ-ㅣㆍᆢ\w\s\-)]/gi, ''))" maxlength="30">
										<div class="input_btn_wrap">
											<button type="button" class="btn_delete" title="이름 지우기" style="display: none;"></button>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="th">
									<p class="tit f_body1">관계</p>
								</div>
								<div class="td">
									<div class="form_input disabled">
										<!-- 2022-12-06 클래스 추가-->
										<input type="hidden" name="member_sq" value="${mDto.member_sq}">
										<input type="text" name="fmlyRelCdNm" value="자녀">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="th">
									<p class="tit f_body1">생년월일</p>
								</div>
								<div class="td">
									<div class="form_input">
										<input type="text" name="child_birth_dt" inputmode="numeric" maxlength="10" oninput="$(this).val(fnc.setDateFormat($(this).val()))" placeholder="예) 20201023">
										<div class="input_btn_wrap">
											<button type="button" class="btn_delete" title="생년월일 지우기"></button>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="th">
									<p class="tit f_body1">성별</p>
								</div>
								<div class="td small">
									<div class="radio_flex_box">
										<div class="form_radio">
											<input type="radio" id="gender1" name="gender" value="M" checked>
											<label for="gender1">남성</label>
										</div>
										<div class="form_radio">
											<input type="radio" id="gender2" name="gender" value="F">
											<label for="gender2">여성</label>
										</div>
									</div>
									<div class="form_checkbox">
										<input type="checkbox" id="chkAgrYn" name="chkAgrYn"> 
										<label for="chkAgrYn">자녀회원 정보 수집 및 활용동의</label>
									</div>
									<div class="terms_for_msg small">
										<div class="msg_div table_type">
											<!-- 2022-12-14 클래스 변경-->
											<!-- 2022-12-14 구조수정-->
											<div class="caption_txt_w">
												<p class="dot_txt">만 14세 미만의 아동 및 미성년자 가족 수강 시 법정 대리인의 개인정보 수집 및 취급위탁업무에 동의가 필요합니다.</p>
												<p class="dot_txt">회원님의 동의를 통해 수집된 가족 정보는 수강신청 및 의사소통의 용도로만 사용됩니다.</p>
											</div>
											<div class="tit_w">
												<p class="tit">수강신청을 위한 가족정보 수집 및 활용동의</p>
											</div>
											<div class="txt_box">
												<p class="info">1. 개인정보 수집항목, 수집목적 및 보유/이용 기간</p>
												<div class="table_div">
													<div class="form_table gray">
														<table>
															<caption>테이블 캡션 내용이 들어갑니다.</caption>
															<colgroup>
																<col width="12%">
																<col width="25%">
																<col width="38%">
																<col width="25%">
															</colgroup>
															<thead>
																<tr>
																	<th>구분</th>
																	<th>개인정보 수집항목</th>
																	<th>수집목적</th>
																	<th>보유 및 이용기간</th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<th class="border_t"><p>가족</p></th>
																	<td class="border_t"><p>가족회원 이름, 관계, 성별, 생년월일</p></td>
																	<td class="border_t"><p>가족회원(14세 미만 자녀)의 수강신청 시 회원 확인</p></td>
																	<td class="border_t"><p class="bold">동반수강자 삭제 시, 문화센터 회원 탈퇴 시</p></td>
																</tr>
															</tbody>
														</table>
													</div>
												</div>
												<p class="info">2. 본인은 롯데백화점 문화센터에 가입하기를 희망하며, 개인정보의 수집내용을 이해하고 동의합니다.</p>
												<p class="info">3. 가족정보 수집 및 이용 동의에 거부할 수 있으며, 이 경우 수강신청이 제한됩니다.</p>
											</div>
											<!-- // 2022-12-14 구조수정-->
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="dot_txt_box">
							<p class="dot_txt">
								등록 가능 관계 : 만 14세 미만 자녀<br>(14세 이상 자녀는 회원가입을 통해 수강 신청이 가능합니다.)
							</p>
							<p class="dot_txt">등록 불가능 관계 : 부모(배우자 부모 포함), 부부, 형제, 자매, 친구, 지인</p>
							<p class="dot_txt">14세 이상 동반 수강자의 경우 동반 수강자가 별도 회원가입 후 데스크 방문하시어 본인확인 후 진행하셔야 합니다.</p>
						</div>
						<div class="flex_btn_wrap">
							<a class="border_btn" href="javascript:addFamily.close()"><span>취소하기</span></a> 
							<a class="b_color_btn" href="javascript:addFamily.save()"><span>저장하기</span></a>
						</div>
					</div>
					</form>
				</div>
			</div>
		</div>
		<a class="btn_close" href="javascript:addFamily.close()" title="닫기">
			<span class="blind"></span>
		</a>
	</div>
</div>

<script type="text/javascript" src="/resources/common/js/member/addFamilyPop.js"></script>
<!-- <script type="text/javascript" src="/resources/common/js/mypage/mypageMember.js"></script> -->
<script type="text/javascript" src="/resources/common/js/payment/payment.js"></script>
