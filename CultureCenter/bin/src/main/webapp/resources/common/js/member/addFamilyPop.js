var addFamily = (function() {

    "use strict";

    var popObj = {};

    var fn_init = function(callbackFn) {
        popObj.callbackFn = callbackFn;
    }

    //가족회원 저장
    var fn_save = function() {
        var frm = {
            form: $("#addFamilyFrm"),
            obj: function(name) {
                return frm.form.find("[name='" + name + "']");
            }
        }

        if (frm.obj("korNm").val().trim() == "") {
            alert("이름을 입력하세요.");
            //frm.obj("korNm").focus();
        } else if (!fn_chk_age(frm.obj("bday").val())) {
            //frm.obj("bday").focus();
        } else if (!frm.obj("chkAgrYn").is(":checked")) {
            alert("가족정보 수집 및 활용동의에 체크하세요.");
            //frm.obj("chkAgrYn").focus();
        } else {
            fnc.frmAjax(function(data) {

                if (data.rtnCode == "91") {
                    alert("이미 등록된 동반 수강자입니다.");
                } else {
                    alert("동반 수강자가 추가되었습니다.");

                    if (popObj.callbackFn) {
                        popObj.callbackFn();
                    }
                }

            }, "/mypage/member/family/insert.ajax", $("#addFamilyFrm"), "json", true, false, true);
        }

    }

    var fn_close = function() {
	    $("#addFamilyPop").find(".btn_close").click();
	    $("#addFamilyPop").find(".border_btn").click();
	    
	  
        $("#addFamilyFrm").find("input").not("input[type='hidden'], input[type='radio'], input[type='checkbox'], input[readonly]").val("");
        $("#addFamilyFrm").find("input[name='sexCd'][value='M']").click();
        $("#addFamilyFrm").find("input[type='checkbox']").prop("checked", false);
    }

    //만 14세 체크
    var fn_chk_age = function(bdayStr) {
        var bday = bdayStr.replace(/[^0-9]/g, "");

        if (bday == "") {
            alert("생년월일을 입력하세요.")
        } else if (bday.length != 8) {
            alert("YYYYMMDD 형태로 입력하세요.");
            return false;
        } else {
            var today = new Date();
            var yearDiff = today.getFullYear() - parseInt(bday.slice(0, 4), 10);
            var monthDiff = today.getMonth() + 1 - parseInt(bday.slice(4, 6), 10);
            var dateDiff = today.getDate() - parseInt(bday.slice(6, 8), 10);

            var bFlag = (monthDiff < 0 || monthDiff === 0 && dateDiff < 0) ? -1 : 0; //생일 지났으면 0 아니면 -1

            if (yearDiff + bFlag < 0) {
                alert("현재날짜 이후 날짜는 입력불가합니다.");
                return false;
            } else if (yearDiff + bFlag >= 14) {
                alert("자녀회원은 만 14세 미만 자녀만 등록 가능합니다.");
                return false;
            } else if (!fnc.checkBday(bday)) {
                alert("생년월일을 확인하세요.");
                return false;
            } else {
                return true;
            }
        }
    }

    return {
        init: fn_init,
        save: fn_save,
        close: fn_close
    }
}());