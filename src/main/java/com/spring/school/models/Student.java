package com.spring.school.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "name", nullable = false, length = 255)
	private String firstName;
	@Column(name = "surname", nullable = false, length = 255)
	private String lastName;
	@Column(name = "age")
	private int Age;
	@ManyToMany
	@JoinTable(name = "students_professors", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "professor_id"))
	private List<Professor> professors = new ArrayList<Professor>();
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "lesson_id", referencedColumnName = "id")
	private Lesson lesson;

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

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public List<Professor> getProfessors() {
		return professors;
	}

	public void setProfessors(List<Professor> professors) {
		this.professors = professors;
	}

	public void enrolledProfessor(Professor professor) {
		professors.add(professor);

	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

}
