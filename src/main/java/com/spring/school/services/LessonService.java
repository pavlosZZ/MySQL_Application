package com.spring.school.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.school.models.Classroom;
import com.spring.school.models.Lesson;
import com.spring.school.repositories.IClassroomRepository;
import com.spring.school.repositories.ILessonRepository;

@Service
public class LessonService implements ILessonService {

	private ILessonRepository lessonRepo;

	private IClassroomRepository classroomRepo;

	@Autowired
	public LessonService(ILessonRepository lessonRepo, IClassroomRepository classroomRepo) {
		super();
		this.lessonRepo = lessonRepo;
		this.classroomRepo = classroomRepo;
	}

	@Override
	public List<Lesson> getAllLessons() {
		return lessonRepo.findAll();
	}

	@Override
	public Lesson addNewLesson(Lesson lesson) {
		return lessonRepo.save(lesson);
	}

	@Override
	public void deleteLesoon(long id) throws Exception {
		if (lessonRepo.findById(id) != null)
			lessonRepo.deleteById(id);
		else
			throw new Exception();

	}

	@Override
	public Lesson updateLessonsClassroom(long lesson_id, long classroom_id) {
		Lesson lesson = lessonRepo.findById(lesson_id).get();
		Classroom classroom = classroomRepo.findById(classroom_id).get();
		lesson.setClassroom(classroom);
		return lessonRepo.save(lesson);
	}

	@Override
	public Lesson getLessonById(long id) throws Exception {
		Optional<Lesson> lesson = lessonRepo.findById(id);
		if (lesson.isPresent())
			return lesson.get();
		else
			throw new Exception();
	}

}
