package com.fantagame.be.business.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.fantagame.be.business.data.bean.Persona;

public class UserValidator  implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return Persona.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		Persona persona = (Persona) obj;
		
		RegexlValidator textValidator = new RegexlValidator();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userLogin.nick", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userLogin.password", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required");
		
		if(persona.getUserLogin().getNick().length() < 5)
			errors.rejectValue("userLogin.nick", "field.invalid.username");
		if(persona.getUserLogin().getPassword().length() < 6)
			errors.rejectValue("userLogin.password", "field.invalid.password");
		if(!textValidator.validate(persona.getEmail(),RegexlValidator.EMAIL_PATTERN))
			errors.rejectValue("email", "field.invalid.mail");
		if(persona.getTelefono() != null && persona.getTelefono().length() > 1 &&!textValidator.validate(persona.getTelefono(), RegexlValidator.PHONE_PATTERN))
			errors.rejectValue("telefono", "field.invalid.telefono");
		
		if(!persona.getAccCond())
			errors.rejectValue("accCond","accettaCondizione.false");
	}
	

}
