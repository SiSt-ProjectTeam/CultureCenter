var detailList = (function(){
	
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
	
	//사은품 취소
	var cancelBtn = function(fgftAppNo) { 
		if(confirm("취소하시겠습니까?")) {
			var data = [
				//todo 정렬 바꿔야함
				{name: 'fgftAppNo', value: fgftAppNo}
			]
					
			fnc.paramAjax(function(r){
				if(r.actCnt > 0) {
					searchMore.search();
				}
			}, "/mypage/freebie/updateFreebieCancel.ajax", data, "json", false, false, false);
		}
	}
	
	var init = function() {
		var initObj = {
				form : $("#searchFreebie")
				, container : $("#listContainer")
				, moreBtn : $("#moreBtn")
				, url : "/mypage/freebie/detailList.ajax"
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
		 , cancelBtn : cancelBtn
	 }
}());