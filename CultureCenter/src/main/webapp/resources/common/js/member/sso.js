var members = (function() {

    "use strict";

    // SSO와 관련된 변수 및 객체 초기화
    var sso = null;
    var ssoObj = {};
    var ssoRtn = {};

    // 페이지에 대한 로그인, 모바일 여부, 앱 여부 확인
    var isLogin = $("#wrap").data("isLogin");
    var isMobile = $("#wrap").data("isMobile");
    var isApp = $("#wrap").data("isApp");

    // 초기화 함수
    var fn_init = function() {
        var rtnObj = {};

        // 로컬 스토리지에서 SSO 정보 가져오기
        ssoObj = JSON.parse(localStorage.getItem("ssoObj"));

        if (ssoObj == null) {
            // SSO 정보가 없으면 서버에서 가져와 로컬 스토리지에 저장
            fnc.bscAjax(function(data) {
                ssoObj = data.info;
                localStorage.setItem("ssoObj", JSON.stringify(ssoObj));

                // 필요한 외부 스크립트 동적으로 추가
                $("#wrap").prepend("<script src='" + ssoObj.domain + "/api/js/serialize.object.js'></script>");
                $("#wrap").prepend("<script src='" + ssoObj.domain + "/api/js/json2.js'></script>");

                // 일정 시간 후에 SSO 링크 함수 호출
                setTimeout(fn_link_sso, 1000);
            }, "/sso/info.ajax", "json", false, false);
        } else {
            // 이미 SSO 정보가 있으면 일정 시간 후에 SSO 링크 함수 호출
            setTimeout(fn_link_sso, 1000);
        }
    }

    // SSO 연결 함수
    var fn_link_sso = function() {
        var device = (isApp == "Y" ? "2" : isMobile == "Y" ? "1" : "0");

        // SSO 라이브러리 초기화
        sso = new SsoClientLibrary({
            ccoSiteNo: ssoObj.ccoSiteNo,
            clntAkInf: JSON.parse(ssoObj.clntAkInf),
            vrblNm: "sso",
            acesTkn: sessionStorage.getItem("acesTkn"),
            urEvnmtDc: device,
            srnOpt: {
                opMd: "2",
                popRturUrl: ssoObj.ssoPopRetUrl
            }
        });

        // 로그인 여부 및 SSO 토큰이 존재하면 자동 로그인 처리
        if (isLogin != "Y" && $("#frmSso #ssoTkn").val() != "" && location.pathname.indexOf("/login/") == -1) {
            sessionStorage.setItem("ssoTkn", $("#frmSso #ssoTkn").val());
            fn_sso_login();
        }
    }

    // SSO 팝업창 호출 함수
    var fn_call_screen = function(ssoUrl) {
        sessionStorage.setItem("ssoTkn", $("#frmSso #ssoTkn").val());
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

    // 로그인 처리 함수
    var fn_login = function() {
        // 아이디와 비밀번호 입력 확인
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

        // SSO 로그인 호출
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

    // SSO 자동 로그인 처리 함수
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

    // SSO 로그아웃 처리 함수
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
            // 로그아웃 후처리를 마치고 로그아웃 페이지로 이동
            $("#frmSso").attr("action", contextPath + "/login/logout.do");
            $("#frmSso").submit();
        }
    }

    // 로그인 콜백 함수
    var fn_login_callback = function(rspDta) {
        // 로그인 정보 설정 및 페이지 이동
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

        // 로그인 후처리
        $("#frmSso").attr("action", "/login/result.do");
        $("#frmSso").submit();
    }

    // SSO 콜백 함수
    var fn_sso_callback = function() {
        if (location.href.indexOf("/mypage/member/info") > -1) {
            fnc.bscAjax(function(data) {
                if (data.rtnCode == "N") {
                    // 회원정보 없는 경우 로그아웃 처리
                    fn_sso_logout();
                } else {
                    // 롯데멤버스 회원정보 재조회 위해 새로고침
                    location.href = location.href;
                }
            }, "/mypage/member/checkMemberDelete.ajax", "json", false, false);
        }
    }

    // 문서가 로드되면 초기화 함수 호출
    $(document).ready(function() {
        fn_init();

        // 뒤로가기로 접근 시
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

    // 외부에서 사용 가능한 함수들을 반환
    return {
        init: fn_init,
        callScreen: fn_call_screen,
        login: fn_login,
        logout: fn_sso_logout,
        ssoCallback: fn_sso_callback
    }
}());
