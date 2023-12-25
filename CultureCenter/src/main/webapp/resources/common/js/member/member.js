var members = (function() {

    "use strict";

    var sso = null;
    var ssoObj = {};
    var ssoRtn = {};

    var isLogin = $("#wrap").data("isLogin");
    var isMobile = $("#wrap").data("isMobile");
    var isApp = $("#wrap").data("isApp");

    var verif_code = null;
    var fn_sendSms = function() {
        var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
			
    	var phoneNumber = $("#phone").val();
		$.ajax({
		  type: "POST",
		  url: "/sendSMS",
		  data: {phoneNumber:phoneNumber},
		  cache: false,
          beforeSend: function(xhr) {
              xhr.setRequestHeader(header, token);
          },
		  success: function(data, status, xhr){		    		      
		      if(data.all[4] == 1){  // data.all[4] == success_count
		          alert('인증번호 발송이 완료되었습니다.\n휴대폰에서 인증번호를 확인해주세요.');
		          verif_code = data.all[1];
		      } else {
		      	  alert("휴대폰 번호가 올바르지 않거나 서버에 문제가 있습니다.\n잠시후 다시 시도해주세요.");
		      }
		  }
	    }); // sendSMS.ajax
    } // fn_sendSms     
    
    
    var fn_phoneChk = function() {
		if($("#verif_code").val() == verif_code){
		    alert('인증성공')
		}else{
		    alert('인증실패')
	    } // if
    } // fn_phoneChk
    
    
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
    	sendSms: fn_sendSms,
      	phoneChk: fn_phoneChk,
        callScreen: fn_call_screen,
        login: fn_login,
        logout: fn_logout
    }
}());