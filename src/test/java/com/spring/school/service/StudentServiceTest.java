package com.spring.school.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.school.models.Student;
import com.spring.school.services.StudentService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class StudentServiceTest {

	@Autowired
	private StudentService studentService;

	@Test
	@Order(1)
	public void testCreate() throws Exception {
		Student p = new Student();
		p.setId(1);
		p.setFirstName("Pavlos");
		p.setLastName("Zotos");
		p.setAge(10);
		p.setLesson(null);
		p.setProfessors(null);
		studentService.addNewStudent(p);
		System.out.println("1");
		assertNotNull(studentService.getStudentById(1L));
	}

	@Test
	@Order(2)
	public void testReadAll() {
		List<Student> students = studentService.getAllStudents();
		System.out.println("2");
		assertThat(students).size().isGreaterThan(0);
	}

	@Test
	@Order(3)
	public void testRead() throws Exception {
		Student student = studentService.getStudentById(1L);
		System.out.println("3");
		assertEquals("Pavlos", student.getFirstName());
	}

	@Test
	@Order(4)
	public void testUpdate() throws Exception {
		Student p = studentService.getStudentById(1L);
		p.setAge(15);
		studentService.addNewStudent(p);
		System.out.println("4");
		assertNotEquals(10, studentService.getStudentById(1L).getAge());
	}

	@Test

	@Order(5)
	public void testDelete() throws Exception {
		studentService.deleteStudent(1L);
		System.out.println("5");
		NumberFormatException thrown = Assertions.assertThrows(NumberFormatException.class, () -> {
			Integer.parseInt("One");
		}, "NumberFormatException was expected");
		Assertions.assertEquals("For input string: \"One\"", thrown.getMessage());
	}

}
