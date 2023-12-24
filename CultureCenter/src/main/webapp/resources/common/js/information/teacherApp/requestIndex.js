var requestIndexCtrl = (function(){
	
    var popupType;

    "use strict";

    var get_layer = function(type){
        popupType = type;
        
        // 강사지원 팝업
        if(type == "teacher"){
            $("#popupTitle").html("롯데문화센터 강사 지원하기");
            fnc.paramAjax(open_popup, "/information/application/teacher/request.do",{}, "html", false, false, true);
        }
        // 제휴신청 팝업
        else if(type == "cooper"){
            $("#popupTitle").html("롯데문화센터 제휴 신청하기");
            fnc.paramAjax(open_popup, "/information/application/cooperation/request1.ajax",{}, "html", false, false, true);
        }

    }
    
    var make_new_btn_close = function(func){
        $("#application_popup .btn_close").remove();

        var btnObj = $('<a class="btn_close" href="javascript:" title="닫기" onclick="'+ func +'"></a>');
        btnObj.append('<span class="blind"></span>');
        $("#application_popup #popupContent").after(btnObj);
    }
	
    var open_popup = function(html){
    
        if($('#wrap').data('isLogin') == "Y"){//로그인 여부 체크 추가
    
        var $layer = $("#application_popup");
        var $con = $("#popupContent");
        var cont = $(".cont_wrap");

        $con.html(html);

		}else{	
			if(confirm("로그인이 필요한 서비스입니다.")){
					fnc.moveLoginPage();
			}
		}
		
        if(typeof init_alert === 'function'){
            init_alert(); //alert
            init_alert = null;
        }
        
        $layer.css("display", "block");
        
        commonScript.openPopupFn("#application_popup", cont, null);
        commonScript.formChkFn();   //셀렉트박스 스크립트 초기화
        filterSelect();

        if(popupType == "teacher"){
            make_new_btn_close("teacherRequestCtrl.btnClose()");

            var selfIntrdnCont = $("textarea[name=selfIntrdnCont]").get(0);
            var lectIntrdnCont = $("textarea[name=lectIntrdnCont]").get(0);
            if(selfIntrdnCont && lectIntrdnCont){
                tcCommon.textareaOnkeyup(selfIntrdnCont);
                tcCommon.textareaOnkeyup(lectIntrdnCont);
            }
        }
        else if(popupType == "cooper"){
            make_new_btn_close("cooperRequestCtrl.btnClose()");
        }

    }

    return {
        getLayer : get_layer,
    }
}());