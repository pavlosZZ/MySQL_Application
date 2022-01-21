package com.spring.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.school.models.Professor;

public interface IProfessorRepository extends JpaRepository<Professor, Long> {

}
