package com.spring.school.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.spring.school.models.Lesson;
import com.spring.school.services.ILessonService;

@RestController
@RequestMapping("/api/v1/lesson")
public class LessonController {

	private ILessonService lessonService;

	@Autowired
	public LessonController(ILessonService lessonService) {
		super();
		this.lessonService = lessonService;
	}

	@GetMapping
	public List<Lesson> getAllLessons() {
		return lessonService.getAllLessons();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Lesson> getLessonById(@PathVariable("id") long id) throws Exception {
		return new ResponseEntity<Lesson>(lessonService.getLessonById(id), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Lesson> addNewLesson(@RequestBody Lesson lesson) {
		return new ResponseEntity<Lesson>(lessonService.addNewLesson(lesson), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteLesson(@PathVariable("id") long id) throws Exception {
		lessonService.deleteLesoon(id);
		return new ResponseEntity<String>("The lesson has deleted successfully!", HttpStatus.OK);

	}

	@PutMapping("/{lesson_id}/classroom/{classroom_id}")
	public ResponseEntity<Lesson> updateLessonsClassroom(@PathVariable("lesson_id") long lesson_id,
			@PathVariable("classroom_id") long classroom_id) {
		return new ResponseEntity<Lesson>(lessonService.updateLessonsClassroom(lesson_id, classroom_id), HttpStatus.OK);
	}

}
