var mainCtrl = (function(){
	
	"use strict";
	
	//추천 강좌 불러오기
	var getRecommendationClassList = function() {
		var data = [
			{name: 'path', value: 'recommendation'}, {name: 'orderSet', value: 'F'}
		]
		
				
		fnc.paramAjax(function(r){
			$("#recommendationContainer").html(r);
			if($("#recommendationContainer").find("div").length == 0) {
				$("#recommendationContainer").closest("section").remove();
			} else {
				mainScript.swiperFn();
				$("#recommendationContainer img").each(function(){
					$(this).load(function() {
						imgResizingFn();
					});
				});
			}
		}, "/getRecommendationClassList.ajax", data, "html", false, true, false);
	}
	
	//카테고리 강좌 불러오기
	var getCategoryClassList = function() {
		var lrclsCtegryCd = $(".category_wrap .scroll_wrap a[class*=on]").data("lrclsCtegryCd");
		var mdclsCtegryCd = $(".category_wrap .scroll_wrap a[class*=on]").data("mdclsCtegryCd");
		
		var data = [
			{name: 'orderSet', value: 'B'}, {name: 'lrclsCtegryCd', value: lrclsCtegryCd} , {name: 'mdclsCtegryCd', value: mdclsCtegryCd}
			, {name: 'initIndex', value: '1'}, {name: 'pageIndex', value: '1'}, {name: 'listCnt', value: '4'}
		]
				
		fnc.paramAjax(function(r){
			$("#categoyContainer").html(r);
			
			$("#categoyContainer img").each(function(){
				$(this).load(function() {
					imgResizingFn();
				});
			});
			
			if($("#categoyContainer img").length == 0) {
				imgResizingFn();
			}
			
			try{			
				ScrollTrigger.refresh();
			}catch(e){}
		}, "/getCategoryClassList.ajax", data, "html", false, true, false);
		
		
	}
	
	//신규 강좌 불러오기
	var getNewClassList = function() {
		var data = [
			{name: 'path', value: 'new'}, {name: 'orderSet', value: "G"}
			, {name: 'initIndex', value: '1'}, {name: 'pageIndex', value: '1'}, {name: 'listCnt', value: '30'}
		]
				
		fnc.paramAjax(function(r){
			$("#newContainer").html(r);
			if($("#newContainer").find("div").length == 0) {
				$("#newContainer").closest("section").remove();
			} else {
				mainScript.swiperFn();
				$("#newContainer img").each(function(){
					$(this).load(function() {
						imgResizingFn();
					});
				});
			}
		}, "/getNewClassList.ajax", data, "html", false, true, false);
	}
	
	//메인 팝업 오픈
	var openPopup = function(){
		var isChk = fnc.getCookie("todayClose");
		//알림 안보기 아닐시
		if(isChk != 'Y' && $('#mainPop').length > 0){
			commonScript.openPopupFn("#mainPop" , $(this));
		}
	}
	//팝업 창닫기
	var popClose =  function(){
		$("#mainPop").find(".btn_close").click();
	}
	
	//오늘 하루 팝업 보지 않기
	var todayClose = function(){
		
		var isChk = fnc.getCookie("todayClose");
		
		if(isChk == 'Y'){
			if($("#mainPop").length > 0){
				$("#mainPop").hide();
			}
		}
		$("#todayClose").click(function(){
			fnc.setCookie("todayClose", "Y", {expires : 1});
			$(this).next().click();
		})
	}
	
	var init = function() {
		//getRecommendationClassList();
		//getCategoryClassList();
		//getNewClassList();
		eventSwiperFn();
		//openPopup();
		//todayClose();
	}
	
	$(document).ready(function() {
		init();
	});
	
	window.onload = function(){
		openPopup();
		todayClose();
		//mainScript.eventSwiperFn();
	} 
	
	 return {
	 	 getRecommendationClassList : getRecommendationClassList ,
		 getCategoryClassList : getCategoryClassList ,
		 getNewClassList : getNewClassList ,
		 popClose	:	popClose ,
		 todayClose :	todayClose
		 
	 }
}());