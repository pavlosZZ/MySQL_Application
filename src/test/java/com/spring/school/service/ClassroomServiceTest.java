package com.spring.school.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.school.models.Classroom;
import com.spring.school.services.ClassroomService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ClassroomServiceTest {

	@Autowired
	private ClassroomService classroomService;

	@Test
	@Order(1)
	public void testCreate() throws Exception {
		Classroom p = new Classroom();
		p.setId(1);
		p.setClass_name("Gym");
		classroomService.addNewClassroom(p);
		System.out.println("1");
		assertNotNull(classroomService.getClassroomById(1L));
	}

	@Test
	@Order(2)
	public void testReadAll() {
		List<Classroom> classrooms = classroomService.getAllClassrooms();
		System.out.println("2");
		assertThat(classrooms).size().isGreaterThan(0);
	}

	@Test
	@Order(3)
	public void testRead() throws Exception {
		Classroom classroom = classroomService.getClassroomById(1L);
		System.out.println("3");
		assertEquals("Gym", classroom.getClass_name());
	}

	@Test
	@Order(4)
	public void testUpdate() throws Exception {
		Classroom p = classroomService.getClassroomById(1L);
		p.setClass_name("Laboratory");
		classroomService.addNewClassroom(p);
		System.out.println("4");
		assertNotEquals(10, classroomService.getClassroomById(1L).getClass_name());
	}
}
