<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="cont_wrap" data-page-type="list">
	<div class="cont_inner no_pb">
		<div class="page_title_area">
			<div class="inner">
				<div class="top_area">
					<a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a> <a
						href="javascript:" class="tit_div arrow" title="페이지 이동 팝업 열기">
						<p class="tit f_h1">
							회원가입 <span class="more_tit"></span>
						</p>
					</a>
				</div>
			</div>
			<div class="m_pop_dimd"></div>
		</div>

		<div class="page_cont_area">
			<form action="/member/join.do" method="post" class="joinForm" id="writeForm">
				<input type="hidden" id="idConfirmed" value="false"><br>
				
					<div class="login_form">
						<div class="login_box">
							<div class="inner sub_inner_w" style="display: flex; flex-direction: column; align-items: center;">
								<strong>아이디</strong>
								<div class="form_input">
									<input type="text" id="id" name="id" placeholder="아이디를 입력해주세요." title="아이디" value="test">
									<div class="input_btn_wrap">
										<button type="button" class="btn_delete" title="아이디 지우기"></button>
									</div>
									<span class="idchk"></span>
								</div>
								
								<br><strong>비밀번호</strong>
								<div class="form_input">									
									<input type="password" id="pw" name="pw" placeholder="비밀번호를 입력해주세요." title="비밀번호" value="1234">
									<div class="input_btn_wrap">
										<button type="button" class="btn_delete" title="비밀번호 지우기"></button>
									</div>
								</div>
								
								<br><strong>비밀번호 확인</strong>
								<div class="form_input">
					                <input type="password" id="pwConfirm" placeholder="비밀번호를 다시 입력해주세요." title="비밀번호 확인" value="1234">
									<div class="input_btn_wrap">
										<button type="button" class="btn_delete"></button>
									</div>
									<span id="pwMatchMsg"></span>
					            </div>
					            
								<br><strong>이름</strong>
					            <div class="form_input"> <!-- 유효성 검사시 id = name 으로하면 받아오질 못해서 name1변경 -->
					                <input type="text" id="name1" name="name" placeholder="이름을 입력해주세요." title="이름" value="유희진">
									<div class="input_btn_wrap">
										<button type="button" class="btn_delete"></button>
									</div>
					            </div>
					            
								<br><strong>이메일</strong>
					            <div class="form_input">
					                <input type="email" id="email" name="email" placeholder="이메일을 입력해주세요." title="이메일" value="123@naver.com">
									<div class="input_btn_wrap">
										<button type="button" class="btn_delete"></button>
									</div>
					            </div>
					            
					            
								<br><strong>전화번호</strong>
					            <div class="form_input">
					                <input type="tel" id="phone" name="phone"  placeholder="전화번호 예) 01012345678"" title="전화번호" value="01012341234">
									<div class="input_btn_wrap">
										<button type="button" class="btn_delete"></button>
									</div>
					            </div>
     <!-- 
								<br><strong>생년월일</strong>
								<div class="form_input">
									<input type="date" id="birth_dt" class="date" name="birth_dt">
									<div class="input_btn_wrap">
										<button type="button" class="btn_delete"></button>
									</div>
								</div>		
								 -->
								<br><strong>주소</strong>
								<div class="form_input">
									<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기" style="width: 100%; padding: 5px; background-color: #DDD;">
									<span id="guide" style="color: #999; display: none"></span> 
									<input type="text" id="postcode" name="postcode" placeholder="우편번호">
									<input type="text" id="roadAddress" name="addr" placeholder="도로명주소" size="35">
									<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소" size="60"> 									
									<input type="hidden" id="jibunAddress" name="jibunAddress" placeholder="지번주소" size="60"> 
									<input type="hidden" id="extraAddress" name="extraAddress" placeholder="참고항목" size="60">
									<input type="hidden" id="engAddress" name="engAddress" placeholder="영문주소" size="60">
								</div>
								
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								<div class="flex_btn_wrap">
									<button type="submit" class="b_color_btn login_btn"><span>회원가입</span></button>
									<button class="b_color_btn login_btn" type="reset"><span>새로입력</span></button>
									<button class="b_color_btn login_btn" type="button"	onclick="cancelRegistration()"><span>취소</span></button>
								</div>
						</div>
					</div>
				</div>
			</form>




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
    function cancelRegistration() {
    	 // 현재는 이전 페이지로 이동 
    	 // 이전으로 안가지고 홈페이지로 가진다.??
        // history.back();

        // 특정 URL로 이동하려면 아래와 같이 사용
        window.location.href = '/login/index.do';
    }
</script>

<script>
    // 아이디 유효성 검사
    $(document).ready(function() {
        var RegexId = /^[a-zA-Z0-9_-]{3,16}$/;

        $('#id').blur(function() {
            if (!RegexId.test($.trim($("#id").val()))) {
                $('.idchk').html(" 영어 또는 숫자 3~16자리").css('color', 'red');
                $('#idConfirmed').val("false");
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
                            $('#idConfirmed').val("false");
                            $('#id').val("");
                        } else if (data == "noredundancy") {
                            $('.idchk').html("사용가능한 아이디입니다").css('color', 'green');
                            $('#idConfirmed').val("true");
                        } 
                    }
                });
            }
        });
    });

</script>

<script>
    // 비밀번호 일치 여부 확인
    $(document).ready(function() {
        $('#pw, #pwConfirm').keyup(function() {
            checkPasswordMatch();
        });

        function checkPasswordMatch() {
            var pw = $('#pw').val();
            var pwConfirm = $('#pwConfirm').val();
            var matchMsg = $('#pwMatchMsg');

            if (pw !== '' && pwConfirm !== '') {
                if (pw.length >= 4 && pw.length <= 15) {
                    if (pw === pwConfirm) {
                        matchMsg.html('비밀번호가 일치합니다.').css('color', 'green');
                    } else {
                        matchMsg.html('비밀번호가 일치하지 않습니다.').css('color', 'red');
                    }
                } else {
                    matchMsg.html('비밀번호는 4자 이상 15자 이하로 작성해주세요.').css('color', 'red');
                }
            } else {
                matchMsg.empty(); // 한 비밀번호라도 비어 있으면 메시지를 지운다.
            }
        }
    });
</script>

<script type="text/javascript">
    $(document).ready(function() {
        $('#writeForm').submit(function(event) {
            // 아이디 유효성 검사
            var idConfirmed = $('#idConfirmed').val();
            if (idConfirmed !== "true") {
                alert("아이디를 올바르게 입력해주세요.");
                event.preventDefault(); // 폼 제출 방지
                return;
            }

            // 비밀번호 일치 여부 확인
            var pw = $('#pw').val();
            var pwchk = $('#pwConfirm').val(); // 수정: 비밀번호 확인 필드 ID 변경
            var matchMsg = $('#pwMatchMsg');

            if (pw !== pwchk || pw.length < 4 || pw.length > 15) {
                alert("비밀번호를 올바르게 입력해주세요.");
                event.preventDefault(); // 폼 제출 방지
                return;
            }

            // 이름 유효성 검사
            // id 변경 -> name로하면 받아오질 못해서
            var name = $.trim($('#name1').val());
            if (!name) {
                alert("이름을 입력해주세요.");
                event.preventDefault(); // 폼 제출 방지
                return;
            }

            // 이메일 유효성 검사
            var email = $.trim($('#email').val());
            if (!email) {
                alert("이메일을 입력해주세요.");
                event.preventDefault(); // 폼 제출 방지
                return;
            }

            // 전화번호 유효성 검사
            var phone = $.trim($('#phone').val());
            var phoneRegex = /^[0-9]{10,11}$/;
            if (!phoneRegex.test(phone)) {
                alert("전화번호를 올바르게 입력해주세요.");
                event.preventDefault(); // 폼 제출 방지
                return;
            }

            // 주소 유효성 검사
            var postcode = $.trim($('#postcode').val());
            var roadAddress = $.trim($('#roadAddress').val());
            var detailAddress = $.trim($('#detailAddress').val());

            if (!postcode || !roadAddress || !detailAddress) {
                alert("주소를 모두 입력해주세요.");
                event.preventDefault(); // 폼 제출 방지
                return;
            }

            // 모든 검사가 통과하면 폼을 제출
        });
    });

    // 아이디 삭제 버튼 클릭 시 이벤트
    $('#id .btn_delete').click(function() {
        $('#id').val('');
        $('#idConfirmed').val('false');
        $('.idchk').empty();
    });

    // 비밀번호 삭제 버튼 클릭 시 이벤트
    $('#pw, #pwConfirm .btn_delete').click(function() {
        $('#pw').val('');
        $('#pwConfirm').val('');
        $('#pwMatchMsg').empty();
    });
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
			<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
			
			
		</div>
	</div>
</div>