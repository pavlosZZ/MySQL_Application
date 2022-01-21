package com.spring.school.models;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "lesson")
public class Lesson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "classroom_id", unique = true, referencedColumnName = "id")
	private Classroom classroom;
	@OneToMany(mappedBy = "lesson")
	private Set<Student> students;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}
