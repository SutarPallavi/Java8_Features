package com.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OperationsOnUserList {

	public static void main(String[] args) {
		int[] arr1 = {1,7,4,9,0};
		int[] arr2 = {2,8,9,3,2,1};
		int[] array = IntStream.concat(IntStream.of(arr1), IntStream.of(arr2))
				.sorted().distinct().toArray();
		System.out.println(Arrays.toString(array));
		
		List<User> userList = new ArrayList<>();
		userList.add(new User(2, "pallavi", "Pune"));
		userList.add(new User(4, "pallavi", "Pune"));
		userList.add(new User(1, "sutar", "pune"));
		userList.add(new User(5, "sutar", "Pune"));
		userList.add(new User(3, "sutar", "Pune"));
		userList.add(new User(6, "pallavi", "Pune"));
		
		List<User> reverseOrder = userList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println("Reverse order: " + reverseOrder);
		List<User> sortedList = userList.stream().sorted(Comparator.comparing(User :: getId).thenComparing(User :: getName))
		.collect(Collectors.toList());
		System.out.println(sortedList);
		
		//grouping by name
		userList.stream().collect(Collectors.groupingBy(u -> u.getName()))
		.forEach((k,v) -> System.out.println(k+": "+ v+" "));
		
		//grouping by name and sorting in descending order
		userList.stream().collect(Collectors.groupingBy(u -> u.getName()))
		.forEach((k,v) -> System.out.println(k+": "+ v+" "));;
		//id is greater than 1
		userList.stream().filter(u -> u.getId()%2==0).forEach(System.out::print);;
		System.out.println();
		//maximum id of user
		User user1 = userList.stream().max(Comparator.comparing(User::getId)).get();
		System.out.println(user1);
		
	}
}
