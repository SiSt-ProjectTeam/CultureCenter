var appList = (function(){
	
	"use strict";
	
	var searchMore = null;
	
	//검색
	var fn_search = function() {
		searchMore.pageIndex = 1;
		searchMore.search();
	}
	
	//지점 선택
	var setBranch = function(brchCd) {
		$("#brchCd").val(brchCd);
		fn_search();
	}
	
	var init = function() {
		var initObj = {
				form : $("#searchFreebie")
				, container : $("#listContainer")
				, moreBtn : $("#moreBtn")
				, url : "/mypage/freebie/appList.ajax"
				, pageIndex : $("#searchFreebie #pageIndex").val()
				, listCnt : $("#searchFreebie #listCnt").val()
				, callbackFunc : function() { $("#totCnt").text(searchMore.totCnt  + "개"); }
		}
		searchMore = new fnc.SearchMore(initObj);
		searchMore.search();
	}
	
	$(document).ready(function() {
		init();
	});
	
	 return {
		 setBranch : setBranch
	 }
}());