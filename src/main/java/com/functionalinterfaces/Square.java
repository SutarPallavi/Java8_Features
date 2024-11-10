package com.functionalinterfaces;

@FunctionalInterface
interface Square {
	int calculate(int n);
	
	static int duble(int n) {
		return n*2;
	}
	
	default String message () {
		return "Square: This is default method";
	}
	
	default String type() {
		return "Basic calculation";
	}
}
