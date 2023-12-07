var teacherEvalCtrl = (function(){
    
    "use strict";

    var searchMore = null;
    var pageType = null;

    var fn_close = function(){
        $("#insertPopup").fadeOut(300, function(){
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

    var fn_open_popup_insert = function(obj){

        var ds = obj.parentElement.dataset;
        var newForm = $("<form></form>", {name: "newForm"});
        newForm.append($('<input/>', {type: 'hidden', name: 'brchCd', value: ds.brchCd }));
        newForm.append($('<input/>', {type: 'hidden', name: 'yy', value: ds.yy }));
        newForm.append($('<input/>', {type: 'hidden', name: 'lectSmsterCd', value: ds.lectSmsterCd }));
        newForm.append($('<input/>', {type: 'hidden', name: 'webPath', value: ds.webPath }));
        newForm.append($('<input/>', {type: 'hidden', name: 'lectStatCdNm', value: ds.lectStatCdNm }));
        newForm.append($('<input/>', {type: 'hidden', name: 'lrclsCtegryCdNm', value: ds.lrclsCtegryCdNm }));
        newForm.append($('<input/>', {type: 'hidden', name: 'mdclsCtegryCdNm', value: ds.mdclsCtegryCdNm }));
        newForm.append($('<input/>', {type: 'hidden', name: 'lectCd', value: ds.lectCd }));
        newForm.append($('<input/>', {type: 'hidden', name: 'lectNm', value: ds.lectNm }));
        newForm.append($('<input/>', {type: 'hidden', name: 'brchCdNm', value: ds.brchCdNm }));
        newForm.append($('<input/>', {type: 'hidden', name: 'tcNm', value: ds.tcNm }));
        newForm.append($('<input/>', {type: 'hidden', name: 'lectSmsterCdNm', value: ds.lectSmsterCdNm }));
        newForm.append($('<input/>', {type: 'hidden', name: 'lectStDtm', value: ds.lectStDtm }));
        newForm.append($('<input/>', {type: 'hidden', name: 'lectEndDtm', value: ds.lectEndDtm }));

        fnc.frmAjax(function(data){
            $("#insertPopup").html(data);
            commonScript.openPopupFn("#insertPopup", $(this));

            // 만족도 평가 체크기능 
            $(".grade").each(function(){
                $(this).on("click", function(){
                    $(".grade").removeClass("grade_on");
                    $(this).addClass("grade_on");
                })
            });

        }, "/mypage/teachereval/insertpop.ajax", newForm, null, false, true, true);
    }

    var fn_regist_evaluation = function(){
        
        /* 2023.03.31 만족도 평가 내용 입력 없이 저장 가능 처리
    	var cont = $("textarea[name=memoCont]").val().trim();
    	if(cont.length ==0){
    		alert("후기를 입력해주세요.")
    		 $("textarea[name=memoCont]").val("");
    		 $("textarea[name=memoCont]").focus();
    		return false;
		}
        */
    	
    	if(!confirm("등록하시겠습니까?")) return;

        var evlPnt = $("#gradeDiv").find(".grade_on").data("value");
        $("#evlPnt").val(evlPnt);

        fnc.frmAjax(function(data){
            alert("등록되었습니다.");
            searchMore.search();
            fn_close();
        }, "/mypage/teachereval/insert.ajax", $("#insertForm"), null, false, true, true);
    }

    var init_list = function(){

        $("#teacherEvalForm").attr("action", "/mypage/teachereval/list.ajax");

        // 만족도 리스트 
		var initObj = {
            form : $("#teacherEvalForm"),
            container : $("#listContainer"),
            moreBtn : $("#moreBtn"),
            url : "/mypage/teachereval/list.ajax",
            pageIndex : $("#teacherEvalForm #pageIndex").val(),
            listCnt : $("#teacherEvalForm #listCnt").val(),
            callbackFunc : function() { $("#totCnt").text(searchMore.totCnt  + "개"); }
		}
		searchMore = new fnc.SearchMore(initObj);
		searchMore.search();
	}

    $(document).ready(function() {
        pageType = $("#teacherEvalForm").data("pageType");

        init_list();

	});

    return {
        cancel: fn_close,
        searchOpt: fn_search_by_option,
        openPopupInsert: fn_open_popup_insert,
        registEvl: fn_regist_evaluation,
    }
}());