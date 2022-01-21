package com.spring.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.school.models.Student;

public interface IStudentRepository extends JpaRepository<Student, Long> {

}
