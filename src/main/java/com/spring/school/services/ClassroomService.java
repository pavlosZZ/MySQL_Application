package com.spring.school.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.school.models.Classroom;
import com.spring.school.repositories.IClassroomRepository;

@Service
public class ClassroomService implements IClassroomService {

	private IClassroomRepository classroomRepo;

	@Autowired
	public ClassroomService(IClassroomRepository classroomRepo) {
		super();
		this.classroomRepo = classroomRepo;
	}

	@Override
	public List<Classroom> getAllClassrooms() {
		return classroomRepo.findAll();
	}

	@Override
	public Classroom addNewClassroom(Classroom classroom) {
		return classroomRepo.save(classroom);
	}

	@Override
	public void deleteClassroom(long id) throws Exception {
		if (classroomRepo.findById(id) != null)
			classroomRepo.deleteById(id);
		else
			throw new Exception();

	}

	@Override
	public Classroom getClassroomById(long id) throws Exception {
		Classroom classroom = classroomRepo.findById(id).get();
		if (classroom != null)
			return classroom;
		else
			throw new Exception();
	}

}
