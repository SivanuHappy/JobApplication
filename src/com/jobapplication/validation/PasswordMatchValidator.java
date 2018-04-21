package com.jobapplication.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jobapplication.entity.User;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {

	@Override
	public void initialize(PasswordMatch constraintAnnotation) {
	}
	
	@Override
	public boolean isValid(Object pswd, ConstraintValidatorContext context) {
        User user = (User) pswd;
        return user.getPassword() == null && user.getConfirmpassword() == null || user.getPassword() != null && user.getConfirmpassword().equals(user.getPassword());  		
	}
}

