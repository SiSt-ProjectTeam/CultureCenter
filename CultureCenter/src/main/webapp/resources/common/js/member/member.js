var members = (function() {

    "use strict";

    var sso = null;
    var ssoObj = {};
    var ssoRtn = {};

    var isLogin = $("#wrap").data("isLogin");
    var isMobile = $("#wrap").data("isMobile");
    var isApp = $("#wrap").data("isApp");

    var fn_call_screen = function(url) {

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
    }

    
    var fn_login = function() {
        if (!$("#username").val()) {
            alert("아이디를 입력해 주세요.");
            $("#username").focus();
            return false;
        }

        if (!$("#password").val()) {
            alert("비밀번호를 입력해 주세요.");
            $("#password").focus();
            return false;
        }

        $("#frmLogin").submit();

    }

    var fn_logout = function() {
        $("#frmLogout").submit();        
    }


    $(document).ready(function() {

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
        callScreen: fn_call_screen,
        login: fn_login,
        logout: fn_logout
    }
}());