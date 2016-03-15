<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- <ul> -->
<%-- 	<li><a href="<c:url value="/app/pub/home.htm" />">Home</a></li> --%>
<%-- 	<li><a href="<c:url value="/app/pub/news.htm" />">News</a></li> --%>
<%-- 	<li><a href="<c:url value="/app/pub/contatti.htm" />">Contatti</a></li> --%>
<%-- 	<li><a href="<c:url value="/app/pub/chisono.htm" />">Chi sono</a></li> --%>
<%-- 	<li><a href="<c:url value="/app/pub/testDao" />">Test Dao</a></li> --%>
<!-- </ul> -->

<link rel="stylesheet" type="text/css" href="pro_dropdown_2/pro_dropdown_2.css" />

<style type="text/css" media="screen">
		@import url("<c:url value="/resources/styles/menu/pro_dropdown_2.css" />"); 
</style>

<script type="text/javascript" src="<c:url value="/resources/styles/menu/stuHover.js" />"> </script>

<ul id="nav">
	<li class="top"><a href="<c:url value="/app/pub/home.htm" />" class="top_link"><span>Home</span></a></li>
	<li class="top"><a href="<c:url value="/app/pub/news.htm" />" class="top_link"><span>News</span></a></li>
	<li class="top"><a href="<c:url value="/app/pub/contatti.htm" />" class="top_link"><span>Contanti</span></a></li>
	<li class="top"><a href="<c:url value="/app/pub/info.htm" />" class="top_link"><span>Info</span></a></li>
</ul>