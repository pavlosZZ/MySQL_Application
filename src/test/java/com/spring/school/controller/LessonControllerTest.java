package com.spring.school.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.spring.school.controllers.LessonController;
import com.spring.school.models.Lesson;
import com.spring.school.services.ClassroomService;
import com.spring.school.services.LessonService;
import com.spring.school.services.ProfessorService;
import com.spring.school.services.StudentService;

@WebMvcTest(LessonController.class)
@RunWith(SpringRunner.class)
public class LessonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LessonService lessonService;

	@MockBean
	private ClassroomService serv;
	@MockBean
	private StudentService stud;
	@MockBean
	private ProfessorService prof;

	private Lesson lesson = new Lesson();
	private List<Lesson> lessons = new ArrayList<>();

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new LessonController(lessonService)).build();

		lesson.setId(1);
		lesson.setTitle("Math");
		lesson.setClassroom(null);
		lesson.setStudents(null);
		lessons.add(lesson);
	}

	@Test
	public void getLessons() throws Exception {

		when(lessonService.getAllLessons()).thenReturn(lessons);

		mockMvc.perform(get("/api/v1/lesson")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Math"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));

	}

	@Test
	public void getLessonByID() throws Exception {

		when(lessonService.getLessonById(1)).thenReturn(lesson);

		mockMvc.perform(get("/api/v1/lesson/1")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Math"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}

	@Test
	public void addNewLessonTest() throws Exception {

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(lesson);

		when(lessonService.addNewLesson(ArgumentMatchers.any())).thenReturn(lesson);

		this.mockMvc.perform(post("/api/v1/lesson").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}

	@Test
	public void deleteLesson() throws Exception {
		this.mockMvc.perform(delete("/api/v1/lesson/1")).andExpect(status().isOk());
	}

	@Test
	public void updateLessonsClassroom() throws Exception {

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(lesson);

		when(lessonService.updateLessonsClassroom(1, 1)).thenReturn(lesson);

		this.mockMvc
				.perform(put("/api/v1/lesson/1/classroom/1").contentType(MediaType.APPLICATION_JSON)
						.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
