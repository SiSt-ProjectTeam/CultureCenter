var mainCtrl = (function(){
	var pathname = "/" + window.location.pathname.split("/")[1];
	var origin = window.location.origin;
	var contextPath = origin + pathname;

	"use strict";
	
	/*
	//메인 공지 불러오기
	var getMainNotice = function() {
		var data = [
			{name: 'path', value: 'mainNotice'}
		]
		
		fnc.paramAjax(function(r){
			$( r.jsonClassArray ).each( function(i, elem){
				$("#mainNoticeContainer").append();
			})
			
			if ($("#recommendationContainer").find("div").length == 0) {
				$("#recommendationContainer").closest("section").remove();
			} else {
				mainScript.swiperFn();
				$("#recommendationContainer img").each(function(){
					$(this).load(function() {
						.
						imgResizingFn();
					});
				});
			}
		}, contextPath+"/getMainNotice.ajax", data, "json", false, true, false);
	}
	*/
	
	//추천 강좌 불러오기
	var getRecommendationClassList = function() {
		var data = [
			{name: 'path', value: 'recommendation'}
		]
		
		fnc.paramAjax(function(r){
			$( r.jsonClassArray ).each( function(i, elem){
				$("#recommendationContainer").append(
					`<div class="swiper-slide card_list_v">
						<a href="`+contextPath+`/application/search/view.do?branch_id=${ elem.branchID }&amp;yy=${ elem.openYear }&amp;lectSmsterCd=${ elem.openSmstId }&amp;lectCd=${ elem.classSemesterSq }" class="lec_list">
							<div class="img_resize_w img">
								<img src="${ elem.classImg }" alt="thumbnail.jpg">
							</div>
							<div class="con">
								<div class="label_div">
									<p class="label large ${ elem.classSt == "접수중"? "lime" : "gray" }">${ elem.classSt }</p>
									<p class="label large border">공동모객</p>
								</div>
								<p class="tit">${ elem.classNm }</p>
								<div class="info_con">
									<div class="type_div">
										<p class="type">${ elem.smstNm }</p>
										<p class="type">${ elem.teacherNm }</p>
									</div>
									<p class="time">${ elem.schedule }, 총 ${ elem.classCnt }회</p>
								</div>
							</div>
						</a>
						<div class="bottom_info">
							<p class="price">${elem.classFee}원</p>
							<button type="button" class="cart" onclick="fnc.cartBtn('0001', '2023', '4', '0490', '03');"></button>
						</div>
					</div>`);
			})
			
			if ($("#recommendationContainer").find("div").length == 0) {
				$("#recommendationContainer").closest("section").remove();
			} else {
				mainScript.swiperFn();
				$("#recommendationContainer img").each(function(){
					$(this).load(function() {
						imgResizingFn();
					});
				});
			}
		}, contextPath+"/getRecommendationClassList.ajax", data, "json", false, true, false);
	}
	
	//카테고리 강좌 불러오기
	var getCategoryClassList = function() {
		var lrclsCtegryCd = $(".category_wrap .scroll_wrap a[class*=on]").data("lrclsCtegryCd");
		var mdclsCtegryCd = $(".category_wrap .scroll_wrap a[class*=on]").data("mdclsCtegryCd");
		
		var data = [
			{name: 'lrclsCtegryCd', value: lrclsCtegryCd} , {name: 'mdclsCtegryCd', value: mdclsCtegryCd}
		]
				
		fnc.paramAjax(function(r){
			$("#categoyContainer").html("");
			$( r.jsonClassArray ).each( function(i, elem){
				$("#categoyContainer").append(
					`<div class="card_list_h">
						<a href="`+contextPath+`/application/search/view.do?branch_id=${ elem.branchID }&amp;yy=${ elem.openYear }&amp;lectSmsterCd=${ elem.openSmstId }&amp;lectCd=${ elem.classSemesterSq }">
							<div class="img_wrap">
								<div class="img_resize_w img reverse">
									<img src="${ elem.classImg }" alt="thumbnail.jpg">
								</div>
							</div>
							<div class="con">
								<div class="label_div">
									<p class="label large ${ elem.classSt == "접수중"? "lime" : "gray" }">${ elem.classSt }</p>
									<p class="label large black_gray">${ elem.branchNm }</p>
								</div>
								<p class="tit">${ elem.classNm }</p>
								<div class="info_con">
									<div class="type_div">
										<p class="type">${ elem.smstNm }</p>
										<p class="type">${ elem.teacherNm }</p>
									</div>
									<p class="time">${ elem.schedule }, 총 ${ elem.classCnt }회</p>
								</div>
							</div>
						</a>
						<div class="bottom_info">
							<p class="price">${ elem.classFee }원</p>
							<button type="button" class="cart" onclick="fnc.cartBtn('0001', '2023', '4', '0490', '03');"></button>
						</div>
					</div>`);
			})
			
			$("#categoyContainer img").each(function(){
				$(this).load(function() {
					imgResizingFn();
				});
			});
			
			if($("#categoyContainer img").length == 0) {
				imgResizingFn();
			}
			
			try{			
				ScrollTrigger.refresh();
			}catch(e){}
		}, contextPath+"/getCategoryClassList.ajax", data, "json", false, true, false);
		
		
	}
	
	//신규 강좌 불러오기
	var getNewClassList = function() {
		var data = [
			{name: 'orderSet', value: "G"}
		]
				
		fnc.paramAjax(function(r){
			
			$( r.jsonClassArray ).each( function(i, elem){
				$("#newContainer").append(
					`<div class="swiper-slide card_list_v swiper-slide-active">
						<a href="`+contextPath+`/application/search/view.do?branch_id=${ elem.branchID }&amp;yy=${ elem.openYear }&amp;lectSmsterCd=${ elem.openSmstId }&amp;lectCd=${ elem.classSemesterSq }" class="lec_list">
							<p class="small_label wide NEW"></p>
							<div class="img_resize_w img">
								<img src="${ elem.classImg }" alt="thumbnail.jpg">
							</div>
							<div class="con">
								<div class="label_div">
									<p class="label large ${ elem.classSt == "접수중"? "lime" : "gray" }">${ elem.classSt }</p>
									<p class="label large black_gray">${ elem.branchNm }</p>
								</div>
								<p class="tit">${ elem.classNm }</p>
								<div class="info_con">
									<div class="type_div">
										<p class="type">${ elem.smstNm }</p>
										<p class="type">${ elem.teacherNm }</p>
									</div>
									<p class="time">${ elem.schedule }, 총 ${ elem.classCnt }회</p>
								</div>
							</div>
						</a>
						<div class="bottom_info">
							<p class="price">${ elem.classFee }원</p>
							<button type="button" class="cart" onclick="fnc.cartBtn('0001', '2023', '4', '0490', '03');"></button>
						</div>
					</div>`);
			})
			
			$("#newContainer").html(  );
			if($("#newContainer").find("div").length == 0) {
				$("#newContainer").closest("section").remove();
			} else {
				mainScript.swiperFn();
				$("#newContainer img").each(function(){
					$(this).load(function() {
						imgResizingFn();
					});
				});
			}
		}, contextPath+"/getNewClassList.ajax", data, "json", false, true, false);
	}
	
	
	//공지사항 이벤트 불러오기
	var getNewsClassList = function() {
		var data = [
			{name: 'path', value: 'news'}
		]
				
		fnc.paramAjax(function(r){
			$( r.jsonClassArray ).each( function(i, elem){
				$("#newsContainer").append(
					`<a href="<%=contextPath %>/community/notice/view.do?noticeSq${ elem.noticeSq }" class="news">
						<p class="tit">${ elem.postingTitle }</p>
						<p class="txt">`+elem.postingContent.replace( /(<([^>]+)>)/ig , '')+`</p>
						<p class="date">${ elem.writeDt }</p>
					</a> `);
			})
			
			$("#newContainer")
				.html(  );
			if($("#newContainer").find("div").length == 0) {
				$("#newContainer").closest("section").remove();
			} else {
				mainScript.swiperFn();
				$("#newContainer img").each(function(){
					$(this).load(function() {
						imgResizingFn();
					});
				});
			}
		}, contextPath+"/getNewsClassList.ajax", data, "json", false, true, false);
	}
	
	//메인 팝업 오픈
	var openPopup = function(){
		var isChk = fnc.getCookie("todayClose");
		//알림 안보기 아닐시
		if(isChk != 'Y' && $('#mainPop').length > 0){
			commonScript.openPopupFn("#mainPop" , $(this));
		}
	}
	//팝업 창닫기
	var popClose =  function(){
		$("#mainPop").find(".btn_close").click();
	}
	
	//오늘 하루 팝업 보지 않기
	var todayClose = function(){
		
		var isChk = fnc.getCookie("todayClose");
		
		if(isChk == 'Y'){
			if($("#mainPop").length > 0){
				$("#mainPop").hide();
			}
		}
		$("#todayClose").click(function(){
			fnc.setCookie("todayClose", "Y", {expires : 1});
			$(this).next().click();
		})
	}
	
	var init = function() {
		//getMainNotice();
		getRecommendationClassList();
		getCategoryClassList();
		getNewClassList();
		getNewsClassList();
		eventSwiperFn();
		//openPopup();
		//todayClose();
	}
	
	$(document).ready(function() {
		init();
	});
	
	window.onload = function(){
		openPopup();
		todayClose();
		//mainScript.eventSwiperFn();
	} 
	
	 return {
	 	 getRecommendationClassList : getRecommendationClassList ,
		 getCategoryClassList : getCategoryClassList ,
		 getNewClassList : getNewClassList ,
		 popClose	:	popClose ,
		 todayClose :	todayClose
		 
	 }
}());