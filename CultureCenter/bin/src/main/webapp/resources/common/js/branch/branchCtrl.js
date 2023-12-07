var branchCtrl = (function(){
	
	"use strict";
	
	//초기화
	var init = function(brchAreaCd)
	{
		
			fnc.paramAjax(function(data) {
			$("#listArea").html(data);
			select_brch_change(this, $("#listArea").find("a:eq(0)").data("brchcd"));
			thrdepRecall();
			fixedMobileH();
		}, "/information/branch/list.ajax" , {brchAreaCd : brchAreaCd} , "html" , false, false );
	
	}
	
	//권역명 클릭
	var tab_brchAreaCd_click = function(brchAreaCd)
	{
		$(this).find(".con").addClass("on");
		$("#brchAreaCd").val(brchAreaCd);
		init(brchAreaCd);
	};
	
	//지점명 클릭
	var select_brch_change = function(obj, brchCd)
	{
		
		$(obj).addClass("on");
		$(obj).siblings().removeClass("on");
		$("#brchCd").val(brchCd);
		
		fnc.paramAjax(function(data) {
			$("#dtlArea").html(data);
			//thrdepRecall();
			$("#dtlArea img").each(function(){
				$(this).load(function() {
					imgResizingFn();
				});
			});
			storeSwiperRecall();
			
		}, "/information/branch/view.ajax" , {brchCd : brchCd} , "html" , false, false );
	}
	
	$(document).ready(function(){
		
		tab_brchAreaCd_click($("#brchAreaCd").val());
		});
	
	 return {
		   tabClCd	  : tab_brchAreaCd_click
		 , selectBrch : select_brch_change
	 }
}());