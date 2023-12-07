var sharePop = (function() {

    "use strict";

    /* Set Popup */
    var fn_set_popup = function(pUrl, pName, pSw, pSh, obj) {
        //스크린의 크기
        var cw = screen.availWidth;
        var ch = screen.availHeight;
        var sw = pSw;
        var sh = pSh;
        var ml = (cw - sw) / 2;
        var mt = (ch - sh) / 2;
        var baseDt = "";

        window.open(pUrl, pName, "width=" + sw + ",height=" + sh + ",top=" + mt + ",left=" + ml + ",location=no,menubar=no,toolbar=no,scrollbars=yes,resizable=no,copyhistory=no");
    };

    //페이스북 공유
    var fn_btn_faceBook = function() {
        $('#btnFaceBook').on("click", function() {
            var targetUrl = encodeURIComponent(jQuery("#og-url-value").attr("content"));

            fn_set_popup("https://www.facebook.com/sharer/sharer.php?u=" + targetUrl, "facebook", 600, 450);

        })
    };

    //트위터 공유
    var fn_btn_twitter = function() {
        $('#btnTwitter').on("click", function() {
            var sendText = encodeURIComponent(jQuery("#og-title-value").attr("content"));
            var sendUrl = encodeURIComponent(jQuery("#og-url-value").attr("content"));

            fn_set_popup("https://twitter.com/intent/tweet?text=" + sendText + "&url=" + sendUrl);

        })
    };

    //카카오공유
    var fn_btn_kakao = function() {
        $('#btnKakao').on("click", function() {

            if (!Kakao.isInitialized()) {
                Kakao.init($(this).data("key"));
            };

            var targetUrl = encodeURIComponent(jQuery("#og-url-value").attr("content"));

            Kakao.Link.sendDefault({
                objectType: "feed",
                content: {
                    title: jQuery("#og-title-value").attr("content"),
                    imageUrl: jQuery("#og-image-value").attr("content"),
                    link: {
                        webUrl: location.href,
                        mobileWebUrl: location.href
                    }
                }
            });
        })

    }

    //클립보드 세팅
    var clipboardInit = function() {
        var clipboardObj = new ClipboardJS("#btnClipboard");

        clipboardObj.on("success", function(e) {
            alert("주소가 복사되었습니다.");
        });

        clipboardObj.on("error", function(e) {
            alert("주소복사 기능이 지원되지 않습니다.");
        });
    }

    //URL 복사
    var fn_btn_clipboard = function() {
        $('#btnClipBoard').on("click", function() {
            var $temp = $("<input>");
            $("body").append($temp);
            $temp.val(location.href).select();
            var is = document.execCommand("copy");
            $temp.remove();
            if (is) {
                alert("복사되었습니다");
            } else {
                alert("지원하지 않는 브라우져입니다.");
            }
        })

    };

    $(document).ready(function() {
        //SNS 공유하기중 URL복사 객체는 미리 생성
        clipboardInit();
        fn_btn_kakao();
        fn_btn_faceBook();
        fn_btn_twitter();
        fn_btn_clipboard();
    });

    return {
        setPopup: fn_set_popup,
        btnKakao: fn_btn_kakao,
        btnFaceBook: fn_btn_faceBook,
        btnTwitter: fn_btn_twitter,
        btnClipBoard: fn_btn_clipboard
    }
}());