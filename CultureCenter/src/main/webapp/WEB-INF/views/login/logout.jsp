<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="cont_wrap notice_info_div log_out_bg">
	<div class="cont_inner">
		<div class="page_title_area white_ver">
			<div class="inner">
				<!-- 2022-11-23 구조 수정 -->
				<div class="top_area">
					<p class="tit_div">
						<span class="tit f_h1">로그인</span>
					</p>
				</div>
				<!-- // 2022-11-23 구조 수정 -->
			</div>
		</div>

		<div class="page_cont_area no_pb">
			<div class="inner">
				<p class="title f_h1">
					안전하게<br class="only_mobile"> 로그아웃 되었습니다.
				</p>
				<p class="txt f_body1">
					롯데백화점 문화센터를 이용해 주셔서 감사합니다.<br> 행복한 하루 되시기 바랍니다.<br>
				</p>
				<div class="flex_btn_wrap margin_large">
					<a class="b_color_btn white" href="/index.do"> <span>메인으로</span></a>
					<a class="b_color_btn" href="javascript:fnc.moveLoginPage()"> <span>재로그인</span></a>
				</div>
			</div>
		</div>
	</div>
</div>
