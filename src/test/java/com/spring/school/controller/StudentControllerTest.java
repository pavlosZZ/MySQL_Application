package com.spring.school.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.modelmapper.ModelMapper;
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
import com.spring.school.controllers.StudentController;
import com.spring.school.models.Student;
import com.spring.school.services.ClassroomService;
import com.spring.school.services.LessonService;
import com.spring.school.services.ProfessorService;
import com.spring.school.services.StudentService;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentService studentService;

	@MockBean
	private ClassroomService classroomService;

	@MockBean
	private LessonService less;

	@MockBean
	private ProfessorService prof;

	private ModelMapper modelMapper = new ModelMapper();
	private Student stud = new Student();
	List<Student> students = new ArrayList<>();

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new StudentController(studentService, modelMapper)).build();

		stud.setAge(0);
		stud.setFirstName("Pavlos");
		stud.setId(2);
		stud.setLastName("Zotos");
		stud.setLesson(null);
		stud.setProfessors(null);
		students.add(stud);

	}

	@Test
	public void addNewStudentTest() throws Exception {

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(stud);

		when(studentService.addNewStudent(ArgumentMatchers.any())).thenReturn(stud);

		mockMvc.perform(post("/api/v1/student").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}

	@Test
	public void getAllStudents() throws Exception {

		when(studentService.getAllStudents()).thenReturn(students);

		mockMvc.perform(get("/api/v1/student")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("Pavlos"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Zotos"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(0));

	}

	@Test
	public void getStudentById() throws Exception {

		when(studentService.getStudentById(2)).thenReturn(stud);

		this.mockMvc.perform(get("/api/v1/student/2")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Pavlos"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Zotos"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.age").value(0));
	}

	@Test
	public void updateStudents() throws Exception {

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(stud);

		when(studentService.updateStudent(stud, 2)).thenReturn(stud);

		mockMvc.perform(put("/api/v1/student/2").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());

	}

	@Test
	public void deleteStudent() throws Exception {
		mockMvc.perform(delete("/api/v1/student/1")).andExpect(status().isOk());

	}

	@Test
	public void updateProfessorList() throws Exception {

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(stud);

		when(studentService.updateProfessorsList(2, 1)).thenReturn(stud);

		mockMvc.perform(put("/api/v1/student/2/professor/1").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}

	@Test
	public void updateStudentsLesson() throws Exception {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(stud);

		when(studentService.updateStudentsLesson(2, 1)).thenReturn(stud);

		mockMvc.perform(put("/api/v1/student/2/lesson/1").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}

	@Test
	public void getAllStudentsDTO() throws Exception {
		when(studentService.getAllStudents()).thenReturn(students);
		mockMvc.perform(get("/api/v1/student/DTO")).andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("Pavlos"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Zotos"));

	}

	@Test
	public void getStudentDTOById() throws Exception {
		when(studentService.getStudentById(2)).thenReturn(stud);

		mockMvc.perform(get("/api/v1/student/DTO/2"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Pavlos"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Zotos"));
	}

	@Test
	public void addNewStudentDTO() throws Exception {

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(stud);

		when(studentService.addNewStudent(ArgumentMatchers.any())).thenReturn(stud);

		mockMvc.perform(post("/api/v1/student/DTO").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}

	@Test
	public void updateStudentDTO() throws Exception {

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(stud);

		when(studentService.updateStudent(stud, 2)).thenReturn(stud);

		mockMvc.perform(put("/api/v1/student/DTO/2").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}
}
