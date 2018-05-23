package com.jobapplication.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SalaryValidValidator implements ConstraintValidator<SalaryValid, String> {

	@Override
	public void initialize(SalaryValid theSalaryValid) {
	}

	@Override
	public boolean isValid(String salary, ConstraintValidatorContext context) {
		boolean result = true;
		if(salary != null){
			Pattern pattern = Pattern.compile("^[1-9]{1}+[0-9]{0,6}\\.{0,1}([0-9]{0,2})?");
			result = pattern.matcher(salary).matches();
		}
		return result;
	}

}
