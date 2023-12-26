var mypageMember = (function() {

    "use strict";

    // 가족회원 리스트
    var fn_family_list = function() {
        fnc.bscAjax(function(data) {
            $("#familyList").html(data);
        }, "/mypage/member/family/list.ajax", "html", false, false);
    }

    var fn_show_popup = function(popupId) {
        commonScript.openPopupFn(popupId, $(this));
    }

    //마케팅 수신동의 저장
    var fn_save_sms_yn = function() {
        var smsYn = $("#checkSMS").is(":checked") ? "Y" : "N";

        fnc.paramAjax(function(data) {
            var updTxt = smsYn == "Y" ? "수신동의" : "수신거부";

            $("#saveSmsGuidePop").find("#updTxt").text("SMS: " + updTxt);
            $("#saveSmsGuidePop").find("#updDt").text(data.rtnMap.smsRcvAgrDt + " 변경완료 되었습니다.");

            fn_show_popup("#saveSmsGuidePop");
        }, "/mypage/member/updateSmsYn.ajax", {
            smsRcvAgrYn: smsYn
        }, "json", true, false);
    }

    //차량정보 수집동의 저장
    var fn_save_car_yn = function() {
	    // 현재 날짜를 가져오기
	    var currentDate = new Date();
	    var formattedDate = currentDate.toISOString().split('T')[0];
	    
        // 체크박스 체크 여부 확인
	    var carYn = $("#checkCar").is(":checked");
	    var carVal = $("#carNoVal").val();

        if (!carYn && carVal !== "") {
            alert("차량번호 수집동의를 선택하세요.");
        } else {
        	var paramObj = {
            car_no: carVal
        };
            fnc.paramAjax(function(data) {
                 var updTxt = carYn ? "수집동의" : "수집거부";

                $("#saveCarGuidePop").find("#updTxt").text("차량번호: " + updTxt);
                $("#saveCarGuidePop").find("#updDt").text(formattedDate + " 변경완료 되었습니다.");

                fn_show_popup("#saveCarGuidePop");
            }, "/mypage/member/updateCarYn.ajax", paramObj, "json", true, false);
        }
    }

    //가족회원 삭제
    var fn_delete_family = function(fmlySeqno) {
        var paramObj = {
            fmlySeqno: fmlySeqno
        }

        var ajaxUrl = contextPath + "/mypage/member/family/delete.ajax";

if (confirm("동반 수강자를 삭제하시겠습니까?")) {
    fnc.paramAjax(function(data) {
        if (data.rtnCode == "1") {
            alert("삭제되었습니다.");
         /*   fn_family_list();*/
        }
    }, ajaxUrl, paramObj, "json", true, false);
}




    }

    //회원탈퇴
    var fn_draw_member = function() {
        fnc.bscAjax(function(data) {
            if (data.rtnCode == "H") {
                alert("현재 수강중인 강좌의 수강이 종료된 후 탈퇴 가능합니다.");
            } else if (confirm("회원탈퇴를 하시겠습니까?")) {
            //  members.callScreen('mbrSesUrl');
               // Ajax 성공 후 리다이렉트
            window.location.href = '/login/index.do';
            }
        }, "/mypage/member/checkMemberDelete.ajax", "json", false, false);
    }


    $(document).ready(function() {
        fn_family_list();

        //자녀회원 팝업js 초기화
        addFamily.init(function() {
            addFamily.close();
            fn_family_list();
        })
    });

    return {
        familyList: fn_family_list,
        showPopup: fn_show_popup,
        saveSmsYn: fn_save_sms_yn,
        saveCarYn: fn_save_car_yn,
        deleteFamily: fn_delete_family,
        drawMember: fn_draw_member
    }
}());