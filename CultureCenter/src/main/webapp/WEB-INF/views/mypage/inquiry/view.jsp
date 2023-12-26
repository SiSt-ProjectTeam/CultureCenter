<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="cont_wrap">
	<div class="cont_inner no_pb">
		<div class="page_title_area only_mobile">
			<div class="inner">
				<div class="top_area">
					<a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a> 
					
				</div>
			</div>
		</div>
		
<c:if test="${not empty inquiryView}">
	<c:forEach items="${inquiryView}" var="dto">	
<form id="inquiryFrm" name="inquiryFrm">
<input type="hidden" id="personal_faq_sq" name="personal_faq_sq" value="${dto.personal_faq_sq}">
</form>
		<div class="page_cont_area">
			<div class="inner">
				<div class="view_con_w">
					<div class="view_con "><!-- answer_con -->
						<div class="top_area">
							<div class="type_div">
								<p class="type">${dto.branch_nm}</p>
								<p class="type">${dto.faq_tp}</p>
								<p class="type f_caption2">${dto.faq_dt}</p>
							</div>
							<p class="title">${dto.faq_title}</p>
						</div>
						<div class="content">
							<div class="txt">
								${dto.faq_detail}</div>
						</div>
						<div class="remove_btn_wrap">
							<a href="javascript:" class="remove_btn f_btn" onclick="mypageInquiryCtrl.deleteInq(this)" data-personal-faq-sq="${dto.personal_faq_sq}" role="button">
								<span>나의 문의 삭제하기</span>
							</a>
						</div>
					</div>
					<div class="flex_btn_wrap no_pb margin_large">
						<a class="border_btn" href="./list.do"> <span>목록으로</span>
						</a>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</c:if>
		
	</div>
</div>


<script type="text/javascript" src="/resources/common/js/mypage/mypageInquiryCtrl.js"></script>
