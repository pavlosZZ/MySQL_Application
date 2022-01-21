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
import com.spring.school.DTO.ProfessorDTO;
import com.spring.school.controllers.ProfessorController;
import com.spring.school.models.Professor;
import com.spring.school.services.ClassroomService;
import com.spring.school.services.LessonService;
import com.spring.school.services.ProfessorService;
import com.spring.school.services.StudentService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProfessorController.class)
public class ProfessorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LessonService lessonService;

	@MockBean
	private ClassroomService serv;
	@MockBean
	private StudentService stud;
	@MockBean
	private ProfessorService professorService;

	ObjectMapper mapper = new ObjectMapper();
	ModelMapper modelMapper = new ModelMapper();
	private Professor prof = new Professor();
	private ProfessorDTO professorDTO = new ProfessorDTO();
	private List<Professor> professors = new ArrayList<>();

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new ProfessorController(professorService, modelMapper)).build();

		prof.setAge(0);
		prof.setFirstName("Pavlos");
		prof.setId(2);
		prof.setLastName("Zotos");
		prof.setEmail(null);
		prof.setExpertization(null);
		prof.setStudents(null);

		professors.add(prof);

		professorDTO.setFirstName("Pavlos");
		professorDTO.setLastName("Zotos");
		professorDTO.setExpertization(null);
		professorDTO.setStudents(null);

	}

	@Test
	public void getAllProfessors() throws Exception {

		when(professorService.getAllProfessors()).thenReturn(professors);

		mockMvc.perform(get("/api/v1/professor")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("Pavlos"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Zotos"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(0));

	}

	@Test
	public void getProfessorById() throws Exception {

		when(professorService.getProfessorById(2)).thenReturn(prof);

		mockMvc.perform(get("/api/v1/professor/2")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Pavlos"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Zotos"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.age").value(0));
	}

	@Test
	public void addNewProfessorTest() throws Exception {

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(prof);

		when(professorService.addNewProfessor(ArgumentMatchers.any())).thenReturn(prof);

		mockMvc.perform(post("/api/v1/professor").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}

	@Test
	public void updateProfessor() throws Exception {

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(prof);

		when(professorService.updateProfessor(prof, 2)).thenReturn(prof);

		mockMvc.perform(put("/api/v1/professor/2").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());

	}

	@Test
	public void deleteProfessor() throws Exception {
		this.mockMvc.perform(delete("/api/v1/professor/1")).andExpect(status().isOk());
	}

	@Test
	public void getProfessorsDTO() throws Exception {

		mockMvc.perform(get("/api/v1/professor/DTO").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void getProfessorDTObyId() throws Exception {
		mockMvc.perform(get("/api/v1/professor/DTO/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void addNewProfessorDTO() throws Exception {

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(prof);

		when(professorService.addNewProfessor(ArgumentMatchers.any())).thenReturn(prof);

		mockMvc.perform(post("/api/v1/professor/DTO").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

	}

	@Test
	public void updateProfessorDTO() throws Exception {

		mockMvc.perform(put("/api/v1/professor/DTO/1").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(professorDTO))).andExpect(status().isOk());
	}

}
