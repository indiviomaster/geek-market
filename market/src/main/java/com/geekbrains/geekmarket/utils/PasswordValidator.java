package com.geekbrains.geekmarket.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
	private Pattern pattern;
	private Matcher matcher;
	private static final String PASSWORD_PATTERN = "^\\d{3}$";
	@Override
	public boolean isValid(final String password, final ConstraintValidatorContext context) {
		pattern = Pattern.compile(PASSWORD_PATTERN);
		if (password == null) {
			return false;
		}
		matcher = pattern.matcher(password);
		return matcher.matches();
	}
}