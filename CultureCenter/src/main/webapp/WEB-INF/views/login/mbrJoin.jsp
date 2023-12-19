<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>


<style>
/* 전체 폼 스타일 */
#writeForm {
	max-width: 400px; /* 폼의 최대 너비 설정 */
	margin: 0 auto; /* 가운데 정렬 */
}

/* 입력 필드 스타일 */
.textForm {
	margin-bottom: 15px; /* 각 입력 필드 아래 여백 설정 */
}

.textForm input {
	width: 100%; /* 입력 필드 폭 100%로 설정 */
	padding: 10px; /* 입력 필드 패딩 설정 */
	box-sizing: border-box; /* 박스 크기를 보더 박스로 설정 */
}

/* 주소 입력 부분 스타일 */
.addr {
	margin-bottom: 15px; /* 주소 입력 부분 아래 여백 설정 */
}

.addr input {
	margin-bottom: 5px; /* 주소 입력 필드 간격 설정 */
}

/* 버튼 스타일 */
.btn {
	padding: 10px 20px; /* 버튼 내 여백 설정 */
	margin-right: 10px; /* 각 버튼 오른쪽 여백 설정 */
}

/* 취소 버튼 스타일 */
.cancelBtn {
	background-color: #ccc; /* 취소 버튼 배경색 설정 */
}

/* 페이지 타이틀 영역 스타일 */
.page_title_area {
	background-color: #f5f5f5; /* 페이지 타이틀 영역 배경색 설정 */
	padding: 20px; /* 페이지 타이틀 영역 패딩 설정 */
}

/* 로고 이미지 스타일 */
.l_point {
	width: 200px; /* 로고 이미지 너비 설정 */
	height: auto; /* 로고 이미지 높이 자동 설정 */
	margin-right: 10px; /* 로고 이미지 오른쪽 여백 설정 */
}

/* 페이지 서브 타이틀 스타일 */
.sub_title {
	margin-top: 10px; /* 서브 타이틀 상단 여백 설정 */
}

/* 버튼 스타일 */
.btn {
	padding: 10px 20px; /* 버튼 내 여백 설정 */
	margin-right: 10px; /* 각 버튼 오른쪽 여백 설정 */
	transition: background-color 0.3s ease; /* 배경색 변화에 트랜지션 효과 추가 */
}

/* 버튼 호버(마우스 오버) 효과 */
.btn:hover {
	background-color: #666; /* 호버 시 배경색을 진하게 설정 */
}

/* 취소 버튼 스타일 */
.cancelBtn {
	background-color: #ccc; /* 취소 버튼 배경색 설정 */
	transition: background-color 0.3s ease; /* 배경색 변화에 트랜지션 효과 추가 */
}

/* 취소 버튼 호버(마우스 오버) 효과 */
.cancelBtn:hover {
	background-color: #999; /* 호버 시 배경색을 진하게 설정 */
}

	
</style>

<div>

<div class="page_title_area">
	<div class="inner">
		<div class="top_area">
			<a href="/" class="page_prev_btn login_prev_btn" title="뒤로가기"></a>
			<p class="tit_div">
				<br>

			</p>
		</div>
	</div>
	<br>
	<br>
	<p class="sub_title">
		<img src="/resources/common/images/logo-lpoint.svg" alt="lpoint" class="l_point">
	<h2>회원가입</h2>
	<br class="only_mobile">

	
</div>

<form action="" method="POST" class="joinForm" id="writeForm">


	<input type="hidden" id="idConfirmed" value="false">





	<!-- 아이디 -->
	<!-- autocomplete="off" 자동완성?끄기 -->
	<div class="textForm">
		<input name="id" type="text" class="id" id="id" value=""
			placeholder="아이디" autocomplete="off">
			<span class="idchk"></span>		
	</div>

	<!-- 비밀번호 입력란 -->
	<div class="textForm">
	    <input name="pw" type="password" class="pw" id="pw" placeholder="비밀번호" autocomplete="off">
	</div>
	
	<!-- 비밀번호 확인 입력란 -->
	<div class="textForm">
	    <input name="pwchk" type="password" class="pwchk" id="pwchk" placeholder="비밀번호 확인">
	    <span id="pwMatchMsg"></span>
	</div>

	<!-- 이름 -->
	<div class="textForm">
		<input name="name" type="text" id="name" class="name1" placeholder="이름" 
			autocomplete="off">
	</div>

	<!-- 이메일 -->
	<div class="textForm">
		<input name="email" type="text" class="email" placeholder="이메일"
			autocomplete="off">
	</div>

		<!-- 전화번호 -->
		<div class="textForm">
			<input name="phone" type="tel" maxlength="11" class="tel"
						placeholder="전화번호 예) 01012345678" autocomplete="off">
		</div>

		<!-- 생년월일 -->
	<div class="textForm">
		생년월일<input type="date" id="birth_dt" class="date" name="birth_dt">
	</div>


	<!-- 주소 -->
	<div class="addr">
		<input type="text" id="postcode" name="postcode" placeholder="우편번호" size="26">
		<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기">
		<input type="text" id="roadAddress" name="addr" placeholder="도로명주소" size="45"> 
		<input type="hidden" id="jibunAddress" name="jibunAddress" placeholder="지번주소" size="50">
		<span id="guide" style="color: #999; display: none"></span> 
		<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소" size="45"> 
		<input type="hidden" id="extraAddress" name="extraAddress" placeholder="참고항목" size="60">
		<input type="hidden" id="engAddress" name="engAddress" placeholder="영문주소" size="60"> <br>
	</div>



	<br>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<button class="btn btn-default">등록</button>
	<button class="btn btn-default" type="reset">새로입력</button>
	<button class="btn btn-default cancelBtn" type="button"
		onclick="cancelRegistration()">취소</button>

<br><br>
</form>
</div>

<script>
    function cancelRegistration() {
        history.back();
    }
</script>
<script>
//아이디 유효성검사
$(document).ready(function() {
    var RegexId = /^[a-zA-Z0-9_-]{3,16}$/;
    
    $('#id').blur(function() { 
        if (!RegexId.test($.trim($("#id").val()))) { 
            $('.idchk').html(" 영어 또는 숫자 3~16자리").css('color', 'red');
            $('#id').val("");
        } else {
            var memberId = $('#id').val(); 
            $.ajax({
                url: '/login/idCheck', 
                type: "get",
                data: {
                    "memberId": memberId
                },
                dataType: 'text',
                success: function(data) {
                    if (data == "redundancy") {
                        $('.idchk').html("중복된 아이디입니다").css('color', 'red');
                        $('#id').val("");
                    } else if (data == "noredundancy") {
                        $('.idchk').html("사용가능한 아이디입니다").css('color', 'green');
                    } else {
                        $('.idchk').html("아이디를 입력해주세요").css('color', 'red');
                    }
                }
            });
        }
    });
});

</script>

<script>
//비밀번호 일치 여부 확인
$(document).ready(function () {
    $('#pw, #pwchk').keyup(function () {
        checkPasswordMatch();
    });

    function checkPasswordMatch() {
        var pw = $('#pw').val();
        var pwchk = $('#pwchk').val();
        var matchMsg = $('#pwMatchMsg');

        if (pw !== '' && pwchk !== '') {  // Check if both passwords are non-empty
            if (pw.length >= 4 && pw.length <= 15) {
                if (pw === pwchk) {
                    matchMsg.html('비밀번호가 일치합니다.').css('color', 'green');
                } else {
                    matchMsg.html('비밀번호가 일치하지 않습니다.').css('color', 'red');
                }
            } else {
                matchMsg.html('비밀번호는 4자 이상 15자 이하로 작성해주세요.').css('color', 'red');
            }
        } else {
            matchMsg.empty();  // 한 비밀번호라도 비어 있으면 메시지를 지운다.
        }
    }
});

</script>

<script type="text/javascript">
$(document).ready(function () {
    $('#writeForm').submit(function (event) {
        // 아이디 유효성 검사
        var RegexId = /^[a-zA-Z0-9_-]{3,16}$/;
        var id = $.trim($('#id').val());

        if (!RegexId.test(id)) {
            alert("아이디를 올바르게 입력해주세요. (영어 또는 숫자 3~16자리)");
            event.preventDefault(); // 폼 제출 방지
            return;
        }

        // 비밀번호 일치 여부 확인
        var pw = $('#pw').val();
        var pwchk = $('#pwchk').val();
        var matchMsg = $('#pwMatchMsg');

        if (pw !== pwchk || pw.length < 4 || pw.length > 15) {
            alert("비밀번호를 올바르게 입력해주세요. (4자 이상 15자 이하)");
            event.preventDefault(); // 폼 제출 방지
            return;
        }
        
        var name = $.trim($('.name1').val());
        if (!name) {
            alert("이름을 입력해주세요.");
            event.preventDefault();
            return;
        }


        var email = $.trim($('.email').val());
        if (!email) {
            alert("이메일을 입력해주세요.");
            event.preventDefault();
            return;
        }

        var phone = $.trim($('.tel').val());
        if (!phone) {
            alert("전화번호를 입력해주세요.");
            event.preventDefault();
            return;
        }

        var birth_dt = $.trim($('.date').val());
        if (!birth_dt) {
            alert("생년월일을 입력해주세요.");
            event.preventDefault();
            return;
        }
        
     // 주소 입력 부분
        var postcode = $.trim($('#postcode').val());
        var roadAddress = $.trim($('#roadAddress').val());
        var detailAddress = $.trim($('#detailAddress').val());

        if (!postcode || !roadAddress || !detailAddress) {
            alert("주소를 모두 입력해주세요.");
            event.preventDefault();
            return;
        }

        // 모든 검사가 통과하면 폼을 제출
    });
});

</script>




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



