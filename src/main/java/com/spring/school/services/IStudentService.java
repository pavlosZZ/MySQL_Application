package com.spring.school.services;

import java.util.List;

import com.spring.school.models.Student;

public interface IStudentService {

	public List<Student> getAllStudents();

	public Student getStudentById(long id) throws Exception;

	public Student addNewStudent(Student student);

	public Student updateStudent(Student student, long id) throws Exception;

	public void deleteStudent(long id) throws Exception;

	public Student updateProfessorsList(long stud_id, long prof_id);

	public Student updateStudentsLesson(long stud_id, long lesson_id);
}
