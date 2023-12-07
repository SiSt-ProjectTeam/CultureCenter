var mypageCoupon = (function(){

	"use strict";
	
	var fn_coupon_list = function(useStatCd) {
		fnc.paramAjax(function(data) {
			$("#couponList").html(data);
			$("#totCnt").text($("#couponList").find(".coupon").length);
		}, "/mypage/coupon/list.ajax", { useStatCd : useStatCd } ,"html", false, false);
	}
	
	var fn_show_popup = function(popupId, obj)
	{
		var detailCont = $(obj).closest(".content").find("."+popupId).html()
		$("#"+popupId).find("#detailCont").html(detailCont)
		
		commonScript.openPopupFn("#"+popupId, $(this));
	}
	
	var fn_change_stat = function(obj)
	{
		fn_coupon_list($(obj).data("useStatCd"));
		couponChange() // 퍼블 : 쿠폰화면 변경이벤트
	}
	
	// 4자리 하이픈 삽입
	var fn_check_coupon_number = function(obj){
		var rplcStr = $(obj).val().replace(/[^a-zA-Z0-9]/g, "").replace(/\B(?=(\d{4})+(?!\d))/g, '-');
		$(obj).val(rplcStr);
	}
	
	var fn_save_random = function(){
		fnc.paramAjax(function(data) {
			if(data.rtnCode == "00")
			{
				alert("쿠폰이 발급되었습니다.");
				location.href = location.href;
			}
			else
			{
				alert(data.rtnMsg)
			}
		}, "/mypage/coupon/issueRandom.ajax", { cpnNo : $("#rndmCpnNo").val().replace(/-/g, "") } ,"json", false, false);
	}

	$(document).ready(function(){
		fn_coupon_list($(".useStatCd").filter(".on").data("useStatCd"));
	});

	return {
		couponList	: fn_coupon_list,
		showPopup	: fn_show_popup,
		changeCd	: fn_change_stat,
		checkCpnNum : fn_check_coupon_number,
		saveRandom	: fn_save_random
	}
}());