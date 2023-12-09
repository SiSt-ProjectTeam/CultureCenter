$(document).ready(function() {
	mypage_cart.list();
});

var mypage_cart = (function(){
	
	"use strict";
	
	// 리스트 ajax
	var fn_list = function(pageIndex){
		if(pageIndex == undefined){
			pageIndex = 1;
		}
		$('#frm_search').find('#pageIndex').val(pageIndex);
		fnc.frmAjax(fn_list_callback, "/mypage/cart/list.ajax", $('#frm_search'), "html");
	}
	
	// 리스트 콜백
	var fn_list_callback = function(html){
		$('a.remove_bag').remove();
		$('div.course_history_w').append(html);
	}
	
	// 지점 변경
	var fn_change_brchCd = function(brchCd, brchNm){
		$('#frm_search').find('#brchCd').val(brchCd);
		$('#frm_search').find('#brchNm').val(brchNm);
		$('#frm_search').find('#pageIndex').val(1);
		$('#frm_search').attr('method', 'get');
		$('#frm_search').attr('action', '/mypage/cart/list.do');
		$('#frm_search').submit();
	}
	
	// 전체선택
	var fn_click_all_checkbox = function(obj){
		if($(obj).is(":checked")){
			// 체크
			var totLectCnt = 0;				// 총 강좌 건수
			var totLectStlmAmt = 0;			// 총 강좌 결제금액
			$('div.course_history_w div.cour_his_list').each(function(){
				totLectCnt++;
				totLectStlmAmt += Number($(this).data('lectStlmAmt'));
			});
			
			$('div.payment_con span.num_case').text(totLectCnt + "건");
			console.log(fnc.fn_numberComma(totLectStlmAmt));
			$('div.payment_con span.price').text(fnc.fn_numberComma(totLectStlmAmt));
		}else{
			// 체크해제
			$('div.payment_con span.num_case').text("0건");
			$('div.payment_con span.price').text(0);
		}
	}
	
	// 단일선택
	var fn_click_checkbox = function(obj){
		var totLectCnt = Number($('div.payment_con span.num_case').text().substring(0, $('div.payment_con span.num_case').text().length - 1));
		var totLectStlmAmt = Number($('div.payment_con span.price').text().replace(/,/gi, ""));
		if($(obj).is(":checked")){
			totLectCnt++;
			totLectStlmAmt += Number($(obj).closest('div.cour_his_list').data('lectStlmAmt'));
		}else{
			totLectCnt--;
			totLectStlmAmt -= Number($(obj).closest('div.cour_his_list').data('lectStlmAmt'));
		}
		
		$('div.payment_con span.num_case').text(totLectCnt + "건");
		$('div.payment_con span.price').text(fnc.fn_numberComma(totLectStlmAmt));
		console.log(fnc.fn_numberComma(totLectStlmAmt));
	}
	
	// 장바구니 비우기
	var fn_remove_cartList = function(){
		if($('div.cour_his_list').length > 0){
			if(confirm("장바구니에 있는 강좌 전부를 삭제하시겠습니까?")){
				fnc.paramAjax(fn_remove_callback, "/mypage/cart/delete.ajax", {type:'all'}, "json");
			}
		}else{
			alert("삭제하실 강좌가 없습니다.");
			return;
		}
	}
	
	// 장바구니 삭제
	var fn_remove_cart = function(obj){
		var cartSeqno = $(obj).closest('div.cour_his_list').data('cartSeqno');
		cartSeqno = "'"+ cartSeqno +"'";
		if(confirm("선택한 강좌를 삭제하시겠습니까?")){
			fnc.paramAjax(fn_remove_callback, "/mypage/cart/delete.ajax", {type:'select', cartSeqno : cartSeqno}, "json");
		}
	}
	
	// 장바구니 선택삭제
	var fn_remove_cartCheck = function(){
		var arrCartSeqno = [];
		$('div.course_history_w div.cour_his_list').each(function(){
			if($(this).find("input[id^='shopbagAgree']").is(":checked")){
				arrCartSeqno.push("'"+ $(this).data('cartSeqno') +"'");
			}
		});
		
		if(arrCartSeqno.length > 0){
			if(confirm("선택한 강좌를 삭제하시겠습니까?")){
				fnc.paramAjax(fn_remove_callback, "/mypage/cart/delete.ajax", {type:'check', cartSeqno : arrCartSeqno.join()}, "json");
			}
		}else{
			alert("선택한 강좌가 없습니다.");
			return;
		}
	}
	
	// 장바구니 삭제 콜백
	var fn_remove_callback = function(rtnMap){
		if(rtnMap.cnt > 0){
			if(rtnMap.type == 'all'){
				alert("강좌가 모두 삭제되었습니다.");
			}else if(rtnMap.type == 'select' || rtnMap.type == 'check'){
				alert("선택하신 강좌가 삭제되었습니다.");
			}
			location.href="/mypage/cart/list.do";
		}else{
			alert("장바구니 비우기에 실패하였습니다.");
			return;
		}
	}
	
	// 결제하기
	var fn_payment = function(){
		var arrLect = [];
		var brchCdYn = true, yySmsterYn = true, lectStatYn = true, optnSeqnoYn = true, mvgYn = true, duplYn = false;
		var mbrGrdeCd = $('div.course_history_w').data('mbrGrdeCd');
		var mvgBlstrCd = $('div.course_history_w').data('mvgBlstrCd');
		var mvgLectNm = ""; // MVG 회원등급이 아닐때 결제하려는 MVG 강좌명 (얼럿메시지에 필요)
		var atlctDuplYn = "", lectDuplYn = "";
		var optnValidateYn = true;
		var optnNotValidateLectNm = "";
		$('div.course_history_w div.cour_his_list').each(function(){
			if($(this).find('input[type=checkbox]').prop('checked')){
				
				// 옵션이 변경되서 유효하지 않을 경우
				if($(this).data('optnValidateYn') == "N"){
					optnValidateYn = false;
					optnNotValidateLectNm = $(this).data('lectNm');
				}
				
				arrLect.push({
					brchCd : $(this).data('brchCd')
					, yy : $(this).data('yy')
					, lectSmsterCd : $(this).data('lectSmsterCd')
					, lectCd : $(this).data('lectCd')
					, lectStatCd : $(this).data('lectStatCd')
					, mvgDsplyUseYn : $(this).data('mvgDsplyUseYn')
					, optnSeqno : $(this).data('optnSeqno')
					, lectNm : $(this).data('lectNm')
					, optnUseYn : $(this).data('optnUseYn')
					, pblPmprcustParntBrchCd : $(this).data('pblPmprcustParntBrchCd')
					, pblPmprcustParntLectCd : $(this).data('pblPmprcustParntLectCd')
					, atlctDuplYn : $(this).data('atlctDuplYn')
					, lectDuplYn : $(this).data('lectDuplYn')
				});
			}
		});
		
		if(!optnValidateYn){
			alert("강좌명 : " + optnNotValidateLectNm + "\n재료비/대여료 옵션이 변경되어 결제 하실 수 없습니다.\n(장바구니에서 삭제 후 새로 담거나 강좌상세에서 재료비/대여료 옵션을 재선택 후 결제 하세요.)");
			return;
		}
		
		if(arrLect.length == 0){
			alert("결제하실 강좌가 없습니다.");
			return;
		}else{
			var brchCd, yy, lectSmsterCd, lectCd, optnSeqno;
			var arrBrchCd = [], arrYy = [], arrLectSmsterCd = [], arrLectCd = [], arrOptnSeqno = [], arrOptnUseYn = [];
			for(var i=0;i<arrLect.length;i++){
				if(arrLect[i].lectStatCd == '01' || arrLect[i].lectStatCd == '04' || arrLect[i].lectStatCd == '05' || arrLect[i].lectStatCd == '06' || arrLect[i].lectStatCd == '07'){
					// 접수예정, 대기신청, 지점문의, 접수마감, 강의종료 상태 결제불가
					lectStatYn = false;
				}
				if(!lectStatYn){
					break;
				}
				
				//("".equals(lgnMap.getString("mbrGrdeCd")) || !lgnMap.getString("mvgBlstrCd").equals(lectMap.getString("brchCd")))
				if(arrLect[i].mvgDsplyUseYn == "Y" && (mbrGrdeCd == "" || mvgBlstrCd != arrLect[i].brchCd)){
					mvgYn = false;
					mvgLectNm = arrLect[i].lectNm;
				}
				if(!mvgYn){
					break;
				}
				
				if(arrLect[i].atlctDuplYn == "Y" || arrLect[i].lectDuplYn == "Y"){
					duplYn = true;
				}
				
				if(i == 0){
					brchCd = arrLect[i].brchCd;
					yy = arrLect[i].yy;
					lectSmsterCd = arrLect[i].lectSmsterCd;
					lectCd = arrLect[i].lectCd;
					optnSeqno = arrLect[i].optnSeqno;
				}else{
					if(brchCd != arrLect[i].brchCd){
						brchCdYn = false;
					}
					if(yy != arrLect[i].yy || lectSmsterCd != arrLect[i].lectSmsterCd){
						yySmsterYn = false;
					}
					if(brchCd == arrLect[i].brchCd && yy == arrLect[i].yy && lectSmsterCd == arrLect[i].lectSmsterCd && lectCd == arrLect[i].lectCd && optnSeqno != arrLect[i].optnSeqno){
						optnSeqnoYn = false;
					}
				}
				if(!brchCdYn){
					break;
				}
				if(!yySmsterYn){
					break;
				}
				if(!optnSeqnoYn){
					break;
				}
				
				arrBrchCd.push(arrLect[i].pblPmprcustParntBrchCd != '' ? arrLect[i].pblPmprcustParntBrchCd : arrLect[i].brchCd);
				arrYy.push(arrLect[i].yy);
				arrLectSmsterCd.push(arrLect[i].lectSmsterCd);
				arrLectCd.push(arrLect[i].pblPmprcustParntLectCd != '' ? arrLect[i].pblPmprcustParntLectCd : arrLect[i].lectCd);
				arrOptnSeqno.push(arrLect[i].optnSeqno);
				arrOptnUseYn.push(arrLect[i].optnUseYn);
			}
			
			if(!brchCdYn){
				alert("다른 지점 강좌는 일괄결제 할 수 없습니다.");
				return;
			}
			if(!yySmsterYn){
				alert("같은 학기 강좌만 결제 가능합니다.");
				return;
			}
			if(!lectStatYn){
				alert("수강결제는 접수중인 강좌만 가능합니다.\n선택한 강좌를 다시 한번 확인하세요.");
				return;
			}
			if(!mvgYn){
				alert("강좌명 : " + mvgLectNm + "\nAVENUEL 회원등급이 아닌 수강자는 결제 하실 수 없습니다.");
				return;
			}
			if(!optnSeqnoYn){
				alert("동일한 강좌가 포함 되어있어 결제하실 수 없습니다.\n선택한 강좌를 다시 한번 확인하세요.");
				return;
			}
			
			// 2023.03.26 중복체크 제거
//			if(duplYn){
//				if(!confirm("강의기간(시간, 요일)이 중복되는 강좌가 있습니다.\n그래도 결제하시겠습니까?")){
//					return;
//				}
//			}
			
			if(confirm("선택한 강좌를 결제하시겠습니까?")){
				$('#frm_submit').find('input[name=brchCd]').val(arrBrchCd.join());
				$('#frm_submit').find('input[name=yy]').val(arrYy.join());
				$('#frm_submit').find('input[name=lectSmsterCd]').val(arrLectSmsterCd.join());
				$('#frm_submit').find('input[name=lectCd]').val(arrLectCd.join());
				$('#frm_submit').find('input[name=optnSeqno]').val(arrOptnSeqno.join());
				$('#frm_submit').find('input[name=optnUseYn]').val(arrOptnUseYn.join());
				$('#frm_submit').submit();
			}
		}
	}
	 return {
		 changeBrchCd : fn_change_brchCd
		 , clickAllCheckbox : fn_click_all_checkbox
		 , clickCheckbox : fn_click_checkbox
		 , removeCartList : fn_remove_cartList
		 , removeCart : fn_remove_cart
		 , removeCartCheck : fn_remove_cartCheck
		 , payment : fn_payment
		 , list : fn_list
	 }
}());