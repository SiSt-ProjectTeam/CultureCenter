var common = (function(){
	
	"use strict";
	
	var logOutCount = 60;
	
	var logOutTimer = {
		
        timer : null,
        count : null,
        limit : 1000 * 60 * 29,		//29분
        startTime : null,
        start : function() {
        	logOutTimer.timer = window.setTimeout(logOutTimer.fnc, logOutTimer.limit);
        	logOutTimer.startTime = new Date().getTime();
        },
        reset : function() {
        	window.clearTimeout(logOutTimer.timer);
        	logOutTimer.start();
        	logOutCount = 60;
        	$("#logOutGuide #limitTime").text(logOutCount + "초");
        },
    	fnc   : function() {
    		fn_count_timer();		//count 시작
    	}
	};
	
	//로그아웃 팝업 타이머
	var fn_count_timer = function()
	{
		//로그인 여부 체크
		fnc.bscAjax(function(data){ if(!data.lgnYn){ fnc.moveLogout(true); } }, "/lgnCheck.ajax");
		
		fn_check_timer();
		
		commonScript.openPopupFn("#logOutGuide", $(this));		//팝업 노출
		
		logOutTimer.count = setInterval(function(){ 
			fn_check_timer();
			
			$("#logOutGuide #limitTime").text(--logOutCount + "초");
		
		}, 1000);
	}
	
	//30분 이상 지나면 로그아웃 
	var fn_check_timer = function()
	{
		var currentTime = new Date().getTime();
		var diffMinutes = ( currentTime - logOutTimer.startTime ) / (1000 * 60);
		
		if (diffMinutes > 30) {
            clearInterval(logOutTimer.count);   // count 종료
            fnc.moveLogout(true);
        }
	}
	
	//로그인 연장
	var fn_delay_logOut = function(){
		fnc.bscAjax(function(data){
			if(data.lgnYn)
			{  
				$("#logOutGuide .btn_close").click();
				clearInterval(logOutTimer.count);
				logOutTimer.reset();
			}
			else
			{
				fnc.moveLogout(true); 
			}
		}, "/lgnCheck.ajax");
	}
	
	//관심지점 설정 팝업 노출
	var fn_show_itrst_popup = function(){
		$(".place_pop_area").addClass("show");
		$(".place_pop_area").addClass("transition");
		$(".place_pop_area").find(".inner_dimd").show();
	}
	
	//관심지점 설정 안 되어 있는 경우 팝업 못 닫게 처리.
	var fn_alert_itrst = function(obj){
		$(obj).off("click");
		alert("관심지점은 반드시 선택해주셔야 합니다.");
	}
	
	//관심지점 설정 저장
	var fn_save_itrst = function(){
		
		var slctBrchBtn = $(".place_pop_area .btn_flex_box .on");
		
		if(slctBrchBtn.length == 0)
		{
			alert("지점을 선택해주세요.")
		}
		else
		{
			var paramObj = {
					itrstBrchCd : slctBrchBtn.data("cd"),
					itrstBrchNm	: slctBrchBtn.data("nm")
			}
			
			fnc.paramAjax(function(data) {
				if(data.rtnCnt > 0)
				{
					alert("관심지점이 설정되었습니다.");
					
					location.href = (location.pathname.indexOf("/payment/") > -1 ? "/" : location.href);
				}
			}, "/setItrst.ajax", paramObj ,"json", false, false);
		}
	}
	
	//LPOINT 가져오기
	var fn_get_lpoint = function(){
		fnc.bscAjax(function(data){
			if(data.lpoint)
			{
				$(".mypage_pop_area").find("#lpointSpan").text(fnc.fn_numberComma(parseInt(data.lpoint)));
			}
	    	
        }, "/mypage/member/lpoint.ajax", "json", false, false);
	}
	
	//마이페이지 카운트 정보
	var fn_mypage_count = function(obj){
		fnc.bscAjax(function(data){
			if(data.mypageInfo)
			{
				$(".mypage_pop_area").find("#cpnCnt").text(parseInt(data.mypageInfo.cpnCnt));
				$(".mypage_pop_area").find("#cartCnt").text(parseInt(data.mypageInfo.cartCnt));
				$(".mypage_pop_area").find("#atlctCnt").text(parseInt(data.mypageInfo.atlctCnt));
				$(".mypage_pop_area").find("#waitingCnt").text(parseInt(data.mypageInfo.waitingCnt));
				$(".mypage_pop_area").find("#certfCnt").text(parseInt(data.mypageInfo.certfCnt));
				$(".mypage_pop_area").find("#rvwCnt").text(parseInt(data.mypageInfo.rvwCnt));
				$(".mypage_pop_area").find("#tcevlCnt").text(parseInt(data.mypageInfo.tcevlCnt));
				
				// 추가요청사항 알럿 해제 2023.03.31
				// if(data.mypageInfo.tcevlCnt)
				// {
				// 	setTimeout(function(){
				// 		//만족도 평가 항목 존재 시
				// 		if(confirm("작성하실 만족도 평가가 있습니다. 바로 작성하시겠습니까?"))
				// 		{
				// 			location.href = "/mypage/teachereval/list.do";
				// 		}
				// 	}, 600);
				// }
			}
        }, "/mypage/member/count.ajax", "json", false, false);
	}
	
	//통합검색 레이어 검색
	var integrationSearch = function(_this) {
		var maxIndex = 10;
		var item = $(_this).closest(".form_search").find("input").val();
		item = fnc.convertHtml(item);
		if(item.trim() != "") {
			var recentItem = {
					"item" : item
			} 
			
			var recentItems = JSON.parse(localStorage.getItem("recentItems"));
			if(recentItems !== null) {
				var findItem = recentItems.find(ele => ele.item == item);
				if(findItem === undefined) {
					recentItems.unshift(recentItem);
					if(recentItems.length > maxIndex) recentItems.pop();
				}
			} else {
				recentItems = new Array(recentItem);
			}
			
			localStorage.setItem("recentItems", JSON.stringify(recentItems));
			location.href = "/application/integration/list.do?q=" + encodeURIComponent(fnc.convertHtml($(_this).closest(".form_search").find("input").val()));
		} else {
			alert("검색어를 입력하세요.")
		}
		
		
	}
	
	//통합검색 레이어 삭제
	var integrationRemove = function(_this) {
		if(_this !== "") {
			var item = $(_this).data("item");
			var recentItems = JSON.parse(localStorage.getItem("recentItems"));
			var findItem = recentItems.find(ele => ele.item == item);
			recentItems.splice(recentItems.indexOf(findItem) , 1);
			
			if(recentItems.length > 0) {
				localStorage.setItem("recentItems", JSON.stringify(recentItems));
				$(_this).closest(".word").remove();
			} else {
				localStorage.removeItem("recentItems");
				$(".recent_keyword_area .swiper-wrapper").html("");
				$(".recent_keyword_area").hide();
			}
			
		} else {
			localStorage.removeItem("recentItems");
			$(".recent_keyword_area .swiper-wrapper").html("");
			$(".recent_keyword_area").hide();
		}
	}
	
	//통합검색 레이어  최근 검색어 불러오기
	var getRecentItems = function() {
		var recentItems = JSON.parse(localStorage.getItem("recentItems"));
		if(recentItems !== null) {
			var itemHtml = '';
			recentItems.forEach(function(ele) {
				var item = fnc.convertHtml(fnc.returnHtml(ele.item));
				itemHtml += '<p class="word swiper-slide">';
				itemHtml += '<a href="/application/integration/list.do?q=' + encodeURIComponent(item) + '">' + item + '</a>';
				itemHtml += ' <button type="button" onclick="common.integrationRemove(this);" data-item="' + item + '" class="delete_btn"><img src="/common/images/btn-search-data-delete.png" alt=""></button>'
				itemHtml += '</p>';
			});
			
			$(".recent_keyword_area .swiper-wrapper").html(itemHtml);
			$(".recent_keyword_area").show();
		}
	}
	
    $(document).ready(function() {
    	
        //강좌로 찾기 모바일
        $(".srch_gate_pop_area .circle_menu").on("click", function(){
            var lrclsCtegryCd = $(this).data("lrclsCtegryCd");
            var mdclsCtegryCd = $(this).data("mdclsCtegryCd");
            $("#lectureCntBnt").prop("href", "/application/search/list.do?type=category&lrclsCtegryCd=" + lrclsCtegryCd + "&mdclsCtegryCd=" + mdclsCtegryCd);
        });

        //지점으로 찾기 모바일
        $(".srch_gate_pop_area .brchBtn").on("click", function(){
        	var brchCd = $(this).data("brchCd");
        	$("#lectureCntBnt").prop("href", "/application/search/list.do?type=branch&brchCd=" + brchCd);
        });
        
        //최근검색어 불러오기
        getRecentItems();
    });
    return {
    	logOutTimer : logOutTimer,
    	delayLogOut : fn_delay_logOut,
    	showItrstPop: fn_show_itrst_popup,
    	alertItrst	: fn_alert_itrst,
    	saveItrst	: fn_save_itrst,
    	getLpoint : fn_get_lpoint,
    	getCount  : fn_mypage_count,
    	integrationSearch : integrationSearch,
    	integrationRemove : integrationRemove
	}
}());