package com.spring.school.models;

import javax.persistence.*;

@Entity
@Table(name = "classroom")
public class Classroom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String class_name;
	@OneToOne(mappedBy = "classroom")
	private Lesson lesson;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

}
