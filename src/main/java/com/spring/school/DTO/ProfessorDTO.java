package com.spring.school.DTO;

import java.util.HashSet;
import java.util.Set;

import com.spring.school.models.Student;

import lombok.Data;

@Data
public class ProfessorDTO {

	private String firstName;
	private String lastName;
	private String expertization;
	private Set<Student> students = new HashSet<>();

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

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}
