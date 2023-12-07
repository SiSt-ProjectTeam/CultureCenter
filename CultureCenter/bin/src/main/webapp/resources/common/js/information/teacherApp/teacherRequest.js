var teacherRequestCtrl = (function(){
	
    var pathname = "/" + window.location.pathname.split("/")[1];	
	var origin = window.location.origin;	
	var contextPath = origin + pathname;
	
    "use strict";
    
    var fn_close = function(){
        $("#application_popup").fadeOut(300, function(){
            $("body, #wrap").removeClass("stop_scroll");
            $("body, html").off("scroll touchmove mousewheel");
        });

    }
    var fn_btn_close = function(){
        var step = $("#popupContent .for_padding").data("step");
        switch (step) {
            case 1:
                if(confirm("강사지원을 취소하시겠습니까?")) fn_close();
                break;
            case 2:
                if(confirm("임시저장 하시겠습니까?")) fn_save();
                else fn_close();
                break;
            case 3:
                fn_close();
                break;
        }
    }

    var fn_next = function(step){
console.log("fn_next step : " + step)	
        switch (step) {
            case 1:
                if(!fn_validate1()) return;
console.log("fn_next case 1....")
                fn_submit1();
                break;
            case 2:
                if(!fn_validate2()) return;
                if(!fn_duplicate2()) return;
                if(!confirm("지원서를 제출하시겠습니까?")) return;
                fn_submit2();
                break;
        }
    }

    var fn_save = function(){
        if(!fn_duplicate2()) return;
        
        fn_process_data();
        if($("input[name=carrInfoClctAgrYn]").val() == 'Y'){
            tcRqst2HistCtrl.processData();
			("#form").val(json);
			
        }
        
        var json = $("#request_form").serializeJSON();
        $("#form").val(json);

        fnc.fileFrmAjax(function(data){
            switch (data.errorCode) {
                case "0":
                    alert("지원중인 강사 정보가 없습니다. 처음부터 다시 시도하여 주십시오.");
                    fn_close();
                    return;
                case "2":
                    alert("이미 강사지원을 완료하였습니다.");
                    fn_close();
                    return;
                case "3":
                    alert("이미 강사 권한이 있습니다.");
                    fn_close();
                    return;
                case "-2":
                    alert("중복된 강사명+휴대폰번호입니다.");
                    return;
                default:
                    break;
            }

            if(data.cnt > 0){
                alert("임시 저장되었습니다.");
                fn_close();
            }
            else {
                alert("다시 시도해주세요.");
            }
        }, contextPath+"/information/application/teacher/save.ajax", $("#submit_form"), null, false, true, true);
    }

    var fn_cancel = function(step){
        if(!confirm("강사지원을 정말 취소하시겠습니까?\n저장된 내용을 모두 삭제합니다.")) return;

        if(step == 1){
            alert("지원 취소되었습니다.");
            fn_close();
        }
        else{
            fnc.frmAjax(function(data){
                switch (data.errorCode) {
                    case 0:
                        alert("지원 취소할 강사 정보가 없습니다. 처음부터 다시 시도하여 주십시오.");
                        fn_close();
                        return;
                    case 2:
                        alert("강사지원을 완료하면 취소할 수 없습니다.");
                        fn_close();
                        return;
                    case 3:
                        alert("이미 강사 권한이 있어 취소할 수 없습니다.");
                        fn_close();
                        return;
                    default:
                        break;
                }
                
                alert("지원 취소되었습니다.");
                fn_close();
            }, contextPath+"/information/application/teacher/delete.ajax", $("#request_form"), false, true, true);
        }
        
    }

    /**
     * 강사정보 제출
     * @returns 통과: true / 실패: false
     */
    var fn_submit1 = function(){
        fnc.frmAjax(function(data){
            switch (data.errorCode) {
                case 1:
                    alert("강사 지원 정보가 존재합니다.");
                    break;
                case 2:
                    alert("이미 강사지원을 완료하였습니다.");
                    fn_close();
                    return;
                case 3:
                    alert("이미 강사 권한이 있습니다.");
                    fn_close();
                    return;
                default:
                    break;
            }

            if(data.cnt > 0){
console.log("fn_submit1 if..")
                fnc.bscAjax(set_popup_content, contextPath+"/information/application/teacher/request.do" , "html", false, false, false);
            } else {
                alert("다시 시도해주세요.");
                fn_close();
            }

        }, contextPath+"/information/application/teacher/insert.ajax", $("#request_form"), "json", false, true, true);
    }
    var fn_submit2 = function(){
        
        fn_process_data();
        if($("input[name=carrInfoClctAgrYn]").val() == 'Y'){
            tcRqst2HistCtrl.processData();
        }
        var json = $("#request_form").serializeJSON();
        $("#form").val(json);

        fnc.fileFrmAjax(set_popup_content, contextPath+"/information/application/teacher/submit.ajax", $("#submit_form"), "html", false, true, true);
    }

    var set_popup_content = function(html){
        if(html){
            var $con = $("#popupContent");
            $con.html(html);

            if(html.indexOf("<!doctype html>") > -1){
                //blank 페이지
                init();
                fn_close();
                return;
            }
            
            commonScript.resizeFn();    //팝업 사이즈 조정
            commonScript.formChkFn();   //셀렉트박스 스크립트 초기화
            filterSelect();
        }
        else {
            alert("강사지원 제출이 완료되었습니다.");
            fn_close();
        }
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

        // 기본정보
        var bdayVali = $("#bdayEssential").hasClass("essential") ? 
            {$obj: $("#bday"), msg: "생일을 입력해 주세요."} : undefined
        
        var valiList = [
            {$obj: $("input[name=phtFileId]"), msg: "프로필 사진을 선택해 주세요."},
            {$obj: $("#tcNm"), msg: "강사명을 입력해 주세요."},
            bdayVali,
            {$obj: $("#hpMidNo"), msg: "휴대전화를 입력해 주세요."},
            {$obj: $("#hpLastNo"), msg: "휴대전화를 입력해 주세요."},
            {$obj: $("#email"), msg: "이메일을 입력해 주세요."},
            {$obj: $("#emailAddrCd"), msg: "이메일을 입력해 주세요."},
            {$obj: $("#addr"), msg: "주소를 입력해 주세요."},
            {$obj: $("#dtlAddr"), msg: "주소를 입력해 주세요."}
        ]
        if(!tcCommon.validateInfo(valiList)) return false;

        // 경력정보수집 동의시
        if($("input[name=carrInfoClctAgrYn]").val() == 'Y' && !tcRqst2HistCtrl.validateInfosAll()) return false;

        // 출강정보
        var valiList = [
            {$obj: $("#frstHopeBrchCd"), msg: "희망 지점을 선택해주세요."},
            {$obj: $("#secHopeBrchCd"), msg: "희망 지점을 선택해주세요."},
            {$obj: $("#frstLrclsCtegryCd"), msg: "희망 분야를 선택해주세요."},
            {$obj: $("#frstMdclsCtegryCd"), msg: "희망 분야를 선택해주세요."},
            {$obj: $("#secLrclsCtegryCd"), msg: "희망 분야를 선택해주세요."},
            {$obj: $("#secMdclsCtegryCd"), msg: "희망 분야를 선택해주세요."},
            // {$obj: $("#hopeDaywVal"), msg: "희망 요일을 선택해주세요."},
            {$obj: $("#hopeStHh"), msg: "희망 시간을 선택해주세요."},
            {$obj: $("#hopeStMi"), msg: "희망 시간을 선택해주세요."},
            {$obj: $("#hopeEndHh"), msg: "희망 시간을 선택해주세요."},
            {$obj: $("#hopeEndMi"), msg: "희망 시간을 선택해주세요."}
        ]
        if(!tcCommon.validateInfo(valiList)) return false;

        // 희망시간
        var stTime = fnc.nvl($("#hopeStHh").val(),"00") + fnc.nvl($("#hopeStMi").val(),"00");
        var endTime = fnc.nvl($("#hopeEndHh").val(),"00") + fnc.nvl($("#hopeEndMi").val(),"00"); 

        if(stTime >= endTime){
            alert("희망시간의 종료시간은 시작시간 후여야 합니다.");
            $("#hopeStHh").focus();
            return false;
        }

        return true;
    }
    /**
     * 중복 검사
     * @returns 통과: true / 실패: false
     */
    var fn_duplicate2 = function(){
        var a, b;
        a = $("#frstHopeBrchCd").val().trim();
        b = $("#secHopeBrchCd").val().trim();
        if(a && a == b){
            alert("희망 지점이 중복되었습니다. 다른 지점을 선택해주세요.");
            tcCommon.focusObject($("#secHopeBrchCd"));
            return false;
        }

        a = $("#frstLrclsCtegryCd").val().trim();
        b =  $("#secLrclsCtegryCd").val().trim();
        if(a && a == b){
            a = $("#frstMdclsCtegryCd").val().trim();
            b = $("#secMdclsCtegryCd").val().trim();
            if(a && a == b){
                alert("희망 분야가 중복되었습니다. 다른 분야를 선택해주세요.");
                tcCommon.focusObject($("#secMdclsCtegryCd"));
                return false;
            }
        }

        return true;
    }

    //전송 전 데이터 가공
    var fn_process_data = function(){
        //희망지점
        var frstHopeBrchCdNm = $("#frstHopeBrchCd").siblings(".btn_open").find('span').html();
        $("#frstHopeBrchCdNm").val(frstHopeBrchCdNm);
        var secHopeBrchCdNm = $("#secHopeBrchCd").siblings(".btn_open").find('span').html();
        $("#secHopeBrchCdNm").val(secHopeBrchCdNm);

        //희망분야
        var frstLrclsCtegryCdNm = $("#frstLrclsCtegryCd").siblings(".btn_open").find('span').html();
        $("#frstLrclsCtegryCdNm").val(frstLrclsCtegryCdNm);
        var secLrclsCtegryCdNm = $("#secLrclsCtegryCd").siblings(".btn_open").find('span').html();
        $("#secLrclsCtegryCdNm").val(secLrclsCtegryCdNm);
        
        // 요일
        var btnTot = $("#hopeDaywValDiv").find(".total.btn.on").get();
        var btnList = [];
        var hopeDaywVal = "";
        var hopeDaywValNm = "";

        if(btnTot.length > 0){
            btnList = $("#hopeDaywValDiv").find(".btn").not(".total").get();
        }
        else {
            btnList = $("#hopeDaywValDiv").find(".btn.on").get();
        }

        var btn1 = btnList.shift();
        hopeDaywVal = btn1.dataset.value;
        hopeDaywValNm = $(btn1).find("span").html();
        for(var btn of btnList){
            hopeDaywVal += "|" + btn.dataset.value;
            hopeDaywValNm += "|" + $(btn).find("span").html();
        }

        $("#hopeDaywVal").val(hopeDaywVal);
        $("#hopeDaywValNm").val(hopeDaywValNm);
    }

    //강사유형
    var tcTpCd_onchange = function(obj){
        if(obj.value == 1){
            //개인이면 면세체크 비활성화
            $("#txfrBizprYn").attr("checked", false);
            $("#txfrBizprYn").attr("disabled", true);

            //생일 필수입력
            $("#bdayEssential").addClass("essential");

        }
        else if(obj.value == 2){
            // 개인사업자
            $("#txfrBizprYn").attr("disabled", false);

            //생일 필수입력
            $("#bdayEssential").addClass("essential");
        }
        else {
            // 법인사업자
            $("#txfrBizprYn").attr("disabled", false);

            //생일 선택입력
            $("#bdayEssential").removeClass("essential");
        }
    };

    // 카테고리 클릭
    var select_option_click_ctgry = function(obj){
        if(obj.dataset.ctegryId === undefined){
            //level 1 카테고리 선택시 level2 목록 내용 변경
            var id = $(obj).parents(".form_select_w").attr("id");
            if(id == "firstLrDiv"){
                var btnArr = $("#firstMdDiv .scroll_wrap .btn_link").get();
                for(var btn of btnArr){
                    if(btn.dataset.ctegryId != ""){
                        if(btn.dataset.ctegryId != obj.dataset.value) {
                            $(btn).css("display", "none");
                        }
                        else {
                            $(btn).css("display", "");
                        }
                    }
                }
                tcCommon.resetSelectbox("#frstMdclsCtegryCd", "선택");

            } else if(id == "secLrDiv"){
                var btnArr = $("#secMdDiv .scroll_wrap .btn_link").get();
                for(var btn of btnArr){
                    if(btn.dataset.ctegryId != ""){
                        if(btn.dataset.ctegryId != obj.dataset.value) {
                            $(btn).css("display", "none");
                        }
                        else {
                            $(btn).css("display", "");
                        }
                    }
                }
                tcCommon.resetSelectbox("#secMdclsCtegryCd", "선택");
            }
        }

        tcCommon.selOptClick(obj);
    }

    //희망 요일
    var select_option_click_dayw = function(obj){
        if($(obj).hasClass("total")){
            $(obj).siblings(".btn").removeClass("on");
            $(obj).removeClass("on"); //전체 클릭시 항상 on
        }
        else {
            if($("#hopeDaywValDiv").find(".btn.on").length == 1 && $(obj).hasClass("on")){
                $("#hopeDaywValDiv").find(".btn.total").addClass("on");
            }
            else {
                $(obj).siblings(".total").removeClass("on");
            }
        }

        // 이후 script.js 버튼이벤트 동작
    }

    //카카오 주소검색
    var search_address = function(){
        new daum.Postcode({
            oncomplete: function(data) {
                $("#pstno").val(data.zonecode); //우편번호
                $("#addr").val(data.address); //주소
                
                $("#dtlAddr").attr("readonly", false);
            }
        }).open();
    }

    return {
        close: fn_close,
        btnClose: fn_btn_close,
        cancel: fn_cancel,
        save: fn_save,
        next: fn_next,
        selOptClick_ctgry: select_option_click_ctgry,
        selOptClick_dayw: select_option_click_dayw,
        tcTpCdOnchange: tcTpCd_onchange,
        searchAddr: search_address,
    }
}());