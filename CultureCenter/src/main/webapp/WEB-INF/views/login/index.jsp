<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>		
${not empty alertScript ? alertScript : ''}
<div class="cont_wrap">
	<div class="cont_inner no_pb">
		<div class="login_wrap">
			<div class="login_inner">
				<a href="javascript:" class="left_layout login_layout">
					<div class="bg_wrap">
						<img src="/resources/common/images/bg-login.jpg" alt="" class="only_pc">
					</div>
					<div class="inner">
						<div class="txt_wrap">
							<p class="title f_h1">
								롯데백화점 문화센터에 <br> 처음 오셨나요?
							</p>
							<p class="sub_title">
								수강신청 관련 서비스를 이용하시려면 <br class="only_mobile"> 회원가입해주세요.
							</p>
							<p href="javascript:" class="sign_up">
								<span onclick="javascript:window.location.href = '/member/join.do'">회원가입</span>
							</p>
						</div>
					</div>
				</a>
				<div class="right_layout login_layout">
					<!-- 2022-11-23 구조 수정 -->
					<div class="page_title_area">
						<div class="inner">
							<div class="top_area">
								<a href="/" class="page_prev_btn login_prev_btn" title="뒤로가기"></a> 
								<p class="tit_div">
									<span class="tit f_h1" id="login">로그인</span>
								</p>
							</div>
						</div>
						<p class="sub_title">
							<img src="/resources/common/images/logo-lpoint.svg" alt="lpoint" class="l_point"> 로그인 하시면 수강신청 관련 서비스를 이용할 수 있습니다.
						</p>
					</div>
					<div class="login_form">
						<div class="login_box">
							<div class="inner">
								<form action="/login" method="post" id="frmLogin">
									<input type="hidden" id="csrf_token_input" name="${ _csrf.parameterName }" value="${ _csrf.token }">
									<div class="form_input login_id">
										<div class="input_btn_wrap">
											<button type="button" class="btn_delete" title="아이디 지우기"></button>
										</div>
										<input type="text" id="username" name="username" onkeyup="if(window.event.keyCode == 13) members.login();" placeholder="아이디 또는 이메일을 입력해주세요." title="아이디(이메일)" value="">
									</div>
									<div class="form_input login_pw">
										<input type="password" id="password" name="password" onkeyup="if(window.event.keyCode == 13) members.login();" placeholder="비밀번호를 입력해주세요." title="비밀번호" value="">
										<div class="input_btn_wrap">
											<button type="button" class="btn_delete" title="비밀번호 지우기"></button>
										</div>
									</div>
									<p class="f_caption2 red_txt">Caps Lock이 켜져 있습니다.</p>
									<div class="flex_btn_wrap">
										<a class="b_color_btn login_btn" href="javascript:members.login()"> <span>로그인</span>
										</a>
									</div>
									<div class="find_account">
										<a onclick="openNewWindow('/login/findId.do')" class="find_id f_body4">아이디 찾기</a>
										<a onclick="openNewWindow('/login/findPw.do')" class="find_pw f_body4">비밀번호 찾기</a>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<script>
    function openNewWindow(url) {
        var width = 650;
        var height = 470;
        var left = (screen.width / 2) - (width / 2) + window.screenLeft;
        var top = (screen.height / 2) - (height / 2) + window.screenTop;

        window.open(url, '_blank', 'width=' + width + ', height=' + height + ', resizable=yes, top=' + top + ', left=' + left);
    }
</script>