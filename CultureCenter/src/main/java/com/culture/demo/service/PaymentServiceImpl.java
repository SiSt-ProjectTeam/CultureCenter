package com.culture.demo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.culture.demo.domain.CartDTO;
import com.culture.demo.domain.PaymentFrmDTO;
import com.culture.demo.mapper.PaymentMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService{

	private PaymentMapper paymentMapper;

	// 세부강좌번호 -> 강좌정보
	@Override
	public CartDTO getLect(int detail_class_sq) throws Exception {
		log.info(">>PaymentServiceImpl.getLect() ...");
		return paymentMapper.selectLect(detail_class_sq);
	}

	// 수강자변경시 list html
	@Override
	public String createCourDetailWHtml(Map<String, String> paramMap) {
		log.info(">>PaymentServiceImpl.createCourDetailWHtml() ...");
		int cnt = Integer.parseInt(paramMap.get("cnt"));
		String[] names = paramMap.get("korNm").split(",");
		String[] fmlyRelCds = paramMap.get("fmlyRelCd").split(",");
		String[] fmlyRelCdNms = paramMap.get("fmlyRelCdNm").split(",");
		String[] sexCds = paramMap.get("sexCd").split(",");
		String[] bdays = paramMap.get("bday").split(",");
		String type = paramMap.get("type");
		int classFee = Integer.parseInt(paramMap.get("lectAmt"));
		int exCharge = Integer.parseInt(paramMap.get("optnAmt"));

		StringBuilder html = new StringBuilder();
		html.append("<div data-rslt-cd='1' data-type='"+paramMap.get("type")+"'></div>");
		for (int i = 0; i < cnt; i++) {
			html.append("<div class=\"cour_detail\" data-kor-nm=\""+names[i]+"\" data-fmly-rel-cd=\""+fmlyRelCds[i]+"\"\r\n"
					+ "data-fmly-rel-cd-nm=\""+fmlyRelCdNms[i]+"\" data-bday=\""+bdays[i]+"\" data-sex-cd=\""+sexCds[i]+"\">\r\n"
					+ "<div class=\"left\">\r\n"
					+ "	<div class=\"tit f_body1\">"+names[i]+"("+fmlyRelCdNms[i]+")"+"</div>\r\n"
					+ "	<div class=\"flex_btn_wrap\">\r\n");
			if(i==0) {
				html.append("<a style=\"background-color: #e0f55c;\" class=\"border_btn\"\r\n"
						+ "		href=\"javascript:\" role=\"button\"\r\n"
						+ "		onclick=\"payment.openFmlyPopup(this, '"+type+"')\">\r\n");
				if(type=="child") html.append("<span style=\"color: #000;\">자녀회원 선택</span>");
				else html.append("<span style=\"color: #000;\">수강자 변경/추가</span>");
			}else {
				html.append("<a class=\"border_btn\" href=\"javascript:\" role=\"button\" onclick=\"payment.deleteActlAtlctNple(this)\">\r\n"
						+ "			                  <span>삭제</span>\r\n"
						+ "			                </a>");
			}
			html.append("	</a>\r\n"
					+ "	</div>\r\n"
					+ "</div>\r\n"
					+ "<div class=\"right\">\r\n"
					+ "	<ul class=\"txt_wrap\">\r\n"
					+ "		<li class=\"f_body3\">\r\n"
					+ "			<div class=\"txt_con\">\r\n"
					+ "				<div class=\"tit\">강좌료</div>\r\n"
					+ "				<div class=\"txt\">\r\n"
					+ "					<p>"+String.format("%,d",classFee)+"원</p>\r\n"
					+ "				</div>\r\n"
					+ "			</div>\r\n"
					+ "		</li>\r\n");
			if( exCharge!= 0 ) {
				html.append("<li class=\"f_body3\">\r\n"
						+ "									<div class=\"txt_con\">\r\n"
						+ "										<div class=\"tit\">재료비/대여료</div>\r\n"
						+ "										<div class=\"txt\">\r\n"
						+ "											<p>"+String.format("%,d", exCharge)+"원</p>\r\n"
						+ "										</div>\r\n"
						+ "									</div>\r\n"
						+ "								</li>\r\n");
			}
			html.append("		<li class=\"expected\">\r\n"
					+ "			<div class=\"txt_con\">\r\n"
					+ "				<div class=\"tit\">결제예정 금액</div>\r\n"
					+ "				<div class=\"txt\">\r\n"
					+ "					<p>"+String.format("%,d",classFee+exCharge)+"원</p>\r\n"
					+ "				</div>\r\n"
					+ "			</div>\r\n"
					+ "		</li>\r\n"
					+ "	</ul>\r\n"
					+ "</div>\r\n"
					+ "</div>");
		}
		return html.toString();
	}

	// 학기별강좌 - 수강가능인원  
	@Override
	public int matchPeopleTotAv(int detail_class_sq, int cnt) throws Exception {
		log.info(">>PaymentServiceImpl.matchPeopleTotAv() ...");
		return paymentMapper.cntPeopleTotAv(detail_class_sq,cnt);
	}
	// 수강결제신청하는 강좌가 수강신청한 강좌인지
	@Override
	public int matchClassOrder(int member_sq, int detail_class_sq) throws Exception {
		log.info(">>PaymentServiceImpl.matchClassOrder() ...");
		return paymentMapper.cntClassOrderMatch(member_sq,detail_class_sq);
	}
	// 강좌상태
	public int getClassStId(int detail_class_sq) throws Exception {
		log.info(">>PaymentServiceImpl.getClassStId() ...");
		return paymentMapper.getClassStId(detail_class_sq);
	}
	// 강좌유형 : 대분류
	@Override
	public String getLrclsctegrycd(int detail_class_sq) throws Exception {
		log.info(">>PaymentServiceImpl.getLrclsctegrycd() ...");
		return paymentMapper.getLrclsctegrycd(detail_class_sq);
	}
	// 강좌명
	@Override
	public String getLectName(int detailLectCd) throws Exception {
		log.info(">>PaymentServiceImpl.getLectName() ...");
		return paymentMapper.getLectName(detailLectCd);
	}

	// 주문번호
	@Override
	public int getOrderSq(int member_sq) throws Exception{
		log.info(">>PaymentServiceImpl.getOrderSq() ...");
		return paymentMapper.getAtlctRsvNo(member_sq);
	}

	// frm_temp -> iframe에 들어갈 nicepay 불러오는 html
	@Override
	public String createNicePayHtml(PaymentFrmDTO frm) throws Exception {
		log.info(">>PaymentServiceImpl.createNicePayHtml() ...");
		StringBuilder nicepayHtml = new StringBuilder();	
		
		// js
		nicepayHtml.append("<html><head><script type=\"text/javascript\" src=\"/resources/common/js/jquery-1.12.4.min.js\"></script>\r\n"
				+ "<script type=\"text/javascript\" src=\"/resources/common/js/fnc.js\"></script>\r\n"
				+ "<script type=\"text/javascript\" src=\"/resources/common/js/payment/payment.js\"></script>");

		// nicepay API 사용하기위한 userAgent분류, 결제창 최종 요청, 결제창 종료함수 js 생성
		nicepayHtml.append("<script src=\"https://web.nicepay.co.kr/v3/webstd/js/nicepay-3.0.js\" type=\"text/javascript\"></script>\r\n"
				+ "<script>\r\n"
				+ "//pc, mobile 구분\r\n"
				+ "function checkPlatform(ua) {\r\n"
				+ "	if(ua === undefined) {\r\n"
				+ "		ua = window.navigator.userAgent;\r\n"
				+ "	}\r\n"
				+ "	\r\n"
				+ "	ua = ua.toLowerCase();\r\n"
				+ "	var platform = {};\r\n"
				+ "	var matched = {};\r\n"
				+ "	var userPlatform = \"pc\";\r\n"
				+ "	var platform_match = /(ipad)/.exec(ua) || /(ipod)/.exec(ua) \r\n"
				+ "		|| /(windows phone)/.exec(ua) || /(iphone)/.exec(ua) \r\n"
				+ "		|| /(kindle)/.exec(ua) || /(silk)/.exec(ua) || /(android)/.exec(ua) \r\n"
				+ "		|| /(win)/.exec(ua) || /(mac)/.exec(ua) || /(linux)/.exec(ua)\r\n"
				+ "		|| /(cros)/.exec(ua) || /(playbook)/.exec(ua)\r\n"
				+ "		|| /(bb)/.exec(ua) || /(blackberry)/.exec(ua)\r\n"
				+ "		|| [];\r\n"
				+ "	\r\n"
				+ "	matched.platform = platform_match[0] || \"\";\r\n"
				+ "	\r\n"
				+ "	if(matched.platform) {\r\n"
				+ "		platform[matched.platform] = true;\r\n"
				+ "	}\r\n"
				+ "	\r\n"
				+ "	if(platform.android || platform.bb || platform.blackberry\r\n"
				+ "			|| platform.ipad || platform.iphone \r\n"
				+ "			|| platform.ipod || platform.kindle \r\n"
				+ "			|| platform.playbook || platform.silk\r\n"
				+ "			|| platform[\"windows phone\"]) {\r\n"
				+ "		userPlatform = \"mobile\";\r\n"
				+ "	}\r\n"
				+ "	\r\n"
				+ "	if(platform.cros || platform.mac || platform.linux || platform.win) {\r\n"
				+ "		userPlatform = \"pc\";\r\n"
				+ "	}\r\n"
				+ "	\r\n"
				+ "	return userPlatform;\r\n"
				+ "}\r\n"
				+ "</script></head><body>");
		// 인증,승인 모두 완료시 return될 html을 출력해줄 지시자	
		// payForm 
		// 상점키(필)              // 테스트 상점키
		String merchantKey 		= "EYzu8jGGMfqaDEp76gSckuvnaHHu+bC4opsSN6lHv3b2lurNYkVXrZ7Z1AoqQnXI3eLuaUFyoRNC6FkrzVjceg=="; 
		// 상점아이디(필) // MID   // 테스트아이디
		String merchantID 		= "nicepay00m"; 
		// 결제상품명(필)
		String goodsName 		= frm.getGoodsName(); 	
		// 결제상품금액(필) // Amt
		String price 			= frm.getCrdStlmAmt(); 
		// 구매자명
		String buyerName 		= frm.getBuyerName(); 	
		// 구매자메일주소
		String buyerEmail 		= frm.getMbrId(); 	
		// 상품주문번호(필) // moid 
		String orderSq			= frm.getAtlctRsvNo();
		System.out.println("order_sq : " + orderSq);
		// 요청응답URL 모바일(필) ( 절대경로 줘야함 ! )
		String returnURL 		= "localhost/payment/payment_result.do";
		// 주문 생성일시
		String ediDate = getyyyyMMddHHmmss();
		// 위변조검증 데이터(필) EdiDate + MID + Amt + MerchantKey
		String signData = encrypt(ediDate + merchantID + price + merchantKey);
		// iframe기반 PG연동시 (필)
		String ConnWithIframe = "Y";
		// 휴대폰 소액결제 추가 파라미터 0:컨텐츠, 1:실물
		String GoodsCl ="1";

		nicepayHtml.append("\r\n<form id=\"payForm\" name=\"payForm\" method=\"get\" action=\"/payment/payment_result.do\">\r\n"
				//+ "	<input type=\"hidden\" name=\"${_csrf.parameterName}\" value=\"${_csrf.token}\" />\r\n"
				+ "	<input type=\"hidden\" name=\"PayMethod\" value=\"CARD\">\r\n"
				+ "	<input type=\"hidden\" name=\"GoodsName\" value=\""+goodsName+"\">\r\n"
				+ "	<input type=\"hidden\" name=\"Amt\" value=\""+price+"\">\r\n"
				+ "	<input type=\"hidden\" name=\"MID\" value=\""+merchantID+"\">\r\n"
				+ "	<input type=\"hidden\" name=\"Moid\" value=\""+orderSq+"\">\r\n"
				+ "	<input type=\"hidden\" name=\"BuyerName\" value=\""+buyerName+"\">\r\n"
				+ "	<input type=\"hidden\" name=\"ReturnURL\" value=\""+returnURL+"\">\r\n"
				+ "	\r\n"
				+ "	<!-- 옵션 --> \r\n"
				+ "	<input type=\"hidden\" name=\"GoodsCl\" value=\""+GoodsCl+"\"/>   \r\n"
				+ "	<input type=\"hidden\" name=\"TransType\" value=\"0\"/> \r\n"   //일반(0)/에스크로(1)
				+ "	<input type=\"hidden\" name=\"CharSet\" value=\"utf-8\"/>\r\n"  // 응답 파라미터 인코딩 방식
				+ "	<input type=\"hidden\" name=\"ReqReserved\" value=\"\"/>	<!-- 상점 예약필드 -->\r\n"
				+ "				\r\n"
				+ "	<!-- 변경 불가능 -->\r\n"
				+ "	<input type=\"hidden\" name=\"EdiDate\" value=\""+ediDate+"\"/>\r\n"
				+ "	<input type=\"hidden\" name=\"SignData\" value=\""+signData+"\"/>\r\n"
				+ "	<input type=\"hidden\" name=\"ConnWithIframe\" value=\""+ConnWithIframe+"\"/>\r\n"
				+ "</form>\r\n");
		
		nicepayHtml.append("<script>\r\n"
				// 모바일,PC NicePay 결제창 진입 goPay
				+ "	if(checkPlatform(window.navigator.userAgent) == \"mobile\"){//모바일 결제창 진입\r\n"
				+ "		document.payForm.action = \"https://web.nicepay.co.kr/v3/v3Payment.jsp\";\r\n"
				+ "		document.payForm.acceptCharset=\"euc-kr\";\r\n"
				+ "		document.payForm.submit();\r\n"
				+ "	}else{//PC 결제창 진입\r\n"
				+ "		goPay(document.payForm);\r\n"
				+ "	}\r\n"
				
				+ "//[PC 결제창 전용]결제 최종 요청시 실행됩니다. <<'nicepaySubmit()' 이름 수정 불가능>>\r\n"
				+ "function nicepaySubmit(){\r\n"
				+ "	document.payForm.submit();\r\n"
				+ "}\r\n"
				+ "\r\n"
				
				+ "//[PC 결제창 전용]결제창 종료 함수 <<'nicepayClose()' 이름 수정 불가능>>\r\n"
				+ "function nicepayClose(){\r\n"
				+ " alert('결제를 취소합니다.');"
				+ " console.log('nicepayClose호출..');\r\n"
				+ "		$.ajax({\r\n"
				+ "			url: \"http://localhost/payment/payment_close.ajax\",\r\n"
				+ "			type: \"get\",\r\n"
				+ "			data: { orderSq: "+orderSq+" },\r\n"
				+ "			contentType: \"application/json; charset=utf-8;\",\r\n"
				+ "			dataType: \"json\",\r\n"
				+ "			async: true,\r\n"				
				+ "			cache: false,\r\n"
				+ "			success: function(data,status,xhr){\r\n"
				+ "                alert('결제가 취소되었습니다.');\r\n"
				+ "			},\r\n"
				+ "			error:function(e,xhr,data){\r\n"
				+ "				if(e.status==403) {\r\n"
				+ "					if(confirm(\"자동 로그아웃 처리되었습니다. 로그인 다시 시도해주세요.\")); {\r\n"
				+ "						fnc.moveLoginPage();\r\n"
				+ "					}\r\n"
				+ "				}else {\r\n"
				+ "					console.log(e);\r\n"
				+ "				}\r\n"
				+ "			}\r\n"
				+ "		});\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "</script>"
				+ "</body>");

		//System.out.println(nicepayHtml.toString());
		return nicepayHtml.toString();
	}

	// 결제인증 후 승인
	@Override
	public HashMap<String, String> getAcknowledgeNicePay(Map<String, Object> params) throws Exception{
		log.info(">>PaymentServiceImpl.getAcknowledgeNicePay() ...");
		HashMap<String, String> rtnMap = new HashMap<String, String>();
		// 결제인증 후 넘어온 파라미터
		String authResultCode = (String) params.get("AuthResultCode"); // 인증결과 : 0000(성공)
		String authResultMsg = (String) params.get("AuthResultMsg"); // 인증결과 메시지
		String nextAppURL = (String) params.get("NextAppURL"); // 승인 요청 URL
		String txTid = (String) params.get("TxTid"); // 거래 ID
		String authToken = (String) params.get("AuthToken"); // 인증 TOKEN
		String payMethod = (String) params.get("PayMethod"); // 결제수단
		String mid = (String) params.get("MID"); // 상점 아이디
		String moid = (String) params.get("Moid"); // 상점 주문번호
		String amt = (String) params.get("Amt"); // 결제 금액
		String reqReserved = (String) params.get("ReqReserved"); // 상점 예약필드
		String netCancelURL = (String) params.get("NetCancelURL"); // 망취소 요청 URL
		String authSignature = (String) params.get("Signature"); // Nicepay에서 내려준 응답값의 무결성 검증 Data

		// 승인처리를 위한 파라미터
		String merchantKey = "EYzu8jGGMfqaDEp76gSckuvnaHHu+bC4opsSN6lHv3b2lurNYkVXrZ7Z1AoqQnXI3eLuaUFyoRNC6FkrzVjceg==";
		String authComparisonSignature = encrypt(authToken + mid + amt + merchantKey); // 넘어온 authSignature와 비교
		// 승인 결과 파라미터
		String ResultCode 	= ""; String ResultMsg 	= ""; String PayMethod 	= "";
		String GoodsName 	= ""; String Amt 		= ""; String TID 		= ""; 

		// 승인 처리 로직
		String resultJsonStr = "";
		if(authResultCode.equals("0000") && authSignature.equals(authComparisonSignature)){
			//해쉬암호화
			String ediDate  = getyyyyMMddHHmmss();
			String signData = encrypt(authToken + mid + amt + ediDate + merchantKey);
			//승인 요청
			// 승인에 필요한 데이터 생성
			StringBuffer requestData = new StringBuffer();
			requestData.append("TID=").append(txTid).append("&");
			requestData.append("AuthToken=").append(authToken).append("&");
			requestData.append("MID=").append(mid).append("&");
			requestData.append("Amt=").append(amt).append("&");
			requestData.append("EdiDate=").append(ediDate).append("&");
			requestData.append("CharSet=").append("utf-8").append("&");
			requestData.append("SignData=").append(signData);

			// server to server 통신을 통해 승인 처리
			resultJsonStr = connectToServer(requestData.toString(), nextAppURL);

			// 승인결과를 담을 Map
			HashMap<String, Object> resultData = new HashMap<>();
			// 승인 성공,실패
			boolean paySuccess = false;
			if("9999".equals(resultJsonStr)){ // Exception 발생
				StringBuffer netCancelData = new StringBuffer();
				requestData.append("&").append("NetCancel=").append("1");
				//망취소 요청
				String cancelResultJsonStr = connectToServer(requestData.toString(), netCancelURL);

				HashMap<String, Object> cancelResultData = jsonStringToHashMap(cancelResultJsonStr);
				ResultCode = (String)cancelResultData.get("ResultCode");
				ResultMsg = (String)cancelResultData.get("ResultMsg");
				/*Signature = (String)cancelResultData.get("Signature");
	    		String CancelAmt = (String)cancelResultData.get("CancelAmt");
	    		paySignature = sha256Enc.encrypt(TID + mid + CancelAmt + merchantKey);*/
				rtnMap.put("ResultCode", ResultCode);
				rtnMap.put("ResultMsg", ResultMsg);
				rtnMap.put("paySuccess", "fail");
			}else{ // 성공
				resultData = jsonStringToHashMap(resultJsonStr);
				ResultCode 	= (String)resultData.get("ResultCode");	// 결과코드 (정상 결과코드:3001)
				ResultMsg 	= (String)resultData.get("ResultMsg");	// 결과메시지
				PayMethod 	= (String)resultData.get("PayMethod");	// 결제수단
				GoodsName   = (String)resultData.get("GoodsName");	// 상품명
				Amt       	= (String)resultData.get("Amt");		// 결제 금액
				TID       	= (String)resultData.get("TID");		// 거래번호
				/*
	    		String Signature = (String)resultData.get("Signature"); // NicePay승인 응답값의 무결성검증Data
	    		String paySignature = encrypt(TID + mid + Amt + merchantKey); //
	    		// 승인 응답값의 무결성 검증
				 */
				// 결제 성공 여부 확인
				if(PayMethod != null){
					if(PayMethod.equals("CARD")){
						if(ResultCode.equals("3001")) paySuccess = true; // 신용카드(정상 결과코드:3001)       	
					}else if(PayMethod.equals("BANK")){
						if(ResultCode.equals("4000")) paySuccess = true; // 계좌이체(정상 결과코드:4000)	
					}else if(PayMethod.equals("CELLPHONE")){
						if(ResultCode.equals("A000")) paySuccess = true; // 휴대폰(정상 결과코드:A000)	
					}else if(PayMethod.equals("VBANK")){
						if(ResultCode.equals("4100")) paySuccess = true; // 가상계좌(정상 결과코드:4100)
					}else if(PayMethod.equals("SSG_BANK")){
						if(ResultCode.equals("0000")) paySuccess = true; // SSG은행계좌(정상 결과코드:0000)
					}else if(PayMethod.equals("CMS_BANK")){
						if(ResultCode.equals("0000")) paySuccess = true; // 계좌간편결제(정상 결과코드:0000)
					}
				}
				if(paySuccess) {
					rtnMap.put("paySuccess", "success");
					rtnMap.put("ResultCode", ResultCode);
					rtnMap.put("ResultMsg", ResultMsg);	
					rtnMap.put("PayMethod", PayMethod);	
					rtnMap.put("GoodsName", GoodsName);
					rtnMap.put("Amt", Amt);
					rtnMap.put("TID", TID); // 거래번호
				}else {
					rtnMap.put("paySuccess", "fail");
					rtnMap.put("ResultCode", ResultCode);
					rtnMap.put("ResultMsg", ResultMsg);	
				}
			}
		}else if(authSignature.equals(authComparisonSignature)){ // 결제인증이 0000(성공)이 아님
			ResultCode 	= authResultCode; 	
			ResultMsg 	= authResultMsg;

			rtnMap.put("paySuccess", "fail");
			rtnMap.put("ResultCode", ResultCode);
			rtnMap.put("ResultMsg", ResultMsg);
		}else{ // 무결성 검증 값이 다름
			rtnMap.put("paySuccess", "error");
			rtnMap.put("ResultMsg", "무결성 검증 값이 다름");
			System.out.println("인증 응답 Signature : " + authSignature);
			System.out.println("인증 생성 Signature : " + authComparisonSignature);
		}
		return rtnMap;
	}

	// f. 현재 날짜 시간 정보를 가져오는 function
	public final synchronized String getyyyyMMddHHmmss(){
		SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
		return yyyyMMddHHmmss.format(new Date());
	}
	// f. Hash암호화(sha-256) 처리 ( java.security ) function
	public String encrypt(String strData) {	
		String passACL = null;
		MessageDigest md = null;
		try{
			md = MessageDigest.getInstance("SHA-256");
			md.reset();
			md.update(strData.getBytes());
			byte[] raw = md.digest();
			passACL = new BigInteger(1, raw).toString(16);
		}catch(Exception e){
			System.out.print("암호화 에러" + e.toString());
		}
		return passACL;
	}
	// f. 승인을 위한 server to server 통신
	public String connectToServer(String data, String reqUrl) throws Exception{
		HttpURLConnection conn 		= null;
		BufferedReader resultReader = null;
		PrintWriter pw 				= null;
		URL url 					= null;

		int statusCode = 0;
		StringBuffer recvBuffer = new StringBuffer();
		try{
			url = new URL(reqUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(15000);
			conn.setReadTimeout(25000);
			conn.setDoOutput(true);

			pw = new PrintWriter(conn.getOutputStream());
			pw.write(data);
			pw.flush();

			statusCode = conn.getResponseCode();
			resultReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			for(String temp; (temp = resultReader.readLine()) != null;){
				recvBuffer.append(temp).append("\n");
			}

			if(!(statusCode == HttpURLConnection.HTTP_OK)){
				throw new Exception();
			}

			return recvBuffer.toString().trim();

		}catch (Exception e){
			return "9999";
		}finally{
			recvBuffer.setLength(0);

			try{
				if(resultReader != null){
					resultReader.close();
				}
			}catch(Exception ex){
				resultReader = null;
			}

			try{
				if(pw != null) {
					pw.close();
				}
			}catch(Exception ex){
				pw = null;
			}

			try{
				if(conn != null) {
					conn.disconnect();
				}
			}catch(Exception ex){
				conn = null;
			}
		}
	}
	// f. JSON String -> HashMap 변환
	private static HashMap<String, Object> jsonStringToHashMap(String str) throws Exception{
		HashMap<String, Object> dataMap = new HashMap();
		ObjectMapper mapper = new ObjectMapper();
		dataMap = mapper.readValue(str, new TypeReference<Map<String, Object>>(){});
		return dataMap;
	}
}
