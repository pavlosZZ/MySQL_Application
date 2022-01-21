package com.spring.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.spring.school.controllers.ClassroomController;
import com.spring.school.models.Classroom;
import com.spring.school.services.ClassroomService;
import com.spring.school.services.LessonService;
import com.spring.school.services.ProfessorService;
import com.spring.school.services.StudentService;

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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClassroomController.class)
@RunWith(SpringRunner.class)
public class ClassroomControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentService studentService;

	@MockBean
	private ClassroomService classroomService;
	@MockBean
	private ProfessorService profe;
	@MockBean
	private LessonService less;

	private Classroom classroom = new Classroom();
	private List<Classroom> classrooms = new ArrayList<>();

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new ClassroomController(classroomService)).build();

		classroom.setId(1);
		classroom.setClass_name("Maths");

		classrooms.add(classroom);

	}

	@Test
	public void getClassrooms() throws Exception {

		when(classroomService.getAllClassrooms()).thenReturn(classrooms);

		mockMvc.perform(get("/api/v1/classroom")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].class_name").value("Maths"));

	}

	@Test
	public void addNewClassroomTest() throws Exception {

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(classroom);

		when(classroomService.addNewClassroom(ArgumentMatchers.any())).thenReturn(classroom);

		this.mockMvc.perform(post("/api/v1/classroom").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void deleteClassroom() throws Exception {
		this.mockMvc.perform(delete("/api/v1/classroom/1")).andExpect(status().isOk());
	}

}
