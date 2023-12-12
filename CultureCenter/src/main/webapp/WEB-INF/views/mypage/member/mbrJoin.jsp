<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<title>회원가입</title>
<style>
body {
	font-family: 'Arial', sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
}

.top_area {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.page_prev_btn {
	width: 24px;
	height: 24px;
	display: inline-block;
	vertical-align: middle;
	margin-right: 10px;
}

.sub_title {
	color: #333;
	font-size: 1em;
	text-align: center;
	margin-top: 20px;
}

.l_point {
	vertical-align: middle;
}

.joinForm {
	max-width: 400px;
	margin: 20px auto;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.textForm {
	margin-bottom: 15px;
}

input {
	width: 100%;
	padding: 10px;
	margin-top: 5px;
	box-sizing: border-box;
}

input[type="date"] {
	width: calc(100% - 22px);
	margin-top: 5px;
}

.addr input {
	width: 100%; 
	box-sizing: border-box; 
	margin-top: 5px; 
}

.addr input[type="button"] {
	width: 100%; 
	margin-top: 5px; 
}

.btn {
	display: inline-block;
	padding: 10px 15px;
	background-color: #fff; 
	color: #333; 
	border: 1px solid #333; 
	border-radius: 4px;
	cursor: pointer;
	margin-right: 10px;
}

.cancelBtn {
	background-color: #ccc; 
	color: #333; 
	border: 1px solid #333; 
}
</style>



	<div class="page_title_area">
		<div class="inner">
			<div class="top_area">
				<a href="/" class="page_prev_btn login_prev_btn" title="뒤로가기"></a>
				<p class="tit_div">
					<br>

				</p>
			</div>
		</div>
		<p class="sub_title">
			<img src="/resources/common/images/logo-lpoint.svg" alt="lpoint"
				class="l_point"><h2>회원가입</h2><br class="only_mobile">
			
		</p>
	</div>

	<form action="" method="POST" class="joinForm" id="writeForm">


		<input type="hidden" id="idConfirmed" value="false">
		




		<!-- 아이디 -->
		<!-- autocomplete="off" 자동완성?끄기 -->
		<div class="textForm">
			<input name="id" type="text" class="id" id="id" value=""
				placeholder="아이디" autocomplete="off">
			<!-- <div id="idCheckDiv" class="alert alert-danger">아이디는 4자 이상 입력하셔야 합니다.</div> -->

			<!-- <input type="button" id="btnIdCheck" value="중복체크"> -->
			<!-- <div id="idConfirmMsg"></div> -->
		</div>

		<!-- 비밀번호 입력란 -->
		<div class="textForm">
			<input name="pw" type="password" class="pw" id="pw"
				placeholder="비밀번호" autocomplete="off">
		</div>

		<!-- 비밀번호 확인 입력란 -->
		<!-- <div class="textForm">
			<input name="pw2" type="password" class="pw"
				id="pw2" placeholder="비밀번호 확인">
		</div> -->

		<!-- 이름 -->
		<div class="textForm">
			<input name="name" type="text" class="name" placeholder="이름"
				autocomplete="off">
		</div>

		<!-- 이메일 -->
		<div class="textForm">
			<input name="email" type="text" class="email" placeholder="이메일"
				autocomplete="off">
		</div>

		<!-- 전화번호 -->
		<div class="textForm">
			<input name="phone" type="tel" maxlength="11" class="email"
				placeholder="전화번호 예) 01012345678" autocomplete="off">
			<!-- email -> 스타일이 안먹어서 임시로 -->
		</div>

		<!-- 생년월일 -->
		<div class="textForm">
			생년월일<input type="date" id="birth_dt" class="date" name="birth_dt">
		</div>


		<!-- 주소 -->
		<div class="addr">
			<input type="hidden" id="postcode" name="postcode" placeholder="우편번호">
			<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
			<input type="text" id="roadAddress" name="addr" placeholder="도로명주소"
				size="35"> <input type="hidden" id="jibunAddress"
				name="jibunAddress" placeholder="지번주소" size="60"> <span
				id="guide" style="color: #999; display: none"></span> <input
				type="text" id="detailAddress" name="detailAddress"
				placeholder="상세주소" size="50"> <input type="hidden"
				id="extraAddress" name="extraAddress" placeholder="참고항목" size="60">
			<input type="hidden" id="engAddress" name="engAddress"
				placeholder="영문주소" size="60"> <br>
		</div>



		<br> <br> <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<button class="btn btn-default">등록</button>
		<button class="btn btn-default" type="reset">새로입력</button>
		<button class="btn btn-default cancelBtn" type="button"
			onclick="cancelRegistration()">취소</button>

	</form>

	<script>
    function cancelRegistration() {
        history.back();
    }
</script>






	<!--   일치 여부 확인 -->
	<!-- 
	<script type="text/javascript">
    function validateForm() {
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("confirmPassword").value;
       
        // 아이디 중복확인하기
        var idConfirmed = document.getElementById("idConfirmed").value; // 새로 추가한 변수

        // 중복확인을 하지 않은 경우
        if (idConfirmed !== "true") {
            alert("아이디 중복 확인을 해주세요.");
            return false;
        }
        // 비밀번호와 비밀번호 확인이 일치하는지 확인
        if (password !== confirmPassword) {
            alert("비밀번호 확인이 일치하지 않습니다.");
            return false;
        }

        return true;
    }
	</script>
 -->



	<!-- 

	아이디 중복확인 
	<script type="text/javascript">
	$(document).ready(function() {
	    $("#btnIdCheck").on("click", function() {
	        var id = $("#idInput").val();  //아이디 가져오고
	        
	        var msgElement = document.getElementById('idConfirmMsg'); // 밑에 메시지를 표시할 요소를 가져

	        if(id == "" || id.length == 0) { // 없거나 0일때
	            alert("아이디를 입력해주세요.");
	            return false;
	        }

	        if(id.length < 5) { // 5글자이상
	            alert("아이디를 5글자 이상 입력해주세요.");
	            return false;
	        }

	        $.ajax({
	        	 url : '/mypage/member/idcheck.ajax',
	            type : "post",  
	            dataType : "json",
	            data : { "id" : id }, // 아이디 보냄
	            success: function(data){ // 요청 성공시
	            	if (data.count >= 1) {
	            	    alert("이미 사용 중인 아이디입니다.");
	            	    msgElement.textContent = "이미 사용 중인 아이디입니다.";
	            	    msgElement.style.color = 'red';  
	            	    // 중복확인 여부를 저장하는 변수를 설정
	            	    document.getElementById("idConfirmed").value = "false";
	            	} else {
	            	    msgElement.textContent = "사용 가능한 아이디입니다.";
	            	    msgElement.style.color = 'blue';  
	            	    // 중복확인 여부를 저장하는 변수를 설정
	            	    document.getElementById("idConfirmed").value = "true";
	            	}
	            },
	            error: function(xhr, status, errorThrown){ // 요청 실패
	                alert('통신 중 오류가 발생하였습니다.');
	            }
	        });
	    });
	});
	</script>

 -->





	<!-- 도로명 주소 -->
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function execDaumPostcode() {
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
                document.getElementById("roadAddress").value = roadAddr;
                document.getElementById("jibunAddress").value = data.jibunAddress;
         
                document.getElementById("engAddress").value = data.addressEnglish;
                       
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
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
    }
</script>






	<script>
    // 생년월일
    const currentDateInput = document.getElementById('birth_dt');
    const today = new Date().toISOString().substring(0, 10);
    currentDateInput.value = today;

    currentDateInput.addEventListener('change', function () {
        const selectedDate = new Date(this.value);
        const currentDate = new Date();

        // 오늘날짜 이후 선택시
        if (selectedDate > currentDate) {
            alert('현재날짜 이후 날짜는 입력불가합니다.');
            this.value = today;
        }
    });
</script>



