var payment = (function(){
	
	"use strict";
	
	// 팝업 열기
	var fn_open_fmly_popup = function(obj, type){
		var objClCd = $(obj).closest('div.cour_his_list').data('objClCd');
		var lectNm = $(obj).closest('div.cour_his_list').data('lectNm');
		var brchCd = $(obj).closest('div.cour_his_list').data('brchCd');
		var yy = $(obj).closest('div.cour_his_list').data('yy');
		var lectSmsterCd = $(obj).closest('div.cour_his_list').data('lectSmsterCd');
		var lectCd = $(obj).closest('div.cour_his_list').data('lectCd');			
		var lectAmt = $(obj).closest('div.cour_his_list').data('lectAmt');
		var optnTypCdNm = $(obj).closest('div.cour_his_list').data('optnTypCdNm');
		var optnNm = $(obj).closest('div.cour_his_list').data('optnNm');
		var optnAmt = $(obj).closest('div.cour_his_list').data('optnAmt');
		var optnSeqno = $(obj).closest('div.cour_his_list').data('optnSeqno');
		var optnUseYn = $(obj).closest('div.cour_his_list').data('optnUseYn');
		
		if($(obj).closest('div.cour_his_list').data('lectTpCd') == "04" && $(obj).closest('div.cour_his_list').data('pblPmprcustBrchCd') != ""){
			brchCd = $(obj).closest('div.cour_his_list').data('pblPmprcustBrchCd');
			lectCd = $(obj).closest('div.cour_his_list').data('pblPmprcustLectCd');
		}
		
		// 수강자 변경/추가
		$('#'+ type +'Popup').data('objClCd', objClCd);
		$('#'+ type +'Popup').data('lectNm', lectNm);
		$('#'+ type +'Popup').data('brchCd', brchCd);
		$('#'+ type +'Popup').data('yy', yy);
		$('#'+ type +'Popup').data('lectSmsterCd', lectSmsterCd);
		$('#'+ type +'Popup').data('lectCd', lectCd);
		$('#'+ type +'Popup').data('lectAmt', lectAmt);
		$('#'+ type +'Popup').data('optnTypCdNm', optnTypCdNm);
		$('#'+ type +'Popup').data('optnNm', optnNm);
		$('#'+ type +'Popup').data('optnAmt', optnAmt);
		$('#'+ type +'Popup').data('optnSeqno', optnSeqno);
		$('#'+ type +'Popup').data('optnUseYn', optnUseYn);
		
		$('#'+ type +'Popup').find('input[type=checkbox]').each(function(){
			$(this).prop('checked', false);
		});
		
		$(obj).closest('div.cour_detail_w').find('div.cour_detail').each(function(){
			var korNm = $(this).data('korNm');
			var fmlyRelCd = $(this).data('fmlyRelCd');
			var bday = $(this).data('bday');
			
			$('#'+ type +'Popup').find('input[type=checkbox]').each(function(){
				if($(this).data('korNm') == korNm && $(this).data('fmlyRelCd') == fmlyRelCd && $(this).data('bday') == bday){
					$(this).prop('checked', true);
				}
			});
		});
		
		commonScript.openPopupFn('#'+ type +'Popup', $(this));
//		$('#'+ type +'Popup').fadeIn();
//		popupResize();
	}
	
	// 수강자 변경/추가 팝업
	var fn_update_actl_atlct_nple = function(obj, type){
		if($('#'+ type +'Popup').find('input[type=checkbox]:checked').length > 0){
			// 강좌대상구분
			var objClCd = $('#'+ type +'Popup').data('objClCd');
			var lectNm = $('#'+ type +'Popup').data('lectNm');
			var brchCd = $('#'+ type +'Popup').data('brchCd');
			var yy = $('#'+ type +'Popup').data('yy');
			var lectSmsterCd = $('#'+ type +'Popup').data('lectSmsterCd');
			var lectCd = $('#'+ type +'Popup').data('lectCd');			
			var lectAmt = $('#'+ type +'Popup').data('lectAmt');
			var optnTypCdNm = $('#'+ type +'Popup').data('optnTypCdNm');
			var optnNm = $('#'+ type +'Popup').data('optnNm');
			var optnAmt = $('#'+ type +'Popup').data('optnAmt');
			var optnSeqno = $('#'+ type +'Popup').data('optnSeqno');
			var optnUseYn = $('#'+ type +'Popup').data('optnUseYn');
			
			
			if(objClCd == "02"){
				// 2인 강좌인 경우
				if($('#'+ type +'Popup').find('input[type=checkbox]:checked').length == 1){
					if(type == 'fmly' && $('#'+ type +'Popup').find('input[type=checkbox]:checked').attr('id') == 'student0'){
						// 본인 선택한 경우
						alert("강좌명 : " + lectNm + "\n본인을 제외한 1명의 실수강자만 선택하세요.");
						return;
					}
				}else{
					alert("강좌명 : " + lectNm + "\n1명의 실수강자만 선택하세요.");
					return;
				}
			}
			
			if(confirm("수강자를 변경하시겠습니까?")){
				var arrKorNm = [], arrBday = [], arrFmlyRelCd = [], arrFmlyRelCdNm = [], arrSexCd = [];
				$('#'+ type +'Popup').find('input[type=checkbox]:checked').each(function(){
					arrKorNm.push($(this).data('korNm'));
					arrFmlyRelCd.push($(this).data('fmlyRelCd'));
					arrFmlyRelCdNm.push($(this).data('fmlyRelCdNm'));
					arrSexCd.push($(this).data('sexCd'));
					arrBday.push($(this).data('bday'));
				});
				
				fnc.paramAjax(fn_update_actl_atlct_nple_callback, "/payment/actlAtlctNpleList.ajax", {
					type : type
					, cnt : $('#'+ type +'Popup').find('input[type=checkbox]:checked').length
					, brchCd : brchCd
					, yy : yy
					, lectSmsterCd : lectSmsterCd
					, lectCd : lectCd
					, lectAmt : lectAmt
					, optnTypCdNm : optnTypCdNm
					, optnNm : optnNm
					, optnAmt : optnAmt
					, optnSeqno : optnSeqno
					, optnUseYn : optnUseYn
					, korNm : arrKorNm.join()
					, fmlyRelCd : arrFmlyRelCd.join()
					, fmlyRelCdNm : arrFmlyRelCdNm.join()
					, sexCd : arrSexCd.join()
					, bday : arrBday.join()
				}, "html");
			}
		}else{
			alert("수강자를 선택하세요.");
			return;
		}
	}
	
	// 수강자 변경/추가 콜백
	var fn_update_actl_atlct_nple_callback = function(html){
		var rsltCd = $(html).data('rsltCd');
		var type = $(html).data('type');
		if(rsltCd == '-1'){
			alert('수강결제는 접수중인 강좌만 가능합니다.\n선택한 강좌를 다시 한번 확인하세요.');
		}else if(rsltCd == '-2'){
			alert('강좌명 : ' + $(html).data('lectNm') + '\n현재 수강 가능인원은 ' + $(html).data('capaCnt') + '명입니다.');
		}else if(rsltCd == '-3'){
			alert('강좌명 : ' + $(html).data('lectNm') + '\n수강자 : ' + $(html).data('actlAtlctNpleNm') + '\n강좌는 이미 수강신청한 강좌입니다.');
		}else if(rsltCd == '-4'){
			alert('AVENUEL ORANGE 등급 이상,  AVENUEL 소속점과 관심지점이 동일한 경우에만 결제 가능합니다.');
		}else{
			var brchCd = $('#'+ type +'Popup').data('brchCd');
			var yy = $('#'+ type +'Popup').data('yy');
			var lectSmsterCd = $('#'+ type +'Popup').data('lectSmsterCd');
			var lectCd = $('#'+ type +'Popup').data('lectCd');
			var optnSeqno = $('#'+ type +'Popup').data('optnSeqno');
			$('div.course_history_w div.cour_his_list').each(function(){
				if((($(this).data('brchCd') == brchCd && $(this).data('lectCd') == lectCd) || ($(this).data('pblPmprcustBrchCd') == brchCd && $(this).data('pblPmprcustLectCd') == lectCd)) 
						&& $(this).data('yy') == yy && $(this).data('lectSmsterCd') == lectSmsterCd && $(this).data('optnSeqno') == optnSeqno){
					$(this).find('div.cour_detail_w').empty();
					$(this).find('div.cour_detail_w').append(html);
					// 체크한게 2개 이상이면 cour_detail_w div에 plural 클래스 추가
					if($('#'+ type +'Popup').find('input[type=checkbox]:checked').length > 1){
						$(this).find('div.cour_detail_w').addClass('plural');
					}
				}
			});
			// 총 결제예정금액 계산
			payment_tot();
			//팝업창 닫기
			fn_fadeOut($('#'+ type +'Popup'));
		}
	}
	
	// 총 결제예정금액 계산
	var payment_tot = function(){
		var totLectStlmAmt = 0;
		$('div.course_history_w div.cour_his_list').each(function(){
			var lectAmt = $(this).data('lectAmt');
			var optnAmt = $(this).data('optnAmt');
			totLectStlmAmt += Number(lectAmt) * $(this).find('div.cour_detail_w').find('div.cour_detail').length;
			totLectStlmAmt += Number(optnAmt) * $(this).find('div.cour_detail_w').find('div.cour_detail').length;
		})
		$('div.all_price').find('span.price').text(fnc.fn_numberComma(totLectStlmAmt));
	}
	
	// 팝업 닫기
	var fn_close_popup = function(obj){
		if($(obj).closest('div.layer_popup').attr("id") == "dcPopup"){
			$('div.flex_btn_wrap.on').removeClass('on');
		}
		fn_fadeOut($(obj).closest('div.layer_popup'));
	}
	
	// 실수강자 삭제
	var fn_delete_actl_atlct_nple = function(obj){
		if(confirm("수강자 목록에서 삭제하시겠습니까?")){
			if($(obj).closest('div.cour_detail_w').find('div.cour_detail').length == 2){
				$(obj).closest('div.cour_detail_w').removeClass('plural');
			}
			$(obj).closest('div.cour_detail').remove();
			
			// 총 결제예정금액 계산
			var totLectStlmAmt = 0;
			$('div.course_history_w div.cour_his_list').each(function(){
				var lectAmt = $(this).data('lectAmt');
				var optnAmt = $(this).data('optnAmt');
				totLectStlmAmt += Number(lectAmt) * $(this).find('div.cour_detail_w').find('div.cour_detail').length;
				totLectStlmAmt += Number(optnAmt) * $(this).find('div.cour_detail_w').find('div.cour_detail').length;
			})
			$('div.all_price').find('span.price').text(fnc.fn_numberComma(totLectStlmAmt));
		}
	}
	
	// 가족회원 삭제
	var fn_delete_fmly = function(obj){
		var fmlySeqno = $(obj).data('fmlySeqno');
		var korNm = $(obj).data('korNm');
		var fmlyRelCd = $(obj).data('fmlyRelCd');
		var sexCd = $(obj).data('sexCd');
		var bday = $(obj).data('bday');
		
		var actlAtlctNpleYn = false;
		$('div.course_history_w').find('div.cour_his_list').each(function(){
			$(this).find('div.cour_detail').each(function(){
				if($(this).data('korNm') == korNm && $(this).data('fmlyRelCd') == fmlyRelCd && $(this).data('sexCd') == sexCd && $(this).data('bday') == bday){
					actlAtlctNpleYn = true;
				}
			});
		});
		
		if(actlAtlctNpleYn){
			alert("수강 신청할 강좌의 수강자로 등록되어있습니다.\n수강자에서 삭제 후 이용 가능합니다.");
		}else{
			if(confirm("가족회원을 삭제하시겠습니까?")){
				var paramObj = {
					fmlySeqno : fmlySeqno
				}
				
				fnc.paramAjax(function(data) {
					if(data.rtnCode == "1")
					{
						window.location.reload();
						/*
						if($(obj).closest('div.info_txt').find('div.info_list').length == 1){
							var hideListDiv = $(obj).closest('div.hide_list');
							$(obj).closest('div.info_txt').remove();							
							var html = '';
							html += '<div class="no_srch_area no_pb">';
							html += '	<div class="no_srch_div">';
							html += '		<p class="txt f_h2"><span class="normal_value">등록된 정보가 없습니다.</span></p>';
							html += '	</div>';
							html += '</div>';
							hideListDiv.append(html);
						}else{
							$(obj).closest('div.info_list').remove();
						}
						*/
					}
				}, "/mypage/member/family/delete.ajax", paramObj, "json", true, false);
			}
		}
	}
	
	// 가족회원 추가
	var fn_add_child = function(){
		commonScript.openPopupFn("#addFamilyPop", $(this));
		addFamily.init(function(){
			window.location.reload();
		});
	}
	
	// step1 -> step2
	var fn_submit_step1_frm = function(){
		var arrLect = [];
		
		$('div.course_history_w').find('div.cour_his_list').each(function(){
			var lectObj = {};
			lectObj.brchCd = $(this).data('brchCd');
			lectObj.yy = $(this).data('yy');
			lectObj.lectSmsterCd = $(this).data('lectSmsterCd');
			lectObj.lectCd = $(this).data('lectCd');
			lectObj.optnSeqno = $(this).data('optnSeqno');
			lectObj.lectTpCd = $(this).data('lectTpCd');
			if($(this).data('lectTpCd') == "04" && $(this).data('pblPmprcustBrchCd') != ""){
				lectObj.brchCd = $(this).data('pblPmprcustBrchCd');
				lectObj.lectCd = $(this).data('pblPmprcustLectCd');
				lectObj.pblPmprcustParntBrchCd = $(this).data('brchCd');
				lectObj.pblPmprcustParntLectCd = $(this).data('lectCd');
			}
//			lectObj.pblPmprcustBrchCd = $(this).data('pblPmprcustBrchCd');
//			lectObj.pblPmprcustLectCd = $(this).data('pblPmprcustLectCd');
//			lectObj.pblPmprcustBrchCdNm = $(this).data('pblPmprcustBrchCdNm');
			
			var arrActlAtlctNple = [];
			$(this).find('div.cour_detail').each(function(){
				var actlAtlctNpleObj = {};
				actlAtlctNpleObj.actlAtlctNpleNm = $(this).data('korNm');
				actlAtlctNpleObj.fmlyRelCd = $(this).data('fmlyRelCd');
				actlAtlctNpleObj.fmlyRelCdNm = $(this).data('fmlyRelCdNm');
				actlAtlctNpleObj.bday = $(this).data('bday');
				actlAtlctNpleObj.sexCd = $(this).data('sexCd');
				
				arrActlAtlctNple.push(actlAtlctNpleObj);
			});
			
			lectObj.arrActlAtlctNple = arrActlAtlctNple;
			
			arrLect.push(lectObj);
		});
		
		fnc.paramAjax(fn_validate_step1_callback, "/payment/validateStep1.ajax", {
			jsonStr : JSON.stringify(arrLect)
		}, "json");
	}
	
	// step1 -> step2 콜백
	var fn_validate_step1_callback = function(data){
		var rtnMap = data.rtnMap;
		if(rtnMap.rsltCd == "-1"){
			alert("수강결제는 접수중인 강좌만 가능합니다.\n선택한 강좌를 다시 한번 확인하세요.");
		}else if(rtnMap.rsltCd == "-2"){
			alert("강좌명 : " + fnc.returnHtml(rtnMap.lectNm) + "\n현재 수강 가능인원은 " + rtnMap.capaCnt + "명입니다.");
		}else if(rtnMap.rsltCd == "-3"){
			alert("강좌명 : " + fnc.returnHtml(rtnMap.atlctYnMap.lectNm) + "\n수강자 : " + rtnMap.atlctYnMap.actlAtlctNpleNm + "\n강좌는 이미 수강신청한 강좌입니다.");
		}else if(rtnMap.rsltCd == "-4"){
			alert('AVENUEL ORANGE 등급 이상,  AVENUEL 소속점과 관심지점이 동일한 경우에만 결제 가능합니다.');
		}else if(rtnMap.rsltCd == "-5"){
			var cnclYn = false;
			var arrLect = [], arrAtlct = [];
			for(var i=0;i<rtnMap.lectDtDuplYnList.length;i++){
				if(rtnMap.lectDtDuplYnList[i].type == "10"){
					// 강좌목록
					arrLect.push(rtnMap.lectDtDuplYnList[i].actlAtlctNpleNm);
					break;
//					if(!confirm("강좌명 : " + rtnMap.lectDtDuplYnList[i].lectNm + "\n수강자 : " + rtnMap.lectDtDuplYnList[i].actlAtlctNpleNm + "\n강의기간(시간, 요일)이 중복되는 강좌가 있습니다.\n그래도 결제하시겠습니까?")){
//						//cnclYn = true;
//					}
				}else if(rtnMap.lectDtDuplYnList[i].type == "20"){
					// 결제내역
					arrAtlct.push(rtnMap.lectDtDuplYnList[i].actlAtlctNpleNm);
					break;
//					if(!confirm("강좌명 : " + rtnMap.lectDtDuplYnList[i].lectNm + "\n수강자 : " + rtnMap.lectDtDuplYnList[i].actlAtlctNpleNm + "\n결제내역 강의기간(시간, 요일)이 중복되는 강좌가 있습니다.\n그래도 결제하시겠습니까?")){
//						//cnclYn = true;
//					}
				}
			}
			
			if(arrLect.length > 0 || arrAtlct.length > 0){
				arrLect = arrLect.concat(arrAtlct);
				arrLect = arrLect.filter((v, i) => arrLect.indexOf(v) === i);
				if(!confirm("수강자 : " + arrLect.join() + "\n강의기간(시간, 요일)이 중복되는 강좌가 있습니다.\n그래도 결제하시겠습니까?")){
					cnclYn = true;
				}
			}
//			if(arrAtlct.length > 0){
//				if(!confirm("수강자 : " + arrAtlct.join() + "\n결제내역 강의기간(시간, 요일)이 중복되는 강좌가 있습니다.\n그래도 결제하시겠습니까?")){
//					cnclYn = true;
//				}
//			}
			
			if(!cnclYn){
				if(rtnMap.atlctType == 'waiting'){
					$('#frm_submit').find('input[name=atlctType]').val(rtnMap.atlctType);
				}
				$('#frm_submit').attr('action', '/payment/step2.do');
				$('#frm_submit').find('input[name=jsonStr]').val(rtnMap.jsonStr);
				$('#frm_submit').attr('method', 'POST');
				$('#frm_submit').submit();
			}
		}else if(rtnMap.rsltCd == "-6"){
			alert("강좌명 : " + fnc.returnHtml(rtnMap.lectNm) + "\n본인을 제외한 1명의 실수강자만 선택하세요.");
		}else{
			if(rtnMap.atlctType == 'waiting'){
				$('#frm_submit').find('input[name=atlctType]').val(rtnMap.atlctType);
			}
			$('#frm_submit').attr('action', '/payment/step2.do');
			$('#frm_submit').find('input[name=jsonStr]').val(rtnMap.jsonStr);
			$('#frm_submit').attr('method', 'POST');
			$('#frm_submit').submit();
		}
	}
	
	// 할인 후 최종 주문금액 저장
	var grdeDcAmt = 0, cpnDcAmt = 0;
	
	// 할인수단 팝업
	var fn_open_dc_popup = function(obj){
		// 할인 먹일 대상 강좌의 실수강자 영역 체크
		$(obj).closest('div.flex_btn_wrap').addClass('on');
		
		var brchCd = $(obj).data('brchCd');
		var yy = $(obj).data('yy');
		var lectSmsterCd = $(obj).data('lectSmsterCd');
		var lectCd = $(obj).data('lectCd');
		var lrclsCtegryCd = $(obj).data('lrclsCtegryCd');
		var mdclsCtegryCd = $(obj).data('mdclsCtegryCd');
		var smclsCtegryCd = $(obj).data('smclsCtegryCd');
		var lectClCd = $(obj).data('lectClCd');
		var lectAmt = $(obj).data('lectAmt');
		var optnAmt = $(obj).data('optnAmt');
		
		// 버튼을 누른 실수강자의 등록되어있던 할인, 쿠폰정보 가져오기
		var mbrGrdeCd = "", cpnNo = "";
		if($(obj).closest('li.optional').find('div.opt_name').find('p.grdeDc').length > 0){
			mbrGrdeCd = $(obj).closest('li.optional').find('div.opt_name').find('p.grdeDc').data('mbrGrdeCd');
		}
		if($(obj).closest('li.optional').find('div.opt_name').find('p.cpnDc').length > 0){
			cpnNo = $(obj).closest('li.optional').find('div.opt_name').find('p.cpnDc').data('cpnNo');
		}
		
		// 기존에 추가되어있는 할인, 쿠폰 정보 가져오기(팝업에서 사용가능한 목록 뿌릴때 기존에 추가된 것들은 제외해야되서)
		var arrMbrGrdeCd = [], arrCpnNo = [];
		$('div.cour_detail_w div.cour_detail').each(function(){
			if($(this).find('div.right ul.txt_wrap li.optional div.opt_name p.grdeDc').length > 0){
				if(mbrGrdeCd != $(this).find('div.right ul.txt_wrap li.optional div.opt_name p.grdeDc').data('mbrGrdeCd')){
					arrMbrGrdeCd.push($(this).find('div.right ul.txt_wrap li.optional div.opt_name p.grdeDc').data('mbrGrdeCd'));
				}
			}
			if($(this).find('div.right ul.txt_wrap li.optional div.opt_name p.cpnDc').length > 0){
				if(cpnNo != $(this).find('div.right ul.txt_wrap li.optional div.opt_name p.cpnDc').data('cpnNo')){
					arrCpnNo.push($(this).find('div.right ul.txt_wrap li.optional div.opt_name p.cpnDc').data('cpnNo'));
				}
			}
		})
		
		$('#dcPopup').remove();
		fnc.paramAjax(function(html){
			$('#wrap').append(html);
			
			commonScript.openPopupFn('#dcPopup', $(this));
//			$('#dcPopup').fadeIn();
//			popupResize();
			commonScript.formChkFn();
			commonScript.popupFn();
		    
			if(mbrGrdeCd != ""){
				grdeDcAmt = Number($(obj).closest('li.optional').find('div.opt_name').find('p.grdeDc').data('grdeDcAmt'));
			}else{
				grdeDcAmt = 0;
			}
		    if(cpnNo != ""){
		    	cpnDcAmt = Number($(obj).closest('li.optional').find('div.opt_name').find('p.cpnDc').data('cpnDcAmt'));
		    }else{
		    	cpnDcAmt = 0;
		    }
		}, "/payment/getApplDcList.ajax", {
			brchCd : brchCd
			, yy: yy
			, lectSmsterCd : lectSmsterCd
			, lectCd : lectCd
			, lrclsCtegryCd : lrclsCtegryCd
			, mdclsCtegryCd : mdclsCtegryCd
			, smclsCtegryCd : smclsCtegryCd
			, lectClCd : lectClCd
			, lectAmt : lectAmt
			, optnAmt : optnAmt
			, existMbrGrdeCd : mbrGrdeCd
			, existCpnNo : cpnNo
			, arrMbrGrdeCd : arrMbrGrdeCd.join()
			, arrCpnNo : arrCpnNo.join()
		}, "html");
	}
	
	// 할인 선택
	var fn_select_grdeDc = function(obj, dcRt){
		//$(obj).addClass('default');
		var selectCpnYn = false;
		// 쿠폰이 선택되있으면 얼럿 후 초기화
		$('#cpnRow div.list_wrap div.scroll_wrap a.btn_link').each(function(){
			if(!$(this).hasClass('default') && $(this).hasClass('on')){
				selectCpnYn = true;
			}
		});
		
		if(dcRt != '' && selectCpnYn){
			alert("할인 > 쿠폰 순으로 할인수단을 선택하세요.");
			$('#grdeRow div.list_wrap div.scroll_wrap a.btn_link').removeClass('on');
			$('#cpnRow div.list_wrap div.scroll_wrap a.btn_link').removeClass('on');
			$('#grdeRow div.form_select_div').removeClass('change');
			$('#cpnRow div.form_select_div').removeClass('change');
			$('#grdeRow div.open_area a.btn_open span').text($('#grdeRow div.list_wrap div.scroll_wrap a.btn_link.init span').text());
			$('#cpnRow div.open_area a.btn_open span').text($('#cpnRow div.list_wrap div.scroll_wrap a.btn_link.init span').text());
			
			grdeDcAmt = 0;
		    cpnDcAmt = 0;
		    $('#dcPopup').find('div.price_area p.price span').text(fnc.fn_numberComma(grdeDcAmt + cpnDcAmt));
		}else{
			$('#grdeRow div.list_wrap div.scroll_wrap a.btn_link').removeClass('on');
			$(obj).addClass('on');
			$('#grdeRow div.form_select_div').addClass('change');
			$('#grdeRow div.open_area a.btn_open span').text($(obj).find('span').text());
			
			var lectAmt = Number($('#dcPopup').data('lectAmt'));
			var dcAmt = Math.round((lectAmt * (Number(dcRt) / 100)) / 10) * 10;
			grdeDcAmt = dcAmt;
			
			// 할인을 적용안함으로 했을 때 쿠폰의 할인가격 재적용
			if(dcAmt == 0 && cpnDcAmt > 0){
				$('#cpnRow').find('div.scroll_wrap a.btn_link').each(function(){
					if($(this).hasClass('on')){
						var dcAmtClCd = $(this).data('dcAmtClCd');
						var originCpnDcAmt = Number($(this).data('dcAmt'));
						if(dcAmtClCd == '02'){
							cpnDcAmt = (lectAmt < originCpnDcAmt) ? lectAmt : originCpnDcAmt;
						}else{
							cpnDcAmt = Math.round((lectAmt * (Number(originCpnDcAmt) / 100)) / 10) * 10;
						}
					}
				})
			}
			
			if(lectAmt < (grdeDcAmt + cpnDcAmt)){
				$('#dcPopup').find('div.price_area p.price span').text(fnc.fn_numberComma(lectAmt));
				$('#dcPopup').find('div.price_area div.price_div p.info').text('(등급할인 ' + fnc.fn_numberComma(grdeDcAmt) + '원 + 쿠폰 ' + fnc.fn_numberComma(lectAmt - grdeDcAmt) + '원)');
			}else{
				$('#dcPopup').find('div.price_area p.price span').text(fnc.fn_numberComma(grdeDcAmt + cpnDcAmt));
				$('#dcPopup').find('div.price_area div.price_div p.info').text('(등급할인 ' + fnc.fn_numberComma(grdeDcAmt) + '원 + 쿠폰 ' + fnc.fn_numberComma(cpnDcAmt) + '원)');
			}
		}
	}
	
	// 쿠폰 선택
	var fn_select_cpnDc = function(obj, dcAmtClCd, dcAmt){
		var lectAmt = Number($('#dcPopup').data('lectAmt'));
		if(dcAmtClCd == '02'){
			cpnDcAmt = Number(dcAmt);
		}else if(dcAmtClCd == '01'){
			cpnDcAmt = Math.round(((lectAmt - grdeDcAmt) * (Number(dcAmt) / 100)) / 10) * 10;
		}else{
			cpnDcAmt = 0;
		}
		
		if(lectAmt < (grdeDcAmt + cpnDcAmt)){
			if(confirm("쿠폰 사용금액이 강좌료 금액을 초과합니다. 그래도 사용하시겠습니까?")){
				cpnDcAmt = lectAmt - grdeDcAmt;
				$('#cpnRow div.list_wrap div.scroll_wrap a.btn_link').removeClass('on');
				$(obj).addClass('on');
				$('#cpnRow div.form_select_div').addClass('change');
				$('#cpnRow div.open_area a.btn_open span').text($(obj).find('span').text());
				$('#dcPopup').find('div.price_area p.price span').text(fnc.fn_numberComma(lectAmt));
				$('#dcPopup').find('div.price_area div.price_div p.info').text('(등급할인 ' + fnc.fn_numberComma(grdeDcAmt) + '원 + 쿠폰 ' + fnc.fn_numberComma(lectAmt - grdeDcAmt) + '원)');
			}else{
				$('#cpnRow div.list_wrap div.scroll_wrap a.btn_link').removeClass('on');
				cpnDcAmt = 0;
				$('#cpnRow div.form_select_div').removeClass('change');
				$('#cpnRow div.open_area a.btn_open span').text($('#cpnRow div.list_wrap div.scroll_wrap a.btn_link.init').find('span').text());
				$('#dcPopup').find('div.price_area p.price span').text(fnc.fn_numberComma(grdeDcAmt + cpnDcAmt));
				$('#dcPopup').find('div.price_area div.price_div p.info').text('(등급할인 ' + fnc.fn_numberComma(grdeDcAmt) + '원 + 쿠폰 ' + fnc.fn_numberComma(cpnDcAmt) + '원)');
			}
		}else{
			$('#cpnRow div.list_wrap div.scroll_wrap a.btn_link').removeClass('on');
			$(obj).addClass('on');
			$('#cpnRow div.form_select_div').addClass('change');
			$('#cpnRow div.open_area a.btn_open span').text($(obj).find('span').text());
			$('#dcPopup').find('div.price_area p.price span').text(fnc.fn_numberComma(grdeDcAmt + cpnDcAmt));
			$('#dcPopup').find('div.price_area div.price_div p.info').text('(등급할인 ' + fnc.fn_numberComma(grdeDcAmt) + '원 + 쿠폰 ' + fnc.fn_numberComma(cpnDcAmt) + '원)');
		}
	}
	
	// 할인 적용
	var fn_appl_dc = function(){
		var lectAmt = Number($('#dcPopup').data('lectAmt'));
		var optnAmt = Number($('#dcPopup').data('optnAmt'));
		if(confirm("적용하시겠습니까?")){
			
			if(Number($("#lpntUseAmt").val().replace(/,/gi, '')) > 0){
				// 엘포인트 초기화 시작
				$('#totLectStlmAmt').data('lpntUseAmt', 0)

				$('div.white_inner.open_pop').find('div.lpnt_box').hide();
				$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(3)').hide();

				$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(3) ul.price_wrap li:nth-child(1) div.price').text('-원');
				
				$('#cnclBtn').hide();
				$('#calcBtn').show();
				$('#lpntUseAmt').val('');
				$('#lpntUseAmt').prop('disabled', false);
				// 엘포인트 초기화 끝
			}
			
			$('div.flex_btn_wrap.on').closest('li.optional').find('div.opt_name').find('p.grdeDc').remove();
			$('div.flex_btn_wrap.on').closest('li.optional').find('div.opt_name').find('p.cpnDc').remove();
			
//			if(grdeDcAmt + cpnDcAmt > 0){
//				//$('div.flex_btn_wrap.on').find('p').addClass('red_txt');
//				
//			}
			$('div.flex_btn_wrap.on').closest('li.optional').find('div.txt_con').find('p.red_txt').text('(-)' + fnc.fn_numberComma(grdeDcAmt + cpnDcAmt) + '원');
			
			if(grdeDcAmt > 0){
				var mbrGrdeCd = $('#grdeRow div.list_wrap div.scroll_wrap a.btn_link.on').data('mbrGrdeCd');
				var grdeDcNm = $('#grdeRow div.list_wrap div.scroll_wrap a.btn_link.on').data('grdeDcNm');
				var grdeDcRt = $('#grdeRow div.list_wrap div.scroll_wrap a.btn_link.on').data('grdeDcRt');
				var grdeHtml = '<p class="grdeDc" data-mbr-grde-cd="'+ mbrGrdeCd +'" data-grde-dc-nm="'+ grdeDcNm +'" data-grde-dc-amt="'+ grdeDcAmt +'" data-grde-dc-rt="'+ grdeDcRt +'">'+ grdeDcNm + '<span class="amount">' + fnc.fn_numberComma(grdeDcAmt) +'원</span></p>';
				$('div.flex_btn_wrap.on').closest('li.optional').find('div.opt_name').append(grdeHtml);
				// cour_detail에 할인금액 세팅
				$('div.flex_btn_wrap.on').closest('div.cour_detail').data('grdeDcCd', mbrGrdeCd);
				$('div.flex_btn_wrap.on').closest('div.cour_detail').data('grdeDcNm', grdeDcNm);
				$('div.flex_btn_wrap.on').closest('div.cour_detail').data('grdeDcAmt', grdeDcAmt);
				$('div.flex_btn_wrap.on').closest('div.cour_detail').data('grdeDcRt', grdeDcRt);
			}else{
				$('div.flex_btn_wrap.on').closest('div.cour_detail').removeData("grdeDcCd");
				$('div.flex_btn_wrap.on').closest('div.cour_detail').removeData("grdeDcNm");
				$('div.flex_btn_wrap.on').closest('div.cour_detail').removeData("grdeDcAmt");
				$('div.flex_btn_wrap.on').closest('div.cour_detail').removeData("grdeDcRt");
//				$('div.flex_btn_wrap.on').closest('div.cour_detail').removeAttr('data-grde-dc-cd');
//				$('div.flex_btn_wrap.on').closest('div.cour_detail').removeAttr('data-grde-dc-nm');
//				$('div.flex_btn_wrap.on').closest('div.cour_detail').removeAttr('data-grde-dc-amt');
//				$('div.flex_btn_wrap.on').closest('div.cour_detail').removeAttr('data-grde-dc-rt');
			}
			
			if(cpnDcAmt > 0){
				var cpnSeqno = $('#cpnRow div.list_wrap div.scroll_wrap a.btn_link.on').data('cpnSeqno');
				var cpnDcNm = $('#cpnRow div.list_wrap div.scroll_wrap a.btn_link.on').data('cpnDcNm');
				var cpnDcAmtClCd = $('#cpnRow div.list_wrap div.scroll_wrap a.btn_link.on').data('dcAmtClCd');
				var cpnDcRt = $('#cpnRow div.list_wrap div.scroll_wrap a.btn_link.on').data('dcAmt');
				var cpnNo = $('#cpnRow div.list_wrap div.scroll_wrap a.btn_link.on').data('cpnNo');
				
				var cpnHtml = "";
				if(lectAmt - grdeDcAmt < cpnDcAmt){
					cpnHtml = '<p class="cpnDc" data-cpn-seqno="'+ cpnSeqno +'" data-cpn-dc-nm="'+ cpnDcNm +'" data-cpn-dc-amt="'+ (lectAmt - grdeDcAmt) +'" data-cpn-no="'+ cpnNo +'">'+ cpnDcNm + '<span class="amount">' + fnc.fn_numberComma(lectAmt - grdeDcAmt) +'원</span></p>';
				}else{
					cpnHtml = '<p class="cpnDc" data-cpn-seqno="'+ cpnSeqno +'" data-cpn-dc-nm="'+ cpnDcNm +'" data-cpn-dc-amt="'+ cpnDcAmt +'" data-cpn-no="'+ cpnNo +'">'+ cpnDcNm + '<span class="amount">' + fnc.fn_numberComma(cpnDcAmt) +'원</span></p>';
				}
				$('div.flex_btn_wrap.on').closest('li.optional').find('div.opt_name').append(cpnHtml);
				
				// cour_detail에 할인금액 세팅
				$('div.flex_btn_wrap.on').closest('div.cour_detail').data('cpnDcCd', cpnSeqno);
				$('div.flex_btn_wrap.on').closest('div.cour_detail').data('cpnDcNm', cpnDcNm);
				$('div.flex_btn_wrap.on').closest('div.cour_detail').data('cpnDcAmt', cpnDcAmt);
				$('div.flex_btn_wrap.on').closest('div.cour_detail').data('cpnDcAmtClCd', cpnDcAmtClCd);
				$('div.flex_btn_wrap.on').closest('div.cour_detail').data('cpnNo', cpnNo);
				if(cpnDcAmtClCd == '01'){
					$('div.flex_btn_wrap.on').closest('div.cour_detail').data('cpnDcRt', cpnDcRt);
				}
			}else{
				$('div.flex_btn_wrap.on').closest('div.cour_detail').removeData("cpnDcCd");
				$('div.flex_btn_wrap.on').closest('div.cour_detail').removeData("cpnDcNm");
				$('div.flex_btn_wrap.on').closest('div.cour_detail').removeData("cpnDcAmt");
				$('div.flex_btn_wrap.on').closest('div.cour_detail').removeData("cpnDcAmtClCd");
				$('div.flex_btn_wrap.on').closest('div.cour_detail').removeData("cpnNo");
				$('div.flex_btn_wrap.on').closest('div.cour_detail').removeData("cpnDcRt");
//				$('div.flex_btn_wrap.on').closest('div.cour_detail').removeAttr('data-cpn-dc-cd');
//				$('div.flex_btn_wrap.on').closest('div.cour_detail').removeAttr('data-cpn-dc-nm');
//				$('div.flex_btn_wrap.on').closest('div.cour_detail').removeAttr('data-cpn-dc-amt');
//				$('div.flex_btn_wrap.on').closest('div.cour_detail').removeAttr('data-cpn-dc-amt-cl-cd');
//				$('div.flex_btn_wrap.on').closest('div.cour_detail').removeAttr('data-cpn-dc-rt');
//				$('div.flex_btn_wrap.on').closest('div.cour_detail').removeAttr('data-cpn-no');
			}
			
			//결제예정금액
			$('div.flex_btn_wrap.on').closest('div.cour_detail').find('ul.txt_wrap').find('li.total_pay div.txt_con div.txt p').text(fnc.fn_numberComma(lectAmt + optnAmt - grdeDcAmt - cpnDcAmt) + '원');
			
			// 총 할인 금액 및 총 결제금액 업데이트
			var totGrdeDcAmt = 0, totCpnDcAmt = 0;
			var arrTotGrdeDc = [], arrTotCpnDc = [];
			$('li.optional').each(function(){
				if($(this).find('div.opt_name').find('p.grdeDc').length > 0){
					totGrdeDcAmt += Number($(this).find('div.opt_name').find('p.grdeDc').data('grdeDcAmt'));
					arrTotGrdeDc.push({
						grdeDcNm : $(this).find('div.opt_name').find('p.grdeDc').data('grdeDcNm')
						, grdeDcAmt : $(this).find('div.opt_name').find('p.grdeDc').data('grdeDcAmt')
					});
				}
				if($(this).find('div.opt_name').find('p.cpnDc').length > 0){
					totCpnDcAmt += Number($(this).find('div.opt_name').find('p.cpnDc').data('cpnDcAmt'));
					arrTotCpnDc.push({
						cpnDcNm : $(this).find('div.opt_name').find('p.cpnDc').data('cpnDcNm')
						, cpnDcAmt : $(this).find('div.opt_name').find('p.cpnDc').data('cpnDcAmt')
					});
				}
			});
			
			// 총 결제금액 - 할인금액 - 상세내역 엘리먼트 그리기
			$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(1) ul.price_wrap li.f_caption2.detail').remove();
			$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(2) ul.price_wrap li.f_caption2.detail').remove();
			$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(3) ul.price_wrap li.f_caption2.detail').remove();
			$('div.white_inner.open_pop').find('div.grde_box').find('div.discount_low').remove();
			$('div.white_inner.open_pop').find('div.cpn_box').find('div.discount_low').remove();
			$('div.white_inner.open_pop').find('div.lpnt_box').find('div.discount_low').remove();
			$('div.white_inner.open_pop').find('div.lpnt_box').hide();
			$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(3)').hide();
			if(totGrdeDcAmt + totCpnDcAmt > 0){
				//$('div.total_price_info').removeClass('no_sale');
				$('div.total_price_info').find('div.price_list.sales').find('p.red_txt.f_body1.price').text('(-) ' + fnc.fn_numberComma(totGrdeDcAmt + totCpnDcAmt) + '원');
				
				if(totGrdeDcAmt > 0){
					$('div.white_inner.open_pop').find('div.grde_box').empty();
					$('div.white_inner.open_pop').find('div.grde_box').show();
					$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(1) ul.price_wrap').empty();
					$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(1)').show();
					for(var i=0;i<arrTotGrdeDc.length;i++){
						if(i == 0){
							$('div.white_inner.open_pop').find('div.grde_box').append(getPcTitleDcTemplateHtml(totGrdeDcAmt, 'grde'));
							$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(1) ul.price_wrap').append(getMblTitleDcTemplateHtml(totGrdeDcAmt, 'grde'));
						}
						$('div.white_inner.open_pop').find('div.grde_box').append(getPcDcTemplateHtml(arrTotGrdeDc[i].grdeDcNm, arrTotGrdeDc[i].grdeDcAmt));
						$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(1) ul.price_wrap').append(getMblDcTemplateHtml(arrTotGrdeDc[i].grdeDcNm, arrTotGrdeDc[i].grdeDcAmt));
					}
				}else{
					$('div.white_inner.open_pop').find('div.grde_box').hide();
					//$('div.white_inner.open_pop').find('div.grde_box').append(getPcDcTemplateHtml('적용 된 할인금액이 없습니다.', '-'));
					$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(1)').hide();
					//$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(1) ul.price_wrap').append(getMblDcTemplateHtml('적용 된 할인금액이 없습니다.', '-'));
				}
				
				if(totCpnDcAmt > 0){
					$('div.white_inner.open_pop').find('div.cpn_box').empty();
					$('div.white_inner.open_pop').find('div.cpn_box').show();
					$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(2) ul.price_wrap').empty();
					$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(2)').show();
					for(var i=0;i<arrTotCpnDc.length;i++){
						if(i == 0){
							$('div.white_inner.open_pop').find('div.cpn_box').append(getPcTitleDcTemplateHtml(totCpnDcAmt, 'cpn'));
							$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(2) ul.price_wrap').append(getMblTitleDcTemplateHtml(totCpnDcAmt, 'cpn'));
						}
						$('div.white_inner.open_pop').find('div.cpn_box').append(getPcDcTemplateHtml(arrTotCpnDc[i].cpnDcNm, arrTotCpnDc[i].cpnDcAmt));
						$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(2) ul.price_wrap').append(getMblDcTemplateHtml(arrTotCpnDc[i].cpnDcNm, arrTotCpnDc[i].cpnDcAmt));
					}
				}else{
					$('div.white_inner.open_pop').find('div.cpn_box').hide();
					//$('div.white_inner.open_pop').find('div.cpn_box').append(getPcDcTemplateHtml('적용 된 할인금액이 없습니다.', '-'));
					$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(2)').hide();
					//$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(2) ul.price_wrap').append(getMblDcTemplateHtml('적용 된 할인금액이 없습니다.', '-'));
				}
				
			}else{
				$('div.white_inner.open_pop').find('div.grde_box').hide();
				$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(1)').hide();
				$('div.white_inner.open_pop').find('div.cpn_box').hide();
				$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(2)').hide();
				
				$('div.total_price_info').find('div.price_list.sales').find('p.red_txt.f_body1.price').text('(-) 0원');
				//$('div.white_inner.open_pop').find('div.grde_box').append(getPcDcTemplateHtml('적용 된 할인금액이 없습니다.', '-', 0, 'grde'));
				//$('div.white_inner.open_pop').find('div.cpn_box').append(getPcDcTemplateHtml('적용 된 할인금액이 없습니다.', '-', 0, 'cpn'));
				//$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(1) ul.price_wrap').append(getMblDcTemplateHtml('적용 된 할인금액이 없습니다.', '-', 0, 'grde'));
				//$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(2) ul.price_wrap').append(getMblDcTemplateHtml('적용 된 할인금액이 없습니다.', '-', 0, 'cpn'));
				
				//$('div.total_price_info').addClass('no_sale');
			}
			
			// 총 할인, 총 쿠폰할인
			$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(1) ul.price_wrap li:nth-child(1) div.price').text(fnc.fn_numberComma(totGrdeDcAmt > 0 ? totGrdeDcAmt : '-') + '원');
			$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(2) ul.price_wrap li:nth-child(1) div.price').text(fnc.fn_numberComma(totCpnDcAmt > 0 ? totCpnDcAmt : '-') + '원');
			
			// 총 결제금액
			var totLectStlmAmt = 0;
			$('div.course_history_w').find('div.cour_his_list').each(function(){
				$(this).find('div.cour_detail').each(function(){
					totLectStlmAmt += Number($(this).find('ul.txt_wrap').find('li.optional').find('a.dcBtn').data('lectAmt'));
					totLectStlmAmt += Number($(this).find('ul.txt_wrap').find('li.optional').find('a.dcBtn').data('optnAmt'));
				})
			});
			
			// 2023-02-14
			if((totGrdeDcAmt + totCpnDcAmt) > 0){
				$('a.sale_drop').removeClass('no_data');
				$('div.drop_type').removeClass('no_data');
				$('a.sale_drop').addClass('drop_down');
				$('a.drop_btn').show();
				$(".pop_drop .drop_down").on("click", function(){
	              if(!$(this).hasClass("no_data")){
	                $(".open_pop").show();
	              }
	            });
			}else{
				$('a.sale_drop').removeClass('drop_down');
				$('a.sale_drop').addClass('no_data');
				$('div.drop_type').addClass('no_data');
				$('a.drop_btn').hide();
			}
			if(!$("#lpntUseAmt").prop('disabled')){
				$("#lpntUseAmt").val('');
			}
			
			totLectStlmAmt -= (totGrdeDcAmt + totCpnDcAmt);
			
			// 0원 결제되면 결제수단 영역 숨김
			if(totLectStlmAmt == 0){
				$('div.mthd_inner').find('div.sub_tit_area').hide();
				$('div.mthd_inner').find('div.payment_methods').hide();
			}else{
				$('div.mthd_inner').find('div.sub_tit_area').show();
				$('div.mthd_inner').find('div.payment_methods').show();
			}
			
			$('#totLectStlmAmt').data('totGrdeDcAmt', totGrdeDcAmt);
			$('#totLectStlmAmt').data('totCpnDcAmt', totCpnDcAmt);
			
			// 할인금액 반영한 총 결제금액 업데이트
			$('div.total_price_info').find('div.total_price').find('div.pay_wrap').html('<p class="pay f_h2">'+ fnc.fn_numberComma(totLectStlmAmt) +'<span class="unit">원</span></p>');
			$('#totLectStlmAmt span').text(fnc.fn_numberComma(totLectStlmAmt) + '원 결제');
			$('#totLectStlmAmt').data('totLectStlmAmt', totLectStlmAmt);
			
			grdeDcAmt = 0;
			cpnDcAmt = 0;
			$('div.flex_btn_wrap.on').removeClass('on');
			fn_fadeOut($('#dcPopup'));
		}
	}
	
	var getPcTitleDcTemplateHtml = function(totAmt, type){
		var pcTemplateHtml = '';
		
		pcTemplateHtml += '<div class="row">';
		pcTemplateHtml += '	<div class="left">';
		if(type == 'grde'){
			pcTemplateHtml += '		<p class="txt f_body4">할인</p>';
		}else if(type == 'cpn'){
			pcTemplateHtml += '		<p class="txt f_body4">쿠폰할인</p>';
		}else if(type == 'lpnt'){
			pcTemplateHtml += '		<p class="txt f_body4">할인혜택</p>';
		}
		pcTemplateHtml += '	</div>';
		pcTemplateHtml += '	<div class="right">';
		if(totAmt == 0){
			pcTemplateHtml += '		<p class="red_txt f_body4">-원</p>';
		}else{
			pcTemplateHtml += '		<p class="red_txt f_body4">(-)'+ fnc.fn_numberComma(totAmt) +'원</p>';
		}
		//pcTemplateHtml += '		<p class="red_txt f_body4">'+ (totAmt == 0 ? '-' : ('(-)' + fnc.fn_numberComma(totAmt))) +'원</p>';
		pcTemplateHtml += '	</div>';
		pcTemplateHtml += '</div>';
		
		return pcTemplateHtml;
	}
	
	// 할인정보 pc html 리턴
	var getPcDcTemplateHtml = function(dcNm, dcAmt){
		var pcTemplateHtml = '';
		
//		pcTemplateHtml += '<div class="row">';
//		pcTemplateHtml += '	<div class="left">';
//		if(type == 'grde'){
//			pcTemplateHtml += '		<p class="txt f_body4">할인</p>';
//		}else if(type == 'cpn'){
//			pcTemplateHtml += '		<p class="txt f_body4">쿠폰할인</p>';
//		}else if(type == 'lpnt'){
//			pcTemplateHtml += '		<p class="txt f_body4">할인혜택</p>';
//		}
//		pcTemplateHtml += '	</div>';
//		pcTemplateHtml += '	<div class="right">';
//		pcTemplateHtml += '		<p class="red_txt f_body4">'+ (totAmt == 0 ? '-' : ('(-)' + fnc.fn_numberComma(totAmt))) +'원</p>';
//		pcTemplateHtml += '	</div>';
//		pcTemplateHtml += '</div>';
		
		
		pcTemplateHtml += '<div class="row stair_low">				';
		pcTemplateHtml += '	<div class="left">				';
		pcTemplateHtml += '		<p class="txt f_caption2">'+ dcNm +'</p>				';
		pcTemplateHtml += '	</div>				';
		pcTemplateHtml += '	<div class="right">				';
		if(dcAmt == 0){
			pcTemplateHtml += '		<p class="txt f_caption2">-원</p>				';
		}else{
			pcTemplateHtml += '		<p class="txt f_caption2">'+ fnc.fn_numberComma(dcAmt) +'원</p>				';
		}
		//pcTemplateHtml += '		<p class="txt f_caption2">'+ (dcAmt == 0 ? '-' : fnc.fn_numberComma(dcAmt)) +'원</p>				';
		pcTemplateHtml += '	</div>				';
		pcTemplateHtml += '</div>				';
		return pcTemplateHtml;
	}
	
	var getMblTitleDcTemplateHtml = function(totAmt, type){
		var mblTemplateHtml = '';
		
		mblTemplateHtml += '<li class="f_body4">';
		if(type == 'grde'){
			mblTemplateHtml += '		<p class="name">할인</p>';
		}else if(type == 'cpn'){
			mblTemplateHtml += '		<p class="name">쿠폰할인</p>';
		}else if(type == 'lpnt'){
			mblTemplateHtml += '		<p class="name">할인혜택</p>';
		}
		
		if(totAmt == 0){
			mblTemplateHtml += '		<div class="red_txt">-원</div>';
		}else{
			mblTemplateHtml += '		<div class="red_txt">(-)'+ fnc.fn_numberComma(totAmt) +'원</div>';
		}
		//mblTemplateHtml += '		<div class="price red_txt">'+ (totAmt == 0 ? '-' : ('(-)' + fnc.fn_numberComma(totAmt))) +'원</div>';
		mblTemplateHtml += '</li>';
		
		return mblTemplateHtml;
	}
	
	// 할인정보 모바일 html 리턴
	var getMblDcTemplateHtml = function(dcNm, dcAmt){
		var mblTemplateHtml = '';
		
//		mblTemplateHtml += '<li class="f_body4">';
//		if(type == 'grde'){
//			mblTemplateHtml += '		<p class="name">할인</p>';
//		}else if(type == 'cpn'){
//			mblTemplateHtml += '		<p class="name">쿠폰할인</p>';
//		}else if(type == 'lpnt'){
//			mblTemplateHtml += '		<p class="name">할인혜택</p>';
//		}
//		
//		mblTemplateHtml += '		<div class="price red_txt">'+ (totAmt == 0 ? '-' : ('(-)' + fnc.fn_numberComma(totAmt))) +'원</div>';
//		console.log(mblTemplateHtml);
//		mblTemplateHtml += '</li>';
		
		mblTemplateHtml += '<li class="f_caption2 detail">';
		mblTemplateHtml += '		<p class="name">'+ dcNm +'</p>';
		if(dcAmt == 0){
			mblTemplateHtml += '		<div class="price">-원</div>';
		}else{
			mblTemplateHtml += '		<div class="price">'+ fnc.fn_numberComma(dcAmt) +'원</div>';
		}
		//mblTemplateHtml += '		<div class="price"> '+ (dcAmt == 0 ? '-' : fnc.fn_numberComma(dcAmt)) +'원</div>';
		mblTemplateHtml += '</li>';
		
		return mblTemplateHtml;
	}
	
	var fn_fadeOut = function(obj){
		if(obj.find(".btn_close").length > 0)
		{
			obj.find(".btn_close").click();
		}
		else
		{
			if(window.innerWidth <= 1024){
				if(obj.find(".pop_wrap").hasClass("full")){
					obj.fadeOut(300, function(){
						$("body").removeClass("stop_scroll");
						$("body, html").off("scroll touchmove mousewheel");
					});
				}else{
					obj.fadeOut(function(){
						$(".m_navi_bar").removeClass("hide")
						$("body").removeClass("stop_scroll");
						$("body, html").off("scroll touchmove mousewheel");
					});
					obj.find(".pop_wrap").css("transform", "translateY(100%)");
				}
			}else{
				obj.fadeOut(300, function(){
					$("body").removeClass("stop_scroll");
		            $("body, html").off("scroll touchmove mousewheel");
				});
			}
		}

	}
	
	// 잔여 lpoint 조회
	var fn_get_remain_lpoint = function(){
		fnc.paramAjax(fn_get_remain_lpoint_callback, "/payment/getRemainLPoint.ajax", {
			brchCd : $('div.course_history_w').data('brchCd')
		}, "json");
	}
	
	// 잔여 lpoint 조회 콜백
	var fn_get_remain_lpoint_callback = function(data){
		console.log(data.rtnData);
		if(data.rtnData.RESULT == "S"){
			var usePoint = data.rtnData.REMAIN_POINT;
			usePoint = Number(usePoint.replace(/(^0+)/, ""));
			
			if(data.rtnData.REMAIN_POINT_SIGN == "-"){
				//$('div.payment_methods').find('p.remaining').text('잔여 : -' + fnc.fn_numberComma(usePoint) + '점');
				$('div.payment_methods').find('span.remaining').text(fnc.fn_numberComma(usePoint));
			}else{
				//$('div.payment_methods').find('p.remaining').text('잔여 : ' + fnc.fn_numberComma(usePoint) + '점');
				$('div.payment_methods').find('span.remaining').text(fnc.fn_numberComma(usePoint));
			}
			
			$("#lpntUseAmt").prop("disabled", false);
			$('div.payment_methods').find('.point_txt_area').find("a").hide().end().find("p").show();
			
			//$('div.payment_methods').find('p.remaining').data('usePoint', usePoint);
			$('div.payment_methods').find('span.remaining').data('usePoint', usePoint);
			$('#lpointOnlnCertPopup').data('usePointYn', 'Y');
		}else{
			alert("L.POINT 조회에 실패했습니다.");
		}
	}
	
	// lpoint 온라인 사용인증 서비스 팝업
	var fn_open_lpoint_popup = function(){
		var onlnCertYn = $('#lpointOnlnCertPopup').data('onlnCertYn');
		if(onlnCertYn == 'N'){
			commonScript.openPopupFn("#lpointOnlnCertPopup", $("#calcBtn"));
//			$('#lpointOnlnCertPopup').fadeIn();
//			popupResize();
		}
	}
	
	// lpoint 온라인 사용인증 비밀번호 체크
	var fn_validate_lpoint_pwd = function(obj){
		if($('#lpointOnlnCertPopup').find('#strPasswd').val() == ""){
			alert("L.POINT 비밀번호를 입력해 주세요.");
			$('#lpointOnlnCertPopup').find('#strPasswd').focus();
			return;
		}
		fnc.paramAjax(fn_validate_lpoint_pwd_callback, "/payment/validateLpointPwd.ajax", {
			brchCd : $('div.course_history_w').data('brchCd')
			, strPasswd : $('#lpointOnlnCertPopup').find('#strPasswd').val()
		}, "json");
	}
	
	// lpoint 온라인 사용인증 비밀번호 체크 콜백
	var fn_validate_lpoint_pwd_callback = function(data){
		if(data.rtnData.RESULT == "S"){
			alert("확인 되었습니다.");
			$('#lpointOnlnCertPopup').data('onlnCertYn', 'Y');
			$('#lpointOnlnCertPopup').data('strPasswd', data.rtnData.strPasswd);
			$('#lpointOnlnCertPopup').data('strCardNo', data.rtnData.strCardNo);
			$('#lpointOnlnCertPopup').data('brchCd', data.rtnData.brchCd);
			$('#lpointOnlnCertPopup').data('strCoprMemstrNo', data.rtnData.strCoprMemstrNo);
			
			fn_calcPay();
			fn_fadeOut($('#lpointOnlnCertPopup'));
		}else if(data.rtnData.RESULT == "F"){
			alert(data.rtnData.result_msg);
		}
	}
	
	// 사용할 lpoint 입력 이벤트
	 var fn_input_lpoint = function(obj){
		 if($('#lpointOnlnCertPopup').data('usePointYn') == 'N'){
			alert('L.POINT 조회 후 사용 가능합니다.');
			$(obj).val('');
		 }else{
			 //var usePoint = Number($('div.payment_methods').find('p.remaining').data('usePoint'));
			 var usePoint = Number($('div.payment_methods').find('span.remaining').data('usePoint', usePoint));
			 
			 obj.value = fnc.fn_numberComma(obj.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1'));
			 
			 var inputPoint = Number(obj.value.replace(/,/gi, ''));
			 if(inputPoint > usePoint){
				 alert('사용 가능 포인트를 확인하시기 바랍니다.');
				 $(obj).val('');
				 return;
			 }
		 }
	 }
	 
	 // lpoint 입력 필드 포커스아웃 이벤트
	 var fn_focusout_lpoint = function(obj){
		 obj.value = fnc.fn_numberComma(obj.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1'));
		 
		 var inputPoint = Number(obj.value.replace(/,/gi, ''));
		 if(inputPoint.toString().length > 1 && inputPoint % 10 != 0){
			 alert("포인트 사용은 10점 단위로 사용 가능합니다.");
			 $(obj).val('');
			 $(obj).focus();
			 return;
		 }
	 }
	 
	 // lpoint 계산
	 var fn_calcPay = function() {
		 if($('#lpointOnlnCertPopup').data('usePointYn') == 'N'){
			alert('L.POINT 조회 후 사용 가능합니다.');
		} else {
			fn_open_lpoint_popup();
			var onlnCertYn = $('#lpointOnlnCertPopup').data('onlnCertYn');
			if(onlnCertYn == 'Y'){
				// 할인금액 반영한 총 결제금액 업데이트
				var totLectStlmAmt = Number($('#totLectStlmAmt').data('totLectStlmAmt'));
				var inputPoint = Number($("#lpntUseAmt").val().replace(/,/gi, ''));
				
				if(inputPoint > totLectStlmAmt){
					inputPoint = totLectStlmAmt;
					$("#lpntUseAmt").val(fnc.fn_numberComma(inputPoint));
				}
				 //var totLectStlmAmt = Number($('#totLectStlmAmt').data('totLectStlmAmt'));
				 totLectStlmAmt -= inputPoint;
				 if(totLectStlmAmt < 0){
					 totLectStlmAmt = 0;
				 }
				 
				// 0원 결제되면 결제수단 영역 숨김
				if(totLectStlmAmt == 0){
					$('div.mthd_inner').find('div.sub_tit_area').hide();
					$('div.mthd_inner').find('div.payment_methods').hide();
				}else{
					$('div.mthd_inner').find('div.sub_tit_area').show();
					$('div.mthd_inner').find('div.payment_methods').show();
				}
				 
				 $('div.total_price_info').find('div.total_price').find('div.pay_wrap').html('<p class="pay f_h2">'+ fnc.fn_numberComma(totLectStlmAmt) +'<span class="unit">원</span></p>');
				 $('#totLectStlmAmt span').text(fnc.fn_numberComma(totLectStlmAmt) + "원 결제");
				 $('#totLectStlmAmt').data('totLectStlmAmt', totLectStlmAmt);
				 $('#totLectStlmAmt').data('lpntUseAmt', inputPoint);
				 
				// lpoint 상세내역 엘리먼트 그리기
				var totGrdeDcAmt = 0, totCpnDcAmt = 0;
				var arrTotGrdeDc = [], arrTotCpnDc = [];
				$('li.optional').each(function(){
					if($(this).find('div.opt_name').find('p.grdeDc').length > 0){
						totGrdeDcAmt += Number($(this).find('div.opt_name').find('p.grdeDc').data('grdeDcAmt'));
					}
					if($(this).find('div.opt_name').find('p.cpnDc').length > 0){
						totCpnDcAmt += Number($(this).find('div.opt_name').find('p.cpnDc').data('cpnDcAmt'));
					}
				});
				
				if(totGrdeDcAmt == 0){
					$('div.white_inner.open_pop').find('div.grde_box').hide();
					$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(1)').hide();
				}
				if(totCpnDcAmt == 0){
					$('div.white_inner.open_pop').find('div.cpn_box').hide();
					$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(2)').hide();
				}
				$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(3) ul.price_wrap li.f_caption2.detail').remove();
				$('div.white_inner.open_pop').find('div.lpnt_box').find('div.discount_low').remove();
				if(inputPoint > 0){
					$('div.total_price_info').find('div.price_list.sales').find('p.red_txt.f_body1.price').text('(-) ' + fnc.fn_numberComma(totGrdeDcAmt + totCpnDcAmt + inputPoint) + '원');
					$('div.white_inner.open_pop').find('div.lpnt_box').empty();
					$('div.white_inner.open_pop').find('div.lpnt_box').show();
					$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(3) ul.price_wrap').empty();
					$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(3)').show();
					
					$('div.white_inner.open_pop').find('div.lpnt_box').append(getPcTitleDcTemplateHtml(inputPoint, 'lpnt'));
					$('div.white_inner.open_pop').find('div.lpnt_box').append(getPcDcTemplateHtml('L.POINT 할인', inputPoint));
					$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(3) ul.price_wrap').append(getMblTitleDcTemplateHtml(inputPoint, 'lpnt'));
					$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(3) ul.price_wrap').append(getMblDcTemplateHtml('L.POINT 할인', inputPoint));
					
					$('a.sale_drop').removeClass('no_data');
					$('a.sale_drop').addClass('drop_down');
					$('div.drop_type').removeClass('no_data');
					$(".pop_drop .drop_down").on("click", function(){
		              if(!$(this).hasClass("no_data")){
		                $(".open_pop").show();
		              }
		            });
				}else{
					//$('div.white_inner.open_pop').find('div.lpnt_box').append(getPcDcTemplateHtml('적용 된 할인금액이 없습니다.', '-', 0, 'lpnt'));
					//$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(3) ul.price_wrap').append(getMblDcTemplateHtml('적용 된 할인금액이 없습니다.', '-', 0, 'lpnt'));
					$('div.white_inner.open_pop').find('div.lpnt_box').hide();
					$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(3)').hide();
					
					if(totGrdeDcAmt + totCpnDcAmt == 0){
						$('a.sale_drop').removeClass('drop_down');
						$('a.sale_drop').addClass('no_data');
						$('div.drop_type').addClass('no_data');
					}
				}
				
				// lpoint
				$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(3) ul.price_wrap li:nth-child(1) div.price').text(fnc.fn_numberComma(inputPoint > 0 ? inputPoint : '-') + '원');
				
				$('#calcBtn').hide();
				$('#cnclBtn').show();
				$("#lpntUseAmt").prop('disabled', true);
			}
		}
	 }
	 
	 // lpoint 사용 취소
	 var fn_cnclPay = function(){
		 var totLectStlmAmt = Number($('#totLectStlmAmt').data('totLectStlmAmt'));
		 var inputPoint = Number($("#lpntUseAmt").val().replace(/,/gi, ''));
		 totLectStlmAmt += inputPoint;
		 
		 $("#lpntUseAmt").val("0");
		 
		// 0원 결제되면 결제수단 영역 숨김
		if(totLectStlmAmt == 0){
			$('div.mthd_inner').find('div.sub_tit_area').hide();
			$('div.mthd_inner').find('div.payment_methods').hide();
		}else{
			$('div.mthd_inner').find('div.sub_tit_area').show();
			$('div.mthd_inner').find('div.payment_methods').show();
		}
		
		$('div.total_price_info').find('div.total_price').find('div.pay_wrap').html('<p class="pay f_h2">'+ fnc.fn_numberComma(totLectStlmAmt) +'<span class="unit">원</span></p>');
		 $('#totLectStlmAmt span').text(fnc.fn_numberComma(totLectStlmAmt) + "원 결제");
		 $('#totLectStlmAmt').data('totLectStlmAmt', totLectStlmAmt);
		 $('#totLectStlmAmt').data('lpntUseAmt', 0);
		 
		 var totGrdeDcAmt = 0, totCpnDcAmt = 0;
		var arrTotGrdeDc = [], arrTotCpnDc = [];
		$('li.optional').each(function(){
			if($(this).find('div.opt_name').find('p.grdeDc').length > 0){
				totGrdeDcAmt += Number($(this).find('div.opt_name').find('p.grdeDc').data('grdeDcAmt'));
			}
			if($(this).find('div.opt_name').find('p.cpnDc').length > 0){
				totCpnDcAmt += Number($(this).find('div.opt_name').find('p.cpnDc').data('cpnDcAmt'));
			}
		});
		$('div.total_price_info').find('div.price_list.sales').find('p.red_txt.f_body1.price').text('(-) ' + fnc.fn_numberComma(totGrdeDcAmt + totCpnDcAmt) + '원');
		 
		 // LPOINT 엘리먼트 숨기기
		 $('div.white_inner.open_pop').find('div.lpnt_box').hide();
		$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(3)').hide();
		
		$('div.total_price_info').find('div.price_list.sales').find('div.hide_con_w').find('div.hide_con:nth-child(3) ul.price_wrap li:nth-child(1) div.price').text('-원');
		
		if(totGrdeDcAmt + totCpnDcAmt == 0){
			$('a.sale_drop').removeClass('drop_down');
			$('a.sale_drop').addClass('no_data');
			$('div.drop_type').addClass('no_data');
		}
		 
		 $('#cnclBtn').hide();
		 $('#calcBtn').show();
		 $("#lpntUseAmt").prop('disabled', false);
		 //$("#lpntUseAmt").val('');
	 }
	 
	 // step2 -> 결제
	 var fn_submit_step2_frm = function(){
		 var paymentObject = {};
		 var arrLect = [];
		if($('#argee1').prop('checked')){
			// 2023.02.28 엘포인트 사용 체크 
			if($('#calcBtn:visible').length > 0 && Number($('#lpntUseAmt').val().replace(/,/gi, '')) > 0){
				alert("엘포인트 사용하기 버튼을 눌러주세요.");
				return;
			}else{
				if(confirm("결제를 진행하시겠습니까?")){
					$('div.course_history_w').find('div.cour_his_list').each(function(){
						var lectObj = {};
						lectObj.atlctRsvNo = $(this).data('atlctRsvNo');
						lectObj.brchCd = $(this).data('brchCd');
						lectObj.yy = $(this).data('yy');
						lectObj.lectSmsterCd = $(this).data('lectSmsterCd');
						lectObj.lectCd = $(this).data('lectCd');
						lectObj.lectTpCd = $(this).data('lectTpCd');
						
						// 공동모객
						if($(this).data('lectTpCd') == "04" && $(this).data('pblPmprcustParntBrchCd') != ""){
							lectObj.brchCd = $(this).data('pblPmprcustParntBrchCd');
							lectObj.lectCd = $(this).data('pblPmprcustParntLectCd');
							lectObj.pblPmprcustBrchCd = $(this).data('brchCd');
							lectObj.pblPmprcustLectCd = $(this).data('lectCd');
							
							
							
//							lectObj.pblPmprcustBrchCd = $(this).data('pblPmprcustBrchCd');
//							lectObj.pblPmprcustLectCd = $(this).data('pblPmprcustLectCd');
//							lectObj.pblPmprcustBrchCdNm = $(this).data('pblPmprcustBrchCdNm');
						}
						
//						lectObj.masterBrchCd = $(this).data('masterBrchCd');
//						lectObj.masterYy = $(this).data('masterYy');
//						lectObj.masterLectSmsterCd = $(this).data('masterLectSmsterCd');
//						lectObj.masterLectCd = $(this).data('masterLectCd');
						
						lectObj.optnSeqno = $(this).data('optnSeqno');
						
						var arrActlAtlctNple = [];
						$(this).find('div.cour_detail').each(function(){
							var actlAtlctNpleObj = {};
							actlAtlctNpleObj.actlAtlctNpleSeqno = $(this).data('actlAtlctNpleSeqno');
							actlAtlctNpleObj.actlAtlctNpleNm = $(this).data('actlAtlctNpleNm');
							actlAtlctNpleObj.fmlyRelCd = $(this).data('fmlyRelCd');
							actlAtlctNpleObj.fmlyRelCdNm = $(this).data('fmlyRelCdNm');
							actlAtlctNpleObj.bday = $(this).data('bday');
							actlAtlctNpleObj.sexCd = $(this).data('sexCd');
							if(typeof $(this).data('grdeDcCd') != 'undefined'){
								actlAtlctNpleObj.grdeDcCd = $(this).data('grdeDcCd');
							}
							if(typeof $(this).data('grdeDcNm') != 'undefined'){
								actlAtlctNpleObj.grdeDcNm = $(this).data('grdeDcNm');
							}
							
							actlAtlctNpleObj.grdeDcAmt = typeof $(this).data('grdeDcAmt') != 'undefined' ? $(this).data('grdeDcAmt') : 0;
							
							if(typeof $(this).data('grdeDcRt') != 'undefined'){
								actlAtlctNpleObj.grdeDcRt = $(this).data('grdeDcRt');
							}
							if(typeof $(this).data('cpnDcCd') != 'undefined'){
								actlAtlctNpleObj.cpnDcCd = $(this).data('cpnDcCd');
							}
							if(typeof $(this).data('cpnDcNm') != 'undefined'){
								actlAtlctNpleObj.cpnDcNm = $(this).data('cpnDcNm');
							}
							actlAtlctNpleObj.cpnDcAmt = typeof $(this).data('cpnDcAmt') != 'undefined' ? $(this).data('cpnDcAmt') : 0;
							
							if(typeof $(this).data('cpnDcAmtClCd') != 'undefined'){
								actlAtlctNpleObj.cpnDcAmtClCd = $(this).data('cpnDcAmtClCd');
							}
							if(typeof $(this).data('cpnDcRt') != 'undefined'){
								actlAtlctNpleObj.cpnDcRt = $(this).data('cpnDcRt');
							}
							
							if(typeof $(this).data('cpnNo') != 'undefined'){
								actlAtlctNpleObj.cpnNo = $(this).data('cpnNo');
							}
							arrActlAtlctNple.push(actlAtlctNpleObj);
						});
						
						lectObj.arrActlAtlctNple = arrActlAtlctNple;
						
						arrLect.push(lectObj);
					});
					
					paymentObject.arrLect = arrLect;
					paymentObject.totLectStlmAmt = Number($('#totLectStlmAmt').data('totLectStlmAmt'));
					if($('#calcBtn:visible').length == 0){
						paymentObject.lpntUseAmt = Number($('#lpntUseAmt').val().replace(/,/gi, ''));
					}else{
						paymentObject.lpntUseAmt = 0;
					}
					paymentObject.totGrdeDcAmt = Number($('#totLectStlmAmt').data('totGrdeDcAmt'));
					paymentObject.totCpnDcAmt = Number($('#totLectStlmAmt').data('totCpnDcAmt')); 
					paymentObject.gsLectAmt = Number($('#totLectStlmAmt').data('gsLectAmt'));
					paymentObject.gsAddAmt = Number($('#totLectStlmAmt').data('gsAddAmt'));
					paymentObject.gsDcAmt = paymentObject.totGrdeDcAmt + paymentObject.totCpnDcAmt;
					paymentObject.gsStlmAmt = paymentObject.gsLectAmt + paymentObject.gsAddAmt - paymentObject.gsDcAmt;
					paymentObject.crdStlmAmt = paymentObject.gsLectAmt + paymentObject.gsAddAmt - paymentObject.gsDcAmt - paymentObject.lpntUseAmt;
					if(paymentObject.lpntUseAmt > 0){
						paymentObject.strPasswd = $('#lpointOnlnCertPopup').data('strPasswd');
						paymentObject.strCardNo = $('#lpointOnlnCertPopup').data('strCardNo');
						paymentObject.brchCd = $('#lpointOnlnCertPopup').data('brchCd');
						paymentObject.strCoprMemstrNo = $('#lpointOnlnCertPopup').data('strCoprMemstrNo');
					}
					
					// 대기신청과 일반 구분
					paymentObject.atlctType = $("div.course_history_w").data('atlctType');
					
					if(paymentObject.lpntUseAmt % 10 != 0){
						alert("L.POINT의 최소사용 단위는 10원입니다.");
					}else{
						fnc.paramAjax(fn_validate_step2_callback, "/payment/validateStep2.ajax", {
							jsonStr : JSON.stringify(paymentObject)
						}, "json");
					}
				}
			}
		}else{
			if(confirm('구매동의에 동의하셔야 결제가 가능합니다. 구매 동의하시겠습니까?')){
				$('#argee1').prop('checked', true);
				$('#argeeBtn1').prop('checked', true);
			}
		}
		
	 }
	 
	 // step2 -> 결제 콜백
	 var submitFlag = false;
	 var fn_validate_step2_callback = function(data){
		$('#frm_success').find('input[name=atlctRsvNo]').val('');
		var rtnMap = data.rtnMap;
		if(rtnMap.rsltCd == "-1"){
			alert("수강결제는 접수중인 강좌만 가능합니다.\n선택한 강좌를 다시 한번 확인하세요.");
		}else if(rtnMap.rsltCd == "-2"){
			alert("강좌명 : " + fnc.returnHtml(rtnMap.lectNm) + "\n현재 수강 가능인원은 " + rtnMap.capaCnt + "명입니다.");
		}else if(rtnMap.rsltCd == "-3"){
			alert("강좌명 : " + fnc.returnHtml(rtnMap.atlctYnMap.lectNm) + "\n수강자 : " + rtnMap.atlctYnMap.actlAtlctNpleNm + "\n강좌는 이미 수강신청한 강좌입니다.");
		}else if(rtnMap.rsltCd == "-4"){
			alert('AVENUEL ORANGE 등급 이상,  AVENUEL 소속점과 관심지점이 동일한 경우에만 결제 가능합니다.');
		}else if(rtnMap.rsltCd == "-5"){
			alert("총 결제금액이 일치하지 않습니다.");
		}else{
			// 중복클릭 방지
			if(submitFlag){
				return;
			}
			submitFlag = true;
			
			
			if(rtnMap.crdStlmAmt > 0){
				$('#pgPopup').find('div.pay_img').empty();
				var iframe = "<iframe id='pgIframe' name='pgIframe' style='width:100%;height:500px;'></iframe>";
				$('#pgPopup').find('div.pay_img').append(iframe);
				
				$('#frm_temp').find('input[name=atlctRsvNo]').val(rtnMap.atlctRsvNo);
				$('#frm_temp').find('input[name=lpntUseAmt]').val(rtnMap.lpntUseAmt);
				$('#frm_temp').find('input[name=strPasswd]').val(rtnMap.strPasswd);
				$('#frm_temp').find('input[name=strCardNo]').val(rtnMap.strCardNo);
				$('#frm_temp').find('input[name=brchCd]').val(rtnMap.brchCd);
				$('#frm_temp').find('input[name=strCoprMemstrNo]').val(rtnMap.strCoprMemstrNo);
				$('#frm_temp').find('input[name=crdStlmAmt]').val(rtnMap.crdStlmAmt);
				$('#frm_temp').find('input[name=goodsName]').val(rtnMap.goodsName);
				$('#frm_temp').find('input[name=merchantID]').val(rtnMap.merchantID);
				$('#frm_temp').find('input[name=moid]').val(rtnMap.moid);
				$('#frm_temp').find('input[name=buyerName]').val(rtnMap.buyerName);
				$('#frm_temp').find('input[name=pgMctKeyVal]').val(rtnMap.pgMctKeyVal);
				$('#frm_temp').find('input[name=mbrNo]').val(rtnMap.mbrNo);
				$('#frm_temp').find('input[name=mbrGrdeCd]').val(rtnMap.mbrGrdeCd);
				$('#frm_temp').find('input[name=mvgBlstrCd]').val(rtnMap.mvgBlstrCd);
				$('#frm_temp').find('input[name=mbrId]').val(rtnMap.mbrId);
				$('#frm_temp').submit();
				
				$('#frm_success').find('input[name=atlctRsvNo]').val(rtnMap.atlctRsvNo);
				
				if(checkPlatform(window.navigator.userAgent) == "mobile"){
					commonScript.openPopupFn("#pgPopup", $(this));
//					$('#pgPopup').fadeIn();
//					popupResize();
				}
			}else{
				// 0원결제
				$('#frm_zeropay').find('input[name=atlctRsvNo]').val(rtnMap.atlctRsvNo);
				$('#frm_zeropay').find('input[name=lpntUseAmt]').val(rtnMap.lpntUseAmt);
				$('#frm_zeropay').find('input[name=strPasswd]').val(rtnMap.strPasswd);
				$('#frm_zeropay').find('input[name=strCardNo]').val(rtnMap.strCardNo);
				$('#frm_zeropay').find('input[name=brchCd]').val(rtnMap.brchCd);
				$('#frm_zeropay').find('input[name=strCoprMemstrNo]').val(rtnMap.strCoprMemstrNo);
				$('#frm_zeropay').find('input[name=gsStlmAmt]').val(rtnMap.gsStlmAmt);
				$('#frm_zeropay').submit();
			}
			
			submitFlag = false;
		}
	 }
	 
	 // step2 -> step1
	 var fn_move_step1 = function(){
		 var arrBrchCd = [], arrYy = [], arrLectSmsterCd = [], arrLectCd = [], arrOptnSeqno = [], arrOptnUseYn = [];
		 $('#frm_prev').find('input[name^=brchCd]').each(function(){
			 arrBrchCd.push($(this).val());
		 });
		 $('#frm_prev').find('input[name^=yy]').each(function(){
			 arrYy.push($(this).val());
		 });
		 $('#frm_prev').find('input[name^=lectSmsterCd]').each(function(){
			 arrLectSmsterCd.push($(this).val());
		 });
		 $('#frm_prev').find('input[name^=lectCd]').each(function(){
			 arrLectCd.push($(this).val());
		 });
		 $('#frm_prev').find('input[name^=optnSeqno]').each(function(){
			 arrOptnSeqno.push($(this).val());
		 });
		 $('#frm_prev').find('input[name^=optnUseYn]').each(function(){
			 arrOptnUseYn.push($(this).val());
		 });
		 
		 $('#frm_prev').find('input[name^=brchCd]').remove();
		 $('#frm_prev').find('input[name^=yy]').remove();
		 $('#frm_prev').find('input[name^=lectSmsterCd]').remove();
		 $('#frm_prev').find('input[name^=lectCd]').remove();
		 $('#frm_prev').find('input[name^=optnSeqno]').remove();
		 $('#frm_prev').find('input[name^=optnUseYn]').remove();
		 
		 $('#frm_prev').append($('<input/>', {type: 'hidden', name: 'brchCd', value:arrBrchCd.join() }));
		 $('#frm_prev').append($('<input/>', {type: 'hidden', name: 'yy', value:arrYy.join() }));
		 $('#frm_prev').append($('<input/>', {type: 'hidden', name: 'lectSmsterCd', value:arrLectSmsterCd.join() }));
		 $('#frm_prev').append($('<input/>', {type: 'hidden', name: 'lectCd', value:arrLectCd.join() }));
		 $('#frm_prev').append($('<input/>', {type: 'hidden', name: 'optnSeqno', value:arrOptnSeqno.join() }));
		 $('#frm_prev').append($('<input/>', {type: 'hidden', name: 'optnUseYn', value:arrOptnUseYn.join() }));
		 
		 $('#frm_prev').submit();
	 }
	 
	// 결제중단
	 var fn_nicepayClose = function(atlctRsvNo){
		 // 2023.04.27 모바일 결제중단
		 if(typeof atlctRsvNo === "undefined"){
			 atlctRsvNo = $('#frm_temp').find('input[name=atlctRsvNo]').val();
		 }
		 fnc.paramAjax(function(data) {
				alert('결제가 취소 되었습니다.');
			}, "/payment/payment_close.ajax", {
			atlctRsvNo : atlctRsvNo
		}, "json");
	 }
	 
	 // 약관 팝업
	 var fn_open_terms_popup = function(obj){
		 commonScript.openPopupFn('#termsPopup', $(this));
	 }
	
	 return {
		 openFmlyPopup : fn_open_fmly_popup
		 , closePopup : fn_close_popup
		 , updateActlAtlctNple : fn_update_actl_atlct_nple
		 , deleteActlAtlctNple : fn_delete_actl_atlct_nple
		 , deleteFmly : fn_delete_fmly
		 , addChild : fn_add_child
		 , submitStep1Frm : fn_submit_step1_frm
		 , openDcPopup : fn_open_dc_popup
		 , selectGrdeDc : fn_select_grdeDc
		 , selectCpnDc : fn_select_cpnDc
		 , applDc : fn_appl_dc
		 , getRemainLPoint : fn_get_remain_lpoint
		 , openLPointPopup : fn_open_lpoint_popup
		 , validateLPointPwd : fn_validate_lpoint_pwd
		 , inputLpoint : fn_input_lpoint
		 , submitStep2Frm : fn_submit_step2_frm
		 , focusoutLpoint : fn_focusout_lpoint
		 , calcPay : fn_calcPay
		 , moveStep1 : fn_move_step1
		 , cnclPay : fn_cnclPay
		 , nicepayClose : fn_nicepayClose
		 , openTermsPopup : fn_open_terms_popup
		 , payment_tot : payment_tot
	 }
}());

//뒤로가기 버튼 처리

window.onpageshow = function(event) {
    if ( event.persisted || (window.performance && window.performance.navigation.type == 2)) {
    	// step3 -> step2로 갈때만 메인으로 팅김
    	if(window.location.pathname.indexOf("/step2.do") > -1){
    		location.replace("/");
    	}
    }
}
