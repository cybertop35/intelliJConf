<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style type="text/css" media="screen">
				@import url("<c:url value="/resources/styles/form.css" />");
				@import url("<c:url value="/resources/script/jquery/jquery.ui.all.css" />");

</style> 

<script type="text/javascript">

<!--
	function validate(){
		
		var isValid = new Boolean();
		isValid = true;
		document.getElementById('msg').innerHTML = "Regitrazione";
		document.getElementById('pwsError2').innerHTML = "";
		
		if(document.userForm.pws1.value.length ==0 ||document.userForm.pws1.value != document.userForm.pws2.value){
			isValid=false;
			document.getElementById('pwsError2').innerHTML = "La password non coincide";
		}
		if(document.userForm.accCond.checked == false){
			isValid=false;
			document.getElementById('msg').innerHTML = 'Regitrazione <font color="#ff0000"> <b>-  Per continuare bisogna accettare le condizioni. </b></font>';
		}
		if(isValid == true){
			do_encrypt(document.userForm.pws1.value);
			
			document.getElementById('userForm').submit();
		}
		
		return isValid;
	};
	
	function do_encrypt(text) {
	  var rsa = new RSAKey();
	  rsa.staticRSASetPublic();
	  var res = rsa.encrypt(text);
	  if(res) {
		document.userForm.pwsToSend.value = linebrk(hex2b64(res), 64);
	  }
	}
	
//-->
</script>



<c:if test="${not empty statusMessageError && statusMessageCode !='100'}">   
<p class="errors">${statusMessageError}"</p>
</c:if>
<c:if test="${statusMessageCode =='100'}">

	<div class="blockbody">
		
		<h2 class="blockhead1">Registrazione </h2>
		<div class="section">
			<div class="blockrow">
				<div id="forumrules" class="${success}">
						${statusMessage}" 

				</div>
			</div>
		</div>
	</div>

</c:if>
<c:if test="${statusMessageCode != '100'}">
<form:form action="${url}" commandName="persona" id="userForm" name="userForm" onsubmit="return validate();" enctype="multipart/form-data">



    <fieldset>
	<h2 class="blockhead1" id="msg">Registrazione </h2>
	
	<div class="blockbody">
		
		<h3 class="blocksubhead">Informazioni richieste</h3>
		<table class="tableForm">
			<tr>
				<td><label for="nick">UserName </label></td>
				<td><form:input path="userLogin.nick" cssClass="textbox" id="username" disabled="true"/></td>
				<td id="checkUser" ><form:errors path="userLogin.nick" cssClass="errors" id="errorUser" /></td>
			</tr>
			<tr>
				<td>	<label for="email">Email </label></td>
				<td>	<form:input path="email" cssClass="textbox" /></td>
				<td><form:errors path="email" cssClass="errors"  /></td>
			</tr>
			<tr>
				<td>	<label for="password">Password </label></td>
				<td>	<input type="password" id="pws1" name="pws1" class="textbox"/></td>
				<td><form:errors path="userLogin.password" cssClass="errors" id="pwserror" name="pwserror" /></td>
			</tr>
			<tr>
				<td><label for="name">Ripeti Password </label></td>
					<td>
						<input type="password" id="pws2" name="pws2" class="textbox" />
						<form:hidden path="userLogin.password" id="pwsToSend"  />
					</td>
				<td><p id="pwsError2" class="errors"   style="border: none;width:300;text-align: left"></p></td>
			</tr>
			
		</table>

	
		<h3 class="blocksubhead">Informazioni Aggiuntive</h3>
		
			<table class="tableForm">
				<tr>
					<td><label for="name">Name </label></td>
					<td><form:input path="nome" cssClass="textbox" id="name"  disabled="true"/></td>
					<td><form:errors path="nome" cssClass="errors" /></td>
				</tr>
				<tr>
					<td><label for="cognome">Cognome </label>
					<td><form:input path="cognome" cssClass="textbox"  disabled="true" /></td>
					<td><form:errors path="cognome" cssClass="errors" /></td>
				</tr>		
				<tr>
					<td><label for="sesso">Sesso </label>				
					<td>
							<form:select path="sesso" cssClass="textbox" >
								<form:option value=" " selected="selected" />
								<form:option value="M"/>
								<form:option value="F"/>
							</form:select>
						
					</td>
					<td><form:errors path="sesso" cssClass="errors" /></td>
				</tr>
				
				<tr>
					<td><label for="dataNascita">Data di nascita </label></td>
					<td><form:input path="dataNascita" cssClass="textbox"/></td>
					<td><form:errors path="dataNascita" cssClass="errors" /></td>
				</tr>
				<tr>
					<td><label for="telefono">Telefono </label></td>
					<td><form:input path="telefono" cssClass="textbox"  /></td>
					<td><form:errors path="telefono" cssClass="errors" /></td>
<!-- 				</tr> -->

<!-- 					<tr> -->
<!-- 						<td><label for="foto">Upload Foto </label></td> -->
<!-- 						<td> -->
<%-- 							<center> --%>
<!-- 								<div id="viewImg" class="previwImage">preview</div> -->
<!-- 								<span class="btn fileinput-button"> <span><span><i -->
<!-- 											class="icon-plus icon-white"></i> Add file</span></span> <input type="file" -->
<!-- 									name="file" id="file" Class="textbox" /> -->
<!-- 								</span> -->
<%-- 							</center> --%>
<!-- 						</td> -->
<!-- 						<td><p id="fileError" class="errors" -->
<!-- 								style="border: none; width: 300; text-align: left"></td> -->
<!-- 					</tr> -->
				</table>
	</div>

	<div style="text-align:center">
	
		<a href="#" class="link2 fleft"  onClick="validate();">
			<span>
				<span>Salva Modifiche</span>
			</span>
		</a>
	
	</div>	   
        
    </fieldset>
</form:form>

</c:if>