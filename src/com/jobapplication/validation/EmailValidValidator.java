package com.jobapplication.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidValidator implements ConstraintValidator<EmailValid, String> {

	
	@Override
	public void initialize(EmailValid theEmailValid) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		System.out.println(email);
		boolean result = true;
		if(email != null){
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		result = pattern.matcher(email).matches();
		}
		return result;
	}

}
