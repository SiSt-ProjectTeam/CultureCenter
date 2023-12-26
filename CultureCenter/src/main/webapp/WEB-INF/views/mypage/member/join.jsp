<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="cont_wrap" data-page-type="list">
	<div class="cont_inner no_pb">
		<div class="page_title_area">
			<div class="inner">
				<div class="top_area">
					<a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a> <a
						href="javascript:" class="tit_div arrow" title="페이지 이동 팝업 열기">
						<p class="tit f_h1">
							회원가입 <span class="more_tit"></span>
						</p>
					</a>
				</div>
			</div>
			<div class="m_pop_dimd"></div>
		</div>

		<div class="page_cont_area">
			<form action="/member/join.do" method="post" class="joinForm" id="joinForm">
				<input type="hidden" id="idConfirmed" value="N">
				<input type="hidden" id="pwConfirmed" value="N">
				<input type="hidden" id="phoneConfirmed" value="N">
				
				<div class="login_form">
					<div class="login_box">
						<div class="inner sub_inner_w" style="display: flex; flex-direction: column; align-items: center;">
							<strong>아이디</strong>
							<div class="form_input">
								<input type="text" id="id" name="id" placeholder="아이디를 입력해주세요." title="아이디" onblur="members.idChk()">
								<span id="idchkMsg" style="margin: 0 auto; display: table;"></span>
							</div>
							
							<br><strong>비밀번호</strong>
							<div class="form_input">									
								<input type="password" id="pw" name="pw" placeholder="비밀번호를 입력해주세요." title="비밀번호">
							</div>							
							<br><strong>비밀번호 확인</strong>
							<div class="form_input">
				                <input type="password" id="pw2" placeholder="비밀번호를 다시 입력해주세요." title="비밀번호 확인"onblur="members.pwChk()">
								<span id="pwChkMsg" style="margin: 0 auto; display: table;"></span>
				            </div>
				            
							<br><strong>이름</strong>
				            <div class="form_input"> <!-- 유효성 검사시 id = name 으로하면 받아오질 못해서 name1변경 -->
				                <input type="text" id="name1" name="name" placeholder="이름을 입력해주세요." title="이름">
				            </div>
				            
							<br><strong>이메일</strong>
				            <div class="form_input">
				                <input type="email" id="email" name="email" placeholder="이메일을 입력해주세요." title="이메일">
				            </div>				            
				            
							<br><strong>전화번호</strong>
				            <div class="form_input">
				                <input type="tel" id="phone" name="phone"  placeholder="전화번호 예) 01012345678" title="전화번호">
				                <input type="button" onclick="javascript:members.sendSms();" id="sendSMS" value="인증번호 발송" style="width: 100%; padding: 5px; background-color: #DDD;">
				            </div>
				            <div class="form_input" id="phone_verif" style="display: none;">
								<input type="text" id="verif_code" placeholder="인증번호를 입력해주세요">
								<input type="button" onclick="javascript:members.phoneChk();"  id="phoneChk" value="확인" style="width: 100%; padding: 5px; background-color: #DDD;">
				            </div>
				            <span id="phoneChkMsg" style="margin: 0 auto; display: table;"></span>
				            
							<br><strong>생년월일</strong>
							<div class="form_input">
								<input type="date" id="birth_dt" class="date" name="birth_dt">
							</div>		
							<script>
								const today = new Date();
							    const maxDate = new Date(today.getFullYear() - 14, today.getMonth(), today.getDate()).toISOString().split('T')[0];
								document.getElementById('birth_dt').max = maxDate;
							</script>
							
							<br><strong>주소</strong>
							<div class="form_input">
								<input type="button" onclick="members.execDaumPostcode()" value="우편번호 찾기" style="width: 100%; padding: 5px; background-color: #DDD;">
								<span id="guide" style="color: #999; display: none"></span> 
								<input type="text" id="postcode" name="postcode" placeholder="우편번호">
								<input type="text" id="roadAddress" name="addr" placeholder="도로명주소" size="35">
								<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소" size="60"> 									
								<input type="hidden" id="jibunAddress" name="jibunAddress" placeholder="지번주소" size="60"> 
								<input type="hidden" id="extraAddress" name="extraAddress" placeholder="참고항목" size="60">
								<input type="hidden" id="engAddress" name="engAddress" placeholder="영문주소" size="60">
							</div>
							
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<div class="flex_btn_wrap">
								<button class="b_color_btn login_btn" type="submit" onclick="members.joinSubmit()"><span>회원가입</span></button>
								<button class="b_color_btn login_btn" type="reset"><span>새로입력</span></button>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- 도로명 주소 -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>