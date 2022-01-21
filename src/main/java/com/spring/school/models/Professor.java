package com.spring.school.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "professor")
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "name", length = 255)
	private String firstName;
	@Column(name = "surname", length = 255)
	private String lastName;
	@Column(name = "expertization", length = 255)
	private String expertization;
	@Column(name = "email", length = 255)
	private String email;
	@Column(name = "age")
	private int age;
	@ManyToMany(mappedBy = "professors")
	@JsonBackReference
	private Set<Student> students = new HashSet<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getExpertization() {
		return expertization;
	}

	public void setExpertization(String expertization) {
		this.expertization = expertization;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}
