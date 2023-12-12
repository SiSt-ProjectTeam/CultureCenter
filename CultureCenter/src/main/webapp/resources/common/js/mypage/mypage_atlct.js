var mypage_atlct = (function(){
	
	"use strict";
	
	var searchMore = null;
	var submitFlag = false;
	
	// 수강내역 리스트
	var fn_list = function(type, pageIndex){
		$('#frm_search').find('#q').val($('div.form_search_w').find('input[name=q]').val());
		searchMore.pageIndex = 1;
		searchMore.search();
	}
	
	// 학기로 검색
	var fn_change_smster = function(obj, type, lectSmsterCd){
		if(lectSmsterCd != "" && $('#frm_search').find('#yy').val() == ""){
			alert("연도를 선택해주세요.");
		}else{
			$('#frm_search').find('#lectSmsterCd').val(lectSmsterCd);
			
			var txtVal = $(obj).text();
	        $(obj).addClass("on").siblings().removeClass("on");
	        $(obj).parents(".btn_wrap").find(".order_txt").html(txtVal);
	        
			fn_list();
		}
	}
	
	// 년도로 검색
	var fn_change_yy = function(obj, type, yy){
		$('#frm_search').find('#yy').val(yy);
		
		if(yy == ""){
			// 전체 년도면 학기 전체학기로 변경
			$('#frm_search').find('#lectSmsterCd').val("");
			var allLectSmsterCdLink = $(obj).closest('.link_select').find('.btn_wrap:nth-child(2)').find('.scroll_wrap').find('.txt:nth-child(1)');
			allLectSmsterCdLink.addClass("on").siblings().removeClass("on");
			$(obj).closest('.link_select').find('.btn_wrap:nth-child(2)').find(".order_txt").html(allLectSmsterCdLink.text());
		}
		var txtVal = $(obj).text();
        $(obj).addClass("on").siblings().removeClass("on");
        $(obj).parents(".btn_wrap").find(".order_txt").html(txtVal);
		
        fn_list();
	}
	
	// 주문번호 입력 필드에서 엔터 입력 시
	var fn_keydown_atlctRsvNo = function(obj, e, type){
		var key = event.key || event.keyCode;
        if (key === 'Enter' || key === 13) {
        	e.preventDefault();
        	fn_list();
        }
	}
	
	// 결제취소 팝업
	var fn_open_cncl_popup = function(allRfndYn){
		var arrActlAtlctNpleSeqno = [];
		var rfndOptnUseYn = true;
		var fgftWdwYn = true;
		var partRfndPopupYn = false;
		
		$('input[name=rfndChk]').each(function(){
			if(allRfndYn){
				if(($(this).closest('div.agree_chk').data("fgftAppNo") != "" && $(this).closest('div.agree_chk').data("recpYn") != "Y") && ($(this).closest('div.agree_chk').data("recpYn") == "Y" && $(this).closest('div.agree_chk').data("wdwYn") != "Y")){
					fgftWdwYn = false;
				}
				arrActlAtlctNpleSeqno.push($(this).val());
			}else{
				if($(this).is(":checked")){
					if($(this).closest('div.agree_chk').data('rfndOptnUseYn') == "Y" && $(this).closest('div.agree_chk').data('freeRfndYn') == "N"){
						// 환불옵션 3일전 체크 된 강좌 중 부분환불 조건인 경우
						rfndOptnUseYn = false;
					}
					if($(this).closest('div.agree_chk').data('freeRfndYn') == "N"){
						// 부분환불인 경우 다른 팝업을 띄워야하기때문에
						partRfndPopupYn = true;
					}
					
					if(($(this).closest('div.agree_chk').data("fgftAppNo") != "" && ($(this).closest('div.agree_chk').data("recpYn") != "Y") || ($(this).closest('div.agree_chk').data("recpYn") == "Y" && $(this).closest('div.agree_chk').data("wdwYn") != "Y"))){
						fgftWdwYn = false;
					}
					arrActlAtlctNpleSeqno.push($(this).val());
				}
			}
		});
		
		if(!fgftWdwYn){
			alert("사은품 신청한 강좌가 있습니다. 마이페이지>사은품신청내역에서 사은품을 먼저 취소해주세요. (단, 이미 수령한 사은품은 데스크에 반납 후 취소 가능)");
			return;
		}
		
		if(arrActlAtlctNpleSeqno.length > 0){
			if(!rfndOptnUseYn){
				if(confirm("[롯데문화센터 환불규정]에 따라 아래에 해당하는 강좌 있습니다.\n계속하시겠습니까?\n- 강의시작 3일전까지 : 잔여회차 전액 환불\n- 그 이후 : 해당 회차에 대한 환불차감액(환불규정 의거) 발생")){
					rfndOptnUseYn = true;
				}
			}
			if(rfndOptnUseYn){
				$('#frm_update input#actlAtlctNpleSeqno').val(arrActlAtlctNpleSeqno.join(','));
				if(partRfndPopupYn){
					// 환불 팝업
					fnc.frmAjax(fn_part_cncl_popup_callback, "/mypage/atlct/getPartRfndData.ajax", $('#frm_update'), "html");
				}else{
					// 결제취소 팝업
					$('#rfnd30 div.open_area a.btn_open span').text("선택");
					$('#rfnd30 div.form_select_div').removeClass('change');
					$('#rfnd30 div.form_select_div').removeClass('on');
					$('#rfnd30 div.form_select_div div.list_wrap').hide();
					$('#rfnd30 div.form_select_div div.scroll_wrap a.btn_link').removeClass('on');
					
					commonScript.openPopupFn("#rfnd30", $(this));
//					$('#rfnd30').fadeIn();
					popupResize();
				}
			}
		}else{
			alert("강좌를 선택하세요.");
			return;
		}
	}
	
	// 부분환불 팝업
	var fn_part_cncl_popup_callback = function(html){
		if($('#rfnd50').length > 0){
			$('#rfnd50').remove();
		}
		
		$('#wrap').append(html);
		commonScript.commonFn();
		commonScript.formChkFn();
//		commonScript.popupFn();
		commonScript.openPopupFn("#rfnd50", $(this));
//		$('#rfnd50').fadeIn();
		popupResize();
	}
	
	// 결제취소 팝업 닫기
	var fn_close_cncl_popup = function(){
		$("#rfnd30").find(".btn_close").click();
	}
	
	// 부분환불 팝업 닫기
	var fn_close_part_cncl_popup = function(){
		$("#rfnd50").find(".btn_close").click();
	}
	
	// 결제취소
	var fn_update_cncl = function(obj, rfndStatCd, amtFee){
		if(Number(amtFee) == 0){
			alert("환불이 불가한 강좌입니다. 관리자에게 문의하세요.");
			return;
		}
		
		if($(obj).closest('div.layer_popup').find('div.list_wrap.rfndRsn div.scroll_wrap a.btn_link.on').length == 0){
			alert("취소(환불) 사유를 선택하세요.");
			submitFlag = false;
			return;
		}else{
			$('#frm_update').find('input[name=rfndRsnCd]').val($(obj).closest('div.layer_popup').find('div.list_wrap.rfndRsn div.scroll_wrap a.btn_link.on').data('rfndRsnCd'));
		}
		
		if(confirm("선택하신 강좌의 온라인 결제를 취소하시겠습니까?")){
			if(!submitFlag){
				submitFlag = true;
				$('.dimd').css('z-index', '300');
				$('.dimd').show();
				
				// 결제취소
				// 2023.04.12 결제취소, 환불 선 상태 업데이트
				if(rfndStatCd == "30"){
					$('#frm_update').find('input[name=rfndClCd]').val("10");
					$('#frm_update').find('input[name=rfndStatCd]').val("30");
				}else if(rfndStatCd == "50"){
					$('#frm_update').find('input[name=rfndClCd]').val("20");
					$('#frm_update').find('input[name=rfndStatCd]').val("50");
				}
				$('#frm_update').attr('method', 'POST');
				$('#frm_update').attr('action', "/payment/paymentCancel.do");
				$('#frm_update').submit();
			}
		}else{
			submitFlag = false;
		}
	}
	
	
	// 내역보기
	var fn_move_dtl = function(atlctRsvNo){
		$('#frm_search').find('input[name=atlctRsvNo]').val(atlctRsvNo);
		$('#frm_search').attr('action', '/mypage/atlct/view.do');
		$('#frm_search').attr('method', 'GET');
		$('#frm_search').submit();
	}
	
	var init = function() {
		var initObj = {
				form : $("#frm_search")
				, container : $("#listContainer")
				, moreBtn : $("#moreBtn")
				, url : "/mypage/atlct/list.ajax"
				, pageIndex : $("#frm_search #pageIndex").val()
				, listCnt : $("#frm_search #listCnt").val()
				, callbackFunc : function() {
					$("#totCnt").text(searchMore.totCnt  + "개");
					moreStudent()
				}
		}
		searchMore = new fnc.SearchMore(initObj);
		searchMore.search();
	}
	
	$(document).ready(function() {
		if($('div.cont_wrap').data('pageType') == 'list'){
			init();
		}
	});
	
	 return {
		 list : fn_list
		 , changeSmster : fn_change_smster
		 , changeYy : fn_change_yy
		 , keydownAtlctRsvNo : fn_keydown_atlctRsvNo
		 , openCnclPopup : fn_open_cncl_popup
		 , closeCnclPopup : fn_close_cncl_popup
		 , closePartCnclPopup : fn_close_part_cncl_popup
		 , updateCncl : fn_update_cncl
		 , moveDtl : fn_move_dtl
	 }
}());