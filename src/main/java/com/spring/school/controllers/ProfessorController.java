package com.spring.school.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.school.DTO.ProfessorDTO;
import com.spring.school.models.Professor;
import com.spring.school.services.IProfessorService;

@RestController
@RequestMapping("/api/v1/professor")
public class ProfessorController {

	private IProfessorService professorService;
	private ModelMapper mapper;

	@Autowired
	public ProfessorController(IProfessorService professorService, ModelMapper mapper) {
		super();
		this.professorService = professorService;
		this.mapper = mapper;
	}

	@GetMapping
	public List<Professor> getAllProfessors() {
		return professorService.getAllProfessors();
	}

	@PostMapping
	public ResponseEntity<Professor> addNewProfessor(@RequestBody Professor professor) {
		return new ResponseEntity<Professor>(professorService.addNewProfessor(professor), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Professor> getProfessorById(@PathVariable("id") long id) throws Exception {
		return new ResponseEntity<Professor>(professorService.getProfessorById(id), HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Professor> updateProfessor(@PathVariable("id") long id, @RequestBody Professor professor)
			throws Exception {
		return new ResponseEntity<Professor>(professorService.updateProfessor(professor, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProfessor(@PathVariable("id") long id) throws Exception {
		professorService.deleteProfessor(id);
		return new ResponseEntity<String>("Professor has deleted successfully!", HttpStatus.OK);

	}

	@GetMapping("/DTO")
	@ResponseBody
	public List<ProfessorDTO> getProfessorsDTO() {
		return professorService.getAllProfessors().stream().map(professor -> mapper.map(professor, ProfessorDTO.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/DTO/{id}")
	public ResponseEntity<Professor> getProfessorById(@PathVariable(name = "id") Long id) throws Exception {
		return ResponseEntity.ok().body(professorService.getProfessorById(id));
	}

	@PostMapping("/DTO")
	public ResponseEntity<ProfessorDTO> createProfessorDTO(@RequestBody ProfessorDTO professorDto) {

		// convert DTO to entity
		Professor professorRequest = mapper.map(professorDto, Professor.class);

		Professor professor = professorService.addNewProfessor(professorRequest);

		// convert entity to DTO
		ProfessorDTO professorResponse = mapper.map(professor, ProfessorDTO.class);

		return new ResponseEntity<ProfessorDTO>(professorResponse, HttpStatus.CREATED);
	}

	// change the request for DTO
	// change the response for DTO
	@PutMapping("/DTO/{id}")
	public ResponseEntity<Professor> updateProfessorDTO(@PathVariable long id, @RequestBody ProfessorDTO professorDto)
			throws Exception {

		// convert DTO to Entity
		Professor professorRequest = mapper.map(professorDto, Professor.class);

		Professor professor = professorService.updateProfessor(professorRequest, id);

		return ResponseEntity.ok().body(professorService.updateProfessor(professor, id));
	}

}
