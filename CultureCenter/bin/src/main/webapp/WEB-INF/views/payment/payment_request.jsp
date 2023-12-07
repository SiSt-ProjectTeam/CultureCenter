<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="/common/js/jquery-1.12.4.min.js"></script>
<!-- 아래 js는 PC 결제창 전용 js입니다.(모바일 결제창 사용시 필요 없음) -->
<script src="https://web.nicepay.co.kr/v3/webstd/js/nicepay-3.0.js" type="text/javascript"></script>
<script>
//[PC 결제창 전용]결제 최종 요청시 실행됩니다. <<'nicepaySubmit()' 이름 수정 불가능>>
function nicepaySubmit(){
	document.payForm.submit();
}

//[PC 결제창 전용]결제창 종료 함수 <<'nicepayClose()' 이름 수정 불가능>>
function nicepayClose(){
	//alert("결제가 취소 되었습니다");
	payment.nicepayClose('2340002Qf6mw');
}

//pc, mobile 구분(가이드를 위한 샘플 함수입니다.)
function checkPlatform(ua) {
	if(ua === undefined) {
		ua = window.navigator.userAgent;
	}
	
	ua = ua.toLowerCase();
	var platform = {};
	var matched = {};
	var userPlatform = "pc";
	var platform_match = /(ipad)/.exec(ua) || /(ipod)/.exec(ua) 
		|| /(windows phone)/.exec(ua) || /(iphone)/.exec(ua) 
		|| /(kindle)/.exec(ua) || /(silk)/.exec(ua) || /(android)/.exec(ua) 
		|| /(win)/.exec(ua) || /(mac)/.exec(ua) || /(linux)/.exec(ua)
		|| /(cros)/.exec(ua) || /(playbook)/.exec(ua)
		|| /(bb)/.exec(ua) || /(blackberry)/.exec(ua)
		|| [];
	
	matched.platform = platform_match[0] || "";
	
	if(matched.platform) {
		platform[matched.platform] = true;
	}
	
	if(platform.android || platform.bb || platform.blackberry
			|| platform.ipad || platform.iphone 
			|| platform.ipod || platform.kindle 
			|| platform.playbook || platform.silk
			|| platform["windows phone"]) {
		userPlatform = "mobile";
	}
	
	if(platform.cros || platform.mac || platform.linux || platform.win) {
		userPlatform = "pc";
	}
	
	return userPlatform;
}
</script>

<form id="payForm" name="payForm" method="post" action="/payment/payment_result.do">
	<input type="hidden" name="csrfPreventionSalt" value="" />
	<input type="hidden" name="PayMethod" value="CARD">
	<input type="hidden" name="GoodsName" value="[2/28] 베네피트와 함께하는 겨울철 모공케어 동안메이크업">
	<input type="hidden" name="Amt" value="6000">
	<input type="hidden" name="MID" value="lottecc02m">
	<input type="hidden" name="Moid" value="2340002Qf6mw">
	<input type="hidden" name="BuyerName" value="유희진">
	<input type="hidden" name="ReturnURL" value="https://culture.lotteshopping.com/payment/payment_result.do">
	
	<!-- 옵션 --> 
	<input type="hidden" name="GoodsCl" value="1"/>						<!-- 상품구분(실물(1),컨텐츠(0)) -->
	<input type="hidden" name="TransType" value="0"/>					<!-- 일반(0)/에스크로(1) --> 
	<input type="hidden" name="CharSet" value="utf-8"/>					<!-- 응답 파라미터 인코딩 방식 -->
	<input type="hidden" name="ReqReserved" value="certReqnTm=20231129231842,pgMctKeyVal=8GgnBGxlF2OGrZ2QX3ERXRRQCgKmUiKJ/hyID6nzjhB38JkyFxnG6X4FCPVNijcanWJD8v2Sl3upqBzXTfcuYw==,atlctRsvNo=2340002Qf6mw,lpntUseAmt=0,strPasswd=,strCardNo=,brchCd=,strCoprMemstrNo=,mbrNo=230584417,mbrGrdeCd=,mvgBlstrCd=,mbrId=heejin740@naver.com"/>					<!-- 상점 예약필드 -->
				
	<!-- 변경 불가능 -->
	<input type="hidden" name="EdiDate" value="20231129231842"/>			<!-- 전문 생성일시 -->
	<input type="hidden" name="SignData" value="b9b02d458c8881609f1d661d0b3188f508bb0516cede5317a9c2da68b2ae6e90"/>	<!-- 해쉬값 -->
	<input type="hidden" name="ConnWithIframe" value="Y"/>
</form>

<script type="text/javascript" src="/common/js/fnc.js"></script>
<script type="text/javascript" src="/common/js/payment/payment.js"></script>
<script>
	if(checkPlatform(window.navigator.userAgent) == "mobile"){//모바일 결제창 진입
		document.payForm.action = "https://web.nicepay.co.kr/v3/v3Payment.jsp";
		document.payForm.acceptCharset="euc-kr";
		document.payForm.submit();
	}else{//PC 결제창 진입
		goPay(document.payForm);
		//$('#pgPopup', parent.document).fadeOut();
	}
	
	//console.log($('#pgPopup', parent.document));
	//console.log($('#pgPopup', parent.document).find('.btn_close'));
	//$('#pgPopup', parent.document).fadeOut();
</script>