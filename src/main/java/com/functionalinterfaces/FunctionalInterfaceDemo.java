package com.functionalinterfaces;

public class FunctionalInterfaceDemo implements Square, Calculations {

	public static void main(String[] args) {
		FunctionalInterfaceDemo fun = new FunctionalInterfaceDemo();
		int a =5; 
		
		Square s = (int x) -> x*x;		
		int ans = s.calculate(a);
		System.out.println("Abstarct method returns: " + ans);
		String str = s.message();
		System.out.println("Deafult method returns: " + str);
		int dubleValue = Square.duble(a);
		System.out.println("Static method returns: " + dubleValue);
		System.out.println(fun.message());

	}

	@Override
	public int triple(int n) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int calculate(int n) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String message() {
		// TODO Auto-generated method stub
		return Calculations.super.message() + " "+ Square.super.message();
	}

}
