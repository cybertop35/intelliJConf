<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style type="text/css" media="screen">
				@import url("<c:url value="/resources/styles/form.css" />");
				@import url("<c:url value="/resources/script/jquery/jquery.ui.all.css" />");

</style> 

	<script src="<c:url value="/resources/script/jquery/jquery.ui.core.js" />"></script>
	<script src="<c:url value="/resources/script/jquery/jquery.ui.widget.js" />"></script>
	<script src="<c:url value="/resources/script/jquery/jquery.ui.datepicker.js" />"></script>
	<script src="<c:url value="/resources/script/jquery/jquery.activity-indicator-1.0.0.js" />"></script>
	<script src="<c:url value="/resources/script/jquery/jquery.iframe-transport.js" />"></script>
	<script src="<c:url value="/resources/script/jquery/jquery.fileupload.js" />"></script>
	
	<script src="<c:url value="/resources/script/RSA/jsbn.js" />"></script>
	<script src="<c:url value="/resources/script/RSA/rsa.js" />"></script>
	<script src="<c:url value="/resources/script/RSA/rng.js" />"></script>
	<script src="<c:url value="/resources/script/RSA/prng4.js" />"></script>
	<script src="<c:url value="/resources/script/RSA/base64.js" />"></script>




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
<script>
	var fileExt="";
	$(function() {
		$( "#dataNascita" ).datepicker({
			changeMonth: true,
			changeYear: true,
			yearRange: '1900:2030'
		});
		
		$('input:file').fileupload({
       		url: 'preview.htm',
			done: function (e, data) {
				var txt = data.result.toString().replace("\"","");
				$('#viewImg').text("");
				$('#img').remove();
				$('#viewImg').append('<img id="img" src="data:image/'+fileExt+'+;base64,'+txt+'"> </img>');
			},
			fail:function (e, data) {
				$('#fileError').text("Error ");
				$('#busy3').remove();
				$('#img').remove();
				$('#viewImg').text("Preview");
			},
			start:function (e) {
					$('#img').remove();
					$('#viewImg').text("");
					$("#viewImg").append('<div id="busy3" class="square"> </div>');
					$('#busy3').css("margin-top", "55px");
					$('#busy3').css("margin-left", "35px");
					$('#fileError').text("");
					start();
				
			},
			stop:function (e) {
				$('#busy3').remove();
			},
			send:function (e, data) {
				var isValid = true;
				$.each(data.files, function (index, file) {
					if(checkFileExtension(file.name)==false){
						$('#fileError').text("The file extension is not allowed!");
						isValid = false;				
					}
				});
				return isValid;
			}
		});

		
		
	});
	
	$(document).ready(function() {
	    // check name availability on focus lost
	    $('#username').blur(function() {			
			var n = $('#username').val().length;
			reset();
			if( n > 3){
				$("input:file").append('<div id="busy3" class="square"> </div>');
				start();
				checkAvailability();
			}
	    });
		$('#username').focus(function() {			
			reset();
			$('#errorUser').text("");

			
	    });
		
	});
	
	function checkAvailability() {
	    $.getJSON("checkUserName.htm", { username: $('#username').val() }, 
	    function(message) {
			$('#busy3').remove();
			if (message.code==100) {
	            $("#checkUser").append('<img src="<c:url value="/resources/img/Icon/32/Ok.ico" />" id="checkUserImg" style="width:24px;height:24px"/>');				
	        } else {				
				 $("#checkUser").addClass('errors').text(message.message);
			}
	    });
	}
	function start(){
		$('#busy3').activity({segments: 7, width:3, space: 0, length: 3, color: '#bbb8b1', speed: 1.5});
	}
	function reset (){
		$("#checkUserImg").remove();
		$("#checkUser").removeClass('errors').text("");
		$('#busy3').remove();
	}
	
	function getMime(name){
		 var filePath = name;
	     if(filePath.indexOf('.') == -1)
	    	 return "";
	     var ext = filePath.substring(filePath.lastIndexOf('.') + 1).toLowerCase();
	     
	     return ext;
	}
	function checkFileExtension(name) {

		fileExt = getMime(name);
    	var validExtensions = new Array();
        validExtensions[0] = 'jpg';
        validExtensions[1] = 'jpeg';
        validExtensions[2] = 'png';
		
        for(var i = 0; i < validExtensions.length; i++) {
            if(fileExt == validExtensions[i])
                return true;
        }

		return false;
    }
	
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
				<td><form:input path="userLogin.nick" cssClass="textbox" id="username"/></td>
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
					<td><form:input path="nome" cssClass="textbox" id="name"/></td>
					<td><form:errors path="nome" cssClass="errors" /></td>
				</tr>
				<tr>
					<td><label for="cognome">Cognome </label>
					<td><form:input path="cognome" cssClass="textbox"  /></td>
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
				</tr>

			<tr>
				<td><label for="foto">Upload Foto </label></td>
				<td>
				<center>
					<div id="viewImg" class="previwImage">preview</div>
					<span class="btn fileinput-button">
						<span><span><i class="icon-plus icon-white"></i> Add file</span></span>
						<input type="file" name="file" id="file" Class="textbox"/>
					</span>
					</center>
				</td>
				<td><p id="fileError" class="errors"   style="border: none;width:300;text-align: left"></td>
			</tr>
		</table>
	</div>

	<!-----OK----->		
	<h2 class="blockhead">Regolamento</h2>
	<div class="blockbody">
		<div class="section">
			<div class="blockrow">
				<p class="label">Per poter procedere devi accettare le seguenti regole:</p>
				<div id="forumrules" class="restore">
					<p>Per potersi registrare è necessario approvare in ogni sua parte il seguente regolamento. 
					<center><a target="_blank" href=""><b><font color="red"> Leggi il regolamento completo</font></b></a> </center>
						<p>Il regolamento potrà essere nel tempo aggiornato e modificato senza alcun preavviso.</p>

				</div>
			</div>
			<div class="blockrow">
				<label for="cb_rules_agree" >
					<form:checkbox path="accCond" id="accCond" tabindex="1" value="1" name="accCond" />
					<strong>Ho letto ed accetto le Regole.</strong>
				</label>

			</div>
		</div>
	</div>

	<div style="text-align:center">
	
		<a href="#" class="link2 fleft"  onClick="validate();">
			<span>
				<span>Completa la registrazione</span>
			</span>
		</a>
	
	</div>	   
        
    </fieldset>
</form:form>

</c:if>