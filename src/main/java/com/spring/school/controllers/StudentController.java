package com.spring.school.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.school.DTO.StudentDTO;
import com.spring.school.models.Student;
import com.spring.school.services.IStudentService;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

	private IStudentService studentService;
	private ModelMapper mapper;

	@Autowired
	public StudentController(IStudentService studentService, ModelMapper mapper) {
		super();
		this.studentService = studentService;
		this.mapper = mapper;
	}

	@GetMapping
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getEmployeeById(@PathVariable("id") long id) throws Exception {
		return new ResponseEntity<Student>(studentService.getStudentById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Student> addNewStudent(@RequestBody Student student) {
		return new ResponseEntity<Student>(studentService.addNewStudent(student), HttpStatus.CREATED);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") long id)
			throws Exception {
		return new ResponseEntity<Student>(studentService.updateStudent(student, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) throws Exception {
		studentService.deleteStudent(id);
		return new ResponseEntity<String>("Student has deleted successfully!", HttpStatus.OK);
	}

	@PutMapping("/{stud_id}/professor/{prof_id}")
	public ResponseEntity<Student> updateProfessorsList(@PathVariable("stud_id") long stud_id,
			@PathVariable("prof_id") long prof_id) {
		return new ResponseEntity<Student>(studentService.updateProfessorsList(stud_id, prof_id), HttpStatus.OK);

	}

	@PutMapping("/{stud_id}/lesson/{lesson_id}")
	public ResponseEntity<Student> updateStudentsLesson(@PathVariable("stud_id") long stud_id,
			@PathVariable("lesson_id") long lesson_id) {
		return new ResponseEntity<Student>(studentService.updateStudentsLesson(stud_id, lesson_id), HttpStatus.OK);
	}

	@GetMapping("/DTO")
	@ResponseBody
	public ResponseEntity<List<Student>> getStudentsDTO() {
		return ResponseEntity.ok().body(studentService.getAllStudents());
	}

	@GetMapping("/DTO/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable(name = "id") Long id) throws Exception {
		return ResponseEntity.ok().body(studentService.getStudentById(id));
	}

	@PostMapping("/DTO")
	public ResponseEntity<StudentDTO> createStudentDTO(@RequestBody StudentDTO studentDto) {

		// convert DTO to entity
		Student studentRequest = mapper.map(studentDto, Student.class);

		Student student = studentService.addNewStudent(studentRequest);

		// convert entity to DTO
		StudentDTO studentResponse = mapper.map(student, StudentDTO.class);

		return new ResponseEntity<StudentDTO>(studentResponse, HttpStatus.CREATED);
	}

	@PutMapping("/DTO/{id}")
	public ResponseEntity<Student> updateStudentDTO(@PathVariable long id, @RequestBody StudentDTO studentDto)
			throws Exception {

		// convert DTO to Entity
		Student studentRequest = mapper.map(studentDto, Student.class);

		Student student = studentService.updateStudent(studentRequest, id);

		return ResponseEntity.ok().body(studentService.updateStudent(student, id));
	}

}
