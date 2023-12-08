var tcRqst2HistCtrl = (function(){
    
    "use strict";
    
    var add_edu_info = function(){
        var html = $("#eduInfo").get(0).outerHTML.replace(/id="eduInfo"/g, "").replace(/style="display: none;"/g, "");

        var $inputCd = $("#schlClCd");
        var cdNm = $inputCd.siblings(".btn_open").find("span").eq(0).html();
        html = html.replace(/\$schlClCd\$/g, $inputCd.val());
        html = html.replace(/\$schlClCdNm\$/g, cdNm);

        $inputCd = $("#grdtClCd");
        cdNm = $inputCd.siblings(".btn_open").find("span").eq(0).html();
        html = html.replace(/\$grdtClCd\$/g, $inputCd.val());
        html = html.replace(/\$grdtClCdNm\$/g, cdNm);

        cdNm = $("#yy").val() ? $("#yy").val() : "년도 선택";
        html = html.replace(/\$yy\$/g, $("#yy").val());
        html = html.replace(/\$yyNm\$/g, cdNm);

        html = html.replace(/\$schlNm\$/g, $("#schlNm").val());
        html = html.replace(/\$mjrNm\$/g, $("#mjrNm").val());

        tcCommon.resetSelectbox("#schlClCd", "학교 선택");
        tcCommon.resetSelectbox("#grdtClCd", "졸업상태 선택");
        tcCommon.resetSelectbox("#yy", "년도 선택");

        tcCommon.resetInput("#schlNm");
        tcCommon.resetInput("#mjrNm");

        return html;
    }

    var add_hist_info = function(){
        var html = $("#histInfo").get(0).outerHTML.replace(/id="histInfo"/g, "").replace(/style="display: none;"/g, "");
        
        html = html.replace(/\$mjrNm\$/g, $("#mjrNm").val());
        html = html.replace(/\$histPlcNm\$/g, $("#histPlcNm").val());
        html = html.replaceAll(/\$histStDt\$/g, $("#histStDt").val());
        html = html.replaceAll(/\$histEndDt\$/g, $("#histEndDt").val());
        
        tcCommon.resetInput("#mjrNm");
        tcCommon.resetInput("#histPlcNm");
        tcCommon.resetInput("#histStDt");

        return html;
    }

    var add_awrd_info = function(){
        var html = $("#awrdInfo").get(0).outerHTML.replace(/id="awrdInfo"/g, "").replace(/style="display: none;"/g, "");
        
        html = html.replace(/\$issueAgncNm\$/g, $("#awrdIssueAgncNm").val());
        html = html.replace(/\$issueNm\$/g, $("#awrdIssueNm").val());
        html = html.replaceAll(/\$awrdIssueDt\$/g, $("#awrdIssueDt").val());
        
        tcCommon.resetInput("#awrdIssueAgncNm");
        tcCommon.resetInput("#awrdIssueNm");
        tcCommon.resetInput("#awrdIssueDt");
        
        return html;
    }

    var add_athctf_info = function(){
        var html = $("#athctfInfo").get(0).outerHTML.replace(/id="athctfInfo"/g, "").replace(/style="display: none;"/g, "");
        
        html = html.replace(/\$issueAgncNm\$/g, $("#athctfIssueAgncNm").val());
        html = html.replace(/\$issueNm\$/g, $("#athctfIssueNm").val());
        html = html.replaceAll(/\$issueDt\$/g, $("#athctfIssueDt").val());
        
        tcCommon.resetInput("#athctfIssueAgncNm");
        tcCommon.resetInput("#athctfIssueNm");
        tcCommon.resetInput("#athctfIssueDt");
        
        return html;
    }

    var validate_edu_info = function(){
        var valiList = [
            {$obj: $("#schlClCd"), msg: "학력을 선택해 주세요."},
            {$obj: $("#grdtClCd"), msg: "졸업상태를 선택해 주세요."},
            {$obj: $("#yy"), msg: "년도를 선택해 주세요."},
            {$obj: $("#schlNm"), msg: "학교명을 입력해 주세요."},
            {$obj: $("#mjrNm"), msg: "전공을 선택해 주세요."}
        ]

        return tcCommon.validateInfo(valiList);
    }
    var validate_edu_infos = function(){
        var valiList = [
            {$obj: $("#request_form input[name='tceduList[][schlClCd]']"), msg: "학력을 선택해 주세요."},
            {$obj: $("#request_form input[name='tceduList[][grdtClCd]']"), msg: "졸업상태를 선택해 주세요."},
            {$obj: $("#request_form input[name='tceduList[][yy]']"), msg: "년도를 선택해 주세요."},
            {$obj: $("#request_form input[name='tceduList[][schlNm]']"), msg: "학교명을 입력해 주세요."},
            {$obj: $("#request_form input[name='tceduList[][mjrNm]']"), msg: "전공을 선택해 주세요."}
        ]

        return tcCommon.validateInfos(valiList);
    }

    var validate_hist_info = function(){
        var valiList = [
            {$obj: $("#histPlcNm"), msg: "기관명을 입력해 주세요."},
            {$obj: $("#histStDt"), msg: "시작일을 입력해 주세요."},
            {$obj: $("#histEndDt"), msg: "종료일을 입력해 주세요."},
        ]

        return tcCommon.validateInfo(valiList);
    }
    var validate_hist_infos = function(){
        var valiList = [
            {$obj: $("#request_form input[name='tchistList[][histPlcNm]']"), msg: "기관명을 입력해 주세요."},
            {$obj: $("#request_form input[name='tchistList[][histStDt]']"), msg: "시작일을 입력해 주세요."},
            {$obj: $("#request_form input[name='tchistList[][histEndDt]']"), msg: "종료일을 입력해 주세요."},
        ]

        return tcCommon.validateInfos(valiList);
    }

    var validate_awrd_info = function(){
        var valiList = [
            {$obj: $("#awrdIssueAgncNm"), msg: "발행기관을 입력해 주세요."},
            {$obj: $("#awrdIssueNm"), msg: "수상내역을 입력해 주세요."},
            {$obj: $("#awrdIssueDt"), msg: "수상일 입력해 주세요."},
        ]

        return tcCommon.validateInfo(valiList);

    }
    var validate_awrd_infos = function(){
        var valiList = [
            {$obj: $("#request_form input[name='tcawrdList[][issueAgncNm]']"), msg: "발행기관을 입력해 주세요."},
            {$obj: $("#request_form input[name='tcawrdList[][issueNm]']"), msg: "수상내역을 입력해 주세요."},
            {$obj: $("#request_form input[name='tcawrdList[][issueDt]']"), msg: "수상일 입력해 주세요."},
        ]

        return tcCommon.validateInfos(valiList);

    }
    var validate_athctf_info = function(){
        var valiList = [
            {$obj: $("#athctfIssueAgncNm"), msg: "발행기관을 입력해 주세요."},
            {$obj: $("#athctfIssueNm"), msg: "자격증명을 입력해 주세요."},
            {$obj: $("#athctfIssueDt"), msg: "취득일 입력해 주세요."},
        ]

        return tcCommon.validateInfo(valiList);
    }
    var validate_athctf_infos = function(){
        var valiList = [
            {$obj: $("#request_form input[name='tcauthctfList[][issueAgncNm]']"), msg: "발행기관을 입력해 주세요."},
            {$obj: $("#request_form input[name='tcauthctfList[][issueNm]']"), msg: "자격증명을 입력해 주세요."},
            {$obj: $("#request_form input[name='tcauthctfList[][issueDt]']"), msg: "취득일 입력해 주세요."},
        ]

        return tcCommon.validateInfos(valiList);
    }
    
    var validate_infos_all = function(){
        if(!validate_edu_infos()) return false;
        if(!validate_hist_infos()) return false;
        if(!validate_awrd_infos()) return false;
        if(!validate_athctf_infos()) return false;

        return true;
    }

    var add_info = function(obj){
        var html = "";

        switch (obj.dataset.value) {
            case "edu":
                if(!validate_edu_info()) return;
                html = add_edu_info();
                break;
            case "hist":
                if(!validate_hist_info()) return;
                html = add_hist_info();
                break;
            case "awrd":
                if(!validate_awrd_info()) return;
                html = add_awrd_info();
                break;
            case "athctf":
                if(!validate_athctf_info()) return;
                html = add_athctf_info();
                break;
        }
        
        $(obj).parents(".input_wrap").find(".plus_div_w").append(html);
        
        //셀렉트박스 스크립트 초기화
        if(obj.dataset.value == "edu") commonScript.formChkFn();
        
    }

    var del_info = function(e){
        $(e.target).parents(".plus_div").remove();
    }

    //임시저장 전송 전 데이터 가공
    var fn_process_data = function(){
        //plus_div가 아닌 임력폼에 값 하나도 없을 시 name 삭제
        var objs = new Object();
        objs.eduIdList = ["schlClCd", "grdtClCd", "yy", "schlNm", "mjrNm"];
        objs.histIdList = ["histPlcNm", "histStDt", "histEndDt"];
        objs.awrdIdList = ["awrdIssueAgncNm", "awrdIssueNm", "awrdIssueDt"];
        objs.athctfIdList = ["athctfIssueAgncNm", "athctfIssueNm", "athctfIssueDt"];

        for(var list of Object.values(objs)){
            var hasValue = false;
            for(var id of list){
                if(document.getElementById(id).value){
                    hasValue = true;
                    break;
                }
            }
            if(!hasValue){
                del_input_info(list);
            }
        }
    }
    function del_input_info(list){
        var obj;
        for(var id of list){
            obj = document.getElementById(id);
            obj.name = '';
        }
    }

    // onclick으로 삭제 처리시 script.js에서 로직 문제. 이벤트순서 나중으로 등록
    $(document).on("click", ".del_info", del_info);

    return {
        addInfo: add_info,
        delInfo: del_info,
        validateInfosAll: validate_infos_all,
        processData: fn_process_data
    }
}());