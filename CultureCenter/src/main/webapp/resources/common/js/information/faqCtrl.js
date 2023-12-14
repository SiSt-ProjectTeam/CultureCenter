var faqCtrl = (function(){

	"use strict";
	
	var searchMore = null;
	
	var tab_clCd_click = function(clCd){
		//console.log(clCd);
		$(this).parents().find(".con").addClass("on");
		$("#clCd").val(clCd);
		
		fn_search();	
	};
	
	//검색 버튼
	var search_btn = function() {
		
		$("#q").val("");
		$("#q").val($(".form_search_div").find("input[name=q]").val());
		
		fn_search();
	}
	
	//검색
	var fn_search = function() {
		
		var searchObj = {
				form : $("#frmSearch")
				, container : $(".listContainer")
				, moreBtn : $("#moreBtn")
				, url : "/information/faq/list.ajax"
				, pageIndex : $("#frmSearch #pageIndex").val()
				, listCnt : $("#frmSearch #listCnt").val()
				, callbackFunc : function() {accorRecall()}
		}
		
		searchMore = new fnc.SearchMore(searchObj);
		searchMore.pageIndex = 1;
		searchMore.search();
	}
		
	//초기화
	var init = function() {
		
		var initObj = {
				form : $("#frmSearch")
				, container : $(".listContainer")
				, moreBtn : $("#moreBtn")
				, url : "/information/faq/list.ajax"
				, pageIndex : $("#frmSearch #pageIndex").val()
				, listCnt : $("#frmSearch #listCnt").val()
				, callbackFunc : function(){accorRecall()}
		}
		searchMore = new fnc.SearchMore(initObj);
		searchMore.search();
		
	}
	
	$(document).ready(function(){
		init();
	});
	
	 return {
		   tabClCd : tab_clCd_click
		 , search_btn : search_btn
		 , fn_search  : fn_search
	 }
}());