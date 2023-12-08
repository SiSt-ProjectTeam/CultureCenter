<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!doctype html>
<html lang="ko">
<tiles:insertAttribute name="head"/>
<body class="">
	<div id="wrap" class="main" data-is-app="" data-is-mobile="" data-is-login="">
		<tiles:insertAttribute name="header"/>
		<tiles:insertAttribute name="content"/>
		<tiles:insertAttribute name="footer"/>
	</div>

	<script type="text/javascript" src="/resources/common/netfunnel/netfunnel.js" charset="UTF-8"></script>
	
	<script>
		if(false)
		{
			common.logOutTimer.start();
		}
		commonScript.headerFooterFn();
		commonScript.formChkFn();
	</script>
</body>
</html>