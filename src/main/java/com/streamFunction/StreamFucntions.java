package com.streamFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

class User {
	private int id;
	private String name; 
	private String address; 
	private Long salary;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	public User(int id, String name, String address, Long salary) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", address=" + address + ", salary=" + salary + "]";
	}
	
}

class Employee {
	private int id; 
	private String name ; 
	private String address; 
	private List<Integer> taskNumbers;
	public Employee(int id, String name, String address, List<Integer> taskNumbers) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.taskNumbers = taskNumbers;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Integer> getTasks() {
		return taskNumbers;
	}
	public void setTasks(List<Integer> tasks) {
		this.taskNumbers = tasks;
	} 
	
}
public class StreamFucntions {

	public static void main(String[] args) {
		List<User> userList = new ArrayList<>();
		userList.add(new User(1, "Pallavi", "Pune", 2000000l));
		userList.add(new User(2, "Ankit", "Mumbai", 1000000l));
		userList.add(new User(3, "Lataa", "Nagpur", 200000l)); 
		
		Optional<User> user = userList.stream().filter(u -> u.getName().equals("Pallavi")).findFirst();		
		System.out.println(".equals(\"Pallavi\") method: " + user) ;
		
		List<String> userNameList = userList.stream().map(u -> u.getName()).collect(Collectors.toList());
		System.out.println(".map(u -> u.getName()) get usernamelist : " + userNameList);
		
		Optional<User> user1 = userList.stream().filter(u -> u.getId()> 1 && u.getSalary()> 20000l).findFirst();
		System.out.println(".filter(u -> u.getId()> 1 && u.getSalary()> 2000000l).findFirst()  " + user1);
		
		List<Employee> employeeList = new ArrayList<>(); 
		List<Integer> list1 = Arrays.asList(1, 2, 3, 5, 4, 8); 
		employeeList.add(new Employee(1, "Pallavi", "Pune", list1));
		List<Integer> list2 = Arrays.asList(1, 3, 4, 8, 7, 9);
		employeeList.add(new Employee(2, "Ankit", "Pune", list2));
		List<Integer> list3 = Arrays.asList( 3, 4, 7, 9, 2, 10);
		employeeList.add(new Employee(3, "Sayali", "Pune", list3));
		List<Integer> list4 = Arrays.asList(1, 8, 10, 6, 5);
		employeeList.add(new Employee(4, "Shubham", "Pune", list4));
		
		Set<Integer> tasks= employeeList.stream().flatMap(emp -> emp.getTasks().stream().distinct()).collect(Collectors.toSet());
		System.out.println(".flatMap(emp -> emp.getTasks().stream().distinct()) method result: " + tasks);
		
		employeeList.parallelStream().forEach(
				emp -> System.out.println(emp + ": " + Thread.currentThread().getName()));
		
		int sum =  employeeList.parallelStream().flatMap(emp -> emp.getTasks().stream().distinct()).reduce(0, Integer :: sum); 
		System.out.println("Sum of tasks assisgned: " + sum);
		
		Long salarySum = userList.stream().mapToLong(u -> u.getSalary()).sum();
		System.out.println(salarySum);
	}

}
