package com.functionalinterfaces;

@FunctionalInterface
public interface Calculations {

	int triple (int n);
	default String message () {
		return "Calculation: This is default method";
	}
}
