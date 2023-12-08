var fnc = (function() {
	var pathname = "/" + window.location.pathname.split("/")[1];
	var origin = window.location.origin;	
	var contextPath = origin + pathname;
	
    "use strict";

    var submitFlag = false;

    /*
     * fnc.nvl
     * note : 데이터가 null 또는 undefined이면 값을 치환한다.
     */
    var nvl = function(val, chgStr) {
        if (val == undefined || val == null || val == "") {
            return chgStr;
        } else {
            return val;
        }
    };

    /*
     * fnc.replaceAll
     * note : str 문자열에서 findStr문자열을 찾아 changeStr문자열로 교체한다.
     */
    var replaceAll = function(str, findStr, changeStr) {
        return str.split(findStr).join(changeStr);
    };

    /*
     * fnc.maxZindex
     * note : z-index 최대값
     */
    var maxZindex = function() {
        return Math.max.apply(null, $.map(jQuery("body > *"), function(e, n) {
            if (jQuery(e).css("position") == "absolute") return parseInt(jQuery(e).css("z-index")) || 1;
        }));
    };

    /*
     * fnc.startProgress
     * note : progress bar 시작
     */
    var startProgress = function(target) {
        if (jQuery(target).length > 0) {
            jQuery(target).css("z-index", "999");
            target.stop(true, true).fadeIn(300);
            $("body").addClass("stop_scroll");
        }
    };

    /*
     * fnc.endProgress
     * note : progress bar 종료
     */
    var endProgress = function(target) {
        if (jQuery(target).length > 0) {
            jQuery(target).css("z-index", "100");
            target.stop(true, true).fadeOut(300);
            $("body").removeClass("stop_scroll");
        }
    };

    /*
     * fnc.bscAjax
     * note : ajax 일반
     */
    var bscAjax = function(callbackAjax, url, dataType, loading, sync, btnFlag) {
        if (typeof btnFlag == "undefined") {
            btnFlag = false;
        }

        if (!submitFlag || !btnFlag) {
            submitFlag = true;

            if (typeof dataType == "undefined") {
                dataType = "json";
            }

            if (typeof loading == "undefined") {
                loading = false;
            }

            if (typeof sync == "undefined") {
                sync = true;
            }

            jQuery.ajax({ // ajax 실행

                url: url,
                type: "get",
                timeout: 30000,
                dataType: dataType,
                async: sync,
                cache: false,
                beforeSend: function() {
console.log("Request URL: " + url);
                    if (loading) {
                        fnc.startProgress(jQuery("#dimdBg"));
                    }
                },
                success: function(data, status, xhr) {
// console.log("success data : " + data);	
                    if (callbackAjax) {
                        callbackAjax(data);
                    }
                },
                error: function(e) {
                    if (e.status == 403) {
                        if (confirm("자동 로그아웃 처리되었습니다. 로그인을 다시 시도해주세요.")) {
                            //moveLoginPage();
                        }
                    } else {}
                },
                complete: function() {
                    if (loading) {
                        fnc.endProgress(jQuery("#dimdBg"));
                    }
                    submitFlag = false;
                }
            });

        }

    };

    /*
     * fnc.frmAjax
     * note : ajax 폼데이터
     */
    var frmAjax = function(callbackAjax, url, formObj, dataType, loading, sync, btnFlag) {
    
        if (typeof btnFlag == "undefined") {
            btnFlag = false;
        }

        if (!submitFlag || !btnFlag) {
            submitFlag = true;

            if (typeof dataType == "undefined") {
                dataType = "json";
            }

            if (typeof loading == "undefined") {
                loading = false;
            }

            if (typeof sync == "undefined") {
                sync = true;
            }
					
			// 폼 데이터를 배열로 가져오기	
			var formDataArray = $(formObj).serializeArray();
			
			// 배열을 객체로 변환
			var formDataObject = {};
			$.each(formDataArray, function(i, field) {
			    formDataObject[field.name] = field.value;
			});
			
			// JSON 형식으로 변환
			var jsonData = JSON.stringify(formDataObject);
			
            jQuery.ajax({
                url: url,
                type: "post",
                timeout: 30000,
                data: jsonData,
                contentType : "application/json; charset=utf-8",
                dataType: dataType,
                async: sync,
                cache: false,
                beforeSend: function() {
                    if (loading) {
                        fnc.startProgress(jQuery("#dimdBg"));
                    }
                },
                success: function(data, status, xhr) {
                    debugger;
                    if (callbackAjax) {
                        callbackAjax(data);
                    }
                },
                error: function(e) {
                    if (e.status == 403) {
                        if (confirm("자동 로그아웃 처리되었습니다. 로그인을 다시 시도해주세요.")) {
                            //moveLoginPage();
                        }
                    } else {}
                },
                complete: function() {
                    if (loading) {
                        fnc.endProgress(jQuery("#dimdBg"));
                    }
                    submitFlag = false;
                }
            });
        }
    };


    /*
     * fnc.paramAjax
     * note : ajax 파라미터 데이터
     */
    var paramAjax = function(callbackAjax, url, data, dataType, loading, sync, btnFlag) {
        if (typeof btnFlag == "undefined") {
            btnFlag = false;
        }

        if (!submitFlag || !btnFlag) {
            submitFlag = true;

            if (typeof dataType == "undefined") {
                dataType = "json";
            }

            if (typeof loading == "undefined") {
                loading = false;
            }

            if (typeof sync == "undefined") {
                sync = true;
            }
		       
		    
		      // JSON 형식으로 변환
		      //var jsonData = JSON.stringify(data);
		    
            jQuery.ajax({
                url: url,
                type: "post",
                timeout: 30000,
                data:data,
                //data: jsonData,
                dataType: dataType,
                //contentType : "application/json; charset=utf-8",
                async: sync,
                cache: false,
                beforeSend: function() {
                    if (loading) {
                        fnc.startProgress(jQuery("#dimdBg"));
                    }
                },
                success: function(data, status, xhr) {
					if (callbackAjax) {
                        callbackAjax(data);
                    }
                },
                error: function(e, status, xhr, data) {
                    if (e.status == 403) {
                        if (confirm("자동 로그아웃 처리되었습니다. 로그인을 다시 시도해주세요.")) {
                            //moveLoginPage();
                        }
                    } else {
                        console.log(e);
                    }
                },
                complete: function() {
                    if (loading) {
                        fnc.endProgress(jQuery("#dimdBg"));
                    }
                    submitFlag = false;
                }
            });
        }
    };

    /*
     * fnc.fileFrmAjax
     * note : ajax 파일 폼데이터
     */
    var fileFrmAjax = function(callbackAjax, url, formObj, dataType, loading, sync, btnFlag) {
        if (typeof btnFlag == "undefined") {
            btnFlag = false;
        }

        if (!submitFlag || !btnFlag) {
            submitFlag = true;

            if (typeof dataType == "undefined") {
                dataType = "json";
            }

            if (typeof loading == "undefined") {
                loading = false;
            }

            if (typeof sync == "undefined") {
                sync = true;
            }

            // 다른 parameter 추가
            var formData = new FormData();
            var formArr = formObj.serializeArray();

            formArr.forEach(function(data) {
                formData.append(data["name"], data["value"]);
            });

            // 파일 업로드
            var inputFile = $("input[type=file]");
            for (var input of inputFile) {
                if (input.files[0] != undefined) {
                    formData.append(input.name, input.files[0]);
                }
            }

            jQuery.ajax({
                url: url,
                type: "post",
                timeout: 30000,
                data: formData,
                dataType: dataType,
                async: sync,
                cache: false,
                contentType: false,
                processData: false,
                beforeSend: function() {
                    if (loading) {
                        fnc.startProgress(jQuery("#dimdBg"));
                    }
                },
                success: function(data, status, xhr) {
                    if (callbackAjax) {
                        callbackAjax(data);
                    }
                },
                error: function(e) {
                    if (e.status == 403) {
                        if (confirm("자동 로그아웃 처리되었습니다. 로그인을 다시 시도해주세요.")) {
                            //moveLoginPage();
                        }
                    } else {}
                },
                complete: function() {
                    if (loading) {
                        fnc.endProgress(jQuery("#dimdBg"));
                    }
                    submitFlag = false;
                }
            });
        }
    };

    /*
     * fnc.setCookie
     * note : 쿠키 생성하기
     */
    var setCookie = function(cName, cValue, cDay) {
        var expire = new Date();

        expire.setDate(expire.getDate() + cDay);

        // 한글 깨짐을 막기위해 escape(cValue)를 합니다.
        var cookies = cName + "=" + escape(cValue) + "; path=/ ";

        if (typeof cDay != "undefined") {
            cookies += ";expires=" + expire.toGMTString() + ";";
        }

        document.cookie = cookies;
    };

    /*
     * fnc.getCookie
     * note : 쿠키 가져오기
     */
    var getCookie = function(strName) {
        var rtn = "";
        var strCookieName = strName + "=";
        var objCookie = document.cookie;

        if (objCookie.length > 0) {
            var nBegin = objCookie.indexOf(strCookieName);

            if (nBegin < 0) {
                return rtn;
            }

            nBegin += strCookieName.length;

            var nEnd = objCookie.indexOf(";", nBegin);

            if (nEnd == -1) {
                nEnd = objCookie.length;
            }
        }

        return unescape(objCookie.substring(nBegin, nEnd));
    };

    /*
     * fnc.checkCapsLock
     * note : CapsLock 활성화 여부를 반환
     */
    var checkCapsLock = function(event, obj) {
        var keyCode = event.keyCode,
            shiftKey = event.shiftKey;

        if (((keyCode >= 65 && keyCode <= 90) && !shiftKey) || ((keyCode >= 97 && keyCode <= 122) && shiftKey)) {
            if (jQuery(obj).siblings(".capsTool").length == 0) {
                jQuery(obj).after("<span class=\"capsTool\">&lt;Caps Lock&gt;이 켜져 있습니다.</span>");

                setTimeout(function() {
                    jQuery(obj).siblings(".capsTool").remove();
                }, 3000);
            }
        }
    };

    /*
     * fnc.checkMaxLength
     * note : input maxlength 처리
     */
    var checkMaxLength = function(obj) {
        var maxLength = obj.dataset.maxlength;

        if (obj.value.length > maxLength) {
            obj.value = obj.value.substring(0, maxLength);
        }
    };

    /*
     * fnc.checkBday
     * note : 생년월일 유효성 체크
     */
    var checkBday = function(bday) {
        var bdayYear = parseInt(bday.slice(0, 4), 10);
        var bdayMonth = parseInt(bday.slice(4, 6), 10);
        var bdayDay = parseInt(bday.slice(6, 8), 10);

        if (bdayMonth < 1 || bdayMonth > 12) {
            return false;
        } else if (bdayDay < 1 || bdayDay > 31) {
            return false;
        } else if ((bdayMonth == 4 || bdayMonth == 6 || bdayMonth == 9 || bdayMonth == 11) && bdayDay == 31) {
            return false;
        } else if (bdayMonth == 2) {
            // 2월 29일(윤년) 체크
            var isleap = (bdayYear % 4 == 0 && (bdayYear % 100 != 0 || bdayYear % 400 == 0));

            if (bdayDay > 29 || (bdayDay == 29 && !isleap)) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    };

    /*
     * fnc.moveLoginPage
     * note : 로그인 페이지 이동
     */
    var moveLoginPage = function(alrtFlag) {
        if (alrtFlag) {
            alert("로그인 후 이용가능합니다.");
        }
        var loginPage = contextPath+"/login/index.do",
            pathname = location.pathname;

        if (pathname.indexOf("/login/") == -1) {
            loginPage += "?rtnUrl=" + (pathname + location.search);
        }

        location.href = loginPage;
    };

    /*
     * fnc.moveLogout
     * note : 로그아웃
     */
    var moveLogout = function(noConfirm) {
        if (!noConfirm) {
            if (confirm("로그아웃 하시겠습니까?")) {
                members.logout();
            }
        } else { 
            members.logout();
        }
    }

    /*
     * fnc.maskingName
     * note : 회원정보 마스킹 (이름) - 이름 두번째 마스킹 : 홍*동
     */
    var maskingName = function(str) {
        var rtnStr = nvl(str, "");

        if (rtnStr) {
            var len = rtnStr.length;

            if (len > 1) {
                rtnStr = rtnStr.substring(0, 1) + "*" + rtnStr.substring(2, len);
            }
        }

        return rtnStr;
    };

    /*
     * fnc.setDateFormat
     * note : input YYYYMMDD 입력 시 YYYY-MM-DD
     */
    var setDateFormat = function(str) {
        var rtnStr = str.replace(/[^0-9]/g, "");

        if (rtnStr != "" && rtnStr != null) {
            if (rtnStr.length < 5) {
                return rtnStr;
            } else if (rtnStr.length < 7) {
                rtnStr = rtnStr.substr(0, 4) + "-" + rtnStr.substr(4);
            } else {
                rtnStr = rtnStr.substr(0, 4) + "-" + rtnStr.substr(4, 2) + "-" + rtnStr.substr(6);
            }
        }

        return rtnStr;
    };

    //마우스 우측 버튼 사용 금지
    var fn_set_block_right_click = function() {
        // 모바일 여부
        var accType = checkMobile();

        if (accType == "web") {
            alert("오른쪽 버튼은 사용할 수 없습니다.");
            return false;
        } else {
            return true;
        }
    };

    //화면 드래그 사용 금지
    var fn_set_block_select = function() {
        // 모바일 여부
        var accType = checkMobile();

        if (accType == "web") {
            return false;
        } else {
            return true;
        }
    };

    //화면 드래그 사용 금지
    var fn_set_block_drag = function() {
        // 모바일 여부
        var accType = checkMobile();

        if (accType == "web") {
            return false;
        } else {
            return true;
        }
    };

    // SNS 공유하기 Layer Popup
    var fn_set_sns_sharing_layer = function() {
        if (appUserAgent.indexOf("thtAND") > -1 || appUserAgent.indexOf("thtIOS") > -1) {
            openSnsAppShare();
        } else {
            mSubScript.layerPop("sharePop");
        }
    }

    //페이스북 공유
    var fn_set_facebook = function(obj) {
        var targetUrl = encodeURIComponent(location.origin + "/sharing/bridge.do?targetUrl=" + location.pathname + location.search);

        fnc.setPopup("https://www.facebook.com/sharer/sharer.php?u=" + targetUrl, "facebook", 600, 450);
    };

    //카카오 공유
    var fn_set_kakao = function(obj) {
        var targetUrl = location.origin + "/sharing/bridge.do?targetUrl=" + encodeURIComponent(location.pathname + location.search);

        Kakao.Link.sendDefault({
            objectType: "feed",
            content: {
                title: jQuery("#og-title-value").attr("content"),
                imageUrl: jQuery("#og-image-value").attr("content"),
                link: {
                    webUrl: targetUrl,
                    mobileWebUrl: targetUrl
                }
            }
        });
    };

    //복사
    var fn_set_clipboard = function(obj) {
        jQuery(obj).attr("data-clipboard-text", jQuery("#og-url-value").prop("content"));
    };

    /*
     * jQuery(object).clearForm
     * note : form clear
     */
    jQuery.fn.clearForm = function() {
        return this.each(function() {
            var type = this.type,
                tag = this.tagName.toLowerCase();

            if (tag === "form") {
                return jQuery(":input", this).clearForm();
            }

            if (type === "text" || type === "password" || type === "hidden" || tag === "textarea") {
                this.value = "";
            } else if (type === "checkbox" || type === "radio") {
                this.checked = false;
            } else if (tag === "select") {
                this.selectedIndex = 0;
            }
        });
    };

    /*
     * jQuery.getURLParam
     * note : 파라미터 추출
     */
    jQuery.extend({
        getXssVal: function(targetValue) {
            var returnValue = targetValue;

            if (returnValue) {
                returnValue = returnValue.replace(/&lt;/g, "<");
                returnValue = returnValue.replace(/&gt;/g, ">");
                returnValue = returnValue.replace(/&#34;/g, "\"");
                returnValue = returnValue.replace(/&#37;/g, "%");
                returnValue = returnValue.replace(/&#39;/g, "\'");
                returnValue = returnValue.replace(/&#46;/g, "\.");
            }

            return returnValue;
        },
        getURLParam: function(strParamName) {
            var strHref = window.location.href;
            var cmpstring = strParamName + "=";
            var bFound = false;
            var strReturn = "";

            if (strHref.indexOf("?") > -1) {
                var aQueryString = strHref.substr(strHref.indexOf("?") + 1).split("&");

                for (var iParam = 0, length = aQueryString.length; iParam < length; iParam++) {
                    if (aQueryString[iParam].substr(0, cmpstring.length) == cmpstring) {
                        strReturn = aQueryString[iParam].split("=")[1];
                        bFound = true;
                        break;
                    }
                }
            }

            if (bFound == false) {
                return null;
            }

            return strReturn;
        },
        getURLParams: function() {
            return location.search;
        }
    });

    /*
     * show / hide / fadeIn / fadeOut
     * note : 이벤트 체크 추가
     */
    $.each(["show", "hide", "fadeIn", "fadeOut"], function(i, e) {
        var el = $.fn[e];

        $.fn[e] = function() {
            var result = el.apply(this, arguments);

            result.promise().done(function() {
                this.trigger(e, [result]);
            });

            return result;
        };
    });

    /*
     * fnc.ieVersionChk
     * note :  IE 브라우저 버전 체크
     */
    var fn_ie_version_chk = function() {
        var version = "N/A";
        var word;
        var agent = navigator.userAgent.toLowerCase();
        var name = navigator.appName;

        // IE old version ( IE 10 or Lower )
        if (name == "Microsoft Internet Explorer") {
            word = "msie ";
        } else {
            if (agent.search("trident") > -1) {
                // IE 11
                word = "trident/.*rv:";
            } else if (agent.search("edge/") > -1) {
                // IE 12 (Microsoft Edge)
                word = "edge/";
            }
        }

        var reg = new RegExp(word + "([0-9]{1,})(\\.{0,}[0-9]{0,1})");

        if (reg.exec(agent) != null) {
            version = RegExp.$1 + RegExp.$2;
        }

        return version;
    };

    /*
     * fnc.fn_numberComma
     * note : 1000단위로 콤마
     */
    var fn_numberComma = function(value) {
        return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    };

    /*
     * fnc.fn_detail
     * note : 페이지 상세
     */
    var fn_detail = function(url) {
        url += url.indexOf('?') > -1 ? '&' : '?';

        var param = location.href.split('?')[1] || '';
        if (param !== '') param = "rtnParam=" + encodeURIComponent(param);

        location.href = url + param;
    }

    /*
     * fnc.search_more
     * note : 더보기
     */
    var SearchMore = function(initObj) {
        this.form = initObj.form; //폼객체
        this.container = initObj.container; //list 컨테이너
        this.moreBtn = initObj.moreBtn; // 더보기 버튼
        this.url = initObj.url; //통신 url
        this.pageIndex = initObj.pageIndex || 1;
        this.listCnt = initObj.listCnt // || 10;
        this.isParam = true; //파라미터 url 올릴 것 인지
        this.isLoding = true; // 로딩바
        this.param = "";
        this.callbackFunc = initObj.callbackFunc;
        this.totCnt = 0;

        this.search = function() {
			this.form.find("input[name^=initIndex]").val(1);
			this.form.find("input[name^=pageIndex]").val(this.pageIndex);
			
			var _this = this;
			fnc.frmAjax(function(r){
				_this.container.html(r);
				
				// 게시글 수
				if(_this.container.find(".typeB").length > 0)
				{	
					_this.totCnt = _this.container.find("a:eq(0)").data("totCnt");
				}
				else
				{	
					_this.totCnt = _this.container.find("div:eq(0)").data("totCnt");
				}
				
				// 페이징 처리 -> 더보기 버튼 숨길지 여부
				var totCnt = parseInt(_this.totCnt);
				var pageIndex = parseInt(_this.pageIndex);
				var listCnt = parseInt(_this.listCnt);
				
				if(totCnt / (pageIndex * listCnt) <= 1) {
					_this.moreBtn.hide();
				} else {
					_this.moreBtn.show();
					
					var remainCnt = totCnt - (pageIndex * listCnt);
					//_this.moreBtn.find(".total").text(remainCnt);
					
					_this.moreBtn.off("click");
					$(_this.moreBtn).on("click", function() {
						_this.pageIndex = pageIndex + 1;
						_this.form.find("input[name^=pageIndex]").val(_this.pageIndex);
						_this.more();
					});
					
				}
				
				_this.callbackFunc();
			}, this.url, this.form, "html", this.isLoding, true, false);
			
			if(this.isParam) {
				this.setParam(false);
			}
		};

        this.more = function() {
            this.form.find("input[name^=initIndex]").val(this.pageIndex);
            var _this = this;

            fnc.frmAjax(function(r) {
                _this.container.append(r);

                var pageIndex = parseInt(_this.pageIndex);
                var listCnt = parseInt(_this.listCnt);
                //_this.totCnt = _this.container.find("div:eq(0)").siblings("div").eq((pageIndex * listCnt)-(listCnt+1)).data("totCnt");
                if (_this.container.find(".typeB").length > 0) {
                    _this.totCnt = _this.container.find("a:eq(0)").siblings("a").eq((pageIndex * listCnt) - (listCnt + 1)).data("totCnt");
                } else {
                    _this.totCnt = _this.container.find("div:eq(0)").siblings("div").eq((pageIndex * listCnt) - (listCnt + 1)).data("totCnt");
                }
                var totCnt = parseInt(_this.totCnt);

                if (totCnt / (pageIndex * listCnt) <= 1) {
                    _this.moreBtn.hide();
                } else {
                    var remainCnt = totCnt - (pageIndex * listCnt);
                    //_this.moreBtn.find(".total").text(remainCnt);

                    _this.moreBtn.off("click");
                    $(_this.moreBtn).on("click", function() {
                        _this.pageIndex = pageIndex + 1;
                        _this.form.find("input[name^=pageIndex]").val(_this.pageIndex);
                        _this.more();
                    });
                }

                _this.callbackFunc();
            }, this.url, this.form, "html", this.isLoding, true, false);

            if (this.isParam) {
                this.setParam(true);
            }
        };

        this.setParam = function(isMore) {
            var urlString = document.location.href.split("?")[0] + "?";

            if (!isMore) {
                this.param = this.form.serialize();
                urlString += this.param;
            } else {
                var searchParams = new URLSearchParams(this.param);
                searchParams.set("pageIndex", this.pageIndex);
                urlString += searchParams.toString();
            }

            history.pushState(null, null, urlString);
            setHistoryUrl();
        }
    }

    // 장바구니
    var cartBtn = function(brchCd, yy, lectSmsterCd, lectCd, lectStatCd) {
        fnc.bscAjax(function(data) {
            if (data.lgnYn) {
                fnc.paramAjax(function(data) {
                    var rtnMap = data.rtnMap;
                    if (rtnMap.result == "S") {

                        if (Number($('div.util_area p.cart_icon span.cart_num').text()) < 50) {
                            $('div.util_area p.cart_icon').addClass('on');
                            // 장바구니 카운트 증가
                            $('div.util_area p.cart_icon span.cart_num').text(Number($('div.util_area p.cart_icon span.cart_num').text()) + 1);
                            $('div.m_navi_bar div.navi_wrap a.cart_btn p.icon span.cart_num').text(Number($('div.m_navi_bar div.navi_wrap a.cart_btn p.icon span.cart_num').text()) + 1);
                        }

                        if (confirm(rtnMap.msg)) {
                            location.href = "/mypage/cart/list.do";
                        }
                    } else {
                        alert(rtnMap.msg);
                        return;
                    }
                }, "/mypage/cart/insert.ajax", {
                    brchCd: brchCd,
                    yy: yy,
                    lectSmsterCd: lectSmsterCd,
                    lectCd: lectCd,
                    lectStatCd: lectStatCd
                }, "json", false, false);
            } else {
                if (confirm("로그인이 필요한 서비스입니다.")) {
                    fnc.moveLoginPage();
                }
            }
        }, "/lgnCheck.ajax");
    }

    // 마이페이지 만족도평가 confirm
    var confirmMyEval = function(obj) {
        if ($(obj).hasClass("active")) return;
        if ($(".mypage_box.eval").data("cnt") == 0) return;

        setTimeout(function() {
            if (confirm("작성하실 만족도 평가가 있습니다. 바로 작성하시겠습니까?")) {
                location.href = "/mypage/teachereval/list.do";
            }
        }, 600);
    }

    var back = function() {
        // location.href = document.referrer;
        if (sessionStorage.historyUrl) {
            var historyUrlArr = sessionStorage.historyUrl.split(',');
            if (location.href.indexOf("/payment/") == -1) historyUrlArr.pop();
            var gotoUrl = historyUrlArr.pop();
            sessionStorage.setItem('historyUrl', historyUrlArr.join(','));
            if (gotoUrl !== undefined) {
                location.href = gotoUrl;
            } else {
                location.href = '/';
            }
        } else {
            location.href = '/';
        }
    }

    var setHistoryUrl = function() {
        if (location.href.indexOf("/payment/") > -1) return;
        var historyUrl = sessionStorage.historyUrl;
        var historyUrlArr = historyUrl ? historyUrl.split(',') : [];

        var findHistoryUrl = historyUrlArr.find(ele => ele.indexOf(location.pathname) > -1);
        if (findHistoryUrl !== undefined)
            historyUrlArr.splice(historyUrlArr.indexOf(findHistoryUrl), 1);

        historyUrlArr.push(location.href);
        sessionStorage.setItem('historyUrl', historyUrlArr);
    }

    var convertHtml = function(value) {
        if (value !== undefined && value != "") {
            value = value.replace(/&/gi, '&amp;');
            value = value.replace(/"/gi, '&quot;');
            value = value.replace(/'/gi, '&#039;');
            value = value.replace(/</gi, '&lt;');
            value = value.replace(/>/gi, '&gt;');
        }

        return value;
    }

    var returnHtml = function(value) {
        if (value !== undefined && value != "") {
            value = value.replace(/&quot;/gi, '"');
            value = value.replace(/&#039;/gi, "'");
            value = value.replace(/&lt;/gi, '<');
            value = value.replace(/&gt;/gi, '>');
            value = value.replace(/&amp;/gi, '&');
            value = value.replace(/&#40;/gi, '(');
            value = value.replace(/&#41;/gi, ')');
        }

        return value;
    }

    jQuery(document).ready(function() {
        //SNS 공유하기중 URL복사 객체는 미리 생성되어있어야 한다.
        if (jQuery("#snsUrl").length > 0) {
            var clip = new ClipboardJS("#snsUrl");

            clip.on("success", function(e) {
                alert("URL이 복사되었습니다. 원하시는 곳에 붙여넣기 (Ctrl+V) 해주세요.");
            });

            clip.on("error", function(e) {
                alert("복사 지원하지 않는 브라우져입니다.");
            });
        }

        setHistoryUrl();

    });

    return {
        nvl: nvl,
        replaceAll: replaceAll,
        startProgress: startProgress,
        endProgress: endProgress,
        bscAjax: bscAjax,
        frmAjax: frmAjax,
        paramAjax: paramAjax,
        fileFrmAjax: fileFrmAjax,
        setCookie: setCookie,
        getCookie: getCookie,
        checkCapsLock: checkCapsLock,
        checkMaxLength: checkMaxLength,
        checkBday: checkBday,
        moveLoginPage: moveLoginPage,
        moveLogout: moveLogout,
        maskingName: maskingName,
        setDateFormat: setDateFormat,
        setBlockRightClick: fn_set_block_right_click,
        setBlockSelect: fn_set_block_select,
        setBlockDrag: fn_set_block_drag,
        setSnsSharingLayer: fn_set_sns_sharing_layer,
        setFaceBook: fn_set_facebook,
        setKakao: fn_set_kakao,
        setClipboard: fn_set_clipboard,
        ieVersionChk: fn_ie_version_chk,
        fn_numberComma: fn_numberComma,
        SearchMore: SearchMore,
        detail: fn_detail,
        cartBtn: cartBtn,
        back: back,
        confirmMyEval: confirmMyEval,
        convertHtml: convertHtml,
        returnHtml: returnHtml
    }
}());