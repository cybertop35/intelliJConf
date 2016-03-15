<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" %>

<style type="text/css" media="screen">
				@import url("<c:url value="/resources/styles/login-box.css" />");
		</style> 
		 
		
	<script src="<c:url value="/resources/script/RSA/jsbn.js" />"></script>
	<script src="<c:url value="/resources/script/RSA/rsa.js" />"></script>
	<script src="<c:url value="/resources/script/RSA/rng.js" />"></script>
	<script src="<c:url value="/resources/script/RSA/prng4.js" />"></script>
	<script src="<c:url value="/resources/script/RSA/base64.js" />"></script>
		
<script type="text/javascript">

<!--
function do_encrypt() {
	
	var text = document.loginform.password.value;
	var user = document.loginform.j_username.value;
//	document.loginform.error.value = "";

	document.loginform.j_password.value = text;//linebrk(hex2b64(res), 64);
	document.getElementById('loginform').submit();
	
	if(user.length() ==  0 || text.length() == 0){
		document.getElementById('error').value = "Username o password non valida.";
	}
	else{
		//var rsa = new RSAKey();
		//rsa.staticRSASetPublic();
		//var res = rsa.encrypt(text);
		//if(res) {
			document.loginform.j_password.value = text;//linebrk(hex2b64(res), 64);
			document.getElementById('loginform').submit();
	//	}
	}
}
//-->
</script>

<sec:authorize access="isAuthenticated()">
	<table>
	        <tr><td>User:</td><td><sec:authentication property="principal.username"/></td></tr>     
	        <tr><td>Role:</td><td></td></tr> 
			<tr><td><a href="<c:url value="/app/secure/logout" />">Logout</a></td><td></td></tr>
	</table>
</sec:authorize>

<sec:authorize access="isAnonymous()">
	
<div class="box col-1 ">
		<div class="border-right">
			<div class="border-bot">
				<div class="border-left">
					<div class="left-top-corner">
						<div class="right-top-corner">
							<div class="right-bot-corner">
								<div class="left-bot-corner">
									<div class="inner">
										<h3>Login</h3>
											    <c:if test="${not empty param.login_error}">
												  <font color="red">
													Your login attempt was not successful, try again.<br/><br/>
													Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
												  </font>
												</c:if>
											<br />
										<div id="login-box">
										<label id="error" class="errors"> </label>
										<form name="loginform" id="loginform" action="<c:url value='/app/pub/login/login_security_check.do'/>" method="POST">
											<table>
												<tr>
													<td><label>Username:</label></TD>
													<td><input class=".textbox" title="Username"  type='text' name='j_username' id='j_username' value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/></td>
												</tr>
												<tr>
													<td><label>Password: </label></td>
													<td><input name="password" type="password" class=".textbox" title="Password" value="" id="password" />
														<input  type="hidden"  title="Password" value="" name='j_password' id="j_password" />
													</td>
												</TR>
												<tr>
													<td></td>
													<td><span><input type="checkbox" name="_spring_security_remember_me" value="1"> Remember Me </span></td>
												</TR>
												<tr>
													<td><div class="aligncenter" style="margin-top:14px;"><a href="#" class="link1"  onClick="do_encrypt();"><span><span>Login</span></span></a></div></td>
												</tr>
													
											</table>
											<div style="margin-top:-40px;margin-left:150;position:absolute" >	
												<ul style="list-style-type: none; ">
													<li ><span><a href="<c:url value="/app/pub/personServices/forgotPassword.htm" />">Forgot password?</a></span></li>
													<li style="margin-top:8px"><span><a href="<c:url value="/app/pub/personServices/registration.htm" />">Registration</a></span></li>
												</ul>
											</div>	
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

 </sec:authorize>
