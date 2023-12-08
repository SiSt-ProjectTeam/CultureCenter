var tcCommon = (function(){
    
    "use strict";
    
    var reset_input = function(selector){
        $(selector).val("");
        $(selector).siblings(".input_btn_wrap").find("button").css("display", "none");
    }
    var reset_selectbox = function(selector, defaultTxt){
        $(selector).val("");

        var $select_div = $(selector).closest(".form_select_div");
        $select_div.removeClass("change");
        $select_div.find(".open_area .btn_open span").eq(0).html(defaultTxt);
        $select_div.find(".list_wrap .scroll_wrap a.on").removeClass("on");

    }
    
    var validate_date_format = function(str){
        try {
            if(str.length != 8) throw new Error();

            var year = str.slice(0, 4);
            var month = str.slice(4, 6);
            var day = str.slice(6, 8);
            var date = new Date(year+"-"+month+"-"+day);
            
            if(date == "Invalid Date") throw new Error();
            //윤달체크
            if(date.getMonth() + 1 != month) throw new Error();

        } catch(err) {
            alert("올바르지 않은 날짜입니다.");
            return false;
        }

        return true;
    }

    var validate_info = function(valiList){
        for(var vali of valiList){
            // 공백체크
            if(vali && !vali.$obj.val().trim()){
                vali.$obj.val("");
                alert(vali.msg);
                focus_object(vali.$obj);
                return false;
            }

            //날짜 체크
            if(vali && vali.$obj.get(0).dataset.type == "date"){
                if(!validate_date_format(vali.$obj.val())) {
                    focus_object(vali.$obj);
                    return false;
                }
            }
        }

        return true;
    }
    // 강사지원 학력 및 경력정보 유효성 체크
    var validate_infos = function(valiList){
        
        var itemLen = valiList[0].$obj.length;
        for(var i = 0; i < itemLen; i++){
            var paramList = [];
            for(var vali of valiList){
                paramList.push({
                    $obj: vali.$obj.eq(i), msg: vali.msg
                });
            }
            if(i == itemLen-1 && !has_value_infos(paramList)){
                // 마지막 입력 폼
                break; 
            }
            if(!validate_info(paramList)){
                return false;
            }
        }

        return true;
    }
    function has_value_infos(valiList){
        for(var vali of valiList){
            // 공백체크
            if(vali.$obj.val()){
                return true;
            }
        }
        return false;
    }

    var focus_object = function($obj){
        if($obj.attr("type") !== "hidden"){
            $obj.focus();
            return;
        }
        
        // selectbox 인 경우 open_area 안에 input[hidden]
        $obj.parent('.open_area').find('a.btn_open').focus();
    }

    // 셀렉트박스 값 변경시
    var select_option = function(obj, value){
        var $textDom;

        $(obj).siblings().removeClass("on");
        $(obj).addClass("on");

        if($(obj).parents(".form_select_div").length > 0){
            $(obj).parents(".form_select_div").addClass("change");
            $textDom = $(obj).parents(".form_select_div").find(".open_area btn_open span");
            $textDom.text(value);
        }
        else if($(obj).parents(".btn_wrap").length > 0){
            $(obj).parents(".btn_wrap").addClass("change");
            $textDom = $(obj).parents(".btn_wrap").find(".filter_open_area a div");
            $textDom.text(value);
        }

        select_option_click(obj);
    }

    // 셀렉트박스 옵션 클릭시 input값 변경
    var select_option_click = function(obj){
        var $inputObj;
        if($(obj).parents(".form_select_div").length > 0){
            $inputObj = $(obj).parents(".form_select_div").find(".open_area input");
        }
        else if($(obj).parents(".btn_wrap").length > 0){
            $inputObj = $(obj).parents(".btn_wrap").find(".filter_open_area input");
        }

        $inputObj.val(obj.dataset.value); 
    }

    //이메일
    var select_option_click_email = function(obj){
        select_option_click(obj);
        
        $("#emailAddrCd").val(obj.dataset.value);
        $("#emailAddrCd")[0].dataset.oldValue = obj.dataset.value;
        if(obj.dataset.value){
            $("#emailAddrCd").siblings().hide();
            $("#emailAddrCd").attr("readonly", true);
        } else {
            $("#emailAddrCd").siblings().show();
            $("#emailAddrCd").attr("readonly", false);
        }
    }

    //textarea
    var textarea_onkeyup = function(obj){
        fnc.checkMaxLength(obj);
        $(obj).parents(".form_textarea").find(".check_byte .r_byte").html(obj.value.length);
    }

    // 업로드 파일 검증
    var validate_upload_file = function(obj){
        const imageInput = $(obj)[0];

        var extensionIndex = imageInput.value.lastIndexOf(".");
        var extensionName = imageInput.value.substring(extensionIndex);
        if(imageInput.accept.indexOf(extensionName.toLowerCase()) == -1){
            alert("등록 불가한 확장자입니다.");
            imageInput.value = "";
            return;
        }

        if(imageInput.files[0].size > imageInput.dataset.maxSize * 1024){   //kb 기준
            alert("등록 가능한 파일 크기를 초과했습니다.");
            imageInput.value = "";
            return;
        };

        return true;
    }

    // 사진 업로드 미리보기
    var set_image_preview = function(obj){
        if(!validate_upload_file(obj)) return;
        
        var imagePreview = $(obj).closest(".upload_div").find(".upload_img .img_div img[name=imagePreview]")[0];
        imagePreview.src = window.URL.createObjectURL(obj.files[0])
        $(obj).closest(".upload_div").addClass("complete");
        $('input[id=imgPre]').val(imagePreview);
		//var imgPre = document.getElementById("imgPre").value;

    }

    // 업로드 버튼 클릭 이벤트
    var fn_upload_btn_onclick = function(obj){
        $(obj).closest(".upload_div").find("input[type=file]").click();
    }

    var delete_image_file = function(obj){
        var $uploadDiv = $(obj).closest(".upload_div");
        
        var hiddenInput = $uploadDiv.find('input[type=hidden]');
        if(hiddenInput){
            $uploadDiv.find('input[type=file]').attr('id', hiddenInput.attr('name'));    
            hiddenInput.remove();
        }
        $uploadDiv.find('input[type=file]').val('');
        
        $uploadDiv.removeClass("complete");
    }

    var fn_check_number_onkeyup = function(obj){
        var value = obj.value;
        var pattern = eval("/[^0-9]/g");
        var regResult = pattern.test(value);
        if(value && regResult){
            obj.value = obj.dataset.oldValue;
        } else {
            obj.dataset.oldValue = obj.value;
        }
    }

    var fn_check_email_onkeyup1 = function(obj){
        var value = obj.value;
        var pattern = eval("/^[_a-zA-Z0-9-\\.\\_]+$/g");
        var regResult = pattern.test(value);
        if(value && !regResult){
            obj.value = obj.dataset.oldValue;
        } else {
            obj.dataset.oldValue = obj.value;
        }
    }
    
    var fn_check_email_onkeyup2 = function(obj){
        var value = obj.value;
        var pattern = eval("/^[\\.a-zA-Z0-9-]+$/g");
        var regResult = pattern.test(value);
        if(value && !regResult){
            obj.value = obj.dataset.oldValue;
        } else {
            obj.dataset.oldValue = obj.value;
        }
    }

    // textarea에 붙여넣기 시 글자수 업데이트 기능
    document.addEventListener('paste', function(e) {
        if(e.target.tagName === 'TEXTAREA') {
            setTimeout(function(){
                tcCommon.textareaOnkeyup(e.target);
            },100);
        }
    });

    return {
        resetInput: reset_input,
        resetSelectbox: reset_selectbox,
        validateInfo: validate_info,
        validateInfos: validate_infos,
        selOpt: select_option,
        selOptClick: select_option_click,
        selOptClick_email: select_option_click_email,
        textareaOnkeyup: textarea_onkeyup,
        deleteFile: delete_image_file,
        setImgPreview: set_image_preview,
        upload_onclick: fn_upload_btn_onclick,
        focusObject: focus_object,
        checkNumberOnkeyup: fn_check_number_onkeyup,
        checkEmailOnkeyup1: fn_check_email_onkeyup1,
        checkEmailOnkeyup2: fn_check_email_onkeyup2,
    }
}());