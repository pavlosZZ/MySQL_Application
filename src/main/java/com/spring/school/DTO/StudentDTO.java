package com.spring.school.DTO;

import java.util.ArrayList;
import java.util.List;

import com.spring.school.models.Professor;

import lombok.Data;

@Data
public class StudentDTO {

	private String firstName;
	private String lastName;
	private List<Professor> professors = new ArrayList<Professor>();

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public List<Professor> getProfessors() {
		return professors;
	}

	public void setProfessors(List<Professor> professors) {
		this.professors = professors;
	}

}
