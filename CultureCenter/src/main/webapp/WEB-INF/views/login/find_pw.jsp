<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    margin: 0 auto;
    background-color: #fff;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    margin-top: 50px;
}

.search-title {
    text-align: center;
    margin-bottom: 20px;
}

h3 {
    color: #333;
}

.form-search {
    margin-bottom: 20px;
}

label {
    display: block;
    margin-bottom: 8px;
    font-weight: bold;
}

input {
    width: 100%;
    padding: 10px;
    margin-bottom: 15px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.btnSearch {
    text-align: center;
}

input[type="button"] {
    background-color: #4caf50;
    color: #fff;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
}

input[type="button"]:hover {
    background-color: #45a049;
}

input[type="button"]:last-child {
    margin-left: 10px;
    background-color: #f44336;
}

input[type="button"]:last-child:hover {
    background-color: #d32f2f;
}
   
   </style>
</head>
<body>




	<form method = "GET">
			<div class = "search-title">
				<h3>등록한 정보로 비밀번호 찾기</h3>
			</div>
		<section class = "form-search">
			<div class = "find-id">
				<label>아이디</label>
				<input type="text" name="id" class="id" placeholder = "ID">
			<br>
			</div>
		
		 <div class = "find-phone">
				<label>전화번호</label>
				<input type="text" name="phone" class = "phone" maxlength="11" placeholder = "휴대폰번호를 '-'없이 입력">
			</div> 
			<br>
	</section>
	<div class ="btnSearch">
		<input type="button" name="enter" value="찾기" onClick="pw_search()" >
		<input type="button" name="cancle" value="취소" onclick="closeWindow()">
 	</div>
 </form>


<script>
function pw_search() {
    
    var id = $('.id').val();
    var phone = $('.phone').val();

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
  
    $.ajax({
        url: '/login/findPw.do', 
        type: "post",
        data: {
            "id":id,
            "phone":phone
        },
        dataType: 'text',
        beforeSend: function(xhr) {
            // 헤더에 CSRF 토큰 추가
            xhr.setRequestHeader(header, token);
        },
        success: function (data) {
            if (data !== "") {
                alert("찾은 비밀번호 : " + data);
            } else {
                alert("일치하는 비밀번호가 없습니다. " );
            }
        }
    });
}

</script>


<script>
    function closeWindow() {
        window.close();
    }
</script>


</body>
</html>