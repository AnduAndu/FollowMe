package com.anduadnu.constraints;

public enum PasswordConstraints {

	
	MIN_LENGTH(1,"5"),
	MAX_LENGTH(2,"10"),
	CONTAINS_CAPITAL(3,"true"),
	CONTAINS_SMALL(4,"true"),
	CONTAINS_NUMBERS(5,"true"),
	CONTAINS_SPECIAL_CHARACTER(6,"true");
	
	private int constarintCode;
	private String constraint;
	
	private PasswordConstraints(int constraintCode,String constraint) {
		this.setConstarintCode(constraintCode);
		this.setConstraint(constraint);
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

	public void setConstarintCode(int constarintCode) {
		this.constarintCode = constarintCode;
	}

	public int getConstarintCode() {
		return constarintCode;
	}

	public String getConstraint() {
		return constraint;
	}
	
	
	
	
}
