<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="cont_wrap">
	<div class="cont_inner no_pb">
		<div class="page_title_area only_mobile">
			<div class="inner">
				<div class="top_area">
					<a href="javascript:fnc.back();" class="page_prev_btn" title="뒤로가기"></a>
					<!-- 2022-11-23 추가 -->
					<div class="share_area only_mobile">
						<a href="javascript:commonScript.openPopupFn('#sharePop');" class="share_btn" title="공유하기"></a>
					</div>
					<!-- // 2022-11-23 추가 -->
				</div>
				<div class="m_pop_dimd"></div>
			</div>
			<div class="m_pop_dimd"></div>
		</div>
		<div class="page_cont_area">
			<div class="inner">
				<div class="view_con_w">
					<div class="view_con">
						<div class="top_area">
							<div class="type_div">
								<p class="type">${notice.notice_event == 'notice' ? '공지사항' : '이벤트'}</p>
								<p class="type">${notice.branch_nm}</p>
								<p class="type">${notice.write_dt}</p>
							</div>
							<div class="share_area only_pc"><!-- 2022-11-23 class 추가 -->
								<a href="javascript:commonScript.openPopupFn('#sharePop');" class="share_btn" title="공유하기"></a>
							</div>
							<p class="title">${notice.posting_title}</p>
						</div>
						<div class="content">
							<div class="txt">${notice.posting_content}</div>
						</div>
						<div class="flex_btn_wrap">
							<a class="border_btn" href="/community/notice/list.do">
							<%-- <a class="border_btn" href="/community/notice/list.do?brchCd=${frmSearchDTO.brchCd}&clCd=${frmSearchDTO.clCd}&notcSeqno=${frmSearchDTO.notcSeqno}&q=${frmSearchDTO.q}&pageIndex=${frmSearchDTO.pageIndex}&initIndex=${frmSearchDTO.initIndex}&listCnt=${frmSearchDTO.listCnt}"> --%>
								<span>목록으로</span>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>