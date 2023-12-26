var mypageInquiryCtrl = (function(){
	
	"use strict";
	
	var searchMore = null;
	var pageType = null;
	
	var popObj = {};
	
	//지점검색조건
	var fn_select_brch_change = function(brchCd)
	{
		$("#brchCd").val(brchCd);
		
		fn_search();
	}
	
	//접수상태검색조건
	var fn_select_status_change = function(faq_status)
	{
		$("#faq_status").val(faq_status);
		
		fn_search();
	}
	
	//팝업 셀렉트 박스
	var fn_select_Div_change = function(obj,cd)
	{
		if($(obj).parent().hasClass('cd1'))
		{
			$("#faq_tp_id").val(cd);
		//	alert("구분cd=" + $("#faq_tp_id").val());
		}
		else if ($(obj).parent().hasClass('cd2'))
		{
			$("#branch_id").val(cd);
		//	alert("지점cd=" + $("#branch_id").val());
		}
	}
	
	var fn_select_detail = function(target, seq, url){
		
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
	
	var fn_detail = function(obj , seq){
       // var ds = obj.parentElement.dataset;
        //alert("ds===" + ds);
        var param = "personal_faq_sq="+seq;
        
        location.href = "/mypage/inquiry/view.do?" + param;
    }
	
	//문의글 삭제
	var fn_delete_inquiry = function(obj, deleteUrl) {
    if (!confirm("삭제하시겠습니까?")) return;
		
    var personal_faq_sq = $("#personal_faq_sq").val();
    		console.log(personal_faq_sq);

    var deleteData = { personal_faq_sq: parseInt(personal_faq_sq, 10) };

	var jsonData = JSON.stringify(deleteData);
			console.log(jsonData);
            jQuery.ajax({
                url: "/mypage/inquiry/delete.ajax",
                type: "post",
                timeout: 30000,
                data: jsonData,
                contentType : "application/json; charset=utf-8",
                dataType: "json",
                async: true,
                cache: false,
                
                success: function(data, status, xhr) {
                    if (parseInt(personal_faq_sq, 10) > 0) {
         			   alert("삭제되었습니다.");
            			location.href = "http://localhost/mypage/inquiry/list.do";
        			} else {
            			alert("삭제 실패했습니다.");
            			return false;
        			}
                },
                error: function(e) {
                    if (e.status == 403) {
                        if (confirm("자동 로그아웃 처리되었습니다. 로그인을 다시 시도해주세요.")) {
                            //moveLoginPage();
                        }
                    } else {}
                }
                
            });

	}
	
	//textarea
	var textarea_onkeyup = function(obj){
        fnc.checkMaxLength(obj);
        $(obj).parents(".form_textarea").find(".check_byte .r_byte").html(obj.value.length);
    }
	
	//검색
	var fn_search = function() {
		
		searchMore.pageIndex = 1;
		searchMore.search();
	}
	
	//초기화
	var init = function() {
		var initObj = {
				form : $("#frmInquiry")
				, container : $("#listContainer")
				, moreBtn : $("#moreBtn")
				, url : "/mypage/inquiry/list.ajax"
				, pageIndex : $("#frmInquiry #pageIndex").val()
				, listCnt : $("#frmInquiry #listCnt").val()
				, callbackFunc : function() { 

				console.log($("#frmInquiry #pageIndex").val());
				console.log($("#frmInquiry #listCnt").val());
				
				$("#totCnt").text(searchMore.totCnt  + "개"); 
				console.log($("#totCnt").text());
				}
		}
	
		searchMore = new fnc.SearchMore(initObj);
		searchMore.search();
		
	}
	
	//문의 팝업
	var fn_open_popup = function(popupId)
	{
		commonScript.openPopupFn(popupId, $(this));
	}
	//팝업 닫기
	var fn_close_popup = function()
	{
		$("#inquiryRegPop").find(".btn_close").click();
		//입력박스
		$("#inquiryRegFrm").find("input").not("input[type='hidden'], input[readonly]").val("");
		$("#inquiryRegFrm").find("textarea").val('');
		$("#inquiryRegFrm").find(".check_byte .r_byte").html('0');
		
		//셀렉트박스 초기화
		//$("#inquiryRegFrm").find(".form_select_div .list_wrap").find("a:eq(1)").click();
		//$("#inquiryRegFrm").find("input[type='checkbox']").prop("checked", false);
		/*$("#inquiryRegFrm").each(function (){
			this.reset();
		});*/
	}
	
	var fn_save_popup = function()
	{
		var frm = {
				form : $("#inquiryRegFrm"),
				obj  : function(name){
					return this.form.find("[name='"+name+"']")
				}
		}
		
		if(frm.obj("faq_title").val().trim() =="")
		{
			alert("제목을 입력해주세요.");
			frm.obj("faq_title").focus();
			return false;
		}
		if($("#faq_tp_id").val() =="")
		{
			alert("문의 유형을 선택해주세요.");
			return false;
		}
		if($("#branch_id").val() =="")
		{
			alert("지점을 선택해주세요");
			return false;
		}
		if(frm.obj("faq_detail").val().trim() == "")
		{
			alert("내용을 입력해주세요");
			frm.obj("faq_detail").focus();
			return false;
		}
		else
		{
			
			if(!confirm("등록하시겠습니까?")) return;
			
			fnc.frmAjax(function(data){
			
            		alert("등록되었습니다.");
            		location.href="http://localhost/mypage/inquiry/list.do";
            		//fn_close_popup();
            	
       	        }, "/mypage/inquiry/insert.ajax", $("#inquiryRegFrm"), "json", true, false, true);
		}
		
	}
	
	$(document).ready(function(){
		
		pageType = $("#frmInquiry").data("pageType");
				
        if(pageType == "list"){
        	init();
        }
		
	});
	
	 return {
		   detail	  : fn_select_detail
		 , detail2	  : fn_detail
		 , deleteInq  : fn_delete_inquiry
		 , selectBrch : fn_select_brch_change
		 , selectStatus : fn_select_status_change
		 , selectDiv  : fn_select_Div_change
		 , textareaOnkeyup: textarea_onkeyup
		 , openPopup  : fn_open_popup
		 , close	  : fn_close_popup
		 , save		  : fn_save_popup
	 }
}());