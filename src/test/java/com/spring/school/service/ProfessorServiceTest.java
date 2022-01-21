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

import com.spring.school.models.Professor;
import com.spring.school.services.ProfessorService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ProfessorServiceTest {

	@Autowired
	private ProfessorService professorService;

	@Test
	@Order(1)
	public void testCreate() throws Exception {
		Professor p = new Professor();
		p.setId(1);
		p.setFirstName("Pavlos");
		p.setLastName("Zotos");
		p.setAge(25);
		p.setEmail(null);
		p.setExpertization(null);
		p.setStudents(null);
		professorService.addNewProfessor(p);
		System.out.println("1");
		assertNotNull(professorService.getProfessorById(1L));
	}

	@Test
	@Order(2)
	public void testReadAll() {
		List<Professor> professors = professorService.getAllProfessors();
		System.out.println("2");
		assertThat(professors).size().isGreaterThan(0);
	}

	@Test
	@Order(3)
	public void testRead() throws Exception {
		Professor professor = professorService.getProfessorById(1L);
		System.out.println("3");
		assertEquals("Pavlos", professor.getFirstName());
	}

	@Test
	@Order(4)
	public void testUpdate() throws Exception {
		Professor p = professorService.getProfessorById(1L);
		p.setAge(35);
		professorService.addNewProfessor(p);
		System.out.println("4");
		assertNotEquals(25, professorService.getProfessorById(1L).getAge());
	}

}
