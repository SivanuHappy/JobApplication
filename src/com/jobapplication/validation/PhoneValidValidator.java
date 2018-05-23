package com.jobapplication.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.jobapplication.entity.Applicant;
import com.jobapplication.entity.Employer;

public class PhoneValidValidator implements ConstraintValidator<PhoneValid, String> {

	@Override
	public void initialize(PhoneValid thePhoneValid) {
	}

	@Override
	public boolean isValid(String phone, ConstraintValidatorContext context) {
		boolean result = true;
		System.out.println(phone);
		if(phone != null){
		Pattern pattern = Pattern.compile("^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$",
				Pattern.CASE_INSENSITIVE);
		result = pattern.matcher(phone).matches();
		}
		return result;
	}

}
