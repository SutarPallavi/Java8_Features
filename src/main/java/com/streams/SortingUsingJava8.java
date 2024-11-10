package com.streams;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SortingUsingJava8 {

	public static void main(String [] args) {
	
		User u1 = new User(1, "sutar", "pune");
		User u2 = new User(2, "pallavi", "Pune");
		User u3 = new User(3, "sutar", "Pune");
		User u4 = new User(4, "pallavi", "Pune");
		User u5 = new User(5, "sutar", "Pune");
		User u6 = new User(6, "pallavi", "Pune");
		
		List<User> userList = new ArrayList();
		userList.add(u1);
		userList.add(u2);
		userList.add(u3);
		userList.add(u4);
		userList.add(u5);
		userList.add(u6);
		System.out.println(userList);
		List<User> userList2 = userList.stream().sorted(Comparator.comparing(User :: getName )).collect(Collectors.toList());
		System.out.println(userList2);
		
		List<User> userList3 = userList.stream().sorted().collect(Collectors.toList());
		System.out.println(userList3);
		
		List<User> userList4 = userList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println(userList4);
		
		List<Integer> numList = Arrays.asList(2,4,5,3,6,7,8);
		int n = numList.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
		System.out.println(n);
		
		
		Optional<Integer> num = numList.stream().reduce((n1, n2) -> n1>n2 ? n1: n2);
		
		System.out.println(num.get());
		
		//This is actually where it gets interesting. If you look into the Integer.java class , 
		//you will find that there is an inner private class, IntegerCache.java that caches all 
		//Integer objects between -128 and 127.
		    Integer a = 1000, b = 1000;
	        System.out.println(a == b);
	        Integer c = 100, d = 100;
	        System.out.println(c == d);
	        
	        
	        // only one string object will be created
	        for(int i=0; i<10; i++) {
	        	String s = new String("abc"); // new String with create 2 objects one in constant pool and one
	        	System.out.println (s.hashCode());
	        }
	        System.out.println("---");
	        for(int i=0; i<10; i++) {
	        	String s = "abc";// will check if "abc" value present in constant pool or not, 
	        					//in this it is there it will not create new object
	        	System.out.println (s.hashCode());
	        }
	        
	        Map<String, List<User>> userMap = userList.stream().collect(Collectors.groupingBy(User :: getName, Collectors.toList()));
	        userMap.forEach((x, y) -> System.out.println(x + "= " + y));
	        //new questions
	        List<Integer> list =Arrays.asList(1, 1, 2, 3, 3, 4);
	        System.out.println(list.stream().distinct().collect(Collectors.toList()));
	        System.out.println(
	        		list.stream()
	        		.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
	        		.entrySet().stream()
	        		.filter(e -> e.getValue()>1)
	        		.map(i -> i.getKey())
	        		.collect(Collectors.toList()));
	        
	        List<Integer> nlist = Arrays.asList(1,2,3,4,5,6);
	        System.out.println("odd and even " + nlist.stream().collect(Collectors.partitioningBy(i -> i % 2 ==0)));
	        
	        System.out.println("Strings______");
	        String s = "Pallavi";
	        System.out.println(s.chars().mapToObj(str -> (char)str)
	        		.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
	        System.out.println(s.chars().mapToObj(ch -> (char)ch)
	        		.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
	        		.entrySet().stream().filter(e -> e.getValue()>1).map(i-> i.getKey()).collect(Collectors.toList()));
	        System.out.println(s.chars().mapToObj(str -> (char)str)
	        		.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
	        		.entrySet().stream().filter(e -> e.getValue()<2).map(i -> i.getKey()).collect(Collectors.toList()));
	        System.out.println("disting characters from a string");
	        System.out.println(s.chars().mapToObj(str ->(char)str).distinct().collect(Collectors.toList()));
	        
	        //sorting
	        List<Integer> lList = Arrays.asList(3,1,4,2,7,5,9,6,8,10);
	        System.out.println("sort in reverse order: "); 
	        lList.stream().sorted(Comparator.reverseOrder()).forEach(System.out:: print);
	        System.out.println();
	        System.out.println("Print multiples of 5 ");
	        lList.stream().sorted().filter(i -> i%5 ==0).forEach(System.out:: print);
	        //minimum 2 number 
	        System.out.println("minimum 2 numbers: ");
	        lList.stream().sorted().limit(2).forEach(System.out::print);
	       System.out.println("maximum 2 number: ");
	       lList.stream().sorted(Comparator.reverseOrder()).limit(2).forEach(System.out::print);
	       System.out.println();
	       System.out.println("2nd hightest number " + lList.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get());
	     //max and min from ,list
	       System.out.println("max " + lList.stream().max(Comparator.naturalOrder()).get());
	       System.out.println("min "+ lList.stream().min(Comparator.naturalOrder()).get());
	       System.out.println();
	       lList.stream().sorted().collect(Collectors.partitioningBy(i -> i%3==0)).forEach((k,v) -> System.out.println(k + ":"+ v));
	       System.out.println();
	       //DOB in years
	       LocalDate dob = LocalDate.of(1994, 10, 11);
	       LocalDate today = LocalDate.now();
	       System.out.println(ChronoUnit.YEARS.between(dob, today));
	       
	       //sum of digits in a  number
	       int number = 123456;
	       System.out.println("Sum: " + Stream.of(String.valueOf(number).split("")).collect(Collectors.summingInt(Integer :: parseInt)));
	       
	       // find string starting with digit
	       List<String> strList = Arrays.asList("avcgh", "hbng", "yhujik", "9juhkiik", "kjnmip", "8uikrde");
	       strList.stream().filter(str -> Character.isDigit(str.charAt(0))).forEach(System.out:: println);
	       
	}
}
