<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="ko">
<tiles:insertAttribute name="head"/>
<body class="">
   <sec:authorize access="isAnonymous()">
      <div id="wrap" class="main" data-is-app="" data-is-mobile="" data-is-login="N">
         <tiles:insertAttribute name="header"/>
         <tiles:insertAttribute name="content"/>
         <tiles:insertAttribute name="footer"/>
      </div>      
   </sec:authorize>
   <sec:authorize access="isAuthenticated()">
      <div id="wrap" class="main" data-is-app="" data-is-mobile="" data-is-login="Y">
         <tiles:insertAttribute name="header"/>
         <tiles:insertAttribute name="content"/>
         <tiles:insertAttribute name="footer"/>
      </div>
   </sec:authorize>
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