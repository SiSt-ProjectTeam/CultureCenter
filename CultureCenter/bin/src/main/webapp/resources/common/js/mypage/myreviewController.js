var myreviewCtrl = (function(){
    
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
        var param = "brchCd="+ds.brchCd+"&yy="+ds.yy+"&lectSmsterCd="+ds.lectSmsterCd+"&lectCd="+ds.lectCd+"&atlctRsvNo="+ds.atlctRsvNo;
        var url = "/mypage/myreview/insert.do?" + param;

        fnc.bscAjax(function(data){
            $("#insertPopup").html(data);
            commonScript.openPopupFn("#insertPopup", $(this));

            commonscript_star();
            
        }, url, null, false, true, true);
    }

    var fn_regist_review = function(){
    	
    	var tit =  $("input[name=titNm]").val().trim();
    	var cont = $("textarea[name=rvwCont]").val().trim();
    	var stfdgPntVal = 5 - $("#starDiv").find(".star.blank").length;
        $("#stfdgPnt").val(stfdgPntVal);

    	if(tit == ""){
    		alert("제목을 입력해주세요.")
    		$("input[name=titNm]").val("");
            $("input[name=titNm]").focus();
    		return false;
    	}
        if(cont.length == 0){
    		alert("내용을 입력해주세요.")
    		$("textarea[name=rvwCont]").val("");
    		$("textarea[name=rvwCont]").focus();
    		return false;
    	}

        if(!confirm("후기를 등록하시겠습니까?")) return;

        
        
        fnc.fileFrmAjax(function(data){
            alert("등록되었습니다.");
            location.reload();
        }, "/mypage/myreview/insert.ajax", $("#insertForm"), null, false, true, true);
    }

    var fn_detail = function(obj){
        var ds = obj.parentElement.dataset;
        var param = "brchCd="+ds.brchCd+"&yy="+ds.yy+"&lectSmsterCd="+ds.lectSmsterCd+"&lectCd="+ds.lectCd+"&tcCdNo="+ds.tcCdNo+"&memberNo="+ds.mbrNo;
        location.href = "/mypage/myreview/dtl.do?" + param;
    }

    var init_list = function(){

        $("#reviewForm").attr("action", "/mypage/myreview/list.ajax");

        // 수강후기 리스트 
		var initObj = {
            form : $("#reviewForm"),
            container : $("#listContainer"),
            moreBtn : $("#moreBtn"),
            url : "/mypage/myreview/list.ajax",
            pageIndex : $("#reviewForm #pageIndex").val(),
            listCnt : $("#reviewForm #listCnt").val(),
            callbackFunc : function() { $("#totCnt").text(searchMore.totCnt  + "개"); }
		}
		searchMore = new fnc.SearchMore(initObj);
		searchMore.search();
	}

    var commonscript_star = function(){
        $("#insertForm .star").each(function(idx){
            // star_rating에 click_able 클래스가 있을 때만, 별 클릭 작동
            if($(this).parent(".star_rating").hasClass("click_able")){

                var starLeng = $("#insertForm .star").not(".blank").length;
                
                $(this).on("click", function(){
                starLeng = $("#insertForm .star").not(".blank").length;
                // 한번 더 눌렀을 때 off
                // if($(this).index() + 1 == starLeng) {
                //   $("#insertForm .star").addClass("blank");
                // }
                
                // 증가
                if($(this).index() + 1 > starLeng){
                    $(this).prevAll().removeClass("blank");
                    $(this).removeClass("blank");
                    // console.log("증가", starLeng);
                }
                // 감소
                if($(this).index() + 1 < starLeng){
                    $(this).nextAll().addClass("blank");
                    $(this).removeClass("blank");
                    // console.log("감소", starLeng);
                }
                });
            }
        })
    }

    $(document).ready(function() {
        pageType = $("#reviewForm").data("pageType");

        if(pageType == "list"){
            init_list();
        }

	});

    return {
        search : fn_search,
        searchOpt: fn_search_by_option,
        openPopupInsert: fn_open_popup_insert,
        registRvw: fn_regist_review,
        close: fn_close,
        detail: fn_detail,
    }
}());