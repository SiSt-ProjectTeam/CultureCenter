var cooperRequestCtrl = (function(){
    var pathname = "/" + window.location.pathname.split("/")[1];	
	var origin = window.location.origin;	
	var contextPath = origin + pathname;
	
    "use strict";
    
    var layer = document.getElementById("application_popup");

    var fn_close = function(){
        $("#application_popup").fadeOut(300, function(){
            $("body, #wrap").removeClass("stop_scroll");
            $("body, html").off("scroll touchmove mousewheel");
        });
        
    }
    var fn_btn_close = function(){
        var step = $("#popupContent .for_padding").data("step");
        switch (step) {
            case 1: case 2:
                fn_cancel();
                break;
            case 3:
                fn_close();
                break;
        }
    }

    var fn_next = function(step){
        switch (step) {
            case 1:
                if(!fn_validate1()) return;
                fn_submit1();
                break;
            case 2:
                fn_process_data();  //데이터 가공
                if(!fn_validate2()) return;
                if(!confirm("신청서를 제출하시겠습니까?")) return;
                fn_submit2();
                break;
        }
    }

    var fn_cancel = function(){
        if(confirm("제휴 신청을 취소하시겠습니까?")){
            alert("지원 취소되었습니다.");
            fn_close();
        }
    }

    /**
     * 제휴신청정보 제출
     * @returns 통과: true / 실패: false
     */
    var fn_submit1 = function(){
        fnc.bscAjax(set_popup_content, contextPath+"/information/application/cooperation/request2.ajax", "html", false, true, true);
    }
    var fn_submit2 = function(){
        fnc.frmAjax(set_popup_content, contextPath+"/information/application/cooperation/submit.ajax", $("#request_form"), "html", false, true, true);
    }

    var set_popup_content = function(html){

        var $layer = $("#application_popup");
        $layer.css("display", "block");
        
        var $con = $("#popupContent");
        $con.html(html);

        commonScript.resizeFn();    //팝업 사이즈 조정
        commonScript.formChkFn();   //셀렉트박스 스크립트 초기화
    }

    //전송 전 데이터 가공
    var fn_process_data = function(){
        //이메일
        var email = $("#email").val() + "@" + $("#emailAddrCd").val();
        $("input[name=email]").val(email);

    }

    /**
     * 유효성 검사
     * @returns 통과: true / 실패: false
     */
    var fn_validate1 = function(){
        var agreeChk1 = document.getElementById("agreeChk1");
        
        if(!agreeChk1.checked){
            alert("개인정보 수집/이용에 동의해주세요.");
            return false;
        }
        
        return true;
    }
    var fn_validate2 = function(){
        var valiList = [
            {$obj: $("#buenNm"), msg: "업체명을 입력해 주세요."},
            {$obj: $("#chrgprNm"), msg: "담당자명을 입력해 주세요."},
            {$obj: $("#hpStNo"), msg: "연락처를 입력해 주세요."},
            {$obj: $("#hpMidNo"), msg: "연락처를 입력해 주세요."},
            {$obj: $("#hpLastNo"), msg: "연락처를 입력해 주세요."},
            {$obj: $("#email"), msg: "이메일을 입력해 주세요."},
            {$obj: $("#emailAddrCd"), msg: "이메일을 입력해 주세요."},
            {$obj: $("#appCont"), msg: "신청내용을 작성해 주세요."}, 
        ]
        if(!tcCommon.validateInfo(valiList)) return false;

        var value = $("#appCont").val().trim();
        if(value.length < 50){
            alert("신청내용을 50자 이상 작성해 주세요.");
            $("#appCont").focus();
            return false;
        }

        return true;
    }

    return {
        close: fn_close,
        btnClose: fn_btn_close,
        cancel: fn_cancel,
        next: fn_next,
    }
}());