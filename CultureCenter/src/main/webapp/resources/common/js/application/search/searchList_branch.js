var search = (function(){
	
	"use strict";
	
	var searchMore = null;
	
	//카테고리 원뎁스 클릭
	var oneDepthCtgy_click = function(categoryCd) {
		$("#lrclsCtegryCd").val(categoryCd);
		$("#mdclsCtegryCd").val("");
		$("#smclsCtegryCd").val("");
		
		$(".circle_sel_swiper.on").find("a").eq(0).click();
		$(".thr_dep_area_w").find(".thr_dep_area").removeClass("on");
		
		fn_search();
	};
	
	//카테고리 투뎁스 클릭
	var twoDepthCtgy_click = function(categoryCd) {
		$("#mdclsCtegryCd").val(categoryCd);
		$("#smclsCtegryCd").val("");
		
		if(categoryCd != "") {
			if($(".thr_dep_area_w").find("div[data-mdcls-ctegry-cd=" + categoryCd + "]").find("a").length > 1) {
				$(".thr_dep_area_w").find("div[data-mdcls-ctegry-cd=" + categoryCd + "]").addClass("on");
			}
			$(".thr_dep_area_w").find("div[data-mdcls-ctegry-cd=" + categoryCd + "]").siblings("div").removeClass("on");
			$(".thr_dep_area_w").find(".thr_dep_area.on").find("a").eq(0).click();
		} else {
			$(".thr_dep_area_w").find(".thr_dep_area").removeClass("on");
		}
		
		thrdepRecall();
		fn_search();
	};
	
	//카테고리 쓰리뎁스 클릭
	var threeDepthCtgy_click = function(categoryCd) {
		$("#smclsCtegryCd").val(categoryCd);
		fn_search();
	};
	
	//검색
	var fn_search = function() {
		commonScript.resizeFn();
		dropDownRecall();
		_isSrchOncePc = false;
		_isSrchOnceMo = false;
		srchDataRecall();
		searchMore.pageIndex = 1;
		searchMore.search();
	}
	
	//검색 버튼
	var search_btn = function() {
		joinButtonList();
		joinFilterBtnList();
		fn_search();
	}
	
	//버튼 조합
	var joinButtonList = function() {
		$("#q").val("");
		$("#q").val($(".srch_popup").find("input[name=q]").val());
		
		$("#yyList").val("");
		$("#yyListArea").find("a").each(function(){
			if($(this).hasClass("on")) {
				var yyList = $("#yyList").val() == "" ? $(this).data("yy") + "||" + $(this).data("lectSmsterCd") : "," + $(this).data("yy") + "||" + $(this).data("lectSmsterCd"); 
				$("#yyList").val($("#yyList").val() + yyList);
			}
		});
		
		$("#lectClCdList").val("");
		$("#lectClCdListArea").find("a").each(function(){
			if($(this).hasClass("on")) {
				var lectClCdList = $("#lectClCdList").val() == "" ? $(this).data("lectClCd") : "," + $(this).data("lectClCd"); 
				$("#lectClCdList").val($("#lectClCdList").val() + lectClCdList);
			}
		});
		
		$("#lectStatCdList").val("");
		$("#lectStatCdListArea").find("a").each(function(){
			if($(this).hasClass("on")) {
				var lectStatCdList = $("#lectStatCdList").val() == "" ? $(this).data("lectStatCd") : "," + $(this).data("lectStatCd"); 
				$("#lectStatCdList").val($("#lectStatCdList").val() + lectStatCdList);
			}
		});
		
		$("#stDaywCdList").val("");
		$("#stDaywCdListArea").find("a").each(function(){
			if($(this).hasClass("on")) {
				var stDaywCdList = $("#stDaywCdList").val() == "" ? $(this).data("stDaywCd") : "," + $(this).data("stDaywCd"); 
				$("#stDaywCdList").val($("#stDaywCdList").val() + stDaywCdList);
			}
		});
		
		$("#timeTypeList").val("");
		$("#timeTypeListArea").find("a").each(function(){
			if($(this).hasClass("on")) {
				var timeTypeList = $("#timeTypeList").val() == "" ? $(this).data("timeType") : "," + $(this).data("timeType"); 
				$("#timeTypeList").val($("#timeTypeList").val() + timeTypeList);
			}
		});
		
		$("#amtTypeList").val("");
		$("#amtTypeListArea").find("a").each(function(){
			if($(this).hasClass("on")) {
				var amtTypeList = $("#amtTypeList").val() == "" ? $(this).data("amtType") : "," + $(this).data("amtType"); 
				$("#amtTypeList").val($("#amtTypeList").val() + amtTypeList);
			}
		});
		
		$("#stAmt").val("");
		$("#stAmt").val($(".srch_popup").find("input[name=stAmt]").val().replaceAll(",", ""));
		
		$("#endAmt").val("");
		$("#endAmt").val($(".srch_popup").find("input[name=endAmt]").val().replaceAll(",", ""));
	}
	
	//필터 버튼 조합
	var joinFilterBtnList = function() {
		var htmlFlag = false;
		
		var innerHtml = '<div class="srch_data_div">';
		innerHtml += '<a href="javascript:search.delbtnAll();" class="reset_data" role="button"><span>선택 초기화</span></a>';
		innerHtml += '<div class="srch_data_list swiper-container">';
		innerHtml += '<div class="swiper-wrapper">';
		
		if($("#q").val().trim() != "") {
			innerHtml += '<p class="word swiper-slide">';
			innerHtml += '<span>' + fnc.convertHtml($("#q").val()) + '</span>';	
			innerHtml += '<button type="button" class="delete_btn" onclick="search.delbtn(' + "'','q',this" + ');"><img src="/resources/common/images/btn-search-data-delete.png" alt=""></button>';
			innerHtml += '</p>';
			htmlFlag = true;
		}
		
		if($("#yyList").val() != "") {
			$("#yyList").val().split(",").forEach(function(data){
				$("#yyListArea").find("a").each(function(){
					if($(this).data("yy") == data.split("||")[0] && $(this).data("lectSmsterCd") == data.split("||")[1]) {
						innerHtml += '<p class="word swiper-slide">';
						innerHtml += '<span>' + $(this).find("span").text() + '</span>';	
						innerHtml += '<button type="button" class="delete_btn" onclick="search.delbtn(' + "'" + $(this).data("yy") + "||" + $(this).data("lectSmsterCd") + "','yyList',this" + ');"><img src="/resources/common/images/btn-search-data-delete.png" alt=""></button>';
						innerHtml += '</p>';
					}
				});
			});
			htmlFlag = true;
		}
		
		if($("#lectClCdList").val() != "") {
			$("#lectClCdList").val().split(",").forEach(function(data){
				$("#lectClCdListArea").find("a").each(function(){
					if($(this).data("lectClCd") == data) {
						innerHtml += '<p class="word swiper-slide">';
						innerHtml += '<span>' + $(this).find("span").text() + '</span>';	
						innerHtml += '<button type="button" class="delete_btn" onclick="search.delbtn(' + "'" + $(this).data("lectClCd") + "','lectClCdList',this" + ');"><img src="/resources/common/images/btn-search-data-delete.png" alt=""></button>';
						innerHtml += '</p>';
					}
				});
			});
			htmlFlag = true;
		}
		
		if($("#lectStatCdList").val() != "") {
			$("#lectStatCdList").val().split(",").forEach(function(data){
				$("#lectStatCdListArea").find("a").each(function(){
					if($(this).data("lectStatCd") == data) {
						innerHtml += '<p class="word swiper-slide">';
						innerHtml += '<span>' + $(this).find("span").text() + '</span>';	
						innerHtml += '<button type="button" class="delete_btn" onclick="search.delbtn(' + "'" + $(this).data("lectStatCd") + "','lectStatCdList',this" + ');"><img src="/resources/common/images/btn-search-data-delete.png" alt=""></button>';
						innerHtml += '</p>';
					}
				});
			});
			htmlFlag = true;
		}
		
		if($("#stDaywCdList").val() != "") {
			$("#stDaywCdList").val().split(",").forEach(function(data){
				$("#stDaywCdListArea").find("a").each(function(){
					if($(this).data("stDaywCd") == data) {
						innerHtml += '<p class="word swiper-slide">';
						innerHtml += '<span>' + $(this).find("span").text() + '</span>';	
						innerHtml += '<button type="button" class="delete_btn" onclick="search.delbtn(' + "'" + $(this).data("stDaywCd") + "','stDaywCdList',this" + ');"><img src="/resources/common/images/btn-search-data-delete.png" alt=""></button>';
						innerHtml += '</p>';
					}
				});
			});
			htmlFlag = true;
		}
		
		if($("#timeTypeList").val() != "") {
			$("#timeTypeList").val().split(",").forEach(function(data){
				$("#timeTypeListArea").find("a").each(function(){
					if($(this).data("timeType") == data) {
						innerHtml += '<p class="word swiper-slide">';
						innerHtml += '<span>' + $(this).find("span").text() + '</span>';	
						innerHtml += '<button type="button" class="delete_btn" onclick="search.delbtn(' + "'" + $(this).data("timeType") + "','timeTypeList',this" + ');"><img src="/resources/common/images/btn-search-data-delete.png" alt=""></button>';
						innerHtml += '</p>';
					}
				});
			});
			htmlFlag = true;
		}
		
		if($("#amtTypeList").val() != "") {
			$("#amtTypeList").val().split(",").forEach(function(data){
				$("#amtTypeListArea").find("a").each(function(){
					if($(this).data("amtType") == data) {
						innerHtml += '<p class="word swiper-slide">';
						innerHtml += '<span>' + $(this).find("span").text() + '</span>';	
						innerHtml += '<button type="button" class="delete_btn" onclick="search.delbtn(' + "'" + $(this).data("amtType") + "','amtTypeList',this" + ');"><img src="/resources/common/images/btn-search-data-delete.png" alt=""></button>';
						innerHtml += '</p>';
					}
				});
			});
			htmlFlag = true;
		}
		
		if($("#stAmt").val().trim() != "" && $("#endAmt").val().trim() != "") {
			innerHtml += '<p class="word swiper-slide">';
			innerHtml += '<span>' + fnc.fn_numberComma($("#stAmt").val()) + '원 ~ ' + fnc.fn_numberComma($("#endAmt").val()) + '원</span>';	
			innerHtml += '<button type="button" class="delete_btn" onclick="search.delbtn(' + "'','stAmt||endAmt',this" + ');"><img src="/resources/common/images/btn-search-data-delete.png" alt=""></button>';
			innerHtml += '</p>';
			htmlFlag = true;
		} else {
			if($("#stAmt").val().trim() != "") {
				innerHtml += '<p class="word swiper-slide">';
				innerHtml += '<span>' + fnc.fn_numberComma($("#stAmt").val()) + '원 ~</span>';	
				innerHtml += '<button type="button" class="delete_btn" onclick="search.delbtn(' + "'','stAmt',this" + ');"><img src="/resources/common/images/btn-search-data-delete.png" alt=""></button>';
				innerHtml += '</p>';
				htmlFlag = true;
			}
			
			if($("#endAmt").val().trim() != "") {
				innerHtml += '<p class="word swiper-slide">';
				innerHtml += '<span>~ ' + fnc.fn_numberComma($("#endAmt").val()) + '원</span>';	
				innerHtml += '<button type="button" class="delete_btn" onclick="search.delbtn(' + "'','endAmt',this" + ');"><img src="/resources/common/images/btn-search-data-delete.png" alt=""></button>';
				innerHtml += '</p>';
				htmlFlag = true;
			}
		}
	
		innerHtml += '</div>';
		innerHtml += '</div>';
		innerHtml += '</div>';
		
		$(".cont_wrap .srch_data_div").remove();
		if(htmlFlag) {
			$(".cont_wrap .filter_bar_div").after(innerHtml);
		}
		
		if($(".cont_wrap .srch_data_div").find(".word").length == 0) {
			$(".cont_wrap .srch_btn_wrap").find("a").removeClass("alarm");
		} else {
			$(".cont_wrap .srch_btn_wrap").find("a").addClass("alarm");
		}
		
		//commonScript.swiperFn();
		$(".cont_wrap .btn_close").click();
	}
	
	//필터 버튼 전체 삭제
	var delbtnAll = function() {
		$(".srch_popup").find("input[name=q]").val("");
		$("#q").val("");
		
		$(".srch_popup").find("input[name=stAmt]").val("");
		$("#stAmt").val("");
		$(".srch_popup").find("input[name=endAmt]").val("");
		$("#endAmt").val("");
		
		$(".filter_btn_list").find("a").removeClass("on");
		$(".srch_popup").find(".list_div").find(".txt_box").addClass("hide").find(".num").text("0");
		$("#yyList").val("");
		$("#lectClCdList").val("");
		$("#lectStatCdList").val("");
		$("#stDaywCdList").val("");
		$("#timeTypeList").val("");
		$("#amtTypeList").val("");
		
		$(".cont_wrap .srch_data_div").remove();
		$(".cont_wrap .srch_btn_wrap").find("a").removeClass("alarm");
		
		fn_search();
	}
	
	//필터 버튼 삭제
	var delbtn = function(value, classValue, _this) {
		if(classValue == "q") {
			$(".srch_popup").find("input[name=q]").val("");
			$("#q").val("");
			$(_this).closest(".word").remove();
		}
		
		if(classValue == "stAmt||endAmt") {
			$(".srch_popup").find("input[name=stAmt]").val("");
			$("#stAmt").val("");
			$(".srch_popup").find("input[name=endAmt]").val("");
			$("#endAmt").val("");
			$(".srch_popup").find("input[name=endAmt]").closest(".list_div").find(".txt_box").addClass("hide").find(".num").text("0");
			$(_this).closest(".word").remove();
		}
		
		if(classValue == "stAmt") {
			$(".srch_popup").find("input[name=stAmt]").val("");
			$("#stAmt").val("");
			$(".srch_popup").find("input[name=endAmt]").closest(".list_div").find(".txt_box").addClass("hide").find(".num").text("0");
			$(_this).closest(".word").remove();
		}
		
		if(classValue == "endAmt") {
			$(".srch_popup").find("input[name=endAmt]").val("");
			$("#endAmt").val("");
			$(".srch_popup").find("input[name=endAmt]").closest(".list_div").find(".txt_box").addClass("hide").find(".num").text("0");
			$(_this).closest(".word").remove();
		}
		
		if(classValue == "yyList") {
			var yy = value.split("||")[0];
			var lectSmsterCd = value.split("||")[1];
			$("#yyListArea").find("a").each(function() {
				if($(this).data("yy") == yy && $(this).data("lectSmsterCd") == lectSmsterCd) {
					$(this).removeClass("on");
				}
			});
			
			var len = $("#yyListArea").find("a.on").length;
			$("#yyListArea").closest(".list_div").find(".txt_box").addClass(len == 0 ? "hide" : "").find(".num").text(len);
			
			var yyList = $("#yyList").val().split(",");
			yyList.forEach(function(data, index){
				if(data == value) {
					yyList.splice(index, 1)
				}
			});
			$("#yyList").val(yyList.join(","));
			$(_this).closest(".word").remove();
		}
		
		if(classValue == "lectClCdList") {
			$("#lectClCdListArea").find("a").each(function() {
				if($(this).data("lectClCd") == value) {
					$(this).removeClass("on");
				}
			});
			
			var len = $("#lectClCdListArea").find("a.on").length;
			$("#lectClCdListArea").closest(".list_div").find(".txt_box").addClass(len == 0 ? "hide" : "").find(".num").text(len);
			
			var lectClCdList = $("#lectClCdList").val().split(",");
			lectClCdList.forEach(function(data, index){
				if(data == value) {
					lectClCdList.splice(index, 1)
				}
			});
			$("#lectClCdList").val(lectClCdList.join(","));
			$(_this).closest(".word").remove();
		}
		
		if(classValue == "lectStatCdList") {
			$("#lectStatCdListArea").find("a").each(function() {
				if($(this).data("lectStatCd") == value) {
					$(this).removeClass("on");
				}
			});
			
			var len = $("#lectStatCdListArea").find("a.on").length;
			$("#lectStatCdListArea").closest(".list_div").find(".txt_box").addClass(len == 0 ? "hide" : "").find(".num").text(len);
			
			var lectStatCdList = $("#lectStatCdList").val().split(",");
			lectStatCdList.forEach(function(data, index){
				if(data == value) {
					lectStatCdList.splice(index, 1)
				}
			});
			$("#lectStatCdList").val(lectStatCdList.join(","));
			$(_this).closest(".word").remove();
		}
		
		if(classValue == "stDaywCdList") {
			$("#stDaywCdListArea").find("a").each(function() {
				if($(this).data("stDaywCd") == value) {
					$(this).removeClass("on");
				}
			});
			
			var len = $("#stDaywCdListArea").find("a.on").length;
			$("#stDaywCdListArea").closest(".list_div").find(".txt_box").addClass(len == 0 ? "hide" : "").find(".num").text(len);
			
			var stDaywCdList = $("#stDaywCdList").val().split(",");
			stDaywCdList.forEach(function(data, index){
				if(data == value) {
					stDaywCdList.splice(index, 1)
				}
			});
			$("#stDaywCdList").val(stDaywCdList.join(","));
			$(_this).closest(".word").remove();
		}
		
		if(classValue == "timeTypeList") {
			$("#timeTypeListArea").find("a").each(function() {
				if($(this).data("timeType") == value) {
					$(this).removeClass("on");
				}
			});
			
			var len = $("#timeTypeListArea").find("a.on").length;
			$("#timeTypeListArea").closest(".list_div").find(".txt_box").addClass(len == 0 ? "hide" : "").find(".num").text(len);
			
			var timeTypeList = $("#timeTypeList").val().split(",");
			timeTypeList.forEach(function(data, index){
				if(data == value) {
					timeTypeList.splice(index, 1)
				}
			});
			$("#timeTypeList").val(timeTypeList.join(","));
			$(_this).closest(".word").remove();
		}
		
		if(classValue == "amtTypeList") {
			$("#amtTypeListArea").find("a").each(function() {
				if($(this).data("amtType") == value) {
					$(this).removeClass("on");
				}
			});
			
			var len = $("#amtTypeListArea").find("a.on").length;
			$("#amtTypeListArea").closest(".list_div").find(".txt_box").addClass(len == 0 ? "hide" : "").find(".num").text(len);
			
			var amtTypeList = $("#amtTypeList").val().split(",");
			amtTypeList.forEach(function(data, index){
				if(data == value) {
					amtTypeList.splice(index, 1)
				}
			});
			$("#amtTypeList").val(amtTypeList.join(","));
			$(_this).closest(".word").remove();
		}
		
		if($(".cont_wrap .srch_data_div").find(".word").length == 0) {
			$(".cont_wrap .srch_data_div").remove();
			$(".cont_wrap .srch_btn_wrap").find("a").removeClass("alarm");
		}
		
		fn_search();
	}
	
	//정렬
	var order = function(type) {
		$("#orderSet").val(type);
		fn_search();
	}
	
	var popResize = function() {
		/*if($(".filter_btn_list").find(".btn.on").length > 0) {
            if($(".reset_wrap").hasClass("active")) {
              $(".srch_popup .for_padding .scroll_area").height($(".srch_popup").find(".for_padding").height() - $(".reset_wrap").innerHeight() + 12); // 플로팅 버튼 패딩 윗 여백값
              $(".srch_popup .for_padding").addClass("on");
            }
		} else {
            if(!$(".reset_wrap").hasClass("active")) {
              $(".srch_popup .for_padding .scroll_area").height($(".srch_popup").find(".pop_wrap").height());
              $(".srch_popup .for_padding").removeClass("on");
            }
		}*/
	}
	
	//스크롤 위치
	var scrollMove = function () {
		if (sessionStorage.historyUrl && sessionStorage.historyUrl.split(',').find((data) => data.indexOf(location.pathname) > -1)) {
			var historyUrl = new URL(sessionStorage.historyUrl.split(',').filter((data) => data.indexOf(location.pathname) > -1)[0]);
			var historyParam = historyUrl.searchParams;

			if (!historyParam.get('initIndex')) {
				sessionStorage.removeItem('listScrollY');
			}
		}
		else {
			sessionStorage.removeItem('listScrollY');
		}		
	}
	
	var init = function() {
		$(".accordion_type .list_div.on").each(function(){
			$(this).find(".hide_con").stop(true, true).slideDown();
		});
		
		thrdepRecall();
		dropDownRecall();
		_isSrchOncePc = false;
		_isSrchOnceMo = false;
		srchDataRecall();

		scrollMove();
		
		var initObj = {
				form : $("#searchBranch")
				, container : $("#listContainer")
				, moreBtn : $("#moreBtn")
				, url : "/search/list.ajax"
				, pageIndex : $("#searchBranch #pageIndex").val()
				, listCnt : $("#searchBranch #listCnt").val()
				, callbackFunc : function() { 
					$("#totCnt").text(searchMore.totCnt  + "개"); 
					fixedMobileH();
					$("#listContainer img").each(function(){
						$(this).load(function() {
							imgResizingFn();
						});
					});
					
					//스크롤 위치 이동
					var listScrollY = parseInt(sessionStorage.getItem("listScrollY"));
					if (listScrollY && listScrollY > 0) {
						window.scrollTo(0, listScrollY);
					}
					
				}
		}
		
		searchMore = new fnc.SearchMore(initObj);
		searchMore.search();
	}
	
	$(document).ready(function() {
		init();
	});
	
	 return {
		 oneDepthCtgy_click : oneDepthCtgy_click
		 , twoDepthCtgy_click : twoDepthCtgy_click
		 , threeDepthCtgy_click : threeDepthCtgy_click
		 , search_btn : search_btn
		 , delbtn : delbtn
		 , delbtnAll : delbtnAll
		 , order : order
		 , popResize : popResize
		 
	 }
}());