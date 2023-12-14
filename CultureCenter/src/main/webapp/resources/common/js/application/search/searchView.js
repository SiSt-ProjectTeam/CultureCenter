var search = (function(){
	
	"use strict";
	var searchMore = null;
	
	//다회차,순회 강좌 선택
	var classInfoSet = function(brchCd, yy, lectSmsterCd, lectCd, close) {
		$("#brchCd").val(brchCd);
		$("#yy").val(yy);
		$("#lectSmsterCd").val(lectSmsterCd);
		$("#lectCd").val(lectCd);
		$("#optnSeqno").val("");
		$("#waitPopup .optionAmt").text("");
		
		fnc.frmAjax(function(data) {
			var classDtl = data.classDtl;
			var teacherDtl = data.teacherDtl;
			var addText = "";
			
			$("#lectStatCd").val(classDtl.lectStatCd);
			$("#optnTypNm").val(classDtl.optnTypNm);
			$("#optnUseYn").val(classDtl.optnUseYn);
			
			if(classDtl.lectStatCd == "02" || classDtl.lectStatCd == "2") {
				$(".total_sum_area .cart_btn").show();
			} else {
				$(".total_sum_area .cart_btn").hide();
			}
			
			$(".lectStatNm").text(classDtl.lectStatNm);
			$(".lectStatNm").attr("class", "label large lectStatNm");
			var className = "";
			if(classDtl.lectStatCd == "01") {
				className = "black";
			} else if(classDtl.lectStatCd == "02" || classDtl.lectStatCd == "2") {
				className = "lime";
			} else if(classDtl.lectStatCd == "04" || classDtl.lectStatCd == "4") {
				className = "gray";
			} else {
				className = "light_gray";
			}
			$(".lectStatNm").addClass(className)
			
			$(".lectClNm").text(classDtl.lectClNm);
			
			var webPath = "";
			var realFileNm = "";
			if(fnc.nvl(classDtl.webPath, "") != "") {
				webPath = fnc.nvl(classDtl.webPath, "");
				realFileNm = fnc.nvl(classDtl.realFileNm, "");
			} else if (fnc.nvl(classDtl.webPath2, "") != "") {
				webPath = fnc.nvl(classDtl.webPath2, "");
				realFileNm = fnc.nvl(classDtl.realFileNm2, "");
			} else {
				webPath = fnc.nvl(classDtl.webPath3, "");
				realFileNm = fnc.nvl(classDtl.realFileNm3, "");
			}
			
			$(".webPath").attr("src", fnc.nvl(webPath, ""));
			$(".webPath").attr("alt", fnc.nvl(realFileNm, ""));
			$(".lectNm").html(classDtl.lectNm);
			$(".lectExpl").html(fnc.nvl(classDtl.lectExpl, ""));
			$(".brchNm").text(classDtl.brchNm);
			$(".yy").text(classDtl.yy + "년 " + classDtl.lectSmsterNm );
			$(".lectSmsterNm").text(classDtl.lectSmsterNm );
			$(".tcNm").text(fnc.returnHtml(classDtl.tcNm));
			$(".lectStDtm").text(classDtl.lectStDtm + " ~ " + classDtl.lectEndDtm);
			
			var lectTimeArr = "";
			classDtl.stringStDaywNm.split(",").forEach(function(v) {
				lectTimeArr += "<p>(" + v + ") " + classDtl.lectSt + "~" + classDtl.lectEnd + "</p>";
			});
			$(".lectTime").html(lectTimeArr);
			
			var addLectText = "";
			if(fnc.nvl(classDtl.lectAddOptnNm, "") != "") {
				addLectText = ', ' + classDtl.lectAddOptnNm;
			}
			
			$(".lectSt").text(classDtl.stDaywNm + " " + classDtl.lectSt + "~" + classDtl.lectEnd + ", 총" + classDtl.lectNbcnt + "회" + addLectText);
			$(".lectNbcnt").text(classDtl.lectNbcnt + "회/" + classDtl.gsCapaCnt + "명");
			$(".lectRmNm").text(classDtl.lectRmNm);
			
			if(classDtl.lectAmt == 0) {
				$(".lectAmt").text("무료");
				$(".lectAmt2").text("강좌료 0원");
			} else {
				$(".lectAmt").text(fnc.fn_numberComma(classDtl.lectAmt)+"원");
				$(".lectAmt2").text("강좌료 " + fnc.fn_numberComma(classDtl.lectAmt)+"원");
			}
			
			$(".objClNm").text(classDtl.objClNm);
			$(".rceptPrdStDt").text(classDtl.rceptPrdStDt + "~" + classDtl.rceptPrdEndDt);
			$(".telno").text(fnc.nvl(classDtl.telno, ""));
			
			var classText = "";
			if(classDtl.lectStatCd == "02" || classDtl.lectStatCd == "2") {
				addText = "수강 신청하기";
				classText = "sign_btn b_color_btn";
				$(".course_popup .total_sum_area .sign_btn span").text("수강신청");
			} else if(classDtl.lectStatCd == "4") {
				addText = "대기 신청하기";
				classText = "sign_btn border_btn"
				$(".course_popup .total_sum_area .sign_btn span").text("대기신청");
			} else if(classDtl.lectStatCd == "06") {
				addText = "접수마감";
				classText = "sign_btn b_color_btn disabled"
			} else if(classDtl.lectStatCd == "04") {
				addText = "지점문의";
				classText = "sign_btn b_color_btn disabled"
			}
			
			if(classText == "") {
				$(".lectStatCd").closest(".fixed_btn_area").hide();
			} else {
				$(".lectStatCd").text(addText);
				$(".lectStatCd").closest("a").attr("class", classText).closest(".fixed_btn_area").show();
			}
			if(classDtl.optnUseYn == "Y") {
				$("#optionArea").show();
			} else {
				$("#optionArea").hide();
			}
			$("#optionArea .btn_open span").text("옵션선택");
			$("#optionArea .scroll_wrap a:eq(0)").siblings("a").remove();
			
			var optionText = "";
			$(".course_popup .selected_box:eq(0) .content_txt").empty();
			optionText += '<p class="name">' + classDtl.lectNm + '</p>';
			optionText += '<div class="option_div">';
			
			if($("#groupLectTpCd").val() == "02") {
				optionText += '<span>' + classDtl.brchNm + '</span>';
				optionText += '<span>' + classDtl.lectStDtm.split(".")[1] + '/' + classDtl.lectStDtm.split(".")[2] + '(' + classDtl.stDaywNm + ')</span>';
				optionText += '<span>' + classDtl.lectSt + '~' + classDtl.lectEnd + '</span>';
			} else if($("#groupLectTpCd").val() == "03") {
				if($("#groupLectClCd").val() == "3") {
					optionText += '<span>' + classDtl.lectStDtm.split(".")[1] + '/' + classDtl.lectStDtm.split(".")[2] + '(' + classDtl.stDaywNm + ')</span>';
					optionText += '<span>' + classDtl.lectSt + '~' + classDtl.lectEnd + '</span>';
				} else {
					optionText += '<span>' + classDtl.stringStDaywNm + '</span>';
					optionText += '<span>' + classDtl.lectSt + '~' + classDtl.lectEnd + '</span>';
					optionText += '<span>' + classDtl.lectNbcnt + '회</span>';
				}
			}
			
			if(fnc.nvl(classDtl.lectAddOptnNm, "") != "") {
				optionText += '<span>' + classDtl.lectAddOptnNm + '</span>';
			}
			optionText += '</div>';
			$(".course_popup .selected_box:eq(0) .content_txt").html(optionText);
			
			var optionHtml = "";
			classDtl.optionList.forEach(function(ele) {
				if(ele.optnAmt == 0) {
					optionHtml += '<a class="btn_link" data-optn-seqno="' + ele.optnSeqno + '" href="javascript:search.optionSet(' + "'" + ele.optnNm + "','" + ele.optnAmt + "','" + ele.optnSeqno + "'" + ');"><span>' + ele.optnNm + '</span></a>';
				} else {
					optionHtml += '<a class="btn_link" data-optn-seqno="' + ele.optnSeqno + '" href="javascript:search.optionSet(' + "'" + ele.optnNm + "','" + ele.optnAmt + "','" + ele.optnSeqno + "'" + ');"><span>' + ele.optnNm + ' (+' + fnc.fn_numberComma(ele.optnAmt) + '원)</span></a>';
				}
			});
			$("#optionArea .scroll_wrap a:eq(0)").after(optionHtml);
			
			$(".course_popup .selected_box:eq(1)").remove();
			
			if(classDtl.lectAmt == 0) {
				$(".course_popup .selected_box:eq(0) .price_txt").html('<span class="price"></span>');
				$(".total_sum_area .price_txt").html('<span class="price">0</span> 원');
			} else {
				$(".course_popup .selected_box:eq(0) .price_txt").html('<span class="price">' + fnc.fn_numberComma(classDtl.lectAmt) + '</span>원');
				$(".total_sum_area .price_txt").html('<span class="price">' + fnc.fn_numberComma(classDtl.lectAmt) + '</span> 원');
			}
			
			if(classDtl.lectStatCd != '02' && classDtl.lectStatCd != '2' && classDtl.lectStatCd != '4') {
				$(".cont_wrap .single_btn_area").addClass("one_layer");
			} else {
				if(close !== undefined) {
					$(".cont_wrap .single_btn_area").addClass("one_layer");
				} else {
					$(".cont_wrap .single_btn_area").removeClass("one_layer");
				}
			}
			
			$("#tcBtn").data("tcCdNo", classDtl.tcCdNo);
			
			// 2023.07.18 강좌 동적 강의일정 시작
			var schedulePlanList = classDtl.schedulePlanList;
			var schedulePlanYn = false;
			var schedulePlanHtml = "";
			for(var schedulePlan of schedulePlanList){
				if(fnc.nvl(schedulePlan.planExpl, "") != ""){
					schedulePlanYn = true;
					
					schedulePlanHtml += "<div class=\"info_txt_wrap\">";
					schedulePlanHtml += "	<p class=\"date f_body2\">"+ schedulePlan.convertLectDt +"("+ schedulePlan.stDaywNm.replaceAll("요일", "") +")</p>";
					schedulePlanHtml += "	<p class=\"txt f_body2\">"+ schedulePlan.planExpl +"</p>";
					schedulePlanHtml += "</div>";
				}
			}
			
			if(!schedulePlanYn){
				$(".cont_wrap .mobile_acco_div .list_div:nth-child(1)").hide();
			}else{
				$(".cont_wrap .mobile_acco_div .list_div:nth-child(1)").show();
				$(".cont_wrap .mobile_acco_div .list_div:nth-child(1) .hide_con").empty();
				$(".cont_wrap .mobile_acco_div .list_div:nth-child(1) .hide_con").append(schedulePlanHtml);
			}
			// 2023.07.18 동적 강의일정 끝
			
			commonScript.commonFn();
			commonScript.resizeFn();
			commonScript.formChkFn();
			
		}, "/application/search/view.ajax", $("#classForm"), "json", true, true, true);
	}
	
	//옵션선택
	var optionSet = function(optnNm, optnAmt, optnSeqno, partRfndPsblYn) {
		$(".course_popup .selected_box:eq(1)").remove();
		$("#waitPopup .optionAmt").text("");
		
		var totalAmt = $(".course_popup .selected_box:eq(0) .price").text();
		
		if(optnNm != "") {
			var optionHtml = "";
			optionHtml += '<div class="selected_box">';
			optionHtml += '<div class="content_txt">';
			optionHtml += '<p class="name"></p>';
			optionHtml += '</div>';
			if(optnAmt == 0) {
				optionHtml += '<p class="price_txt"><span class="price"></span></p>';
			} else {
				optionHtml += '<p class="price_txt"><span class="price">' + fnc.fn_numberComma(optnAmt) + '</span>원</p>';
			}
			optionHtml += '</div>';
			
			$(".course_popup .selected_box:eq(0)").after(optionHtml);
			$(".course_popup .selected_box:eq(1)").find(".name").text(optnNm);
			
			if(optnAmt == 0) {
				$("#waitPopup .optionAmt").text("재료비/대여료 " + optnNm);
			} else {
				$("#waitPopup .optionAmt").text("재료비/대여료 " + optnNm + " " + fnc.fn_numberComma(optnAmt) + "원");
			}
			
			if(optnAmt == 0) {
				if(totalAmt != "") {
					totalAmt = parseInt(totalAmt.replaceAll(",", ""));
				} 
			} else {
				if(totalAmt != "") {
					totalAmt = parseInt(totalAmt.replaceAll(",", "")) + parseInt(optnAmt);
				} else {
					totalAmt = parseInt(optnAmt);
				}
			}
			
			if(totalAmt == "") {
				$(".course_popup .total_sum_area .price_txt").html('<span class="price">0</span> 원');
			} else {
				$(".course_popup .total_sum_area .price_txt").html('<span class="price">' + fnc.fn_numberComma(totalAmt) + '</span> 원');
			}
		} else {
			if(totalAmt == "") {
				$(".course_popup .total_sum_area .price_txt").html('<span class="price">0</span> 원');
			} else {
				$(".course_popup .total_sum_area .price_txt").html('<span class="price">' + fnc.fn_numberComma(parseInt(totalAmt.replaceAll(",", ""))) + '</span> 원');
			}
		}
		
		$("#optionArea a[data-optn-seqno='" + optnSeqno + "']").addClass("on").siblings("a").removeClass("on");
		
		$("#optionArea .scroll_wrap a").each(function() {
			if($(this).hasClass("on")) {
				$("#optionArea .btn_open span").text($(this).find("span").text());
			}
		});
		
		$("#optnSeqno").val(optnSeqno);
		$("#optnNm").val(optnNm);
		$("#optnAmt").val(optnAmt);
		$('#partRfndPsblYn').val(partRfndPsblYn);
	}
	
	//수강신청 대기신청 
	var paymentBtn = function() {
		if($('#wrap').data('isLogin') == "Y"){
			if($("#optnUseYn").val() == 'Y' && $("#optnSeqno").val() == "") {
				//강좌 옵션 사용시 선택 안했을때
				alert("옵션을 선택하세요.");
				return;
			}
			
			var lectStatCd = $("#lectStatCd").val();
			console.log(lectStatCd);
			if (lectStatCd == "02" || lectStatCd == "2") {
				//수강 신청
				$('#classForm').attr('action', '/payment/step1.do');
				$('#classForm').attr('method', 'POST');
				$('#classForm').submit();
			} else if (lectStatCd == "4") {
				//대기 신청
				commonScript.openPopupFn("#waitPopup", $(".cont_wrap .total_sum_area .sign_btn"));	
			}
		}else{
			if(confirm("로그인이 필요한 서비스입니다.")){
				fnc.moveLoginPage();
			}
		}
	}
	
	// 장바구니
	var cartBtn = function(){
		fnc.bscAjax(function(data){
			if(data.lgnYn){
				if($("#optnUseYn").val() == 'Y' && $("#optnSeqno").val() == "") {
					//강좌 옵션 사용시 선택 안했을때
					alert("옵션을 선택하세요.");
					return;
				}
				
				fnc.paramAjax(function(data) {
					var rtnMap = data.rtnMap;
					if(rtnMap.result == "S"){
						if(Number($('div.util_area p.cart_icon span.cart_num').text()) < 50)
						{
							$('div.util_area p.cart_icon').addClass('on');
							// 장바구니 카운트 증가
							$('div.util_area p.cart_icon span.cart_num').text(Number($('div.util_area p.cart_icon span.cart_num').text()) + 1);
							$('div.m_navi_bar div.navi_wrap a.cart_btn p.icon span.cart_num').text(Number($('div.m_navi_bar div.navi_wrap a.cart_btn p.icon span.cart_num').text()) + 1);
						}
						
						
						if(confirm(rtnMap.msg)){
							location.href="/mypage/cart/list.do";
						}
					}else{
						alert(rtnMap.msg);
						return;
					}
				}, "/mypage/cart/insert.ajax" , {
					brchCd : $("#brchCd").val()
					, yy : $("#yy").val()
					, lectSmsterCd : $("#lectSmsterCd").val()
					, lectCd : $("#lectCd").val()
					, optnSeqno : $("#optnSeqno").val()
					, lectStatCd : $("#lectStatCd").val()
				} , "json" , false, false );
			}else{
				if(confirm("로그인이 필요한 서비스입니다.")){
					fnc.moveLoginPage();
				}
			}
		}, "/lgnCheck.ajax");
	}
	
	// 대기신청
	var waitingApplBtn = function(){
		if($('#waitPopup').find('input[name=student]:checked').length == 0){
			alert("대기접수 대상을 선택하세요.");
			return;
		}
		
		if(confirm("대기자 접수를 신청 하시겠습니까?")){
			var arrKorNm = [], arrBday = [], arrFmlyRelCd = [], arrFmlyRelCdNm = [], arrSexCd = [];
			$('#waitPopup').find('input[name=student]:checked').each(function(){
				arrKorNm.push($(this).data('korNm'));
				arrFmlyRelCd.push($(this).data('fmlyRelCd'));
				arrFmlyRelCdNm.push($(this).data('fmlyRelCdNm'));
				arrSexCd.push($(this).data('sexCd'));
				arrBday.push($(this).data('bday'));
			});
			
			
			var paramObj = {
					brchCd : $("#brchCd").val()
					, yy : $("#yy").val()
					, lectSmsterCd : $("#lectSmsterCd").val()
					, lectCd : $("#lectCd").val()
					, optnSeqno : $("#optnSeqno").val()
					, optnNm : $("#optnNm").val()
					, optnAmt : $("#optnAmt").val()
					, partRfndPsblYn : $("#partRfndPsblYn").val()
					, optnTypCd : $("#optnTypCd").val()
					, lectAmt : $("#lectAmt").val()
					, actlAtlctNpleNm : arrKorNm.join()
					, fmlyRelCd : arrFmlyRelCd.join()
					, fmlyRelCdNm : arrFmlyRelCdNm.join()
					, sexCd : arrSexCd.join()
					, bday : arrBday.join()
					, pblYn : $('#waitPopup').data('pblYn')
					, lectNm : $("#lectNm").val()
					, lectStDtm : $("#lectStDtm").val()
					, lectSt : $("#lectSt").val()
			};
			
			if($('#waitPopup').data('pblYn') == "Y"){
				paramObj.pblPmprcustParntBrchCd = paramObj.brchCd;
				paramObj.pblPmprcustParntLectCd = paramObj.lectCd;
				paramObj.brchCd = $('#pblPmprcustParntBrchCd').val();
				paramObj.lectCd = $('#pblPmprcustParntLectCd').val();
			}
			
			fnc.paramAjax(function(data) {
				var rtnMap = data.rtnMap;
				if(rtnMap.rsltCd == "-1"){
					alert("강좌상태가 대기접수인 강좌만 대기자 등록이 가능합니다.");
				}else if(rtnMap.rsltCd == "-2"){
//					var waitingYnList = rtnMap.waitingYnMap;
					alert("대기자명 : " + rtnMap.waitingYnMap.actlAtlctNpleNm + "\n이미 대기자 신청한 강좌입니다.");
//					for(var i=0;i<waitingYnList.length;i++){
//						alert("대기자명 : " + waitingYnList[i].actlAtlctNpleNm + "\n강좌는 이미 대기자 신청한 강좌입니다.");
//					}
				}else if(rtnMap.rsltCd == "-3"){
					alert("수강자명 : " + rtnMap.atlctYnMap.actlAtlctNpleNm + "\n이미 수강신청한 강좌입니다.");
//					var atlctList = rtnMap.atlctList;
//					for(var i=0;i<atlctList.length;i++){
//						alert("수강자명 : " + atlctList[i].actlAtlctNpleNm + "\n강좌는 이미 수강신청한 강좌입니다.");
//					}
				}else if(rtnMap.rsltCd == "-4"){
					alert("AVENUEL ORANGE 등급 이상,  AVENUEL 소속점과 관심지점이 동일한 경우에만 결제 가능합니다.");
				}else if(rtnMap.rsltCd == "-5"){
					alert("기존회원 접수 기간입니다.");
				}else{
					alert("신청완료되었습니다.");
					// 대기신청 성공 시
					location.reload();
					//$('#waitPopup .btn_close').click();
				}
			}, "/mypage/waiting/appl.ajax" , paramObj, "json" , false, false );
		}
	}
	
	var reviewDtl = function(brchCd, yy, lectSmsterCd, lectCd, tcNo, mbrNo, _this) {
    	fnc.bscAjax(function(r) {
    		$("#reviewPopup .review_detail_con").html(r);
    		commonScript.openPopupFn('#reviewPopup', $(_this));
    	}, "/application/search/reviewDtl.ajax?brchCd=" + brchCd + "&yy=" + yy + "&lectSmsterCd=" + lectSmsterCd + "&lectCd=" + lectCd + "&tcNo=" + tcNo + "&memberNo=" + mbrNo, "html", false, false, false);
    }
	
	var btnCheck = function() {
		if($(".single_btn_area").closest(".btn_area").css("display") != "none" && $(".single_btn_area").hasClass("one_layer") && !$(".single_btn_area .sign_btn").hasClass("disabled")) {
			alert("강좌를 선택하세요.");
		}
	}
	
	var teacherSet = function() {
		fnc.bscAjax(function(r) {
			$("#teacherView").html(r);
    		commonScript.openPopupFn('.instructor_intro_pop', $("#tcBtn"));
    		console.log(tcCdNo);
    	}, "/application/search/teacherView.ajax?tcCdNo=" + $("#tcBtn").data("tcCdNo"), "html", false, false, false);
	}
	
	var init = function() {
		if($("#lectStatCd").val() != '02' && $("#lectStatCd").val() != '2' && $("#lectStatCd").val() != '4') {
			$(".cont_wrap .single_btn_area").addClass("one_layer");
		} 
		
		if($("#groupLectTpCd").val() == '02' || $("#groupLectTpCd").val() == '2') {
			$(".cont_wrap .single_btn_area").addClass("one_layer");
		}

		var initObj = {
				form : $("#classForm")
				, container : $("#listContainer")
				, moreBtn : $("#moreBtn")
				, url : "/application/search/reviewList.ajax"
				, pageIndex : $("#classForm #pageIndex").val()
				, listCnt : $("#classForm #listCnt").val()
				, callbackFunc : function() {}
		}
		searchMore = new fnc.SearchMore(initObj);
		searchMore.isParam = false;
		searchMore.search();
		classInfoSet();
	}
	
	$(document).ready(function() {
		init();
	});
	
	 return {
		 classInfoSet : classInfoSet
		 , optionSet : optionSet
		 , paymentBtn : paymentBtn
		 , cartBtn : cartBtn
		 , waitingApplBtn : waitingApplBtn
		 , reviewDtl : reviewDtl
		 , btnCheck : btnCheck
		 , teacherSet : teacherSet
	 }
}());