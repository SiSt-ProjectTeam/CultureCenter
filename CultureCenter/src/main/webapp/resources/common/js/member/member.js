var members = (function() {

    "use strict";

    var sso = null;
    var ssoObj = {};
    var ssoRtn = {};

    var isLogin = $("#wrap").data("isLogin");
    var isMobile = $("#wrap").data("isMobile");
    var isApp = $("#wrap").data("isApp");
    

    var fn_idChk = function() {
		var regexId = /^[a-zA-Z0-9_-]{3,16}$/;
    	var inputId = $("#id").val();
    	var idchkMsg = $('#idchkMsg');
    	var idConfirmed = $('#idConfirmed');

        if (regexId.test(inputId)) {    		
            $.ajax({
                url: '/login/idCheck',
                type: "get",
                data: {
                    "memberId": inputId
                },
                dataType: 'text',
                success: function(data) {
                    if (data == 'redundancy') {
                        idchkMsg.text('중복된 아이디입니다.').css('color', 'red');
                        idConfirmed.val('N');
                    } else if (data == 'noredundancy') {
                        idchkMsg.text('사용가능한 아이디입니다.').css('color', 'green');
                        idConfirmed.val('Y');
                    } 
                }
            }); // ajax
        } else {
            idchkMsg.text('영어 또는 숫자 3~16자리').css('color', 'red');
            idConfirmed.val('N');            
        } // if
    } // fn_idChk
    
    
    var fn_pwChk = function() {
        var pw = $('#pw').val();
        var pw2 = $('#pw2').val();
        var pwChkMsg = $('#pwChkMsg');
    	var pwConfirmed = $('#pwConfirmed');

        if (pw !== '' && pw2 !== '') {
            if (pw.length >= 4 && pw.length <= 15) {
                if (pw === pw2) {
                    pwChkMsg.text('비밀번호가 일치합니다.').css('color', 'green');
                    pwConfirmed.val('Y');
                } else {
                    pwChkMsg.text('비밀번호가 일치하지 않습니다.').css('color', 'red');
                    pwConfirmed.val('N');
                }
            } else {
                pwChkMsg.text('4~15자리').css('color', 'red');
                pwConfirmed.val('N');
            }
        } else {
            pwChkMsg.empty(); // 한 비밀번호라도 비어 있으면 메시지를 지운다.
            pwConfirmed.val('N');
        }    	
    } // fn_pwChk
    
    
    var verif_code = null;
    var fn_sendSms = function() {
        var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		
        var phoneChkMsg = $('#phoneChkMsg');			
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
		      if(data.all[2].childNodes[1].innerHTML == 1){  // success_count == 1
		      	$("#phone_verif").show();
		        alert('인증번호 발송이 완료되었습니다.\n휴대폰에서 인증번호를 확인해주세요.');
		        verif_code = data.all[1].innerHTML;
		      } else {
		      	$("#phone_verif").hide();
		      	alert("휴대폰 번호가 올바르지 않거나 서버에 문제가 있습니다.\n잠시후 다시 시도해주세요.");
		      }
		      phoneChkMsg.hide();
		  }
	    }); // sendSMS.ajax
    } // fn_sendSms         
    
    var fn_phoneChk = function() {
    	var phoneConfirmed = $('#phoneConfirmed');
        var phoneChkMsg = $('#phoneChkMsg');
        
		if($("#verif_code").val() == verif_code){
		    $("#phone_verif").hide();
		    phoneConfirmed.val('Y');
		    $("#phone").prop("readOnly", true);
		    $("#sendSMS").prop("disabled", true);
		    phoneChkMsg.text('인증 성공했습니다.').show();
		}else{		
		    phoneConfirmed.val('N');
		    phoneChkMsg.text('인증 실패했습니다.').show();
	    } // if
    } // fn_phoneChk
    
    
    var fn_execDaumPostcode = function() {
    	new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	            var roadAddr = data.roadAddress; // 도로명 주소 변수
	            var extraRoadAddr = ''; // 참고 항목 변수
	
	            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                extraRoadAddr += data.bname;
	            }
	            // 건물명이 있고, 공동주택일 경우 추가한다.
	            if(data.buildingName !== '' && data.apartment === 'Y'){
	               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	            }
	            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	            if(extraRoadAddr !== ''){
	                extraRoadAddr = ' (' + extraRoadAddr + ')';
	            }
	
	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
	            document.getElementById('postcode').value = data.zonecode;
	            document.getElementById('roadAddress').value = roadAddr;
	            document.getElementById('jibunAddress').value = data.jibunAddress;
	     
	            document.getElementById("engAddress").value = data.addressEnglish;
	                   
	            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
	            if(roadAddr !== ''){
	                document.getElementById('extraAddress').value = extraRoadAddr;
	            } else {
	                document.getElementById('extraAddress').value = '';
	            }
	
	            var guideTextBox = document.getElementById('guide');
	            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
	            if(data.autoRoadAddress) {
	                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
	                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
	                guideTextBox.style.display = 'block';
	
	            } else if(data.autoJibunAddress) {
	                var expJibunAddr = data.autoJibunAddress;
	                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
	                guideTextBox.style.display = 'block';
	            } else {
	                guideTextBox.innerHTML = '';
	                guideTextBox.style.display = 'none';
	            }
	        }
	    }).open();
    } // fn_execDaumPostcode
    
    
    var fn_joinSubmit = function() {
        if ($('#idConfirmed').val() == 'N') {
            alert("아이디를 올바르게 입력해주세요.");
            event.preventDefault(); // 폼 제출 방지
            return;
        }

        if ($('#pwConfirmed').val() == 'N') {
            alert("비밀번호를 올바르게 입력해주세요.");
            event.preventDefault();
            return;
        }

        if ($('#name1').val() == '') {
            alert("이름을 입력해주세요.");
            event.preventDefault();
            return;
        }

        if ($('#email').val() == '') {
            alert("이메일을 입력해주세요.");
            event.preventDefault();
            return;
        }

        if ($('#phoneConfirmed').val() == 'N') {
            alert("전화번호 인증이 필요합니다.");
            event.preventDefault();
            return;
        }

		if ($('#phoneConfirmed').val() == '') {
            alert("생년월일 입력이 필요합니다.");
            event.preventDefault();
            return;
        }
        
        // 주소 유효성 검사
        var postcode = $.trim($('#postcode').val());
        var roadAddress = $.trim($('#roadAddress').val());
        var detailAddress = $.trim($('#detailAddress').val());

        if (!postcode || !roadAddress || !detailAddress) {
            alert("주소를 모두 입력해주세요.");
            event.preventDefault(); // 폼 제출 방지
            return;
        }
    } // fn_joinFormChk
    
    
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
    	idChk: fn_idChk,
    	pwChk: fn_pwChk,
      	phoneChk: fn_phoneChk,
      	execDaumPostcode: fn_execDaumPostcode,
      	joinSubmit: fn_joinSubmit,
        callScreen: fn_call_screen,
        login: fn_login,
        logout: fn_logout
    }
}());