var lectureCertfCtrl = (function(){
    
    "use strict";

    var searchMore = null;
    var pageType = null;

    var fn_close = function(){
        $("#dtlPopup").fadeOut(300, function(){
            $("body, #wrap").removeClass("stop_scroll");
            $("body, html").off("scroll touchmove mousewheel");
        });
    }

    var fn_search = function(){
        searchMore.pageIndex = 1;
		searchMore.search();
    }

    var fn_search_by_option = function(obj){
        tcCommon.selOptClick(obj);
        fn_search();
    }

    var fn_open_popup_detail = function(obj){

        var params = "";
        var keyList = Object.keys($(obj.parentElement).data());
        for(var key of keyList){
            params += key + "=" + $(obj.parentElement).data(key) + "&";
        }
        params = params.substring(0, params.length - 1);
        var url = "/mypage/lectureCertf/dtl.ajax?" + params;

        fnc.bscAjax(function(data){
            $("#dtlPopup").html(data);
            commonScript.openPopupFn("#dtlPopup", $(this));

        }, url, null, false, true, true);
    }

    var init_list = function(){

        $("#lectureCertfForm").attr("action", "/mypage/lectureCertf/list.ajax");

        // 만족도 리스트 
		var initObj = {
            form : $("#lectureCertfForm"),
            container : $("#listContainer"),
            moreBtn : $("#moreBtn"),
            url : "/mypage/lectureCertf/list.ajax",
            pageIndex : $("#lectureCertfForm #pageIndex").val(),
            listCnt : $("#lectureCertfForm #listCnt").val(),
            callbackFunc : function() { $("#totCnt").text(searchMore.totCnt  + "개"); }
		}
		searchMore = new fnc.SearchMore(initObj);
		searchMore.search();
	}

    $(document).ready(function() {
        pageType = $("#lectureCertfForm").data("pageType");

        init_list();

	});

    return {
        close: fn_close,
        searchOpt: fn_search_by_option,
        openPopupDtl: fn_open_popup_detail,
    }
}());