package com.jobapplication.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jobapplication.entity.Applicant;

public class PhoneValidValidator implements ConstraintValidator<PhoneValid, Object> {

	@Override
	public void initialize(PhoneValid constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object phone, ConstraintValidatorContext context) {
		Applicant app = (Applicant) phone;
		boolean result = true;
		if(app.getPhone() != null){
		Pattern pattern = Pattern.compile("^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$",
				Pattern.CASE_INSENSITIVE);
		result = pattern.matcher(app.getPhone()).matches();
		}
		return result;
	}

}
