var _opacitySlide; // 추천강좌, 신규강좌 투명 슬라이드
var _isFirst = true; // 마지막 슬라이드 도달 후 전 슬라이드 돌아갈 때 변수
// var _visualSwiper; // 비주얼 스와이퍼 변수
var _resizeCheck; // 리사이즈 END체크
var _videoTime; // 비디오 영상 시간
var _wrap;
var _scrollBar;
var _winWidth = 0;

var mainScript = (function(){
  return {
    commonFn: function(){

      gsap.config({nullTargetWarn:false});

      _wrap = document.getElementById("wrap");
      _scrollBar = window.innerWidth - $(window).width();
      _winWidth = Math.ceil(_wrap.getBoundingClientRect().width) + _scrollBar;
    },
    scrollFn: function(){
      // not IE
      $(window).on("scroll", function(){
        _isScrollTop =  $(window).scrollTop();        
      });$(window).scroll()

      // for IE
      $("body").on("scroll", function(){
        
      });

    },
    resizeFn: function(){
      $(window).resize(function(){
        
        $(".visual_area").addClass("active");
        setTimeout(function(){
          $(".visual_wrap .form_search").addClass("active");
          $(".visual_area .swiper-slide-active").addClass("on");
          _isFirst = false;
        },1300)

        if($(".category_wrap").size() > 0){
          setTimeout(function(){
            if(_winWidth > 1024){
              $(".category_wrap .half_wrap .form_select_div").css("left", $(".category_wrap .inner").offset().left);
            }else{
              $(".category_wrap .half_wrap .form_select_div, .category_wrap .half_wrap .left").removeAttr("style");
            }
          }, 150)
          
        }

        lectureSwiperFn()

      }).resize();      
    },
    swiperFn: function(){

      if($(".lecture_list_w").size() > 0){
        var lectureSwiper = [];
        $(".lecture_list_w").each(function(q){
          $(this).find(".swiper-container").addClass("lec_list_swiper" + q);

          if($(this).find(".swiper-slide").size() <= 4){
          }else{
            $(this).append('<div class="swiper-button-next"></div>')
            $(this).append('<div class="swiper-button-prev"></div>')
          }


          lectureSwiper[q]= new Swiper(".lec_list_swiper"+q, {
            slidesPerView: "auto",
            // loop:true,
            observer: true,
            observeParents: true,
            // noSwipe:true,
            noSwipingClass:'no_swiping',
            pagination: {
              el: $(this).find(".swiper-pagination"),
              type: "progressbar",
            },
            navigation: {
              nextEl: $(this).find(".swiper-button-next"),
              prevEl: $(this).find(".swiper-button-prev"),
            },
          });
        });
      }

      $(window).resize(function(){
        if(_resizeCheck){ //계속 리사이즈중이면 clear
          clearTimeout(_resizeCheck); 
        };
        if($(".main").size() > 0){
          $(".event_slide_wrap .swiper-container").width(Math.floor($(".event_slide_wrap").width()))
        }
      }).resize()
    },
    scrollTriggerFn: function(){
      ScrollTrigger.matchMedia({
        "(min-width : 1024.1px)": function(){
          gsap.to(".category_wrap .half_wrap .left", {
            scrollTrigger: {
              trigger: ".category_wrap .half_wrap .left",
              start: "top top",
              end: "bottom bottom",
              endTrigger:$(".category_wrap .half_wrap"),
              pin: true,
              // markers: true,
              onEnter:function(){
                $(".category_wrap .half_wrap .left").addClass("in");
              },
              onEnterBack:function(){
                $(".category_wrap .half_wrap .left").addClass("in");
              },
              onLeave:function(){
                $(".category_wrap .half_wrap .left").removeClass("in");
              },
              onLeaveBack:function(){
                $(".category_wrap .half_wrap .left").removeClass("in");
              }
            },
          });
        }
      });
    }
  }
})();

$(window).on("load", function(){
  mainScript.commonFn();
  mainScript.scrollFn();
  mainScript.resizeFn();
  mainScript.swiperFn();
  mainScript.scrollTriggerFn();
});

function eventSwiperFn(){
  var eventProgressBarMotion = gsap.to($(".event_slide_wrap .progress_bar .bar"), 4, {width:"100%", ease:"none", onComplete:function(){
    eventLargeSwiper.slideNext();
  }});
  

  var eventLargeSwiper = new Swiper(".event_slide_wrap .swiper-container", {
    parallax: true,
    loop: true,
    speed: 1000,
    observer: true,
    observeParents: true,
    navigation: {
      nextEl: $(".event_slide_wrap").find(".swiper-button-next"),
      prevEl: $(".event_slide_wrap").find(".swiper-button-prev"),
    },
    pagination: {
      el: $(".event_slide_wrap").find(".swiper-pagination"),
      type: "fraction",
    },
    on:{
      init:function(){
        $(".event_slide_wrap .left .swiper-container .swiper-slide-active").addClass("active");
      },
      slideChangeTransitionEnd:function(){

        $(".event_slide_wrap .left .swiper-container .swiper-slide").removeClass("active");
        $(".event_slide_wrap .left .swiper-container .swiper-slide-active").addClass("active");
        $(".event_slide_wrap .left .swiper-container .swiper-slide-duplicate-active").addClass("active");

        if($(".event_slide_wrap .visual_btn").hasClass("pause")){
          eventProgressBarMotion.restart();
        }
      },
    }
  });

  $(".event_slide_wrap .visual_btn").on("click", function(){
    if($(this).hasClass("pause")){
      $(this).removeClass("pause");
      $(this).addClass("play");
      eventProgressBarMotion.pause();
    }else if($(this).hasClass("play")){
      $(this).removeClass("play");
      $(this).addClass("pause");
      eventProgressBarMotion.play();
    }
  });
}

function lectureSwiperFn(){
  $(".lecture_list_w").each(function(q){
    if($(this).find(".swiper-slide").size() <= 4){
      if(window.innerWidth > 1280){
        $(this).find(".swiper-container").addClass("no_swiping")
      }else{
        $(this).find(".swiper-container").removeClass("no_swiping")
      }
    }else{
      $(this).find(".swiper-container").removeClass("no_swiping")
      $(this).find(".swiper-container").addClass("swiping")
    }
  });
}



