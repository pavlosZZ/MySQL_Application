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

import com.spring.school.models.Lesson;
import com.spring.school.services.LessonService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class LessonServiceTest {

	@Autowired
	private LessonService lessonService;

	@Test
	@Order(1)
	public void testCreate() throws Exception {
		Lesson p = new Lesson();
		p.setId(1);
		p.setTitle("Maths");
		p.setClassroom(null);
		lessonService.addNewLesson(p);
		System.out.println("1");
		assertNotNull(lessonService.getLessonById(1L));
	}

	@Test
	@Order(2)
	public void testReadAll() {
		List<Lesson> lessons = lessonService.getAllLessons();
		System.out.println("2");
		assertThat(lessons).size().isGreaterThan(0);
	}

	@Test
	@Order(3)
	public void testRead() throws Exception {
		Lesson lesson = lessonService.getLessonById(1L);
		System.out.println("3");
		assertEquals("Maths", lesson.getTitle());
	}

	@Test
	@Order(4)
	public void testUpdate() throws Exception {
		Lesson p = lessonService.getLessonById(1L);
		p.setTitle("Physics");
		lessonService.addNewLesson(p);
		System.out.println("4");
		assertNotEquals("Math", lessonService.getLessonById(1L).getTitle());
	}
}
