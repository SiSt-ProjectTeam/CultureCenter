<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<sec:csrfMetaTags/>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

 <style type="text/css">
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }

        form {
            max-width: 600px;
            margin: 20px auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            font-size: 24px;
            color: #333;
            margin-bottom: 20px;
            text-align: center;
        }

        strong {
            color: #333;
        }

        .form_input {
            margin-bottom: 15px;
        }

        input[type="text"],
        input[type="email"],
        input[type="tel"] {
            width: calc(100% - 20px);
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            margin-bottom: 10px;
        }

        input[type="button"] {
            width: 100%;
            padding: 10px;
            background-color: #DDD;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .flex_btn_wrap {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }

        button {
            width: calc(33% - 10px);
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .btn_delete {
            width: 30px;
            height: 30px;
            background-color: #ff4d4d;
            border: none;
            border-radius: 50%;
            color: #fff;
            cursor: pointer;
            font-size: 14px;
            margin-left: 10px;
        }

        #guide {
            color: #999;
            display: none;
        }
    </style>

</head>
<body>





<form action="/mypage/member/upMember.do" method="post" class="joinForm" id="writeForm" enctype="application/json">
	
	<div class="outer">
		<div id="joinInfoArea">
			
				<h1>회원 정보 변경</h1>

								<br><strong>이름</strong>
					            <div class="form_input"> <!-- 유효성 검사시 id = name 으로하면 받아오질 못해서 name1변경 -->
					                <input type="text" id="name1" name="name" placeholder="이름을 입력해주세요." title="이름" value="${mDto.name}">
									<div class="input_btn_wrap">
									</div>
					            </div>
								<br><strong>이메일</strong>
					            <div class="form_input">
					                <input type="email" id="email" name="email" placeholder="이메일을 입력해주세요." title="이메일" value="${mDto.email}">
									<div class="input_btn_wrap">
									</div>
					            </div>
					            
								<br><strong>전화번호</strong>
					            <div class="form_input">
					                <input type="tel" id="phone" name="phone"  maxlength="11" placeholder="전화번호 예) 01012345678"" title="전화번호" value="${mDto.phone}">
									<div class="input_btn_wrap">
									</div>
					            </div>

								<br><strong>주소</strong>
								<div class="form_input">
									<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기" style="width: 100%; padding: 5px; background-color: #DDD;">
									<span id="guide" style="color: #999; display: none"></span> 
									<input type="text" id="postcode" name="postcode" placeholder="우편번호">
									<input type="text" id="roadAddress" name="addr" placeholder="도로명주소" size="35" value="${mDto.addr}">
									<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소" size="60"> 									
									<input type="hidden" id="jibunAddress" name="jibunAddress" placeholder="지번주소" size="60"> 
									<input type="hidden" id="extraAddress" name="extraAddress" placeholder="참고항목" size="60">
									<input type="hidden" id="engAddress" name="engAddress" placeholder="영문주소" size="60">
								</div>
								
									<br>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								<div class="flex_btn_wrap">
									<button type="submit" class="b_color_btn login_btn"><span>수정하기</span></button>
									<button class="b_color_btn login_btn" type="reset"><span>새로입력</span></button>
									<button class="b_color_btn login_btn" type="button" onclick="cancelRegistration()"><span>취소</span></button>
								
								</div>
						</div>
					</div>
			</form>
			
			
<script type="text/javascript">		
$(document).ready(function() {
    $('#writeForm').submit(function(e) {
        e.preventDefault(); // 폼이 실제로 전송되는 것을 막음

        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        
   	  // 도로명주소와 상세주소를 합쳐서 addr로 저장
        var addr = $('#roadAddress').val() + ' ' + $('#detailAddress').val();

        
        var formData = {
            name: $('#name1').val(),
            email: $('#email').val(),
            phone: $('#phone').val(), 
            addr: addr,
            _csrf: {
                parameterName: token,
                token: header
            }
        };

        $.ajax({
            type: 'POST',
            url: '/mypage/member/upMember.do',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function(response) {
                // 성공 시 처리
                alert("회원 정보가 업데이트되었습니다.");
                window.close();
            },
            error: function(error) {
                // 오류 처리
                alert("회원 정보 업데이트에 실패했습니다. 다음에 다시 이용해주세요.");
                window.close();
            }
        });
    });
});
</script>


<script>
    function cancelRegistration() {
        window.close();
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



</body>
</html>