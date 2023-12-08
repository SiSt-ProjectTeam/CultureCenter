/**
 * =========================================================================
 * 통합회원제 Web : 가맹점 제공 라이브러리
 * 
 * @fileName : lotte.sso.api.js
 * =========================================================================
 */

if (window.console == undefined) {
    console = {
        log: function() {}
    };
}
var SsoLibName = null;

var _isDebug = false; //디버그모드

//44401 : ch.join.alt.44401
//99997 : ch.comm.alt.99997
var _alertMsg = {
    "44401": {
        "default": "아이디, 비밀번호가 일치하지 않습니다.",
        "ko": "아이디, 비밀번호가 일치하지 않습니다.",
        "en": "The ID or password are incorrect.",
        "ja": "ID、パスワードが一致しません。",
        "zh_cn": "ID和密码不匹配。",
        "zh_tw": "ID和密碼不匹配。",
        "vi": "ID và mật khẩu không khớp."
    },
    "99997": {
        "default": "브라우저 쿠키 차단 상태이므로 정상 동작하지 않습니다.",
        "ko": "브라우저 쿠키 차단 상태이므로 정상 동작하지 않습니다.",
        "en": "Your browser is blocking cookies preventing normal operations.",
        "ja": "ブラウザのCookieをブロックした状態になっているため、正常に動作しません。",
        "zh_cn": "浏览器cookie被阻止，所以无法正常启动。",
        "zh_tw": "瀏覽器cookie被阻止，所以無法正常啟動。",
        "vi": "Cookie trình duyệt bị chặn, vì vậy nó không hoạt động được."
    }
};

var _noticePageUrl = {
    'default_error': '/exView/login/login_02_001',
    '00401': '/view/login/login_04_001', //성공 후처리 안내페이지 (비밀번호 변경 캠페인)
    '00403': '/view/login/login_04_002', //성공 후처리 안내페이지 (선택약관 추가수집 캠페인)
    '44404': '/exView/login/login_03_001', //실패 후처리 안내페이지 (부정로그인)
    '44406': '/exView/login/login_05_001', //실패 후처리 안내페이지 (LPOINT&제휴사 휴면)
    '44407': '/exView/login/login_05_001', //실패 후처리 안내페이지 (LPOINT 휴면)
    '44408': '/exView/login/login_05_001', //실패 후처리 안내페이지 (제휴사 휴면)
    '44410': '/exView/manage/chPassword_03_001', //실패 후처리 안내페이지 (임시비밀번호)
    '44419': '/exView/join/mbrJoin_04_001', //실패 후처리 안내페이지 (제휴사 약관 미동의상태)
    '44420': '/exView/join/mbrJoin_03_001', //실패 후처리 안내페이지 (제휴사 약관 미동의상태 14세미만)
    '44423': '/exView/login/socialReg_01_001', //실패 후처리 안내페이지 (소셜정보 매핑 실패)
    '44705': '/exView/login/esyReg_01_001', //실패 후처리 안내페이지 (등록된 간편인증 정보가 없음)
    '44434': '/exView/login/lv2CtfLogin_01_001' //실패 후처리 안내페이지 (2단계 인증 대기화면) 2020-11-18 added by 박재홍
};

var SsoClientLibrary = (function() {
    function SsoClientLibrary(initOptions) {
        var _acesTkn = initOptions.acesTkn == 'null' ? null : initOptions.acesTkn; //접근토큰
        var _clntAkInf = null; //클라이언트요청정보(JSON String)
        var _ccoSiteNo = null; //제휴사사이트번호
        var _urEvnmtDc = null; //사용자환경구분코드
        var _langCode = initOptions.langCode; //언어코드
        var _srnOpt = {}; //화면옵션
        var _rturUrl = null; //제휴사리턴URL
        var _rturUrlCaloMethd = null; //제휴사리턴URL HTTP METHOD
        var _callback = null; //hidden iframe callback 처리
        var _ccoCallback = null; //제휴사 callback 저장
        var _iosMobYn = false; //IOS모바일여부
        var _akHeader = {}; //JWT 헤더
        var _domain = null; //통합회원제 도메인
        var _clntEncKey = null; //JWT 암호화키
        var _signKey = null; //JWT 서명키
        var _self = this;
        //hidden iframe tag 지정
        var _hiddenIframe = {
            tag: '<div style="display:none" hidden-api="ssoweb_default">' +
                '		<iframe name="sso_iframe" />' +
                '</div>',
            close: function() {
                $('[hidden-api="ssoweb_default"]').empty();
                $('[hidden-api="ssoweb_default"]').remove();
            }
        };
        //hidden iframe으로 호출할 URL 모음
        var _apiUrl = {
            getSsoTkn: "/exView/api/getSsoTkn_01_001",
            callLogin: "/exView/api/callLgn_01_001",
            callSsoLogin: "/exView/api/callSsoLgn_01_001",
            callLogout: "/exView/api/callLgo_01_001",
            callLogoutforLptIdLgn: "/exView/api/lptIdLgnCallLgo_01_001",
            setSsoTkn: "/exView/api/setSsoTkn_01_001",
            getFdsDta: "/exView/api/getFdsDta_01_001",
            callCtfLogin: "/exView/api/callCtfLgn_01_001",
            callEsyJoinCtfLogin: "/exView/api/callCtfLgn_02_001", //2020-10-19 added by 박재홍. CI 간편가입 로그인 내용 추가
            callLv2CtfLogin: "/exView/api/callLv2CtfLogin_01_001" //2020-11-18 added by 박재홍. 2단계 인증 로그인 API용 빈화면 URL 추가
        };
        var _serviceUrl = {
            callLoginIdPswd: "/exBiz/login/login_01_001",
            callLoginSocial: "/exBiz/login/sociLogin_01_001",
            callLoginOnlCno: "/exBiz/login/ccoLogin_01_001",
            callSsoLoginSsoTkn: "/exBiz/login/ssoLogin_01_001",
            callSsoLoginRnwTkn: "/exBiz/login/autoLogin_01_001",
            callEasyLoginPin: "/bypass/fido/esyPin_01_001",
            callEasyLoginFingerPrint: "/exBiz/login/fidoLogin_01_001",
            callSsoGroupLogin: "/exBiz/login/ssoGrpLogin_01_001",
            callCtfLogin: "/exBiz/login/ctfLogin_01_001",
            callFidoCtfSttKeyIsu: "/biz/manage/fidoCtfSttKey_01_001",
            callFidoCtf: "/biz/manage/fidoCtf_01_001",
            callEsyJoinCtfLogin: "/exBiz/login/ctfLogin_02_001", //2020-10-19 added by 박재홍. CI 간편가입 로그인 내용 추가
            callLv2CtfLogin: "/exBiz/login/lv2CtfLogin_02_001" //2020-11-18 added by 박재홍. 2단계 인증 로그인 서비스 URL 추가
        }

        SsoLibName = initOptions.vrblNm || 'sso';

        if (initOptions.srnOpt != null) {
            _srnOpt = initOptions.srnOpt;
        } else {
            throw _isDebug ? 'srnOpt는 필수 항목입니다.' : 'ERROR';
        }
        if (_srnOpt.opMd == null || _srnOpt.opMd == "" || _srnOpt.opMd == '0') {
            _srnOpt.opMd = '0'; // opMd 기본값 세팅
        } else if (_srnOpt.opMd == '1') { //iframe 정보를 저장한다.
            if (_srnOpt.iframeTagNm == null || _srnOpt.iframeTagNm == "") {
                throw _isDebug ? 'srnOpt.opMd가 "1"일 때, srnOpt.iframeTagNm은 필수 항목입니다.' : 'ERROR';
            }
            if (_srnOpt.showFunc == null) {
                throw _isDebug ? 'srnOpt.opMd가 "1"일 때, srnOpt.showFunc은 필수 항목입니다.' : 'ERROR';
            } else if (typeof(_srnOpt.showFunc) !== "function") {
                throw _isDebug ? 'srnOpt.showFunc이 함수가 아닙니다.' : 'ERROR';
            }

            if (_srnOpt.hideFunc == null) {
                throw _isDebug ? 'srnOpt.opMd가 "1"일 때, srnOpt.hideFunc은 필수 항목입니다.' : 'ERROR';
            } else if (typeof(_srnOpt.hideFunc) !== "function") {
                throw _isDebug ? 'srnOpt.hideFunc이 함수가 아닙니다.' : 'ERROR';
            }
        } else if (_srnOpt.opMd == '2') {
            if (_srnOpt.popRturUrl == null || _srnOpt.popRturUrl == "") {
                throw _isDebug ? 'srnOpt.opMd가 "2"일 때, srnOpt.popRturUrl은 필수 항목입니다.' : 'ERROR';
            }
            if (_srnOpt.popRturUrl.indexOf("://") == -1) {
                throw _isDebug ? 'rturUrl은 프로토콜을 포함한 전체 URL 혹은 스키마를 입력해야합니다.' : 'ERROR';
            }
        } else {
            throw _isDebug ? "srnOpt.opMd가 '0', '1', '2'에 해당하지 않습니다." : "ERROR";
        }
        //srnHeaderExpsYn 기본값 세팅
        _srnOpt.srnHeaderExpsYn = (_srnOpt.srnHeaderExpsYn != null && _srnOpt.srnHeaderExpsYn != "") ? ((_srnOpt.srnHeaderExpsYn.toUpperCase() != 'Y' && _srnOpt.srnHeaderExpsYn.toUpperCase() != 'N') ? 'Y' : (_srnOpt.srnHeaderExpsYn.toUpperCase())) : 'Y';

        if (initOptions.clntAkInf != null && initOptions.clntAkInf != "") {
            _clntAkInf = initOptions.clntAkInf;
        } else {
            throw _isDebug ? 'clntAkInf는 필수 항목입니다.' : 'ERROR';
        }

        if (initOptions.ccoSiteNo != null && initOptions.ccoSiteNo != "") {
            _ccoSiteNo = initOptions.ccoSiteNo;
        } else {
            throw _isDebug ? 'ccoSiteNo는 필수 항목입니다.' : 'ERROR';
        }

        if (initOptions.urEvnmtDc != null && initOptions.urEvnmtDc != "") {
            if (!(initOptions.urEvnmtDc == '0' || initOptions.urEvnmtDc == '1' || initOptions.urEvnmtDc == '2')) {
                throw _isDebug ? "urEvnmtDc가 '0', '1', '2'에 해당하지 않습니다." : "ERROR";
            }
            _urEvnmtDc = initOptions.urEvnmtDc;
            if (_urEvnmtDc == '2' && navigator.userAgent.toLowerCase().match(/(iphone|ipod|ipad)/)) {
                //ios 웹앱 여부 체크해서 flag 세팅
                _iosMobYn = true;
            }
        } else {
            throw _isDebug ? 'urEvnmtDc는 필수 항목입니다.' : 'ERROR';
        }


        try {
            if (typeof(_clntAkInf) === 'string') {
                _clntAkInf = JSON.parse(_clntAkInf);
                if (_clntAkInf == null) {
                    throw _isDebug ? 'clntAkInf는 필수 항목입니다.' : 'ERROR';
                }
            }
        } catch (e) {
            throw e;
        }

        _akHeader = {
            akTypCode: 'C',
            flwNo: _clntAkInf.flwNo,
            ccoSiteNo: _ccoSiteNo
        };

        _domain = _clntAkInf.domain;
        _clntEncKey = _clntAkInf.clntEncKey;
        _signKey = _clntEncKey;


        /**
         * 제휴사 제공 Web API 라이브러리
         * - 로그인 요청
         */
        this.callLogin = function(options) {
            if (_isDebug) {
                console.log("[callLogin] API start");
            }
            //==========필수 항목 체크 및 데이터 세팅 start==========
            var data = {};
            //URL 체크
            if (SsoDataUtil.isEmpty(options.akUrl)) {
                throw _isDebug ? 'akUrl는 필수 항목입니다.' : 'ERROR';
            }
            //요청데이터 체크
            if (SsoDataUtil.isEmpty(options.akDta)) {
                throw _isDebug ? 'akDta는 필수 항목입니다.' : 'ERROR';
            } else {
                //통합회원제 양식에 맞게 세팅
                data.bfSrnPmt = {};
                data.bfSrnPmt.akUrl = options.akUrl;
                data.bfSrnPmt.lgnTpDc = '1';

                if (data.bfSrnPmt.akUrl == _serviceUrl.callLoginIdPswd) {
                    // ID/PSWD 로그인 일 때, 필수값 검증 및 세팅
                    if (SsoDataUtil.isEmpty(options.akDta.onlId)) {
                        throw _isDebug ? 'akDta.onlId는 필수 항목입니다.' : 'ERROR';
                    }
                    if (SsoDataUtil.isEmpty(options.akDta.cstPswd)) {
                        throw _isDebug ? 'akDta.cstPswd는 필수 항목입니다.' : 'ERROR';
                    }

                    data.bfSrnPmt.onlId = options.akDta.onlId;
                    options.akDta.cstPswdSha512 = SsoEncryptUtil.hashSHA512(options.akDta.cstPswd);
                    delete options.akDta['cstPswd'];
                    data.bfSrnPmt.cstPswdSha512 = options.akDta.cstPswdSha512;
                } else if (data.bfSrnPmt.akUrl == _serviceUrl.callLoginSocial) {
                    // 소셜 로그인 일 때, 필수값 검증 및 세팅
                    if (SsoDataUtil.isEmpty(options.akDta.copAccDc)) {
                        throw _isDebug ? 'akDta.copAccDc는 필수 항목입니다.' : 'ERROR';
                    }
                    if (SsoDataUtil.isEmpty(options.akDta.copTkn)) {
                        throw _isDebug ? 'akDta.copTkn은 필수 항목입니다.' : 'ERROR';
                    }
                    data.bfSrnPmt.copAccDc = options.akDta.copAccDc;
                    data.bfSrnPmt.copTkn = options.akDta.copTkn;
                    if (data.bfSrnPmt.copAccDc == '04') { //웨이신 소셜로그인
                        if (SsoDataUtil.isEmpty(options.akDta.copAdInf)) {
                            throw _isDebug ? 'akDta.copAccDc가 "04"일 때, akDta.copAdInf는 필수 항목입니다.' : 'ERROR';
                        } else {
                            data.bfSrnPmt.copAdInf = options.akDta.copAdInf;
                        }
                    }
                } else if (data.bfSrnPmt.akUrl == _serviceUrl.callLoginOnlCno) {
                    //온라인고객번호 로그인 일 때, 필수값 검증 및 세팅
                    if (SsoDataUtil.isEmpty(options.akDta.onlCno)) {
                        throw _isDebug ? 'akDta.onlCno은 필수 항목입니다.' : 'ERROR';
                    }
                    data.bfSrnPmt.onlCno = options.akDta.onlCno;
                    data.bfSrnPmt.lgnTpDc = '8';
                } else {
                    throw _isDebug ? "올바른 akUrl이 아닙니다." : "ERROR";
                }
            }

            //제휴사 전달메시지가 있는 경우 세팅
            if (!SsoDataUtil.isEmpty(options.ccoDlyMsg)) {
                data.ccoDlyMsg = options.ccoDlyMsg;
            }

            //로그인 후처리 모드 설정 ('0' : 라이브러리 후처리 프로세스 사용, '1' : 제휴사가 후처리 프로세스 작성)
            if (SsoDataUtil.isEmpty(options.aftPcMd)) {
                if (_isDebug) {
                    console.log("[callLogin] aftPcMd : null");
                    console.log("[callLogin] aftPcMd 기본 값 '0' 세팅");
                }
                data.aftPcMd = '0'; // 후처리 옵션 기본값 세팅
            } else if (options.aftPcMd == '0' || options.aftPcMd == '1') {
                data.aftPcMd = options.aftPcMd;
            } else {
                throw _isDebug ? "aftPcMd는 '0' 또는 '1' 값이어야 합니다." : "ERROR";
            }

            //로그인 callback함수 세팅
            if (SsoDataUtil.isFunction(options.callback)) {
                //로그인 요청의 callback함수 세팅
                if (data.aftPcMd == '0') {
                    // 라이브러리 자체 후처리모드인 경우 후처리 callback 세팅 메소드 호출
                    _callback = _self._setAftPcMdCallback(options);
                } else {
                    // 라이브러리 자체 후처리를 사용하지 않는 경우, 후처리 프로세스는 제휴사의 callback 함수에 있어야 함.
                    _callback = options.callback;
                }
            } else {
                throw _isDebug ? 'callback 함수는 필수 항목입니다.' : 'ERROR';
            }

            //리턴 URL & Http Method 세팅
            if (data.aftPcMd == '0' && _srnOpt.opMd == '0') {
                if (SsoDataUtil.isEmpty(options.rturUrl)) {
                    throw _isDebug ? 'akDta.aftPcMd가 "0"이고, srnOpt.opMd가 "0"일 때 rturUrl은 필수 항목입니다.' : 'ERROR';
                }
                if (SsoDataUtil.isEmpty(options.rturUrlCaloMethd)) {
                    throw _isDebug ? 'akDta.aftPcMd가 "0"이고, srnOpt.opMd가 "0"일 때 rturUrlCaloMethd는 필수 항목입니다.' : 'ERROR';
                }
                if (!_self._urlChecker(options.rturUrl)) {
                    throw _isDebug ? 'rturUrl은 프로토콜을 포함한 전체 URL 혹은 스키마를 입력해야합니다.' : 'ERROR';
                }
                data.rturUrl = options.rturUrl;
                data.rturUrlCaloMethd = options.rturUrlCaloMethd;
            }

            //2019-10-08 add. 로그아웃분리여부 파라미터 추가
            if (options.lgoSepYn != 'Y') {
                data.bfSrnPmt.lgoSepYn = 'N';
            } else {
                data.bfSrnPmt.lgoSepYn = 'Y';
            }
            //==========================================

            data.bfSrnPmt = JSON.stringify(data.bfSrnPmt);

            if (_isDebug) {
                console.log("[callLogin] data		: " + JSON.stringify(data));
            }
            //==========필수 항목 체크 및 데이터 세팅 end==========

            //2018-11-06 add. 브라우저 쿠키차단 설정에 따른 프로세스 분기
            if (navigator.cookieEnabled == false) {
                //브라우저 쿠키차단 설정 시 제휴사 응답데이터 세팅
                var rspDta = {
                    rspClac: 'A',
                    rspC: '99',
                    rspDtc: '997',
                    rspMsgCn: '브라우저 쿠키 차단 상태이므로 정상 동작하지 않습니다.'
                }
                _self._removeCallback(rspDta);
            } else {
                //hidden iframe 생성 및 호출
                _self._hiddenIframeOpen(_domain + _apiUrl.callLogin, data);
            }

            if (_isDebug) {
                console.log("[callLogin] API end");
            }
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - SSO로그인/자동로그인 요청 (웹뷰 케이스로 인해 실제 요청 함수를 한번더 감싼 형태)
         */
        this.callSsoLogin = function(options) {
            //SSO로그인에서 IOS 호출 방식이 스키마호출(GET방식)이기 때문에 먼저 토큰 저장소를 조회
            //hidden iframe으로 인한 제약사항
            if (options.akUrl == _serviceUrl.callSsoLoginSsoTkn) {
                if (_isDebug) {
                    console.log("[callSsoLogin] SsoLogin");
                }
                if (!SsoDataUtil.isEmpty(options.akDta) && !SsoDataUtil.isEmpty(options.akDta.ssoTkn)) {
                    //파라미터로 ssoTkn이 들어오는 경우 앱저장소를 조회하지 않고 ssoLogin 진행
                    if (_isDebug) {
                        console.log("[callSsoLogin] SsoLogin with Parameter");
                    }
                    //파라미터로 ssoTkn이 들어오는 경우에만 후처리 적용
                    _self._callSsoLogin(options, true);
                } else {
                    if (_isDebug) {
                        console.log("[callSsoLogin] SsoLogin without Parameter");
                    }
                    _self.getSsoTkn({
                        callback: function(ssoTkn) {
                            var _options = options;

                            if (!SsoDataUtil.isEmpty(_options.akDta)) {
                                //ssoTkn에 대한 검증은 callSsoLogin API 내에서 수행
                                //조회 결과를 무조건 세팅해서 함수 호출
                                _options.akDta.ssoTkn = ssoTkn;
                            } else {
                                _options.akDta = {
                                    ssoTkn: ssoTkn
                                };
                            }
                            _self._callSsoLogin(_options);
                        }
                    });
                }
            } else if (options.akUrl == _serviceUrl.callSsoLoginRnwTkn) {
                if (_isDebug) {
                    console.log("[callSsoLogin] AutoLogin");
                }
                _self._callSsoLogin(options);
            } else {
                throw _isDebug ? "잘못된 akUrl입니다. URL 값을 확인해주시기 바랍니다." : "ERROR";
            }
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - 로그아웃요청
         */
        this.callLogout = function(options) {
            if (_isDebug) {
                console.log("[callLogout] API start");
            }

            if (SsoDataUtil.isEmpty(_acesTkn)) {
                throw _isDebug ? "acesTkn이 없습니다. 로그인한 사용자를 찾을 수 없습니다." : "ERROR";
            }
            var data = {};
            data.acesTkn = _acesTkn;

            if (SsoDataUtil.isFunction(options.callback)) {
                _callback = options.callback;
            } else {
                throw _isDebug ? 'callback 함수는 필수 항목입니다.' : 'ERROR';
            }

            if (_isDebug) {
                console.log("[callLogout] data		: " + JSON.stringify(data));
            }
            //2018-11-06 add. 브라우저 쿠키차단 설정에 따른 프로세스 분기
            if (navigator.cookieEnabled == false) {
                //브라우저 쿠키차단 설정 시 제휴사 응답데이터 세팅
                var rspDta = {
                    rspClac: 'A',
                    rspC: '99',
                    rspDtc: '997',
                    rspMsgCn: '브라우저 쿠키 차단 상태이므로 정상 동작하지 않습니다.'
                }
                _self._removeCallback(rspDta);
            } else {
                _self._hiddenIframeOpen(_domain + _apiUrl.callLogout, data);
            }

            if (_isDebug) {
                console.log("[callLogout] API end");
            }
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - 로그아웃요청(엘포인트 아이디로 로그인)
         */
        this.callLogoutforLptIdLgn = function(options) {
            if (_isDebug) {
                console.log("[callLogout] API start");
            }

            if (SsoDataUtil.isEmpty(_acesTkn)) {
                throw _isDebug ? "acesTkn이 없습니다. 로그인한 사용자를 찾을 수 없습니다." : "ERROR";
            }
            var data = {};
            data.acesTkn = _acesTkn;

            if (SsoDataUtil.isFunction(options.callback)) {
                _callback = options.callback;
            } else {
                throw _isDebug ? 'callback 함수는 필수 항목입니다.' : 'ERROR';
            }

            if (_isDebug) {
                console.log("[callLogout] data		: " + JSON.stringify(data));
            }
            //2018-11-06 add. 브라우저 쿠키차단 설정에 따른 프로세스 분기
            if (navigator.cookieEnabled == false) {
                //브라우저 쿠키차단 설정 시 제휴사 응답데이터 세팅
                var rspDta = {
                    rspClac: 'A',
                    rspC: '99',
                    rspDtc: '997',
                    rspMsgCn: '브라우저 쿠키 차단 상태이므로 정상 동작하지 않습니다.'
                }
                _self._removeCallback(rspDta);
            } else {
                _self._hiddenIframeOpen(_domain + _apiUrl.callLogoutforLptIdLgn, data);
            }

            if (_isDebug) {
                console.log("[callLogout] API end");
            }
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - 통합회원제 서비스 요청 (Ajax통신)
         */
        this.callApi = function(options) {
            if (_isDebug) {
                console.log("[callApi] API start");
                console.log("[callApi] open : " + options.akUrl);
            }
            if (SsoDataUtil.isEmpty(options.akUrl)) {
                throw _isDebug ? 'akUrl은 필수 항목입니다.' : 'ERROR';
            }

            var data = null;
            if (SsoDataUtil.isEmpty(options.akDta)) {
                data = {};
            } else if (typeof(options.akDta) === "object") {
                data = options.akDta;
            } else {
                if (SsoDataUtil.isJsonStr(options.akDta)) {
                    data = JSON.parse(options.akDta);
                }
            }
            data.acesTkn = _acesTkn;

            data.ccoDlyMsg = options.ccoDlyMsg;

            data.akCcoDmn = document.domain;

            if (!SsoDataUtil.isFunction(options.callback)) {
                throw _isDebug ? 'callback 함수는 필수 항목입니다.' : 'ERROR';
            }

            if (_isDebug) {
                console.log("[callApi] data			: " + JSON.stringify(data));
            }
            //JSON->JWT
            data = SsoEncryptUtil.createJwt(_clntEncKey, _signKey, _akHeader, data);

            //서비스 호출(use Ajax)
            SsoHttpUtil.ajax(_domain + options.akUrl, data,
                function(response) {
                    var jsonOutput = {};
                    try {
                        if (typeof(response) === 'object') {
                            jsonOutput = response;
                        } else if (SsoDataUtil.isJsonStr(response)) {
                            jsonOutput = JSON.parse(response);
                        } else {
                            jsonOutput = SsoEncryptUtil.extractJwtPayload(_clntEncKey, response);
                        }
                    } catch (e) {
                        throw _isDebug ? 'JWT 변환오류' : 'ERROR';
                    }

                    if (_isDebug) {
                        console.log("[callApi] response			: " + JSON.stringify(jsonOutput));
                    }
                    //callback에 응답데이터 전달
                    options.callback(jsonOutput);
                }
            );

            if (_isDebug) {
                console.log("[callApi] API end");
            }
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - 통합회원제 화면호출
         */
        this.callScreen = function(options) {
            if (_isDebug) {
                console.log("[callScreen] API start");
                console.log("[callScreen] open		 : " + options.akUrl);
            }
            if (SsoDataUtil.isEmpty(options.akUrl)) {
                throw _isDebug ? 'akUrl은 필수 항목입니다.' : 'ERROR';
            }
            var data = {};
            data.bfSrnPmt = options.akDta;
            if (SsoDataUtil.isEmpty(data.bfSrnPmt)) {
                data.bfSrnPmt = {};
            }
            data.acesTkn = _acesTkn;

            data.ccoDlyMsg = options.ccoDlyMsg;

            data.akCcoDmn = document.domain;

            //라이브러리 초기화시 지정한 화면연동방식
            data.opMd = _srnOpt.opMd;
            data.langCode = _langCode;
            data.srnHeaderExpsYn = _srnOpt.srnHeaderExpsYn;
            data.urEvnmtDc = _urEvnmtDc;
            var target = null;
            if (_srnOpt.opMd == '0') { //redirection
                if (SsoDataUtil.isEmpty(options.rturUrl)) {
                    throw _isDebug ? "srnOpt.opMd가 '0'일때, rturUrl은 필수 항목입니다." : "ERROR";
                } else if (SsoDataUtil.isEmpty(options.rturUrlCaloMethd)) {
                    throw _isDebug ? "srnOpt.opMd가 '0'일때, rturUrlCaloMethd는 필수 항목입니다." : "ERROR";
                }
                if (!_self._urlChecker(options.rturUrl)) {
                    throw _isDebug ? 'rturUrl은 프로토콜을 포함한 전체 URL 혹은 스키마를 입력해야합니다.' : 'ERROR';
                }
                data.rturUrl = options.rturUrl;
                data.rturUrlCaloMethd = options.rturUrlCaloMethd;
            } else if (_srnOpt.opMd == '1') { //custom iframe
                target = _srnOpt.iframeTagNm;
                _srnOpt.showFunc();
            } else if (_srnOpt.opMd == '2') { //popup
                if (_urEvnmtDc == '2') {
                    //2018-11-06 add. 하이브리드 앱의 팝업모드는 외부 브라우져(모바일)
                    data.urEvnmtDc = '1';
                }
                data.rturUrl = _srnOpt.popRturUrl;
                data.rturUrlCaloMethd = 'get';
            }

            if (_isDebug) {
                console.log("[callScreen] data		 : " + JSON.stringify(data));
                console.log("[callScreen] target	 : " + target);
            }

            data.bfSrnPmt = JSON.stringify(data.bfSrnPmt);
            //JSON->JWT
            data = SsoEncryptUtil.createJwt(_clntEncKey, _signKey, _akHeader, data);

            if (window.ssoJsItf && _srnOpt.opMd == '2') { //android popup으로 submit
                //안드로이드 javascriptinterface 호출
                window.ssoJsItf.callExternalBrowser(_domain + options.akUrl, "jwt", data);
            } else if (_iosMobYn && _srnOpt.opMd == '2') {
                //IOS 스키마 호출 및 파라미터 세팅
                _self._callIOSScheme("lpointSsoApp://callExternalBrowser?url=" + _domain + options.akUrl + "&jwt=" + data);
            } else { // 그 외 케이스
                try {
                    if (_srnOpt.opMd == '2') {
                        target = _self._createPopup(options.popWidth, options.popHeight);
                    }
                    //submit
                    _self._submit(_domain + options.akUrl, data, target);
                } catch (e) {
                    alert("팝업이 차단되었습니다. 팝업차단을 해제해주세요.");
                }
            }

            if (_isDebug) {
                console.log("[callScreen] API end");
            }
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - SSO토큰조회 API
         */
        this.getSsoTkn = function(options) {
            if (_isDebug) {
                console.log("[getSsoTkn] API start");
            }
            if (SsoDataUtil.isFunction(options.callback)) {
                _ccoCallback = options.callback;
            } else {
                throw _isDebug ? 'callback 함수는 필수 항목입니다.' : 'ERROR';
            }

            if (window.ssoJsItf) {
                //안드로이드 javascriptinterface 호출
                _self._getCcoCallback(window.ssoJsItf.getSsoTkn());
            } else if (_iosMobYn) {
                //IOS 스키마 호출 및 파라미터로 콜백함수 지정
                var fnNm = SsoLibName + "._getCcoCallback";
                _self._callIOSScheme("lpointSsoApp://getSsoTkn?callback=" + fnNm);
            } else {
                //2018-11-06 add. 브라우저 쿠키차단 설정에 따른 프로세스 분기
                if (navigator.cookieEnabled == false) {
                    //브라우저 쿠키차단 설정 시 ssoTkn값은 null을 반환
                    _self._removeCcoCallback(null);
                } else {
                    _self._hiddenIframeOpen(_domain + _apiUrl.getSsoTkn);
                }
            }

            if (_isDebug) {
                console.log("[getSsoTkn] API end");
            }
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - PIN/지문 로그인 요청을 보내는 API 메소드
         */
        this.callEasyLogin = function(options) {
            if (_isDebug) {
                console.log("[callEasyLogin] API Start");
                console.log("[callEasyLogin] 필수 항목 검증 시작");
            }

            var akUrl = options.akUrl;
            var rturUrl = options.rturUrl;
            var rturUrlCaloMethd = options.rturUrlCaloMethd;
            var fnNm = "SsoInterfaceCallback"; //앱에서 호출할 JS 함수

            //필수 항목 검증 시작
            //akUrl
            if (SsoDataUtil.isEmpty(akUrl)) {
                throw _isDebug ? "akUrl은 필수 항목입니다." : "ERROR";
            } else if (akUrl != _serviceUrl.callEasyLoginPin && akUrl != _serviceUrl.callEasyLoginFingerPrint) {
                throw _isDebug ? "akUrl이 올바르지 않습니다." : "ERROR";
            }
            //aftPcMd
            if (SsoDataUtil.isEmpty(options.aftPcMd)) {
                if (_isDebug) {
                    console.log("[callEasyLogin] aftPcMd가 누락되었습니다.");
                    console.log("[callEasyLogin] aftPcMd 기본 값 '0'으로 세팅합니다.");
                }
                options.aftPcMd = '0';
            } else if (options.aftPcMd != '0' && options.aftPcMd != '1') {
                throw _isDebug ? "aftPcMd가 '0' 또는 '1' 이 아닙니다." : "ERROR";
            }
            //callback
            if (SsoDataUtil.isFunction(options.callback)) {
                if (options.aftPcMd == '0') {
                    //라이브러리 후처리모드용 callback 세팅
                    _callback = _self._setAftPcMdCallback(options);
                } else {
                    //제휴사 자체 후처리모드용 callback 세팅
                    _callback = options.callback;
                }
            } else {
                throw _isDebug ? "callback이 함수가 아닙니다." : "ERROR";
            }
            //rturUrl
            //rturUrlCaloMethd
            if (options.aftPcMd == '0' && _srnOpt.opMd == '0') {
                if (SsoDataUtil.isEmpty(options.rturUrl)) {
                    throw _isDebug ? "aftPcMd가 '0'이고, srnOpt.opMd가 '0'일 때 rturUrl은 필수 항목입니다." : "ERROR";
                }
                if (SsoDataUtil.isEmpty(options.rturUrlCaloMethd)) {
                    throw _isDebug ? "aftPcMd가 '0'이고, srnOpt.opMd가 '0'일 때 rturUrlCaloMethd은 필수 항목입니다." : "ERROR";
                }
                if (!_self._urlChecker(options.rturUrl)) {
                    throw _isDebug ? 'rturUrl은 프로토콜을 포함한 전체 URL 혹은 스키마를 입력해야합니다.' : 'ERROR';
                }
            }
            //======2019-11-15 added by 박재홍. 간편로그인에 로그아웃분리여부 항목 추가==========
            var lgoSepYn = "N";
            if (options.lgoSepYn != 'Y') {
                lgoSepYn = 'N';
            } else {
                lgoSepYn = 'Y';
            }
            //=============================================================================
            //필수 항목 검증 종료


            if (_isDebug) {
                console.log("[callEasyLogin] 필수 항목 검증 종료");
            }

            var langCode = _langCode || "";

            if (_iosMobYn) { //ios
                //URL 스키마 세팅 후 get방식으로 호출
                var url_scheme = "lpointSsoApp://requestEasyLogin?";
                url_scheme += "domain=" + _domain;
                url_scheme += "&bizUrl=" + akUrl;
                url_scheme += "&ccoSiteNo=" + _ccoSiteNo;
                url_scheme += "&langCode=" + langCode;
                url_scheme += "&rturUrl=" + rturUrl;
                url_scheme += "&rturUrlCaloMethd=" + rturUrlCaloMethd;
                url_scheme += "&opMd=" + _srnOpt.opMd;
                url_scheme += "&aftPcMd=" + options.aftPcMd;
                url_scheme += "&lgoSepYn=" + lgoSepYn;
                url_scheme += "&callback=" + fnNm;
                _self._callIOSScheme(url_scheme);
            } else if (window.ssoJsItf) { //android
                //안드로이드 인터페이스 메소드 호출
                //window.ssoJsItf.requestEasyLogin(_domain, akUrl, _ccoSiteNo, rturUrl, rturUrlCaloMethd, _srnOpt.opMd, options.aftPcMd ,fnNm);
                if ("N" == lgoSepYn) {
                    window.ssoJsItf.requestEasyLogin(_domain, akUrl, _ccoSiteNo, langCode, rturUrl, rturUrlCaloMethd, _srnOpt.opMd, options.aftPcMd, fnNm);
                } else {
                    window.ssoJsItf.requestEasyLogin(_domain, akUrl, _ccoSiteNo, langCode, rturUrl, rturUrlCaloMethd, _srnOpt.opMd, options.aftPcMd, lgoSepYn, fnNm);
                }
            } else { //etc
                //웹앱이 아닌경우 해당 기능을 지원하지 않는다.
                return;
            }

            if (_isDebug) {
                console.log("[callEasyLogin] API End");
            }
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - 모바일 웹앱에서 L.point 앱을 호출하여 로그인 요청을 보낸다.
         * L.point 앱 호출을 통해 사용자 인증을 마친 후 각 제휴사에서 로그인 처리를 하기 위해 callSsoLogin API를 이용한다.
         */
        this.callLpointAppLogin = function(options) {
            //rturUrl : Lpoint앱에서 프로세스가 끝나고 응답을 받을 제휴사 앱의 스키마 URL

            var data = {};

            if (SsoDataUtil.isEmpty(options.rturUrl)) {
                throw _isDebug ? "rturUrl은 필수 항목입니다." : "ERROR";
            } else if (!_self._urlChecker(options.rturUrl)) {
                throw _isDebug ? "rturUrl은 프로토콜을 포함한 전체 URL 혹은 스키마를 입력해야합니다." : "ERROR";
            }

            data.rturUrl = options.rturUrl;
            data.rturUrlCaloMethd = 'get';

            //화면 호출 필수 데이터 세팅
            //Lpoint 앱은 하이브리드 앱+외부로 호출하는 것이기에 구분 값 강제 세팅
            data.urEvnmtDc = '2';
            data.opMd = '2';
            data.srnHeaderExpsYn = _srnOpt.srnHeaderExpsYn;
            data.langCode = _langCode;
            //2018-11-13 add. 요청제휴사도메인정보 추가
            data.akCcoDmn = document.domain;

            if (_iosMobYn) {
                data = SsoEncryptUtil.createJwt(_clntEncKey, _clntEncKey, _akHeader, data);
                //IOS 웹앱
                var url_scheme = "lpointSsoApp://requestLpointApp";
                url_scheme += "?jwt=" + data;
                _self._callIOSScheme(url_scheme);
            } else if (window.ssoJsItf) {
                data = SsoEncryptUtil.createJwt(_clntEncKey, _clntEncKey, _akHeader, data);
                //안드로이드 웹앱
                window.ssoJsItf.requestLpointApp(data);
            } else if (_urEvnmtDc == '1') {
                //모바일 웹 브라우저
                data = SsoEncryptUtil.createJwt(_clntEncKey, _clntEncKey, _akHeader, data);
                var lpointAppScheme = 'lottemembers://ims?type=LOG_01_001';
                //2018-11-05 add. 앱 호출 시 get방식 쿼리스트링에 '+'가 ' '로 바뀌는 문제
                //web->앱으로 넘어갈 때 디코딩 1번 & 앱->통합회원제 요청할 때 디코딩 1번 하므로, 총 2번에 걸쳐 인코딩 처리
                lpointAppScheme += "&jwt=" + SsoEncryptUtil.encodeUri(SsoEncryptUtil.encodeUri(data));
                window.location.href = lpointAppScheme;
            } else {
                //상기 케이스가 아닌 경우는 지원하지 않는다
                return;
            }
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * SSO 그룹로그인
         * - 통합가입을 사용하는 제휴사들이 사용하는 SSO 그룹로그인 메소드
         */
        this.callSsoGroupLogin = function(option) {
            if (option.akUrl == _serviceUrl.callSsoGroupLogin) {
                if (_isDebug) {
                    console.log("[callSsoGroupLogin] SSO Group Login start");
                }

                //ssoTkn은 입력파라미터로 필수적으로 들어옴
                if (option.akDta != null) {
                    if (SsoDataUtil.isEmpty(option.akDta.ssoTkn)) {
                        //ssoTkn 필수값 누락
                        throw _isDebug ? "ssoTkn은 필수 입력 항목입니다." : "ERROR";
                    }

                    if (SsoDataUtil.isEmpty(option.akDta.siteGrpNo)) {
                        //사이트그룹번호 필수값 누락
                        throw _isDebug ? "siteGrpNo는 필수 입력 항목입니다." : "ERROR";
                    }

                    if (option.callback == null) {
                        throw _isDebug ? "callback은 필수 입력 항목입니다." : "ERROR";
                    }

                    if (!SsoDataUtil.isFunction(option.callback)) {
                        throw _isDebug ? "callback은 함수여야 합니다." : "ERROR";
                    }

                    if (option.aftPcMd != "1") {
                        //SSO 그룹로그인은 제휴사 자체 후처리모드로 강제함
                        option.aftPcMd = "1";
                    }

                    //SSO 로그인 요청
                    _self._callSsoLogin(option);
                } else {
                    //akDta 필수값 누락
                    throw _isDebug ? "akDta는 필수 입력 항목입니다." : "ERROR";
                }

            } else {
                //잘못된 URL 입력
                throw _isDebug ? "잘못된 akUrl입니다. URL 값을 확인해주시기 바랍니다." : "ERROR";
            }
        }

        /**
         * 2019-09-18 added by 박재홍. 신규 API 추가
         * 제휴사 제공 Web API 라이브러리
         * FDS 데이터 생성
         * - FDS 솔루션 데이터를 생성하여 콜백함수로 반환
         */
        this.getFdsDta = function(options) {
            if (_isDebug) {
                console.log("[getFdsDta] API start");
            }
            if (SsoDataUtil.isFunction(options.callback)) {
                _ccoCallback = function(response) {
                    var rspDta = "";
                    if (SsoDataUtil.isJsonStr(response)) {
                        rspDta = JSON.parse(response);
                    } else {
                        rspDta = response;
                    }

                    options.callback(rspDta);
                }
            } else {
                throw _isDebug ? 'callback 함수는 필수 항목입니다.' : 'ERROR';
            }

            if (navigator.cookieEnabled == false) {
                //브라우저 쿠키차단 설정 시 비정상동작 경고 응답
                _self._removeCcoCallback({
                    rspClac: 'A',
                    rspC: '99',
                    rspDtc: '997',
                    rspMsgCn: '브라우저 쿠키 차단 상태이므로 정상 동작하지 않습니다.'
                });
            } else {
                _self._hiddenIframeOpen(_domain + _apiUrl.getFdsDta);
            }

            if (_isDebug) {
                console.log("[getFdsDta] API end");
            }
        }

        /**
         * 2019-09-18 added by 박재홍. 신규 API 추가
         * 제휴사 제공 Web API 라이브러리
         * SSO 토큰 저장
         * - SSO 토큰을 사용자환경에 따라 저장소에 저장
         * - PC/MOB WEB은 세션스토리지
         * - MOB APP은 앱저장소
         */
        this.setSsoTkn = function(options) {
            if (_isDebug) {
                console.log("[setSsoTkn] API start");
            }

            if (SsoDataUtil.isEmpty(options.ssoTkn)) {
                throw _isDebug ? 'SSO토큰은 필수 항목입니다.' : 'ERROR';
            }

            if (SsoDataUtil.isFunction(options.callback)) {
                _ccoCallback = options.callback;
            } else {
                throw _isDebug ? 'callback 함수는 필수 항목입니다.' : 'ERROR';
            }

            if (window.ssoJsItf) {
                //안드로이드 javascriptinterface 호출
                window.ssoJsItf.saveSsoTkn(options.ssoTkn);
                _self._getCcoCallback({
                    rspC: '00',
                    rspDtc: '000',
                    rspClac: 'S',
                    rspMsg: '정상 처리되었습니다.'
                });
            } else if (_iosMobYn) {
                //IOS 스키마 호출 및 파라미터로 콜백함수 지정
                _self._callIOSScheme("lpointSsoApp://saveSsoTkn?ssoTkn=" + options.ssoTkn);
                _self._getCcoCallback({
                    rspC: '00',
                    rspDtc: '000',
                    rspClac: 'S',
                    rspMsg: '정상 처리되었습니다.'
                });
            } else {
                if (navigator.cookieEnabled == false) {
                    //브라우저 쿠키차단 설정 시 비정상동작 경고 응답
                    _self._removeCcoCallback({
                        rspClac: 'A',
                        rspC: '99',
                        rspDtc: '997',
                        rspMsgCn: '브라우저 쿠키 차단 상태이므로 정상 동작하지 않습니다.'
                    });
                } else {
                    var data = {};
                    var akDta = {};
                    data.ssoTkn = options.ssoTkn;
                    akDta.bfSrnPmt = JSON.stringify(data);

                    _self._hiddenIframeOpen(_domain + _apiUrl.setSsoTkn, akDta);
                }
            }

            if (_isDebug) {
                console.log("[setSsoTkn] API end");
            }
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - CI번호로 로그인을 요청하는 메소드
         */
        this.callCtfLogin = function(options) {
            if (_isDebug) {
                console.log("[callCtfLogin] API start");
            }

            //필수값 체크 및 항목 세팅
            var data = {};
            //URL 체크
            if (SsoDataUtil.isEmpty(options.akUrl)) {
                throw _isDebug ? 'akUrl는 필수 항목입니다.' : 'ERROR';
            }
            //요청데이터 체크
            if (SsoDataUtil.isEmpty(options.akDta)) {
                throw _isDebug ? 'akDta는 필수 항목입니다.' : 'ERROR';
            } else {
                //통합회원제 양식에 맞게 세팅
                data.bfSrnPmt = {};
                data.bfSrnPmt.akUrl = options.akUrl;
                data.bfSrnPmt.lgnTpDc = '9';

                if (data.bfSrnPmt.akUrl == _serviceUrl.callCtfLogin) {
                    // 필수값 검증 및 세팅
                    if (SsoDataUtil.isEmpty(options.akDta.ciNo)) {
                        throw _isDebug ? 'akDta.ciNo는 필수 항목입니다.' : 'ERROR';
                    }

                    //CI번호 세팅
                    data.bfSrnPmt.ciNo = options.akDta.ciNo;
                    //onlId는 선택항목으로 입력 될 수 있음
                    data.bfSrnPmt.onlId = options.akDta.onlId;

                } else {
                    throw _isDebug ? "올바른 akUrl이 아닙니다." : "ERROR";
                }
            }

            //제휴사 전달메시지가 있는 경우 세팅
            if (!SsoDataUtil.isEmpty(options.ccoDlyMsg)) {
                data.ccoDlyMsg = options.ccoDlyMsg;
            }

            //로그인 후처리 모드 설정 ('0' : 라이브러리 후처리 프로세스 사용, '1' : 제휴사가 후처리 프로세스 작성)
            if (SsoDataUtil.isEmpty(options.aftPcMd)) {
                if (_isDebug) {
                    console.log("[callCtfLogin] aftPcMd : null");
                    console.log("[callCtfLogin] aftPcMd 기본 값 '0' 세팅");
                }
                data.aftPcMd = '0'; // 후처리 옵션 기본값 세팅
            } else if (options.aftPcMd == '0' || options.aftPcMd == '1') {
                data.aftPcMd = options.aftPcMd;
            } else {
                throw _isDebug ? "aftPcMd는 '0' 또는 '1' 값이어야 합니다." : "ERROR";
            }

            //로그인 callback함수 세팅
            if (SsoDataUtil.isFunction(options.callback)) {
                //로그인 요청의 callback함수 세팅
                if (data.aftPcMd == '0') {
                    // 라이브러리 자체 후처리모드인 경우 후처리 callback 세팅 메소드 호출
                    _callback = _self._setAftPcMdCallback(options);
                } else {
                    // 라이브러리 자체 후처리를 사용하지 않는 경우, 후처리 프로세스는 제휴사의 callback 함수에 있어야 함.
                    _callback = options.callback;
                }
            } else {
                throw _isDebug ? 'callback 함수는 필수 항목입니다.' : 'ERROR';
            }

            //리턴 URL & Http Method 세팅
            if (data.aftPcMd == '0' && _srnOpt.opMd == '0') {
                if (SsoDataUtil.isEmpty(options.rturUrl)) {
                    throw _isDebug ? 'akDta.aftPcMd가 "0"이고, srnOpt.opMd가 "0"일 때 rturUrl은 필수 항목입니다.' : 'ERROR';
                }
                if (SsoDataUtil.isEmpty(options.rturUrlCaloMethd)) {
                    throw _isDebug ? 'akDta.aftPcMd가 "0"이고, srnOpt.opMd가 "0"일 때 rturUrlCaloMethd는 필수 항목입니다.' : 'ERROR';
                }
                if (!_self._urlChecker(options.rturUrl)) {
                    throw _isDebug ? 'rturUrl은 프로토콜을 포함한 전체 URL 혹은 스키마를 입력해야합니다.' : 'ERROR';
                }
                data.rturUrl = options.rturUrl;
                data.rturUrlCaloMethd = options.rturUrlCaloMethd;
            }

            //2019-10-08 add. 로그아웃분리여부 파라미터 추가
            if (options.lgoSepYn != 'Y') {
                data.bfSrnPmt.lgoSepYn = 'N';
            } else {
                data.bfSrnPmt.lgoSepYn = 'Y';
            }
            //==========================================

            data.bfSrnPmt = JSON.stringify(data.bfSrnPmt);

            if (_isDebug) {
                console.log("[callCtfLogin] data		: " + JSON.stringify(data));
            }

            //브라우저 캐시 체크 및 호출
            if (navigator.cookieEnabled == false) {
                //브라우저 쿠키차단 설정 시 제휴사 응답데이터 세팅
                var rspDta = {
                    rspClac: 'A',
                    rspC: '99',
                    rspDtc: '997',
                    rspMsgCn: '브라우저 쿠키 차단 상태이므로 정상 동작하지 않습니다.'
                }
                _self._removeCallback(rspDta);
            } else {
                //hidden iframe 생성 및 호출
                _self._hiddenIframeOpen(_domain + _apiUrl.callCtfLogin, data);
            }

            if (_isDebug) {
                console.log("[callCtfLogin] API End");
            }
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - CI번호로 간편가입 및 로그인을 요청하는 메소드
         */
        this.callEsyJoinCtfLogin = function(options) {
            if (_isDebug) {
                console.log("[callEsyJoinCtfLogin] API start");
            }

            //필수값 체크 및 항목 세팅
            var data = {};
            //URL 체크
            if (SsoDataUtil.isEmpty(options.akUrl)) {
                throw _isDebug ? 'akUrl는 필수 항목입니다.' : 'ERROR';
            }
            //요청데이터 체크
            if (SsoDataUtil.isEmpty(options.akDta)) {
                throw _isDebug ? 'akDta는 필수 항목입니다.' : 'ERROR';
            } else {
                //통합회원제 양식에 맞게 세팅
                data.bfSrnPmt = {};
                data.bfSrnPmt.akUrl = options.akUrl;
                data.bfSrnPmt.lgnTpDc = '9';

                if (data.bfSrnPmt.akUrl == _serviceUrl.callEsyJoinCtfLogin) {
                    // 필수값 검증 및 세팅
                    if (SsoDataUtil.isEmpty(options.akDta.hsCtfMdc)) {
                        throw _isDebug ? 'akDta.hsCtfMdc는 필수 항목입니다.' : 'ERROR';
                    }

                    if (SsoDataUtil.isEmpty(options.akDta.cstNm)) {
                        throw _isDebug ? 'akDta.cstNm는 필수 항목입니다.' : 'ERROR';
                    }

                    if (SsoDataUtil.isEmpty(options.akDta.bird)) {
                        throw _isDebug ? 'akDta.bird는 필수 항목입니다.' : 'ERROR';
                    }

                    if (SsoDataUtil.isEmpty(options.akDta.maFemDvC)) {
                        throw _isDebug ? 'akDta.maFemDvC는 필수 항목입니다.' : 'ERROR';
                    }

                    if (SsoDataUtil.isEmpty(options.akDta.frnYn)) {
                        throw _isDebug ? 'akDta.frnYn는 필수 항목입니다.' : 'ERROR';
                    }

                    if (SsoDataUtil.isEmpty(options.akDta.ciNo)) {
                        throw _isDebug ? 'akDta.ciNo는 필수 항목입니다.' : 'ERROR';
                    }

                    if (SsoDataUtil.isEmpty(options.akDta.diNo)) {
                        throw _isDebug ? 'akDta.diNo는 필수 항목입니다.' : 'ERROR';
                    }

                    if (options.akDta.hsCtfMdc == "H" && SsoDataUtil.isEmpty(options.akDta.tcccDc)) {
                        throw _isDebug ? 'akDta.tcccDc는 필수 항목입니다.' : 'ERROR';
                    }

                    if (options.akDta.hsCtfMdc == "H" && SsoDataUtil.isEmpty(options.akDta.mbzNoC)) {
                        throw _isDebug ? 'akDta.mbzNoC는 필수 항목입니다.' : 'ERROR';
                    }

                    if (options.akDta.hsCtfMdc == "H" && SsoDataUtil.isEmpty(options.akDta.mmtExno)) {
                        throw _isDebug ? 'akDta.mmtExno는 필수 항목입니다.' : 'ERROR';
                    }

                    if (options.akDta.hsCtfMdc == "H" && SsoDataUtil.isEmpty(options.akDta.mtlno)) {
                        throw _isDebug ? 'akDta.mtlno는 필수 항목입니다.' : 'ERROR';
                    }

                    if (!SsoDataUtil.isEmpty(options.akDta.autoPcYn) && options.akDta.autoPcYn == "Y") {
                        data.bfSrnPmt = options.akDta;
                        data.bfSrnPmt.akUrl = options.akUrl;
                        data.bfSrnPmt.lgnTpDc = '9';
                    } else {
                        data.bfSrnPmt.autoPcYn == "N"
                        //==========================================================

                        //필수 입력데이터 세팅
                        data.bfSrnPmt.hsCtfMdc = options.akDta.hsCtfMdc;
                        data.bfSrnPmt.cstNm = options.akDta.cstNm;
                        data.bfSrnPmt.bird = options.akDta.bird;
                        data.bfSrnPmt.maFemDvC = options.akDta.maFemDvC;
                        data.bfSrnPmt.frnYn = options.akDta.frnYn;
                        data.bfSrnPmt.ciNo = options.akDta.ciNo;
                        data.bfSrnPmt.diNo = options.akDta.diNo;
                        data.bfSrnPmt.tcccDc = options.akDta.tcccDc;
                        data.bfSrnPmt.mbzNoC = options.akDta.mbzNoC;
                        data.bfSrnPmt.mmtExno = options.akDta.mmtExno;
                        data.bfSrnPmt.mtlno = options.akDta.mtlno;

                        //onlId는 선택항목으로 입력 될 수 있음
                        data.bfSrnPmt.onlId = options.akDta.onlId;
                    }

                } else {
                    throw _isDebug ? "올바른 akUrl이 아닙니다." : "ERROR";
                }
            }

            //제휴사 전달메시지가 있는 경우 세팅
            if (!SsoDataUtil.isEmpty(options.ccoDlyMsg)) {
                data.ccoDlyMsg = options.ccoDlyMsg;
            }

            //로그인 후처리 모드 설정 ('0' : 라이브러리 후처리 프로세스 사용, '1' : 제휴사가 후처리 프로세스 작성)
            if (SsoDataUtil.isEmpty(options.aftPcMd)) {
                if (_isDebug) {
                    console.log("[callEsyJoinCtfLogin] aftPcMd : null");
                    console.log("[callEsyJoinCtfLogin] aftPcMd 기본 값 '0' 세팅");
                }
                data.aftPcMd = '0'; // 후처리 옵션 기본값 세팅
            } else if (options.aftPcMd == '0' || options.aftPcMd == '1') {
                data.aftPcMd = options.aftPcMd;
            } else {
                throw _isDebug ? "aftPcMd는 '0' 또는 '1' 값이어야 합니다." : "ERROR";
            }

            //로그인 callback함수 세팅
            if (SsoDataUtil.isFunction(options.callback)) {
                //로그인 요청의 callback함수 세팅
                if (data.aftPcMd == '0') {
                    // 라이브러리 자체 후처리모드인 경우 후처리 callback 세팅 메소드 호출
                    _callback = _self._setAftPcMdCallback(options);
                } else {
                    // 라이브러리 자체 후처리를 사용하지 않는 경우, 후처리 프로세스는 제휴사의 callback 함수에 있어야 함.
                    _callback = options.callback;
                }
            } else {
                throw _isDebug ? 'callback 함수는 필수 항목입니다.' : 'ERROR';
            }

            //리턴 URL & Http Method 세팅
            if (data.aftPcMd == '0' && _srnOpt.opMd == '0') {
                if (SsoDataUtil.isEmpty(options.rturUrl)) {
                    throw _isDebug ? 'akDta.aftPcMd가 "0"이고, srnOpt.opMd가 "0"일 때 rturUrl은 필수 항목입니다.' : 'ERROR';
                }
                if (SsoDataUtil.isEmpty(options.rturUrlCaloMethd)) {
                    throw _isDebug ? 'akDta.aftPcMd가 "0"이고, srnOpt.opMd가 "0"일 때 rturUrlCaloMethd는 필수 항목입니다.' : 'ERROR';
                }
                if (!_self._urlChecker(options.rturUrl)) {
                    throw _isDebug ? 'rturUrl은 프로토콜을 포함한 전체 URL 혹은 스키마를 입력해야합니다.' : 'ERROR';
                }
                data.rturUrl = options.rturUrl;
                data.rturUrlCaloMethd = options.rturUrlCaloMethd;
            }

            //2019-10-08 add. 로그아웃분리여부 파라미터 추가
            if (options.lgoSepYn != 'Y') {
                data.bfSrnPmt.lgoSepYn = 'N';
            } else {
                data.bfSrnPmt.lgoSepYn = 'Y';
            }
            //==========================================

            data.bfSrnPmt = JSON.stringify(data.bfSrnPmt);

            if (_isDebug) {
                console.log("[callEsyJoinCtfLogin] data		: " + JSON.stringify(data));
            }

            //브라우저 캐시 체크 및 호출
            if (navigator.cookieEnabled == false) {
                //브라우저 쿠키차단 설정 시 제휴사 응답데이터 세팅
                var rspDta = {
                    rspClac: 'A',
                    rspC: '99',
                    rspDtc: '997',
                    rspMsgCn: '브라우저 쿠키 차단 상태이므로 정상 동작하지 않습니다.'
                }
                _self._removeCallback(rspDta);
            } else {
                //hidden iframe 생성 및 호출
                _self._hiddenIframeOpen(_domain + _apiUrl.callEsyJoinCtfLogin, data);
            }

            if (_isDebug) {
                console.log("[callEsyJoinCtfLogin] API End");
            }
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - 2단계 인증 토큰으로 로그인을 요청하는 메소드
         */
        this.callLv2CtfLogin = function(options) {
            if (_isDebug) {
                console.log("[callLv2CtfLogin] API start");
            }
            //==========필수 항목 체크 및 데이터 세팅 start==========
            var data = {};
            //URL 체크
            if (SsoDataUtil.isEmpty(options.akUrl)) {
                throw _isDebug ? 'akUrl는 필수 항목입니다.' : 'ERROR';
            }
            //요청데이터 체크
            if (SsoDataUtil.isEmpty(options.akDta)) {
                throw _isDebug ? 'akDta는 필수 항목입니다.' : 'ERROR';
            } else {
                //통합회원제 양식에 맞게 세팅
                data.bfSrnPmt = {};
                data.bfSrnPmt.akUrl = options.akUrl;

                //2단계 인증 로그인은 로그인유형구분코드를 세팅하지 않음

                if (data.bfSrnPmt.akUrl == _serviceUrl.callLv2CtfLogin) {
                    // ID/PSWD 로그인 일 때, 필수값 검증 및 세팅
                    if (SsoDataUtil.isEmpty(options.akDta.lv2CtfTkn)) {
                        throw _isDebug ? 'akDta.lv2CtfTkn는 필수 항목입니다.' : 'ERROR';
                    }

                    data.bfSrnPmt.lv2CtfTkn = options.akDta.lv2CtfTkn;
                } else {
                    throw _isDebug ? "올바른 akUrl이 아닙니다." : "ERROR";
                }
            }

            //제휴사 전달메시지가 있는 경우 세팅
            if (!SsoDataUtil.isEmpty(options.ccoDlyMsg)) {
                data.ccoDlyMsg = options.ccoDlyMsg;
            }

            //로그인 후처리 모드 설정 ('0' : 라이브러리 후처리 프로세스 사용, '1' : 제휴사가 후처리 프로세스 작성)
            if (SsoDataUtil.isEmpty(options.aftPcMd)) {
                if (_isDebug) {
                    console.log("[callLv2CtfLogin] aftPcMd : null");
                    console.log("[callLv2CtfLogin] aftPcMd 기본 값 '0' 세팅");
                }
                data.aftPcMd = '0'; // 후처리 옵션 기본값 세팅
            } else if (options.aftPcMd == '0' || options.aftPcMd == '1') {
                data.aftPcMd = options.aftPcMd;
            } else {
                throw _isDebug ? "aftPcMd는 '0' 또는 '1' 값이어야 합니다." : "ERROR";
            }

            //로그인 callback함수 세팅
            if (SsoDataUtil.isFunction(options.callback)) {
                //로그인 요청의 callback함수 세팅
                if (data.aftPcMd == '0') {
                    // 라이브러리 자체 후처리모드인 경우 후처리 callback 세팅 메소드 호출
                    _callback = _self._setAftPcMdCallback(options);
                } else {
                    // 라이브러리 자체 후처리를 사용하지 않는 경우, 후처리 프로세스는 제휴사의 callback 함수에 있어야 함.
                    _callback = options.callback;
                }
            } else {
                throw _isDebug ? 'callback 함수는 필수 항목입니다.' : 'ERROR';
            }

            //리턴 URL & Http Method 세팅
            if (data.aftPcMd == '0' && _srnOpt.opMd == '0') {
                if (SsoDataUtil.isEmpty(options.rturUrl)) {
                    throw _isDebug ? 'akDta.aftPcMd가 "0"이고, srnOpt.opMd가 "0"일 때 rturUrl은 필수 항목입니다.' : 'ERROR';
                }
                if (SsoDataUtil.isEmpty(options.rturUrlCaloMethd)) {
                    throw _isDebug ? 'akDta.aftPcMd가 "0"이고, srnOpt.opMd가 "0"일 때 rturUrlCaloMethd는 필수 항목입니다.' : 'ERROR';
                }
                if (!_self._urlChecker(options.rturUrl)) {
                    throw _isDebug ? 'rturUrl은 프로토콜을 포함한 전체 URL 혹은 스키마를 입력해야합니다.' : 'ERROR';
                }
                data.rturUrl = options.rturUrl;
                data.rturUrlCaloMethd = options.rturUrlCaloMethd;
            }

            if (options.lgoSepYn != 'Y') {
                data.bfSrnPmt.lgoSepYn = 'N';
            } else {
                data.bfSrnPmt.lgoSepYn = 'Y';
            }
            //==========================================

            data.bfSrnPmt = JSON.stringify(data.bfSrnPmt);

            if (_isDebug) {
                console.log("[callLv2CtfLogin] data		: " + JSON.stringify(data));
            }
            //==========필수 항목 체크 및 데이터 세팅 end==========

            if (navigator.cookieEnabled == false) {
                //브라우저 쿠키차단 설정 시 제휴사 응답데이터 세팅
                var rspDta = {
                    rspClac: 'A',
                    rspC: '99',
                    rspDtc: '997',
                    rspMsgCn: '브라우저 쿠키 차단 상태이므로 정상 동작하지 않습니다.'
                }
                _self._removeCallback(rspDta);
            } else {
                //hidden iframe 생성 및 호출
                _self._hiddenIframeOpen(_domain + _apiUrl.callLogin, data);
            }

            if (_isDebug) {
                console.log("[callLv2CtfLogin] API end");
            }
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * 생체인증 결제키 발급 API
         * - APP에서 생체인증 성공 후 호출 
         */
        this.callFidoCtfSttKeyIsu = function(options) {
            if (SsoDataUtil.isEmpty(options)) {
                throw _isDebug ? "options은 필수 값입니다." : "ERROR";
            }

            if (SsoDataUtil.isEmpty(options.akUrl)) {
                throw _isDebug ? "akUrl은 필수 값입니다." : "ERROR";
            } else if (options.akUrl != _serviceUrl.callFidoCtfSttKeyIsu) {
                throw _isDebug ? "akUrl이 올바르지 않습니다." : "ERROR";
            }

            if (SsoDataUtil.isEmpty(options.sttDc)) {
                throw _isDebug ? "sttDc는 필수 값입니다." : "ERROR";
            }

            if (SsoDataUtil.isFunction(options.callback)) {
                //제휴사 자체 후처리모드용 callback 세팅
                _callback = options.callback;
            } else {
                throw _isDebug ? "callback이 함수가 아닙니다." : "ERROR";
            }

            var langCode = _langCode || "";
            var rturUrl = SsoDataUtil.trim(options.rturUrl) || window.locaion.href;
            var fnNm = "SsoInterfaceCallback"; //앱에서 호출할 JS 함수
            var ccoDlyMsg = SsoDataUtil.isEmpty(options.ccoDlyMsg) ? "" : options.ccoDlyMsg;
            if (_iosMobYn) { //ios
                //URL 스키마 세팅 후 get방식으로 호출
                var url_scheme = "lpointSsoApp://requestFidoCtfSttKeyIsu?";
                url_scheme += "domain=" + _domain;
                url_scheme += "&bizUrl=" + options.akUrl;
                url_scheme += "&ccoSiteNo=" + _ccoSiteNo;
                url_scheme += "&langCode=" + langCode;
                url_scheme += "&acesTkn=" + _acesTkn;
                url_scheme += "&rturUrl=" + rturUrl;
                url_scheme += "&sttDc=" + options.sttDc;
                url_scheme += "&ccoDlyMsg=" + ccoDlyMsg;
                url_scheme += "&callback=" + fnNm;
                _self._callIOSScheme(url_scheme);
            } else if (window.ssoJsItf) { //android
                //안드로이드 인터페이스 메소드 호출
                window.ssoJsItf.requestFidoCtfSttKeyIsu(_domain, options.akUrl, _ccoSiteNo, langCode, _acesTkn, rturUrl, options.sttDc, ccoDlyMsg, fnNm);
            } else { //etc
                //웹앱이 아닌경우 해당 기능을 지원하지 않는다.
                return;
            }

        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * 생체인증 API
         * - APP에서 생체인증 성공 후 호출 
         */
        this.callFidoCtf = function(options) {
            if (SsoDataUtil.isEmpty(options)) {
                throw _isDebug ? "options은 필수 값입니다." : "ERROR";
            }

            if (SsoDataUtil.isEmpty(options.akUrl)) {
                throw _isDebug ? "akUrl은 필수 값입니다." : "ERROR";
            } else if (options.akUrl != _serviceUrl.callFidoCtf) {
                throw _isDebug ? "akUrl이 올바르지 않습니다." : "ERROR";
            }

            if (SsoDataUtil.isFunction(options.callback)) {
                //제휴사 자체 후처리모드용 callback 세팅
                _callback = options.callback;
            } else {
                throw _isDebug ? "callback이 함수가 아닙니다." : "ERROR";
            }

            var langCode = _langCode || "";
            var rturUrl = SsoDataUtil.trim(options.rturUrl) || window.locaion.href;
            var fnNm = "SsoInterfaceCallback"; //앱에서 호출할 JS 함수
            var ccoDlyMsg = SsoDataUtil.isEmpty(options.ccoDlyMsg) ? "" : options.ccoDlyMsg;
            if (_iosMobYn) { //ios
                //URL 스키마 세팅 후 get방식으로 호출
                var url_scheme = "lpointSsoApp://requestFidoCtf?";
                url_scheme += "domain=" + _domain;
                url_scheme += "&bizUrl=" + options.akUrl;
                url_scheme += "&ccoSiteNo=" + _ccoSiteNo;
                url_scheme += "&langCode=" + langCode;
                url_scheme += "&acesTkn=" + _acesTkn;
                url_scheme += "&rturUrl=" + rturUrl;
                url_scheme += "&ccoDlyMsg=" + ccoDlyMsg;
                url_scheme += "&callback=" + fnNm;
                _self._callIOSScheme(url_scheme);
            } else if (window.ssoJsItf) { //android
                //안드로이드 인터페이스 메소드 호출
                window.ssoJsItf.requestFidoCtf(_domain, options.akUrl, _ccoSiteNo, langCode, _acesTkn, rturUrl, ccoDlyMsg, fnNm);
            } else { //etc
                //웹앱이 아닌경우 해당 기능을 지원하지 않는다.
                return;
            }
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - IOS 앱 라이브러리 스키마 호출을 위한 메소드
         */
        this._callIOSScheme = function(urlScheme) {
            var _ifrm = $('<iframe></iframe>');
            _ifrm.attr('src', urlScheme);
            _ifrm.appendTo('body');
            _ifrm.remove();
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - 파라미터로 입력받은 URL이 전체 URL인지(프로토콜이 명시된) 확인
         * @param url : URL/스키마
         * @return boolean : true (프로토콜이 포함된 전체 URL/스키마)
         * 					 false (프로토콜이 없는 URL)
         */
        this._urlChecker = function(url) {
            if (url.indexOf("://") == -1) {
                //프로토콜 혹은 스키마 부분이 누락됬음을 의미
                return false;
            } else {
                //프로토콜 혹은 스키마 부분이 포함된 전체 URL임을 의미
                return true;
            }
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - 라이브러리 내 사용하는 alert 메소드
         */
        this._fnAlert = function(rspC, rspDtc) {
            var langCode = _langCode || 'default'; //langCode가 null/undefined/'' 이면 'default'로 세팅
            var rspFullCode = rspC + rspDtc;
            var msg = _alertMsg[rspFullCode][langCode]; //응답코드 + 응답 상세코드로 메시지를 찾고, langCode에 따라 다국어처리

            //javascript 기본 alert
            alert(msg);
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - SSO로그인과 자동로그인 요청
         */
        this._callSsoLogin = function(options, isParameter) {
            if (_isDebug) {
                console.log("[callSsoLogin] API start");
                console.log("[callSsoLogin] options : " + JSON.stringify(options));
            }
            //==========ssoTkn을 파라미터로 넘겨주는 경우에만 후처리 작동=============
            //웹앱 구현상 토큰조회를 우선처리하므로, 파라미터로 ssoTkn을 넘겨주는 케이스와 구분이 되지 않음
            var _isParameter = false;
            if (!SsoDataUtil.isEmpty(isParameter) && typeof(isParameter) === "boolean") {
                //ssoTkn이 options안에 포함되서 넘어온 경우
                _isParameter = isParameter;
            }
            //==========ssoTkn을 파라미터로 넘겨주는 경우에만 후처리 작동=============

            //==========필수값 검증 및 데이터 세팅 start=============
            //요청 URL 세팅
            var data = {};
            //제휴사 전달 메시지 존재하면 세팅
            data.ccoDlyMsg = options.ccoDlyMsg;

            //SSO로그인 후처리모드 세팅
            if (SsoDataUtil.isEmpty(options.aftPcMd)) {
                if (_isDebug) {
                    console.log("[callSsoLogin] aftPcMd : " + options.aftPcMd);
                    console.log("[callSsoLogin] aftPcMd 기본 값 '0' 세팅");
                }
                data.aftPcMd = '0'; // 후처리 옵션 기본값 세팅
            } else if (options.aftPcMd == '0' || options.aftPcMd == '1') {
                data.aftPcMd = options.aftPcMd;
            } else {
                throw _isDebug ? "aftPcMd는 '0' 또는 '1' 값이어야 합니다." : "ERROR";
            }

            //리턴 URL & Http Method 세팅
            if (data.aftPcMd == '0' && _srnOpt.opMd == '0') {
                if (SsoDataUtil.isEmpty(options.rturUrl)) {
                    throw _isDebug ? 'akDta.aftPcMd가 "0"이고, srnOpt.opMd가 "0"일 때 rturUrl은 필수 항목입니다.' : 'ERROR';
                }
                if (SsoDataUtil.isEmpty(options.rturUrlCaloMethd)) {
                    throw _isDebug ? 'akDta.aftPcMd가 "0"이고, srnOpt.opMd가 "0"일 때 rturUrlCaloMethd는 필수 항목입니다.' : 'ERROR';
                }
                if (!_self._urlChecker(options.rturUrl)) {
                    throw _isDebug ? 'rturUrl은 프로토콜을 포함한 전체 URL 혹은 스키마를 입력해야합니다.' : 'ERROR';
                }
                data.rturUrl = options.rturUrl;
                data.rturUrlCaloMethd = options.rturUrlCaloMethd;
            }

            //callback 함수 세팅
            if (SsoDataUtil.isFunction(options.callback)) {
                if (data.aftPcMd == '0' && _isParameter) {
                    //파라미터로 ssoTkn이 넘어온 경우에만 후처리 적용을 위해 callback을 감싼다
                    _callback = _self._setAftPcMdCallback(options, _isParameter);
                } else {
                    _callback = options.callback;
                }
            } else {
                throw _isDebug ? 'callback 함수는 필수 항목입니다.' : 'ERROR';
            }

            data.bfSrnPmt = {};
            if (SsoDataUtil.isEmpty(options.akUrl)) {
                throw _isDebug ? 'akUrl은 필수 항목입니다.' : 'ERROR';
            } else {
                data.bfSrnPmt.akUrl = options.akUrl;
                //2018-11-09 modified. akDta 항목 nullable로 변경(넘겨줄 데이터가 없을 때 akDta를 안넣어서 오류가 난다는 컴플레인 들어옴)
                //요청 데이터 세팅				
                if (options.akUrl == _serviceUrl.callSsoLoginSsoTkn) {
                    //SSO Login
                    //파라미터로 ssoTkn을 넘겼으면 세팅함
                    data.bfSrnPmt.lgnTpDc = '2';
                    if (!SsoDataUtil.isEmpty(options.akDta) && !SsoDataUtil.isEmpty(options.akDta.ssoTkn)) {
                        data.bfSrnPmt.ssoTkn = options.akDta.ssoTkn;
                    } else {
                        if (_isDebug) {
                            console.log("[callSsoLogin] 저장소 및 파라미터 모두 ssoTkn이 없음");
                        }
                        //2018-11-09 add. ssoTkn이 저장소에도 파라미터에도 없으면 SSO로그인 서비스 호출자체를 하지 않는다
                        //ssoTkn이 없으면 callback 함수에 오류응답 세팅하여 파라미터로 전달
                        var rspDta = {
                            rspClac: 'E',
                            rspC: '44',
                            rspDtc: '425',
                            rspMsgCn: '유효한 토큰정보가 존재하지 않습니다'
                        };
                        _self._removeCallback(rspDta);
                        return;
                    }
                } else if (options.akUrl == _serviceUrl.callSsoLoginRnwTkn) {
                    //Auto Login
                    data.bfSrnPmt.lgnTpDc = '3';
                    if (!SsoDataUtil.isEmpty(options.akDta)) {
                        //rnwTkn이 없으면 오류
                        if (SsoDataUtil.isEmpty(options.akDta.rnwTkn)) {
                            throw _isDebug ? "akDta.rnwTkn은 필수 항목입니다." : "ERROR";
                        } else {
                            data.bfSrnPmt.rnwTkn = options.akDta.rnwTkn;
                        }
                        //2018-11-01 자동로그인 요청데이터에 자동로그인등록일자 항목 추가
                        //14자리 숫자로만 구성된 문자열 (ex) 20181101144152 yyyyMMddHH24miss
                        //(방어로직)자동로그인등록일자 항목에 숫자를 제외한 항목 제거 2018-11-07 added
                        options.akDta.autoLgnRgDtti = SsoDataUtil.toNumberString(options.akDta.autoLgnRgDtti);
                        if (SsoDataUtil.isEmpty(options.akDta.autoLgnRgDtti)) {
                            throw _isDebug ? "akDta.autoLgnRgDtti는 필수 항목입니다." : "ERROR";
                        } else if (options.akDta.autoLgnRgDtti.length != 14) {
                            throw _isDebug ? "akDta.autoLgnRgDtti는 14자리 숫자이어야 합니다.(yyyyMMddHH24miss)" : "ERROR";
                        } else {
                            data.bfSrnPmt.autoLgnRgDtti = options.akDta.autoLgnRgDtti;
                        }
                    }
                } else if (options.akUrl == _serviceUrl.callSsoGroupLogin) { //2019.05.03 YH. SSO 그룹 로그인인 경우, 필수값은 callSsoGroupLogin에서 검증
                    if (!SsoDataUtil.isEmpty(options.akDta)) {
                        data.bfSrnPmt.lgnTpDc = '2';
                        data.bfSrnPmt.akUrl = options.akUrl; //2019.05.07 박재홍. SSO 그룹 로그인 URL 항목 누락된 것 추가
                        if (!SsoDataUtil.isEmpty(options.akDta) && !SsoDataUtil.isEmpty(options.akDta.ssoTkn)) {
                            data.bfSrnPmt.ssoTkn = options.akDta.ssoTkn;
                        }
                        if (!SsoDataUtil.isEmpty(options.akDta) && !SsoDataUtil.isEmpty(options.akDta.siteGrpNo)) {
                            data.bfSrnPmt.siteGrpNo = options.akDta.siteGrpNo;
                        }
                    }
                } else {
                    throw _isDebug ? '올바른 akUrl이 아닙니다.' : 'ERROR';
                }
            }

            if (_isDebug) {
                console.log("[callSsoLogin] data 		: " + JSON.stringify(data));
            }

            //2019-10-08 add. 로그아웃분리여부 파라미터 추가
            if (options.lgoSepYn != 'Y') {
                data.bfSrnPmt.lgoSepYn = 'N';
            } else {
                data.bfSrnPmt.lgoSepYn = 'Y';
            }
            //==========================================

            data.bfSrnPmt = JSON.stringify(data.bfSrnPmt);
            //=============필수값 검증 및 데이터 세팅 end==================

            //2018-11-06 add. 브라우저 쿠키차단 설정에 따른 프로세스 분기
            if (navigator.cookieEnabled == false) {
                //브라우저 쿠키차단 설정 시 제휴사 응답데이터 세팅
                var rspDta = {
                    rspClac: 'A',
                    rspC: '99',
                    rspDtc: '997',
                    rspMsgCn: '브라우저 쿠키 차단 상태이므로 정상 동작하지 않습니다.'
                }
                _self._removeCallback(rspDta);
            } else {
                //hidden iframe 호출
                _self._hiddenIframeOpen(_domain + _apiUrl.callSsoLogin, data);
            }

            if (_isDebug) {
                console.log("[callSsoLogin] API end");
            }
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - 라이브러리 자체 후처리시 호출할 callback함수 세팅하는 부분
         * @param options : 제휴사가 입력한 options bypass함
         */
        this._setAftPcMdCallback = function(options, ssoLoginWithParameter) {
            var aftPcMdCallback = function(rspDta) {
                var param = {};

                //후처리가 끝나고 필요한 리턴URL과 메소드를 세팅
                if (_srnOpt.opMd == '0') {
                    //rturUrl과 rturUrlCaloMethd 만 세팅해준다.
                    param.rturUrl = options.rturUrl;
                    param.rturUrlCaloMethd = options.rturUrlCaloMethd;
                } else if (_srnOpt.opMd == '2') {
                    //popup모드일 경우 rturUrl을 popRturUrl로 세팅
                    param.rturUrl = _srnOpt.popRturUrl;
                    param.rturUrlCaloMethd = 'post';
                }
                //후처리 여부와 상관없이 제휴사 callback을 저장한다.
                _ccoCallback = options.callback;
                //로그인 요청에 대한 응답데이터를 param에 세팅하여 넘겨준다.
                param.rspDta = rspDta;

                //ssoTkn을 파라미터로 넘겨 로그인을 처리하는 경우 구분값세팅
                if (!SsoDataUtil.isEmpty(ssoLoginWithParameter) && typeof(ssoLoginWithParameter) === "boolean") {
                    param.ssoLoginWithParameter = ssoLoginWithParameter;
                }

                _self._callScreenInResponse(param, options);

            }

            //생성한 후처리 callback함수 객체를 리턴
            return aftPcMdCallback;
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - 팝업 띄우는 메소드
         */
        this._createPopup = function(width, height) {
            try {
                var popup_title = "sso_popup";
                //2018-12-07 modified by MP01126. 현업 추가 요건사항 (팝업 호출 사이즈 지정 가능하게 수정 및 팝업 위치 화면 중앙으로 이동)
                var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : window.screenX;
                var dualScreenTop = window.screenTop != undefined ? window.screenTop : window.screenY;
                var parentWidth = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
                var parentHeight = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;
                var popWidth = width || 1160;
                var popHeight = height || 850;
                var left = ((parentWidth / 2) - (popWidth / 2)) + dualScreenLeft;
                var top = ((parentHeight / 2) - (popHeight / 2)) + dualScreenTop;
                var popCssText = eval("'width=" + popWidth +
                    ",height=" + popHeight +
                    ",left=" + left +
                    ",top=" + top +
                    ",scrollbars=1" +
                    ",resizable=1'");
                var popup;
                if (navigator.userAgent.match(/iP(hone|ad|od)|Android/gi) != null) {
                    popup = window.open('about:blank', popup_title);
                } else {
                    popup = window.open('about:blank', popup_title, popCssText);
                }

                popup.focus();
            } catch (e) { //popup 생성 실패!
                throw _isDebug ? e : 'ERROR';
            }
            return popup_title;
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - 응답상세코드에 따른 화면호출 메소드
         */
        this._callScreenInResponse = function(param, options) {
            var ssoLoginWithParameter = param.ssoLoginWithParameter;
            var rspDta = param.rspDta;
            var rspFullCode = rspDta.rspC + rspDta.rspDtc;
            var akUrl = _noticePageUrl[rspFullCode]; //응답코드+응답상세코드 조합으로 실패 안내페이지 URL 파싱
            var data = param;

            //화면 연동시 불필요한 rspDta, ssoLoginWithParameter 항목 삭제
            delete data['rspDta'];
            delete data['ssoLoginWithParameter'];

            if (!SsoDataUtil.isEmpty(ssoLoginWithParameter) && typeof(ssoLoginWithParameter) === "boolean") {
                //ssoLoginWithParameter : callSsoLogin API에서 파라미터로 ssoTkn이 넘어올때만 true로 세팅됨
                //						  해당 항목이 false면 제휴사 callback만 호출한다
                if (ssoLoginWithParameter) {
                    //ssoTkn이 파라미터로 넘어올때
                    if (rspDta.rspC == '44' && (rspDta.rspDtc == '419' || rspDta.rspDtc == '420')) {
                        //이중회원사 약관 미동의 & 이중회원사 약관 미동의(14세 미만)
                        data.akDta = rspDta;
                        data.akUrl = akUrl;
                        _self.callScreen(data);
                        return;
                    } else {
                        _self._removeCcoCallback(rspDta);
                        return;
                    }
                } else {
                    _self._removeCcoCallback(rspDta);
                    return;
                }
            } else {
                //기본 후처리 프로세스
                if (rspDta.rspClac == 'S') {
                    // 응답데이터에 접근토큰이 존재하는 경우 라이브러리 내부 변수에 접근토큰 저장
                    if (!SsoDataUtil.isEmpty(rspDta.acesTkn)) {
                        _acesTkn = rspDta.acesTkn;
                    }
                    if ((rspDta.rspC == '00' && rspDta.rspDtc == '401' && rspDta.pswdChCmpExpsYn == 'Y') ||
                        (rspDta.rspC == '00' && rspDta.rspDtc == '403' && rspDta.choPrvAgCmpExpsYn == 'Y')) { // 비밀번호 변경 캠페인 또는 선택약관추가수집 캠페인
                        //무조건 popup처리 되고 제휴사 리턴 URL 호출이 필요 없으므로 제거
                        delete data['rturUrl'];
                        delete data['rturUrlCaloMethd'];

                        var _originOpMd = null;
                        _originOpMd = _srnOpt.opMd;
                        _srnOpt.opMd = '2';
                    } else {
                        _self._removeCcoCallback(rspDta);
                        return;
                    }
                } else if (rspDta.rspClac == 'E') {
                    //에러케이스 중에서 후처리 케이스를 확인한다.
                    if (rspDta.rspC == '44' && rspDta.rspDtc == '423') { // 소셜로그인 매핑 정보가 없음
                        //제휴사가 넘겨준 소셜정보를 세팅해야 함
                        data.akDta = options.akDta;
                    } else if (rspDta.rspC == '44' && rspDta.rspDtc == '705') {
                        if (_srnOpt.opMd == '2' && _urEvnmtDc == '2') {
                            //웹뷰이고 화면호출방식이 팝업이면 등록안내 페이지로 이동하지 않고 제휴사 callback 호출
                            _self._removeCcoCallback(rspDta);
                            return;
                        } else {
                            //그 외의 케이스인 경우, 해당 오류가 나면 간편인증 등록안내페이지로 이동한다.
                            //응답데이터를 채널 data로 세팅
                            data.akDta = rspDta;
                        }
                    } else if (!SsoDataUtil.isEmpty(akUrl)) {
                        //후처리 URL이 매핑되는 경우는 응답데이터 전체를 요청데이터로 안내페이지에 전달
                        data.akDta = rspDta;
                    } else {
                        //후처리 URL이 매핑되지 않는 경우는 default 안내페이지로 이동한다.
                        akUrl = _noticePageUrl['default_error'];
                        data.akDta = rspDta;
                    }
                } else if (rspDta.rspClac == 'A') {
                    if (rspDta.rspC == '44' && rspDta.rspDtc == '401') {
                        //아이디, 비밀번호가 일치하지 않음
                        _self._fnAlert(rspDta.rspC, rspDta.rspDtc);
                    } else if (rspDta.rspC == '00' && rspDta.rspDtc == '206') {
                        //간편인증등록성공
                        //앱 SDK에서 alert를 띄우므로 여기서 alert를 띄우지 않는다.
                        if (_srnOpt.opMd == '1') {
                            //iframe을 감추는 함수 호출
                            _srnOpt.hideFunc();
                        }
                    } else if (rspDta.rspC == '99' && rspDta.rspDtc == '997') {
                        //브라우저 쿠키차단 설정되어 있음
                        _self._fnAlert(rspDta.rspC, rspDta.rspDtc);
                    }
                    //제휴사 callback으로 응답데이터 반환
                    _self._removeCcoCallback(rspDta);
                    return;
                } else {
                    // 후처리가 필요한 케이스들을 제외하고 응답데이터를 callback(제휴사제공)으로 넘겨준다
                    _self._removeCcoCallback(rspDta);
                    return;
                }

                // 화면 URL 값 저장
                data.akUrl = akUrl;

                /* 상단으로 이동 (1106 line)
                // 응답데이터에 접근토큰이 존재하는 경우 라이브러리 내부 변수에 접근토큰 저장
                if(!SsoDataUtil.isEmpty(rspDta.acesTkn)) {
                	_acesTkn = rspDta.acesTkn;	
                }
                */

                _self.callScreen(data);

                if ((rspDta.rspC == '00' && rspDta.rspDtc == '401' && rspDta.pswdChCmpExpsYn == 'Y') ||
                    (rspDta.rspC == '00' && rspDta.rspDtc == '403' && rspDta.choPrvAgCmpExpsYn == 'Y')) {
                    //비밀번호 변경캠페인은 팝업 호출과 동시에 제휴사 callback을 실행한다.
                    //선택약관 추가수집 캠페인은 팝업 호출과 동시에 제휴사 callback을 실행한다.
                    _self._removeCcoCallback(rspDta);
                    // 기존 opMd로 라이브러리 변수 값 복구
                    _srnOpt.opMd = _originOpMd;
                }
            }
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - hidden iframe 생성
         */
        this._hiddenIframeOpen = function(url, data) {
            if (_isDebug) {
                console.log('open=' + url);
            }

            var _data = data;
            if (SsoDataUtil.isEmpty(_data)) {
                _data = {};
            } else if (SsoDataUtil.isJsonStr(_data)) {
                _data = JSON.parse(_data);
            }

            //라이브러리 초기화시 세팅값 중에 JWT payload에 포함되야 하는 값 추가
            _data.opMd = _srnOpt.opMd;
            _data.langCode = _langCode;
            _data.srnHeaderExpsYn = _srnOpt.srnHeaderExpsYn;
            _data.urEvnmtDc = _urEvnmtDc;
            //2018-11-13 add. 요청제휴사도메인정보 추가
            _data.akCcoDmn = document.domain;

            var target = 'sso_iframe';
            $('body').append(_hiddenIframe.tag);

            //JSON -> JWT
            _data = SsoEncryptUtil.createJwt(_clntEncKey, _signKey, _akHeader, _data);

            _self._submit(url, _data, target);
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - JSON여부 확인
         */
        this._submit = function(url, data, target) {

            //기존 생성 form과 충돌방지를 위해 제거
            $('form[name="sso_frm"]').remove();
            //동적 form 생성
            var form = $('<form></form>');
            form.attr('name', 'sso_frm');
            form.attr('action', url);
            form.attr('method', 'post');
            form.appendTo('body');
            if (!SsoDataUtil.isEmpty(target)) {
                form.attr('target', target);
            }
            // hidden data 세팅
            if (!SsoDataUtil.isEmpty(data)) {
                var formDta = $("<input name='jwt' type='hidden' value='" + data + "'/>");
                form.append(formDta);
            }
            form.submit();

        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - 파라미터로 받아 라이브러리 변수에 저장해둔 callback 호출
         */
        this._getCcoCallback = function(rspDta) {
            if (SsoDataUtil.isFunction(_ccoCallback)) {
                var response = null;
                try {
                    if (!SsoDataUtil.isEmpty(rspDta)) {
                        response = SsoEncryptUtil.extractJwtPayload(_clntEncKey, rspDta);
                    }
                } catch (e) {
                    response = rspDta;
                }
                _self._removeCcoCallback(response);
                return;
            } else {
                return;
            }
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - 파라미터로 받아 라이브러리 변수에 저장해둔 callback 호출(간편 로그인에서 사용)
         */
        this._getCallback = function(rspDta) {
            if (_srnOpt.opMd == '1' && SsoDataUtil.isFunction(_srnOpt.hideFunc)) {
                _srnOpt.hideFunc();
            }
            if (SsoDataUtil.isFunction(_callback)) {
                var response = {};

                if (SsoDataUtil.isJsonStr(rspDta)) {
                    response = JSON.parse(rspDta);
                } else {
                    response = rspDta;
                }
                _self._removeCallback(response);
                return;
            } else if (SsoDataUtil.isFunction(_ccoCallback)) {
                var response = {};

                if (SsoDataUtil.isJsonStr(rspDta)) {
                    response = JSON.parse(rspDta);
                } else {
                    response = rspDta;
                }
                _self._removeCcoCallback(response);
                return;
            } else {
                return;
            }
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - API callback 호출 및 초기화
         * @param data : API callback에 파라미터로 넘길 데이터
         */
        this._removeCallback = function(data) {
            //2018-12-12 modified by MP01126. 제휴사 callback을 호출하고 callback 초기화 하는 시점이 한번에 실행되지 않아
            //버그 발생하여 수정함

            //제휴사 callback을 임시 callback 변수에 저장 후 기존에 저장한 callback을 선 제거 후 호출
            var tmpCallback = _callback;
            _callback = null;
            tmpCallback(data);
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - API callback 호출 및 초기화(ccoCallback은 라이브러리 자체 후처리때 제휴사 callback을 저장하는 변수)
         * @param data : API callback에 파라미터로 넘길 데이터
         */
        this._removeCcoCallback = function(data) {
            //2018-12-12 modified by MP01126. 제휴사 callback을 호출하고 callback 초기화 하는 시점이 한번에 실행되지 않아
            //버그 발생하여 수정함

            //제휴사 callback을 임시 callback 변수에 저장 후 기존에 저장한 callback을 선 제거 후 호출
            var tmpCallback = _ccoCallback;
            _ccoCallback = null;
            tmpCallback(data);
        }

        /**
         * 제휴사 제공 Web API 라이브러리
         * - message event handler 함수
         */
        this._eventHandler = function(e) {
            if (_isDebug) {
                console.log("[Message Event] Event Handling start");
                console.log("[Message Event] Sender Orign : " + e.origin);
                console.log("[Message Event] Domain       : " + _domain);
            }

            if (e.origin == _domain) { //통합회원제 URL

                //생성된 hidden iframe 제거
                _hiddenIframe.close();

                var jsonData = null;
                var _data = null;
                if (SsoDataUtil.isJsonStr(e.data)) {
                    jsonData = JSON.parse(e.data);
                }
                _data = jsonData.data;

                if (_isDebug) {
                    console.log("[Message Event] eventCode    : " + jsonData.eventCode);
                }

                if (jsonData.eventCode == 'SSOTKN') { //ssoTkn조회(data가 ssoTkn만 있으므로 암복호화 하지 않음)
                    //callback처리
                    if (SsoDataUtil.isFunction(_ccoCallback)) {
                        _self._removeCcoCallback(_data);
                    }
                } else if (jsonData.eventCode == 'API_ERROR') {
                    //2018-11-20 add. API 호출중 hidden iframe 에러 발생 시, 에러 응답 반환
                    if (SsoDataUtil.isFunction(_callback)) {
                        _self._removeCallback(_data);
                    }
                } else if (jsonData.eventCode == 'RESPONSE') { //hidden iframe
                    try {
                        if (!SsoDataUtil.isEmpty(_data)) {
                            _data = SsoEncryptUtil.extractJwtPayload(_clntEncKey, _data);
                        }
                        //2018-11-12 add. 연동된 제휴사들의 정보가 담긴 List를 제거
                        if (!SsoDataUtil.isEmpty(_data['ccoLgoList'])) {
                            delete _data['ccoLgoList'];
                        }
                    } catch (e) {
                        throw _isDebug ? '[Message Event] JWT 복호화 오류' : 'ERROR';
                    }

                    if (_isDebug) {
                        console.log("[Message Event] data         : " + JSON.stringify(_data));
                    }
                    //callback처리
                    if (SsoDataUtil.isFunction(_callback)) {
                        _self._removeCallback(_data);
                    }
                } else if (jsonData.eventCode == 'END') { //화면연동 iframe close
                    try {
                        if (!SsoDataUtil.isEmpty(_data)) {
                            _data = SsoEncryptUtil.extractJwtPayload(_clntEncKey, _data);
                        }
                    } catch (e) {
                        throw _isDebug ? '[Message Event] JWT 복호화 오류' : 'ERROR';
                    }

                    if (_isDebug) {
                        console.log("[Message Event] data         : " + JSON.stringify(_data));
                    }

                    //화면연동 iframe이 끝나는 시점에 _data가 존재하는 경우 제휴사 callback으로 넘겨준다
                    if (SsoDataUtil.isFunction(_ccoCallback)) {
                        _self._removeCcoCallback(_data);
                    }
                    _srnOpt.hideFunc();
                } else if (jsonData.eventCode == "FDS") { //2019-09-18 added by 박재홍. FDS 데이터 생성 API 응답
                    //callback처리
                    if (SsoDataUtil.isFunction(_ccoCallback)) {
                        _self._removeCcoCallback(_data);
                    }
                }
            }
            if (_isDebug) {
                console.log("[Message Event] Event Handling end");
            }
        }

        this._removeEventHandler = function() {
            if (window.detachEvent) {
                window.detachEvent('onmessage', _self._eventHandler);
            } else {
                window.removeEventListener('message', _self._eventHandler);
            }
        }
        /**
         * 제휴사 제공 Web API 라이브러리
         * - iframe message event handler 추가(IE9 문제 해결)
         */
        if (window.attachEvent) {
            window.attachEvent('onmessage', _self._eventHandler);
        } else {
            window.addEventListener('message', _self._eventHandler);
        }

        if (_isDebug) {
            console.log("[SsoClientLibrary] 클라이언트 라이브러리 정상 초기화");
        }
    }

    return SsoClientLibrary;
}());

var SsoPopUpCallback = function(rspDta) {
    var SsoLib = null;
    if (SsoLibName == null || SsoLibName == "") {
        SsoLib = eval('sso');
    } else {
        SsoLib = eval(SsoLibName);
    }

    SsoLib._getCcoCallback(rspDta);
}

/**
 * 웹앱 전용 - 간편로그인 요청에 대한 응답을 받아 처리할 callback함수
 */
var SsoInterfaceCallback = function(rspDta) {
    if (_isDebug) {
        console.log("[SsoInterfaceCallback] rspDta : " + JSON.stringify(rspDta));
    }
    var SsoLib = null;
    if (SsoLibName == null || SsoLibName == "") {
        SsoLib = eval('sso');
    } else {
        SsoLib = eval(SsoLibName);
    }
    var _rspDta;
    if (SsoDataUtil.isJsonStr(rspDta)) {
        _rspDta = JSON.parse(rspDta);
    } else {
        _rspDta = rspDta;
    }

    if (SsoDataUtil.isEmpty(SsoLib)) {
        throw _isDebug ? "초기화된 제휴사 API 라이브러리가 없습니다." : "ERROR";
    } else {
        SsoLib._getCallback(_rspDta);
    }
}

/**
 * 통신 유틸리티 
 * - 생성자
 */

var SsoHttpUtil = {
    ajax: function(targetUrl, inDta, callback) {
        if (_isDebug) {
            console.log('URL : ' + targetUrl);
        }

        $.ajax({
            url: targetUrl,
            type: 'POST',
            async: false,
            cache: false,
            contentType: 'text/html;charset=utf-8',
            data: inDta,
            timeout: 11000,
            beforeSend: function() {

            },
            success: function(response) {
                if (_isDebug) {
                    console.log('OUTPUT : ' + JSON.stringify(response));
                }
                if (response == null || response == undefined) {
                    if (_isDebug) {
                        console.log("IO Exception Error :::::: Server Response is null");
                    }
                    var rspDta = {
                        rspClac: "E",
                        rspC: "77",
                        rspDtc: "004",
                        rspMsgCn: "HTTP 통신데이터 송수신 중 오류가 발생하였습니다."
                    };

                    return callback(rspDta);
                } else {
                    callback(response);
                }
            },
            error: function(response, textStatus, errorThrown) {
                if (_isDebug) {
                    console.log('ERROR : ' + JSON.stringify(response));
                    console.log("Http status code : " + response.status);
                }
                var rspDta = {};

                if (response == null || response == undefined) {
                    rspDta = SsoHttpUtil.setErrorMessage();
                    return callback(rspDta);
                } else {
                    rspDta = SsoHttpUtil.setErrorMessage(response.status);
                    return callback(rspDta);
                }
            }
        });
    },

    setErrorMessage: function(statusCode) {
        var rspDta = {};
        if (SsoDataUtil.isEmpty(statusCode) || statusCode == '0') {
            if (_isDebug) {
                console.log("[setErrorMessage] Connection Error");
            }
            rspDta = {
                rspClac: "E",
                rspC: "77",
                rspDtc: "003",
                rspMsgCn: "Connection 오류가 발생하였습니다."
            };
        } else if (statusCode == '408') {
            if (_isDebug) {
                console.log("[setErrorMessage] Timeout Error");
            }
            rspDta = {
                rspClac: "E",
                rspC: "77",
                rspDtc: "002",
                rspMsgCn: "요청시간을 초과하였습니다."
            };
        } else {
            rspDta = {
                rspClac: "E",
                rspC: "77",
                rspDtc: "005",
                rspMsgCn: "송수신 중 오류가 발생하였습니다. [상태코드:" + statusCode + "]"
            };
        }

        return rspDta;
    }
}

/**
 * 통합회원제 암복호화 유틸리티 
 * - (주의) 통합회원제 클라이언트 라이브러리에서 참조하는 유틸리티로, 제휴사에서 직접 호출하지 않는다.
 */
var SsoEncryptUtil = {
    _keyStr: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",

    /**
     * JSON --> JWT
     * - 암호화된 JWT를 생성한다.
     */
    createJwt: function(clientEncKey, signKey, inHeader, inPayload) {
        var jwt = "";
        var header = "";
        var payload = "";

        try {
            // header SET     
            header = JSON.stringify(inHeader);

            // payload SET
            payload = JSON.stringify(inPayload);

            // header Base64 인코딩
            var encodedHeader = SsoEncryptUtil._encodeBASE64(header);
            // payload AES128 암호화
            var iv = clientEncKey.substr(0, 16);
            var encPayload = SsoEncryptUtil._encryptAES128(payload, clientEncKey, iv);
            // payload Base64 인코딩
            var encodedPayload = SsoEncryptUtil._encodeBASE64(encPayload);
            // signature HmacSHA256 생성
            var encBody = encodedHeader + "." + encodedPayload;
            var signature = SsoEncryptUtil._signHMACSHA256(encBody, signKey);

            // jwt 생성
            jwt = encBody + "." + signature;
        } catch (e) {
            throw e;
        }

        return jwt;
    },

    /**
     * JWT --> JSON
     * - JWT의 Header부분을 디코딩하여 반환
     * @param jwt
     */
    extractJwtHeader: function(jwt) {
        var header = {};

        //header GET
        var values = jwt.split('.');
        var encodedHeader = values[0];

        //header BASE64 디코딩
        var decodedHeader = SsoEncryptUtil._decodeBASE64(encodedHeader);

        if (SsoDataUtil.isJsonStr(decodedHeader)) {
            header = JSON.parse(decodedHeader.toString());
        } else {
            throw _isDebug ? "[extractJwtHeader] JWT header 형식 오류" : 'ERROR';
        }

        return header;
    },

    /**
     * JWT --> JSON
     * - 암호화된 JWT를 복호화하여 JSON으로 변환한다.
     */
    extractJwtPayload: function(encKey, jwt) {
        var rtnJson = {};
        var header = {};
        var payload = {};

        // header, payload GET
        var values = jwt.split('.');
        var encodedHeader = values[0];
        var encodedPayload = values[1];

        // header Base64 디코딩
        var decodedHeader = SsoEncryptUtil._decodeBASE64(encodedHeader);
        // header JSON String --> JSON
        if (SsoDataUtil.isJsonStr(decodedHeader)) {
            header = JSON.parse(decodedHeader.toString());
        } else {
            throw _isDebug ? "[extractJwtPayload] JWT header 형식 오류" : 'ERROR';
        }
        // payload Base64 디코딩
        var decodedPayload = SsoEncryptUtil._decodeBASE64(encodedPayload);
        // payload AES128 복호화
        var iv = encKey.substring(0, 16);
        var strPayload = SsoEncryptUtil._decryptAES128(decodedPayload, encKey, iv);

        // payload JSON String --> JSON
        // JSON 형식 체크
        if (SsoDataUtil.isJsonStr(strPayload)) {
            payload = JSON.parse(strPayload.toString());
        } else {
            throw _isDebug ? "[extractJwtPayload] JWT payload 형식 오류" : 'ERROR';
        }

        rtnJson = payload;

        return rtnJson;
    },

    /**
     * JWT 검증
     * - JWT 서명 위조여부를 검증한다.
     */
    isJwtVerified: function(signKey, jwt) {
        try {
            var values = jwt.split('.');

            var header = ""; // header
            var payload = ""; // payload
            var signature = ""; // signature

            if (values.length == 3) {
                header = values[0]; // header
                payload = values[1]; // payload
                signature = values[2]; // signature
            } else {
                return false;
            }

            var bodyDta = header + "." + payload;

            var verifySignature = SsoEncryptUtil._signHMACSHA256(bodyDta, signKey);

            if (signature == verifySignature) {
                return true;
            } else {
                return false;
            }
        } catch (e) {
            if (_isDebug) {
                console.log("[isJwtVerified] JWT 검증 오류 발생");
            }
            throw e
        }
    },

    /**
     * SHA512 방식으로 단방향암호화한다.
     * @param input
     */
    hashSHA512: function(input) {
        var encryptedInput = "";
        //encrypt SHA512
        encryptedInput = CryptoJS.SHA512(input).toString(CryptoJS.enc.Hex);

        return encryptedInput;
    },

    /**
     * MD5 방식으로 단방향암호화한다.
     * @param input
     */
    hashMD5: function(input) {
        var encryptedInput = "";
        //encrypt SHA512
        encryptedInput = CryptoJS.MD5(input).toString();

        return encryptedInput;
    },

    /**
     * URI Encode 처리
     * @param str
     * @returns
     */
    encodeUri: function(str) {
        var result = encodeURIComponent(str);

        return result;
    },

    /**
     * URI Decode 처리
     * @param str
     * @returns
     */
    decodeUri: function(str) {
        var result = decodeURI(str);

        return result;
    },

    /**
     * BASE64 인코딩
     * - BASE64 방식으로 인코딩한다.
     */
    _encodeBASE64: function(input) {
        var output = "";
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        var i = 0;

        input = SsoEncryptUtil._encodeUTF8(input);

        while (i < input.length) {

            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);

            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;

            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }

            output = output + this._keyStr.charAt(enc1) +
                this._keyStr.charAt(enc2) + this._keyStr.charAt(enc3) +
                this._keyStr.charAt(enc4);
        }

        return output;
    },

    /**
     * BASE64 디코딩
     * - BASE64 방식으로 디코딩한다.
     */
    _decodeBASE64: function(input) {
        var output = "";
        var chr1, chr2, chr3;
        var enc1, enc2, enc3, enc4;
        var i = 0;

        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

        while (i < input.length) {

            enc1 = this._keyStr.indexOf(input.charAt(i++));
            enc2 = this._keyStr.indexOf(input.charAt(i++));
            enc3 = this._keyStr.indexOf(input.charAt(i++));
            enc4 = this._keyStr.indexOf(input.charAt(i++));

            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;

            output = output + String.fromCharCode(chr1);

            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }

        }

        output = SsoEncryptUtil._decodeUTF8(output);

        return output;
    },

    /**
     * UTF8 인코딩
     * - UTF8 방식으로 인코딩한다.
     */
    _encodeUTF8: function(str) {
        str = str.replace(/\r\n/g, "\n");
        var utftext = "";

        for (var n = 0; n < str.length; n++) {

            var c = str.charCodeAt(n);

            if (c < 128) {
                utftext += String.fromCharCode(c);
            } else if ((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            } else {
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }

        }

        return utftext;
    },

    /**
     * UTF8 디코딩
     * - UTF8 방식으로 디코딩한다.
     */
    _decodeUTF8: function(utftext) {
        var str = "";
        var i = 0;
        var c = c1 = c2 = 0;

        while (i < utftext.length) {

            c = utftext.charCodeAt(i);

            if (c < 128) {
                str += String.fromCharCode(c);
                i++;
            } else if ((c > 191) && (c < 224)) {
                c2 = utftext.charCodeAt(i + 1);
                str += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                i += 2;
            } else {
                c2 = utftext.charCodeAt(i + 1);
                c3 = utftext.charCodeAt(i + 2);
                str += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) |
                    (c3 & 63));
                i += 3;
            }

        }

        return str;
    },

    /**
     * AES128 암호화
     * - AES128 CBC 방식으로 암호화한다.
     */
    _encryptAES128: function(str, encKey, initialVector) {
        var key = CryptoJS.enc.Utf8.parse(encKey);
        var iv = CryptoJS.enc.Utf8.parse(initialVector);

        var encBytes = CryptoJS.AES.encrypt(str, key, {
            iv: iv,
            mode: CryptoJS.mode.CBC
        });

        return encBytes.toString();
    },

    /**
     * AES128 복호화
     * - AES128 CBC 방식으로 복호화한다.
     */
    _decryptAES128: function(str, encKey, initialVector) {
        try {
            var key = CryptoJS.enc.Utf8.parse(encKey);
            var iv = CryptoJS.enc.Utf8.parse(initialVector);

            var decBytes = CryptoJS.AES.decrypt(str, key, {
                iv: iv,
                mode: CryptoJS.mode.CBC
            });

            var decStr = decBytes.toString(CryptoJS.enc.Utf8);
            //                                        var decStr = decBytes.toString();
        } catch (e) {
            console.log('[decryptAES128] ' + e);
        }
        return decStr;
    },

    /**
     * 메시지 서명
     * - HmacSHA256 방식으로 메시지 서명을 생성한다.
     */
    _signHMACSHA256: function(str, secret) {
        var hash = CryptoJS.HmacSHA256(str, secret);
        return CryptoJS.enc.Base64.stringify(hash);
    }
}

var SsoDataUtil = {
    isEmpty: function(data) {
        if (typeof(data) === "string") {
            //공백 제거(trim)
            data = SsoDataUtil.trim(data);
            //공백문자열이거나 "null"문자열인 경우
            if (data == "" || data.toLowerCase() == "null") {
                return true;
            } else {
                return false;
            }
        } else {
            if (data == null || data == undefined) {
                return true;
            } else {
                return false;
            }
        }
    },
    isJsonStr: function(data) {
        try {
            var _jsonData = JSON.parse(data);
        } catch (e) {
            return false;
        }
        return true;
    },
    isFunction: function(func) {
        if (func != null && typeof(func) === "function") {
            return true;
        } else {
            return false;
        }
    },
    trim: function(str) {
        var regText = /\s/gi;
        var rtnStr = str.replace(regText, "");
        return rtnStr;
    },
    toNumberString: function(str) {
        var regText = /[^0-9]/gi;
        var rtnStr = str.replace(regText, "");
        return rtnStr;
    }
}

/** **************************** 외부 라이브러리 *********************************** */
/*
 * CryptoJS v3.1.2 code.google.com/p/crypto-js (c) 2009-2013 by Jeff Mott. All
 * rights reserved. code.google.com/p/crypto-js/wiki/License
 */
var CryptoJS = CryptoJS || function(u, p) {
    var d = {},
        l = d.lib = {},
        s = function() {},
        t = l.Base = {
            extend: function(a) {
                s.prototype = this;
                var c = new s;
                a && c.mixIn(a);
                c.hasOwnProperty("init") || (c.init = function() {
                    c.$super.init.apply(this, arguments)
                });
                c.init.prototype = c;
                c.$super = this;
                return c
            },
            create: function() {
                var a = this.extend();
                a.init.apply(a, arguments);
                return a
            },
            init: function() {},
            mixIn: function(a) {
                for (var c in a) a.hasOwnProperty(c) && (this[c] = a[c]);
                a.hasOwnProperty("toString") && (this.toString = a.toString)
            },
            clone: function() {
                return this.init.prototype.extend(this)
            }
        },
        r = l.WordArray = t.extend({
            init: function(a, c) {
                a = this.words = a || [];
                this.sigBytes = c != p ? c : 4 * a.length
            },
            toString: function(a) {
                return (a || v).stringify(this)
            },
            concat: function(a) {
                var c = this.words,
                    e = a.words,
                    j = this.sigBytes;
                a = a.sigBytes;
                this.clamp();
                if (j % 4)
                    for (var k = 0; k < a; k++) c[j + k >>> 2] |= (e[k >>> 2] >>> 24 - 8 * (k % 4) & 255) << 24 - 8 * ((j + k) % 4);
                else if (65535 < e.length)
                    for (k = 0; k < a; k += 4) c[j + k >>> 2] = e[k >>> 2];
                else c.push.apply(c, e);
                this.sigBytes += a;
                return this
            },
            clamp: function() {
                var a = this.words,
                    c = this.sigBytes;
                a[c >>> 2] &= 4294967295 <<
                    32 - 8 * (c % 4);
                a.length = u.ceil(c / 4)
            },
            clone: function() {
                var a = t.clone.call(this);
                a.words = this.words.slice(0);
                return a
            },
            random: function(a) {
                for (var c = [], e = 0; e < a; e += 4) c.push(4294967296 * u.random() | 0);
                return new r.init(c, a)
            }
        }),
        w = d.enc = {},
        v = w.Hex = {
            stringify: function(a) {
                var c = a.words;
                a = a.sigBytes;
                for (var e = [], j = 0; j < a; j++) {
                    var k = c[j >>> 2] >>> 24 - 8 * (j % 4) & 255;
                    e.push((k >>> 4).toString(16));
                    e.push((k & 15).toString(16))
                }
                return e.join("")
            },
            parse: function(a) {
                for (var c = a.length, e = [], j = 0; j < c; j += 2) e[j >>> 3] |= parseInt(a.substr(j,
                    2), 16) << 24 - 4 * (j % 8);
                return new r.init(e, c / 2)
            }
        },
        b = w.Latin1 = {
            stringify: function(a) {
                var c = a.words;
                a = a.sigBytes;
                for (var e = [], j = 0; j < a; j++) e.push(String.fromCharCode(c[j >>> 2] >>> 24 - 8 * (j % 4) & 255));
                return e.join("")
            },
            parse: function(a) {
                for (var c = a.length, e = [], j = 0; j < c; j++) e[j >>> 2] |= (a.charCodeAt(j) & 255) << 24 - 8 * (j % 4);
                return new r.init(e, c)
            }
        },
        x = w.Utf8 = {
            stringify: function(a) {
                try {
                    return decodeURIComponent(escape(b.stringify(a)))
                } catch (c) {
                    throw Error("Malformed UTF-8 data");
                }
            },
            parse: function(a) {
                return b.parse(unescape(encodeURIComponent(a)))
            }
        },
        q = l.BufferedBlockAlgorithm = t.extend({
            reset: function() {
                this._data = new r.init;
                this._nDataBytes = 0
            },
            _append: function(a) {
                "string" == typeof a && (a = x.parse(a));
                this._data.concat(a);
                this._nDataBytes += a.sigBytes
            },
            _process: function(a) {
                var c = this._data,
                    e = c.words,
                    j = c.sigBytes,
                    k = this.blockSize,
                    b = j / (4 * k),
                    b = a ? u.ceil(b) : u.max((b | 0) - this._minBufferSize, 0);
                a = b * k;
                j = u.min(4 * a, j);
                if (a) {
                    for (var q = 0; q < a; q += k) this._doProcessBlock(e, q);
                    q = e.splice(0, a);
                    c.sigBytes -= j
                }
                return new r.init(q, j)
            },
            clone: function() {
                var a = t.clone.call(this);
                a._data = this._data.clone();
                return a
            },
            _minBufferSize: 0
        });
    l.Hasher = q.extend({
        cfg: t.extend(),
        init: function(a) {
            this.cfg = this.cfg.extend(a);
            this.reset()
        },
        reset: function() {
            q.reset.call(this);
            this._doReset()
        },
        update: function(a) {
            this._append(a);
            this._process();
            return this
        },
        finalize: function(a) {
            a && this._append(a);
            return this._doFinalize()
        },
        blockSize: 16,
        _createHelper: function(a) {
            return function(b, e) {
                return (new a.init(e)).finalize(b)
            }
        },
        _createHmacHelper: function(a) {
            return function(b, e) {
                return (new n.HMAC.init(a,
                    e)).finalize(b)
            }
        }
    });
    var n = d.algo = {};
    return d
}(Math);
(function() {
    var u = CryptoJS,
        p = u.lib.WordArray;
    u.enc.Base64 = {
        stringify: function(d) {
            var l = d.words,
                p = d.sigBytes,
                t = this._map;
            d.clamp();
            d = [];
            for (var r = 0; r < p; r += 3)
                for (var w = (l[r >>> 2] >>> 24 - 8 * (r % 4) & 255) << 16 | (l[r + 1 >>> 2] >>> 24 - 8 * ((r + 1) % 4) & 255) << 8 | l[r + 2 >>> 2] >>> 24 - 8 * ((r + 2) % 4) & 255, v = 0; 4 > v && r + 0.75 * v < p; v++) d.push(t.charAt(w >>> 6 * (3 - v) & 63));
            if (l = t.charAt(64))
                for (; d.length % 4;) d.push(l);
            return d.join("")
        },
        parse: function(d) {
            var l = d.length,
                s = this._map,
                t = s.charAt(64);
            t && (t = d.indexOf(t), -1 != t && (l = t));
            for (var t = [], r = 0, w = 0; w <
                l; w++)
                if (w % 4) {
                    var v = s.indexOf(d.charAt(w - 1)) << 2 * (w % 4),
                        b = s.indexOf(d.charAt(w)) >>> 6 - 2 * (w % 4);
                    t[r >>> 2] |= (v | b) << 24 - 8 * (r % 4);
                    r++
                }
            return p.create(t, r)
        },
        _map: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="
    }
})();
(function(u) {
    function p(b, n, a, c, e, j, k) {
        b = b + (n & a | ~n & c) + e + k;
        return (b << j | b >>> 32 - j) + n
    }

    function d(b, n, a, c, e, j, k) {
        b = b + (n & c | a & ~c) + e + k;
        return (b << j | b >>> 32 - j) + n
    }

    function l(b, n, a, c, e, j, k) {
        b = b + (n ^ a ^ c) + e + k;
        return (b << j | b >>> 32 - j) + n
    }

    function s(b, n, a, c, e, j, k) {
        b = b + (a ^ (n | ~c)) + e + k;
        return (b << j | b >>> 32 - j) + n
    }
    for (var t = CryptoJS, r = t.lib, w = r.WordArray, v = r.Hasher, r = t.algo, b = [], x = 0; 64 > x; x++) b[x] = 4294967296 * u.abs(u.sin(x + 1)) | 0;
    r = r.MD5 = v.extend({
        _doReset: function() {
            this._hash = new w.init([1732584193, 4023233417, 2562383102, 271733878])
        },
        _doProcessBlock: function(q, n) {
            for (var a = 0; 16 > a; a++) {
                var c = n + a,
                    e = q[c];
                q[c] = (e << 8 | e >>> 24) & 16711935 | (e << 24 | e >>> 8) & 4278255360
            }
            var a = this._hash.words,
                c = q[n + 0],
                e = q[n + 1],
                j = q[n + 2],
                k = q[n + 3],
                z = q[n + 4],
                r = q[n + 5],
                t = q[n + 6],
                w = q[n + 7],
                v = q[n + 8],
                A = q[n + 9],
                B = q[n + 10],
                C = q[n + 11],
                u = q[n + 12],
                D = q[n + 13],
                E = q[n + 14],
                x = q[n + 15],
                f = a[0],
                m = a[1],
                g = a[2],
                h = a[3],
                f = p(f, m, g, h, c, 7, b[0]),
                h = p(h, f, m, g, e, 12, b[1]),
                g = p(g, h, f, m, j, 17, b[2]),
                m = p(m, g, h, f, k, 22, b[3]),
                f = p(f, m, g, h, z, 7, b[4]),
                h = p(h, f, m, g, r, 12, b[5]),
                g = p(g, h, f, m, t, 17, b[6]),
                m = p(m, g, h, f, w, 22, b[7]),
                f = p(f, m, g, h, v, 7, b[8]),
                h = p(h, f, m, g, A, 12, b[9]),
                g = p(g, h, f, m, B, 17, b[10]),
                m = p(m, g, h, f, C, 22, b[11]),
                f = p(f, m, g, h, u, 7, b[12]),
                h = p(h, f, m, g, D, 12, b[13]),
                g = p(g, h, f, m, E, 17, b[14]),
                m = p(m, g, h, f, x, 22, b[15]),
                f = d(f, m, g, h, e, 5, b[16]),
                h = d(h, f, m, g, t, 9, b[17]),
                g = d(g, h, f, m, C, 14, b[18]),
                m = d(m, g, h, f, c, 20, b[19]),
                f = d(f, m, g, h, r, 5, b[20]),
                h = d(h, f, m, g, B, 9, b[21]),
                g = d(g, h, f, m, x, 14, b[22]),
                m = d(m, g, h, f, z, 20, b[23]),
                f = d(f, m, g, h, A, 5, b[24]),
                h = d(h, f, m, g, E, 9, b[25]),
                g = d(g, h, f, m, k, 14, b[26]),
                m = d(m, g, h, f, v, 20, b[27]),
                f = d(f, m, g, h, D, 5, b[28]),
                h = d(h, f,
                    m, g, j, 9, b[29]),
                g = d(g, h, f, m, w, 14, b[30]),
                m = d(m, g, h, f, u, 20, b[31]),
                f = l(f, m, g, h, r, 4, b[32]),
                h = l(h, f, m, g, v, 11, b[33]),
                g = l(g, h, f, m, C, 16, b[34]),
                m = l(m, g, h, f, E, 23, b[35]),
                f = l(f, m, g, h, e, 4, b[36]),
                h = l(h, f, m, g, z, 11, b[37]),
                g = l(g, h, f, m, w, 16, b[38]),
                m = l(m, g, h, f, B, 23, b[39]),
                f = l(f, m, g, h, D, 4, b[40]),
                h = l(h, f, m, g, c, 11, b[41]),
                g = l(g, h, f, m, k, 16, b[42]),
                m = l(m, g, h, f, t, 23, b[43]),
                f = l(f, m, g, h, A, 4, b[44]),
                h = l(h, f, m, g, u, 11, b[45]),
                g = l(g, h, f, m, x, 16, b[46]),
                m = l(m, g, h, f, j, 23, b[47]),
                f = s(f, m, g, h, c, 6, b[48]),
                h = s(h, f, m, g, w, 10, b[49]),
                g = s(g, h, f, m,
                    E, 15, b[50]),
                m = s(m, g, h, f, r, 21, b[51]),
                f = s(f, m, g, h, u, 6, b[52]),
                h = s(h, f, m, g, k, 10, b[53]),
                g = s(g, h, f, m, B, 15, b[54]),
                m = s(m, g, h, f, e, 21, b[55]),
                f = s(f, m, g, h, v, 6, b[56]),
                h = s(h, f, m, g, x, 10, b[57]),
                g = s(g, h, f, m, t, 15, b[58]),
                m = s(m, g, h, f, D, 21, b[59]),
                f = s(f, m, g, h, z, 6, b[60]),
                h = s(h, f, m, g, C, 10, b[61]),
                g = s(g, h, f, m, j, 15, b[62]),
                m = s(m, g, h, f, A, 21, b[63]);
            a[0] = a[0] + f | 0;
            a[1] = a[1] + m | 0;
            a[2] = a[2] + g | 0;
            a[3] = a[3] + h | 0
        },
        _doFinalize: function() {
            var b = this._data,
                n = b.words,
                a = 8 * this._nDataBytes,
                c = 8 * b.sigBytes;
            n[c >>> 5] |= 128 << 24 - c % 32;
            var e = u.floor(a /
                4294967296);
            n[(c + 64 >>> 9 << 4) + 15] = (e << 8 | e >>> 24) & 16711935 | (e << 24 | e >>> 8) & 4278255360;
            n[(c + 64 >>> 9 << 4) + 14] = (a << 8 | a >>> 24) & 16711935 | (a << 24 | a >>> 8) & 4278255360;
            b.sigBytes = 4 * (n.length + 1);
            this._process();
            b = this._hash;
            n = b.words;
            for (a = 0; 4 > a; a++) c = n[a], n[a] = (c << 8 | c >>> 24) & 16711935 | (c << 24 | c >>> 8) & 4278255360;
            return b
        },
        clone: function() {
            var b = v.clone.call(this);
            b._hash = this._hash.clone();
            return b
        }
    });
    t.MD5 = v._createHelper(r);
    t.HmacMD5 = v._createHmacHelper(r)
})(Math);
(function() {
    var u = CryptoJS,
        p = u.lib,
        d = p.Base,
        l = p.WordArray,
        p = u.algo,
        s = p.EvpKDF = d.extend({
            cfg: d.extend({
                keySize: 4,
                hasher: p.MD5,
                iterations: 1
            }),
            init: function(d) {
                this.cfg = this.cfg.extend(d)
            },
            compute: function(d, r) {
                for (var p = this.cfg, s = p.hasher.create(), b = l.create(), u = b.words, q = p.keySize, p = p.iterations; u.length < q;) {
                    n && s.update(n);
                    var n = s.update(d).finalize(r);
                    s.reset();
                    for (var a = 1; a < p; a++) n = s.finalize(n), s.reset();
                    b.concat(n)
                }
                b.sigBytes = 4 * q;
                return b
            }
        });
    u.EvpKDF = function(d, l, p) {
        return s.create(p).compute(d,
            l)
    }
})();
CryptoJS.lib.Cipher || function(u) {
    var p = CryptoJS,
        d = p.lib,
        l = d.Base,
        s = d.WordArray,
        t = d.BufferedBlockAlgorithm,
        r = p.enc.Base64,
        w = p.algo.EvpKDF,
        v = d.Cipher = t.extend({
            cfg: l.extend(),
            createEncryptor: function(e, a) {
                return this.create(this._ENC_XFORM_MODE, e, a)
            },
            createDecryptor: function(e, a) {
                return this.create(this._DEC_XFORM_MODE, e, a)
            },
            init: function(e, a, b) {
                this.cfg = this.cfg.extend(b);
                this._xformMode = e;
                this._key = a;
                this.reset()
            },
            reset: function() {
                t.reset.call(this);
                this._doReset()
            },
            process: function(e) {
                this._append(e);
                return this._process()
            },
            finalize: function(e) {
                e && this._append(e);
                return this._doFinalize()
            },
            keySize: 4,
            ivSize: 4,
            _ENC_XFORM_MODE: 1,
            _DEC_XFORM_MODE: 2,
            _createHelper: function(e) {
                return {
                    encrypt: function(b, k, d) {
                        return ("string" == typeof k ? c : a).encrypt(e, b, k, d)
                    },
                    decrypt: function(b, k, d) {
                        return ("string" == typeof k ? c : a).decrypt(e, b, k, d)
                    }
                }
            }
        });
    d.StreamCipher = v.extend({
        _doFinalize: function() {
            return this._process(!0)
        },
        blockSize: 1
    });
    var b = p.mode = {},
        x = function(e, a, b) {
            var c = this._iv;
            c ? this._iv = u : c = this._prevBlock;
            for (var d = 0; d < b; d++) e[a + d] ^=
                c[d]
        },
        q = (d.BlockCipherMode = l.extend({
            createEncryptor: function(e, a) {
                return this.Encryptor.create(e, a)
            },
            createDecryptor: function(e, a) {
                return this.Decryptor.create(e, a)
            },
            init: function(e, a) {
                this._cipher = e;
                this._iv = a
            }
        })).extend();
    q.Encryptor = q.extend({
        processBlock: function(e, a) {
            var b = this._cipher,
                c = b.blockSize;
            x.call(this, e, a, c);
            b.encryptBlock(e, a);
            this._prevBlock = e.slice(a, a + c)
        }
    });
    q.Decryptor = q.extend({
        processBlock: function(e, a) {
            var b = this._cipher,
                c = b.blockSize,
                d = e.slice(a, a + c);
            b.decryptBlock(e, a);
            x.call(this,
                e, a, c);
            this._prevBlock = d
        }
    });
    b = b.CBC = q;
    q = (p.pad = {}).Pkcs7 = {
        pad: function(a, b) {
            for (var c = 4 * b, c = c - a.sigBytes % c, d = c << 24 | c << 16 | c << 8 | c, l = [], n = 0; n < c; n += 4) l.push(d);
            c = s.create(l, c);
            a.concat(c)
        },
        unpad: function(a) {
            a.sigBytes -= a.words[a.sigBytes - 1 >>> 2] & 255
        }
    };
    d.BlockCipher = v.extend({
        cfg: v.cfg.extend({
            mode: b,
            padding: q
        }),
        reset: function() {
            v.reset.call(this);
            var a = this.cfg,
                b = a.iv,
                a = a.mode;
            if (this._xformMode == this._ENC_XFORM_MODE) var c = a.createEncryptor;
            else c = a.createDecryptor, this._minBufferSize = 1;
            this._mode = c.call(a,
                this, b && b.words)
        },
        _doProcessBlock: function(a, b) {
            this._mode.processBlock(a, b)
        },
        _doFinalize: function() {
            var a = this.cfg.padding;
            if (this._xformMode == this._ENC_XFORM_MODE) {
                a.pad(this._data, this.blockSize);
                var b = this._process(!0)
            } else b = this._process(!0), a.unpad(b);
            return b
        },
        blockSize: 4
    });
    var n = d.CipherParams = l.extend({
            init: function(a) {
                this.mixIn(a)
            },
            toString: function(a) {
                return (a || this.formatter).stringify(this)
            }
        }),
        b = (p.format = {}).OpenSSL = {
            stringify: function(a) {
                var b = a.ciphertext;
                a = a.salt;
                return (a ? s.create([1398893684,
                    1701076831
                ]).concat(a).concat(b) : b).toString(r)
            },
            parse: function(a) {
                a = r.parse(a);
                var b = a.words;
                if (1398893684 == b[0] && 1701076831 == b[1]) {
                    var c = s.create(b.slice(2, 4));
                    b.splice(0, 4);
                    a.sigBytes -= 16
                }
                return n.create({
                    ciphertext: a,
                    salt: c
                })
            }
        },
        a = d.SerializableCipher = l.extend({
            cfg: l.extend({
                format: b
            }),
            encrypt: function(a, b, c, d) {
                d = this.cfg.extend(d);
                var l = a.createEncryptor(c, d);
                b = l.finalize(b);
                l = l.cfg;
                return n.create({
                    ciphertext: b,
                    key: c,
                    iv: l.iv,
                    algorithm: a,
                    mode: l.mode,
                    padding: l.padding,
                    blockSize: a.blockSize,
                    formatter: d.format
                })
            },
            decrypt: function(a, b, c, d) {
                d = this.cfg.extend(d);
                b = this._parse(b, d.format);
                return a.createDecryptor(c, d).finalize(b.ciphertext)
            },
            _parse: function(a, b) {
                return "string" == typeof a ? b.parse(a, this) : a
            }
        }),
        p = (p.kdf = {}).OpenSSL = {
            execute: function(a, b, c, d) {
                d || (d = s.random(8));
                a = w.create({
                    keySize: b + c
                }).compute(a, d);
                c = s.create(a.words.slice(b), 4 * c);
                a.sigBytes = 4 * b;
                return n.create({
                    key: a,
                    iv: c,
                    salt: d
                })
            }
        },
        c = d.PasswordBasedCipher = a.extend({
            cfg: a.cfg.extend({
                kdf: p
            }),
            encrypt: function(b, c, d, l) {
                l = this.cfg.extend(l);
                d = l.kdf.execute(d,
                    b.keySize, b.ivSize);
                l.iv = d.iv;
                b = a.encrypt.call(this, b, c, d.key, l);
                b.mixIn(d);
                return b
            },
            decrypt: function(b, c, d, l) {
                l = this.cfg.extend(l);
                c = this._parse(c, l.format);
                d = l.kdf.execute(d, b.keySize, b.ivSize, c.salt);
                l.iv = d.iv;
                return a.decrypt.call(this, b, c, d.key, l)
            }
        })
}();
(function() {
    for (var u = CryptoJS, p = u.lib.BlockCipher, d = u.algo, l = [], s = [], t = [], r = [], w = [], v = [], b = [], x = [], q = [], n = [], a = [], c = 0; 256 > c; c++) a[c] = 128 > c ? c << 1 : c << 1 ^ 283;
    for (var e = 0, j = 0, c = 0; 256 > c; c++) {
        var k = j ^ j << 1 ^ j << 2 ^ j << 3 ^ j << 4,
            k = k >>> 8 ^ k & 255 ^ 99;
        l[e] = k;
        s[k] = e;
        var z = a[e],
            F = a[z],
            G = a[F],
            y = 257 * a[k] ^ 16843008 * k;
        t[e] = y << 24 | y >>> 8;
        r[e] = y << 16 | y >>> 16;
        w[e] = y << 8 | y >>> 24;
        v[e] = y;
        y = 16843009 * G ^ 65537 * F ^ 257 * z ^ 16843008 * e;
        b[k] = y << 24 | y >>> 8;
        x[k] = y << 16 | y >>> 16;
        q[k] = y << 8 | y >>> 24;
        n[k] = y;
        e ? (e = z ^ a[a[a[G ^ z]]], j ^= a[a[j]]) : e = j = 1
    }
    var H = [0, 1, 2, 4, 8,
            16, 32, 64, 128, 27, 54
        ],
        d = d.AES = p.extend({
            _doReset: function() {
                for (var a = this._key, c = a.words, d = a.sigBytes / 4, a = 4 * ((this._nRounds = d + 6) + 1), e = this._keySchedule = [], j = 0; j < a; j++)
                    if (j < d) e[j] = c[j];
                    else {
                        var k = e[j - 1];
                        j % d ? 6 < d && 4 == j % d && (k = l[k >>> 24] << 24 | l[k >>> 16 & 255] << 16 | l[k >>> 8 & 255] << 8 | l[k & 255]) : (k = k << 8 | k >>> 24, k = l[k >>> 24] << 24 | l[k >>> 16 & 255] << 16 | l[k >>> 8 & 255] << 8 | l[k & 255], k ^= H[j / d | 0] << 24);
                        e[j] = e[j - d] ^ k
                    }
                c = this._invKeySchedule = [];
                for (d = 0; d < a; d++) j = a - d, k = d % 4 ? e[j] : e[j - 4], c[d] = 4 > d || 4 >= j ? k : b[l[k >>> 24]] ^ x[l[k >>> 16 & 255]] ^ q[l[k >>>
                    8 & 255]] ^ n[l[k & 255]]
            },
            encryptBlock: function(a, b) {
                this._doCryptBlock(a, b, this._keySchedule, t, r, w, v, l)
            },
            decryptBlock: function(a, c) {
                var d = a[c + 1];
                a[c + 1] = a[c + 3];
                a[c + 3] = d;
                this._doCryptBlock(a, c, this._invKeySchedule, b, x, q, n, s);
                d = a[c + 1];
                a[c + 1] = a[c + 3];
                a[c + 3] = d
            },
            _doCryptBlock: function(a, b, c, d, e, j, l, f) {
                for (var m = this._nRounds, g = a[b] ^ c[0], h = a[b + 1] ^ c[1], k = a[b + 2] ^ c[2], n = a[b + 3] ^ c[3], p = 4, r = 1; r < m; r++) var q = d[g >>> 24] ^ e[h >>> 16 & 255] ^ j[k >>> 8 & 255] ^ l[n & 255] ^ c[p++],
                    s = d[h >>> 24] ^ e[k >>> 16 & 255] ^ j[n >>> 8 & 255] ^ l[g & 255] ^ c[p++],
                    t =
                    d[k >>> 24] ^ e[n >>> 16 & 255] ^ j[g >>> 8 & 255] ^ l[h & 255] ^ c[p++],
                    n = d[n >>> 24] ^ e[g >>> 16 & 255] ^ j[h >>> 8 & 255] ^ l[k & 255] ^ c[p++],
                    g = q,
                    h = s,
                    k = t;
                q = (f[g >>> 24] << 24 | f[h >>> 16 & 255] << 16 | f[k >>> 8 & 255] << 8 | f[n & 255]) ^ c[p++];
                s = (f[h >>> 24] << 24 | f[k >>> 16 & 255] << 16 | f[n >>> 8 & 255] << 8 | f[g & 255]) ^ c[p++];
                t = (f[k >>> 24] << 24 | f[n >>> 16 & 255] << 16 | f[g >>> 8 & 255] << 8 | f[h & 255]) ^ c[p++];
                n = (f[n >>> 24] << 24 | f[g >>> 16 & 255] << 16 | f[h >>> 8 & 255] << 8 | f[k & 255]) ^ c[p++];
                a[b] = q;
                a[b + 1] = s;
                a[b + 2] = t;
                a[b + 3] = n
            },
            keySize: 8
        });
    u.AES = p._createHelper(d)
})();



/*
 * CryptoJS v3.1.2 code.google.com/p/crypto-js (c) 2009-2013 by Jeff Mott. All
 * rights reserved. code.google.com/p/crypto-js/wiki/License
 */
var CryptoJS = CryptoJS || function(h, s) {
    var f = {},
        g = f.lib = {},
        q = function() {},
        m = g.Base = {
            extend: function(a) {
                q.prototype = this;
                var c = new q;
                a && c.mixIn(a);
                c.hasOwnProperty("init") || (c.init = function() {
                    c.$super.init.apply(this, arguments)
                });
                c.init.prototype = c;
                c.$super = this;
                return c
            },
            create: function() {
                var a = this.extend();
                a.init.apply(a, arguments);
                return a
            },
            init: function() {},
            mixIn: function(a) {
                for (var c in a) a.hasOwnProperty(c) && (this[c] = a[c]);
                a.hasOwnProperty("toString") && (this.toString = a.toString)
            },
            clone: function() {
                return this.init.prototype.extend(this)
            }
        },
        r = g.WordArray = m.extend({
            init: function(a, c) {
                a = this.words = a || [];
                this.sigBytes = c != s ? c : 4 * a.length
            },
            toString: function(a) {
                return (a || k).stringify(this)
            },
            concat: function(a) {
                var c = this.words,
                    d = a.words,
                    b = this.sigBytes;
                a = a.sigBytes;
                this.clamp();
                if (b % 4)
                    for (var e = 0; e < a; e++) c[b + e >>> 2] |= (d[e >>> 2] >>> 24 - 8 * (e % 4) & 255) << 24 - 8 * ((b + e) % 4);
                else if (65535 < d.length)
                    for (e = 0; e < a; e += 4) c[b + e >>> 2] = d[e >>> 2];
                else c.push.apply(c, d);
                this.sigBytes += a;
                return this
            },
            clamp: function() {
                var a = this.words,
                    c = this.sigBytes;
                a[c >>> 2] &= 4294967295 <<
                    32 - 8 * (c % 4);
                a.length = h.ceil(c / 4)
            },
            clone: function() {
                var a = m.clone.call(this);
                a.words = this.words.slice(0);
                return a
            },
            random: function(a) {
                for (var c = [], d = 0; d < a; d += 4) c.push(4294967296 * h.random() | 0);
                return new r.init(c, a)
            }
        }),
        l = f.enc = {},
        k = l.Hex = {
            stringify: function(a) {
                var c = a.words;
                a = a.sigBytes;
                for (var d = [], b = 0; b < a; b++) {
                    var e = c[b >>> 2] >>> 24 - 8 * (b % 4) & 255;
                    d.push((e >>> 4).toString(16));
                    d.push((e & 15).toString(16))
                }
                return d.join("")
            },
            parse: function(a) {
                for (var c = a.length, d = [], b = 0; b < c; b += 2) d[b >>> 3] |= parseInt(a.substr(b,
                    2), 16) << 24 - 4 * (b % 8);
                return new r.init(d, c / 2)
            }
        },
        n = l.Latin1 = {
            stringify: function(a) {
                var c = a.words;
                a = a.sigBytes;
                for (var d = [], b = 0; b < a; b++) d.push(String.fromCharCode(c[b >>> 2] >>> 24 - 8 * (b % 4) & 255));
                return d.join("")
            },
            parse: function(a) {
                for (var c = a.length, d = [], b = 0; b < c; b++) d[b >>> 2] |= (a.charCodeAt(b) & 255) << 24 - 8 * (b % 4);
                return new r.init(d, c)
            }
        },
        j = l.Utf8 = {
            stringify: function(a) {
                try {
                    return decodeURIComponent(escape(n.stringify(a)))
                } catch (c) {
                    throw Error("Malformed UTF-8 data");
                }
            },
            parse: function(a) {
                return n.parse(unescape(encodeURIComponent(a)))
            }
        },
        u = g.BufferedBlockAlgorithm = m.extend({
            reset: function() {
                this._data = new r.init;
                this._nDataBytes = 0
            },
            _append: function(a) {
                "string" == typeof a && (a = j.parse(a));
                this._data.concat(a);
                this._nDataBytes += a.sigBytes
            },
            _process: function(a) {
                var c = this._data,
                    d = c.words,
                    b = c.sigBytes,
                    e = this.blockSize,
                    f = b / (4 * e),
                    f = a ? h.ceil(f) : h.max((f | 0) - this._minBufferSize, 0);
                a = f * e;
                b = h.min(4 * a, b);
                if (a) {
                    for (var g = 0; g < a; g += e) this._doProcessBlock(d, g);
                    g = d.splice(0, a);
                    c.sigBytes -= b
                }
                return new r.init(g, b)
            },
            clone: function() {
                var a = m.clone.call(this);
                a._data = this._data.clone();
                return a
            },
            _minBufferSize: 0
        });
    g.Hasher = u.extend({
        cfg: m.extend(),
        init: function(a) {
            this.cfg = this.cfg.extend(a);
            this.reset()
        },
        reset: function() {
            u.reset.call(this);
            this._doReset()
        },
        update: function(a) {
            this._append(a);
            this._process();
            return this
        },
        finalize: function(a) {
            a && this._append(a);
            return this._doFinalize()
        },
        blockSize: 16,
        _createHelper: function(a) {
            return function(c, d) {
                return (new a.init(d)).finalize(c)
            }
        },
        _createHmacHelper: function(a) {
            return function(c, d) {
                return (new t.HMAC.init(a,
                    d)).finalize(c)
            }
        }
    });
    var t = f.algo = {};
    return f
}(Math);
(function(h) {
    for (var s = CryptoJS, f = s.lib, g = f.WordArray, q = f.Hasher, f = s.algo, m = [], r = [], l = function(a) {
            return 4294967296 * (a - (a | 0)) | 0
        }, k = 2, n = 0; 64 > n;) {
        var j;
        a: {
            j = k;
            for (var u = h.sqrt(j), t = 2; t <= u; t++)
                if (!(j % t)) {
                    j = !1;
                    break a
                }
            j = !0
        }
        j && (8 > n && (m[n] = l(h.pow(k, 0.5))), r[n] = l(h.pow(k, 1 / 3)), n++);
        k++
    }
    var a = [],
        f = f.SHA256 = q.extend({
            _doReset: function() {
                this._hash = new g.init(m.slice(0))
            },
            _doProcessBlock: function(c, d) {
                for (var b = this._hash.words, e = b[0], f = b[1], g = b[2], j = b[3], h = b[4], m = b[5], n = b[6], q = b[7], p = 0; 64 > p; p++) {
                    if (16 > p) a[p] =
                        c[d + p] | 0;
                    else {
                        var k = a[p - 15],
                            l = a[p - 2];
                        a[p] = ((k << 25 | k >>> 7) ^ (k << 14 | k >>> 18) ^ k >>> 3) + a[p - 7] + ((l << 15 | l >>> 17) ^ (l << 13 | l >>> 19) ^ l >>> 10) + a[p - 16]
                    }
                    k = q + ((h << 26 | h >>> 6) ^ (h << 21 | h >>> 11) ^ (h << 7 | h >>> 25)) + (h & m ^ ~h & n) + r[p] + a[p];
                    l = ((e << 30 | e >>> 2) ^ (e << 19 | e >>> 13) ^ (e << 10 | e >>> 22)) + (e & f ^ e & g ^ f & g);
                    q = n;
                    n = m;
                    m = h;
                    h = j + k | 0;
                    j = g;
                    g = f;
                    f = e;
                    e = k + l | 0
                }
                b[0] = b[0] + e | 0;
                b[1] = b[1] + f | 0;
                b[2] = b[2] + g | 0;
                b[3] = b[3] + j | 0;
                b[4] = b[4] + h | 0;
                b[5] = b[5] + m | 0;
                b[6] = b[6] + n | 0;
                b[7] = b[7] + q | 0
            },
            _doFinalize: function() {
                var a = this._data,
                    d = a.words,
                    b = 8 * this._nDataBytes,
                    e = 8 * a.sigBytes;
                d[e >>> 5] |= 128 << 24 - e % 32;
                d[(e + 64 >>> 9 << 4) + 14] = h.floor(b / 4294967296);
                d[(e + 64 >>> 9 << 4) + 15] = b;
                a.sigBytes = 4 * d.length;
                this._process();
                return this._hash
            },
            clone: function() {
                var a = q.clone.call(this);
                a._hash = this._hash.clone();
                return a
            }
        });
    s.SHA256 = q._createHelper(f);
    s.HmacSHA256 = q._createHmacHelper(f)
})(Math);
(function() {
    var h = CryptoJS,
        s = h.enc.Utf8;
    h.algo.HMAC = h.lib.Base.extend({
        init: function(f, g) {
            f = this._hasher = new f.init;
            "string" == typeof g && (g = s.parse(g));
            var h = f.blockSize,
                m = 4 * h;
            g.sigBytes > m && (g = f.finalize(g));
            g.clamp();
            for (var r = this._oKey = g.clone(), l = this._iKey = g.clone(), k = r.words, n = l.words, j = 0; j < h; j++) k[j] ^= 1549556828, n[j] ^= 909522486;
            r.sigBytes = l.sigBytes = m;
            this.reset()
        },
        reset: function() {
            var f = this._hasher;
            f.reset();
            f.update(this._iKey)
        },
        update: function(f) {
            this._hasher.update(f);
            return this
        },
        finalize: function(f) {
            var g =
                this._hasher;
            f = g.finalize(f);
            g.reset();
            return g.finalize(this._oKey.clone().concat(f))
        }
    })
})();

/*
CryptoJS v3.1.2
code.google.com/p/crypto-js
(c) 2009-2013 by Jeff Mott. All rights reserved.
code.google.com/p/crypto-js/wiki/License
*/
var CryptoJS = CryptoJS || function(a, m) {
    var r = {},
        f = r.lib = {},
        g = function() {},
        l = f.Base = {
            extend: function(a) {
                g.prototype = this;
                var b = new g;
                a && b.mixIn(a);
                b.hasOwnProperty("init") || (b.init = function() {
                    b.$super.init.apply(this, arguments)
                });
                b.init.prototype = b;
                b.$super = this;
                return b
            },
            create: function() {
                var a = this.extend();
                a.init.apply(a, arguments);
                return a
            },
            init: function() {},
            mixIn: function(a) {
                for (var b in a) a.hasOwnProperty(b) && (this[b] = a[b]);
                a.hasOwnProperty("toString") && (this.toString = a.toString)
            },
            clone: function() {
                return this.init.prototype.extend(this)
            }
        },
        p = f.WordArray = l.extend({
            init: function(a, b) {
                a = this.words = a || [];
                this.sigBytes = b != m ? b : 4 * a.length
            },
            toString: function(a) {
                return (a || q).stringify(this)
            },
            concat: function(a) {
                var b = this.words,
                    d = a.words,
                    c = this.sigBytes;
                a = a.sigBytes;
                this.clamp();
                if (c % 4)
                    for (var j = 0; j < a; j++) b[c + j >>> 2] |= (d[j >>> 2] >>> 24 - 8 * (j % 4) & 255) << 24 - 8 * ((c + j) % 4);
                else if (65535 < d.length)
                    for (j = 0; j < a; j += 4) b[c + j >>> 2] = d[j >>> 2];
                else b.push.apply(b, d);
                this.sigBytes += a;
                return this
            },
            clamp: function() {
                var n = this.words,
                    b = this.sigBytes;
                n[b >>> 2] &= 4294967295 <<
                    32 - 8 * (b % 4);
                n.length = a.ceil(b / 4)
            },
            clone: function() {
                var a = l.clone.call(this);
                a.words = this.words.slice(0);
                return a
            },
            random: function(n) {
                for (var b = [], d = 0; d < n; d += 4) b.push(4294967296 * a.random() | 0);
                return new p.init(b, n)
            }
        }),
        y = r.enc = {},
        q = y.Hex = {
            stringify: function(a) {
                var b = a.words;
                a = a.sigBytes;
                for (var d = [], c = 0; c < a; c++) {
                    var j = b[c >>> 2] >>> 24 - 8 * (c % 4) & 255;
                    d.push((j >>> 4).toString(16));
                    d.push((j & 15).toString(16))
                }
                return d.join("")
            },
            parse: function(a) {
                for (var b = a.length, d = [], c = 0; c < b; c += 2) d[c >>> 3] |= parseInt(a.substr(c,
                    2), 16) << 24 - 4 * (c % 8);
                return new p.init(d, b / 2)
            }
        },
        G = y.Latin1 = {
            stringify: function(a) {
                var b = a.words;
                a = a.sigBytes;
                for (var d = [], c = 0; c < a; c++) d.push(String.fromCharCode(b[c >>> 2] >>> 24 - 8 * (c % 4) & 255));
                return d.join("")
            },
            parse: function(a) {
                for (var b = a.length, d = [], c = 0; c < b; c++) d[c >>> 2] |= (a.charCodeAt(c) & 255) << 24 - 8 * (c % 4);
                return new p.init(d, b)
            }
        },
        fa = y.Utf8 = {
            stringify: function(a) {
                try {
                    return decodeURIComponent(escape(G.stringify(a)))
                } catch (b) {
                    throw Error("Malformed UTF-8 data");
                }
            },
            parse: function(a) {
                return G.parse(unescape(encodeURIComponent(a)))
            }
        },
        h = f.BufferedBlockAlgorithm = l.extend({
            reset: function() {
                this._data = new p.init;
                this._nDataBytes = 0
            },
            _append: function(a) {
                "string" == typeof a && (a = fa.parse(a));
                this._data.concat(a);
                this._nDataBytes += a.sigBytes
            },
            _process: function(n) {
                var b = this._data,
                    d = b.words,
                    c = b.sigBytes,
                    j = this.blockSize,
                    l = c / (4 * j),
                    l = n ? a.ceil(l) : a.max((l | 0) - this._minBufferSize, 0);
                n = l * j;
                c = a.min(4 * n, c);
                if (n) {
                    for (var h = 0; h < n; h += j) this._doProcessBlock(d, h);
                    h = d.splice(0, n);
                    b.sigBytes -= c
                }
                return new p.init(h, c)
            },
            clone: function() {
                var a = l.clone.call(this);
                a._data = this._data.clone();
                return a
            },
            _minBufferSize: 0
        });
    f.Hasher = h.extend({
        cfg: l.extend(),
        init: function(a) {
            this.cfg = this.cfg.extend(a);
            this.reset()
        },
        reset: function() {
            h.reset.call(this);
            this._doReset()
        },
        update: function(a) {
            this._append(a);
            this._process();
            return this
        },
        finalize: function(a) {
            a && this._append(a);
            return this._doFinalize()
        },
        blockSize: 16,
        _createHelper: function(a) {
            return function(b, d) {
                return (new a.init(d)).finalize(b)
            }
        },
        _createHmacHelper: function(a) {
            return function(b, d) {
                return (new ga.HMAC.init(a,
                    d)).finalize(b)
            }
        }
    });
    var ga = r.algo = {};
    return r
}(Math);
(function(a) {
    var m = CryptoJS,
        r = m.lib,
        f = r.Base,
        g = r.WordArray,
        m = m.x64 = {};
    m.Word = f.extend({
        init: function(a, p) {
            this.high = a;
            this.low = p
        }
    });
    m.WordArray = f.extend({
        init: function(l, p) {
            l = this.words = l || [];
            this.sigBytes = p != a ? p : 8 * l.length
        },
        toX32: function() {
            for (var a = this.words, p = a.length, f = [], q = 0; q < p; q++) {
                var G = a[q];
                f.push(G.high);
                f.push(G.low)
            }
            return g.create(f, this.sigBytes)
        },
        clone: function() {
            for (var a = f.clone.call(this), p = a.words = this.words.slice(0), g = p.length, q = 0; q < g; q++) p[q] = p[q].clone();
            return a
        }
    })
})();
(function() {
    function a() {
        return g.create.apply(g, arguments)
    }
    for (var m = CryptoJS, r = m.lib.Hasher, f = m.x64, g = f.Word, l = f.WordArray, f = m.algo, p = [a(1116352408, 3609767458), a(1899447441, 602891725), a(3049323471, 3964484399), a(3921009573, 2173295548), a(961987163, 4081628472), a(1508970993, 3053834265), a(2453635748, 2937671579), a(2870763221, 3664609560), a(3624381080, 2734883394), a(310598401, 1164996542), a(607225278, 1323610764), a(1426881987, 3590304994), a(1925078388, 4068182383), a(2162078206, 991336113), a(2614888103, 633803317),
            a(3248222580, 3479774868), a(3835390401, 2666613458), a(4022224774, 944711139), a(264347078, 2341262773), a(604807628, 2007800933), a(770255983, 1495990901), a(1249150122, 1856431235), a(1555081692, 3175218132), a(1996064986, 2198950837), a(2554220882, 3999719339), a(2821834349, 766784016), a(2952996808, 2566594879), a(3210313671, 3203337956), a(3336571891, 1034457026), a(3584528711, 2466948901), a(113926993, 3758326383), a(338241895, 168717936), a(666307205, 1188179964), a(773529912, 1546045734), a(1294757372, 1522805485), a(1396182291,
                2643833823), a(1695183700, 2343527390), a(1986661051, 1014477480), a(2177026350, 1206759142), a(2456956037, 344077627), a(2730485921, 1290863460), a(2820302411, 3158454273), a(3259730800, 3505952657), a(3345764771, 106217008), a(3516065817, 3606008344), a(3600352804, 1432725776), a(4094571909, 1467031594), a(275423344, 851169720), a(430227734, 3100823752), a(506948616, 1363258195), a(659060556, 3750685593), a(883997877, 3785050280), a(958139571, 3318307427), a(1322822218, 3812723403), a(1537002063, 2003034995), a(1747873779, 3602036899),
            a(1955562222, 1575990012), a(2024104815, 1125592928), a(2227730452, 2716904306), a(2361852424, 442776044), a(2428436474, 593698344), a(2756734187, 3733110249), a(3204031479, 2999351573), a(3329325298, 3815920427), a(3391569614, 3928383900), a(3515267271, 566280711), a(3940187606, 3454069534), a(4118630271, 4000239992), a(116418474, 1914138554), a(174292421, 2731055270), a(289380356, 3203993006), a(460393269, 320620315), a(685471733, 587496836), a(852142971, 1086792851), a(1017036298, 365543100), a(1126000580, 2618297676), a(1288033470,
                3409855158), a(1501505948, 4234509866), a(1607167915, 987167468), a(1816402316, 1246189591)
        ], y = [], q = 0; 80 > q; q++) y[q] = a();
    f = f.SHA512 = r.extend({
        _doReset: function() {
            this._hash = new l.init([new g.init(1779033703, 4089235720), new g.init(3144134277, 2227873595), new g.init(1013904242, 4271175723), new g.init(2773480762, 1595750129), new g.init(1359893119, 2917565137), new g.init(2600822924, 725511199), new g.init(528734635, 4215389547), new g.init(1541459225, 327033209)])
        },
        _doProcessBlock: function(a, f) {
            for (var h = this._hash.words,
                    g = h[0], n = h[1], b = h[2], d = h[3], c = h[4], j = h[5], l = h[6], h = h[7], q = g.high, m = g.low, r = n.high, N = n.low, Z = b.high, O = b.low, $ = d.high, P = d.low, aa = c.high, Q = c.low, ba = j.high, R = j.low, ca = l.high, S = l.low, da = h.high, T = h.low, v = q, s = m, H = r, E = N, I = Z, F = O, W = $, J = P, w = aa, t = Q, U = ba, K = R, V = ca, L = S, X = da, M = T, x = 0; 80 > x; x++) {
                var B = y[x];
                if (16 > x) var u = B.high = a[f + 2 * x] | 0,
                    e = B.low = a[f + 2 * x + 1] | 0;
                else {
                    var u = y[x - 15],
                        e = u.high,
                        z = u.low,
                        u = (e >>> 1 | z << 31) ^ (e >>> 8 | z << 24) ^ e >>> 7,
                        z = (z >>> 1 | e << 31) ^ (z >>> 8 | e << 24) ^ (z >>> 7 | e << 25),
                        D = y[x - 2],
                        e = D.high,
                        k = D.low,
                        D = (e >>> 19 | k << 13) ^
                        (e << 3 | k >>> 29) ^ e >>> 6,
                        k = (k >>> 19 | e << 13) ^ (k << 3 | e >>> 29) ^ (k >>> 6 | e << 26),
                        e = y[x - 7],
                        Y = e.high,
                        C = y[x - 16],
                        A = C.high,
                        C = C.low,
                        e = z + e.low,
                        u = u + Y + (e >>> 0 < z >>> 0 ? 1 : 0),
                        e = e + k,
                        u = u + D + (e >>> 0 < k >>> 0 ? 1 : 0),
                        e = e + C,
                        u = u + A + (e >>> 0 < C >>> 0 ? 1 : 0);
                    B.high = u;
                    B.low = e
                }
                var Y = w & U ^ ~w & V,
                    C = t & K ^ ~t & L,
                    B = v & H ^ v & I ^ H & I,
                    ha = s & E ^ s & F ^ E & F,
                    z = (v >>> 28 | s << 4) ^ (v << 30 | s >>> 2) ^ (v << 25 | s >>> 7),
                    D = (s >>> 28 | v << 4) ^ (s << 30 | v >>> 2) ^ (s << 25 | v >>> 7),
                    k = p[x],
                    ia = k.high,
                    ea = k.low,
                    k = M + ((t >>> 14 | w << 18) ^ (t >>> 18 | w << 14) ^ (t << 23 | w >>> 9)),
                    A = X + ((w >>> 14 | t << 18) ^ (w >>> 18 | t << 14) ^ (w << 23 | t >>> 9)) + (k >>> 0 < M >>>
                        0 ? 1 : 0),
                    k = k + C,
                    A = A + Y + (k >>> 0 < C >>> 0 ? 1 : 0),
                    k = k + ea,
                    A = A + ia + (k >>> 0 < ea >>> 0 ? 1 : 0),
                    k = k + e,
                    A = A + u + (k >>> 0 < e >>> 0 ? 1 : 0),
                    e = D + ha,
                    B = z + B + (e >>> 0 < D >>> 0 ? 1 : 0),
                    X = V,
                    M = L,
                    V = U,
                    L = K,
                    U = w,
                    K = t,
                    t = J + k | 0,
                    w = W + A + (t >>> 0 < J >>> 0 ? 1 : 0) | 0,
                    W = I,
                    J = F,
                    I = H,
                    F = E,
                    H = v,
                    E = s,
                    s = k + e | 0,
                    v = A + B + (s >>> 0 < k >>> 0 ? 1 : 0) | 0
            }
            m = g.low = m + s;
            g.high = q + v + (m >>> 0 < s >>> 0 ? 1 : 0);
            N = n.low = N + E;
            n.high = r + H + (N >>> 0 < E >>> 0 ? 1 : 0);
            O = b.low = O + F;
            b.high = Z + I + (O >>> 0 < F >>> 0 ? 1 : 0);
            P = d.low = P + J;
            d.high = $ + W + (P >>> 0 < J >>> 0 ? 1 : 0);
            Q = c.low = Q + t;
            c.high = aa + w + (Q >>> 0 < t >>> 0 ? 1 : 0);
            R = j.low = R + K;
            j.high = ba + U + (R >>> 0 < K >>> 0 ? 1 : 0);
            S = l.low =
                S + L;
            l.high = ca + V + (S >>> 0 < L >>> 0 ? 1 : 0);
            T = h.low = T + M;
            h.high = da + X + (T >>> 0 < M >>> 0 ? 1 : 0)
        },
        _doFinalize: function() {
            var a = this._data,
                f = a.words,
                h = 8 * this._nDataBytes,
                g = 8 * a.sigBytes;
            f[g >>> 5] |= 128 << 24 - g % 32;
            f[(g + 128 >>> 10 << 5) + 30] = Math.floor(h / 4294967296);
            f[(g + 128 >>> 10 << 5) + 31] = h;
            a.sigBytes = 4 * f.length;
            this._process();
            return this._hash.toX32()
        },
        clone: function() {
            var a = r.clone.call(this);
            a._hash = this._hash.clone();
            return a
        },
        blockSize: 32
    });
    m.SHA512 = r._createHelper(f);
    m.HmacSHA512 = r._createHmacHelper(f)
})();