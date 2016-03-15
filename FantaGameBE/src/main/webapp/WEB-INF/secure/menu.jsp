<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link rel="stylesheet" type="text/css" href="pro_dropdown_2/pro_dropdown_2.css" />

<style type="text/css" media="screen">
		@import url("<c:url value="/resources/styles/menu/pro_dropdown_2.css" />"); 
</style>

<script type="text/javascript" src="<c:url value="/resources/styles/menu/stuHover.js" />"> </script>

<ul id="nav">
	<li class="top"><a href="<c:url value="/app/secure/home.htm" />" class="top_link"><span>Home</span></a></li>
	<li class="top"><a href="<c:url value="/app/secure/showMysquadra.htm"/>" class="top_link"><span class="down">Gestione squadra</span></a>
 		<UL CLASS="SUB"> 

 			<LI><a  href="<c:url value="/app/secure/createClub.htm"/>"> Crea nuova</a></LI> 

 		</UL> 
	</li>
	<li class="top"><a href="<c:url value="/app/secure/showMysquadra.htm"/>" id="products" class="top_link"><span class="down">Gestione gruppi</span></a>
		<ul class="sub">
			<li><a href="#nogo3" >Crea Nuovo</a></li>
			<li><a href="#nogo3" >Modifica</a></li>
			<li><a href="#nogo3" >Invita</a></li>
			<li><a href="#nogo3">Andamento</a></li>			
		</ul>
	</li>
	<li class="top"><a href="" id="products" class="top_link"><span class="down">Profilo</span></a>
		<ul class="sub">
			<li><a href="<c:url value="/app/secure/myProfile.htm"/>">Modifica</a></li>
		</ul>
	</li>
</ul>