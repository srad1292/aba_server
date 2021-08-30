/**
 * 
 */
package com.radford.aba.shared.validator;

import static org.junit.jupiter.api.Assertions.*;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;


/**
 * @author smr12
 *
 */
class NotOnlyWhiteSpaceValidatorTest {

	@Mock
	private NotOnlyWhiteSpaceConstraint notOnlyWhiteSpaceConstraint;
	
	@Mock
	private ConstraintValidatorContext context;
	
	/**
	 * Test method for {@link com.radford.aba.shared.validator.NotOnlyWhiteSpaceValidator#isValid(java.lang.String, javax.validation.ConstraintValidatorContext)}.
	 */
	@Test
	void isValidReturnsTrueForStringWithNonWhitespaceCharacters() {
		// Arrange
		NotOnlyWhiteSpaceValidator notOnlyWhiteSpaceValidator = new NotOnlyWhiteSpaceValidator();
		notOnlyWhiteSpaceValidator.initialize(notOnlyWhiteSpaceConstraint);
				
		// Act
		boolean result = notOnlyWhiteSpaceValidator.isValid("MVP", context);
		
		// Assert
		assertTrue(result);
	}
	
	/**
	 * Test method for {@link com.radford.aba.shared.validator.NotOnlyWhiteSpaceValidator#isValid(java.lang.String, javax.validation.ConstraintValidatorContext)}.
	 */
	@Test
	void isValidReturnsTrueForStringWithTextSurroundedBySpaces() {
		// Arrange
		NotOnlyWhiteSpaceValidator notOnlyWhiteSpaceValidator = new NotOnlyWhiteSpaceValidator();
		notOnlyWhiteSpaceValidator.initialize(notOnlyWhiteSpaceConstraint);
				
		// Act
		boolean result = notOnlyWhiteSpaceValidator.isValid("   MVP   ", context);
		
		// Assert
		assertTrue(result);
	}
	
	/**
	 * Test method for {@link com.radford.aba.shared.validator.NotOnlyWhiteSpaceValidator#isValid(java.lang.String, javax.validation.ConstraintValidatorContext)}.
	 */
	@Test
	void isValidReturnsFalseForNull() {
		// Arrange
		NotOnlyWhiteSpaceValidator notOnlyWhiteSpaceValidator = new NotOnlyWhiteSpaceValidator();
		notOnlyWhiteSpaceValidator.initialize(notOnlyWhiteSpaceConstraint);
				
		// Act
		boolean result = notOnlyWhiteSpaceValidator.isValid(null, context);
		
		// Assert
		assertFalse(result);
	}
	
	/**
	 * Test method for {@link com.radford.aba.shared.validator.NotOnlyWhiteSpaceValidator#isValid(java.lang.String, javax.validation.ConstraintValidatorContext)}.
	 */
	@Test
	void isValidReturnsFalseForEmptyString() {
		// Arrange
		NotOnlyWhiteSpaceValidator notOnlyWhiteSpaceValidator = new NotOnlyWhiteSpaceValidator();
		notOnlyWhiteSpaceValidator.initialize(notOnlyWhiteSpaceConstraint);
				
		// Act
		boolean result = notOnlyWhiteSpaceValidator.isValid("", context);
		
		// Assert
		assertFalse(result);
	}
	
	/**
	 * Test method for {@link com.radford.aba.shared.validator.NotOnlyWhiteSpaceValidator#isValid(java.lang.String, javax.validation.ConstraintValidatorContext)}.
	 */
	@Test
	void isValidReturnsFalseForStringOfOnlyWhiteSpace() {
		// Arrange
		NotOnlyWhiteSpaceValidator notOnlyWhiteSpaceValidator = new NotOnlyWhiteSpaceValidator();
		notOnlyWhiteSpaceValidator.initialize(notOnlyWhiteSpaceConstraint);
				
		// Act
		boolean result = notOnlyWhiteSpaceValidator.isValid("    ", context);
		
		// Assert
		assertFalse(result);
	}
	
	/**
	 * Test method for {@link com.radford.aba.shared.validator.NotOnlyWhiteSpaceValidator#isValid(java.lang.String, javax.validation.ConstraintValidatorContext)}.
	 */
	@Test
	void isValidReturnsFalseForStringOfOnlyTab() {
		// Arrange
		NotOnlyWhiteSpaceValidator notOnlyWhiteSpaceValidator = new NotOnlyWhiteSpaceValidator();
		notOnlyWhiteSpaceValidator.initialize(notOnlyWhiteSpaceConstraint);
				
		// Act
		boolean result = notOnlyWhiteSpaceValidator.isValid("\t", context);
		
		// Assert
		assertFalse(result);
	}
	
	/**
	 * Test method for {@link com.radford.aba.shared.validator.NotOnlyWhiteSpaceValidator#isValid(java.lang.String, javax.validation.ConstraintValidatorContext)}.
	 */
	@Test
	void isValidReturnsFalseForStringOfOnlyNewLine() {
		// Arrange
		NotOnlyWhiteSpaceValidator notOnlyWhiteSpaceValidator = new NotOnlyWhiteSpaceValidator();
		notOnlyWhiteSpaceValidator.initialize(notOnlyWhiteSpaceConstraint);
				
		// Act
		boolean result = notOnlyWhiteSpaceValidator.isValid("\n", context);
		
		// Assert
		assertFalse(result);
	}

}
