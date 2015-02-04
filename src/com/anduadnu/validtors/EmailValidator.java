package com.anduadnu.validtors;

public class EmailValidator implements FieldValidator{

	private static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	@Override
	public boolean validate(String stringToBeValidated) {
		return stringToBeValidated.matches(EMAIL_REGEX);
	}

}
