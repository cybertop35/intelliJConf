

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<title>
			<tiles:getAsString name="title" />
		</title>
		<style type="text/css" media="screen">
			@import url("<c:url value="/resources/styles/standard.css" />");
		</style> 
	</head>
	<body>
	<script type="text/javascript" src="<c:url value="/resources/script/jquery/jquery-1.7.1.js" />"> </script>
	
	<div id="page">			
			<div id="header" class="clearfix"></div>
			
			<div id="content" class="clearfix">				
				<div id="main">
					<tiles:insertAttribute name="body" />
				</div>				
			</div>
	</div>
	
	</body>
</html>
