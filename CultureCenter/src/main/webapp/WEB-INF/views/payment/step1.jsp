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
		<div class="cour_his_list" data-brch-cd="${dto.branch_id}"
				data-yy="${dto.open_year}" data-lect-smster-cd="${dto.open_smst_id}"
				data-lect-cd="${dto.detail_class_sq}" data-obj-cl-cd="01"
				data-lect-nm="${dto.class_nm}" data-lect-amt="${dto.class_fee}"
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
								<p class="dd f_body1">
								<fmt:formatNumber value="${dto.class_fee}" currencySymbol=""></fmt:formatNumber> 원</p>
							</li>
							<c:if test="${dto.ex_charge != 0}">
								<li class="dl f_body2">
									<p class="dt">재료비/대여료</p>
									<p class="dd f_body1">${dto.ex_charge}원</p>
								</li>
							</c:if>
						</ul>
					</div>
				</div>
			<!-- 수강자 정보 -->
			<div class="cour_detail_w">
				<div class="cour_detail" data-kor-nm="유희진" data-fmly-rel-cd="00"
						data-fmly-rel-cd-nm="본인" data-bday="19970921" data-sex-cd="F">
						<div class="left">
							<div class="tit f_body1">유희진(본인)</div>
							<div class="flex_btn_wrap">
								<a style="background-color: #e0f55c;" class="border_btn"
									href="javascript:" role="button"
									onclick="payment.openFmlyPopup(this, 'fmly')">
									<!-- onclick="payment.openFmlyPopup(this, 'child')"> -->
								<span style="color: #000;">수강자 변경/추가</span>
								<!-- <span style="color: #000;">자녀회원 선택</span> -->
								</a>
							</div>
						</div>
						<div class="right">
							<ul class="txt_wrap">
								<li class="f_body3">
									<div class="txt_con">
										<div class="tit">강좌료</div>
										<div class="txt">
											<p>3,000원</p>
										</div>
									</div>
								</li>
								<li class="expected">
									<div class="txt_con">
										<div class="tit">결제예정 금액</div>
										<div class="txt">
											<p>3,000원</p>
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
            <p><span class="price">3,000</span><span class="f_body3">원</span></p>
          </div>
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

<!-- 가족회원 중 자녀가 있는지 여부 변수 -->
<!-- 수강자 변경/추가 팝업 -->
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
												<input type="checkbox" id="student0" name="" data-kor-nm="${mDto.name}" data-fmly-rel-cd="00" data-bday="${mDto.m_birth_dt}" data-fmly-rel-cd-nm="본인" data-sex-cd="F">
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

<!-- 자녀회원 선택 팝업 -->
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
                	<tr>
					                    <td>
					                      <div class="form_checkbox">
					                        <input type="checkbox" id="childStudent1" name="" data-kor-nm="ddddd" data-fmly-rel-cd="02" data-bday="20150101" data-fmly-rel-cd-nm="자녀" data-sex-cd="M">
					                        <label for="childStudent1"></label>
					                      </div>
					                    </td>
					                    <td>
					                      <p class="f_body2">ddddd</p>
					                    </td>
					                    <td>
					                      <p class="f_body2">자녀</p>
					                    </td>
					                    <td>
					                    <p class="f_body2">2015.01.01</p>
					                    </td>
					                    <td>
					                      <p class="f_body2">남성</p>
					                    </td>
					                </tr>
                				<tr>
					                    <td>
					                      <div class="form_checkbox">
					                        <input type="checkbox" id="childStudent2" name="" data-kor-nm="ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ" data-fmly-rel-cd="02" data-bday="20201023" data-fmly-rel-cd-nm="자녀" data-sex-cd="M">
					                        <label for="childStudent2"></label>
					                      </div>
					                    </td>
					                    <td>
					                      <p class="f_body2">ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ</p>
					                    </td>
					                    <td>
					                      <p class="f_body2">자녀</p>
					                    </td>
					                    <td>
					                    <p class="f_body2">2020.10.23</p>
					                    </td>
					                    <td>
					                      <p class="f_body2">남성</p>
					                    </td>
					                </tr>
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


<script type="text/javascript" src="/resources/common/js/member/addFamilyPop.js"></script>
<!-- <script type="text/javascript" src="/resources/common/js/mypage/mypageMember.js"></script> -->
<script type="text/javascript" src="/resources/common/js/payment/payment.js"></script>
