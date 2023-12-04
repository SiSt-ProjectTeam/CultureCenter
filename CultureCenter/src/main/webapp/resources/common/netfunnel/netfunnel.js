/**
 * Copyright (c) 2022 STCLab. All rights reserved.
 * Code licensed under the STCLab License
 * Version 2.2.25_hotfix
 *
 * @author jhh<jhh@stclab.com>
 */

var NetFunnel = {};
NetFunnel.Skin = {};
//EditZoneStart ----------------------------------------------------------------
if (typeof window !== 'undefined') {
    NetFunnel.TS_HOST = 'control.lotteshopping.com'; // Default TS host
    //NetFunnel.TS_HOST        		= 'nf2.netfunnel.co.kr';	// Default TS host
    NetFunnel.TS_PORT = 443; // Default TS port
    NetFunnel.TS_PROTO = 'https'; // Default TS protocol [http|https]
    NetFunnel.TS_QUERY = 'ts.wseq'; // Default request query
    NetFunnel.TS_SERVICE_ID = 'service_2'; // Default TS Service id
    NetFunnel.TS_ACTION_ID = 'act_1_prd'; // Default TS Action id
    NetFunnel.TS_MAX_TTL = 30; // Default max ttl (second) 5~30
    NetFunnel.TS_CONN_TIMEOUT = 3; // Default connect timeout (second)
    NetFunnel.TS_CONN_RETRY = 1; // Default connect retry count
    NetFunnel.TS_COOKIE_ID = 'NetFunnel_ID'; // Default Cookie ID
    NetFunnel.TS_COOKIE_TIME = 10; // Default Cookie Time (minute)
    NetFunnel.TS_COOKIE_DOMAIN = ''; // Default Cookie Domain
    NetFunnel.TS_BYPASS = false; // NetFunnel Routine Bypass [true|false]
    NetFunnel.TS_POPUP_TOP = false; // Popup Top Position ( "false" is center )
    NetFunnel.TS_POPUP_LEFT = false; // Popup Left Position ( "false" is center )
    NetFunnel.TS_AUTO_COMPLETE = false; // Auto setComplete [true|false]
    NetFunnel.TS_DEBUG_MODE = false; // Debug Mode
    NetFunnel.TS_SHOWTIME_LIMIT = 0; // Show WaitTime Limit (second, 0 is Unlimited)
    NetFunnel.TS_SHOWCNT_LIMIT = 0; // Show WaitUser Limit (0 is Unlimited)
    NetFunnel.TS_SHOWNEXT_LIMIT = 0; // Show NextWaitUser Limit (0 is Unlimited)
    NetFunnel.TS_LIMIT_TEXT = '다수'; // SHOWCNT,SHOWNEXT Limit를 넘었을때 출력되는 문자열
    NetFunnel.TS_IFRAME_RESIZE = false; // true | false
    NetFunnel.TS_USE_UNFOCUS = true; // object unfocus after netfunnel call
    NetFunnel.TS_VIRT_WAIT = 10000; // virtual wait time (millisecond)
    NetFunnel.TS_USE_MOBILE_UI = true; // Mobile UI
    NetFunnel.TS_POPUP_TARGET = window; // Popup target window
    NetFunnel.TS_USE_FRAME_BLOCK = false; // Block FrameSet Page
    NetFunnel.TS_FRAME_BLOCK_LIST = []; // Frame Block Window List
    NetFunnel.TS_USE_PRE_WAIT = false; // Pre waiting popup use
    NetFunnel.TS_USER_DATA_KEYS = []; // Input UserData Key & Type(c=cookie,v=variable)
    // ex) [ {"key":<user_data_key>, "type":<c|v>}, ... ]
    NetFunnel.TS_CONFIG_USE = true; // 무조건 Config에 있는 IP 와 PORT로 사용
    NetFunnel.TS_POPUP_ZINDEX = 32000; // 대기 Popup창의 z-index 값.
    // 대기창이 뒤로 숨지 않도록 적당한 값을 넣어줘야 한다.
    NetFunnel.TS_IP_ERROR_RETRY = true; // Retry(Re-Issue) Where IP Validation Error
    NetFunnel.TS_SUCCESS_POPUP_VISIBILITY = false;

    //일정 기간 동안 대기인원 변함 없을시 Bypass 처리
    NetFunnel.TS_NWAIT_BYPASS = true; // 사용 유무
    NetFunnel.TS_MAX_NWAIT_COUNT = 100; // 대기인원 반복 체크 기준값

    //Server Block
    NetFunnel.TS_BLOCK_MSG = 'Service Block!!'; // Server Block시 팝업에 표시할 문구
    NetFunnel.TS_BLOCK_URL = ''; // Server Block시 등록된 url로 이동(미등록시 경고창 후 서비스 진입 불가)
    NetFunnel.TS_IPBLOCK_WAIT_COUNT = 200; // Server IP Block 가상대기창 반복 횟수
    NetFunnel.TS_IPBLOCK_WAIT_TIME = 10000; // Server IP Block 가상대기시간

    //대기창 미리보기
    NetFunnel.TS_SHOW_WAIT_POPUP = false; //대기창 보기

    //event skin 지정
    NetFunnel.TS_SKIN_ID = ''; // Skin ID (미지정시 default 대기창)

    // Variable for MProtect
    NetFunnel.MP_USE = false; // 매크로방지기능 사용유무 (true|false)
    NetFunnel.MP_TIMELIMIT = 20000; // 사용자의 요청을 체크하기 위한 단위 시간 (ms)
    NetFunnel.MP_MAXREQLIMIT = NetFunnel.MP_TIMELIMIT / 1100; // TIMELIMIT 시간 내에 getTidChkEnter를 요청가능한 최대값
    NetFunnel.MP_DEVLIMIT = 20; // 요청주기의 표준편차 제한값 (ms)
    NetFunnel.MP_DEVCNTLIMIT = 7; // 표준편차 계산을 위한 item숫자
    NetFunnel.MP_REQONLYLIMIT = 10; // setComplete 없이 getTidChkEnter만 요청한 횟수 제한값(횟수)
    NetFunnel.MP_MINCOUNT = 5; // 계산을 하지 않는 자료개수

    /* eslint-disable indent */
    /* eslint-disable max-len */
    /* eslint-disable no-unused-vars */
    // Logo Image Data -------------------------------------------------------------
    //   - height:16 pixel
    //   - GIF Format Data (Base64 Encoding)
    NetFunnel.gLogoData = 'iVBORw0KGgoAAAANSUhEUgAAAEYAAAAjCAYAAAApF3xtAAAHHklEQVRoge2Zf2wcRxXHP7Nze7v+cbZJUuy4TtvQKpQSSAOlNERuhFBLf4iiIjUl/QcJFQIChCqhIorgT34kCi0SSMRIIIGghESUVtCI/iBFlBDiNBWqRZpWjWM5bv0jtuvL+X7t3g2a9Vu0uCG5sy/GlfyVTrM7Mzsz7zvvvXnzjhWsYAWNgLrYGF/4ylfnV3UDVwNdQAZYJfWTQBZ4HTgFjNWzPifvkd9ygvKGMzi5pvP2aWmZZmDgVk6e3EZLy1Td4g999/6a+6Zq7Pce4FPAJ4H3A95F+ueAl4DHgAPA4EVnMAqqqoatWhrUQsz3gQcT71YbjotmvAGUrFiAD1wOrANuALbI7zvAN4Fd5x3dElFVqHIKdHVupLcJMTEptwF/BoI6xr4b+K2Qe35iDDizPqWNpwmuHMfJ+zUv/lLCqWHs70l5cx2kWITANnn+2ltaxWT0ZIagZ4LZmwfmtKVSy5IuPWrRmG8AVwEPAautP65xVfuA7UAfsCeqsT7E+hJlcApeREJ4+SSFm15GlVKoshu1vV2IsdgBrAF2AlNC0oXQJ6TsR5mdVJ3IXEwqBLcCQYpw7RTlDSME3Wcj32LbcZaJg6mDGItbgBdFgyaAh/9HP+tsPwc8gzLbVZBClVyCq0YjIowbQqiprMli/DIq76Gs9iwjUqiTGIutwKvAD+RU2jev/UtC3IAKUrcQaFSoKXz4ZYqbX0PJu/UvkenMtMyZzjIxnyTqJSYP3AicBn4j5T+k7VYc8yNVSE+rUPdWOnJYbSleN0Tx+lPobHOkKf9FwjIkJEa9xFiMSHzSDzwNdALNKHNQFdKY5tIHChtPvxl2T0aCGy9AW82wp80yJmI+Fno2HgPulyvBJuBOKo6DMh/Pbzlxurjp1O3GDa8xuhr5FyqqB2XuAnoWMpkxDqlUmdbWSVKpEtWqXuCya8digobYhKzWedZ/VNtnn6p05N6rp1qfVEHqJRXqrrnjOXLGjwOfv8B49wJvuZhFE6RKFIutDA7eQC63GtctNkD0C2MhphRjtZSVKPCLQnunRYVaRSfP3BXhT6JRI4m+iKZ9VBz5CSAtPsvid8BwPIkxira2CY4d62Vg4Dba29+gqWkm0qJLicUQcz5E9Eh9Ti6cn04Iaom4FnhWjnwbOH5x3qXUnnj3zJlPKTKf4eH3MTy8iUxmDK3DS04KizSli2GPaMGjwJel7xoJ/mzq4hDQDvxabt+Hpc8eK7jvn4v8yuDghzh6dDtB4JFOF1iqW2ajNSaJWYmYrcbcIfXvEtOaEAltkNgqKYpzzDnaI76fJQw9jh69l/Hxq/G8WZqaskuiKTEaPJMpax14WgdoHfRaX+D72U84TsW+Ww0YVqr6pDHqMmtOxjhprcsjvp+b0jrYZrlKp/PW/3DkyA7GxjZECSnXLSwpKTRSY+z2a2PeWQ3d4YrjPKKrPDM4eKP1CX/o7h54sFxu7jJGH/T9c3tct2CMcXZpXTbZbOej09M9ZDITDzU1Zbc2NWWD/v7tjI9fQ3v7WOR8/x/Zq4YR46WKTFbaVh3u3/GKcsxup2rS2WznFq2D0ZGR63YXi61XhKFX6ux81fqP+wqFtq2elx+amlp3ZmqqZ3NLy5v7OjpGHlaqah2tn8lMbjZGjYr/uV7YeVGma5Y6mz59DdgopyBiBcfFf10r3+SXkpj428gbegRMOJlXJgpdd2YqpQNWDN/P2R0/fvbs+gccJ/wL8PuhoQ/eHYZuD6jnjVEvuG5xazpdOJzPt/szM507QfU1N0+vV8ocNkYdFP9kv20DPgv8HHg38DdJnH0M+CvQkVjbKkmS/RS4KRFzLQkxZSltDFI1KDRV0m5Bu9pmO9kP/FIpM+p5ua7kh55HHLrGWe+C6xZ91y3ulYzfiTkT+g9mhZifSQ55YN5aOhLO3hFHvqAoO8ZiI9+DorKxoEoyd0gQt05ywtNSF5MZxzrFxHexuj8t/0Ak+6cS7X+UYz/ZXpRne+pdKWuIs43xXHVhMcSURc0PyRE8fxE2R/xjUf8ZqcvPK10hxe74v4AHJJF+QNpjkm2U/U/JP/cm0h3xfHb8dwCPAD+UungzsktNTAwb4Y7Ks59Y7C6xdZvvvULqNki5Xsp8Yme7RbB9kvexKCTWabVot9y5eqU+1rhO+cdirYyDPMdlmzjsmtGo4MCS8AvgMvkh96Jp2fX4NPmICNAv748l7k+xQPclNCzpm2Iy70kQtjbRvlbuXSclaJyU+kMy3lP1CNSo49pqyWdkvGHxEy8k2l+XXf6WnBKD4it2yTf7xWHGdy1rht8WH4ZcLc7JcyDtXxehLX4iWrM6sdlHgCdkfOvvnqtHoEZfCULZsb3ziLF4XgR4VoT8e+Li05dwpIhQeyVGQY7d5F838fE8JO+/ElNRQkxJyO8T7bImfqbBsq5gBStYwcIA/Bt7U162SmCp6QAAAABJRU5ErkJggg==';
    NetFunnel.gLogoText = '';
    NetFunnel.gLogoURL = '	https://www.netfunnel.co.kr';

    NetFunnel.gPreWaitData = 'R0lGODlhKAAoALMMAPj4+MTExPT09NTU1NPT08XFxcbGxsLCwtXV1cPDw/X19b+/v////wAAAAAAAAAAACH/C05FVFNDQVBFMi4wAwEAAAAh+QQJBQAMACwAAAAAKAAoAAAEgJDJSau9OOvNu/9gKI5kaZ5oqq5sCwKIYSAABcv0qQRLvwQCyc73C5YGxB5BgkwuS4nk4iCJJqlQKdZKxJII0ifYaRLwfECJmZg2AQaFwqA2ecfnrry+dJvR80NoRi5NRE8uXD5eLYk9iyxjhnprgnt2cn97mpucnZ6foKGio3oRACH5BAkFAAwALAAAAAAoACgAAASAkMlJq7046827/2AojmRpnmiqrmwLAohhIAAFy/SpBEu/BALJzvcLlgbEHkGCTC5LieTiIIkmqVAp1krEkgjSJ9hpEvB8QImZmDYBBoXCoDZ5x+euvP4Vm9HzQ2hGLk1ETy5cPl4tiT2LLGOGemuCe3Zyf3uam5ydnp+goaKjnREAIfkECQUADAAsAAAAACgAKAAABICQyUmrvTjrzbv/YCiOZGmeaKqubAsCiGEgAAXL9KkES78EAsnO9wuWBsQeQYJMLkuJ5OIgiSapUCnWSsSSCNIn2GkS8HxAiZmYNgEGhcKgNnnH5668fnOb0fNDaEYuTURPLlw+Xi2JPYssY4Z6a4J7dnJ/e5qbnJ2en6ChoqOiEQAh+QQJBQAMACwAAAAAKAAoAAAEgJDJSau9OOvNu/9gKI5kaZ5oqq5sCwKIYSCAOynBoi+BYDOD3Y7wSwh1h+JxkbQRlkQJTEY7CXK7ngQn1JoAg0JhUJMEj1GX8dhUL9uk6azMeKJN3KyPce3uSWdCaWBiZCdrQnAsiDuKK3aCP316PwyEY3SVmpucnZ6foKGio6IRACH5BAkFAAwALAAAAAAoACgAAAR9kMlJq7046827/2AojmRpnmiqrmwLAohhIIA7KcGiL4FgM4PdjvBLCHWH4nGRtBGWRJsgt+v9GIBBoTCoXb/gDUxGo4xn3hJOaGWsq75S8BidC6Mk47GpFzbzS3yBJk90EoV3JlNscYtwJ1lbXWZaXGlhmJmam5ydnp+goBEAIfkECQUADAAsAAAAACgAKAAABH2QyUmrvTjrzbv/YCiOZGmeaKqubFsCiGEggDspwaIvgWAzg92O8EsIdYficZG0EZZEmyC36/0YgEGhMKhdv2ALTEajjGfeEk5oZayrvlLwGJ0LoyTjsakXNvNLfIEmT3QShXcmU2xxi3AnWVtdZlpcaWGYmZqbnJ2en6CgEQAh+QQJBQAMACwAAAAAKAAoAAAEfZDJSau9OOvNu/9gKI5kaZ5oqq5sqwKIYSCAOynBoi+BYDOD3Y7wSwh1h+JxkbQRlkSbILfr/RiAQaEwqF2/YGxs5pXAZLQTTmhlrKu+UvAYnQujJOOxqRc280t8gSZPdBKFdyZTbHGLcCdZW10UkVxlYZiZmpucnZ6foKARACH5BAUFAAwALAAAAAAoACgAAASBkMlJq7046827/2AojmRpnmiqrmyrAohhIIA7KcGiL4FgM4PdjvBLCHWH4nGRNMFktAlhSSzhhD2JILfLloLHKgMwKBQGNZPx2HSthe3WNDx5ztKlLdbHuHb5JWRmaBNgQmItbztxLIpIP3OHP3p/P2NlZ3iWm5ydnp+goaKjpD8RADs=';

    NetFunnel.gTextDecoration = false;
    NetFunnel.gFixelData = 'R0lGODlhAQABAJEAAAAAAP///////wAAACH5BAEAAAIALAAAAAABAAEAAAICVAEAOw==';
}
//EditZoneEnd ------------------------------------------------------------------
;
"undefined" != typeof window && function(top) {
    function NetFunnel_init(e, t, n) {
        "use strict";
        return NetFunnel.gControl && (NetFunnel.gControl._resetScript(), NetFunnel.gControl = null), void 0 === n ? n = NetFunnel.DefaultCallback : (n.onSuccess || (n.onSuccess = NetFunnel.DefaultCallback.onSuccess), n.onContinued || (n.onContinued = NetFunnel.DefaultCallback.onContinued), n.onError || (n.onError = NetFunnel.DefaultCallback.onError), n.onStop || (n.onStop = NetFunnel.DefaultCallback.onStop), n.onBypass || (n.onBypass = NetFunnel.DefaultCallback.onBypass), n.onBlock || (n.onBlock = NetFunnel.DefaultCallback.onBlock), n.onExpressnumber || (n.onExpressnumber = NetFunnel.DefaultCallback.onExpressnumber), n.onIpBlock || (n.onIpBlock = NetFunnel.DefaultCallback.onIpBlock)), NetFunnel.gControl = new NetFunnel.TsClient(t, n), !0
    }

    function NetFunnel_sendStop(e) {
        "use strict";
        try {
            return NetFunnel.gControl || NetFunnel_init(), NetFunnel.gAlreadyProc = 0, NetFunnel.gControl.setNext(e), NetFunnel.gControl.sendStop(), !0
        } catch (e) {
            return !1
        }
    }

    function NetFunnel_getTicketID(e, t, n) {
        "use strict";
        return NetFunnel.gControl || NetFunnel_init(), NetFunnel.gAlreadyProc = 0, NetFunnel.gControl.setNext(e), NetFunnel.gControl.getTicketID(t, n), !0
    }

    function NetFunnel_chkEnter(e, t) {
        "use strict";
        if (NetFunnel.gControl || NetFunnel_init(), void(NetFunnel.gAlreadyProc = 0) !== t && t.constructor == Object) {
            if (!(n = t.key)) return !1
        } else {
            var n, i = new NetFunnel.Storage(2),
                t = i.getItem(NetFunnel.gControl.mConfig.cookie_id);
            if (null === t || "" == t) return !1;
            if (!(n = new NetFunnel.RetVal(t).getValue("key"))) return (i = new NetFunnel.Storage(2)).removeItem(this.mConfig.cookie_id), !1
        }
        return NetFunnel.gControl.setNext(e), NetFunnel.gControl.chkEnter(n), !0
    }

    function NetFunnel_getTidChkEnter(e, t, n) {
        return NetFunnel.gControl || NetFunnel_init(), NetFunnel.gAlreadyProc = 0, NetFunnel.gControl.setNext(e), NetFunnel.gControl.getTidChkEnter(t, n), !0
    }

    function NetFunnel_aliveNotice(e, t) {
        "use strict";
        try {
            NetFunnel.gControl || NetFunnel_init(), NetFunnel.gAlreadyProc = 0;
            var n = "",
                i = "",
                o = "";
            if (void 0 !== t && t.constructor == Object) {
                if (!(n = t.key)) return !1;
                i = t.ip, o = t.port
            } else {
                var r = new NetFunnel.Storage(2),
                    s = r.getItem(NetFunnel.gControl.mConfig.cookie_id);
                if (null === s || "" == s) return !1;
                var l = new NetFunnel.RetVal(s);
                if (!(n = l.getValue("key"))) return r.removeItem(this.mConfig.cookie_id), !1;
                i = l.getValue("ip"), o = l.getValue("port")
            }
            return NetFunnel.gControl.setNext(e), NetFunnel.gControl.aliveNotice(n, i, o), !0
        } catch (e) {
            return !1
        }
    }

    function NetFunnel_setComplete(e, t) {
        "use strict";
        NetFunnel.gControl || NetFunnel_init(), NetFunnel.gAlreadyProc = 0, NetFunnel.gControl.setNext(e);
        var n = "",
            i = "",
            o = "";
        if (NetFunnel.gPop && (NetFunnel.gPop.hide(), NetFunnel.gPop.destroy(), delete NetFunnel.gPop, NetFunnel.gPop = null), void 0 !== t && t.constructor == Object) {
            if (!(n = t.key)) return NetFunnel.gControl._sendError(NetFunnel.RTYPE_SET_COMPLETE, NetFunnel.kErrorSystem), !1;
            i = t.ip, o = t.port
        } else {
            var r = new NetFunnel.Storage(2),
                s = r.getItem(NetFunnel.gControl.mConfig.cookie_id);
            if (null === s || "" == s) return NetFunnel.gControl._sendError(NetFunnel.RTYPE_SET_COMPLETE, NetFunnel.kErrorSystem), !1;
            e = new NetFunnel.RetVal(s), t = e.getRetCode(), s = e.getReqType();
            if (t != NetFunnel.kSuccess && t != NetFunnel.kTsBypass && (s != NetFunnel.RTYPE_ALIVE_NOTICE || t != NetFunnel.kContinue)) {
                t = new NetFunnel.RetVal(NetFunnel.RTYPE_SET_COMPLETE + ':200:msg="Success"');
                return NetFunnel.gControl._showResultSetComplete(t), !0
            }
            if (!(n = e.getValue("key"))) return (r = new NetFunnel.Storage(2)).removeItem(NetFunnel.gControl.mConfig.cookie_id), NetFunnel.gControl._sendError(NetFunnel.RTYPE_SET_COMPLETE, NetFunnel.kErrorSystem), !1;
            i = e.getValue("ip"), o = e.getValue("port")
        }
        return NetFunnel.gControl.setComplete(n, i, o), !0
    }

    function NetFunnel_cookieExist() {
        return !!NetFunnel.gControl && NetFunnel.gControl.cookieExist()
    }

    function NetFunnel_isRunning() {
        return !!NetFunnel.gControl && NetFunnel.gControl.isRunning()
    }

    function NetFunnel_goForm(e, t, n) {
        "use strict";
        var i = null;
        if ("string" == typeof t) {
            if ("object" != typeof(i = document.getElementById(t)) || null === i) {
                var o = document.getElementsByName(t);
                if ("object" != typeof(i = o[0]) || null === i) return alert("[NetFUNNEL] Invalid input form"), !1
            }
        } else {
            if ("object" != typeof t) return alert("[NetFUNNEL] Invalid input form"), !1;
            i = t
        }
        return "function" != typeof n && (n = function(e, t) {
            return !1
        }), NetFunnel_init(null, e), NetFunnel_getTidChkEnter({
            success: function(e, t) {
                null !== i && i.submit()
            },
            error: function(e, t) {
                null !== i && i.submit()
            },
            bypass: function(e, t) {
                null !== i && i.submit()
            },
            stop: n
        }), !1
    }

    function NetFunnel_goUrl(e, t, n) {
        "use strict";
        return "string" != typeof t ? alert("[NetFUNNEL] Invalid input url") : ("function" != typeof n && (n = function(e, t) {
            return !1
        }), NetFunnel_init(null, e), NetFunnel_getTidChkEnter({
            success: t,
            error: t,
            bypass: t,
            stop: n
        })), !1
    }

    function NetFunnel_goFunc(e, t, n) {
        "use strict";
        return "function" != typeof t ? alert("[NetFUNNEL] Invalid input function") : ("function" != typeof n && (n = function(e, t) {
            return !1
        }), NetFunnel_init(null, e), NetFunnel_getTidChkEnter({
            success: t,
            error: t,
            bypass: t,
            stop: n
        })), !1
    }

    function NetFunnel_goComplete(e, t) {
        "use strict";
        return "function" != typeof t && (t = function(e, t) {
            return !1
        }), NetFunnel_init(null, e), NetFunnel_setComplete({
            success: t,
            error: t,
            bypass: t
        }), !1
    }

    function NetFunnel_goAliveNotice(e, t) {
        "use strict";
        return "function" != typeof t && (t = function(e, t) {
            return !1
        }), NetFunnel_init(null, e), NetFunnel_aliveNotice({
            success: t,
            error: t,
            bypass: t
        }), !1
    }

    function NetFunnel_Action(e, t) {
        "use strict";
        var n = null,
            i = t.success,
            o = t.continued,
            r = t.stop,
            s = t.error,
            l = t.bypass,
            u = t.block,
            a = t.ipblock,
            p = t.expressnumber;
        return "object" == typeof(i = void 0 === t.success ? t : i) && (n = i), void 0 === r && (r = function(e, t) {
            return !1
        }), void 0 === s && (s = i), void 0 === l && (l = i), void 0 === p && (p = i), NetFunnel_init(null, e), NetFunnel_getTidChkEnter(null === n ? {
            success: i,
            error: s,
            stop: r,
            bypass: l,
            block: u,
            ipblock: a,
            expressnumber: p,
            continued: o
        } : {
            success: function(e, t) {
                null !== n && n.submit()
            },
            error: function(e, t) {
                null !== n && n.submit()
            },
            bypass: function(e, t) {
                null !== n && n.submit()
            },
            expressnumber: function(e, t) {
                null !== n && n.submit()
            },
            stop: r,
            block: u,
            ipblock: a,
            continued: o
        }), !1
    }

    function NetFunnel_Complete(e, t) {
        "use strict";
        return "function" != typeof t && (t = function(e, t) {
            return !1
        }), NetFunnel_init(null, e), NetFunnel_setComplete({
            success: t,
            error: t,
            bypass: t
        }), !1
    }

    function NetFunnel_AliveNotice(e, t) {
        "use strict";
        var n, i;
        return NetFunnel_init(null, e), "function" == typeof t ? NetFunnel_aliveNotice({
            success: t,
            error: t,
            bypass: t,
            continued: t
        }) : "object" == typeof t ? (n = t.success, i = t.continued, e = t.stop, NetFunnel_aliveNotice({
            success: n,
            error: t.error,
            stop: e,
            bypass: t.bypass,
            block: t.block,
            ipblock: t.ipblock,
            expressnumber: t.expressnumber,
            continued: i
        })) : NetFunnel_aliveNotice({
            success: t = function(e, t) {
                return !1
            },
            error: t,
            bypass: t,
            continued: t
        }), !1
    }

    function DefaultCallback_onSuccess(e, t, n) {
        "use strict";
        var i, o;
        return 0 < navigator.userAgent.toLowerCase().indexOf("chrome") && NetFunnel.gPop ? (i = n.getConfig("popup_target").document, (o = document.createElement("IMG")).src = "data:image/gif;base64," + NetFunnel.gFixelData, o.style.position = "absolute", o.style.top = "-10px", o.style.left = "-10px", o.style.display = "none", o.onload = o.onerror = function() {
            t.next(e, t), i.getElementsByTagName("body")[0].removeChild(o)
        }, i.getElementsByTagName("body")[0].appendChild(o)) : t.next(e, t), !1
    }
    NetFunnel.RTYPE_NONE = 0, NetFunnel.RTYPE_CHK_ENTER = 5002, NetFunnel.RTYPE_ALIVE_NOTICE = 5003, NetFunnel.RTYPE_SET_COMPLETE = 5004, NetFunnel.RTYPE_GET_TID_CHK_ENTER = 5101, NetFunnel.RTYPE_INIT = 5105, NetFunnel.RTYPE_STOP = 5106, NetFunnel.kSuccess = 200, NetFunnel.kContinue = 201, NetFunnel.kContinueDebug = 202, NetFunnel.kTsBypass = 300, NetFunnel.kTsBlock = 301, NetFunnel.kTsIpBlock = 302, NetFunnel.kTsExpressNumber = 303, NetFunnel.kTsErrorNoUservice = 500, NetFunnel.kTsErrorNoAction = 501, NetFunnel.kTsErrorAComplete = 502, NetFunnel.kTsErrorWrongServer = 503, NetFunnel.kTsErrorTooRecreate = 504, NetFunnel.kTsErrorNoKey = 505, NetFunnel.kTsErrorInvalidID = 506, NetFunnel.kTsErrorInvalidKey = 507, NetFunnel.kTsErrorInvalidIdStr = 508, NetFunnel.kTsErrorDuplicate = 509, NetFunnel.kTsErrorDelAction = 510, NetFunnel.kTsErrorUserviceExist = 511, NetFunnel.kTsErrorActionExist = 512, NetFunnel.kTsErrorLicenseOver = 513, NetFunnel.kTsErrorSize = 514, NetFunnel.kTsErrorNoUserAction = 515, NetFunnel.kTsErrorTooBigKey = 516, NetFunnel.kTsErrorInvalidIp = 517, NetFunnel.kErrorAuth = 900, NetFunnel.kErrorNotFound = 901, NetFunnel.kErrorNoinit = 902, NetFunnel.kErrorCode = 903, NetFunnel.kErrorParam = 904, NetFunnel.kErrorData = 905, NetFunnel.kErrorUnknownType = 906, NetFunnel.kErrorAlready = 907, NetFunnel.kErrorService = 908, NetFunnel.kErrorExecution = 909, NetFunnel.kErrorSock = 920, NetFunnel.kErrorSockSend = 921, NetFunnel.kErrorSockRecv = 922, NetFunnel.kErrorNotFoundLocalIP = 925, NetFunnel.kErrorSockConnect = 926, NetFunnel.kErrorNoConnect = 927, NetFunnel.kErrorSockData = 928, NetFunnel.kErrorIO = 991, NetFunnel.kErrorArunning = 992, NetFunnel.kErrorPermission = 993, NetFunnel.kErrorExpiredTime = 994, NetFunnel.kErrorOverCounter = 995, NetFunnel.kErrorSecurity = 996, NetFunnel.kErrorSystemStopping = 997, NetFunnel.kErrorNotSupport = 998, NetFunnel.kErrorSystem = 999, NetFunnel.PS_N_RUNNING = 0, NetFunnel.PS_RUNNING = 1, NetFunnel.PS_CONTINUE = 2, NetFunnel.PS_TIMEOUT = 3, NetFunnel.PS_ERROR = 99, NetFunnel.CONN_TIMEOUT_KEY = "connection_timeout", NetFunnel.gControl = null, NetFunnel.gShowtimeLimit = !1, NetFunnel.gShowcntLimit = !1, NetFunnel.gShownextLimit = !1, NetFunnel.gSkinId = "", NetFunnel.gPopupTop = !1, NetFunnel.gPopupLeft = !1, NetFunnel.gTotWait = -1, NetFunnel.gPrevWaitTime = -1, NetFunnel.gLastSkinID = "default", NetFunnel.gUseMobileUI = !1, NetFunnel.gUseUnfocus = !1, NetFunnel.gAlreadyProc = 0, NetFunnel.gWaitPop = null, NetFunnel.gIPBlockWaitCount = 0, NetFunnel.gNWaitCount = 0, NetFunnel.gNWaitTemp = 0, NetFunnel.gReTimer = null, NetFunnel.gDebugflag = !1, Function.prototype.bind || (Function.prototype.bind = function(e) {
        if ("function" != typeof this) throw new TypeError("Function.prototype.bind - what is trying to be bound is not callable");

        function t() {}

        function n() {
            return o.apply(this instanceof t && e ? this : e, i.concat(Array.prototype.slice.call(arguments)))
        }
        var i = Array.prototype.slice.call(arguments, 1),
            o = this;
        return t.prototype = this.prototype, n.prototype = new t, n
    }), NetFunnel.Util = {
        makeDebugMsg: function(e, t, n, i, o) {
            var r = "\n",
                s = "       ";
            1 == o && (r = "<br>", s = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
            var l = "Unknown",
                u = "Unkonwn Error";
            switch (t) {
                case NetFunnel.RTYPE_GET_TID:
                    l = "getTicketID";
                    break;
                case NetFunnel.RTYPE_CHK_ENTER:
                    l = "chkEnter";
                    break;
                case NetFunnel.RTYPE_ALIVE_NOTICE:
                    l = "aliveNotice";
                    break;
                case NetFunnel.RTYPE_SET_COMPLETE:
                    l = "setComplete";
                    break;
                case NetFunnel.RTYPE_GET_TID_CHK_ENTER:
                    l = "getTID+ChkEnter";
                    break;
                case NetFunnel.RTYPE_INIT:
                    l = "Init";
                    break;
                case NetFunnel.RTYPE_STOP:
                    l = "stop";
                    break;
                default:
                    l = "Unknown"
            }
            switch (n) {
                case NetFunnel.kSuccess:
                    u = "Normal";
                    break;
                case NetFunnel.kContinue:
                    u = "Continue";
                    break;
                case NetFunnel.kContinueDebug:
                    u = "Debug Continue mode";
                    break;
                case NetFunnel.kTsBypass:
                    u = "ServerSide Bypass";
                    break;
                case NetFunnel.kTsBlock:
                    u = "ServerSide Block";
                    break;
                case NetFunnel.kTsIpBlock:
                    u = "ServerSide Ip Block";
                    break;
                case NetFunnel.kErrorSystem:
                    u = "System Error";
                    break;
                case NetFunnel.kErrorSecurity:
                    u = "Security Error";
                    break;
                case NetFunnel.kErrorIO:
                    u = "I/O Error";
                    break;
                case NetFunnel.kErrorSockConnect:
                    u = "Connection Timeout";
                    break;
                case NetFunnel.kErrorAlready:
                    u = "Already Running";
                    break;
                case NetFunnel.kErrorNoinit:
                    u = "Init Error";
                    break;
                case NetFunnel.E_INSERT:
                    u = "Insert Error";
                    break;
                case NetFunnel.kErrorPermission:
                    u = "No Permission";
                    break;
                case NetFunnel.kErrorExpiredTime:
                    u = "Key Expire";
                    break;
                case NetFunnel.kErrorParam:
                    u = "Parameter Error";
                    break;
                case NetFunnel.E_NOT_STARTED:
                    u = "No service time";
                    break;
                case NetFunnel.kTsErrorNoUserAction:
                    u = "No action Error";
                    break;
                default:
                    u = "Unknown Error"
            }
            var a, p = e + " " + r + r + "  - type : " + l + " (" + t + ")" + r + " - Code : " + u + " (" + n + ")" + r + " - Params" + r;
            for (a in i) p += s + a + " ---\x3e " + i[a] + r;
            return p
        },
        goNextPage: function(e, t) {
            var n, i = e;
            for (n in t) i += "&" + n + "=" + t[n];
            document.location.href = i
        },
        alertDebugMsg: function(e) {
            alert(e)
        },
        decodeBase64: function(e) {
            var t, n, i, o, r, s, l = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
                u = "",
                a = 0;
            for (e = e.replace(/[^A-Za-z0-9+/=]/g, ""); i = l.indexOf(e.charAt(a++)), t = (15 & (o = l.indexOf(e.charAt(a++)))) << 4 | (r = l.indexOf(e.charAt(a++))) >> 2, n = (3 & r) << 6 | (s = l.indexOf(e.charAt(a++))), u += String.fromCharCode(i << 2 | o >> 4), 64 != r && (u += String.fromCharCode(t)), 64 != s && (u += String.fromCharCode(n)), a < e.length;);
            return u
        },
        getParam: function(e) {
            e = e.replace(/[[]/, "\\[").replace(/[\]]/, "\\]");
            e = new RegExp("[\\?&]" + e + "=([^&#]*)").exec(document.location.href);
            return null === e ? "" : e[1]
        },
        isSmartPhone: function() {
            var e = ["iPhone", "iPod", "iPad", "BlakBerry", "Android", "WindowsCE", "LG", "MOT", "SAMSUNG", "SonyEricsson", "Nokia", "Webos", "Opera mini", "Opera mobi", "Iemobile"];
            try {
                for (var t = 0; t < e.length; t++)
                    if (null !== navigator.userAgent.match(e[t])) return !0
            } catch (e) {}
            return !1
        },
        calcStdDev: function(e, t) {
            if ("object" != typeof e) return !1;
            if (e.length < 2) return !1;
            (1 < t || t < 0) && (t = 0);
            for (var n = 0, i = 0, i = 0; i < e.length; i++) n += parseInt(e[i], 10);
            var o = n / e.length,
                r = 0;
            for (i = 0; i < e.length; i++) r += (parseInt(e[i], 10) - o) * (parseInt(e[i], 10) - o);
            return Math.sqrt(r / (e.length - t))
        },
        delFocus: function(e) {
            try {
                var t = document,
                    n = (t = "object" == typeof e && "object" == typeof e.document ? e.document : t).getElementsByTagName("body")[0],
                    i = t.createElement("div");
                i.style.position = "absolute", i.style.width = "0px", i.style.height = "0px", i.style.border = "0px", i.style.top = NetFunnel.PopupUtil.getScrollTop(t), i.style.left = NetFunnel.PopupUtil.getScrollLeft(t), n.appendChild(i), i.focus();
                var o = i.parentNode;
                o && "object" == typeof o && o.removeChild(i)
            } catch (e) {}
        },
        isVirtualWait: function(e) {
            return "object" == typeof e && ("number" == typeof e.mprotect && 0 < e.mprotect)
        },
        getTimeStr: function(e, t, n, i, o) {
            e = parseInt(e, 10);
            void 0 === n && (n = " "), void 0 === i && (i = !1);
            var r = 0,
                s = 0,
                l = 0,
                u = 0,
                a = !1,
                p = !1,
                N = !1,
                c = !1,
                g = !1,
                h = (t = void 0 === t ? "%H시간 %M분 %S초" : t).match(/%[-]*[0-9]*[H|M|S]/g);
            for (u = 0; u < h.length; u++) "H" == (p = (a = h[u]).charAt(a.length - 1)) && (N = !0), "M" == p && (c = !0), "S" == p && (g = !0);
            1 == N && (s = Math.floor(e / 3600)), 1 == c && (r = 1 == N ? Math.floor(e % 3600 / 60) : Math.floor(e / 60)), 1 == g && (0 == N && 0 == c ? l = e : 1 == c ? l = e % 60 : 1 == N && 0 == c && (l = Math.floor(e % 3600)));
            for (var _ = "", F = t.split(n), m = 0; m < F.length; m++) {
                for (var d = F[m], h = d.match(/%[-]*[0-9]*[H|M|S]/g), f = !0, y = "", u = 0; h && u < h.length; u++) {
                    var T = "",
                        C = !1,
                        E = "&nbsp;",
                        b = !1,
                        k = 0,
                        p = (a = h[u]).charAt(a.length - 1);
                    if (2 < a.length) {
                        for (var S = "", P = !0, v = 1; v < a.length - 1; v++) {
                            var x = a[v];
                            "-" == x ? b = !0 : C = "0" == x && 1 == P ? !(P = !(E = "0")) : (S += x, !0)
                        }
                        k = parseInt(S, 10)
                    }
                    var I = "";
                    if ("H" == p ? (0 == s && (f = !1), I = "" + s) : "M" == p ? (0 == r && (f = !1), I = "" + r) : "S" == p && (I = "" + l), C) {
                        b && (T = I);
                        for (var R = k - I.length, w = 0; w < R; w++) T += E;
                        b || (T += I)
                    } else T = I;
                    y += o ? T + '<span style="' + o + '">' + d.substr(a.length, d.length - 1) + "</span>" : d.replace(a, T), u < 2 && (y += " ")
                }
                1 != i && 1 != f || (_ = 0 < _.length ? _ + n + y : y)
            }
            return _
        },
        getFrameWindowList: function(e) {
            for (var t = [], n = 0; n < top.frames.length; n++) {
                var i = top.frames[n];
                i !== e && t.push({
                    win: i,
                    popup: null
                })
            }
            return t
        }
    }, NetFunnel.BrowserDetect = {
        init: function() {
            this.browser = this.searchString(this.dataBrowser) || "An unknown browser", this.version = this.searchVersion(navigator.userAgent) || this.searchVersion(navigator.appVersion) || "an unknown version", this.OS = this.searchString(this.dataOS) || "an unknown OS";
            ! function(e) {
                var t = document.getElementsByTagName("head")[0],
                    n = document.createElement("link");
                n.rel = "preconnect", n.href = "https://fonts.gstatic.com";
                var i = document.createElement("link");
                i.type = "text/css", i.rel = "stylesheet", t.appendChild(n), t.appendChild(i), i.href = "https://fonts.googleapis.com/css?family=" + e.family
            }({
                family: "Noto+Sans+KR"
            })
        },
        searchString: function(e) {
            for (var t = 0; t < e.length; t++) {
                var n = e[t].string,
                    i = e[t].prop;
                if (this.versionSearchString = e[t].versionSearch || e[t].identity, n) {
                    if (-1 != n.indexOf(e[t].subString)) return e[t].identity
                } else if (i) return e[t].identity
            }
            return ""
        },
        searchVersion: function(e) {
            var t = e.indexOf(this.versionSearchString);
            return -1 == t ? 0 : parseFloat(e.substring(t + this.versionSearchString.length + 1))
        },
        dataBrowser: [{
            string: navigator.userAgent,
            subString: "Chrome",
            identity: "Chrome"
        }, {
            string: navigator.userAgent,
            subString: "OmniWeb",
            versionSearch: "OmniWeb/",
            identity: "OmniWeb"
        }, {
            string: navigator.vendor,
            subString: "Apple",
            identity: "Safari"
        }, {
            prop: window.opera,
            identity: "Opera"
        }, {
            string: navigator.vendor,
            subString: "iCab",
            identity: "iCab"
        }, {
            string: navigator.vendor,
            subString: "KDE",
            identity: "Konqueror"
        }, {
            string: navigator.userAgent,
            subString: "Firefox",
            identity: "Firefox"
        }, {
            string: navigator.vendor,
            subString: "Camino",
            identity: "Camino"
        }, {
            string: navigator.userAgent,
            subString: "Netscape",
            identity: "Netscape"
        }, {
            string: navigator.userAgent,
            subString: "MSIE",
            identity: "Explorer",
            versionSearch: "MSIE"
        }, {
            string: navigator.userAgent,
            subString: "Gecko",
            identity: "Mozilla",
            versionSearch: "rv"
        }, {
            string: navigator.userAgent,
            subString: "Mozilla",
            identity: "Netscape",
            versionSearch: "Mozilla"
        }],
        dataOS: [{
            string: navigator.platform,
            subString: "Win",
            identity: "Windows"
        }, {
            string: navigator.platform,
            subString: "Mac",
            identity: "Mac"
        }, {
            string: navigator.platform,
            subString: "Linux",
            identity: "Linux"
        }]
    }, NetFunnel.BrowserDetect.init(), "Explorer" == NetFunnel.BrowserDetect.browser && ("function" != typeof Array.push && (Array.prototype.push = function() {
        for (var e = this.length >>> 0, t = 0; t < arguments.length; t++) this[e] = arguments[t], e = e + 1 >>> 0;
        return this.length = e
    }), "function" != typeof Array.pop && (Array.prototype.pop = function() {
        var e, t = this.length >>> 0;
        return t && (e = this[--t], delete this[t]), this.length = t, e
    })), NetFunnel.getCommandStr = function(e, t) {
        var n = "",
            i = 0;
        switch ("recv" == e ? i = parseInt(t.substring(0, 4), 10) : 1 < (t = /opcode=([0-9]+)&/.exec(t)).length && (i = parseInt(t[1], 10)), i) {
            case 5101:
                n = "getTidchkEnter";
                break;
            case 5002:
                n = "chkEnter      ";
                break;
            case 5003:
                n = "aliveNotice   ";
                break;
            case 5004:
                n = "setComplete   ";
                break;
            default:
                n = "Unknown       "
        }
        return n
    }, NetFunnel.writeDebugMsg = function(e, t, n) {
        var i = new Date,
            o = parseInt(i.getHours(), 10),
            r = parseInt(i.getMinutes(), 10),
            s = parseInt(i.getSeconds(), 10),
            l = "";
        o < 10 && (l += "0"), l += o + ":", r < 10 && (r += "0"), l += r + ":", s < 10 && (s += "0"), l += s, l += "." + parseInt(i.getMilliseconds(), 10);
        r = "", s = "", i = "", i = "recv" == t ? (r = "padding-left:1px;", s = "#9E9E9E;", l + " | Recv | <b>" + NetFunnel.getCommandStr(t, n) + "</b> | ") : (r = "margin-top:5px;", s = "#EEEEEE;", l + " | Send | <b>" + NetFunnel.getCommandStr(t, n) + "</b> | "), i = "<div onload='this.focus()' style='width:650;overflow:hidden;padding:1px;border:1px solid #eeeeee;margin:0px;font-size:10px;font-family:monospace;background-color:" + s + r + "'>" + i + n.substring(0, 50) + "</div>";
        e && e.document && e.document.body && (n = e.document.body.innerHTML, e.document.body.innerHTML = n + i)
    }, NetFunnel.printDebugMsg = function(e, t) {
        NetFunnel.debugWindow = window.open("", "NetFunnel_debugWindow", "status=1,width=700,height=300,resizable=1,scrollbars=1"), "object" == typeof NetFunnel.debugWindow && NetFunnel.writeDebugMsg(NetFunnel.debugWindow, e, t)
    }, NetFunnel.Storage = function(e) {
        this.html5Support = this.supportsHtml5Storage(), "number" == typeof e && (this.type = e)
    }, NetFunnel.Storage.prototype.supportsHtml5Storage = function() {
        try {
            return "localStorage" in window && null !== window.localStorage
        } catch (e) {
            return !1
        }
    }, NetFunnel.Storage.prototype.html5Support = !1, NetFunnel.Storage.prototype.length = 0, NetFunnel.Storage.prototype.type = 1, NetFunnel.Storage.prototype.setStorageType = function(e) {
        this.type = e < 1 || 2 < e ? 1 : e
    }, NetFunnel.Storage.prototype.getStorage = function() {
        return 1 != this.type && 2 == this.type ? sessionStorage : localStorage
    }, NetFunnel.Storage.prototype.setItem = function(e, t, n, i) {
        try {
            return this.html5Support && this.getStorage().setItem(e, t), NetFunnel.Cookie.set(e, t, n, i), !0
        } catch (e) {
            return !1
        }
    }, NetFunnel.Storage.prototype.setItemStorageOnly = function(e, t, n, i) {
        try {
            return this.html5Support ? this.getStorage().setItem(e, t) : NetFunnel.Cookie.set(e, t, n, i), !0
        } catch (e) {
            return !1
        }
    }, NetFunnel.Storage.prototype.getItem = function(e, t) {
        var n = !1;
        try {
            return n = !this.html5Support || !((n = this.getStorage() && this.getStorage().getItem(e) ? this.getStorage().getItem(e).replace(/(?:\r\n|\r|\n)/g, "") : n) || null != t && 0 != t) ? NetFunnel.Cookie.get(e) : n
        } catch (e) {
            return !1
        }
    }, NetFunnel.Storage.prototype.removeItem = function(e, t) {
        try {
            return this.html5Support ? (this.getStorage().removeItem(e), null != t && 0 != t || NetFunnel.Cookie.del(e)) : NetFunnel.Cookie.del(e), !0
        } catch (e) {
            return !1
        }
    }, NetFunnel.Storage.prototype.clear = function() {
        try {
            return this.html5Support && this.getStorage().clear(), !0
        } catch (e) {
            return !1
        }
    }, NetFunnel.MProtect = function() {
        try {
            var e = new NetFunnel.Storage,
                t = (new Date).getTime(),
                n = e.getItem("NFMPT.data", !0);
            null === n && (n = "");
            var i = e.getItem("NFMPT.stdData", !0);
            null === i && (i = "");
            var o = parseInt(e.getItem("NFMPT.lastTime", !0), 10);
            !isNaN(o) && null !== o && "" != o || (o = 0);
            var r = parseInt(e.getItem("NFMPT.reqCnt", !0), 10);
            !isNaN(r) && null !== r && "" != r || (r = 0);
            var s = [],
                l = [];
            "" != n && (s = n.split(",")), "" != i && (l = i.split(",")), 0 != o && (s[s.length] = t - o, l[l.length] = t - o);
            for (var o = t, u = s.length - 1, a = 0; 0 <= u && !((a += parseInt(s[u], 10)) > NetFunnel.MP_TIMELIMIT); u--);
            var p = l.length - NetFunnel.MP_DEVCNTLIMIT;
            p < 0 && (p = 0);
            var N = l.slice(p),
                c = s.slice(u + 1);
            e.setItemStorageOnly("NFMPT.data", c.join(",")), e.setItemStorageOnly("NFMPT.stdData", N.join(",")), e.setItemStorageOnly("NFMPT.lastTime", o + ""), e.setItemStorageOnly("NFMPT.reqCnt", ++r + "");
            var g = NetFunnel.Util.calcStdDev(N, 0);
            if (0 != g && g < NetFunnel.MP_DEVLIMIT) return 2;
            if (c.length < NetFunnel.MP_MINCOUNT) return 0;
            if (c.length + 1 > NetFunnel.MP_MAXREQLIMIT) return 1;
            if (r > NetFunnel.MP_REQONLYLIMIT) return e.setItemStorageOnly("NFMPT.reqCnt", "0"), 3
        } catch (e) {}
        return 0
    }, NetFunnel.ProgressBar = function(e, t, n) {
        if (this._bar = null, this._bar2 = null, this._config = {}, this._obj = "string" == typeof e ? n.getElementById(e) : e, this._config.interval = 50, this._config.color = this._color, this._config.bgcolor = this._bgcolor, this._config.waitchk = 0, "object" == typeof t)
            for (var i in t) this._config[i] = t[i];
        this._tBody = n.createElement("div"), this._tBody.style.width = "100%", this._tBody.style.height = "100%", this._tBody.style.borderRadius = parseInt(this._obj.style.height, 10) / 2 + "px", this._tBody.style.overflow = "hidden", this._tBody.style.backgroundColor = this._config.bgcolor, this._tRun = n.createElement("div"), this._tRun.style.width = "0%", this._tRun.style.height = "100%", this._tRun.style.borderRadius = parseInt(this._obj.style.height, 10) / 2 + "px", this._tRun.style.backgroundColor = this._config.color, this._tBody.appendChild(this._tRun), this._obj.appendChild(this._tBody), this.show = function() {
            this._obj.style.visibility = "visible";
            var e = this;
            this._timer = setInterval(function() {
                e._action(0)
            }, this._config.interval)
        }, this.hide = function() {
            this._obj.style.visibility = "hidden", this._timer && (clearTimeout(this._timer), this._timer = null)
        }, this._action = function() {
            try {
                0 != this._config.waitchk && parseInt(this._config.waitchk, 10) < parseInt(NetFunnel.gLastData.nwait, 10) && (NetFunnel.gLastData.nwait = this._config.waitchk), NetFunnel.gTotWait <= 0 && (NetFunnel.gTotWait = NetFunnel.gLastData.nwait), parseInt(NetFunnel.gLastData.nwait, 10) > parseInt(NetFunnel.gTotWait, 10) && (NetFunnel.gTotWait = NetFunnel.gLastData.nwait);
                var e = 100 - NetFunnel.gLastData.nwait / NetFunnel.gTotWait * 100;
                100 < (e = e < 0 ? 0 : e) && (e = 100), this._tRun.style.width = e + "%", this._config.waitchk = NetFunnel.gLastData.nwait
            } catch (e) {}
            return !0
        }
    }, NetFunnel.ProgressBar.prototype._mmm = 0, NetFunnel.ProgressBar.prototype._curr = 0, NetFunnel.ProgressBar.prototype._direct = 0, NetFunnel.ProgressBar.prototype._obj = null, NetFunnel.ProgressBar.prototype._cells = null, NetFunnel.ProgressBar.prototype._timer = null, NetFunnel.ProgressBar.prototype._oTable = null, NetFunnel.ProgressBar.prototype._config = null, NetFunnel.ProgressBar.prototype._color = "#4F7FF9", NetFunnel.ProgressBar.prototype._bgcolor = "#CACACA", NetFunnel.Cookie = {
        set: function(e, t, n, i) {
            e = e + "=" + escape(t);
            void 0 !== n && n.constructor == Number && 0 < n && ((t = new Date).setMinutes(t.getMinutes() + n), e += ";expires=" + t.toGMTString()), void 0 !== i && i.constructor == String && "" != i ? e += ";domain=" + i : "" != NetFunnel.TS_COOKIE_DOMAIN && (e += ";domain=" + NetFunnel.TS_COOKIE_DOMAIN), i && -1 < location.protocol.indexOf("https") ? e += ";path=/; samesite=none; secure;" : e += ";path=/;", document.cookie = e
        },
        del: function(e) {
            NetFunnel.Cookie.set(e, "", -1)
        },
        get: function(e) {
            if (0 < document.cookie.length && -1 != (t = document.cookie.indexOf(e + "="))) {
                var t = t + e.length + 1,
                    e = document.cookie.indexOf(";", t);
                return -1 == e && (e = document.cookie.length), unescape(document.cookie.substring(t, e).replace(/(?:\r\n|\r|\n)/g, ""))
            }
            return ""
        }
    }, NetFunnel.getUrlParameters = function(e) {
        if ("string" != typeof e || "" == e) return "";
        var t = "",
            n = document.location.href;
        if (-1 < n.indexOf("?"))
            for (var i = n.substr(n.indexOf("?")).split("&"), o = 0; o < i.length; o++)
                if (-1 < i[o].indexOf(e + "=")) {
                    var r = i[o].indexOf(e + "=") + e.length + 1,
                        t = i[o].substr(r);
                    break
                }
        return unescape(t)
    }, NetFunnel.gPop = null, NetFunnel.gTimer = null, NetFunnel.gLastData = null, NetFunnel.countdown_stop = function() {
        try {
            NetFunnel.Util.isVirtualWait(NetFunnel.gLastData) || (NetFunnel.gControl.fireEvent(null, NetFunnel.gControl, "onStop", {
                next: NetFunnel.gControl.next.stop
            }), NetFunnel_sendStop(), NetFunnel.gPop && (NetFunnel.gPop.hide(), NetFunnel.gPop.destroy(), delete NetFunnel.gPop, NetFunnel.gPop = null), 1 == NetFunnel.gControl.getConfig("use_frame_block") && NetFunnel.PopupUtil.hideBlockList(NetFunnel.gControl.getConfig("frame_block_list")))
        } catch (e) {}
    }, NetFunnel.countdown = function() {
        if (NetFunnel.gLastData && 0 <= NetFunnel.gLastData.time_left && NetFunnel.gPop) {
            var tTime = NetFunnel.gPop.getObj("NetFunnel_Loading_Popup_TimeLeft"),
                tCount = NetFunnel.gPop.getObj("NetFunnel_Loading_Popup_Count"),
                tNext = NetFunnel.gPop.getObj("NetFunnel_Loading_Popup_NextCnt");
            0 != this._gNWaitView && parseInt(this._gNWaitView, 10) < parseInt(NetFunnel.gLastData.nwait, 10) && (NetFunnel.gLastData.nwait = this._gNWaitView), this._gNWaitView = NetFunnel.gLastData.nwait;
            var numWithCommas = function(e) {
                    return e.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
                },
                tformat = "",
                tformatArr = null,
                shownextLimit = 0,
                showcntLimit = 0,
                showtimeLimit;
            if (tCount && (showcntLimit = NetFunnel.gControl.getConfig("showcnt_limit"), 0 < showcntLimit && NetFunnel.gLastData.nwait > showcntLimit ? (tformat = tCount.className, 0 < tformat.length ? tCount.innerHTML = tformat : tCount.innerHTML = NetFunnel.TS_LIMIT_TEXT) : tCount.innerHTML = String(numWithCommas(NetFunnel.gLastData.nwait))), tNext && (shownextLimit = NetFunnel.gControl.getConfig("shownext_limit"), null == NetFunnel.gLastData.nnext ? tNext.innerHTML = "0" : 0 < shownextLimit && NetFunnel.gLastData.nnext > shownextLimit ? (tformat = tNext.className, 0 < tformat.length ? tNext.innerHTML = tformat : tNext.innerHTML = NetFunnel.TS_LIMIT_TEXT) : tNext.innerHTML = String(numWithCommas(NetFunnel.gLastData.nnext))), tTime && (showtimeLimit = NetFunnel.gControl.getConfig("showtime_limit"), 0 < showtimeLimit && NetFunnel.gLastData.real_time_left > showtimeLimit ? (tformat = tTime.className, tformatArr = tformat.split("^"), 4 == tformatArr.length && 0 < tformatArr[3].length ? tTime.innerHTML = tformatArr[3] : 5 <= tTime.innerHTML.length ? tTime.innerHTML = "." : tTime.innerHTML += ".") : (tformat = tTime.className, 0 < tformat.length ? (tformatArr = tformat.split("^"), tTime.innerHTML = NetFunnel.Util.getTimeStr(NetFunnel.gLastData.real_time_left, tformatArr[0], tformatArr[1], eval(tformatArr[2]), tformatArr[3])) : tTime.innerHTML = NetFunnel.Util.getTimeStr(NetFunnel.gLastData.real_time_left))), NetFunnel.gTextDecoration) try {
                "object" == typeof tTime && ("none" == tTime.style.textDecoration ? tTime.style.textDecoration = "underline" : tTime.style.textDecoration = "none"), "object" == typeof tNext && ("none" == tNext.style.textDecoration ? tNext.style.textDecoration = "underline" : tNext.style.textDecoration = "none"), "object" == typeof tCount && ("none" == tCount.style.textDecoration ? tCount.style.textDecoration = "underline" : tCount.style.textDecoration = "none")
            } catch (e) {}
        }
        var leftPerc, skinObj, self;
        NetFunnel.gLastData.time_left <= 0 && NetFunnel.gTimer ? NetFunnel.gPop : (leftPerc = 0, skinObj = NetFunnel.SkinUtil.get(NetFunnel.gSkinId, NetFunnel.Util.isSmartPhone()), "function" == typeof skinObj.updateCallback && (leftPerc = parseInt(NetFunnel.gTotWait, 10) <= 0 ? 0 : (parseInt(NetFunnel.gTotWait, 10) < parseInt(NetFunnel.gLastData.nwait, 10) && (NetFunnel.gTotWait = parseInt(NetFunnel.gLastData.nwait, 10)), parseInt(100 * (NetFunnel.gTotWait - NetFunnel.gLastData.nwait) / NetFunnel.gTotWait, 10)), skinObj.updateCallback(leftPerc, NetFunnel.gLastData.nwait, NetFunnel.gTotWait, NetFunnel.gLastData.real_time_left, !0)), NetFunnel.gLastData.time_left--, self = this, NetFunnel.gTimer = setTimeout(function() {
            self.countdown()
        }, 500))
    }, NetFunnel.SkinUtil = {
        prevID: "",
        add: function(e, t, n) {
            "use strict";
            try {
                return "string" != typeof e || "" == e ? !1 : "object" == typeof t && ("string" == typeof n && "" != n || (n = "normal"), "object" != typeof NetFunnel.Skin[e] && (NetFunnel.Skin[e] = {}), NetFunnel.Skin[e][n] = t, NetFunnel.gLastSkinID = e, !0)
            } catch (e) {
                return !1
            }
        },
        get: function(e, t) {
            "use strict";
            try {
                "string" == typeof e && "" != e || (e = NetFunnel.gLastSkinID);
                var n = "normal";
                return 1 == NetFunnel.gUseMobileUI && 1 == t && (n = "mobile"), "object" == typeof NetFunnel.Skin[e] && "object" == typeof NetFunnel.Skin[e][n] ? NetFunnel.Skin[e][n] : ("" != NetFunnel.TS_SKIN_ID && NetFunnel.TS_SKIN_ID != e && "object" == typeof NetFunnel.Skin[NetFunnel.TS_SKIN_ID] && "object" == typeof NetFunnel.Skin[NetFunnel.TS_SKIN_ID][n] ? NetFunnel.Skin[NetFunnel.TS_SKIN_ID] : NetFunnel.Skin["default"])[n]
            } catch (e) {}
            return NetFunnel.Skin["default"].normal
        }
    }, NetFunnel.mtstr = '<div id="NetFunnel_Skin_Top" style="background-color:#ffffff; width:290px; height:280px; font-family:\'Noto Sans KR\', sans-serif !important;">     <div style="padding: 10px 10px 0px 20px;"> \t\t<div style="padding-top:5px; padding-right:5px; line-height:25px;">     \t\t<span style="text-align: left;"><a href="' + NetFunnel.gLogoURL + '" target="_blank" style="cursor:pointer;text-decoration:none;">', "Explorer" == NetFunnel.BrowserDetect.browser && "6" == NetFunnel.BrowserDetect.version || "" == NetFunnel.gLogoData ? NetFunnel.mtstr += '<b style="font-size:12px !important;">' + NetFunnel.gLogoText + "</b></a>" : NetFunnel.mtstr += '<b style="font-size:12px !important;">' + NetFunnel.gLogoText + '</b><img style="width:50px; height:25px; color:black; font-size:11px !important;" border=0 src="data:image/gif;base64,' + NetFunnel.gLogoData + '" ></a>', NetFunnel.mtstr += '</span>     \t\t<b style="text-align:right;"><span id="NetFunnel_Loding_Popup_Debug_Alerts" style="color:#ff0000;"></span></b>         </div>     </div>     <div style="padding-top:0px; padding-left:5px; padding-right:5px; line-height: initial;">         <div style="box-sizing:initial; text-align:center; ">             <b style="font-size:16px !important; color:#525252;font-family:\'Noto Sans KR\', sans-serif !important;">서비스 <span style="color:#4F7FF9;">접속대기 중</span>입니다.</b>         </div>         <div style="box-sizing:initial;text-align:center;font-size:15px !important;color:#525252;padding-top:4px;white-space:nowrap; text-overflow:ellipsis; overflow:hidden;font-family:\'Noto Sans KR\', sans-serif !important;">             <b>예상대기시간 :</b>                 <span id="NetFunnel_Loading_Popup_TimeLeft" class="%H시간 %M분 %02S초^ ^false^font-size:15px !important;color:#525252;" style="color: #4F7FF9; font-size: 21px !important;"></span>         </div>         <div style="margin:auto; box-sizing: initial; padding-top: 6px; padding-bottom: 6px; width:190px; height:8px; visibility: visible;" id="NetFunnel_Loading_Popup_Progressbar"></div>         <div style="box-sizing:initial; width:100%; padding-bottom:8px; overflow:hidden; color:#6C6C6C; text-align:center; font-size:12px !important;">             <div style="padding-left:5px">                 <div style="box-sizing:initial;text-align:center;padding:3px;padding-top:10px;white-space:nowrap; text-overflow:ellipsis; overflow:hidden;font-size:11px !important;font-weight:normal !important;font-family:\'Noto Sans KR\', sans-serif !important;">고객님 앞에                     <span style="color:#00BF08; font-size:11px !important; font-weight: bold !important;">                         <span id="NetFunnel_Loading_Popup_Count" class="' + NetFunnel.TS_LIMIT_TEXT + '"></span><span style="font-weight:normal !important;font-size:11px !important;">명</span>                    </span>                     의 대기자가 있습니다.                 </div>                 <div style="padding:1px;font-size:11px !important;">현재 접속 사용자가 많아 대기 중이며, </div>                 <div style="padding:1px;font-size:11px !important;">잠시만 기다리시면 </div>                 <div style="padding:1px;font-size:11px !important;">서비스로 자동 접속 됩니다.</div>                 <div style="padding-top:10px;">                     <div id="NetFunnel_Countdown_Stop" style="position:relative;box-sizing:initial; cursor:pointer; margin:auto; width:40px; height:25px; border:1px solid #e8e8e8; padding-left:15px; line-height:22px;font-size:11px !important;">                         <div class="NetFunnel_stop_x" style="position:absolute; width:25px; height:25px; top:0; left:0; color:#CCC;"></div>                    중지</div>                     </div>                 <div style="padding-top:5px;font-size:11px !important;">                     재 접속하시면 대기시간이 더 길어집니다. </div>             </div>         </div>         <div style="height:5px;"></div>     </div>     <style>.NetFunnel_stop_x:after{content:\'\\d7\'; font-size:14px !important;}</style> </div> ', NetFunnel.SkinUtil.add("default", {
        htmlStr: NetFunnel.mtstr
    }, "mobile"), NetFunnel.tstr = '<div id="NetFunnel_Skin_Top" style="background-color:#ffffff; width:438px; height:376px; font-family:\'Noto Sans KR\', sans-serif;">     <div style="padding: 10px 10px 0px 20px;"> \t\t<div style="padding-top:5px; padding-right:5px; font-size:10px !important;">     \t\t<span style="text-align: left;"><a href="' + NetFunnel.gLogoURL + '" target="_blank" style="cursor:pointer;text-decoration:none;">', "Explorer" == NetFunnel.BrowserDetect.browser && "6" == NetFunnel.BrowserDetect.version || "" == NetFunnel.gLogoData ? NetFunnel.tstr += '<b style="font-size:12px !important;">' + NetFunnel.gLogoText + "</b></a>" : NetFunnel.tstr += '<b style="font-size:12px !important;">' + NetFunnel.gLogoText + '</b><img style="width:65px; height:30px; color:black; font-size:11px !important;" border=0 src="data:image/gif;base64,' + NetFunnel.gLogoData + '" ></a>', NetFunnel.tstr += '</span>     \t\t<b style="text-align:right;"><span id="NetFunnel_Loding_Popup_Debug_Alerts" style="color:#ff0000;"></span></b>         </div>     </div>     <div style="padding-top:0px; padding-left:5px; padding-right:5px; line-height: initial !important;">         <div style="box-sizing:initial; text-align:center; font-size:21px !important; color:#525252;">             <b>서비스 <span style="color:#4F7FF9;">접속대기 중</span>입니다.</b>         </div>         <div style="box-sizing:initial;text-align:center;font-size:18px !important;color:#525252;padding-top:4px; white-space:nowrap; text-overflow:ellipsis; overflow:hidden;">             <b>예상대기시간 :</b>                 <span id="NetFunnel_Loading_Popup_TimeLeft" class="%H시간 %M분 %02S초^ ^false^font-size:18px !important;color:#525252;" style="color: #4F7FF9; font-size: 32px !important;"></span>         </div>         <div style="margin:auto; box-sizing: initial; padding-top: 6px; padding-bottom: 6px; width:270px; height:8px; visibility: visible;" id="NetFunnel_Loading_Popup_Progressbar"></div>         <div style="box-sizing:initial; width:100%; padding-bottom:8px; overflow:hidden; color:#6C6C6C; text-align:center; ">             <div style="padding-left:5px">                 <div style="box-sizing:initial;font-size:14px !important;text-align:center;padding:3px;padding-top:10px;white-space:nowrap; text-overflow:ellipsis; overflow:hidden;">고객님 앞에                     <span style="color:#00BF08; font-size:24px !important; font-weight: bold;">                         <span id="NetFunnel_Loading_Popup_Count" class="' + NetFunnel.TS_LIMIT_TEXT + '"></span><span style="font-weight:normal;font-size:14px !important;">명</span>                    </span>                    , 뒤에                     <b>                         <span style="color:#00BF08;  font-weight: bold;">                             <span id="NetFunnel_Loading_Popup_NextCnt" class="' + NetFunnel.TS_LIMIT_TEXT + '"></span><span style="font-weight:normal;font-size:14px !important;">명</span>                        </span>                     </b>                     의 대기자가 있습니다.                 </div>                 <div style="box-sizing:initial;padding:3px;font-size:14px !important;">현재 접속 사용자가 많아 대기 중이며, 잠시만 기다리시면 </div>                 <div style="box-sizing:initial;padding:3px;font-size:14px !important;">서비스로 자동 접속 됩니다.</div>                 <div style="box-sizing:initial;padding-top:25px;font-size:14px !important;">                     <div id="NetFunnel_Countdown_Stop" style="box-sizing:initial; position:relative; cursor:pointer; margin:auto; width:50px; height:30px; border:1px solid #e8e8e8;padding-left:15px;line-height:27px;font-size:14px !important;">                         <div class="NetFunnel_stop_x" style="position:absolute; width:30px; height:30px; top:0; left:0; color:#CCC;"></div>                    중지</div>                     </div>                 <div style="box-sizing:initial;padding-top:10px;font-size:14px !important;">                     재 접속하시면 대기시간이 더 길어집니다. </div>             </div>         </div>         <div style="height:5px;"></div>     </div>     <style>.NetFunnel_stop_x:after{content:\'\\d7\'; font-size:17px !important; line-height:27px !important;}#NetFunnel_Countdown_Stop:hover{background-color:#f7f7f7;}</style> </div> ', NetFunnel.SkinUtil.add("default", {
        htmlStr: NetFunnel.tstr
    }, "normal"), NetFunnel.PopupSetup = function(e, t, n) {
        null !== n && "" != n || (n = NetFunnel.gSkinId);
        n = NetFunnel.SkinUtil.get(n, NetFunnel.Util.isSmartPhone());
        "vcontinue" === e && (t.data.nwait = 1e6, t.data.ttl = "2", t.data.tps = 30), "alert" != e && "object" == typeof t && (NetFunnel.gLastData = t.data, NetFunnel.gLastData.time_left = parseInt(t.data.ttl, 10), NetFunnel.gLastData.tps = parseInt(t.data.tps, 10), 0 == NetFunnel.gLastData.tps && (NetFunnel.gLastData.tps = 1), NetFunnel.gLastData.real_time_left = Math.round(parseInt(t.data.nwait, 10) / NetFunnel.gLastData.tps), NetFunnel.gLastData.real_time_left < 1 && (NetFunnel.gLastData.real_time_left = 1), -1 < NetFunnel.gPrevWaitTime && NetFunnel.gLastData.real_time_left > NetFunnel.gPrevWaitTime && (NetFunnel.gLastData.real_time_left = NetFunnel.gPrevWaitTime), NetFunnel.gPrevWaitTime = NetFunnel.gLastData.real_time_left, NetFunnel.gTotWait < 0 && (NetFunnel.gTotWait = NetFunnel.gLastData.nwait)), NetFunnel.gPop || (NetFunnel.gPop = new NetFunnel.Popup(n.htmlStr, NetFunnel.gPopupTop, NetFunnel.gPopupLeft, NetFunnel.gControl.getConfig("popup_target"), !1, !1, NetFunnel.gControl.getConfig("popup_zindex")), "function" == typeof n.prepareCallback && n.prepareCallback(), this._gNWaitView = 0), NetFunnel.gPop.show();
        NetFunnel.gPop.getObj("NetFunnel_Loding_Popup_Debug_Alerts") && (NetFunnel.gDebugflag ? NetFunnel.gPop.getObj("NetFunnel_Loding_Popup_Debug_Alerts").innerHTML = " Debug Mode " : NetFunnel.gPop.getObj("NetFunnel_Loding_Popup_Debug_Alerts").innerHTML = ""), 1 == NetFunnel.gControl.getConfig("use_frame_block") && NetFunnel.PopupUtil.showBlockList(NetFunnel.gControl.getConfig("frame_block_list")), "alert" != e && NetFunnel.countdown()
    }, NetFunnel.DefaultCallback = {
        onSuccess: function(e, t, n) {
            NetFunnel.gTimer && clearTimeout(NetFunnel.gTimer), NetFunnel.gPop && !n.getConfig("success_popup_visibility") && (NetFunnel.gPop.hide(), NetFunnel.gPop.destroy(), delete NetFunnel.gPop, NetFunnel.gPop = null), 1 == n.getConfig("use_frame_block") && NetFunnel.PopupUtil.hideBlockList(n.getConfig("frame_block_list")), "string" == typeof t.next && "" != t.next ? document.location.href = t.next : "function" == typeof t.next && DefaultCallback_onSuccess(e, t, n)
        },
        onContinued: function(e, t) {
            "string" != typeof t.next ? "function" == typeof t.next && 0 == t.next(e, t) || t.rtype != NetFunnel.RTYPE_CHK_ENTER && t.rtype != NetFunnel.RTYPE_GET_TID_CHK_ENTER || (NetFunnel.gTimer && clearTimeout(NetFunnel.gTimer), NetFunnel.PopupSetup("continue", t, NetFunnel.gSkinId)) : document.location.href = t.next
        },
        onError: function(e, t, n) {
            NetFunnel.gPop && (NetFunnel.gPop.hide(), NetFunnel.gPop.destroy(), delete NetFunnel.gPop, NetFunnel.gPop = null), 1 == n.getConfig("use_frame_block") && NetFunnel.PopupUtil.hideBlockList(n.getConfig("frame_block_list")), "string" != typeof t.next || "" == t.next ? "function" == typeof t.next && t.next(e, t) : document.location.href = t.next
        },
        onStop: function(e, t) {
            "string" != typeof t.next || "" == t.next ? "function" == typeof t.next && t.next(e, t) : document.location.href = t.next
        },
        onBypass: function(e, t, n) {
            NetFunnel.gTimer && clearTimeout(NetFunnel.gTimer), NetFunnel.gPop && (NetFunnel.gPop.hide(), NetFunnel.gPop.destroy(), delete NetFunnel.gPop, NetFunnel.gPop = null), 1 == n.getConfig("use_frame_block") && NetFunnel.PopupUtil.hideBlockList(n.getConfig("frame_block_list")), "string" != typeof t.next || "" == t.next ? "function" == typeof t.next && t.next(e, t) : document.location.href = t.next
        },
        onExpressnumber: function(e, t, n) {
            NetFunnel.gTimer && clearTimeout(NetFunnel.gTimer), NetFunnel.gPop && (NetFunnel.gPop.hide(), NetFunnel.gPop.destroy(), delete NetFunnel.gPop, NetFunnel.gPop = null), 1 == n.getConfig("use_frame_block") && NetFunnel.PopupUtil.hideBlockList(n.getConfig("frame_block_list")), "string" != typeof t.next || "" == t.next ? "function" == typeof t.next && t.next(e, t) : document.location.href = t.next
        },
        onBlock: function(e, t, n) {
            NetFunnel.gTimer && clearTimeout(NetFunnel.gTimer), NetFunnel.gPop && (NetFunnel.gPop.hide(), NetFunnel.gPop.destroy(), delete NetFunnel.gPop, NetFunnel.gPop = null), "string" != typeof t.next || "" == t.next ? "function" == typeof t.next && 0 == t.next(e, t) || ("string" == typeof n.getConfig("block_url") && "" != n.getConfig("block_url") ? document.location.href = n.getConfig("block_url") : "" == n.getConfig("block_msg") || "string" != typeof n.getConfig("block_msg") ? alert("[NetFUNNEL]Service Block!") : alert(n.getConfig("block_msg"))) : document.location.href = t.next
        },
        onIpBlock: function(e, t) {
            "string" != typeof t.next ? "function" == typeof t.next && 0 == t.next(e, t) || (NetFunnel.gTimer && clearTimeout(NetFunnel.gTimer), NetFunnel.PopupSetup("vcontinue", t, NetFunnel.gSkinId)) : document.location.href = t.next
        }
    }, NetFunnel.Event = function() {
        this.events = [], this.builtinEvts = []
    }, NetFunnel.Event.prototype.getActionIdx = function(e, t, n, i) {
        if (e && t) {
            var o = this.events[e][t];
            if (!o) return -1;
            for (var r = o.length - 1; 0 <= r; r--)
                if (o[r].action == n && o[r].binding == i) return r
        }
        return -1
    }, NetFunnel.Event.prototype.addListener = function(e, t, n, i) {
        var o;
        this.events[e] ? this.events[e][t] ? -1 == this.getActionIdx(e, t, n, i) && ((o = this.events[e][t])[o.length] = {
            action: n,
            binding: i
        }) : (this.events[e][t] = [], this.events[e][t][0] = {
            action: n,
            binding: i
        }) : (this.events[e] = [], this.events[e][t] = [], this.events[e][t][0] = {
            action: n,
            binding: i
        })
    }, NetFunnel.Event.prototype.removeListener = function(e, t, n, i) {
        this.events[e] && (!this.events[e][t] || 0 <= (i = this.actionExists(e, t, n, i)) && this.events[e][t].splice(i, 1))
    }, NetFunnel.Event.prototype.fireEvent = function(e, t, n, i) {
        if (e = e || window.event, t && this.events) {
            var o = this.events[t];
            if (o) {
                var r = o[n];
                if (r)
                    for (var s = 0; r.length > s; s++) {
                        var l = r[s].action;
                        (l = r[s].binding ? l.bind(r[s].binding) : l)(e, i, t)
                    }
            }
        }
    }, NetFunnel.gPopup = [], NetFunnel.PopupUtil = {
        getViewportHeight: function(e, t) {
            return e.innerHeight != e.undefined ? e.innerHeight : "CSS1Compat" == t.compatMode ? t.documentElement.clientHeight : t.body ? t.body.clientHeight : e.undefined
        },
        getViewportWidth: function(e, t) {
            return e.innerWidth != e.undefined ? e.innerWidth : "CSS1Compat" == t.compatMode ? t.documentElement.clientWidth : t.body ? t.body.clientWidth : 0
        },
        getScrollTop: function(e) {
            return e.pageYOffset || (e.documentElement && "number" == typeof e.documentElement.scrollTop ? e.documentElement.scrollTop : e.body ? e.body.scrollTop : 0)
        },
        getScrollLeft: function(e) {
            return e.pageXOffset || (e.documentElement && "number" == typeof e.documentElement.scrollLeft ? e.documentElement.scrollLeft : e.body ? e.body.scrollLeft : 0)
        },
        resizePopup: function() {
            for (var e = 0; NetFunnel.gPopup.length > e; e++) NetFunnel.gPopup[e]._centerPopWin()
        },
        getObjWidth: function(e) {
            if (!e) return 0;
            return parseInt(e.style.width, 10) > parseInt(e.offsetWidth, 10) ? parseInt(e.style.width, 10) : e.offsetWidth
        },
        getObjHeight: function(e) {
            if (!e) return 0;
            return parseInt(e.style.height, 10) > parseInt(e.offsetHeight, 10) ? parseInt(e.style.height, 10) : e.offsetHeight
        },
        showBlockList: function(e) {
            for (var t = 0; t < e.length; t++) try {
                var n = e[t];
                n.popup = new NetFunnel.Popup("", NetFunnel.gPopupTop, NetFunnel.gPopupLeft, n.win, !1, !1, NetFunnel.gControl.getConfig("popup_zindex")), n.popup.show()
            } catch (e) {}
        },
        hideBlockList: function(e) {
            for (var t = 0; t < e.length; t++) try {
                var n = e[t];
                n.popup && (n.popup.hide(), n.popup.destroy(), delete n.popup, n.popup = null)
            } catch (e) {}
        },
        hideWaitPopup: function() {
            "object" == typeof NetFunnel && NetFunnel.gWaitPop && (NetFunnel.gWaitPop.hide(), NetFunnel.gWaitPop.destroy(), NetFunnel.gWaitPop = null)
        },
        showWaitPopup: function() {
            var e;
            "object" == typeof NetFunnel && (e = '<div style="padding:2px;border:1px solid darkgray;"> \t\t\t\t<table> \t\t\t\t\t<tr>', "Explorer" == NetFunnel.BrowserDetect.browser && "6" == NetFunnel.BrowserDetect.version ? e += "<td></td>" : e += '<td><img style="" border=0 src="data:image/gif;base64,' + NetFunnel.gPreWaitData + '" ></td>', NetFunnel.gWaitPop = new NetFunnel.Popup(e += '\t<td style="valign:middle;font-size:9pt">wait...</td> \t\t\t\t\t</tr> \t\t\t\t</table> \t\t\t</div>', !1, !1, NetFunnel.gControl, !0, "NetFunnel_Waiting_Popup", NetFunnel.gControl.getConfig("popup_zindex")), NetFunnel.gWaitPop.show())
        },
        getDocumentEntireHeight: function(e) {
            var t = e.body,
                e = e.documentElement;
            return Math.max(t.scrollHeight, t.offsetHeight, e.clientHeight, e.scrollHeight, e.offsetHeight)
        }
    }, NetFunnel.Popup = function(e, t, n, i, o, r, s) {
        "object" == typeof i && "object" == typeof(this._mWin = i).document ? this._mDoc = i.document : (this._mWin = window, this._mDoc = document), "boolean" != typeof o && (o = !1), "string" != typeof r && (r = "NetFunnel_Loading_Popup"), void 0 === s || isNaN(s) || (this._mZindex = s);
        var l, u, a, i = this._mDoc.getElementsByTagName("BODY")[0];
        i && ((s = this._mDoc.getElementById(r)) && NetFunnel.SkinUtil.prevID == NetFunnel.gSkinId || ((s = this._mDoc.createElement("div")).id = r, s.style.display = "none", s.style.top = 0, s.style.left = 0, s.innerHTML = e, i.appendChild(s), (r = this._mDoc.getElementById("NetFunnel_Loading_Popup_Progressbar")) && (u = {
            count: 50,
            interval: 50
        }, e = parseInt(r.style.width, 10), l = parseInt(r.style.height, 10), isNaN(e) || (u.width = e), isNaN(l) || (u.height = l), (l = new NetFunnel.ProgressBar(r, u, this._mDoc)).show(), this._mProgress = l), (u = this._mDoc.getElementById("NetFunnel_Countdown_Stop")) && (u.onclick = NetFunnel.countdown_stop), this.new_draw = !0), NetFunnel.SkinUtil.prevID = NetFunnel.gSkinId, l = this._mDoc.getElementById("mpopup_bg"), u = this._mDoc.getElementById("pop_iframe"), l || ((l = this._mDoc.createElement("div")).id = "mpopup_bg", l.innerHTML = "<div style='width:100%; height:100%'>&nbsp;</div>", l.style.zIndex = this._mZindex + 1, l.style.top = "0px", l.style.left = "0px", l.style.width = "100%", "Mozilla" == (a = NetFunnel.BrowserDetect.browser) || "Explorer" == a ? (l.style.backgroundColor = "rgb(80,80,80)", l.style.filter = "alpha(opacity=30)", l.style.opacity = "0.3", l.style.position = "absolute", l.style.height = NetFunnel.PopupUtil.getDocumentEntireHeight(this._mDoc) + "px") : (l.style.position = "fixed", l.style.height = "100%", l.style.backgroundColor = "rgba(80,80,80,0.3)"), l.style.margin = "0", l.style.padding = "0", l.style.border = "0px solid black", l.fontSize = "0", i.appendChild(l)), u || ((u = this._mDoc.createElement("div")).id = "pop_iframe", u.frameborder = "0", u.border = "0", u.framespacing = "0", u.marginheight = "0", u.marginwidth = "0", o ? (u.style.opacity = "0", u.style.filter = "alpha(opacity=0)") : (u.style.opacity = "0.5", u.style.filter = "alpha(opacity=50)"), u.style.zIndex = this._mZindex, u.style.top = "0px", u.style.left = "0px", u.style.width = "100%", u.style.position = "fixed", u.style.border = "0px solid #FFFFFF", u.style.backgroundColor = "#FFFFFF", "Mozilla" == (a = NetFunnel.BrowserDetect.browser) || "Explorer" == a ? (u.style.backgroundColor = "rgb(80,80,80)", o ? (u.style.opacity = "0", u.style.filter = "alpha(opacity=0)") : (u.style.opacity = "0.5", u.style.filter = "alpha(opacity=50)"), u.style.height = NetFunnel.PopupUtil.getDocumentEntireHeight(this._mDoc) + "px") : (u.style.backgroundColor = o ? "rgba(80,80,80,0)" : "rgba(80,80,80,0.3)", u.style.height = "100%"), (i = this._mDoc.getElementsByTagName("BODY")[0]).appendChild(u)), s.style.position = "absolute", s.style.visibility = "hidden", this._mCount++, this._mMask = l, this._mPopIFrame = u, this._mObj = s, this._mTop = t, this._mLeft = n, this.mid = "mpopup_" + this._mCount, this.addListener(this._mWin, "resize", NetFunnel.PopupUtil.resizePopup), NetFunnel.gPopup.push(this))
    }, NetFunnel.Popup.prototype = new NetFunnel.Event, NetFunnel.Popup.prototype._mCount = 0, NetFunnel.Popup.prototype._mid = "", NetFunnel.Popup.prototype._mWin = window, NetFunnel.Popup.prototype._mDoc = document, NetFunnel.Popup.prototype._mObj = null, NetFunnel.Popup.prototype._mMask = null, NetFunnel.Popup.prototype._mPopIFrame = null, NetFunnel.Popup.prototype._mIsShown = !1, NetFunnel.Popup.prototype._mIframeResize = NetFunnel.TS_IFRAME_RESIZE, NetFunnel.Popup.prototype._mProgress = null, NetFunnel.Popup.prototype._mZindex = NetFunnel.TS_POPUP_ZINDEX, NetFunnel.Popup.prototype._centerPopWin = function() {
        var e, t, n, i;
        !this._mIsShown || (i = this._mDoc.getElementsByTagName("BODY")[0]) && (n = 1 == NetFunnel.Util.isSmartPhone() ? window : "Explorer" == NetFunnel.BrowserDetect.browser ? this._mDoc : this._mWin, e = parseInt(NetFunnel.PopupUtil.getScrollTop(n), 10), t = parseInt(i.scrollLeft, 10), n = 0, n = 1 == NetFunnel.Util.isSmartPhone() ? NetFunnel.PopupUtil.getViewportHeight(window, this._mDoc) : NetFunnel.PopupUtil.getViewportHeight(this._mWin, this._mDoc), i = NetFunnel.PopupUtil.getViewportWidth(this._mWin, this._mDoc), "number" == typeof this._mTop ? this._mObj.style.top = this._mTop + "px" : this._mObj.style.top = e + (n - NetFunnel.PopupUtil.getObjHeight(this._mObj)) / 2 + "px", "number" == typeof this._mLeft ? this._mObj.style.left = this._mLeft + "px" : this._mObj.style.left = t + (i - NetFunnel.PopupUtil.getObjWidth(this._mObj)) / 2 + "px", this._mIframeResize && "object" == typeof this._mPopIFrame && (this._mPopIFrame.style.top = this._mObj.style.top, this._mPopIFrame.style.left = this._mObj.style.left, this._mPopIFrame.style.width = this._mObj.style.width, this._mPopIFrame.style.height = parseInt(this._mObj.style.height, 10) + 6))
    }, NetFunnel.Popup.prototype.getObj = function(e) {
        return this._mDoc.getElementById(e)
    }, NetFunnel.Popup.prototype.show = function() {
        var e = this._mDoc.getElementsByTagName("BODY")[0];
        e && (e.style.overflow = "auto", this._mObj.style.zIndex = this._mZindex + 2, this._mObj.style.visibility = "visible", this._mObj.style.display = "block", this._mMask.style.visiblity = "visible", this._mMask.style.display = "block", this._mPopIFrame.style.visiblity = "visible", this._mPopIFrame.style.display = "block", this._mIsShown = !0, this._centerPopWin())
    }, NetFunnel.Popup.prototype.hide = function() {
        var e = this._mDoc.getElementsByTagName("BODY")[0];
        e && (e.style.overflow = "auto", this._mObj.style.visibility = "hidden", this._mObj.style.display = "none", this._mMask.style.visiblity = "hidden", this._mMask.style.display = "none", this._mPopIFrame.style.visiblity = "hidden", this._mPopIFrame.style.display = "none", this._mIsShown = !1)
    }, NetFunnel.Popup.prototype.destroy = function() {
        var e = this._mDoc.getElementsByTagName("BODY")[0];
        if (e) {
            var t = NetFunnel.gPopup.length;
            try {
                var n = this._mDoc.getElementById("mpopup_bg");
                e.removeChild(n)
            } catch (e) {}
            try {
                var i = this._mDoc.getElementById("pop_iframe");
                e.removeChild(i)
            } catch (e) {}
            for (var o = 0; o < t; o++) {
                var r = NetFunnel.gPopup.pop();
                if (r.mid != this.mid) NetFunnel.gPopup.push(r);
                else try {
                    e.removeChild(r._mObj)
                } catch (e) {}
            }
            this._mProgress && this._mProgress.hide()
        }
    }, NetFunnel.RetVal = function(e) {
        this._mParam = {}, this._mRtype = parseInt(e.substr(0, 4), 10), this._mCode = parseInt(e.substr(5, 3), 10), this._mRetStr = e.substr(9, e.length - 9), this._parse()
    }, NetFunnel.RetVal.prototype._ltrim = function(e) {
        for (var t = 0; t < e.length && this._isWhitespace(e.charAt(t)); t++);
        return e.substring(t, e.length)
    }, NetFunnel.RetVal.prototype._rtrim = function(e) {
        for (var t = e.length - 1; 0 <= t && this._isWhitespace(e.charAt(t)); t--);
        return e.substring(0, t + 1)
    }, NetFunnel.RetVal.prototype._trim = function(e) {
        return this._ltrim(this._rtrim(e))
    }, NetFunnel.RetVal.prototype._isWhitespace = function(e) {
        return -1 != " \t\n\r\f".indexOf(e)
    }, NetFunnel.RetVal.prototype._parse = function() {
        for (var e, t, n, i = this._mRetStr.split("&"), o = 0; i.length > o; o++) 1 < (e = i[o].split("=")).length && (t = this._trim(e[0]), n = this._trim(e[1]), this._mParam[t] = n)
    }, NetFunnel.RetVal.prototype.getRetCode = function() {
        return this._mCode
    }, NetFunnel.RetVal.prototype.setRetCode = function(e) {
        return this._mCode = e, this._mCode
    }, NetFunnel.RetVal.prototype.getReqType = function() {
        return this._mRtype
    }, NetFunnel.RetVal.prototype.setReqType = function(e) {
        return this._mRtype = e, this._mRtype
    }, NetFunnel.RetVal.prototype.getRetStr = function() {
        return this._mRetStr
    }, NetFunnel.RetVal.prototype.getValue = function(e) {
        try {
            return this._mParam[e]
        } catch (e) {
            return null
        }
    }, NetFunnel.RetVal.prototype.setValue = function(e, t) {
        var n = null;
        try {
            return this.isKeyExist(e) && (n = this.getValue(e)), this._mParam[e] = t, n
        } catch (e) {
            return null
        }
    }, NetFunnel.RetVal.prototype.getNumber = function(e) {
        try {
            return parseInt(this._mParam[e], 10)
        } catch (e) {
            return 0
        }
    }, NetFunnel.RetVal.prototype.isKeyExist = function(e) {
        try {
            if (null !== this._mParam[e]) return !0
        } catch (e) {}
        return !1
    }, NetFunnel.RetVal.prototype.getParam = function() {
        return this._mParam
    }, NetFunnel.TsClient = function(e, t) {
        if (this.mConfig = {}, this.mConfig.host = NetFunnel.TS_HOST, this.mConfig.port = NetFunnel.TS_PORT, this.mConfig.proto = NetFunnel.TS_PROTO, this.mConfig.query = NetFunnel.TS_QUERY, this.mConfig.max_ttl = NetFunnel.TS_MAX_TTL, this.mConfig.conn_timeout = NetFunnel.TS_CONN_TIMEOUT, this.mConfig.conn_retry = NetFunnel.TS_CONN_RETRY, this.mConfig.cookie_id = NetFunnel.TS_COOKIE_ID, this.mConfig.cookie_time = NetFunnel.TS_COOKIE_TIME, this.mConfig.cookie_domain = NetFunnel.TS_COOKIE_DOMAIN, this.mConfig.showcnt_limit = NetFunnel.TS_SHOWCNT_LIMIT, this.mConfig.showtime_limit = NetFunnel.TS_SHOWTIME_LIMIT, this.mConfig.shownext_limit = NetFunnel.TS_SHOWNEXT_LIMIT, this.mConfig.popup_top = NetFunnel.TS_POPUP_TOP, this.mConfig.popup_left = NetFunnel.TS_POPUP_LEFT, this.mConfig.skin_id = NetFunnel.TS_SKIN_ID, this.mConfig.use_unfocus = NetFunnel.TS_USE_UNFOCUS, this.mConfig.virt_wait = NetFunnel.TS_VIRT_WAIT, this.mConfig.use_mobile_ui = NetFunnel.TS_USE_MOBILE_UI, this.mConfig.mp_use = NetFunnel.MP_USE, this.mConfig.use_frame_block = NetFunnel.TS_USE_FRAME_BLOCK, this.mConfig.frame_block_list = NetFunnel.TS_FRAME_BLOCK_LIST, this.mConfig.use_pre_wait = NetFunnel.TS_USE_PRE_WAIT, this.mConfig.popup_target = NetFunnel.TS_POPUP_TARGET, this.mConfig.user_data = !1, this.mConfig.user_data_keys = NetFunnel.TS_USER_DATA_KEYS, this.mConfig.block_msg = NetFunnel.TS_BLOCK_MSG, this.mConfig.block_url = NetFunnel.TS_BLOCK_URL, this.mConfig.ipblock_wait_count = NetFunnel.TS_IPBLOCK_WAIT_COUNT, this.mConfig.ipblock_wait_time = NetFunnel.TS_IPBLOCK_WAIT_TIME, this.mConfig.service_id = NetFunnel.TS_SERVICE_ID, this.mConfig.action_id = NetFunnel.TS_ACTION_ID, this.mConfig.show_wait_popup = NetFunnel.TS_SHOW_WAIT_POPUP, this.mConfig.config_use = NetFunnel.TS_CONFIG_USE, this.mConfig.popup_zindex = NetFunnel.TS_POPUP_ZINDEX, this.mConfig.ip_error_retry = NetFunnel.TS_IP_ERROR_RETRY, this.mConfig.success_popup_visibility = NetFunnel.TS_SUCCESS_POPUP_VISIBILITY, this.mConfig._host_changed = !1, this.mConfig._port_changed = !1, "object" == typeof e)
            for (var n in e) this.mConfig[n] = e[n], "host" == n && (this.mConfig._host_changed = !0), "port" == n && (this.mConfig._port_changed = !0);
        var i;
        NetFunnel.gPopupLeft = this.mConfig.popup_left, NetFunnel.gPopupTop = this.mConfig.popup_top, NetFunnel.gBlockList = this.mConfig.block_list, "" == this.mConfig.skin_id ? NetFunnel.gSkinId = NetFunnel.TS_SKIN_ID : (NetFunnel.gSkinId = this.mConfig.skin_id, 2 < (i = this.mConfig.skin_id.split("|")).length && NetFunnel.BrowserDetect.browser == i[1] && parseInt(NetFunnel.BrowserDetect.version, 10) < parseInt(i[2], 10) && (NetFunnel.gSkinId = "default")), "boolean" != typeof this.mConfig.use_unfocus && ("string" == typeof this.mConfig.use_unfocus && "true" == this.mConfig.use_unfocus ? this.mConfig.use_unfocus = !0 : this.mConfig.use_unfocus = !1), NetFunnel.gUseUnfocus = this.mConfig.use_unfocus, "boolean" != typeof this.mConfig.use_mobile_ui && ("string" == typeof this.mConfig.use_mobile_ui && "true" == this.mConfig.use_mobile_ui ? this.mConfig.use_mobile_ui = !0 : this.mConfig.use_mobile_ui = !1), NetFunnel.gUseMobileUI = this.mConfig.use_mobile_ui, "boolean" != typeof this.mConfig.use_frame_block && ("string" == typeof this.mConfig.use_frame_block && "true" == this.mConfig.use_frame_block ? this.mConfig.use_frame_block = !0 : this.mConfig.use_frame_block = !1), 1 == this.mConfig.use_frame_block ? this.mConfig.frame_block_list.length < 1 && (this.mConfig.frame_block_list = NetFunnel.Util.getFrameWindowList(this.mConfig.popup_target)) : this.mConfig.frame_block_list = [], this.id = 0, NetFunnel.TsClient._Objects[this.id] = this, NetFunnel.TsClient._Count += 1, t.onSuccess && this.addListener(this, "onSuccess", t.onSuccess), t.onContinued && this.addListener(this, "onContinued", t.onContinued), t.onBypass && this.addListener(this, "onBypass", t.onBypass), t.onBlock && this.addListener(this, "onBlock", t.onBlock), t.onIpBlock && this.addListener(this, "onIpBlock", t.onIpBlock), t.onError && this.addListener(this, "onError", t.onError), t.onStop && this.addListener(this, "onStop", t.onStop), t.onExpressnumber && this.addListener(this, "onExpressnumber", t.onExpressnumber), this.counter[NetFunnel.RTYPE_NONE] = 0, this.counter[NetFunnel.RTYPE_GET_TID_CHK_ENTER] = 0, this.counter[NetFunnel.RTYPE_GET_TID] = 0, this.counter[NetFunnel.RTYPE_CHK_ENTER] = 0, this.counter[NetFunnel.RTYPE_ALIVE_NOTICE] = 0, this.counter[NetFunnel.RTYPE_SET_COMPLETE] = 0, this.counter[NetFunnel.RTYPE_INIT] = 0, this.counter[NetFunnel.RTYPE_STOP] = 0, this.connTimeout = function e() {
            if (this != NetFunnel.gControl) return e.apply(NetFunnel.gControl, arguments);
            if (0 != NetFunnel.gAlreadyProc) return !1;
            if (this._resetScript(), this.counter[this._mReqType] < this.mConfig.conn_retry) switch (this._mStatus = NetFunnel.PS_TIMEOUT, this.counter[this._mReqType] += 1, this._mReqType) {
                case NetFunnel.RTYPE_GET_TID:
                    return this.getTicketID(this.user_id, this.user_tid, !1), !0;
                case NetFunnel.RTYPE_CHK_ENTER:
                    return this.chkEnter(this.key, !1), !0;
                case NetFunnel.RTYPE_GET_TID_CHK_ENTER:
                    return this.getTidChkEnter(this.user_id, this.user_tid, !1), !0;
                case NetFunnel.RTYPE_ALIVE_NOTICE:
                    return this.aliveNotice(this.key, "", "", !1), !0;
                case NetFunnel.RTYPE_SET_COMPLETE:
                    return this.setComplete(this.key, "", "", !1), !0
            }
            return NetFunnel.PopupUtil.hideWaitPopup(), this._mReqType != NetFunnel.RTYPE_CHK_ENTER && this._mReqType != NetFunnel.RTYPE_GET_TID_CHK_ENTER || new NetFunnel.Storage(2).setItem(this.mConfig.cookie_id, "5002:200:key=" + NetFunnel.CONN_TIMEOUT_KEY, 1, this.mConfig.cookie_domain), !(1 <= NetFunnel.gAlreadyProc) && (this.fireEvent(null, this, "onError", {
                rtype: this._mReqType,
                code: NetFunnel.kErrorSockConnect,
                data: {
                    msg: "Connection Timeout"
                },
                next: this.next.error
            }), this._mStatus = NetFunnel.PS_ERROR, !0)
        }
    }, NetFunnel.TsClient._Count = 0, NetFunnel.TsClient._Objects = {}, NetFunnel.TsClient.prototype = new NetFunnel.Event, NetFunnel.TsClient.prototype._initDone = !1, NetFunnel.TsClient.prototype.id = null, NetFunnel.TsClient.prototype.mConfig = null, NetFunnel.TsClient.prototype.key = null, NetFunnel.TsClient.prototype.script = null, NetFunnel.TsClient.prototype.alarm = null, NetFunnel.TsClient.prototype._mReqType = NetFunnel.RTYPE_NONE, NetFunnel.TsClient.prototype._mMousePos = 0, NetFunnel.TsClient.prototype._mNoActTime = 0, NetFunnel.TsClient.prototype._mStatus = NetFunnel.PS_N_RUNNING, NetFunnel.TsClient.prototype.counter = {}, NetFunnel.TsClient.prototype.next = {
        success: "",
        error: ""
    }, NetFunnel.TsClient.prototype.init = function() {
        this._nCount++, this._initDone = !0
    }, NetFunnel.TsClient.prototype.getConfig = function(e) {
        return this.mConfig[e]
    }, NetFunnel.TsClient.prototype._isNoAction = function() {
        return this._mMousePos == NetFunnel.MouseX || (this._mMousePos = NetFunnel.MouseX, !1)
    }, NetFunnel.TsClient.prototype._resetAlarm = function() {
        null !== this.alarm && clearTimeout(this.alarm), this.alarm = null
    }, NetFunnel.TsClient.prototype._resetReTimer = function() {
        null !== NetFunnel.gReTimer && clearTimeout(NetFunnel.gReTimer), NetFunnel.gReTimer = null
    }, NetFunnel.TsClient.prototype._resetRetryTimer = function() {
        null !== this.retryTimer && clearTimeout(this.retryTimer), this.retryTimer = null
    }, NetFunnel.TsClient.prototype._resetScript = function() {
        try {
            var e;
            !this.script || "object" != typeof this.script || (e = this.script.parentNode) && "object" == typeof e && e.removeChild(this.script)
        } catch (e) {}
        this.script = null
    }, NetFunnel.TsClient.prototype.getUserdata = function() {
        try {
            var userdata = "",
                uKey = this.mConfig.user_data_keys;
            if ("string" == typeof this.mConfig.user_data) return this.mConfig.user_data;
            if ("Array" != Object.prototype.toString.call(uKey).slice(8, -1)) return !1;
            for (var i = 0; i < uKey.length; i++) {
                var keydata = uKey[i];
                if ("object" == typeof keydata) {
                    if ("v" == keydata.type) try {
                        if (userdata = eval(keydata.key), "" != userdata) break
                    } catch (e) {}
                    if ("c" == keydata.type && (userdata = NetFunnel.Cookie.get(keydata.key), "" != userdata)) break
                }
            }
            return userdata
        } catch (e) {
            return !1
        }
    }, NetFunnel.TsClient.prototype._showResult = function() {
        if (this._resetAlarm(), 1 != NetFunnel.gAlreadyProc || NetFunnel.gRtype != NetFunnel.RTYPE_GET_TID_CHK_ENTER) switch (NetFunnel.gAlreadyProc = 1, NetFunnel.PopupUtil.hideWaitPopup(), this.retval = new NetFunnel.RetVal(this.result), this.retval.getReqType() == NetFunnel.RTYPE_GET_TID_CHK_ENTER && this.retval.setReqType(NetFunnel.RTYPE_CHK_ENTER), NetFunnel.TS_DEBUG_MODE && NetFunnel.printDebugMsg("recv", this.result), NetFunnel.ttl = 0, this.counter[this._mReqType] = 0, this.retval.getRetCode() == NetFunnel.kContinueDebug ? NetFunnel.gDebugflag = !0 : NetFunnel.gDebugflag = !1, this.retval.getReqType()) {
            case NetFunnel.RTYPE_GET_TID:
                this._showResultGetTicketID(this.retval);
                break;
            case NetFunnel.RTYPE_CHK_ENTER:
                this._showResultChkEnter(this.retval);
                break;
            case NetFunnel.RTYPE_ALIVE_NOTICE:
                this._showResultAliveNotice(this.retval);
                break;
            case NetFunnel.RTYPE_SET_COMPLETE:
                this._showResultSetComplete(this.retval);
                break;
            default:
                new NetFunnel.Storage(2).removeItem(this.mConfig.cookie_id), this.fireEvent(null, this, "onError", {
                    rtype: NetFunnel.RTYPE_NONE,
                    code: this.retval.getRetCode(),
                    data: this.retval.getParam(),
                    next: this.next.error
                }), this._mStatus = NetFunnel.PS_ERROR
        }
    }, NetFunnel.TsClient.prototype._showResultGetTicketID = function(e) {
        var t = new NetFunnel.Storage(2);
        e.getRetCode() === NetFunnel.kSuccess ? (t.setItem(this.mConfig.cookie_id, this.result, this.mConfig.cookie_time, this.mConfig.cookie_domain), this._mStatus = NetFunnel.PS_N_RUNNING, this.fireEvent(null, this, "onSuccess", {
            rtype: e.getReqType(),
            code: e.getRetCode(),
            data: e.getParam(),
            next: this.next.success
        })) : (t.removeItem(this.mConfig.cookie_id), this._mStatus = NetFunnel.PS_ERROR, this.fireEvent(null, this, "onError", {
            rtype: e.getReqType(),
            code: e.getRetCode(),
            data: e.getParam(),
            next: this.next.error
        }))
    }, NetFunnel.TsClient.prototype._showResultChkEnter = function(e) {
        var t = this,
            n = new NetFunnel.Storage(2);
        switch (e.getRetCode()) {
            case NetFunnel.kSuccess:
                n.setItem(this.mConfig.cookie_id, this.result, this.mConfig.cookie_time, this.mConfig.cookie_domain), this._mStatus = NetFunnel.PS_N_RUNNING, NetFunnel.gNWaitTemp = 0, NetFunnel.gNWaitCount = 0, this.fireEvent(null, this, "onSuccess", {
                    rtype: e.getReqType(),
                    code: e.getRetCode(),
                    data: e.getParam(),
                    next: this.next.success
                });
                break;
            case NetFunnel.kContinueDebug:
            case NetFunnel.kContinue:
                this._mStatus = NetFunnel.PS_CONTINUE;
                var i = e.getNumber("ttl");
                i > this.mConfig.max_ttl && (i = this.mConfig.max_ttl, e.setValue("ttl", i));
                var o = e.getNumber("nwait");
                if (NetFunnel.TS_NWAIT_BYPASS && (NetFunnel.gNWaitTemp == o ? NetFunnel.gNWaitCount += 1 : (NetFunnel.gNWaitTemp = o, NetFunnel.gNWaitCount = 0), NetFunnel.TS_MAX_NWAIT_COUNT <= NetFunnel.gNWaitCount)) {
                    this.fireEvent(null, this, "onSuccess", {
                        rtype: e.getReqType(),
                        code: e.getRetCode(),
                        data: e.getParam(),
                        next: this.next.success
                    });
                    break
                }
                this.fireEvent(null, this, "onContinued", {
                    rtype: e.getReqType(),
                    code: e.getRetCode(),
                    data: e.getParam(),
                    next: this.next.continued
                }), (NetFunnel.gAlreadyProc = 0) < i && this._mStatus != NetFunnel.PS_N_RUNNING && (this.retryTimer && clearTimeout(this.retryTimer), NetFunnel.ttl = i, this.retryTimer = setTimeout(function() {
                    t.chkEnterCont(t.retval.getValue("key"))
                }, 1e3 * i));
                break;
            case NetFunnel.kTsBlock:
                this._mStatus = NetFunnel.PS_N_RUNNING, n.setItem(this.mConfig.cookie_id, this.result, this.mConfig.cookie_time, this.mConfig.cookie_domain), this.fireEvent(null, this, "onBlock", {
                    rtype: e.getReqType(),
                    code: e.getRetCode(),
                    data: e.getParam(),
                    next: this.next.block
                }), this.retryTimer && clearTimeout(this.retryTimer), NetFunnel.ttl = i;
                break;
            case NetFunnel.kTsIpBlock:
                if (this._mStatus = NetFunnel.PS_N_RUNNING, this.retryTimer && clearTimeout(this.retryTimer), NetFunnel.gAlreadyProc = 0, !(this.mConfig.ipblock_wait_count >= NetFunnel.gIPBlockWaitCount + 1)) {
                    n.setItem(this.mConfig.cookie_id, this.result, this.mConfig.cookie_time, this.mConfig.cookie_domain), this._mStatus = NetFunnel.PS_N_RUNNING, this.fireEvent(null, this, "onSuccess", {
                        rtype: e.getReqType(),
                        code: e.getRetCode(),
                        data: e.getParam(),
                        next: this.next.success
                    });
                    break
                }
                NetFunnel.gReTimer = setTimeout(function() {
                    t.getTidChkEnter(t.user_id, t.user_tid, !0)
                }, this.mConfig.ipblock_wait_time), this.fireEvent(null, this, "onIpBlock", {
                    rtype: e.getReqType(),
                    code: e.getRetCode(),
                    data: e.getParam(),
                    next: this.next.ipblock
                }), 0 != this.mConfig.ipblock_wait_count && (NetFunnel.gIPBlockWaitCount += 1);
                break;
            case NetFunnel.kTsBypass:
                this._mStatus = NetFunnel.PS_N_RUNNING, n.setItem(this.mConfig.cookie_id, this.result, this.mConfig.cookie_time, this.mConfig.cookie_domain), this.fireEvent(null, this, "onBypass", {
                    rtype: e.getReqType(),
                    code: e.getRetCode(),
                    data: e.getParam(),
                    next: this.next.bypass
                });
                break;
            case NetFunnel.kTsExpressNumber:
                this._mStatus = NetFunnel.PS_N_RUNNING, n.setItem(this.mConfig.cookie_id, this.result, this.mConfig.cookie_time, this.mConfig.cookie_domain), this.fireEvent(null, this, "onExpressnumber", {
                    rtype: e.getReqType(),
                    code: e.getRetCode(),
                    data: e.getParam(),
                    next: this.next.expressnumber
                });
                break;
            default:
                n.removeItem(this.mConfig.cookie_id), this._mStatus = NetFunnel.PS_ERROR, e.getRetCode() == NetFunnel.kTsErrorInvalidIp && 1 == this.mConfig.ip_error_retry ? (NetFunnel.gAlreadyProc = 0, this._mStatus = NetFunnel.PS_N_RUNNING, NetFunnel.gReTimer = setTimeout(function() {
                    t.getTidChkEnter(t.user_id, t.user_tid, !0, NetFunnel.gTotWait)
                }, 100)) : (this._mStatus = NetFunnel.PS_ERROR, this.fireEvent(null, this, "onError", {
                    rtype: e.getReqType(),
                    code: e.getRetCode(),
                    data: e.getParam(),
                    next: this.next.error
                }))
        }
    }, NetFunnel.TsClient.prototype._showResultAliveNotice = function(e) {
        var t = new NetFunnel.Storage(2);
        switch (e.getRetCode()) {
            case NetFunnel.kSuccess:
                this._mStatus = NetFunnel.PS_N_RUNNING, this.fireEvent(null, this, "onSuccess", {
                    rtype: e.getReqType(),
                    code: e.getRetCode(),
                    data: e.getParam(),
                    next: this.next.success
                });
                break;
            case NetFunnel.kContinueDebug:
            case NetFunnel.kContinue:
                if (this._mStatus = NetFunnel.PS_CONTINUE, this._mNoActTime > this.mConfig.no_action) {
                    this.fireEvent(null, this, "onError", {
                        rtype: e.getReqType(),
                        code: NetFunnel.kTsErrorNoUserAction,
                        data: e.getParam(),
                        next: this.next.error
                    }), this._mNoActTime = 0, this._mStatus = NetFunnel.PS_ERROR;
                    break
                }
                var n, i = e.getNumber("ttl");
                i > this.mConfig.max_ttl && (i = this.mConfig.max_ttl, e.setValue("ttl", i)), this.fireEvent(null, this, "onContinued", {
                    rtype: e.getReqType(),
                    code: e.getRetCode(),
                    data: e.getParam(),
                    next: this.next.continued
                }), (NetFunnel.gAlreadyProc = 0) < i && this._mStatus != NetFunnel.PS_N_RUNNING && (this.retryTimer && clearTimeout(this.retryTimer), this._isNoAction() ? this._mNoActTime += i : this._mNoActTime = 0, t.setItem(this.mConfig.cookie_id, this.result, this.mConfig.cookie_time, this.mConfig.cookie_domain), (n = this).retryTimer = setTimeout(function() {
                    t.getItem(n.mConfig.cookie_id) && n.aliveNoticeCont(n.retval.getValue("key"), n.retval.getValue("ip"), n.retval.getValue("port"), n.retval.getValue("first"))
                }, 1e3 * i));
                break;
            case NetFunnel.kTsBlock:
                this._mStatus = NetFunnel.PS_N_RUNNING, this.fireEvent(null, this, "onBlock", {
                    rtype: e.getReqType(),
                    code: e.getRetCode(),
                    data: e.getParam(),
                    next: this.next.block
                });
                break;
            case NetFunnel.kTsExpressNumber:
                this._mStatus = NetFunnel.PS_N_RUNNING, this.fireEvent(null, this, "onExpressnumber", {
                    rtype: e.getReqType(),
                    code: e.getRetCode(),
                    data: e.getParam(),
                    next: this.next.expressnumber
                });
                break;
            case NetFunnel.kTsBypass:
                this._mStatus = NetFunnel.PS_N_RUNNING, this.fireEvent(null, this, "onBypass", {
                    rtype: e.getReqType(),
                    code: e.getRetCode(),
                    data: e.getParam(),
                    next: this.next.bypass
                });
                break;
            case NetFunnel.kTsIpBlock:
                this._mStatus = NetFunnel.PS_N_RUNNING, this.fireEvent(null, this, "onIpBlock", {
                    rtype: e.getReqType(),
                    code: e.getRetCode(),
                    data: e.getParam(),
                    next: this.next.ipblock
                });
                break;
            default:
                e.getRetCode() == NetFunnel.kErrorExpiredTime && t.removeItem(this.mConfig.cookie_id), this._mStatus = NetFunnel.PS_ERROR, this.fireEvent(null, this, "onError", {
                    rtype: e.getReqType(),
                    code: e.getRetCode(),
                    data: e.getParam(),
                    next: this.next.error
                })
        }
    }, NetFunnel.TsClient.prototype._showResultSetComplete = function(e) {
        new NetFunnel.Storage(2).removeItem(this.mConfig.cookie_id), e.getRetCode() === NetFunnel.kSuccess ? (this._mStatus = NetFunnel.PS_N_RUNNING, this.fireEvent(null, this, "onSuccess", {
            rtype: e.getReqType(),
            code: e.getRetCode(),
            data: e.getParam(),
            next: this.next.success
        })) : (this._mStatus = NetFunnel.PS_ERROR, this.fireEvent(null, this, "onError", {
            rtype: e.getReqType(),
            code: e.getRetCode(),
            data: e.getParam(),
            next: this.next.error
        }))
    }, NetFunnel.TsClient.prototype._connInit = function(e) {
        this.result = null, this._mReqType = e, this._resetAlarm(), this._resetScript(), this._resetRetryTimer();
        var t = this;
        return this.alarm = setTimeout(function() {
            t.connTimeout(0)
        }, 1e3 * parseInt(this.mConfig.conn_timeout, 10)), !(!this.mConfig.host || "" == this.mConfig.host) && (!(!this.mConfig.port || "" == this.mConfig.port) && (!(!this.mConfig.query || "" == this.mConfig.query) && (!(!this.mConfig.service_id || "" == this.mConfig.service_id) && (!(!this.mConfig.action_id || "" == this.mConfig.action_id) && (this._mStatus = NetFunnel.PS_RUNNING, !0)))))
    }, NetFunnel.TsClient.prototype._sendRequest = function(e) {
        this.script = document.createElement("script"), this.script.src = e;
        var t = document.getElementsByTagName("head").item(0);
        return NetFunnel.TS_DEBUG_MODE && NetFunnel.printDebugMsg("send", e), t.appendChild(this.script), !0
    }, NetFunnel.TsClient.prototype._sendError = function(e, t) {
        var n = "";
        switch (t) {
            case NetFunnel.kErrorAlready:
                n = "Already running";
                break;
            case NetFunnel.kErrorNoinit:
                n = "Uninitialized object";
                break;
            case NetFunnel.kErrorSystem:
            default:
                n = "System error"
        }
        this.fireEvent(null, this, "onError", {
            rtype: e,
            code: t,
            data: {
                msg: n
            },
            next: this.next.error
        })
    }, NetFunnel.TsClient.prototype.setNext = function(e) {
        if ("object" == typeof e) this.next = e;
        else try {
            this.next.success = void 0, this.next.continued = void 0, this.next.bypass = void 0, this.next.expressnumber = void 0, this.next.block = void 0, this.next.ipblock = void 0, this.next.error = void 0, this.next.stop = void 0
        } catch (e) {
            this.next.success = window.undefined, this.next.continued = window.undefined, this.next.bypass = window.undefined, this.next.expressnumber = window.undefined, this.next.block = window.undefined, this.next.ipblock = window.undefined, this.next.error = window.undefined, this.next.stop = window.undefined
        }
    }, NetFunnel.TsClient.prototype.sendStop = function(e) {
        return 1 == NetFunnel.TS_BYPASS ? this.fireEvent(null, this, "onSuccess", {
            rtype: this._mReqType,
            code: NetFunnel.kSuccess,
            data: {},
            next: this.next.success
        }) : ((e = "undefined" == e ? !0 : e) && (this.counter[NetFunnel.RTYPE_STOP] = 0), this._resetReTimer(), this._resetAlarm(), this._resetRetryTimer(), this._resetScript(), this.fireEvent(null, this, "onSuccess", {
            rtype: this._mReqType,
            code: NetFunnel.kSuccess,
            data: {},
            next: this.next.success
        }), this._mStatus = NetFunnel.PS_N_RUNNING), !0
    }, NetFunnel.TsClient.prototype.getTicketID = function(e, t, n) {
        if (NetFunnel.gPrevWaitTime = -1, 1 == NetFunnel.TS_BYPASS) return this.fireEvent(null, this, "onSuccess", {
            rtype: this._mReqType,
            code: NetFunnel.kSuccess,
            data: {},
            next: this.next.success
        }), !0;
        1 == this.mConfig.use_unfocus && NetFunnel.Util.delFocus(this.getConfig("popup_target")), NetFunnel.gTotWait = -1, NetFunnel.retryData = null;
        var i = 1 == this.mConfig.mp_use ? NetFunnel.MProtect() : 0;
        if (0 != i) {
            this.fireEvent(null, this, "onContinued", {
                rtype: NetFunnel.RTYPE_CHK_ENTER,
                code: NetFunnel.kContinue,
                data: {
                    tps: 1,
                    eps: 2,
                    nwait: 2 * NetFunnel.gControl.getConfig("showcnt_limit"),
                    mprotect: i,
                    ttl: 10
                }
            }), this.retryTimer && clearTimeout(this.retryTimer), NetFunnel.retryData = {
                user_id: e,
                user_tid: t,
                first: n
            };
            var o = this;
            return this.retryTimer = setTimeout(function() {
                o.retryFunction(NetFunnel.RTYPE_GET_TID)
            }, this.mConfig.virt_wait), !1
        }
        if ((n = "undefined" == n ? !0 : n) && (this.counter[NetFunnel.RTYPE_GET_TID] = 0), this._mStatus == NetFunnel.PS_RUNNING) return this._sendError(NetFunnel.RTYPE_GET_TID, NetFunnel.kErrorAlready), !1;
        this.user_id = e, this.user_tid = t;
        e = this.mConfig.proto + "://" + this.mConfig.host + ":" + this.mConfig.port + "/" + this.mConfig.query + "?opcode=" + NetFunnel.RTYPE_GET_TID + "&nfid=" + this.id + "&prefix=NetFunnel.gRtype=" + NetFunnel.RTYPE_GET_TID + ";";
        e += "&sid=" + this.mConfig.service_id + "&aid=" + this.mConfig.action_id;
        t = this.getUserdata();
        return "" != t && (e += "&user_data=" + t), e += "&js=yes", e += "&" + (new Date).getTime(), this._connInit(NetFunnel.RTYPE_GET_TID) ? (this._sendRequest(e), !0) : (this._sendError(NetFunnel.RTYPE_GET_TID, NetFunnel.kErrorNoinit), !1)
    }, NetFunnel.TsClient.prototype.chkEnter = function(e, t) {
        return 1 == NetFunnel.TS_BYPASS ? (this.fireEvent(null, this, "onSuccess", {
            rtype: this._mReqType,
            code: NetFunnel.kSuccess,
            data: {},
            next: this.next.success
        }), !0) : this._mStatus == NetFunnel.PS_RUNNING || this._mStatus == NetFunnel.PS_CONTINUE ? (this._sendError(NetFunnel.RTYPE_CHK_ENTER, NetFunnel.kErrorAlready), !1) : this.chkEnterProc(e, t)
    }, NetFunnel.TsClient.prototype.chkEnterCont = function(e, t) {
        return this._mStatus == NetFunnel.PS_RUNNING ? (this._sendError(NetFunnel.RTYPE_CHK_ENTER, NetFunnel.kErrorAlready), !1) : this.chkEnterProc(e, t)
    }, NetFunnel.TsClient.prototype.chkEnterProc = function(e, t) {
        if ((t = "undefined" == t ? !0 : t) && (this.counter[NetFunnel.RTYPE_CHK_ENTER] = 0), !e || "" == e) {
            if (!this.key) return this._sendError(NetFunnel.RTYPE_CHK_ENTER, NetFunnel.kErrorParam), !1;
            e = this.key
        }
        this.key = e;
        var n = this.retval.getValue("ip");
        1 < this.mConfig.conn_retry && this.counter[this._mReqType] == this.mConfig.conn_retry && (n = this.mConfig.config_use);
        var i = this.retval.getValue("port"),
            t = "";
        t = (t = n && "" != n && i && "" != i && !this.mConfig.config_use ? this.mConfig.proto + "://" + n + ":" + i + "/" : this.mConfig.proto + "://" + this.mConfig.host + ":" + this.mConfig.port + "/") + this.mConfig.query + "?opcode=" + NetFunnel.RTYPE_CHK_ENTER + "&key=" + e + "&nfid=" + this.id + "&prefix=NetFunnel.gRtype=" + NetFunnel.RTYPE_CHK_ENTER + ";", 0 < NetFunnel.ttl && (t = t + "&ttl=" + NetFunnel.ttl), t += "&sid=" + this.mConfig.service_id + "&aid=" + this.mConfig.action_id;
        e = this.getUserdata();
        return "" != e && (t += "&user_data=" + e), t += "&js=yes", t += "&" + (new Date).getTime(), this._connInit(NetFunnel.RTYPE_CHK_ENTER) ? (this._sendRequest(t), !0) : (this._sendError(NetFunnel.RTYPE_CHK_ENTER, NetFunnel.kErrorNoinit), !1)
    }, NetFunnel.retryData = null, NetFunnel.retryFunction = function(e) {
        var t;
        "object" == typeof NetFunnel.retryData && (t = NetFunnel.retryData, e == NetFunnel.RTYPE_GET_TID ? NetFunnel.gControl.getTicketID(t.user_id, t.user_tid, t.first) : e == NetFunnel.RTYPE_GET_TID_CHK_ENTER && NetFunnel.gControl.getTidChkEnter(t.user_id, t.user_tid, t.first))
    }, NetFunnel.TsClient.prototype.getTidChkEnter = function(e, t, n, i) {
        if (NetFunnel.gPrevWaitTime = -1, 1 == NetFunnel.TS_BYPASS) return this.fireEvent(null, this, "onSuccess", {
            rtype: this._mReqType,
            code: NetFunnel.kSuccess,
            data: {},
            next: this.next.success
        }), !0;
        if (this._mStatus == NetFunnel.PS_RUNNING || this._mStatus == NetFunnel.PS_CONTINUE) return this._sendError(NetFunnel.RTYPE_CHK_ENTER, NetFunnel.kErrorAlready), !1;
        1 == this.mConfig.use_unfocus && NetFunnel.Util.delFocus(this.getConfig("popup_target")), NetFunnel.gTotWait = null == i || isNaN(i) ? -1 : i, NetFunnel.retryData = null;
        i = 1 == this.mConfig.mp_use ? NetFunnel.MProtect() : 0;
        return 0 == i && 0 == this.mConfig.show_wait_popup ? (this.getConfig("use_pre_wait") && NetFunnel.PopupUtil.showWaitPopup(), this.getTidChkEnterProc(e, t, n)) : (this.fireEvent(null, this, "onContinued", {
            rtype: NetFunnel.RTYPE_CHK_ENTER,
            code: NetFunnel.kContinue,
            data: {
                tps: 1,
                eps: 2,
                nwait: 2 * NetFunnel.gControl.getConfig("showcnt_limit"),
                mprotect: i,
                ttl: 10
            }
        }), this.retryTimer && clearTimeout(this.retryTimer), NetFunnel.retryData = {
            user_id: e,
            user_tid: t,
            first: n
        }, this.retryTimer = setTimeout(function() {
            NetFunnel.retryFunction(NetFunnel.RTYPE_GET_TID_CHK_ENTER)
        }, this.mConfig.virt_wait), !1)
    }, NetFunnel.TsClient.prototype.getTidChkEnterProc = function(e, t, n) {
        (n = "undefined" == n ? !0 : n) && (this.counter[NetFunnel.RTYPE_GET_TID_CHK_ENTER] = 0), this.user_id = e, this.user_tid = t;
        e = this.mConfig.proto + "://" + this.mConfig.host + ":" + this.mConfig.port + "/" + this.mConfig.query + "?opcode=" + NetFunnel.RTYPE_GET_TID_CHK_ENTER + "&nfid=" + this.id + "&prefix=NetFunnel.gRtype=" + NetFunnel.RTYPE_GET_TID_CHK_ENTER + ";";
        0 < NetFunnel.ttl && (e = e + "&ttl=" + NetFunnel.ttl), e += "&sid=" + this.mConfig.service_id + "&aid=" + this.mConfig.action_id, e += "&js=yes";
        t = this.getUserdata();
        return "" != t && (e += "&user_data=" + t), e += "&" + (new Date).getTime(), this._connInit(NetFunnel.RTYPE_GET_TID_CHK_ENTER) ? (this._sendRequest(e), !0) : (this._sendError(NetFunnel.RTYPE_GET_TID_CHK_ENTER, NetFunnel.kErrorNoinit), !1)
    }, NetFunnel.TsClient.prototype.aliveNoticeProc = function(e, t, n, i) {
        if ((i = "undefined" == i ? !0 : i) && (this.counter[NetFunnel.RTYPE_ALIVE_NOTICE] = 0), !e || "" == e) {
            if (!this.key) return this._sendError(NetFunnel.RTYPE_ALIVE_NOTICE, NetFunnel.kErrorParam), !1;
            e = this.key
        }
        this.key = e, this.ip = t, this.port = n;
        i = "";
        i = (i = (t = 1 < this.mConfig.conn_retry && this.counter[this._mReqType] == this.mConfig.conn_retry ? this.mConfig.config_use : t) && "" != t && n && "" != n && !this.mConfig.config_use ? (this.ip = 0 == this.mConfig._host_changed ? t : this.mConfig.host, this.port = 0 == this.mConfig._port_changed ? n : this.mConfig.port, this.mConfig.proto + "://" + this.ip + ":" + this.port + "/") : this.mConfig.proto + "://" + this.mConfig.host + ":" + this.mConfig.port + "/") + this.mConfig.query + "?opcode=" + NetFunnel.RTYPE_ALIVE_NOTICE + "&key=" + e + "&nfid=" + this.id + "&prefix=NetFunnel.gRtype=" + NetFunnel.RTYPE_ALIVE_NOTICE + ";", i += "&sid=" + this.mConfig.service_id + "&aid=" + this.mConfig.action_id;
        e = this.getUserdata();
        return "" != e && (i += "&user_data=" + e), i += "&js=yes", i += "&" + (new Date).getTime(), this._connInit(NetFunnel.RTYPE_ALIVE_NOTICE) ? (this._sendRequest(i), !0) : (this._sendError(NetFunnel.RTYPE_ALIVE_NOTICE, NetFunnel.kErrorNoinit), !1)
    }, NetFunnel.TsClient.prototype.aliveNotice = function(e, t, n, i) {
        return 1 == NetFunnel.TS_BYPASS ? (this.fireEvent(null, this, "onSuccess", {
            rtype: this._mReqType,
            code: NetFunnel.kSuccess,
            data: {},
            next: this.next.success
        }), !0) : this._mStatus == NetFunnel.PS_RUNNING || this._mStatus == NetFunnel.PS_CONTINUE ? (this._sendError(NetFunnel.RTYPE_ALIVE_NOTICE, NetFunnel.kErrorAlready), !1) : this.aliveNoticeProc(e, t, n, i)
    }, NetFunnel.TsClient.prototype.aliveNoticeCont = function(e, t, n, i) {
        return this._mStatus == NetFunnel.PS_RUNNING ? (this._sendError(NetFunnel.RTYPE_ALIVE_NOTICE, NetFunnel.kErrorAlready), !1) : this.aliveNoticeProc(e, t, n, i)
    }, NetFunnel.TsClient.prototype.setComplete = function(e, t, n, i) {
        if ((new NetFunnel.Storage).setItemStorageOnly("NFMPT.reqCnt", "0"), 1 == NetFunnel.TS_BYPASS) return this.fireEvent(null, this, "onSuccess", {
            rtype: this._mReqType,
            code: NetFunnel.kSuccess,
            data: {},
            next: this.next.success
        }), !0;
        if ((i = "undefined" == i ? !0 : i) && (this.counter[NetFunnel.RTYPE_SET_COMPLETE] = 0), this._mStatus == NetFunnel.PS_RUNNING) return this._sendError(NetFunnel.RTYPE_SET_COMPLETE, NetFunnel.kErrorAlready), !1;
        if (!e || "" == e) {
            if (!this.key) return this._sendError(NetFunnel.RTYPE_SET_COMPLETE, NetFunnel.kErrorParam), !1;
            e = this.key
        }
        if (this.key = e, this.ip = t, 1 < this.mConfig.conn_retry && this.counter[this._mReqType] == this.mConfig.conn_retry - 1 && (t = this.mConfig.config_use), this.port = n, e == NetFunnel.CONN_TIMEOUT_KEY) {
            var o = new NetFunnel.RetVal(NetFunnel.RTYPE_SET_COMPLETE + ":" + NetFunnel.kSuccess + ":utime=1");
            return this._showResultSetComplete(o), !0
        }
        o = "";
        o = (o = t && "" != t && n && "" != n && !this.mConfig.config_use ? (this.ip = 0 == this.mConfig._host_changed ? t : this.mConfig.host, this.port = 0 == this.mConfig._port_changed ? n : this.mConfig.port, this.mConfig.proto + "://" + this.ip + ":" + this.port + "/") : this.mConfig.proto + "://" + this.mConfig.host + ":" + this.mConfig.port + "/") + this.mConfig.query + "?opcode=" + NetFunnel.RTYPE_SET_COMPLETE + "&key=" + e + "&nfid=" + this.id + "&prefix=NetFunnel.gRtype=" + NetFunnel.RTYPE_SET_COMPLETE + ";";
        e = this.getUserdata();
        return "" != e && (o += "&user_data=" + e), o += "&js=yes", o += "&" + (new Date).getTime(), this._connInit(NetFunnel.RTYPE_SET_COMPLETE) ? (this._sendRequest(o), !0) : (this._sendError(NetFunnel.RTYPE_SET_COMPLETE, NetFunnel.kErrorNoinit), !1)
    }, NetFunnel.TsClient.prototype.cookieExist = function() {
        var e = new NetFunnel.Storage(2),
            t = e.getItem(this.mConfig.cookie_id);
        return 0 != t && "" != t && (!!new NetFunnel.RetVal(t).getValue("key") || (e.removeItem(this.mConfig.cookie_id), !1))
    }, NetFunnel.TsClient.prototype.isRunning = function() {
        return this._mStatus == NetFunnel.PS_RUNNING || this._mStatus == NetFunnel.PS_CONTINUE
    }, 1 == NetFunnel.TS_AUTO_COMPLETE && NetFunnel_Complete(), "function" == typeof window.Function.bind ? (top.NetFunnel = NetFunnel.NetFunnel = NetFunnel, top.NetFunnel_init = NetFunnel.NetFunnel_init = NetFunnel_init.bind(this), top.NetFunnel_sendStop = NetFunnel.NetFunnel_sendStop = NetFunnel_sendStop.bind(this), top.NetFunnel_getTicketID = NetFunnel.NetFunnel_getTicketID = NetFunnel_getTicketID.bind(this), top.NetFunnel_chkEnter = NetFunnel.NetFunnel_chkEnter = NetFunnel_chkEnter.bind(this), top.NetFunnel_getTidChkEnter = NetFunnel.NetFunnel_getTidChkEnter = NetFunnel_getTidChkEnter.bind(this), top.NetFunnel_aliveNotice = NetFunnel.NetFunnel_aliveNotice = NetFunnel_aliveNotice.bind(this), top.NetFunnel_setComplete = NetFunnel.NetFunnel_setComplete = NetFunnel_setComplete.bind(this), top.NetFunnel_cookieExist = NetFunnel.NetFunnel_cookieExist = NetFunnel_cookieExist.bind(this), top.NetFunnel_isRunning = NetFunnel.NetFunnel_isRunning = NetFunnel_isRunning.bind(this), top.NetFunnel_goForm = NetFunnel.NetFunnel_goForm = NetFunnel_goForm.bind(this), top.NetFunnel_goUrl = NetFunnel.NetFunnel_goUrl = NetFunnel_goUrl.bind(this), top.NetFunnel_goFunc = NetFunnel.NetFunnel_goFunc = NetFunnel_goFunc.bind(this), top.NetFunnel_goComplete = NetFunnel.NetFunnel_goComplete = NetFunnel_goComplete.bind(this), top.NetFunnel_goAliveNotice = NetFunnel.NetFunnel_goAliveNotice = NetFunnel_goAliveNotice.bind(this), top.NetFunnel_Action = NetFunnel.NetFunnel_Action = NetFunnel_Action.bind(this), top.NetFunnel_Complete = NetFunnel.NetFunnel_Complete = NetFunnel_Complete.bind(this), top.NetFunnel_AliveNotice = NetFunnel.NetFunnel_AliveNotice = NetFunnel_AliveNotice.bind(this), top.DefaultCallback_onSuccess = NetFunnel.DefaultCallback_onSuccess = DefaultCallback_onSuccess.bind(this)) : (top.NetFunnel = NetFunnel, top.NetFunnel_init = NetFunnel_init, top.NetFunnel_sendStop = NetFunnel_sendStop, top.NetFunnel_getTicketID = NetFunnel_getTicketID, top.NetFunnel_chkEnter = NetFunnel_chkEnter, top.NetFunnel_getTidChkEnter = NetFunnel_getTidChkEnter, top.NetFunnel_aliveNotice = NetFunnel_aliveNotice, top.NetFunnel_setComplete = NetFunnel_setComplete, top.NetFunnel_cookieExist = NetFunnel_cookieExist, top.NetFunnel_isRunning = NetFunnel_isRunning, top.NetFunnel_goForm = NetFunnel_goForm, top.NetFunnel_goUrl = NetFunnel_goUrl, top.NetFunnel_goFunc = NetFunnel_goFunc, top.NetFunnel_goComplete = NetFunnel_goComplete, top.NetFunnel_goAliveNotice = NetFunnel_goAliveNotice, top.NetFunnel_Action = NetFunnel_Action, top.NetFunnel_Complete = NetFunnel_Complete, top.NetFunnel_AliveNotice = NetFunnel_AliveNotice, top.DefaultCallback_onSuccess = DefaultCallback_onSuccess), "object" == typeof module && "object" == typeof module.exports && (module.exports = NetFunnel)
}("undefined" != typeof window ? window : this);