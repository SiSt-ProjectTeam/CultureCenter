var noticeCtrl = (function(){
	
	"use strict";
	


	
	var searchMore = null;
	
	var select_brch_change = function(branchId)
	{
		$("#branchId").val(branchId);
		
		fn_search();
	}
	// 공지사항,이벤트 클릭
	var tab_clCd_click = function(noticeEvent){
	
		$(".tab_con_area").find(".con").addClass("on");
		
		$("#noticeEvent").val(noticeEvent);
		
		fn_search();
	};
	
	var fn_query_onkeyup = function(){
        if (window.event.keyCode == 13) {
        	 $("input[name=q]").val($("#q").val());
        	 fn_search();
        }
    }
	
	var select_detail = function(target, seq, url){
		
		var strPam = location.search.substr(location.search.indexOf("?") + 1);

    	if(target == null && seq == null){
    		location.href = url;
    	}else{
    		if (strPam.indexOf(target + "=") < 0)
    		{
    			location.href = url + "?" + (strPam ? "&" : "") + target + "=" + seq;
    		}
    		else
    		{
    			var paramArr = strPam.split("&").map(function(value){
    				return value.indexOf(target + "=") < 0 ? value : target + "=" + seq;
    			});

    			location.href = url + "?" + paramArr.join("&");
    		}
    	}
		
	};
	
	//검색 버튼
	var search_btn = function() {
		
		$("input[name=q]").val($("#q").val());
		
		fn_search();
	};
	
	//검색
	var fn_search = function() {
		
		searchMore.pageIndex = 1;
		searchMore.search();
	}
	
	//초기화
	var init = function() {
		var noticeEvent = $("#noticeEvent").val();
		var pathname = "/" + window.location.pathname.split("/")[1];
		var origin = window.location.origin;	
		var contextPath = origin + pathname;
		
		var initObj = {
				form : $("#frmSearch")
				, container : $("#listContainer")
				, moreBtn : $("#moreBtn")
				, url : contextPath+"/community/notice/list.ajax"
				, pageIndex : $("#frmSearch #pageIndex").val()
				, listCnt : $("#frmSearch #listCnt").val()
				, callbackFunc : function() {
					 $("#totCnt").text(searchMore.totCnt  + "개");
				 	fixedMobileH();
				 }
		}
	
		searchMore = new fnc.SearchMore(initObj);
		searchMore.search();
	
		tab_clCd_click(noticeEvent);
		
	}
	
	$(document).ready(function(){
		init();
	});
	
	 return {
		   tabClCd	  : tab_clCd_click
		 , selectBrch : select_brch_change
		 , search_btn : search_btn 
		 , detail	  : select_detail
		 , fn_search  : fn_search
		 , queryOnkeyup: fn_query_onkeyup
	 }
}());