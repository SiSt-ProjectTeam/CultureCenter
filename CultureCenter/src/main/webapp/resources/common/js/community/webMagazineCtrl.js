var webMagazineCtrl = (function(){
	
	"use strict";
	
	var searchMore = null;
	
	//초기화
	var init = function() {
		
		var initObj = {
				form : $("#frmMagazine")
				, container : $("#listContainer")
				, moreBtn : $("#moreBtn")
				, url : "/community/magazine/list.ajax"
				, pageIndex : $("#frmMagazine #pageIndex").val()
				, listCnt : $("#frmMagazine #listCnt").val()
				, callbackFunc : function() { $("#totCnt").text(searchMore.totCnt  + "개"); }
		}
	
		searchMore = new fnc.SearchMore(initObj);
		searchMore.search();
		
	}
	
	$(document).ready(function(){
		init();
	});
	
	 return {
	 }
}());