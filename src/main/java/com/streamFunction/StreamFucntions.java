package com.streamFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Employee {
	private int id; 
	private String name ; 
	private String address;
	private String domain;
	private int salary;
	private List<Integer> taskNumbers;
	public Employee(int id, String name, String address, String domain, int salary, List<Integer> taskNumbers) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.domain = domain;
		this.salary = salary;
		this.taskNumbers = taskNumbers;
	}
	
	
	public String getDomain() {
		return domain;
	}


	public void setDomain(String domain) {
		this.domain = domain;
	}


	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
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

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", address=" + address + ", domain=" + domain + ", salary="
				+ salary + ", taskNumbers=" + taskNumbers + "]";
	}
}
public class StreamFucntions {

	public static void main(String[] args) {
		
		List<Employee> employeeList = new ArrayList<>(); 
		List<Integer> list1 = Arrays.asList(1, 2, 3, 5, 4, 8); 
		employeeList.add(new Employee(1, "Abc", "Pune", "IT", 30000, list1));
		List<Integer> list2 = Arrays.asList(1, 3, 4, 8, 7, 9);
		employeeList.add(new Employee(2, "Def", "Sangli", "HR", 20000, list2));
		List<Integer> list3 = Arrays.asList( 3, 4, 7, 9, 2, 10);
		employeeList.add(new Employee(3, "Jkl", "Pune", "IT", 50000, list3));
		List<Integer> list4 = Arrays.asList(1, 8, 10, 6, 5);
		employeeList.add(new Employee(4, "Ghi", "Sangli", "HR", 40000, list4));
		
		//Get Employees by address
		employeeList.stream().sorted(Comparator.comparing(Employee :: getDomain).thenComparing(Employee :: getName))
		.forEach(System.out:: println);
		
		//Filter employee list by address
		employeeList.stream().filter(e -> e.getAddress().equals("Pune")).forEach(System.out::println);
		//grouping by address
		employeeList.stream().map(e->e).collect(
				Collectors.groupingBy(e -> e.getAddress(), Collectors.toList()))
				.forEach((k, v) -> System.out.println(k+": "+v+" "));
		//sort by comparing name
		employeeList.stream().sorted(Comparator.comparing(Employee :: getName)).forEach(System.out::println);
		
		//find employee with second highest salary 
		Employee emp = employeeList.stream().sorted(Comparator.comparing(Employee :: getSalary).reversed()).skip(1).findFirst().get();
		System.out.println(emp);
		//avg employee salary
		double avg = employeeList.stream().collect(Collectors.averagingDouble(Employee :: getSalary));
		System.out.println(avg);
		//list of employees with salaries greater than >20000
		employeeList.stream().filter(e -> e.getSalary()>20000).forEach(System.out:: println);
		//Get numbers of employees in each domain
		employeeList.stream().collect(Collectors.groupingBy(Employee :: getDomain, Collectors.counting()))
		.entrySet().forEach(System.out::println);
		//get distinct task list
		Set<Integer> tasks= employeeList.stream().flatMap(e -> e.getTasks().stream().distinct()).collect(Collectors.toSet());
		System.out.println(".flatMap(emp -> emp.getTasks().stream().distinct()) method result: " + tasks);
		
		employeeList.parallelStream().forEach(e -> System.out.println(emp + ": " + Thread.currentThread().getName()));
		//sum of tasks assigned 
		int sum =  employeeList.parallelStream().flatMap(e -> e.getTasks().stream().distinct()).reduce(0, Integer :: sum); 
		System.out.println("Sum of tasks assigned: " + sum);
	}

}
