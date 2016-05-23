package functions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by jsimone on 5/22/16.
 */
public class Employee implements Comparable {

	private String name;
	private Integer age;
	private Dept dept;
	private LocalDate hireDate;
	private Gender gender;

	public Employee() {
	}
	public Employee(String name, int age, Dept dept, String hireDate, Gender gender) {
		this.name = name;
		this.age = age;
		this.dept = dept;
		this.hireDate = LocalDate.parse(hireDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return name+ " "+age+ " " +dept+" "+hireDate+"  "+gender;
	}

	@Override
	public int compareTo(Object o) {
		return this.getAge().compareTo(((Employee) o).getAge());
	}
}
