package com.radford.aba.shared.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotOnlyWhiteSpaceValidator implements ConstraintValidator<NotOnlyWhiteSpaceConstraint, String>{

	@Override
	public void initialize(NotOnlyWhiteSpaceConstraint constraint) {}

	@Override
	public boolean isValid(String entityField, ConstraintValidatorContext cxt) {
      return entityField != null && entityField.trim().length() > 0;
	}
	
}
