<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<body class="">
	<div class="page_title_area">
		<div class="inner">
			<br>
			<br>
			<br>
			<div>
				<h1>My Access Denied Page</h1>
				<h2>
					<c:out value="${ SPRING_SECURITY_403_EXCEPTION.getMessage() }" />
				</h2>
				<h2>
					<c:out value="${ msg }" />
				</h2>
				기타 오류 설명 부분
			</div>
		</div>
	</div>
</body>
