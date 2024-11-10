package com.streams;

import java.util.function.Function;
import java.util.stream.Collectors;

public class OperationsonString {

	public static void main(String[] args) {
		String str = "Pallavi";
		//print frequency of each char
		System.out.println("Frequency of each char");
		str.chars().mapToObj(s -> (char)s)
		.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
		.forEach((k,v) -> System.out.print(k+ ": "+v+" "));
		System.out.println("Print repeating chars");
		str.chars().mapToObj(s -> (char)s)
		.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
		.entrySet().stream().filter(m-> m.getValue()>1)
		.forEach(System.out::println);
		
		//basic string questions
		String s ="Hi";
		String s1 = new String("Hi");
		String s2 = s1;
		String s3 = s1.intern();
		String s4 = "Hi";
		System.out.println(s==s1);  //1-
		System.out.println(s==s3); //2-
		System.out.println(s.equals(s1)); //3-
		System.out.println(s1.equals(s3)); //4-
	}

}
