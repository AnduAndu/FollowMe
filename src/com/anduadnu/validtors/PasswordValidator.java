package com.anduadnu.validtors;

import java.util.regex.Pattern;

import com.anduadnu.constraints.PasswordConstraints;


public class PasswordValidator implements FieldValidator{

	@Override
	public boolean validate(String stringToBeValidated) {
		
		for(PasswordConstraints passwordConstarint : PasswordConstraints.values()) {
			if(!validateConstraint(stringToBeValidated, passwordConstarint))
				return false;
		}
		
		return true;
	}

	private boolean validateConstraint(String stringToBeValidated, PasswordConstraints passwordConstarint) {
		
		switch (passwordConstarint.getConstarintCode()) {
		case 1:
			return validatePassswordMinLength(stringToBeValidated, passwordConstarint);
		case 2:
			return validatePassswordMaxLength(stringToBeValidated, passwordConstarint);
		case 3:
			return validateUpperCase(stringToBeValidated, passwordConstarint);
		case 4:
			return validateLowerCase(stringToBeValidated, passwordConstarint);
		case 5:
			return validateNumber(stringToBeValidated, passwordConstarint);
		case 6:
			return validateSpecialCharacter(stringToBeValidated, passwordConstarint);
		default:
			return false;
		}
	}
	
	private boolean validateNumber(String stringToBeValidated,
			PasswordConstraints passwordConstarint) {
		if(passwordConstarint.getConstraint().equalsIgnoreCase("true")) {
			return stringToBeValidated.matches(".*[0-9].*");
		}
		return true;
	}

	private boolean validateSpecialCharacter(String stringToBeValidated,
			PasswordConstraints passwordConstarint) {
		if(passwordConstarint.getConstraint().equalsIgnoreCase("true")) {
			String special = "!@#$%^&*()_";
			String pattern = ".*[" + Pattern.quote(special) + "].*";
			return stringToBeValidated.matches(pattern);
		}
		return true;
	}

	private boolean validateLowerCase(String stringToBeValidated,
			PasswordConstraints passwordConstarint) {
		if(passwordConstarint.getConstraint().equalsIgnoreCase("true")) {
			return stringToBeValidated.matches(".*[a-z].*");
		}
		return true;
	}

	private boolean validateUpperCase(String stringToBeValidated,
			PasswordConstraints passwordConstarint) {
		
		if(passwordConstarint.getConstraint().equalsIgnoreCase("true")) {
			return stringToBeValidated.matches(".*[A-Z].*");
		}
		return true;
		
	}

	private boolean validatePassswordMaxLength(String stringToBeValidated,
			PasswordConstraints passwordConstarint) {
		if(Integer.parseInt(passwordConstarint.getConstraint()) > 0)
			return stringToBeValidated.length() <= Integer.parseInt(passwordConstarint.getConstraint());
		return true;
	}

	private boolean validatePassswordMinLength(
			String stringToBeValidated, PasswordConstraints passwordConstarint) {
		if(Integer.parseInt(passwordConstarint.getConstraint()) > 0)
			return stringToBeValidated.length() >= Integer.parseInt(passwordConstarint.getConstraint());
		return true;
		
	}

}
