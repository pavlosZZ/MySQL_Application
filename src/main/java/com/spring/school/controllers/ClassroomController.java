package com.spring.school.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.school.models.Classroom;
import com.spring.school.services.IClassroomService;

@RestController
@RequestMapping("/api/v1/classroom")
public class ClassroomController {

	private IClassroomService classroomService;

	@Autowired
	public ClassroomController(IClassroomService classroomService) {
		super();
		this.classroomService = classroomService;
	}

	@GetMapping
	public List<Classroom> getAllClassrooms() {
		return classroomService.getAllClassrooms();
	}

	@PostMapping
	public ResponseEntity<Classroom> addNewClassroom(@RequestBody Classroom classroom) {
		return new ResponseEntity<Classroom>(classroomService.addNewClassroom(classroom), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteClassroom(@PathVariable("id") long id) throws Exception {
		classroomService.deleteClassroom(id);
		return new ResponseEntity<String>("The classroom has deleted successfully!", HttpStatus.OK);
	}

}
