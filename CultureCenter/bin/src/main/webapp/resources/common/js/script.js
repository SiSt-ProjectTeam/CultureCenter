var _this_scroll = 0; // 스크롤 up & down 체크위한 변수
var _isScrollTop; // scrollTop 변수
var _pageScrollOffset; // dimd 시 scrollTop 값 기억
var _beforeScrollOffset;
var _device = ''; // 접속 device 체크 위한 변수
var _deviceCondition = ''; // 해상도 따른 device 체크 위한 변수
var _browser = ''; // browser 체크 위한 변수
var _anchorPdNum // 상단 여백 값
var _betweenSpace;
var _thrDepSwiper; // 3depth 스와이퍼
var _filterTop; // 필터 상단 top값
var _barWidthArr = [];
var _newsDetailSwiper; // 코오롱 뉴스 상세 리스트
var _viewConTitHeight; // view 페이지 title 높이값
var _isMotionOnce = false; // resize 안에서 모션 한번만 작동하기 위한 변수
var _mainPopSwiper; // 메인팝업
var _mGnbAccordion; // mobile header accordion 변수
var _headerHeightArr = []; // header
var _headerMaxHeight = 0; // header
var _pathLength // top 버튼 stroke-dasharray
var _srchDataSwiper; // 데이터 검색, 선택 초기화 swiper 변수
var _popScrollTop;
var _fooH // 푸터 Height값
var _visualSwiper; // 비주얼 스와이퍼 변수
var _videoTime; // 비디오 영상 시간
var _progressBarMotion;
var _paybarH;
var _fixedBtnH;
var _rightH;
var _isSrchOncePc = false;
var _isSrchOnceMo = false;
var _winWidth = 0;

var commonScript = (function() {
    return {
        deviceChk: function() {
            // 디바이스 체크
            function isTouchscreen() { // iPad 접속 OS13부터 Macintosh으로 인식
                return (navigator.maxTouchPoints > 0) ? true : false;
            }

            if (/Android/i.test(navigator.userAgent)) {
                if (isTouchscreen()) {
                    _device = 'android';
                } else {
                    _device = 'pc';
                }
            }

            if (/iPhone|iPad|iPod/i.test(navigator.userAgent)) {
                if (isTouchscreen()) {
                    _device = 'ios';
                    $("html").addClass("ios")
                } else {
                    _device = 'pc';
                }
            }

            // 브라우저 체크
            var agent = navigator.userAgent.toLowerCase(),
                name = navigator.appName;

            if (name === 'Microsoft Internet Explorer' || agent.indexOf('trident') > -1 || agent.indexOf('edg/') > -1) {
                if (isTouchscreen()) {
                    _device = 'mobileIe';
                } else {
                    _device = 'pcIe';
                }
                _browser = 'ie';
                $("html").addClass("ie");
            } else if (agent.indexOf('Chrome') > -1 || window.navigator.userAgent.indexOf('CriOS') > -1) { // Chrome
                if (isTouchscreen()) {
                    _device = 'mobileChrome';
                } else {
                    _device = 'pcChrome';
                }
                _browser = 'chrome';
                $("html").addClass("chrome");
            } else if (agent.indexOf('safari') > -1) { // Safari
                if (isTouchscreen()) {
                    _device = 'mobileSafari';
                } else {
                    _device = 'pcSafari';
                }
                _browser = 'safari';
                $("html").addClass("safari");
            } else if (agent.indexOf('firefox') > -1) { // Firefox
                _browser = 'firefox';
            }

        },
        init: function() {
            if (window.location.href.indexOf("https://ldcs.easymedia.kr/") > -1 || window.location.href.indexOf('127.0.0.1') > -1 || window.location.href.indexOf("https://eznet1.easymedia.kr") > -1) {
                if (window.location.href.indexOf("styleguide/") == -1) {
                    if ($("header").html() == '') {
                        $(".m_navi_bar").empty().load("../../../html/navigation.html");
                        $("header").empty().load("../../../html/header.html");
                        $("footer").empty().load("../../../html/footer.html", function() {
                            gsap.delayedCall(0.2, function() {
                                commonScript.headerFooterFn();
                                commonScript.formChkFn();
                                // commonScript.commonFn();
                                // popupResize();
                                // commonScript.resizeFn();
                                // commonScript.scrollFn();
                            });
                        });
                    }
                }
            }

            var wrap = document.getElementById("wrap");
            var scrollBar = window.innerWidth - $(window).width();
            _winWidth = Math.ceil(wrap.getBoundingClientRect().width) + scrollBar;
        },
        headerFooterFn: function() {
            // pc header gnb
            var gnbNum = -1;
            var headerDelayTime;

            $("header nav .gnb > li").each(function(q) {
                //$(this).mouseenter(function(){
                $(this).find(".one_depth").on("mouseenter focusin", function() {
                    if (_winWidth > 1024) {
                        if (gnbNum != $(this).parents("li").index()) {
                            $("header nav .two_pack > li").removeClass("on");
                        }
                        if ($("header nav .gnb > li").eq($(this).parents("li").index()).find(".two_pack").size() > 0) {
                            $("header").addClass("mouse_hover gnb_hover drop_down")
                        } else {
                            $("header").removeClass("mouse_hover gnb_hover drop_down")
                        }

                        $(".form_select.on").find("select").blur();
                        $(".form_select").removeClass("on");

                        $(this).parents("li").siblings().removeClass("on");
                        $(this).parents("li").addClass("on");
                        $(this).parents("li").removeClass("opacity")
                        $(this).parents("li").siblings().addClass("opacity");
                        $(this).parents("li").siblings().find(".two_pack").css("z-index", "3")
                        $("header nav .gnb > li").eq($(this).parents("li").index()).find(".two_pack").css({
                            "z-index": "5",
                            "height": "414px"
                        });
                        $("header nav .gnb > li").eq($(this).parents("li").index()).find(".two_pack").fadeIn(200);

                        gnbNum = $(this).parents("li").index();
                    }
                });

                $(this).find(".two_pack li").each(function(q) {
                    $(this).find(".two_depth").on("mouseenter focusin", function() {
                        $(this).parents(".two_dep_div").find("li").removeClass("opacity")
                        $(this).parents(".two_dep_div").find("li").not($(this).parents("li")).addClass("opacity");
                        $(this).parents(".two_pack").find(".img_div .img").siblings().removeClass("on");
                        $(this).parents(".two_pack").find(".img_div .img").eq(q).addClass("on");
                        $(this).parents(".two_pack").find(".img_div .img").eq(q).fadeIn(200, function() {
                            $(this).parents(".two_pack").find(".img_div .img").not($(this).parents(".two_pack").find(".img_div .img.on")).hide();
                        });
                    });
                });
            });


            $("header nav").on("mouseleave", function() {
                if (_winWidth > 1024) {
                    setTimeout(function() {
                        $("header").removeClass("mouse_hover");
                    }, 400);

                    $("header").removeClass("drop_down gnb_hover");
                    $(".dimd").stop(true, true).fadeOut(400);
                    $("header nav .gnb > li").removeClass("on");

                    $("header nav .gnb > li .two_pack .img_div .img").removeAttr("style");
                    $("header nav .gnb > li .two_pack .img_div .img").removeClass("on");
                    $("header nav .gnb > li .two_pack .img_div .img").eq(0).addClass("on");
                    $("header nav .gnb > li .two_pack .img_div .img").eq(0).show();
                    $("header nav .gnb > li .two_pack .two_dep_div > li").removeClass("opacity");
                    $("header .gnb > li .one_depth").blur();
                    clearTimeout(headerDelayTime);
                }
            });

            // header search
            $("header .srch_icon, header .header_srch_pop_area .btn_close, header .header_srch_pop_area .inner_dimd").on("click", function() {
                if (!$(this).hasClass("active")) {
                    $("header .btn_srch, header .header_srch_pop_area .btn_close").addClass("active");
                    $("header .header_srch_pop_area .btn_close").addClass("active");
                    $("header .header_srch_pop_area .inner_dimd").addClass("active");
                    $("header .header_srch_pop_area").addClass("show");
                    $("header .header_srch_pop_area .form_search input").focus();
                    $("header .header_srch_pop_area .inner_dimd").stop(true, true).fadeIn(300);
                    $('body').addClass('stop_scroll');
                    $('#wrap').addClass('stop_scroll');
                    $(".m_navi_bar").addClass("hide");
                    $(".btn_top").addClass("law_index");
                    $("body, html").on('scroll touchmove mousewheel', function(e) {
                        e.preventDefault();
                    });

                    if ($(".page_title_area").size() > 0) {
                        $(".page_title_area .tit_popup").hide();
                        $(".m_pop_dimd").hide();
                        $(".page_title_area").removeClass("bg_white");
                        $(".page_title_area .tit_div").removeClass("active");
                    }
                    if ($(".filter_bar_area").size() > 0) {
                        $(".filter_bar_area .filter_list_wrap").hide();
                        $(".filter_bar_area .dimd").hide();
                        $(".filter_bar_area .filter_open_area .btn").removeClass("active");
                    }
                } else {
                    $("header .btn_srch, header .header_srch_pop_area .btn_close").removeClass("active");
                    $("header .header_srch_pop_area .btn_close").removeClass("active");
                    $("header .header_srch_pop_area .inner_dimd").removeClass("active");
                    $("header .header_srch_pop_area").removeClass("show");
                    $("header .header_srch_pop_area .inner_dimd").stop(true, true).fadeOut(300, function() {
                        $(".btn_top").removeClass("law_index");
                        $(".m_navi_bar").removeClass("hide");
                    });
                    $("body").removeClass("stop_scroll")
                    $("body, html").off('scroll touchmove mousewheel');
                    $('#wrap').removeClass('stop_scroll');

                    if ($(".main").size() > 0) {
                        if ($(".visual_wrap .swiper-slide-active video").size() > 0) {
                            $(".visual_div .swiper-slide-active video").get(0).currentTime = 0;
                            $(".visual_div .swiper-slide-active video").get(0).play();
                        }
                        _progressBarMotion.duration(_videoTime).restart();
                        $(".visual_div .swiper-slide-active").addClass("on");
                        $(".visual_div .swiper-slide-duplicate-active").addClass("on");
                    }
                }
            });

            // header mypage
            $(".mypage_icon, header .mypage_pop_area .btn_close, header .mypage_pop_area .inner_dimd").on("click", function() {
                if (!$(this).hasClass("active")) {
                    _pageScrollOffset = window.pageYOffset;
                    $(".m_navi_bar").addClass("hide");
                    $(".btn_top").addClass("law_index");
                    setTimeout(function() {
                        $(".mypage_pop_area").removeAttr("style");
                        $(".mypage_pop_area").innerHeight(window.innerHeight)
                    }, 10)
                    $("header .btn_srch, header .mypage_pop_area .btn_close").addClass("active");
                    $("header .mypage_pop_area .btn_close").addClass("active");
                    $("header .mypage_pop_area .inner_dimd").addClass("active");
                    $("header .mypage_pop_area").addClass("transition");
                    $("header .mypage_pop_area").addClass("show");
                    $("header .mypage_pop_area .inner_dimd").stop(true, true).fadeIn(300);
                    $('body').addClass('stop_scroll');
                    $('#wrap').addClass('stop_scroll');
                    $("body, html").on('scroll touchmove mousewheel', function(e) {
                        e.preventDefault();
                    });

                    if ($(".page_title_area").size() > 0) {
                        if ($(".page_title_area").hasClass("fixed")) {
                            $(".page_title_area").addClass("up");
                        }

                        if ($(".page_title_area").hasClass("white_ver")) {
                            $(".page_title_area").addClass("law_index");
                        }

                        $(".fixed_mobile").removeClass("move");
                        $(".page_title_area .tit_popup").hide();
                        $(".m_pop_dimd").hide();
                        $(".page_title_area").removeClass("bg_white");
                        $(".page_title_area .tit_div").removeClass("active");
                        if ($(".page_title_area").hasClass("fixed")) {
                            $(".page_title_area").addClass("law_index");
                        }
                    }
                    if ($(".filter_bar_area").size() > 0) {
                        $(".filter_bar_area .filter_list_wrap").hide();
                        $(".filter_bar_area .dimd").hide();
                        $(".filter_bar_area .filter_open_area .btn").removeClass("active");
                        $(".filter_bar_area").removeClass("high_index");
                    }
                    if ($(".fixed_btn_area").size() > 0) {
                        $(".fixed_btn_area").addClass("law_index");
                    }
                } else {
                    $("header .btn_srch, header .mypage_pop_area .btn_close").removeClass("active");
                    $("header .mypage_pop_area .btn_close").removeClass("active");
                    $("header .mypage_pop_area .inner_dimd").removeClass("active")
                    $(".page_title_area").removeClass("up");

                    // $(".fixed_mobile").addClass("move");
                    $("header .mypage_pop_area").removeClass("show");
                    $("header .mypage_pop_area .inner_dimd").stop(true, true).fadeOut(300, function() {
                        $(".btn_top").removeClass("law_index");
                        $(".m_navi_bar").removeClass("hide");
                        if (_winWidth > 1024) {
                            $("body").removeClass("stop_scroll");
                            $('#wrap').removeClass('stop_scroll');
                            $("body, html").off('scroll touchmove mousewheel');
                            gsap.to($("body, html"), 0, {
                                scrollTop: _pageScrollOffset,
                                ease: Power3.easeOut
                            });
                        }
                    });

                    if (window.innerWidth <= 1024) {
                        $("body").removeClass("stop_scroll");
                        $('#wrap').removeClass('stop_scroll');
                        $("body, html").off('scroll touchmove mousewheel');
                        gsap.to($("body, html"), 0, {
                            scrollTop: _pageScrollOffset,
                            ease: Power3.easeOut
                        });
                    }

                    setTimeout(function() {
                        if ($(".page_title_area").hasClass("law_index")) {
                            $(".page_title_area").removeClass("law_index");
                        }
                    }, 450);

                    if ($(".fixed_btn_area").size() > 0) {
                        $(".fixed_btn_area").removeClass("law_index");
                    }

                    if ($(".main").size() > 0) {
                        if ($(".visual_wrap .swiper-slide-active video").size() > 0) {
                            $(".visual_div .swiper-slide-active video").get(0).currentTime = 0;
                            $(".visual_div .swiper-slide-active video").get(0).play();
                        }
                        _progressBarMotion.duration(_videoTime).restart();
                        $(".visual_div .swiper-slide-active").addClass("on");
                        $(".visual_div .swiper-slide-duplicate-active").addClass("on");
                    }
                }
            });

            // header 나의 관심지점 설정
            $(".place_pop_area .btn_close").on("click", function() {
                $("header .place_pop_area .btn_close").removeClass("active");
                $("header .place_pop_area").removeClass("show");
                $("header .place_pop_area .for_padding").removeClass("on");
                $("header .place_pop_area .inner_dimd").stop(true, true).fadeOut(300);

                if ($(".main").size() > 0) {
                    if ($(".visual_wrap .swiper-slide-active video").size() > 0) {
                        $(".visual_div .swiper-slide-active video").get(0).currentTime = 0;
                        $(".visual_div .swiper-slide-active video").get(0).play();
                    }
                    _progressBarMotion.duration(_videoTime).restart();
                    $(".visual_div .swiper-slide-active").addClass("on");
                    $(".visual_div .swiper-slide-duplicate-active").addClass("on");
                }
            });

            // 검색게이트
            if ($(".class_gate_w").size() > 0) {
                /* $(".class_gate_w .tab_btn_area .btn").each(function(q){
          if(window.innerWidth > 1024) {
	          $(this).on("mouseover", function(){
	            if(!$(this).hasClass("on")){
	              $(".class_gate_w .tab_btn_area .btn").removeClass("on");
	              $(this).addClass("on");
	              $(".class_gate_w .tab_img_area .img").removeClass("on");
	              $(".class_gate_w .tab_img_area .img").eq(q).addClass("on");
	              $(".class_gate_w .tab_con_area .con").removeClass("on");
	              $(".class_gate_w .tab_con_area .con").eq(q).addClass("on");
	              $(".class_gate_w .tab_con_area .con .img_w").removeClass("on");
	
	              if($(".srch_gate_pop_area").hasClass("show")){
	                imgResizingFn()
	              }
	            }
	          });
          }
        });*/

                $(".class_gate_w .tab_con_area .con .list_w .list .txt").each(function(q) {
                    $(this).on("mouseover", function() {
                        if (!$(this).hasClass("on")) {
                            $(".class_gate_w .tab_con_area .con .list_w .list .txt").removeClass("on");
                            $(this).addClass("on");
                            $(".class_gate_w .tab_con_area .con .list_w .list .txt").not($(this)).find("a").addClass("opacity");
                            $(".class_gate_w .tab_con_area .con .img_w").addClass("on");
                            $(".class_gate_w .tab_con_area .con .img_w .img").eq(q).addClass("on");
                        }
                    });

                    $(this).on("mouseleave", function() {
                        $(this).removeClass("on");
                        $(".class_gate_w .tab_con_area .con .list_w .list .txt").removeClass("on");
                        $(".class_gate_w .tab_con_area .con .list_w .list .txt").find("a").removeClass("opacity");
                        $(".class_gate_w .tab_con_area .con .img_w .img_div .img").removeClass("on");
                        $(".class_gate_w .tab_con_area .con .img_w").removeClass("on");
                    });
                });

                $(".class_gate_w .tab_con_area .accordion_type .list_div .btn_flex_box .place_btn").on("click", function() {
                    $(".class_gate_w .tab_con_area .accordion_type .list_div .btn_flex_box .place_btn").removeClass("on");
                    $(this).addClass("on");
                    $(".class_gate_w").addClass("on");
                    $(".class_gate_w .btn_wrap").addClass("on");
                });

                $(".circle_menu").each(function() {
                    $(this).on("click", function() {
                        if (!$(this).hasClass("check")) {
                            $(".circle_menu").removeClass("check");
                            $(this).addClass("check");
                            $(".class_gate_w").addClass("on");
                            $(".class_gate_w .btn_wrap").addClass("on");
                        }
                    })
                });
            }

            $(".class_srch").on("click", function() {
                _pageScrollOffset = window.pageYOffset;
                $(".btn_top").addClass("law_index");
                $(".page_title_area").addClass("law_index");
                $(".m_navi_bar").addClass("hide");
                $('body').addClass('stop_scroll');
                $('#wrap').addClass('stop_scroll');
                $("body, html").on('scroll touchmove mousewheel', function(e) {
                    e.preventDefault();
                });
                if ($(".page_title_area").hasClass("fixed")) {
                    $(".page_title_area").addClass("up");
                    $(".fixed_mobile").removeClass("move");
                }
                $("header .srch_gate_pop_area .class_gate_w .tab_btn_area").addClass("fir")
                $("header .srch_gate_pop_area .class_gate_w .tab_con_area .con .list_w").addClass("fir")
                $(".srch_gate_pop_area").height(window.innerHeight)
                if (_winWidth > 1024) {
                    $(".srch_gate_pop_area").fadeIn(200, function() {
                        imgResizingFn();
                    });
                } else {
                    $(".srch_gate_pop_area").addClass("show")
                    $("footer").siblings(".dimd").fadeIn();
                    $("header").css("z-index", "101");
                }
                $(".srch_gate_pop_area .scroll_area .inner > div").addClass("active");
                $(".srch_gate_pop_area .tab_img_area .img:first-child").addClass("on");
                setTimeout(function() {
                    $("header .srch_gate_pop_area .class_gate_w .tab_con_area .con .img_w").addClass("show")
                    $("header .srch_gate_pop_area .class_gate_w .tab_btn_area").removeClass("fir")
                }, 400)
                setTimeout(function() {
                    $("header .srch_gate_pop_area .class_gate_w .tab_con_area .con .list_w").removeClass("fir")
                }, 1300)
            });

            $(".srch_gate_pop_area .btn_close").on("click", function() {
                $("footer").siblings(".dimd").fadeOut(300, function() {
                    $("header").removeAttr("style");
                });
                $("body").removeClass("stop_scroll")
                $('#wrap').removeClass('stop_scroll');
                $("body, html").off('scroll touchmove mousewheel');
                $("body, html").scrollTop(_pageScrollOffset);
                $(".srch_gate_pop_area").removeClass("show")
                $(".page_title_area").removeClass("law_index");
                if (_winWidth > 1024) {
                    $(".srch_gate_pop_area").fadeOut(200, function() {
                        $(".btn_top").removeClass("law_index");
                        $(".m_navi_bar").removeClass("hide");
                    });
                } else {
                    $(".m_navi_bar").removeClass("hide");
                }

                $(".page_title_area").removeClass("up");
                $(".srch_gate_pop_area .scroll_area .inner > div").removeClass("active");
                $(".srch_gate_pop_area .tab_img_area .img:first-child").removeClass("on");

                if ($(".main").size() > 0) {
                    if ($(".visual_wrap .swiper-slide-active video").size() > 0) {
                        $(".visual_div .swiper-slide-active video").get(0).currentTime = 0;
                        $(".visual_div .swiper-slide-active video").get(0).play();
                    }
                    _progressBarMotion.duration(_videoTime).restart();
                    $(".visual_div .swiper-slide-active").addClass("on");
                    $(".visual_div .swiper-slide-duplicate-active").addClass("on");
                }
            });


            //footer 패밀리 사이트
            $(document).off().on("click", "footer .select_box_div .btn_open", function() {
                if (!$("footer .select_box_div .btn_open").hasClass("on")) {
                    $("footer .select_box_div .list_wrap").show();
                    $("footer .select_box_div .btn_open").addClass("on");
                } else {
                    $("footer .select_box_div .list_wrap").hide();
                    $("footer .select_box_div .btn_open").removeClass("on");
                }
            });

            $("footer .bottom_area .site_box > a").on("click", function() {
                $("body").addClass("stop_scroll")
                $('#wrap').removeClass('stop_scroll');
                $("header").addClass("law_index");
                $(".btn_top").addClass("law_index");
                $(".m_navi_bar").addClass("hide");
                $("footer .layer_popup").fadeIn();

                if (window.innerWidth <= 1024) {
                    $("footer .layer_popup").find(".pop_wrap").stop(true, true).slideDown(300);

                    if ($(".right_box_fix_area").size() > 0) {
                        $(".page_title_area.white_ver, .right_box_fix_area .filter_bar_area, .course_popup, .course_popup .fixed_btn_area, .toast_popup").addClass("law_index");
                    }

                    if ($(".page_title_area").hasClass("fixed")) {
                        $("footer").css("z-index", 100);
                    }
                }
                popupResize();
            });

            $(".all_menu_btn").on("click", function() {
                _pageScrollOffset = window.pageYOffset;
                $(".all_menu_dimd").fadeIn();
                $(".btn_top").addClass("law_index");
                $(".all_menu_pop").addClass("show");
                $("body, html").on('scroll touchmove mousewheel', function(e) {
                    e.preventDefault();
                });
                $("body").addClass("stop_scroll");
                $('#wrap').addClass('stop_scroll');
                setTimeout(function() {
                    $(".all_menu_pop").addClass("fixed");
                    $("body").addClass("lock")
                }, 700)
                if ($(".page_title_area").hasClass("fixed") || $(".page_title_area").hasClass("white_ver")) {
                    $(".page_title_area").addClass("law_index");
                }
            });

            $(".all_menu_pop .btn_close").on("click", function() {
                setTimeout(function() {
                    $(".btn_top").removeClass("law_index");
                }, 300)
                $(".all_menu_dimd").fadeOut();
                $(".all_menu_pop").removeClass("fixed");
                $(".all_menu_pop").removeClass("show");
                $("body").removeClass("stop_scroll");
                $('#wrap').removeClass('stop_scroll');
                $("body, html").off('scroll touchmove mousewheel');
                $("body").removeClass("lock");
                $("html").scrollTop(_pageScrollOffset);
                if ($(".page_title_area").hasClass("law_index")) {
                    $(".page_title_area").removeClass("law_index");
                }
            });

            // navigation 전체 메뉴
            $(".all_menu_pop .one_d").each(function() {
                $(this).on("click", function() {
                    if (!$(this).parents("li").hasClass("on")) {
                        $(".all_menu_pop .menu").removeClass("on opacity");
                        $(".all_menu_pop .two_d_w").stop(true, true).slideUp();
                        $(this).parents("li").addClass("on");
                        $(this).siblings(".two_d_w").stop(true, true).slideDown();
                        $(this).parents(".all_menu").find(".menu").not($(this).parents("li")).addClass("opacity");
                    } else {
                        $(".all_menu_pop .tit_area .title").removeClass("on");
                        $(this).parents("li").removeClass("on");
                        $(this).siblings(".two_d_w").stop(true, true).slideUp();
                        $(this).parents(".all_menu").find(".menu").removeClass("opacity");
                    }
                });
            });

            // 나의 관심지점
            $("header .place_pop_area .place_con .accordion_type .list_div .btn_flex_box .place_btn").off().on("click", function() {
                $("header .place_pop_area .place_con .accordion_type .list_div .btn_flex_box .place_btn").removeClass("on")
                $(this).addClass("on")
                $("header .place_pop_area .for_padding").addClass("on");
            });


        },
        commonFn: function() {
            if (_device == 'ios') {
                $("body").addClass("ios");
            }

            // 지점검색 결과페이지 값 선택 후 링크 복사해서 열었을 경우
            classScrollPosition();

            // 탑버튼
            repositioningTopBtn(); // 스크롤 없는 페이지에서 스크롤 전 초기 위치값 설정

            $(".btn_top").on("click", function() {
                gsap.to($("html, body"), {
                    duration: 0.8,
                    scrollTop: 0,
                    ease: Power3.easeOut
                });
            });

            // 탭 공통 기능
            $(".tab_func_area").each(function(k) {
                $(this).find(".tab_btn_area .btn").each(function(q) {
                    $(this).on("click", function() {
                        if (!$(this).hasClass("on")) {
                            $(this).parents(".tab_func_area").find(".tab_btn_area .btn").removeClass("on");
                            $(this).addClass("on");
                            $(this).closest(".tab_func_area").find(".tab_con_area").find(".con").removeClass("on");

                            // 전체 탭 없는 경우
                            if (!$(this).siblings(".btn").hasClass("total") && !$(this).hasClass("total")) {
                                $(this).closest(".tab_func_area").find(".tab_con_area").find(".con").eq(q).addClass("on");
                            } else {
                                // 전체 탭 있는 경우 ex) CLS-01-005
                                if ($(this).hasClass("total")) {
                                    $(this).closest(".tab_func_area").find(".tab_con_area").find(".con").removeClass("on");
                                } else {
                                    $(this).closest(".tab_func_area").find(".tab_con_area > .con").eq(q - 1).addClass("on");
                                }
                            }
                            if ($(this).parents(".class_gate_w").size() > 0) {
                                $(this).parents(".class_gate_w").find(".circle_menu_w .circle img").each(function() {
                                    if ($(this).attr('src') == '') {
                                        $(this).attr("src", $(this).data("src"))
                                        setTimeout(function() {
                                            imgResizingFn()
                                        }, 10)
                                    }
                                });
                            }
                        }

                        fixedMobileH();

                        dropDownRecall();
                    });
                });
            });

            // 앵커 이동 공통 기능
            $(".anchor_func_area").each(function() {
                $(this).find(".anchor_btn_area .anchor_btn").each(function(q) {
                    $(this).on("click", function() {
                        var offset = $(this).parents(".anchor_func_area").find(".anchor_con").eq(q).offset()
                        gsap.to($("html, body"), 0.5, {
                            scrollTop: offset.top - $(".anchor_btn_area").innerHeight() + 1,
                            ease: Power3.easeOut
                        });

                        setTimeout(function() {
                            if ($(".page_title_area").hasClass("fixed") && !$(".page_title_area").hasClass("up")) {
                                gsap.to($("html, body"), 0.5, {
                                    scrollTop: offset.top - $(".anchor_btn_area").innerHeight() - $(".page_title_area.fixed").innerHeight() + 1,
                                    delay: 0.1,
                                    ease: Power3.easeOut
                                });
                            }
                        }, 100)
                    });
                });
            });

            if ($(".page_title_area .tit_div").size() <= 0 && $(".main").size() <= 0 && $(".top_visual").size() <= 0) {
                $(".page_title_area").addClass("no_margin");
                $(".page_title_area").addClass("no_padding");
            }

            if ($(".view_con").size() > 0 || $(".white_inner").size() > 0 && $(".white_inner.open_pop").size() <= 0 || $(".right_box_fix_area").size() > 0 || $(".pay_channel").size() > 0) {
                $("#wrap").addClass("bright");
            }

            if ($(".page_title_area").hasClass("white_ver")) {
                $("header").addClass("white_ver");
            }

            // 별 클릭체크 기능 defalt 5개 blank
            $(".star").each(function(idx) {
                // star_rating에 click_able 클래스가 있을 때만, 별 클릭 작동
                if ($(this).parent(".star_rating").hasClass("click_able")) {

                    var starLeng = $(".star").not(".blank").length;

                    $(this).on("click", function() {
                        starLeng = $(".star").not(".blank").length;

                        // 증가
                        if ($(this).index() + 1 > starLeng) {
                            $(this).prevAll().removeClass("blank");
                            $(this).removeClass("blank");
                            // console.log("증가", starLeng);
                        }
                        // 감소
                        if ($(this).index() + 1 < starLeng) {
                            $(this).nextAll().addClass("blank");
                            $(this).removeClass("blank");
                            // console.log("감소", starLeng);
                        }
                    });
                }
            })

            // 만족도 평가 체크기능 
            $(".grade").each(function() {
                $(this).on("click", function() {
                    $(".grade").removeClass("grade_on");
                    $(this).addClass("grade_on");
                })
            });

            // 아코디언
            accorRecall()

            // 결제상세, 수강결제 드롭다운
            $(".txt_con .drop_type").off().on("click", function() {
                //수강결제
                if ($(".total_price_info").size() > 0) {
                    if (window.innerWidth <= 1024) {
                        if (!$(this).hasClass("no_data")) {
                            $(this).parents(".txt_con").toggleClass("on");
                            if ($(this).parents(".txt_con").hasClass("on")) {
                                $(this).parents(".txt_con").siblings(".hide_con_w").stop(true, true).slideDown();
                            } else {
                                $(this).parents(".txt_con").siblings(".hide_con_w").stop(true, true).slideUp();
                            }
                        }
                    }
                } else {
                    $(this).parents(".txt_con").toggleClass("on");
                    if ($(this).parents(".txt_con").hasClass("on")) {
                        $(this).parents(".txt_con").siblings(".hide_con_w").stop(true, true).slideDown();
                    } else {
                        $(this).parents(".txt_con").siblings(".hide_con_w").stop(true, true).slideUp();
                    }
                }
            });

            // 동반 수강자 정보 드롭다운
            $(".pay_step .sub_inner .dropdown_btn").on("click", function() {
                $(this).toggleClass("active");
                $(".hide_list").toggleClass("on");
                if ($(".hide_list").hasClass("on")) {
                    $(".hide_list").stop(true, true).slideUp(0);
                    $(".hide_list").parents(".sub_inner").addClass("show_bord");
                    repositioningTopBtn();
                } else {
                    $(".hide_list").stop(true, true).slideDown(0);
                    $(".hide_list").parents(".sub_inner").removeClass("show_bord");
                    repositioningTopBtn();
                }

                repositioningTopBtn();
            });

            // payment 약관 드롭다운
            $(".payment_bar label, .payment_bar .dropdown_btn").on("click", function() {
                $(".payment_bar .dropdown_btn").toggleClass("active");
                if ($(".payment_bar .dropdown_btn").hasClass("active")) {
                    $(this).parents(".payment_con").siblings(".terms_for_msg").removeClass("hide");
                } else {
                    $(this).parents(".payment_con").siblings(".terms_for_msg").addClass("hide");
                }
                _paybarH = $(".payment_bar").innerHeight();
                repositioningTopBtn();
            });

            // 수강 리스트 2개형 드롭다운
            if ($(".dual_list").size() > 0) {
                //수강자만 드롭될 때 ex) MYP-04-001.html
                moreStudent()
            }

            // 다중 필터 선택
            filterSelect();

            // 토스트 알림 팝업
            if ($(".fix_box_area .toast_popup").size() > 0) {
                if ($("body").hasClass("ios")) {
                    $(".fix_box_area .toast_popup").addClass("up");
                }

                setTimeout(function() {
                    $(".right_box_fix_area .fix_box_area .toast_popup").fadeIn(500, function() {
                        setTimeout(function() {
                            $(".right_box_fix_area .fix_box_area .toast_popup").fadeOut(500);
                        }, 3000);
                    });
                }, 500);
            }

            // 페이지 상단 타이틀 팝업
            if ($(".page_title_area .tit_div.arrow").size() > 0) {
                $(".page_title_area .tit_div.arrow").on("click", function() {
                    $(this).toggleClass("active");

                    if ($(this).hasClass("active")) {
                        $(this).siblings(".tit_popup").removeClass("slideUp");
                        $(".filter_bar_area").removeClass("high_index");
                        $(".filter_bar_div .filter_list_wrap").addClass("slideUp").stop(true, true).slideUp();
                        $(".filter_bar_div .filter_open_area .filter_popup_btn").removeClass("active");
                        $(".m_navi_bar").addClass("hide");

                        $(this).siblings(".tit_popup").stop(true, true).slideDown(function() {
                            if (_winWidth > 1024) {
                                $(".tit_popup .pop_cont").height($(".tit_popup").find(".for_padding").innerHeight()); // 내부 스크롤
                            } else {
                                $(".tit_popup .pop_cont .for_padding").css("max-height", "calc(80vh - 60px)");
                                $(".tit_popup .pop_cont").height($(".tit_popup").height());
                            }
                        });

                        // 팝업 하단 흰 딤드
                        if (_winWidth > 1024) {
                            if ($(this).siblings(".tit_popup").find(".pop_wrap").height() >= 560) {
                                $(this).siblings(".tit_popup").addClass("on");
                            }
                        } else {
                            if ($(this).siblings(".tit_popup").find(".for_padding").height() * 0.8 >= window.innerHeight / 2) {
                                $(this).siblings(".tit_popup").addClass("on");
                            }
                        }
                    } else {
                        $(this).siblings(".tit_popup").stop(true, true).slideUp();
                        $(".m_navi_bar").removeClass("hide");
                        $(this).siblings(".tit_popup").removeClass("on");
                        $(".tit_popup .pop_cont, .tit_popup .pop_cont .for_padding").removeAttr("style");
                    }

                    if (window.innerWidth <= 1024) {
                        if ($(".page_title_area .tit_div.arrow").hasClass("active")) {
                            $(".page_title_area").addClass("bg_white");
                            $("body").addClass("stop_scroll");
                            $("body, html").on('scroll touchmove mousewheel', function(e) {
                                e.preventDefault();
                            });
                            $(".page_title_area .m_pop_dimd").stop(true, true).fadeIn(300);
                            $("footer, .btn_top").addClass("law_index");
                        } else {
                            $("body").removeClass("stop_scroll");
                            $("body, html").off("scroll touchmove mousewheel");
                            $(".page_title_area").removeClass("bg_white");
                            $(".page_title_area .m_pop_dimd").stop(true, true).fadeOut(300);
                            $("footer").removeClass("law_index");
                            setTimeout(function() {
                                $(".btn_top").removeClass("law_index");
                            }, 300);

                            $(this).siblings(".tit_popup").removeClass("on");
                        }
                    }
                });

                $(".page_title_area .tit_popup .branch a").on("click", function() {
                    var txtVal = $(this).find('p.f_desc').text();
					console.log(txtVal);

                    $("body").removeClass("stop_scroll");
                    $("body, html").off("scroll touchmove mousewheel");
                    $(this).addClass("active").siblings().removeClass("active").parent().siblings(".branch").find("a").removeClass("active");
                    $(this).parents(".page_title_area").find(".m_pop_dimd").stop(true, true).fadeOut(300);
                    $(this).parents(".page_title_area").find(".tit_popup").stop(true, true).slideUp();
                    $(this).parents(".page_title_area").find(".tit_div").removeClass("active");
                    $(this).parents(".page_title_area").find(".tit_div.arrow .tit").html(txtVal + "<span class='more_tit'></span>");
                    $(this).parents(".page_title_area").removeClass("bg_white");
                    $("footer").removeClass("law_index");
                    setTimeout(function() {
                        $(".btn_top").removeClass("law_index");
                    }, 300);
                });

                $(".page_title_area .m_pop_dimd").on("click", function() {
                    $("body").removeClass("stop_scroll");
                    $("body, html").off("scroll touchmove mousewheel");
                    $(".m_navi_bar").removeClass("hide");
                    $(this).stop(true, true).fadeOut(300);
                    $(this).parents(".page_title_area").find(".tit_div").removeClass("active");
                    $(this).parents(".page_title_area").find(".tit_popup").stop(true, true).slideUp();
                    $(this).parents(".page_title_area").removeClass("bg_white");
                    $("footer").removeClass("law_index");
                    setTimeout(function() {
                        $(".btn_top").removeClass("law_index");
                    }, 300);
                })
            }

            // 상세 필터바
            if ($(".filter_bar_div").size() > 0) {
                $(".filter_bar_div .btn_area .btn_wrap").each(function() {
                    $(this).find(".btn").on("click", function() {
                        _pageScrollOffset = window.pageYOffset;

                        $(this).toggleClass("active");

                        if ($(this).hasClass("active")) {
                            // 공통
                            $(".m_navi_bar").addClass("hide");
                            $(this).parents(".filter_bar_area").addClass("high_index");

                            if (_winWidth > 1024) {
                                $(".tit_popup").addClass("slideUp").stop(true, true).slideUp();
                                $(".tit_popup").siblings(".tit_div").removeClass("active");
                                $(this).parents(".btn_wrap").find(".filter_list_wrap").removeClass("slideUp");

                                $(this).parents(".btn_wrap").siblings().find(".btn").removeClass("active");
                                $(this).parents(".btn_wrap").find(".filter_list_wrap").stop(true, true).slideDown();
                                $(this).parents(".btn_wrap").siblings().find(".filter_list_wrap").stop(true, true).slideUp();
                            } else {
                                $("body").addClass("stop_scroll lock");
                                $("body, html").on('scroll touchmove mousewheel', function(e) {
                                    e.preventDefault();
                                });
                                $(this).parent().siblings(".dimd").stop(true, true).fadeIn();
                                setTimeout(function() {
                                    $("body").addClass("lock");
                                }, 100);

                                if ($(".page_title_area").hasClass("white_ver")) {
                                    $(".page_title_area.white_ver").addClass("law_index");
                                }

                                $(this).parents(".btn_wrap").siblings().find(".btn").removeClass("active");
                                $(this).parents(".btn_wrap").find(".filter_list_wrap").addClass("show").stop(true, true).slideDown();
                            }
                        } else {
                            // 공통
                            $(".m_navi_bar").removeClass("hide");
                            $(this).parents(".filter_bar_area").removeClass("high_index");

                            $(this).parents(".btn_wrap").find(".filter_list_wrap").stop(true, true).slideDown();
                            $(this).parents(".btn_wrap").siblings().find(".filter_list_wrap").stop(true, true).slideUp();

                            if (_winWidth > 1024) {
                                $(this).parent().siblings(".filter_list_wrap").stop(true, true).slideUp();
                            } else {
                                $("body").removeClass("stop_scroll");
                                $("body, html").off('scroll touchmove mousewheel');
                                $(this).parents(".btn_wrap").find(".filter_list_wrap").removeClass("show").stop(true, true).slideUp();
                                $("body").removeClass("lock");
                                $("html").scrollTop(_pageScrollOffset);
                            }
                        }
                    });

                    // 필터 닫기
                    $(this).find(".filter_list_wrap .txt, .dimd, .close").on("click", function() {
                        // 공통
                        $("body").removeClass("stop_scroll");
                        $("body, html").off("scroll touchmove mousewheel");
                        $(".m_navi_bar").removeClass("hide");
                        $("body").removeClass("lock");

                        $(this).parents(".filter_bar_area").removeClass("high_index");
                        $(this).parents(".btn_wrap").find(".filter_popup_btn").removeClass("active");

                        if (_winWidth > 1024) {
                            $(this).parents(".btn_wrap").find(".filter_list_wrap").stop(true, true).slideUp();
                        } else {
                            $(this).parents(".btn_wrap").find(".dimd").stop(true, true).fadeOut();
                            $(this).parents(".btn_wrap").find(".filter_list_wrap").removeClass("show").stop(true, true).slideUp();

                            if ($(".page_title_area").hasClass("white_ver")) {
                                $(".page_title_area.white_ver").removeClass("law_index");
                            }
                            $("html").scrollTop(_pageScrollOffset);
                        }
                    });

                    $(this).find(".filter_list_wrap .txt").on("click", function() {
                        if ($(this).parents(".link_select").size() <= 0) {
                            var txtVal = $(this).html();

                            $(this).addClass("on").siblings().removeClass("on");
                            $(this).parents(".btn_wrap").find(".order_txt").html(txtVal);
                        }
                    });
                });
            }

            // 상세검색 팝업
            if ($(".srch_popup").size() > 0) {
                // $(".srch_popup .for_padding .scroll_area").height($(".srch_popup").find(".pop_wrap").height());
                $(".srch_popup .for_padding .scroll_area").height($(".srch_popup").find(".for_padding").height() - $(".reset_wrap").innerHeight() + 12); // 플로팅 버튼 패딩 윗 여백값


                // 상세검색 팝업 열기
                $(".detail_srch_btn").on("click", function() {
                    $("body").addClass("stop_scroll");
                    $("body, html").on('scroll touchmove mousewheel', function(e) {
                        e.preventDefault();
                    });
                    $(".srch_popup").addClass("show");
                    $(".srch_popup").find(".dimd").stop(true, true).fadeIn(400);
                    $(".page_title_area .tit_popup").addClass("slideUp").stop(true, true).slideUp();
                    $(".page_title_area .tit_div.arrow").removeClass("active");
                });

                // 상세검색 팝업 닫기
                $(".srch_popup .dimd, .srch_popup .btn_close").on("click", function() {
                    $("body").removeClass("stop_scroll");
                    $("body, html").off("scroll touchmove mousewheel");
                    $("body").removeClass("lock");
                    $("html").scrollTop(_pageScrollOffset);

                    $(".filter_bar_div .detail_srch_btn").removeClass("active");
                    $(".srch_popup").removeClass("show");
                    $(".srch_popup").find(".dimd").stop(true, true).fadeOut(400);
                    $(".filter_bar_area").removeClass("high_index");
                    $(".page_title_area .tit_popup").removeClass("slideUp");
                    $(".m_navi_bar").removeClass("hide");

                    $(".srch_popup .accordion_type .list_div").each(function(q) {
                        if ($(this).parents(".srch_popup").find(".accordion_type .list_div.on").length > 0) {
                            if (!$(this).find(".hide_con .btn").hasClass("on")) {
                                $(this).removeClass("on");
                                $(this).find(".hide_con").stop(true, true).slideUp();
                            }
                        }
                    });
                });

                // 상세검색 팝업 초기화 버튼
                $(".reset_wrap .reset_btn").on("click", function() {
                    $(this).parent().siblings().find(".srch_con .list_div").find(".filter_btn_list .btn").removeClass("on");
                    $(this).parents(".srch_popup").find(".list_div .f_body4").parent().addClass("hide");
                    $(this).parents(".srch_popup").find(".list_div .f_body4").siblings(".num").text("0");
                    $(".price_input_wrap .form_input input").val("");
                });
            }

            $(".opacity_txt_list").each(function() {
                $(this).find(".txt").each(function() {
                    $(this).on("mouseenter focusin", function() {
                        $(this).parents(".opacity_txt_list").find(".txt").removeClass("opacity");
                        $(this).parents(".opacity_txt_list").find(".txt").not($(this)).addClass("opacity");
                    });

                    $(this).on("mouseleave", function() {
                        $(this).parents(".opacity_txt_list").find(".txt").removeClass("opacity");
                    })
                });
            });

            // 강좌상세
            if ($(".right_box_fix_area").size() > 0) {
                $(".right_box_fix_area .course_popup").each(function(q) {
                    // 수강신청 버튼 클릭 시
                    $(this).find(".single_btn_area").on("click", function() {
                        _pageScrollOffset = window.pageYOffset;

                        _beforeScrollOffset = _pageScrollOffset;

                        if (!$(this).hasClass("one_layer")) {
                            $(this).parents(".course_popup").siblings(".list").addClass("active");

                            if (_winWidth > 1024) {
                                $(this).parents(".course_popup").find(".form_select_div .list_wrap").stop(true, true).slideUp();
                                $(this).parents(".course_popup").find(".form_select_div").removeClass("on");
                                if ($(".course_popup.list").hasClass("active")) {
                                    $(".course_popup.list").find(".for_padding").innerHeight($(".course_popup.list").height() - $(".course_popup.list").find(".btn_area").outerHeight(true));

                                    gsap.to(".course_popup.list .pop_wrap .pop_cont .for_padding .box_con, .course_popup.list .btn_area", {
                                        duration: .3,
                                        "opacity": 1,
                                        delay: .35
                                    });
                                    $(".course_popup").not(".list").addClass("on");
                                }
                            } else {
                                $("body").addClass("stop_scroll");
                                $("body, html").on('scroll touchmove mousewheel', function(e) {
                                    e.preventDefault();
                                });
                                $(".fix_box_area").find(".box_dimd").addClass("high_index").stop(true, true).fadeIn();
                                $(".btn_top").addClass("law_index");
                                setTimeout(function() {
                                    $("body").addClass("lock");
                                }, 100);
                            }
                        }
                    });

                    $(".right_box_fix_area .box_prev_btn, .right_box_fix_area .fix_box_area .box_dimd").off().on("click", function() {
                        $(".course_popup.list").removeClass("active");

                        if (window.innerWidth <= 1024) {
                            $("body").removeClass("stop_scroll");
                            $("body, html").off("scroll touchmove mousewheel");
                            setTimeout(function() {
                                $(".fix_box_area").find(".box_dimd").removeClass("high_index").stop(true, true).fadeOut(250, function() {
                                    $(".btn_top").removeClass("law_index");
                                });
                            }, 200);
                            $("body").removeClass("lock");
                            $("html").scrollTop(_pageScrollOffset);

                            if ($(this).parents(".fix_box_area").find(".course_popup.list").is(":visible")) {
                                $("html").scrollTop(_beforeScrollOffset);
                            }
                        } else {
                            $(".course_popup.list .pop_wrap .pop_cont .for_padding .box_con, .course_popup.list .btn_area").removeAttr("style");
                            $(".course_popup").not(".list").removeClass("on");
                        }
                    });
                });

                // 강좌상세 내 강좌소개 더보기
                if ($(".right_box_fix_area .info_img_txt").size() > 0) {
                    if ($(".right_box_fix_area .info_img_txt .inner_mobile").height() < 550) {
                        $(".right_box_fix_area .info_img_txt").removeClass("hide");
                        $(".right_box_fix_area .more_btn_wrap").css("display", "none");
                    } else {
                        $(".right_box_fix_area .info_img_txt").addClass("hide");
                        $(".right_box_fix_area .more_btn_wrap").removeAttr("style");
                    }

                    $(".right_box_fix_area .more_btn_wrap").each(function() {
                        $(this).find(".more_btn").on("click", function() {
                            $(this).toggleClass("on");

                            if ($(this).hasClass("on")) {
                                $(this).parent().siblings(".info_img_txt").removeClass("hide");
                                $(this).find("span").text("강좌소개 닫기");
                            } else {
                                $(this).parent().siblings(".info_img_txt").addClass("hide");
                                $(this).find("span").text("강좌소개 더보기");
                            }
                        });
                    });
                }
            }


            // header empty 때문에 중복으로 적어놨음
            $(".place_pop_area .form_radio label").off().on("click", function() {
                $("header .place_pop_area .for_padding").addClass("on");
            });

            // 나의 쿠폰
            couponChange();

            // 장바구니 check_box
            if ($(".form_checkbox").size() > 0) {
                $(".form_checkbox #allSelect").on("click", function() {
                    if ($(this).prop("checked")) {
                        $("input:checkbox").prop("checked", true);
                    } else {
                        $("input:checkbox").prop("checked", false);
                    }
                });

                $(".cour_top_area .form_checkbox").each(function() {
                    $(this).on("change", function() {
                        var checkBoxLeng = $(".cour_top_area .form_checkbox input:checkbox").not("#allSelect input:checkbox").length;
                        var checkedLeng = $(".cour_top_area .form_checkbox input:checkbox:checked").not("#allSelect input:checkbox").length;
                        if (checkBoxLeng == checkedLeng) {
                            $("#allSelect:checkbox").prop("checked", true);
                        } else {
                            $("#allSelect:checkbox").prop("checked", false);
                        }
                    });
                });
            }

            if ($(".m_navi_bar").size() <= 0 && $(".right_box_fix_area").size() <= 0) {
                $("footer").addClass("small_padding");
            }

            if ($(".login_wrap").size() > 0 || $(".main").size() > 0) {
                $("footer").addClass("large_width");
            }

            if ($(".pay_agree_con_w").size() > 0) {
                $(".payment_bar .txt_box .agree_chk_txt input").off().on("click", function() {
                    gsap.to($("html, body"), 0.8, {
                        scrollTop: $("footer").offset().top - window.innerHeight,
                        ease: Power3.easeOut
                    });
                    if ($(this).is(":checked")) {
                        $(".pay_agree_con_w").find("input").prop("checked", true)
                    } else {
                        $(".pay_agree_con_w").find("input").prop("checked", false)
                    }
                });

                $(".pay_agree_con_w .form_checkbox").find("input").off().on("click", function() {
                    if ($(this).is(":checked")) {
                        $(".payment_bar .txt_box .agree_chk_txt").find("input").prop("checked", true)
                    } else {
                        $(".payment_bar .txt_box .agree_chk_txt").find("input").prop("checked", false)
                    }
                });
            }
        },
        scrollFn: function() {
            // not IE
            $(window).on("scroll", function() {
                _isScrollTop = $(window).scrollTop();

                if (_winWidth > 1024) {
                    if (_isScrollTop > _this_scroll) { // down
                        if (_isScrollTop > 0) {
                            if ($("header").size() > 0) {
                                if ($(window).scrollTop() > 50) {
                                    if (!$("header").hasClass("mouse_hover")) {
                                        $("header").addClass("hide");
                                    }
                                }
                            }

                            if ($(".fix_box_area").size() > 0) {
                                $(".fixed_content_area .fix_box_area").removeClass("pd_top");
                            }
                        }
                    }

                    if (_isScrollTop < _this_scroll) { // up
                        $("header").removeClass("hide");
                        $("header").addClass("up_scroll");

                        if ($(".fix_box_area").size() > 0) {
                            $(".fixed_content_area .fix_box_area").addClass("pd_top");
                        }
                    }
                } else {
                    if ($("body").hasClass("ios")) {
                        if (_isScrollTop > _this_scroll) { // down
                            $(".m_navi_bar").addClass("scroll_down");
                            // console.log("모바일");
                        } else {
                            $(".m_navi_bar").removeClass("scroll_down");
                        }

                        if (_isScrollTop == 0) {
                            $(".m_navi_bar").removeClass("scroll_down");
                        }
                    }

                    if (_isScrollTop > _this_scroll) { // down
                        $(".page_title_area").addClass("up");
                        $(".fixed_mobile.fixed").removeClass("move");

                        if (_isScrollTop < _filterTop) {
                            $(".fixed_mobile").removeClass("move");
                        }
                    } else { // up
                        if (!$(".filter_list_wrap").hasClass("show")) {
                            $(".page_title_area").addClass("fixed");
                        }
                        $(".page_title_area").removeClass("up");
                        $(".fixed_mobile.fixed").addClass("move");
                    }

                    if (_isScrollTop == 0) {
                        if (!$(".filter_list_wrap").hasClass("show")) {
                            $(".page_title_area").removeClass("fixed");
                        }
                        $(".page_title_area").removeClass("up");
                        $(".fixed_mobile").removeClass("fixed move");
                    }
                }

                _this_scroll = _isScrollTop;

                if (_isScrollTop == 0) {
                    $("header").removeClass("up_scroll");
                }

                if (_isScrollTop < 100) {
                    if ($(".fix_box_area").size() > 0) {
                        $(".fixed_content_area .fix_box_area").removeClass("pd_top");
                    }
                }


                // 필터영역 sticky
                if ($(".fixed_mobile_w").size() > 0) {
                    $(".fixed_mobile_w").each(function() {
                        _filterTop = $(this).offset().top;

                        if (window.innerWidth <= 1024) { // mobile
                            if (_isScrollTop > _filterTop) {
                                $(this).find(".fixed_mobile").addClass("fixed");
                            }
                            if (_isScrollTop + 60 < _filterTop) {
                                if ($(this).find(".fixed_mobile").hasClass("move")) {
                                    $(this).find(".fixed_mobile").removeClass("fixed");
                                }
                            }
                        } else { // pc
                            $(this).find(".fixed_mobile").removeClass("fixed");
                        }
                    });
                }

                fixedMobileH(); // fixedMobile 높이값

                // 이용안내 - 강사/제휴 신청안내
                if ($(".divide_area").size() > 0) {
                    if (window.innerWidth <= 1024) {
                        $(".divide_area .inner").each(function() {
                            if (_isScrollTop <= $(this).find(".left .inner_box").offset().top) {
                                $(this).find(".left").addClass("active");
                                $(this).find(".right").removeClass("active");
                            } else {
                                $(this).find(".left").removeClass("active");
                                $(this).find(".right").addClass("active");
                            }

                            $(this).find(".box").hover(function() {
                                $(this).addClass("active").siblings(".box").removeClass("active");
                            });
                        });
                    }
                }

                // 앵커 이동 공통 기능
                if ($(".anchor_func_area").size() > 0) {
                    if (window.innerWidth <= 1024) {
                        $(".anchor_func_area .anchor_con").each(function(q) {
                            if (_isScrollTop > $(this).offset().top - $(".anchor_btn_area").innerHeight() - $(".page_title_area.fixed").innerHeight() - 1) {
                                $(".anchor_btn_area .anchor_btn").removeClass("on");
                                $(".anchor_btn_area .anchor_btn").eq(q).addClass("on")
                            }
                            if (_isScrollTop + 100 >= $("#wrap").innerHeight() - $(window).innerHeight()) {
                                $(".anchor_btn_area .anchor_btn").removeClass("on");
                                $(".anchor_btn_area .anchor_btn").eq(-1).addClass("on")
                            }
                        });
                    }
                }

                // 탑버튼
                repositioningTopBtn();
            });
            $(window).scroll();

            // for IE
            $("body").on("scroll", function() {

            });

            // scrollMotion
            scrollMotionTrigger();
        },
        swiperFn: function() {
            if ($(".main_pop_swiper").size() > 0) {
                if ($(".main_pop_swiper .swiper-slide").length > 1) {
                    $(".main_pop_swiper .swiper-container").after($('<div class="swiper-button-prev"></div>'));
                    $(".main_pop_swiper .swiper-container").after($('<div class="swiper-button-next"></div>'));

                    _mainPopSwiper = new Swiper(".main_pop_swiper .swiper-container", {
                        observer: true,
                        observeParents: true,
                        navigation: {
                            nextEl: ".main_pop_swiper .swiper-button-next",
                            prevEl: ".main_pop_swiper .swiper-button-prev",
                        },
                        pagination: {
                            el: ".main_pop_swiper .swiper-pagination",
                            type: "fraction",
                        },
                        touchReleaseOnEdges: true,
                        loop: true,
                    });

                    $(".main_pop_swiper").hover(function() {
                        $(".main_pop_swiper").find(".swiper-button-next, .swiper-button-prev").fadeIn(250);
                    }, function() {
                        $(".main_pop_swiper").find(".swiper-button-next, .swiper-button-prev").fadeOut(250);
                    });
                } else {
                    _mainPopSwiper = new Swiper(".main_pop_swiper .swiper-container", {
                        observer: true,
                        observeParents: true,
                        touchReleaseOnEdges: true,
                        pagination: {
                            el: ".main_pop_swiper .swiper-pagination",
                            type: "fraction",
                        },
                    });
                }
            }


            /* 2023-10-31 수정 */
            if ($(".visual_wrap").size() > 0) {
                if ($(".visual_div .swiper-slide").length > 1) {
                    _progressBarMotion = gsap.to($(".visual_wrap .progress_bar .bar"), 4, {
                        width: "100%",
                        ease: "none",
                        onComplete: function() {
                            _visualSwiper.slideNext();
                        }
                    });

                    _visualSwiper = new Swiper(".visual_div .swiper-container", {
                        parallax: true,
                        speed: 1200,
                        loop: true,
                        observer: true,
                        observeParents: true,
                        navigation: {
                            nextEl: ".visual_div .swiper-button-next",
                            prevEl: ".visual_div .swiper-button-prev",
                        },
                        pagination: {
                            el: ".visual_div .swiper-pagination",
                            type: "fraction",
                        },
                        on: {
                            init: function() {
                                if ($(".visual_wrap .swiper-slide-active video").size() > 0) {
                                    var isFirst = false;
                                    $(window).resize(function() {
                                        if (_winWidth > 1024) {
                                            if (!isFirst) {
                                                _videoTime = $(".visual_wrap .swiper-slide-active .only_pc video")[0].duration;
                                                _progressBarMotion.duration(_videoTime).restart();
                                                isFirst = true;
                                            }
                                        } else {
                                            if (isFirst) {
                                                _videoTime = $(".visual_wrap .swiper-slide-active .only_mobile video")[0].duration;
                                                _progressBarMotion.duration(_videoTime).restart();
                                                isFirst = false;
                                            }
                                        }
                                    }).resize()
                                }
                            },
                            slideChangeTransitionStart: function() {
                                if ($(".visual_wrap .swiper-slide-active video").size() > 0) {
                                    var isFirst = false;
                                    if ($(".visual_wrap .swiper-slide-active").find("video").size() > 0) {
                                        $(window).resize(function() {
                                            if (_winWidth > 1024) {
                                                if (isFirst) {
                                                    if ($(".visual_btn").hasClass("play")) {
                                                        $(".visual_btn").removeClass("play");
                                                        $(".visual_btn").addClass("pause");
                                                    }
                                                    console.log(2222)
                                                    $(".visual_wrap .swiper-slide-active .only_pc video").get(0).currentTime = 0;
                                                    _videoTime = $(".visual_wrap .swiper-slide-active .only_pc video")[0].duration;
                                                    _progressBarMotion.duration(_videoTime).restart();
                                                    $(".visual_div .swiper-slide-active .only_pc video, .visual_div .swiper-slide-duplicate-active .only_pc video").get(0).play();
                                                    isFirst = false;
                                                }
                                            } else {
                                                if (!isFirst) {
                                                    if ($(".visual_btn").hasClass("play")) {
                                                        $(".visual_btn").removeClass("play");
                                                        $(".visual_btn").addClass("pause");
                                                    }
                                                    console.log(1111)
                                                    $(".visual_wrap .swiper-slide-active .only_mobile video").get(0).currentTime = 0;
                                                    _videoTime = $(".visual_wrap .swiper-slide-active .only_mobile video")[0].duration;
                                                    _progressBarMotion.duration(_videoTime).restart();
                                                    $(".visual_div .swiper-slide-active .only_mobile video, .visual_div .swiper-slide-duplicate-active .only_mobile video").get(0).play();
                                                    isFirst = true;
                                                }
                                            }
                                        }).resize()
                                    }

                                    if ($(".visual_wrap .swiper-slide").not(".swiper-slide-active, .swiper-slide-duplicate-active").find("video").size() > 0) {
                                        $(".visual_wrap .swiper-slide").not(".swiper-slide-active, .swiper-slide-duplicate-active").find("video").get(0).pause();
                                    }

                                    $(".visual_wrap .swiper-slide-active video, .visual_wrap .swiper-slide-duplicate-active video").get(0).currentTime = 0;

                                    if (isNaN(_videoTime)) {
                                        var vid = $(".visual_wrap .swiper-slide-active video").get(0)
                                        vid.onloadedmetadata = function() {
                                            _videoTime = $(".visual_wrap .swiper-slide-active video")[0].duration;
                                            _progressBarMotion.duration(_videoTime).restart();
                                        };
                                    }

                                } else {
                                    _videoTime = 5;
                                }
                            },
                            slideChangeTransitionEnd: function() {
                                if ($(".visual_wrap .swiper-slide-active video").size() > 0) {
                                    if (_deviceCondition == "pc") {
                                        if ($(".visual_area .visual_btn").hasClass("pause")) {
                                            $(".visual_div .swiper-slide-active .only_pc video").get(0).currentTime = 0;
                                            _videoTime = $(".visual_wrap .swiper-slide-active .only_pc video")[0].duration;
                                            _progressBarMotion.duration(_videoTime).restart();
                                            $(".visual_div .swiper-slide-active .only_pc video").get(0).play();
                                        } else if ($(".visual_area .visual_btn").hasClass("play")) {}
                                    } else if (_deviceCondition == "mobile") {
                                        $(".visual_div .swiper-slide-active .only_mobile video, .visual_div .swiper-slide-duplicate-active .only_mobile video").get(0).play();
                                    }
                                }

                                $(".visual_div .swiper-slide").removeClass("on");

                                if (!_isFirst) {
                                    $(".visual_div .swiper-slide-active").addClass("on");
                                    $(".visual_div .swiper-slide-duplicate-active").addClass("on");
                                }

                                if ($(".visual_area .visual_btn").hasClass("pause")) {
                                    _progressBarMotion.duration(_videoTime).restart();
                                }
                            },
                        }
                    });

                    $(".visual_area .visual_btn").on("click", function() {
                        if ($(this).hasClass("pause")) {
                            $(this).removeClass("pause");
                            $(this).addClass("play");
                            _progressBarMotion.pause();
                            if ($(".visual_wrap .swiper-slide-active video").size() > 0) {
                                if (_deviceCondition == "pc") {
                                    $(".visual_div .swiper-slide-active .only_pc video").get(0).pause();
                                } else if (_deviceCondition == "mobile") {
                                    $(".visual_div .swiper-slide-active .only_mobile video").get(0).pause();
                                }
                            }
                        } else if ($(this).hasClass("play")) {
                            $(this).removeClass("play");
                            $(this).addClass("pause");
                            _progressBarMotion.play();
                            if ($(".visual_wrap .swiper-slide-active video").size() > 0) {
                                if (_deviceCondition == "pc") {
                                    _progressBarMotion.duration(_videoTime).restart();
                                    $(".visual_wrap .swiper-slide-active .only_pc video").get(0).currentTime = 0;
                                    $(".visual_div .swiper-slide-active .only_pc video, .visual_div .swiper-slide-duplicate-active .only_pc video").get(0).play();
                                } else if (_deviceCondition == "mobile") {
                                    $(".visual_wrap .swiper-slide-active .only_mobile video").get(0).currentTime = 0;
                                    $(".visual_div .swiper-slide-active .only_mobile video, .visual_div .swiper-slide-duplicate-active .only_mobile video").get(0).play();
                                }
                            }
                        }
                    });
                } else {
                    $(".visual_div .swiper-slide").addClass("on");

                    $(".visual_div .swiper-button-prev").hide()
                    $(".visual_div .swiper-button-next").hide()
                    $(".visual_area .control_area").hide()
                }
            }
            /* // 2023-10-31 수정 */

            // 이미지 슬라이드
            if ($(".lecture_img_swiper").size() > 0) {
                $(".lecture_img_swiper").each(function(q) {
                    if ($(this).find(".swiper-slide").length > 1) {
                        $(this).removeClass("one_slide");

                        $(this).find(".swiper-container").after($('<div class="swiper-button-prev"></div>'));
                        $(this).find(".swiper-container").after($('<div class="swiper-button-next"></div>'));

                        var _lectureImgSwiper = new Swiper($(this).find(".swiper-container"), {
                            slidesPerView: "auto",
                            observer: true,
                            observeParents: true,
                            watchOverflow: true,
                            pagination: {
                                el: $(this).find(".swiper-pagination"),
                                type: "progressbar",
                            },
                            navigation: {
                                nextEl: $(this).find(".swiper-button-next"),
                                prevEl: $(this).find(".swiper-button-prev"),
                            },
                        });
                    } else {
                        $(this).addClass("one_slide");
                    }
                });
            }
            // 수강후기 슬라이드
            if ($(".course_review_slide").size() > 0) {
                if ($(".course_review_slide").find(".swiper-slide").size() > 4) {
                    $(".course_review_slide").append('<div class="swiper-button-next"></div>')
                    $(".course_review_slide").append('<div class="swiper-button-prev"></div>')
                } else {
                    $(".course_review_slide").addClass("no_swipe")
                }

                var courseReviewSwiper = new Swiper(".course_review_slide .swiper-container", {
                    slidesPerView: "auto",
                    observer: true,
                    observeParents: true,
                    pagination: {
                        el: ".course_review_slide .swiper-pagination",
                        type: "progressbar",
                    },
                    navigation: {
                        nextEl: $(".course_review_slide .swiper-button-next"),
                        prevEl: $(".course_review_slide .swiper-button-prev"),
                    },
                });
            }

            if ($(".border_tab_area").size() > 0) {
                var borderTabSwiper = [];
                $(".border_tab_area").each(function(q) {
                    $(this).find(".swiper-container").addClass("border_tab_swiper" + q);

                    if ($(this).find(".swiper-slide").length == 2) {
                        $(this).addClass("two");
                    } else if ($(this).find(".swiper-slide").length == 3) {
                        $(this).addClass("thr");
                    } else if ($(this).find(".swiper-slide").length == 4) {
                        $(this).addClass("fou");
                    } else if ($(this).find(".swiper-slide").length > 4) {
                        $(this).addClass("more");
                        borderTabSwiper[q] = new Swiper(".border_tab_swiper" + q, {
                            slidesPerView: "auto",
                            observer: true,
                            observeParents: true,
                            on: {
                                init: function() {
                                    $(".border_tab_swiper" + q).addClass("start");
                                },
                                transitionStart: function() {
                                    $(".border_tab_swiper" + q).removeClass("start");
                                    $(".border_tab_swiper" + q).removeClass("end");
                                },
                                transitionEnd: function() {
                                    if (borderTabSwiper[q].isBeginning) {
                                        $(".border_tab_swiper" + q).addClass("start");
                                        $(".border_tab_swiper" + q).removeClass("end");
                                    } else if (borderTabSwiper[q].isEnd) {
                                        $(".border_tab_swiper" + q).addClass("end");
                                        $(".border_tab_swiper" + q).removeClass("start");
                                    }
                                }
                            }
                        });
                    }

                    if (!$(this).hasClass("tab_btn_area")) {
                        $(this).find(".swiper-slide").on("click", function() {
                            $(this).addClass("on").siblings().removeClass("on");
                        });
                    }
                });
            }

            if ($(".circle_sel_swiper").size() > 0) {
                var circleSelSwiper = [];

                dropDownRecall();

                $(".circle_sel_swiper").each(function(q) {
                    $(this).find(".swiper-container").addClass("circle_sel_swiper" + q);
                    circleSelSwiper[q] = new Swiper(".circle_sel_swiper" + q, {
                        slidesPerView: "auto",
                        observer: true,
                        observeParents: true,
                        on: {
                            init: function() {
                                $(".circle_sel_swiper" + q).addClass("start");
                            },
                            transitionStart: function() {
                                $(".circle_sel_swiper" + q).removeClass("start");
                                $(".circle_sel_swiper" + q).removeClass("end");
                            },
                            transitionEnd: function() {
                                if (this.isBeginning) {
                                    $(".circle_sel_swiper" + q).addClass("start");
                                    $(".circle_sel_swiper" + q).removeClass("end");
                                } else if (this.isEnd) {
                                    $(".circle_sel_swiper" + q).addClass("end");
                                    $(".circle_sel_swiper" + q).removeClass("start");
                                }
                            }
                        }
                    });

                    $(this).find(".drop_btn").on("click", function() {
                        if (!$(this).parents(".circle_sel_swiper").hasClass("click")) {
                            $(this).parents(".circle_sel_swiper").addClass("click");
                        } else {
                            $(this).parents(".circle_sel_swiper").removeClass("click");
                        }
                    });

                    $(this).find(".swiper-slide").on("click", function() {
                        $(this).addClass("on").siblings().removeClass("on");

                        if ($(this).parents(".circle_sel_swiper").hasClass("click")) {
                            circleSelSwiper[q].slideTo($(".circle_sel_swiper .swiper-slide.on").index(), 0, false);
                        }
                        $(this).parents(".circle_sel_swiper").removeClass("click");
                    });
                });
            }

            var originActiveIndex;

            if ($(".inquiry_slide_w").size() > 0) {
                var inquirySwiper = new Swiper(".inquiry_slide_w .swiper-container", {
                    slidesPerView: "auto",
                    // centeredSlides:true,
                    observer: true,
                    observeParents: true,
                    autoHeight: true,
                    pagination: {
                        el: ".inquiry_slide .swiper-pagination",
                        type: "progressbar",
                    },
                    navigation: {
                        nextEl: ".inquiry_slide .swiper-button-next",
                        prevEl: ".inquiry_slide .swiper-button-prev",
                    },
                    on: {
                        init: function() {

                        },
                        transitionStart: function() {
                            if (_winWidth > 1024) {
                                if (inquirySwiper.activeIndex < $(".inquiry_slide_w .swiper-slide.on").index() && Math.abs(inquirySwiper.activeIndex - $(".inquiry_slide_w .swiper-slide.on").index()) < 3) {
                                    inquirySwiper.activeIndex = $(".inquiry_slide_w .swiper-slide.on").index();
                                    inquirySwiper.updateAutoHeight(0);
                                    inquirySwiper.activeIndex = $(".inquiry_slide_w .swiper-slide.swiper-slide-active").index();
                                }
                            }
                        },
                    }
                });

                $(".inquiry_slide_w .swiper-slide .txt").each(function() {
                    if ($(this).height() > 76) {
                        $(this).addClass("on");
                        $(this).parents(".swiper-slide").find(".more").show();
                    }
                });

                $(".inquiry_slide_w .swiper-slide .more").each(function() {
                    $(this).on("click", function() {
                        originActiveIndex = inquirySwiper.activeIndex;
                        inquirySwiper.activeIndex = $(this).parents(".swiper-slide").index();

                        if (!$(this).parents(".swiper-slide").hasClass("on")) {
                            $(this).parents(".swiper-slide").addClass("on");
                            $(this).parents(".swiper-slide").find(".txt").removeClass("on");

                            $(this).parents(".swiper-slide").siblings().removeClass("on")
                            $(this).parents(".swiper-slide").siblings().find(".txt").addClass("on");
                            $(this).parents(".swiper-slide").siblings().find(".more").text("더보기")
                            inquirySwiper.updateAutoHeight(0);
                            $(this).text("접기")
                        } else {
                            $(this).parents(".swiper-slide").removeClass("on");
                            $(this).parents(".swiper-slide").find(".txt").addClass("on");
                            inquirySwiper.updateAutoHeight(0);
                            $(this).text("더보기")
                        }

                        inquirySwiper.activeIndex = originActiveIndex;
                    })
                })
            }
        },
        resizeFn: function() {
            $(window).resize(function() {
                var wrap = document.getElementById("wrap");
                var scrollBar = window.innerWidth - $(window).width();
                _winWidth = Math.ceil(wrap.getBoundingClientRect().width) + scrollBar;

                $(".srch_gate_pop_area").height(window.innerHeight)

                _rightH = $(".fixed_content_area .shadow_div").height();

                $("header .place_pop_area .for_padding").height(window.innerHeight);
                $(".m_navi_bar .all_menu_pop").height(window.innerHeight);

                // 탑버튼
                repositioningTopBtn();

                // 이미지 리사이징
                imgResizingFn();

                // fixedMobile 높이값
                fixedMobileH();

                // 상세검색 검색데이터
                srchDataRecall();

                // 탭메뉴 드롭다운
                dropDownRecall();

                // 메인 비주얼 모션
                if ($(".main").size() > 0) {
                    if (_winWidth >= 1281) {
                        $(".visual_wrap").removeAttr("style");
                        gsap.to($(".visual_div"), {
                            duration: 1.3,
                            width: "calc(100% - 200px)",
                            height: "67vh",
                            borderRadius: "16px",
                            ease: Power2.easeOut,
                            onComplete: function() {
                                $(".visual_div .swiper-container").width(Math.floor($(".visual_div").width()))
                            }
                        });
                    } else if (_winWidth > 1024 && _winWidth <= 1280) { // pc
                        $(".visual_wrap").removeAttr("style");
                        gsap.to($(".visual_div"), {
                            duration: 1.3,
                            width: "calc(100% - 100px)",
                            height: "67vh",
                            borderRadius: "16px",
                            ease: Power2.easeOut,
                            onComplete: function() {
                                $(".visual_div .swiper-container").width(Math.floor($(".visual_div").width()))
                            }
                        });
                    } else { // mobile
                        $(".visual_wrap").height(window.innerHeight * 0.74)
                        gsap.to($(".visual_div"), {
                            duration: 1.3,
                            width: "calc(100% - 4rem)",
                            height: "calc(100% - 104px)",
                            borderRadius: "12px",
                            ease: Power2.easeOut,
                            onComplete: function() {
                                $(".visual_div .swiper-container").width(Math.floor($(".visual_div").width()))
                            }
                        });

                        $(".lecture_list_w .swiper-container").removeClass("no_swiping")
                    }
                }

                // 팝업 visible 일 때
                if ($(".tit_popup").size() > 0) {
                    $(".tit_popup .pop_cont, .tit_popup .pop_cont .for_padding").removeAttr("style");
                    $(".m_pop_dimd").removeAttr("style");
                    $(".page_title_area").removeClass("bg_white");
                    $(".page_title_area .tit_popup").hide();
                    $(".page_title_area .tit_div.arrow").removeClass("active");
                    $("footer, .btn_top").removeClass("law_index");
                }

                if ($(".srch_popup").size() > 0) {
                    if ($(".srch_popup").is(":visible")) {
                        $(".srch_popup:visible").find(".pop_wrap").height(Math.ceil($(".srch_popup:visible").find(".for_padding").height() + $(".srch_popup:visible").find(".scroll_area").innerHeight()));
                        $(".srch_popup:visible .for_padding .scroll_area").height($(".srch_popup:visible").find(".for_padding").height() - $(".reset_wrap").innerHeight() + 12); // 플로팅 버튼 패딩 윗 여백값
                    }
                }

                if ($(".thr_dep_area").size() > 0) {
                    var thrTotalWidth = 0;

                    // 지점안내(NOT-01-002) 3depth 드롭다운
                    $(".thr_dep_area").each(function(q) {
                        $(this).find(".swiper-slide").each(function(i) {
                            thrTotalWidth += $(this).outerWidth(true);
                        });

                        if (thrTotalWidth <= $(this).find(".swiper-container").width()) {
                            $(this).addClass("all");
                            $(this).find(".drop_btn").removeClass("show");
                        } else {
                            $(this).removeClass("all click");
                            // $(this).removeClass("all");
                            $(this).find(".swiper-container").removeClass("start");
                            $(this).find(".swiper-container").removeClass("end");
                            $(this).find(".drop_btn").addClass("show");
                        }
                    });
                }

                // 해상도 따른 pc, mobile 구분
                if (_winWidth > 1024) { // pc
                    _deviceCondition = "pc";

                    if ($(".form_select_div .list_wrap").is(":visible")) {
                        $("body").removeClass("stop_scroll")
                    }

                    // 헤더 pc버전
                    $("header").addClass("pc");
                    if ($("header").hasClass("mobile")) {
                        _mGnbAccordion = -1;
                        $("header .gnb > li").removeClass("on");
                        $("header").removeClass("mobile");
                    }
                    $("header .gnb_w").removeAttr("style");

                    // 페이지 상단 타이틀 영역
                    if ($(".page_title_area .tit_div.arrow").size() > 0) {
                        if ($(".page_title_area .tit_div.arrow").hasClass("active")) {
                            $("body").removeClass("stop_scroll");
                            $("body, html").off("scroll touchmove mousewheel");
                            $(".page_title_area").removeClass("bg_white");
                            $(".page_title_area .m_pop_dimd").stop(true, true).fadeOut(300);
                            $("footer").removeClass("law_index");
                        }
                    }

                    // 강좌상세 pc (ex: CLS-01-009 )
                    if ($(".right_box_fix_area").size() > 0) {
                        $(".right_box_fix_area .fix_box_area .course_popup").each(function(q) {
                            if (!$(this).hasClass("list")) {
                                if ($(this).find(".fixed_btn_area").size() > 0) {
                                    $(".course_popup:visible").height(window.innerHeight * 0.74);
                                    $(".course_popup:visible").css("min-height", $(".course_popup:visible").find(".scroll_area .pop_head").height() + $(".course_popup:visible").find(".btn_area").outerHeight(true));
                                    $(".course_popup:visible").css("max-height", 60 + $(".course_popup:visible").find(".scroll_area .pop_head").height() + $(".course_popup:visible").find(".scroll_area .box_con").height() + 20 + $(".course_popup:visible").find(".btn_area").outerHeight(true));
                                    $(".course_popup:visible").find(".for_padding").innerHeight($(".course_popup:visible").height() - $(".course_popup:visible").find(".btn_area").outerHeight(true));
                                } else {
                                    $(".course_popup:visible").height(window.innerHeight * 0.74);
                                    $(".course_popup:visible").css("min-height", $(".course_popup:visible").find(".scroll_area .pop_head"));
                                    $(".course_popup:visible").css("max-height", 60 + $(".course_popup:visible").find(".scroll_area .pop_head").height() + $(".course_popup:visible").find(".scroll_area .box_con").height());
                                    $(".course_popup:visible").find(".for_padding").innerHeight($(".course_popup:visible").height());
                                }

                                if ($(".course_popup:visible").find(".for_padding").innerHeight() < ($(".course_popup:visible").find(".scroll_area .pop_head").height() + $(".course_popup:visible").find(".scroll_area .box_con").height() + 80)) {
                                    $(this).find(".for_padding").addClass("on");
                                } else {
                                    $(this).find(".for_padding").removeClass("on");
                                }
                                $(".course_popup.list").removeAttr("style");

                                if ($(this).find(".form_select_div").hasClass("on")) {
                                    $("body").removeClass("stop_scroll");
                                    $("body, html").off("scroll touchmove mousewheel");
                                    $("body").removeClass("lock");
                                }
                            } else {
                                if ($(this).hasClass("active")) {
                                    $("body").removeClass("stop_scroll");
                                    $("body, html").off("scroll touchmove mousewheel");
                                    $("body").removeClass("lock");
                                    $(".fix_box_area").find(".box_dimd").removeClass("high_index").stop(true, true).fadeOut();
                                    $(".course_popup.list").find(".for_padding").innerHeight($(".course_popup.list").height() - $(".course_popup.list").find(".btn_area").outerHeight(true));
                                    $(".course_popup.list").find(".pop_wrap .pop_cont .for_padding .box_con").css("opacity", 1);
                                    $(".course_popup.list").find(".btn_area").css("opacity", 1);
                                }

                                if ($(".course_popup.list").find(".list_wrap").is(":visible")) {
                                    $(this).addClass("active");
                                    $("body").removeClass("lock");
                                    $(".course_popup.list").find(".for_padding").innerHeight($(".course_popup.list").height() - $(".course_popup.list").find(".btn_area").outerHeight(true));
                                    $(".course_popup.list .pop_wrap .pop_cont .for_padding .box_con, .course_popup.list .btn_area").css("opacity", 1);
                                } else {
                                    setTimeout(function() {
                                        $("body").removeClass("lock");
                                    }, 100);
                                }
                            }
                        });
                    }

                    if ($(".slide_area .lecture_img_swiper").size() > 0) {
                        $(".lecture_img_swiper").each(function(q) {
                            $(this).parents(".slide_area").hasClass("spread") ? $(this).addClass("off_slide") : $(this).removeClass("off_slide");
                        });
                    }

                    // 필터팝업 pc버전
                    if ($(".filter_bar_div").size() > 0) {
                        if ($(".filter_list_wrap").hasClass("show")) {
                            $("body").removeClass("stop_scroll");
                            $("body, html").off("scroll touchmove mousewheel");
                            $(".m_navi_bar").addClass("hide");
                            $("body").removeClass("lock");

                            $(".filter_list_wrap").hide();
                            $(".filter_open_area .btn").removeClass("active");
                            $(".filter_open_area").siblings(".dimd").hide();
                            $(".filter_list_wrap").removeClass("show");
                        }
                    }

                    if ($(".mobile_acco_div").size() > 0) {
                        $(".accordion_type").each(function() {
                            $(this).find(".list_div").removeClass("on");
                            $(this).parents(".mobile_acco_div").addClass("open");
                            $(this).find(".hide_con").removeAttr("style");

                            if (!$(this).parents(".mobile_acco_div").hasClass("first") && $(".white_inner").size() > 0) {
                                $(this).parents(".mobile_acco_div").addClass("first");
                            }
                        });
                    }

                    if ($(".total_price_info").size() > 0) {
                        $(".txt_con").removeClass("on");
                        $(".hide_con_w").hide();
                    }

                    // 수강결제 팝업
                    if ($(".open_pop").size() > 0) {
                        $(".pop_drop .drop_down").on("click", function() {
                            if (!$(this).hasClass("no_data")) {
                                $(".open_pop").show();
                            }
                            // $("body").css({overflow: "hidden"});
                            // console.log("눌리긴함");
                        });
                        $(".open_pop .btn_close").on("click", function() {
                            $(this).parents(".open_pop").hide();
                            // $("body").css({overflow: "visible"});
                        });
                    }

                    // 이용안내 - 강사/제휴 신청안내
                    if ($(".divide_area").size() > 0) {
                        $(".divide_area .inner .box").each(function() {
                            $(this).on("mouseenter focusin", function() {
                                $(this).addClass("active")
                            });

                            $(this).on("mouseleave focusout", function() {
                                $(this).removeClass("active")
                            });
                        });
                    }

                    if ($(".login_wrap").size() > 0) {
                        $(".login_inner").removeAttr("style")
                    }

                    if ($(".login_wrap").size() > 0) {
                        $(".login_wrap").css("min-height", "auto")
                        $(".login_inner .left_layout").css("height", "auto")
                    }

                    if ($(".class_gate_w").size() > 0) {
                        $(".class_gate_w .tab_btn_area .btn").each(function(q) {
                            $(this).on("mouseover", function() {
                                if (!$(this).hasClass("on")) {
                                    $(".class_gate_w .tab_btn_area .btn").removeClass("on");
                                    $(this).addClass("on");
                                    $(".class_gate_w .tab_img_area .img").removeClass("on");
                                    $(".class_gate_w .tab_img_area .img").eq(q).addClass("on");
                                    $(".class_gate_w .tab_con_area .con").removeClass("on");
                                    $(".class_gate_w .tab_con_area .con").eq(q).addClass("on");
                                    $(".class_gate_w .tab_con_area .con .img_w").removeClass("on");

                                    if ($(".srch_gate_pop_area").hasClass("show")) {
                                        imgResizingFn()
                                    }
                                }
                            });
                        });
                    }

                } else { // mobile
                    _deviceCondition = "mobile";

                    if ($(".form_select_div .list_wrap").is(":visible")) {
                        $("body").addClass("stop_scroll")
                    }

                    $("header .mypage_pop_area, header .place_pop_area").removeClass("transition");

                    if ($(".login_wrap").size() > 0) {
                        $(".login_wrap").css("min-height", window.innerHeight - $("footer").innerHeight())
                        $(".login_inner .left_layout").css("height", $(".login_wrap").innerHeight() - $(".login_inner .right_layout").innerHeight())
                    }

                    if ($(".page_title_area .tit_div.arrow").size() > 0) {
                        if ($(".page_title_area .tit_div.arrow").hasClass("active")) {
                            $(".page_title_area").addClass("bg_white");
                            $("body").addClass("stop_scroll");
                            $("body, html").on('scroll touchmove mousewheel', function(e) {
                                e.preventDefault();
                            });
                            $(".page_title_area .m_pop_dimd").stop(true, true).fadeIn(300);
                            $("footer, .btn_top").addClass("law_index");
                        } else {
                            $(".page_title_area").removeClass("bg_white");
                            $("body").removeClass("stop_scroll");
                            $("body, html").off("scroll touchmove mousewheel");
                            $(".page_title_area .m_pop_dimd").stop(true, true).fadeOut(300);
                            $("footer").removeClass("law_index");
                            setTimeout(function() {
                                $(".btn_top").removeClass("law_index");
                            }, 300);
                            $(".tit_popup").removeClass("on");
                        }

                        if ($(".page_title_area .tit_popup").is(":visible")) {
                            var timer = null;
                            clearTimeout(timer);

                            timer = setTimeout(function() {
                                if (window.innerWidth <= 1024) {
                                    $("body").removeClass("stop_scroll");
                                    $("body, html").off("scroll touchmove mousewheel");
                                    $(".page_title_area").removeClass("bg_white");
                                    $(".page_title_area .tit_div.arrow").removeClass("active");
                                    $(".page_title_area .tit_popup").stop(true, true).slideUp();
                                    $(".page_title_area .m_pop_dimd").stop(true, true).fadeOut(300);
                                    $("footer, .btn_top").removeClass("law_index");
                                    $(".m_navi_bar").removeClass("hide");
                                }
                            }, 100);
                        }
                    }

                    // 강좌상세 mobile (ex: CLS-01-009)
                    if ($(".right_box_fix_area").size() > 0) {
                        $(".right_box_fix_area .fix_box_area .course_popup").each(function() {
                            if (!$(this).hasClass("list")) {
                                $(".course_popup").find(".for_padding").height("auto");
                                $(".course_popup").height("auto");
                                $(".course_popup").css("max-height", "100%");
                                $(".course_popup, .course_popup .for_padding").removeClass("on");

                                if ($(this).find(".form_select_div").hasClass("on")) {
                                    $("body").addClass("stop_scroll");
                                    $("body, html").on("scroll touchmove mousewheel", function(e) {
                                        e.preventDefault();
                                    });
                                } else {
                                    $("body").removeClass("lock");
                                }

                            } else {
                                if ($(this).hasClass("active")) {
                                    $("body").addClass("stop_scroll");
                                    $("body, html").on("scroll touchmove mousewheel", function(e) {
                                        e.preventDefault();
                                    });
                                    $(".fix_box_area").find(".box_dimd").addClass("high_index").stop(true, true).fadeIn();
                                    setTimeout(function() {
                                        $("body").addClass("lock");
                                    }, 100);
                                    $(".course_popup.list .for_padding .box_con, .course_popup.list .btn_area").removeAttr("style");

                                    if ($(this).find(".form_select_div").hasClass("on")) {
                                        $(this).removeClass("active");
                                        $(".right_box_fix_area .fix_box_area .box_dimd").hide();
                                    }
                                }
                            }
                        });

                        // 강좌상세 내 강좌소개 더보기
                        if ($(".right_box_fix_area .info_img_txt").size() > 0) {
                            if ($(".right_box_fix_area .info_img_txt .inner_mobile").height() < 550) {
                                $(".right_box_fix_area .info_img_txt").removeClass("hide");
                                $(".right_box_fix_area .more_btn_wrap").css("display", "none");
                            } else {
                                $(".right_box_fix_area .info_img_txt").addClass("hide");
                                $(".right_box_fix_area .more_btn_wrap").removeAttr("style");

                                if ($(".more_btn_wrap .more_btn").hasClass("on")) {
                                    $(".right_box_fix_area .info_img_txt").removeClass("hide");
                                }
                            }
                        }
                    }

                    // 필터팝업 mobile버전
                    if ($(".filter_bar_div").size() > 0) {
                        if ($(".filter_list_wrap").not(".show").is(":visible")) {
                            $(".filter_list_wrap").hide();
                            $(".filter_open_area .btn").removeClass("active");
                        }
                    }

                    // 상세검색 팝업 mobile
                    if ($(".srch_popup").size() > 0) {
                        if ($(".srch_popup").hasClass("show")) {
                            $("body").addClass("stop_scroll");
                            $("body, html").on('scroll touchmove mousewheel', function(e) {
                                e.preventDefault();
                            });
                        }
                    }

                    // 페이지 상단 비주얼 영역
                    if ($(".top_visual.full .bg_img").size() > 0) {
                        if (window.innerWidth <= 1024) {
                            gsap.to(".page_cont_area .top_visual", {
                                scrollTrigger: {
                                    trigger: ".top_visual.full .bg_img",
                                    start: "top top",
                                    endTrigger: $(".page_cont_area .bg_inner"),
                                    end: "bottom bottom",
                                    pin: true,
                                    pinSpacing: false,
                                    // markers: true,
                                },
                            });
                        }
                    }

                    // 모바일 아코디언
                    if ($(".mobile_acco_div").size() > 0) { // 모바일 (ex: CLS-01-009)
                        $(".accordion_type").each(function() {
                            $(this).parents(".mobile_acco_div").removeClass("open");

                            if ($(".white_inner").size() > 0) {
                                if ($(this).parents(".mobile_acco_div").hasClass("first")) {
                                    $(this).find(".list_div").find(".hide_con").hide();
                                    $(this).parents(".mobile_acco_div").removeClass("first");
                                }
                            }
                        });
                    }

                    if ($(".slide_area .lecture_img_swiper").size() > 0) {
                        $(".lecture_img_swiper").each(function(q) {
                            if ($(this).parents(".slide_area").hasClass("spread")) {
                                $(this).removeClass("off_slide");
                            } else {
                                $(this).addClass("off_slide");
                            }
                        });
                    }

                    // 이용안내 - 강사/제휴 신청안내
                    if ($(".divide_area").size() > 0) {
                        if (window.innerWidth <= 1024) {
                            $(".divide_area .inner").each(function() {
                                if (_isScrollTop <= $(this).find(".left .inner_box").offset().top) {
                                    $(this).find(".left").addClass("active");
                                    $(this).find(".right").removeClass("active");
                                } else {
                                    $(this).find(".left").removeClass("active");
                                    $(this).find(".right").addClass("active");
                                }

                                $(this).find(".box").hover(function() {
                                    $(this).addClass("active").siblings(".box").removeClass("active");
                                });
                            });
                        }
                    }

                    // 결제 fixed_bar
                    if ($(".payment_bar").size() > 0) {

                        // 수강결제 resize
                        if ($(".total_price_info").size() > 0) {
                            $(".total_price_info").find(".txt_con").removeClass("on");
                            $(".hide_con_w").hide();
                        }

                    }

                    // 수강결제 팝업
                    if ($(".open_pop").size() > 0) {
                        $(".open_pop").hide();
                    }

                    $('.layer_popup').on("click", function(e) {
                        if (_deviceCondition == "mobile") {
                            if ($(e.target).parents().hasClass("pop_wrap")) {

                            } else {
                                if ($(this).find(".pop_wrap").hasClass("full")) {
                                    $(this).fadeOut(300, function() {
                                        $("body").removeClass("stop_scroll");
                                        $("#wrap").removeClass("stop_scroll");
                                        $("body, html").off("scroll touchmove mousewheel");
                                    });
                                } else {
                                    $(this).fadeOut(300, function() {
                                        $(".m_navi_bar").removeClass("hide");
                                        $("body").removeClass("stop_scroll");
                                        $("#wrap").removeClass("stop_scroll");
                                        $("body, html").off("scroll touchmove mousewheel");
                                    });
                                    $(this).find(".pop_wrap").css("transform", "translateY(100%)");

                                    if ($(".right_box_fix_area").size() > 0) {
                                        $(".page_title_area.white_ver, .right_box_fix_area .filter_bar_area, .course_popup, .course_popup .fixed_btn_area, .toast_popup").removeClass("law_index");
                                    }
                                }
                            }
                        }
                    });

                    if ($(".class_gate_w").size() > 0) {
                        $(".class_gate_w .tab_btn_area .btn").each(function(q) {
                            $(this).off("mouseover");
                        });
                    }
                }

                // 팝업 리사이즈
                popupResize();

                // 콘텐츠 영역 최소높이값 설정
                setTimeout(function() {
                    $(".cont_wrap, .cont_inner").css("min-height", window.innerHeight - $("footer").innerHeight());
                    repositioningTopBtn();
                }, 100);


                if ($(".layer_popup").is(":visible")) {
                    $("body").addClass("stop_scroll");
                    $("body, html").on('scroll touchmove mousewheel', function(e) {
                        e.preventDefault();
                    });
                    $("#wrap").addClass("stop_scroll");
                }
            }).resize();
        },
        formChkFn: function() {
            // select (focus 여부에 따른 화살표 전환 위한 스크립트)
            /*$(".form_select_arr").each(function(q){
        $(this).find("select").focus(function(){
          $(this).parents(".form_select").addClass("on");

          console.log("focus");
        });

        $(this).find("select").blur(function(){
          $(this).data('isopen', false);
          $(this).parents(".form_select").removeClass("on");
          if($(this).parents(".has_error_msg").size() > 0){
            if($(this).val() == ""){
              $(this).parents(".form_select, .has_error_msg").addClass("error");
            }else{
              $(this).parents(".form_select").removeClass("error");
              if($(this).closest(".has_error_msg").find(".error").size() == 0){
                $(this).parents(".has_error_msg").removeClass("error");
              }
            }
          }
        });

        $(this).find("select").on("change", function(){
          $(this).parents(".form_select").removeClass("on");
          if($(this).parents(".has_error_msg").size() > 0){
            if($(this).val() == ""){
              $(this).parents(".form_select, .has_error_msg").addClass("error");
            }else{
              $(this).parents(".form_select").removeClass("error");
              if($(this).closest(".has_error_msg").find(".error").size() == 0){
                $(this).parents(".has_error_msg").removeClass("error");
              }
            }
          }
        });
        
        $(this).find("select").mouseup(function(e) {
          var open = $(this).data("isopen");
    
          if(open) {
            $(this).parents(".form_select").removeClass("on");
            if($(this).parents(".has_error_msg").size() > 0){
              if($(this).val() == ""){
                $(this).parents(".form_select, .has_error_msg").addClass("error");
              }else{
                $(this).parents(".form_select").removeClass("error");
                if($(this).closest(".has_error_msg").find(".error").size() == 0){
                  $(this).parents(".has_error_msg").removeClass("error");
                }
              }
            }
          } else {
            $(this).parents(".form_select").addClass("on");
          }
          $(this).data("isopen", !open);
        });
      });*/

            // input checkbox (label 태그 내부에 텍스가 없을 시)
            $(".form_checkbox").each(function(q) {
                if ($(this).find("label").html() == "") {
                    $(this).addClass("no_txt")
                };
            });

            $(".form_radio").each(function(q) {
                if ($(this).find("label").html() == "") {
                    $(this).addClass("no_txt")
                }
            });

            // 검색 박스 지우기 버튼 노출/미노출
            $(".form_search, .form_input").each(function(q) {

                $(this).off().on("focusin", function() {
                    $(this).addClass("focus");
                    if ($(this).find("input").val() !== "") {
                        $(this).find(".btn_delete").show();

                        if ($(this).parents(".price_input_wrap").find(".form_input").hasClass("focus")) {
                            $(this).parents(".btn").removeClass("on");
                        }
                    }
                });

                $(this).find("input").off().on("focusout", function() {
                    $(this).parents(".form_search, .form_input").removeClass("focus")
                    setTimeout(function() {
                        $(".form_search, .form_input").eq(q).find(".btn_delete").hide();
                    }, 150)
                })

                $(this).find(".btn_delete").off().on("click", function() {
                    $(this).hide();
                    $(this).parents(".input_btn_wrap").siblings("input").val("").focus();
                    if ($(".change_input_box").size() > 0) {
                        $(".change_input_box").find(".s_color_btn").addClass("disabled");
                    }
                })

                $(this).find("input").on("keyup", function() {
                    if (!$(this).is('[readonly]')) {
                        if ($(this).val() !== "") {
                            $(this).closest(".form_search, .form_input").find(".btn_delete").show();
                            if ($(".filter_btn_list").size() > 0) {
                                $(this).parents(".filter_btn_list").find(".btn").removeClass("on");
                                $(this).parents(".btn").addClass("on");
                                $(this).parents(".list_div").find(".txt_box").removeClass("hide");
                                if (!$(".price_input_wrap.btn").hasClass("on")) {
                                    $(this).parents(".list_div").find(".f_body4").next().text($(".filter_btn_list .btn.on").length);
                                }

                                if ($(".price_input_wrap.btn").hasClass("on")) {
                                    $(this).parents(".list_div").find(".f_body4").next().text($(".price_input_wrap.btn.on").length);
                                }
                            }

                            if ($(this).parents(".login_pw").size() > 0) {
                                checkCapsLock(event)
                            }
                        } else {
                            if ($(".filter_btn_list").size() > 0) {
                                if ($(this).parents(".price_input_wrap").find(".form_input").eq(0).find("input").val() == "" && $(this).parents(".price_input_wrap").find(".form_input").eq(1).find("input").val() == "") {
                                    $(this).parents(".list_div").find(".txt_box").addClass("hide");
                                }

                                $(this).parents(".btn").removeClass("on");
                            }
                            $(this).closest(".form_search, .form_input").find(".btn_delete").hide();

                            if ($(this).parents(".login_pw").size() > 0) {
                                $(".login_pw").next().removeClass("on")
                            }
                        }
                    }
                });

                // 회원정보변경 텍스트 감지
                $(".change_input_box").find("input").on("keyup", function() {
                    var changeText = $(this).val();
                    if (changeText.length == 0) {
                        $(this).parents(".change_input_box").find(".s_color_btn").addClass("disabled");
                    } else {
                        $(this).parents(".change_input_box").find(".s_color_btn").removeClass("disabled");
                    }
                });
            });

            $(".form_textarea").each(function() {
                $(this).find("textarea").on("focusin", function() {
                    $(this).parents(".form_textarea").addClass("focus")
                });

                $(this).find("textarea").on("focusout", function() {
                    $(this).parents(".form_textarea").removeClass("focus")
                });
            });
            // 셀렉트 박스 첫번째 값 제외 선택한 경우
            $(".form_select_div").each(function() {
                $(this).find(".btn_open").off("click").on("click", function() {
                    if (!$(this).parents(".form_select_div").hasClass("on")) {
                        _pageScrollOffset = window.pageYOffset;

                        $(".form_select_div").find(".list_wrap").stop(true, true).slideUp();
                        $(".form_select_div").removeClass("on");
                        $(this).parents(".form_select_div").addClass("on");
                        $(this).parents(".form_select_div").find(".list_wrap").stop(true, true).slideDown();

                        if ($(this).parents().hasClass("layer_popup")) {
                            $(this).parents(".layer_popup").find(".scroll_area").addClass("hidden");
                        }

                        if (_deviceCondition == 'mobile') {
                            $("body").addClass("stop_scroll");
                            $("body, html").on('scroll touchmove mousewheel', function(e) {
                                e.preventDefault();
                            });
                        }

                        if ($(".right_box_fix_area .course_popup").size() > 0) {
                            if (window.innerWidth <= 1024) {
                                if ($(this).parents(".course_popup").hasClass("list")) {
                                    $(this).parents(".course_popup.list").removeClass("active");
                                    $(".right_box_fix_area .fix_box_area .box_dimd").hide();
                                } else {
                                    $(".page_title_area, .btn_top").addClass("law_index");
                                    $(".right_box_fix_area .filter_bar_area").addClass("law_index");
                                    $(this).parents(".course_popup").find(".btn_area").addClass("law_index");
                                }
                            }
                        }
                    } else {
                        $(this).parents(".form_select_div").removeClass("on");
                        $(this).parents(".form_select_div").find(".list_wrap").stop(true, true).slideUp();

                        if ($(this).parents().hasClass("layer_popup")) {
                            $(this).parents(".layer_popup").find(".scroll_area").removeClass("hidden");
                        }
                        if ($(this).parents(".layer_popup").size() <= 0) {
                            $("body").removeClass("stop_scroll");
                            $("body, html").off("scroll touchmove mousewheel");
                            $("#wrap").removeClass("stop_scroll");
                        }

                        if ($(".right_box_fix_area .course_popup").size() > 0) {
                            if ($(this).parents(".course_popup").hasClass("list")) {
                                $(this).parents(".course_popup.list").addClass("active");
                                $(".right_box_fix_area .fix_box_area .box_dimd").show();
                            } else {
                                $(".right_box_fix_area .filter_bar_area").removeClass("law_index");
                                $(".page_title_area, .btn_top").removeClass("law_index");
                                $(this).parents(".course_popup").find(".btn_area").removeClass("law_index");
                                $("body").removeClass("lock");
                                $("html").scrollTop(_pageScrollOffset);
                            }
                        }
                    }
                });

                $(this).find(".list_wrap .btn_link").each(function(q) {
                    $(this).off("click").on("click", function() {
                        if ($(this).parents(".scroll_area").hasClass("hidden")) {
                            $(this).parents(".scroll_area").removeClass("hidden");
                        }
                        if ($(this).parents(".layer_popup").size() <= 0) {
                            $("body").removeClass("stop_scroll");
                        }
                        if ($(this).parents(".form_select_div.disabled").size() <= 0) {
                            if ($(this).parents(".discount_area").size() <= 0) {
                                $(this).parents(".form_select_div").find(".open_area .btn_open").html($(this).html());
                                $(this).parents(".form_select_div").find(".select").html($(this).html());

                                if (!$(this).hasClass("default")) {
                                    $(this).parents(".form_select_div").addClass("change");
                                    $(this).parents(".list_wrap").find(".btn_link").removeClass("on")
                                    $(this).addClass("on")
                                } else {
                                    $(this).parents(".list_wrap").find(".btn_link").removeClass("on")
                                    $(this).parents(".form_select_div").removeClass("change");
                                }
                            }


                            $(this).parents(".form_select_div").removeClass("on");
                            $(this).parents(".form_select_div").find(".list_wrap").stop(true, true).slideUp();
                        }

                        if ($(".right_box_fix_area .course_popup").size() > 0) {
                            if (window.innerWidth <= 1024) {
                                $("body").removeClass("lock");
                                $(".page_title_area.white_ver, .right_box_fix_area .filter_bar_area, .course_popup, .course_popup .fixed_btn_area, .toast_popup").removeClass("law_index");
                                $("html").scrollTop(_pageScrollOffset);

                                if ($(this).parents(".course_popup.list").size() > 0) {
                                    $(this).parents(".course_popup.list").addClass("active");
                                    $(".right_box_fix_area .fix_box_area .box_dimd").show();
                                    $("body").addClass("stop_scroll");
                                    $("body, html").on("scroll touchmove mousewheel", function(e) {
                                        e.preventDefault();
                                    });
                                }
                            }
                        }

                        if ($(".cont_wrap").hasClass("main")) {
                            lazyLoading();
                            imgResizingFn();

                            $(this).parents(".category_wrap").find(".img_con").eq(q).find('img').attr('src', $(this).parents(".category_wrap").find(".img_con").eq(q).find('img').data().src);

                            $(this).parents(".category_wrap").find(".img_con").removeClass("on");
                            $(this).parents(".category_wrap").find(".img_con").eq(q).addClass("on");
                            $(this).parents(".category_wrap").find(".img_con").eq(q).fadeIn(250, function() {
                                $(this).parents(".category_wrap").find(".img_con").not($(this)).removeAttr("style");
                            });
                            $(this).parents(".category_wrap .bg").removeClass()
                            $(this).parents(".category_wrap > div").addClass("bg category" + q);
                        }
                    });
                });

                $(this).find(".dimd").off("click").on("click", function() {
                    if ($(this).parents(".layer_popup").size() <= 0) {
                        $("body").removeClass("stop_scroll");
                        $("#wrap").removeClass("stop_scroll");
                    }
                    if ($(this).parents(".scroll_area").hasClass("hidden")) {
                        $(this).parents(".scroll_area").removeClass("hidden");
                    }
                    $(this).parents(".form_select_div").removeClass("on");
                    $(this).parents(".form_select_div").find(".list_wrap").stop(true, true).slideUp();

                    if ($(".right_box_fix_area .course_popup").size() > 0) {
                        if (window.innerWidth <= 1024) {
                            $("body").removeClass("lock");
                            $(".page_title_area.white_ver, .right_box_fix_area .filter_bar_area, .course_popup, .course_popup .fixed_btn_area, .toast_popup").removeClass("law_index");
                            $("html").scrollTop(_pageScrollOffset);

                            if ($(this).parents(".course_popup.list").size() > 0) {
                                $(this).parents(".course_popup.list").addClass("active");
                                $(".right_box_fix_area .fix_box_area .box_dimd").show();
                                $("body").addClass("stop_scroll");
                                $("body, html").on("scroll touchmove mousewheel", function(e) {
                                    e.preventDefault();
                                });
                            }
                        }
                    }
                });

                $(this).find(".close").off("click").on("click", function() {
                    if ($(this).parents(".layer_popup").size() <= 0) {
                        $("body").removeClass("stop_scroll");
                        $("#wrap").removeClass("stop_scroll");
                    }

                    if ($(this).parents().hasClass("layer_popup")) {
                        $(this).parents(".layer_popup").find(".scroll_area").removeClass("hidden");
                    }

                    $(this).parents(".form_select_div").removeClass("on");

                    $(this).parents(".form_select_div").find(".list_wrap").stop(true, true).slideUp();

                    if ($(".right_box_fix_area .course_popup").size() > 0) {
                        if (window.innerWidth <= 1024) {
                            $("body").removeClass("lock");
                            $(".page_title_area.white_ver, .right_box_fix_area .filter_bar_area, .course_popup, .course_popup .fixed_btn_area, .toast_popup").removeClass("law_index");
                            $("html").scrollTop(_pageScrollOffset);

                            if ($(this).parents(".course_popup.list").size() > 0) {
                                $(this).parents(".course_popup.list").addClass("active");
                                $(".right_box_fix_area .fix_box_area .box_dimd").show();
                                $("body").addClass("stop_scroll");
                                $("body, html").on("scroll touchmove mousewheel", function(e) {
                                    e.preventDefault();
                                });
                            }
                        }
                    }
                });
            });
        },
        scrollTriggerFn: function() {
            // 우측 박스 픽스 - CLS-01-009.html
            if ($(".right_box_fix_area").size() > 0) {
                ScrollTrigger.saveStyles(".fixed_content_area");
                ScrollTrigger.matchMedia({
                    "(min-width: 1024.1px)": function() {
                        gsap.to(".right_box_fix_area", {
                            scrollTrigger: {
                                trigger: ".fixed_content_area",
                                start: "top+=70px 100",
                                // end: "bottom-=98px " + $(".course_popup").height(),
                                end: "bottom-=138px " + _rightH,
                                endTrigger: $(".flow_txt_area"),
                                pin: true,
                                pinSpacing: false,
                                // markers: true,
                            }
                        });
                    },
                });
            }
        },
        popupFn: function() {
            // 팝업 닫기

            $(".layer_popup .pop_wrap .btn_close").on("click", function() {
                if (!$("header .place_pop_area").hasClass("show")) {
                    if (window.innerWidth <= 1024) {
                        if ($(this).parents(".layer_popup").find(".pop_wrap").hasClass("full")) {
                            $(this).parents(".layer_popup").fadeOut(300, function() {
                                $("body").removeClass("stop_scroll");
                                $("body, html").off("scroll touchmove mousewheel");
                                $("#wrap").removeClass("stop_scroll");
                                $("body, html").scrollTop(_pageScrollOffset);
                            });
                        } else {
                            $(this).parents(".layer_popup").fadeOut(function() {
                                $(".m_navi_bar").removeClass("hide")
                                $("body").removeClass("stop_scroll");
                                $("body, html").off("scroll touchmove mousewheel");
                                $("#wrap").removeClass("stop_scroll");
                                $("body, html").scrollTop(_pageScrollOffset);
                            });
                            $(this).parents(".layer_popup").find(".pop_wrap").css("transform", "translateY(100%)");

                            if ($(".right_box_fix_area").size() > 0) {
                                $(".page_title_area.white_ver, .right_box_fix_area .filter_bar_area, .course_popup, .course_popup .fixed_btn_area, .toast_popup").removeClass("law_index");
                            }
                        }
                        $("footer").removeAttr("style")
                    } else {
                        $(this).parents(".layer_popup").fadeOut(300, function() {
                            $("body").removeClass("stop_scroll");
                            $("body, html").off("scroll touchmove mousewheel");
                            $("#wrap").removeClass("stop_scroll");
                            $("body, html").scrollTop(_pageScrollOffset);
                        });
                    }
                }
            });

        },
        openPopupFn: function(popName, comebackEl, customFunc) {
            var designatedPopup = $(popName) || $("#popName");
            var comebackElement = comebackEl;

            _pageScrollOffset = window.pageYOffset;

            gsap.delayedCall(0.1, function() {
                designatedPopup.fadeIn();
                designatedPopup.attr("tabindex", 0).focus();
                popupResize();

                //gsap 완료 후 호출할 함수
                if (customFunc) {
                    customFunc();
                }
                designatedPopup.find("input[type=text]").eq(0).focus();
                $("body").addClass("stop_scroll");
            });

            designatedPopup.find(".btn_close").on("click", function() {
                $("body, html").scrollTop(_pageScrollOffset);
                designatedPopup.hide();
                if ($("#calcBtn").size() <= 0) {
                    comebackElement.attr("tabindex", 0).show().focus();
                }
                if (!$("header .place_pop_area").hasClass("show")) {
                    $("body").removeClass("stop_scroll");
                }
            });
        },
    }
})();

$(window).on("load", function() {
    commonScript.deviceChk();
    commonScript.init();
    // commonScript.headerFooterFn();
    gsap.delayedCall(0.2, function() {
        commonScript.swiperFn();
        commonScript.commonFn();
        commonScript.formChkFn(); // form 관련 스크립트
        commonScript.resizeFn();
        commonScript.scrollTriggerFn(); // 스크롤 트리거 모션
        commonScript.scrollFn();
        commonScript.popupFn();
    });

});

function imgResizingFn() {
    var imgNum;
    $(".img_resize_w").each(function() {
        if ($(this).find("img").size() >= 2) {
            if (_winWidth > 768) {
                imgNum = 0;
            } else {
                imgNum = 1;
            }
        } else {
            imgNum = 0;
        }

        if ($(this).find("img").get(imgNum).naturalWidth / $(this).find("img").get(imgNum).naturalHeight <= $(this).width() / $(this).innerHeight()) {
            $(this).addClass("reverse");
        } else {
            $(this).removeClass("reverse");
        }
    });
}

function scrollMotionTrigger() {
    if ($(".scroll_motion").size() > 0) {
        $(".scroll_motion:visible").each(function(q) {
            gsap.to($(this), {
                scrollTrigger: {
                    trigger: $(this),
                    start: "top 80%",
                    end: "bottom top",
                    toggleClass: {
                        targets: $(".scroll_motion:visible").eq(q),
                        className: "active"
                    },
                    once: true,
                    // markers: true,
                },
            });
        });
    }
}

function popupResize() {
    $(".layer_popup:visible").css("top", "0px") // 스크롤 없는 팝업 내에서 생기는 ios 노치영역 배경색 오류 해결 위해 적음

    var safetyChar = getComputedStyle(document.documentElement).getPropertyValue("--sab")
    var safetyNum = parseInt(safetyChar.split("p"));
    var fixedBtn = 0;

    $(".layer_popup:visible").height(window.innerHeight);

    if ($(".fixed_out_btn").size() > 0) {
        fixedBtn = $(".layer_popup:visible").find(".fixed_out_btn").outerHeight(true)
    }

    if (_deviceCondition == 'pc') {
        $(".layer_popup:visible").find(".pop_wrap, .pop_cont").css({
            "height": "",
            "transform": "translate(0,0)"
        });

        $(".layer_popup:visible").find(".pop_cont").height($(".layer_popup:visible").find(".pop_wrap").height() - $(".layer_popup:visible").find(".pop_head").innerHeight())
        $(".layer_popup:visible").find(".pop_wrap").height(Math.ceil($(".layer_popup:visible").find(".pop_cont").height() + $(".layer_popup:visible").find(".pop_head").innerHeight())); // 소수점 버림


    } else if (_deviceCondition == "mobile") {
        if ($(".fixed_bot_btn").size() <= 0 && $(".fixed_out_btn").size() <= 0) {
            $(".layer_popup:visible").find(".pop_cont .for_padding").css("padding-bottom", 30 + safetyNum)
        }

        $(".layer_popup:visible").find(".pop_wrap, .pop_cont").css({
            "height": "auto",
            "transform": "translate(0,0)"
        });
        $(".layer_popup:visible").find(".pop_wrap.full").css("height", window.innerHeight) // 소수점 버림
        $(".layer_popup:visible").find(".pop_cont").height($(".layer_popup:visible").find(".pop_wrap").height() - $(".layer_popup:visible").find(".pop_head").innerHeight() + 1)

        if ($(".fixed_bot_btn").size() > 0) {} else if ($(".fixed_out_btn").size() > 0) {
            $(".layer_popup:visible").find(".for_padding").css({
                "height": "auto",
                "padding-bottom": 40 + safetyNum
            });
            $(".layer_popup:visible").find(".for_padding").innerHeight($(".layer_popup:visible").find(".pop_cont").height() - fixedBtn);
        }
    }

    $(".layer_popup:visible").find(".pop_wrap").css({
        "margin-left": $(".layer_popup:visible").find(".pop_wrap").width() * -0.5,
        "margin-top": $(".layer_popup:visible").find(".pop_wrap").height() * -0.5
    }); // 중앙정렬


}

function classScrollPosition() {
    if ($(".select_reload").size() > 0) {
        gsap.to($("html, body"), 0, {
            scrollTop: $(".fixed_mobile_w").offset().top,
            delay: 2,
            ease: Power3.easeOut
        })
    }
}


function repositioningTopBtn() {
    if ($(".btn_top").length) {
        if ($(window).scrollTop() > 0) {
            if (!$(".filter_list_wrap").hasClass("show")) {
                $(".btn_top").fadeIn();
            }
            if ($(".review_write").size() > 0) {
                if (window.innerWidth <= 1024) {
                    $(".review_write").fadeIn()
                }
                $(".review_write").addClass("move")
            }
        } else {
            $(".btn_top").fadeOut()
            if ($(".review_write").size() > 0) {
                if (window.innerWidth <= 1024) {
                    $(".review_write").fadeOut()
                } else {
                    $(".review_write").removeAttr("style")
                }
                $(".review_write").removeClass("move")
            }
        }

        // var topBtnPositionGap = 0;
        var navigationGap = 0;
        _paybarH = $(".payment_bar").innerHeight();
        _fixedBtnH = $(".course_popup .fixed_btn_area").outerHeight(true);
        var safetyChar = getComputedStyle(document.documentElement).getPropertyValue("--sab")
        var safetyNum = parseInt(safetyChar.split("p"));
        // console.log(safetyChar, safetyNum);
        var topBtnGap = 40;

        if (window.innerWidth <= 1024) {
            if ($(".payment_bar").size() > 0 || $(".course_popup .fixed_btn_area").size() > 0) {
                if ($(".shopbag_w").size() > 0) {
                    navigationGap = 64 + safetyNum;
                } else {
                    navigationGap = 0;
                }
            } else {
                navigationGap = 64 + safetyNum;
                $("footer").css("padding-bottom", 110 + safetyNum)

            }
        }

        if ($(window).scrollTop() + window.innerHeight > $("footer").offset().top + navigationGap) {
            // 푸터에 붙었을 때,
            if (_winWidth > 1024) {
                if ($(".payment_bar").size() > 0) {
                    $(".btn_top").css("bottom", $(window).scrollTop() + window.innerHeight - $("footer").offset().top + _paybarH + 20);
                    $(".payment_bar").css("bottom", $(window).scrollTop() + window.innerHeight - $("footer").offset().top)
                } else {
                    $(".btn_top").css("bottom", $(window).scrollTop() + window.innerHeight - $("footer").offset().top + 40);
                    $(".review_write").css("bottom", $(window).scrollTop() + window.innerHeight - $("footer").offset().top + 40);
                }
            } else {
                if ($(".payment_bar").size() > 0) {
                    $(".page_cont_area").css({
                        "padding-bottom": $(".payment_bar").innerHeight() + $(".btn_top").innerHeight() + topBtnGap
                    });
                    $(".btn_top").addClass("no_fixed");
                    $(".payment_bar").addClass("no_fixed");
                    $(".btn_top").css("bottom", $("footer").innerHeight() + _paybarH + 20);
                    $(".payment_bar").css("bottom", $("footer").innerHeight())
                } else {
                    $(".btn_top").addClass("no_fixed");
                    $(".btn_top").css("bottom", $("footer").innerHeight() + 20);
                    $(".review_write").css("bottom", $(window).scrollTop() + window.innerHeight - $("footer").offset().top + 78);
                }
            }
        } else {
            // 스크롤 할 때,
            $(".payment_bar").css("bottom", navigationGap);
            $(".payment_bar").removeClass("no_fixed");
            $(".btn_top").removeClass("no_fixed");

            if (_winWidth > 1024) {
                if ($(".payment_bar").size() > 0) {
                    $(".btn_top").css("bottom", _paybarH + 20);
                } else {
                    $(".btn_top").css("bottom", 40);
                    $(".review_write").css("bottom", 40);
                }
            } else {
                if ($(".payment_bar").size() > 0) {
                    $(".page_cont_area").css({
                        "padding-bottom": $(".payment_bar").innerHeight() + $(".btn_top").innerHeight() + topBtnGap
                    });
                    if ($(".shopbag_w").size() > 0) {
                        $(".btn_top").css("bottom", _paybarH + $(".shopbag").innerHeight());
                    } else {
                        $(".btn_top").css("bottom", _paybarH + 20);
                    }
                } else {
                    $(".btn_top").css("bottom", 16 + navigationGap);
                    $(".review_write.move").css("bottom", 74 + navigationGap);
                }
            }
        }

        if ($(".course_popup .fixed_btn_area").size() > 0) {
            if (window.innerWidth <= 1024) {
                if ($(window).scrollTop() + window.innerHeight > $("footer").offset().top + _fixedBtnH) {
                    $(".btn_top").addClass("no_fixed");
                } else {
                    $(".btn_top").removeClass("no_fixed");
                    $(".btn_top").css("bottom", _fixedBtnH + 15);
                }
            }
        }
    }
}

function couponChange() {
    if ($(".coupon_list").size() > 0) {
        $(".coupon_list .coupon").each(function() {
            $(this).find(".change").on("click", function() {
                if (!$(this).parents(".coupon").hasClass("on")) {
                    $(this).parents(".coupon").addClass("on");
                    $(this).parents(".coupon").find(".content").eq(0).removeClass("on");
                    $(this).parents(".coupon").find(".content").eq(1).addClass("on");
                    $(this).parents(".coupon").find(".change span").remove();
                    $(this).parents(".coupon").find(".change").append('<span>쿠폰<br class="only_mobile">보기</span>');
                } else {
                    $(this).parents(".coupon").removeClass("on");
                    $(this).parents(".coupon").find(".content").eq(1).removeClass("on");
                    $(this).parents(".coupon").find(".content").eq(0).addClass("on");
                    $(this).parents(".coupon").find(".change span").remove();
                    $(this).parents(".coupon").find(".change").append('<span>설명<br class="only_mobile">보기</span>');
                }
            });

        });
    }
}

function accorRecall() {
    if ($(".accordion_type").size() > 0) {
        $(".accordion_type").each(function() {
            $(this).find(".list_div .list").off().on("click", function() {
                if ($(this).parents(".accordion_type").hasClass("one_open")) { // 한개만 열려야될때
                    if (!$(this).parent().hasClass("on")) {
                        $(this).parents(".accordion_type").find(".list_div").removeClass("on");
                        $(this).parent().addClass("on");
                        $(this).parents(".accordion_type").find(".list_div .hide_con").stop(true, true).slideUp();
                        $(this).siblings(".hide_con").stop(true, true).slideDown();
                    } else {
                        $(this).parent().removeClass("on");
                        $(this).siblings(".hide_con").stop(true, true).slideUp();
                    }
                } else {
                    $(this).parent().toggleClass("on");

                    if ($(this).parent().hasClass("on")) {
                        $(this).siblings(".hide_con").stop(true, true).slideDown();
                    } else {
                        $(this).siblings(".hide_con").stop(true, true).slideUp();
                    }
                }
            });

            // 온라인 신청가이드
            $(".guide_con .list_div").each(function() {
                $(this).on("click", function() {
                    if ($(this).hasClass("on")) {
                        $(this).siblings(".num_wrap").find(".num").addClass("on");
                    } else {
                        $(this).siblings(".num_wrap").find(".num").removeClass("on");
                    }
                });
            });
        });
    }
}

// 지점안내 슬라이드
function storeSwiperRecall() {
    if ($(".store_info_swiper").size() > 0) {
        $(".store_info_swiper").each(function(q) {
            if ($(this).find(".swiper-slide").length > 1) {
                $(this).find(".swiper-container").after($('<div class="swiper-button-prev"></div>'));
                $(this).find(".swiper-container").after($('<div class="swiper-button-next"></div>'));

                var storeInfoSwiper = new Swiper($(this).find(".swiper-container"), {
                    slidesPerView: "auto",
                    observer: true,
                    observeParents: true,
                    speed: 800,
                    loop: true,
                    pagination: {
                        el: $(this).find(".swiper-pagination"),
                        type: "fraction",
                    },
                    navigation: {
                        nextEl: $(this).find(".swiper-button-next"),
                        prevEl: $(this).find(".swiper-button-prev"),
                    },
                });

                $(".store_info_swiper").hover(function() {
                    $(".store_info_swiper").find(".swiper-button-next, .swiper-button-prev").fadeIn(250);
                }, function() {
                    $(".store_info_swiper").find(".swiper-button-next, .swiper-button-prev").fadeOut(250);
                });
            } else {
                var storeInfoSwiper = new Swiper($(this).find(".swiper-container"), {
                    slidesPerView: "auto",
                    observer: true,
                    observeParents: true,
                    speed: 800,
                    loop: true,
                    pagination: {
                        el: $(this).find(".swiper-pagination"),
                        type: "fraction",
                    },
                });
            }
        });
    }
}

// 3depth 메뉴
function thrdepRecall() {
    if ($(".thr_dep_area").size() > 0) {
        var thrDepSwiper = [];
        var thrTotalWidth = 0;

        $(".thr_dep_area").each(function(q) {
            $(this).find(".swiper-container").addClass("thr_dep_area" + q);

            thrDepSwiper[q] = new Swiper(".thr_dep_area" + q, {
                slidesPerView: "auto",
                observer: true,
                observeParents: true,
                on: {
                    init: function() {
                        $(".thr_dep_area" + q).addClass("start");
                    },
                    transitionStart: function() {
                        $(".thr_dep_area" + q).removeClass("start");
                        $(".thr_dep_area" + q).removeClass("end");
                    },
                    transitionEnd: function() {
                        if (!$(".thr_dep_area").hasClass("all")) {
                            if (this.isEnd) {
                                $(".thr_dep_area" + q).addClass("end");
                                $(".thr_dep_area" + q).removeClass("start");
                            } else if (this.isBeginning) {
                                $(".thr_dep_area" + q).addClass("start");
                                $(".thr_dep_area" + q).removeClass("end");
                            }
                        }
                    }
                }
            });

            if (!$(this).find(".drop_btn").length == 0) {
                $(this).addClass("drop_type");
            } else {
                $(this).removeClass("drop_type");
            }

            $(this).find(".drop_btn").on("click", function() {
                if (!$(this).parents(".thr_dep_area").hasClass("click")) {
                    $(this).parents(".thr_dep_area").addClass("click");
                } else {
                    $(this).parents(".thr_dep_area").removeClass("click");
                    $(this).parents(".thr_dep_area").addClass("start");
                }
            });

            $(this).find(".swiper-slide").on("click", function() {
                $(this).addClass("on").siblings().removeClass("on");

                if ($(this).parents(".thr_dep_area").hasClass("click")) {
                    thrDepSwiper[q].slideTo($(".thr_dep_area .swiper-slide.on").index(), 0, false);

                    $(this).parents(".thr_dep_area").removeClass("click");
                }

                // $(this).parents(".thr_dep_area").removeClass("click");
            });

            // 3depth 너비관련
            $(this).find(".swiper-slide").each(function(i) {
                thrTotalWidth += $(this).outerWidth(true);
            });
            if (thrTotalWidth <= $(this).find(".swiper-container").width()) {
                $(this).addClass("all");
                $(this).find(".drop_btn").removeClass("show");
            } else {
                $(this).removeClass("all click");
                // $(this).removeClass("all");
                $(this).find(".swiper-container").removeClass("start");
                $(this).find(".swiper-container").removeClass("end");
                $(this).find(".drop_btn").addClass("show");
            }
        });
    }
}

// 탭메뉴 드롭다운
function dropDownRecall() {
    // 2depth 너비관련
    if ($(".circle_sel_swiper").size() > 0) {
        $(".circle_sel_swiper").each(function(q) {
            _barWidthArr[q] = $(this).find(".swiper-slide").outerWidth(true) * $(this).find(".swiper-slide").length;

            if (_barWidthArr[q] < $(this).parents(".tab_con_area").width()) {
                $(this).addClass("all");
            } else {
                $(this).removeClass("all");
            }
        });
    }
}

// 상세검색 검색데이터
function srchDataRecall() {
    if ($(".filter_bar_area .srch_data_div").size() > 0) {
        // pc
        // _isSrchOncePc = false;
        // _isSrchOnceMo = false;

        if (_winWidth > 1024) {
            if (!_isSrchOncePc) {
                _isSrchOncePc = true;
                _isSrchOnceMo = false;

                if (typeof _srchDataSwiper != "undefined") {
                    _srchDataSwiper.destroy()
                }
                _srchDataSwiper = new Swiper(".filter_bar_area .srch_data_div .swiper-container", {
                    slidesPerView: "auto",
                    simulateTouch: false,
                    observer: true,
                    observeParents: true,
                    noSwiping: true,
                });
            }
        } else {
            if (!_isSrchOnceMo) {
                _isSrchOncePc = false;
                _isSrchOnceMo = true;

                _srchDataSwiper = new Swiper(".filter_bar_area .srch_data_div .swiper-container", {
                    slidesPerView: "auto",
                    observer: true,
                    observeParents: true,
                    on: {
                        init: function() {
                            $(".filter_bar_area .srch_data_list").addClass("start");
                        },
                        transitionEnd: function() {
                            if (_srchDataSwiper.isBeginning) {
                                $(".filter_bar_area .srch_data_list").addClass("start");
                                $(".filter_bar_area .srch_data_list").removeClass("end");
                            } else if (_srchDataSwiper.isEnd) {
                                $(".filter_bar_area .srch_data_list").addClass("end");
                                $(".filter_bar_area .srch_data_list").removeClass("start");
                            }
                        }
                    }
                });
            }
        }
    }
}

function popScrollPosition() {
    $(".layer_popup .scroll_area").on("scroll", function() {
        _popScrollTop = $(".layer_popup:visible").find(".pop_cont .scroll_area").scrollTop();

        $(".layer_popup .form_input input, .layer_popup .form_textarea textarea").off().on("focusout", function() {
            gsap.to($(".layer_popup").find(".scroll_area"), 0, {
                scrollTop: _popScrollTop,
                delay: 0.05,
                ease: Power3.easeOut
            })
        })
    }).scroll()
}

function fixedMobileH() {
    if ($(".fixed_mobile_w").size() > 0) {
        $(".fixed_mobile_w").each(function() {
            $(this).css("height", $(".fixed_mobile_w:visible").find(".fixed_mobile").height());
        });
    }
}

function moreStudent() {
    if ($(".single_type").size() > 0) {
        $(".cour_his_list").find(".drop_type").off().on("click", function() {
            $(this).parents(".cour_his_list").toggleClass("on");
            if ($(this).parents(".cour_his_list").hasClass("on")) {
                $(this).find("p").text("수강자 닫기");
                $(this).siblings(".content_w").find(".hiding_con").stop(true, true).slideDown();
            } else {
                var hideL = $(this).siblings(".content_w").find(".hiding_con").length;
                // $(this).find("p").text("수강자 더보기" + "(" + "+" +hideL + ")");
                $(this).find("p").text("수강자 더보기");
                $(this).siblings(".content_w").find(".hiding_con").stop(true, true).slideUp();
            }
        })
    }
    //수강자, 강의가 드롭될 때, ex) MYP-03-001_T01.html
    else {
        $(".cour_his_list").find(".drop_type").off().on("click", function() {
            $(this).parents(".cour_his_list").toggleClass("on");
            if ($(this).parents(".cour_his_list").hasClass("on")) {
                $(this).find("p").text("정보 닫기");
                $(this).siblings(".hiding_con").stop(true, true).slideDown();
                $(this).siblings(".content_w").find(".hiding_con").stop(true, true).slideDown();
            } else {
                // var hideL = $(this).siblings(".hiding_con").length;
                // $(this).find("p").text("정보 더보기" + "(" + "+" +hideL + ")");
                $(this).find("p").text("정보 더보기");
                $(this).siblings(".hiding_con").stop(true, true).slideUp();
                $(this).siblings(".content_w").find(".hiding_con").stop(true, true).slideUp();
            }
        })
    }
}

function filterSelect() {
    // 다중 필터 선택
    $(".filter_btn_list").each(function(i) {
        $(this).find(".btn").each(function(q) {
            $(this).off().on("click", function() {
                if (!$(this).hasClass("price_input_wrap")) {
                    $(this).toggleClass("on");
                    $(this).parents(".filter_btn_list").find(".price_input_wrap").removeClass("on");
                    $(this).parents(".filter_btn_list").find(".price_input_wrap input").val("");

                    var num = $(".filter_btn_list").eq(i).find(".btn.on").length;
                    var total_num = $(this).parents(".list_div").find(".btn.on").length;
                    $(this).parents(".divide_wrap").find(".txt_box .num").text(num);
                    $(this).parents(".list_div").find(".f_body4").next().text(total_num);

                    if ($(this).parents(".list_div").find(".btn.on").length == 0) {
                        $(this).parents(".list_div").find(".f_body4").parent().addClass("hide");
                    } else {
                        $(this).parents(".list_div").find(".f_body4").parent().removeClass("hide");
                    }

                    $(".reset_wrap .filter_btn").on("click", function() {
                        $("body").removeClass("stop_scroll");
                        $("body, html").off("scroll touchmove mousewheel");
                        $("body").removeClass("lock");
                        $("html").scrollTop(_pageScrollOffset);

                        $(".srch_popup").removeClass("show");
                        $(".srch_popup").find(".dimd").stop(true, true).fadeOut(400);
                        $(".filter_bar_area").removeClass("high_index");
                        $(".page_title_area .tit_popup").removeClass("slideUp");

                        $(".srch_popup .accordion_type .list_div").each(function(q) {
                            if ($(this).parents(".srch_popup").find(".accordion_type .list_div.on").length > 0) {
                                if (!$(this).find(".hide_con .btn").hasClass("on")) {
                                    $(this).removeClass("on");
                                    $(this).find(".hide_con").stop(true, true).slideUp();
                                }
                            }
                        });
                    });
                }
            });
        });
    });
}

var lazyLoading = () => {
    var imgs = document.querySelectorAll('.lazy');

    var observerCallback = (entries, observer) => {
        entries.forEach(({
            isIntersecting,
            intersectionRatio,
            target
        }) => {
            if (isIntersecting && intersectionRatio > 0) {
                target.src = target.dataset.src;
                target.classList.remove("lazy");
                observer.unobserve(target);
            }
        });
    };

    var io = new IntersectionObserver(observerCallback);
    imgs.forEach((img) => io.observe(img));
};

function checkCapsLock(event) {
    if (event.getModifierState("CapsLock")) {
        $(".login_pw").next().addClass("on")
    } else {
        $(".login_pw").next().removeClass("on")
    }
}