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
    font-family: 'Helvetica', sans-serif;
    background-color: #f2f2f2;
    margin: 0;
    padding: 0;
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100vh;
  }

  .outer {
    width: 400px;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }

  h1 {
    color: #333;
    text-align: center;
  }

  h4 {
    margin-top: 15px;
    color: #555;
  }

  .input_area {
    margin-bottom: 15px;
  }

  .pw {
    width: 100%;
    padding: 10px;
    box-sizing: border-box;
  }

  input[type="submit"] {
    background-color: #4caf50;
    color: #fff;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    display: block;
    margin: 0 auto;
  }

  input[type="submit"]:hover {
    background-color: #45a049;
  }
</style>
</head>
<body>







<form action="/mypage/member/upPw.do" method="post" class="joinForm" id="writeForm" enctype="multipart/form-data">
	
	<div class="outer">
			<div id="joinInfoArea">

            <h1>비밀번호</h1>
            <strong>기존 비밀번호</strong>
            <div class="form_input">
                <div class="input_btn_wrap">
               		 <input type="password" id="password" name="password" placeholder="비밀번호를 입력해주세요." title="비밀번호" value="">
                    <button type="button" class="" title="기존 비밀번호 확인" onclick="checkPassword()">확인</button>
                </div>
            </div>
            <div id="resultMessage"></div>
        
				
				<br>
				<strong>변경할 비밀번호</strong>
				<div class="form_input">
					<input type="password" id="pw" name="pw"
						placeholder="비밀번호를 입력해주세요." title="비밀번호" value="">
				</div>

				<br>
				<strong>변경할 비밀번호 확인</strong>
				<div class="form_input">
					<input type="password" id="pwConfirm"
						placeholder="비밀번호를 다시 입력해주세요." title="비밀번호 확인" value="">
					<span id="pwMatchMsg"></span>
				</div>
				<br>
					 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					 <input type="submit" class="btn" value="변 경 하 기" onclick="return validateForm()" />
					 <button class="b_color_btn login_btn" type="button" onclick="cancelRegistration()"><span>취소</span></button>
		</div>
    </div>
    
		
	
</form>

<script type="text/javascript">
    $(document).ready(function () {
        // 초기 상태: 제출 버튼 비활성화
        $('input[type="submit"]').prop('disabled', true);

        // 비밀번호와 확인의 키 입력 이벤트 핸들러
        $('#pw, #pwConfirm').keyup(function () {
            checkPasswordMatch();
        });

        // 비밀번호와 확인이 일치하는지 확인하는 함수
        function checkPasswordMatch() {
            var pw = $('#pw').val();
            var pwConfirm = $('#pwConfirm').val();
            var matchMsg = $('#pwMatchMsg');
            var submitBtn = $('input[type="submit"]');

            if (pw !== '' && pwConfirm !== '') {
                if (pw.length >= 4 && pw.length <= 15) {
                    if (pw === pwConfirm) {
                        matchMsg.html('비밀번호가 일치합니다.').css('color', 'green');
                        submitBtn.prop('disabled', false);
                        return true;
                    } else {
                        matchMsg.html('비밀번호가 일치하지 않습니다.').css('color', 'red');
                        submitBtn.prop('disabled', true);
                    }
                } else {
                    matchMsg.html('비밀번호는 4자 이상 15자 이하로 작성해주세요.').css('color', 'red');
                    submitBtn.prop('disabled', true);
                }
            } else {
                matchMsg.empty();
                submitBtn.prop('disabled', true);
            }

            return false;
        }

        // 폼 제출 이벤트
        $('#writeForm').submit(function (event) {
            var pw = $('#pw').val();
            var pwchk = $('#pwConfirm').val();

            // 비밀번호 유효성 검사
            if (pw !== pwchk || pw.length < 4 || pw.length > 15) {
                alert("비밀번호를 올바르게 입력해주세요.");
                event.preventDefault();
            }
        });


        // 제출 버튼 비활성화 함수
        function disableSubmitButton() {
            $('input[type="submit"]').prop('disabled', true);
        }
    });

    // 서버에서 비밀번호 확인하는 함수
    function checkPassword() {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        var formData = {
            pw: $('#password').val(),
            _csrf: {
                parameterName: token,
                token: header
            }
        };

        $.ajax({
            type: 'POST',
            url: '/mypage/member/password.do',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(formData),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (response) {
                if (response.success) {
                    $('#resultMessage').text('비밀번호가 일치합니다.');

                    var newPassword = $('#pw').val();
                    if (newPassword.length >= 4 && newPassword.length <= 15) {
                        checkPasswordMatch(); // 비밀번호 일치 여부 확인
                    } else {
                        alert("새로운 비밀번호는 4자 이상 15자 이하로 입력해주세요.");
                    }
                } else {
                    $('#resultMessage').text('틀린 비밀번호입니다.');
                    disableSubmitButton(); // 기존 비밀번호가 틀릴 경우 제출 버튼 비활성화
                }
            },
            error: function (error) {
                console.error(error);
                $('#resultMessage').text('비밀번호 확인 중 오류가 발생했습니다.');
                disableSubmitButton(); // 오류 발생 시 제출 버튼 비활성화
            }
        });
    }
</script>


<script>
    function cancelRegistration() {
        window.close();
    }
</script>






</body>
</html>