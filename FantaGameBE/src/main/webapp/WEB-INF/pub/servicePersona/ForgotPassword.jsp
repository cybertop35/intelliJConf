<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style type="text/css" media="screen">
				@import url("<c:url value="/resources/styles/form.css" />");
</style> 

<h2 class="blockhead1">Forget Password </h2>
<div class="blockbody">
	<c:if test="${statusMessageCode } == '100'">
		<div class="section">
			<div class="blockrow">
				<div id="forumrules" class="${classs}">
						${message}"
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${statusMessageCode !='100'}">	
		<c:if test="${statusMessageCode  == '-1'}" >		
			<div class="section">
				<div class="blockrow">
					<div id="forumrules" class="${classs}">
							${message}"
					</div>
				</div>
			</div>
		</c:if>
			<div class="section">
				<div class="blockrow">
					<form:form id="sendmail" action="${url}" method="POST" commandName="persona">
						<table class="tableForm">
							<tr>
								<td>Enter your mail</td>
								<td><form:input path="email" cssClass="textbox" /></td>
								<td><form:errors  path="email" ssClass="errors"/></td>
							</tr>
						</table>					
					</form:form>
					<div style="text-align:center">	
						<a href="#" class="link2 fleft"  onClick="document.getElementById('sendmail').submit()">
							<span>
								<span>Send</span>
							</span>
						</a>
					</div>	
				</div>
			</div>
	</c:if>		

</div>		
		