package com.spring.school.services;

import java.util.List;

import com.spring.school.models.Professor;

public interface IProfessorService {

	public List<Professor> getAllProfessors();

	public Professor addNewProfessor(Professor student);

	public Professor getProfessorById(long id) throws Exception;

	public Professor updateProfessor(Professor professor, long id) throws Exception;

	public void deleteProfessor(long id) throws Exception;
}
