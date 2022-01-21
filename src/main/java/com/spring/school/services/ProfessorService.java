package com.spring.school.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.school.models.Professor;
import com.spring.school.repositories.IProfessorRepository;

@Service
public class ProfessorService implements IProfessorService {

	private IProfessorRepository professorRepo;

	@Autowired
	public ProfessorService(IProfessorRepository professorRepo) {
		super();
		this.professorRepo = professorRepo;
	}

	@Override
	public List<Professor> getAllProfessors() {
		return professorRepo.findAll();
	}

	@Override
	public Professor addNewProfessor(Professor professor) {
		return professorRepo.save(professor);
	}

	@Override
	public Professor getProfessorById(long id) throws Exception {
		Optional<Professor> professor = professorRepo.findById(id);
		if (professor.isPresent()) {
			return professor.get();
		} else {
			throw new Exception();
		}
	}

	@Override
	public Professor updateProfessor(Professor professor, long id) throws Exception {
		if (professorRepo.findById(id) != null) {
			professor.setId(id);
			return professorRepo.save(professor);
		} else
			throw new Exception();

	}

	@Override
	public void deleteProfessor(long id) throws Exception {
		if (professorRepo.findById(id) != null)
			professorRepo.deleteById(id);
		else
			throw new Exception();
	}

}
