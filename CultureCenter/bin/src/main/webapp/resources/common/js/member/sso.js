var members = (function() {
	
	var pathname = "/" + window.location.pathname.split("/")[1];
	var origin = window.location.origin;	
	var contextPath = origin + pathname;

    "use strict";

    var sso = null;
    var ssoObj = {};
    var ssoRtn = {};

    var isLogin = $("#wrap").data("isLogin");
    var isMobile = $("#wrap").data("isMobile");
    var isApp = $("#wrap").data("isApp");

    var fn_init = function() {

        var rtnObj = {};

        ssoObj = JSON.parse(localStorage.getItem("ssoObj"));

        if (ssoObj == null) {
            fnc.bscAjax(function(data) {

                ssoObj = data.info; //sso정보 삽입

                localStorage.setItem("ssoObj", JSON.stringify(ssoObj));

                $("#wrap").prepend("<script src='" + ssoObj.domain + "/api/js/serialize.object.js'></script>");
                $("#wrap").prepend("<script src='" + ssoObj.domain + "/api/js/json2.js'></script>");

                setTimeout(fn_link_sso, 1000);

            }, "/sso/info.ajax", "json", false, false);
        } else {
            setTimeout(fn_link_sso, 1000);
        }
    }

    var fn_link_sso = function() {
        var device = (isApp == "Y" ? "2" : isMobile == "Y" ? "1" : "0");

        sso = new SsoClientLibrary({
            ccoSiteNo: ssoObj.ccoSiteNo, // 제휴사사이트번호
            clntAkInf: JSON.parse(ssoObj.clntAkInf), // 클라이언트요청정보
            vrblNm: "sso", // 라이브러리 변수명
            acesTkn: sessionStorage.getItem("acesTkn"),
            urEvnmtDc: device, // 사용자환경구분코드 0: PC Web 1: Mobile Web 2: Mobile App
            srnOpt: {
                opMd: "2",
                popRturUrl: ssoObj.ssoPopRetUrl
            }
            // 오픈모드 0: Redirect 1: iframe 2: Popup
        });

        if (isLogin != "Y" && $("#frmSso #ssoTkn").val() != "" && location.pathname.indexOf("/login/") == -1) {
            sessionStorage.setItem("ssoTkn", $("#frmSso #ssoTkn").val()),
                //ssoObj.ssoTkn = $("#frmSso #ssoTkn").val(); 
                fn_sso_login();
        }
    }

    /* SSO 팝업창 연결 */
    var fn_call_screen = function(ssoUrl) {

        //ssoRtn.ssoTkn = $("#frmSso #ssoTkn").val();
        sessionStorage.setItem("ssoTkn", $("#frmSso #ssoTkn").val()),
            ssoRtn.onlCstTpC = $("#frmSso #onlCstTpC").val();
        ssoRtn.frnYn = $("#frmSso #frnYn").val();
        ssoRtn.rspDtc = $("#frmSso #rspDtc").val();

        var callObj = {
            akUrl: ssoObj[ssoUrl],
            akDta: ssoRtn,
            popWidth: "720",
            popHeight: "640"
        }

        sso.callScreen(callObj);
    }

    var fn_login = function() {
        if (!$("#mbrId").val()) {
            alert("아이디를 입력해 주세요.");
            $("#mbrId").focus();
            return false;
        }

        if (!$("#mbrPwd").val()) {
            alert("비밀번호를 입력해 주세요.");
            $("#mbrPwd").focus();
            return false;
        }

        sso.callLogin({
            akUrl: ssoObj.apiLoginUrl,
            akDta: {
                onlId: $("#mbrId").val(),
                cstPswd: $("#mbrPwd").val()
            },
            aftPcMd: '1',
            callback: fn_login_callback
        });

    }

    var fn_sso_login = function() {
        sso.callSsoLogin({
            akUrl: ssoObj.apiSsoLoginUrl,
            akDta: {
                ssoTkn: sessionStorage.getItem("ssoTkn")
            },
            aftPcMd: '1',
            callback: fn_login_callback
        });
    }

    var fn_sso_logout = function() {
        try {
            sso.callLogout({
                callback: function(rspDta) {
                    // 제휴사 로그아웃 후처리 
                    $("#frmSso").find("#rspClac").val(rspDta.rspClac);
                    $("#frmSso").find("#rspC").val(rspDta.rspC);
                    $("#frmSso").find("#rspDtc").val(rspDta.rspDtc);
                    $("#frmSso").find("#rspMsgCn").val(rspDta.rspMsgCn);
                }
            });
        } catch (e) {

        } finally {
            $("#frmSso").attr("action", contextPath+"/login/logout.do");
            $("#frmSso").submit();
        }
    }

    var fn_login_callback = function(rspDta) {
        $("#frmSso").find("#ssoTkn").val(rspDta.ssoTkn);
        $("#frmSso").find("#acesTkn").val(rspDta.acesTkn);
        $("#frmSso").find("#onlCstTpC").val(rspDta.onlCstTpC);
        $("#frmSso").find("#frnYn").val(rspDta.frnYn);
        $("#frmSso").find("#rspClac").val(rspDta.rspClac);
        $("#frmSso").find("#rspC").val(rspDta.rspC);
        $("#frmSso").find("#rspDtc").val(rspDta.rspDtc);
        $("#frmSso").find("#rspMsgCn").val(rspDta.rspMsgCn);

        sessionStorage.setItem("ssoTkn", rspDta.ssoTkn);
        sessionStorage.setItem("acesTkn", rspDta.acesTkn);
        ssoObj['ssoTkn'] = '';
        ssoObj['acesTkn'] = '';
        localStorage.setItem("ssoObj", JSON.stringify(ssoObj));

        //로그인 후처리
        $("#frmSso").attr("action", "/login/result.do");
        $("#frmSso").submit();
    }

    var fn_sso_callback = function() {
        if (location.href.indexOf("/mypage/member/info") > -1) {
            fnc.bscAjax(function(data) {
                if (data.rtnCode == "N") {
                    //회원정보 없는 경우 로그아웃처리.
                    fn_sso_logout();
                } else {
                    //롯데멤버스 회원정보 재조회 위해 새로고침.
                    location.href = location.href;
                }
            }, "/mypage/member/checkMemberDelete.ajax", "json", false, false);
        }
    }

    $(document).ready(function() {

        fn_init();

        //뒤로가기로 접근 시
        window.onpageshow = function(event) {
            if (event.persisted || (window.performance && (window.performance.navigation.type == 1 || window.performance.navigation.type == 2))) {
                if (location.pathname.indexOf("/login/index.do") > -1) {
                    fnc.bscAjax(function(data) {
                        if (data.lgnYn) {
                            location.href = "/";
                        }
                    }, "/lgnCheck.ajax");
                }
            }
        }

    });

    return {
        init: fn_init,
        callScreen: fn_call_screen,
        login: fn_login,
        logout: fn_sso_logout,
        ssoCallback: fn_sso_callback
    }
}());