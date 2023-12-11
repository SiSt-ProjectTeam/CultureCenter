var reviewCtrl = (function(){
    
    "use strict";

    var searchMore = null;
    var pageType = null;


    var prcs_param = function(){
        $("input[name=q]").val($("#q").val());
        $("input[name=orderSet]").val($("#orderSet").val());
        $("input[name=brchCd]").val($("#brchCd").val());
    }
/* 수정하는중
    var prcs_param = function(){
        $("input[name=searchWord]").val($("#searchWord").val());
        $("input[name=orderSet]").val($("#orderSet").val());
        $("input[name=brchCd]").val($("#brchCd").val());
    }
*/
    var fn_search = function(){
        // form param setting
        prcs_param();

        searchMore.pageIndex = 1;
		searchMore.search();
    }
    var fn_search_by_option = function(obj){
        tcCommon.selOptClick(obj);
        fn_search();
    }

    var fn_open_popup_comment = function(){
        commonScript.openPopupFn("#cmntPopup", $(this));
    }

    var fn_regist_comment = function(){
        if(!$("#wrap").data("isLogin")){
            alert("로그인 후 이용가능합니다.");
            location.href = "${contextPath}/login/index.do?rtnUrl=" + encodeURIComponent(window.location.href);
            return;
        }
        var cmntCont1 = $("#cmntCont1").val().trim();
        if(!cmntCont1){
            alert("내용을 입력해주세요.");
            $("#cmntCont1").val("");
            $("#cmntCont1").focus();
            return;
        }
        if(!confirm("댓글을 등록하시겠습니까?")) return;
        
        $("#reviewForm input[name=cmntCont]").val(cmntCont1);

        fnc.frmAjax(function(res){
            $("#cmntCont1").val("");
            $("#cmntCont2").val("");
            if(res.cnt > 0){
                alert("등록되었습니다.");
                searchMore.search();
            }
            if($("#cmntPopup").length > 0){
                $("#cmntPopup").find(".btn_close").click();
            }

        }, "/community/review/comment/insert.ajax", $("#reviewForm"), null, false, true, true);
    }

    var fn_delete_comment = function(obj){
        if(!confirm("댓글을 삭제하시겠습니까?")) return;
        
        var ds = obj.dataset;
        // var newForm = $("<form></form>", {name: "newForm"});
        // newForm.append($('<input/>', {type: 'hidden', name: 'brchCd', value: ds.brchCd }));
        // newForm.append($('<input/>', {type: 'hidden', name: 'yy', value: ds.yy }));
        // newForm.append($('<input/>', {type: 'hidden', name: 'lectSmsterCd', value: ds.lectSmsterCd }));
        // newForm.append($('<input/>', {type: 'hidden', name: 'lectCd', value: ds.lectCd }));
        // newForm.append($('<input/>', {type: 'hidden', name: 'tcCdNo', value: ds.tcCdNo }));
        // newForm.append($('<input/>', {type: 'hidden', name: 'memberNo', value: ds.mbrNo }));
        // newForm.append($('<input/>', {type: 'hidden', name: 'sortSeqno', value: ds.sortSeqno }));
        $("#reviewForm input[name=sortSeqno]").val(ds.sortSeqno);

        fnc.frmAjax(function(res){
            if(res.cnt > 0){
                alert("삭제되었습니다.");
                searchMore.search();
            }
        }, "/community/review/comment/delete.ajax", $("#reviewForm"), null, false, true, true);
        
    }

    var fn_detail = function(obj){
        var ds = obj.dataset;
        var param = "brchCd="+ds.brchCd+"&yy="+ds.yy+"&lectSmsterCd="+ds.lectSmsterCd+"&lectCd="+ds.lectCd+"&tcCdNo="+ds.tcCdNo+"&memberNo="+ds.mbrNo;
        location.href = "/community/review/dtl.do?" + param;
    }

    var init_list = function(){
		var pathname = "/" + window.location.pathname.split("/")[1];
		var origin = window.location.origin;	
		var contextPath = origin + pathname;
		
		var searchMore = null;

        // 수강후기 리스트 
		var initObj = {
            form : $("#reviewForm"),
            container : $("#listContainer"),
            moreBtn : $("#moreBtn"),
            url : contextPath+"/community/review/list.ajax",
            pageIndex : $("#reviewForm #pageIndex").val(),
            listCnt : $("#reviewForm #listCnt").val(),
            callbackFunc : function() {
			  $("#totCnt").text(searchMore.totCnt  + "개"); }
		}
		searchMore = new fnc.SearchMore(initObj);
		searchMore.search();

	}

    var init_dtl = function(){
        // 댓글 리스트
        var initObj = {
            form : $("#reviewForm"),
            container : $("#listContainer"),
            moreBtn : $("#moreBtn"),
            url : "/community/review/comment/list.ajax",
            pageIndex : $("#reviewForm #pageIndex").val(),
            listCnt : $("#reviewForm #listCnt").val(),
            callbackFunc : function() { $("#totCnt").text(searchMore.totCnt); }
		}
		searchMore = new fnc.SearchMore(initObj);
		searchMore.search();
    }

    var fn_query_onkeyup = function(){
        if (window.event.keyCode == 13) {
            fn_search();
        }
    }

    var fn_regist_onkeyup = function(obj){
        $("#cmntCont1").val(obj.value);
        $("#cmntCont2").val(obj.value);
        if (window.event.keyCode == 13) {
            fn_regist_comment();
        }
    }

    $(document).ready(function() {
        pageType = $("#reviewForm").data("pageType");
        
        if(pageType == "list"){
		    init_list();
        }
        else if(pageType == "dtl"){
            init_dtl();
        }
	});

    return {
        search : fn_search,
        searchOpt: fn_search_by_option,
        openPopupCmnt: fn_open_popup_comment,
        registCmnt: fn_regist_comment,
        deleteCmnt: fn_delete_comment,
        detail: fn_detail,
        queryOnkeyup: fn_query_onkeyup,
        registOnkeyup: fn_regist_onkeyup,
    }
});