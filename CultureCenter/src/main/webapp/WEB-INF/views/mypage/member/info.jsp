<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>	
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>


<div class="cont_wrap" id="topDiv">
	<div class="cont_inner no_pb">
		<div class="page_title_area">
			<div class="inner">
				<div class="top_area">
					<a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a> 
					<a href="javascript:" class="tit_div arrow" title="페이지 이동 팝업 열기">
						<p class="tit f_h1">회원정보변경 <span class="more_tit"></span></p>
					</a>
					<div class="tit_popup">
						<div class="pop_wrap">
							<div class="pop_cont">
								<div class="for_padding">
									<div class="scroll_area">
										<div class="branch">
    	<a class="active" href="/mypage/member/info.do"><p class="f_desc">회원정보변경</p></a>
       	<a class="" href="/mypage/atlct/list.do"><p class="f_desc">수강내역 조회</p></a>
       	<a class="" href="/mypage/waiting/list.do"><p class="f_desc">대기자 조회</p></a>
       	<a class="" href="/mypage/freebie/appList.do"><p class="f_desc">사은품 신청</p></a>
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

		<div class="page_cont_area" >
			<div class="inner">
				<div class="sub_inner_w change_info">
					<div class="sub_inner">
						<div class="sub_tit_area">
							<div class="left">
								<p class="f_h2">회원정보</p>
							</div>
							<div class="right"></div>
						</div>
						<div class="info_txt">
							<div class="left">
								<ul class="txt_wrap">
									<li class="dl f_body2">
										<p class="dt">이름</p>
										<p class="dd f_body1">${mDto.name}</p>
									</li>
									<li class="dl f_body2">
										<p class="dt">생년월일</p>
										<p class="dd f_body1">${mDto.m_birth_dt}</p>
									</li>
									<li class="dl f_body2">
										<p class="dt">휴대전화</p>
										<p class="dd f_body1">${mDto.phone}</p>
									</li>
								</ul>
							</div>
							<div class="right">
								<ul class="txt_wrap">
									<li class="dl f_body2">
										<p class="dt">아이디</p>
										<p class="dd f_body1">${mDto.id}</p>
									</li>
									<li class="dl f_body2">
										<p class="dt">이메일</p>
										<p class="dd f_body1">${mDto.email}</p>
									</li>
									<li class="dl f_body2">
										<p class="dt">주소</p>
										 <p class="dd f_body1">${mDto.addr}</p>
									</li>
									<li class="dl f_body2">
										<p class="dt">백화점 마케팅 수신동의</p>
										<p class="dd f_body1">
											SMS : 수신거부<br> 
											이메일 : 수신거부</p>
									</li>
								</ul>
							</div>
						</div>
						<div class="other_info">
							<p class="f_caption2">회원정보 및 비밀번호 변경은 L.Point에서 수정 가능합니다.</p>
							<div class="flex_btn_wrap">
								<a class="border_btn" onclick="openNewWindow('/mypage/member/upPw.do')"><span>비밀번호 변경</span></a> 
								<a class="border_btn" onclick="openNewWindow('/mypage/member/upMember.do')"><span>회원정보 변경</span></a>
							</div>
						</div>
					</div>
					<div class="sub_inner">
						<div class="sub_tit_area" style="flex-direction:column; align-items:start;">
							<div class="right">
								<p class="f_h2 red_txt">
									[선택] 마케팅 활용 및 광고성 정보 수신 동의
								</p>
							</div>
						</div>
						<div class="info_txt">
							<p class="f_body2">백화점 마케팅 수신동의와 별개로, 문화센터 강좌수강 및 학습활동과 관련된 정보 및 소식을 받아보실 수 있습니다.<br><br>
											1. 수집 목적 : 롯데문화센터 접수 및 강좌 안내, 강좌 추천<br>
											2. 수집 항목 : 성명, 휴대전화번호, 관심지점<br>
											3. 보유 기간 : 회원 탈회시 까지<br>
											4. 거부권/불이익 : 활용 동의를 거부할 수 있으나, 강좌 추천 및 안내를 받을 수 없음<br>
											※ 서비스 주요 정책 및 공지사항 안내 등은 수신동의 여부와 상관없이 발송됩니다.<br>
							</p>
						</div>
						<div class="other_info tight_mg">
							<div class="flex_box">
								<div class="form_checkbox">
									<div>
										<input type="checkbox" id="checkMKTG"  > 								
										<label for="checkMKTG">[선택] 문화센터 마케팅 활용 동의</label>
									</div>
									<div>
										<input type="checkbox" id="checkSMS"  > 
										<label for="checkSMS">[선택] 문화센터 광고성 정보 수신 동의(SMS)</label>									
									</div>									
								</div>
							</div>
							<div class="flex_btn_wrap">
								<a class="b_color_btn gray" href="javascript:mypageMember.saveSmsYn()" role="button"> <span>저장</span>
								</a>
							</div>
						</div>
					</div>
					
					<div class="sub_inner">
						<div class="sub_tit_area">
							<div class="left">
								<p class="f_h2"><span class="red_txt">[선택]</span> 차량정보 등록</p>
							</div>
							<div class="right"></div>
						</div>
						<div class="info_txt">
							<div class="form_input">
								<input type="text" id="carNoVal" oninput="$(this).val($(this).val().replace(/[^(가-힣ㄱ-ㅎㅏ-ㅣㆍᆢ\w)]/gi, ''))" maxlength="10" placeholder="차량번호를 입력해주세요." value="${mDto.car_no}">
								<div class="input_btn_wrap">
									<button type="button" class="btn_delete" title="차량번호 지우기"></button>
								</div>
							</div>
							<div class="flex_box">
								<div class="form_checkbox">
									<input type="checkbox" id="checkCar">
									<label for="checkCar">차량번호 수집동의</label>
								</div>
							</div>
							<div class="terms_for_msg">
								<div class="msg_div f_body2">
									<p class="title">차량등록서비스 이용을 위해 아래와 같이 알려드립니다.</p>
									<div class="txt">
										<p>1. 수집목적 : 백화점 주차요금 정산처리</p>
										<p>2. 수집항목 : 차량번호</p>
										<p>3. 보유기간 : <span>등록해제 시, 회원탈퇴 시 파기</span></p>
										<p>4. 정보수집 거부시 주차정산 서비스 불가</p>
										<p>5. 차량번호의 모든자리를 띄어쓰기 없이 입력</p>
										<p>※ 예시) [서울12가3456] or [12가3456]</p>
										<p>6. 차량번호 입력 오류시 자동주차정산 불가</p>
										<p>7. 자동주차정산 제외점포 : 잠실점, 관악점, 포항점, 상인점, 전주점, 건대스타시티점, 마산점, 군산점</p>
									</div>
								</div>
							</div>
						</div>
						<div class="other_info btn_right">
							<div class="flex_btn_wrap">
								<a class="b_color_btn gray" href="javascript:mypageMember.saveCarYn()" role="button"> <span>저장</span>
								</a>
							</div>
						</div>
					</div>
					
    <script>
        // 페이지 로드 시 실행되는 스크립트
        document.addEventListener("DOMContentLoaded", function () {
            // ${mDto.car_no}의 값이 있을 때만 체크박스에 checked 속성 추가
            const carNoValue = "${mDto.car_no}";
            const checkbox = document.getElementById("checkCar");

            if (carNoValue) {
                checkbox.checked = true;
            }
        });
    </script>
					<div class="sub_inner">
						<div class="sub_tit_area">
							<div class="left">
								<p class="f_h2">동반 수강자 정보</p>
							</div>
							<div class="right"></div>
						</div>
						<div class="info_txt" id="familyList">
							<div class="info_list">
						    <c:forEach var="child" items="${mDto.childrenList}">
						        <div class="writer_info">
						            <p class="item_name f_body1">${child.children_nm}</p>
						            <a href="javascript:mypageMember.deleteFamily(${child.children_sq})" class="comment_remove f_caption1">삭제</a>
						        </div>
						        <div class="type_div">
						            <p class="type">자녀</p>
						            <p class="type">${child.child_birth_dt}</p>
						            <p class="type">${child.realGender}</p>
						        </div>
						    </c:forEach>
						</div>
							
						</div>
						<div class="other_info btn_right">
							<div class="flex_btn_wrap">
								<a class="b_color_btn gray" href="javascript:mypageMember.showPopup('#addFamilyPop')" role="button"> <span>자녀회원 추가</span>
								</a>
							</div>
						</div>
					</div>
					
					
				</div>
				<div class="func_btn">
					<a href="javascript:mypageMember.drawMember();" class="f_btn">롯데백화점 문화센터 탈퇴하기</a> 
					<a href="javascript:fnc.moveLogout()" class="f_btn">로그아웃</a>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="layer_popup" id="saveSmsGuidePop" style="display:none">
	<div class="pop_wrap">
		<div class="pop_head">
			<p class="title">변경 내용</p>
		</div>
		<div class="pop_cont">
			<div class="for_padding">
				<div class="scroll_area">
					<div class="txt_con">
						<p class="txt light_color">롯데문화센터 마케팅 수신 동의</p>
						<div class="txt2 light_color">
							<p id=updTxtMktg></p>
						</div>
						<div>
							<p class="f_caption2" id="updDtMktg"></p>
						</div>
						<br>
						<div class="txt2 light_color">
							<p id="updTxt"></p>
						</div>
						<div>
							<p class="f_caption2" id="updDt"></p>
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
<div class="layer_popup" id="saveCarGuidePop" style="display: none">
	<div class="pop_wrap">
		<div class="pop_head">
			<p class="title">변경 내용</p>
		</div>
		<div class="pop_cont">
			<div class="for_padding">
				<div class="scroll_area">
					<div class="txt_con">
						<p class="txt light_color">롯데백화점 차량번호 등록</p>
						<div class="txt2 light_color">
							<p id="updTxt"></p>
						</div>
						<p class="f_caption2" id="updDt"></p>
					</div>
				</div>
			</div>
		</div>
		<a class="btn_close" href="javascript:" title="닫기"> <span
			class="blind"></span>
		</a>
	</div>
</div>


<div class="layer_popup add_children" id="addFamilyPop" style="display: none">
	<div class="pop_wrap w800 full">
		<div class="pop_head">
			<p class="title">자녀회원 추가하기</p>
		</div>
		<div class="pop_cont">
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
											<button type="button" class="btn_delete" title="이름 지우기"></button>
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
										<input type="hidden" name="fmlyRelCd" value="02" readonly>
										<input type="text" name="fmlyRelCdNm" value="자녀" readonly>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="th">
									<p class="tit f_body1">생년월일</p>
								</div>
								<div class="td">
									<div class="form_input">
										<input type="text" name="bday" inputmode="numeric" maxlength="10" oninput="$(this).val(fnc.setDateFormat($(this).val()))" placeholder="예) 20201023"/>
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
											<input type="radio" id="gender1" name="sexCd" value="M" checked>
											<label for="gender1">남성</label>
										</div>
										<div class="form_radio">
											<input type="radio" id="gender2" name="sexCd" value="F">
											<label for="gender2">여성</label>
										</div>
									</div>
									<div class="form_checkbox">
										<input type="checkbox" id="chkAgrYn" name="chkAgrYn" > 
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
								등록 가능 관계 : 만 14세 미만 자녀<br />(14세 이상 자녀는 회원가입을 통해 수강 신청이 가능합니다.)
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

<script>
    function openNewWindow(url) {
        var width = 450;
        var height = 470;
        var left = (screen.width / 2) - (width / 2) + window.screenLeft;
        var top = (screen.height / 2) - (height / 2) + window.screenTop;

        window.open(url, '_blank', 'width=' + width + ', height=' + height + ', resizable=yes, top=' + top + ', left=' + left);
    }
</script>
<script>
    var contextPath = '<c:url value="/"/>';
</script>
<script type="text/javascript" src="/resources/common/js/mypage/mypageMember.js"></script>
<script type="text/javascript" src="/resources/common/js/member/addFamilyPop.js"></script>