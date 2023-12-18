console.log("mypage_waiting.js loaded");

var mypage_waiting = (function(){
	
	"use strict";
	
	var searchMore = null;
	var callCnt = 0;
	
	// 지점 변경
	var fn_changebrchNm = function(brchNm){
		$('#frm_search').find('#brchNm').val(brchNm);
		searchMore.pageIndex = 1;
		searchMore.search();
		moreStudent();	
	}
	
	// 신청하기
	var fn_move_payment = function(obj){
		var arrLect = [];
		var lectObj = {};
		lectObj.atlctRsvNo = $(obj).closest('div.cour_his_list').data('atlctRsvNo');
		lectObj.brchCd = $(obj).closest('div.cour_his_list').find('div.cour_top_area').data('brchCd');
		lectObj.yy = $(obj).closest('div.cour_his_list').find('div.cour_top_area').data('yy');
		lectObj.lectSmsterCd = $(obj).closest('div.cour_his_list').find('div.cour_top_area').data('lectSmsterCd');
		lectObj.lectCd = $(obj).closest('div.cour_his_list').find('div.cour_top_area').data('lectCd');
		lectObj.optnSeqno = $(obj).closest('div.cour_his_list').find('div.cour_top_area').data('optnSeqno');
		lectObj.lectTpCd = $(obj).closest('div.cour_his_list').find('div.cour_top_area').data('lectTpCd');
		lectObj.pblPmprcustbrchNm = $(obj).closest('div.cour_his_list').find('div.cour_top_area').data('pblPmprcustbrchNm');
		lectObj.pblPmprcustLectCd = $(obj).closest('div.cour_his_list').find('div.cour_top_area').data('pblPmprcustLectCd');
		lectObj.pblPmprcustbrchNm = $(obj).closest('div.cour_his_list').find('div.cour_top_area').data('pblPmprcustbrchNm');
		
		var arrActlAtlctNple = [];
		$(obj).closest('div.cour_his_list').find('div.cour_detail_w').find('div.cour_detail').each(function(){
			var actlAtlctNpleObj = {};
			actlAtlctNpleObj.actlAtlctNpleSeqno = $(this).data('actlAtlctNpleSeqno');
			actlAtlctNpleObj.actlAtlctNpleNm = $(this).data('actlAtlctNpleNm');
			actlAtlctNpleObj.fmlyRelCd = $(this).data('fmlyRelCd');
			actlAtlctNpleObj.fmlyRelCdNm = $(this).data('fmlyRelCdNm');
			actlAtlctNpleObj.bday = $(this).data('bday');
			actlAtlctNpleObj.sexCd = $(this).data('sexCd');
			
			arrActlAtlctNple.push(actlAtlctNpleObj);
		});
		
		lectObj.arrActlAtlctNple = arrActlAtlctNple;
		arrLect.push(lectObj);
		// 결제 페이지 넘어가기전 유효성 체크
		fnc.paramAjax(fn_validate_callback, "/payment/validateStep1.ajax", {
			jsonStr : JSON.stringify(arrLect)
			, atlctType : 'waiting'
		}, "json");
	}
	
	// 결제 페이지 넘어가기전 유효성 체크 콜백
	var fn_validate_callback = function(data){
		var rtnMap = data.rtnMap;
		if(rtnMap.rsltCd == "-1"){
			alert("수강결제는 접수중인 강좌만 가능합니다.\n선택한 강좌를 다시 한번 확인하세요.");
		}else if(rtnMap.rsltCd == "-2"){
			//alert("강좌명 : " + rtnMap.lectNm + "\n현재 수강 가능인원은 " + rtnMap.capaCnt + "명입니다.");
			alert("강좌명 : " + fnc.returnHtml(rtnMap.lectNm) + "\n결제 하실 수 없습니다. 관리자에게 문의하세요.");
		}else if(rtnMap.rsltCd == "-3"){
			alert("강좌명 : " + fnc.returnHtml(rtnMap.atlctYnMap.lectNm) + "\n수강자 : " + rtnMap.atlctYnMap.actlAtlctNpleNm + "\n강좌는 이미 수강신청한 강좌입니다.");
		}else if(rtnMap.rsltCd == "-4"){
			alert("AVENUEL ORANGE 등급 이상,  AVENUEL 소속점과 관심지점이 동일한 경우에만 결제 가능합니다.");
		}else if(rtnMap.rsltCd == "-5"){
			var cnclYn = false;
			if(rtnMap.lectDtDuplYnList.length > 0){
				if(rtnMap.lectDtDuplYnList[0].type == "10"){
					if(!confirm("수강자 : " + rtnMap.lectDtDuplYnList[0].actlAtlctNpleNm + "\n강의기간(시간, 요일)이 중복되는 강좌가 있습니다.\n그래도 결제하시겠습니까?")){
						cnclYn = true;
					}
				}else if(rtnMap.lectDtDuplYnList[0].type == "20"){
					if(!confirm("수강자 : " + rtnMap.lectDtDuplYnList[0].actlAtlctNpleNm + "\n결제내역 강의기간(시간, 요일)이 중복되는 강좌가 있습니다.\n그래도 결제하시겠습니까?")){
						cnclYn = true;
					}
				}
			}
//			for(var i=0;i<rtnMap.lectDtDuplYnList.length;i++){
//				if(rtnMap.lectDtDuplYnList[i].type == "10"){
//					// 강좌목록
//					if(confirm("수강자 : " + rtnMap.lectDtDuplYnList[i].actlAtlctNpleNm + "\n강의기간(시간, 요일)이 중복되는 강좌가 있습니다.\n그래도 결제하시겠습니까?")){
//						cnclYn = false;
//					}else{
//						cnclYn = true;
//					}
//				}else if(rtnMap.lectDtDuplYnList[i].type == "20"){
//					// 결제내역
//					if(confirm("수강자 : " + rtnMap.lectDtDuplYnList[i].actlAtlctNpleNm + "\n결제내역 강의기간(시간, 요일)이 중복되는 강좌가 있습니다.\n그래도 결제하시겠습니까?")){
//						cnclYn = false;
//					}else{
//						cnclYn = true;
//					}
//				}
//				if(cnclYn){
//					break;
//				}
//			}
			if(!cnclYn){
				$('#frm_submit').attr('action', '/payment/step2.do');
				$('#frm_submit').find('input[name=jsonStr]').val(rtnMap.jsonStr);
				$('#frm_submit').attr('method', 'POST');
				$('#frm_submit').submit();
			}
		}else{
			if(confirm("결제를 진행하시겠습니까?")){
				$('#frm_submit').attr('action', '/payment/step2.do');
				$('#frm_submit').find('input[name=jsonStr]').val(rtnMap.jsonStr);
				$('#frm_submit').attr('method', 'POST');
				$('#frm_submit').submit();
			}
		}
	}
	
	// 대기취소
	var fn_move_cancel = function(obj) {
    if (confirm("선택한 대기자를 삭제하시겠습니까?")) {
        var lateSq = $(obj).closest('div.cour_his_list').data('lateSq');
        var lectNm = $(obj).closest('div.cour_his_list').data('classNm');
        var optnNm = $(obj).closest('div.cour_his_list').data('optnNm');
        var lectStDtm = $(obj).closest('div.cour_his_list').data('scheduleStartDt');
        var lectSt = $(obj).closest('div.cour_his_list').data('classSt');
        console.log(lateSq);
        $('#frm_cancel').attr('action', '/mypage/waiting/cancel.do');
        $('#frm_cancel').find('input[name=atlctRsvNo]').val(lateSq);
        $('#frm_cancel').find('input[name=lectNm]').val(lectNm);
        $('#frm_cancel').find('input[name=optnNm]').val(optnNm);
        $('#frm_cancel').find('input[name=lectStDtm]').val(lectStDtm);
        $('#frm_cancel').find('input[name=lectSt]').val(lectSt);
        $('#frm_cancel').attr('method', 'POST');
        $('#frm_cancel').submit();
    }
}
	
	var init = function() {
		var initObj = {
				form : $("#frm_search")
				, container : $("#listContainer")
				, moreBtn : $("#moreBtn")
				, url : "/mypage/waiting/list.ajax"
				, pageIndex : $("#frm_search #pageIndex").val() 
				, listCnt : $("#frm_search #listCnt").val()    
				, callbackFunc : function(data) {
				
				console.log($("#frm_search #pageIndex").val());
				console.log($("#frm_search #listCnt").val());
				
							
					$("#totCnt").text(searchMore.totCnt  + "개");
					moreStudent();
					
				}
			
		}
		searchMore = new fnc.SearchMore(initObj);
		searchMore.search();
	}
	
	$(document).ready(function() {
		init();
	});
	
	 return {
		 changebrchNm : fn_changebrchNm
		 , movePayment : fn_move_payment
		 , moveCancel : fn_move_cancel
	 }
}());