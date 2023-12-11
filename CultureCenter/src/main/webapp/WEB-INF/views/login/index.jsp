<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> --%>

<doctype html>
<html lang="ko">
    
<%--     <!-- Page Header -->
<tiles:insertAttribute name="head" /> --%>
    
<div class="cont_wrap">
	<div class="cont_inner no_pb">
		<div class="login_wrap">
			<div class="login_inner">
				<a href="javascript:" class="left_layout login_layout">
					<div class="bg_wrap">
						<img src="../common/images/bg-login.jpg" alt="" class="only_pc">
					</div>
					<div class="inner">
						<div class="txt_wrap">
							<p class="title f_h1">
								롯데백화점 문화센터에 <br> 처음 오셨나요?
							</p>
							<p class="sub_title">
								수강신청 관련 서비스를 이용하시려면 <br class="only_mobile"> L.POINT 통합회원에
								가입해주세요.
							</p>
							
								
								<a class="sign_up" href="mbrJoin.do"> <span>회원가입</span></a>
				<!-- 				<p href="javascript:" class="sign_up">
								<span onclick="javascript:members.callScreen('joinUrl')">회원가입</span>
							</p>  -->
						</div>
					</div>
				</a>






				<form action="/login" method="post">

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
								<img src="../../common/images/logo-lpoint.svg" alt="lpoint"
									class="l_point"> 통합 회원은 롯데 서비스를<br class="only_mobile">
								하나의 ID로 이용할 수 있습니다.
							</p>
						</div>
						<!-- // 2022-11-23 구조 수정 -->
						<div class="login_form">
							<div class="login_box">
								<!-- 2022-11-23 구조 수정-->
								<div class="inner">
									<div class="form_input login_id">
										<!-- 2022-11-23 클래스 추가-->
										<div class="input_btn_wrap">
											<button type="button" class="btn_delete" title="아이디 지우기"></button>
										</div>
										
									<!-- 	<input type="text" id="id" name="username"
											onkeyup="if(window.event.keyCode == 13) members.login();"
											placeholder="아이디 또는 이메일을 입력해주세요." title="아이디(이메일)" value="admin"> -->
											
											<input name="username"	class="text" id="id" value="admin" />
											
									</div>
									<div class="form_input login_pw">
									
										<!-- 2022-11-23 클래스 추가-->
									<!-- 	<input type="password" id="pw" name="password" 
											onkeyup="if(window.event.keyCode == 13) members.login();"
											placeholder="비밀번호를 입력해주세요." title="비밀번호" value=""> -->
											
											<input type="password"	name="password" class="text" id="pw" value="4321" />
											
										<div class="input_btn_wrap">
											<button type="button" class="btn_delete" title="비밀번호 지우기"></button>
										</div>
									</div>
									<p class="f_caption2 red_txt">Caps Lock이 켜져 있습니다.</p>
									<div class="flex_btn_wrap">
									
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									
										<button class="b_color_btn login_btn">로그인</button>
										<!-- <a class="b_color_btn login_btn" href="javascript:members.login()"> <span>로그인</span> 
										</a> -->
									</div>





									<div class="find_account">
										<a href="javascript:members.callScreen('fdIdUrl')"
											class="find_id f_body4">아이디 찾기</a> <a
											href="javascript:members.callScreen('fdPwdUrl')"
											class="find_pw f_body4">비밀번호 찾기</a>
									</div>
								</div>
								<!-- // 2022-11-23 구조 수정 -->
							</div>
						</div>
					</div>

				</form>




			</div>
		</div>
	</div>
</div>



</html>