package com.jobapplication.validation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD,ElementType.FIELD}) 
@Retention(RUNTIME)
@Constraint(validatedBy = SalaryValidValidator.class)

public @interface SalaryValid {
	String message() default "Invalid salary";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
