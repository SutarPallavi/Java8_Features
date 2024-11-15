package com.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OprationsOnIntArray {

	public static void main(String[] args) {
	
		int [] arr1 = {1,3,2,5,4};
        int [] arr2 = {6, 8, 0, 7, 9, 1, 2,3, 3,6,8};
        
        //merge two unsorted arrays into one sorted order
        int sortedArray [] =IntStream.concat(IntStream.of(arr1), IntStream.of(arr2)).sorted().toArray();
        System.out.println(Arrays.toString(sortedArray));
        //reverse an integer array
        System.out.println("Reverse an integer array: ");
        System.out.println(Arrays.toString(IntStream.rangeClosed(1, arr1.length).map(i -> arr1[arr1.length-i]).toArray()));
        //Common elements between two arrays
        System.out.println("Common elements between two arrays: ");
        List<Integer> arrList1 = Arrays.stream(arr1).boxed().collect(Collectors.toList());
        List<Integer> arrList2 = Arrays.stream(arr2).boxed().collect(Collectors.toList());
        arrList1.stream().filter(arrList2 :: contains).forEach(System.out::println);
        //Frequency of each element in the array
        System.out.println("Frequency of each element in the array");
        arrList2.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .forEach((k, v) -> System.out.println(k+":"+v+" "));
        //Merge two arrays and sort it and print distinct elements
        System.out.println("Print distinct elements");
        int distinctelemnts[] = IntStream.concat(IntStream.of(arr1), IntStream.of(arr2)).sorted().distinct().toArray();
        System.out.println(Arrays.toString(distinctelemnts));
        //print odd and even elements
        System.out.println("odd and even " + Arrays.stream(distinctelemnts).boxed().collect(Collectors.partitioningBy(i -> i%2==0)));
        
        int nums[] = {10,20,5,2,9,3,4};
        int targetSum = 7;
        System.out.println("find out two elements which return targate sum value: " + targetSum);
        twoSum(nums, targetSum);
        
        int[] arrayNum = {2,0,5,0,8};
        System.out.println("push zeros to end: "+ Arrays.toString(arrayNum));
        pushZerosToEnd(arrayNum);
   	 	System.out.println(Arrays.toString(arrayNum));
   	 
        System.out.print("Print avg ");
        List<Integer> numList = Arrays.asList(1,2,4,3,7,5,6,9,8);
        numList.stream().mapToInt(i->i).average().ifPresent(avg -> System.out.println("Avg "+ avg));
        
        //print 2nd highest element 
        List<Integer> intList = Arrays.asList(4,2,1,6,3,5,9,8,10,7);
        int n = intList.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
        System.out.println(n);
	}

	private static void pushZerosToEnd(int[] arrayNum) {
		int n = arrayNum.length;
		int[] temp = new int[n];
		int j=0;
		for(int i=0; i<n; i++) {
			if(arrayNum[i]!=0) {
				temp[j++]= arrayNum[i];
			}
		}
		while(j<n)
			temp[j++]=0;
		for(int i=0; i<n; i++) {
			arrayNum[i] = temp[i];
		}
		
	}

	private static void twoSum(int[] nums, int targetSum) {
		Set<Integer> unique = new HashSet<>();
		for(int a: nums) {
			int b = targetSum-a;
			if(unique.contains(b)) {
				System.out.println(a + " "+b);
			}
			unique.add(a);
		}
	}

}
